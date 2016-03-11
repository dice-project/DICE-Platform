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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The Class DefaultUpdateDiagramFeature. Updates the shapes directly contained
 * in the diagram. Does not update the connections contained in the diagram,
 * since in many cases the connections get already updated by the shapes they
 * belong to.
 */
public class DefaultUpdateDiagramFeature extends AbstractUpdateFeature {

	/**
	 * Creates a new {@link DefaultUpdateDiagramFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public DefaultUpdateDiagramFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		return context.getPictogramElement() instanceof Diagram;
	}

	@Override
	public boolean update(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (pe instanceof Diagram) {
			Diagram d = (Diagram) pe;
			EList<Shape> children = d.getChildren();
			// Collect PEs for update. Execute update later, because otherwise we could get a 
			// ConcurrentModificationException if PEs get deleted etc.
			Map<IUpdateFeature, IUpdateContext> connToUpdate = new HashMap<IUpdateFeature, IUpdateContext>();
			for (Shape shape : children) {
				UpdateContext updateContext = new UpdateContext(shape);
				IUpdateFeature updateFeature = getFeatureProvider().getUpdateFeature(updateContext);
				if (updateFeature != null && updateFeature.canUpdate(updateContext)
						&& updateFeature.updateNeeded(updateContext).toBoolean()) {
					connToUpdate.put(updateFeature, updateContext);
				}
			}
			for (IUpdateFeature feature : connToUpdate.keySet()) {
				feature.update(connToUpdate.get(feature));
			}
		}
		return true;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (pe instanceof Diagram) {
			Diagram d = (Diagram) pe;
			EList<Shape> children = d.getChildren();
			for (Shape shape : children) {
				UpdateContext updateContext = new UpdateContext(shape);
				IUpdateFeature updateFeature = getFeatureProvider().getUpdateFeature(updateContext);
				if (updateFeature != null && updateFeature.updateNeeded(updateContext).toBoolean()) {
					return Reason.createTrueReason();
				}
			}
			EList<Connection> connections = d.getConnections();
			for (Connection connection : connections) {
				UpdateContext updateContext = new UpdateContext(connection);
				IUpdateFeature updateFeature = getFeatureProvider().getUpdateFeature(updateContext);
				if (updateFeature != null && updateFeature.updateNeeded(updateContext).toBoolean()) {
					return Reason.createTrueReason();
				}
			}
		}
		return Reason.createFalseReason();
	}
}
