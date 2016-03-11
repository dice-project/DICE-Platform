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
package org.eclipse.epf.export.msp;

/**
 * Signals that a Export Project Plan Service operation has failed.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportMSPServiceException extends Exception {

	private static final long serialVersionUID = -6103689868572480359L;

	/**
	 * The root cause of the exception.
	 */
	protected Throwable rootCause;

	/**
	 * The error message associated with the exception.
	 */
	protected String errorMsg;

	/**
	 * Creates a new instance.
	 */
	public ExportMSPServiceException() {
		super();
	}

	/**
	 * Creates a new instance given the root cause of the exception.
	 * 
	 * @param rootCause
	 *            the root cause of the exception
	 */
	public ExportMSPServiceException(Throwable rootCause) {
		this(rootCause, rootCause.getMessage());
	}

	/**
	 * Creates a new instance given the error message.
	 * 
	 * @param errorMsg
	 *            the error message
	 */
	public ExportMSPServiceException(String errorMsg) {
		this(null, errorMsg);
	}

	/**
	 * Creates a new instance given the root cause of the exception and the
	 * error message.
	 * 
	 * @param rootCause
	 *            the root cause of the exception
	 * @param errorMsg
	 *            the error message
	 */
	public ExportMSPServiceException(Throwable rootCause, String errorMsg) {
		super();
		this.rootCause = rootCause;
		this.errorMsg = errorMsg;
	}

	/**
	 * Returns the root cause of the exception.
	 * 
	 * @return the root cause of the exception
	 */
	public Throwable getRootCause() {
		return rootCause;
	}

}