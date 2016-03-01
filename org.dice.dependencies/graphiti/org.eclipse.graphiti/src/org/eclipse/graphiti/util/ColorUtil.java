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
 * Utility for working with Colors.
 */
public class ColorUtil {

	/**
	 * Gets the corresponding int representation from hexedecimal RGB string for
	 * color blue.
	 * 
	 * @param hexRGBString
	 * @return corresponding int value for blue
	 */
	public static int getBlueFromHex(final String hexRGBString) {
		return Integer.valueOf(hexRGBString.substring(4, 6), 16);
	}

	/**
	 * Gets the corresponding int representation from hexedecimal RGB string for
	 * color red.
	 * 
	 * @param hexRGBString
	 * @return corresponding int value for red
	 */
	public static int getRedFromHex(final String hexRGBString) {
		return Integer.valueOf(hexRGBString.substring(0, 2), 16);
	}

	/**
	 * Gets the corresponding int representation from hexedecimal RGB string for
	 * color green.
	 * 
	 * @param hexRGBString
	 * @return corresponding int value for green
	 */
	public static int getGreenFromHex(final String hexRGBString) {
		return Integer.valueOf(hexRGBString.substring(2, 4), 16);
	}

}
