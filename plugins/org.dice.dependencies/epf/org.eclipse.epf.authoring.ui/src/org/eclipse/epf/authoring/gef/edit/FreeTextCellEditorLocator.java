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

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.epf.authoring.gef.figures.FreeTextFigure;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * Constraint for placing FreeText Cell Editor.
 * @author Shashidhar Kannoori
 *
 */
public class FreeTextCellEditorLocator implements CellEditorLocator {

	IFigure figure;
	/*
	 * Creates a new FreeTextCellEditorLocator 
	 * 
	 * @param label the Label
	 */
	public FreeTextCellEditorLocator(IFigure figure) {
		setFigure(figure);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gef.tools.CellEditorLocator#relocate(org.eclipse.jface.viewers.CellEditor)
	 */
	public void relocate(CellEditor ce) {
		// TODO Auto-generated method stub
		FreeTextFigure fig = (FreeTextFigure)figure;
		Text text = (Text) ce.getControl();
		String n = (String) ce.getValue();
		GC gc = new GC(text);
		Point size = gc.textExtent(n);
		gc.dispose();
		if (size.x != 0)
			size = text.computeSize(size.x, SWT.DEFAULT);
		text.setSize(size.x, size.y);
		Rectangle textBounds = new Rectangle(text.getClientArea().x, text
				.getClientArea().y, text.getClientArea().width, text
				.getClientArea().height);
		Rectangle figBounds = fig.getClientArea();
		fig.translateToAbsolute(figBounds);
		int delta = (figBounds.width - textBounds.width) / 2;
		int y = fig.getTextFlowPage().getLocation().y;
		text.setLocation(figBounds.x + delta, y);

	}
	
	public void setFigure(IFigure figure) {
		this.figure = figure;
	}
	/**
	 * @return Returns the figure.
	 */
	public IFigure getFigure() {
		return figure;
	}
	

}
