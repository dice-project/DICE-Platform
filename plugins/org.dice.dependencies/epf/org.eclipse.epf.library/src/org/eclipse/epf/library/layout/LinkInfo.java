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
package org.eclipse.epf.library.layout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;

import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.common.xml.XSLTProcessor;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.layout.util.XmlHelper;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.publish.layout.LayoutPlugin;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.ProcessComponent;

/**
 * class to process an element link in the content
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class LinkInfo {

	public static final String LINK_ATTR_CLASS = "class"; //$NON-NLS-1$

	public static final String LINK_ATTR_GUID = "guid"; //$NON-NLS-1$

	public static final String LINK_ATTR_HREF = "href"; //$NON-NLS-1$

	public static final String LINK_ATTR_KEY = "key"; //$NON-NLS-1$

	public static final String LINK_ATTR_TEXT = "text"; //$NON-NLS-1$

	public static final String LINK_ATTR_NAME = "name"; //$NON-NLS-1$

	private String linkedText = ""; //$NON-NLS-1$

	private MethodElement linkElement = null;

	private MethodElement ownerElement = null;

	private boolean isMissingReference = false;

	private Map<String, String> attributeMap = new LinkedHashMap<String, String>();

	private IContentValidator validator;

	private String pubDir;
	
	private String tag;
	
	//Temp solution for getting around without having a libMgr during migration
	//Should be removed when lib loaded during migration is registed with Lib service.
	private static MethodLibrary library;

	/**
	 * constructor
	 * 
	 * @param owner
	 * @param validator
	 * @param pubDir
	 */
	public LinkInfo(MethodElement owner, IContentValidator validator,
			String pubDir, String tag) {
		this.ownerElement = owner;
		this.validator = validator;
		this.pubDir = pubDir;
		this.tag = tag;
	}

	/**
	 * is this an element link?
	 * @return boolean
	 */
	public boolean isElementLink() {
		return ResourceHelper.isElementLink(getElementLinkType());
	}

	/**
	 * get the element link type
	 * @return String
	 */
	public String getElementLinkType() {
		return getAttribute(LINK_ATTR_CLASS);
	}

	/**
	 * get the guid of the element link
	 * @return String
	 */
	public String getGuid() {
		return getAttribute(LINK_ATTR_GUID);
	}

	/**
	 * get the url of the link
	 * @return String
	 */
	public String getUrl() {
		return getAttribute(LINK_ATTR_HREF);
	}

	/**
	 * get the linked text
	 * @return String
	 */
	public String getLinkedText() {
		return linkedText;
	}

	/**
	 * get the linked element
	 * @return MethodElement
	 */
	public MethodElement getLinkedElement() {
		return linkElement;
	}

	/**
	 * get the html for the link.
	 * 
	 * @param showBrokenLinks boolean true to show the broken link mark. false to show text only
	 * @return StringBuffer
	 */
	public StringBuffer getHtml(boolean showBrokenLinks) {
		StringBuffer buffer = new StringBuffer();

		if (isMissingReference && (pubDir != null)) {
			String url = ""; //$NON-NLS-1$
			if (!showBrokenLinks) {
				buffer.append(linkedText);
				return buffer;
			}

			url = getMissingElementUrl();
			buffer
					.append("<img alt=\"\" src=\"" + ResourceHelper.getBackPath(ownerElement) + "images/no_rup.gif\">&#160;"); //$NON-NLS-1$ //$NON-NLS-2$

			setUrl(url);
		}

		buffer.append("<" + tag); //$NON-NLS-1$

		for (Iterator<Map.Entry<String, String>> it = attributeMap.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = it.next();
			String key = entry.getKey();
			String value = entry.getValue();
			if (LINK_ATTR_HREF.equals(key)
					&& (value == null || value.length() == 0)) {
				continue;
			}

			buffer.append(" ") //$NON-NLS-1$
					.append(entry.getKey()).append("=") //$NON-NLS-1$
					.append("\"").append(entry.getValue()).append("\""); //$NON-NLS-1$ //$NON-NLS-2$
		}

		buffer.append(">").append(linkedText).append("</" + tag + ">"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		return buffer;
	}

	/**
	 * validate the link attributes
	 * @param attributes String the link attributes
	 * @param text String the linked text
	 * @param config MethodConfiguration
	 */
	public void validateLink(String attributes, String text,
			MethodConfiguration config) {
		this.linkedText = text;

		Matcher m2 = ResourceHelper.p_tag_attributes.matcher(attributes);
		while (m2.find()) {
			String attrName = m2.group(1).trim().toLowerCase();
			String attrValue = ""; //$NON-NLS-1$
			if (m2.group(3) != null) {
				attrValue = m2.group(3).trim();
			} else if (m2.group(2) != null) {
				attrValue = m2.group(2).trim();
			}
			
			/*
			 * if (attrValue.startsWith("\"")) { attrValue =
			 * attrValue.substring(1); } if (attrValue.endsWith("\"")) {
			 * attrValue = attrValue.substring(0, attrValue.length() - 1); }
			 */

			// GVT: Cannot Preview/Browse Description Tab when
			// the CP contains chinese characters
			// generated html from xslt got the href urls encoded. we don't want
			// that make sure decode the url using "UTF-8" encoding
			if (attrName.equals(LINK_ATTR_HREF)) {
				try {
					attrValue = decode(attrValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			attributeMap.put(attrName, attrValue);
		}

		String guid = getGuid();
		if (guid == null) {
			// check if the file contains guid, if yes, need to get the element
			String url = getUrl();
			guid = ResourceHelper.getGuidFromFileName(url);
			if (guid != null) {
				setGuid(guid);
			}
		}

		validateElementUrl(config);
	}

	protected String decode(String str) throws UnsupportedEncodingException {
		if (NetUtil.isRawUrl(str)) {
			return str;
		}
		
		return URLDecoder.decode(str, "UTF-8"); //$NON-NLS-1$
	}

	/**
	 * get attribute
	 * @param name
	 * @return String
	 */
	private String getAttribute(String name) {
		return (String) attributeMap.get(name);
	}

	/**
	 * set guid
	 * @param guid String
	 */
	private void setGuid(String guid) {
		attributeMap.put(LINK_ATTR_GUID, guid);
	}

	public void setUrl(String url) {
		attributeMap.put(LINK_ATTR_HREF, url);
	}

	private void validateElementUrl(MethodConfiguration config) {
		String guid = getGuid();
		if (guid != null) {
			MethodElement e = null;
			e = getMethodElement(guid);

			// if process component, get show the process
			if (e instanceof ProcessComponent) {
				e = ((ProcessComponent) e).getProcess();
			}

			// 156474 - Process Package (either CP package or DP package) can
			// not be linked in RTE
			// don't show link for packages.
			if (e instanceof MethodPackage || e instanceof MethodConfiguration
					|| e instanceof MethodLibrary) {
				e = null;
				// remove the link atytribute
				setUrl(null);
				return;
			}

			if (e != null) {
				if (config != null) {
					MethodElement e1 = ConfigurationHelper
							.getCalculatedElement(e, config);
					if (e1 != null) {				
						if (! validator.showBrokenLinks() && validator.isDiscarded(ownerElement, null, e1)) {
							isMissingReference = true;
							validator.logMissingReference(ownerElement, e1);
						} else {
							e = e1;
						}
					} else {
						isMissingReference = true;
						validator.logMissingReference(ownerElement, e);
					}
				}
			} else {
				boolean toLog = true;
				if (ownerElement instanceof CustomCategory) {
					if (MethodElementPropUtil.getMethodElementPropUtil().isTransientElement(ownerElement)) {
						toLog = false;
					}
				}
				if (toLog) {
					isMissingReference = true;
					validator.logMissingReference(ownerElement, guid, linkedText);
				}
			}

			if (e != null) {
				guid = e.getGuid();
				setGuid(guid);
				String url = getUrl();

				// url can be null, for example, an element link for
				// presentation name, such as
				// <a class="PresentationName"
				// guid="{EE97A8CD-66CA-4A9B-9871-E3B94CCED528}">Project
				// Manager</a>
				// we only need to get the element from the guid
				if (url != null) {
					url = url.trim();
					if (!url.toLowerCase().startsWith(
							ResourceHelper.URL_STR_JAVASCRIPT)) {
						url = ResourceHelper.getUrl(e, ownerElement,
								ResourceHelper.FILE_EXT_HTML, url);
						setUrl(url);
					}
				}

				if (isElementLink() && !tag.equals("area")) { //$NON-NLS-1$
					String text = ResourceHelper.getLinkText(e,
							getElementLinkType(), config);
					if (text != null) {
						// if null, can be used text, don't reset it
						this.linkedText = text;
					}
				}

				if (validator.isDiscarded(ownerElement, IContentValidator.elementUrlFeature, e, config)) {
					isMissingReference = true;
					validator.logMissingReference(ownerElement, e);
					e = null; // ignore the element since it's discarded
				}
			}

			this.linkElement = e;

		}
	}

	public static MethodElement getMethodElement(String guid) {
		MethodElement e;
		ILibraryManager manager = LibraryService.getInstance()
				.getCurrentLibraryManager();
		if (manager != null && getLibrary() == null) {
			e = manager.getMethodElement(guid);
		} else {
			e = getMethodElement_(guid);
			if (e == null && manager != null) {
				e = manager.getMethodElement(guid);
			}
		}
		return e;
	}

	// private static final String MISSING_PAGES_XSL = "pages_not_installed/";
	// //$NON-NLS-1$
	private String getMissingElementUrl() {
		if (!isMissingReference) {
			return ""; //$NON-NLS-1$
		}

		String url = null;

		// if (linkElement == null) {
		// // this is an invalid element, maybe deleted from the library
		// // already
		// url = ResourceHelper.MISSING_PAGES_FOLDER + getGuid()
		// + ResourceHelper.FILE_EXT_HTML;
		// } else {
		// url = ResourceHelper.MISSING_PAGES_FOLDER
		// + ResourceHelper.getFileName(linkElement,
		// ResourceHelper.FILE_EXT_HTML);
		// }

		url = ResourceHelper.MISSING_PAGES_FOLDER + "pages_not_installed" //$NON-NLS-1$
				+ ResourceHelper.FILE_EXT_HTML;

		File f = new File(pubDir, url);
		File dir = f.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// if ( !f.exists() )
		{
			// generate the html file
			XmlElement xml = new XmlElement("Element"); //$NON-NLS-1$
			
			
			Locale locale = Locale.getDefault();
			String lang = locale.getLanguage();
			xml.setAttribute("lang", lang); //$NON-NLS-1$


			if (linkElement == null) {
				xml.setAttribute("invalidLink", "true") //$NON-NLS-1$ //$NON-NLS-2$
						.setAttribute("guid", getGuid()) //$NON-NLS-1$
						.setAttribute("name", linkedText); //$NON-NLS-1$
			} else {
				xml
						.setAttribute("guid", getGuid()) //$NON-NLS-1$
						.setAttribute("type", linkElement.getType().getName()) //$NON-NLS-1$
						.setAttribute("name", linkElement.getName()) //$NON-NLS-1$
						.setAttribute(
								"pluginName", LibraryUtil.getMethodPlugin(linkElement).getName()); //$NON-NLS-1$
			}
			String xslPath = LayoutResources.getDefaultXslPath(
					"page_not_installed", null); //$NON-NLS-1$
			saveHtml(xml, xslPath, f);
		}

		return ResourceHelper.getBackPath(ownerElement).replace(
				File.separatorChar, '/')
				+ url;
	}

	/**
	 * save a html file 
	 * @param xmlElement
	 * @param xsl_uri
	 * @param outputFile
	 * @deprecated this method id not used any more
	 */
	public void saveHtml(XmlElement xmlElement, String xsl_uri, File outputFile) {
		try {
			StringBuffer xml = new StringBuffer();
			xml.append(XmlHelper.XML_HEADER).append(xmlElement.toXml());

			OutputStreamWriter output = new OutputStreamWriter(
					new FileOutputStream(outputFile), "utf-8"); //$NON-NLS-1$
			Properties xslParams = LayoutPlugin.getDefault().getProperties(
					"/layout/xsl/resources.properties"); //$NON-NLS-1$

			XSLTProcessor.transform(xsl_uri, xml.toString(), xslParams, output);
			output.flush();
			output.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static MethodElement getMethodElement_(String guid) {
				try {
					if (getLibrary() == null) {
						return null;
					}
					ILibraryResourceSet resourceSet = (ILibraryResourceSet) getLibrary()
							.eResource().getResourceSet();
					if (resourceSet != null) {
						return (MethodElement) resourceSet.getEObject(guid);
					}
				} catch (Throwable th) {
					// Log error here
					th.printStackTrace();
				}
				return null;
			}

	public static MethodLibrary getLibrary() {
		return library;
	}

	public static void setLibrary(MethodLibrary library) {
		LinkInfo.library = library;
	}

}
