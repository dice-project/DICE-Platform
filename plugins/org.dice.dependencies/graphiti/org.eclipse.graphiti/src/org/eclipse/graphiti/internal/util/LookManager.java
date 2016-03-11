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
package org.eclipse.graphiti.internal.util;

import org.eclipse.graphiti.util.ILook;

/**
 * The Class LookManager.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class LookManager {

	private static ILook currentLook;

	private static ILook defaultLook;

	private static ILook dynamicLook;

	/**
	 * Gets the look.
	 * 
	 * @return the look
	 */
	public static ILook getLook() {
		if (currentLook == null) {
			currentLook = getDefaultLook();
		}
		return currentLook;
	}

	public static void setDynamicLook(boolean dynamic) {
		if (dynamic) {
			currentLook = getDynamicLook();
		} else {
			currentLook = getDefaultLook();
		}
	}

	private static ILook getDefaultLook() {
		if (defaultLook == null) {
			defaultLook = new DefaultLook();
		}
		return defaultLook;
	}

	private static ILook getDynamicLook() {
		if (dynamicLook == null) {
			dynamicLook = new DynamicLook();
		}
		return dynamicLook;
	}
}
