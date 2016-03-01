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
package org.eclipse.graphiti.util;

/**
 * The Interface ILook.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ILook {

	/**
	 * Gets the grid background color.
	 * 
	 * @return the grid background color
	 */
	IColorConstant getGridBackgroundColor();

	/**
	 * Gets the major grid line color.
	 * 
	 * @return the major grid line color
	 */
	IColorConstant getMajorGridLineColor();

	/**
	 * Gets the minor grid line color.
	 * 
	 * @return the minor grid line color
	 */
	IColorConstant getMinorGridLineColor();

	/**
	 * Gets the major grid line distance.
	 * 
	 * @return the major grid line distance
	 */
	int getMajorGridLineDistance();

	/**
	 * Gets the minor grid line distance.
	 * 
	 * @return the minor grid line distance
	 */
	int getMinorGridLineDistance();

	/**
	 * Gets the grid line thickness.
	 * 
	 * @return the grid line thickness
	 */
	int getGridLineThickness();

	/**
	 * Gets the field error background color.
	 * 
	 * @return the field error background color
	 */
	IColorConstant getFieldErrorBackgroundColor();
}
