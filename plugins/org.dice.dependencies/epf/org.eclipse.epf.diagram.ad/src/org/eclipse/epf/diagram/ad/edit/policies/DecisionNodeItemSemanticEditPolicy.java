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
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.ad.edit.commands.ControlFlowCreateCommand;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.bridge.NodeAdapter;
import org.eclipse.epf.diagram.core.util.DiagramCoreValidation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class DecisionNodeItemSemanticEditPolicy extends
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

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.diagram.ad.edit.policies.UMLBaseItemSemanticEditPolicy#getCreateIncomingComplete(org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest)
	 * @modified
	 */
	protected Command getCreateIncomingComplete(CreateRelationshipRequest req) {
		// allow maximum only one incoming connection to decision node from other activity node
		//
		if (req.getTarget() instanceof DecisionNode) {
			DecisionNode decision = (DecisionNode) req.getTarget();
			if(DiagramCoreValidation.hasVisibleSource(decision)) {
				return UnexecutableCommand.INSTANCE;
			}
		}
		return super.getCreateIncomingComplete(req);
	}
}
