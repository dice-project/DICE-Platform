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
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.epf.authoring.gef.commands.ChangeNameCommand;
import org.eclipse.epf.authoring.gef.figures.Colors;
import org.eclipse.epf.authoring.gef.figures.SelectableLabel;
import org.eclipse.epf.authoring.gef.figures.TextFigure;
import org.eclipse.epf.authoring.gef.util.TemplateConstants;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.TaskNode;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.epf.diagram.model.WorkProductDescriptorNode;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.swt.graphics.Image;

import com.ibm.icu.text.BreakIterator;

/**
 * Diagram children should extend this NamedNodeEditPart in order to support name for figure.
 * NamedNodeEditPart provides instructure to handle the name wrapping and color. Support direct 
 * editing feedback.   
 *  
 * @author Phong Nguyen Le
 * @since 1.0
 */
public abstract class NamedNodeEditPart extends NodeEditPart {

	public NamedNodeEditPart(NamedNode model) {
		super(model);
	}

	protected IFigure createFigure() {
		Label l = new SelectableLabel();
		l.setLabelAlignment(PositionConstants.LEFT);
		if (getImage() != null) {
			l.setIcon(getImage());
		}
		l.setTextPlacement(PositionConstants.SOUTH);
		l.setFont(TemplateConstants.DEFAULT_FONT);
		setLabelForegroundColor(l);
		return l;
	}

	private void setLabelForegroundColor(Label l) {
		NamedNode node = (NamedNode) getModel();
		// Object e = node.getObject();
		// boolean suppressed = isSuppressed(e);
		boolean suppressed = node.isSuppressed();
		if (node.isReadOnly()) {
			if (!suppressed) {
				l.setForegroundColor(Colors.INHERITED_ELEMENT_LABEL);
			}
		} else {
			if (!suppressed) {
				l.setForegroundColor(ColorConstants.black);
			}
		}
		if (suppressed) {
			l.setForegroundColor(Colors.SUPRESSED_ELEMENT_LABEL);
		}
	}

	protected Image getImage() {
		return null;
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		if (!((Node) getModel()).isReadOnly()) {
			installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
					createDirectEditPolicy());
		}
	}

	protected DirectEditPolicy createDirectEditPolicy() {
		return new DirectEditPolicy() {
			protected Command getDirectEditCommand(DirectEditRequest request) {
				return new ChangeNameCommand((NamedNode) getModel(),
						(String) request.getCellEditor().getValue());
			}

			protected void showCurrentEditValue(DirectEditRequest request) {
				IFigure fig = getDirectEditFigure();
				String s = (String) request.getCellEditor().getValue();
				if (fig instanceof Label) {
					((Label) fig).setText(s);
				}
				else if (fig instanceof TextFigure) {
					((TextFigure) fig).setText(s);
				}
				fig.getUpdateManager().performUpdate();
			}
		};
	}

	protected void handlePropertyChanged(Notification msg) {
		switch (msg.getFeatureID(NamedNode.class)) {
		case ModelPackage.NAMED_NODE__NAME:
			refreshVisuals();
			return;
		}
		super.handlePropertyChanged(msg);
	}

	public void performRequest(Request request) {
		if (request.getType() == RequestConstants.REQ_DIRECT_EDIT
				&& !((Node) getModel()).isReadOnly()) {
			performDirectEdit();
		} else {
			super.performRequest(request);
		}
	}

	protected static final int WRAP_WIDTH = 12;

	protected static final int WORK_PRODUCT_WRAP_WIDTH = 3;

	protected void refreshVisuals() {
		super.refreshVisuals();
		IFigure fig = getDirectEditFigure();
		if (fig instanceof Label) {
			Label l = (Label) fig;
			setLabelForegroundColor(l);
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
				} else {
					text = ((NamedNode) getModel()).getName();
				}
			} else {
				text = ((NamedNode) getModel()).getName();
			}
			try {
				if (getParent() instanceof ActivityDetailDiagramEditPart
						|| getParent().getParent() instanceof ActivityDetailDiagramEditPart) {
					int w = WRAP_WIDTH;
					if (this.getModel() instanceof WorkProductDescriptorNode)
						w = WORK_PRODUCT_WRAP_WIDTH;
					
					if(this.getModel() instanceof TaskNode && 
							GraphicalDataHelper.isAutoLayout(
							(ActivityDetailDiagram)getParent().getParent().getModel())){
						// Used 16 as min length for taskname, else add spaces to it.
						// TODO: find better solution for this.
						if(text.length() < 16){
							int gap = 16 - text.length();
							for(int i=0; i<gap/2; i++){
								text+=" "; //$NON-NLS-1$
							}
							for(int i=0; i<gap/2; i++){
								text = " "+text; //$NON-NLS-1$
							}
						}
					}
					text = wrap(text, w);
				}
			} catch (NullPointerException npe) {
			}
			l.setText(text);
		}
	}

	protected String wrap(String s, int wrapWidth) {
		String wrapped = ""; //$NON-NLS-1$
		BreakIterator bi = BreakIterator.getLineInstance();
		String remaining = s;
		while (true) {
			if (remaining.length() <= wrapWidth) {
				wrapped += remaining;
				break;
			} else {
				bi.setText(remaining);
				int pos = bi.following(wrapWidth);
				if (pos == BreakIterator.DONE) {
					wrapped += remaining;
					break;
				} else {
					if (pos >= remaining.length()) {
						wrapped += remaining;
						break;
					} else {
						wrapped += remaining.substring(0, pos) + "\n"; //$NON-NLS-1$
						remaining = remaining.substring(pos);
					}
				}
			}
		}
		return wrapped;
	}

}