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
package org.eclipse.epf.authoring.gef.edit;

import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.WorkProductNode;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

/**
 * Factory for Work Product Dependency Diagram. Support creation 
 * of child editpart in WorkPRoduct dependency diagram. 
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class WPDiagramEditPartFactory implements EditPartFactory {

	public WPDiagramEditPartFactory() {
		super();
	}

	/**
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(EditPart, Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof Diagram) {
			return new DiagramEditPart((Diagram) model);
		} else if (model instanceof WorkProductNode) {
			return new WorkProductNodeEditPart((NamedNode) model);
		} else if (model instanceof Link) {
			return new LinkEditPart((Link) model);
		}
		// typed node creation
		if (model instanceof TypedNode) {
			TypedNode node = (TypedNode) model;
			switch (node.getType()) {
			case TypedNode.START:
				return new StartNodeEditPart(node);
			case TypedNode.END:
				return new EndNodeEditPart(node);
			case TypedNode.DECISION:
				return new DecisionNodeEditPart(node);
			case TypedNode.SYNCH_BAR:
				return new SynchBarNodeEditPart(node);
			case TypedNode.FREE_TEXT:
				return new FreeTextNodeEditPart(node);
			}
		}
		return null;
	}

}
