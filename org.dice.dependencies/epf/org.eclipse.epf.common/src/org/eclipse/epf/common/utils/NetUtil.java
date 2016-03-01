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
package org.eclipse.epf.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Implements a utility class for managing URLs and URIs.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class NetUtil {

	/**
	 * FILE scheme.
	 */
	public final static String FILE_SCHEME = "file"; //$NON-NLS-1$

	/**
	 * File URI prefix.
	 */
	public final static String FILE_URI_PREFIX = FILE_SCHEME + ":/"; //$NON-NLS-1$

	/**
	 * File URI prefix size.
	 */
	public final static int FILE_URI_PREFIX_LENGTH = FILE_URI_PREFIX.length();

	/**
	 * HTTP scheme.
	 */
	public final static String HTTP_SCHEME = "http"; //$NON-NLS-1$

	/**
	 * HTTP URI prefix.
	 */
	public final static String HTTP_URI_PREFIX = HTTP_SCHEME + "://"; //$NON-NLS-1$

	/**
	 * HTTP URI prefix size.
	 */
	public final static int HTTP_URI_PREFIX_LENGTH = HTTP_URI_PREFIX.length();

	public static final String FILE_PREFIX_2 = "file://"; //$NON-NLS-1$

	public static final String FILE_PREFIX_3 = "file:///"; //$NON-NLS-1$

	/**
	 * A table of hex values.
	 */
	private final static String[] HEX_VALUES = {
			"%00", "%01", "%02", "%03", "%04", "%05", "%06", "%07", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%08", "%09", "%0A", "%0B", "%0C", "%0D", "%0E", "%0F", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%10", "%11", "%12", "%13", "%14", "%15", "%16", "%17", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%18", "%19", "%1A", "%1B", "%1C", "%1D", "%1E", "%1F", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%20", "%21", "%22", "%23", "%24", "%25", "%26", "%27", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%28", "%29", "%2A", "%2B", "%2C", "%2D", "%2E", "%2F", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%30", "%31", "%32", "%33", "%34", "%35", "%36", "%37", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%38", "%39", "%3A", "%3B", "%3C", "%3D", "%3E", "%3F", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%40", "%41", "%42", "%43", "%44", "%45", "%46", "%47", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%48", "%49", "%4A", "%4B", "%4C", "%4D", "%4E", "%4F", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%50", "%51", "%52", "%53", "%54", "%55", "%56", "%57", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%58", "%59", "%5A", "%5B", "%5C", "%5D", "%5E", "%5F", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%60", "%61", "%62", "%63", "%64", "%65", "%66", "%67", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%68", "%69", "%6A", "%6B", "%6C", "%6D", "%6E", "%6F", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%70", "%71", "%72", "%73", "%74", "%75", "%76", "%77", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%78", "%79", "%7A", "%7B", "%7C", "%7D", "%7E", "%7F", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%80", "%81", "%82", "%83", "%84", "%85", "%86", "%87", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%88", "%89", "%8A", "%8B", "%8C", "%8D", "%8E", "%8F", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%90", "%91", "%92", "%93", "%94", "%95", "%96", "%97", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%98", "%99", "%9A", "%9B", "%9C", "%9D", "%9E", "%9F", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%A0", "%A1", "%A2", "%A3", "%A4", "%A5", "%A6", "%A7", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%A8", "%A9", "%AA", "%AB", "%AC", "%AD", "%AE", "%AF", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%B0", "%B1", "%B2", "%B3", "%B4", "%B5", "%B6", "%B7", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%B8", "%B9", "%BA", "%BB", "%BC", "%BD", "%BE", "%BF", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%C0", "%C1", "%C2", "%C3", "%C4", "%C5", "%C6", "%C7", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%C8", "%C9", "%CA", "%CB", "%CC", "%CD", "%CE", "%CF", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%D0", "%D1", "%D2", "%D3", "%D4", "%D5", "%D6", "%D7", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%D8", "%D9", "%DA", "%DB", "%DC", "%DD", "%DE", "%DF", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%E0", "%E1", "%E2", "%E3", "%E4", "%E5", "%E6", "%E7", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%E8", "%E9", "%EA", "%EB", "%EC", "%ED", "%EE", "%EF", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%F0", "%F1", "%F2", "%F3", "%F4", "%F5", "%F6", "%F7", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
			"%F8", "%F9", "%FA", "%FB", "%FC", "%FD", "%FE", "%FF" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
	};
	
	public static final String RAW_URL_RAW = "raw_URL_raw"; //$NON-NLS-1$

	/**
	 * Private constructor to prevent this class from being instantiated. All
	 * methods in this class should be static.
	 */
	private NetUtil() {
	}

	/**
	 * Returns the URI for the given file.
	 * 
	 * @param file
	 *            The input file.
	 * @return The URI for the given file.
	 * @throws MalformedURLException
	 *             if an error occur while constructing the URI for the given
	 *             file.
	 */
	public static String getUri(File file) throws MalformedURLException {
		String url = file.toURL().toExternalForm();
		StringBuffer strBuf = new StringBuffer();
		int urlLength = url.length();
		for (int i = 0; i < urlLength; i++) {
			char ch = url.charAt(i);
			switch (ch) {
			case ' ':
				strBuf.append("%20"); //$NON-NLS-1$
				break;
			default:
				strBuf.append(ch);
				break;
			}
		}
		return strBuf.toString();
	}

	/**
	 * Resolves the given URI using the given the base URI.
	 * 
	 * @param uri
	 *            The URI to resolve.
	 * @param baseUri
	 *            The base URI.
	 * @return A fully formed URI.
	 */
	public static String resolveUri(String uri, String baseUri) {
		if (uri == null) {
			return null;
		}

		if (uri.startsWith("../")) { //$NON-NLS-1$
			if (baseUri.endsWith("/")) { //$NON-NLS-1$
				baseUri = baseUri.substring(0, baseUri.length() - 1);
			}
			while (uri.startsWith("../")) { //$NON-NLS-1$
				uri = uri.substring(3);
				int index = baseUri.lastIndexOf('/');
				if (index > 0) {
					baseUri = baseUri.substring(0, index);
				}
			}
			uri = "/" + uri; //$NON-NLS-1$
		}

		if (uri.startsWith("/")) { //$NON-NLS-1$
			return baseUri.endsWith("/") //$NON-NLS-1$
					? baseUri + uri.substring(1) : baseUri + uri;
		}

		if (uri.startsWith(FILE_URI_PREFIX) || uri.startsWith(HTTP_URI_PREFIX)) {
			return uri;
		}

		return baseUri.endsWith("/") ? baseUri + uri : baseUri + '/' + uri; //$NON-NLS-1$
	}

	/**
	 * Returns the input stream for the given URI.
	 * 
	 * @param uri
	 *            The source URI.
	 * @return The input stream for the given URI.
	 * @throw MalformedURLException if a given XML document URI is invalid.
	 * @throw IOException if an I/O error occur while accessing the URI.
	 */
	public static InputStream getInputStream(String uri)
			throws MalformedURLException, IOException {
		if (uri == null) {
			return null;
		}

		if (uri.startsWith(HTTP_URI_PREFIX)) {
			URL url = new URL(uri);
			return url.openStream();
		} else if (uri.startsWith(FILE_URI_PREFIX)) {
			uri = uri.substring(FILE_URI_PREFIX_LENGTH);
		}

		return new FileInputStream(NetUtil.decodeUrl(uri, null));
	}

	/**
	 * Returns the Java string represention (encoded in UTF-16) of the given URL
	 * (encoded in the given encoding and ASCII-escaped).
	 * 
	 * @param url
	 *            The URL to decode.
	 * @param encoding
	 *            The encoding of the URL.
	 * @return The Java UTF-16 string respresentation.
	 * @throws IllegalArgumentException
	 *             if the given URL contain improperly escaped characters.
	 */
	public static String decodeUrl(String url, String encoding) {
		if (url == null) {
			return url;
		}
		
		int len = url.length();
		if (len == 0) {
			return url;
		}

		// Unescape the url.
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < len; i++) {
			char ch = url.charAt(i);
			switch (ch) {
			case '+':
				strBuf.append(' ');
				break;
			case '%':
				try {
					strBuf.append((char) Integer.parseInt(url.substring(i + 1,
							i + 3), 16));
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException();
				}
				i += 2;
				break;
			default:
				strBuf.append(ch);
				break;
			}
		}

		// Convert the un-escaped byte values to Java UTF-16 string.
		String result = strBuf.toString();
		if (encoding != null) {
			try {
				byte[] bytes = result.getBytes("8859_1"); //$NON-NLS-1$
				result = new String(bytes, encoding);
			} catch (UnsupportedEncodingException e) {
			}
		}

		return result;
	}

	/**
	 * Returns the ASCII-escaped representation (encoded in the specified
	 * encoding) of the given URL (encoded in UTF-16).
	 * 
	 * @param url
	 *            The URL to encode.
	 * @param encoding
	 *            The encoding of the URL.
	 * @return the ASCII-escaped respresentation.
	 * @throws IllegalArgumentException
	 *             if the given URL contain improperly escaped characters.
	 * @throws UnsupportedEncodingException
	 *             if the given coding is unsupport.
	 */
	public static String encodeUrl(String url, String encoding)
			throws UnsupportedEncodingException {
		if (url == null) {
			return url;
		}		
		int len = url.length();
		if (len == 0) {
			return url;
		}

		StringBuffer result = new StringBuffer();

		byte[] bytes = url.getBytes(encoding);
		for (int i = 0; i < bytes.length; i++) {
			char ch = (char) bytes[i];

			if (ch >= 'a' && ch <= 'z') {
				result.append(ch);
			} else if (ch >= 'A' && ch <= 'Z') {
				result.append(ch);
			} else if (ch >= '0' && ch <= '9') {
				result.append(ch);
			} else {
				switch (ch) {
				case '-':
				case '_':
				case '.':
				case '!':
				case '~':
				case '*':
				case '\'':
				case '(':
				case ')':
					result.append(ch);
					break;
				default:
					result.append(HEX_VALUES[ch & 0xFF]);
					break;
				}
			}
		}

		return result.toString();
	}

	/**
	 * Returns the ASCII-escaped representation of the given file URL.
	 * 
	 * @param fileURL
	 *            The file URL to encode.
	 * @return the ASCII-escaped respresentation.
	 */
	public static String encodeFileURL(String fileURL) {
		String url = fileURL;
		StringBuffer strBuf = new StringBuffer();
		int urlLength = url.length();
		for (int i = 0; i < urlLength; i++) {
			char ch = url.charAt(i);
			switch (ch) {
			case ' ':
				strBuf.append("+"); //$NON-NLS-1$
				break;
			default:
				strBuf.append(ch);
				break;
			}
		}
		return strBuf.toString();
	}

	/**
	 * Returns the ASCII-escaped representation of the given file URL.
	 * 
	 * @param fileURL
	 *            The file URL to encode.
	 * @return the ASCII-escaped respresentation.
	 */
	public static String decodedFileUrl(String fileURL) {
		if (isRawUrl(fileURL)) {
			return fileURL;
		}
		
		String url = fileURL;
		StringBuffer strBuf = new StringBuffer();
		int urlLength = url.length();
		for (int i = 0; i < urlLength; i++) {
			char ch = url.charAt(i);
			switch (ch) {
			case '+':
				strBuf.append(' ');
				break;
			case '%':
				try {
					strBuf.append((char) Integer.parseInt(url.substring(i + 1,
							i + 3), 16));
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException();
				}
				i += 2;
				break;
			default:
				strBuf.append(ch);
				break;
			}
		}
		return strBuf.toString();
	}
	
	public static String decodeURL (String url) throws UnsupportedEncodingException {
//		url = url.replaceAll("\\+", "%2B"); //$NON-NLS-1$
		url = URLDecoder.decode(url, "UTF-8"); //$NON-NLS-1$
		return url;
	}
	
	public static boolean isRawUrl(String url) {
		boolean result = false;
		
		if (url.endsWith(RAW_URL_RAW)) {
			result = true;
		}
		
		return result;
	}

}