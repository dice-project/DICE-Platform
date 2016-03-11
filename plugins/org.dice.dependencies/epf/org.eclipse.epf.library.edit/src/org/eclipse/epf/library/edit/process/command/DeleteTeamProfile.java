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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.TeamProfile;


/**
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class DeleteTeamProfile extends AbstractCommand implements
		IResourceAwareCommand {
	private Activity activity;

	private TeamProfile team;

	private Collection modifiedResources;

	/**
	 * 
	 */
	public DeleteTeamProfile(TeamProfile team) {
		super();

		this.team = team;

		Object parent = getParentActivity(team);

		if (parent instanceof Activity) {
			this.activity = (Activity) parent;
		}

		this.modifiedResources = new HashSet();
		if (activity.eResource() != null) {
			modifiedResources.add(activity.eResource());
		}
		if (team.eResource() != null) {
			modifiedResources.add(team.eResource());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		// get all subteam recursively
		List teamList = new ArrayList();
		getSubTeams(team, teamList);

		teamList.add(team);

		for (int i = 0; i < teamList.size(); i++) {
			TeamProfile tt = (TeamProfile) teamList.get(i);
			// clear team's roledescriptor
			tt.getTeamRoles().clear();
		}

		redo();
	}

	private void getSubTeams(TeamProfile team, List teamList) {
		List list = team.getSubTeam();
		if (list != null) {
			for (Iterator itor = list.iterator(); itor.hasNext();) {
				TeamProfile tt = (TeamProfile) itor.next();
				teamList.add(tt);
				getSubTeams(tt, teamList);
			}
		}
	}

	/**
	 * Get parent activity for breakdownelement
	 * 
	 * @param brElement
	 * @return
	 */
	private Object getParentActivity(BreakdownElement brElement) {
		AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
				.adapt(brElement, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(brElement);
		while (!(parent instanceof Activity)) {
			brElement = (BreakdownElement) parent;
			adapter = (ItemProviderAdapter) adapterFactory.adapt(brElement,
					ITreeItemContentProvider.class);
			parent = adapter.getParent(brElement);
		}

		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {

	}

	public void undo() {

	}

	protected boolean prepare() {
		return true;
	}

	public Collection getModifiedResources() {

		return modifiedResources;
	}
}
