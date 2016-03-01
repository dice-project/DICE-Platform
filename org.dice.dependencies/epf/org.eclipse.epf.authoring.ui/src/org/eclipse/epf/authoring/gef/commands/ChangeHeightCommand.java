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
package org.eclipse.epf.authoring.gef.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.gef.commands.Command;

/**
 * @author Shashidhar Kannoori
 * Command to handle Node's height changes.
 * ChangeBoundsCommand handles Node's width changes. 
 */
public class ChangeHeightCommand extends Command {

	private Node node;

	private Point newLocation, oldLocation;

	private int newHeight, oldHeight;

	private static final String LABEL = "Change Height Command"; //$NON-NLS-1$

	public ChangeHeightCommand(Node node, Point newLocation, int newHeight) {
		super(ChangeHeightCommand.LABEL);
		setNode(node);
		setNewLocation(newLocation.getCopy());
		this.newHeight = newHeight;
	}

	public boolean canExecute() {
		return node != null
				&& newLocation != null
				&& (newHeight != oldHeight || !(node.getLocation()
						.equals(newLocation)));
	}

	public void execute() {
		oldLocation = node.getLocation();
		oldHeight = node.getHeight();
		redo();
	}

	public void redo() {
		node.setLocation(newLocation);
		node.setHeight(newHeight);
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public void setNewLocation(Point loc) {
		newLocation = loc;
	}

	public void undo() {
		node.setHeight(oldHeight);
		node.setLocation(oldLocation);
	}
}
