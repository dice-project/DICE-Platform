/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.library.edit.process.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TeamProfile;


/**
 * Command to add role to team profile
 *  
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class AddRoleToTeamCommand extends AddMethodElementCommand {

	private List roles;

	private List roleDescList;

	private Activity activity;

	private TeamProfile team;

	private Collection modifiedResources;

	private List existingRoleDescList;

	public AddRoleToTeamCommand(TeamProfile team, List roles) {

		super(TngUtil.getOwningProcess(team));

		this.roles = roles;
		this.team = team;

		this.activity = (Activity) ProcessUtil.getParentActivityOfTeam(team);

		this.modifiedResources = new HashSet();
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

		roleDescList = new ArrayList();
		for (Iterator itor = roles.iterator(); itor.hasNext();) {
			RoleDescriptor roleDesc = ProcessUtil
					.createRoleDescriptor((Role) itor.next());
			roleDescList.add(roleDesc);
		}

		// automatic assignment
		existingRoleDescList = new ArrayList();
		ProcessUtil.getRoleDescriptor(TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory(), activity,
				existingRoleDescList, roles);

		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {

		if (!roleDescList.isEmpty()) {
			for (Iterator itor = roleDescList.iterator(); itor.hasNext();) {
				team.getTeamRoles().add((RoleDescriptor) itor.next());
			}
		}

		if (!existingRoleDescList.isEmpty()) {
			for (Iterator itor = existingRoleDescList.iterator(); itor
					.hasNext();) {
				team.getTeamRoles().add((RoleDescriptor) itor.next());
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#undo()
	 */
	public void undo() {
		if (!roleDescList.isEmpty()) {
			// basically remove from configuration if anything was added
			super.undo();
			for (Iterator itor = roleDescList.iterator(); itor.hasNext();) {
				team.getTeamRoles().remove((RoleDescriptor) itor.next());
			}
		}
		if (!existingRoleDescList.isEmpty()) {
			for (Iterator itor = existingRoleDescList.iterator(); itor
					.hasNext();) {
				team.getTeamRoles().remove((RoleDescriptor) itor.next());
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
			if (team.eResource() != null) {
				modifiedResources.add(team.eResource());
			}
		}
		return modifiedResources;
	}

	public Collection getAffectedObjects() {
		if (roleDescList != null) {
			return roleDescList;
		}
		return super.getAffectedObjects();
	}
}
