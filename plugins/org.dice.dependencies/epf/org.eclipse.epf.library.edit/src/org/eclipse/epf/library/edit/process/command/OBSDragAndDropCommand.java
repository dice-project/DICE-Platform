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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.CompositeRole;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaPackage;

/**
 * Drag and drop command for dragging role into Team Allocation
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class OBSDragAndDropCommand extends BSDragAndDropCommand {

	/**
	 * @param domain
	 * @param owner
	 * @param location
	 * @param operations
	 * @param operation
	 * @param collection
	 */
	public OBSDragAndDropCommand(EditingDomain domain, Object owner,
			float location, int operations, int operation, Collection collection) {
		super(domain, owner, location, operations, operation, collection);
	}

	protected boolean accept(Object obj) {
		if (obj instanceof Role) {
			if (owner instanceof Activity) {
				return true;
//				return ProcessCommandUtil.getValidDescriptor(obj,
//						(Activity) owner,
//						((AdapterFactoryEditingDomain) domain)
//								.getAdapterFactory()) == null;
			} else if (owner instanceof TeamProfile) {
				if (ProcessUtil.getAssociatedElementList(
						((TeamProfile) owner).getTeamRoles()).contains(obj))
					return false;
				return true;
			}
		}
		
		return false;
	}

	protected Command createDropCommand(Object owner, List dropSrc) {
		if (owner instanceof Activity) {
			return new OBSDropCommand((Activity) owner, dropSrc);
		} else if (owner instanceof TeamProfile) {
			// return new GenericDropCommand((TeamProfile) owner,
			// UmaPackage.eINSTANCE.getTeamProfile_TeamRoles(), dropSrc,
			// getDropAdapter(owner));
			return new AddRoleToTeamCommand((TeamProfile) owner, dropSrc);
		} else if (owner instanceof CompositeRole) {
			return new GenericDropCommand((CompositeRole) owner,
					UmaPackage.eINSTANCE.getCompositeRole_AggregatedRoles(),
					dropSrc, getDropAdapter(owner));
		}

		return UnexecutableCommand.INSTANCE;
	}

	private GenericDropCommand.ElementAdapter getDropAdapter(Object owner) {
		AdapterFactory adapterFactory = ((AdapterFactoryEditingDomain) domain)
				.getAdapterFactory();
		BreakdownElementItemProvider adapter = (BreakdownElementItemProvider) adapterFactory
				.adapt(owner, IEditingDomainItemProvider.class);
		return adapter.createDropAdapter();
	}
}
