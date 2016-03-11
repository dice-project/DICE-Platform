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
package org.eclipse.epf.authoring.gef.commands;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.gef.commands.Command;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class DeleteBendpointCommand extends Command {

	private Link link;

	private AbsoluteBendpoint bendpoint;

	private int index;

	private static final String LABEL = AuthoringUIResources.gef_deleteBendpointCommand_label; 

	public DeleteBendpointCommand(Link link, int index) {
		super(LABEL);
		this.link = link;
		this.index = index;
		bendpoint = link.getBendpoints().get(index);
	}

	public boolean canExecute() {
		return index >= 0 && bendpoint != null && link != null;
	}

	public void execute() {
		link.getBendpoints().remove(index);
	}

	public void undo() {
		link.getBendpoints().add(index, bendpoint);
	}

}