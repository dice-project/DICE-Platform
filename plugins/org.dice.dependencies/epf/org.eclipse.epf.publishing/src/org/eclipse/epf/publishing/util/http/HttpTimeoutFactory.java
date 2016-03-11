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
package org.eclipse.epf.publishing.util.http;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

class HttpTimeoutFactory implements URLStreamHandlerFactory
{
	private int timeout;
	HttpTimeoutFactory(int timeout) { this.timeout = timeout; }
	public URLStreamHandler createURLStreamHandler(String str)
	{
		HttpTimeoutHandler handler = new HttpTimeoutHandler();
		handler.setTimeout(timeout);
		return handler;
	}
}

