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
/**
 * 
 */
package org.eclipse.graphiti.platform;

/**
 * The Interface IExtension.
 */
public interface IExtension {

	/**
	 * Gets the provider id.
	 * 
	 * @return the provider id
	 */
	String getProviderId();

	/**
	 * Sets the provider id.
	 * 
	 * @param providerId
	 *            the new provider id
	 */
	void setProviderId(String providerId);
}
