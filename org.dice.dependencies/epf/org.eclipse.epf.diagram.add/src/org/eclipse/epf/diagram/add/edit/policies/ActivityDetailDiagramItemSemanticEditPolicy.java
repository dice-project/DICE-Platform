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

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.commands.DuplicateEObjectsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.epf.diagram.add.edit.commands.RoleTaskCompositeCreateCommand;
import org.eclipse.epf.diagram.add.edit.commands.WorkProductCompositeCreateCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.epf.diagram.add.providers.DiagramElementTypes;

import org.eclipse.epf.diagram.model.ModelPackage;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class ActivityDetailDiagramItemSemanticEditPolicy extends
		DiagramBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (DiagramElementTypes.RoleTaskComposite_1001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(ModelPackage.eINSTANCE
						.getNodeContainer_Nodes());
			}
			return getMSLWrapper(new RoleTaskCompositeCreateCommand(req));
		}
		if (DiagramElementTypes.WorkProductComposite_1002 == req
				.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(ModelPackage.eINSTANCE
						.getNodeContainer_Nodes());
			}
			return getMSLWrapper(new WorkProductCompositeCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @modified
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		return null;
		//		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
		//				.getEditingDomain();
		//		return getMSLWrapper(new DuplicateAnythingCommand(editingDomain, req));
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
