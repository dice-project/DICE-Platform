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
package org.eclipse.epf.authoring.ui.actions;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.command.MoveDownCommand;
import org.eclipse.epf.uma.Activity;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Moves up breakdown element in a process tree.
 * 
 * @author Phong Nguyen Le
 * @since  1.2
 */
public class MoveDownAction extends AbstractMoveAction {

	private static final String LABEL = AuthoringUIResources.ProcessEditor_Action_MoveDown;
	private static final ImageDescriptor IMG_DESC = AuthoringUIPlugin
			.getDefault().getImageDescriptor("full/etool16/move_down.gif"); //$NON-NLS-1$
	private static final ImageDescriptor DISABLED_IMG_DESC = AuthoringUIPlugin
			.getDefault().getImageDescriptor(
					"full/etool16/move_down_disabled.gif"); //$NON-NLS-1$
	
	/**
	 * Creates an instance
	 *
	 */
	public MoveDownAction() {
		super(LABEL);
		setImageDescriptor(IMG_DESC);
		setDisabledImageDescriptor(DISABLED_IMG_DESC);
		setToolTipText(LABEL);
	}

	@Override
	protected Command createMoveCommand(Activity act, Object element,
			Collection<EClass> classes) {
		return new MoveDownCommand(act, element, classes);
	}	
	
	@Override
	protected boolean accept(IStructuredSelection selection) {		
		if(super.accept(selection)) {
			Object element = selection.getFirstElement();
			IBSItemProvider ip = getItemProvider(element);
			if(ip != null) {
				return !ip.isLastElement(element);
			}
		}
		return false;
	}
}
