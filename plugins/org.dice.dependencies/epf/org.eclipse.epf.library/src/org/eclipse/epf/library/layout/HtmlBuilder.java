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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.eclipse.epf.common.utils.I18nUtil;
import org.eclipse.epf.common.utils.Timer;
import org.eclipse.epf.common.xml.XSLTProcessor;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.layout.elements.DescriptorLayout;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.layout.util.XmlHelper;
import org.eclipse.epf.library.prefs.PreferenceUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.publish.layout.LayoutPlugin;
import org.eclipse.epf.uma.MethodElement;

import com.ibm.icu.util.Calendar;


/**
 * Generates the HTML page for a Method element.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class HtmlBuilder {

	private boolean debug = false;

	private Properties xslParams = null;

	private ElementLayoutManager mgr = null;

	private ElementLayoutManager internal_mgr = null;

	//private ContentResourceScanner scanner = null;

	private boolean scanContent = true;

	// If true, display the "Show tree browse" image/link to the right of a
	// published page.
	private boolean showTreeBrowser = false;

	//private IContentValidator validator = null;

	// override this path to specify xsl path
	private String layoutXslRootPath = LayoutPlugin.getDefault().getLayoutXslPath();
	
	private Map<MethodElement, String> elementContentMap;

	/**
	 * Creates a new instance.
	 */
	public HtmlBuilder() {
		this(null);
	}

	/**
	 * Creates a new instance.
	 */
	public HtmlBuilder(ElementLayoutManager mgr) {
		init();
		setLayoutManager(mgr);
	}
		
	public void setElementContentMap(Map<MethodElement, String> elementContentMap) {
		this.elementContentMap = elementContentMap;
	}
	
	public void setLayoutXslRootPath(String path) {
		try {
			if ( path != null && path.length() > 0 ) {
				this.layoutXslRootPath = path;
				if ( !this.layoutXslRootPath.endsWith(File.separator) ) {
					this.layoutXslRootPath += File.separator;
				}
				
				// get the localized file
				File file = new File(this.layoutXslRootPath, "resources.properties"); //$NON-NLS-1$
				Locale locale = Locale.getDefault();
				String localFileName = I18nUtil.getLocalizedFile(file.getAbsolutePath(), locale);
				if ( localFileName != null ) {
					file = new File(localFileName);
				}
				if (file.exists()) {
					xslParams.load(new FileInputStream(file));
				}	
				
				// the PreferenceUtil also uses the xsl properties for activity breakdown layout
				// so set it as well
				PreferenceUtil.setXslProperties(xslParams);
			}
		} catch (IOException e) {
		}
	}
	
	/**
	 * Performs the necessary initialization.
	 */
	protected void init() {
		debug = LibraryPlugin.getDefault().isDebugging();
		loadDefaultLayoutXsl();
	}

	public void addParam(String key, String value) {
		xslParams.put(key, value);
	}
	
	public void loadDefaultLayoutXsl() {
		layoutXslRootPath = LayoutPlugin.getDefault().getLayoutXslPath();
		try {
			xslParams = LayoutPlugin.getDefault().getProperties(
					"/layout/xsl/resources.properties"); //$NON-NLS-1$
			
			// add the colon property, 
			xslParams.put("colon_with_space", LibraryResources.colon_with_space); //$NON-NLS-1$
			
			// the PreferenceUtil also uses the xsl properties for activity breakdown layout
			// so set it as well
			PreferenceUtil.setXslProperties(xslParams);

			
		} catch (IOException e) {
			xslParams = null;
		}
	}
	
	/**
	 * Returns the publish directory.
	 */
	public String getPublishDir() {
		return (mgr == null) ? null : mgr.getPublishDir();
	}

	/**
	 * Returns the publish directory.
	 */
	public void setPublishDir(String dir) {
		if (mgr != null) {
			mgr.setPublishDir(dir);
		}

		//getScanner().setTargetRootPath(new File(getPublishDir()));

		// Reset the validator's publish dir.
		getValidator().setPublishDir(dir);
	}

//	/**
//	 * Sets the content validator.
//	 */
//	public void setValidator(IContentValidator validator) {
//		this.validator = validator;
//		getScanner().setValidator(this.validator);
//	}

	/**
	 * Returns the content validator.
	 */
	public IContentValidator getValidator() {
//		if (validator == null) {
//			validator = new DefaultContentValidator(getPublishDir());
//		}
//		return validator;
		
		return this.mgr.getValidator();
	}

//	/**
//	 * Returns the content resource scanner for the element.
//	 */
//	private ContentResourceScanner getScanner(MethodElement owner) {
//		ILibraryResourceManager resMgr = ResourceHelper.getResourceMgr(owner);
//		if ( resMgr == null ) {
//			return null;
//		}
//		
//		String rootContentPath = resMgr.getLogicalPluginPath(owner);
//		File src_root = new File(resMgr.getPhysicalPluginPath(owner));
//		File tgt_root = new File(getPublishDir(), rootContentPath);
//		ContentResourceScanner scanner = new ContentResourceScanner(src_root, tgt_root, rootContentPath, getValidator());
//
//		return scanner;
//	}

	/**
	 * Sets the flag to display the "Show tree browser" image/link.
	 */
	public void enableTreeBrowser(boolean flag) {
		this.showTreeBrowser = flag;
	}

	/**
	 * Enables content scanning.
	 */
	public void enableContentScan(boolean scan) {
		this.scanContent = scan;
	}

	/**
	 * Returns <code>true</code> if content scanning is enabled.
	 */
	public boolean contentScanEnabled() {
		return scanContent;
	}

	/**
	 * Sets the Element Layout Manager.
	 * <p>
	 * A Element Layout Manager is associated with a configuration.
	 */
	public void setLayoutManager(ElementLayoutManager mgr) {
		this.mgr = mgr;
		if (this.mgr == null) {
			this.mgr = getDefaultLayoutManager(); 
		}

		// Update the scanner target path.
		//getScanner().setTargetRootPath(new File(getPublishDir()));

		// Reset the validator pub dir.
		getValidator().setPublishDir(getPublishDir());
	}

	/**
	 * Returns the default Element Layout Manager.
	 */
	private ElementLayoutManager getDefaultLayoutManager() {
		if ( this.internal_mgr == null ) {
			this.internal_mgr = new ElementLayoutManager();
		}
		
		return this.internal_mgr;
	}

	/**
	 * Returns the Element Layout Manager.
	 */
	public ElementLayoutManager getLayoutManager() {
		if (this.mgr == null) {
			this.mgr = getDefaultLayoutManager();
		}
		return mgr;
	}

	/**
	 * Generates the HTML for a Method element.
	 * 
	 * @param element
	 *            A Method element.
	 * @return A url of the generated content HTML file.
	 */
	public String generateHtml(MethodElement element) {
		String url = null;
		if (debug) {
			long startTime = System.currentTimeMillis();
			IElementLayout layout = getLayoutManager().getLayout(element, true);
			url = generateHtml(layout);
			long endTime = System.currentTimeMillis();
			System.out
					.println("Time taken to render HTML page for " + element.getName() + //$NON-NLS-1$
							": " + (endTime - startTime) + " ms"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			IElementLayout layout = getLayoutManager().getLayout(element, true);
			url = generateHtml(layout);
		}
		return url;
	}


	/**
	 * Generates the HTML for a Method element.
	 * 
	 * @param url
	 *            The URL of a Method element.
	 */
	public void generateHtml(String url) {
		IElementLayout layout = getLayoutManager().getLayout(url);
		generateHtml(layout);
	}

	/**
	 * Generates the HTML for a Method element.
	 * 
	 * @param layout
	 *            A Method Element. Layout object.
	 * @param linkedElements
	 *            If not null, this object will be populated with a list of
	 *            linked elements in the page.
	 * @return A url of the generated content HTML file.
	 */
	public String generateHtml(IElementLayout layout) {
		if (layout == null) {
			return "about:blank"; //$NON-NLS-1$
		}

		//System.out.println("*** generateHtml: " + LibraryUtil.getTypeName(layout.getElement()) );
		
		// add time logging when publishing element
		long time_start = Calendar.getInstance().getTimeInMillis();
		String elementPath = layout.getNoAdjustedElementPath().replace('/',
				File.separatorChar);
		String elementPathName = elementPath
				+ layout.getFileName(ResourceHelper.FILE_EXT_HTML);
		String filePath = this.getPublishDir() + elementPath;
		String html_file = this.getPublishDir() + elementPathName;

		try {

			StringBuffer xml = getXml(layout);

			String xsl_uri;

			File f = new File(filePath);
			if (!f.exists()) {
				f.mkdirs();
			}

			// Generate the additonal outputs.
			List layouts = layout.getLayouts();
			xsl_uri = layoutXslRootPath + layout.getXslUrl();
			generateHtml(layout, xsl_uri, html_file, xml);

			// Generate other layout files.
			if (layouts != null && layouts.size() > 0) {
				for (Iterator it = layouts.iterator(); it.hasNext();) {
					LayoutInfo info = (LayoutInfo) it.next();
					xsl_uri = layoutXslRootPath + info.layout_xsl;
					String file = filePath + info.fileName;
					generateHtml(layout, xsl_uri, file, xml);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			html_file = null;
		}

		long time_end = Calendar.getInstance().getTimeInMillis();
		long mini_seconds = time_end - time_start;
		if (mini_seconds > 1000) {
			String msg = mini_seconds
					+ " mini-second(s) publishing element " + LibraryUtil.getTypeName(layout.getElement()) + "[" + elementPathName + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			IContentValidator validator = getValidator();
			if (validator == null) {
				System.out.println(msg);
			} else {
				validator.logInfo(msg);
			}
		}
		if (html_file != null) {
			return html_file;
		}

		return "about:blank"; //$NON-NLS-1$
	}

	/**
	 * Generates the HTML for a Method element.
	 * 
	 * @param layout
	 *            A Method Element. Layout object.
	 * @param xslURI
	 *            The XSL stylesheet.
	 * @param htmlFile
	 *            The HTML file to be generated.
	 * @param xml
	 *            The XML buffer.
	 * @return A url of the generated content HTML file.
	 */
	private void generateHtml(IElementLayout layout,
			String xsl_uri, String html_file, StringBuffer xml) {
		Throwable th = null;
		if (layout == null) {
			return;
		}

		Timer timer = null;
		if ( debug ) {
			timer = new Timer();
		}
		
		try {
			StringWriter sw = new StringWriter();
			
			if (getValidator()
					.showLinkedPageForDescriptor()
					&& layout instanceof DescriptorLayout) {

				DescriptorLayout dLayout = (DescriptorLayout) layout;
				MethodElement linedElement = dLayout.getLinkedElement();
				if (linedElement != null) {
					getValidator().addReferencedElement(dLayout.getElement(),
							linedElement);
					return;
				}
			}		
			
			XSLTProcessor.transform(xsl_uri, xml.toString(), xslParams, sw);
			sw.flush();
			String content = sw.getBuffer().toString();

			if ( debug) {
				timer.stop();
				System.out.println(timer.getTime() + " mini seconds for xml/xslt transformation"); //$NON-NLS-1$
				timer.start();
			}
			// Always validate and fix the content before publishing.
			
			content = ResourceHelper.validateContent(layout.getElement(),
					content, getValidator(), layout.getLayoutMgr()
							.getConfiguration(), this.layoutXslRootPath);
						
			if (contentScanEnabled()) {
//				scanContentForResources(layout.getElement(), content, layout
//						.getFilePath());
				getValidator().scanContent(layout, content);
			}
			
			content = getLayoutManager().getAdjustedElementPathStringValue(content);		
			if (elementContentMap != null) {
				elementContentMap.put(layout.getElement(), content);
			}
						
			if ( debug) {
				timer.stop();
				System.out.println(timer.getTime() + " mini seconds scanning content"); //$NON-NLS-1$
				timer.start();
			}
			
			OutputStreamWriter output = new OutputStreamWriter(
					new FileOutputStream(html_file), "utf-8"); //$NON-NLS-1$
			output.write(content);
			output.flush();
			output.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			th = e;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			th = e;
		} catch (IOException e) {
			e.printStackTrace();
			th = e;
		} catch (Exception e) {
			e.printStackTrace();
			th = e;
		}

		if (th != null) {
			this.getValidator().logError(layout.getElement(),
					"Error generating element content", th); //$NON-NLS-1$
		}
	}

	/**
	 * Generates the HTML for a Method element.
	 * 
	 * @param layout
	 *            A Method Element. Layout object.
	 * @return A url of the generated content HTML file.
	 */
	private StringBuffer getXml(IElementLayout layout) {
		StringBuffer xml = new StringBuffer();
		XmlElement xmlElement = layout.getXmlElement(true);
		
		// set the language attribute
		Locale locale = Locale.getDefault();
		String lang = locale.getLanguage();
		xmlElement.setAttribute("lang", lang); //$NON-NLS-1$

		if (showTreeBrowser) {
			xmlElement.setAttribute("showTreeBrowser", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		xml.append(XmlHelper.XML_HEADER).append(xmlElement.toXml());

//		if ( debug || CommandLineRunUtil.getInstance().isNeedToRun()) {
		if ( debug) {			
			try {
				String xml_file = this.getPublishDir() + "xml" + File.separator; //$NON-NLS-1$
				xml_file += layout.getType() + "." + layout.getFileName(".xml"); //$NON-NLS-1$ //$NON-NLS-2$
				File xf = new File(xml_file);
				if ( !xf.exists() )
				{
					xf.getParentFile().mkdirs();
					xf.createNewFile();
				}
							
				OutputStreamWriter output = new OutputStreamWriter(
						new FileOutputStream(xf), "utf-8"); //$NON-NLS-1$
				output.write(xml.toString());
				output.flush();
				output.close();
	
				// FileWriter xw = new FileWriter(xml_file);
				// xw.write(xml.toString());
				// xw.flush();
				// xw.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return xml;
	}

//	/**
//	 * Scans the content for resource references.
//	 */
//	private void scanContentForResources(MethodElement owner, String content,
//			String contentPath) {
//		ContentResourceScanner scanner = getScanner(owner);
//		if ( scanner != null ) {
//			scanner.resolveResources(owner, content, contentPath);
//		}
//	}

	public void dispose() {
//		if (validator != null) {
//			validator.dispose();
//		}

		if ( internal_mgr != null ) {
			internal_mgr.clear();
			internal_mgr = null;
		}
		
		mgr = null;
//		scanner = null;
//		validator = null;
	}
}
