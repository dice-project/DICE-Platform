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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.gef.commands.Command;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ChangeBoundsCommand extends Command {

	private Node node;

	private Point newLocation, oldLocation;

	private int newWidth, oldWidth;

	private static final String LABEL = AuthoringUIResources.gef_changeBoundsCommand_label; 

	public ChangeBoundsCommand(Node node, Point newLocation, int newWidth) {
		super(ChangeBoundsCommand.LABEL);
		setNode(node);
		setNewLocation(newLocation.getCopy());
		this.newWidth = newWidth;
	}

	public boolean canExecute() {
		return node != null
				&& newLocation != null
				&& (newWidth != oldWidth || !(node.getLocation()
						.equals(newLocation)));
	}

	public void execute() {
		oldLocation = node.getLocation();
		oldWidth = node.getWidth();
		redo();
	}

	public void redo() {
		node.setLocation(newLocation);
		node.setWidth(newWidth);
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public void setNewLocation(Point loc) {
		newLocation = loc;
	}

	public void undo() {
		node.setWidth(oldWidth);
		node.setLocation(oldLocation);
	}

}