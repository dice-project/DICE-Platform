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
import org.eclipse.epf.authoring.gef.util.Validation;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.gef.commands.Command;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class CreateLinkCommand extends Command {

	private Link link;

	private Node source, target;

	private Point targetEndPoint;

	private Point sourceEndPoint;

	private static final String LABEL = AuthoringUIResources.gef_createLinkCommand_label; 

	public CreateLinkCommand(Link newObj, Node src) {
		super(LABEL);
		link = newObj;
		source = src;
	}

	public boolean canExecute() {
		boolean result = source != null && target != null && link != null;
		if (!result)
			return result;

		String msg = Validation.checkConnect(source, target, link);
		if (msg != null) {
			return false;
		}
		return true;
	}

	public void execute() {
		if (source == target) {
			link.getBendpoints().add(
					new AbsoluteBendpoint(source.getLocation().getTranslated(
							-10, 10)));
			link.getBendpoints().add(
					new AbsoluteBendpoint(source.getLocation().getTranslated(
							-10, -10)));
			link.getBendpoints().add(
					new AbsoluteBendpoint(source.getLocation().getTranslated(
							10, -10)));
		}

		link.setTargetEndPoint(targetEndPoint);
		link.setSourceEndPoint(sourceEndPoint);
		link.setSource(source);
		link.setTarget(target);
	}

	public void setTarget(Node target) {
		this.target = target;
	}

	public void setTargetEndPoint(Point p) {
		targetEndPoint = p;
	}

	public void setSourceEndPoint(Point p) {
		sourceEndPoint = p;
	}

	public Point getTargetEndPoint() {
		return targetEndPoint;
	}

	/**
	 * @return Returns the sourceEndPoint.
	 */
	public Point getSourceEndPoint() {
		return sourceEndPoint;
	}

	public void undo() {
		link.setSourceEndPoint(null);
		link.setTargetEndPoint(null);
		link.setSource(null);
		link.setTarget(null);
		if (source == target) {
			link.getBendpoints().clear();
		}
	}

}