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
package org.eclipse.graphiti.ui.internal.config;

/**
 * A simple implementation of the interface IConfigurationProviderHolder.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class AbstractConfigurationProviderHolder implements IConfigurationProviderHolder {

	private IConfigurationProvider _configurationProvider;

	/**
	 * Creates a new SimpleConfigurationProviderHolder.
	 * 
	 * @param configurationProvider
	 *            the configuration provider
	 */
	public AbstractConfigurationProviderHolder(IConfigurationProvider configurationProvider) {
		if (configurationProvider == null)
			throw new RuntimeException("Implementation-error: the IConfigurationProvider must not be null."); //$NON-NLS-1$

		_configurationProvider = configurationProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.config.provider.IConfigurationProviderHolder#
	 * getConfigurationProvider()
	 */
	public IConfigurationProvider getConfigurationProvider() {
		return _configurationProvider;
	}
}