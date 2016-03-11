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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaPackage;


/**
 * Command for assign roles to task descriptor. It will assign primary, additional
 * performers and assisted by to a task descriptor
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class AssignRoleToTaskDescriptor extends AddMethodElementCommand {

	private List roles;

	private Activity activity;

	private TaskDescriptor taskDesc;

	private Collection modifiedResources;

	private Collection affectedObjects;
	
	private int action;

	List existingRoleDescList = new ArrayList();

	List newRoleDescList = new ArrayList();

	private HashMap map = new HashMap();

	private MethodConfiguration config;

	private boolean isNewRoleDescriptor = false;

	private boolean calledForExculded = false;
	
	private DescriptorPropUtil propUtil;
	
	/**
	 * Assign role to task descriptor Used for both additionally performed by
	 * and assisted by
	 */
	public AssignRoleToTaskDescriptor(TaskDescriptor taskDesc, List roles,
			int action, MethodConfiguration config) {
		this(taskDesc, roles, action, config, false);
	}
	
	/**
	 * Assign role to task descriptor Used for both additionally performed by
	 * and assisted by
	 */
	public AssignRoleToTaskDescriptor(TaskDescriptor taskDesc, List roles,
			int action, MethodConfiguration config, boolean calledForExculded) {
		super(TngUtil.getOwningProcess(taskDesc));

		this.calledForExculded = calledForExculded;
		this.roles = roles;
		this.taskDesc = taskDesc;
		this.action = action;
		this.config = config;
		this.propUtil = DescriptorPropUtil.getDesciptorPropUtil();

		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				taskDesc, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(taskDesc);
		if (parent instanceof Activity) {
			this.activity = (Activity) parent;
		}

		this.modifiedResources = new HashSet();
		this.affectedObjects = new HashSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		
		// add to default configuration if not there already
		if (!super.addToDefaultConfiguration(roles))
			return;

		for (Iterator it = roles.iterator(); it.hasNext();) {
			// boolean descExists = false;
			Role role = (Role) it.next();

			RoleDescriptor roleDesc = null;
			isNewRoleDescriptor = false;
			// check for local descriptor
			roleDesc = (RoleDescriptor) ProcessCommandUtil.getDescriptor(role,
					activity, config);
			if (roleDesc == null) {
				// check for inherited descriptor
				roleDesc = (RoleDescriptor) ProcessCommandUtil
						.getInheritedDescriptor(role, activity, config);
				if (roleDesc == null) {
					roleDesc = ProcessUtil.createRoleDescriptor(role);
					isNewRoleDescriptor = true;

				}
			}
			if (isNewRoleDescriptor)
				newRoleDescList.add(roleDesc);
			else
				existingRoleDescList.add(roleDesc);

			// get team
			TeamProfile team = UserInteractionHelper.getTeam(activity, role);
			if (team != null) {
				map.put(roleDesc, team);
			}
		}

		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {

		if (action == IActionTypeConstants.ADD_PRIMARY_PERFORMER) {
			taskDesc.getPerformedPrimarilyBy().addAll(existingRoleDescList);
			taskDesc.getPerformedPrimarilyBy().addAll(newRoleDescList);
		} else if (action == IActionTypeConstants.ADD_ADDITIONAL_PERFORMER) {
			taskDesc.getAdditionallyPerformedBy().addAll(existingRoleDescList);
			taskDesc.getAdditionallyPerformedBy().addAll(newRoleDescList);
		} else if (action == IActionTypeConstants.ADD_ASSISTED_BY) {
			taskDesc.getAssistedBy().addAll(existingRoleDescList);
			taskDesc.getAssistedBy().addAll(newRoleDescList);
		}

		if (ProcessUtil.isSynFree()) {
			if (calledForExculded) {
				List excludedList = null;
				EReference ref = null;
				if (action == IActionTypeConstants.ADD_PRIMARY_PERFORMER) {
					excludedList = taskDesc.getPerformedPrimarilyByExcluded();
					ref = UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy();
				} else if (action == IActionTypeConstants.ADD_ADDITIONAL_PERFORMER) {
					excludedList = taskDesc.getAdditionallyPerformedByExclude();
					ref = UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy();
				}
				if (excludedList != null) {
					excludedList.removeAll(roles);
				}
				
				TaskDescriptor greenParent = (TaskDescriptor) propUtil.getGreenParentDescriptor(taskDesc);
				if (greenParent != null) {
					EReference eRef = LibraryEditUtil.getInstance().getExcludeFeature(ref);
					List<Role> parentExecludeList = (List<Role>) greenParent.eGet(eRef);
					for (Role role : (List<Role>) roles) {
						propUtil.removeGreenRefDelta(taskDesc, role, eRef, true);
						if (parentExecludeList != null && parentExecludeList.contains(role)) {
							propUtil.addGreenRefDelta(taskDesc, role, eRef, false);
						}
					}
				}							
			} else {
				propUtil.addLocalUsingInfo(existingRoleDescList, taskDesc, getFeature(action));
				propUtil.addLocalUsingInfo(newRoleDescList, taskDesc, getFeature(action));		
			}
			
			for (RoleDescriptor rd : (List<RoleDescriptor>) newRoleDescList) {
				propUtil.setCreatedByReference(rd, true);
			}
			
		}

		activity.getBreakdownElements().addAll(newRoleDescList);

		if (map != null) {
			Set keyset = map.keySet();
			for (Iterator itor = keyset.iterator(); itor.hasNext();) {
				Object key = itor.next();
				TeamProfile team = (TeamProfile) map.get(key);

				// add to team
				team.getTeamRoles().add((RoleDescriptor) key);
			}
		}

		
	}
	
	private EReference getFeature(int action) {
		UmaPackage up = UmaPackage.eINSTANCE;
		
		if (action == IActionTypeConstants.ADD_PRIMARY_PERFORMER) {
			return up.getTaskDescriptor_PerformedPrimarilyBy();		
		} 
		
		if (action == IActionTypeConstants.ADD_ADDITIONAL_PERFORMER) {
			return up.getTaskDescriptor_AdditionallyPerformedBy();	
		}

		if (action == IActionTypeConstants.ADD_ASSISTED_BY) {
			return up.getTaskDescriptor_AssistedBy();	
		}
		
		return null;
	}
	
	public void undo() {

		// basically remove from configuration if anything was added
		super.undo();

		if (action == IActionTypeConstants.ADD_ADDITIONAL_PERFORMER) {
			taskDesc.getPerformedPrimarilyBy().removeAll(
					existingRoleDescList);
			taskDesc.getPerformedPrimarilyBy().removeAll(newRoleDescList);
		} else	if (action == IActionTypeConstants.ADD_ADDITIONAL_PERFORMER) {
			taskDesc.getAdditionallyPerformedBy().removeAll(
					existingRoleDescList);
			taskDesc.getAdditionallyPerformedBy().removeAll(newRoleDescList);
		} else if (action == IActionTypeConstants.ADD_ASSISTED_BY) {
			taskDesc.getAssistedBy().removeAll(existingRoleDescList);
			taskDesc.getAssistedBy().removeAll(newRoleDescList);
		}
		
		if (ProcessUtil.isSynFree()) {
			if (calledForExculded) {
				List excludedList = null;
				EReference ref = null;
				if (action == IActionTypeConstants.ADD_PRIMARY_PERFORMER) {
					excludedList = taskDesc.getPerformedPrimarilyByExcluded();
					ref = UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy();
				} else if (action == IActionTypeConstants.ADD_ADDITIONAL_PERFORMER) {
					excludedList = taskDesc.getAdditionallyPerformedByExclude();
					ref = UmaPackage.eINSTANCE.getTaskDescriptor_AdditionallyPerformedBy();
				}
				if (excludedList != null) {
					excludedList.addAll(roles);
				}
				
				TaskDescriptor greenParent = (TaskDescriptor) propUtil.getGreenParentDescriptor(taskDesc);
				if (greenParent != null) {
					EReference eRef = LibraryEditUtil.getInstance().getExcludeFeature(ref);
					List<Role> parentExecludeList = (List<Role>) greenParent.eGet(eRef);
					for (Role role : (List<Role>) roles) {
						propUtil.addGreenRefDelta(taskDesc, role, eRef, true);
						if (parentExecludeList != null && parentExecludeList.contains(role)) {
							propUtil.removeGreenRefDelta(taskDesc, role, eRef, false);
						}
					}
				}
			} else {
				propUtil.removeLocalUsingInfo(existingRoleDescList, taskDesc, getFeature(action));
				propUtil.removeLocalUsingInfo(newRoleDescList, taskDesc, getFeature(action));
			}
		}
		
		activity.getBreakdownElements().removeAll(newRoleDescList);

		if (map != null) {
			Set keyset = map.keySet();
			for (Iterator itor = keyset.iterator(); itor.hasNext();) {
				Object key = itor.next();
				TeamProfile team = (TeamProfile) map.get(key);

				// remove it from team
				team.getTeamRoles().remove((RoleDescriptor) key);
			}
		}
	}

	protected boolean prepare() {
		return true;
	}

	public Collection getModifiedResources() {

		if (roles != null && !roles.isEmpty()) {
			if (activity.eResource() != null) {
				modifiedResources.add(activity.eResource());
			}
			if (taskDesc.eResource() != null) {
				modifiedResources.add(taskDesc.eResource());
			}
		}
		return modifiedResources;
	}
	
	public Collection getAffectedObjects() {
		if (roles != null && !roles.isEmpty()) {
			affectedObjects.add(activity);
			affectedObjects.add(taskDesc);
			return affectedObjects;
		}
		return super.getAffectedObjects();
	}

}
