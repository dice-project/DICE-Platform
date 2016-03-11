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

import java.io.IOException;
import java.net.URL;

class HttpTimeoutHandler extends sun.net.www.protocol.http.Handler implements TimeoutHandler
{
	private int timeout = 0;

	HttpTimeoutHandler() {
		super();
	}

	HttpTimeoutHandler(int timeout) {
		this();
		this.timeout = timeout;
	}

    protected java.net.URLConnection openConnection(URL u) throws IOException {
		return new HttpURLConnectionTimeout(u, this, timeout);
    }

    public void setTimeout(int timeout) {
    	this.timeout = timeout;
    }

    String getProxy() { return proxy; }		// breaking encapsulation
    int getProxyPort() { return proxyPort; }    // breaking encapsulation
}

