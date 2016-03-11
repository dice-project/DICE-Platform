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
package org.eclipse.epf.dataexchange.util;

import java.io.PrintStream;

public class DefaultLogger implements ILogger {

	protected PrintStream info = System.out;
	protected PrintStream warning = System.out;
	protected PrintStream error = System.err;
	public void logError(String message, Throwable th) {
		error.println(message);
		if ( th != null ) {
			th.printStackTrace(error);
		}	
	}
	public void logWarning(String message) {
		warning.println(message);
	}
	public void logMessage(String message) {
		info.println(message);
	}

	public void dispose() {
		
	}
}
