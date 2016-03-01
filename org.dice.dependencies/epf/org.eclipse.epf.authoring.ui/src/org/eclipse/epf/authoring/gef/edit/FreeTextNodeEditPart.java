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

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.epf.authoring.gef.commands.ChangePropertyValueCommand;
import org.eclipse.epf.authoring.gef.figures.FreeTextFigure;
import org.eclipse.epf.authoring.gef.util.TemplateConstants;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.util.GraphicalDataHelper;
import org.eclipse.epf.uma.Property;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.ui.PlatformUI;

/**
 * Provides support for direct editing, text font, color and size changes. 
 * @author Shashidhar Kannoori.
 */

public class FreeTextNodeEditPart extends NodeEditPart {
	private Font font;

	private Color color;

	private FontData fData;

	private NodeEditPart part;

	public FreeTextNodeEditPart(Node model) {
		super(model);
		part = this; 
		// TODO Auto-generated constructor stub
	}
	protected IFigure createFigure() {
		FreeTextFigure l = new FreeTextFigure();
		return l;

	}
	protected void createEditPolicies() {
		super.createEditPolicies();
		if (!((Node) getModel()).isReadOnly()) {
			installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
					createDirectEditPolicy());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.edit.BaseEditPart#createDirectEditPolicy()
	 */
	protected DirectEditPolicy createDirectEditPolicy() {
		// TODO Auto-generated method stub
		return new DirectEditPolicy() {
			protected Command getDirectEditCommand(DirectEditRequest request) {
				return new ChangePropertyValueCommand(part, (String) request
						.getCellEditor().getValue());
			}

			protected void showCurrentEditValue(DirectEditRequest request) {
				IFigure fig = getDirectEditFigure();
				if (fig instanceof FreeTextFigure) {
					((FreeTextFigure) fig).setText((String) request.getCellEditor()
							.getValue());
					fig.getUpdateManager().performUpdate();
				}
			}
		};
	}
	
	
	protected void handlePropertyChanged(Notification msg) {
		switch (msg.getFeatureID(Node.class)) {
		case ModelPackage.TYPED_NODE__TYPE:
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

	protected void refreshVisuals() {
		super.refreshVisuals();
		IFigure fig = getDirectEditFigure();
		if (fig instanceof FreeTextFigure) {
			FreeTextFigure l = (FreeTextFigure) fig;
			List alist = ((Node) getModel()).getGraphNode().getProperty();
			String fontName = TemplateConstants.DEFAULT_FONT_NAME;
			int fontStyle = SWT.NORMAL;
			int fontHeight = TemplateConstants.DEFAULT_FONT_SIZE;
			int fontRed = SWT.COLOR_RED;
			int fontBlue = SWT.COLOR_BLUE;
			int fontGreen = SWT.COLOR_GREEN;

			if (alist.size() > 0) {
				for (Iterator ior = alist.iterator(); ior.hasNext();) {
					Property prop = (Property) ior.next();
					if (prop.getKey().equals(
							GraphicalDataHelper.GRAPH_NODE_FREE_TEXT)) {
						l.setText(prop.getValue());
					} else if (prop.getKey().equals(
							TemplateConstants.PROPERTY_FONT_NAME)) {
						fontName = prop.getValue();
					} else if (prop.getKey().equals(
							TemplateConstants.PROPERTY_FONT_STYLE)) {
						fontStyle = new Integer(prop.getValue()).intValue();
					} else if (prop.getKey().equals(
							TemplateConstants.PROPERTY_FONT_HEIGHT)) {
						fontHeight = new Integer(prop.getValue()).intValue();
					} else if (prop.getKey().equals(
							TemplateConstants.PROPERTY_FONT_RED)) {
						fontRed = new Integer(prop.getValue()).intValue();
					} else if (prop.getKey().equals(
							TemplateConstants.PROPERTY_FONT_BLUE)) {
						fontBlue = new Integer(prop.getValue()).intValue();
					} else if (prop.getKey().equals(
							TemplateConstants.PROPERTY_FONT_GREEN)) {
						fontGreen = new Integer(prop.getValue()).intValue();
					}
				}
			} else {
				l.setText(((Node) getModel()).getGraphNode().getName());
			}
			if (font != null && !font.isDisposed()) {
				font.dispose();
			}
			if (color != null && !color.isDisposed()) {
				color.dispose();
			}
			fData = new FontData(fontName, fontHeight, fontStyle);
			font = new Font(PlatformUI.getWorkbench().getDisplay(), fData);
			color = new Color(null, fontRed, fontGreen, fontBlue);
			l.setFont(font);

			l.setForegroundColor(color);
		}
	}

	protected DirectEditManager createDirectEditManager() {
		FreeTextFigure l = (FreeTextFigure) getFigure();
		return new FreeTextDirectEditManager(this, TextCellEditor.class,
				new FreeTextCellEditorLocator(l), l) {
			protected String validate(String txt) {
				return super.validate(txt);
			}
		};
	}

	IFigure getDirectEditFigure() {
		return getFigure();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.authoring.gef.edit.NodeEditPart#deactivate()
	 */
	public void deactivate() {
		if (font != null && !font.isDisposed()) {
			font.dispose();
		}
		if (color != null && !color.isDisposed()) {
			color.dispose();
		}
		super.deactivate();
	}
	String getDirectEditText() {
		// TODO Auto-generated method stub
		//return super.getDirectEditText();
		IFigure fig = getDirectEditFigure();
		if (fig instanceof FreeTextFigure) {
			return ((FreeTextFigure) fig).getText();
		} 
		return "";  //$NON-NLS-1$
	}

}
