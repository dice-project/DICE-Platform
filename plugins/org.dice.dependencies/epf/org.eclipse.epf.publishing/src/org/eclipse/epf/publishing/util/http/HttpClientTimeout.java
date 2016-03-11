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

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.SocketException;
import java.net.URL;

import sun.net.www.http.HttpClient;
//import sun.net.*;
//import sun.net.www.*;
class HttpClientTimeout extends HttpClient
{
	private int timeout = 0 ;

    HttpClientTimeout(URL url, String proxy, int proxyPort, int timeout) throws IOException
	{
		super(url, proxy, proxyPort);
		_setTimeout(timeout);

	}

    HttpClientTimeout(URL url, int timeout) throws IOException
	{
		super(url, (String)null, -1);
		setConnectTimeout(timeout);
    }

	private void _setTimeout(int i)
    	throws SocketException
	{
    	this.timeout = i;
    	serverSocket.setSoTimeout(i) ;
 	}

    /* This class has no public constructor for HTTP.  This method is used to
     * get an HttpClient to the specifed URL.  If there's currently an
     * active HttpClient to that server/port, you'll get that one.
	 *
	 * no longer syncrhonized -- it slows things down too much
	 * synchronize at a higher level
     */
    static HttpClientTimeout GetNew(URL url, int timeout)
    throws IOException {
		/* see if one's already around */
		HttpClientTimeout ret = (HttpClientTimeout) kac.get(url, null);
		if (ret == null) {
	    	ret = new HttpClientTimeout (url, timeout);  // CTOR called openServer()
		} else {
	    	ret.url = url;
		}
		// don't know if we're keeping alive until we parse the headers
		// for now, keepingAlive is false
		return ret;
    }

    public void openServer(String s, int i)
        throws IOException
    {
        serverSocket = doConnect(s, i);
        serverOutput = new PrintStream(new BufferedOutputStream(serverSocket.getOutputStream()));
        serverSocket.setTcpNoDelay(true);
        serverSocket.setSoTimeout(timeout);
    }

}
