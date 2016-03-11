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
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAreaContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class AbstractAddPictogramElementFeature implements an add feature
 * especially for pictogram elements.
 */
public abstract class AbstractAddPictogramElementFeature extends AbstractAddFeature {

	/**
	 * Creates a new {@link AbstractAddPictogramElementFeature}.
	 * 
	 * @param fp
	 *            the fp
	 */
	public AbstractAddPictogramElementFeature(IFeatureProvider fp) {
		super(fp);
	}

	/**
	 * Checks if the pictogram element could be updated. This implementation
	 * asks the feature provider for available update features.
	 * 
	 * @param pe
	 *            the pictogram element
	 * 
	 * @return true, if the update could be processed
	 */
	protected boolean canUpdatePictogramElement(PictogramElement pe) {
		boolean ret = false;
		UpdateContext context = new UpdateContext(pe);
		ret = getFeatureProvider().canUpdate(context).toBoolean();
		return ret;
	}

	/**
	 * Checks if the pictogram element has to be updated. This implementation
	 * asks the feature provider for available update features.
	 * 
	 * @param pe
	 *            the pe
	 * 
	 * @return true, if update pictogram element needed
	 */
	protected boolean updatePictogramElementNeeded(PictogramElement pe) {
		boolean ret = false;
		UpdateContext updateSemanticsContext = new UpdateContext(pe);
		ret = getFeatureProvider().updateNeeded(updateSemanticsContext).toBoolean();
		return ret;
	}

	/**
	 * This is a convenience method for layouting a graphics algorithm.
	 * 
	 * @param ga
	 *            the ga
	 * @param minWidth
	 *            minimum width
	 * @param minHeight
	 *            minimum height
	 * @param context
	 *            the layout context
	 */
	protected void setSizeAndLocation(GraphicsAlgorithm ga, int minWidth, int minHeight, IAreaContext context) {
		int x = context.getX();
		int y = context.getY();
		int width = context.getWidth();
		int height = context.getHeight();

		// set min-size if user didn't draw a rectangle
		if (width < 0 && height < 0) {
			height = minHeight;
			width = minWidth;
		}

		Graphiti.getGaService().setLocationAndSize(ga, x, y, width, height);
	}
}
