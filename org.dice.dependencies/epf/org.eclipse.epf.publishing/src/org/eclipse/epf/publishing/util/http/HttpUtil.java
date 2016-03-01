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

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLStreamHandler;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.epf.publishing.PublishingResources;
import org.eclipse.osgi.util.NLS;

import com.ibm.icu.util.StringTokenizer;


/**
 * @author Phong Nguyen Le 09/21/2001
 */
public class HttpUtil {
	private final static boolean DEBUG = false;
	/**
	 * Do a GET using low level socket to have control over timeout
	 *
	 * @return response
	 * @exception <li> java.net.ConnectException could not connect to host:port
	 *            <li> java.io.InterruptedIOException timeout exceeded
	 *            <li> java.io.IOException I/O error
	 *            <li> CataHttpException invalid response from server
	 */
	static HttpResponse doGet(Socket sock, String url, Properties headers) throws Exception
	{
		int respStatus;
		Properties respHeaders = new Properties();
		Vector respCookies = new Vector();
		String resp = null;
		DataOutputStream out = null;
		DataInputStream in = null;
		try {
			String host = sock.getInetAddress().getHostName();
			int port = sock.getPort();
			out = new DataOutputStream(sock.getOutputStream());
			in = new DataInputStream(new BufferedInputStream(sock.getInputStream()));
			out.writeBytes("GET " + url + " HTTP/1.1\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
			out.writeBytes("Host: " + host + ":" + port + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			out.writeBytes("User-Agent: Mozilla/4.0\r\n"); //$NON-NLS-1$
			out.writeBytes("Content-Length: 0\r\n"); //$NON-NLS-1$
			// send addional headers if any
			if(headers != null && headers.size() > 0) {
				Enumeration names = headers.keys();
				while(names.hasMoreElements()) {
					String name = (String)names.nextElement();
					if(name.equalsIgnoreCase("Host") || name.equalsIgnoreCase("User-Agent") || name.equalsIgnoreCase("Content-Length")) continue; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					out.writeBytes(name + ": " + headers.getProperty(name) + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			out.writeBytes("\r\n"); //$NON-NLS-1$
			out.flush();
			String line;
			boolean endFound = false;
			int contentLen = -1;
			final String contentLenHeader = "content-length: "; //$NON-NLS-1$
			int contentLenHeaderLen = contentLenHeader.length();
			String httpResp = ""; //$NON-NLS-1$

			// read the first line
			line = in.readLine();
			//logger.debug("line="+line);
			if(line == null) {
				throw new Exception(PublishingResources.serverError_msg); 
			}
			httpResp += line + "\n"; //$NON-NLS-1$
			StringTokenizer tokens = new StringTokenizer(line);
			try {
				// skip the first token which is "HTTP/1.1" or "HTTP/1.0"
				tokens.nextToken();
				respStatus = Integer.parseInt(tokens.nextToken());
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new Exception(NLS.bind(PublishingResources.invalidHttpResponseError_msg, (new Object[]{host, Integer.toString(port), line}))); 
			}

			// read the headers
			while((line = in.readLine()) != null) {
				//logger.debug("line="+line);
				httpResp += line + "\n"; //$NON-NLS-1$
				if(line.length() == 0) {
					endFound = true;
					break;
				}
				int id = line.indexOf(": "); //$NON-NLS-1$
				if(id > 0) {
					String header = line.substring(0, id).toLowerCase();
					String value = ""; //$NON-NLS-1$
					if(line.length() > id + 2) {
						value = line.substring(id + 2);
					}

					if(header.equalsIgnoreCase("set-cookie")) { //$NON-NLS-1$
						HttpResponse.Cookie cookie = parseCookie(value);
						if(cookie != null) {
							respCookies.addElement(cookie);
						}
					}
					else {
						respHeaders.put(header, value);
					}

					//respHeaders.put(header, value);
				}
			}
			String contentLenStr = respHeaders.getProperty("content-length"); //$NON-NLS-1$
			if(contentLenStr == null) {
				contentLen = -1;
			}
			else {
				contentLen = Integer.parseInt(contentLenStr);
			}
			if(DEBUG) {
			System.out.println("++++BEGIN HEADERS++++"); //$NON-NLS-1$
			System.out.println(httpResp);
			System.out.println("++++END HEADERS++++"); //$NON-NLS-1$
			}
			if(!endFound) {
				throw new Exception(NLS.bind(PublishingResources.invalidHttpResponseError_msg, (new Object[]{host, Integer.toString(port), httpResp}))); 
			}
			//System.out.println("contentLen="+contentLen);
			if(contentLen > 0) {
				/*
				byte[] buff = new byte[contentLen];
				in.readFully(buff);
				resp = new String(buff);
				*/
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buff = new byte[Math.min(2048, contentLen)];
				int num;
				while((num = in.read(buff)) != -1) {
					//System.out.println("num="+num);
					int n = contentLen - bos.size();
					if(n <= num) {
						bos.write(buff, 0, n);
						break;
					}
					bos.write(buff, 0, num);
				}
				resp = bos.toString();
			}
			else if(contentLen < 0) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buff = new byte[2048];
				int num;
				while((num = in.read(buff)) != -1) {
					//System.out.println("num="+num);
					bos.write(buff, 0, num);
				}
				resp = bos.toString();
			}
		}
		finally {
			try { in.close(); } catch(Exception e) {  }
			try { out.close(); } catch(Exception e) {  }
		}
		return new HttpResponse(respStatus, respHeaders, respCookies, resp);
	}


	/**
	 * Do a GET using low level socket to have control over timeout
	 *
	 * @return response
	 * @exception <li> java.net.ConnectException could not connect to host:port
	 *            <li> java.io.InterruptedIOException timeout exceeded
	 *            <li> java.io.IOException I/O error
	 *            <li> CataHttpException invalid response from server
	 */
	public static HttpResponse doGet(String host, int port, String url, int timeout, Properties headers) throws Exception
	{
		Socket sock = null;
		try {
			sock = new Socket(host, port);
			sock.setSoTimeout(timeout);
			return doGet(sock, url, headers);
		}
		finally {
			try { sock.close(); } catch(Exception e) {}
		}
	}

	private static Map handlerMap = new HashMap();
	private static URLStreamHandler getHandler(int timeout) {
		Integer key = new Integer(timeout);
		URLStreamHandler handler = (URLStreamHandler)handlerMap.get(key);
		if(handler == null) {
			synchronized(handlerMap) {
				if((handler = (URLStreamHandler)handlerMap.get(key)) == null) {
					handler = new HttpTimeoutHandler(timeout);
					handlerMap.put(key, handler);
				}
			}
		}
		return handler;
	}

	/**
	 * Do a GET
	 *
	 * @return response
	 * @exception <li> java.net.ConnectException could not connect to host:port
	 *            <li> java.io.InterruptedIOException timeout exceeded
	 *            <li> java.io.IOException I/O error
	 */
	public static HttpResponse doGet(String url, Properties headers, int timeout)
			throws Exception {
		return doGet(new URL((URL) null, url, getHandler(timeout)), headers);
	}
	
	/**
	 * Do a GET
	 *
	 * @return response
	 * @exception <li> java.net.ConnectException could not connect to host:port
	 *            <li> java.io.InterruptedIOException timeout exceeded
	 *            <li> java.io.IOException I/O error
	 */
	public static HttpResponse doGet(String url, Properties headers,
			int timeout, IProxyData proxy) throws Exception {
		return doGet(new URL((URL) null, url, getHandler(timeout)), headers,
				proxy);
	}

	public static class ProxyAuthenticator extends Authenticator {
		private String username, password;

		public ProxyAuthenticator(String username, String password) {
			this.username = username;
			this.password = password;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password.toCharArray());
		}
	}
	
	/**
	 * Do a GET
	 * 
	 * @return response
	 * @exception
	 *         <li> java.net.ConnectException could not connect to host:port
	 *         <li> java.io.InterruptedIOException timeout exceeded
	 *         <li> java.io.IOException I/O error
	 */
	static HttpResponse doGet(URL url, Properties headers) throws Exception {
		return doGet(url, headers, null);
	}
	
	/**
	 * Do a GET
	 *
	 * @return response
	 * @exception <li> java.net.ConnectException could not connect to host:port(or a proxy)
	 *            <li> java.io.InterruptedIOException timeout exceeded
	 *            <li> java.io.IOException I/O error
	 */
	static HttpResponse doGet(URL url, Properties headers, IProxyData proxy)
			throws Exception {
		HttpURLConnection conn = null;
		/***********************************************************************
		 * For Using Proxy
		 **********************************************************************/
		if (proxy != null) {
			Proxy.Type proxyType = null;
			if (proxy.getType().equals(IProxyData.HTTP_PROXY_TYPE)) {
				proxyType = Proxy.Type.HTTP;
			} else if (proxy.getType().equals(IProxyData.HTTPS_PROXY_TYPE)) {
				proxyType = Proxy.Type.HTTP;
			} else if (proxy.getType().equals(IProxyData.SOCKS_PROXY_TYPE)) {
				proxyType = Proxy.Type.SOCKS;
			}

			Proxy proxyServer = new Proxy(proxyType, new InetSocketAddress(
					proxy.getHost(), proxy.getPort()));
			if (proxy.isRequiresAuthentication()) {
				Authenticator.setDefault(new ProxyAuthenticator(proxy
						.getUserId(), proxy.getPassword()));
			} else {
				Authenticator.setDefault(null);
			}
			conn = (HttpURLConnection) url.openConnection(proxyServer);
		} else {
			conn = (HttpURLConnection) url.openConnection();
		}
		
		Properties respHeaders = new Properties();
		Vector respCookies = new Vector();
		HttpURLConnection.setFollowRedirects(false);
		//conn.setInstanceFollowRedirects(false); //JDK1.3
		conn.setRequestMethod("GET"); //$NON-NLS-1$
		conn.setUseCaches(false);
		// send addional headers if any
		if(headers != null && headers.size() > 0) {
			Enumeration names = headers.keys();
			while(names.hasMoreElements()) {
				String name = (String)names.nextElement();
				if(name.equalsIgnoreCase("Host") || name.equalsIgnoreCase("User-Agent") || name.equalsIgnoreCase("Content-Length")) continue; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				conn.setRequestProperty(name, headers.getProperty(name));
			}
		}
		int respStatus = conn.getResponseCode();
		String name;
		for(int i = 1; (name = conn.getHeaderFieldKey(i)) != null; i++) {
			String value = conn.getHeaderField(name);
			if(name.equalsIgnoreCase("set-cookie")) { //$NON-NLS-1$
				HttpResponse.Cookie cookie = parseCookie(value);
				if(cookie != null) {
					respCookies.addElement(cookie);
				}
			}
			else {
				respHeaders.put(name.toLowerCase(), value);
			}
		}
		int contentLen = conn.getContentLength();
		BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
		String resp = null;
		if(contentLen > 0) {
			/*
			byte[] buff = new byte[contentLen];
			in.readFully(buff);
			resp = new String(buff);
			*/
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buff = new byte[Math.min(2048, contentLen)];
			int num;
			while((num = in.read(buff)) != -1) {
				//System.out.println("num="+num);
				int n = contentLen - bos.size();
				if(n <= num) {
					bos.write(buff, 0, n);
					break;
				}
				bos.write(buff, 0, num);
			}
			resp = bos.toString();
		}
		else if(contentLen < 0) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buff = new byte[2048];
			int num;
			while((num = in.read(buff)) != -1) {
				//System.out.println("num="+num);
				bos.write(buff, 0, num);
			}
			resp = bos.toString();
		}
//		System.out.println("Content  :" +resp);
		return new HttpResponse(respStatus, respHeaders, respCookies, resp);
	}

	/**
	 * Do a POST
	 *
	 * @return response
	 * @exception <li> java.net.ConnectException could not connect to host:port
	 *            <li> java.io.InterruptedIOException timeout exceeded
	 *            <li> java.io.IOException I/O error
	 */
	static HttpResponse doPost(String url, Properties headers, Properties data, int timeout) throws Exception
	{
		return doPost(new URL((URL)null, url, getHandler(timeout)), headers, data);
	}

	/**
	 * Do a POST
	 *
	 * @return response
	 * @exception <li> java.net.ConnectException could not connect to host:port
	 *            <li> java.io.InterruptedIOException timeout exceeded
	 *            <li> java.io.IOException I/O error
	 */
	static HttpResponse doPost(URL url, Properties headers, Properties data) throws Exception
	{
		Properties respHeaders = new Properties();
		Vector respCookies = new Vector();
		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		//conn.setInstanceFollowRedirects(false); //JDK1.3
		conn.setRequestMethod("POST"); //$NON-NLS-1$
		conn.setUseCaches(false);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		// send addional headers if any
		if(headers != null && headers.size() > 0) {
			Enumeration names = headers.keys();
			while(names.hasMoreElements()) {
				String name = (String)names.nextElement();
				if(name.equalsIgnoreCase("Host") || name.equalsIgnoreCase("User-Agent") || name.equalsIgnoreCase("Content-Length")) continue; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				conn.setRequestProperty(name, headers.getProperty(name));
			}
		}
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());

		String content = ""; //$NON-NLS-1$
		Enumeration enu = data.propertyNames();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			content += (name + "=" + URLEncoder.encode(data.getProperty(name))); //$NON-NLS-1$
			if(enu.hasMoreElements()) {
				content += "&"; //$NON-NLS-1$
			}
		}
		if(content.length() > 0) {
			out.writeBytes(content);
		}

		int respStatus = conn.getResponseCode();
		String name;
		for(int i = 1; (name = conn.getHeaderFieldKey(i)) != null; i++) {
			String value = conn.getHeaderField(name);
			if(name.equalsIgnoreCase("set-cookie")) { //$NON-NLS-1$
				HttpResponse.Cookie cookie = parseCookie(value);
				if(cookie != null) {
					respCookies.addElement(cookie);
				}
			}
			else {
				respHeaders.put(name.toLowerCase(), value);
			}
		}
		int contentLen = conn.getContentLength();
		BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
		String resp = null;
		if(contentLen > 0) {
			/*
			byte[] buff = new byte[contentLen];
			in.readFully(buff);
			resp = new String(buff);
			*/
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buff = new byte[Math.min(2048, contentLen)];
			int num;
			while((num = in.read(buff)) != -1) {
				//System.out.println("num="+num);
				int n = contentLen - bos.size();
				if(n <= num) {
					bos.write(buff, 0, n);
					break;
				}
				bos.write(buff, 0, num);
			}
			resp = bos.toString();
		}
		else if(contentLen < 0) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buff = new byte[2048];
			int num;
			while((num = in.read(buff)) != -1) {
				//System.out.println("num="+num);
				bos.write(buff, 0, num);
			}
			resp = bos.toString();
		}
		return new HttpResponse(respStatus, respHeaders, respCookies, resp);
	}

	/**
	 * Do a POST using low level socket to have control over timeout
	 *
	 * @return response
	 * @exception <li> java.net.ConnectException could not connect to host:port
	 *            <li> java.io.InterruptedIOException timeout exceeded
	 *            <li> java.io.IOException I/O error
	 *            <li> CataHttpException invalid response from server
	 */
	static HttpResponse doPost(Socket sock, String url, Properties headers, Properties data) throws Exception
	{
		int respStatus;
		Properties respHeaders = new Properties();
		Vector respCookies = new Vector();
		String resp = null;
		DataOutputStream out = null;
		DataInputStream in = null;
		try {
			String host = sock.getInetAddress().getHostName();
			int port = sock.getPort();
			out = new DataOutputStream(sock.getOutputStream());
			in = new DataInputStream(new BufferedInputStream(sock.getInputStream()));
			String content = ""; //$NON-NLS-1$
			Enumeration enu = data.propertyNames();
			while(enu.hasMoreElements()) {
				String name = (String)enu.nextElement();
				content += (name + "=" + URLEncoder.encode(data.getProperty(name))); //$NON-NLS-1$
				if(enu.hasMoreElements()) {
					content += "&"; //$NON-NLS-1$
				}
			}
			//logger.debug("postdata="+content);
			out.writeBytes("POST " + url + " HTTP/1.1\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
			out.writeBytes("Host: " + host + ":" + port + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			out.writeBytes("User-Agent: Mozilla/4.0\r\n"); //$NON-NLS-1$
			//out.writeBytes("Accept: text/html");
			out.writeBytes("Content-Type: application/x-www-form-urlencoded\r\n"); //$NON-NLS-1$
			out.writeBytes("Content-Length: " + content.length() + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
			// send addional headers if any
			if(headers != null && headers.size() > 0) {
				Enumeration names = headers.keys();
				while(names.hasMoreElements()) {
					String name = (String)names.nextElement();
					if(name.equalsIgnoreCase("Host") || name.equalsIgnoreCase("User-Agent") || name.equalsIgnoreCase("Content-Length") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						|| name.equalsIgnoreCase("Content-Type")) continue; //$NON-NLS-1$
					out.writeBytes(name + ": " + headers.getProperty(name) + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			out.writeBytes("\r\n"); //$NON-NLS-1$
			if(content.length() > 0) {
				out.writeBytes(content);
			}
			out.flush();
			String line;
			boolean endFound = false;
			int contentLen = -1;
			final String contentLenHeader = "content-length: "; //$NON-NLS-1$
			int contentLenHeaderLen = contentLenHeader.length();
			String httpResp = ""; //$NON-NLS-1$

			// read the first line
			line = in.readLine();
			//logger.debug("line="+line);
			if(line == null) {
				throw new Exception(PublishingResources.serverError_msg); 
			}
			httpResp += line + "\n"; //$NON-NLS-1$
			StringTokenizer tokens = new StringTokenizer(line);
			try {
				// skip the first token which is "HTTP/1.1" or "HTTP/1.0"
				tokens.nextToken();
				respStatus = Integer.parseInt(tokens.nextToken());
			}
			catch(Exception e) {
				e.printStackTrace();
				throw new Exception(NLS.bind(PublishingResources.invalidHttpResponseError_msg, (new Object[]{host, Integer.toString(port), line}))); 
			}

			// read the headers
			while((line = in.readLine()) != null) {
				//logger.debug("line="+line);
				httpResp += line + "\n"; //$NON-NLS-1$
				if(line.length() == 0) {
					endFound = true;
					break;
				}
				int id = line.indexOf(": "); //$NON-NLS-1$
				if(id > 0) {
					String header = line.substring(0, id).toLowerCase();
					String value = ""; //$NON-NLS-1$
					if(line.length() > id + 2) {
						value = line.substring(id + 2);
					}

					if(header.equals("set-cookie")) { //$NON-NLS-1$
						HttpResponse.Cookie cookie = parseCookie(value);
						if(cookie != null) {
							respCookies.addElement(cookie);
						}
					}
					else {
						respHeaders.put(header, value);
					}

					//respHeaders.put(header, value);
				}
			}
			String contentLenStr = respHeaders.getProperty("content-length"); //$NON-NLS-1$
			if(contentLenStr == null) {
				contentLen = 0;
			}
			else {
				contentLen = Integer.parseInt(contentLenStr);
			}
			if(DEBUG) {
			System.out.println("++++BEGIN HEADERS++++"); //$NON-NLS-1$
			System.out.println(httpResp);
			System.out.println("++++END HEADERS++++"); //$NON-NLS-1$
			}
			if(!endFound || contentLen == -1) {
				throw new Exception(NLS.bind(PublishingResources.invalidHttpResponseError_msg, (new Object[]{host, Integer.toString(port), httpResp}))); 
			}
			if(contentLen > 0) {
				byte[] buff = new byte[contentLen];
				in.readFully(buff);
				resp = new String(buff);
			}
			else if(contentLenStr == null) {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				byte[] buff = new byte[2048];
				int num;
				while((num = in.read(buff)) != -1) {
					bos.write(buff, 0, num);
				}
				resp = bos.toString();
			}
		}
		finally {
			try { in.close(); } catch(Exception e) {}
			try { out.close(); } catch(Exception e) {}
		}
		return new HttpResponse(respStatus, respHeaders, respCookies, resp);
	}

	/**
	 * Do a POST using low level socket to have control over timeout
	 *
	 * @return response
	 * @exception <li> java.net.ConnectException could not connect to host:port
	 *            <li> java.io.InterruptedIOException timeout exceeded
	 *            <li> java.io.IOException I/O error
	 *            <li> CataHttpException invalid response from server
	 */
	public static HttpResponse doPost(String host, int port, String url, int timeout,
		Properties headers, Properties data) throws Exception
	{
		Socket sock = null;
		try {
			sock = new Socket(host, port);
			sock.setSoTimeout(timeout);
			return doPost(sock, url, headers, data);
		}
		finally {
			try { sock.close(); } catch(Exception e) {}
		}
	}

	/**
	 * Parses a query string and URL-decodes the parameter values
	 */
	public static Map parseQueryString(String str) throws Exception {
		StringTokenizer tokens = new StringTokenizer(str, "&"); //$NON-NLS-1$
		Map map = new HashMap();
		int maxId = str.length() - 1;
		while(tokens.hasMoreTokens()) {
			String token = tokens.nextToken();
			int id = token.indexOf('=');
			if(id == -1) {
				continue;
			}
			else if(id == 0 || id == maxId) {
				map.put(token, null);
			}
			else {
				map.put(token.substring(0, id), urlDecode(token.substring(id + 1)));
			}
		}
		return map;
	}

	/**
	 * Parses the cookie from the value of response header Set-Cookie
	 */
    public static HttpResponse.Cookie parseCookie(String str) {
    	//logger.debug(str);
    	int id = str.indexOf('=');
    	if(id <= 0) return null;
    	String name = str.substring(0, id);
    	int fromId =name.length() + 1;
    	int toId = str.indexOf(";"); //$NON-NLS-1$
    	String value;
    	String theRest = null;
    	if(toId == -1) {
    		value = str.substring(fromId);
    	}
    	else {
    		value = str.substring(fromId, toId);
    		if(toId < str.length() - 1) {
    			theRest = str.substring(toId + 1);
    		}
    	}
    	HttpResponse.Cookie cookie = new HttpResponse.Cookie(name, value);
    	if(theRest != null) {
    		StringTokenizer tokens = new StringTokenizer(theRest, ";"); //$NON-NLS-1$
    		while(tokens.hasMoreTokens()) {
    			String token = tokens.nextToken().trim();
    			id = token.indexOf('=');
    			if(id <= 0) continue;
    			name = token.substring(0, id);
    			if(id == token.length() - 1) {
    				value = ""; //$NON-NLS-1$
    			}
    			else {
    				value = token.substring(id + 1);
    			}
    			if(name.equalsIgnoreCase("domain")) { //$NON-NLS-1$
    				cookie.setDomain(value);
    			}
    			else if(name.equalsIgnoreCase("path")) { //$NON-NLS-1$
    				cookie.setPath(value);
				}
			}
        }

    	return cookie;
    }

    /**
     * Parses the cookies from the value of request header Cookie
     * @return an enumeration of HttpResponse.Cookie objects
     */
    public static Enumeration parseCookies(String str) {
    	StringTokenizer tokens = new StringTokenizer(str, ";"); //$NON-NLS-1$
    	Vector cookies = new Vector();
    	while(tokens.hasMoreTokens()) {
    		String token = tokens.nextToken().trim();
    		int id = token.indexOf('=');
    		if(id == -1) continue;
    		String name = token.substring(0, id);
    		String value = ""; //$NON-NLS-1$
    		if(id < token.length() - 1) {
    			value = token.substring(id + 1);
    		}
    		if(DEBUG) System.out.println(name+"="+value); //$NON-NLS-1$
    		cookies.addElement(new HttpResponse.Cookie(name, value));
    	}
    	return cookies.elements();
    }

    public static String toCookieString(Enumeration cookies) {
		StringBuffer strBuf = new StringBuffer();
		while(cookies.hasMoreElements()) {
			HttpResponse.Cookie cookie = (HttpResponse.Cookie)cookies.nextElement();
			strBuf.append(cookie.getName()).append('=').append(cookie.getValue()).append("; "); //$NON-NLS-1$
		}
		return strBuf.toString();
    }

	/**
	* This utility method is for converting from
	* a MIME format called "<code>x-www-form-urlencoded</code>"
	* to a <code>String</code>
	* <p>
	* To convert to a <code>String</code>, each character is examined in turn:
	* <ul>
	* <li>The ASCII characters '<code>a</code>' through '<code>z</code>',
	* '<code>A</code>' through '<code>Z</code>', and '<code>0</code>'
	* through '<code>9</code>' remain the same.
	* <li>The plus sign '<code>+</code>'is converted into a
	* space character '<code>&nbsp;</code>'.
	* <li>The remaining characters are represented by 3-character
	* strings which begin with the percent sign,
	* "<code>%<i>xy</i></code>", where <i>xy</i> is the two-digit
	* hexadecimal representation of the lower 8-bits of the character.
	* </ul>
	*
	*/
    public static String urlDecode(String s) throws Exception {
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '+':
                    sb.append(' ');
                    break;
                case '%':
                    try {
                        sb.append((char)Integer.parseInt(
                                        s.substring(i+1,i+3),16));
                    }
                    catch (NumberFormatException e) {
                        throw new IllegalArgumentException();
                    }
                    i += 2;
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        // Undo conversion to external encoding
        String result = sb.toString();
        byte[] inputBytes = result.getBytes("8859_1"); //$NON-NLS-1$
        return new String(inputBytes);
    }

    public static void main(String[] args) throws Exception {
    	//System.out.println(doGet(args[0], null, 0));
    	String url = args[0];
    	String uid = args[1];
    	String email = args[2];
    	Properties data = new Properties();
    	data.put("uid", uid); //$NON-NLS-1$
    	data.put("email", email); //$NON-NLS-1$
    	System.out.println(doPost(url, null, data, 0));
	}
}

