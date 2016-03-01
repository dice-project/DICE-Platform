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
package org.eclipse.graphiti.palette.impl;

import org.eclipse.graphiti.palette.IPaletteEntry;

/**
 * The Class AbstractPaletteEntry.
 */
public abstract class AbstractPaletteEntry implements IPaletteEntry {

	private String label, iconId;

	/**
	 * Creates a new {@link AbstractPaletteEntry}.
	 * 
	 * @param label
	 *            the label
	 * @param iconId
	 *            the icon id
	 */
	public AbstractPaletteEntry(String label, String iconId) {
		this.label = label;
		this.iconId = iconId;
	}

	public String getLabel() {
		return this.label;
	}

	public String getIconId() {
		return this.iconId;
	}

}
