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
package org.eclipse.epf.library.edit.process.command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.library.edit.IConfigurationApplicator;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.command.BatchCommand;
import org.eclipse.epf.library.edit.command.INestedCommandProvider;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.command.NestedCommandExcecutor;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.library.edit.util.IRunnableWithProgress;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProductDescriptor;

/**
 * Abstract base DropCommand class for breakdown structure editor
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public abstract class BSDropCommand extends AbstractCommand implements
		IResourceAwareCommand {
	private static final Set<EReference> BASIC_SYNC_REFERENCES = Collections.unmodifiableSet(new HashSet<EReference>(Arrays.asList(new EReference[] {
	})));
	
	public static final Set<EStructuralFeature> DEFAULT_SYNCH_FEATURES = new HashSet<EStructuralFeature>(Arrays.asList(
			new EStructuralFeature[] {
					UmaPackage.eINSTANCE.getNamedElement_Name(),
					UmaPackage.eINSTANCE.getMethodElement_BriefDescription(),
					UmaPackage.eINSTANCE.getMethodElement_PresentationName(),
					UmaPackage.eINSTANCE.getTask_OptionalInput(),
					UmaPackage.eINSTANCE.getTask_MandatoryInput(),
					UmaPackage.eINSTANCE.getTask_Output(),
					UmaPackage.eINSTANCE.getTask_Steps(),				
					UmaPackage.eINSTANCE.getTask_PerformedBy(),
					UmaPackage.eINSTANCE.getTask_AdditionallyPerformedBy(),
					UmaPackage.eINSTANCE.getRole_ResponsibleFor(),
					UmaPackage.eINSTANCE.getArtifact_ContainedArtifacts(),
					UmaPackage.eINSTANCE.getDeliverable_DeliveredWorkProducts()
			}
	));
	
	/**
	 * Map of linked element's feature to descriptor's feature
	 */
	public static final Map<EStructuralFeature, EStructuralFeature> FEATURE_MAP = new HashMap<EStructuralFeature, EStructuralFeature>();
	static {
		DEFAULT_SYNCH_FEATURES.addAll(BASIC_SYNC_REFERENCES);
		
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getNamedElement_Name(), UmaPackage.eINSTANCE.getNamedElement_Name());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getMethodElement_BriefDescription(), UmaPackage.eINSTANCE.getMethodElement_BriefDescription());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getMethodElement_PresentationName(), UmaPackage.eINSTANCE.getMethodElement_PresentationName());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getTask_OptionalInput(), UmaPackage.eINSTANCE.getTaskDescriptor_OptionalInput());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getTask_MandatoryInput(), UmaPackage.eINSTANCE.getTaskDescriptor_MandatoryInput());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getTask_Output(), UmaPackage.eINSTANCE.getTaskDescriptor_Output());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getRole_ResponsibleFor(), UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getDeliverable_DeliveredWorkProducts(), UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getTask_Steps(), UmaPackage.eINSTANCE.getTaskDescriptor_SelectedSteps());
//		FEATURE_MAP.put(UmaPackage.eINSTANCE.getEstimatedMethodElement_Estimate(), UmaPackage.eINSTANCE.getEstimatedMethodElement_Estimate());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getTask_AdditionallyPerformedBy(), UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getTask_PerformedBy(), UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy());
		// guidance
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getContentElement_Checklists(), UmaPackage.eINSTANCE.getBreakdownElement_Checklists());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getContentElement_ConceptsAndPapers(), UmaPackage.eINSTANCE.getBreakdownElement_Concepts());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getContentElement_Examples(), UmaPackage.eINSTANCE.getBreakdownElement_Examples());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getContentElement_Guidelines(), UmaPackage.eINSTANCE.getBreakdownElement_Guidelines());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getContentElement_Assets(), UmaPackage.eINSTANCE.getBreakdownElement_ReusableAssets());
		FEATURE_MAP.put(UmaPackage.eINSTANCE.getContentElement_SupportingMaterials(), UmaPackage.eINSTANCE.getBreakdownElement_SupportingMaterials());
//		FEATURE_MAP.put(UmaPackage.eINSTANCE.getContentElement_, UmaPackage.eINSTANCE.getBreakdownElement_);	
	}

	protected Activity activity;

	protected List<Object> dropElements;
	
	protected List<Object> unwrappedDropElements;

	private Set<Resource> modifiedResources;

	protected List<Object> elementsToAddToDefaultConfig;

	protected Set<Object> addedObjects;

	private Process process;

	private boolean addedToDefaultConfig = false;

	protected boolean synchronize = false;

	protected ArrayList<TaskDescriptor> taskDescList;

	protected ArrayList<Descriptor> taskDescriptorsToUpdate;

//	protected Set descriptorsToRefresh;

	/**
	 * Map of descriptor to old refreshable feature map
	 */
	private Map<Descriptor, Map<?, ?>> descriptorToOldRefreshableFeaturesMap;

	/**
	 * Map of descriptor to map of feature to new values
	 */
	protected DescriptorUpdateBatchCommand batchCommand;

	/**
	 * Map of TaskDescriptor to list of newly added steps
	 */
	private Map taskDescToNewStepsMap;

	private Map taskDescToOldEstimateMap;
	
	protected boolean canceled;

	private MethodConfiguration config;

	protected Set synchFeatures = DEFAULT_SYNCH_FEATURES;

	private BatchCommand refreshResponsibleForCommand;

	protected boolean executed;

//	protected Object UIContext;
	
	protected boolean runAsJob;
	
	private IProgressMonitor monitor;

	private int workedUnits;

	private NestedCommandExcecutor nestedCommandExcecutor;
	
	/**
	 * @param activity
	 *            the activity that the given elements are dropped on
	 * @param dropElements
	 *            the elements to drop on the given activity
	 */
	public BSDropCommand(Activity activity, List<Object> dropElements) {
		this(activity, dropElements, null, null, false);
	}

	/**
	 * @param synch
	 *            if true, the command will clean the content and relationships
	 *            of existing descriptors and refresh them with the linked
	 *            MethodElement
	 */
	public BSDropCommand(Activity activity, List<Object> dropElements, boolean synch) {
		this(activity, dropElements, null, null, synch);
	}

	/**
	 * Synchronize the given content elements to its TaskDescriptors in the
	 * given activity using the given MethodConfiguration and list of
	 * synchronizable features.
	 * 
	 * @param activity
	 * @param dropElements
	 *            the elements to synchronize
	 * @param config
	 * @param synchFeatures
	 *            the synchronizable features, if <code>null</code> the DEFAULT_SYNCH_FEATURES will be used.
	 */
	public BSDropCommand(Activity activity, List<Object> dropElements,
			MethodConfiguration config, Set synchFeatures) {
		this(activity, dropElements, config, synchFeatures, true);
	}
	
	public BSDropCommand(Activity activity, List<Object> dropElements,
			MethodConfiguration config, Set synchFeatures, boolean synch) {
		super();
		this.activity = activity;
		process = TngUtil.getOwningProcess(activity);
		this.synchronize = synch;
		this.config = config;
		if (synchFeatures == null) {
			this.synchFeatures = DEFAULT_SYNCH_FEATURES;
		}
		else {
			this.synchFeatures = synchFeatures;
		}
		
		this.dropElements = dropElements;
		nestedCommandExcecutor = createNestedCommandExcecutor();
	}
	
	protected NestedCommandExcecutor createNestedCommandExcecutor() {
		return new NestedCommandExcecutor(this) {
			@Override
			public void executeNestedCommands() {
				if(taskDescList != null && !taskDescList.isEmpty()) {
					List nestedCommandProviders = ExtensionManager
					.getNestedCommandProviders();
					if (!nestedCommandProviders.isEmpty()) {
						for (Iterator iter = nestedCommandProviders.iterator(); iter
						.hasNext();) {
							INestedCommandProvider cmdProvider = (INestedCommandProvider) iter
							.next();
							try {
								Command cmd = cmdProvider.createRelatedObjects(
										taskDescList, BSDropCommand.this);
								if (cmd != null && cmd.canExecute()) {
									cmd.execute();
									getNestedCommands().add(cmd);
								}
							} catch (Exception e) {
								LibraryEditPlugin.getDefault().getLogger()
								.logError(e);
							}
						}
					}
				}

				super.executeNestedCommands();
			}
		};
	}
	
	protected IProgressMonitor getProgressMonitor() {
		if(monitor == null) {
			monitor = new NullProgressMonitor();
		}
		return monitor;
	}
	
	protected void prepareDropElements() {		
		ArrayList<Object> elements = new ArrayList<Object>();
		getMethodConfiguration();
		unwrappedDropElements = new ArrayList<Object>();
		for (Object o : dropElements) {
			Object element = TngUtil.unwrap(o);
			element = Providers.getConfigurationApplicator().resolve(element, config);
			if ((allowDuplicateDropElements() || !elements.contains(element))
					&& !(element instanceof EObject && ((EObject) element)
							.eIsProxy())) {				
				elements.add(element);
				unwrappedDropElements.add(o);
			}
		}
		dropElements = elements;
	}
	
	protected boolean allowDuplicateDropElements() {
		return false;
	}
	
//	/**
//	 * Sets UI context for this command, this can be a Shell object
//	 * @param UIContext
//	 */
//	public void setUIContext(Object UIContext) {
//		this.UIContext = UIContext;
//	}

	public MethodConfiguration getMethodConfiguration() {
		if(config == null) {
			config = TngUtil.getOwningProcess(activity).getDefaultContext();
		}
		return config;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#dispose()
	 */
	public void dispose() {
		if (dropElements != null) {
			dropElements.clear();
		}
		if(unwrappedDropElements != null) {
			unwrappedDropElements.clear();
		}
		if (modifiedResources != null) {
			modifiedResources.clear();
		}
		if (elementsToAddToDefaultConfig != null) {
			elementsToAddToDefaultConfig.clear();
		}
		if (addedObjects != null) {
			addedObjects.clear();
		}
		if (descriptorToOldRefreshableFeaturesMap != null) {
			descriptorToOldRefreshableFeaturesMap.clear();
		}
		if (taskDescList != null) {
			taskDescList.clear();
		}
		if (taskDescToNewStepsMap != null) {
			taskDescToNewStepsMap.clear();
		}
		if (taskDescToOldEstimateMap != null) {
			taskDescToOldEstimateMap.clear();
		}
		if (refreshResponsibleForCommand != null) {
			refreshResponsibleForCommand.dispose();
		}
		if (taskDescriptorsToUpdate != null) {
			taskDescriptorsToUpdate.clear();
		}
		if(batchCommand != null) {
			batchCommand.dispose();
		}
		activity = process = null;
		if(nestedCommandExcecutor != null) {
			nestedCommandExcecutor.dispose();
		}
		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.IResourceAwareCommand#getModifiedResources()
	 */
	public Collection<Resource> getModifiedResources() {
		if (modifiedResources == null) {
			modifiedResources = new HashSet<Resource>();
			if (activity.eResource() != null) {
				modifiedResources.add(activity.eResource());
			}
		}
		
		modifiedResources.addAll(nestedCommandExcecutor.getModifiedResources());

		return modifiedResources;
	}

	/**
	 * Clears all the refreshable features of the given descriptor
	 * 
	 * @param descriptor
	 */
	private boolean clearDescriptor(Descriptor descriptor) {
		Set excludeSynchFeatures = new HashSet(DEFAULT_SYNCH_FEATURES);
		excludeSynchFeatures.removeAll(synchFeatures);
		Set excludeFeatures;
		if (excludeSynchFeatures.isEmpty()) {
			excludeFeatures = excludeSynchFeatures;
		}
		else {
			excludeFeatures = new HashSet();
			for (Iterator iter = excludeSynchFeatures.iterator(); iter.hasNext();) {
				EStructuralFeature feature = getDescriptorFeature((EStructuralFeature) iter.next());
				if(feature != null) {
					excludeFeatures.add(feature);
				}
			}
		}
		boolean ret = ProcessCommandUtil.clearDescriptor(descriptor,
				descriptorToOldRefreshableFeaturesMap, excludeFeatures);
		if (ret && descriptor instanceof TaskDescriptor) {
			if (taskDescriptorsToUpdate == null) {
				taskDescriptorsToUpdate = new ArrayList<Descriptor>();
			}
			taskDescriptorsToUpdate.add(descriptor);
		}
		return ret;
	}

	/**
	 * @param feature
	 * @return
	 */
	public static EStructuralFeature getDescriptorFeature(EStructuralFeature linkedElementFeature) {
		return (EStructuralFeature) FEATURE_MAP.get(linkedElementFeature);
	}
	
	protected boolean preExecute() {
		prepareDropElements();
		
		boolean b = !dropElements.isEmpty();
		if (b) {
//			descriptorToNewFeatureValuesMap = new HashMap();
			batchCommand = new DescriptorUpdateBatchCommand(false, synchFeatures, config); //TODO: why clear must be false ???

//			if (synchronize) {
//				descriptorsToRefresh = new HashSet();
//			}
		}
		return b;
	}
	
	protected boolean collectElementsToAddToDefaultConfig() {
		if (elementsToAddToDefaultConfig == null) {
			elementsToAddToDefaultConfig = new ArrayList<Object>();
			for (Iterator iter = dropElements.iterator(); iter.hasNext();) {
				Object element = iter.next();
				switch (UserInteractionHelper.checkAgainstDefaultConfiguration(
						process, element)) {
				case 0:
					iter.remove();
					break;
				case 2:
					elementsToAddToDefaultConfig.add(element);
					break;
				case -1:
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Adds elements to the default configuration
	 * 
	 * @return true if default configuration has been modified, false otherwise
	 */
	private boolean addToDefaultConfiguration() {
		if (!addedToDefaultConfig && elementsToAddToDefaultConfig != null && !elementsToAddToDefaultConfig.isEmpty()) {
			if(addedObjects == null) {
				addedObjects = new HashSet();
			}
			else {
				addedObjects.clear();
			}
			for (Iterator iter = elementsToAddToDefaultConfig.iterator(); iter
			.hasNext();) {
				EObject element = (EObject) iter.next();
				ProcessUtil.addToDefaultConfiguration(process, element,
						addedObjects);
			}
			if (!addedObjects.isEmpty()) {
				getModifiedResources().add(
						process.getDefaultContext().eResource());
				return true;
			}
			addedToDefaultConfig = true;
		}
		return false;
	}
	
	protected int getTotalWork() {
		return 20;
	}
	
	protected void worked(int unitsOfWork) {
		monitor.worked(unitsOfWork);
		workedUnits = workedUnits + unitsOfWork;
	}
	
	protected int getRemainingWork() {
		int remaining = getTotalWork() - workedUnits;
		if(remaining < 0) {
			remaining = 0;
		}
		return remaining;
	}

	protected boolean updateDefaultConfigurationNeeded() {
		return !synchronize;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {		
		// BusyIndicator.showWhile(Display.getCurrent(), new Runnable() {
		//			
		// public void run() {
		// canceled = !addToDefaultConfiguration();
		// }
		//			
		// });
		//		
		// //TODO: This caused NullPointerException or ThreadAccessError when
		// showing selection dialog is needed
		// //
		// IRunnableWithProgress runnable = new IRunnableWithProgress() {
		//
		// public void run(IProgressMonitor monitor) throws
		// InvocationTargetException, InterruptedException {
		// canceled = !preExecute();
		// }
		//			
		// };
		// UserInteractionHelper.runWithProgress(runnable, null);
		//		
		// if(!canceled) {
		// BusyIndicator.showWhile(Display.getCurrent(), new Runnable() {
		//				
		// public void run() {
		// redo();
		// }
		//				
		// });
		// }

//		BusyIndicator.showWhile(Display.getCurrent(), new Runnable() {
//
//			public void run() {
//				if ((!synchronize && addToDefaultConfiguration()) && preExecute()) {
//					redo();
//				}
//			}
//
//		});
		
		if(updateDefaultConfigurationNeeded()) {
			if(!collectElementsToAddToDefaultConfig()) {
				return;
			}
		}
		
		final String taskName = (label == null ? "" : label); //$NON-NLS-1$
		IRunnableWithProgress runnable = new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				try {
					monitor.beginTask(taskName, getTotalWork());
					BSDropCommand.this.monitor = monitor;
					worked(getRemainingWork() / 3);
					
					// this must be called before preExecute() to add required elements to configuration
					// so the command can realize data correctly
					//
					addToDefaultConfiguration();
					worked(getRemainingWork() / 3);
					
					if (preExecute()) {
						worked(getRemainingWork() / 3);
						redo();
					}
				}
				finally {
					monitor.done();
				}
			}
			
		};
		if(runAsJob) {
			UserInteractionHelper.runAsJob(runnable, taskName);
		}
		else {
//			UserInteractionHelper.runInUI(runnable, (Shell)null);
			UserInteractionHelper.runWithProgress(runnable, null);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		try {
			// add elements to the default configuration if needed
			//
			addToDefaultConfiguration();
			
			doExecute();
			updateDescriptors();
			
			long time = 0;
			if(TngUtil.DEBUG) {
				time = System.currentTimeMillis();
			}
			nestedCommandExcecutor.executeNestedCommands();
			if(TngUtil.DEBUG) {
				System.out.println("BSDropCommand.redo(): executeNestedCommands(). " + (System.currentTimeMillis() - time)); //$NON-NLS-1$
			}
			
			IRealizationManager mgr = LibraryEditUtil.getInstance().getDefaultRealizationManager();
			if (mgr != null) {
				mgr.updateActivityModel(activity);
			}
			
			executed = true;
		}
		catch(RuntimeException e) {
			if(TngUtil.DEBUG) {
				e.printStackTrace();
			}
			LibraryEditPlugin.getDefault().getLogger().logError(e);
			throw e;
		}
	}

	/**
	 * Updates descriptors
	 */
	protected void updateDescriptors() {
		if (synchronize) {
			clearDescriptors();
			if(!batchCommand.getDescriptorsToRefresh().isEmpty()) {
				Collection<EReference> basicSyncReferences = new ArrayList<EReference>();
				for (Object feature : synchFeatures) {
					if(BASIC_SYNC_REFERENCES.contains(feature)) {
						basicSyncReferences.add((EReference) feature);
					}
				}
				if (!basicSyncReferences.isEmpty()) {
					for (Object desc : batchCommand
							.getDescriptorsToRefresh()) {
						Descriptor descriptor = (Descriptor) desc;
						MethodElement element = ProcessUtil.getAssociatedElement(descriptor);
						if(element instanceof VariabilityElement) {
							for (EReference ref : basicSyncReferences) {
								Object value = Providers.getConfigurationApplicator().getReference((VariabilityElement) element, ref, config);
								EStructuralFeature descriptorFeature = FEATURE_MAP.get(ref);
								if(descriptorFeature.isMany()) {
									batchCommand.addFeatureValues(descriptor, descriptorFeature, (Collection) value);
								} else {
									batchCommand.addFeatureValue(descriptor, descriptorFeature, value);
								}
							}
							
						}
					}
				}
			}
		}

		// update new values;
		//
		batchCommand.execute();
		
		updateTaskDescriptors();

		setResponsibleRoles();
	}

//	/**
//	 * @param desc
//	 * @param feature
//	 * @param value
//	 */
//	protected void saveOldFeatureValue(Descriptor desc,
//			EStructuralFeature feature) {
//		if (descriptorToOldFeatureValuesMap == null) {
//			descriptorToOldFeatureValuesMap = new HashMap();
//		}
//		Object value = desc.eGet(feature);
//		if(feature.isMany()) {
//			value = new ArrayList((List)value);
//		}
//		Map featureMap = (Map) descriptorToOldFeatureValuesMap.get(desc);
//		if (featureMap == null) {
//			featureMap = new HashMap();
//			descriptorToOldFeatureValuesMap.put(desc, featureMap);
//		}
//		featureMap.put(feature, value);
//	}

	protected void undoUpdateDescriptors() {
		undoSetResponsibleRole();

		undoUpdateTaskDescriptors();

		// restore old values
		//
		batchCommand.undo();

		// restore value for refreshable features that had been cleared
		//
		if (synchronize) {
			undoClearDescriptors();
		}
	}

	/**
	 * 
	 */
	private void clearDescriptors() {
		if (descriptorToOldRefreshableFeaturesMap == null) {
			descriptorToOldRefreshableFeaturesMap = new HashMap();
			if (!batchCommand.getDescriptorsToRefresh().isEmpty()) {
				for (Iterator iter = batchCommand.getDescriptorsToRefresh().iterator(); iter
						.hasNext();) {
					clearDescriptor((Descriptor) iter.next());
				}
				// descriptorsToRefresh.clear();
			}
		}
	}

	/**
	 * Updates affected task descriptors with new data from method content
	 */
	private void updateTaskDescriptors() {
		if (taskDescriptorsToUpdate != null) {
			// refresh steps from the associated task
			//
			if(synchFeatures.contains(UmaPackage.eINSTANCE.getTask_Steps())) {
				if (taskDescToNewStepsMap == null) {
					taskDescToNewStepsMap = new HashMap();
				}
				IConfigurationApplicator configApplicator = Providers
				.getConfigurationApplicator();
				for (Iterator iter = taskDescriptorsToUpdate.iterator(); iter
				.hasNext();) {
					TaskDescriptor taskDesc = (TaskDescriptor) iter.next();
					Task task = taskDesc.getTask();
					if (task != null && !((EObject) task).eIsProxy()) {
						List steps = (List) configApplicator.getReference(task
								.getPresentation(), task, UmaPackage.eINSTANCE
								.getContentDescription_Sections(), config);
						
						// add those steps to TaskDescriptor if they are not there
						// yet.
						//
						ArrayList newSteps = new ArrayList();
						taskDesc.getSelectedSteps().retainAll(steps);
						for (Iterator iter1 = steps.iterator(); iter1.hasNext();) {
							Object step = iter1.next();
							if (!taskDesc.getSelectedSteps().contains(step)) {
								newSteps.add(step);
							}
						}
						if (!newSteps.isEmpty()) {
							taskDesc.getSelectedSteps().addAll(newSteps);
							taskDescToNewStepsMap.put(taskDesc, newSteps);
//							System.out
//							.println("BSDropCommand.updateTaskDescriptors(): changed");
						}
					}
				}
			}
			
		}
	}

	private void undoUpdateTaskDescriptors() {
		if (taskDescToNewStepsMap != null) {
			for (Iterator iter = taskDescToNewStepsMap.entrySet().iterator(); iter
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				TaskDescriptor taskDesc = (TaskDescriptor) entry.getKey();
				taskDesc.getSelectedSteps().removeAll(
						(Collection) entry.getValue());
			}
		}
	}

	/**
	 * Go thru all the work product descriptors of this activity and set the
	 * responsible role for them if there is any
	 */
	private void setResponsibleRoles() {
		if(!synchFeatures.contains(UmaPackage.eINSTANCE.getRole_ResponsibleFor())) {
			return;
		}
		
		if(refreshResponsibleForCommand != null) {
			refreshResponsibleForCommand.dispose();
		}
		refreshResponsibleForCommand = new BatchCommand(true);
 
		List brElements = activity.getBreakdownElements();
		List wpDescList = new ArrayList();
		List roleDescriptors = new ArrayList();
		for (Iterator itor = brElements.iterator(); itor.hasNext();) {
			Object obj = itor.next();
			if (obj instanceof WorkProductDescriptor) {
				wpDescList.add(obj);
			} else if (obj instanceof RoleDescriptor) {
				roleDescriptors.add(obj);
			}
		}

		for (Iterator itor = roleDescriptors.iterator(); itor.hasNext();) {
			RoleDescriptor roleDesc = (RoleDescriptor) itor.next();
			Role role = roleDesc.getRole();
			if (role != null) {				
				List responsibleWorkProducts = (List) Providers.getConfigurationApplicator().getReference(role, UmaPackage.eINSTANCE.getRole_ResponsibleFor(), config);
				List responsibleWPDList = new ArrayList();
				for (int j = wpDescList.size() - 1; j > -1; j--) {
					WorkProductDescriptor wpDesc = (WorkProductDescriptor) wpDescList
							.get(j);
//					RoleDescriptor responsibleRoleDesc = AssociationHelper.getResponsibleRoleDescriptor(wpDesc);
//					if (responsibleWorkProducts.contains(wpDesc
//							.getWorkProduct())
//							&&  responsibleRoleDesc != roleDesc) {
//						wpdToOldResponsibleRoleMap.put(wpDesc, responsibleRoleDesc);
////						wpDesc.setResponsibleRole(roleDesc);
//						roleDesc.getResponsibleFor().add(wpDesc);
//					}
					
					if(responsibleWorkProducts.contains(wpDesc.getWorkProduct())) {
						responsibleWPDList.add(wpDesc);
					}
				}				
				if(!roleDesc.getResponsibleFor().equals(responsibleWPDList)) {
					refreshResponsibleForCommand.addFeatureValues(roleDesc, UmaPackage.eINSTANCE.getRoleDescriptor_ResponsibleFor(), responsibleWPDList);
				}
			}
		}
		
		refreshResponsibleForCommand.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#undo()
	 */
	public void undo() {
		nestedCommandExcecutor.undoNestedCommands();
		
		// remove any object that had been added to the default configuration
		//
		if (addedObjects != null && !addedObjects.isEmpty()) {
			MethodConfiguration config = process.getDefaultContext();
			for (Iterator iter = addedObjects.iterator(); iter.hasNext();) {
				Object element = iter.next();
				if (element instanceof MethodPlugin) {
					config.getMethodPluginSelection().remove(element);
				} else if (element instanceof MethodPackage) {
					config.getMethodPackageSelection().remove(element);
				}
			}
			addedToDefaultConfig = false;
		}

		undoUpdateDescriptors();

		doUndo();
	}

	/**
	 * 
	 */
	private void undoClearDescriptors() {
		if(descriptorToOldRefreshableFeaturesMap != null) {
			for (Iterator iter = descriptorToOldRefreshableFeaturesMap.entrySet()
					.iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				Descriptor desc = (Descriptor) entry.getKey();
				Map featureMap = (Map) entry.getValue();
				for (Iterator iterator = featureMap.entrySet().iterator(); iterator
				.hasNext();) {
					entry = (Map.Entry) iterator.next();
					desc
					.eSet((EStructuralFeature) entry.getKey(), entry
							.getValue());
				}
			}
		}
	}

	/**
	 * Undoes the change made by
	 * {@link #setResponsibleRoles() setResponsibleRoles() }
	 */
	private void undoSetResponsibleRole() {
//		if(wpdToOldResponsibleRoleMap != null && !wpdToOldResponsibleRoleMap.isEmpty()) {
//			for (Iterator iter = wpdToOldResponsibleRoleMap.entrySet().iterator(); iter
//			.hasNext();) {
//				Map.Entry entry = (Map.Entry) iter.next();
//				WorkProductDescriptor wpd = (WorkProductDescriptor) entry.getKey();
//				((RoleDescriptor) entry.getValue()).getResponsibleFor().remove(wpd);
//			}
//		}
		if(refreshResponsibleForCommand != null) {
			refreshResponsibleForCommand.undo();
		}
	}

	protected boolean prepare() {
		return true;
	}
	
	protected abstract void doExecute();

	protected abstract void doUndo();

	public static interface IExecutor {
		boolean preExecute();

		void doExcecute();

		void doUndo();
	}

}