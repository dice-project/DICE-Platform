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
import org.eclipse.epf.authoring.gef.util.Validation;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.gef.commands.Command;

/**
 * @author Phong Nguyen Le
 * @since 3.1
 */
public class ReconnectLinkCommand extends Command {

	private boolean isSource;

	private Link link;

	private Node newNode, oldNode;

	private int viewIndex;

	private Point endPoint;

	private static final String LABEL = AuthoringUIResources.gef_reconnectLinkCommand_label; 

	public ReconnectLinkCommand(Link link, Node newNode, boolean isSource) {
		super(LABEL);
		this.link = link;
		this.newNode = newNode;
		this.isSource = isSource;
	}

	public void setEndPoint(Point p) {
		endPoint = p.getCopy();
	}

	public boolean canExecute() {
		boolean result = link != null && newNode != null;
		if (!result) {
			return false;
		}
		if (isSource) {
			return Validation.checkReconnect(newNode, link.getTarget(), link) == null;
		} else {
			return Validation.checkReconnect(link.getSource(), newNode, link) == null;
		}
	}

	public void execute() {

		// Link
		if (isSource) {
			oldNode = link.getSource();
			if (oldNode != null) { // safety check to handle dangling link
				viewIndex = oldNode.getOutgoingConnections().indexOf(link);
			}
			link.setSource(newNode);
			link.setSourceEndPoint(endPoint);
		} else {
			oldNode = link.getTarget();
			if (oldNode != null) { // safety check to handle dangling link
				viewIndex = oldNode.getIncomingConnections().indexOf(link);
			}
			link.setTarget(newNode);
			link.setTargetEndPoint(endPoint);
		}

	}

	public void undo() {
		if (isSource) {
			newNode.getOutgoingConnections().remove(link);
			oldNode.getOutgoingConnections().add(viewIndex, link);
		} else {
			newNode.getIncomingConnections().remove(link);
			oldNode.getIncomingConnections().add(viewIndex, link);
		}
		oldNode = null;
	}

}
