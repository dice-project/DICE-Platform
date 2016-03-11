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
package org.eclipse.epf.library.layout.util;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.XMLUtil;


/**
 * Helper class for processing the XML representation of a Method Element.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 */
public class XmlHelper {

	/**
	 * XML declaration.
	 */
	public final static String XML_HEADER = XMLUtil.XML_DECLARATION
			+ FileUtil.LINE_SEP;

	public static String validName(String elementName) {
		return XMLUtil.escape(elementName.replace(' ', '-'));
	}

	public static String quote(String str) {
		return "\"" + str + "\""; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static String validate(String s) {
		return s == null ? "" : s; //$NON-NLS-1$
	}

	/**
	 * create the start tag of an xml element
	 * @param elementName
	 * @param attributes
	 * @return String
	 */
	public static String beginElement(String elementName, Map attributes) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<" + validName(elementName)); //$NON-NLS-1$
		if ((attributes != null) && (attributes.size() > 0)) {
			Iterator it = attributes.entrySet().iterator();
			String key, value;
			while (it.hasNext()) {
				Map.Entry entry = (Map.Entry) it.next();
				key = (String) entry.getKey();
				value = quote(XMLUtil.escape(
						validate((String) entry.getValue()), true));
				buffer.append(" " + validName(key) + "=" + value); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		buffer.append(">"); //$NON-NLS-1$
		return buffer.toString();
	}

	/**
	 * create an end tag of an xml element
	 * @param elementName
	 * @return String
	 */
	public static String endElement(String elementName) {
		return "</" + validName(elementName) + ">"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * create an xml element string
	 * @param elementName
	 * @param elementValue
	 * @param attributes
	 * @return String
	 */
	public static String getElement(String elementName, String elementValue,
			Map attributes) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(beginElement(elementName, attributes)).append(
				XMLUtil.escape(validate(elementValue), true)).append(
				endElement(elementName));
		return buffer.toString();
	}

}
