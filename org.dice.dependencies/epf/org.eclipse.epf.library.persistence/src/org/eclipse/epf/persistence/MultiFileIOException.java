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
package org.eclipse.epf.persistence;

/**
 * Base exception for file-based persistence
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MultiFileIOException extends RuntimeException {

	private static final long serialVersionUID = 3257853198722347321L;

	private Object troubleObject;

	/**
	 * Creates a new instance.
	 */
	public MultiFileIOException() {
		super();
	}

	/**
	 * Creates a new instance.
	 */
	public MultiFileIOException(String arg0) {
		super(arg0);
	}

	public MultiFileIOException(String msg, Object troubleObj) {
		super(msg);
		troubleObject = troubleObj;
	}

	/**
	 * @return Returns the troubleObject.
	 */
	public Object getTroubleObject() {
		return troubleObject;
	}

}
