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
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.WorkProductNode;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Image;

/**
 * Provides support for {@link WorkProductDescriptor}.
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class WorkProductNodeEditPart extends NamedNodeEditPart {

	public WorkProductNodeEditPart(NamedNode model) {
		super(model);
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.NamedNodeEditPart#getImage()
	 */
	protected Image getImage() {
		Object model = getModel();
		if (model instanceof WorkProductNode) {
			WorkProductNode node = (WorkProductNode) model;
			switch (node.getType()) {
			case WorkProductNode.ARTIFACT:
				return Images.WORK_PRODUCT_DESCRIPTOR_16;
			case WorkProductNode.DELIVERABLE:
				return Images.DELIVERABLE;
			case WorkProductNode.OUTCOME:
				return Images.OUTCOME;
			}
			return Images.WORK_PRODUCT_DESCRIPTOR_16;
		}
		return null;
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.NamedNodeEditPart#createDirectEditPolicy()
	 */
	protected DirectEditManager createDirectEditManager() {
		Label l = (Label) getFigure();
		return new ValidatingDirectEditManager(this, TextCellEditor.class,
				new LabelTextCellEditorLocator(l), l) {
			protected String validate(String txt) {
				Object obj = getEditPart().getModel();
				if (obj instanceof WorkProductNode) {
					Node node = (Node) obj;
					Object e = node.getObject();
					Suppression suppression = node.getDiagram().getSuppression();
					return TngUtil.checkWorkProductDescriptorPresentationName(
							e, txt, suppression);
				}
				return super.validate(txt);
			}
		};
	}

}
