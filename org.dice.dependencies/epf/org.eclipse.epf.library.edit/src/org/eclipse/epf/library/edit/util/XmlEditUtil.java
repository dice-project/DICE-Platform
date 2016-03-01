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
package org.eclipse.epf.library.edit.util;

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.util.MeList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
* @author Weiping Lu
*
* @since 1.5.1.3
*/
public class XmlEditUtil {

	private MethodElementPropUtil propUtil;
	private Document doc;
	public MethodElementPropUtil getPropUtil() {
		return propUtil;
	}

	public XmlEditUtil(MethodElementPropUtil propUtil) {
		this.propUtil = propUtil;
	}
	
	protected Document getDocument() throws Exception {
		if (doc == null) {
			doc = XMLUtil.createDocument();
		}
		return doc;
	}
	
	//Return the root element
	protected Element loadDocumentAndGetFirstElement(String xmlString) throws Exception {
		if (xmlString == null) {
			return null;
		}
		doc = XMLUtil.loadXml(xmlString);
		Node node = doc.getFirstChild();
		return node instanceof Element ? (Element) node : null;
	}
	
	protected Element createFirstElement(String name) throws Exception {
		Element element = getDocument().createElement(name);
		getDocument().appendChild(element);
		return element;
	}
	
	protected void storeToOwner(MethodElement owner, String propName)  throws Exception  {
		String value = XMLUtil.toXmlString(doc);
		propUtil.setStringValue(owner, propName, value);
	}
	
	public static String convertToGuidsString(Collection<? extends MethodElement> elements) {
		String value = ""; 	//$NON-NLS-1$
		if (elements == null || elements.isEmpty()) {
			return value;
		}
		for (MethodElement element : elements) {
			if (value.length() > 0) {
				value += MethodElementPropUtil.infoSeperator;
			}
			value += element.getGuid();
		}
		return value;
	}
	
	public static MeList convertToMethodElements(String guidsString, EClass type) {
		return convertToMethodElements(guidsString, type, uHandler);
	}
	
	public static MeList convertToMethodElements(String guidsString, EClass type, UnresolvedGuidHandler uHandler) {
		return convertToMethodElements(guidsString, type, uHandler, null);
	}
	
	public static MeList convertToMethodElements(String guidsString, EClass type, UnresolvedGuidHandler uHandler, Set<MethodElement> validSet) {
		MeList list = new MeList();
		if (guidsString == null || guidsString.length() == 0) {
			return list;
		}
		String[] guids = guidsString.split(MethodElementPropUtil.infoSeperator);
		if (guids == null || guids.length == 0) {
			return list;			
		}		
		
		boolean hasUnresolved = false;
		for (String guid : guids) {
			MethodElement element = LibraryEditUtil.getInstance().getMethodElement(guid);
			if (element == null && uHandler != null) {
				element = uHandler.getElement(guid);
				if (element != null) {
					hasUnresolved = true;
				}
			}
			if (element != null) {
				if (type == null || type.isSuperTypeOf(element.eClass())) {
					if (validSet == null || validSet.contains(element)) {
						list.add(element);
					}
				}
			}
		}
		if (hasUnresolved) {
			list.setHasUnresolved(true);
		}
		return list;
	}
		
	protected Integer getIntValue(Element element, String attName) {		
		String value = element.getAttribute(attName);
		if (value == null || value.length() == 0) {
			return null;
		}
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return null;
		}
	}

	protected void addClonedElement(Element parent, Element elementToClone)
			throws Exception {
		if (elementToClone == null) {
			return;
		}
		Node node = XMLUtil.cloneNode(elementToClone, getDocument());
		parent.appendChild(node);
	}
	
	public static UnresolvedGuidHandler uHandler = new UnresolvedGuidHandler();
	public static class UnresolvedGuidHandler {
		
		public MethodElement getElement(String guid) {
			return null;
		}
		
		public boolean hasUnresolvedElement() {
			return false;
		}
		
	}	
	
}
