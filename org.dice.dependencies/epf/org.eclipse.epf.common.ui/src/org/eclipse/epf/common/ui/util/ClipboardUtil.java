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
package org.eclipse.epf.common.ui.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.HTMLTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.osgi.framework.Bundle;

/**
 * Utility class for retrieving data from the clipboard.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ClipboardUtil {
	
	/**
	 * The current clipboard.
	 */
	private static Clipboard clipboard;

	private static final String SOURCE_URL = "SourceURL:"; //$NON-NLS-1$

	private static Transfer htmlTransfer = null;

	static {
		if (SWT.getPlatform().equals("win32")) { //$NON-NLS-1$
			try {
				Bundle bundle = Platform
						.getBundle("org.eclipse.epf.common.win32.win32.x86"); //$NON-NLS-1$
				Class c = bundle
						.loadClass("org.eclipse.epf.common.win32.Win32HTMLTransfer"); //$NON-NLS-1$
				if (c != null) {
					htmlTransfer = (Transfer) c.newInstance();
				}
			} catch (Exception e) {
				htmlTransfer = null;
			}
		}
	}

	/**
	 * Gets the HTML source URL from the current clipboard.
	 * 
	 * @return the HTML source URL or <code>null</code>
	 */
	public static String getHTMLSourceURL() {
		if (htmlTransfer == null) {
			return null;
		}

		Clipboard clipboard = new Clipboard(Display.getCurrent());
		String sourceURL = null;
		try {
			String htmlContent = (String) clipboard.getContents(htmlTransfer);
			if (htmlContent != null && htmlContent.length() > 0) {
				int index = htmlContent.indexOf(SOURCE_URL);
				if (index > 0) {
					sourceURL = htmlContent.substring(index
							+ SOURCE_URL.length());
					sourceURL = sourceURL.substring(0, sourceURL
							.indexOf(FileUtil.LINE_SEP));
				}
			}
			if (sourceURL != null && sourceURL.indexOf("\\") != -1) { //$NON-NLS-1$
				// IE provides sourceURL in form "file://C:\foo\bar.htm"
				// but when the hrefs are resolved, files look like "file:///C:/foo/bar.htm"
				URL url = new URL(sourceURL);
				sourceURL = url.toExternalForm();
				if (sourceURL.startsWith("file://") && !sourceURL.startsWith("file:///")) { //$NON-NLS-1$ //$NON-NLS-2$
					// need to add a third / so rte.js can match the sourceURL to hrefs
					sourceURL = "file:///" + sourceURL.substring(7); //$NON-NLS-1$
				}
			}
			return sourceURL;
		} catch (MalformedURLException urlEx) {
			return sourceURL;
		} finally {
			clipboard.dispose();
		}
	}

	/**
	 * Copy the string to the clipboard.
	 */
	public static void copyTextHTMLToClipboard(String string) {
		if (clipboard != null)
			clipboard.dispose();
		clipboard = new Clipboard(null);
		clipboard.setContents(new Object[] { string, string },
				new Transfer[] { TextTransfer.getInstance(), HTMLTransfer.getInstance() });
		if (clipboard != null)
			clipboard.dispose();
	}

}
