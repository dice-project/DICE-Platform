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
package org.eclipse.epf.library.edit.process.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.Providers;
import org.eclipse.epf.library.edit.command.BatchCommand;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.UniqueNamePNameHandler;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * Command to drag and drop work products to Work Product Usage breakdown
 * structure
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class PBSDropCommand extends BSDropCommand {
	private ArrayList wpDescList;

	private ArrayList roleDescList;

	private Map wpDescToDeliverableParts;

	private HashMap<WorkProductDescriptor, WorkProductDescriptor> wpdToDeliverableDescriptorMap;

	private BatchCommand updateDeliverablePartsCmd;

	private boolean newDuplicatesRemoved;

	private IConfigurator configurator;

	private ArrayList linkedTasks;

	private HashMap wpdToTaskFeaturesMap;

	public PBSDropCommand(Activity activity, List workProducts) {
		super(activity, workProducts);
		this.activity = activity;
		for (Iterator iter = dropElements.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (!(element instanceof WorkProduct)) {
				iter.remove();
			}
		}
	}

	/**
	 * @param activity
	 * @param dropElements
	 * @param synch
	 */
	public PBSDropCommand(Activity activity, List dropElements, boolean synch) {
		super(activity, dropElements, synch);
	}

	/**
	 * @param activity
	 * @param dropElements
	 * @param config
	 * @param synchFeatures
	 */
	public PBSDropCommand(Activity activity, List dropElements,
			MethodConfiguration config, Set synchFeatures, IConfigurator configurator) {
		super(activity, dropElements, config, synchFeatures);
		this.configurator = configurator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#preExecute()
	 */
	protected boolean preExecute() {
		if (!super.preExecute())
			return false;

		wpDescList = new ArrayList();
		taskDescList = new ArrayList();
		roleDescList = new ArrayList();
		wpDescToDeliverableParts = new HashMap();
		wpdToDeliverableDescriptorMap = new HashMap();

		if(!synchronize || (synchronize && synchFeatures.contains(UmaPackage.eINSTANCE.getArtifact_ContainedArtifacts()))) {
			// add subartifacts to dropElements list if there is any
			//
			for (Iterator iter = new ArrayList(dropElements).iterator(); iter
			.hasNext();) {
				Object element = iter.next();
				if (element instanceof Artifact) {
					Iterator iterator = new AbstractTreeIterator(element, false) {
						
						/**
						 * Comment for <code>serialVersionUID</code>
						 */
						private static final long serialVersionUID = -4820477887426087262L;
						
						protected Iterator getChildren(Object object) {
							Object subArtifacts = Providers.getConfigurationApplicator().getReference(
									(VariabilityElement) object,
									UmaPackage.eINSTANCE.getArtifact_ContainedArtifacts(),
									getMethodConfiguration());
							return ((Collection)subArtifacts).iterator();
						}
						
					};
					
					while (iterator.hasNext()) {
						Artifact subArtifact = (Artifact) iterator.next();
						if (!dropElements.contains(subArtifact) && prepareAdd((WorkProduct) subArtifact)) 
						{
							dropElements.add(subArtifact);
						}
					}
				}
			}
		}

		MethodConfiguration config = getMethodConfiguration();
		Set descriptorsToRefresh = synchronize ? batchCommand.getDescriptorsToRefresh() : null;
		
		List bes = activity.getBreakdownElements();
		UniqueNamePNameHandler uniqueNamesHandler = new UniqueNamePNameHandler(bes);
		
		for (int i = 0; i < dropElements.size(); i++) {
			WorkProduct wp = (WorkProduct) dropElements.get(i);			
			if (TngUtil.isContributor(wp)) {
				wp = (WorkProduct) TngUtil.getBase(wp);
			}
			WorkProductDescriptor wpDesc = null;
			if (synchronize) {
				wpDesc = (WorkProductDescriptor) ProcessCommandUtil
					.getBestDescriptor(wp, activity, config);
			}
			if (wpDesc == null) {
				wpDesc = ProcessCommandUtil.createWorkProductDescriptor(wp, config,
						wpDescToDeliverableParts);
//				uniqueNamesHandler.ensureUnique(wpDesc);
				wpDescList.add(wpDesc);

				// automatic adding to the existing deliverable descriptor in
				// the activity's scope if there is any
				// valid one.
				//
				WorkProductDescriptor descriptor = UserInteractionHelper
						.getDeliverable(activity, wp);
				if (descriptor != null) {
					wpdToDeliverableDescriptorMap.put(wpDesc, descriptor);
				}
				
				if(wpdToTaskFeaturesMap != null) {
					// replace work product with its work product descriptor in wpdToTaskFeaturesMap
					//
					Map featuresMap = (Map) wpdToTaskFeaturesMap.get(wp);
					if(featuresMap != null) {
						wpdToTaskFeaturesMap.remove(wp);
						wpdToTaskFeaturesMap.put(wpDesc, featuresMap);
					}
				}

			} else {
				if (descriptorsToRefresh != null && wpDesc.getIsSynchronizedWithSource().booleanValue()) {
					descriptorsToRefresh.add(wpDesc);
				}
				if (wp instanceof Deliverable && synchFeatures.contains(UmaPackage.eINSTANCE.getDeliverable_DeliveredWorkProducts())) {
					ProcessCommandUtil.createDeliverableParts(wpDesc,
							(Deliverable) wp, config, wpDescToDeliverableParts, descriptorsToRefresh);
				}
				
				if(wpdToTaskFeaturesMap != null) {
					wpdToTaskFeaturesMap.remove(wp);
				}
			}

		
			if (!synchronize) {
				// get all possible tasks for this workproduct for
				// which task descriptor needs to be created
				List tasks = ProcessUtil.getTasksForWorkProduct(wp, config);

				if ((tasks != null) && (tasks.size() > 0)) {
					// show task selections dialog		
					final List finalTasks = tasks;
					final WorkProduct finalWp = wp;
					final List finalSelected = new ArrayList();
					List selectedTasks = new ArrayList();
					// show task selections dialog
					UserInteractionHelper.getUIHelper().runSafely(new Runnable() {
						public void run() {
							List selected = UserInteractionHelper.selectTasks(
									finalTasks, finalWp);						
							finalSelected.addAll(selected);
						}
					}, true);
					selectedTasks.addAll(finalSelected);
					
					// create task descriptors for this workproduct
					if ((selectedTasks != null) && (!(selectedTasks.isEmpty()))) {
						for (int j = 0; j < selectedTasks.size(); j++) {
							Task task = (Task) selectedTasks.get(j);

							// call this method even the descriptor for the
							// given task already exists in this activity
							// to add any additional relationships in case of
							// recent change in the default configuration
							// of the process.
							PBSDropCommand.addToDescriptorLists(task, activity,
									taskDescList, roleDescList, wpDescList,
									wpDescToDeliverableParts,
									descriptorsToRefresh,
									batchCommand.getObjectToNewFeatureValuesMap(), 
									config, true, synchFeatures);
						}
					} else {
						// If no tasks are selected, add Responsible role to wp
						ProcessCommandUtil.addResponsibleRoleDescriptors(wpDesc, activity,
								roleDescList, descriptorsToRefresh, config);
					}
				}
				else {
					//	If there are no tasks to show, add Responsible role to wp
					ProcessCommandUtil.addResponsibleRoleDescriptors(wpDesc, activity,	roleDescList, descriptorsToRefresh, config);
				}
			}
//			else {
//				//	in case of synchronization, add Responsible role to wp
//				if (synchFeatures.contains(UmaPackage.eINSTANCE.getRole_ResponsibleFor()))
//					PBSDropCommand.addResponsibleRole(wpDesc, activity,	roleDescList, descriptorsToRefresh, config);
//			}				
		}

		if(!wpDescToDeliverableParts.isEmpty()) {
			updateDeliverablePartsCmd = new BatchCommand(true);
			for (Iterator iter = wpDescToDeliverableParts.entrySet().iterator(); iter
			.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object wpDesc = entry.getKey();
				updateDeliverablePartsCmd.getObjectToNewFeatureValuesMap().put(wpDesc, 
						Collections.singletonMap(UmaPackage.eINSTANCE.getWorkProductDescriptor_DeliverableParts(), entry.getValue()));
			}
		}
		

		// ensure unique names for wp descriptors
		for (int i = 0; i < wpDescList.size(); i++) {
			WorkProductDescriptor wpDesc = (WorkProductDescriptor) wpDescList
					.get(i);
			if (wpDesc != null) {
				uniqueNamesHandler.ensureUnique(wpDesc);
			}
		}
		return !taskDescList.isEmpty()
				|| !roleDescList.isEmpty()
				|| !wpDescList.isEmpty()
				|| (updateDeliverablePartsCmd != null && updateDeliverablePartsCmd.canExecute())
				|| !wpdToDeliverableDescriptorMap.isEmpty()
				|| (descriptorsToRefresh != null && !descriptorsToRefresh
						.isEmpty())
				|| batchCommand.canExecute();
	}
	
	/**
	 * @param subArtifact
	 * @return
	 */
	private boolean prepareAdd(WorkProduct wp) {
		if (synchronize) {	
			// get linked tasks of the task descriptors in the activity for the configuration
			//
			List tasks = getLinkedTasks();
			
			if(!tasks.isEmpty()) {
				// select only work products that are input or output of an existing task in the activity
				//	
				Map featuresMap = ProcessCommandUtil.getFeaturesMap(tasks, wp, getMethodConfiguration());
				if(!featuresMap.isEmpty()) {
					// use the wp to the key in the map right now and replace it with
					// its work product descriptor later
					//
					if(wpdToTaskFeaturesMap == null) {
						wpdToTaskFeaturesMap = new HashMap();
					}
					wpdToTaskFeaturesMap.put(wp, featuresMap);
					return true;
				}
			}
			return false;
		}
		return true;
	}

	/**
	 * @return
	 */
	private List getLinkedTasks() {
		if(linkedTasks == null) {
			linkedTasks = new ArrayList();
			for (Iterator iter = activity.getBreakdownElements().iterator(); iter.hasNext();) {
				Object element = iter.next();
				if(element instanceof TaskDescriptor) {
					Task task = ((TaskDescriptor)element).getTask();
					if(task != null && configurator.accept(element)) {
						linkedTasks.add(task);
					}
				}
			}
		}
		return linkedTasks;
	}

	/**
	 * Removes new elements that are duplicate b/c they are created by the previous WBSDropCommand
	 */
	private void removeNewDuplicates() {
		if(synchronize) {
			if(!newDuplicatesRemoved) {
				for (Iterator iter = roleDescList.iterator(); iter.hasNext();) {
					RoleDescriptor roleDesc = (RoleDescriptor) iter.next();
					Object desc = ProcessCommandUtil.getDescriptor(roleDesc.getRole(), activity, getMethodConfiguration());
					if(desc != null) {
						iter.remove();
					}
				}
				newDuplicatesRemoved = true;			
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#doExecute()
	 */
	protected void doExecute() {
		removeNewDuplicates();
		
		// automatically add work product descriptor to deliverable part
		//
		if (!wpdToDeliverableDescriptorMap.isEmpty()) {
			for (Map.Entry<WorkProductDescriptor, WorkProductDescriptor> entry : 
				wpdToDeliverableDescriptorMap.entrySet()) {
				WorkProductDescriptor deliverable = (WorkProductDescriptor) entry
						.getValue();
				deliverable.getDeliverableParts().add(entry.getKey());
			}
		}

		// add work product descriptors
		//
		activity.getBreakdownElements().addAll(wpDescList);

		// add task descriptors
		//
		activity.getBreakdownElements().addAll(taskDescList);

		// add role descriptors
		//
		activity.getBreakdownElements().addAll(roleDescList);
		
		// associate new work product descriptors with task descriptors
		//
		if(wpdToTaskFeaturesMap != null) {
			for (Iterator iter = wpdToTaskFeaturesMap.entrySet().iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				Map taskFeatures = (Map) entry.getValue();
				for (Iterator iterator = taskFeatures.entrySet().iterator(); iterator
				.hasNext();) {
					Map.Entry ent = (Map.Entry) iterator.next();
					TaskDescriptor taskDesc = (TaskDescriptor) ProcessCommandUtil.getDescriptor(ent.getKey(), activity.getBreakdownElements(), getMethodConfiguration(), false);
					for (Iterator iterator1 = ((Collection)ent.getValue()).iterator(); iterator1
					.hasNext();) {
						EStructuralFeature f = (EStructuralFeature) iterator1.next();
						EStructuralFeature descFeature = (EStructuralFeature) FEATURE_MAP.get(f);
						if(descFeature.isMany()) {
							((List)taskDesc.eGet(descFeature)).add(entry.getKey());							
						}
						else {
							// TODO: need to back up old value here
							//
							taskDesc.eSet(descFeature, entry.getKey());
						}
					}
				}
			}
		}

		// add new descriptors to activity's package
		//
		ProcessPackage pkg = (ProcessPackage) activity.eContainer();
		if (pkg != null) {
			pkg.getProcessElements().addAll(taskDescList);
			pkg.getProcessElements().addAll(roleDescList);
			pkg.getProcessElements().addAll(wpDescList);

			for (Iterator iter = wpDescToDeliverableParts.values().iterator(); iter
					.hasNext();) {
				pkg.getProcessElements().addAll((Collection) iter.next());
			}
		}

//		getModifiedResources().add(activity.eResource());
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.command.BSDropCommand#updateDescriptors()
	 */
	protected void updateDescriptors() {
		super.updateDescriptors();
		
		// add deliverable parts to the work product descriptors
		//
		if(updateDeliverablePartsCmd != null) {
			updateDeliverablePartsCmd.execute();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.command.BSDropCommand#undoUpdateDescriptors()
	 */
	protected void undoUpdateDescriptors() {
		// remove deliverable parts of the work product descriptors
		//
		if(updateDeliverablePartsCmd != null) {
			updateDeliverablePartsCmd.undo();
		}

		super.undoUpdateDescriptors();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#doUndo()
	 */
	protected void doUndo() {
		// remove work product descriptors
		//
		activity.getBreakdownElements().removeAll(wpDescList);

		// remove role descriptors
		//
		activity.getBreakdownElements().removeAll(roleDescList);

		// remove task descriptors
		//
		activity.getBreakdownElements().removeAll(taskDescList);

		// remove work product descriptor from deliverable part
		//
		if (!wpdToDeliverableDescriptorMap.isEmpty()) {
			for (Iterator iter = wpdToDeliverableDescriptorMap.entrySet()
					.iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				WorkProductDescriptor deliverable = (WorkProductDescriptor) entry
						.getValue();
				deliverable.getDeliverableParts().remove(entry.getKey());
			}
		}

		// remove descriptors from activity's package
		//
		ProcessPackage pkg = (ProcessPackage) activity.eContainer();
		if (pkg != null) {
			pkg.getProcessElements().removeAll(taskDescList);
			pkg.getProcessElements().removeAll(roleDescList);
			pkg.getProcessElements().removeAll(wpDescList);
			for (Iterator iter = wpDescToDeliverableParts.values().iterator(); iter
					.hasNext();) {
				pkg.getProcessElements().removeAll((Collection) iter.next());
			}
		}
		
		// disassociate new work product descriptors with task descriptors
		//
		if(wpdToTaskFeaturesMap != null) {
			for (Iterator iter = wpdToTaskFeaturesMap.entrySet().iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				Map taskFeatures = (Map) entry.getValue();
				for (Iterator iterator = taskFeatures.entrySet().iterator(); iterator
						.hasNext();) {
					Map.Entry ent = (Map.Entry) iterator.next();
					TaskDescriptor taskDesc = (TaskDescriptor) ProcessCommandUtil.getDescriptor(ent.getKey(), activity, getMethodConfiguration());
					for (Iterator iterator1 = ((Collection)ent.getValue()).iterator(); iterator1
							.hasNext();) {
						EStructuralFeature f = (EStructuralFeature) iterator1.next();
						EStructuralFeature descFeature = (EStructuralFeature) FEATURE_MAP.get(f);
						if(descFeature.isMany()) {
							((List)taskDesc.eGet(descFeature)).remove(entry.getKey());							
						}
						else {
							taskDesc.eSet(descFeature, null);
						}
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
	 */
	public Collection getAffectedObjects() {
		if (wpDescList != null) {
			return wpDescList;
		}

		return super.getAffectedObjects();
	}

	/**
	 * 
	 * @param task
	 * @param activity
	 * @param taskDescList
	 * @param roleDescList
	 * @param wpDescList
	 * @param wpDescToDeliverableParts
	 * @param descriptorsToRefresh
	 * @return <code>true</code> if a new TaskDescriptor is created for the
	 *         given task, <code>false</code> otherwise
	 */
	static boolean addToDescriptorLists(Task task, Activity activity,
			List taskDescList, List roleDescList, List wpDescList,
			Map wpDescToDeliverableParts, Set descriptorsToRefresh,
			Map descriptorToNewFeatureValuesMap, MethodConfiguration config, boolean useExistingDescriptor, Set synchFeatures) {
		TaskDescriptor desc = ProcessCommandUtil.createTaskDescriptor(task, activity,
				roleDescList, wpDescList, wpDescToDeliverableParts, null,
				descriptorsToRefresh, descriptorToNewFeatureValuesMap, 
				config, useExistingDescriptor, synchFeatures);
		if ((desc != null) && (taskDescList != null)
				&& !taskDescList.contains(desc)) {
			taskDescList.add(desc);
		}
		return desc != null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.command.BSDropCommand#dispose()
	 */
	public void dispose() {
		if(roleDescList != null) {
			roleDescList.clear();
		}
		if(wpDescList != null) {
			wpDescList.clear();
		}
		if(wpDescToDeliverableParts != null) {
			wpDescToDeliverableParts.clear();
		}
		if(wpdToDeliverableDescriptorMap != null) {
			wpdToDeliverableDescriptorMap.clear();
		}
		if(wpdToTaskFeaturesMap != null) {
			wpdToTaskFeaturesMap.clear();
		}
		
		super.dispose();
	}
	
}
