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
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.func.IAdd;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class AddFeatureForPattern.
 */
public class AddFeatureForPattern extends AbstractAddFeature {
	private IAdd pattern;

	/**
	 * Creates a new {@link AddFeatureForPattern}.
	 * 
	 * @param featureProvider
	 *            the feature provider
	 * @param pattern
	 *            the pattern
	 */
	public AddFeatureForPattern(IFeatureProvider featureProvider, IAdd pattern) {
		super(featureProvider);
		this.pattern = pattern;
	}

	public boolean canAdd(IAddContext context) {
		return pattern.canAdd(context);
	}

	public PictogramElement add(IAddContext context) {
		return pattern.add(context);
	}
}
