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

import java.util.Properties;
import java.util.Vector;
/**
 * @author Phong Nguyen Le 09/21/2001
 */
public class HttpResponse {
	static final String SESSION_COOKIE_NAME = "SMSESSION"; //$NON-NLS-1$

	static class Cookie implements Cloneable {
		private String name;
		private String value;
		private String domain;
		private String path;

		Cookie(String name, String value) {
			this.name = name;
			this.value = value;
		}

		String getName() {
			return name;
		}

		String getValue() {
			return value;
		}

		void setValue(String value) {
			this.value = value;
		}

		String getDomain() {
			return domain;
		}

		void setDomain(String domain) {
			this.domain = domain;
		}

		String getPath() {
			return path;
		}

		void setPath(String path) {
			this.path = path;
		}

		public Object clone() {
	    	try {
	        	return super.clone();
	    	}
	    	catch(CloneNotSupportedException e) {
	        	return null;
	    	}
		}

		public String toString() {
			return getClass().getName()+"(name="+name+", value="+value+", domain="+domain+", path="+path+")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}
	}
		private int status;
		private Properties headers;
		private Vector cookies;

		private String data;

		HttpResponse(int status, Properties headers, Vector cookies, String data) {
			this.status = status;
			this.headers = headers;
			this.cookies = cookies;
			this.data = data;
		}

		int getStatus() {
			return status;
		}

		Properties getHeaders() {
			return headers;
		}

		Vector getCookies() {
			return cookies;
		}

		String getData() {
			return data;
		}

		public String toString() {
			return getClass().getName() + "(status="+status+", headers="+headers+", cookies="+cookies+", data="+data+")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		}
}
