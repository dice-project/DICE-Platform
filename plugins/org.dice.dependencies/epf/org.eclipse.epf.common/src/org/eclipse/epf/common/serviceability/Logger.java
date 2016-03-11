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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;

/**
 * Generic logger used for logging status messages, warning messages, error
 * messages, and exception stack traces.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class Logger {

	public static String SeeLogFileForMoreDetails;
	
	// The plug-in instance.
	private Plugin plugin;

	// The plug-in ID.
	private String pluginId;

	/**
	 * Create a new <code>Logger</code> given the plug-in instance.
	 * 
	 * @param plugin
	 *            The plugin instance.
	 */
	public Logger(Plugin plugin) {
		this.plugin = plugin;
		this.pluginId = plugin.getBundle().getSymbolicName(); //plugin.getId();
	}

	/**
	 * Logs the given error message.
	 * 
	 * @param msg
	 *            The error message.
	 */
	public void logError(String msg) {
		logMsg(IStatus.ERROR, msg, null);
	}

	/**
	 * Logs the given exception.
	 * 
	 * @param ex
	 *            The exception.
	 */
	public void logError(Throwable ex) {
		logMsg(IStatus.ERROR, null, ex);
	}

	/**
	 * Logs the given error message and exception.
	 * 
	 * @param msg
	 *            The error message.
	 * @param ex
	 *            The exception.
	 */
	public void logError(String msg, Throwable ex) {
		logMsg(IStatus.ERROR, msg, ex);
	}

	/**
	 * Logs the given warning message.
	 * 
	 * @param msg
	 *            The warning message.
	 */
	public void logWarning(String msg) {
		logMsg(IStatus.WARNING, msg, null);
	}

	/**
	 * Logs the given warning message and exception.
	 * 
	 * @param msg
	 *            The warning message.
	 * @param ex
	 *            The exception.
	 */
	public void logWarning(String msg, Throwable ex) {
		logMsg(IStatus.WARNING, msg, ex);
	}

	/**
	 * Logs the given status message and exception.
	 * 
	 * @param msg
	 *            The status message.
	 */
	public void logInfo(String msg) {
		logMsg(IStatus.INFO, msg, null);
	}

	/**
	 * Logs the given message and exception.
	 * 
	 * @param severity
	 *            The severity.
	 * @param msg
	 *            The message.
	 * @param ex
	 *            The exception.
	 */
	public synchronized void logMsg(int severity, String msg, Throwable ex) {
		if (msg == null && ex != null) {
			msg = ex.getMessage(); 
		}

		if (msg == null) {
			msg = ""; //$NON-NLS-1$
		}

		Status status = new Status(severity, pluginId, IStatus.OK, msg, ex);

		// Call the Eclipse Logger.
		plugin.getLog().log(status);
	}

}