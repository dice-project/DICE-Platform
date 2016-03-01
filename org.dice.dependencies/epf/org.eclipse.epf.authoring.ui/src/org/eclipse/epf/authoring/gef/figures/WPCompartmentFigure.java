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
package org.eclipse.epf.authoring.gef.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.epf.authoring.gef.edit.RaisedMarginBorder;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * @author Shashidhar Kannoori
 * @since 1.0
 * @deprecated
 */
public class WPCompartmentFigure extends Figure {

	public final static Color wpColor = new Color(Display.getCurrent(), 255,
			255, 156);

	public final static Color artifactColor = new Color(Display.getCurrent(),
			255, 255, 156);

	public final static Color deliverableColor = new Color(
			Display.getCurrent(), 206, 255, 216);

	public final static Color outcomeColor = new Color(Display.getCurrent(),
			206, 255, 100);

	public final static String newWPName = AuthoringUIResources.WPCompartmentFigure_0; 

	public Label label;

	WPFigure figure = null;

	/**
	 * WorkProduct Compartment - With background filled depending on Type of the
	 * WorkProduct.
	 */
	public WPCompartmentFigure(Label name, int type) {
		label = name;
		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager(layout);
		setOpaque(true);
		setBorder(new RaisedMarginBorder());
		if (type == 1)
			setBackgroundColor(artifactColor);
		else if (type == 2)
			setBackgroundColor(deliverableColor);
		else if (type == 3)
			setBackgroundColor(outcomeColor);
		else
			setBackgroundColor(wpColor);
		label.setText(newWPName);
		add(label);
	}

	public Label getLabel() {
		return label;
	}

	public void setLabelName(String name) {
		label.setText(name);
	}

	public void setLabel(Label label) {
		this.label = label;
	}

}
