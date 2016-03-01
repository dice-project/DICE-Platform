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

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.ad.edit.commands.ControlFlowCreateCommand;
import org.eclipse.epf.diagram.ad.edit.commands.ControlFlowReorientCommand;
import org.eclipse.epf.diagram.ad.edit.parts.ControlFlowEditPart;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.epf.diagram.ad.providers.UMLElementTypes;

import org.eclipse.gef.commands.UnexecutableCommand;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateRelationshipCommand;

import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.InitialNode;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class InitialNodeItemSemanticEditPolicy extends
		UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand cc = getDestroyEdgesCommand(req
				.isConfirmationRequired());
		View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			req.setElementToDestroy(view);
		}
		cc.add(getMSLWrapper(new DestroyElementCommand(req)));
		return cc.unwrap();
	}

	/**
	 * @modified
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
//		if (UMLElementTypes.ControlFlow_3001 == req.getElementType()) {
//			return req.getTarget() == null ? getCreateStartOutgoingControlFlow_3001Command(req)
//					: getCreateCompleteIncomingControlFlow_3001Command(req);
//		}
		return super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getCreateStartOutgoingControlFlow_3001Command(
			CreateRelationshipRequest req) {
		EObject sourceEObject = req.getSource();
		if (false == sourceEObject instanceof ActivityNode) {
			return UnexecutableCommand.INSTANCE;
		}
		ActivityNode source = (ActivityNode) sourceEObject;
		StructuredActivityNode container = (StructuredActivityNode) getRelationshipContainer(
				source, UMLPackage.eINSTANCE.getStructuredActivityNode(), req
						.getElementType());
		if (container == null) {
			return UnexecutableCommand.INSTANCE;
		}
		if (!UMLBaseItemSemanticEditPolicy.LinkConstraints
				.canCreateControlFlow_3001(container, source, null)) {
			return UnexecutableCommand.INSTANCE;
		}
		return new Command() {
		};
	}

	/**
	 * @generated
	 */
	protected Command getCreateCompleteIncomingControlFlow_3001Command(
			CreateRelationshipRequest req) {
		EObject sourceEObject = req.getSource();
		EObject targetEObject = req.getTarget();
		if (false == sourceEObject instanceof ActivityNode
				|| false == targetEObject instanceof ActivityNode) {
			return UnexecutableCommand.INSTANCE;
		}
		ActivityNode source = (ActivityNode) sourceEObject;
		ActivityNode target = (ActivityNode) targetEObject;
		StructuredActivityNode container = (StructuredActivityNode) getRelationshipContainer(
				source, UMLPackage.eINSTANCE.getStructuredActivityNode(), req
						.getElementType());
		if (container == null) {
			return UnexecutableCommand.INSTANCE;
		}
		if (!UMLBaseItemSemanticEditPolicy.LinkConstraints
				.canCreateControlFlow_3001(container, source, target)) {
			return UnexecutableCommand.INSTANCE;
		}
		if (req.getContainmentFeature() == null) {
			req.setContainmentFeature(UMLPackage.eINSTANCE
					.getStructuredActivityNode_Edge());
		}
		return getMSLWrapper(new ControlFlowCreateCommand(req, container,
				source, target));
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @modified
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
//		switch (getVisualID(req)) {
//		case ControlFlowEditPart.VISUAL_ID:
//			return getMSLWrapper(new ControlFlowReorientCommand(req));
//		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * @see org.eclipse.epf.gmf.diagram.edit.policies.UMLBaseItemSemanticEditPolicy#getCreateIncomingComplete(org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest)
	 * @modified
	 */
	protected Command getCreateIncomingComplete(CreateRelationshipRequest req) {
		if (req.getTarget() instanceof InitialNode) {
			return UnexecutableCommand.INSTANCE;
		}
		return super.getCreateIncomingComplete(req);
	}
}
