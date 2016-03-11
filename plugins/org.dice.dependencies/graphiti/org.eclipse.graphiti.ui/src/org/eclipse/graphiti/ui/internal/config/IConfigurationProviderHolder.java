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
 * A simple interface, which can be implemented by all classes/interfaces which
 * provide access to an IConfigurationProvider.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IConfigurationProviderHolder {

	/**
	 * Returns the IConfigurationProvider. Must not return null.
	 * 
	 * @return The IConfigurationProvider. Must not return null.
	 * 
	 * @throws IllegalStateException
	 *             If no IConfigurationProvider is available (if it is null).
	 */
	IConfigurationProvider getConfigurationProvider() throws IllegalStateException;
}