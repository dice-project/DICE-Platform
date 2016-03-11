//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.add.edit.policies;

import java.util.Iterator;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.add.edit.commands.LinkCreateCommand;
import org.eclipse.epf.diagram.add.edit.commands.LinkReorientCommand;
import org.eclipse.epf.diagram.add.edit.commands.WorkProductDescriptorNodeCreateCommand;
import org.eclipse.epf.diagram.add.edit.parts.LinkEditPart;
import org.eclipse.epf.diagram.add.edit.parts.WorkProductCompositeWorkProductCompartmentEditPart;
import org.eclipse.epf.diagram.add.edit.parts.WorkProductDescriptorNodeEditPart;
import org.eclipse.epf.diagram.add.part.DiagramVisualIDRegistry;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.epf.diagram.add.providers.DiagramElementTypes;

import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;

import org.eclipse.gef.commands.UnexecutableCommand;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateRelationshipCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @generated
 */
public class WorkProductCompositeItemSemanticEditPolicy extends
		DiagramBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (DiagramElementTypes.WorkProductDescriptorNode_2003 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(ModelPackage.eINSTANCE
						.getNodeContainer_Nodes());
			}
			return getMSLWrapper(new WorkProductDescriptorNodeCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

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
			org.eclipse.gmf.runtime.notation.Node node = (org.eclipse.gmf.runtime.notation.Node) it
					.next();
			switch (DiagramVisualIDRegistry.getVisualID(node)) {
			case WorkProductDescriptorNodeEditPart.VISUAL_ID:
				cmd.add(getDestroyElementCommand(node, confirm));
				break;
			case WorkProductCompositeWorkProductCompartmentEditPart.VISUAL_ID:
				for (Iterator cit = node.getChildren().iterator(); cit
						.hasNext();) {
					org.eclipse.gmf.runtime.notation.Node cnode = (org.eclipse.gmf.runtime.notation.Node) cit
							.next();
					switch (DiagramVisualIDRegistry.getVisualID(cnode)) {
					}
				}
				break;
			}
		}
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (DiagramElementTypes.Link_3001 == req.getElementType()) {
			return req.getTarget() == null ? getCreateStartOutgoingLink_3001Command(req)
					: getCreateCompleteIncomingLink_3001Command(req);
		}
		return super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getCreateStartOutgoingLink_3001Command(
			CreateRelationshipRequest req) {
		EObject sourceEObject = req.getSource();
		if (false == sourceEObject instanceof Node) {
			return UnexecutableCommand.INSTANCE;
		}
		Node source = (Node) sourceEObject;
		Node container = (Node) getRelationshipContainer(source,
				ModelPackage.eINSTANCE.getNode(), req.getElementType());
		if (container == null) {
			return UnexecutableCommand.INSTANCE;
		}
		if (!DiagramBaseItemSemanticEditPolicy.LinkConstraints
				.canCreateLink_3001(container, source, null)) {
			return UnexecutableCommand.INSTANCE;
		}
		return new Command() {
		};
	}

	/**
	 * @generated
	 */
	protected Command getCreateCompleteIncomingLink_3001Command(
			CreateRelationshipRequest req) {
		EObject sourceEObject = req.getSource();
		EObject targetEObject = req.getTarget();
		if (false == sourceEObject instanceof Node
				|| false == targetEObject instanceof Node) {
			return UnexecutableCommand.INSTANCE;
		}
		Node source = (Node) sourceEObject;
		Node target = (Node) targetEObject;
		Node container = (Node) getRelationshipContainer(source,
				ModelPackage.eINSTANCE.getNode(), req.getElementType());
		if (container == null) {
			return UnexecutableCommand.INSTANCE;
		}
		if (!DiagramBaseItemSemanticEditPolicy.LinkConstraints
				.canCreateLink_3001(container, source, target)) {
			return UnexecutableCommand.INSTANCE;
		}
		if (req.getContainmentFeature() == null) {
			req.setContainmentFeature(ModelPackage.eINSTANCE
					.getNode_OutgoingConnections());
		}
		return getMSLWrapper(new LinkCreateCommand(req, container, source,
				target));
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * 
	 */
	@Override
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
//		switch (getVisualID(req)) {
//		case LinkEditPart.VISUAL_ID:
//			return getMSLWrapper(new LinkReorientCommand(req));
//		}
//		return super.getReorientRelationshipCommand(req);
		return UnexecutableCommand.INSTANCE;
	}
}
