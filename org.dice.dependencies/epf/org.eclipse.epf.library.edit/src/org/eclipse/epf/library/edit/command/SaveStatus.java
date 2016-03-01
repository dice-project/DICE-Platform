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
package org.eclipse.epf.library.edit.command;

import org.eclipse.core.runtime.Status;

/**
 * The status of a save operation.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class SaveStatus extends Status {

	public static final int SAVE_ERROR = 1;

	public static final int ROLLBACK_ERROR = 2;

	/**
	 * Creates a new instance.
	 * 
	 * @param severity
	 * @param pluginId
	 * @param code
	 * @param message
	 * @param exception
	 */
	public SaveStatus(int severity, String pluginId, int code, String message,
			Throwable exception) {
		super(severity, pluginId, code, message, exception);
	}

}
