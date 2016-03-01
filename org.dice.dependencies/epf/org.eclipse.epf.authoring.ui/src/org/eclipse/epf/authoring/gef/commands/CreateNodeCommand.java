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
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class CreateNodeCommand extends DiagramModifyCommand {

	private Point loc;

	private Node node;

	private Diagram diagram;

	private static final String LABEL = AuthoringUIResources.gef_createNodeCommand_label; 

	public CreateNodeCommand(Node newObject, Diagram parent, Point location) {
		super(LABEL);
		node = newObject;
		diagram = parent;
		loc = location.getCopy();
		setDiagram(parent);
	}

	public void undo() {
		node.setLocation(null);
		node.setDiagram(null);
		diagram.getNodes().remove(node);
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.commands.DiagramModifyCommand#doExecute()
	 */
	protected void doExecute() {
		node.setLocation(loc);
		node.setDiagram(diagram);
		if (node instanceof NamedNode) {
			diagram.setDefaultName((NamedNode) node);
		}
		diagram.getNodes().add(node);
	}

	public boolean canExecute() {
		if (diagram instanceof Diagram) {
			if (diagram.isReadOnly()) {
				return false;
			}
		}
		return super.canExecute();
	}

}