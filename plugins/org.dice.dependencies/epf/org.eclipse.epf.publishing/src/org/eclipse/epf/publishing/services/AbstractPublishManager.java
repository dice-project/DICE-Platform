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
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.common.serviceability.DebugTrace;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.util.FileNameGenerator;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.publishing.PublishingPlugin;
import org.eclipse.epf.publishing.PublishingResources;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.ui.PlatformUI;

/**
 * The abstract base class for all Publish Managers.
 * <p>
 * Use the org.eclipse.epf.publishing.ui.publishers extension point to extend
 * this class if you need to customize the publishing.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public abstract class AbstractPublishManager {

	protected static boolean debug = PublishingPlugin.getDefault()
			.isDebugging();

	protected static boolean profiling = PublishingPlugin.getDefault()
			.isProfiling();

	protected String pubDir;

	protected MethodConfiguration config;

	protected PublishHTMLOptions options;

	protected AbstractViewBuilder viewBuilder;

	protected boolean success = false;

	/**
	 * Creates a new instance.
	 */
	public AbstractPublishManager() {
	}

	/**
	 * Initilizes the publishing manager.
	 * 
	 * @param pubDir
	 *            the publish directory
	 * @param config
	 *            a method configuration
	 * @param options
	 *            the publishing options
	 */
	public void init(String pubDir, MethodConfiguration config,
			PublishOptions options) {
		this.pubDir = pubDir;

		if (!this.pubDir.endsWith(File.separator)) {
			this.pubDir += File.separator;
		}

		this.config = config;
		if (options instanceof PublishHTMLOptions) {
			this.options = (PublishHTMLOptions) options;
		} else {
			throw new IllegalArgumentException();
		}
		this.viewBuilder = createViewBuilder();

		// clear the file name cache
		// NameCache.getInstance().clear();
	}

	/**
	 * get the publishing view builder. The view builder is responsible to build
	 * the publishing views and contents.
	 * 
	 * @return AbstractViewBuilder
	 */
	public AbstractViewBuilder getViewBuilder() {
		return this.viewBuilder;
	}

	public ISiteGenerator getSiteGenerator() {
		return viewBuilder.getSiteGenerator();
	}

	/**
	 * do publishing. The publishing process is defined in three steps.
	 * prePublish to do some preparation before the actual publishing doPublish
	 * to publish the views and contents postPublish to do some cleanup work
	 * after publish. Extended classes can override these methods to achieve
	 * additional publishing results.
	 * 
	 * @param monitor
	 *            IProgressMonitor
	 * @throws PublishingServiceException
	 */
	public void publish(IProgressMonitor monitor)
			throws PublishingServiceException {

		if (options != null) {
			options.validate();
		}

		// clear the file name cache so that the file name will be based on the latest library data
		// this can avoid the cases where element name changed before publishing
		FileNameGenerator.INSTANCE.clear();
		
		long beginTime = 0L;
		long startTime = 0L;

		// do a gc before getting stared
		Runtime.getRuntime().gc();
		
		try {
			ConfigurationHelper.getDelegate().setPublishingMode(true);
			if (! ConfigurationHelper.serverMode) {
				SafeUpdateController.syncExec(new Runnable() {	
					public void run() {
						PlatformUI.
						getWorkbench().
						getActiveWorkbenchWindow().
						getActivePage().
						closeAllEditors(true);
					}
				});
			}
			
			if (profiling) {
				beginTime = startTime = System.currentTimeMillis();
			}
			FileUtil.setCopiedFileMap(new HashMap<File, File>());
			
			prePublish(monitor);
			if (profiling) {
				DebugTrace.print(this, "prePublish", //$NON-NLS-1$
						(System.currentTimeMillis() - startTime) + " ms"); //$NON-NLS-1$
			}

			if (profiling) {
				startTime = System.currentTimeMillis();
			}
			
			getViewBuilder().getLayoutMgr().prepareElementPathAdjustment();
			doPublish(monitor);
			getViewBuilder().getLayoutMgr().handlePluginFolderRename();
			if (profiling) {
				DebugTrace.print(this, "doPublish", //$NON-NLS-1$
						(System.currentTimeMillis() - startTime) + " ms"); //$NON-NLS-1$
			}

			if (profiling) {
				startTime = System.currentTimeMillis();
			}
			postPublish(monitor);
			if (profiling) {
				DebugTrace.print(this, "postPublish", //$NON-NLS-1$
						(System.currentTimeMillis() - startTime) + " ms"); //$NON-NLS-1$
			}

			success = true;
		} catch (Exception e) {
			throw new PublishingServiceException(e);
		} finally {
			FileUtil.setCopiedFileMap(null);
			
			ConfigurationHelper.getDelegate().setPublishingMode(false);
			if (profiling) {
				System.out
						.println("Time taken to publish configuration '" + config.getName() //$NON-NLS-1$
								+ "': " + (System.currentTimeMillis() - beginTime) + " ms"); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}

	protected Collection<Resource> loadLibrary(IProgressMonitor monitor) {
		// first of all, we need to load the library,
		// otherwise, some relationships and opposite features are not
		// established
		long startTime = 0L;
		monitor.subTask(PublishingResources.loadLibraryTask_name);
		if (profiling) {
			startTime = System.currentTimeMillis();
		}
		
		Collection<Resource> changedResources = LibraryUtil.loadAll((MethodLibrary) config.eContainer(), config);
		if (profiling) {
			DebugTrace.print(this, "loadLibrary", "LibraryUtil.loadAll: " //$NON-NLS-1$  //$NON-NLS-2$
							+ (System.currentTimeMillis() - startTime)
							+ " ms"); //$NON-NLS-1$
		}

		return changedResources;
	}
	
//	protected void unloadResources(Collection<Resource> resources) {
//		
//		//Runtime.getRuntime().gc();
//		//long m1 = Runtime.getRuntime().freeMemory()/1000000;
//		PersistenceUtil.unload(resources);
//		//Runtime.getRuntime().gc();		
//		//long m2 = Runtime.getRuntime().freeMemory()/1000000;
//		//System.out.println("*** " + Long.toString(m2-m1) + " MB freed by unloading resources");
//	}
//
//	protected void disposeDiagramManagers(Collection<DiagramManager> mgrs, Collection<DiagramManager> keeplist) {
//		for ( Iterator<DiagramManager> it = mgrs.iterator(); it.hasNext(); ) {
//			DiagramManager mgr = it.next();
//			if ( keeplist == null || !keeplist.contains(mgr) ) {
//				mgr.dispose();
//			}
//		}
//	}
	
	/**
	 * Disposes the object. Once disposed, the object is not valid any more.
	 */
	public void dispose() {
		if (viewBuilder != null) {
			viewBuilder.dispose();
			viewBuilder = null;
		}
	}

	/**
	 * abstract method to get the url to the published site
	 * 
	 * @return String the url
	 */
	public abstract String getPublishedUrl();

	/**
	 * abstract method to get the url to the publish report
	 * 
	 * @return String
	 */
	public abstract String getPublishReportUrl();

	protected abstract AbstractViewBuilder createViewBuilder();

	protected abstract void prePublish(IProgressMonitor monitor)
			throws Exception;

	protected abstract void doPublish(IProgressMonitor monitor)
			throws Exception;

	protected abstract void postPublish(IProgressMonitor monitor)
			throws Exception;

}
