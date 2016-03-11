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
package org.eclipse.graphiti.palette;

/**
 * The Interface IPaletteEntry.
 */
public interface IPaletteEntry {

	/**
	 * Gets the label.
	 * 
	 * @return the label
	 */
	String getLabel();

	/**
	 * Gets the icon id.
	 * 
	 * @return the icon id
	 */
	String getIconId();
}
