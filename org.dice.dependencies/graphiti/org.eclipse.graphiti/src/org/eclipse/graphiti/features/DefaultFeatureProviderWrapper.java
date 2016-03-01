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
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.context.IAddBendpointContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICopyContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.context.IMoveBendpointContext;
import org.eclipse.graphiti.features.context.IMoveConnectionDecoratorContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IPasteContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveBendpointContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class DefaultFeatureProviderWrapper.
 */
public class DefaultFeatureProviderWrapper implements IFeatureProvider {

	private IFeatureProvider innerFeatureProvider;

	/**
	 * Creates a new {@link DefaultFeatureProviderWrapper}.
	 * 
	 * @param innerFeatureProvider
	 *            the inner feature provider
	 */
	public DefaultFeatureProviderWrapper(IFeatureProvider innerFeatureProvider) {
		setInnerFeatureProvider(innerFeatureProvider);
	}

	public PictogramElement addIfPossible(IAddContext context) {
		return getInnerFeatureProvider().addIfPossible(context);
	}

	public IReason canAdd(IAddContext context) {
		return getInnerFeatureProvider().canAdd(context);
	}

	public IReason canLayout(ILayoutContext context) {
		return getInnerFeatureProvider().canLayout(context);
	}

	public IReason canUpdate(IUpdateContext context) {
		return getInnerFeatureProvider().canUpdate(context);
	}

	public IAddBendpointFeature getAddBendpointFeature(IAddBendpointContext context) {
		return getInnerFeatureProvider().getAddBendpointFeature(context);
	}

	public IAddFeature getAddFeature(IAddContext context) {
		return getInnerFeatureProvider().getAddFeature(context);
	}

	public Object[] getAllBusinessObjectsForPictogramElement(PictogramElement pictogramElement) {
		return getInnerFeatureProvider().getAllBusinessObjectsForPictogramElement(pictogramElement);
	}

	public PictogramElement[] getAllPictogramElementsForBusinessObject(Object businessObject) {
		return getInnerFeatureProvider().getAllPictogramElementsForBusinessObject(businessObject);
	}

	public Object getBusinessObjectForPictogramElement(PictogramElement pictogramElement) {
		return getInnerFeatureProvider().getBusinessObjectForPictogramElement(pictogramElement);
	}

	public ICopyFeature getCopyFeature(ICopyContext context) {
		return getInnerFeatureProvider().getCopyFeature(context);
	}

	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return getInnerFeatureProvider().getCreateConnectionFeatures();
	}

	public ICreateFeature[] getCreateFeatures() {
		return getInnerFeatureProvider().getCreateFeatures();
	}

	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		return getInnerFeatureProvider().getCustomFeatures(context);
	}

	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		return getInnerFeatureProvider().getDeleteFeature(context);
	}

	public IDiagramTypeProvider getDiagramTypeProvider() {
		return getInnerFeatureProvider().getDiagramTypeProvider();
	}

	public IDirectEditingFeature getDirectEditingFeature(IDirectEditingContext context) {
		return getInnerFeatureProvider().getDirectEditingFeature(context);
	}

	public IDirectEditingInfo getDirectEditingInfo() {
		return getInnerFeatureProvider().getDirectEditingInfo();
	}

	public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
		return getInnerFeatureProvider().getDragAndDropFeatures(context);
	}

	/**
	 * Gets the inner feature provider.
	 * 
	 * @return the inner feature provider
	 */
	public IFeatureProvider getInnerFeatureProvider() {
		return this.innerFeatureProvider;
	}

	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		return getInnerFeatureProvider().getLayoutFeature(context);
	}

	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		return getInnerFeatureProvider().getMoveAnchorFeature(context);
	}

	public IMoveBendpointFeature getMoveBendpointFeature(IMoveBendpointContext context) {
		return getInnerFeatureProvider().getMoveBendpointFeature(context);
	}

	public IMoveConnectionDecoratorFeature getMoveConnectionDecoratorFeature(IMoveConnectionDecoratorContext context) {
		return getInnerFeatureProvider().getMoveConnectionDecoratorFeature(context);
	}

	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		return getInnerFeatureProvider().getMoveShapeFeature(context);
	}

	public IPasteFeature getPasteFeature(IPasteContext context) {
		return getInnerFeatureProvider().getPasteFeature(context);
	}

	public PictogramElement getPictogramElementForBusinessObject(Object businessObject) {
		return getInnerFeatureProvider().getPictogramElementForBusinessObject(businessObject);
	}

	public IPrintFeature getPrintFeature() {
		return getInnerFeatureProvider().getPrintFeature();
	}

	public IReconnectionFeature getReconnectionFeature(IReconnectionContext context) {
		return getInnerFeatureProvider().getReconnectionFeature(context);
	}

	public IRemoveBendpointFeature getRemoveBendpointFeature(IRemoveBendpointContext context) {
		return getInnerFeatureProvider().getRemoveBendpointFeature(context);
	}

	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		return getInnerFeatureProvider().getRemoveFeature(context);
	}

	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {
		return getInnerFeatureProvider().getResizeShapeFeature(context);
	}

	public ISaveImageFeature getSaveImageFeature() {
		return getInnerFeatureProvider().getSaveImageFeature();
	}

	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		return getInnerFeatureProvider().getUpdateFeature(context);
	}

	public boolean hasPictogramElementForBusinessObject(Object businessObject) {
		return getInnerFeatureProvider().hasPictogramElementForBusinessObject(businessObject);
	}

	public IReason layoutIfPossible(ILayoutContext context) {
		return getInnerFeatureProvider().layoutIfPossible(context);
	}

	public void link(PictogramElement pictogramElement, Object businessObject) {
		getInnerFeatureProvider().link(pictogramElement, businessObject);
	}

	public void link(PictogramElement pictogramElement, Object[] businessObjects) {
		getInnerFeatureProvider().link(pictogramElement, businessObjects);
	}

	private void setInnerFeatureProvider(IFeatureProvider innerFeatureProvider) {
		this.innerFeatureProvider = innerFeatureProvider;
	}

	public IReason updateIfPossible(IUpdateContext context) {
		return getInnerFeatureProvider().updateIfPossible(context);
	}

	public IReason updateIfPossibleAndNeeded(IUpdateContext context) {
		return getInnerFeatureProvider().updateIfPossibleAndNeeded(context);
	}

	public IReason updateNeeded(IUpdateContext context) {
		return getInnerFeatureProvider().updateNeeded(context);
	}

	@Override
	public void dispose() {
	}
}
