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
package org.eclipse.graphiti.datatypes;

/**
 * The Interface IDimension.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IDimension {

	/**
	 * Gets the width.
	 * 
	 * @return the width of this dimension
	 */
	int getWidth();

	/**
	 * Sets the width.
	 * 
	 * @param width
	 *            the new width of this dimension
	 */
	void setWidth(int width);

	/**
	 * Gets the height.
	 * 
	 * @return the height of this dimension
	 */
	int getHeight();

	/**
	 * Sets the height.
	 * 
	 * @param height
	 *            the new height of this dimension
	 */
	void setHeight(int height);
}
