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
import java.io.PrintStream;
import java.net.URL;

import sun.net.www.http.HttpClient;

class HttpURLConnectionTimeout extends sun.net.www.protocol.http.HttpURLConnection
{
	private int timeout;
	private HttpTimeoutHandler timeoutHandler;

  	HttpURLConnectionTimeout(URL u, HttpTimeoutHandler handler, int timeout) throws IOException
	{
    	super(u, handler);
		this.timeout = timeout;
		timeoutHandler = handler;
	}

	HttpURLConnectionTimeout(URL u,  String host, int port, int timeout) throws IOException
	{
    	super(u, host, port);
    	this.timeout = timeout;
	}

    public void connect() throws IOException {
		if (connected) {
	    	return;
		}
		try {
	    	if ("http".equals(url.getProtocol()) /* && !failedOnce <- PRIVATE */ ) { //$NON-NLS-1$
				// for safety's sake, as reported by KLGroup
				synchronized (url) {
					http = HttpClientTimeout.GetNew(url, timeout);
				}
	    	}
	    	else {
				// make sure to construct new connection if first
				// attempt failed
				http = new HttpClientTimeout(url, timeoutHandler.getProxy(), timeoutHandler.getProxyPort(), timeout);
	    	}
	    	ps = (PrintStream)http.getOutputStream();
		} catch (IOException e) {
	    	throw e;
		}
		// this was missing from the original version
		connected = true;
	}

    protected HttpClient getProxiedClient(URL url, String s, int i)
        throws IOException
    {
        return (HttpClient)new HttpClientTimeout(url, s, i, timeout);
    }



    /**
     * Create a new HttpClient object, bypassing the cache of
     * HTTP client objects/connections.
     *
     * @param url	the URL being accessed
     */
    protected HttpClient getNewClient(URL url)
    throws IOException {
		return (HttpClient)(new HttpClientTimeout (url, (String)null, -1, timeout));
    }
}
