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
package org.eclipse.epf.library.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.common.utils.NetUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.common.xml.XSLTProcessor;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.ILibraryResourceManager;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.BrowsingLayoutSettings;
import org.eclipse.epf.library.layout.DefaultContentValidator;
import org.eclipse.epf.library.layout.IContentValidator;
import org.eclipse.epf.library.layout.LayoutResources;
import org.eclipse.epf.library.layout.LinkInfo;
import org.eclipse.epf.library.layout.elements.ActivityLayout;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.layout.util.XmlHelper;
import org.eclipse.epf.persistence.MethodLibraryPersister;
import org.eclipse.epf.publish.layout.LayoutPlugin;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * @author Jinhua Xi
 * @since 1.0
 */
public class ResourceHelper {

	// public static final String BRACE_REPLACEMENT = "_BR_"; //$NON-NLS-1$
	// public static final String OPENBRACE_STRING = "\\{"; //$NON-NLS-1$

	public static final String URL_BOOKMARK_INDICATOR = "#"; //$NON-NLS-1$

	public static final String URL_PARAMETER_INDICATOR = "?"; //$NON-NLS-1$

	public static final String URL_PARAMETER_PROCESS = "proc"; //$NON-NLS-1$

	public static final String URL_STR_JAVASCRIPT = "javascript:"; //$NON-NLS-1$

	public static final String URL_STR_MAILTO = "mailto:"; //$NON-NLS-1$

	public static final String URL_PARAMETER_PATH = "path"; //$NON-NLS-1$

	public static final String TAG_ATTR_NAME = "name"; //$NON-NLS-1$

	public static final String TAG_ATTR_KEY = "key"; //$NON-NLS-1$

	public static final String TAG_ATTR_TEXT = "text"; //$NON-NLS-1$

	public static final String TAG_ATTR_CLASS = "class"; //$NON-NLS-1$

	public static final String TAG_ATTR_GUID = "guid"; //$NON-NLS-1$

	public static final String TAG_ATTR_HREF = "href"; //$NON-NLS-1$

	public static final String TAG_ATTR_VALUE_INDEX = "index"; //$NON-NLS-1$

	public static final String FILE_EXT_HTML = ".html"; //$NON-NLS-1$

	public static final String FILE_EXT_HTM = ".htm"; //$NON-NLS-1$

	public static final String FILE_EXT_JPEG = ".jpeg"; //$NON-NLS-1$

	public static final String MISSING_PAGES_FOLDER = "pages_not_installed/"; //$NON-NLS-1$

	public static final String ELEMENT_LINK_CLASS_elementLink = "elementLink"; //$NON-NLS-1$

	public static final String ELEMENT_LINK_CLASS_elementLinkWithType = "elementLinkWithType"; //$NON-NLS-1$

	public static final String ELEMENT_LINK_CLASS_elementLinkWithUserText = "elementLinkWithUserText"; //$NON-NLS-1$

	public static final String RESOURCE_FOLDER = MethodLibraryPersister.RESOURCE_FOLDER;

	public static final Pattern p_html_file_name = Pattern
			.compile("(.*),(.*)\\.html"); //$NON-NLS-1$

	// this one does not work when containing non-english characters, use the
	// more general one
	// public static final Pattern p_link_ref =
	// Pattern.compile("<a\\s+?([^>]*)>(.*?)</a>", Pattern.CASE_INSENSITIVE |
	// Pattern.DOTALL); //$NON-NLS-1$
	public static final Pattern p_link_ref = Pattern
			.compile(
					"<a\\s+?(.*?)>(.*?)</a>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	public static final Pattern p_template_attachment_url = Pattern
			.compile(
					"<a\\s+?href\\s*?=\\s*?\"(.*?)\"\\s*?>(.*?)</a>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

//	public static final Pattern p_area_ref = Pattern
//			.compile(
//					"<area.*?(href\\s*=\\s*\"(.*?)\")", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$
	public static final Pattern p_area_ref = Pattern
	.compile(
			"<area\\s+?(.*?)/?>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	public static final Pattern p_link_ref_gen = Pattern
			.compile(
					"<(a|area)\\s+?([^>]*)>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	public static final Pattern p_link_type_picker = Pattern
			.compile(
					"\\s*class\\s*?=\\s*?(.*?)\\s+", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	public static final Pattern p_link_guid_picker = Pattern
			.compile(
					"\\s*guid\\s*?=\\s*?(.*?)\\s+", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	public static final Pattern p_link_href_picker = Pattern
			.compile(
					"\\s*href\\s*?=\\s*?\"(.*?)\"\\s+", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	public static final Pattern p_tag_ref = Pattern
			.compile(
					"<([^>!]*)(\\n|\\r)([^>]*)>", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	public static final Pattern p_image_ref = Pattern
			.compile(
					"(<(img|iframe).*?src\\s*=\\s*\")(.*?)(\")", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	public static final Pattern p_href_ref = Pattern
	.compile(
			"(<(a|area).*?href\\s*=\\s*\")(.*?)(\")", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$
	
	public static final Pattern p_url_decoder = Pattern
			.compile(
					"(<[^>]*?(src|href)\\s*=\\s*\")(.*?)(\"[^>]*?>)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	public static final Pattern p_tag_attributes = Pattern
	.compile(
			"([\\S&&[^=]]+)\\s*=\\s*([\\S&&[^\"]]+|\"([\\S &&[^\"]]+)\")", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	public static final Pattern p_css_ref = Pattern.compile(
			" url\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$

	// define constants for diagram type.
	// This will be used when linking diagrams into published contents
	// Activity diagram
	public static final String DIAGRAM_TYPE_WORKFLOW = "Activity"; //$NON-NLS-1$

	// activity detail diagram
	public static final String DIAGRAM_TYPE_ACTIVITY_DETAIL = "ActivityDetail"; //$NON-NLS-1$

	// WPDependency diagram
	public static final String DIAGRAM_TYPE_WP_DEPENDENCY = "WPDependency"; //$NON-NLS-1$

	private static ILibraryResourceManager defaultResourceMgr;
		
	private static boolean showSkinResource = false;
	
	public static String LAYOUT_XSL_ROOT_PATH = null;
	
	public static boolean birt_publishing = true;
	
	public ResourceHelper() {
	}

	public static synchronized void setDefaultResourceMgr(ILibraryResourceManager mgr) {
		defaultResourceMgr = mgr;
	}
	
	public static synchronized ILibraryResourceManager getDefaultResourceMgr() {
		return defaultResourceMgr;
	}
	
	public static ILibraryResourceManager getResourceMgr(MethodElement element) {
		MethodLibrary lib = UmaUtil.getMethodLibrary(element);
		if ( lib != null ) {
			ILibraryManager libMgr = LibraryService.getInstance().getLibraryManager(lib);
			if (libMgr == null) {
				lib = LibraryService.getInstance().getCurrentMethodLibrary();
				libMgr = LibraryService.getInstance().getLibraryManager(lib);
			}
			if (libMgr == null) {
				return getDefaultResourceMgr();
			}
			return libMgr.getResourceManager();
		}
		
		return null;
	}
	
	/**
	 * the relative path of the resource folder of the plugin in the library,
	 * relative to the library root
	 * 
	 * @param element
	 * @return String
	 */
	public static String getPluginResourcePath(MethodElement element) {
		ILibraryResourceManager resMgr = getResourceMgr(element);
		if ( resMgr != null ) {
			MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
			if (plugin == null) {
				return resMgr.getLogicalResourcePath(element);
			} else {
				return resMgr.getLogicalResourcePath(plugin);
			}
		}
		
		return null;
	}

	/**
	 * get the relative path of the resource folder of the element in the
	 * library, relative to the library root
	 * 
	 * @param element MethodElement
	 * @return String
	 */
	public static String getElementResourcePath(MethodElement element) {
		ILibraryResourceManager resMgr = getResourceMgr(element);
		if ( resMgr != null ) {
			return resMgr.getLogicalResourcePath(element);
		}
		
		return null;
	}
	
	/**
	 * Gets the absolute path of the resource folder of the element in the library.
	 * 
	 * @param e
	 * @return absolute path of the element's resource folder
	 */
	public static String getAbsoluteElementResourcePath(MethodElement element) {
		ILibraryResourceManager resMgr = getResourceMgr(element);
		if ( resMgr != null ) {
			return resMgr.getPhysicalResourcePath(element);
		}
		
		return null;
	}

	/**
	 * get the relative path of the folder of the element in the library,
	 * relative to the library root
	 * 
	 * @param element MethodElement
	 * @return String
	 */
	public static String getElementPath(MethodElement element) {
//		try {
//			String path = MethodLibraryPersister.INSTANCE.getRelativeElementPath(element);
//			if (path == null || path.equals("")) //$NON-NLS-1$
//			{
//				System.out
//						.println("Warning! No Path for Element [" + element.getType().getName() + ": " + element.getName() + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//				return ""; //$NON-NLS-1$
//			}
//			return fixPath(path);
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//		}
//
//		return ""; //$NON-NLS-1$
		ILibraryResourceManager resMgr = getResourceMgr(element);
		if ( resMgr != null ) {
			return resMgr.getLogicalPath(element);
		}
		
		return null;
	}
	
//	private static IFileBasedLibraryPersister getFileBasedLibraryPersister(MethodElement element) {
//		Resource resource = element.eResource();
//		if(resource != null) {
//			ILibraryPersister persister = LibraryServiceUtil.getPersisterFor(resource);
//			if(persister instanceof IFileBasedLibraryPersister) {
//				return ((IFileBasedLibraryPersister)persister);
//			}
//		}
//		return null;
//	}
//	
//	/**
//	 * Gets the path of a method element's folder relative to its plug-in.
//	 * 
//	 * @param element
//	 * @return
//	 * @deplicated 
//	 */
//	private static String getFolderRelativePath(MethodElement element) {
//		IFileBasedLibraryPersister persister = getFileBasedLibraryPersister(element);
//		return persister != null ? persister.getFolderRelativePath(element) : null;
//	}
	
	/**
	 * Gets the absolute path of a method element's folder.
	 * 
	 * @param element
	 * @return the absolute path
	 */
	public static String getFolderAbsolutePath(MethodElement element) {
//		if ( element == null ) {
//			return null;
//		}
//		
//		IFileBasedLibraryPersister persister = getFileBasedLibraryPersister(element);
//		return persister != null ? persister.getFolderAbsolutePath(element) : null;
		ILibraryResourceManager resMgr = getResourceMgr(element);
		if ( resMgr != null ) {
			return resMgr.getPhysicalPath(element);
		}
		
		return null;
	}

//	/**
//	 * return the parent folder of the plugin or configuration of the element.
//	 * or the library root if the element is the MethodLibrary.
//	 * 
//	 * @param element MethodElement
//	 * @return String
//	 */
//	public static String getVirtualLibraryRoot(MethodElement element) {
//		if ( element instanceof MethodLibrary ) {
//			// default, return the current library path
//			return LibraryService.getInstance().getCurrentMethodLibraryLocation();					
//		} 
//		
//		String path = null;
//		if (element instanceof MethodConfiguration ) {
//			path = getFolderAbsolutePath(element);
//		} else {
//			path = getPluginPath(element);
//		}
//		
//		if ( path != null ) {
//			File f = new File(path);
//			return f.getParentFile().getAbsolutePath();
//		}
//		
//		return null;
//	}
	
//	/**
//	 * fix the path by appending a File.seperator to the end of it
//	 * @param path String
//	 * @return String
//	 */
//	public static String fixPath(String path) {
//		if (path == null || path.equals("")) //$NON-NLS-1$
//		{
//			return ""; //$NON-NLS-1$
//		} else if (!path.endsWith(File.separator)) {
//			return path + File.separator;
//		} else {
//			return path;
//		}
//	}

	
	/**
	 * get the element's back path relative to the library root. For example,
	 * "OpenUP\guidance\concept\c1.xml", the back path is "./../../../"
	 * 
	 * @param element MethodElement
	 * @return String
	 */
	public static String getBackPath(MethodElement element) {
//		// Linux: Browsing and preview shows only plain text.
//		// There are no images/sections
//		// element path should check File.separatorChar instead of "\"
//		String backPath = ""; //$NON-NLS-1$
//		String path = getElementPath(element);
//		if (path != null && path.length() > 0) {
//			backPath = path.replace(File.separatorChar, '/').replaceAll(
//					".*?/", "\\.\\./"); //$NON-NLS-1$ //$NON-NLS-2$
//		}
//		return "./" + backPath; //$NON-NLS-1$
		
		ILibraryResourceManager resMgr = getResourceMgr(element);
		if ( resMgr != null ) {
			return resMgr.getBackPath(element);
		}
		
		return ""; //$NON-NLS-1$
	}

	/**
	 * get the file name with the given extension
	 * 
	 * @param element MethodElement
	 * @param ext String the file extenasion, for example, ".html"
	 * @return String
	 */
	public static String getFileName(MethodElement element, String ext) {
		return getFileName(element, null, null, ext);
	}

	/**
	 * get the file name with the given prefix and extension
	 * @param element MethodElement
	 * @param namePrefix String prefix for the name
	 * @param nameSuffix String suffix for the name
	 * @param ext String file extension, for example, ".html"
	 * @return String
	 */	 
	public static String getFileName(MethodElement element, String namePrefix,
			String nameSuffix, String ext) {		
		return FileNameGenerator.INSTANCE.getFileName(element, namePrefix, nameSuffix, ext);
	}

	/**
	 * get the guid from the file name, assuming the file name is generated from an element, 
	 * return null if not found.
	 * @param fileName String
	 * @return String
	 */
	public static String getGuidFromFileName(String fileName) {
		return FileNameGenerator.INSTANCE.getGuidFromFileName(fileName);
	}

	/**
	 * get the element from the file name, assuming the file name is generated from an element,
	 * return null if not.
	 * @param fileName String
	 * @return MethodElement
	 */
	public static MethodElement getElementFromFileName(String fileName) {
		String guid = getGuidFromFileName(fileName);
		if (guid != null) {
			ILibraryManager manager = LibraryService.getInstance()
					.getCurrentLibraryManager();
			if (manager != null) {
				return manager.getMethodElement(guid);
			}
		}

		return null;
	}

	/**
	 * get the element url relative to the refElement
	 * 
	 * @param element MethodElement
	 * @param refElement MethodElement
	 * @param fileExt String file extension
	 * @param old_url String the old url of the element. If the old url is not null, 
	 * any bookmark or url parameters are passed to the new url
	 * @return String String the new element
	 */
	public static String getUrl(MethodElement element,
			MethodElement refElement, String fileExt, String old_url) {
		String url = getUrl(element, refElement, fileExt);

		// if the old url has bookmark in it, keep it
		if (old_url != null) {
			int indx = old_url.indexOf(URL_BOOKMARK_INDICATOR);
			if (indx < 0) {
				// keep url query string as well
				indx = old_url.indexOf(URL_PARAMETER_INDICATOR);
			}

			if (indx >= 0) {
				url = url + old_url.substring(indx);
			}
		}

		return url;
	}

	/**
	 * get the element url relative to the refElement
	 * 
	 * @param element MethodElement
	 * @param refElement MethodElement
	 * @param fileExt String file extension
	 * @return String the url
	 */
	public static String getUrl(MethodElement element,
			MethodElement refElement, String fileExt) {
		if (element == null) {
			return ""; //$NON-NLS-1$
		}

		if (refElement == null) {
			return getElementPath(element).replace(File.separatorChar, '/')
					+ getFileName(element, fileExt);
		} else {
			return (getBackPath(refElement) + getElementPath(element)).replace(
					File.separatorChar, '/')
					+ getFileName(element, fileExt);
		}
	}

	/**
	 * get the link text for an element
	 * @param e MethodElement
	 * @param linkType String
	 * @return String
	 */
	public static String getLinkText(MethodElement e, String linkType, MethodConfiguration config) {
		String linkedText = null;

		// RTE may change the case of attributes.
		if ((linkType != null)
				&& !ELEMENT_LINK_CLASS_elementLinkWithUserText.equalsIgnoreCase(linkType)) {
			if (ELEMENT_LINK_CLASS_elementLinkWithType.equalsIgnoreCase(linkType)) {
				linkedText = getElementLinkText(e, true, config);
			} else if (ELEMENT_LINK_CLASS_elementLink.equalsIgnoreCase(linkType)) {
				linkedText = getElementLinkText(e, false, config);
			}
		}

		return linkedText;
	}
	
	public static String getLinkText(MethodElement e, String linkType) {
		return getLinkText(e, linkType, null);
	}

	/**
	 * get the element link text for the given element
	 * @param element MethodElement
	 * @param withType boolean if true the element type string will be included
	 * @return String
	 */
	public static String getElementLinkText(MethodElement element,
			boolean withType) {
		return getElementLinkText(element, withType, null);
	}
	
	public static String getElementLinkText(MethodElement element,
			boolean withType, final MethodConfiguration config) {
		
		TngUtil.PresentationNameHelper pHelper = new TngUtil.PresentationNameHelper() {
			
			public String getPresentationName(MethodElement element) {
				if (element == null) {
					return null;
				}
				String str = ConfigurationHelper.getPresentationName(element, config);
				return str;
			}
			
		};
		
		String text = TngUtil.getPresentationName(element, pHelper);
//		if (withType) {
		
//			return getElementTypeText(element) + LibraryResources.colon_with_space + text; 
//		}
//
//		return text;
		if (withType) {
			if (showSkinResource) {
				if ((LAYOUT_XSL_ROOT_PATH == null)
						|| (LAYOUT_XSL_ROOT_PATH.equals(""))) {//$NON-NLS-1$
					File xslPath = BrowsingLayoutSettings.INSTANCE.getXslPath();
					if (xslPath != null) 
						LAYOUT_XSL_ROOT_PATH = xslPath.getAbsolutePath();
				}
				Properties browsingResource = new Properties();
				File file = new File(LAYOUT_XSL_ROOT_PATH, "resources.properties"); //$NON-NLS-1$
//				Locale locale = Locale.getDefault();
//				String localFileName = I18nUtil.getLocalizedFile(file
//						.getAbsolutePath(), locale);
//				if (localFileName != null) {
//					file = new File(localFileName);
//				}
				if (file.exists()) {
					try {
						browsingResource.load(new FileInputStream(file));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				String type = getElementTypeText(element);
				String elementName = element.eClass().getName();
				String key = elementName.substring(0, 1).toLowerCase()
						+ elementName.substring(1) + "Text";//$NON-NLS-1$
				String value = browsingResource.getProperty(key);
				if (value != null) {
					return value + LibraryResources.colon_with_space + text;
				} else {
					return type + LibraryResources.colon_with_space + text;
				}
			}
			else
			{
				return getElementTypeText(element) + LibraryResources.colon_with_space + text;
			}
			
		}
		
		return text;
	}

	/**
	 * gte the element type string
	 * @param element MethodElement
	 * @return String
	 */
	public static String getElementTypeText(MethodElement element) {
		if (element == null) {
			return ""; //$NON-NLS-1$
		}

		return TngUtil.getTypeText(element);
	}

	/**
	 * auto generated element link <a class="elementLink" href="element_url"
	 * guid="element_guid">element_type: element_presentation_name</a>
	 * 
	 * @param element
	 *            MethodElement
	 * @param withType
	 *            boolean
	 * @param url
	 *            String
	 * @return String
	 */
	public static String getElementLink(MethodElement element,
			boolean withType, String url) {
		String text = getElementLinkText(element, withType);
		String linkClass = ELEMENT_LINK_CLASS_elementLink;
		if (withType)
			linkClass = ELEMENT_LINK_CLASS_elementLinkWithType;
		return getElementLink(element, text, url, linkClass);
	}

	/**
	 * element link with user specified link text
	 * 
	 * @param element
	 * @param text
	 * @param url
	 * @return String
	 */
	public static String getElementLink(MethodElement element, String text,
			String url) {
		return getElementLink(element, text, url,
				ELEMENT_LINK_CLASS_elementLinkWithUserText);
	}

	/**
	 * element link with user specified link text
	 * 
	 * @param element
	 * @param text
	 * @param url
	 * @return String
	 */
	public static String getElementLink(MethodElement element, String text,
			String url, String linkClass) {
		return "<a class=\"" + linkClass + "\" " + getUrlText(url) + " guid=\"" + element.getGuid() + "\" >" + text + "</a>"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}

	/**
	 * the passed in url might contain the href attribute with it, or even other
	 * attributes, so check if this is a pure url or not. return the url href
	 * term string
	 * 
	 * @param url
	 * @return
	 */
	private static String getUrlText(String url) {
		if (url == null) {
			url = ""; //$NON-NLS-1$
		} else {
			url = url.trim();
		}

		// starts with href, or contains an href term in it
		if (url.toLowerCase().startsWith("href") || url.toLowerCase().indexOf(" href") > 0) //$NON-NLS-1$ //$NON-NLS-2$
		{
			return url;
		}

		return "href=\"" + url + "\""; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * validate the content by checking element links and images
	 * 
	 * @param element
	 * @param source
	 * @return String
	 */
	public static String validateContent(MethodElement element, String source) {
		return validateContent(element, source, new DefaultContentValidator(),
				null, null);
	}

	/**
	 * check if the link type is element link or not
	 * @param linkType String
	 * @return boolean
	 */
	public static boolean isElementLink(String linkType) {
		if (linkType == null || linkType.length() == 0) {
			return false;
		}

		// RTE may change the case of attributes.
		return linkType.equalsIgnoreCase(ELEMENT_LINK_CLASS_elementLink)
				|| linkType.equalsIgnoreCase(ELEMENT_LINK_CLASS_elementLinkWithType)
				|| linkType.equalsIgnoreCase(ELEMENT_LINK_CLASS_elementLinkWithUserText);
	}

	/**
	 * validate the content by checking element links and images, for the
	 * specified element owner
	 * 
	 * @param element
	 *            MethodElement, the element that owns the content
	 * @param content
	 * @param config
	 *            MethodConfiguration the configuration to which the content is
	 *            validated
	 * @param linkedElements
	 *            List a passed in list to collect the linked elements in this
	 *            content, if null, no linked elements are collected.
	 * @return String the validated content
	 */
	public static String validateContent(MethodElement element, String source,
			IContentValidator validator, MethodConfiguration config, String layoutXslRootPath) {
			
		try {
			ResourceHelper.LAYOUT_XSL_ROOT_PATH = layoutXslRootPath;
			showSkinResource = true;
			// first validate the tags, remove any CF/LF from the tag text
			source = validateTag(source);

			StringBuffer sb = new StringBuffer();
			Matcher m = p_link_ref.matcher(source);

			while (m.find()) {
				String text = m.group();

				// Problems parsing <a href> tags
				// need to remove all LF, CR within the <a ..> tag
				String urltext = m.group(1);
				String linkedText = m.group(2);
				LinkInfo info = validator.validateLink(element, urltext,
						linkedText, config, "a"); //$NON-NLS-1$
				if (info != null) {
					text = info.getHtml(validator.showBrokenLinks()).toString();
					MethodElement e = info.getLinkedElement();
					if (e != null) {
						validator.addReferencedElement(element, e);
					}
				}

				String replacement = text.replaceAll("file:///", ""); //$NON-NLS-1$ //$NON-NLS-2$
				replacement = replacement.replaceAll("file://", ""); //$NON-NLS-1$ //$NON-NLS-2$				
				m.appendReplacement(sb, regExpEscape(replacement));
			}

			m.appendTail(sb);

			if (element == null) {
				return sb.toString();
			}

			// also fix the area map
			source = sb.toString();
			m = p_area_ref.matcher(source);
			sb.setLength(0);
			while (m.find()) {
				String text = m.group();

				String urltext = m.group(1);
				LinkInfo info = validator.validateLink(element, urltext,
						"", config, "area");  //$NON-NLS-1$ //$NON-NLS-2$
				if (info != null) {
					
					// don't show broken links in area map. Show plan text instead
					text = info.getHtml(false/*validator.showBrokenLinks()*/).toString();
					MethodElement e = info.getLinkedElement();
					if (e != null) {
						validator.addReferencedElement(element, e);
					}
				}

				String replacement = text.replaceAll("file:///", ""); //$NON-NLS-1$ //$NON-NLS-2$
				replacement = replacement.replaceAll("file://", ""); //$NON-NLS-1$ //$NON-NLS-2$				
				m.appendReplacement(sb, regExpEscape(replacement));
			}

			m.appendTail(sb);

			// Shape icon broken in preview and browsing
			// // need to decode the image path unless we can disable the url
			// encoding in xslt
			// source = sb.toString();
			// sb.setLength(0);
			// m = ResourceHelper.p_image_ref.matcher(source);
			// while ( m.find() )
			// {
			// String url = m.group(3);
			// url = URLDecoder.decode(url, "UTF-8");
			// m.appendReplacement(sb, m.group(1) + url + m.group(4));
			// }
			// m.appendTail(sb);
			// return sb.toString();

			// decode the urls
			return decodeUrlsInContent(sb.toString());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			ResourceHelper.LAYOUT_XSL_ROOT_PATH = null;
			showSkinResource = false;
		}

		return source;
	}

	/**
	 * decode the urls in the content. Content from xslt output are encoded. We
	 * don't want this to be encoded since the browser may decode it in a wrong
	 * encoding.
	 * 
	 * @param content
	 * @return String
	 */
	public static String decodeUrlsInContent(String content) {
		try {
			// Shape icon broken in preview and browsing
			// need to decode the image path unless we can disable the url
			// encoding in xslt
			StringBuffer sb = new StringBuffer();
			Matcher m = ResourceHelper.p_url_decoder.matcher(content);
			while (m.find()) {
				String url = m.group(3);
				if (NetUtil.isRawUrl(url)) {
					url = restore(url); 
				} else {
					url = URLDecoder.decode(url, "UTF-8"); //$NON-NLS-1$
				}
				String text = m.group(1) + url + m.group(4);
				m.appendReplacement(sb, regExpEscape(text)); 	
			}
			m.appendTail(sb);

			return sb.toString();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return content;
	}
	
	private static String restore(String url) {
		int index = url.indexOf(NetUtil.RAW_URL_RAW);
		
		return url.substring(0, index);		
	}

	/**
	 * escape the regexp reserved words, such as $
	 * @param text String
	 * @return String
	 */
	public static String regExpEscape(String text) {
		// escape the regExp reserved words,
		// the $ sign is reserved for group matching 
		String newtext = StrUtil.escapeChar(text, '\\');
		newtext = StrUtil.escapeChar(newtext, '$');
		return newtext;
	}
	
	/**
	 * fix the element url in the urltext. the text is the part from the
	 * p_link_ref pattern match
	 * 
	 * @param urltext
	 * @param config
	 *            MethodConfiguration the configuration to which the content is
	 *            validated
	 * @return String the fixed text
	 */
	public static String fixElementUrl(MethodElement element, String urltext,
			MethodConfiguration config) {
		Matcher m = p_link_href_picker.matcher(" " + urltext + " "); //$NON-NLS-1$ //$NON-NLS-2$
		StringBuffer sb = new StringBuffer();
		if (m.find()) {
			String url = m.group(1);
			String newurl = null;
			if (element != null) {
				String guid = getGuidFromUrl(urltext);
				if (guid != null) {
					ILibraryManager manager = LibraryService.getInstance()
							.getCurrentLibraryManager();
					MethodElement e = manager != null ? manager
							.getMethodElement(guid) : null;
					if (e != null) {
						if (config != null) {
							e = ConfigurationHelper.getCalculatedElement(e,
									config);
						}
						newurl = getUrl(e, element, FILE_EXT_HTML, url);
					}

					// if the element is null, we should remove the url link and
					// log an error
					// TODO
				}
			}

			if (newurl != null && !newurl.equals(url)) {
				String replacement = " href=\"" + newurl + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
				m.appendReplacement(sb, regExpEscape(replacement));
				m.appendTail(sb);
				return sb.toString();

			}
		}

		return urltext;
	}

	/**
	 * get the element link type for the url
	 * @param source String the link
	 * @return String the type string
	 */
	public static String getElementLinkType(String source) {
		Matcher m = p_link_type_picker.matcher(" " + source + " "); //$NON-NLS-1$ //$NON-NLS-2$
		if (m.find()) {
			return m.group(1).trim().replaceAll("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return null;
	}

	/**
	 * get the guid from the url
	 * @param source String the url
	 * @return Strign the guid
	 */
	public static String getGuidFromUrl(String source) {
		Matcher m = p_link_guid_picker.matcher(" " + source + " "); //$NON-NLS-1$ //$NON-NLS-2$
		if (m.find()) {
			return m.group(1).trim().replaceAll("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
		}

		m = p_link_href_picker.matcher(" " + source + " "); //$NON-NLS-1$ //$NON-NLS-2$
		if (m.find()) {
			String href = m.group(1).trim().replaceAll("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
			return getGuidFromFileName(href);
		}

		return null;
	}

	/**
	 * handle a bug in IE. anchor tag can't have CR LF, like <A \r\n
	 * href="www.yahoo.com" \r\n> scan the string source, find all anchor tags
	 * and replace with space.
	 * 
	 * @param source
	 * @return String the fixed source
	 */
	public static String validateTag(String source) {
		// Pattern p_anchor_ref = Pattern.compile("(*+?)=(*.?)",
		// Pattern.CASE_INSENSITIVE | Pattern.DOTALL);

		StringBuffer sb = new StringBuffer();
		Matcher m = p_tag_ref.matcher(source);
		// StringBuffer tempSb = new StringBuffer();

		while (m.find()) {
			String text = m.group();
			String replacement = fixTagAttribute(text);
			m.appendReplacement(sb, regExpEscape(replacement));
		}

		m.appendTail(sb);

		return sb.toString();
	}

	/**
	 * fix tag attribute
	 * @param source
	 * @return String
	 */
	public static String fixTagAttribute(String source) {
		// simply replace all CR LF with ' ', fine tune later
		return source
				.replaceAll("\\\r\\\n", " ").replaceAll("\\\r", " ").replaceAll("\\\n", " "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	}

//	/**
//	 * get the resource file url relative to the library root
//	 * 
//	 * @param e
//	 *            MethodElement the element that references this resource
//	 * @param file
//	 *            File the resource's physical path
//	 * @return String the url to this resource from the element
//	 * @deplicated this method is not valid for distributed library
//	 */
//	private static String getResourceFileUrl(MethodElement e, File file) {
//		String fileUrl = getFileUrl(file);
//		// String elementPath =
//		// ResourceHelper.getElementResourcePath(e).replace(File.separatorChar,
//		// '/');
//
//		fileUrl = ResourceHelper.getBackPath(e) + fileUrl;
//
//		return fileUrl;
//
//	}
//
//	/**
//	 * return the file's relative path to the plugin root
//	 * 
//	 * @param f
//	 * @return String
//	 * @deplicated this method is not valid for distributed library
//	 */
//	private static String getFileUrl(File f) {
//		String path = getFileRelPath(f);
//		path = path.replace(File.separatorChar, '/');
//		return path;
//	}

//	/**
//	 * get the URI relative to the specified base
//	 * 
//	 * @param uri
//	 * @return java.net.URI
//	 */
//	public static URI getRelativeURI(URI uri, String rootPath) {
//		return getRelativeURI(uri, new File(rootPath).toURI());
//	}

	/**
	 * get the URI relative to the specified base
	 * 
	 * @param uri
	 * @return java.net.URI
	 * @deplicated
	 */
	public static URI getRelativeURI(URI uri, URI relativeTo) {
		URI relUri = relativeTo.relativize(uri);
		return relUri;
	}

//	/**
//	 * resolve the relative path to the library root
//	 * 
//	 * @param f
//	 * @return String the raltive path
//	 * @deplicated this method is not valid for distributed library
//	 */
//	private static String getFileRelPath(File f) {
//		File libraryRootPath = new File(LibraryService.getInstance()
//				.getCurrentMethodLibraryLocation());
//		String root = libraryRootPath.getAbsolutePath();
//		String path = f.getAbsolutePath();
//		if (path.startsWith(root)) {
//			path = path.substring(root.length());
//		}
//
//		if (path.startsWith(File.separator)) {
//			path = path.substring(1);
//		}
//
//		return path;
//	}

//	/**
//	 * get the URI relative to the library root
//	 * 
//	 * @param f
//	 * @return java.net.URI
//	 * @deplicated this method is not valid for distributed library
//	 */
//	private static java.net.URI getFileRelativeURI(File f) {
//		File libraryRootPath = new File(LibraryService.getInstance()
//				.getCurrentMethodLibraryLocation());
//		java.net.URI libURI = libraryRootPath.toURI();
//		java.net.URI fileURI = f.toURI();
//		java.net.URI relUri = libURI.relativize(fileURI);
//
//		return relUri;
//	}



	/**
	 * 
	 * @param element
	 * @param attachment
	 * @return String
	 */
	public static String getRelativePathToFileFromElement(
			MethodElement element, File attachment) {
		String elementLoc = getFolderAbsolutePath(element);	
		return FileUtil.getRelativePath(attachment, new File(elementLoc));
	}

	/**
	 * get the file path name of the type of diagram for the specified element
	 * 
	 * @param e
	 * @param diagramType
	 *            String diagram type defined in one of the diagram type
	 *            constants
	 * @return String the file path relative to the library root
	 */
	public static String getDiagramFilePathName(MethodElement e,
			String diagramType) {
		// Instances of the same activity/CP share the
		// same diagram in a Delivery Process
		// need to make the name unique
//		return getElementResourcePath(e)
//				+ StrUtil.removeSpecialCharacters(e.getName())
//				+ "_" + e.getGuid() + "_" + diagramType + FILE_EXT_JPEG; //$NON-NLS-1$ //$NON-NLS-2$
		
		return getElementResourcePath(e)
		+ FileNameGenerator.INSTANCE.getFileName(e, "", "_" + diagramType, FILE_EXT_JPEG); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static String getXmlExportedDiagramImageFileName(MethodElement e, String diagramType, String format) {
		String str = e.getName() + " " + e.getGuid() + "_" + diagramType + format; //$NON-NLS-1$ //$NON-NLS-2$
		return str;
	}
	
	public static boolean isAbsolutepath(String path) {
		
		if ( path == null ) {
			return false;
		}
		
		if ( 	path.startsWith("/")  //$NON-NLS-1$
				|| path.startsWith(File.separator) 
				|| path.indexOf(":") > 0 ) { //$NON-NLS-1$
			return true;
		}
		
		return false;
	}
	
	/**
	 * get the relative path of the file based on the url
	 * 
	 * @param url
	 *            String the url
	 * @param contentPath
	 *            String the path of the content that contains this url
	 * @return String the file path relative to the content publishing root
	 */
	public static String getFilePathFromUrl(String url, String contentPath) {

		String filePath = url;
		if (isExternalLink(filePath)
				|| filePath.startsWith("mailto:") || filePath.startsWith(URL_BOOKMARK_INDICATOR)) //$NON-NLS-1$ 
		{
			return null;
		}

		int index = filePath.indexOf(NetUtil.FILE_PREFIX_3);
		if (index == 0) {
			filePath = filePath.substring(NetUtil.FILE_PREFIX_3.length());
		}

		index = filePath.indexOf(NetUtil.FILE_PREFIX_2);
		if (index == 0) {
			filePath = filePath.substring(NetUtil.FILE_PREFIX_2.length());
		}
		
		if ( isAbsolutepath(filePath) ) {
			// assume this is an absolute path
			return XMLUtil.unescape(NetUtil.decodedFileUrl(filePath));
//			return url;
		}

		File f = new File(contentPath);
		
		int start = 0;
		index = 0;
		while ((f != null) && (index = filePath.indexOf("../", start)) >= 0) //$NON-NLS-1$
		{
			f = f.getParentFile();
			start = index + 3;
		}
		
		String rootFolder = ""; //$NON-NLS-1$
		if (f != null) {
			rootFolder = f.getPath();
		}

		if (rootFolder.length() > 0 && !rootFolder.endsWith(File.separator)) {
			rootFolder += File.separator;
		}

		// the file path is
		String path = rootFolder
				+ filePath.substring(start).replace('/', File.separatorChar);

		return XMLUtil.unescape(NetUtil.decodedFileUrl(path));
	}

	/**
	 * for the given content with url based on the oldContentPath, resolve to
	 * the path relative to the root of the contentPath. If backpath is
	 * specified, add the backPath. return the updated content. A typical
	 * scenario is the content is from a contributing element, which has urls
	 * relative to the contributing element's contentPath. This method will fix
	 * the url to based on the base element's contentPath.
	 * 
	 * So call fixContentUrlPath(contributor_text, contributor_elementPath,
	 * baseElement_backPath)
	 * 
	 * @param content
	 * @param contentPath
	 * @param oldContentPath
	 * @return String the updated content with fixed path.
	 */
	public static String fixContentUrlPath(String content, String contentPath,
			String backPath) {
		
		if ( contentPath == null || backPath == null ) {
			return content;
		}
		
		StringBuffer sb = new StringBuffer();
		try {
			// process images
			Matcher m = ResourceHelper.p_image_ref.matcher(content);
			while (m.find()) {
				String url = m.group(3);
				url = resolveUrl(url, contentPath, backPath);
				if (url != null) {
					m.appendReplacement(sb, regExpEscape(m.group(1) + url + m.group(4)));
				}
			}
			m.appendTail(sb);

			content = sb.toString();
			sb = new StringBuffer();
			
			// process attachments
			m = ResourceHelper.p_link_ref_gen.matcher(content);
			while (m.find()) {
				StringBuffer sbLink = new StringBuffer();
				// String tag = m.group(1);
				String urltext = " " + m.group(2) + " "; //$NON-NLS-1$ //$NON-NLS-2$
				
				if (ResourceHelper.getGuidFromUrl(urltext) == null) {
					Matcher m2 = ResourceHelper.p_link_href_picker
							.matcher(urltext);
					if (m2.find()) {
						String url = m2.group(1).trim().replaceAll("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$

						if ( isExternalLink(url) ) {
							// decode for external link 
							url = XMLUtil.unescape(NetUtil.decodedFileUrl(url));
							if (birt_publishing && NetUtil.isRawUrl(url)) {
								url = restore(url);
							}
						} else {
							url = resolveUrl(url, contentPath, backPath);
						}
						if (url != null) {
							String replacement = urltext.substring(m2.start(), m2.start(1))
									+ url
									+ urltext.substring(m2.end(1), m2.end());
							
							m2.appendReplacement(sbLink, regExpEscape(replacement));
							m2.appendTail(sbLink);
							
							replacement = content.substring(m.start(), m.start(2))
									+ sbLink.toString() + content.substring(m.end(2), m.end());
							m.appendReplacement(sb, regExpEscape(replacement));
						}
					}
				}

			}
			m.appendTail(sb);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param url
	 * @param contentPath
	 * @param backPath
	 * @return String
	 */
	public static String resolveUrl(String url, String contentPath,
			String backPath) {
		String new_url = getFilePathFromUrl(url, contentPath);
		if (isAbsolutepath(new_url))
			return new_url;
		if (new_url != null && !new_url.equals(url) ) {
			if (backPath != null) {
				new_url = backPath + new_url;
			}

			return new_url.replace(File.separatorChar, '/');
		}

		return null;
	}

	private static final String EXTERNAL_URL_START_WITH = LayoutResources
			.getString("externalUrl_startWith"); //$NON-NLS-1$

	public static final Pattern p_external_url_startWith = Pattern
			.compile(
					"(" + EXTERNAL_URL_START_WITH + ")", Pattern.CASE_INSENSITIVE | Pattern.DOTALL); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * check if this is an external url or not
	 * @param url
	 * @return boolean
	 */
	public static boolean isExternalLink(String url) {
		// 162969 - Refine external link's regular exp in epf.library/layout/LayoutResources.properties
		// file:// should be excluded
		if ( url == null ) {
			return false;
		}
		
		url = url.toLowerCase();
		if ( url.startsWith("file://") ) {  //$NON-NLS-1$
			return false;
		}
		
		if ( url.startsWith("http://") 	//$NON-NLS-1$
				|| url.startsWith("https://") //$NON-NLS-1$
				|| url.startsWith("ftp://")  //$NON-NLS-1$
				|| url.startsWith("www.") ) { //$NON-NLS-1$
			return true;
		}
		
		Matcher m = p_external_url_startWith.matcher(url);
		if (m.find()) {
			return (m.start(1) == 0);
		}

		return false;
	}

	/**
	 * 
	 * @param url
	 * @return boolean
	 */
	public static boolean isJavascriptUrl(String url) {
		if (url == null ) {
			return false;
		}
		return url.startsWith(URL_STR_MAILTO)
				|| url.toLowerCase().startsWith(URL_STR_JAVASCRIPT);
	}
	
	/**
	 * find the resource file based on the url, copy the file to the destination
	 * folder. if the file is an html attachment, scan the file recursively and
	 * copy contained images, resources.
	 * 
	 * @param owner
	 *            MethodElement, the owner element
	 * @param resourceFile
	 *            File, the resource file
	 * @param url
	 *            String the url to be processed
	 * @param contentPath
	 *            String the content path of the url owner, relative to the
	 *            source root path
	 * @param sourceRootPath
	 *            File the source root path, usually it's the library root path
	 * @param targetRootPath
	 *            File the target root path, usually it's the publishing root
	 *            path
	 * @param rootContentPath String the content path of the root
	 * @param processedItems
	 *            List, a list to hold all processed urls, to avoid duplicate
	 *            processing
	 * @param validator
	 *            IContentValidator
	 * @return String the fixed url, not used.
	 */
	public static String processResourceUrl(MethodElement owner,
			File resourceFile, String url, String contentPath,
			File sourceRootPath, File targetRootPath, String rootContentPath, List processedItems,
			IContentValidator validator) {
		if (url == null || isExternalLink(url) || isJavascriptUrl(url)
				|| url.startsWith(URL_BOOKMARK_INDICATOR)) {
			return url;
		}
		
		// if the url contains the guid, it might be some kind of element link,
		// for example, activity can have model than one element link
		String guid = ""; //$NON-NLS-1$
		if (owner != null) {
			guid = owner.getGuid();
			if (url.indexOf(guid) >= 0 || isActivityTabUrl(url) ) {				
				return url;
			}			
		}
		
		// Publishing report lists missing resource files that
		// are present
		// bookmarks should be stripped out when checking resource
		int index = url.indexOf(URL_BOOKMARK_INDICATOR);
		if (index < 0) {
			index = url.indexOf(URL_PARAMETER_INDICATOR);
		}

		String url_tail = ""; //$NON-NLS-1$
		if (index > 0) {
			url_tail = url.substring(index);
			url = url.substring(0, index);
		}

		String imageFile = ResourceHelper.getFilePathFromUrl(url, contentPath);
		if (imageFile == null) {
			if (validator != null) {
				validator.logMissingResource(owner, resourceFile, url);
			}
			return null;
		}
			
		// index.htm is the default index file, always ignore it
		if (imageFile.equalsIgnoreCase("index.htm") || imageFile.equalsIgnoreCase("index.html")) //$NON-NLS-1$ //$NON-NLS-2$
		{
			return url + url_tail;
		}

		String newUrl = url;

		File source;
		File dest;
		
		// if the url is a local file in an absolute path, we don't copy the file, leave as is
		source = new File(imageFile);
		if ( source.exists() ) {
			return url + url_tail;
		}

		// if root Content path is set, remove from the file path
		boolean usePubRoot = true;
		String filePath = imageFile;
		if ( rootContentPath != null && rootContentPath.length() > 0 ) {
			if ( filePath.startsWith(rootContentPath) ) {
				filePath = filePath.substring(rootContentPath.length());
				usePubRoot = false;
			}
		}
		// check if this is a real file, if not, it is a reference to a base
		// plugin
		source = new File(sourceRootPath, filePath);
		
		// if the source does not exist, might be a reference to a resource in another plugin?
		// for example, if the content is contributed from another element in another plugin, this will happen
		if (!source.exists()) {
			ILibraryResourceManager mgr = getResourceMgr(owner);
			if ( mgr != null ) {
				String tmpPath = mgr.resolve(owner, filePath);
				if ( tmpPath != null ) {
					source = new File(tmpPath);
				}
			}
		}
		
		// if the file is not in the library, check the plugin layout folder
		if (!source.exists()) {
			source = new File(LayoutPlugin.getDefault().getLayoutPath(),
					filePath);
		}
		
		boolean calledFromGuidanceTypeConvert = false;
		File sourceRootParent = sourceRootPath.getParentFile();
		if (sourceRootParent.getName().equals("guidances")) {	//$NON-NLS-1$ 
			File targetRootParent = targetRootPath.getParentFile();
			if (targetRootParent != null) {
				targetRootParent = targetRootParent.getParentFile();
			}
			if (sourceRootParent.equals(targetRootParent)) {
				calledFromGuidanceTypeConvert = true;
				targetRootPath = targetRootPath.getParentFile();				
			}
		}

		// if the filePath is relative to the pub root, fix the target path
		if ( !calledFromGuidanceTypeConvert && usePubRoot && rootContentPath != null && rootContentPath.length() > 0 ) {
			File tmpf =  new File(rootContentPath);
			while ( tmpf != null ) {
				tmpf = tmpf.getParentFile();
				targetRootPath = targetRootPath.getParentFile();
			}
		}
		
		dest = new File(targetRootPath, filePath);
		
//			To be re-visited for SCM handling			
//		if (calledFromGuidanceTypeConvert) {		
//			IStatus status = Services.getFileManager().checkModify(
//					new String[] { targetRootPath.getAbsolutePath(),
//							dest.getAbsolutePath() },
//					PersistencePlugin.getDefault().getContext());
//			if (status != null && !status.isOK()) {
//				Copy file using ws resource API			
//				return newUrl + url_tail;
//			}			
//		}

		if (source.exists()) {
			FileUtil.copyFile(source, dest);

			// if the file is an html attachment, need to scan the file and copy
			// the referenced file in it, recursively
			if (imageFile.endsWith(ResourceHelper.FILE_EXT_HTM)
					|| imageFile.endsWith(ResourceHelper.FILE_EXT_HTML)) {
				File f = new File(imageFile);
				try {
					resolveResources(owner, f, FileUtil.readFile(source,
							FileUtil.ENCODING_UTF_8).toString(), f.getParent(),
							sourceRootPath, targetRootPath, rootContentPath, processedItems,
							validator);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		// if the destination file is there, might be generated dynamically, so
		// don't log it
		else if (!dest.exists() && isValidFileUrl(url) && (validator != null)) {
			validator.logMissingResource(owner, resourceFile, url);
		}

		return newUrl + url_tail;

	}

	private static boolean isActivityTabUrl(String url) {
		return ActivityLayout.isActivityTabUrl(url);
	}
	
	private static boolean isValidFileUrl(String url) {
		// just a temp solution here to get rid of the wrong reporting of mssing
		// resource
		if (url == null || url.equals("' + diagram_img + '")) { //$NON-NLS-1$
			return false;
		}

		return true;
	}

	/**
	 * resolve the images in the text. copy the image to destination if needed
	 * This is used for copying resources from a library to another destination
	 * 
	 * @param owner
	 *            MethodElement, the owner element
	 * @param resourceFile
	 *            File, the resource file
	 * @param content
	 *            String the content string to be processed
	 * @param contentPath
	 *            String the content path of the source text, relative to the
	 *            source root path
	 * @param sourceRootPath
	 *            File the source root path, usually it's the library root path
	 * @param targetRootPath
	 *            File the target root path, usually it's the publishing root
	 *            path
	 * @param rootContentPath String the content path of the root
	 * @param processedItems
	 *            List, a list to hold all processed urls, to avoid duplicate
	 *            processing
	 * @param validator
	 *            IContentValidator
	 */
	public static void resolveResources(MethodElement owner, File resourceFile,
			String content, String contentPath, File sourceRootPath,
			File targetRootPath, String rootContentPath, List processedItems,
			IContentValidator validator) {
		try {
			// process images
			Matcher m = ResourceHelper.p_image_ref.matcher(content);
			while (m.find()) {
				String url = m.group(3);
				if (!processedItems.contains(url)) {
					// note: add the url into the processedItems list first,
					// otherwise may get deadlock if the url content has
					// reference back to the owner
					processedItems.add(url);
					ResourceHelper.processResourceUrl(owner, resourceFile, url,
							contentPath, sourceRootPath, targetRootPath, rootContentPath, 
							processedItems, validator);
				}
			}

			m = ResourceHelper.p_css_ref.matcher(content);
			while (m.find()) {
				String cssURL = m.group(1);

				// it's hard to tell if this is a right match or not
				// since we can't tell if the matched one is actually from a css
				// or just a html text
				// for example, text can have something like url(xxx) which is
				// matched here
				// we don't want to report missing resource for this
				// so don't set the validator
				// Publishing warnings: inappropriate warnings
				// coming from required copyrite.htm

				if (!processedItems.contains(cssURL)) {
					// note: add the url into the processedItems list first,
					// otherwise may get deadlock if the url content has
					// reference back to the owner
					processedItems.add(cssURL);
					ResourceHelper
							.processResourceUrl(owner, resourceFile, cssURL,
									contentPath, sourceRootPath,
									targetRootPath, rootContentPath, processedItems, null/* validator */);
				}
			}

			// process attachments
			m = ResourceHelper.p_link_ref_gen.matcher(content);
			while (m.find()) {
				// String tag = m.group(1);
				String urltext = m.group(2);
				if (ResourceHelper.getGuidFromUrl(urltext) == null) {
					Matcher m2 = ResourceHelper.p_link_href_picker
							.matcher(" " + urltext + " "); //$NON-NLS-1$ //$NON-NLS-2$
					if (m2.find()) {
						String url = m2.group(1).trim().replaceAll("\"", ""); //$NON-NLS-1$ //$NON-NLS-2$
						if (!processedItems.contains(url)) {
							// note: add the url into the processedItems list
							// first, otherwise may get deadlock if the url
							// content has reference back to the owner
							processedItems.add(url);
							ResourceHelper.processResourceUrl(owner,
									resourceFile, url, contentPath,
									sourceRootPath, targetRootPath, rootContentPath, 
									processedItems, validator);
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * take the tag attributes string as input and returns the attribute
	 * name-value map
	 * 
	 * @param attributesStr
	 * @return Map the attribute name-value map
	 */
	public static Map getTagAttributes(String attributesStr) {
		try {
			// use LinkedHashMap to reserve the order of the attributes
			Map attributeMap = new LinkedHashMap(3);
			Matcher m2 = p_tag_attributes.matcher(attributesStr);

			while (m2.find()) {
				String attrName = m2.group(1).trim().toLowerCase();
				String attrValue = ""; //$NON-NLS-1$
				if (m2.group(3) != null) {
					attrValue = m2.group(3).trim();
				} else if (m2.group(2) != null) {
					attrValue = m2.group(2).trim();
				}				
		
				// Cannot Preview/Browse Description Tab when
				// the CP contains chinese characters
				// generated html from xslt got the href urls encoded. we don't want
				// that make sure decode the url using "UTF-8" encoding
				if (attrName.equals(TAG_ATTR_HREF)) {
					try {
						attrValue = URLDecoder.decode(attrValue, "UTF-8"); //$NON-NLS-1$
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}					
				}
				attributeMap.put(attrName, attrValue);
			}
			return attributeMap;
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * returns the attribute name-value map for an HTML link
	 * 
	 * @param link
	 * @return Map
	 */
	public static Map getAttributesFromLink(String link) {
		Matcher m = p_link_ref.matcher(link);
		if (m.find()) {
			String attributes = m.group(1);
			return getTagAttributes(attributes);
		}
		return null;
	}

	/**
	 * returns the attribute name-value map for an HTML link
	 * 
	 * @param link
	 * @return Map
	 */
	public static Map getAttributesFromLink(Pattern pattern, String link) {
		Matcher m = pattern.matcher(link);
		if (m.find()) {
			String attributes = m.group(1);
			return getTagAttributes(attributes);
		}
		return null;
	}
	
	/**
	 * takes an attributeMap (ie, from #getTagAttributes) and returns the String
	 * representation
	 * 
	 * @param attributeMap
	 * @return String
	 */
	public static String getAttributesAsString(Map attributeMap) {
		StringBuffer buf = new StringBuffer();
		for (Iterator iter = attributeMap.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			String value = (String) attributeMap.get(key);
			if (buf.length() > 0)
				buf.append(" "); //$NON-NLS-1$
			buf.append(key + "=\"" + value + "\""); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return buf.toString();
	}

	/**
	 * Bad link from Welcome page hump chart
	 * 
	 */
	private static String getDiscardedElementURL(MethodElement ownerElement,
			MethodElement linkElement, String guid, String pubDir) {
		StringBuffer discardedElementURL = new StringBuffer();

		discardedElementURL.append(MISSING_PAGES_FOLDER).append(
				"pages_not_installed").append(FILE_EXT_HTML); //$NON-NLS-1$

		File outputFile = new File(pubDir, discardedElementURL.toString());
		File dir = outputFile.getParentFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}

		// generate the html file
		XmlElement xml = new XmlElement("Element"); //$NON-NLS-1$

		xml
				.setAttribute("guid", guid).setAttribute("type", linkElement.getType().getName()) //$NON-NLS-1$ //$NON-NLS-2$
				.setAttribute("name", linkElement.getName()) //$NON-NLS-1$
				.setAttribute(
						"pluginName", LibraryUtil.getMethodPlugin(linkElement).getName()); //$NON-NLS-1$								
		String xslPath = LayoutResources.getDefaultXslPath("page_not_installed", null); //$NON-NLS-1$

		try {
			StringBuffer xml2 = new StringBuffer();
			xml2.append(XmlHelper.XML_HEADER).append(xml.toXml());

			OutputStreamWriter output = new OutputStreamWriter(
					new FileOutputStream(outputFile), "utf-8"); //$NON-NLS-1$
			Properties xslParams = LayoutPlugin.getDefault().getProperties(
					"/layout/xsl/resources.properties"); //$NON-NLS-1$

			XSLTProcessor
					.transform(xslPath, xml2.toString(), xslParams, output);
			output.flush();
			output.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return getBackPath(ownerElement).replace(File.separatorChar, '/')
				+ discardedElementURL.toString();
	}
	
	
	
	/**
	 * FOR USE WITH RICH TEXT EDITOR
	 * validate the content by checking element links and images, for the
	 * specified element owner
	 * 
	 * This is identical to the validateContent method, except it does not decode the results
	 * via URLDecoder.decode()
	 * 
	 *  this will do the following to the HTML:
	 *  1.  update Element Links
	 *  2.  change <A ..> to <a ..>
	 *  3.  change </A> to </a>
	 *  4.  add double-quotes (") around all attribute values if they are missing
	 * 
	 * @param element
	 *            MethodElement, the element that owns the content
	 * @param content
	 * @param config
	 *            MethodConfiguration the configuration to which the content is
	 *            validated
	 * @param linkedElements
	 *            List a passed in list to collect the linked elements in this
	 *            content, if null, no linked elements are collected.
	 * @return String the validated content
	 */
	public static String validateRichTextContent(MethodElement element, String source, IContentValidator validator) {
 		try {
			MethodConfiguration config = null;
			
			// first validate the tags, remove any CF/LF from the tag text
			source = validateTag(source);

			StringBuffer sb = new StringBuffer();
			Matcher m = p_link_ref.matcher(source);

			while (m.find()) {
				String text = m.group();

				// Problems parsing <a href> tags
				// need to remove all LF, CR within the <a ..> tag
				String urltext = m.group(1);
				String linkedText = m.group(2);
				LinkInfo info = validator.validateLink(element, urltext,
						linkedText, config, "a"); //$NON-NLS-1$
				if (info != null) {
					text = info.getHtml(validator.showBrokenLinks()).toString();
					MethodElement e = info.getLinkedElement();
					if (e != null) {
						validator.addReferencedElement(element, e);
					}
				}

				String replacement = text.replaceAll("file:///", ""); //$NON-NLS-1$ //$NON-NLS-2$
				replacement = replacement.replaceAll("file://", ""); //$NON-NLS-1$ //$NON-NLS-2$
				m.appendReplacement(sb, regExpEscape(replacement));
			}

			m.appendTail(sb);

			if (element == null) {
				return sb.toString();
			}

			// also fix the area map
			source = sb.toString();
			m = p_area_ref.matcher(source);
			sb.setLength(0);

			while (m.find()) {
				String text = m.group();

				// Problems parsing <a href> tags
				// need to remove all LF, CR within the <a ..> tag
				String urltext = m.group(1);
				LinkInfo info = validator.validateLink(element, urltext,
						"", config, "area"); //$NON-NLS-1$ //$NON-NLS-2$
				if (info != null) {
					text = info.getHtml(validator.showBrokenLinks()).toString();
					MethodElement e = info.getLinkedElement();
					if (e != null) {
						validator.addReferencedElement(element, e);
					}
				}

				String replacement = text.replaceAll("file:///", ""); //$NON-NLS-1$ //$NON-NLS-2$
				replacement = replacement.replaceAll("file://", ""); //$NON-NLS-1$ //$NON-NLS-2$
				m.appendReplacement(sb, regExpEscape(replacement));
			}

			m.appendTail(sb);

			return sb.toString();

		} catch (Exception ex) {
			LibraryPlugin.getDefault().getLogger().logError(ex);
		}

		return source;
	}

//	/**
//	 * copy all resource files associated with the element to the target location.
//	 * recursive all it's contained elements if recursive is true.
//	 * 
//	 * @param element
//	 * @param from
//	 * @param to
//	 */
//	public static void copyAllResources(MethodElement element, File from, File to, boolean recursive) {
//		
//		ContentResourceScanner scanner = new ContentResourceScanner(from, to, );
//		scanner.setTargetRootPath(to);
//		scanner.copyResources(element);
//		
//		if ( recursive ) {
//			for ( Iterator it = element.eAllContents(); it.hasNext(); ) {
//				Object obj = it.next();
//				if ( obj instanceof MethodElement ) {
//					scanner.copyResources((MethodElement)obj);
//				}
//			}
//		}
//	}
	
	public static String getPluginPath(MethodElement e) {		
		MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
		return getFolderAbsolutePath(plugin);
	}

	public static String getPluginPath(MethodPlugin plugin) {
		return getFolderAbsolutePath(plugin);
	}
	
	public static String convertToRteString(String attachmentString) {
		String str = "<ul>";		//$NON-NLS-1$		
		if ( (attachmentString != null) && (attachmentString.indexOf(ConfigurationHelper.ATTRIBUTE_VALUE_SEPERATOR) > 0) ) {
			attachmentString = attachmentString.replaceAll(ConfigurationHelper.ATTRIBUTE_VALUE_SEPERATOR, TngUtil.GUIDANCE_FILESTRING_SEPARATOR); 
		}
		List attachmentList = TngUtil.convertGuidanceAttachmentsToList(attachmentString);
		for (Iterator iter = attachmentList.iterator();iter.hasNext();) {
			String attachmentFile = (String) iter.next();
			if (attachmentFile != null) {
				Matcher m = ResourceHelper.p_template_attachment_url.matcher(attachmentFile);
				if (!m.find()) {
					String fileName = FileUtil.getFileName(attachmentFile);
					str += "<li>";										//$NON-NLS-1$
					str += "<a  href=\"" + attachmentFile;				//$NON-NLS-1$					
					str += "\" target=\"_blank\"; >" + fileName;		//$NON-NLS-1$					
					str += "</a>";										//$NON-NLS-1$
					str += "</li>";										//$NON-NLS-1$ 
				} else {
					String url = m.group(1);
					String fileName = m.group(2);
					str += "<li>";										//$NON-NLS-1$
					str += "<a  href=\"" + url;				//$NON-NLS-1$					
					str += "\" target=\"_blank\"; >" + fileName;		//$NON-NLS-1$					
					str += "</a>";										//$NON-NLS-1$
					str += "</li>";										//$NON-NLS-1$
				}
			}
		}
		str += "<ul>";													//$NON-NLS-1$
		return str;
	}
	
	

	
}
