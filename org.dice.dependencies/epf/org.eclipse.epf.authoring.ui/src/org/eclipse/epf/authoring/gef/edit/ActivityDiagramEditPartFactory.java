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

import org.eclipse.epf.diagram.model.ActivityDiagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ActivityDiagramEditPartFactory implements EditPartFactory {

	/**
	 * Creates editpart for ActivityDiagram's children. 
	 * 
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 *      java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof ActivityDiagram) {
			return new ActivityDiagramEditPart((ActivityDiagram) model);
		} else if (model instanceof WorkBreakdownElementNode) {
			return new WorkBreakdownElementNodeEditPart((NamedNode) model);
		} else if (model instanceof Link) {
			return new LinkEditPart((Link) model);
		} else if (model instanceof TypedNode) {
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
