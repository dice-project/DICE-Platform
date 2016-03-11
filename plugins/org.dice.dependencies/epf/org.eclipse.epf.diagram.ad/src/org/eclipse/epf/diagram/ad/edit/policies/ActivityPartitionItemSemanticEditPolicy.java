/*
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *
 */
package org.eclipse.epf.diagram.ad.edit.policies;

import java.util.Iterator;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartition2EditPart;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityPartitionPartitionCampartmentEditPart;
import org.eclipse.epf.diagram.ad.part.UMLVisualIDRegistry;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class ActivityPartitionItemSemanticEditPolicy extends
		UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand cc = getDestroyEdgesCommand(req
				.isConfirmationRequired());
		addDestroyChildNodesCommand(cc, req.isConfirmationRequired());
		View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			req.setElementToDestroy(view);
		}
		cc.add(getMSLWrapper(new DestroyElementCommand(req)));
		return cc.unwrap();
	}

	/**
	 * @generated
	 */
	protected void addDestroyChildNodesCommand(CompoundCommand cmd,
			boolean confirm) {
		View view = (View) getHost().getModel();
		for (Iterator it = view.getChildren().iterator(); it.hasNext();) {
			Node node = (Node) it.next();
			switch (UMLVisualIDRegistry.getVisualID(node)) {
			case ActivityPartitionPartitionCampartmentEditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (UMLVisualIDRegistry.getVisualID(cnode)) {
					case ActivityPartition2EditPart.VISUAL_ID:
						cmd.add(getDestroyElementCommand(cnode, confirm));
						break;
					}
				}
				break;
			}
		}
	}
}
