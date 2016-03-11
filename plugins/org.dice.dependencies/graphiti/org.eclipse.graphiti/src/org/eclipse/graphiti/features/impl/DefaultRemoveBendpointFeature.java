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
import org.eclipse.graphiti.features.IRemoveBendpointFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveBendpointContext;
import org.eclipse.graphiti.internal.Messages;

/**
 * The Class DefaultRemoveBendpointFeature.
 */
public class DefaultRemoveBendpointFeature extends AbstractFeature implements IRemoveBendpointFeature {

	/**
	 * Creates a new {@link DefaultRemoveBendpointFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public DefaultRemoveBendpointFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canRemoveBendpoint(IRemoveBendpointContext context) {
		return true;
	}

	public void removeBendpoint(IRemoveBendpointContext context) {
		context.getConnection().getBendpoints().remove(context.getBendpointIndex());
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IRemoveBendpointContext) {
			ret = canRemoveBendpoint((IRemoveBendpointContext) context);
		}
		return ret;
	}

	public void execute(IContext context) {
		removeBendpoint((IRemoveBendpointContext) context);
	}

	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.DefaultRemoveBendpointFeature_0_xfld;
}
