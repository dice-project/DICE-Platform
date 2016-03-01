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
package org.eclipse.epf.publishing.ui.internal.wizards;

import java.lang.reflect.InvocationTargetException;
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

/**
 * The publish configuration operation.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class PublishConfigOperation implements IRunnableWithProgress {

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
	public PublishConfigOperation(AbstractPublishManager publishMgr) {
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

				if (published_url == null || report_url == null) {
					msgDialog.displayError(PUBLISH_CONFIG_ERROR_TITLE,
							PUBLISH_CONFIG_ERROR_MSG,
							PUBLISH_CONFIG_ERROR_REASON, runException);
					return;
				}

				PublishOptions options = publishMgr.getViewBuilder()
						.getOptions();
				if (options instanceof PublishHTMLOptions) {
					if (!((PublishHTMLOptions) options)
							.isPublishDynamicWebApp()) {
						// Launch a HTML browser to view the published site.
						if (openBrowser(published_url) == false) {
							String reason = MessageFormat.format(
									OPEN_BROWSER_ERROR_REASON,
									new Object[] { published_url });
							msgDialog.displayError(PREVIEW_CONFIG_ERROR_TITLE,
									PREVIEW_CONFIG_ERROR_MSG, reason);
						}
					}
				}

				// Launch a HTML browser to view the report on the published
				// site.
				if (openBrowser(report_url) == false) {
					String reason = MessageFormat.format(
							OPEN_BROWSER_ERROR_REASON,
							new Object[] { report_url });
					msgDialog.displayError(VIEW_REPORT_ERROR_TITLE,
							VIEW_REPORT_ERROR_MSG, reason);
				}
			}
		});
	}

	/**
	 * open a browser to show the published content.
	 * 
	 * @param url
	 *            String the site url
	 * @return boolean true if browser opened successfully.
	 */
	public boolean openBrowser(final String url) {
		boolean success = false;
		try {
			// Launch the default HTML viewer to display the HTML page.
			// On Linux, this can't launch urls, only local files can be
			// launched.
			// Sorry I have use internal class here.
			// hope Eclipse.org can have a public api to do so.
			if (SWT.getPlatform().equals("win32")) { //$NON-NLS-1$
				success = Program.launch(url);
			} else {
				// IBrowser browser =
				// org.eclipse.help.internal.browser.BrowserManager
				// .getInstance().createBrowser(true);
				// if (browser == null) {
				// try {
				// browser.displayURL(url);
				// success = true;
				// } catch (Exception e) {
				// e.printStackTrace();
				// }
				// }

				// 155490 - Cannot detected default browser when preview
				// published configuration on Linux
				// changed to use swt browser
				success = true;
				Display.getCurrent().asyncExec(new Runnable() {
					public void run() {
						// // open another swt browser since we have no (simple)
						// way of creating a system default browser
						// // the Eclipse guys hide all the good apis as
						// internal
						Shell shell = new Shell();
						shell.setText(url);
						GridLayout layout = new GridLayout();
						layout.marginHeight = 0;
						layout.marginWidth = 0;
						shell.setLayout(layout);
						Browser popup_browser = new Browser(shell, SWT.NONE);
						popup_browser.setLayoutData(new GridData(
								GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL
										| GridData.GRAB_VERTICAL));
						popup_browser.setUrl(url);
						shell.open();
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}

		return success;
	}

}
