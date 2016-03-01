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
import org.eclipse.epf.authoring.gef.figures.ActivityFigure;
import org.eclipse.epf.authoring.gef.figures.Colors;
import org.eclipse.epf.authoring.gef.figures.Images;
import org.eclipse.epf.authoring.gef.figures.TextFigure;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.epf.diagram.model.WorkProductDescriptorNode;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

/**
 * Common editpart for different {@link Descriptor}
 * Provides support for word-wrap of editpart's object name. 
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class WorkBreakdownElementNodeEditPart extends NamedNodeEditPart {

	public WorkBreakdownElementNodeEditPart(NamedNode model) {
		super(model);
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.NamedNodeEditPart#getImage()
	 */
	protected Image getImage() {
		Image img = null;
		Node node = (Node) getModel();
		Object obj = node.getObject();
		if (obj instanceof Phase) {
			img = Images.PHASE;
		}
		else if (obj instanceof Iteration) {
			img = Images.ITERATION;
		}
		else if (obj instanceof Activity) {
			img = Images.ACTIVITY;
		}
		else if (obj instanceof TaskDescriptor) {
			img = Images.TASK_DESCRIPTOR;
		}
		else if (obj instanceof Milestone) {
			img = Images.MILESTONE;
		}
		return img;
	}

	protected IFigure createFigure() {
		ActivityFigure figure = new ActivityFigure();
		Image image = getImage();
		if (image != null) {
			figure.add(image);
		}
		return figure;
	}

	String getDirectEditText() {
		TextFigure note = (TextFigure) getFigure();
		return note.getText();
	}

	protected void refreshVisuals() {
		super.refreshVisuals();
		IFigure fig = getDirectEditFigure();
		if (fig instanceof ActivityFigure) {
			ActivityFigure l = (ActivityFigure) fig;
			fig.setForegroundColor(getForegroundColor());
			String text;
			// Below check is required for Extended Activity presentation name
			// should be used from base.
			if (getModel() instanceof WorkBreakdownElementNode) {
				WorkBreakdownElementNode node = (WorkBreakdownElementNode) getModel();
				Object wbelement = node.getObject();
				if (wbelement instanceof Activity
						&& ProcessUtil
								.isExtendingOrLocallyContributing((BreakdownElement) wbelement)) {
					text = ProcessUtil
							.getPresentationName((BreakdownElement) wbelement);
				}
				else {
					text = ((NamedNode) getModel()).getName();
				}
			}
			else {
				text = ((NamedNode) getModel()).getName();
			}
			try {
				if (getParent() instanceof ActivityDetailDiagramEditPart
						|| getParent().getParent() instanceof ActivityDetailDiagramEditPart) {
					int w = WRAP_WIDTH;
					if (this.getModel() instanceof WorkProductDescriptorNode)
						w = WORK_PRODUCT_WRAP_WIDTH;
					text = wrap(text, w);
				}
			}
			catch (NullPointerException npe) {
			}
			l.setText(text);
		}
	}

	private Color getForegroundColor() {
		NamedNode node = (NamedNode) getModel();
		if (node.isSuppressed()) {
			return Colors.SUPRESSED_ELEMENT_LABEL;
		}
		if (node.isReadOnly()) {
			return Colors.INHERITED_ELEMENT_LABEL;
		}
		else {
			return ColorConstants.black;
		}
	}
	/**
	 * @see org.eclipse.epf.authoring.gef.edit.BaseEditPart#createDirectEditManager()
	 */
	protected DirectEditManager createDirectEditManager() {
		return new TextFigureDirectEditManager((GraphicalEditPart) this,
				TextCellEditor.class, new TextFigureCellEditorLocator(
						(TextFigure) getFigure()), (TextFigure) getFigure()) {
			protected String validate(String txt) {
				Object obj = getEditPart().getModel();
				if (obj instanceof WorkBreakdownElementNode) {
					WorkBreakdownElementNode node = (WorkBreakdownElementNode) obj;
					Object e = node.getObject();
					if (e instanceof Activity) {
						return TngUtil.checkWBSActivityPresentationName(e, txt, node.getDiagram().getSuppression());
					}
					else {
						return TngUtil
								.checkWorkBreakdownElementPresentationName(e,
										txt, node.getDiagram().getSuppression());
					}
				}

				return super.validate(txt);
			}
		};
	}

}
