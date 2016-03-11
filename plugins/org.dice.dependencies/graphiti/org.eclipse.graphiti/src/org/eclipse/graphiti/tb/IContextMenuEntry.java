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

/**
 * The Interface IContextMenuEntry.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IContextMenuEntry extends IContextEntry {

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	IContextMenuEntry[] getChildren();

	/**
	 * Adds the.
	 * 
	 * @param entry
	 *            the entry
	 */
	void add(IContextMenuEntry entry);

	/**
	 * Checks if is submenu.
	 * 
	 * @return true, if is submenu
	 */
	boolean isSubmenu();
}
