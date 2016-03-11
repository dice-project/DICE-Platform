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

import java.util.Iterator;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class DefaultRemoveFeature.
 */
public class DefaultRemoveFeature extends AbstractFeature implements IRemoveFeature {

	/**
	 * Creates a new {@link DefaultRemoveFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public DefaultRemoveFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canRemove(IRemoveContext context) {
		return !(context.getPictogramElement() instanceof Diagram);
	}

	public final void remove(IRemoveContext context) {
		if (!getUserDecision()) {
			return;
		}
		preRemove(context);

		PictogramElement pe = context.getPictogramElement();

		if (pe instanceof Shape) {
			Shape shape = (Shape) pe;
			removeAllConnections(shape);
		}

		Graphiti.getPeService().deletePictogramElement(pe);

		postRemove(context);
	}

	public void preRemove(IRemoveContext context) {
	}

	/**
	 * Removes the all connections.
	 * 
	 * @param shape
	 *            the shape
	 */
	protected void removeAllConnections(Shape shape) {
		IFeatureProvider featureProvider = getFeatureProvider();
		for (Iterator<Anchor> iter = shape.getAnchors().iterator(); iter.hasNext();) {
			Anchor anchor = iter.next();
			for (Iterator<Connection> iterator = Graphiti.getPeService().getAllConnections(anchor).iterator(); iterator.hasNext();) {
				Connection connection = iterator.next();
				if (GraphitiInternal.getEmfService().isObjectAlive(connection)) {
					IRemoveContext rc = new RemoveContext(connection);
					IRemoveFeature removeFeature = featureProvider.getRemoveFeature(rc);
					if (removeFeature != null) {
						ConnectionDecorator decorators[] = connection.getConnectionDecorators().toArray(new ConnectionDecorator[0]);
						for (ConnectionDecorator decorator : decorators) {
							if (decorator != null && GraphitiInternal.getEmfService().isObjectAlive(decorator)) {
								EcoreUtil.delete(decorator, true);
							}
						}
						removeFeature.remove(rc);
					}
				}
			}
		}
	}

	public void postRemove(IRemoveContext context) {
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IRemoveContext) {
			ret = canRemove((IRemoveContext) context);
		}
		return ret;
	}

	public void execute(IContext context) {
		if (context instanceof IRemoveContext) {
			remove((IRemoveContext) context);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.DefaultRemoveFeature_0_xfld;
}