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
package org.eclipse.epf.authoring.ui.actions;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewPart;

/**
 * Method Create Sibiling action.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class MethodCreateSiblingAction extends
		StaticSelectionCommandAction {

	/**
	 * This describes the sibling to be created.
	 */
	protected Object descriptor;

	/**
	 * This constructs an instance of an action that creates a sibling specified
	 * by <code>descriptor</code>.
	 */
	public MethodCreateSiblingAction(IViewPart viewPart, ISelection selection,
			Object descriptor) {
		super();
		if (viewPart instanceof IEditingDomainProvider) {
			editingDomain = ((IEditingDomainProvider) viewPart)
					.getEditingDomain();
		}
		this.descriptor = descriptor;
		configureAction(selection);
	}

	/**
	 * This creates the command for {@link
	 * StaticSelectionCommandAction#createActionCommand}.
	 */
	protected Command createActionCommand(EditingDomain editingDomain,
			Collection collection) {
		if (collection.size() == 1) {
			Command cmd = CreateChildCommand.create(editingDomain, null,
					descriptor, collection);
			return new CreateMethodElementCommand(cmd);
		}
		return UnexecutableCommand.INSTANCE;
	}

}
