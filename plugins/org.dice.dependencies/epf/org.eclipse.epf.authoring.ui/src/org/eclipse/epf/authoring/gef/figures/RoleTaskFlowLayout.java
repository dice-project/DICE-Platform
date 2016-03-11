/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.authoring.gef.figures;

import java.util.Iterator;

import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * @author skannoor
 * @deprecated
 */
public class RoleTaskFlowLayout extends FlowLayout {

	boolean adjust = false;
	WorkingData data;
	/**
	 * 
	 */
	public RoleTaskFlowLayout() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param isHorizontal
	 */
	public RoleTaskFlowLayout(boolean isHorizontal) {
		super(isHorizontal);
		// TODO Auto-generated constructor stub
	}
	
	public void layout(IFigure parent) {
		// TODO Auto-generated method stub
		if(adjust){
			adjustLayout(parent);
		}else
			super.layout(parent);
	}
	class WorkingData {
		int rowHeight, rowWidth, rowCount, rowX, rowY, maxWidth;
		Rectangle bounds[], area;
		IFigure row[];
	}
	/**
	 * Initializes the state of row data, which is internal to the layout process. 
	 */
	protected void initRow() {
		data.rowX = 0;
		data.rowHeight = 0;
		data.rowWidth = 0;
		data.rowCount = 0;
	}

	/**
	 * Initializes state data for laying out children, based on the Figure given as input.
	 *
	 * @param parent the parent figure
	 * @since 2.0 
	 */
	protected void initVariables(IFigure parent) {
		data.row = new IFigure[parent.getChildren().size()];
		data.bounds = new Rectangle[data.row.length];
		data.maxWidth = data.area.width;
	}
	private void adjustLayout(IFigure parent) {
		// TODO Auto-generated method stub
		data = new WorkingData();
		Rectangle relativeArea = parent.getClientArea();
		data.area = transposer.t(relativeArea);

		Iterator iterator = parent.getChildren().iterator();
		int dx;

		//Calculate the hints to be passed to children
		int wHint = -1;
		int hHint = -1;
		if (isHorizontal())
			wHint = parent.getClientArea().width;
		else
			hHint = parent.getClientArea().height;

		initVariables(parent);
		initRow();
		int i = 0; 
		while (iterator.hasNext()) {
			IFigure f = (IFigure)iterator.next();
			Dimension pref = transposer.t(getChildSize(f, wHint, hHint));
			Rectangle r = new Rectangle(0, 0, pref.width, pref.height);

			if (data.rowCount > 0) {
				if (data.rowWidth + pref.width > data.maxWidth)
					layoutRow(parent);
			}
			r.x = data.rowX;
			r.y = data.rowY;
			dx = r.width + getMinorSpacing();
			data.rowX += dx;
			data.rowWidth += dx;
			data.rowHeight = Math.max(data.rowHeight, r.height);
			data.row [data.rowCount] = f;
			data.bounds[data.rowCount] = r;
			data.rowCount++;
			i++;
		}
		if (data.rowCount != 0)
			layoutRow(parent);
		data = null;
	}

	public void setAdjust(boolean adjust) {
		this.adjust = adjust;
	}	

}
