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
package org.eclipse.epf.common.serviceability;

/**
 * Helper utility for printing debug traces to the console.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class DebugTrace {

	/**
	 * Prints a debug trace to the console.
	 * 
	 * @param instance
	 *            a class instance
	 * @param method
	 *            a method name
	 * @param msg
	 *            a debug message
	 * @param th
	 *            a <code>Throwable</code> object
	 */
	public static void print(Object instance, String method, String msg,
			Throwable th) {
		StringBuffer debugMsg = new StringBuffer();
		if (instance != null) {
			debugMsg.append(instance.getClass().getName());
			if (method != null) {
				debugMsg.append('.').append(method);
			}
			debugMsg.append(": "); //$NON-NLS-1$
		}
		if (msg != null && msg.length() > 0) {
			debugMsg.append(msg); 
		}
		System.out.println(debugMsg);
		if (th != null) {
			th.printStackTrace();
		}
	}

	/**
	 * Prints a debug trace to the console.
	 * 
	 * @param instance
	 *            a class instance
	 * @param method
	 *            a method name
	 * @param th
	 *            a <code>Throwable</code> object
	 */
	public static void print(Object instance, String method, Throwable th) {
		print(instance, method, null, th);
	}

	/**
	 * Prints a debug trace to the console.
	 * 
	 * @param instance
	 *            a class instance
	 * @param method
	 *            a method name
	 * @param msg
	 *            a debug message
	 */
	public static void print(Object instance, String method, String msg) {
		print(instance, method, msg, null);
	}

	/**
	 * Prints a debug trace to the console.
	 * 
	 * @param instance
	 *            a class instance
	 * @param method
	 *            a method name
	 */
	public static void print(Object instance, String method) {
		print(instance, method, null, null);
	}

	/**
	 * Prints a debug trace to the console.
	 * 
	 * @param th
	 *            a <code>Throwable</code> object
	 */
	public static void print(Throwable th) {
		if (th != null) {
			th.printStackTrace();
		}
	}

}
