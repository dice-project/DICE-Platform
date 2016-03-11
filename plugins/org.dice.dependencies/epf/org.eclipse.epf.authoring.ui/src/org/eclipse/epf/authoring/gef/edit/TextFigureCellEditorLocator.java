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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.epf.authoring.gef.figures.TextFigure;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;

/**
 * Constraint for TextFlow placing.
 * @author James Thario
 * @since 1.0
 */
public class TextFigureCellEditorLocator implements CellEditorLocator {

	private TextFigure figure;


	/**
	 * Creates a new LabelTextCellEditorLocator for the given text figure
	 * @param tf
	 */
	public TextFigureCellEditorLocator(TextFigure tf) {
		setFigure(tf);
	}

	/**
	 * @see CellEditorLocator#relocate(org.eclipse.jface.viewers.CellEditor)
	 */
	public void relocate(CellEditor celleditor) {
		TextFigureCellEditorLocator.doLocate(celleditor, figure);
	}

	public static void doLocate(CellEditor ce, TextFigure fig) {
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

	public TextFigure getFigure() {
		return figure;
	}

	public void setFigure(TextFigure figure) {
		this.figure = figure;
	}

}
