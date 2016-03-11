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

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.common.utils.FileUtil;


/**
 * Implements a XML element.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class XmlElement implements IXmlElement {

	public static final String BR = FileUtil.LINE_SEP;

	private static String NODE_ID = "nodeId"; //$NON-NLS-1$

	// private static String PARENT_NODE_ID = "parentNodeId";

	private String element_name = null;

	private String element_value = null;

	private Map attributes = new HashMap();

	private Map contents = new HashMap();

	private List children = new ArrayList();

	private String id = null;

	/**
	 * create an XmlElement
	 * 
	 * @param name String
	 */
	public XmlElement(String name) {
		this(name, null);
	}

	/**
	 * create an XmlElement
	 * @param name String
	 * @param id String
	 */
	public XmlElement(String name, String id) {
		this.element_name = name;
		
		if (id == null ) {
			//id = Integer.toHexString(this.toString().hashCode());
			id = getNodeId();
		}
		
		this.id = id;
		setAttribute(NODE_ID, this.id);
	}
	
	private String getNodeId() {			
		int original_hash = this.toString().hashCode();
		long confusion = System.currentTimeMillis();
		int confusion_hash = (int)(confusion ^ (confusion >>> 32));
		
		int result = original_hash + confusion_hash;
		
		return Integer.toHexString(result);		
	}
	
	/**
	 * set attribute
	 * @param name
	 * @param value
	 * @return XmlElement the element itself
	 */
	public XmlElement setAttribute(String name, String value) {
		attributes.put(name, value);
		return this;
	}

	public String getAttribute(String name) {
		Object value = attributes.get(name);
		return value instanceof String ? (String) value : null;
	}
	
	/**
	 * set the value of the element
	 * @param value
	 * @return XmlElement the element itself
	 */
	public XmlElement setValue(String value) {
		this.element_value = value;
		return this;
	}

	/**
	 * set the content of the element.
	 * @param name
	 * @param value
	 * @return XmlElement the element itself
	 */
	public XmlElement setContent(String name, Object value) {
		contents.put(name, value);
		return this;
	}

	/**
	 * add a child element
	 * @param child
	 * @return XmlElement the element itself
	 */
	public XmlElement addChild(XmlElement child) {
		children.add(child);
		return this;
	}

	/**
	 * @param child
	 * @return
	 */
	public XmlElement removeChild(XmlElement child) {
		if (children.remove(child)) {
			return child;
		}
		return null;
	}
	
	/**
	 * create a new chiild element
	 * @param name
	 * @return XmlElement the child element
	 */
	public XmlElement newChild(String name) {
		return newChild(name, null);
	}
	
	/**
	 * create a new child element
	 * @param name
	 * @param id
	 * @return XmlElement the child element
	 */
	public XmlElement newChild(String name, String id) {
		XmlElement child = new XmlElement(name, id);
		children.add(child);
		return child;
	}

	/**
	 * get the id
	 * @return String
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * get the xml string
	 * @return StringBuffer
	 */
	public StringBuffer toXml() {
		StringBuffer buffer = new StringBuffer();
		if (contents.size() == 0 && children.size() == 0) {
			buffer.append(
					XmlHelper.getElement(element_name, element_value,
							attributes)).append(BR);
		} else {
			buffer.append(XmlHelper.beginElement(element_name, attributes))
					.append(BR);

			// process the contents
			for (Iterator it = contents.entrySet().iterator(); it.hasNext();) {
				Map.Entry entry = (Map.Entry) it.next();
				String key = (String) entry.getKey();
				Object value = entry.getValue();
				if (value instanceof List) {
					for (Iterator itList = ((List) value).iterator(); itList
							.hasNext();) {
						buffer.append(
								XmlHelper.getElement(key, itList.next()
										.toString(), null)).append(BR);
					}
				} else {
					buffer
							.append(
									XmlHelper
											.getElement(
													key,
													(value == null) ? "" : value.toString(), null)).append(BR); //$NON-NLS-1$
				}
			}

			// process the children
			for (Iterator it = children.iterator(); it.hasNext();) {
				IXmlElement child = (IXmlElement) it.next();
				buffer.append(child.toXml());
			}

			buffer.append(XmlHelper.endElement(element_name)).append(BR);
		}

		return buffer;
	}

	/**
	 * save the element's xml to a file
	 * @param filePathName
	 */
	public void saveTo(String filePathName) {
		StringBuffer xml = new StringBuffer();
		xml.append(XmlHelper.XML_HEADER).append(this.toXml());

		try {
			// may need to create the folders, TODO
			/*
			 * File xf = new File( filePath); if ( !xf.exists() ) { xf.mkdirs(); }
			 */

			// need to enfore utf-8 encoding, can't use FileWriter, jxi
			// FileWriter xw = new FileWriter(filePathName);
			OutputStreamWriter xw = new OutputStreamWriter(
					new FileOutputStream(filePathName), "utf-8"); //$NON-NLS-1$

			xw.write(xml.toString());
			xw.flush();
			xw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
