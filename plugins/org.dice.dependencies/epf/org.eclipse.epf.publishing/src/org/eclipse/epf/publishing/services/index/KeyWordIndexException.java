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
package org.eclipse.epf.publishing.services.index;

/**
 *
 * This exception is used by all classes that are involved with the keywordindex
 *
 */


class KeyWordIndexException extends Exception
{
	private String name;
	private String reason;

	//-------------------------------------------------------------------------------
	// Class Constructor.
	KeyWordIndexException(String exceptionName, String exceptionReason)
	{
		super( exceptionReason );
		name	= exceptionName;
		reason	= exceptionReason;
	}

	//-------------------------------------------------------------------------------
	// Return the name of the exception.
	String getExceptionName()
	{
		return name;
	}

	//-------------------------------------------------------------------------------
	// Return the reason for the exception.
	String getExceptionReason()
	{
		return reason;
	}

	public String toString() {

		return name + ":" + reason; //$NON-NLS-1$

	}
}
