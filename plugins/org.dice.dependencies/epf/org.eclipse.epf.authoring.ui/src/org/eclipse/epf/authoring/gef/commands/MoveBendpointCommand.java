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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.gef.commands.Command;

/**
 * Bendpoint manipulation commands cannot be chained or compounded.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MoveBendpointCommand extends Command {

	private Link link;

	private int index;

	private AbsoluteBendpoint oldBendpoint;

	AbsoluteBendpoint newBendpoint;

	private static final String LABEL = AuthoringUIResources.gef_moveBendpointCommand_label; 

	public MoveBendpointCommand(Link link, Point location, int index) {
		super(LABEL);
		this.link = link;
		this.index = index;
		newBendpoint = new AbsoluteBendpoint(location);
		oldBendpoint = link.getBendpoints().get(index);
	}

	public boolean canExecute() {
		return link != null && index >= 0 && newBendpoint != null
				&& oldBendpoint != null;
	}

	public void execute() {
		link.getBendpoints().set(index, newBendpoint);
	}

	public void undo() {
		link.getBendpoints().set(index, oldBendpoint);
	}

}