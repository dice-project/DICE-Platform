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
package org.eclipse.epf.library;

/**
 * Signals that a Method Library cannot be located.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryNotFoundException extends LibraryServiceException {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance.
	 */
	public LibraryNotFoundException() {
		super();
	}

	/**
	 * Creates a new instance given the root cause of the exception.
	 * 
	 * @param rootCause
	 *            the root cause of the exception
	 */
	public LibraryNotFoundException(Throwable rootCause) {
		super(rootCause);
	}

	/**
	 * Creates a new instance given the error message.
	 * 
	 * @param errorMsg
	 *            the error message
	 */
	public LibraryNotFoundException(String errorMsg) {
		super(errorMsg);
	}

	/**
	 * Creates a new instance given the error message and the root cause of the
	 * exception.
	 * 
	 * @param errorMsg
	 *            the error message
	 * @param rootCause
	 *            the root cause of the exception
	 */
	public LibraryNotFoundException(String errorMsg, Throwable rootCause) {
		super(errorMsg, rootCause);
	}

}
