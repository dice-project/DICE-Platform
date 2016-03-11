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
package org.eclipse.epf.common.utils;

/**
 * Utility class for handling threads.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ThreadUtil {

	/**
	 * Private constructor to prevent this class from being instantiated. All
	 * methods in this class should be static.
	 */
	private ThreadUtil() {
	}

	/**
	 * Puts the current thread to sleep for a given duration.
	 * 
	 * @param time
	 *            The duration in milliseconds.
	 */
	public static void pause(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

}
