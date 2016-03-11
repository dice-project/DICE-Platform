//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.command;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage.Literals;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.IRunnableWithProgress;
import org.eclipse.epf.library.edit.util.ITextReferenceReplacer;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.Messenger;
import org.eclipse.epf.library.edit.util.Misc;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.AbstractStringValidator;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.library.edit.validation.NameChecker;
import org.eclipse.epf.library.edit.validation.internal.ContentElementNameValidator;
import org.eclipse.epf.library.edit.validation.internal.UniquenessValidator;
import org.eclipse.epf.library.edit.validation.internal.ValidatorFactory;
import org.eclipse.epf.services.IFileBasedLibraryPersister;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.MethodUnit;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.edit.domain.TraceableAdapterFactoryEditingDomain;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.epf.uma.util.MessageException;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;

/**
 * This command is used to add a method element to a containing method element.
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class MethodElementAddCommand extends CommandWrapper implements
		IResourceAwareCommand {
	public static final Map<EClass, Collection<EStructuralFeature>> typeToExcludedFeaturesFromCheck = new HashMap<EClass, Collection<EStructuralFeature>>();
	
	public static void excludeFromCheck(EClass type, EStructuralFeature feature) {
		Collection<EStructuralFeature> features = typeToExcludedFeaturesFromCheck.get(type);
		if(features == null) {
			features = new HashSet<EStructuralFeature>();
		}
		features.add(feature);
	}

	protected boolean canUndo = true;

	protected AddCommand addCommand;

	// protected Map elementToNewNameMap;
	protected Map elementToOldPluginMap;

	protected boolean removeXRefRequired;

	protected MethodPlugin ownerPlugin;

	protected ArrayList addList;

	protected MultiStatus status = new MultiStatus(LibraryEditPlugin.INSTANCE
			.getSymbolicName(), IStatus.OK, "", null); //$NON-NLS-1$

	protected boolean runWithProgress;

	protected ArrayList<EStructuralFeature> featuresToCheck;

	protected ArrayList nestedCommands;

	private HashSet modifiedResources;

	public MethodElementAddCommand(Command command) {
		this(command, true);
	}

	public MethodElementAddCommand(Command command, boolean runWithProgress) {
		super(command);
		addCommand = (AddCommand) TngUtil.unwrap(command);
		setRunWithProgress(runWithProgress);
	}

	public void setRunWithProgress(boolean runWithProgress) {
		this.runWithProgress = runWithProgress;
	}

	public boolean canUndo() {
		return canUndo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.CommandWrapper#getResult()
	 */
	public Collection getResult() {
		if (status.isOK()) {
			return super.getResult();
		}
		return Collections.EMPTY_LIST;
	}

	private void handleError(IStatus errorStatus) {
		Messenger.INSTANCE.showError(getLabel(), LibraryEditResources.createElementError_msg, errorStatus);
		status.add(errorStatus);
		status.addAll(errorStatus);
		return;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.CommandWrapper#dispose()
	 */
	public void dispose() {	
		// dispose the nested commands
		if (nestedCommands != null && !nestedCommands.isEmpty()) {
			for (int i = nestedCommands.size() - 1; i > -1; i--) {
				Command cmd = (Command) nestedCommands.get(i);
				try {
					cmd.dispose();
				} catch (Exception e) {
				}
			}
		}
		super.dispose();
	}
	
	
	public void execute() {
		Object shell = LibraryEditPlugin.getDefault().getContext();
		// Check whether the owner resource can be modified.
		//
		Collection resourcesToChange = getModifiedResources();
		IStatus execStatus = UserInteractionHelper.checkModify(
				resourcesToChange, shell);
		if (!execStatus.isOK()) {
			handleError(execStatus);
			return;
		}
		execStatus = UserInteractionHelper.checkConfigurationsToUpdate(
				addCommand, shell);
		if (!execStatus.isOK()) {
			handleError(execStatus);
			return;
		}
		if (willSaveModifiedResources()
				&& !UserInteractionHelper.checkOutOfSynch(resourcesToChange)) {
			status.add(Status.CANCEL_STATUS);
			return;
		}

		// if(!checkFolder(shell)) {
		// return;
		// }

		doAdd();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.CommandWrapper#undo()
	 */
	public void undo() {
		undoNestedCommands();
		super.undo();
	}

	/**
	 * 
	 */
	private void undoNestedCommands() {
		if (nestedCommands != null && !nestedCommands.isEmpty()) {
			for (int i = nestedCommands.size() - 1; i > -1; i--) {
				Command cmd = (Command) nestedCommands.get(i);
				try {
					if (cmd.canUndo()) {
						cmd.undo();
					}
				} catch (Exception e) {
					LibraryEditPlugin.getDefault().getLogger().logError(e);
				} finally {
					try {
						cmd.dispose();
					} catch (Exception e) {
					}
				}
			}
		}
	}

	/**
	 * @return
	 */
	private boolean checkFolder(Object shell) {
		// if any of the elements to be added will be saved in its own file
		// check if the folder to save the file to can be modified
		//
		IFileBasedLibraryPersister persister = (IFileBasedLibraryPersister) Services.getLibraryPersister(Services.XMI_PERSISTENCE_TYPE);
		ArrayList foldersToCheck = new ArrayList();
		for (Iterator iter = addCommand.getCollection().iterator(); iter
				.hasNext();) {
			Object element = TngUtil.unwrap(iter.next());
			if (element instanceof MethodElement
					&& persister.hasOwnResource(element)) {
				String folder = persister.getFolderRelativePath(
								(MethodElement) element);
				MethodPlugin plugin = UmaUtil.getMethodPlugin(addCommand
						.getOwner());
				String baseFolder = null;
				if (plugin == null) {
					MethodLibrary lib = UmaUtil.getMethodLibrary(addCommand
							.getOwner());
					baseFolder = new File(lib.eResource().getURI()
							.toFileString()).getParent();
				} else {
					baseFolder = new File(plugin.eResource().getURI()
							.toFileString()).getParent();
				}
				foldersToCheck.add(baseFolder + File.separator + folder);
			}
		}
		if (!foldersToCheck.isEmpty()) {
			String[] folders = new String[foldersToCheck.size()];
			foldersToCheck.toArray(folders);
			IStatus status = Services.getFileManager()
					.checkModify(folders, shell);
			if (!status.isOK()) {
				handleError(status);
				return false;
			}
		}

		return true;
	}

	public IStatus getStatus() {
		return status;
	}

	/**
	 * @return
	 */
	private boolean isMove() {
		Command cmd = TngUtil.unwrap(command);
		if (cmd instanceof AddCommand) {
			for (Iterator iter = ((AddCommand) cmd).getCollection().iterator(); iter
					.hasNext();) {
				EObject element = (EObject) iter.next();
				if (UmaUtil.getMethodPlugin(element) == null) {
					return false;
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param ownerPlugin
	 * @param e
	 * @return List of Reference objects that keeps the info of illegal outgoing
	 *         references
	 * @see Reference
	 */
	public static Collection<Reference> getIllegalOutgoingReferences(
			MethodPlugin ownerPlugin, EObject e, Collection<Reference> illegalReferences) {
		Map<String, Boolean> map = new HashMap<String, Boolean>(); 
		if (illegalReferences == null) {
			illegalReferences = new ArrayList<Reference>();
		}
		for (EContentsEList.FeatureIterator<EObject> featureIterator = (EContentsEList.FeatureIterator<EObject>) e
				.eCrossReferences().iterator(); featureIterator.hasNext();) {
			EObject eObject = (EObject) featureIterator.next();
			EStructuralFeature feature = featureIterator.feature();
			if (!feature.isDerived()) {
				MethodPlugin plugin = UmaUtil.getMethodPlugin(eObject);
				if (plugin != null && plugin != ownerPlugin
						&& !Misc.isBaseOf(plugin, ownerPlugin, map)) {
					// illegal reference to be removed
					//
					illegalReferences
							.add(new Reference(e, feature, eObject, -1));
					if (feature == UmaPackage.eINSTANCE
							.getVariabilityElement_VariabilityBasedOnElement()) {
						feature = UmaPackage.eINSTANCE.getVariabilityElement_VariabilityType();
						illegalReferences
							.add(new Reference(e, feature, e.eGet(feature), -1));
					}
				}
			}
		}
		return illegalReferences;
	}

	private static void removeReferences(Collection<Reference> references) {
		for (Reference ref: references) {
			if (ref.feature.isMany()) {
				List<?> list = ((List<?>) ref.owner.eGet(ref.feature));
				list.remove(ref.value);
			} else {
				ref.owner.eSet(ref.feature, null);
			}
		}
	}

	/**
	 * @param element
	 * @return List of Reference objects
	 */
	public static Collection<Reference> removeIllegalReferences(MethodPlugin ownerPlugin,
			EObject e) {
		Collection<Reference> removeRefs = getIllegalOutgoingReferences(ownerPlugin, e,
				null);

		// remove illegal references
		//
		removeReferences(removeRefs);

		return removeRefs;
	}

	private static boolean hasNext(Iterator iter) {
		try {
			return iter.hasNext();
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	public static boolean hasIllegalReference(MethodPlugin ownerPlugin,
			EObject e, Collection moveList) {
		Map<String, Boolean> map = new HashMap<String, Boolean>(); 
		for (EContentsEList.FeatureIterator featureIterator = (EContentsEList.FeatureIterator) e
				.eCrossReferences().iterator(); hasNext(featureIterator);) {
			EObject eObject = (EObject) featureIterator.next();

			// check if the x-ref object is in the move list
			//
			if (!moveList.contains(eObject)) {
				MethodPlugin plugin = UmaUtil.getMethodPlugin(eObject);
				if (plugin != null && plugin != ownerPlugin
						&& !Misc.isBaseOf(plugin, ownerPlugin, map)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks if the given MethodElement is referenced illegally after the move.
	 * 
	 * @param ownerPlugin
	 * @param e
	 * @param moveList
	 * @return
	 */
	public static boolean isReferencedIllegally(MethodPlugin ownerPlugin,
			MethodElement e, Collection moveList) {
		Map<String, Boolean> map = new HashMap<String, Boolean>(); 
		Collection references = AssociationHelper.getReferences(e);
		for (Iterator iter = references.iterator(); iter.hasNext();) {
			MethodElement element = (MethodElement) iter.next();

			if (isExcludedFromOutgoingReferenceCheck(element)) {
				continue;
			}

			if (!moveList.contains(element)) {
				MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
				if (plugin != null && plugin != ownerPlugin
						&& !Misc.isBaseOf(ownerPlugin, plugin, map)) {
					return true;
				}
			}
		}
		return false;
	}

	private static void checkModify(EObject element) {
		IStatus status = TngUtil.checkEdit(element, null);
		if (!status.isOK()) {
			throw new MessageException(TngUtil.getMessage(status));
		}
	}

	public static Collection removeIllegalReferencesTo(
			MethodPlugin ownerPlugin, MethodElement e, Collection moveList) {
		Map<String, Boolean> map = new HashMap<String, Boolean>(); 
		ArrayList affectedReferencers = new ArrayList();
		Collection references = AssociationHelper.getReferences(e);
		for (Iterator iter = references.iterator(); iter.hasNext();) {
			MethodElement element = (MethodElement) iter.next();
			if (!moveList.contains(element)) {
				MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
				if (plugin != null && plugin != ownerPlugin
						&& !Misc.isBaseOf(ownerPlugin, plugin, map)) {
					checkModify(element);
					removeReferences(element, e);
					affectedReferencers.add(element);
				}
			}
		}
		return affectedReferencers;
	}

	/**
	 * Gets all cross references of <code>referencer</code> to
	 * <code>referenced</code>
	 * 
	 * @param referencer
	 * @param referenced
	 * @param xReferences
	 *            output collection of EStructuralFeature objects
	 * @return collection of EStructuralFeature objects
	 */
	private static Collection getXReferences(EObject referencer,
			EObject referenced, Collection xReferences) {
		if (xReferences == null) {
			xReferences = new ArrayList();
		}
		for (EContentsEList.FeatureIterator featureIterator = (EContentsEList.FeatureIterator) referencer
				.eCrossReferences().iterator(); featureIterator.hasNext();) {
			EObject eObject = (EObject) featureIterator.next();
			EStructuralFeature feature = featureIterator.feature();
			// if(feature.isMany()) {
			// if(((List)referencer.eGet(feature)).contains(referenced)) {
			// xReferences.add(feature);
			// }
			// }
			// else
			if (eObject == referenced) {
				xReferences.add(feature);
			}
		}
		return xReferences;
	}

	/**
	 * Remove all references to <code>referenced</code> from
	 * <code>referencer</code>
	 * 
	 * @param referencer
	 * @param referenced
	 * @return Collection of references to the <code>referenced</code>
	 */
	public static Collection removeReferences(EObject referencer,
			EObject referenced) {
		Collection removeRefs = getXReferences(referencer, referenced, null);

		// remove illegal references
		//
		for (Iterator iter = removeRefs.iterator(); iter.hasNext();) {
			EStructuralFeature f = (EStructuralFeature) iter.next();
			if (f.isMany()) {
				((Collection) referencer.eGet(f)).remove(referenced);
			} else {
				referencer.eSet(f, null);
			}
		}

		return removeRefs;
	}

	public static boolean hasIllegalReferenceIncludingAllChildren(
			MethodPlugin ownerPlugin, MethodElement e, Collection moveList) {
		if (ownerPlugin == UmaUtil.getMethodPlugin(e)) {
			// move within the same plugin, no further check
			//
			return false;
		}
		if (hasIllegalReference(ownerPlugin, e, moveList))
			return true;
		for (Iterator iterator = e.eAllContents(); iterator.hasNext();) {
			if (hasIllegalReference(ownerPlugin, (EObject) iterator.next(),
					moveList))
				return true;
		}
		return false;
	}

	protected void superExecute() {
		super.execute();
	}

	protected Collection getFeaturesToCheck() {
		if (featuresToCheck == null) {
			featuresToCheck = new ArrayList<EStructuralFeature>();
			featuresToCheck.add(UmaPackage.eINSTANCE.getNamedElement_Name());
			
		}
		return featuresToCheck;
	}

	protected IValidator createValidator(EObject e, EStructuralFeature feature) {
		IValidator validator = null;

		if(addCommand.getFeature() instanceof EReference){
			validator = IValidatorFactory.INSTANCE.createValidator(addCommand.getOwner(), (EReference) addCommand.getFeature(), (EClass)null, e, feature);
		}

		return validator;
	}

	/**
	 * @param e
	 * @param objectsToCheckName
	 */
	private void getOffStringToCheckName(Object e, Collection objectsToCheckName) {
		// if(addCommand.getOwner() instanceof ContentPackage &&
		// addCommand.getFeature() ==
		// UmaPackage.eINSTANCE.getMethodPackage_ChildPackages()
		// && e instanceof ContentPackage) {
		// for (Iterator iter = ((ContentPackage)e).eAllContents();
		// iter.hasNext();) {
		// Object element = iter.next();
		// if(element instanceof ContentElement) {
		// objectsToCheckName.add(element);
		// }
		// }
		// }
		// else
		if (addCommand.getOwner() instanceof ProcessPackage
				&& addCommand.getFeature() == UmaPackage.eINSTANCE
						.getMethodPackage_ChildPackages()
				&& e instanceof ProcessPackage
				&& !(e instanceof ProcessComponent)) {
			getProcessComponents((ProcessPackage) e, objectsToCheckName);
		}
	}

	/**
	 * @param e
	 * @param objectsToCheckName
	 */
	private static void getProcessComponents(ProcessPackage pkg,
			Collection objectsToCheckName) {
		for (Iterator iter = pkg.getChildPackages().iterator(); iter.hasNext();) {
			Object childPkg = iter.next();
			if (childPkg instanceof ProcessComponent) {
				objectsToCheckName.add(childPkg);
			} else if (childPkg instanceof ProcessPackage) {
				getProcessComponents((ProcessPackage) childPkg,
						objectsToCheckName);
			}
		}
	}

	protected boolean checkStringValue(NamedElement e,
			EStructuralFeature feature, Collection objectsToAdd) {
		Collection excludedFeaturesToCheck = typeToExcludedFeaturesFromCheck.get(e.eClass()); 
		if(excludedFeaturesToCheck != null && excludedFeaturesToCheck.contains(feature)) {
			return true;
		}
		
		IValidator validator = createValidator(e, feature);
		if (validator != null) {
			String newName = null;
			try {
				newName = checkStringValue(e, feature, validator, objectsToAdd);
			} catch (OperationCanceledException ex) {
				return false;
			}
			if (newName != null) {
				newName = newName.trim();
				e.eSet(feature, newName);
				featureChanged(e, feature, newName);
			}
		}
		return true;
	}

	protected boolean checkStringValue(EStructuralFeature feature) {
		ArrayList objectsToCheckName = new ArrayList();
		Collection objectsToAdd = addCommand.getCollection();

		// feature value check
		//
		for (Iterator iter = objectsToAdd.iterator(); iter.hasNext();) {
			NamedElement e = (NamedElement) iter.next();
			if (!checkStringValue(e, feature, objectsToAdd)) {
				return false;
			}

			getOffStringToCheckName(e, objectsToCheckName);
		}

		for (Iterator iter = objectsToCheckName.iterator(); iter.hasNext();) {
			NamedElement e = (NamedElement) iter.next();
			EStructuralFeature f = UmaPackage.eINSTANCE.getNamedElement_Name();
			if (!checkStringValue(e, f, objectsToCheckName)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Subclass can override this method to deal with element change during add
	 * 
	 * @param feature
	 * @param newValue
	 */
	protected void featureChanged(EObject object, EStructuralFeature feature,
			Object newValue) {

	}

	private static Collection getElementsToCheckName(AddCommand addCommand) {
		ArrayList elements = new ArrayList(addCommand.getCollection());
		for (Iterator iter = addCommand.getCollection().iterator(); iter
				.hasNext();) {
			Object element = iter.next();
			if (element instanceof ProcessPackage) {
				// need to get all process components in this package to check
				// name
				//
				Iterator iterator = new AbstractTreeIterator(element, false) {

					/**
					 * Comment for <code>serialVersionUID</code>
					 */
					private static final long serialVersionUID = 1L;

					protected Iterator getChildren(Object object) {
						if (object instanceof ProcessComponent) {
							return Collections.EMPTY_LIST.iterator();
						} else if (object instanceof ProcessPackage) {
							return ((ProcessPackage) object).getChildPackages()
									.iterator();
						} else {
							return Collections.EMPTY_LIST.iterator();
						}
					}

				};
				while (iterator.hasNext()) {
					Object pkg = iterator.next();
					if (pkg instanceof ProcessComponent) {
						elements.add(pkg);
					}
				}
			}
		}
		return elements;
	}

	/**
	 * Checks the name of all elements to be added.
	 * 
	 * @param addCommand
	 * @return a element to new feature name map or <code>null</code> if user
	 *         canceled the operation.
	 */
	protected static Map checkName(AddCommand addCommand) {
		Collection elementsToCheck = getElementsToCheckName(addCommand);
		HashMap elementToNewNameMap = new HashMap();

		// feature value check
		//
		for (Iterator iter = elementsToCheck.iterator(); iter.hasNext();) {
			NamedElement e = (NamedElement) iter.next();
			IValidator validator;
			if (e instanceof ContentElement) {
				validator = new ContentElementNameValidator(addCommand
						.getOwner(), addCommand.getFeature(),
						(ContentElement) e, new ValidatorFactory.TypeFilter(e));
			} else {
				validator = IValidatorFactory.INSTANCE.createNameValidator(
						addCommand.getOwner(), e);
			}

			String newName = null;
			try {
				newName = checkStringValue(e, UmaPackage.eINSTANCE
						.getNamedElement_Name(), validator, elementsToCheck);
			} catch (OperationCanceledException ex) {
				return null;
			}
			if (newName != null) {
				// new name required, check if the element can be modified
				//
				IStatus status = UserInteractionHelper.checkModify(e, LibraryEditPlugin.getDefault().getContext());
				String title = LibraryEditResources.errorDialog_title; 
				String msg = MessageFormat.format(
						LibraryEditResources.errorDialog_cannotRename,
						new Object[] { TngUtil.getTypeText(e), e.getName(),
								newName });
				while (!status.isOK()) {
					IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
					if (uiHandler != null) {
						if (uiHandler.selectOne(new int[] {
								IUserInteractionHandler.ACTION_OK,
								IUserInteractionHandler.ACTION_CANCEL }, title,
								msg, null) == IUserInteractionHandler.ACTION_CANCEL) {
							return null;
						}
					}
					else {
						// cancel the operation if there is no user interaction handler
						//
						return null;
					}
				}
				elementToNewNameMap.put(e, newName);
			}
		}

		return elementToNewNameMap;
	}

	/**
	 * Checks feature value
	 * 
	 * @param e
	 * @param feature
	 * @param validator
	 * @param objectsToAdd
	 * @return new feature value that user has choosen or <code>null</code> if
	 *         the feature value is valid
	 * @throws OperationCanceledException
	 */
	public static String checkStringValue(NamedElement e,
			EStructuralFeature feature, IValidator validator,
			Collection objectsToAdd) throws OperationCanceledException {
		// TODO: The validator is display an error message that is too long for
		// the dialog.
		// feature value check
		final StringValidator strValidator = getStringValidator();
		strValidator.setElements(objectsToAdd);
		strValidator.setFeature(feature);
		strValidator.setValidator(validator);

		String str = (String) e.eGet(feature);
		if (str != null && str.length() > 0) {
			String msg = strValidator.isValid(str);
			if (msg != null) {
				String featureTxt = TngUtil.getFeatureText(feature);
				String title = LibraryEditResources.resolveNameConflictDialog_title; 
				String dlgMsg = NLS.bind(
						LibraryEditResources.resolveNameConflictDialog_text,
						StrUtil.toLower(featureTxt), e.getName());
				String currentValue = (String) e.eGet(feature);

				IValidator inputValidator = new AbstractStringValidator() {

					public String isValid(String newText) {
						if (strValidator != null) {
							return UserInteractionHelper
									.getSimpleErrorMessage(strValidator
											.isValid(newText));
						}
						return null;
					}

				};
				IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
				if(uiHandler != null) {
					UserInput input = new UserInput("", UserInput.TEXT, false, null, null, inputValidator, null); //$NON-NLS-1$
					input.setInput(currentValue);
					List<UserInput> userInputs = Collections.singletonList(input);
					if(uiHandler.requestInput(title, dlgMsg, userInputs)) {
						return (String) userInputs.get(0).getInput();
					}
					else {
						throw new OperationCanceledException();
					}
				}
			}
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.CommandWrapper#getAffectedObjects()
	 */
	public Collection getAffectedObjects() {
		Collection affectedObjects = super.getAffectedObjects();
		if (affectedObjects == null) {
			EObject owner = addCommand.getOwner();
			affectedObjects = owner == null ? Collections.EMPTY_SET
					: Collections.singleton(owner);
		}
		return affectedObjects;
	}

	protected Collection getElementsToCheckForIllegalReferences() {
		return addCommand.getCollection();
	}

	private static boolean isExcludedFromOutgoingReferenceCheck(Object e) {
		// don't check on ProcessPackage, ProcessElement for illegal references
		// b/c process validation is based on configuration, not plugin
		// visibility
		//
		return e instanceof ProcessPackage || e instanceof ProcessElement;
	}

	protected String checkForIllegalReferences() {
		Collection elements = getElementsToCheckForIllegalReferences();
		addList = new ArrayList(elements);
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			// String newName = (String) elementToNewNameMap.get(element);
			// if(newName != null) {
			// element.setName(newName);
			// }
			for (Iterator iterator = element.eAllContents(); iterator.hasNext();) {
				addList.add(iterator.next());
			}
		}

		// check if there is any illegal reference in the moved objects
		//		
		ownerPlugin = UmaUtil.getMethodPlugin(addCommand.getOwner());
		removeXRefRequired = false;
		if (ownerPlugin != null) {
			find_xPluginRef: for (Iterator iter = addCommand.getCollection()
					.iterator(); iter.hasNext();) {
				Object element = iter.next();
	
				if (isExcludedFromOutgoingReferenceCheck(element)) {
					continue find_xPluginRef;
				}
	
				if (element instanceof MethodElement) {
					if (hasIllegalReferenceIncludingAllChildren(ownerPlugin,
							(MethodElement) element, addList)) {
						removeXRefRequired = true;
						break find_xPluginRef;
					}
				}
			}
		}

		if (removeXRefRequired) {
			return LibraryEditResources.invalidReferencesError_reason; 
		}
		return null;
	}

	public Collection getModifiedResources() {
		if (modifiedResources == null) {
			modifiedResources = new HashSet();
		}
		if (addFeatureIsContainment()) {
			ILibraryPersister persister = Services.getDefaultLibraryPersister();
			boolean saveOwnerResource = false;
			for (Iterator iter = addCommand.getCollection().iterator(); iter
					.hasNext();) {
				Object e = (Object) iter.next();
				if (!persister.hasOwnResourceWithoutReferrer(e)) {
					saveOwnerResource = true;
				}
			}
			if (saveOwnerResource) {
				modifiedResources.add(addCommand.getOwner().eResource());
			}
		} else {
			modifiedResources.add(addCommand.getOwner().eResource());
		}

		if (nestedCommands != null && !nestedCommands.isEmpty()) {
			for (int i = nestedCommands.size() - 1; i > -1; i--) {
				Command cmd = (Command) nestedCommands.get(i);
				try {
					if (cmd instanceof IResourceAwareCommand) {
						Collection resources = ((IResourceAwareCommand) cmd)
								.getModifiedResources();
						if (resources != null) {
							modifiedResources.addAll(resources);
						}
					}
				} catch (Exception e) {
					LibraryEditPlugin.getDefault().getLogger().logError(e);
				} finally {
					
				}
			}
		}

		return modifiedResources;
	}

	protected boolean willSaveModifiedResources() {
		return true;
	}
	
	private ILibraryPersister.FailSafeMethodLibraryPersister getPersister() {
		return Services.getDefaultLibraryPersister().getFailSafePersister();
	}

	protected void saveModifiedResources() {
		ILibraryPersister.FailSafeMethodLibraryPersister persister = getPersister();
		try {

			// save the modified resources
			//
			for (Iterator iter = getModifiedResources().iterator(); iter
					.hasNext();) {
				Resource resource = (Resource) iter.next();
				try {
					persister.save(resource);
					canUndo = false;
				} catch (Exception e) {
					LibraryEditPlugin.getDefault().getLogger().logError(e);
					String msg = NLS.bind(
							LibraryEditResources.saveFileError_reason, 
							resource.getURI().isFile() ? resource
									.getURI().toFileString() : resource.toString());
					status.add(new SaveStatus(IStatus.ERROR,
							LibraryEditPlugin.INSTANCE.getSymbolicName(),
							SaveStatus.SAVE_ERROR, msg, e));
					return;
				}
			}

			if (resMgr != null) {
				Collection elementsToCopyRefContents = new ArrayList();
				for (Iterator iter = getAffectedObjects().iterator(); iter
						.hasNext();) {
					elementsToCopyRefContents.add(TngUtil.unwrap(iter.next()));
				}

				Collection modified = resMgr.copyReferencedContents(
						elementsToCopyRefContents, elementToOldPluginMap);
				modified
						.addAll(replaceTextReferences(elementsToCopyRefContents));
				if (modified != null && !modified.isEmpty()) {
					for (Iterator iter = modified.iterator(); iter.hasNext();) {
						Resource resource = (Resource) iter.next();
						try {
							persister.save(resource);
						} catch (Exception e) {
							LibraryEditPlugin.getDefault().getLogger()
									.logError(e);
							String msg = NLS.bind(
									LibraryEditResources.saveFileError_reason,
									resource.getURI().toFileString());
							status.add(new SaveStatus(IStatus.ERROR,
									LibraryEditPlugin.INSTANCE
											.getSymbolicName(),
									SaveStatus.SAVE_ERROR, msg, e));
							return;
						}
					}
				}
			}

			persister.commit();
		} catch (RuntimeException e) {
			StringWriter msg = new StringWriter();
			e.printStackTrace(new PrintWriter(msg));
			IStatus newStatus = new SaveStatus(IStatus.ERROR,
					LibraryEditPlugin.INSTANCE.getSymbolicName(),
					SaveStatus.SAVE_ERROR, msg.toString(), e);
			status.add(newStatus);
		} finally {
			if (status.getSeverity() == IStatus.ERROR) {
				try {
					persister.rollback();
				} catch (Exception e) {
					LibraryEditPlugin.INSTANCE.log(e);
					StringWriter msg = new StringWriter();
					e.printStackTrace(new PrintWriter(msg));
					IStatus newStatus = new SaveStatus(IStatus.ERROR,
							LibraryEditPlugin.INSTANCE.getSymbolicName(),
							SaveStatus.ROLLBACK_ERROR, msg.toString(), e);
					status.add(newStatus);
				}
			}
		}
	}

	/**
	 * If textual descriptions in the copied elements contain references (URLs)
	 * to other elements within the same copied process then replace these
	 * references with references that point to the new elements in the copied
	 * structures.
	 */
	private Collection replaceTextReferences(
			Collection elementsToCopyRefContents) {
		Collection modifiedResources = new HashSet();
		ITextReferenceReplacer txtRefReplacer = ExtensionManager
				.getTextReferenceReplacer();
		if (txtRefReplacer == null)
			return modifiedResources;

		// get map
		EditingDomain ed = addCommand.getDomain();
		if (ed instanceof TraceableAdapterFactoryEditingDomain) {
			Map copyToOriginalMap = ((TraceableAdapterFactoryEditingDomain) ed)
					.getCopyToOriginalMap();
			// the TextReferenceReplacer expects a map of old-to-new. we
			// have a map of new-to-old, so convert it
			Map oldToNewObjectMap = new HashMap();
			for (Iterator iter = copyToOriginalMap.entrySet().iterator(); iter
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object key = entry.getKey();
				Object val = entry.getValue();
				if (key != null && val != null)
					oldToNewObjectMap.put(val, key);
			}
			for (Iterator iter = elementsToCopyRefContents.iterator(); iter
					.hasNext();) {
				EObject element = (EObject) iter.next();
				for (Iterator childIter = element.eAllContents(); childIter
						.hasNext();) {
					EObject child = (EObject) childIter.next();
					for (Iterator attributes = child.eClass()
							.getEAllAttributes().iterator(); attributes
							.hasNext();) {
						EAttribute attribute = (EAttribute) attributes.next();
						if (attribute.isChangeable()
								&& !attribute.isDerived()
								&& (attribute.isMany() || child
										.eIsSet(attribute))
								&& attribute.getEAttributeType()
										.getInstanceClass() == Literals.STRING
										.getInstanceClass()) {
							String text = (String) child.eGet(attribute);
							if (text != null) {
								String newtext = txtRefReplacer.replace(text,
										child, oldToNewObjectMap);
								if (!newtext.equals(text)) {
									child.eSet(attribute, newtext);
									modifiedResources.add(child.eResource());
								}
							}
						}
					}
				}
			}
		}
		return modifiedResources;
	}

	protected void doAdd() {
		try {
			// check feature values
			//
			for (Iterator iter = getFeaturesToCheck().iterator(); iter
					.hasNext();) {
				EStructuralFeature feature = (EStructuralFeature) iter.next();
				if (!checkStringValue(feature)) {
					// user canceled the operaion
					//
					return;
				}
			}

			boolean showWarning = false;
			EditingDomain ed = addCommand.getDomain();
			if (ed instanceof TraceableAdapterFactoryEditingDomain) {
				Map copyToOriginalMap = ((TraceableAdapterFactoryEditingDomain) ed)
						.getCopyToOriginalMap();

				// check first element of addCommand.getCollection()
				// if it's a key in the CopyToOriginalMap, we are pasting and
				// may need to warn the user
				// if it's not a key in the CopyToOriginalMap, then we are
				// probably adding a new element
				if (addCommand.getCollection() != null
						&& addCommand.getCollection().size() > 0
						&& copyToOriginalMap.containsKey(addCommand
								.getCollection().toArray()[0])) {
					elementToOldPluginMap = new HashMap();
					Iterator iter = copyToOriginalMap.entrySet().iterator();
					while (iter.hasNext()) {
						Map.Entry entry = (Entry) iter.next();
						if (entry.getValue() instanceof MethodElement
								&& !(entry.getValue() instanceof MethodConfiguration)) {
							MethodPlugin plugin = UmaUtil
									.getMethodPlugin((MethodElement) entry
											.getValue());
							if (plugin != null) {
								elementToOldPluginMap.put(entry.getKey(),
										plugin);
							} else {
								// plugin == null, original element was probably
								// deleted
								showWarning = true;
							}
						}
					}
				}
			}

			if (showWarning) {
					Messenger.INSTANCE
						.showWarning(
								getLabel(),
								LibraryEditResources.MethodElementAddCommand_originalNotFoundWarning_msg);
			}

			final StringBuffer msgBuff = new StringBuffer();
			IRunnableWithProgress operation = new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor) {
					String msg = checkForIllegalReferences();
					if (msg != null) {
						msgBuff.append(msg);
					}
				}

			};

			IProgressMonitor defaultMonitor = null;
			if (!runWithProgress) {
				defaultMonitor = new NullProgressMonitor();
			}

			if (runWithProgress) {
				UserInteractionHelper.runWithProgress(operation,
						LibraryEditResources.checkingReferencesTask_name); 
			} else {
				try {
					operation.run(defaultMonitor);
				} catch (RuntimeException e) {
					LibraryEditPlugin.INSTANCE.log(e);
					throw e;
				} catch (Exception e) {
					LibraryEditPlugin.INSTANCE.log(e);
					throw new RuntimeException(e.toString());
				}
			}
			if (msgBuff.length() > 0) {
				IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
				if(uiHandler  != null) {
					int ret = uiHandler.selectOne(new int[] {IUserInteractionHandler.ACTION_YES, IUserInteractionHandler.ACTION_NO }, 
							getLabel(), msgBuff.toString(), null);
					if(ret == IUserInteractionHandler.ACTION_NO) {
						return;
					}
				}
			}

			status = new MultiStatus(
					LibraryEditPlugin.INSTANCE.getSymbolicName(),
					IStatus.OK,
					LibraryEditResources.MethodElementAddCommand_errorSavingFiles,
					null); 
			operation = new IRunnableWithProgress() {

				public void run(IProgressMonitor monitor) {
					if (removeXRefRequired) {
						monitor.subTask(""); //$NON-NLS-1$

						for (Iterator iter = addList.iterator(); iter.hasNext();) {
							EObject element = (EObject) iter.next();
							if (element instanceof MethodElement) {
								removeIllegalReferences(ownerPlugin, element);
							}
						}
					}

					// add the elements
					//
					superExecute();
					
					//Note: no need to undo this prop change if fails since the added pkgs instances would be removed
					PropUtil propUtil = PropUtil.getPropUtil();
					for (Object addedItem : addList) {
						if (addedItem instanceof MethodPackage) {
							MethodPackage pkg = (MethodPackage) addedItem;
							Boolean b = propUtil.getBooleanValue(pkg, PropUtil.Pkg_loadCheck);
							if (b == null || !b.booleanValue() ) {
								propUtil.setBooleanValue(pkg, PropUtil.Pkg_loadCheck, true);
							}
						}						
					}

					executeNestedCommands();

					if (willSaveModifiedResources()) {
						IStatus result = saveNewElementsWithOwnResource();
						if (result.isOK()) {
							monitor
									.subTask(LibraryEditResources.savingModifiedFilesTask_name); 
							saveModifiedResources();
						} else {
							status.add(result);
						}
					}

					if (!status.isOK()) {
						// FIXME: this causes dead-lock at
						// LibraryProcessor.notifyListeners(). The method
						// is synchronized, this might be the cause of the
						// dead-lock
						//
						undo();
						
						if(CommandStatusChecker.hasRollbackError(status)) {
							Collection<?> createdElements = command.getResult();
							ILibraryPersister persister = Services.getDefaultLibraryPersister();
							for (Object object : createdElements) {
								if(object instanceof MethodElement) {
									try {
										persister.delete((MethodElement) object);
									}
									catch(Exception e) {
										LibraryEditPlugin.getDefault().getLogger().logError(e);
									}
								}
							}								
						}						
					}
				}

			};
			if (runWithProgress) {
				UserInteractionHelper.runWithProgress(operation, ""); //$NON-NLS-1$
			} else {
				try {
					operation.run(defaultMonitor);
				} catch (RuntimeException e) {
					LibraryEditPlugin.INSTANCE.log(e);
					throw e;
				} catch (Exception e) {
					LibraryEditPlugin.INSTANCE.log(e);
					throw new RuntimeException(e.toString());
				}
			}

			if (!status.isOK()) {
				Messenger.INSTANCE.showError(getLabel(), LibraryEditResources.error_msgWithDetails, status);
				return;
			}
		} catch (RuntimeException e) {
			Messenger.INSTANCE.showError(getLabel(), LibraryEditResources.error_msg, null, e);
		}
	}

	private boolean addFeatureIsContainment() {
		return addCommand.getFeature() instanceof EReference
				&& ((EReference) addCommand.getFeature()).isContainment();
	}

	/**
	 * Saves added elements that are new to the library and can have own
	 * resource that no other resource refers to (like MethodConfiguration)
	 * 
	 * @throws Exception
	 */
	protected IStatus saveNewElementsWithOwnResource() {
		if (addFeatureIsContainment()) {
			ILibraryPersister persister = Services.getDefaultLibraryPersister();
			for (Iterator iter = addList.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof MethodElement) {
					MethodElement me = (MethodElement) element;

					if (me instanceof ContentElement ||
						me instanceof MethodPlugin || 
						me instanceof MethodConfiguration) {						
						IStatus is = checkFilePathLengthBeforeSave(me);
						if (is != null) {
							return is;
						}
					}

					if (persister.hasOwnResourceWithoutReferrer(me)) {
						try {
							persister.save(me);
						} catch (Exception e) {
							LibraryEditPlugin.getDefault().getLogger()
									.logError(e);
							String msg = e.getMessage();
							if (StrUtil.isBlank(msg)) {
								msg = e.toString();
							}
							return new SaveStatus(IStatus.ERROR,
									LibraryEditPlugin.INSTANCE
											.getSymbolicName(),
									SaveStatus.SAVE_ERROR, msg, e);
						}
					}
				}
			}
		}

		return Status.OK_STATUS;
	}

	private IStatus checkFilePathLengthBeforeSave(MethodElement element) {
		MethodElement parentElementWithResource = null;
		if (element instanceof MethodPlugin || element instanceof MethodConfiguration) {
			parentElementWithResource = UmaUtil.getMethodLibrary(element);
		} else {
			parentElementWithResource = UmaUtil.getMethodPlugin(element);
		}
		if (! NameChecker.checkFilePathLength(parentElementWithResource, element, element.getName(), 
				IValidator.MaxFilePathNameLength, null)) {
			String msg = NLS.bind(LibraryEditResources.filePathNameTooLong_msg, 
					new Object[] { new Integer(IValidator.MaxFilePathNameLength) } );
			return new SaveStatus(IStatus.ERROR,
					LibraryEditPlugin.INSTANCE
							.getSymbolicName(),
					SaveStatus.SAVE_ERROR, msg, null);
		}
		return null;
	}

	/**
	 * 
	 */
	protected void executeNestedCommands() {
		List nestedCommandProviders = ExtensionManager
				.getNestedCommandProviders();
		if (!nestedCommandProviders.isEmpty()) {
			if (!addList.isEmpty()) {
				nestedCommands = new ArrayList();
				for (Iterator iter = nestedCommandProviders.iterator(); iter
						.hasNext();) {
					INestedCommandProvider cmdProvider = (INestedCommandProvider) iter
							.next();
					try {
						Command cmd = cmdProvider.createRelatedObjects(addList,
								this);
						if (cmd != null && cmd.canExecute()) {
							cmd.execute();
							nestedCommands.add(cmd);
						}
					} catch (Exception e) {
						LibraryEditPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}

	}

	private static class ContainmentInfo {
		EObject container;

		int index;

		ContainmentInfo(EObject container, int index) {
			this.container = container;
			this.index = index;
		}
	}

	private static class IllegalReferenceRemover {
		private MethodPlugin targetPlugin;

		private Collection elementsToMove;

		/**
		 * list of Reference objects
		 * 
		 * @see Reference
		 */
		private List illegalReferences;

		private Set affectedResources;

		private ArrayList removedReferences;

		private boolean searchOutgoingRefs;

		private boolean searchIncomingRefs;

		public IllegalReferenceRemover(MethodPlugin targetPlugin,
				Collection elementsToMove, boolean searchOutgoingRefs,
				boolean searchIncomingRefs) {
			this.targetPlugin = targetPlugin;
			this.elementsToMove = elementsToMove;
			this.searchOutgoingRefs = searchOutgoingRefs;
			this.searchIncomingRefs = searchIncomingRefs;
		}

		public Set getAffectedResources() {
			if (affectedResources == null) {
				illegalReferences = new ArrayList();
				for (Iterator iterator = elementsToMove.iterator(); iterator
						.hasNext();) {
					Object obj = iterator.next();

					if (obj instanceof MethodElement) {
						MethodElement e = (MethodElement) obj;

						// get outgoing references
						//
						if (searchOutgoingRefs
								&& !isExcludedFromOutgoingReferenceCheck(obj)) {
							getIllegalOutgoingReferences(targetPlugin, e,
									illegalReferences);
						}

						// get incoming references
						//
						if (searchIncomingRefs) {
							getIllegalIncomingReferences(e);
						}
					}
				}

				affectedResources = new HashSet();
				for (Iterator iter = illegalReferences.iterator(); iter
						.hasNext();) {
					Reference ref = (Reference) iter.next();
					if (ref.owner.eResource() != null) {
						affectedResources.add(ref.owner.eResource());
					}
				}
			}
			return affectedResources;
		}

		private void getIllegalIncomingReferences(MethodElement e) {
			Collection references = AssociationHelper
					.getReferences(e);
			for (Iterator iter = references.iterator(); iter
					.hasNext();) {
				MethodElement element = (MethodElement) iter
						.next();
				if (!elementsToMove.contains(element)) {
					MethodPlugin plugin = UmaUtil
							.getMethodPlugin(element);
					if (plugin != null
							&& plugin != targetPlugin
							&& !Misc.isBaseOf(targetPlugin,
									plugin, new HashMap<String, Boolean>())) {
						Collection xRefs = getXReferences(
								element, e, null);
						for (Iterator iter1 = xRefs.iterator(); iter1
								.hasNext();) {
							EStructuralFeature f = (EStructuralFeature) iter1
									.next();
							illegalReferences
									.add(new Reference(element,
											f, e));
						}

					}
				}
			}
		}

		public void removeIllegalReferences() {
			removedReferences = new ArrayList();
			for (Iterator iter = illegalReferences.iterator(); iter.hasNext();) {
				Reference ref = (Reference) iter.next();
				if (ref.feature.isMany()) {
					List list = ((List) ref.owner.eGet(ref.feature));
					// TODO: needs revisit
					// This code throws
					// org.eclipse.emf.common.util.BasicEList$BasicIndexOutOfBoundsException
					// sometimes
					//
					// if(ref.index != -1) {
					// list.remove(ref.index);
					// }
					// else {
					// list.remove(ref.value);
					// }

					list.remove(ref.value);
				} else {
					if (ref.feature == UmaPackage.eINSTANCE
							.getVariabilityElement_VariabilityType()) {
						//Should query the default value if extended for any attribute feature
						ref.owner.eSet(ref.feature, VariabilityType.NA);
					} else {
						ref.owner.eSet(ref.feature, null);
						if (ref.feature == UmaPackage.eINSTANCE
							.getVariabilityElement_VariabilityBasedOnElement()) {
							ref.owner.eSet(UmaPackage.eINSTANCE
									.getVariabilityElement_VariabilityType(), VariabilityType.NA);
						}
					}
				}
				removedReferences.add(ref);
			}

		}

		public void restoreRemovedReferences() {
			if (removedReferences == null) {
				return;
			}
			for (int i = removedReferences.size() - 1; i > -1; i--) {
				Reference ref = (Reference) removedReferences.get(i);
				if (ref.feature.isMany()) {
					List list = ((List) ref.owner.eGet(ref.feature));
					if (ref.index != -1) {
						try {
							// TODO: need revisits
							//
							if (!list.contains(ref.value)) {
								if (ref.index < list.size()) {
									list.add(ref.index, ref.value);
								} else {
									list.add(ref.value);
								}
							}
						} catch (RuntimeException e) {
							throw e;
						}
					} else {
						list.add(ref.value);
					}
				} else {
					ref.owner.eSet(ref.feature, ref.value);
				}
			}
		}
	}

	public static class MoveOperation extends CommandWrapper {
		/** Constants for state of the move operation */
		private static final int STATE_START = 0;

		private static final int STATE_SAVED_FILES = 80;

		private static final int STATE_MOVED_FILES = 70;

		private static final int STATE_COPIED_RESOURCES = 90;

		private static final int STATE_END = 100;

		// private Command command;
		private AddCommand addCommand;

		private Collection moveList;

		private boolean removeXRefRequired = false;

		private boolean isRefenrecedIllegally = false;

		private MethodPlugin ownerPlugin;

		private Map elementToOldPluginMap;

		private Map elementToNewNameMap;

		private IProgressMonitor monitor;

		private Object shell;

		private HashSet movedResources;

		protected MultiStatus status;

		/**
		 * Current state of the move operation, it can be one of the state
		 * constants
		 */
		private int state;

		protected Set modifiedResources;

		private IllegalReferenceRemover illegalReferenceRemover;

		private HashMap elementToOldContainerMap;

		private RefPluginsInfo refPluginsInfo;
		
		private MethodPlugin pluginForAddingTagetAsBase;

		public MoveOperation(Command command, IProgressMonitor monitor,
				Object shell) {
			super(command);
			addCommand = (AddCommand) TngUtil.unwrap(command);
			this.monitor = monitor;
			this.shell = shell;
		}
		
		public Map getElementToOldPluginMap() {
			return elementToOldPluginMap;
		}

		/**
		 * @return Returns the status.
		 */
		public IStatus getStatus() {
			return status;
		}

		/**
		 * Checks if reloading library is needed in case of move failure
		 * 
		 * @return
		 * @see #getStatus()
		 */
		public boolean reloadNeeded() {
			return status != null
					&& CommandStatusChecker.hasRollbackError(status);
		}

		public void undo() {
			// undo name change
			//
			if (elementToNewNameMap != null) {
				for (Iterator iter = elementToNewNameMap.entrySet().iterator(); iter
						.hasNext();) {
					Map.Entry entry = (Map.Entry) iter.next();
					NamedElement e = (NamedElement) entry.getKey();
					e.setName((String) entry.getValue());
				}
			}

			if (elementToOldContainerMap != null) {
				for (Iterator iter = elementToOldContainerMap.entrySet()
						.iterator(); iter.hasNext();) {
					Map.Entry entry = (Map.Entry) iter.next();
					EObject e = (EObject) entry.getKey();
					ContainmentInfo containmentInfo = (ContainmentInfo) entry
							.getValue();
					EStructuralFeature f = addCommand.getFeature();
					if (f.isMany()) {
						((List) containmentInfo.container.eGet(f)).add(
								containmentInfo.index, e);
					} else {
						containmentInfo.container.eSet(f, e);
					}
				}
			}

			if (illegalReferenceRemover != null) {
				illegalReferenceRemover.restoreRemovedReferences();
			}
			
			if (refPluginsInfo != null && !refPluginsInfo.refPluginsToAdd.isEmpty()) {
				ownerPlugin.getBases().removeAll(refPluginsInfo.refPluginsToAdd);
			}
			
			if (pluginForAddingTagetAsBase != null) {
				pluginForAddingTagetAsBase.getBases().remove(ownerPlugin);
			}
			
			if (pkgsMarkedByLoadCheck != null) {
				PropUtil propUtil = PropUtil.getPropUtil();
				for (MethodPackage pkg : pkgsMarkedByLoadCheck) {
					propUtil.setBooleanValue(pkg, PropUtil.Pkg_loadCheck, false);
				}
			}
		}
		
		@Override
		public void execute() {
			run();
		}
		
		public void run() {
			state = STATE_START;

			status = new MultiStatus(LibraryEditPlugin.INSTANCE
					.getSymbolicName(), IStatus.OK,
					LibraryEditResources.error_reason, null); 

			if (checkName()) {
				// check if the configurations that will be updated after this
				// command can be modified
				//
				IStatus execStatus = movingCC() ? Status.OK_STATUS : UserInteractionHelper
						.checkConfigurationsToUpdate(addCommand, shell);
				if (!execStatus.isOK()) {
					Messenger.INSTANCE.showError(LibraryEditResources.moveDialog_title, null, execStatus);
					return;
				}

				final StringBuffer msgBuffer = new StringBuffer();
				IRunnableWithProgress runnable = new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						monitor
								.subTask(LibraryEditResources.checkingReferencesTask_name); 
						String msg = checkForIllegalReferences();
						if (msg != null) {
							msgBuffer.append(msg);
						}
					}

				};

				IStatus stat = UserInteractionHelper.getUIHelper().runInModalContext(runnable, true, monitor, shell);
				if(!stat.isOK()) {
					status.add(stat);
					return;
				}

				if (msgBuffer.length() > 0) {
					if (! addRefPlugins()) {
						return;
					}
				}

				// save reference to old resource of all elements in the
				// moveList
				//
				final Map elementToOldResourceMap = new HashMap();
				for (Iterator iter = moveList.iterator(); iter.hasNext();) {
					EObject element = (EObject) iter.next();
					if (element instanceof MethodElement) {
						// save the reference to old resource
						//
						elementToOldResourceMap.put(element, element
								.eResource());
					}
				}

				//Never remove references with new code, but keep the old code here for easy reference
				if (false) {
					removeReferences();
				} else {
					modifiedResources = new HashSet();
				}

				// set new name if there is any
				//
				for (Iterator iter = elementToNewNameMap.entrySet().iterator(); iter
						.hasNext();) {
					Map.Entry entry = (Map.Entry) iter.next();
					NamedElement element = (NamedElement) entry.getKey();
					element.setName((String) entry.getValue());
					modifiedResources.add(element.eResource());
					if (element instanceof MethodUnit) {
						movedResources.add(element.eResource());
						if (element instanceof ContentElement) {
							ContentElement e = (ContentElement) element;
							if (ContentDescriptionFactory.hasPresentation(e)) {
								movedResources.add(e.getPresentation());
							}
						}
					}
				}

				// move elements
				//
				runnable = new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						monitor
								.setTaskName(LibraryEditResources.movingTask_name); 
						doMove(monitor, elementToOldResourceMap,
								modifiedResources);
					}

				};
				stat = UserInteractionHelper.getUIHelper().runInModalContext(runnable, true, monitor, shell);
				if(!stat.isOK()) {
					undo();
					status.add(stat);
					return;
				}


				// check moved resources that are not in modifiable resources
				// for unmodifiable
				//
				List resources = new ArrayList(movedResources);
				resources.removeAll(modifiedResources);
				execStatus = UserInteractionHelper
						.checkModify(resources, shell);
				if (!execStatus.isOK()) {
					Messenger.INSTANCE.showError(LibraryEditResources.moveDialog_title, null, execStatus);

					undo();
					return;
				}

				// save resources
				//
				runnable = new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						try {
							IStatus saveStatus = save(monitor);
							if (!saveStatus.isOK()) {
								status.add(saveStatus);
							}
						} catch (RuntimeException e) {
							LibraryEditPlugin.INSTANCE.log(e);
							throw e;
						}
					}

				};
				stat = UserInteractionHelper.getUIHelper().runInModalContext(runnable, true, monitor, shell);
				if(!stat.isOK()) {
					status.add(stat);
					return;
				}

				// run nested commands
				//
				NestedCommandExcecutor nestedCommandExcecutor = new NestedCommandExcecutor(this);
				try {
					nestedCommandExcecutor.executeNestedCommands();
				}
				finally {
					nestedCommandExcecutor.dispose();
				}
			}

			state = STATE_END;
		}

		@Deprecated
		private void removeReferences() {
			IStatus execStatus;
			IRunnableWithProgress runnable;
			IStatus stat;
			if (removeXRefRequired || isRefenrecedIllegally) {
				// get set of resources that will be modified during this
				// move
				//
				illegalReferenceRemover = new IllegalReferenceRemover(
						ownerPlugin, moveList, removeXRefRequired,
						isRefenrecedIllegally);
				runnable = new IRunnableWithProgress() {

					public void run(IProgressMonitor monitor)
							throws InvocationTargetException,
							InterruptedException {
						monitor
								.subTask(LibraryEditResources.checkAffectedResourcesTask_name); 
						modifiedResources = illegalReferenceRemover
								.getAffectedResources();
					}

				};
				stat = UserInteractionHelper.getUIHelper().runInModalContext(runnable, true, monitor, shell);
				if(!stat.isOK()) {
					status.add(stat);
//						return;
				}

				// check affected resources for unmodifiable
				execStatus = UserInteractionHelper.checkModify(
						modifiedResources, shell);
				if (!execStatus.isOK()) {
					Messenger.INSTANCE.showError(LibraryEditResources.moveDialog_title, null, execStatus);
//						return;
				}

				try {
					monitor
							.subTask(LibraryEditResources.removingReferencestask_name); 
					illegalReferenceRemover.removeIllegalReferences();
				} catch (Exception e) {
					undo();
					String msg = TngUtil.toStackTraceString(e);
					status.add(new Status(IStatus.ERROR,
							LibraryEditPlugin.INSTANCE.getSymbolicName(),
							0, msg, e));
				}
			} else {
				modifiedResources = new HashSet();
				monitor.subTask(""); //$NON-NLS-1$
			}
		}
		
		private boolean addRefPlugins() {
			IUserInteractionHandler uiHandler = ExtensionManager.getDefaultUserInteractionHandler();
			if (uiHandler == null) {
				return false;
			}
				
//					Old code: ask confirmation to remove illegal references						
//						int ret = uiHandler.selectOne(new int[] {IUserInteractionHandler.ACTION_YES, IUserInteractionHandler.ACTION_NO }, 
//								LibraryEditResources.moveDialog_title, msgBuffer.toString(), null);
//						if(ret == IUserInteractionHandler.ACTION_NO) {
//							return;
//						}
//					New code: ask confirmation to add required plug-ins	
			refPluginsInfo = calRefPluginsInfo(ownerPlugin);
			List<String> pluginsToAdd = new ArrayList<String>();
			for (MethodPlugin p : refPluginsInfo.refPluginsToAdd) {
				pluginsToAdd.add(p.getName());
			}
			if (pluginsToAdd.size() > 1) {
				Collections.sort(pluginsToAdd);
			}
			
			if (! pluginsToAdd.isEmpty()) {
				String str = " " + this.ownerPlugin.getName() + ":";//$NON-NLS-1$ //$NON-NLS-2$
				for (String p : pluginsToAdd) {
					str += "\n" + p;//$NON-NLS-1$
				}						
				int ret = uiHandler.selectOne(new int[] {
						IUserInteractionHandler.ACTION_YES,
						IUserInteractionHandler.ACTION_NO,
						IUserInteractionHandler.ACTION_CANCEL,
						},
						LibraryEditResources.moveDialog_title,
						LibraryEditResources.moveDialog_addRefPluginsWarningText + "\n\n" + //$NON-NLS-1$ 
						LibraryEditResources.moveDialog_addRefPluginsText + str, null);
			 
				if (ret == IUserInteractionHandler.ACTION_CANCEL) {
					refPluginsInfo.refPluginsToAdd.clear();
					return false;
					
				} else if (ret == IUserInteractionHandler.ACTION_NO) {
					refPluginsInfo.refPluginsToAdd.clear();
					
				} else {
//					ownerPlugin.getBases().addAll(refPluginsInfo.refPluginsToAdd);
					//Double check
					Map<String, Boolean> map = new HashMap<String, Boolean>();
					for (MethodPlugin baseToAdd : refPluginsInfo.refPluginsToAdd) {
						if (baseToAdd != ownerPlugin && !Misc.isBaseOf(ownerPlugin, baseToAdd, map) && !Misc.isBaseOf(baseToAdd, ownerPlugin, map)) {
							ownerPlugin.getBases().add(baseToAdd);
						}
					}					
				}
							
				return true;
			}
			
			pluginForAddingTagetAsBase = getPluginForAddingTagetAsBase(true);
			if (pluginForAddingTagetAsBase != null) {
				String str = " " + pluginForAddingTagetAsBase.getName() + ":";//$NON-NLS-1$ //$NON-NLS-2$
				str += "\n" + ownerPlugin.getName();//$NON-NLS-1$					
				int ret = uiHandler.selectOne(new int[] {
						IUserInteractionHandler.ACTION_YES,
						IUserInteractionHandler.ACTION_NO,
						IUserInteractionHandler.ACTION_CANCEL,
						},
						LibraryEditResources.moveDialog_title,
						LibraryEditResources.moveDialog_addRefPluginsWarningText + "\n\n" + //$NON-NLS-1$ 
						LibraryEditResources.moveDialog_addRefPluginsText + str, null);
				if (ret == IUserInteractionHandler.ACTION_CANCEL) {
					pluginForAddingTagetAsBase = null;
					if (! refPluginsInfo.refPluginsToAdd.isEmpty()) {
						ownerPlugin.getBases().removeAll(refPluginsInfo.refPluginsToAdd);
						refPluginsInfo.refPluginsToAdd.clear();
					}
					return false;
					
				} else if (ret == IUserInteractionHandler.ACTION_NO) {
						pluginForAddingTagetAsBase = null;						
					
				} else {
//					pluginForAddingTagetAsBase.getBases().add(ownerPlugin);	
					//Double check
					Map<String, Boolean> map = new HashMap<String, Boolean>();
					if (pluginForAddingTagetAsBase != ownerPlugin
							&& !Misc.isBaseOf(ownerPlugin,
									pluginForAddingTagetAsBase, map)
							&& !Misc.isBaseOf(pluginForAddingTagetAsBase,
									ownerPlugin, map)) {
						pluginForAddingTagetAsBase.getBases().add(ownerPlugin);
					}
				}
				
				return true;
			}						
			
			if (! refPluginsInfo.refPluginsCircular.isEmpty() || getPluginForAddingTagetAsBase(false) != null) {
				int ret = uiHandler.selectOne(new int[] {
						IUserInteractionHandler.ACTION_YES,
						IUserInteractionHandler.ACTION_CANCEL,
						},
						LibraryEditResources.moveDialog_title,
						LibraryEditResources.moveDialog_addRefPluginsWarningText1, null);
				if (ret == IUserInteractionHandler.ACTION_CANCEL) {
					return false;					
				} 
			}
			
			return true;
		}

		private MethodPlugin getPluginForAddingTagetAsBase(boolean testCircular) {
			MethodPlugin srcPlugin = null;
			Set<MethodElement> moveSet = new HashSet<MethodElement>();
			for (Object obj : moveList) {
				if (obj instanceof MethodElement) {
					if (srcPlugin == null) {
						srcPlugin = UmaUtil
								.getMethodPlugin((MethodElement) obj);
					}
					moveSet.add((MethodElement) obj);
				}
			}
			if (srcPlugin == null) {
				return null;
			}

			Map<String, Boolean> map = new HashMap<String, Boolean>();
			if (Misc.isBaseOf(ownerPlugin, srcPlugin, map) || testCircular && (Misc.isBaseOf(srcPlugin, ownerPlugin, map))) {
				return null;
			}
			
			CustomCategory srcRoot = TngUtil.getRootCustomCategory(srcPlugin);
			
			for (MethodElement element : moveSet) {
				for (MethodElement referencing : (Collection<MethodElement>) AssociationHelper
						.getReferences(element)) {
					if (!moveSet.contains(referencing)
							&& srcPlugin == UmaUtil
									.getMethodPlugin(referencing) && referencing != srcRoot) {
						return srcPlugin;
					}
				}

			}

			return null;
		}


		static class RefPluginsInfo {
			Set<MethodPlugin > refPluginsToAdd = new HashSet<MethodPlugin>();
			Set<MethodPlugin> refPluginsCircular = new HashSet<MethodPlugin>();
			Set<MethodPlugin> refPluginsToAddBase = new HashSet<MethodPlugin>(); 
		}

		private RefPluginsInfo calRefPluginsInfo(MethodPlugin ownerPlugin) {
			RefPluginsInfo info = new RefPluginsInfo();
			Set<MethodPlugin> refPluginsToAdd = info.refPluginsToAdd;
			Set<MethodPlugin> refPluginsCircular = info.refPluginsCircular;
			Set<MethodPlugin> refPluginsToAddBase = info.refPluginsToAddBase;

			List<MethodPlugin> referencedPlugnList = new ArrayList<MethodPlugin>();
			for (EObject element : (Collection<EObject>) moveList) {
				if (element instanceof MethodElement) {
					Collection<Reference> iReferences = getIllegalOutgoingReferences(
							ownerPlugin, element, null);
					for (Reference ref : iReferences) {
						Object value = ref.getValue();
						if (value instanceof MethodElement) {
							MethodPlugin p = UmaUtil
									.getMethodPlugin((MethodElement) value);
							if (p != null) {
								if (refPluginsToAdd.add(p)) {
									referencedPlugnList.add(p);
								}
							}
						}
					}
				}
			}

			Map<String, Boolean> map = new HashMap<String, Boolean>();
			for (MethodPlugin plugin : referencedPlugnList) {
				if (Misc.isBaseOf(ownerPlugin, plugin, map)) {
					refPluginsCircular.add(plugin);
					refPluginsToAdd.remove(plugin);

				} else if (refPluginsToAdd.contains(plugin)) {
					List<MethodPlugin> toRmoveList = new ArrayList<MethodPlugin>();
					for (MethodPlugin testBasePlugin : refPluginsToAdd) {
						if (Misc.isBaseOf(testBasePlugin, plugin, map)) {
							refPluginsToAddBase.add(testBasePlugin);
							toRmoveList.add(testBasePlugin);
						}
					}
					refPluginsToAdd.removeAll(toRmoveList);
				}
			}

			return info;
		}

		/**
		 * interacts with user
		 * 
		 * @return
		 */
		private boolean checkName() {
			elementToNewNameMap = MethodElementAddCommand.checkName(addCommand);
			return elementToNewNameMap != null;
		}

		private boolean movingCC() {
			return this instanceof MoveOperationExt;
		}
		
		/**
		 * Long running method
		 * 
		 * @return
		 */
		private String checkForIllegalReferences() {			
			elementToOldPluginMap = new HashMap();

			moveList = new ArrayList(addCommand.getCollection());
			movedResources = new HashSet();

			for (Iterator iter = addCommand.getCollection().iterator(); iter
					.hasNext();) {
				EObject element = (EObject) iter.next();

				// // set new name if there is any
				// //
				// String newName = (String) elementToNewNameMap.get(element);
				// if(newName != null) {
				// ((NamedElement)element).setName(newName);
				// if(element instanceof MethodUnit) {
				// movedResources.add(element.eResource());
				// if(element instanceof ContentElement) {
				// ContentElement e = (ContentElement) element;
				// if(ContentDescriptionFactory.hasPresentation(e)) {
				// movedResources.add(e.getPresentation());
				// }
				// }
				// }
				// }

				if (element instanceof MethodElement) {
					elementToOldPluginMap.put(element, UmaUtil
							.getMethodPlugin(element));
				}
				for (Iterator iterator = element.eAllContents(); iterator
						.hasNext();) {
					moveList.add(iterator.next());
				}
			}

			// check if there is any illegal reference in the moved objects
			//			
			ownerPlugin = UmaUtil.getMethodPlugin(addCommand.getOwner());			
			find_xPluginRef: for (Iterator iter = addCommand.getCollection()
					.iterator(); iter.hasNext();) {
				Object element = iter.next();

				if (isExcludedFromOutgoingReferenceCheck(element)) {
					continue find_xPluginRef;
				}

				if (element instanceof MethodElement) {
					if (hasIllegalReferenceIncludingAllChildren(ownerPlugin,
							(MethodElement) element, moveList)) {
						removeXRefRequired = true;
						break find_xPluginRef;
					}
				}
			}

			find_illegalReferencer: for (Iterator iter = moveList.iterator(); iter
					.hasNext();) {
				Object element = iter.next();
				if (element instanceof MethodElement) {
					if (referencedIllegally(ownerPlugin,
							(MethodElement) element, moveList)) {
						isRefenrecedIllegally = true;
						break find_illegalReferencer;
					}
				}
			}
			if (removeXRefRequired || isRefenrecedIllegally) {
				return LibraryEditResources.invalidReferencesError_reason; 
			}
			return null;
		}

		protected boolean referencedIllegally(MethodPlugin ownerPlugin,
				MethodElement e, Collection moveList) {			
			return isReferencedIllegally(ownerPlugin, e, moveList);	
		}
		
		private Set<MethodPackage> pkgsMarkedByLoadCheck;
		
		/**
		 * Long running method
		 * 
		 * @return
		 */
		protected void doMove(IProgressMonitor monitor,
				Map elementToOldResourceMap, Set modifiedResources) {
			monitor.subTask(""); //$NON-NLS-1$

			PropUtil propUtil = PropUtil.getPropUtil();
			elementToOldContainerMap = new HashMap();
			pkgsMarkedByLoadCheck = new HashSet<MethodPackage>();
			for (Iterator iter = addCommand.getCollection().iterator(); iter
					.hasNext();) {
				EObject element = (EObject) iter.next();
				if (element instanceof MethodElement) {
					EObject container = element.eContainer();
					EStructuralFeature feature = element.eContainingFeature();
					int index;
					if (feature.isMany()) {
						index = ((List) container.eGet(feature))
								.indexOf(element);
					} else {
						index = -1;
					}
					elementToOldContainerMap.put(element, new ContainmentInfo(
							container, index));
					
					if (element instanceof MethodPackage) {
						MethodPackage pkg = (MethodPackage) element;
						Boolean b = propUtil.getBooleanValue(pkg, PropUtil.Pkg_loadCheck);
						if (b == null || !b.booleanValue()) {
							propUtil.setBooleanValue(pkg, PropUtil.Pkg_loadCheck, true);
							pkgsMarkedByLoadCheck.add(pkg);
						}
					}
				}
			}

			// move the elements
			//
			addCommand.execute();

			// collect resources that had been modifed as result of this move
			//
			Resource newResource = addCommand.getOwner().eResource();
			modifiedResources.add(newResource);
			for (Iterator iter = addCommand.getAffectedObjects().iterator(); iter
					.hasNext();) {
				EObject element = (EObject) TngUtil.unwrap(iter.next());
				if (element instanceof MethodElement) {
					EObject oldContainer = ((ContainmentInfo) elementToOldContainerMap
							.get(element)).container;
					if (oldContainer.eResource() != newResource
							&& !oldContainer.eResource().getContents()
									.isEmpty()) {
						modifiedResources.add(oldContainer.eResource());
						Resource oldResource = (Resource) elementToOldResourceMap
								.get(element);
						if (oldContainer.eResource() != oldResource) {
							movedResources.add(oldResource);
						}
					}

					Object plugin = elementToOldPluginMap.get(element);
					if (ownerPlugin != plugin) {
						// it's a cross-plugin move, add the resources of those
						// children that have direct resource
						// to the moveResources list
						//						
						for (Iterator iterator = element.eAllContents(); iterator
								.hasNext();) {
							EObject e = (EObject) iterator.next();
							if (UmaUtil.hasDirectResource(e)) {
								movedResources.add(e.eResource());
								elementToOldPluginMap.put(e, plugin);
							}
						}

						// if(element instanceof DescribableElement &&
						// ContentDescriptionFactory.hasPresentation((DescribableElement)
						// element)) {
						// movedResources.add(((DescribableElement)element).getPresentation().eResource());
						// elementToOldPluginMap.put(((DescribableElement)element).getPresentation(),
						// plugin);
						// }
						// for (Iterator iterator = element.eAllContents();
						// iterator
						// .hasNext();) {
						// Object e = iterator.next();
						// if(e instanceof DescribableElement &&
						// ContentDescriptionFactory.hasPresentation((DescribableElement)
						// e)) {
						// movedResources.add(((DescribableElement)e).getPresentation().eResource());
						// elementToOldPluginMap.put(((DescribableElement)e).getPresentation(),
						// plugin);
						// }
						// }
					}
				}
			}
		}

		private IStatus save(IProgressMonitor monitor) {
			MultiStatus status = new MultiStatus(
					LibraryEditPlugin.INSTANCE.getSymbolicName(),
					IStatus.OK,
					LibraryEditResources.MethodElementAddCommand_errorSavingFiles,
					null); 

			monitor.subTask(LibraryEditResources.savingModifiedFilesTask_name); 
			ILibraryPersister.FailSafeMethodLibraryPersister persister = Services
					.getDefaultLibraryPersister().getFailSafePersister();
			save: do {
				try {

					if (!movedResources.isEmpty()) {
						monitor
								.subTask(LibraryEditResources.movingFilesTask_name); 

						// move the moved resources
						//
						persister.adjustLocation(movedResources);
					}
					state = STATE_MOVED_FILES;

					// save the modified resources
					//
					for (Iterator iter = modifiedResources.iterator(); iter
							.hasNext();) {
						Resource resource = (Resource) iter.next();
						try {
							// monitor.subTask(MessageFormat.format("Saving
							// ''{0}''", pathArgs));
							persister.save(resource);

						} catch (Exception e) {
							String msg = NLS.bind(
									LibraryEditResources.saveFileError_reason,
									resource.getURI().isFile() ? resource.getURI().toFileString() : resource.toString());
							status.add(new SaveStatus(IStatus.ERROR,
									LibraryEditPlugin.INSTANCE
											.getSymbolicName(),
									SaveStatus.SAVE_ERROR, msg, e));
							break save;
						}
					}
					persister.commit();
					state = STATE_SAVED_FILES;

					if (resMgr != null) {
						monitor
								.subTask(LibraryEditResources.copyingResourcesTask_name); 

						Collection elementsToCopyRefContents = new ArrayList();
						for (Iterator iter = movedResources.iterator(); iter
								.hasNext();) {
							Resource resource = (Resource) iter.next();
							elementsToCopyRefContents.addAll(resource
									.getContents());
						}

						elementsToCopyRefContents.addAll(addCommand
								.getAffectedObjects());

						Collection modified = resMgr.copyReferencedContents(
								elementsToCopyRefContents,
								elementToOldPluginMap);
						if (modified != null && !modified.isEmpty()) {
							for (Iterator iter = modified.iterator(); iter
									.hasNext();) {
								Resource resource = (Resource) iter.next();
								Object[] pathArgs = { resource.getURI().isFile() ? resource.getURI()
										.toFileString() : resource.toString()};
								try {
									monitor
											.subTask(MessageFormat
													.format(
															LibraryEditResources.savingFileTask_name,
															pathArgs)); 
									persister.save(resource);
								} catch (Exception e) {
									String msg = MessageFormat
											.format(
													LibraryEditResources.saveFileError_reason,
													pathArgs); 
									status.add(new SaveStatus(IStatus.ERROR,
											LibraryEditPlugin.INSTANCE
													.getSymbolicName(),
											SaveStatus.SAVE_ERROR, msg, e));
									break save;
								}
							}
						}
					}
					persister.commit();
					state = STATE_COPIED_RESOURCES;

				} catch (RuntimeException e) {
					StringWriter msg = new StringWriter();
					e.printStackTrace(new PrintWriter(msg));
					IStatus newStatus = new SaveStatus(IStatus.ERROR,
							LibraryEditPlugin.INSTANCE.getSymbolicName(),
							SaveStatus.SAVE_ERROR, msg.toString(), e);
					status.add(newStatus);
				} finally {
					if (status.getSeverity() == IStatus.ERROR) {
						try {
							persister.rollback();
						} catch (Exception e) {
							LibraryEditPlugin.INSTANCE.log(e);
							StringWriter msg = new StringWriter();
							e.printStackTrace(new PrintWriter(msg));
							IStatus newStatus = new SaveStatus(IStatus.ERROR,
									LibraryEditPlugin.INSTANCE
											.getSymbolicName(),
									SaveStatus.ROLLBACK_ERROR, msg.toString(),
									e);
							status.add(newStatus);
						}
					}
				}
			} while (false);

			monitor.subTask(LibraryEditResources.command_done); 
			return status;
		}
	}

	public static interface ResourceManager {

		/**
		 * Copies the content resources that are referenced by the content of
		 * the given elements that have been moved/copied to new location.
		 * 
		 * @param elements
		 *            Elements that are moved or copied.
		 * @return collection of modified resources as the result of this call.
		 */
		Collection<Resource> copyReferencedContents(Collection<MethodElement> elements,
				Map<MethodElement, MethodPlugin> elementToOldPluginMap);
	}

	public static class BasicResourceManager implements ResourceManager {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.ibm.library.edit.command.MethodElementAddCommand.ResourceManager#copyReferencedContents(java.util.Collection)
		 */
		public Collection<Resource> copyReferencedContents(Collection<MethodElement> elements,
				Map<MethodElement, MethodPlugin> elementToOldPluginMap) {
			return null;
		}

	}

	public static void setResourceManager(ResourceManager resMgr) {
		MethodElementAddCommand.resMgr = resMgr;
	}

	protected static StringValidator getStringValidator() {
		if (stringValidator == null) {
			stringValidator = new StringValidator();
		}
		return stringValidator;
	}

	private static ResourceManager resMgr = null;

	private static StringValidator stringValidator = null;

	protected static class StringValidator extends AbstractStringValidator {

		private Collection elements;

		private IValidator validator;

		private EStructuralFeature feature;

		/**
		 * 
		 */
		private StringValidator() {
		}

		/**
		 * @param elements
		 *            the elements to include in the check.
		 */
		public StringValidator(Collection elements, EStructuralFeature feature) {
			this.elements = elements;
			this.feature = feature;
		}

		/**
		 * Sets the elements to include in the check.
		 * 
		 * @param elements
		 *            the elements to include in the check.
		 */
		public void setElements(Collection elements) {
			this.elements = elements;
		}

		public void setFeature(EStructuralFeature feature) {
			this.feature = feature;
		}

		public void setValidator(IValidator validator) {
			this.validator = validator;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.dialogs.IInputValidator#isValid(java.lang.String)
		 */
		public String isValid(String newText) {
			if (validator instanceof UniquenessValidator) {
				EObject obj = ((UniquenessValidator) validator).getEObject();
				int classID = obj.eClass().getClassifierID();
				newText = newText.trim();
				for (Iterator iter = elements.iterator(); iter.hasNext();) {
					EObject e = (EObject) iter.next();
					String str = (String) e.eGet(feature);
					if (obj != e && classID == e.eClass().getClassifierID()
							&& newText.equalsIgnoreCase(str)) {
						// return I18nUtil.formatString(RESOURCE_BUNDLE, key,
						// data);
						return NLS
								.bind(
										LibraryEditResources.duplicateElementNameError_msg,
										newText);
					}
				}
			}
			return validator.isValid(newText);
		}

	}	

	//MoveOperation for moving custom category
	public static class MoveOperationExt extends MoveOperation {
		private List<CustomCategory> movingCCs;
		private List<CustomCategory> movingCCsrcParents;
		private CustomCategory tgtParent;
		private Map<CustomCategory, CustomCategory> movingCCtoParentsMap = new HashMap<CustomCategory, CustomCategory>();
		private boolean samePluginMove = false;
		private MethodPlugin srcPlugin;
		
		public MoveOperationExt(Command command, IProgressMonitor monitor,
				Object shell, List<CustomCategory> movingCCs, List<CustomCategory> movingCCsrcParents, CustomCategory tgtParent) {
			super(command, monitor, shell);
			this.movingCCs = movingCCs;
			this.movingCCsrcParents = movingCCsrcParents;
			this.tgtParent = tgtParent;
			for (int i = 0; i < movingCCs.size(); i++) {
				movingCCtoParentsMap.put(movingCCs.get(i), movingCCsrcParents.get(i));
			}
			srcPlugin = UmaUtil.getMethodPlugin(movingCCs.get(0));
			samePluginMove = srcPlugin == UmaUtil.getMethodPlugin(tgtParent);
		}
				
		@Override
		public void run() {
			if (samePluginMove) {
				reassign();
				MethodPlugin plugin = UmaUtil.getMethodPlugin(tgtParent);
				modifiedResources = new HashSet();
				modifiedResources.add(plugin.eResource());
				LibraryEditUtil.getInstance().save(modifiedResources);
				status = new MultiStatus(LibraryEditPlugin.INSTANCE
						.getSymbolicName(), IStatus.OK,
						LibraryEditResources.error_reason, null); 
				return;
			}
			super.run();
		}
		
		@Override
		protected void doMove(IProgressMonitor monitor,
				Map elementToOldResourceMap, Set modifiedResources) {
			super.doMove(monitor, elementToOldResourceMap, modifiedResources);
			reassign();
			LibraryEditUtil.getInstance().fixUpDanglingCustomCategories(srcPlugin);
		}

		private void reassign() {
			
			Set<CustomCategory> notToReassignSet = new HashSet<CustomCategory>();
			for (int i = 0; i < movingCCs.size(); i++) {
				CustomCategory cc = movingCCs.get(i);
				boolean toReassign = true;
				
				if (!samePluginMove && movingCCs.size() > 1) {
					
					//Build ancestorSet
					Set ancestorSet = new HashSet();
					Stack<List> stack = new Stack<List>();
					List<CustomCategory> parents = AssociationHelper.getCustomCategories(cc);
					if (parents != null && !parents.isEmpty()) {
						stack.push(parents);	
					}					
					while (! stack.isEmpty()) {
						parents = stack.pop();
						for (CustomCategory p : parents) {
							if (! ancestorSet.contains(p)) {
								List<CustomCategory> moreParents = AssociationHelper.getCustomCategories(p);
								if (moreParents != null && !moreParents.isEmpty()) {
									stack.push(moreParents);	
								}
							}
						}						
						ancestorSet.addAll(parents);
					}
					
					//Test if any other moving cc1 is an ancestor, and not to assign if there is any
					for (CustomCategory cc1 : movingCCs) {
						if (cc1 != cc && ancestorSet.contains(cc1)) {
							notToReassignSet.add(cc);
							toReassign = false;
							break;
						}
					}
				}
				
				if (toReassign) {
					movingCCsrcParents.get(i).getCategorizedElements().remove(cc);
				}
			}
			
			
			Set set = new HashSet();
			set.addAll(tgtParent.getCategorizedElements());
			for (CustomCategory cc : movingCCs) {
				if (set.contains(cc) || notToReassignSet.contains(cc)) {
					continue;
				}
				set.add(cc);
				tgtParent.getCategorizedElements().add(cc);
			}
		}
		
		@Override
		protected boolean referencedIllegally(MethodPlugin ownerPlugin,
				MethodElement e, Collection moveList) {	
			if (e instanceof CustomCategory) {
				CustomCategory referencedCC = (CustomCategory) e;
				
				Map<String, Boolean> map = new HashMap<String, Boolean>();
				Collection references = AssociationHelper.getReferences(referencedCC);
				
				for (Iterator iter = references.iterator(); iter.hasNext();) {
					MethodElement element = (MethodElement) iter.next();
					if (movingCCtoParentsMap.containsKey(element)) {
						continue;
					}
					if (element instanceof CustomCategory) {
						if (movingCCtoParentsMap.get(referencedCC) == element) {
							continue;
						}
					} 
					MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
					if (plugin != null && plugin != ownerPlugin
							&& !Misc.isBaseOf(ownerPlugin, plugin, map)) {
						return true;
					}
				}
				
				return false;
			}

			return super.referencedIllegally(ownerPlugin, e, moveList);	
		}
		
		@Override
		public void undo() {
			super.undo();
		}
	}

	

}