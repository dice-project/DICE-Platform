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

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.epf.diagram.ad.edit.commands.ActivityFinalNodeCreateCommand;
import org.eclipse.epf.diagram.ad.edit.commands.ActivityParameterNodeCreateCommand;
import org.eclipse.epf.diagram.ad.edit.commands.ActivityPartitionCreateCommand;
import org.eclipse.epf.diagram.ad.edit.commands.DecisionNodeCreateCommand;
import org.eclipse.epf.diagram.ad.edit.commands.ForkNodeCreateCommand;
import org.eclipse.epf.diagram.ad.edit.commands.InitialNodeCreateCommand;
import org.eclipse.epf.diagram.ad.edit.commands.JoinNodeCreateCommand;
import org.eclipse.epf.diagram.ad.edit.commands.MergeNodeCreateCommand;
import org.eclipse.epf.diagram.ad.edit.commands.StructuredActivityNodeCreateCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.epf.diagram.ad.providers.UMLElementTypes;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class ActivityItemSemanticEditPolicy extends
		UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (UMLElementTypes.ActivityFinalNode_1001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Node());
			}
			return getMSLWrapper(new ActivityFinalNodeCreateCommand(req));
		}
		if (UMLElementTypes.MergeNode_1002 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Node());
			}
			return getMSLWrapper(new MergeNodeCreateCommand(req));
		}
		if (UMLElementTypes.ForkNode_1003 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Node());
			}
			return getMSLWrapper(new ForkNodeCreateCommand(req));
		}
		if (UMLElementTypes.InitialNode_1004 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Node());
			}
			return getMSLWrapper(new InitialNodeCreateCommand(req));
		}
		if (UMLElementTypes.DecisionNode_1005 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Node());
			}
			return getMSLWrapper(new DecisionNodeCreateCommand(req));
		}
		if (UMLElementTypes.JoinNode_1006 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Node());
			}
			return getMSLWrapper(new JoinNodeCreateCommand(req));
		}
		if (UMLElementTypes.StructuredActivityNode_1007 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Node());
			}
			return getMSLWrapper(new StructuredActivityNodeCreateCommand(req));
		}
		if (UMLElementTypes.ActivityPartition_1008 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Group());
			}
			return getMSLWrapper(new ActivityPartitionCreateCommand(req));
		}
		if (UMLElementTypes.ActivityParameterNode_1009 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Node());
			}
			return getMSLWrapper(new ActivityParameterNodeCreateCommand(req));
		}
		if (UMLElementTypes.StructuredActivityNode_1010 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Node());
			}
			return getMSLWrapper(new StructuredActivityNodeCreateCommand(req));
		}
		if (UMLElementTypes.StructuredActivityNode_1011 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Node());
			}
			return getMSLWrapper(new StructuredActivityNodeCreateCommand(req));
		}
		if (UMLElementTypes.ActivityParameterNode_1012 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Node());
			}
			return getMSLWrapper(new ActivityParameterNodeCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @modified
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		//		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
		//				.getEditingDomain();
		//		return getMSLWrapper(new DuplicateAnythingCommand(editingDomain, req));
		return null;
	}

	/**
	 * @generated
	 */
	private static class DuplicateAnythingCommand extends
			DuplicateEObjectsCommand {

		/**
		 * @generated
		 */
		public DuplicateAnythingCommand(
				TransactionalEditingDomain editingDomain,
				DuplicateElementsRequest req) {
			super(editingDomain, req.getLabel(), req
					.getElementsToBeDuplicated(), req
					.getAllDuplicatedElementsMap());
		}
	}
}
