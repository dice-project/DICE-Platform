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
package org.eclipse.graphiti.pattern.config;

/**
 * The Interface IMinimumSizeConfiguration.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IMinimumSizeConfiguration extends IPatternConfiguration {

	/**
	 * Gets the minimum height.
	 * 
	 * @return the minimum height
	 */
	int getMinimumHeight();

	/**
	 * Gets the minimum width.
	 * 
	 * @return the minimum width
	 */
	int getMinimumWidth();

	/**
	 * Sets the minimum height.
	 * 
	 * @param minimumHeight
	 *            the new minimum height
	 */
	void setMinimumHeight(int minimumHeight);

	/**
	 * Sets the minimum width.
	 * 
	 * @param minimumWidth
	 *            the new minimum width
	 */
	void setMinimumWidth(int minimumWidth);

}
