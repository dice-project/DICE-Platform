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
package org.eclipse.epf.diagram.ad.custom.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.util.DiagramCoreUtil;

/**
 * Customized figure for the BreakdownElement figures to show overlapped portion. 
 * @author Shashidhar Kannoori
 */
public class BreakdownElementNodeFigure extends RectangleFigure {

	/**
	 * 
	 */
	public BreakdownElementNodeFigure() {
		this.setOutline(false);
		this.setOpaque(false);
		createContents();
	}

	/**
	 * @modified
	 */
	private void createContents() {
		org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig_0 = new org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel();
		fig_0.setText(DiagramCoreResources.ActivityDiagram_New_Iteration);

		DiagramCoreUtil.setLabelProperties(fig_0);

		setFigureNodeNameFigure(fig_0);

		Object layData0 = null;

		this.add(fig_0, layData0);
	}

	/**
	 * @generated
	 */
	private org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fNodeNameFigure;

	/**
	 * @generated
	 */
	public org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel getFigureNodeNameFigure() {
		return fNodeNameFigure;
	}

	/**
	 * @generated
	 */
	private void setFigureNodeNameFigure(
			org.eclipse.gmf.runtime.draw2d.ui.figures.WrapLabel fig) {
		fNodeNameFigure = fig;
	}

	/**
	 * @generated
	 */
	private boolean myUseLocalCoordinates = false;

	/**
	 * @generated
	 */
	protected boolean useLocalCoordinates() {
		return myUseLocalCoordinates;
	}

	/**
	 * @generated
	 */
	protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
		myUseLocalCoordinates = useLocalCoordinates;
	}

	@Override
	public void paintFigure(Graphics graphics) {
		if(isOpaque())
			super.paintFigure(graphics);
		return;
	}	
}
