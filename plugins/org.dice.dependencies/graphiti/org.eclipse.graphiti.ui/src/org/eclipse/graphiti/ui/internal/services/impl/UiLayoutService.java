/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services.impl;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.datatypes.IRectangle;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILayoutService;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.services.IUiLayoutService;
import org.eclipse.graphiti.util.ILocationInfo;

/**
 *
 */
public class UiLayoutService implements IUiLayoutService {

	ILayoutService ls = Graphiti.getLayoutService();

	@Override
	public ILocation getConnectionMidpoint(Connection c, double d) {
		return ls.getConnectionMidpoint(c, d);
	}

	@Override
	public IRectangle getGaBoundsForAnchor(Anchor anchor) {
		return ls.getGaBoundsForAnchor(anchor);
	}

	@Override
	public ILocationInfo getLocationInfo(Shape shape, int x, int y) {
		return ls.getLocationInfo(shape, x, y);
	}

	@Override
	public ILocation getLocationRelativeToDiagram(Anchor anchor) {
		return ls.getLocationRelativeToDiagram(anchor);
	}

	@Override
	public ILocation getLocationRelativeToDiagram(Shape shape) {
		return ls.getLocationRelativeToDiagram(shape);
	}

	@Override
	public IDimension calculateSize(GraphicsAlgorithm ga) {
		return ls.calculateSize(ga);
	}

	@Override
	public IDimension calculateSize(GraphicsAlgorithm ga, boolean considerLineWidth) {
		return ls.calculateSize(ga, considerLineWidth);
	}

	@Override
	public void setHeight(GraphicsAlgorithm ga, int height) {
		ls.setHeight(ga, height);

	}

	@Override
	public void setLocationAndSize(GraphicsAlgorithm ga, int x, int y, int width, int height) {
		ls.setLocationAndSize(ga, x, y, width, height);

	}

	@Override
	public void setLocationAndSize(GraphicsAlgorithm ga, int x, int y, int width, int height, boolean avoidNegativeCoordinates) {
		ls.setLocationAndSize(ga, x, y, width, height, avoidNegativeCoordinates);

	}

	@Override
	public void setLocation(GraphicsAlgorithm ga, int x, int y) {
		ls.setLocation(ga, x, y);

	}

	@Override
	public void setLocation(GraphicsAlgorithm ga, int x, int y, boolean avoidNegativeCoordinates) {
		ls.setLocation(ga, x, y, avoidNegativeCoordinates);

	}

	@Override
	public void setSize(GraphicsAlgorithm ga, int width, int height) {
		ls.setSize(ga, width, height);

	}

	@Override
	public void setWidth(GraphicsAlgorithm ga, int width) {
		ls.setWidth(ga, width);

	}

	@Override
	public IDimension calculateTextSize(String text, Font font) {
		return GraphitiUiInternal.getGefService().calculateTextSize(text, font);
	}

}
