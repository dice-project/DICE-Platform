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
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.context.impl.ResizeShapeContext;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The Class AbstractLayoutFeature.
 */
public abstract class AbstractLayoutFeature extends AbstractFeature implements ILayoutFeature {

	/**
	 * Creates a new {@link AbstractLayoutFeature}.
	 * 
	 * @param fp
	 *            the fp
	 */
	public AbstractLayoutFeature(IFeatureProvider fp) {
		super(fp);
	}

	public final boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof ILayoutContext) {
			ILayoutContext layoutSemanticsContext = (ILayoutContext) context;
			ret = canLayout(layoutSemanticsContext);
		}
		return ret;
	}

	public void execute(IContext context) {
		if (context instanceof ILayoutContext) {
			layout((ILayoutContext) context);
		}
	}

	/**
	 * Can layout pictogram element.
	 * 
	 * @param pe
	 *            the pe
	 * 
	 * @return true, if successful
	 */
	protected boolean canLayoutPictogramElement(PictogramElement pe) {
		return getFeatureProvider().canLayout(new LayoutContext(pe)).toBoolean();
	}

	/**
	 * Resize shape.
	 * 
	 * @param shape
	 *            the shape
	 */
	protected void resizeShape(Shape shape) {
		final ResizeShapeContext resizeShapeContext = new ResizeShapeContext(shape);
		GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
		resizeShapeContext.setWidth(ga.getWidth());
		resizeShapeContext.setHeight(ga.getHeight());

		final IResizeShapeFeature resizeShapeFeature = getFeatureProvider().getResizeShapeFeature(resizeShapeContext);
		if (resizeShapeFeature != null) {
			if (resizeShapeFeature.canResizeShape(resizeShapeContext)) {
				resizeShapeFeature.resizeShape(resizeShapeContext);
			}
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.AbstractLayoutFeature_0_xfld;
}