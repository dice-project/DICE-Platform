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
package org.eclipse.epf.uma.util;

import org.eclipse.emf.common.util.WrappedException;

/**
 * Signals that a method library persistence operation has failed.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MessageException extends WrappedException {

	private static final Exception DUMMY_EXCEPTION = new Exception();

	private static final long serialVersionUID = 3904682656683013433L;

	/**
	 * Creates a new instance.
	 * 
	 * @param message
	 *            an error message
	 */
	public MessageException(String msg) {
		super(msg, DUMMY_EXCEPTION);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param message
	 *            an error message
	 * @param exception
	 *            an exception object
	 */
	public MessageException(String message, Exception exception) {
		super(message, exception);
	}

}