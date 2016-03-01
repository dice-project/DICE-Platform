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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.epf.authoring.gef.figures.Images;
import org.eclipse.epf.authoring.gef.figures.SelectableLabel;
import org.eclipse.epf.authoring.gef.figures.WPCompartmentFigure;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.WorkProductNode;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

/**
 * 
 * @author Shashidhar Kannoori
 * @author Phong Nguyen Le
 * @since 1.0
 * @deprecated 
 * @see WorkProductNodeEditPart
 */
public class WPNodeEditPart extends NamedNodeEditPart {

	int type = 1;

	public WPNodeEditPart(NamedNode model) {
		super(model);
	}

	protected IFigure createFigure() {
		if (getModel() instanceof WorkProductNode) {
			type = ((WorkProductNode) getModel()).getType();
		}
		Label l = new SelectableLabel();
		l.setLabelAlignment(PositionConstants.LEFT);
		l.setTextPlacement(PositionConstants.SOUTH);
		l.setBackgroundColor(ColorConstants.white);
		Font font = new Font(null, "Arial", 9, SWT.NORMAL); //$NON-NLS-1$
		l.setFont(font);
		if (type == 1)
			l.setIcon(Images.ARTIFACT);
		else if (type == 2)
			l.setIcon(Images.DELIVERABLE);
		else if (type == 3)
			l.setIcon(Images.OUTCOME);
		else
			l.setIcon(Images.WORK_PRODUCT_DESCRIPTOR);
		l.setIconTextGap(5);

		WPCompartmentFigure wpfigure = new WPCompartmentFigure(l, type);
		return wpfigure;
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.BaseEditPart#createDirectEditManager()
	 */
	protected DirectEditManager createDirectEditManager() {
		Label l = (Label) (((WPCompartmentFigure) getFigure()).getLabel());
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

	IFigure getDirectEditFigure() {
		return (((WPCompartmentFigure) getFigure()).getLabel());
	}

}
