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
package org.eclipse.epf.common.html;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import org.eclipse.epf.common.IHTMLParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Configuration;
import org.w3c.tidy.Tidy;

/**
 * Extracts the title, meta tags and text from a HTML file or source.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class DefaultHTMLParser implements IHTMLParser{

	private static final int BUFFER_SIZE = 4096;

	private static final String HTML_SCRIPT_TAG = "script"; //$NON-NLS-1$	

	private static final String HTML_TITLE_TAG = "title"; //$NON-NLS-1$

	private static final String HTML_META_TAG = "meta"; //$NON-NLS-1$

	protected Tidy tidy;

	private String title;

	private String summary;

	private String text;

	private Properties metaTags;

	private StringBuffer htmlText;

	/**
	 * Creates a new instance.
	 */
	public DefaultHTMLParser() {
		try {
			tidy = new Tidy();
			tidy.setXHTML(true);
			tidy.setDropEmptyParas(true);
			tidy.setDropFontTags(true);
			tidy.setQuiet(true);
			tidy.setShowWarnings(false);
			tidy.setSmartIndent(false);
			tidy.setTidyMark(false);
			tidy.setWraplen(132);
			tidy.setIndentAttributes(false);
			tidy.setIndentContent(false);
			tidy.setSpaces(2);
			tidy.setCharEncoding(Configuration.ISO2022);
//			tidy.setInputEncoding("UTF-8"); //$NON-NLS-1$
//			tidy.setOutputEncoding("UTF-8"); //$NON-NLS-1$
		} catch (Exception e) {
			tidy = null;
		}
	}

	/**
	 * Parses the given HTML file.
	 */
	public void parse(File file) throws Exception {
		if (tidy == null || !file.exists() || !file.canRead()) {
			return;
		}

		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); //$NON-NLS-1$
		BufferedReader br = new BufferedReader(isr);

		StringBuffer textBuffer = new StringBuffer(BUFFER_SIZE);
		char[] buffer = new char[BUFFER_SIZE];
		int charsRead;
		while ((charsRead = br.read(buffer, 0, BUFFER_SIZE)) > 0) {
			textBuffer.append(buffer, 0, charsRead);
		}

		parse(textBuffer.toString());

		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Parses the given HTML source.
	 */
	protected void parse(String htmlSource) throws Exception {
		title = ""; //$NON-NLS-1$
		summary = ""; //$NON-NLS-1$
		text = ""; //$NON-NLS-1$
		metaTags = new Properties();

		Document doc = getDocument(htmlSource);
		if (doc != null) {
			htmlText = new StringBuffer(1024);
			extract(doc.getChildNodes());
			text = htmlText.toString();
		}
	}

	/**
	 * Returns the title text.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the HTML meta tags.
	 */
	public Properties getMetaTags() {
		return metaTags;
	}

	/**
	 * Returns the summary.
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Returns the body text.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Returns the DOM document for the given HTML source.
	 */
	protected Document getDocument(String html) throws Exception {
		if (html == null || html.length() == 0) {
			return null;
		}

		ByteArrayInputStream input = new ByteArrayInputStream(html
				.getBytes("UTF-8")); //$NON-NLS-1$	
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		tidy.setErrout(pw);

		return tidy.parseDOM(input, output);
	}

	/**
	 * Extracts the title, meta tags and body text from the given nodes.
	 */
	protected void extract(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			String nodeName = node.getNodeName();
			switch (node.getNodeType()) {
			case Node.ELEMENT_NODE:
				if (!nodeName.equals(HTML_SCRIPT_TAG)) {
					NamedNodeMap attrs = node.getAttributes();
					for (int j = 0; j < attrs.getLength(); j++) {
						Node attrNode = attrs.item(j);
						String attrNodeName = attrNode.getNodeName();
						String attrNodeValue = attrNode.getNodeValue();
						if (attrNodeName.equals(HTML_TITLE_TAG)) {
							title = attrNodeValue;
						} else if (attrNodeName.equals(HTML_META_TAG)) {
							metaTags.put(attrNodeName, attrNodeValue);
						}
					}
					NodeList childNodes = node.getChildNodes();
					if (childNodes != null && childNodes.getLength() > 0) {
						extract(childNodes);
					}
				}
				break;
			case Node.TEXT_NODE:
				htmlText.append(node.getNodeValue()).append(' ');
				break;
			}
		}
	}

}
