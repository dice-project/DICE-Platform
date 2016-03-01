//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.ui.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.command.NestedCommandExcecutor;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.IOppositeFeatureLoader;
import org.eclipse.epf.library.edit.util.Messenger;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.util.ContentResourceScanner;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElementDescription;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;

/**
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class TypeConverter {
	private static final Comparator activityFeatureComparator = new Comparator() {

		public int compare(Object arg0, Object arg1) {
			if(arg0 == UmaPackage.Literals.BREAKDOWN_ELEMENT__SUPER_ACTIVITIES) {
				return -1;
			}
			return 0;
		}
		
	};
	
	/**
	 * Converts the specified object to a object of new type specified by <code>newType</code> while still
	 * preserving as many attributes and relationships as possible
	 * 
	 * @param object
	 * @param newType
	 * @param oldFeatureToNewFeatureMap map of feature of <code>object</code> to feature of <code>newType</code>
	 * @return new object of type <code>newType</code>
	 */
	public static final EObject convert(EObject object, EClass newType,
			Map oldFeatureToNewFeatureMap, Comparator featureComparator, Set modifiedResources) 
	{
		TypeConversionCommand cmd = createTypeConversionCommand(object, newType, oldFeatureToNewFeatureMap, featureComparator);
		Collection<Resource> resources = cmd.getModifiedResources();
		if(!resources.isEmpty()) {
			Resource[] resourceArray = new Resource[resources.size()];
			resources.toArray(resourceArray);
			IStatus status = Services.getAccessController().checkModify(resourceArray, ExtensionManager.getDefaultUserInteractionHandler().getUIContext());
			if(!status.isOK()) {
				Messenger.INSTANCE.showError(LibraryResources.convertActivityError_title, null, status);
				return null;
			}
		}
		try {
			cmd.execute();
			modifiedResources.addAll(cmd.getModifiedResources());
			return (EObject) cmd.getResult().iterator().next();
		}
		finally {
			cmd.dispose();
		}
	}
	
	private static class ResourceCopyTask {
		ContentResourceScanner scanner;
		MethodElement owner;
		String content;
		String contentPath;
		
		/**
		 * @param scanner
		 * @param owner
		 * @param content
		 * @param contentPath
		 */
		public ResourceCopyTask(ContentResourceScanner scanner, MethodElement owner, String content, String contentPath) {
			super();
			this.scanner = scanner;
			this.owner = owner;
			this.content = content;
			this.contentPath = contentPath;
		}	
				
	}
	
	public static class TypeConversionCommand extends CompoundCommand implements IResourceAwareCommand {

		private Collection<?> result;
		private EObject object;
		private HashSet<Resource> modifiedResources;
		private boolean gotModifiedResources;
		private List<ResourceCopyTask> resourceCopyTasks;
		private Resource resource;
		private Collection illegalReferencers;
		private NestedCommandExcecutor nestedCommandExcecutor;

		/**
		 * @param object
		 */
		public TypeConversionCommand(EObject object) {
			this.object = object;
			modifiedResources = new HashSet<Resource>();
			resource = object.eResource();
			if(resource != null) {
				modifiedResources.add(resource);
			}
			resourceCopyTasks = new ArrayList<ResourceCopyTask>();
			nestedCommandExcecutor = new NestedCommandExcecutor(this);
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.emf.common.command.CompoundCommand#dispose()
		 */
		public void dispose() {
			if(illegalReferencers != null) {
				illegalReferencers.clear();
			}
			if(modifiedResources != null) {
				modifiedResources.clear();
			}
			if(resourceCopyTasks != null) {
				resourceCopyTasks.clear();
			}
			if(nestedCommandExcecutor != null) {
				nestedCommandExcecutor.dispose();
			}
			super.dispose();
		}
				
		/**
		 * Gets referencers that become illegal after the type conversion
		 * 
		 * @return
		 */
		public Collection getIllegalReferencers() {
			if(illegalReferencers == null) {
				getModifiedResources();
			}
			return illegalReferencers;
		}
		
		public void copyResources() {
			if(!resourceCopyTasks.isEmpty()) {
				for (ResourceCopyTask t : resourceCopyTasks) {
					File newResourcePath = new File(ResourceHelper.getAbsoluteElementResourcePath(t.owner));
					t.scanner.setTargetRootPath(newResourcePath);
					if(t.content != null) {
						t.scanner.resolveResources(t.owner, t.content, t.contentPath);
					}
					else {
						for (EAttribute attribute : t.owner.eClass().getEAllAttributes()) {
							Object value = t.owner.eGet(attribute);
							if(value instanceof String) {
								t.scanner.resolveResources(t.owner, (String) value, t.contentPath);
							}
						}
					}
				}
			}
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.epf.library.edit.command.IResourceAwareCommand#getModifiedResources()
		 */
		public Collection<Resource> getModifiedResources() {
			if(!gotModifiedResources) {
				illegalReferencers = new HashSet();
				for (Iterator iter = commandList.iterator(); iter.hasNext();) {
					Command cmd = (Command) iter.next();
					if(cmd instanceof RemoveCommand) {
						RemoveCommand removeCommand = (RemoveCommand) cmd;
						EObject owner = removeCommand.getOwner();
						Resource ownerResource = owner.eResource();
						if(ownerResource != null && ownerResource != resource) {
							modifiedResources.add(ownerResource);
						}
						if(ownerResource != null && owner != object && !UmaUtil.isContainedBy(owner, object)) {
							illegalReferencers.add(owner);
						}
					}
					else if(cmd instanceof SetCommand) {
						SetCommand setCommand = (SetCommand) cmd;
						EObject owner = setCommand.getOwner();
						Resource ownerResource = owner.eResource();
						if(ownerResource != null && ownerResource != resource && setCommand.getValue() instanceof EObject) {
							EObject value = (EObject) setCommand.getValue();
							if(value != object || !UmaUtil.isContainedBy(value, object)) {
								modifiedResources.add(ownerResource);
							}
						}
						if(ownerResource != null && owner != object && !UmaUtil.isContainedBy(owner, object)
								&& setCommand.getValue() == setCommand.getFeature().getDefaultValue()) {
							illegalReferencers.add(owner);
						}
					}
				}
				
				for (Object obj: illegalReferencers) {
					if (obj instanceof MethodElement) {
						Resource res = ((MethodElement) obj).eResource();
						if (res != null) {
							modifiedResources.add(res);
						}
					}					
				}
				
				gotModifiedResources = true;
			}
			modifiedResources.addAll(nestedCommandExcecutor.getModifiedResources());		
			return modifiedResources;
		}
		
		private void setResult(Collection<?> result) {
			this.result = result;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.emf.common.command.CompoundCommand#getResult()
		 */
		public Collection<?> getResult() {
			return result;
		}
		
		@Override
		public void execute() {
			super.execute();			
			nestedCommandExcecutor.executeNestedCommands();
		}
		
		@Override
		public void undo() {
			nestedCommandExcecutor.undoNestedCommands();
			super.undo();
		}
		
		private List<Command> commandList() {
			return commandList;
		}
	}
	
	/**
	 * 
	 * @param object EObject
	 * @param newType EClass
	 * @param oldFeatureToNewFeatureMap Map
	 * @param featureComparator Comparator
	 * @return TypeConversionCommand
	 */
	public static final TypeConversionCommand createTypeConversionCommand(EObject object, EClass newType, 
			Map oldFeatureToNewFeatureMap, Comparator featureComparator) {
		return createTypeConversionCommand(object, newType, oldFeatureToNewFeatureMap, featureComparator, false, false);
	}
	
	/**
	 * 
	 * @param object EObject
	 * @param newType EClass
	 * @param oldFeatureToNewFeatureMap Map
	 * @param featureComparator Comparator
	 * @param removeIncomingReferences boolean
	 * @param removeVariability boolean
	 * @return TypeConversionCommand
	 */
	public static final TypeConversionCommand createTypeConversionCommand(EObject object, EClass newType, 
			Map oldFeatureToNewFeatureMap, Comparator featureComparator, 
			boolean removeIncomingReferences, boolean removeVariability) {
		TypeConversionCommand cmd = new TypeConversionCommand(object);
		EObject newObject = prepareConvert(object, newType, oldFeatureToNewFeatureMap, featureComparator, removeIncomingReferences, removeVariability, cmd);
		
		// replace object with newObject in the object's container
		//
		EObject container = object.eContainer();
		if(container != null) {
			EReference ref = object.eContainmentFeature();
			if(ref.isMany()) {
				List list = (List) container.eGet(ref);
				int index = list.indexOf(object);
				cmd.commandList().add(0, new SetCommand(null, container, ref, newObject, index));
			}
			else {
				cmd.commandList().add(0, new SetCommand(null, container, ref, newObject));
			}
		}
		
		cmd.setResult(Collections.singletonList(newObject));
		
		return cmd;
	}
	
	private static ContentResourceScanner createScanner(EObject object) {
		if(object instanceof ContentDescription && !(object instanceof BreakdownElementDescription)) {			
			File oldResourcePath = new File(ResourceHelper.getFolderAbsolutePath((MethodElement) object));
			String rootContentpath = ResourceHelper.getElementPath((MethodElement)object);
			ContentResourceScanner scanner = new ContentResourceScanner(oldResourcePath, null, rootContentpath, null);
			return scanner;
		}
		return null;
	}	
	
	/**
	 * Prepares a compound command to do a type conversion
	 * 
	 * @param object
	 * @param newType
	 * @param oldFeatureToNewFeatureMap
	 * @param featureComparator used to sort the feature list of object to convert before copying/moving
	 *        feature values to new object.
	 * @param removeIncomingReferences if true will remove all incoming references 
	 * @param compoundCommand
	 * @return EObject
	 */
	public static final EObject prepareConvert(EObject object, EClass newType, Map oldFeatureToNewFeatureMap,
			Comparator featureComparator, boolean removeIncomingReferences, boolean removeVariability,
			TypeConversionCommand compoundCommand) {
		return prepareConvert(object, newType, oldFeatureToNewFeatureMap,
				featureComparator, removeIncomingReferences, removeVariability,
				compoundCommand, 0);
	}
	
	private static final EObject prepareConvert(EObject object, EClass newType, Map oldFeatureToNewFeatureMap,
			Comparator featureComparator, boolean removeIncomingReferences, boolean removeVariability,
			TypeConversionCommand compoundCommand, int level) {
		EObject newObject = UmaFactory.eINSTANCE.create(newType);
		
		if(object instanceof MethodElement) {
			// Handle incoming references.
			// If removeIncommingReferences is false, redirect all incoming references of object to newObject
			// If removeIncommingReferences is true, remove all of them.
			//			

			MethodElement me = (MethodElement) object;
			Collection oppositeFeatures = me.getOppositeFeatures();
			if(!oppositeFeatures.isEmpty()) {				
				
				// load opposite features
				//
				Collection collection = Collections.singletonList(object);
				for (Iterator iter = ExtensionManager.getOppositeFeatureLoaders().iterator(); iter
						.hasNext();) {
					IOppositeFeatureLoader loader = (IOppositeFeatureLoader) iter.next();
					loader.loadOppositeFeatures(collection);
				}
				
				if (level == 0) {
					LibraryUtil.loadAllSkipContents(LibraryService.getInstance().getCurrentMethodLibrary());
				}
				
				Map referencerToFeatureListMap = AssociationHelper.getReferenceMap(me);
				for (Iterator iter = referencerToFeatureListMap.entrySet().iterator(); iter
						.hasNext();) {
					Map.Entry entry = (Map.Entry) iter.next();
					EObject referencer = (EObject) entry.getKey();
					for (Iterator iterator = ((Collection)entry.getValue()).iterator(); iterator
							.hasNext();) {
						EReference feature = (EReference) iterator.next();
						if(!removeIncomingReferences && feature.getEType().isInstance(newObject)) {
							// new type is compatible, reference can be preserved
							//
							if(feature.isMany()) {
								List list = (List) referencer.eGet(feature);
								int index = list.indexOf(object);
								compoundCommand.append(new SetCommand(null, referencer, feature, newObject, index));
							}
							else {
								compoundCommand.append(new SetCommand(null, referencer, feature, newObject));
							}
						}
						else {
							// new type is not compatible, reference must be removed
							//
							if(feature.isMany()) {
								compoundCommand.append(new RemoveCommand(null, referencer, feature, object));
							}
							else {
								compoundCommand.append(new SetCommand(null, referencer, feature, feature.getDefaultValue()));
							}
						}
					}
				}
			}
		}
		
		// copy all the features
		//
		List newObjectFeatures = newType.getEAllStructuralFeatures();
		List objectFeatures = new ArrayList(object.eClass().getEAllStructuralFeatures());
		if(removeVariability) {
			objectFeatures.remove(UmaPackage.Literals.VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT);
			objectFeatures.remove(UmaPackage.Literals.VARIABILITY_ELEMENT__VARIABILITY_TYPE);
		}
		if(featureComparator != null) {
			Collections.sort(objectFeatures, featureComparator);
		}
		ContentResourceScanner scanner = createScanner(object);
		for (Iterator iter = objectFeatures.iterator(); iter.hasNext();) {
			EStructuralFeature feature = (EStructuralFeature) iter.next();
			if(!newObjectFeatures.contains(feature)) {
				if(oldFeatureToNewFeatureMap != null) {
					Object f = oldFeatureToNewFeatureMap.get(feature);
					if(f != null && newObjectFeatures.contains(f)) {
						feature = (EStructuralFeature) f;
					}
					else {
						feature = null;
					}
				}
				else {
					feature = null;
				}
			}
			if(feature != null && feature.isChangeable()) {				
				boolean featureHandled = false;
				if(feature instanceof EReference) {
					EReference ref = (EReference) feature;
					if(ref == UmaPackage.Literals.DESCRIBABLE_ELEMENT__PRESENTATION) {
						if(ContentDescriptionFactory.hasPresentation((MethodElement) object)) {
							ContentDescription content = (ContentDescription) object.eGet(ref);
							EClass contentDescType = ContentDescriptionFactory.getContentDescriptionType(newType);
							if(contentDescType != null && contentDescType != content.eClass()) {
								EObject newContent = prepareConvert(content, contentDescType, null, null, removeIncomingReferences, removeVariability, compoundCommand, level + 1);
								
								// remove old content
								//
								EObject container = content.eContainer();
								if(container != null) {
									compoundCommand.append(new SetCommand(null, object, ref, null));
									Resource resource = content.eResource();
									if(resource != null) {
										compoundCommand.modifiedResources.add(content.eResource());
									}
								}
								
								content = (ContentDescription) newContent;
							}
							else {
								compoundCommand.resourceCopyTasks.add(new ResourceCopyTask(createScanner(content), content, null, "")); //$NON-NLS-1$
							}
							compoundCommand.append(new SetCommand(null, newObject, ref, content));
						}
						featureHandled = true;
					}					
					else if(!ref.isContainment() && ref.getEOpposite() != null) {
						// Reference is bi-directional. If opposite reference is a list, replace object
						// with new object at the same position in the list.
						//
						EReference oppositeRef = ref.getEOpposite();						
						if(oppositeRef.isMany()) {
							Object value = object.eGet(ref);
							if(value != null) {
								if(ref.isMany()) {
									for (Iterator iterator = ((Collection)value)
											.iterator(); iterator.hasNext();) {
										EObject otherEnd = (EObject) iterator.next();
										List list = (List) otherEnd.eGet(oppositeRef);
										int index = list.indexOf(object);
										compoundCommand.append(new SetCommand(null, otherEnd, oppositeRef, newObject, index));
									}
								}
								else {
									EObject otherEnd = (EObject) value;
									List list = (List) otherEnd.eGet(oppositeRef);
									int index = list.indexOf(object);
									compoundCommand.append(new SetCommand(null, otherEnd, oppositeRef, newObject, index));								}
							}
							featureHandled = true;
						}
					}
				}
				if(!featureHandled) {
					Object value = object.eGet(feature);
					if (feature == UmaPackage.Literals.NAMED_ELEMENT__NAME) {
						// validate name
						String newName = checkName(object, newType);
						if (newName != null) {
							value = newName;
						}
					}
					if(scanner != null && value instanceof String && object instanceof MethodElement) {
						String str = (String) value;
						if(!StrUtil.isBlank(str)) {
							compoundCommand.resourceCopyTasks.add(new ResourceCopyTask(scanner, (MethodElement)object, str, "")); //$NON-NLS-1$
						}
					}
					compoundCommand.append(new SetCommand(null, newObject, feature, value));
				}
			}
		}
						
		return newObject;
	}

	/**
	 * Converts an activity to new type.
	 * 
	 * @param oldActivity Activity
	 * @param newType EClass
	 * @return Activity
	 */
	public static Activity convertActivity(Activity oldActivity, EClass newType) {
		// activity is already this type
		//
		if (newType == oldActivity.eClass()) {
			return null;
		}
		
		HashSet<Resource> modifiedResources = new HashSet<Resource>();
		Activity newActivity = (Activity) TypeConverter.convert(oldActivity, newType, null, activityFeatureComparator, modifiedResources);

		// make oldActivity a proxy so reference to it can be resolved to newActivity
		//
		URI uri = PersistenceUtil.getProxyURI(newActivity);
		((InternalEObject)oldActivity).eSetProxyURI(uri);
				
		// save modified resources
		//
		if (!modifiedResources.isEmpty()) {
			ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil.getCurrentPersister().getFailSafePersister();
			try {
				for (Resource res : modifiedResources) {
					persister.save(res);
				}
				persister.commit();

			} catch (Exception e) {
				LibraryUIPlugin
						.getDefault()
						.getMsgDialog()
						.displayError(
								LibraryResources.convertActivityError_title,
								NLS
										.bind(
												LibraryResources.saveConvertedActivityError_msg,
												newActivity.getName()),
								LibraryResources.error_reason, e);

				persister.rollback();
			}
		}
		
		return newActivity;
	}		
	
	/**
	 * 
	 * @param typeID int
	 * @return EClass
	 */
	public static EClass getActivityType(int typeID) {
		switch (typeID) {
		case UmaPackage.ACTIVITY:
			return UmaPackage.Literals.ACTIVITY;
		case UmaPackage.ITERATION:
			return UmaPackage.Literals.ITERATION;
		case UmaPackage.PHASE:
			return UmaPackage.Literals.PHASE;
		default:
			return null;
		}
	}

	
	/**
	 * @param oldGuidance
	 * @param newGuidance
	 * @return
	 */
	private static String checkName(EObject object, EClass newType) {
		// TODO Auto-generated method stub
		if (object instanceof NamedElement) {
			NamedElement e = (NamedElement)object;
			final IValidator validator = IValidatorFactory.INSTANCE.createNameValidator(e.eContainer(), e, newType);
			String msg = validator.isValid(e.getName());
			if(msg != null) {
				String featureTxt = TngUtil.getFeatureText(UmaPackage.eINSTANCE
						.getNamedElement_Name());
				String title = LibraryEditResources.resolveNameConflictDialog_title; 
				String dlgMsg = NLS.bind(
						LibraryEditResources.resolveNameConflictDialog_text,
						StrUtil.toLower(featureTxt), e.getName());
				String currentValue = (String) e.eGet(UmaPackage.eINSTANCE
						.getNamedElement_Name());
	
				IInputValidator inputValidator = new IInputValidator() {
					public String isValid(String newText) {
						return validator.isValid(newText);
					}
				};
	
				InputDialog inputDlg = new InputDialog(
						MsgBox.getDefaultShell(), title, dlgMsg, currentValue,
						inputValidator);
				if (inputDlg.open() == Window.CANCEL) {
					throw new OperationCanceledException();
				}
				return inputDlg.getValue();
			}
		}
		return null;
	}

}