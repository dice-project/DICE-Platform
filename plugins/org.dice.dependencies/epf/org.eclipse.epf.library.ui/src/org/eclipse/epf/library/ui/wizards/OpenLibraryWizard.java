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
package org.eclipse.epf.library.ui.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.common.service.utils.CommandLineRunUtil;
import org.eclipse.epf.common.service.versioning.VersionUtil;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.ui.LibraryUIManager;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.library.xmi.XMILibraryManager;
import org.eclipse.epf.library.xmi.XMILibraryUtil;
import org.eclipse.epf.persistence.migration.UpgradeCallerInfo;
import org.eclipse.epf.ui.wizards.BaseWizard;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * A wizard that guides the user to open a method library.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class OpenLibraryWizard extends BaseWizard implements INewWizard {

	/**
	 * The wizard ID.
	 */
	public static final String WIZARD_ID = OpenLibraryWizard.class.getName();

	/**
	 * The Open Library wizard extension point ID.
	 */
	public static final String WIZARD_EXTENSION_POINT_ID = "org.eclipse.epf.library.ui.openLibraryWizard"; //$NON-NLS-1$	

	// The main wizard page.
	protected OpenLibraryMainPage mainPage;

	/**
	 * Creates a new instance.
	 */
	public OpenLibraryWizard() {
		super();
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#init(IWorkbench,
	 *      IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(LibraryUIResources.openLibraryWizard_title);
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
			mainPage = createMainPage();
			super.addPage(mainPage);
		} else {
			List<IWizardPage> wizardPages = new ArrayList<IWizardPage>();

			IWizardPage page = wizardExtender
					.getReplaceWizardPage(OpenLibraryMainPage.PAGE_NAME);
			if (page != null) {
				wizardPages.add(page);
			} else {
				mainPage = createMainPage();
				wizardPages.add(mainPage);
			}

			super.getNewWizardPages(wizardPages);

			for (Iterator it = wizardPages.iterator(); it.hasNext();) {
				IWizardPage wizardPage = (IWizardPage) it.next();
				super.addPage(wizardPage);
			}

			wizardExtender.initWizardPages(wizardPages);
		}
	}

	/**
	 * Creates the main wizard page.
	 * 
	 * @return the main wizard page
	 */
	protected OpenLibraryMainPage createMainPage() {
		return new OpenLibraryMainPage();
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(Composite)
	 */
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		pageContainer.getShell().setImage(
				LibraryUIPlugin.getDefault().getSharedImage(
						"full/obj16/MethodLibrary.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		boolean b = performFinish_();
		if (b) {
			FileUtil.getValidateEdit().sychnConneciton();
		}
		return b;
	}
	private boolean performFinish_() {
		if (LibraryService.getInstance().getCurrentMethodConfiguration() != null) {
			LibraryService.getInstance().setCurrentMethodConfiguration(null);
		}		
		if (wizardExtender != null) {
			return wizardExtender.doFinish();
		} else {
			return openMethodLibrary(mainPage.getLibraryPath(), "xmi"); //$NON-NLS-1$	
		}
	}

	/**
	 * Opens the method library at the user specified path.
	 * 
	 * @param path
	 *            the method library path
	 * @param type
	 *            the method library type
	 */
	public boolean openMethodLibrary(String path, String type) {
		return openMethodLibrary(path, type, true);
	}
	public boolean openMethodLibrary(String path, String type, boolean addOpenLibraryPath) {
		try {
			Map<String, String> options = new HashMap<String, String>();
			File libraryPath = new File(path);
			options.put(XMILibraryManager.ARG_LIBRARY_PATH, libraryPath
					.getAbsolutePath());

			if (XMILibraryUtil.isValidLibrary(path, true) == Status.OK_STATUS) {
				if (!handleToolVersion(path, null)) {
					return false;
				}
				if (LibraryUIManager.getInstance().openLibrary(path)) {
					if (addOpenLibraryPath) {
						LibraryUIPreferences.addOpenLibraryPath(libraryPath
								.getAbsolutePath());
					}
					return true;
				}
			} else if (XMILibraryUtil
					.containsPluginOrConfigSpecExportFile(path)) {
				throw new Exception(LibraryUIResources.openLibraryError_reason4);
			} else {
				MsgDialog msgDialog = LibraryUIPlugin.getDefault()
						.getMsgDialog();
				boolean rc = msgDialog
						.displayConfirmation(
								LibraryUIResources.openLibraryWizard_title,
								NLS
										.bind(
												LibraryUIResources.openLibraryDialog_newLibrary_text,
												new Object[] { path }));
				if (!rc)
					return false;
				if (LibraryUIManager.getInstance().createLibrary(path)) {
					LibraryUIPreferences.addOpenLibraryPath(libraryPath
							.getAbsolutePath());
					return true;
				}
			}

			return true;
		} catch (Exception e) {
			String reason = e.getMessage();
			if (reason == null) {
				reason = LibraryUIResources.openLibraryInternlError_reason;
			}
			reason += "\n\n" + LibraryUIResources.openLibraryError_advice; //$NON-NLS-1$
			if (e.getMessage() != null) {
				LibraryUIPlugin.getDefault().getMsgDialog().displayError(
						LibraryUIResources.openLibraryWizard_title,
						LibraryUIResources.openLibraryError_msg, reason);
			} else {
				LibraryUIPlugin.getDefault().getMsgDialog().displayError(
						LibraryUIResources.openLibraryWizard_title,
						LibraryUIResources.openLibraryError_msg, reason, e);
			}
		}
		return false;
	}

	/**
	 * Check and handle tool version differences
	 */
	public static boolean handleToolVersion(String path,
			UpgradeCallerInfo callerInfo) {
		if (callerInfo == null) {
			callerInfo = new UpgradeCallerInfo(UpgradeCallerInfo.upgradeLibrary, null);
		}
		
		String libXmi = XMILibraryManager.LIBRARY_XMI;
		if (callerInfo != null && callerInfo.getIsExportedPluginLib()) {
			libXmi = XMILibraryManager.exportFile;
		}
		VersionUtil.VersionCheckInfo info = VersionUtil
				.checkLibraryVersion(new File(path, libXmi));
		if (info != null && info.result > 0) {
			String message = ""; //$NON-NLS-1$
			if (info.toolID.equals(VersionUtil.getPrimaryToolID())) {
				message = NLS.bind(
						LibraryUIResources.versionMismatchDialog_text,
						new Object[] { Platform.getProduct().getName(),
								info.toolVersion });
			} else {
				message = NLS.bind(
						LibraryUIResources.versionMismatchDialog_text_unknown,
						new Object[] { Platform.getProduct().getName() });
			}
			if (!isUpgradeLibrary(callerInfo)) {
				callerInfo.setErrorMsg(message);
				return false;
			}
			LibraryUIPlugin.getDefault().getMsgDialog().displayError(
					LibraryUIResources.openLibraryWizard_title, message);
			return false;
		}
		if (XMILibraryUtil.isMethodLibraryUpgradeRequired(path, libXmi, info)) {
//			if (!CommandLineRunUtil.getInstance().isNeedToRun() 
//					&& isUpgradeLibrary(callerInfo)
//					&& !LibraryUIPlugin
//							.getDefault()
//							.getMsgDialog()
//							.displayConfirmation(
//									LibraryUIResources.openLibraryWizard_title,
//									LibraryUIResources.upgradeLibraryDialog_text)) {
			int confirmationIx = 0;
			if (!CommandLineRunUtil.getInstance().isNeedToRun()
					&& isUpgradeLibrary(callerInfo)) {
				confirmationIx = LibraryUIPlugin.getDefault().getMsgDialog()
						.displayConfirmationWithCheckBox(
								LibraryUIResources.openLibraryWizard_title,
								LibraryUIResources.upgradeLibraryDialog_text,
								LibraryUIResources.convertToSynProcessLib_msg);
				if (confirmationIx == 0) {
					return false;
				}
			}
			
			if (! LibraryEditUtil.getInstance().isJunitTest()) {
				callerInfo.setConverToSynFree(confirmationIx == 1);
			}
			
			if (!isUpgradeLibrary(callerInfo)) {
				callerInfo.copyLibrary();
				if (callerInfo.getCopiedLibFile() != null) {
					path = callerInfo.getCopiedLibFile().getParentFile()
							.getAbsolutePath();
				}
			}
			if (!LibraryUIManager.upgradeLibrary(path, callerInfo)) {
				if (callerInfo != null) {
					// callerInfo.setErrorMsg("upgradeLibrary Failed!"); //need
					// new resource string
				}
				return false;
			}
		}

		return true;
	}

	private static boolean isUpgradeLibrary(UpgradeCallerInfo callerInfo) {
		return UpgradeCallerInfo.isUpgradeLibrary(callerInfo);
	}
	

}
