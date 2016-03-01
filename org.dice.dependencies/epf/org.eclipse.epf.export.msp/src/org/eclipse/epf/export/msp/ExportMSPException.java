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
 * Signals that an Export Microsoft Project operation has failed.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportMSPException extends ExportMSPServiceException {

	private static final long serialVersionUID = -923189700561806262L;

	/**
	 * Creates a new instance.
	 */
	public ExportMSPException() {
		super();
	}

	/**
	 * Creates a new instance given the root cause of the exception.
	 * 
	 * @param rootCause
	 *            the root cause of the exception
	 */
	public ExportMSPException(Throwable rootCause) {
		super(rootCause);
	}

	/**
	 * Creates a new instance given the error message.
	 * 
	 * @param errorMsg
	 *            the error message
	 */
	public ExportMSPException(String errorMsg) {
		super(errorMsg);
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
	public ExportMSPException(Throwable rootCause, String errorMsg) {
		super(rootCause, errorMsg);
	}

}