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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.tools.ant.util.ReaderInputStream;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

/**
 * Utility class for processing XML documents.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class XMLUtil {

	/**
	 * XML declaration.
	 */
	public final static String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; //$NON-NLS-1$

	/**
	 * XML Escape characters.
	 */
	public final static String XML_AMP = "&amp;"; //$NON-NLS-1$

	public final static String XML_BACKSLASH = "&#92;"; //$NON-NLS-1$	

	public final static String XML_APOS = "&apos;"; //$NON-NLS-1$

	public final static String XML_CR = "&#13;"; //$NON-NLS-1$

	public final static String XML_GT = "&gt;"; //$NON-NLS-1$

	public final static String XML_LT = "&lt;"; //$NON-NLS-1$

	public final static String XML_LF = "&#10;"; //$NON-NLS-1$	

	public final static String XML_QUOT = "&quot;"; //$NON-NLS-1$

	public final static String XML_TAB = "&#9;"; //$NON-NLS-1$

	private static final String CRLF = "\r\n"; //$NON-NLS-1$

	private static final byte[] CRLF_BYTES = CRLF.getBytes();

	/**
	 * Private constructor to prevent this class from being instantiated. All
	 * methods in this class should be static.
	 */
	private XMLUtil() {
	}

	/**
	 * Clones the given DOM node into the given DOM document.
	 * 
	 * @param node
	 *            The DOM node to clone.
	 * @param doc
	 *            The target DOM document.
	 * @return The cloned node in the target DOM document.
	 */
	public static Node cloneNode(Node node, Document doc) {
		Node clone = null;
		switch (node.getNodeType()) {
		case Node.ELEMENT_NODE:
			clone = doc.createElement(node.getNodeName());
			NamedNodeMap attrs = node.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++) {
				Node attrNode = attrs.item(i);
				Attr attrClone = doc.createAttribute(attrNode.getNodeName());
				attrClone.setNodeValue(attrNode.getNodeValue());
				((Element) clone).setAttributeNode(attrClone);
			}

			// Iterate through each child nodes.
			NodeList childNodes = node.getChildNodes();
			if (childNodes != null) {
				for (int i = 0; i < childNodes.getLength(); i++) {
					Node childNode = childNodes.item(i);
					if (childNode == null) {
						continue;
					}
					Node childClone = cloneNode(childNode, doc);
					if (childClone == null) {
						continue;
					}
					clone.appendChild(childClone);
				}
			}
			break;

		case Node.TEXT_NODE:
		case Node.CDATA_SECTION_NODE:
			clone = doc.createTextNode(node.getNodeName());
			clone.setNodeValue(node.getNodeValue());
			break;
		}
		return clone;
	}

	/**
	 * Escapes a XML attribute to make it XML parser friendly.
	 * 
	 * @param str
	 *            The XML attribute string.
	 * @return The escaped string.
	 */
	public static String escapeAttr(String str) {
		if (str == null || str.length() == 0)
			return ""; //$NON-NLS-1$
		StringBuffer sb = new StringBuffer();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case '<':
				sb.append(XML_LT);
				break;
			case '&':
				sb.append(XML_AMP);
				break;
			case '"':
				sb.append(XML_QUOT);
				break;
			default:
				sb.append(ch);
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * Escapes the given string to make it XML parser friendly.
	 * 
	 * @param str
	 *            The source string.
	 * @return The escaped string.
	 */
	public static String escape(String str) {
		if (str == null || str.length() == 0)
			return ""; //$NON-NLS-1$
		StringBuffer sb = new StringBuffer();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case '<':
				sb.append(XML_LT);
				break;
			case '>':
				sb.append(XML_GT);
				break;
			case '&':
				sb.append(XML_AMP);
				break;
			case '"':
				sb.append(XML_QUOT);
				break;
			case '\'':
				sb.append(XML_APOS);
				break;
			case '\r':
				sb.append(XML_CR);
				break;
			case '\n':
				sb.append(XML_LF);
				break;
			case '\\':
				sb.append(XML_BACKSLASH);
				break;
			default:
				sb.append(ch);
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * Escapes the given string to make it XML parser friendly.
	 * 
	 * @param str
	 *            The source string.
	 * @param ignoreCRLF
	 *            If true, do not escape the CR and LF characters.
	 * @return The escaped string.
	 */
	public static String escape(String str, boolean ignoreCRLF) {
		if (str == null || str.length() == 0)
			return ""; //$NON-NLS-1$
		StringBuffer sb = new StringBuffer();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case '<':
				sb.append(XML_LT);
				break;
			case '>':
				sb.append(XML_GT);
				break;
			case '&':
				// This is to avoid the double escaping (see Bugzilla 179921)
				if (!str.startsWith(XML_LT+"p/", i) && !str.startsWith(XML_APOS, i) //$NON-NLS-1$ 
						&& !str.startsWith(XML_AMP, i))
					sb.append(XML_AMP);
				else
					sb.append(ch);
				break;
			case '"':
				sb.append(XML_QUOT);
				break;
			case '\'':
				sb.append(XML_APOS);
				break;
			case '\r':
				if (ignoreCRLF)
					sb.append(ch);
				else
					sb.append(XML_CR);
				break;
			case '\n':
				if (ignoreCRLF)
					sb.append(ch);
				else
					sb.append(XML_LF);
				break;
			default:
				sb.append(ch);
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * Unescapes the given XML string.
	 * 
	 * @param str
	 *            The source string.
	 * @return The escaped string.
	 */
	public static String unescape(String str) {
		if (str == null || str.length() == 0)
			return ""; //$NON-NLS-1$
		StringBuffer sb = new StringBuffer();
		int len = str.length();
		for (int i = 0; i < len; i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case '&':
				if (str.startsWith(XML_LT, i)) {
					sb.append('<');
					i += 3;
				} else if (str.startsWith(XML_GT, i)) {
					sb.append('>');
					i += 3;
				} else if (str.startsWith(XML_AMP, i)) {
					sb.append('&');
					i += 4;
				} else if (str.startsWith(XML_QUOT, i)) {
					sb.append('"');
					i += 5;
				} else if (str.startsWith(XML_APOS, i)) {
					sb.append("\'"); //$NON-NLS-1$
					i += 5;
				} else if (str.startsWith(XML_CR, i)) {
					sb.append('\r');
					i += 4;
				} else if (str.startsWith(XML_LF, i)) {
					sb.append('\n');
					i += 4;
				} else {
					sb.append(ch);
				}
				break;
			default:
				sb.append(ch);
				break;
			}
		}
		return sb.toString();
	}

	/**
	 * Writes the content of the given DOM document to the output stream.
	 * 
	 * @param xmlDoc
	 *            The DOM document.
	 * @param output
	 *            The output stream.
	 * @throws IOException
	 *             if an I/O error occur while accessing the output stream.
	 */
	public static void writeDocument(Document xmlDoc, OutputStream output)
			throws IOException {
		DataOutputStream out = new DataOutputStream(output);
		writeNode(xmlDoc, "", out); //$NON-NLS-1$
		out.flush();
	}

	/**
	 * Writes the content of the given DOM document to the PrintWriter.
	 * 
	 * @param xmlDoc
	 *            The DOM document.
	 * @param pw
	 *            The PrintWriter object.
	 * @throws IOException
	 *             if an I/O error occur while accessing the output stream.
	 */
	public static void writeDocument(Document xmlDoc, PrintWriter pw)
			throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(os);
		writeNode(xmlDoc, "", out); //$NON-NLS-1$
		out.flush();
		// FIXME! Need to specify encoding?
		String s = os.toString();
		pw.write(s);
		pw.flush();
	}

	/**
	 * Saves the content of the given DOM document to file.
	 * 
	 * @param xmlDoc
	 *            The DOM document.
	 * @param xmlFile
	 *            The XML file.
	 * @throws IOException
	 *             if an I/O error occur while accessing the output stream.
	 */
	public static void saveDocument(Document xmlDoc, String xmlFile)
			throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream(
				xmlFile));
		writeNode(xmlDoc, "", out); //$NON-NLS-1$
		out.flush();
		out.close();
	}

	/**
	 * Saves the given XML string to the given file.
	 * 
	 * @param xmlStr
	 *            The XML string.
	 * @param xmlFile
	 *            The XML file.
	 * @throws IOException
	 *             if an I/O error occur while accessing the output stream.
	 */
	public static void saveDocument(String xmlStr, String xmlFile)
			throws IOException {
		DataOutputStream out = new DataOutputStream(new FileOutputStream(
				xmlFile));
		out.write(xmlStr.getBytes());
		out.flush();
		out.close();
	}

	/**
	 * Writes the given DOM tree node to the given output stream.
	 * 
	 * @param node
	 *            The DOM node.
	 * @param indent
	 *            The string indentation (containing space characters).
	 * @param out
	 *            The output stream.
	 * @throws IOException
	 *             if an I/O error occur while accessing the output stream.
	 */
	private static void writeNode(Node node, String indent, DataOutputStream out)
			throws IOException {
		String text;

		switch (node.getNodeType()) {
		case Node.DOCUMENT_NODE:
			// Write the XML file signature.
			out.write(StrUtil.getUTF8Bytes(XML_DECLARATION));
			out.write(CRLF_BYTES);

			// Iterate through each child nodes.
			NodeList nodes = node.getChildNodes();
			if (nodes != null) {
				for (int i = 0; i < nodes.getLength(); i++) {
					writeNode(nodes.item(i), "", out); //$NON-NLS-1$
				}
			}
			break;

		case Node.ELEMENT_NODE:
			String name = node.getNodeName();
			out.write(StrUtil.getUTF8Bytes(indent + "<" + name)); //$NON-NLS-1$
			NamedNodeMap attrs = node.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++) {
				Node attrNode = attrs.item(i);
				out.write(StrUtil.getUTF8Bytes(" " + attrNode.getNodeName() //$NON-NLS-1$
						+ "=\"" + escape(attrNode.getNodeValue()) + "\"")); //$NON-NLS-1$ //$NON-NLS-2$
			}
			out.write(StrUtil.getUTF8Bytes(">")); //$NON-NLS-1$
			out.write(CRLF_BYTES);

			// Iterate through each child nodes.
			NodeList childNodes = node.getChildNodes();
			if (childNodes != null) {
				for (int i = 0; i < childNodes.getLength(); i++) {
					writeNode(childNodes.item(i), indent, out);
				}
			}
			out.write(StrUtil.getUTF8Bytes(indent + "</" + name + ">")); //$NON-NLS-1$ //$NON-NLS-2$
			out.write(CRLF_BYTES);

			break;

		case Node.TEXT_NODE:
			text = StrUtil.removeWhiteSpaceChars(node.getNodeValue());
			if (text.length() > 0) {
				out.write(StrUtil.getUTF8Bytes(escape(text)));
			}
			break;

		case Node.CDATA_SECTION_NODE:
			text = StrUtil.removeWhiteSpaceChars(node.getNodeValue());
			if (text.length() > 0) {
				out.write(StrUtil.getUTF8Bytes("<![CDATA[")); //$NON-NLS-1$
				out.write(StrUtil.getUTF8Bytes(text));
				out.write(StrUtil.getUTF8Bytes("]]>")); //$NON-NLS-1$
				out.write(CRLF_BYTES);
			}
			break;

		case Node.PROCESSING_INSTRUCTION_NODE:
			out.write(StrUtil.getUTF8Bytes("<?" + node.getNodeName() //$NON-NLS-1$
					+ " " + node.getNodeValue() + "?>")); //$NON-NLS-1$ //$NON-NLS-2$
			out.write(CRLF_BYTES);
			break;

		case Node.ENTITY_REFERENCE_NODE:
			out.write(StrUtil.getUTF8Bytes("&" + node.getNodeName() + ";")); //$NON-NLS-1$ //$NON-NLS-2$
			break;
		}
	}

	/**
	 * Returns the file location where the given SAX exception occurred.
	 * 
	 * @param e
	 *            The SAX parse exception.
	 * @return A string containing the file location where the exception
	 *         occurred.
	 */
	public static String getLocationOfException(SAXParseException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("row "); //$NON-NLS-1$
		sb.append(e.getLineNumber());
		sb.append(", col "); //$NON-NLS-1$
		sb.append(e.getColumnNumber());

		String systemId = e.getSystemId();
		if (systemId != null) {
			int index = systemId.lastIndexOf('/');
			if (index != -1) {
				systemId = systemId.substring(index + 1);
			}
			sb.append(" of XML document "); //$NON-NLS-1$
			sb.append(systemId);
		}

		return sb.toString();
	}

	public static Document loadXml(File file) throws Exception {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();

		return builder.parse(file);
	}

	public static Document loadXml(String xmlString) throws Exception {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		ReaderInputStream istrem = new ReaderInputStream(new StringReader(xmlString), "UTF-8"); //$NON-NLS-1$
		
		return builder.parse(istrem);
	}
	
	public static Document createDocument() throws Exception {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		return builder.newDocument();
	}

	public static String toXmlString(Document doc) throws Exception {
		DOMSource domSource = new DOMSource(doc);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.transform(domSource, result);
		writer.flush();

		return writer.toString();
	}
	
	/**
	 * text of a leaf node, without child element
	 * 
	 * @param tag
	 * @return String
	 */
	public static String getNodeText(Element tag) {
		String text = tag.toString();
		int i = text.indexOf(">"); //$NON-NLS-1$
		int j = text.lastIndexOf("</"); //$NON-NLS-1$
		if (i < 0 || j < 0 || j < i) {
			return ""; //$NON-NLS-1$
		}

		return text.substring(i + 1, j);
	}

	public static String getChildText(Element tag, String childTagName) {
		Element child = getFirstChild(tag, childTagName);
		if (child != null) {
			return getNodeText(child);
		}

		return ""; //$NON-NLS-1$
	}

	public static Element getFirstChild(Element tag, String childTagName) {
		NodeList nodes = tag.getElementsByTagName(childTagName);
		if (nodes == null || nodes.getLength() == 0) {
			return null;
		}

		return (Element) nodes.item(0);
	}

	/**
	 * iterator of all the children of the element
	 * 
	 * @param tag
	 * @return Iterator
	 */
	public static Iterator childIterator(Element tag) {
		NodeList nodes = tag.getChildNodes();

		// NodeList contains no Element nodes such as text nodes, ignore those
		List<Node> elements = new ArrayList<Node>();
		if (nodes != null) {
			int size = nodes.getLength();
			for (int i = 0; i < size; i++) {
				Node node = nodes.item(i);
				if (node instanceof Element) {
					elements.add(node);
				}
			}
		}

		return elements.iterator();
	}

	private static class NodeIterator implements Iterator {
		int currentIndex = -1;

		int size = 0;

		NodeList nodes = null;

		public NodeIterator(NodeList nodes) {
			this.nodes = nodes;
			if (nodes != null)
				size = nodes.getLength();
		}

		public void remove() {
			// Do nothing, this is a readonly iterator.
		}

		public boolean hasNext() {
			return currentIndex + 1 < size;
		}

		public Object next() {
			if (hasNext()) {
				return nodes.item(++currentIndex);
			}

			return null;
		}
	}

	/**
	 * iterator of all the children of the element
	 * 
	 * @param tag
	 * @return Iterator
	 */
	public static Iterator childIterator(Element tag, String childTagName) {
		NodeList nodes = tag.getElementsByTagName(childTagName);
		return new NodeIterator(nodes);
	}

	/**
	 * Gets the list of immediate child elements with the given tag name.
	 * 
	 * @param element
	 * @param tagName
	 * @return list of {@link Element} objects
	 */
	public static List<Element> getChildElementsByTagName(Element element, String tagName) {	
		List<Element> elements = new ArrayList<Element>();
		NodeList list = element.getChildNodes();
		int size = list.getLength(); 
		if(size > 0) {
			for (int i = 0; i < size; i++) {
				Node node = list.item(i);
				if(node instanceof Element) {
					Element e = (Element) node;
					if(e.getTagName().equals(tagName)) {
						elements.add(e);
					}
				}
			}
		}
		return elements;
	}
	
	public static List<String> getChildTextsByTagName(Element element, String tagName) {	
		List<Element> elements = getChildElementsByTagName(element, tagName);
		List <String> list = new ArrayList<String>();
		for (Element e : elements) {
			String textValue = e.getTextContent().trim();
			if (textValue.length() != 0) {
				list.add(textValue);
			}
		}
		return list;
	}
	
	public static String getFirstChildTextByTagName(Element element, String tagName) {	
		Element e = getFirstChildElementByTagName(element, tagName);
		return e == null ? null : e.getTextContent().trim();
	}
	
	/**
	 * Gets the first element with the given tag name in the immediate child elements.
	 * 
	 * @param element
	 * @param tagName
	 * @return
	 */
	public static Element getFirstChildElementByTagName(Node element, String tagName) {
		NodeList list = element.getChildNodes();
		int size = list.getLength(); 
		if(size > 0) {
			for (int i = 0; i < size; i++) {
				Node node = list.item(i);
				if(node instanceof Element) {
					Element e = (Element) node;
					if(e.getTagName().equals(tagName)) {
						return e;
					}
				}
			}
		}
		return null;
	}
	
	public static String removeBOM(String xml) {
		return xml.trim().replaceFirst("^([\\W]+)<", "<"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	public static String elementToString(Element element) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		writeNode(element, " ", dos); //$NON-NLS-1$
		
		return baos.toString();
	}
	
}
