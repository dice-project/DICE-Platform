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

import org.eclipse.graphiti.palette.IPaletteCompartmentEntry;
import org.eclipse.graphiti.palette.IToolEntry;

/**
 * The Class PaletteCompartmentEntry creates a compartment entry which
 * visualises as a drawer in the palette containing multiple tools.
 */
public class PaletteCompartmentEntry extends AbstractPaletteEntry implements IPaletteCompartmentEntry {

	private List<IToolEntry> toolEntries = new ArrayList<IToolEntry>();

	private boolean initiallyOpen = true;

	/**
	 * Creates a new {@link PaletteCompartmentEntry}.
	 * 
	 * @param label
	 *            the text label
	 * @param iconId
	 *            the icon which is displayed
	 */
	public PaletteCompartmentEntry(String label, String iconId) {
		super(label, iconId);
	}

	/**
	 * Gets the tool entries.
	 * 
	 * @return the tools contained in the compartment
	 */
	public List<IToolEntry> getToolEntries() {
		return this.toolEntries;
	}

	/**
	 * adds a tool entry to the compartment.
	 * 
	 * @param toolEntry
	 *            the tool entry
	 */
	public void addToolEntry(IToolEntry toolEntry) {
		this.toolEntries.add(toolEntry);
	}

	public void setInitiallyOpen(boolean initiallyOpen) {
		this.initiallyOpen = initiallyOpen;
	}

	public boolean isInitiallyOpen() {
		return this.initiallyOpen;
	}

}
