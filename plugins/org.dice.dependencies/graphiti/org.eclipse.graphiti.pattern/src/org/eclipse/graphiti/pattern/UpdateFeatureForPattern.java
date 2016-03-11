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
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.func.IUpdate;
import org.eclipse.graphiti.internal.util.T;

/**
 * The Class UpdateFeatureForPattern.
 */
public class UpdateFeatureForPattern extends AbstractUpdateFeature {

	private IUpdate pattern;

	/**
	 * Creates a new {@link UpdateFeatureForPattern}.
	 * 
	 * @param featureProvider
	 *            the feature provider
	 * @param pattern
	 *            the pattern
	 */
	public UpdateFeatureForPattern(IFeatureProvider featureProvider, IUpdate pattern) {
		super(featureProvider);
		this.pattern = pattern;
	}

	public boolean canUpdate(IUpdateContext context) {
		boolean ret = pattern.canUpdate(context);
		return ret;
	}

	public IReason updateNeeded(IUpdateContext context) {
		if (T.racer().info()) {
			T.racer().info("UpdateFeatureForPattern", "updateNeeded", "context: " + context); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		IReason ret = pattern.updateNeeded(context);
		if (T.racer().info()) {
			T.racer().info("UpdateFeatureForPattern", "updateNeeded", "returns " + ret.toString()); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		return ret;
	}

	public boolean update(IUpdateContext context) {
		return pattern.update(context);
	}
}
