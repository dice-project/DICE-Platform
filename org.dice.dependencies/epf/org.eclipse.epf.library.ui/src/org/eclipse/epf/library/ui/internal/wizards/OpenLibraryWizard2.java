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
package org.eclipse.epf.library.ui.internal.wizards;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.common.service.versioning.VersionUtil;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.library.LibraryManagerFactory;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.ui.LibraryUIManager;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.library.ui.wizards.OpenLibraryWizardPage;
import org.eclipse.epf.library.xmi.XMILibraryManager;
import org.eclipse.epf.library.xmi.XMILibraryUtil;
import org.eclipse.epf.persistence.migration.UpgradeCallerInfo;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * A wizard that guides the user to open a method library.
 * 
 * @author Kelvin Low
 * @since 1.2
 * @deprecated
 */
public class OpenLibraryWizard2 extends Wizard implements INewWizard {

	/**
	 * The wizard ID.
	 */
	public static final String WIZARD_ID = OpenLibraryWizard2.class.getName();

	// The main wizard page.
	private OpenLibraryMainPage2 mainPage;

	/**
	 * Creates a new instance.
	 */
	public OpenLibraryWizard2() {
		super();
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWizard#init(IWorkbench,
	 *      IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(LibraryUIResources.openLibraryWizard_title);
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		mainPage = new OpenLibraryMainPage2();
		addPage(mainPage);

		for (Iterator it = LibraryManagerFactory.getInstance()
				.getLibraryTypes().keySet().iterator(); it.hasNext();) {
			String persistenceId = (String) it.next();
			String pageId = persistenceId + "Page"; //$NON-NLS-1$
			OpenLibraryWizardPage page = OpenLibraryWizardPageFactory
					.getInstance().createWizardPage(pageId, persistenceId);
			if (page != null) {
				page.setImageDescriptor(LibraryUIPlugin.getDefault()
						.getImageDescriptor("full/wizban/Open.gif")); //$NON-NLS-1$					
				addPage(page);
			}
		}
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
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	public boolean canFinish() {
		if (mainPage.isPageComplete() && !mainPage.isOpenUnlistedLibrary()) {
			return true;
		}
		IWizardPage[] pages = getPages();
		for (int i = 1; i < pages.length; i++) {
			if (!pages[i].isPageComplete()) {
				return false;
			}
		}
		return getContainer().getCurrentPage() != mainPage;
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		String type = mainPage.getLibraryType();
		OpenLibraryWizardPage page = OpenLibraryWizardPageFactory.getInstance()
				.getWizardPage(type);
		try {
			String path = null;
			if (mainPage.isOpenUnlistedLibrary()) {
				Map options = page.getSelections();
				path = (String) options.get(XMILibraryManager.ARG_LIBRARY_PATH);
			} else {
				path = mainPage.getLibraryPath();
			}
			if (path != null) {
				if (XMILibraryUtil.isValidLibrary(path, true) == Status.OK_STATUS) {
					if (!handleToolVersion(path, null)) {
						return false;
					}
					if (LibraryUIManager.getInstance().openLibrary(path)) {
						addRecentlyOpenedLibrary();
						return true;
					}
				} else if (XMILibraryUtil
						.containsPluginOrConfigSpecExportFile(path)) {
					throw new Exception(
							LibraryUIResources.openLibraryError_reason4);
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
						addRecentlyOpenedLibrary();
						return true;
					}
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
	 * Adds the newly opened or created method library to the recently opened
	 * method libraries preference.
	 */
	protected void addRecentlyOpenedLibrary() {
		MethodLibrary library = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		if (library != null) {
			String libraryURI = LibraryService.getInstance().getLibraryManager(
					library).getMethodLibraryURI().toString();
			LibraryUIPreferences.addOpenLibraryURI(libraryURI);
		}
	}

	/**
	 * Check and handle tool version differences
	 */
	public static boolean handleToolVersion(String path,
			UpgradeCallerInfo callerInfo) {
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
			if (isUpgradeLibrary(callerInfo)
					&& !LibraryUIPlugin
							.getDefault()
							.getMsgDialog()
							.displayConfirmation(
									LibraryUIResources.openLibraryWizard_title,
									LibraryUIResources.upgradeLibraryDialog_text)) {
				return false;
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
