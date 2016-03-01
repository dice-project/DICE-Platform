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
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class CreateBendpointCommand extends Command {

	private Link link;

	private AbsoluteBendpoint point;

	private int index = -1;

	private static final String LABEL = AuthoringUIResources.gef_createBendpointCommand_label; 

	public CreateBendpointCommand(Link link, Point location, int index) {
		super(LABEL);
		this.link = link;
		point = new AbsoluteBendpoint(location);
		this.index = index;
	}

	public boolean canExecute() {
		return link != null && point != null;
	}

	public void execute() {
		link.getBendpoints().add(index, point);
	}

	public void undo() {
		link.getBendpoints().remove(index);
	}

}