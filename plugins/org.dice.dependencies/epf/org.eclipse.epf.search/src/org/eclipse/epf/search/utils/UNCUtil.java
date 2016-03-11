//------------------------------------------------------------------------------
//Copyright (c) 2005, 2007 IBM Corporation and others.
//All rights reserved. This program and the accompanying materials
//are made available under the terms of the Eclipse Public License v1.0
//which accompanies this distribution, and is available at
//http://www.eclipse.org/legal/epl-v10.html
//
//Contributors:
//IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.search.utils;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;


public class UNCUtil {
	public static final String UNC_FILE_PREFIX = "file:/"; //$NON-NLS-1$
	public static final String ALTERNATE_UNC_FILE_PREFIX = "file://"; //$NON-NLS-1$
	public static final String NETSCAPE_UNC_FILE_PREFIX = "file:///"; //$NON-NLS-1$
	public static final String UNC_FILE_ONLY = "file:"; //$NON-NLS-1$
	public static final String UNC_SEPARATOR = "/"; //$NON-NLS-1$


	/**
	 * Accounts for netscape unc file prefix. Converts to standard one.
	 * If not Netscape, nothing changes.
	 */
	public static String handleNetscapeFilePrefix(String filename)
	{
		String convertedName = null;

		if (filename.startsWith(NETSCAPE_UNC_FILE_PREFIX))
		{
			convertedName =
				ALTERNATE_UNC_FILE_PREFIX
					+ filename.substring(
						NETSCAPE_UNC_FILE_PREFIX.length(),
						filename.length());
		}
		else
		{
			convertedName = filename;
		}

		return (convertedName);
	}

	/**
	 * Handles URL paths for Mozilla (and other browsers) over UNC.
	 */
	public static URL handleURLForUNC(URL originalUrl)
	{
		// handle special case for Mozilla over UNC
		URL newUrl = null;
		try
		{
			if (originalUrl.toString().startsWith("file://")) //$NON-NLS-1$
			{
				// change "file://" to "file://///"
				String extractedString = originalUrl.toString().substring(5);
				newUrl = new URL("file", "", "///" + extractedString); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			}
			else
			{
				newUrl = originalUrl;
			}
		}
		catch (java.net.MalformedURLException mue)
		{
			newUrl = originalUrl;
		}

		return (newUrl);
	}

	/**
	 * Converts the UNC file to a regular, platform-dependent one.
	 */
	public static String convertFilename(String filename)
	{
		String convertedName = null;

		// remove any URL prefixes
		String tempName = null;
		if (filename.startsWith(UNC_FILE_PREFIX))
		{
			tempName = filename.substring(UNC_FILE_PREFIX.length(), filename.length());
			int index = tempName.indexOf(":"); //$NON-NLS-1$
			if (index == -1)
			{
				// on Unix, add separator back to first character
				tempName = UNC_SEPARATOR + tempName;
			}
		}
		else
		{
			tempName = filename;
		}

		// convert any UNC separators to the system default one
		convertedName =
			tempName.replace(UNC_SEPARATOR.charAt(0), File.separator.charAt(0));

		return (convertedName);
	}

	/**
	 * Converts the filename to a UNC filename.
	 */
	public static String convertFilenameToUNC(String filename)
	{
		// convert any UNC separators to the system default one
		String convertedName =
			filename.replace(File.separator.charAt(0), UNC_SEPARATOR.charAt(0));
		String finalName = convertFileSeparator(convertedName);
		return (finalName);
	}

	/**
	 * Converts the URL to one which can that matches a UNC convention.
	 */
	public static String convertToUNC(String url)
	{
		String convertedUrl = null;
		if (url.startsWith(ALTERNATE_UNC_FILE_PREFIX))
		{
			convertedUrl =
				UNC_FILE_PREFIX
					+ url.substring(ALTERNATE_UNC_FILE_PREFIX.length(), url.length());
		}
		else
		{
			convertedUrl = url;
		}
		return (convertedUrl);

	}

	/**
	 * Removes the "%20" that is used by some browsers for spaces
	 */
	public static String convertFileSpacing(String filename)
	{
		// now remove any "%20"
		int start = 0;
		String newFilename = ""; //$NON-NLS-1$
		while (start < filename.length())
		{
			int index = filename.indexOf("%20", start); //$NON-NLS-1$
			if (index == -1)
			{
				index = filename.length();
			}
			newFilename += filename.substring(start, index);
			newFilename += " "; //$NON-NLS-1$
			start = index + 3;
		}

		newFilename = newFilename.trim();

		return (newFilename);
	}

	/**
	 * Removes the "%5C" that is used by some browsers for file separator.
	 */
	public static String convertFileSeparator(String filename)
	{
		// remove any "%5C"
		int start = 0;
		String newFilename = ""; //$NON-NLS-1$
		while (start < filename.length())
		{
			int index = filename.indexOf("%5C", start); //$NON-NLS-1$
			if (index == -1)
			{
				//index = filename.length();
				newFilename = filename.trim();
				break;
			}
			newFilename += filename.substring(start, index);
			newFilename += UNC_SEPARATOR;
			start = index + 3;
		}

		newFilename = newFilename.trim();

		// also if file separator equals unc separator, remove any Windows
		// specific "\"
		String windowsFileSeparator = "\\"; //$NON-NLS-1$
		if (!File.separator.equals(windowsFileSeparator)
			&& File.separator.equals(UNC_SEPARATOR))
		{
			// on Unix
			newFilename =
				newFilename.replace(
					windowsFileSeparator.charAt(0),
					UNC_SEPARATOR.charAt(0));
		}
		return (newFilename);
	}

	/**
	 * Retrieves the file names from the specified directory
	 * that matches the given suffix.  An example of a 
	 * suffix is ".dat".
	 */
	public static Vector getFileList(String directory, String suffix)
	{
		if (directory.startsWith("http")) //$NON-NLS-1$
		{
			// remote directory, use this instead
			return (getFileListFromRemote(directory, suffix));
		}

		String tempDir = UNCUtil.convertFilename(directory);
		File documentDirectory = new File(UNCUtil.convertFileSpacing(tempDir));
		String[] fileNameList = documentDirectory.list();

		// now parse and only take those that end with the proper suffix
		Vector finalList = new Vector();
		for (int i = 0; i < fileNameList.length; i++)
		{
			if (fileNameList[i].endsWith(suffix))
			{
				finalList.addElement(fileNameList[i]);
			}
		}

		return (finalList);
	}

	/**
	 * Retrieves the applicable default rup files from remote directory.
	 */
	private static Vector getFileListFromRemote(String directory, String suffix)
	{
		Vector filenames = new Vector();
		try
		{
			// get the listing using a URL
			URL remoteUrl = new URL(directory);
			InputStream inStream = remoteUrl.openStream();
			StringBuffer result = new StringBuffer();
			int c;
			while ((c = inStream.read()) != -1)
			{
				result.append((char) c);
			}
			inStream.close();

			// convert string to upper case to handle multiple server types such as Apache
			String directoryResult = result.toString();
			directoryResult = directoryResult.replaceAll("href", "HREF"); //$NON-NLS-1$ //$NON-NLS-2$
			directoryResult = directoryResult.replaceAll("</a", "</A"); //$NON-NLS-1$ //$NON-NLS-2$
			directoryResult = directoryResult.replaceAll("<tt>", ""); //$NON-NLS-1$ //$NON-NLS-2$
			directoryResult = directoryResult.replaceAll("</tt>", ""); //$NON-NLS-1$ //$NON-NLS-2$
			directoryResult = directoryResult.replaceAll("<TT>", ""); //$NON-NLS-1$ //$NON-NLS-2$
			directoryResult = directoryResult.replaceAll("</TT>", ""); //$NON-NLS-1$ //$NON-NLS-2$

			// parse the content
			//StringTokenizer lineTokenizer = new StringTokenizer( result.toString() );
			StringTokenizer lineTokenizer = new StringTokenizer(directoryResult);
			Vector lines = new Vector();
			try
			{
				while (true)
				{
					lineTokenizer.nextToken("REF"); //$NON-NLS-1$
					lineTokenizer.nextToken("\""); //$NON-NLS-1$
					String line = lineTokenizer.nextToken("\""); //$NON-NLS-1$

					// strip out "</A"; needed this because StringTokenizer
					// has problems parsing filenames that being with "A"
					int index = line.indexOf("</A"); //$NON-NLS-1$
					if (index != -1 && index != 0)
					{
						String filename = line.substring(0, index);
						lines.addElement(filename);
					}
					else
					{
						lines.addElement(line);
					}
				}
			}
			catch (NoSuchElementException ne)
			{
				// do nothing, end of content
			}

			// parse each line
			for (int i = 0; i < lines.size(); i++)
			{
				String line = (String) lines.elementAt(i);

				if (line.endsWith(suffix))
				{
					// we want this
					filenames.addElement(line);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return (filenames);
	}
}