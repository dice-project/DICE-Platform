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
package org.eclipse.epf.common.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.common.utils.XMLUtil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * An abstract SAX parser that can be used to parse a single XML file or a
 * directory of XML files.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public abstract class AbstractSAXParser extends DefaultHandler {

	/**
	 * The logger used for logging warnings, errors and exceptions.
	 */
	protected Logger logger;

	/**
	 * The name of the current XML file being parsed.
	 */
	protected String xmlFile;

	/**
	 * The String buffer for accumulating text.
	 */
	protected StringBuffer strBuf;

	/**
	 * The XML validating flag.
	 */
	private boolean validating;

	/**
	 * Creates a new <code>AbstractSaxParser</code> given the validating flag.
	 * 
	 * @param logger
	 *            The logger for logging warnings and errors.
	 * @param validating
	 *            If true, validate the XML document.
	 */
	public AbstractSAXParser(Logger logger, boolean validating) {
		this.logger = logger;
		this.validating = validating;
	}

	/**
	 * Parses the given XML directory or file.
	 * 
	 * @param xmlPath
	 *            The XML directory or file.
	 */
	public void parse(String xmlPath) {
		String pathName = StrUtil.replace(xmlPath, "%20", " "); //$NON-NLS-1$ //$NON-NLS-2$
		File path = new File(pathName);
		File[] files = path.isFile() ? new File[] { path } : path.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isDirectory()) {
					try {
						parseFile(files[i].getAbsoluteFile());
					} catch (Exception e) {
						if (logger != null) {
							logger
									.logError(
											"Failed to parse \"" + files[i].getAbsolutePath() + '"', e); //$NON-NLS-1$						
						}
					}
				}
			}
		}
	}

	/**
	 * Parses the given XML file.
	 * 
	 * @param xmlFile
	 *            The XML file.
	 */
	protected void parseFile(File xmlFile) throws ParserConfigurationException,
			IOException, SAXException {
		// Create the SAX parser factory.
		SAXParserFactory spf = SAXParserFactory.newInstance();

		// Set the XML validating flag.
		spf.setValidating(validating);

		// Create the SAX parser.
		SAXParser saxParser = spf.newSAXParser();

		// Get the SAX XML reader.
		XMLReader xmlReader = saxParser.getXMLReader();

		// Set the SAX parser handlers to this class.
		xmlReader.setContentHandler(this);
		xmlReader.setErrorHandler(this);

		// Instruct the SAX parser to parse the given XML file.
		this.xmlFile = xmlFile.getAbsolutePath();
		xmlReader.parse(NetUtil.getUri(xmlFile));
	}

	/**
	 * Called when the XML parser starts to parse the document.
	 */
	public void startDocument() {
		strBuf = new StringBuffer();
	}

	/**
	 * Called when the the XML parser encounters the chararacter data within a
	 * text element.
	 */
	public void characters(char[] buffer, int start, int length) {
		strBuf.append(buffer, start, length);
	}

	/**
	 * Called when the parser starts to parse each new element.
	 */
	public abstract void startElement(String namespaceURL, String localName,
			String qname, Attributes attributes);

	/**
	 * Called when the XML parser reaches the end of the an element.
	 */
	public abstract void endElement(String namespaceURL, String localName,
			String qname);

	/**
	 * Called when the XML parser reaches the end of the document.
	 */
	public void endDocument() {
	}

	/**
	 * Logs the SAX parsing warning.
	 * 
	 * @param e
	 *            The SAX parse exception.
	 */
	public void warning(SAXParseException e) {
		if (logger != null) {
			logger
					.logWarning("SAX parsing warning encountered at " //$NON-NLS-1$
							+ XMLUtil.getLocationOfException(e)
							+ ": " + e.getMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Logs the SAX parsing error.
	 * 
	 * @param e
	 *            The SAX parse exception.
	 */
	public void error(SAXParseException e) {
		if (logger != null) {
			logger
					.logError("SAX parsing error encountered at " //$NON-NLS-1$
							+ XMLUtil.getLocationOfException(e)
							+ ": " + e.getMessage()); //$NON-NLS-1$
		}
	}

	/**
	 * Logs the SAX non-recoverable error and exits.
	 * 
	 * @param e
	 *            The SAX parse exception.
	 */
	public void fatalError(SAXParseException e) throws SAXException {
		if (logger != null) {
			logger
					.logError("SAX parsing fatal error encountered at " //$NON-NLS-1$
							+ XMLUtil.getLocationOfException(e)
							+ ": " + e.getMessage()); //$NON-NLS-1$
			logger.logError(e);
		}
	}

	/**
	 * Returns the current XML file that is being parsed.
	 * 
	 * @return The name of the XML file.
	 */
	public String getXMLFile() {
		return xmlFile;
	}

}