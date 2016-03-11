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
package org.eclipse.graphiti.ui.internal.parts;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.features.IFeatureProviderHolder;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.ga.IVisualStateHolder;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * The Interface IPictogramElementDelegate.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IPictogramElementDelegate extends IAdaptable, IFeatureProviderHolder, IVisualStateHolder {

	/**
	 * Creates the figure.
	 * 
	 * @return the i figure
	 */
	IFigure createFigure();

	/**
	 * Refresh figure for edit part.
	 */
	void refreshFigureForEditPart();

	/**
	 * Gets the configuration provider.
	 * 
	 * @return the configuration provider
	 */
	IConfigurationProvider getConfigurationProvider();

	/**
	 * Gets the pictogram element.
	 * 
	 * @return the pictogram element
	 */
	PictogramElement getPictogramElement();

	/**
	 * Activate.
	 */
	void activate();

	/**
	 * Deactivate.
	 */
	void deactivate();

	/**
	 * Gets the figure for graphics algorithm.
	 * 
	 * @param graphicsAlgorithm
	 *            the graphics algorithm
	 * 
	 * @return the figure for graphics algorithm
	 */
	IFigure getFigureForGraphicsAlgorithm(GraphicsAlgorithm graphicsAlgorithm);

	/**
	 * Refresh edit parts for model children and source connections.
	 * 
	 * @param ep
	 *            the ep
	 */
	void refreshEditPartsForModelChildrenAndSourceConnections(EditPart ep);

	/**
	 * If set to true, the visual refresh (synchronization of figures and
	 * graphics algorithm) will be done even it is not necessary.
	 * 
	 * @param forceRefresh
	 */
	void setForceRefresh(boolean forceRefresh);

	/**
	 * 
	 * @return true, if edit part (and it's delegate) are still valid (alive)
	 */
	boolean isValid();

	List<IFigure> getMainFiguresFromChildEditparts();
}
