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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.graphiti.palette.ICreationToolEntry;
import org.eclipse.graphiti.palette.IStackToolEntry;

/**
 * The Class StackEntry creates a compartment entry which visualises as a drawer
 * in the palette containing multiple tools in a drop down list of which one is
 * active and visible.
 */
public class StackEntry extends AbstractPaletteEntry implements IStackToolEntry {

	private String description;

	private List<ICreationToolEntry> creationToolEntries = new ArrayList<ICreationToolEntry>();

	/**
	 * Creates a new {@link StackEntry}.
	 * 
	 * @param label
	 *            the text to display
	 * @param description
	 *            the tooltip text
	 * @param iconId
	 *            the id for the icon
	 */
	public StackEntry(String label, String description, String iconId) {
		super(label, iconId);
		this.description = description;
	}

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Returns the creation tools.
	 * 
	 * @return the creation tool entries
	 */
	public List<ICreationToolEntry> getCreationToolEntries() {
		return this.creationToolEntries;
	}

	/**
	 * Adds a creation tool to the stack entry.
	 * 
	 * @param creationToolEntry
	 *            the creation tool entry
	 */
	public void addCreationToolEntry(ICreationToolEntry creationToolEntry) {
		this.creationToolEntries.add(creationToolEntry);
	}
}
