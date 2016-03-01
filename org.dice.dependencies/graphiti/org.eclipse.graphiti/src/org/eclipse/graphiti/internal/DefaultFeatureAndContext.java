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
package org.eclipse.graphiti.internal;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureAndContext;
import org.eclipse.graphiti.features.context.IContext;

/**
 * The Class DefaultFeatureAndContext.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DefaultFeatureAndContext implements IFeatureAndContext {
	private IFeature feature;

	private IContext context;

	/**
	 * Instantiates a new default feature and context.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public DefaultFeatureAndContext(IFeature feature, IContext context) {
		super();
		setFeature(feature);
		setContext(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IFeatureHolder#getFeature()
	 */
	public IFeature getFeature() {
		return feature;
	}

	private void setFeature(IFeature feature) {
		this.feature = feature;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.features.IContextHolder#getContext()
	 */
	public IContext getContext() {
		return context;
	}

	private void setContext(IContext context) {
		this.context = context;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ret = this.getClass().getSimpleName();
		ret = ret + " (Feature: " + getFeature() + " - Context: " + getContext() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return ret;
	}
}
