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

import java.util.List;

/**
 * The Interface IPaletteCompartmentEntry.
 */
public interface IPaletteCompartmentEntry extends IPaletteEntry {

	/**
	 * Gets the tool entries.
	 * 
	 * @return the tool entries inside this compartment
	 */
	List<IToolEntry> getToolEntries();

	/**
	 * set the initial open state of the compartment.
	 * 
	 * @param initiallyOpen
	 *            the initially open
	 */
	void setInitiallyOpen(boolean initiallyOpen);

	/**
	 * provide the initial open state of the compartment.
	 * 
	 * @return the open state
	 */
	boolean isInitiallyOpen();
}
