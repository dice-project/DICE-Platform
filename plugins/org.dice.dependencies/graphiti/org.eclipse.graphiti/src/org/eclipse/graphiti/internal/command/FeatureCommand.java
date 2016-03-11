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
package org.eclipse.graphiti.internal.command;

import org.eclipse.graphiti.IReadOnly;
import org.eclipse.graphiti.IReadOnlyProvider;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.IFeatureHolder;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.internal.Messages;

/**
 * This command encapsulates a features.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class FeatureCommand implements ICommand, IFeatureHolder, IReadOnlyProvider {

	/** The Constant NO_FEATURE. */
	private static final String NO_FEATURE = Messages.FeatureCommand_0_xmsg;

	/** The feature. */
	private final IFeature feature;

	/**
	 * The Constructor.
	 * 
	 * @param feature
	 *            the feature
	 */
	public FeatureCommand(IFeature feature) {
		super();
		if (feature == null) {
			throw new IllegalArgumentException("feature must not be null"); //$NON-NLS-1$
		}
		this.feature = feature;
	}

	/**
	 * Gets the feature.
	 * 
	 * @return Returns the feature.
	 */
	public IFeature getFeature() {
		return feature;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.IDescription#getDescription()
	 */
	public String getDescription() {
		IFeature f = getFeature();
		if (f != null) {
			return f.getName();
		}
		return NO_FEATURE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.features.IFeatureProviderHolder#getFeatureProvider()
	 */
	public IFeatureProvider getFeatureProvider() {
		return getFeature().getFeatureProvider();
	}

	public boolean isReadOnly() {
		boolean ret = false;
		IFeature f = getFeature();
		if (f != null && f instanceof IReadOnly) {
			ret = true;
		}
		return ret;
	}
}