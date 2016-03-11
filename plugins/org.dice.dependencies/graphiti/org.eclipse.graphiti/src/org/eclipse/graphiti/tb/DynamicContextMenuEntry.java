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
package org.eclipse.graphiti.tb;

import java.util.ArrayList;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;

/**
 * The Class DynamicContextMenuEntry.
 */
public class DynamicContextMenuEntry extends AbstractContextEntry implements IContextMenuEntry {

	private static final IContextMenuEntry[] NO_CONTEXT_MENU_ENTRIES = new IContextMenuEntry[0];

	private int minimumSubmenuEntries = 3;

	private ArrayList<IContextMenuEntry> children = new ArrayList<IContextMenuEntry>();

	/**
	 * Creates a new {@link DynamicContextMenuEntry}.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public DynamicContextMenuEntry(IFeature feature, IContext context) {
		super(feature, context);
	}

	public IContextMenuEntry[] getChildren() {
		IContextMenuEntry[] ret = this.children.toArray(NO_CONTEXT_MENU_ENTRIES);
		return ret;
	}

	public void add(IContextMenuEntry entry) {
		this.children.add(entry);
	}

	public boolean isSubmenu() {
		return (getText() != null || getFeature() != null) && getChildren().length >= getMinimumSubmenuEntries();
	}

	/**
	 * Sets the minimum submenu entries.
	 * 
	 * @param minimumSubmenuEntries
	 *            the new minimum submenu entries
	 */
	public void setMinimumSubmenuEntries(int minimumSubmenuEntries) {
		this.minimumSubmenuEntries = minimumSubmenuEntries;
	}

	/**
	 * Gets the minimum submenu entries.
	 * 
	 * @return the minimum submenu entries
	 */
	public int getMinimumSubmenuEntries() {
		return this.minimumSubmenuEntries;
	}
}
