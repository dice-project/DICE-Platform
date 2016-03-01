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
package org.eclipse.epf.common.win32;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.ole.win32.FORMATETC;
import org.eclipse.swt.internal.ole.win32.IDataObject;
import org.eclipse.swt.internal.ole.win32.STGMEDIUM;
import org.eclipse.swt.internal.win32.OS;

/**
 * The class <code>HTMLTransfer</code> provides a platform specific mechanism
 * for converting text in HTML format represented as a java <code>String</code>
 * to a platform specific representation of the data and vice versa. See
 * <code>Transfer</code> for additional information.
 * 
 * <p>
 * An example of a java <code>String</code> containing HTML text is shown
 * below:
 * </p>
 * 
 * <code><pre>
 *       String htmlData = &quot;
 * <p>
 *  This is a paragraph of text.
 * </p>
 *  &quot;;
 * </code></pre>
 */
public class Win32HTMLTransfer extends ByteArrayTransfer {

	static Win32HTMLTransfer _instance = new Win32HTMLTransfer();

	static final String HTML_FORMAT = "HTML Format"; //$NON-NLS-1$

	static final int HTML_FORMATID = registerType(HTML_FORMAT);

	static final String NUMBER = "00000000"; //$NON-NLS-1$

	static final String HEADER = "Version:0.9\r\nStartHTML:" + NUMBER //$NON-NLS-1$
			+ "\r\nEndHTML:" + NUMBER + "\r\nStartFragment:" + NUMBER //$NON-NLS-1$ //$NON-NLS-2$
			+ "\r\nEndFragment:" + NUMBER + "\r\n"; //$NON-NLS-1$ //$NON-NLS-2$

	static final String PREFIX = "<html><body><!--StartFragment-->"; //$NON-NLS-1$

	static final String SUFFIX = "<!--EndFragment--></body></html>"; //$NON-NLS-1$

	static final String StartFragment = "StartFragment:"; //$NON-NLS-1$

	static final String EndFragment = "EndFragment:"; //$NON-NLS-1$

	public Win32HTMLTransfer() {
	}

	/**
	 * Returns the singleton instance of the HTMLTransfer class.
	 * 
	 * @return the singleton instance of the HTMLTransfer class
	 */
	public static Win32HTMLTransfer getInstance() {
		return _instance;
	}

	/**
	 * This implementation of <code>javaToNative</code> converts
	 * HTML-formatted text represented by a java <code>String</code> to a
	 * platform specific representation. For additional information see
	 * <code>Transfer#javaToNative</code>.
	 * 
	 * @param object
	 *            a java <code>String</code> containing HTML text
	 * @param transferData
	 *            an empty <code>TransferData</code> object; this object will
	 *            be filled in on return with the platform specific format of
	 *            the data
	 */
	public void javaToNative(Object object, TransferData transferData) {
		if (!checkHTML(object) || !isSupportedType(transferData)) {
			DND.error(DND.ERROR_INVALID_DATA);
		}
		String string = (String) object;
		int count = string.length();
		char[] chars = new char[count + 1];
		string.getChars(0, count, chars, 0);
		int codePage = OS.GetACP();
		int cchMultiByte = OS.WideCharToMultiByte(codePage, 0, chars, -1, null,
				0, null, null);
		if (cchMultiByte == 0) {
			transferData.stgmedium = new STGMEDIUM();
			transferData.result = COM.DV_E_STGMEDIUM;
			return;
		}
		int startHTML = HEADER.length();
		int startFragment = startHTML + PREFIX.length();
		int endFragment = startFragment + cchMultiByte - 1;
		int endHTML = endFragment + SUFFIX.length();

		StringBuffer buffer = new StringBuffer(HEADER);
		int maxLength = NUMBER.length();
		// startHTML
		int start = buffer.toString().indexOf(NUMBER);
		String temp = Integer.toString(startHTML);
		buffer.replace(start + maxLength - temp.length(), start + maxLength,
				temp);
		// endHTML
		start = buffer.toString().indexOf(NUMBER, start);
		temp = Integer.toString(endHTML);
		buffer.replace(start + maxLength - temp.length(), start + maxLength,
				temp);
		// startFragment
		start = buffer.toString().indexOf(NUMBER, start);
		temp = Integer.toString(startFragment);
		buffer.replace(start + maxLength - temp.length(), start + maxLength,
				temp);
		// endFragment
		start = buffer.toString().indexOf(NUMBER, start);
		temp = Integer.toString(endFragment);
		buffer.replace(start + maxLength - temp.length(), start + maxLength,
				temp);

		buffer.append(PREFIX);
		buffer.append(string);
		buffer.append(SUFFIX);

		count = buffer.length();
		chars = new char[count + 1];
		buffer.getChars(0, count, chars, 0);
		cchMultiByte = OS.WideCharToMultiByte(codePage, 0, chars, -1, null, 0,
				null, null);
		int lpMultiByteStr = OS.GlobalAlloc(OS.GMEM_FIXED | OS.GMEM_ZEROINIT,
				cchMultiByte);
		OS.WideCharToMultiByte(codePage, 0, chars, -1, lpMultiByteStr,
				cchMultiByte, null, null);
		transferData.stgmedium = new STGMEDIUM();
		transferData.stgmedium.tymed = COM.TYMED_HGLOBAL;
		transferData.stgmedium.unionField = lpMultiByteStr;
		transferData.stgmedium.pUnkForRelease = 0;
		transferData.result = COM.S_OK;
		return;
	}

	/**
	 * This implementation of <code>nativeToJava</code> converts a platform
	 * specific representation of HTML text to a java <code>String</code>.
	 * For additional information see <code>Transfer#nativeToJava</code>.
	 * 
	 * @param transferData
	 *            the platform specific representation of the data to be been
	 *            converted
	 * @return a java <code>String</code> containing HTML text if the
	 *         conversion was successful; otherwise null
	 */
	public Object nativeToJava(TransferData transferData) {
		if (!isSupportedType(transferData) || transferData.pIDataObject == 0)
			return null;
		IDataObject data = new IDataObject(transferData.pIDataObject);
		data.AddRef();
		STGMEDIUM stgmedium = new STGMEDIUM();
		FORMATETC formatetc = transferData.formatetc;
		stgmedium.tymed = COM.TYMED_HGLOBAL;
		transferData.result = data.GetData(formatetc, stgmedium);
		data.Release();
		if (transferData.result != COM.S_OK)
			return null;
		int hMem = stgmedium.unionField;

		try {
			int lpMultiByteStr = OS.GlobalLock(hMem);
			if (lpMultiByteStr == 0)
				return null;
			try {
				int codePage = OS.GetACP();
				int cchWideChar = OS.MultiByteToWideChar(codePage,
						OS.MB_PRECOMPOSED, lpMultiByteStr, -1, null, 0);
				if (cchWideChar == 0)
					return null;
				char[] lpWideCharStr = new char[cchWideChar - 1];
				OS
						.MultiByteToWideChar(codePage, OS.MB_PRECOMPOSED,
								lpMultiByteStr, -1, lpWideCharStr,
								lpWideCharStr.length);
				return new String(lpWideCharStr);
			} finally {
				OS.GlobalUnlock(hMem);
			}
		} finally {
			OS.GlobalFree(hMem);
		}
	}

	protected int[] getTypeIds() {
		return new int[] { HTML_FORMATID };
	}

	protected String[] getTypeNames() {
		return new String[] { HTML_FORMAT };
	}

	boolean checkHTML(Object object) {
		return (object != null && object instanceof String && ((String) object)
				.length() > 0);
	}

	protected boolean validate(Object object) {
		return checkHTML(object);
	}
}
