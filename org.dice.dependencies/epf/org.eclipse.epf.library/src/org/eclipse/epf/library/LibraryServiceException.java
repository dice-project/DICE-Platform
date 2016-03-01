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
 * Signals that a Library Service operation has failed.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance.
	 */
	public LibraryServiceException() {
		super();
	}

	/**
	 * Creates a new instance given the root cause of the exception.
	 * 
	 * @param rootCause
	 *            the root cause of the exception
	 */
	public LibraryServiceException(Throwable rootCause) {
		super(rootCause);
		LibraryPlugin.getDefault().getLogger().logError(rootCause);
	}

	/**
	 * Creates a new instance given the error message.
	 * 
	 * @param errorMsg
	 *            the error message
	 */
	public LibraryServiceException(String errorMsg) {
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
	public LibraryServiceException(String errorMsg, Throwable rootCause) {
		super(errorMsg, rootCause);
	}

}