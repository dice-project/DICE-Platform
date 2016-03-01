//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.common.ui.util;

import org.eclipse.swt.widgets.MenuItem;

/**
 * A helper class for retrieving Eclipse menus.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class MenuUtil {

	/**
	 * Searches an array of menu items and returns the menu item that matches
	 * the given menu text.
	 * 
	 * @param menuItems
	 *            An array of menu items.
	 * @param text
	 *            The text to look for.
	 * @return The menu item if found, <code>null</code> otherwise.
	 */
	public static MenuItem getMenuItem(MenuItem[] menuItems, String text) {
		if (menuItems == null || text == null || text.length() == 0) {
			return null;
		}

		for (int i = 0; i < menuItems.length; i++) {
			if (menuItems[i].getText().startsWith(text)) {
				return menuItems[i];
			}
		}

		return null;
	}

}
