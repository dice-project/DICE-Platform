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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.validation.UniqueNamePNameHandler;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.WorkProductDescriptor;


/**
 * Command to drap and drop tasks onto an activity in work breakdown structure
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @author Jinhua Xi
 * @since 1.0
 */
public class WBSDropCommand extends BSDropCommand {
	private ArrayList roleDescList;

	private ArrayList wpDescList;

	private HashMap wpDescToDeliverableParts;

	private HashMap<WorkProductDescriptor, WorkProductDescriptor> wpdToDeliverableDescriptorMap;

	private HashMap<RoleDescriptor, TeamProfile> roleDescTeamProfileMap;
	
	private IExecutor executor;
	
	private List<TaskDescriptor> taskDescriptorsToSyn;

	private class Executor implements IExecutor {

		public boolean preExecute() {
			taskDescList = new ArrayList();
			roleDescList = new ArrayList();
			wpDescList = new ArrayList();
			wpDescToDeliverableParts = new HashMap();
			wpdToDeliverableDescriptorMap = new HashMap();
			Set descriptorsToRefresh = synchronize ? batchCommand.getDescriptorsToRefresh() : null;

			List bes = activity.getBreakdownElements();
			UniqueNamePNameHandler uniqueNamesHandler = new UniqueNamePNameHandler(bes);
			
			for (int i = 0; i < dropElements.size(); i++) {
				Task task = (Task) dropElements.get(i);
				TaskDescriptor td = null;
				if (taskDescriptorsToSyn != null && i < taskDescriptorsToSyn.size()) {
					td = taskDescriptorsToSyn.get(i);
				}
				
				TaskDescriptor desc = ProcessCommandUtil.createTaskDescriptor(task,
						td,
						activity, roleDescList, wpDescList,
						wpDescToDeliverableParts,
						wpdToDeliverableDescriptorMap, descriptorsToRefresh,
						batchCommand.getObjectToNewFeatureValuesMap(), 
						WBSDropCommand.this.getMethodConfiguration(),
						synchronize,
						synchFeatures);
				if (desc != null) {
					uniqueNamesHandler.ensureUnique(desc);
					taskDescList.add(desc);
				}
			}
			
			//For DND a task with a Deliverable to process, don't
			//bring over with the deliverable parts of the deliverable.
			//This is to be consistent with DND a task with a artifact:
			//the sub-artifacts don't get bring over.
			wpDescToDeliverableParts.clear();

			// Add unique name check for roledesc and wp descriptors as well
			for (int i = 0; i < roleDescList.size(); i++) {
				RoleDescriptor roleDesc = (RoleDescriptor) roleDescList.get(i);			
				if (roleDesc != null) {
					uniqueNamesHandler.ensureUnique(roleDesc);	
				}
			}
			for (int i = 0; i < wpDescList.size(); i++) {
				WorkProductDescriptor wpDesc = (WorkProductDescriptor) wpDescList.get(i);			
				if (wpDesc != null) {
					uniqueNamesHandler.ensureUnique(wpDesc);	
				}
			}
			return !taskDescList.isEmpty()
					|| !roleDescList.isEmpty()
					|| !wpDescList.isEmpty()
					|| !wpDescToDeliverableParts.isEmpty()
					|| !wpdToDeliverableDescriptorMap.isEmpty()
					|| batchCommand.canExecute();
		}

		public void doExcecute() {
			// automatically add work product descriptor to deliverable part
			//
			if (!wpdToDeliverableDescriptorMap.isEmpty()) {
				for (Map.Entry<WorkProductDescriptor, WorkProductDescriptor> entry : 
					wpdToDeliverableDescriptorMap.entrySet()) {
					WorkProductDescriptor deliverable = (WorkProductDescriptor) entry
							.getValue();
					if (!deliverable.getDeliverableParts().contains(
							entry.getKey())) {
						try {
							deliverable.getDeliverableParts().add(
									entry.getKey());
						} catch (Exception e) {
							LibraryEditPlugin.INSTANCE
									.log("WBSDropCommand::doExecute - Adding deliverable part to deliverable" + e); //$NON-NLS-1$
						}
					}
				}
			}

			// add task descriptors
			//
			activity.getBreakdownElements().addAll(taskDescList);

			// add role descriptors
			//
			activity.getBreakdownElements().addAll(roleDescList);

			// add work product descriptors
			//
			activity.getBreakdownElements().addAll(wpDescList);

			// add deliverable parts to the work product descriptors
			//
			if (!wpDescToDeliverableParts.isEmpty()) {
				for (Iterator iter = wpDescToDeliverableParts.entrySet()
						.iterator(); iter.hasNext();) {
					Map.Entry entry = (Map.Entry) iter.next();
					WorkProductDescriptor wpDesc = (WorkProductDescriptor) entry
							.getKey();
					wpDesc.getDeliverableParts().addAll(
							(Collection) entry.getValue());
				}
			}

			if(roleDescTeamProfileMap == null) {
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

			// add new descriptors to activity's package
			//
			ProcessPackage pkg = (ProcessPackage) activity.eContainer();
			if (pkg != null) {
				pkg.getProcessElements().addAll(taskDescList);
				pkg.getProcessElements().addAll(roleDescList);
				pkg.getProcessElements().addAll(wpDescList);
			}
			
		}

		public void doUndo() {
			
			// remove work product descriptors
			//
			activity.getBreakdownElements().removeAll(wpDescList);

			// remove role descriptors
			//
			activity.getBreakdownElements().removeAll(roleDescList);

			// remove task descriptors
			//
			activity.getBreakdownElements().removeAll(taskDescList);

			// remove deliverable parts to the work product descriptors
			//
			if (!wpDescToDeliverableParts.isEmpty()) {
				for (Iterator iter = wpDescToDeliverableParts.entrySet()
						.iterator(); iter.hasNext();) {
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

			// remove role descriptors from team profiles
			//
			for (Iterator iter = roleDescTeamProfileMap.entrySet().iterator(); iter
					.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				TeamProfile team = (TeamProfile) entry.getValue();
				team.getTeamRoles().remove(entry.getKey());
			}

			// remove descriptors from activity's package
			//
			ProcessPackage pkg = (ProcessPackage) activity.eContainer();
			if (pkg != null) {
				pkg.getProcessElements().removeAll(taskDescList);
				pkg.getProcessElements().removeAll(roleDescList);
				pkg.getProcessElements().removeAll(wpDescList);
			}
		}

	};

	/**
	 * @param act
	 *            the activity that the given tasks are dropped on
	 * @param tasks
	 *            the tasks to drop on the given activity
	 */
	public WBSDropCommand(Activity act, List tasks) {
		super(act, tasks);
		for (Iterator iter = dropElements.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (!(element instanceof Task)) {
				iter.remove();
			}
		}
	}

	/**
	 * @param activity
	 * @param dropElements
	 * @param synch
	 */
	public WBSDropCommand(Activity activity, List dropElements, boolean synch) {
		super(activity, dropElements, synch);
	}

	/**
	 * @param activity
	 * @param dropElements
	 * @param config
	 * @param synchFeatures
	 */
	public WBSDropCommand(Activity activity, List dropElements,
			MethodConfiguration config, Set synchFeatures) {
		super(activity, dropElements, config, synchFeatures);
	}
	
	public WBSDropCommand(Activity activity, List<Task> sourceTasks,
			List<TaskDescriptor> tdsToSyn, MethodConfiguration config, Set synchFeatures) {
		this(activity, sourceTasks, config, synchFeatures);
		this.taskDescriptorsToSyn = tdsToSyn;
	}

	protected boolean allowDuplicateDropElements() {
		return taskDescriptorsToSyn != null && taskDescriptorsToSyn.size() > 1;
	}
	
	public IExecutor getExecutor() {
		if (executor == null) {
			executor = new Executor();
		}
		return executor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#preExecute()
	 */
	protected boolean preExecute() {
		if (!super.preExecute())
			return false;

		return getExecutor().preExecute();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#doExecute()
	 */
	protected void doExecute() {
		executor.doExcecute();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.AbstractCommand#getAffectedObjects()
	 */
	public Collection getAffectedObjects() {
		if (taskDescList != null) {
			return taskDescList;
		}

		return super.getAffectedObjects();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.command.BSDropCommand#doUndo()
	 */
	protected void doUndo() {
		executor.doUndo();
	}

	/* (non-Javadoc)
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

		super.dispose();
	}
}
