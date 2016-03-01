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
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;


/**
 * 
 * Command to assign primary performer to task descriptor
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class AssignPrimaryPerformerToTaskDescriptor extends
		AddMethodElementCommand {
	private RoleDescriptor newRoleDesc = null;

	private RoleDescriptor oldRoleDesc = null;

	private Role role;

	private List roles;

	private Activity activity;

	private TaskDescriptor taskDesc;

	private Collection modifiedResources;
	
	private Collection affectedObjects;

	// private boolean descExists = false;

	private TeamProfile team;

	private MethodConfiguration config;

	private boolean isNewRoleDescriptor = false;

	/**
	 * 
	 */
	public AssignPrimaryPerformerToTaskDescriptor(TaskDescriptor taskDesc,
			Role role, MethodConfiguration config) {
		super(TngUtil.getOwningProcess(taskDesc));

		this.role = role;
		this.taskDesc = taskDesc;
		this.config = config;

		roles = new ArrayList();
		roles.add(role);

		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				taskDesc, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(taskDesc);
		if (parent instanceof Activity) {
			this.activity = (Activity) parent;
		}

		this.modifiedResources = new HashSet(); 
		this.affectedObjects  = new HashSet();
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

		if (!roles.isEmpty()) {
			isNewRoleDescriptor = false;
			// check for local descriptor
			newRoleDesc = (RoleDescriptor) ProcessCommandUtil.getDescriptor(
					role, activity, config);
			if (newRoleDesc == null) {
				// check for inherited descriptor
				newRoleDesc = (RoleDescriptor) ProcessCommandUtil
						.getInheritedDescriptor(role, activity, config);
				if (newRoleDesc == null) {

					newRoleDesc = ProcessUtil.createRoleDescriptor(role);
					isNewRoleDescriptor = true;
				}
			}

			// get team
			team = UserInteractionHelper.getTeam(activity, role);

			redo();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {

		taskDesc.getPerformedPrimarilyBy().add(newRoleDesc);

		if (isNewRoleDescriptor) {
			activity.getBreakdownElements().add(newRoleDesc);
		}

		if (team != null) {
			team.getTeamRoles().add(newRoleDesc);
		}
	}

	public void undo() {

		if (!roles.isEmpty()) {
			// remove from configuration if added
			super.undo();

			taskDesc.getPerformedPrimarilyBy().remove(newRoleDesc);

			if (isNewRoleDescriptor) {
				activity.getBreakdownElements().remove(newRoleDesc);
			}
			if (team != null) {
				team.getTeamRoles().remove(newRoleDesc);
			}
		}
	}

	protected boolean prepare() {
		return true;
	}

	public Collection getModifiedResources() {

		if (role != null) {
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
		if (role != null) {
			affectedObjects.add(activity);
			affectedObjects.add(taskDesc);
			return affectedObjects;
		}
		return super.getAffectedObjects();
	}

}
