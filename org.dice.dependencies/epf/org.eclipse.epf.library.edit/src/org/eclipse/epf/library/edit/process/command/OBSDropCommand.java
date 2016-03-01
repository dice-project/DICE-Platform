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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.UniqueNamePNameHandler;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;

/**
 * Command to drap and drop roles to Team Allocation breakdown structure
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class OBSDropCommand extends BSDropCommand {
	private ArrayList wpDescList;

	private ArrayList roleDescList;

	private Map<RoleDescriptor, TeamProfile> roleDescTeamProfileMap;

	private Map wpDescToDeliverableParts;

	private HashMap<WorkProductDescriptor, WorkProductDescriptor> wpdToDeliverableDescriptorMap;

	private HashMap wpdToTaskFeaturesMap; // map of WorkProductDescriptor to

	// map of task ot feature list

	private IConfigurator configrator;

	private boolean newDuplicatesRemoved;
	
	public OBSDropCommand(Activity act, List roles) {
		super(act, roles);
		for (Iterator iter = dropElements.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (!(element instanceof Role)) {
				iter.remove();
			}
		}
	}

	/**
	 * @param activity
	 * @param dropElements
	 * @param config
	 * @param synchFeatures
	 */
	public OBSDropCommand(Activity activity, List dropElements,
			MethodConfiguration config, Set synchFeatures,
			IConfigurator configurator) {
		super(activity, dropElements, config, synchFeatures);
		this.configrator = configurator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#preExecute()
	 */
	protected boolean preExecute() {
		if (!super.preExecute())
			return false;

		roleDescList = new ArrayList();
		taskDescList = new ArrayList();
		wpDescList = new ArrayList();
		wpDescToDeliverableParts = new HashMap();
		wpdToDeliverableDescriptorMap = new HashMap();

		MethodConfiguration config = getMethodConfiguration();
		Set descriptorsToRefresh = synchronize ? batchCommand
				.getDescriptorsToRefresh() : null;

		List bes = activity.getBreakdownElements();
		UniqueNamePNameHandler uniqueNamesHandler = new UniqueNamePNameHandler(bes);
				
		int size = dropElements.size();
		for (int i = 0; i < size; i++) {
			Role role = (Role) dropElements.get(i);
			if (TngUtil.isContributor(role)) {
				role = (Role) TngUtil.getBase(role);
			}

			// check if the role descriptor of the role exists under this
			// activity
			RoleDescriptor roleDesc = null;
			if (synchronize) {
				roleDesc = (RoleDescriptor) ProcessCommandUtil
					.getBestDescriptor(role, activity, config);
			}

			if (roleDesc == null) {
				// create role descriptor object
				roleDesc = ProcessUtil.createRoleDescriptor(role);
				uniqueNamesHandler.ensureUnique(roleDesc);
				roleDescList.add(roleDesc);
			} else {
				// need to clear all the refreshable features of the existing
				// descriptor
				//
				if (synchronize
						&& roleDesc.getIsSynchronizedWithSource()
								.booleanValue()) {
					batchCommand.getDescriptorsToRefresh().add(roleDesc);
				}
			}
		}

		// if not a synchronization, get all workproducts which have role as
		// responsible role for
		// which work product descriptor needs to be created
		//
		if (!synchronize) {
			for (int i = 0; i < size; i++) {
				Role role = (Role) dropElements.get(i);
				List workProducts = ProcessUtil.getWorkProductsForRole(role,
						config);
				if ((workProducts != null) && (!(workProducts.isEmpty()))) {
					HashMap wpToExistingDescriptorMap = new HashMap();
					List elements = new ArrayList(activity
							.getBreakdownElements());
					elements.addAll(wpDescList);
					for (Iterator iter = workProducts.iterator(); iter
							.hasNext();) {
						WorkProduct wp = (WorkProduct) iter.next();						
						Object wpd = ProcessUtil.getWorkProductDescriptor(wpDescList, wp);
						if(wpd == null) {
							wpd = ProcessCommandUtil.getDescriptor(wp, activity, config);
						}
						if (wpd != null) {
							wpToExistingDescriptorMap.put(wp, wpd);
						}
					}
					List selectedWorkProducts = new ArrayList(
							wpToExistingDescriptorMap.keySet());
					workProducts.removeAll(selectedWorkProducts);
					
					final List finalSelectedWps = new ArrayList();
					if (!workProducts.isEmpty()) {
						final List finalWps = new ArrayList();
						finalWps.addAll(workProducts);
						final Role finalRole = role;
						
						UserInteractionHelper.getUIHelper().runSafely(new Runnable() {
							public void run() {
								List selected = UserInteractionHelper
										.selectWorkProducts(finalWps, finalRole);
								if (selected != null) {
									finalSelectedWps.addAll(selected);
								}
							}
						}, true);
					}

					selectedWorkProducts.addAll(finalSelectedWps);
					if (!selectedWorkProducts.isEmpty()) {
						HashSet allSelectedTasks = new HashSet();

						for (int j = 0; j < selectedWorkProducts.size(); j++) {
							WorkProduct wp = (WorkProduct) selectedWorkProducts
									.get(j);

							if (TngUtil.isContributor(wp)) {
								wp = (WorkProduct) TngUtil.getBase(wp);
							}
							WorkProductDescriptor wpDesc = (WorkProductDescriptor) wpToExistingDescriptorMap.get(wp);
							if (wpDesc == null) {
								wpDesc = (WorkProductDescriptor) ProcessCommandUtil
										.getDescriptor(wp, activity, config);
							}
							if (wpDesc == null) {
								wpDesc = ProcessCommandUtil
										.createWorkProductDescriptor(wp,
												config,
												wpDescToDeliverableParts);
								wpDescList.add(wpDesc);

								// automatic adding to the existing deliverable
								// descriptor in the activity's scope if there
								// is
								// any
								// valid one.
								//
								WorkProductDescriptor descriptor = UserInteractionHelper
										.getDeliverable(activity, wp);
								if (descriptor != null) {
									wpdToDeliverableDescriptorMap.put(wpDesc,
											descriptor);
								}

							} else {
								if (descriptorsToRefresh != null
										&& wpDesc.getIsSynchronizedWithSource()
												.booleanValue()) {
									descriptorsToRefresh.add(wpDesc);
								}
								if (wp instanceof Deliverable
										&& synchFeatures
												.contains(UmaPackage.eINSTANCE
														.getDeliverable_DeliveredWorkProducts())) {
									ProcessCommandUtil.createDeliverableParts(
											wpDesc, (Deliverable) wp, config,
											wpDescToDeliverableParts,
											descriptorsToRefresh);
								}
							}

							// get all possible tasks for this workproduct for
							// which task descriptor needs to be created
							List tasks = ProcessUtil.getTasksForWorkProduct(wp,
									config);

							if ((tasks != null) && (tasks.size() > 0)) {
								// exclude any task that is already selected or already exists in activity
								//
								tasks.removeAll(allSelectedTasks);
								for (Iterator iter = tasks.iterator(); iter
										.hasNext();) {
									Task task = (Task) iter.next();
									Object td = ProcessUtil.getTaskDescriptor(taskDescList, task); 
									if(td == null) {
										td = ProcessCommandUtil.getDescriptor(task, activity, config);
									}
									if(td != null) {
										iter.remove();
									}
								}

								if (!tasks.isEmpty()) {
									final List finalTasks = tasks;
									final WorkProduct finalWp = wp;
									final List finalSelected = new ArrayList();
									List selectedTasks = new ArrayList();
									// show task selections dialog
									UserInteractionHelper.getUIHelper().runSafely(new Runnable() {
										public void run() {
											List selected = UserInteractionHelper
											.selectTasks(finalTasks, finalWp);
											finalSelected.addAll(selected);
										}
									}, true);
									selectedTasks.addAll(finalSelected);
									if ((selectedTasks != null)
											&& ((!selectedTasks.isEmpty()))) {
										allSelectedTasks.addAll(selectedTasks);
									} else {
										// If no tasks are selected, add
										// Responsible role to wp
										ProcessCommandUtil.addResponsibleRoleDescriptors(
												wpDesc, activity, roleDescList,
												null, config);
									}
								}
							} else {
								// If there are no tasks to show, add
								// Responsible role to wp
								ProcessCommandUtil.addResponsibleRoleDescriptors(wpDesc,
										activity, roleDescList, null, config);
							}
						}

						// create task descriptors for this workproduct
						if ((allSelectedTasks != null)
								&& (!(allSelectedTasks.isEmpty()))) {
							for (Iterator itor = allSelectedTasks.iterator(); itor
									.hasNext();) {

								Task task = (Task) itor.next();

								// call this method even the descriptor for the
								// given task already exists in this activity
								// to add any additional relationships in case
								// of
								// recent change in the default configuration
								// of the process.
								PBSDropCommand
										.addToDescriptorLists(
												task,
												activity,
												taskDescList,
												roleDescList,
												wpDescList,
												wpDescToDeliverableParts,
												descriptorsToRefresh,
												batchCommand
														.getObjectToNewFeatureValuesMap(),
												config, synchronize, synchFeatures);
							}
						}
					}
				}
			}
		}

		// ensure unique names for wp descriptors
		for (int i = 0; i < wpDescList.size(); i++) {
			WorkProductDescriptor wpDesc = (WorkProductDescriptor) wpDescList.get(i);			
			if (wpDesc != null) {
				uniqueNamesHandler.ensureUnique(wpDesc);	
			}
		}
		return (!roleDescList.isEmpty() // || !roleAssociationList.isEmpty()
				|| !taskDescList.isEmpty()
				|| !wpDescList.isEmpty()
				|| (wpDescToDeliverableParts != null && !wpDescToDeliverableParts
						.isEmpty()) || !wpdToDeliverableDescriptorMap.isEmpty())
				|| (descriptorsToRefresh != null && !descriptorsToRefresh
						.isEmpty()) || batchCommand.canExecute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#doExecute()
	 */
	protected void doExecute() {
		removeNewDuplicates();

		// add role descriptors to activity
		//
		activity.getBreakdownElements().addAll(roleDescList);

		if (roleDescTeamProfileMap == null) {
			// prepare for automatic assignment of role descriptor to team
			// profile
			//
			roleDescTeamProfileMap = new HashMap();
			for (Iterator iter = roleDescList.iterator(); iter.hasNext();) {
				RoleDescriptor roleDesc = (RoleDescriptor) iter.next();
				TeamProfile teamProfile = UserInteractionHelper.getTeam(
						activity, roleDesc.getRole());
				if (teamProfile != null) {
					roleDescTeamProfileMap.put(roleDesc, teamProfile);
				}
			}
		}

		// add role descriptors to team profiles
		//
		for (Map.Entry<RoleDescriptor, TeamProfile> entry : roleDescTeamProfileMap.entrySet()) {
			TeamProfile team = (TeamProfile) entry.getValue();
			team.getTeamRoles().add(entry.getKey());
		}

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

		// add deliverable parts to the work product descriptors
		//
		if (!wpDescToDeliverableParts.isEmpty()) {
			for (Iterator iter = wpDescToDeliverableParts.entrySet().iterator(); iter
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				WorkProductDescriptor wpDesc = (WorkProductDescriptor) entry
						.getKey();
				wpDesc.getDeliverableParts().addAll(
						(Collection) entry.getValue());
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

		// getModifiedResources().add(activity.eResource());

	}

	/**
	 * Removes new elements that are duplicate b/c they are created by the
	 * previous WBSDropCommand
	 */
	private void removeNewDuplicates() {
		if (synchronize) {
			if (!newDuplicatesRemoved) {
				for (Iterator iter = wpDescList.iterator(); iter.hasNext();) {
					WorkProductDescriptor wpd = (WorkProductDescriptor) iter
							.next();
					Object desc = ProcessCommandUtil.getDescriptor(wpd
							.getWorkProduct(), activity,
							getMethodConfiguration());
					if (desc != null) {
						iter.remove();
						if (wpDescToDeliverableParts != null) {
							wpDescToDeliverableParts.remove(wpd);
						}
						if (wpdToTaskFeaturesMap != null) {
							wpdToTaskFeaturesMap.remove(wpd);
						}
						if (wpdToDeliverableDescriptorMap != null) {
							wpdToDeliverableDescriptorMap.remove(wpd);
						}
					}
				}
				newDuplicatesRemoved = true;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#doUndo()
	 */
	protected void doUndo() {
		// remove role descriptors from activity
		//
		activity.getBreakdownElements().removeAll(roleDescList);

		// remove role descriptors from team profiles
		//
		for (Iterator iter = roleDescTeamProfileMap.entrySet().iterator(); iter
				.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			TeamProfile team = (TeamProfile) entry.getValue();
			team.getTeamRoles().remove(entry.getKey());
		}

		// remove work product descriptors
		//
		activity.getBreakdownElements().removeAll(wpDescList);

		// remove task descriptors
		//
		activity.getBreakdownElements().removeAll(taskDescList);

		// // disassociate new work product descriptors with task descriptors
		// //
		// if(wpdToTaskFeaturesMap != null) {
		// for (Iterator iter = wpdToTaskFeaturesMap.entrySet().iterator();
		// iter.hasNext();) {
		// Map.Entry entry = (Map.Entry) iter.next();
		// Map taskFeatures = (Map) entry.getValue();
		// for (Iterator iterator = taskFeatures.entrySet().iterator(); iterator
		// .hasNext();) {
		// Map.Entry ent = (Map.Entry) iterator.next();
		// TaskDescriptor taskDesc = (TaskDescriptor)
		// ProcessCommandUtil.getDescriptor(ent.getKey(), activity,
		// getMethodConfiguration());
		// for (Iterator iterator1 = ((Collection)ent.getValue()).iterator();
		// iterator1
		// .hasNext();) {
		// EStructuralFeature f = (EStructuralFeature) iterator1.next();
		// EStructuralFeature descFeature = (EStructuralFeature)
		// FEATURE_MAP.get(f);
		// if(descFeature.isMany()) {
		// ((List)taskDesc.eGet(descFeature)).remove(entry.getKey());
		// }
		// else {
		// taskDesc.eSet(descFeature, null);
		// }
		// }
		// }
		// }
		// }

		// remove deliverable parts to the work product descriptors
		//
		if (!wpDescToDeliverableParts.isEmpty()) {
			for (Iterator iter = wpDescToDeliverableParts.entrySet().iterator(); iter
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				WorkProductDescriptor wpDesc = (WorkProductDescriptor) entry
						.getKey();
				wpDesc.getDeliverableParts().removeAll(
						(Collection) entry.getValue());
			}
		}

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

		// remove role descriptors from activity's package
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
	}

	// /**
	// * Get task descriptors map for an activity
	// * @param activity
	// * @return
	// */
	// private static HashMap getTasksMapForActivity(Activity activity)
	// {
	// List elements = activity.getBreakdownElements();
	// HashMap map = new HashMap();
	// for (int i=0; i < elements.size(); i++)
	// {
	// Object obj = elements.get(i);
	// if (obj instanceof TaskDescriptor)
	// {
	// TaskDescriptor taskDesc = (TaskDescriptor) obj;
	// Task task = taskDesc.getTask();
	// if (task != null)
	// {
	// map.put(task, taskDesc);
	// }
	// }
	// }
	// return map;
	// }
	//	
	//	
	// /**
	// * Show RoleAssociation Dialog
	// * @param tasks
	// */
	// private static int showRoleAssociationDialog(Task task)
	// {
	//		
	// RoleAssociationDialog roleAssoc = new
	// RoleAssociationDialog(Display.getCurrent().getActiveShell(), task);
	// roleAssoc.open();
	//		
	// // selected tasks;
	// return roleAssoc.getRoleAssociation();
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
	 */
	public Collection getAffectedObjects() {
		if (roleDescList != null) {
			return roleDescList;
		}

		return super.getAffectedObjects();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.process.command.BSDropCommand#dispose()
	 */
	public void dispose() {
		if (roleDescList != null) {
			roleDescList.clear();
		}
		if (roleDescTeamProfileMap != null) {
			roleDescTeamProfileMap.clear();
		}
		if (wpDescList != null) {
			wpDescList.clear();
		}
		if (wpDescToDeliverableParts != null) {
			wpDescToDeliverableParts.clear();
		}
		if (wpdToDeliverableDescriptorMap != null) {
			wpdToDeliverableDescriptorMap.clear();
		}
		if (wpdToTaskFeaturesMap != null) {
			wpdToTaskFeaturesMap.clear();
		}

		super.dispose();
	}
	
}
