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
import org.eclipse.graphiti.features.IMoveBendpointFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IMoveBendpointContext;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.services.Graphiti;

/**
 * The Class DefaultMoveBendpointFeature.
 */
public class DefaultMoveBendpointFeature extends AbstractFeature implements IMoveBendpointFeature {

	/**
	 * Creates a new {@link DefaultMoveBendpointFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public DefaultMoveBendpointFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean moveBendpoint(IMoveBendpointContext context) {
		Point newPoint = Graphiti.getGaService().createPoint(context.getX(), context.getY());
		context.getConnection().getBendpoints().set(context.getBendpointIndex(), newPoint);
		return true;
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IMoveBendpointContext) {
			IMoveBendpointContext moveContext = (IMoveBendpointContext) context;
			ret = canMoveBendpoint(moveContext);
		}
		return ret;
	}

	public void execute(IContext context) {
		moveBendpoint((IMoveBendpointContext) context);
	}

	public boolean canMoveBendpoint(IMoveBendpointContext context) {
		return true;
	}

	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.DefaultMoveBendpointFeature_0_xfld;
}
