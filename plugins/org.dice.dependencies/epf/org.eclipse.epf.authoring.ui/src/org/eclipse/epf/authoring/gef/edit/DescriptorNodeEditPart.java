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

import org.eclipse.draw2d.Label;
import org.eclipse.epf.authoring.gef.figures.Images;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.LinkedObject;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.RoleNode;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.WorkProductDescriptorNode;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.Outcome;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Image;


/**
 * EditPart for WorkBreakdown Elements in an Activity.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class DescriptorNodeEditPart extends NamedNodeEditPart {

	public DescriptorNodeEditPart(NamedNode model) {
		super(model);
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.NamedNodeEditPart#getImage()
	 */
	protected Image getImage() {
		Object model = getModel();
		if (model instanceof RoleNode) {
			if(((Node)model).getDiagram() instanceof ActivityDetailDiagram)
				return Images.ROLE_DESCRIPTOR_16;
			return Images.ROLE;
		} else if (model instanceof TaskNode) {
			if(((Node)model).getDiagram() instanceof ActivityDetailDiagram)
				return Images.TASK_DESCRIPTOR_16;
			return Images.TASK_DESCRIPTOR;
		} else if (model instanceof WorkProductDescriptorNode) {
			if(((Node)model).getDiagram() instanceof ActivityDetailDiagram)
				return Images.WORK_PRODUCT_DESCRIPTOR_16;
			WorkProductDescriptor wpd = (WorkProductDescriptor) ((LinkedObject) model)
					.getObject();
			WorkProduct wp = wpd.getWorkProduct();
			if (wp instanceof Artifact) {
				return Images.WORK_PRODUCT_DESCRIPTOR_16;
			} else if (wp instanceof Deliverable) {
				return Images.DELIVERABLE;
			} else if (wp instanceof Outcome) {
				return Images.OUTCOME;
			}
			return Images.WORK_PRODUCT_DESCRIPTOR_16;
		}
		return null;
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.NamedNodeEditPart#createDirectEditPolicy() *
	 */
	protected DirectEditManager createDirectEditManager() {
		Label l = (Label) getFigure();
		return new ValidatingDirectEditManager(this, TextCellEditor.class,
				new LabelTextCellEditorLocator(l), l) {

			protected String validate(String txt) {
				Object obj = getEditPart().getModel();
				if (obj instanceof WorkProductDescriptorNode) {
					Node node = (Node) obj;
					Object e = node.getObject();
					return TngUtil.checkWorkProductDescriptorPresentationName(
							e, txt, node.getDiagram().getSuppression());
				}
				if (obj instanceof TaskNode) {
					Node node = (Node) obj;
					Object e = node.getObject();
					return TngUtil.checkWorkBreakdownElementPresentationName(e,
							txt, node.getDiagram().getSuppression());
				}
				if (obj instanceof RoleNode) {
					Node node = (Node) obj;
					Object e = node.getObject();
					return TngUtil.checkRoleDescriptorPresentationName(e, txt, node.getDiagram().getSuppression());
				}

				return super.validate(txt);
			}
		};
	}

	protected void refreshSourceConnections() {
		super.refreshSourceConnections();
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.COMPONENT_ROLE, null);
	}
	protected DirectEditManager getDirectEditManager() {
		return null;
	}
}
