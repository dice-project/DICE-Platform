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

import java.util.List;

import org.eclipse.epf.authoring.gef.edit.NodeEditPart;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.uma.GraphNode;
import org.eclipse.epf.uma.Property;
import org.eclipse.gef.commands.Command;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ChangePropertyValueCommand extends Command {

	private Node element;

	private NodeEditPart part;

	private String newName, oldName;

	private static final String LABEL = AuthoringUIResources.gef_changePropertyValueCommand_label; 

	public ChangePropertyValueCommand(NodeEditPart elem, String newName) {
		super(LABEL);
		part = elem;
		element = (Node) elem.getModel();
		GraphNode graphNode = element.getGraphNode();
		List list = graphNode.getProperty();
		oldName = ((Property) list.get(0)).getValue();
		this.newName = newName.trim();
	}

	public boolean canExecute() {
		return element != null;
	}

	public void execute() {
		((Property) element.getGraphNode().getProperty().get(0))
				.setValue(newName);
		part.refresh();
	}

	public void undo() {
		((Property) element.getGraphNode().getProperty().get(0))
				.setValue(oldName);
		part.refresh();
	}

}