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
package org.eclipse.epf.library.util;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.eclipse.emf.common.util.URI;
import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.epf.common.xml.AbstractSAXParser;
import org.eclipse.epf.persistence.MultiFileXMISaveImpl.MyEscape;
import org.xml.sax.Attributes;


/**
 * This class parses an XMI file, and stores the data in a HashMap called an
 * "eMap".
 * <p>
 * An eMap has 4 entries:
 * <li>"tag" - the element's tag</li>
 * <li>"attributes" - an ArrayList of the XML attributes. Each attribute is of
 * the form (attribute name),(attribute value)</li>
 * <li>"content" - the element's content</li>
 * <li>"children" - an ArrayList of the element's sub-elements. Each child is
 * an eMap.</li>
 * </p>
 * <p>
 * The root element's eMap is referenced by TNGSAXParserXMI.xmiMap
 * </p>
 * 
 * @author Jeff Hardy
 * @since 1.0
 */
public class SAXParserXMI extends AbstractSAXParser {

	public HashMap xmiMap;

	// private int elementCount = 0;
	private Stack stack;

	private MyEscape escape = new MyEscape();

	public SAXParserXMI(Logger logger) {
		// validating is false since we don't have a DTD
		super(logger, false);
	}

	/**
	 * Called when the XML parser starts to parse the document.
	 */
	public void startDocument() {
		super.startDocument();
		stack = new Stack();
	}

	/**
	 * 
	 */
	public void endDocument() {
		super.endDocument();
	}

	/**
	 * 
	 */
	public void startElement(String namespaceURL, String localName,
			String qname, Attributes attributes) {
		HashMap eMap = new HashMap();
		try {
			HashMap parent = (HashMap) stack.peek();
			((ArrayList) parent.get("children")).add(eMap); //$NON-NLS-1$
		} catch (EmptyStackException e) {
			xmiMap = eMap;
		}

		// put tag
		eMap.put("tag", qname); //$NON-NLS-1$

		// put attributeMap
		List attributeList = new ArrayList();
		if (attributes.getLength() > 0) {
			for (int i = 0; i < attributes.getLength(); i++) {
				attributeList.add(attributes.getQName(i)
						+ "," + attributes.getValue(i)); //$NON-NLS-1$
			}
		}

		strBuf = new StringBuffer();

		eMap.put("attributes", attributeList); //$NON-NLS-1$

		eMap.put("children", new ArrayList()); //$NON-NLS-1$
		stack.push(eMap);
	}

	public void endElement(String namespaceURL, String localName, String qname) {
		try {
			HashMap eMap = (HashMap) stack.peek();
			List attributeList = (ArrayList) eMap.get("attributes"); //$NON-NLS-1$
			if (attributeList.size() == 0)
				if (strBuf.toString().trim().length() > 0) {
					eMap.put("content", escape.convertLines(strBuf.toString())); //$NON-NLS-1$
				}
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
		stack.pop();
	}

	/**
	 * For use with TNGSAXParserXMI. Find a guid's element tag within the XMI
	 * file.
	 * 
	 * @param elementTag
	 * @param guid
	 * @param xmiMap
	 * @return
	 */
	public static HashMap findElementTagofGuid(String elementTag, String guid,
			HashMap xmiMap) {
		HashMap eMap = SAXParserXMI.findGuid(guid, xmiMap);

		// we have hashmap of the guid's element - search children for the
		// elementTag
		Iterator iter = ((ArrayList) eMap.get("children")).iterator(); //$NON-NLS-1$
		while (iter.hasNext()) {
			HashMap childMap = (HashMap) iter.next();
			if (SAXParserXMI.getTag(childMap).equals(elementTag))
				return childMap;
		}
		return null;
	}

	/**
	 * For use with TNGSAXParserXMI. searches eMap's children for elementTag
	 * 
	 * @param elementTag
	 * @param eMap
	 * @return HashMap
	 */
	public static HashMap getChildElementTag(String elementTag, HashMap eMap) {
		// search children for the elementTag
		Iterator iter = ((ArrayList) eMap.get("children")).iterator(); //$NON-NLS-1$
		while (iter.hasNext()) {
			HashMap childMap = (HashMap) iter.next();
			if (SAXParserXMI.getTag(childMap).equals(elementTag))
				return childMap;
		}
		return null;
	}

	/**
	 * For use with TNGSAXParserXMI. returns Tag of element. Does not return
	 * null, returns "" instead.
	 * 
	 * @param eMap
	 * @return String
	 */
	public static String getTag(HashMap eMap) {
		String tag = ""; //$NON-NLS-1$
		if (eMap != null)
			tag = (String) eMap.get("tag"); //$NON-NLS-1$
		if (tag != null)
			return tag;
		return ""; //$NON-NLS-1$
	}

	/**
	 * For use with TNGSAXParserXMI. gets content of element - can be null
	 * 
	 * @param eMap
	 * @return String
	 */
	public static String getElementContent(HashMap eMap) {
		String content = null;
		if (eMap != null)
			content = (String) eMap.get("content"); //$NON-NLS-1$
		return content;
	}

	/**
	 * Finds a guid contained within the given eMap - can return null
	 * 
	 * @param guid
	 *            guid to find
	 * @param eMap
	 *            use TNGSAXParserXMI.xmiMap, or a sub-eMap
	 * @return HashMap
	 */
	public static HashMap findGuid(String guid, HashMap eMap) {
		ArrayList attList = (ArrayList) eMap.get("attributes"); //$NON-NLS-1$
		if (guid.equals(SAXParserXMI.getGuidOfList(attList)))
			return eMap;

		Iterator iter = ((ArrayList) eMap.get("children")).iterator(); //$NON-NLS-1$
		while (iter.hasNext()) {
			HashMap childMap = (HashMap) iter.next();
			HashMap result = findGuid(guid, childMap);
			if (result != null)
				return result;
		}
		return null;
	}

	/**
	 * For use with TNGSAXParserXMI.
	 * 
	 * @param attList
	 * @return String
	 */
	public static String getGuidOfList(ArrayList attList) {
		Iterator iter = attList.iterator();
		while (iter.hasNext()) {
			String attpair = (String) iter.next();
			String attname = attpair.substring(0, attpair.indexOf(",")); //$NON-NLS-1$
			String attval = attpair.substring(attpair.indexOf(",") + 1); //$NON-NLS-1$
			if (attname.equals("guid")) //$NON-NLS-1$
				return attval;
		}
		return null;
	}

	/**
	 * For use with TNGSAXParserXMI when parsing resmgr.xmi
	 * 
	 * @param attList
	 * @return String
	 */
	public static String getIdOfList(ArrayList attList) {
		Iterator iter = attList.iterator();
		while (iter.hasNext()) {
			String attpair = (String) iter.next();
			String attname = attpair.substring(0, attpair.indexOf(",")); //$NON-NLS-1$
			String attval = attpair.substring(attpair.indexOf(",") + 1); //$NON-NLS-1$
			if (attname.equals("id")) //$NON-NLS-1$
				return attval;
		}
		return null;
	}

	/**
	 * 
	 * @param eMap
	 * @return URI
	 */
	public static URI getPresentationUriOfElement(HashMap eMap) {
		if (eMap == null)
			return null;
		String tag = SAXParserXMI.getTag(eMap);

		// // check to see if this is an breakdownElement - if so, return null
		// // because while breakdownElements have presentations, they are not
		// used.
		// if (tag.equals("breakdownElements")) {
		// return null;
		// }
		// // processes don't use their presentation either
		// if (tag.equals("process")) {
		// return null;
		// }

		// look for childPackage presentations, which are an href attribute
		if (tag.equals("childPackages")) { //$NON-NLS-1$
			ArrayList attList = (ArrayList) eMap.get("attributes"); //$NON-NLS-1$
			Iterator iter = attList.iterator();
			while (iter.hasNext()) {
				String attpair = (String) iter.next();
				String attname = attpair.substring(0, attpair.indexOf(",")); //$NON-NLS-1$
				String attval = attpair.substring(attpair.indexOf(",") + 1); //$NON-NLS-1$
				if (attname.equals("href")) { //$NON-NLS-1$
					try {
						URI uri = URI.createURI(attval);
						return uri;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					// if (attval.indexOf("uma://") != -1) {
					// String presId =
					// attval.substring(attval.indexOf("uma://")+6);
					// return presId;
					// }
				}
			}
		}

		ArrayList childList = (ArrayList) eMap.get("children"); //$NON-NLS-1$
		Iterator iter = childList.iterator();
		while (iter.hasNext()) {
			HashMap childMap = (HashMap) iter.next();
			tag = SAXParserXMI.getTag(childMap);
			if (tag.equals("presentation")) { // || //$NON-NLS-1$
				// tag.equals("variabilityBasedOnElement"))
				// { //$NON-NLS-1$ //$NON-NLS-2$
				ArrayList attList = (ArrayList) childMap.get("attributes"); //$NON-NLS-1$
				Iterator attIter = attList.iterator();
				while (attIter.hasNext()) {
					String attpair = (String) attIter.next();
					String attname = attpair.substring(0, attpair.indexOf(",")); //$NON-NLS-1$
					String attval = attpair.substring(attpair.indexOf(",") + 1); //$NON-NLS-1$
					if (attname.equals("href")) { //$NON-NLS-1$
						try {
							URI uri = URI.createURI(attval);
							return uri;
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						// if (attval.indexOf("uma://") != -1) {
						// String presId =
						// attval.substring(attval.indexOf("uma://")+6);
						// return presId;
						// }
					}
				}

				// if (attList.size() == 0)
				// continue;
				// String attpair = (String)attList.get(0);
				// // String attname = attpair.substring(0,
				// attpair.indexOf(","));
				// String attval = attpair.substring(attpair.indexOf(",")+1);
				// if (attval.indexOf("uma://") != -1) {
				// String presId = attval.substring(attval.indexOf("uma://")+6);
				// return presId;
				// }
			}
		}
		return null;
	}

	/**
	 * 
	 * @param eMap
	 * @param attributeName
	 * @param attributeValue
	 * @return HashMap
	 */
	public static HashMap findElementByAttribute(HashMap eMap,
			String attributeName, String attributeValue) {
		// search this eMap's children
		Iterator iter = ((ArrayList) eMap.get("children")).iterator(); //$NON-NLS-1$
		while (iter.hasNext()) {
			HashMap childMap = (HashMap) iter.next();
			HashMap result = findElementByAttribute(childMap, attributeName,
					attributeValue);
			if (result != null)
				return result;
		}

		// search this eMap
		iter = ((ArrayList) eMap.get("attributes")).iterator(); //$NON-NLS-1$
		while (iter.hasNext()) {
			String attpair = (String) iter.next();
			String attname = attpair.substring(0, attpair.indexOf(",")); //$NON-NLS-1$
			String attval = attpair.substring(attpair.indexOf(",") + 1); //$NON-NLS-1$
			if (attname.equals(attributeName) && attval.equals(attributeValue))
				return eMap;
		}
		return null;
	}

	/**
	 * 
	 * @param eMap
	 * @param attributeName
	 * @return String
	 */
	public static String getAttributeOfElement(HashMap eMap,
			String attributeName) {
		Iterator iter = ((ArrayList) eMap.get("attributes")).iterator(); //$NON-NLS-1$
		while (iter.hasNext()) {
			String attpair = (String) iter.next();
			String attname = attpair.substring(0, attpair.indexOf(",")); //$NON-NLS-1$
			String attval = attpair.substring(attpair.indexOf(",") + 1); //$NON-NLS-1$
			if (attname.equals(attributeName))
				return attval;
		}
		return null;
	}

}
