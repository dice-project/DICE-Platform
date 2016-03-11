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
package org.eclipse.epf.publishing.services;

import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.layout.Bookmark;
import org.eclipse.epf.library.layout.BookmarkList;
import org.eclipse.epf.library.layout.HtmlBuilder;
import org.eclipse.epf.library.layout.LayoutResources;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.publishing.PublishingPlugin;
import org.eclipse.epf.publishing.util.PublishingUtil;


/**
 * default implementation of the site generator.
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class DefaultSiteGenerator extends AbstractSiteGenerator {

	public static final String NO_APPLET_DIRECTORY = "noapplet"; //$NON-NLS-1$

	// public static final String BOOKMARK_SUFFIX = ".bkm"; //$NON-NLS-1$
	public static final String BOOKMARK_SUFFIX_XML = ".xml"; //$NON-NLS-1$

	public static final String BOOKMARK_SUFFIX_HTML = ".html"; //$NON-NLS-1$

	public static final String SPACE_REPLACEMENT = "_"; //$NON-NLS-1$

	public static final String SPACE_STRING = " "; //$NON-NLS-1$

	// public static final String DEFAULT_BOOKMARK_CFG_NAME =
	// "DefaultBookmark.cfg"; //$NON-NLS-1$
	public static final String PUBLISHED_BOOKMARKS_CFG_NAME = "PublishedBookmarks.xml"; //$NON-NLS-1$

	public static final String PUBLISHED_BOOKMARKS_DELIMITER = "*"; //$NON-NLS-1$

	protected static final String BOOKMARK_XSL_FILE = "xsl/bookmark.xsl"; //$NON-NLS-1$

	protected static final String BOOKMARKS_XSL_FILE = "xsl/PublishedBookmarks.xsl"; //$NON-NLS-1$

	protected static final String INDEX_XSL_FILE = "xsl/index.xsl"; //$NON-NLS-1$

	protected static final String TOPNAV_XSL_FILE = "xsl/topnav.xsl"; //$NON-NLS-1$
	
	protected static final String SEARCH_XSL_FILE = "xsl/search.xsl"; //$NON-NLS-1$

	private static final String APPLET_PATH = "applet" + File.separatorChar; //$NON-NLS-1$
	private static final String NO_APPLET_PATH = "noapplet" + File.separatorChar; //$NON-NLS-1$
	private static final String ICON_PATH = "images" + File.separatorChar; //$NON-NLS-1$
	private static final String CUSTOMIZED_ICON_PATH = ICON_PATH + AbstractSiteGenerator.customizedName + File.separatorChar; //$NON-NLS-1$
	
	private static final String SERVER_SEARCH_PATH = "docroot/server_search"; //$NON-NLS-1$	
	
	private static final String DEFAULT_BANNER_FILE = "EPFC_banner.jpg"; //$NON-NLS-1$

	protected File iconPath;
	
	protected File customizedIconPath;

	public DefaultSiteGenerator(HtmlBuilder builder, PublishHTMLOptions options) {
		super(builder, options);
		
		iconPath = new File(pubDir, (options.isPublishDynamicWebApp() ) ? NO_APPLET_PATH  + ICON_PATH 
				: APPLET_PATH + ICON_PATH);
		
		customizedIconPath = new File(pubDir, (options.isPublishDynamicWebApp() ) ? NO_APPLET_PATH  + CUSTOMIZED_ICON_PATH 
				: APPLET_PATH + CUSTOMIZED_ICON_PATH);
	}
	
	public HtmlBuilder getHtmlBuilder() {
		return builder;
	}
	
	public PublishOptions getPublishOptions() {
		return options;
	}

	public String getNodeIconPath() {
		return iconPath.getAbsolutePath();
	}
	
	public String getCustomizedNodeIconPath() {
		return customizedIconPath.getAbsolutePath();
	}

	public String getDefaultBannerImageFile() {
		//return "banner.gif"; //$NON-NLS-1$
		return DEFAULT_BANNER_FILE;
	}
	
	public void prePublish() throws Exception {
		copyFiles();
	}
	
	public void postPublish() throws Exception {
		copyIconsForNonApplet();
		writeIndexAndTopNavHtml();
		writeSearchHtml();
	}
	
	protected void copyFiles() throws Exception {

		copyDocRootFiles();

		// copy localized files
		copyLocalizedFiles();

		// copy user customized files last
		copyCustomizedFiles();
	}

	protected void copyDocRootFiles() throws Exception {

		URL plugin_url = PublishingPlugin.getDefault().getInstallURL();
		URL url = new URL(plugin_url, DOC_ROOT);
		String fromPath = Platform.resolve(url).getPath();

		// folders that apply to all
		// NOTE: applet images should always be included since it's used in
		// Atlantic configuration
		// applet/help.htm is used in RSA to check character encoding, so
		// need to include this file
		// Process Advisor: FileNotFoundException in RSA log
		// when indexing start on a non-applet published site
		String includes = "*.*, process/**, images/**, index/**, scripts/**, stylesheets/**"; //$NON-NLS-1$

		String excludes = ""; //$NON-NLS-1$

		// based on the selection, copy the other folders
		if (options.isPublishJavaScriptTree()) {
			includes += ", noapplet/**"; //$NON-NLS-1$
		}

		// String publishDir = viewBuilder.getHtmlBuilder().getPublishDir();

		LayoutResources.copyDir(fromPath, pubDir, includes, excludes);		
		
		String path = FileLocator.resolve(new URL(plugin_url, SERVER_SEARCH_PATH)).getPath();
		includes = "*.*, search/**"; //$NON-NLS-1$
		LayoutResources.copyDir(path, pubDir, includes, null);		
	}

	protected void copyCustomizedFiles() throws Exception {
		super.copyCustomizedFiles();

		if (options.aboutHTML != null && options.aboutHTML.length() > 0) {
			FileUtil.copyFile(options.aboutHTML, pubDir + "about.htm"); //$NON-NLS-1$
		}
	}	

	protected void copyLocalizedFiles() throws Exception {

		super.copyLocalizedFiles();

		if (options.isPublishJavaScriptTree()) {
			// copy the applet html files, rename locale specific names
			PublishingPlugin
					.getDefault()
					.copyLocalizedFiles(
							DOC_ROOT + "noapplet/", new File(pubDir, "noapplet/"), false, false); //$NON-NLS-1$ //$NON-NLS-2$
		}

	}
	
	protected void copyIconsForNonApplet()
	{
		try
		{
			// don't jar it. copy the icons to the images
//			File jarFile = new File(builder.getPublishDir(), APPLET_PATH + ICON_ZIP_FILE);
//			PublishingUtil.jarFiles(iconPath, jarFile);

			if ( options.isPublishJavaScriptTree() )
			{
				// also copy the icons to the no-applet folder
				LayoutResources.copyDir(iconPath.getAbsolutePath(), pubDir + NO_APPLET_PATH + ICON_PATH);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private String writeBookmark(Bookmark b) {

		// get the bookmark name from the bookmark url
		String bookmarkFileName = super.getViewDataFileName(b, BOOKMARK_SUFFIX_XML);
		XmlElement xml = b.getXmlElement();

		String buffer = PublishingUtil.getHtml(xml, BOOKMARK_XSL_FILE);
		if (buffer != null) {
			String htmlPath = getBookmarkHtmlPath(bookmarkFileName);
			FileUtil.writeUTF8File(htmlPath, buffer.toString());
		}

		return bookmarkFileName;
	}
	
	public void writePublishedBookmarks(List bookmarks, Bookmark defaultView) throws Exception {
		
		// first write xml for each individual view 
		BookmarkList list = new BookmarkList();

		if ((defaultView == null) && (bookmarks.size() > 0)) {
			defaultView = (Bookmark) bookmarks.get(0);
		}

		for (Iterator it = bookmarks.iterator(); it.hasNext();) {
			Bookmark b = (Bookmark) it.next();
			String fileName = writeBookmark(b);
			list.addBookmark(b.getPresentationName(), fileName,
					(defaultView == b));
		}
		
		// then write the top level bookmark xml
		XmlElement xml = list.getXmlElement();

		String buffer = PublishingUtil.getHtml(xml, BOOKMARKS_XSL_FILE);
		if (buffer != null) {
			String htmlPath = getBookmarkHtmlPath(PUBLISHED_BOOKMARKS_CFG_NAME);
			FileUtil.writeUTF8File(htmlPath, buffer.toString());
		}
	}
	
	protected void writeIndexAndTopNavHtml() {
				
		XmlElement optionXml = getOptionXml();		
				
		String buffer = PublishingUtil.getHtml(optionXml, INDEX_XSL_FILE);
		if (buffer != null) {
			String htmlPath = getIndexFilePath();
			FileUtil.writeUTF8File(htmlPath, buffer.toString());
		}

		buffer = PublishingUtil.getHtml(optionXml, TOPNAV_XSL_FILE);
		if (buffer != null) {
			String htmlPath = pubDir + "topnav.htm"; //$NON-NLS-1$
			FileUtil.writeUTF8File(htmlPath, buffer.toString());
		}
	}
	
	protected void writeSearchHtml() {

		boolean serverSideSearch = (options == null) || options.isIncludeServletSearch();
		
		String webAppName = options.getDynamicWebAppName();
		
		if (webAppName != null && webAppName.length() > 0 && serverSideSearch) {
		
			XmlElement searchXml = new XmlElement("Search").setAttribute( //$NON-NLS-1$
					"webAppName", webAppName); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			
			String buffer = PublishingUtil.getHtml(searchXml, SEARCH_XSL_FILE);
			if (buffer != null) {
				String htmlPath = getSearchFilePath();
				FileUtil.writeUTF8File(htmlPath + "search.html", buffer.toString()); //$NON-NLS-1$
			}
		}
	}
	
	protected String getBookmarkHtmlPath(String boomkarkFile) {
		int indx = boomkarkFile.lastIndexOf(BOOKMARK_SUFFIX_XML);
		return pubDir
				+ NO_APPLET_DIRECTORY + File.separatorChar
				+ boomkarkFile.substring(0, indx) + BOOKMARK_SUFFIX_HTML;
	}
	
	public String getIndexFilePath() {
		return pubDir + "index.htm"; //$NON-NLS-1$
	}
	
	private String getSearchFilePath() {
		return pubDir + File.separatorChar + "search" + File.separatorChar; //$NON-NLS-1$
	}

	protected XmlElement getOptionXml() {
		boolean showGlossary = (options == null) || options.isPublishGlossary();
		boolean showIndex = (options == null) || options.isPublishIndex();

		XmlElement optionXml = new XmlElement("PublishingOption"); //$NON-NLS-1$
		if ( options != null ) {		
			optionXml.setAttribute("title", options.getTitle()) //$NON-NLS-1$ //$NON-NLS-2$
			.setAttribute("bannerImage", options.bannerImage) //$NON-NLS-1$ //$NON-NLS-2$
			.setAttribute("bannerImageHeight", Integer.toString(options.getBannerImageHeight())) //$NON-NLS-1$ //$NON-NLS-2$
			.setAttribute(
					"feedbackUrl", options.feedbackURL) //$NON-NLS-1$ //$NON-NLS-2$
			.setAttribute("showGlossary", (showGlossary ? "true" : "false")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			.setAttribute("showIndex", (showIndex ? "true" : "false")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			.setAttribute("serverSideSearch", options.isIncludeServletSearch() ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

		}
		
		return optionXml;
	}
	
	public void dispose() {
		super.dispose();
	}
}
