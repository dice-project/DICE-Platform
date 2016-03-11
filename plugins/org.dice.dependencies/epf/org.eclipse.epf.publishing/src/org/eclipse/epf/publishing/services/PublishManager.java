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
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.common.serviceability.DebugTrace;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.diagram.core.services.DiagramManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.layout.Bookmark;
import org.eclipse.epf.library.layout.ElementLayoutManager;
import org.eclipse.epf.library.layout.HtmlBuilder;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.publishing.PublishingPlugin;
import org.eclipse.epf.publishing.PublishingResources;
import org.eclipse.epf.publishing.services.index.DefinitionObject;
import org.eclipse.epf.publishing.services.index.KeyWordIndexHelper;
import org.eclipse.epf.publishing.util.PublishingUtil;
import org.eclipse.epf.search.SearchService;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * Manages the publishing of a method configuration.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class PublishManager extends AbstractPublishManager {

	protected static final String KEYWORD_DEF_FILE = "keywordindexdef.txt"; //$NON-NLS-1$

	protected static final String PUBLISHING_REPORT_XSL_FILE = "xsl/PublishingReport.xsl"; //$NON-NLS-1$

	protected static final String PUBLISHING_REPORT_HTML_FILE = "PublishingReport.html"; //$NON-NLS-1$

	/**
	 * Creates a new instance.
	 */
	public PublishManager() {
	}

	protected AbstractViewBuilder createViewBuilder() {
		// boolean validateExternalLinks = (options != null)
		// && options.isCheckExternalLinks();

		PublishingContentValidator validator = null;
		ElementRealizer realizer = null;
		if ((options != null) && options.publishProcess) {
			validator = new ProcessPublishingContentValidator(pubDir, options);
			realizer = ProcessPublishingElementRealizer.newProcessPublishingElementRealizer(config,
					(ProcessPublishingContentValidator) validator);
		} else {
			validator = new PublishingContentValidator(pubDir, options);
			realizer = PublishingElementRealizer.newPublishingElementRealizer(config, validator);
		}

		// validator.setShowBrokenLinks(options.convertBrokenLinks == false);
		// validator.setShowExtraInfoForDescriptors(options
		// .isShowMethodContentInDescriptors());
		// validator.setShowRelatedDescriptors(options.showRelatedDescriptors);

		ElementLayoutManager layoutMgr = new ElementLayoutManager(config,
				pubDir, validator, true);
		layoutMgr.setElementRealizer(realizer);
		HtmlBuilder builder = new HtmlBuilder(layoutMgr);

		builder.enableContentScan(true);
		builder.enableTreeBrowser(true);

		ISiteGenerator generator = new DefaultSiteGenerator(builder, options);
		return new ConfigurationViewBuilder(generator);
	}

	protected void prePublish(IProgressMonitor monitor) throws Exception {

		// fixOptions(options);

		monitor.subTask(PublishingResources.initializingDirTask_name); //$NON-NLS-1$

		// make sure the publish directory is created
		File f = new File(viewBuilder.getHtmlBuilder().getPublishDir());
		if (!f.exists()) {
			f.mkdirs();
		}

		// copy the configuration files
		monitor.subTask(PublishingResources.copyingFilesTask_name); //$NON-NLS-1$
		/*
		 * copyDocRootFiles(); // copy localized files copyLocalizedFiles(); //
		 * copy user customized files last copyCustomizedFiles();
		 */
		getSiteGenerator().prePublish();

	}

	protected void doPublish(IProgressMonitor monitor) throws Exception {
		boolean suspendRefresh = RefreshJob.getInstance().isSuspendRefresh();
		if(! suspendRefresh) {
			RefreshJob.getInstance().setSuspendRefresh(true);
		}
		try {
			ResourceHelper.birt_publishing = false;
			ConfigurationHelper.getDelegate().buildDynamicCustomCategoriesMap(config);
			doPublish_(monitor);
			MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();			
		} finally {
			ResourceHelper.birt_publishing = true;
			ConfigurationHelper.getDelegate().clearDynamicCustomCategoriesMap();
			if (! suspendRefresh) {
				RefreshJob.getInstance().setSuspendRefresh(false);
				RefreshJob.getInstance().resumeRefresh();
			}
		}
	}
	
	private void doPublish_(IProgressMonitor monitor) throws Exception {
		// before doing publishing, load the whole library and remember the resouces loaded
		// so we can unload those resources later to free memory
		Collection<Resource> changedResources = loadLibrary(monitor);
		
		IRealizationManager mgr = ConfigurationHelper.getDelegate().getRealizationManager(config);
		if (mgr != null) {
			mgr.updateAllProcesseModels();
		}
		
		Collection<DiagramManager> existingMgrs = DiagramManager.getDiagramManagers();

		// don't copy all the content resources
		// let the system scan the content and copy only the required
		// resource
		// viewBuilder.getHtmlBuilder().enableContentScan(true);
		// viewBuilder.getHtmlBuilder().enableTreeBrowser(true);

		// generate bookmarks and published the contents as needed
		monitor.subTask(PublishingResources.generatingBookmarksTask_name); //$NON-NLS-1$
		generateBookmarks(monitor);

		String title = options == null ? "" : options.getTitle(); //$NON-NLS-1$

		if (!monitor.isCanceled()) {			
			// creating glossary
			if (options == null || options.isPublishGlossary()) {
				monitor.subTask(PublishingResources.generatingGlossaryTask_name); //$NON-NLS-1$
				new GlossaryBuilder().execute(config, pubDir, title, monitor);
			}
		}
		
		// make sure the resources are unloaded before return
		// now it's time to unload the resoruces
		Collection<DiagramManager> newMgrs = DiagramManager.getDiagramManagers();
		PublishingUtil.disposeDiagramManagers(newMgrs, existingMgrs);
		PublishingUtil.unloadResources(changedResources);
		changedResources.clear();
		existingMgrs.clear();
		newMgrs.clear();
		Runtime.getRuntime().gc();	
		
		if (monitor.isCanceled()) {
			return;
		}

		// creating content index
		if (options == null || options.isPublishIndex()) {
			monitor
					.subTask(PublishingResources.generatingBookmarkIndexTask_name); //$NON-NLS-1$
			// new IndexBuilder().execute(config, publishDir, monitor);
			generateBookmarkIndex(pubDir, title, monitor);
		}

		if (monitor.isCanceled()) {
			return;
		}

		// enable searching
		if (options != null && options.isPublishDynamicWebApp()
				&& options.isIncludeServletSearch()) {
			// generating the search index
			monitor.subTask(PublishingResources.generatingSearchIndexTask_name);
			SearchService.getInstance().createIndex(pubDir, false);
		}
	}

	protected void postPublish(IProgressMonitor monitor) throws Exception {
		// finally, generate a report
		getSiteGenerator().postPublish();

		createWARFile();

		getPublishReportUrl();
	}

	private void generateBookmarks(IProgressMonitor monitor) throws Exception {
		long beginTime = 0L;
		long startTime = 0L;
		if (profiling) {
			beginTime = startTime = System.currentTimeMillis();
		}
		List<Bookmark> bookmarks = viewBuilder.buildViews(monitor);
		if (profiling) {
			DebugTrace.print(viewBuilder, "buildViews", //$NON-NLS-1$
					(System.currentTimeMillis() - startTime) + " ms"); //$NON-NLS-1$
		}
		
		Bookmark defaultView = viewBuilder.getDefaultView();

		if (monitor.isCanceled()) {
			return;
		}

		// 3. finally, write the published bookmarks file
		if (profiling) {
			startTime = System.currentTimeMillis();
		}
		getSiteGenerator().writePublishedBookmarks(bookmarks, defaultView);
		if (profiling) {
			DebugTrace.print(getSiteGenerator(), "writePublishedBookmarks", //$NON-NLS-1$
					(System.currentTimeMillis() - startTime) + " ms"); //$NON-NLS-1$
			DebugTrace.print(this, "generateBookmarks", //$NON-NLS-1$
					(System.currentTimeMillis() - beginTime) + " ms"); //$NON-NLS-1$			
		}
	}

	/**
	 * get the url for the published site.
	 * 
	 * @return String
	 */
	public String getPublishedUrl() {
		if (success) {
			String url = getSiteGenerator().getIndexFilePath();
			File f = new File(url);
			if (f.exists()) {
				return url;
			}
		}

		return null;
	}

	protected void generateBookmarkIndex(String publishDir, String title,
			IProgressMonitor monitor) {
		try {

			URL url = new URL(PublishingPlugin.getDefault().getInstallURL(),
					KEYWORD_DEF_FILE);
			if (url == null) {
				System.out.println("Unable to get index definition file "); //$NON-NLS-1$
				return;
			}

			String deffile = Platform.resolve(url).getPath();
			String charSet = "utf-8"; //$NON-NLS-1$
			String helpFile = null;
			KeyWordIndexHelper indexHelper = new KeyWordIndexHelper(deffile,
					charSet, helpFile);
			DefinitionObject defObj = indexHelper.loadDefinition(publishDir);
			if (defObj != null) {
				defObj.setwwwRoot(publishDir);
				defObj.setIndexTitle(PublishingResources.indexLabel_text
						+ " - " + title); //$NON-NLS-1$ //$NON-NLS-2$
			}

			indexHelper.execute(monitor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * generate the publishing report and return the url
	 * 
	 * @return String the report url
	 */
	public String getPublishReportUrl() {
		String publishDir = viewBuilder.getHtmlBuilder().getPublishDir();
		String file_pathname = PublishingContentValidator.LOGS_FOLDER
				+ File.separator + PUBLISHING_REPORT_HTML_FILE;
		File f = new File(publishDir, file_pathname);
		String report_file = f.getAbsolutePath();
		if (!f.exists()) {
			File errorLog = new File(publishDir,
					PublishingContentValidator.LOGS_FOLDER + File.separator
							+ PublishingContentValidator.ERROR_LOG_FILENAME);
			File warningLog = new File(publishDir,
					PublishingContentValidator.LOGS_FOLDER + File.separator
							+ PublishingContentValidator.WARNING_LOG_FILENAME);
			File infoLog = new File(publishDir,
					PublishingContentValidator.LOGS_FOLDER + File.separator
							+ PublishingContentValidator.INFO_LOG_FILENAME);

			// geenrate the index.htm
			XmlElement reportXml = new XmlElement("PublishingReport"); //$NON-NLS-1$

			reportXml.newChild("pubDir").setAttribute("path", publishDir); //$NON-NLS-1$ //$NON-NLS-2$
			reportXml
					.newChild("errorLog") //$NON-NLS-1$
					.setAttribute(
							"name", PublishingContentValidator.ERROR_LOG_FILENAME) //$NON-NLS-1$
					.setAttribute("path", "./" + errorLog.getName()); //$NON-NLS-1$ //$NON-NLS-2$

			reportXml
					.newChild("warningLog") //$NON-NLS-1$
					.setAttribute(
							"name", PublishingContentValidator.WARNING_LOG_FILENAME) //$NON-NLS-1$
					.setAttribute("path", "./" + warningLog.getName()); //$NON-NLS-1$ //$NON-NLS-2$

			reportXml
					.newChild("infoLog") //$NON-NLS-1$
					.setAttribute(
							"name", PublishingContentValidator.INFO_LOG_FILENAME) //$NON-NLS-1$
					.setAttribute("path", "./" + infoLog.getName()); //$NON-NLS-1$ //$NON-NLS-2$

			// detail information
			PublishingContentValidator validator = (PublishingContentValidator) viewBuilder
					.getHtmlBuilder().getValidator();
			reportXml.addChild(validator.getReport());

			String buffer = PublishingUtil.getHtml(reportXml,
					PUBLISHING_REPORT_XSL_FILE);
			if (buffer != null) {
				FileUtil.writeUTF8File(report_file, buffer.toString());
			}
		}

		return report_file;
	}

	protected void createWARFile() throws Exception {
		if (options != null && options.isPublishDynamicWebApp()
				&& options.getDynamicWebAppName().length() > 0) {
			SearchService.getInstance().createWAR(pubDir,
					options.getDynamicWebAppName());
		}
	}

	public void dispose() {
		super.dispose();
	}
}
