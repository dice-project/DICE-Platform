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
package org.eclipse.epf.publishing.ui.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.publishing.PublishingResources;
import org.eclipse.epf.publishing.services.AbstractPublishManager;
import org.eclipse.epf.publishing.services.AbstractViewBuilder;
import org.eclipse.epf.publishing.services.PublishHTMLOptions;
import org.eclipse.epf.publishing.services.PublishOptions;
import org.eclipse.epf.publishing.ui.PublishingUIPlugin;
import org.eclipse.epf.publishing.ui.PublishingUIResources;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

/**
 * Performs the real work of publishing a Method Configuration.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class PublishingOperation implements IRunnableWithProgress, org.eclipse.epf.library.edit.util.IRunnableWithProgress {

	private static final String PUBLISH_CONFIG_ERROR_TITLE = PublishingUIResources.publishConfigDialog_title; //$NON-NLS-1$

	private static final String PUBLISH_CONFIG_ERROR_MSG = PublishingUIResources.publishConfigError_msg; //$NON-NLS-1$

	private static final String PUBLISH_CONFIG_ERROR_REASON = PublishingUIResources.publishConfigError_reason; //$NON-NLS-1$

	private static final String PREVIEW_CONFIG_ERROR_TITLE = PublishingUIResources.previewConfigError_title; //$NON-NLS-1$

	private static final String PREVIEW_CONFIG_ERROR_MSG = PublishingUIResources.previewConfigError_msg; //$NON-NLS-1$

	private static final String VIEW_REPORT_ERROR_TITLE = PublishingUIResources.viewReportError_title; //$NON-NLS-1$

	private static final String VIEW_REPORT_ERROR_MSG = PublishingUIResources.viewReportError_msg; //$NON-NLS-1$	

	private static final String OPEN_BROWSER_ERROR_REASON = PublishingUIResources.openBrowserError_reason; //$NON-NLS-1$

	private static final String PUBLISH_CONFIG_CANCEL_MSG = PublishingUIResources.cancelPublishConfig_msg; //$NON-NLS-1$

	private AbstractPublishManager publishMgr;

	private String published_url;

	private String report_url;

	private MsgDialog msgDialog;

	private Exception runException;

	/**
	 * Creates a new instance.
	 */
	public PublishingOperation(AbstractPublishManager publishMgr) {
		super();
		this.publishMgr = publishMgr;
		this.msgDialog = PublishingUIPlugin.getDefault().getMsgDialog();
	}

	/**
	 * get the view builder.
	 * 
	 * @return AbstractViewBuilder
	 */
	public AbstractViewBuilder getViewBuilder() {
		return this.publishMgr.getViewBuilder();
	}

	/**
	 * get the url for the published site
	 * 
	 * @return String
	 */
	public String getPublishedUrl() {
		return published_url;
	}

	/**
	 * run the operation to perform the publishing task.
	 * 
	 * @param monitor
	 *            IProgressMonitor
	 * @throws InvocationTargetException
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		
		try {
			_runIt(monitor);
		} finally {
			this.publishMgr = null;
			this.msgDialog = null;
		}
	}

	/**
	 * run the operation to perform the publishing task.
	 * 
	 * @param monitor
	 *            IProgressMonitor
	 * @throws InvocationTargetException
	 */
	private void _runIt(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		runException = null;
		try {
			monitor
					.setTaskName(PublishingResources.publishingConfigurationTask_name); //$NON-NLS-1$			
			publishMgr.publish(monitor);

			published_url = publishMgr.getPublishedUrl();
			report_url = publishMgr.getPublishReportUrl();
		} catch (Exception e) {
			runException = e;
		}

		final boolean canceled = monitor.isCanceled();

		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				if (canceled) {
					msgDialog.displayInfo(PUBLISH_CONFIG_ERROR_TITLE,
							PUBLISH_CONFIG_CANCEL_MSG);
					return;
				}

				if (report_url == null) {
					msgDialog.displayError(PUBLISH_CONFIG_ERROR_TITLE,
							PUBLISH_CONFIG_ERROR_MSG,
							PUBLISH_CONFIG_ERROR_REASON, runException);
					return;
				}

				PublishOptions options = publishMgr.getViewBuilder()
						.getOptions();
				boolean toOpenPublishedUri = false;
				if (options instanceof PublishHTMLOptions) {
					if (!((PublishHTMLOptions) options)
							.isPublishDynamicWebApp()) {
						toOpenPublishedUri = true;
						if (published_url == null) {
							msgDialog.displayError(PUBLISH_CONFIG_ERROR_TITLE,
									PUBLISH_CONFIG_ERROR_MSG,
									PUBLISH_CONFIG_ERROR_REASON, runException);
							return;
						}

					}
				}
				
				// removed launch of published_url
				// because launching 2 URLS at nearly the same time 
				// was causing problems with IE7 & Firefox
				// instead, the report HTML file has an
				// onload script of the body element that will
				// load the published url
				
				// Launch a HTML browser to view the published site.
				if (toOpenPublishedUri && openBrowser(published_url) == false) {
					String reason = MessageFormat.format(
							OPEN_BROWSER_ERROR_REASON,
							new Object[] { published_url });
					msgDialog.displayError(PREVIEW_CONFIG_ERROR_TITLE,
							PREVIEW_CONFIG_ERROR_MSG, reason);
				}


				// Launch a HTML browser to view the report on the published
				// site.
				if (openSWTBrowser(report_url) == false) {
					String reason = MessageFormat.format(
							OPEN_BROWSER_ERROR_REASON,
							new Object[] { report_url });
					msgDialog.displayError(VIEW_REPORT_ERROR_TITLE,
							VIEW_REPORT_ERROR_MSG, reason);
				}
			}
		});
		

	}
	
	public boolean openSWTBrowser(final String urlString) {
		boolean success = false;
		try {
			Shell shell = new Shell(Display.getDefault(), SWT.SHELL_TRIM);
			shell.setText(urlString);
			GridLayout layout = new GridLayout();
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			shell.setLayout(layout);
			Browser popup_browser = new Browser(shell, SWT.NONE);
			popup_browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			popup_browser.setUrl(urlString);
			shell.open();
			success = true;
		} catch (Exception ex) {
			PublishingUIPlugin.getDefault().getLogger().logError(ex);
		}
		return success;
	}

	/**
	 * open a browser to show the published content.
	 * 
	 * @param urlString
	 *            String the site url
	 * @return boolean true if browser opened successfully.
	 */
	public boolean openBrowser(final String urlString) {
		boolean success = false;
		try {
			if (SWT.getPlatform().equals("win32")) { //$NON-NLS-1$
				success = Program.launch(urlString);
			} else {
				success = true;
				IWebBrowser browser = null;
				IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
				try {
					browser = browserSupport.getExternalBrowser();
					if (browser != null) {
						URL url = new File(urlString).toURL();
						browser.openURL(url);
					}
				} catch (PartInitException ex) {
					PublishingUIPlugin.getDefault().getLogger().logError(ex);
				} catch (MalformedURLException ex) {
					PublishingUIPlugin.getDefault().getLogger().logError(ex);
				}
			}
		} catch (Exception e) {
			PublishingUIPlugin.getDefault().getLogger().logError(e);
			success = false;
		}

		return success;
	}

}