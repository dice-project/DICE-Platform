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
package org.eclipse.epf.library.persistence;

/**
 * @author Phong Nguyen Le - Oct 2, 2006
 * @since  1.0
 */
public class LibraryResourceException extends Exception {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 8272587344678676828L;

	/**
	 * 
	 */
	public LibraryResourceException() {
		super();
	}

	/**
	 * @param message
	 */
	public LibraryResourceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public LibraryResourceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public LibraryResourceException(Throwable cause) {
		super(cause);
	}

}
