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

import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.RoleNode;
import org.eclipse.epf.diagram.model.RoleTaskComposite;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.epf.diagram.model.WorkProductDescriptorNode;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

/**
 * Factory for ActivityDetailDiagram's EditPart. Creates an EditPart for
 * Activity Detail Diagram's children.  
 * 
 * @author Jinhua Xi
 * @since 1.0
 * 
 */
public class ActivityDetailDiagramEditPartFactory implements EditPartFactory {

	/**
	 * Creates an EditPart for a child with in the context.
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof ActivityDetailDiagram) {
			return new ActivityDetailDiagramEditPart(
					(ActivityDetailDiagram) model);
		} else if (model instanceof RoleTaskComposite) {
			return new RoleTaskCompositeEditPart((RoleTaskComposite) model);
		} else if (model instanceof WorkProductComposite) {
			return new WorkProductCompositeEditPart(
					(WorkProductComposite) model);
		} else if (model instanceof RoleNode || model instanceof TaskNode
				|| model instanceof WorkProductDescriptorNode) {
			return new DescriptorNodeEditPart((NamedNode) model);
		} else if (model instanceof Link) {
			return new LinkEditPart((Link) model);
		}
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
