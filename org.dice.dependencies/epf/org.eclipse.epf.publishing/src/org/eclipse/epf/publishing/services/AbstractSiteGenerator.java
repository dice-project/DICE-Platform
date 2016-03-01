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
package org.eclipse.epf.publishing.services;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.layout.Bookmark;
import org.eclipse.epf.library.layout.HtmlBuilder;
import org.eclipse.epf.library.layout.LayoutResources;
import org.eclipse.epf.publishing.PublishingPlugin;

/**
 * The abstract base class for a Site Generator.
 * 
 * @author Jinhua Xi
 */
public abstract class AbstractSiteGenerator implements ISiteGenerator {
	
	public static final String DOC_ROOT = "docroot/"; //$NON-NLS-1$;

	private static final String WEB_INF_PATH = "docroot/WEB-INF"; //$NON-NLS-1$
	private static final String WEB_INF_DIR = "WEB-INF"; //$NON-NLS-1$
	public static final String customizedName = "customized"; //$NON-NLS-1$
	
	protected String pubDir;
	protected PublishHTMLOptions options;
	protected HtmlBuilder builder;
	
	public AbstractSiteGenerator(HtmlBuilder builder, PublishHTMLOptions options) {
		this.options = options;
		this.builder = builder;
		this.pubDir = builder.getPublishDir();
	}

	public abstract HtmlBuilder getHtmlBuilder();

	public abstract String getIndexFilePath();

	public abstract String getNodeIconPath(); 

	public abstract String getDefaultBannerImageFile();
	
	public abstract PublishOptions getPublishOptions();

	public abstract void postPublish() throws Exception;

	public abstract void prePublish() throws Exception;
	
	public abstract void writePublishedBookmarks(List bookmarks, Bookmark defaultView)
			throws Exception;
	
	protected void copyCustomizedFiles() throws Exception {
		if (options.bannerImage != null && options.bannerImage.length() > 0) {
			FileUtil.copyFile(options.bannerImage, pubDir + "images"); //$NON-NLS-1$
			options.bannerImage = FileUtil.getFileName(options.bannerImage);
		} else {
			options.bannerImage = getDefaultBannerImageFile();
		}
		
		if (options.isPublishDynamicWebApp()) {
			URL plugin_url = PublishingPlugin.getDefault().getInstallURL();
			String path = FileLocator.resolve(new URL(plugin_url, WEB_INF_PATH)).getPath();
			String includes = "*.*, **/"; //$NON-NLS-1$
			LayoutResources.copyDir(path, pubDir + WEB_INF_DIR, includes, null);
		}
	}

	protected void copyLocalizedFiles() throws Exception {

		// copy scripts, rename the localse specific name to the default
		// name
		PublishingPlugin
				.getDefault()
				.copyLocalizedFiles(
						DOC_ROOT + "scripts/", new File(pubDir, "scripts/"), true, false); //$NON-NLS-1$ //$NON-NLS-2$

		PublishingPlugin.getDefault().copyLocalizedFiles(
				DOC_ROOT + "images/", new File(pubDir, "images/"), true, false); //$NON-NLS-1$ //$NON-NLS-2$

		PublishingPlugin.getDefault().copyLocalizedFiles(DOC_ROOT,
				new File(pubDir, "/"), false, false); //$NON-NLS-1$ //$NON-NLS-2$

	}
	
	protected String getViewDataFileName(Bookmark b, String ext) {
		File f = new File(b.getFileName());
		String fileName = f.getName();
		int i = fileName.lastIndexOf("."); //$NON-NLS-1$
		if (i >= 0) {
			fileName = fileName.substring(0, i);
		}

		return fileName + ext; //$NON-NLS-1$
	}
	
	public void dispose() {
		if ( builder != null ) {
			builder.getLayoutManager().clear();
			builder.dispose();
			builder = null;
		}
	}
}
