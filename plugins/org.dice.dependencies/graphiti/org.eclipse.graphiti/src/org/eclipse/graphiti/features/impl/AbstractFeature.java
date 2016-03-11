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
/*
 * Created on 13.07.2005
 */
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IProgress;
import org.eclipse.graphiti.features.context.IAreaContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.platform.IDiagramEditor;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * Abstract base class for all features. Prefer extending this class over
 * inheriting interface {@link org.eclipse.graphiti.features.IFeature}.
 */
public abstract class AbstractFeature implements IFeature {

	private IFeatureProvider fp;

	private IProgress progressCallback;

	/**
	 * Creates a new {@link AbstractFeature}.
	 * 
	 * @param fp
	 *            the fp
	 */
	public AbstractFeature(IFeatureProvider fp) {
		super();
		this.fp = fp;
	}

	public boolean canUndo(IContext context) {
		return true;
	}

	@Override
	public boolean hasDoneChanges() {
		return true;
	}

	public String getDescription() {
		return toString();
	}

	public IFeatureProvider getFeatureProvider() {
		return this.fp;
	}

	public String getName() {
		return this.getClass().getName();
	}

	public boolean isAvailable(IContext context) {
		return true;
	}

	/**
	 * Sets the progress callback.
	 * 
	 * @param progress
	 *            the new progress callback
	 */
	public void setProgressCallback(IProgress progress) {
		this.progressCallback = progress;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

	/**
	 * Adds the graphical representation.
	 * 
	 * @param context
	 *            the context
	 * @param newObject
	 *            the new object
	 * 
	 * @return the added pictogram element
	 */
	protected PictogramElement addGraphicalRepresentation(IAreaContext context, Object newObject) {
		return getFeatureProvider().addIfPossible(new AddContext(context, newObject));
	}

	/**
	 * Gets the all business objects for pictogram element.
	 * 
	 * @param pe
	 *            the pe
	 * 
	 * @return the all business objects for pictogram element
	 */
	protected Object[] getAllBusinessObjectsForPictogramElement(PictogramElement pe) {
		return getFeatureProvider().getAllBusinessObjectsForPictogramElement(pe);
	}

	/**
	 * Gets the business object for pictogram element.
	 * 
	 * @param pe
	 *            the pe
	 * 
	 * @return the business object for pictogram element
	 */
	protected Object getBusinessObjectForPictogramElement(PictogramElement pe) {
		return getFeatureProvider().getBusinessObjectForPictogramElement(pe);
	}

	/**
	 * Gets the diagram.
	 * 
	 * @return the diagram
	 */
	protected Diagram getDiagram() {
		return getFeatureProvider().getDiagramTypeProvider().getDiagram();
	}

	/**
	 * Gets the diagram editor.
	 * 
	 * @return the diagram editor
	 */
	protected IDiagramEditor getDiagramEditor() {
		return getFeatureProvider().getDiagramTypeProvider().getDiagramEditor();
	}

	/**
	 * Gets the progress callback.
	 * 
	 * @return the progress callback
	 */
	protected IProgress getProgressCallback() {
		return this.progressCallback;
	}

	/**
	 * Gets the user decision.
	 * 
	 * @return the user decision
	 */
	protected boolean getUserDecision() {
		return true;
	}

	/**
	 * Layoutes the given pictogram element. This implementation asks the
	 * feature provider for available layout features and processes the first
	 * one.
	 * 
	 * @param pe
	 *            the pe
	 */
	protected void layoutPictogramElement(PictogramElement pe) {
		LayoutContext context = new LayoutContext(pe);
		getFeatureProvider().layoutIfPossible(context);
	}

	/**
	 * Link.
	 * 
	 * @param pe
	 *            the pe
	 * @param businessObject
	 *            the business object
	 */
	protected void link(PictogramElement pe, Object businessObject) {
		link(pe, new Object[] { businessObject });
	}

	/**
	 * Link.
	 * 
	 * @param pe
	 *            the pe
	 * @param businessObjects
	 *            the business objects
	 */
	protected void link(PictogramElement pe, Object businessObjects[]) {
		getFeatureProvider().link(pe, businessObjects);
	}

	/**
	 * Manage color.
	 * 
	 * @param colorConstant
	 *            the color constant
	 * 
	 * @return the color
	 */
	protected Color manageColor(IColorConstant colorConstant) {
		return Graphiti.getGaService().manageColor(getDiagram(), colorConstant);
	}

	/**
	 * Manage color.
	 * 
	 * @param red
	 *            the red
	 * @param green
	 *            the green
	 * @param blue
	 *            the blue
	 * 
	 * @return the color
	 */
	protected Color manageColor(int red, int green, int blue) {
		return Graphiti.getGaService().manageColor(getDiagram(), red, green, blue);
	}

	/**
	 * Updates the given pictogram element. This implementation asks the feature
	 * provider for available update features and processes the first one.
	 * 
	 * @param pe
	 *            the pe
	 */
	protected void updatePictogramElement(PictogramElement pe) {
		UpdateContext context = new UpdateContext(pe);
		getFeatureProvider().updateIfPossible(context);
	}
}