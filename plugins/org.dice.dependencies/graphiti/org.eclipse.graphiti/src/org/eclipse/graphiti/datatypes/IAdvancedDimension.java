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
 * The Interface IAdvancedDimension. It provides additional methods for the
 * dimension data type.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IAdvancedDimension extends IDimension {
	/**
	 * 
	 * @return An exact copy of the current advanced dimension instance.
	 */
	IDimension getDimensionCopy();

	/**
	 * Sets the width and height of the dimension.
	 * 
	 * @param width
	 *            the new width of the dimension
	 * @param height
	 *            the new height of the dimension
	 * @return the current instance
	 */
	IDimension setDimension(int width, int height);

	/**
	 * Sets the width and height of the dimension.
	 * 
	 * @param dimension
	 *            the dimension which contains the new width and height
	 * @return the current instance
	 */
	IDimension setDimension(IDimension dimension);

	/**
	 * Changes the current dimension
	 * 
	 * @param amount
	 *            width and height of the dimension will be multiplied with this
	 *            amount
	 */
	void scale(double amount);

	/**
	 * Expands the current dimension
	 * 
	 * @param dw
	 *            this value will be added to the width
	 * @param dh
	 *            this value will be added to the height
	 * @return the current instance
	 */
	IDimension expand(int dw, int dh);
}
