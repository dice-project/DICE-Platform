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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.common.service.utils.CommandLineRunUtil;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.edit.ui.UIHelper;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.LibraryUtil.ConfigAndPlugin;
import org.eclipse.epf.publishing.services.PublishHTMLOptions;
import org.eclipse.epf.publishing.services.PublishManager;
import org.eclipse.epf.publishing.services.PublishOptions;
import org.eclipse.epf.publishing.ui.PublishingUIPlugin;
import org.eclipse.epf.publishing.ui.PublishingUIResources;
import org.eclipse.epf.publishing.ui.preferences.PublishingUIPreferences;
import org.eclipse.epf.ui.wizards.BaseWizard;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * A wizard that guides the user to publish a method configuration to a web
 * site.
 * 
 * @author Kelvin Low
 * @author Bingxue Xu
 * @author Jinhua Xi
 * @since 1.0
 */
public class PublishConfigWizard extends BaseWizard implements INewWizard {

	/**
	 * The wizard ID.
	 */
	public static final String WIZARD_ID = PublishConfigWizard.class.getName();

	/**
	 * The Publish Configuration wizard extension point ID.
	 */
	public static final String WIZARD_EXTENSION_POINT_ID = "org.eclipse.epf.publishing.ui.publishConfigWizard"; //$NON-NLS-1$	

	// The select configuration wizard page.
	protected SelectConfigPage selectConfigPage;

	// The select configuration content wizard page.
	protected SelectContentPage selectContentPage;

	// The select publishing options wizard page.
	protected SelectPublishingOptionsPage selectPublishingOptionsPage;

	// The select destination wizard page.
	protected SelectDestinationPage selectDestinationPage;

	/**
	 * Creates a new instance.
	 */
	public PublishConfigWizard() {
		super();
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#init(IWorkbench,
	 *      IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(PublishingUIResources.publishConfigWizard_title);
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#getWizardExtenderExtensionPointId()
	 */
	public String getWizardExtenderExtensionPointId() {
		return WIZARD_EXTENSION_POINT_ID;
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		if (wizardExtender == null) {
			selectConfigPage = new SelectConfigPage();
			selectContentPage = new SelectContentPage();
			selectPublishingOptionsPage = new SelectPublishingOptionsPage();
			selectDestinationPage = new SelectDestinationPage();
			super.addPage(selectConfigPage);
			super.addPage(selectContentPage);
			super.addPage(selectPublishingOptionsPage);
			super.addPage(selectDestinationPage);
		} else {
			List<IWizardPage> wizardPages = new ArrayList<IWizardPage>();

			IWizardPage page = wizardExtender
					.getReplaceWizardPage(SelectConfigPage.PAGE_NAME);
			if (page != null) {
				wizardPages.add(page);
			} else {
				selectConfigPage = new SelectConfigPage();
				wizardPages.add(selectConfigPage);
			}

			page = wizardExtender
					.getReplaceWizardPage(SelectContentPage.PAGE_NAME);
			if (page != null) {
				wizardPages.add(page);
			} else {
				selectContentPage = new SelectContentPage();
				wizardPages.add(selectContentPage);
			}

			page = wizardExtender
					.getReplaceWizardPage(SelectPublishingOptionsPage.PAGE_NAME);
			if (page != null) {
				wizardPages.add(page);
			} else {
				selectPublishingOptionsPage = new SelectPublishingOptionsPage();
				wizardPages.add(selectPublishingOptionsPage);
			}

			page = wizardExtender
					.getReplaceWizardPage(SelectDestinationPage.PAGE_NAME);
			if (page != null) {
				wizardPages.add(page);
			} else {
				selectDestinationPage = new SelectDestinationPage();
				wizardPages.add(selectDestinationPage);
			}

			super.getNewWizardPages(wizardPages);

			for (Iterator it = wizardPages.iterator(); it.hasNext();) {
				IWizardPage wizardPage = (IWizardPage) it.next();
				super.addPage(wizardPage);
			}

			wizardExtender.initWizardPages(wizardPages);
		}
	}

	@Override
	public IWizardPage getStartingPage() {
		IWizardPage page = wizardExtender == null ?  null : wizardExtender.getStartingPage();
		if (page != null) {
			return page;
		}
		return super.getStartingPage();
	}
	
	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		//override the base wizard's logic, 
		//let the wizard extender drive the page flow when there is a wizard extender
		IWizardPage nextPage = null;
		if (wizardExtender != null) {
			nextPage = wizardExtender.getNextPage(page);
		}
		else {
			nextPage = super.getNextPage(page);
		}
		return nextPage;
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(Composite)
	 */
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		pageContainer.getShell().setImage(
				AuthoringUIPlugin.getDefault().getSharedImage(
						"full/obj16/MethodConfiguration.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		if (wizardExtender != null) {
			return wizardExtender.doFinish();
		} else {
			PublishManager publisher = new PublishManager();
			boolean success = publishConfig(selectConfigPage.getSelectedConfig(),
					getPublishingOptions(), publisher);
			if (publisher != null) {
				publisher.dispose();
			}
			return success;
		}
	}

	/**
	 * Publishes the selected method configuration.
	 * 
	 * @param configName
	 *            the method configuration name
	 * @param options
	 *            the publishing options
	 */
	public boolean publishConfig(MethodConfiguration config, PublishOptions options,
			PublishManager publisher) {
		
		ConfigAndPlugin tempConfigAndPlugin = null;
		try {
			if (checkAndCreateDir(options)) {
				MethodConfiguration realConfig = config;
				if (UmaUtil.getMethodLibrary(config) == null && 		//config passed is a mock config
					options.isPublishProcess()) {
					tempConfigAndPlugin = LibraryUtil.addTempConfigAndPluginToCurrentLibrary(options.getProcesses());
					if (tempConfigAndPlugin != null && tempConfigAndPlugin.config != null) {
						realConfig = tempConfigAndPlugin.config;					
					}
				}
												
				publisher.init(options.getPublishDir(), realConfig, options);
				PublishingOperation operation = new PublishingOperation(
						publisher);

				PublishProgressMonitorDialog dlg = new PublishProgressMonitorDialog(
						Display.getCurrent().getActiveShell(), publisher
								.getViewBuilder());

				UIHelper.runWithProgress(operation, dlg, true,
						PublishingUIResources.publishConfigWizard_title);

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			PublishingUIPlugin.getDefault().getMsgDialog().displayError(
					PublishingUIResources.publishConfigWizard_title,
					PublishingUIResources.publishConfigError_msg,
					PublishingUIResources.publishConfigError_reason, e);
		} finally {
			LibraryUtil.removeTempConfigAndPluginFromCurrentLibrary(tempConfigAndPlugin);			
			if (selectPublishingOptionsPage != null) {
				selectPublishingOptionsPage.savePreferences();
			}
			if (selectDestinationPage != null) {
				selectDestinationPage.savePreferences();
			}
			if (selectContentPage != null) {
				selectContentPage.savePreferences();
			}
			String configId = config.getGuid();
			PublishingUIPreferences.setConfigPrefInitialized(configId, true);
			PublishingUIPreferences.saveAllPreferences();
		}

		return true;
	}

	/**
	 * Gets the user specified publishing options.
	 * 
	 * @return the user specified publishing options.
	 */
	public PublishOptions getPublishingOptions() {
		PublishOptions options;
		
		if (selectPublishingOptionsPage != null) {
			options = selectPublishingOptionsPage.getPublishingOptions();
		}
		else {
			options = new PublishOptions();
		}

		if (selectContentPage != null) {
			boolean publishConfig = selectContentPage.getPublishConfigSelection();
			options.setPublishConfiguration(publishConfig);
			options.setPublishProcess(!publishConfig);
			if (!publishConfig) {
				options.setProcesses(selectContentPage.getSelectedProcesses());
			}
		}
		
		if (selectDestinationPage != null) {
			options.setPublishDir(selectDestinationPage.getPublishDirectory());

			if (!selectDestinationPage.getStaticWebSiteSelection()
					&& options instanceof PublishHTMLOptions) {
				PublishHTMLOptions htmlOptions = (PublishHTMLOptions) options;
				htmlOptions.setPublishDynamicWebApp(true);
				htmlOptions.setDynamicWebAppName(selectDestinationPage
						.getWebAppName());
				htmlOptions.setIncludeServletSearch(selectDestinationPage
						.getIncludeSearchSelection());
			}
		}

		return options;
	}

	/**
	 * Checks and creates the destination path where the method configuration
	 * will be published.
	 * 
	 * @param options
	 *            the publishing options
	 * @return <code>true</code> if the destination path is valid,
	 *         <code>false</code> otherwise
	 */
	public boolean checkAndCreateDir(PublishOptions options) {
		String dir = options.getPublishDir();
		String defaultPublishPath = PublishingUIPreferences
				.getDefaultPublishPath();
		boolean answer = false;

		IPath ecPath = Path.fromOSString(dir);
		if (!ecPath.isAbsolute()) {
			String path = defaultPublishPath
					+ System.getProperty("file.separator") + dir; //$NON-NLS-1$
			answer = PublishingUIPlugin
					.getDefault()
					.getMsgDialog()
					.displayPrompt(
							PublishingUIResources.publishConfigDialog_title,
							PublishingUIResources
									.bind(
											PublishingUIResources.confirmPathDialog_text,
											path));

			if (answer) {
				options.setPublishDir(dir);
			} else {
				return false;
			}
		}

		File file = new File(dir);

		if (file.exists()) {
			File[] files = file.listFiles();
			if (files != null && files.length > 0) {
				answer =  CommandLineRunUtil.getInstance().isNeedToRun() ? true : PublishingUIPlugin
						.getDefault()
						.getMsgDialog()
						.displayConfirmation(
								PublishingUIResources.publishConfigDialog_title,
								PublishingUIResources
										.bind(
												PublishingUIResources.overwriteDialog_text,
												dir));

				if (answer == true) {
					try {
						answer = FileUtil
								.deleteAllFiles(file.getAbsolutePath());
						if (answer == false) {
							PublishingUIPlugin
									.getDefault()
									.getMsgDialog()
									.displayError(
											PublishingUIResources.publishConfigDialog_title,
											PublishingUIResources.cannotPublishError_msg,
											PublishingUIResources
													.bind(
															PublishingUIResources.deleteFilesError_reason,
															dir));
							return false;

						}
					} catch (Exception e) {
						PublishingUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayError(
										PublishingUIResources.publishConfigDialog_title,
										PublishingUIResources.cannotPublishError_msg,
										PublishingUIResources
												.bind(
														PublishingUIResources.deleteFilesError_reason,
														dir), e);
						return false;
					}
				}
			} else {
				return true;
			}
		} else {
			try {
				answer = file.mkdirs();
			} catch (Exception e) {
				PublishingUIPlugin.getDefault().getMsgDialog().displayError(
						PublishingUIResources.publishConfigDialog_title,
						PublishingUIResources.cannotPublishError_msg,
						PublishingUIResources.bind(
								PublishingUIResources.createDirError_reason,
								file.getAbsolutePath()), e);
				return false;
			}
			if (!answer) {
				PublishingUIPlugin.getDefault().getMsgDialog().displayError(
						PublishingUIResources.publishConfigDialog_title,
						PublishingUIResources.cannotPublishError_msg,
						PublishingUIResources.bind(
								PublishingUIResources.createDirError_reason,
								file.getAbsolutePath()));
				return false;
			}
		}

		return answer;
	}

}