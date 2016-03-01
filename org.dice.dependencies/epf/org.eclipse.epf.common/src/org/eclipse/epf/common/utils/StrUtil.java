//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.common.utils;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.CommonPlugin;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.util.StringTokenizer;

/**
 * A helper class for manuipulating strings.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @since 1.0
 */
public class StrUtil {
	public static final String EMPTY_STRING = ""; //$NON-NLS-1$

	public static final String TAB = "\t"; //$NON-NLS-1$

	private static final String REGEXP_ANY_SPECIAL_CHARS = "(`|~|!|@|#|\\$|%|\\^|&|\\*|\\(|\\)|\\+|=|\\[|\\]|\\||\\:|\"|<|>|\\?|/|'|\\s|\\\\)+"; //$NON-NLS-1$

	private static final String REGEXP_INVALID_PUBLISHED_PATH_CHARS = "(\\[|#|\\*|\\?|\"|<|>|\\||!|%|/|\\])+"; //$NON-NLS-1$

	private static final String REGEXP_INVALID_PUBLISHED_PATH_CHARS_LINUX = "(\\[|#|\\*|\\?|\"|<|>|\\||!|%|\\])+"; //$NON-NLS-1$

	private static final String REGEXP_INVALID_FILENAME_CHARS = "(\\[|#|/|\\\\|\\:|\\*|\\?|\"|<|>|\\||\\]|\\s)+"; //$NON-NLS-1$

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static final String LINE_FEED = System.getProperty("line.separator"); //$NON-NLS-1$

	public static final String ESCAPED_LF = "&#xA;"; //$NON-NLS-1$

	public static final String ESCAPED_CR = "&#xD;"; //$NON-NLS-1$

	public static final String ESCAPED_LINE_FEED = LINE_FEED.replace(
			"\n", ESCAPED_LF).replace("\r", ESCAPED_CR); //$NON-NLS-1$ //$NON-NLS-2$

	public static final String LINE_FEED_REGEX = LINE_FEED.replaceAll(
			"\\\\", "\\\\"); //$NON-NLS-1$ //$NON-NLS-2$

	public static final String ESCAPED_LINE_FEED_REGEX = ESCAPED_LINE_FEED;

	public static final String HTML_BREAK = "<br/>";//$NON-NLS-1$

	public static final String HTML_COPY = "&copy;";//$NON-NLS-1$

	public static final String HTML_EURO = "&euro;";//$NON-NLS-1$ 	

	public static final String HTML_REG = "&reg;";//$NON-NLS-1$ 

	public static final String HTML_TRADEMARK = "&trade;";//$NON-NLS-1$
	
	public static boolean during_migration = false;
	
	private static StrUtilOptions options;

	/**
	 * Private constructor to prevent this class from being instantiated. All
	 * methods in this class should be static.
	 */
	private StrUtil() {
	}

	/**
	 * Tests for null string.
	 * <p>
	 * A null string is defined as one that has an empty reference or has zero
	 * length.
	 * 
	 * @param str
	 *            a string
	 * @return <code>true</code> if the given string is a null string
	 */
	public static boolean isNull(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * Tests for blank string.
	 * <p>
	 * A blank string is defined as one that has an empty reference or has zero
	 * length after the leading and trailing space characters are trimmed.
	 * 
	 * @param str
	 *            a string
	 * @return <code>true</code> if the given string is a blank string
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * Removes the leading and trailing space characters from a string.
	 * 
	 * @param str
	 *            a string
	 * @return a string with no leading and trailing space characters
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * Removes whitespace characters (TAB, CR, LF and SPACE) from a string.
	 * 
	 * @param str
	 *            a string
	 * @return a string with whitespace characters removed
	 */
	public static String removeWhiteSpaceChars(String str) {
		int len = (str == null) ? 0 : str.length();
		for (int i = 0; i < len; i++) {
			switch (str.charAt(i)) {
			case '\t':
			case '\r':
			case '\n':
			case ' ':
				break;
			default:
				return str;
			}
		}
		return ""; //$NON-NLS-1$
	}

	/**
	 * Removes CR, LF from a string.
	 * 
	 * @param str
	 *            a string
	 * @return a string with CR, LF removed
	 */
	public static String removeNewlines(String str) {
		StringBuffer sb = new StringBuffer();
		int len = (str == null) ? 0 : str.length();
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			switch (c) {
			case '\r':
			case '\n':
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString(); 
	}

	/**
	 * Splits a string into an array of string tokens.
	 * 
	 * @param str
	 *            a string
	 * @param sep
	 *            a string containing the string separators
	 * @param count
	 *            the desired number of string tokens
	 * @return an array of string tokens
	 */
	public static String[] split(String str, String sep, int count) {
		if (str == null || count == 0 || count < -1) {
			return null;
		}

		StringTokenizer tokenizer = new StringTokenizer(str, sep,
				count == -1 ? false : true);

		if (count == -1) {
			count = tokenizer.countTokens();
		}

		String[] result = new String[count];
		int i = 0;
		while (tokenizer.hasMoreTokens()) {
			String t = tokenizer.nextToken();
			if (i < count) {
				if ((t.length() == 1) && (sep.indexOf(t) != -1)) {
					continue;
				}
				result[i++] = t;
			} else {
				result[count - 1] += t;
			}
		}
		return result;
	}

	/**
	 * Splits a string into an array of string tokens.
	 * 
	 * @param str
	 *            a string
	 * @param sep
	 *            a string containing the string separators
	 * @return an array of string tokens
	 */
	public static String[] split(String str, String sep) {
		return split(str, sep, -1);
	}

	/**
	 * Replaces a substring within a string with another substring.
	 * <p>
	 * Note: Only the first occurrence of the substring will be replaced.
	 * 
	 * @param str
	 *            a string
	 * @param src
	 *            the substring to replace
	 * @param tgt
	 *            the substring to use for the replacement
	 * @return a string with the first substring replaced
	 * 
	 * TODO: Review implementation. Call String.replaceFirst() instead?
	 */
	public static String replace(String str, String src, String tgt) {
		if ((str == null || str.length() == 0)
				|| (src == null || src.length() == 0)) {
			return str;
		}

		String tmpStr = str;
		int index;
		while ((index = tmpStr.indexOf(src)) != -1) {
			tmpStr = tmpStr.substring(0, index) + tgt
					+ tmpStr.substring(index + src.length());
		}
		return tmpStr;
	}

	/**
	 * Returns the integer value of a string.
	 * 
	 * @param str
	 *            a string
	 * @param defaultValue
	 *            a default integer value for the string
	 * @return the integer value of the given string
	 */
	public static int getIntValue(String str, int defaultValue) {
		if (StrUtil.isBlank(str)) {
			return defaultValue;
		}

		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	/**
	 * Returns an array of bytes representing the UTF-8 encoding of a string.
	 * 
	 * @param str
	 *            a string
	 * @return a byte array containing the UTF-8 encoding of the given string
	 */
	public static byte[] getUTF8Bytes(String str) {
		char[] c = str.toCharArray();
		int len = c.length;
		int count = 0;
		for (int i = 0; i < len; i++) {
			int ch = c[i];
			if (ch <= 0x7f) {
				count++;
			} else if (ch <= 0x7ff) {
				count += 2;
			} else {
				count += 3;
			}
		}

		byte[] b = new byte[count];
		int off = 0;
		for (int i = 0; i < len; i++) {
			int ch = c[i];
			if (ch <= 0x7f) {
				b[off++] = (byte) ch;
			} else if (ch <= 0x7ff) {
				b[off++] = (byte) ((ch >> 6) | 0xc0);
				b[off++] = (byte) ((ch & 0x3f) | 0x80);
			} else {
				b[off++] = (byte) ((ch >> 12) | 0xe0);
				b[off++] = (byte) (((ch >> 6) & 0x3f) | 0x80);
				b[off++] = (byte) ((ch & 0x3f) | 0x80);
			}
		}
		return b;
	}

	/**
	 * Returns the hexidecimal character representation for an integer.
	 * 
	 * @param value
	 *            an integer
	 * @return the hexidecimal representation
	 */
	private static char toHex(int value) {
		return HEX_DIGITS[(value & 0xF)];
	}

	/**
	 * Returns the escaped Unicode representation of a string.
	 * 
	 * @param str
	 *            a string
	 * @param skipASCII
	 *            if <code>true</code>, avoid escaping the ASCII characters
	 * @return the escaped Unicode representation of the given string
	 */
	public static String toEscapedUnicode(String str, boolean skipASCII) {
		int len = str.length();
		StringBuffer result = new StringBuffer(len * 2);

		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);

			if (skipASCII && ch < 0x007E) {
				result.append(ch);
			} else {
				result.append("\\u"); //$NON-NLS-1$
				result.append(toHex((ch >> 12) & 0xF));
				result.append(toHex((ch >> 8) & 0xF));
				result.append(toHex((ch >> 4) & 0xF));
				result.append(toHex(ch & 0xF));
			}
		}

		return result.toString();
	}

	/**
	 * Returns the escaped HTML representation of a string.
	 * 
	 * @param html
	 *            a HTML string
	 * @return the escaped Unicode representation of the given HTML string
	 */
	public static String getEscapedHTML(String html) {
		if (html == null || html.length() == 0) {
			return ""; //$NON-NLS-1$
		}

		StrUtilOptions options = getOptions();
		StringBuffer result = new StringBuffer();
		int length = html.length();
		for (int i = 0; i < length; i++) {
			char ch = html.charAt(i);
			switch (ch) {
			case '%':
				if (i + 4 < length) {
					String hexStr = html.substring(i + 1, i + 5);
					boolean validHextStr = true;
					
					for (int j = 0; j < hexStr.length(); j++) {
						char c = hexStr.charAt(j);
						if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
							validHextStr = false;
							break;
						}
					}
					
					if (options == null) {
						//code below will treat "%20de" as " de"
						//this may lose some double bytes character(e.g. Chinese), which start with %20, but keep all url links
						//so far open this convertion not only during library migration to support file like "my design.gif"
						if (/*during_migration && */validHextStr) {
							if (hexStr.startsWith("20")) { //$NON-NLS-1$
								result.append("%20"); //$NON-NLS-1$
								i += 2;
								break;
							}
						}
					} else {
						int ix = options.getRteUrlDecodingOption();
						if (ix == 1) {
							validHextStr = false;
						} else if (ix == 2) {
							String key = getHexStr("%" + hexStr);
							if (key != null && options.getRteUrlDecodingHexMap().containsKey(key)) {
								validHextStr = false;
							}
						}
					}
					
					if (validHextStr) {
						try {
							int codePoint = Integer.parseInt(hexStr, 16);
							char[] c = UCharacter.toChars(codePoint);
							result.append(c);
							i += 4;
							break;
						} catch (NumberFormatException e) {
							// wasn't a valid hex string..
							// fall through to the result.append(ch)
						} catch (Exception e) {
							CommonPlugin.getDefault().getLogger().logError(e);
						}
					}
				}
				result.append(ch);
				break;
			case '\u00a9':
				result.append(HTML_COPY);
				break;
			case '\u00ae':
				result.append(HTML_REG);
				break;
			case '\u20ac':
				result.append(HTML_EURO);
				break;
			case '\u2122':
				result.append(HTML_TRADEMARK);
				break;
			default:
				result.append(ch);
				break;
			}
		}
		return result.toString();
	}

	/**
	 * Returns the plain text from HTML text.
	 * <p>
	 * Note: All HTML tags will be stripped.
	 * 
	 * @param html
	 *            the HTML text.
	 * @return the plain text representation of the given HTML text
	 */
	public static String getPlainText(String html) {
		if (html == null) {
			return ""; //$NON-NLS-1$
		}

		final Pattern p_plaintext_filter = Pattern.compile(
				"<[^>]*?>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$
		final Pattern p_plaintext_filter2 = Pattern.compile(
				"&.{1,5}[^;];", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$
		final Pattern p_plaintext_filter3 = Pattern.compile(
				"\\s+", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

		String result = html.replaceAll(p_plaintext_filter.pattern(), " ") //$NON-NLS-1$
				.replaceAll(p_plaintext_filter2.pattern(), " ") //$NON-NLS-1$
				.replaceAll(p_plaintext_filter3.pattern(), " "); //$NON-NLS-1$
		return result;
	}

	/**
	 * Converts a string into a valid file name.
	 * 
	 * @param str
	 *            a string
	 * @return a valid file name derived from the given string
	 */
	public static String makeValidFileName(String str) {
		if (str == null) {
			return ""; //$NON-NLS-1$
		}
		return getPlainText(str)
				.replaceAll(REGEXP_INVALID_FILENAME_CHARS, " ").trim(); //$NON-NLS-1$ 
	}

	public static String removeSpecialCharacters(String str) {
		if (str == null) {
			return ""; //$NON-NLS-1$
		}
		return getPlainText(str)
				.replaceAll(REGEXP_ANY_SPECIAL_CHARS, " ").trim(); //$NON-NLS-1$ 
	}

	/**
	 * Returns true if the path does not contain any invalid filename
	 * characters.
	 * 
	 * @param path
	 *            the file path
	 * @return <code>true</code> if the given path contains only valid
	 *         filename characters
	 */
	public static boolean isValidPublishPath(String path) {
		// return path.replaceAll(invalidPublishPathCharsRegExp,
		// "").equals(path);

		if (Platform.getOS().equals(Platform.WS_WIN32)) {
			return path
					.replaceAll(REGEXP_INVALID_PUBLISHED_PATH_CHARS, "").equals(path); //$NON-NLS-1$
		}

		// else default to Linux
		return path
				.replaceAll(REGEXP_INVALID_PUBLISHED_PATH_CHARS_LINUX, "").equals(path); //$NON-NLS-1$
	}

	/**
	 * Returns the lower-case of str unless the current locale is German
	 * 
	 * @param str
	 * @return
	 */
	public static String toLower(String str) {
		if (Platform.getNL().startsWith("de")) { //$NON-NLS-1$
			return str;
		}
		return str.toLowerCase();
	}

	/**
	 * escape the " and '
	 * 
	 * @param str
	 * @return
	 */
	public static String escape(String str) {
		return str.replaceAll("'", "\\\\\'").replaceAll("\"", "\\\\\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	/**
	 * Converts a <code>List</code> to an String array.
	 * 
	 * @param list
	 *            a <code>List</code>
	 * @return a String array
	 */
	public static String[] convertListToStrArray(List list) {
		if (list != null) {
			int cnt = list.size();
			String[] strArray = new String[cnt];

			for (int i = 0; i < cnt; i++) {
				String str = (String) list.get(i);
				strArray[i] = new String(str);
			}
			return strArray;
		} else {
			return null;
		}
	}

	public static boolean isValidName(String name) {
		return name.replaceAll(REGEXP_ANY_SPECIAL_CHARS, "").equals(name); //$NON-NLS-1$		
	}

	/**
	 * added some test code
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String text = "A&%      `&#           $~!@#$%^&*()_-+={}[\"]:|\\:;\"'<,>.?/ \t\r\nZ"; //$NON-NLS-1$

		System.out.println("[" + text + "] <== text"); //$NON-NLS-1$ //$NON-NLS-2$
		System.out.println("[" + text.replaceAll(REGEXP_ANY_SPECIAL_CHARS, " ") //$NON-NLS-1$ //$NON-NLS-2$
				+ "] <== All"); //$NON-NLS-1$
		System.out.println("[" //$NON-NLS-1$
				+ text.replaceAll(REGEXP_INVALID_FILENAME_CHARS, " ") //$NON-NLS-1$
				+ "] <== File Name"); //$NON-NLS-1$
		System.out.println("[" //$NON-NLS-1$
				+ text.replaceAll(REGEXP_INVALID_PUBLISHED_PATH_CHARS, " ") //$NON-NLS-1$
				+ "] <== path"); //$NON-NLS-1$
		System.out.println("[" //$NON-NLS-1$
				+ text.replaceAll(REGEXP_INVALID_PUBLISHED_PATH_CHARS_LINUX,
						" ") + "] <== path, Linux"); //$NON-NLS-1$ //$NON-NLS-2$

		System.out.println(""); //$NON-NLS-1$
	}

	/**
	 * Converts the platform line-separator (\n or \n\r or \r) to &lt;br/&gt;
	 * 
	 * @param text
	 * @return
	 */
	public static String convertNewlinesToHTML(String text) {
		if (text != null) {
			text = text.replaceAll(LINE_FEED_REGEX, HTML_BREAK + LINE_FEED);
			text = text.replaceAll(ESCAPED_LINE_FEED_REGEX, HTML_BREAK
					+ ESCAPED_LINE_FEED);
		}
		return text;
	}

	public static URI toURI(String pathStr) {
		if (pathStr != null && pathStr.length() > 0) {
			IPath path = Path.fromOSString(pathStr);
			try {
				return path.toFile().toURI();
			} catch (Exception e) {
				CommonPlugin.getDefault().getLogger().logError(e);
			}
		}
		return null;
	}

	public static String escapeChar(String text, char c) {
		int i=text.indexOf(c); 
		if ( i < 0 ) {
			return text;
		}
		
		int start = 0;
		StringBuffer buffer = new StringBuffer();
		while ( i > start ) {
			buffer.append(text.substring(start, i)).append("\\"); //$NON-NLS-1$
			start = i;
			i=text.indexOf(c, start+1); 
		}
		
		buffer.append(text.substring(start));
		
		return buffer.toString();
	}
	
	public static String getHexStr(String str) {
		if (str.length() < 3) {
			return null;
		}
		if (str.charAt(0) != '%') {
			return null;
		}
		StringBuffer b = new StringBuffer();
		b.append('%');
		for (int i = 1 ; i <= 2; i++) {
			char c = str.charAt(i);
			if (c >= 'a' && c <= 'z') {
				c -= 'a';
				c += 'A'; 
			} 			
			boolean valid = (c >= '0' && c <= '9') ||
							(c >= 'A' && c <= 'F');
			if (!valid) {
				return null;
			}
			b.append(c);			
		}
		
		return b.toString();
	}
	
	public interface StrUtilOptions {
		int getRteUrlDecodingOption();
		Map<String, String> getRteUrlDecodingHexMap();		
	}

	public static StrUtilOptions getOptions() {
		return options;
	}

	public static void setOptions(StrUtilOptions options) {
		StrUtil.options = options;
	}
	
}