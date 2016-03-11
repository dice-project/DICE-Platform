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
import java.util.Set;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.ui.ReferenceSelection;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.util.AssociationHelper;


;

/**
 * Delete role descriptor
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class DeleteRoleDescriptor extends RemoveUnusedDescriptorsCommand {
	private Activity activity;

	private RoleDescriptor roleDesc;

	private Collection modifiedResources;

	private List notUsedReferences = new ArrayList();

	private boolean forceRemoveUnusedReferences;

	/**
	 * 
	 */
	public DeleteRoleDescriptor(RoleDescriptor roleDesc) {
		super();

		this.roleDesc = roleDesc;

		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				roleDesc, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(roleDesc);
		if (parent instanceof Activity) {
			this.activity = (Activity) parent;
		}

		this.modifiedResources = new HashSet();
		if (activity.eResource() != null) {
			modifiedResources.add(activity.eResource());
		}
		if (roleDesc.eResource() != null) {
			modifiedResources.add(roleDesc.eResource());
		}
	}

	public DeleteRoleDescriptor(RoleDescriptor roleDesc,
			boolean forceRemoveUnusedReferences) {
		this(roleDesc);
		this.forceRemoveUnusedReferences = forceRemoveUnusedReferences;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		Set refList = new HashSet();

		// get RoleDescriptor relationships references
		List assistedTaskDescriptors = AssociationHelper.getAssistedTaskDescriptors(roleDesc);
		List additionalTaskDescriptors = AssociationHelper.getAdditionalTaskDescriptors(roleDesc);
		List primaryTaskDescriptors = AssociationHelper.getPrimaryTaskDescriptors(roleDesc);
		refList.addAll(assistedTaskDescriptors);
		refList.addAll(additionalTaskDescriptors);
		refList.addAll(primaryTaskDescriptors);
		refList.addAll(roleDesc.getResponsibleFor());

		for (Iterator itor = refList.iterator(); itor.hasNext();) {
			Object object = itor.next();

			if (object instanceof Descriptor) {
				if (!ProcessUtil.checkDescriptorReferences(roleDesc,
						(Descriptor) object)) {
					if (activity.getBreakdownElements().contains(object)) // check
																			// for
																			// local
																			// descriptor
						notUsedReferences.add(object);
				}
			}
		}

		if (!(notUsedReferences.isEmpty())) {
			try {
				Object[] refToBeDeleted = forceRemoveUnusedReferences ? notUsedReferences
						.toArray()
						: ReferenceSelection.getReferences(notUsedReferences,
								roleDesc);
						
						delete(refToBeDeleted);
			}
			catch(OperationCanceledException e) {
				aborted = true;
			}
		}

		// clear relationships
//		clear(roleDesc.getAssistsIn());
		for (Iterator iter = assistedTaskDescriptors.iterator(); iter.hasNext();) {
			TaskDescriptor td = (TaskDescriptor) iter.next();
			td.getAssistedBy().remove(roleDesc);
		}
//		clear(roleDesc.getPerformsAdditionally());
		for (Iterator iter = additionalTaskDescriptors.iterator(); iter.hasNext();) {
			TaskDescriptor td = (TaskDescriptor) iter.next();
			td.getAdditionallyPerformedBy().remove(roleDesc);
		}
//		clear(roleDesc.getPerformsAsOwner());
		for (Iterator iter = primaryTaskDescriptors.iterator(); iter.hasNext();) {
			TaskDescriptor td = (TaskDescriptor) iter.next();
			td.getPerformedPrimarilyBy().remove(roleDesc);
		}
		clear(roleDesc.getResponsibleFor());

		// if role is part of any team, please remove it from team as well
		List teams = AssociationHelper.getTeamProfiles(roleDesc);
		for (Iterator itor = teams.iterator(); itor.hasNext();) {
			TeamProfile team = (TeamProfile) itor.next();
			team.getTeamRoles().remove(roleDesc);

			// find matching roleDescriptor in team roles
			Object teamRoleDesc = ProcessUtil
					.findRoleDescriptor(team, roleDesc);
			team.getTeamRoles().remove(teamRoleDesc);
		}

		redo();
	}

	private void clear(List list) {
		try {
			if ((list != null) && (list.size() > 0)) {

				list.clear();
			}
		} catch (Exception ex) {
		}
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
