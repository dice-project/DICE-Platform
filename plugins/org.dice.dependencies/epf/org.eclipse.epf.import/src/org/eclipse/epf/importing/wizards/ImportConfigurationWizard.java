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
package org.eclipse.epf.importing.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.authoring.ui.wizards.SaveAllEditorsPage;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.services.ConfigurationImportData;
import org.eclipse.epf.importing.services.ConfigurationImportService;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.library.ui.wizards.LibraryBackupUtil;
import org.eclipse.epf.services.IFileManager;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.ui.wizards.BaseWizard;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * A wizard that imports exported library configurations into the current method
 * library.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class ImportConfigurationWizard extends BaseWizard implements IImportWizard {

	public boolean okToComplete = false;

	protected SelectImportConfigurationSource page1;

	protected SelectConfigsToImport configPage;

	protected SelectConfigSpecsToImportPage specsPage;

	protected ConfigurationImportData data = new ConfigurationImportData();

	protected ConfigurationImportService service = ConfigurationImportService.newInstance(
			data);	

	public static final String WIZARD_EXTENSION_POINT_ID = "org.eclipse.epf.import.importConfigurationWizard"; //$NON-NLS-1$
	
	/**
	 * Creates a new instance.
	 */
	public ImportConfigurationWizard() {
		setWindowTitle(ImportResources.importConfigWizard_title);
		setNeedsProgressMonitor(true);
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWizard#init(IWorkbench,
	 *      IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		IFileManager fileMgr = Services.getFileManager();
		if (LibraryService.getInstance().getCurrentMethodLibrary() != null) {
			fileMgr.checkModify(LibraryService.getInstance()
					.getCurrentMethodLibrary().eResource().getURI()
					.toFileString(), MsgBox.getDefaultShell());
		}

		ILibraryManager manager = (ILibraryManager) LibraryService
				.getInstance().getCurrentLibraryManager();
		if (manager != null && manager.isMethodLibraryReadOnly()) {
			ImportPlugin.getDefault().getMsgDialog().displayError(
					ImportResources.importConfigWizard_title,
					ImportResources.ImportConfigurationWizard_readonly);
			return;
		}

		SaveAllEditorsPage.addPageIfNeeded(this, true, null, null, ImportPlugin
				.getDefault().getImageDescriptor(
						"full/wizban/ImportLibraryConfiguration.gif")); //$NON-NLS-1$
		if (wizardExtender == null) {
			page1 = new SelectImportConfigurationSource(data, service);
			addPage(page1);
	
			configPage = new SelectConfigsToImport(service);
			addPage(configPage);
	
			specsPage = new SelectConfigSpecsToImportPage(data);
			addPage(specsPage);
			
			return;
		}
		
		
		List<IWizardPage> wizardPages = new ArrayList<IWizardPage>();

		IWizardPage page = wizardExtender
				.getReplaceWizardPage(SelectImportConfigurationSource.PAGE_NAME);
		if (page != null) {
			page1 = (SelectImportConfigurationSource) page;
			page1.setData(data);
			page1.setService(service);
			wizardPages.add(page);
		} else {
			page1 = new SelectImportConfigurationSource(data, service);
			wizardPages.add(page1);
		}

		page = wizardExtender
				.getReplaceWizardPage(SelectPluginsToImport.PAGE_NAME);
		if (page != null) {
			configPage = (SelectConfigsToImport) page;
			wizardPages.add(page);
		} else {
			configPage = new SelectConfigsToImport(service);
			wizardPages.add(configPage);
		}

		page = wizardExtender
				.getReplaceWizardPage(SelectConfigSpecsToImportPage.PAGE_NAME);
		if (page != null) {
			specsPage = (SelectConfigSpecsToImportPage) page;
			wizardPages.add(page);
		} else {
			specsPage = new SelectConfigSpecsToImportPage(data);
			wizardPages.add(specsPage);
		}
		
		super.getNewWizardPages(wizardPages);

		for (Iterator<IWizardPage> it = wizardPages.iterator(); it
				.hasNext();) {
			IWizardPage wizardPage = it.next();
			super.addPage(wizardPage);
		}

		wizardExtender.initWizardPages(wizardPages);
		
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(Composite)
	 */
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		pageContainer.getShell().setImage(
				LibraryUIImages.IMG_METHOD_CONFIGURATON);
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 */
	public boolean canFinish() {
		return okToComplete;
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		if (ProcessUtil.isSynFree() && !service.isSynFreeLib()) {
//			String message = ImportResources.ImportNoSynLib_ConvertMsg;
//			boolean yes = ImportPlugin.getDefault().getMsgDialog()
//					.displayConfirmation(
//							ImportResources.importConfigWizard_title, message);
//			if (!yes) {
//				return false;
//			}
			
			String message = ImportResources.ImportNoSynLibToSynLib_Error;
			MessageDialog.openError(this.getShell(), ImportResources.importConfigWizard_title, message);			
			
			return false;
			
		} else if (!ProcessUtil.isSynFree() && service.isSynFreeLib()) {
			String message = ImportResources.ImportSynLibToNoSynLib_Error;
//			ImportPlugin.getDefault().getMsgDialog()
//					.displayError(
//							ImportResources.importConfigWizard_title, message);
			
			//Use the standard MessageDialog to avoid double-byte character display problem
			MessageDialog.openError(this.getShell(), ImportResources.importConfigWizard_title, message);			
			
			return false;
		}
		
		// Prompt the user to back up library.
		LibraryBackupUtil.promptBackupCurrentLibrary(null, LibraryService
				.getInstance());
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					// Start the import.
					monitor.beginTask(
							ImportResources.ImportConfigurationWizard_MSG1,
							IProgressMonitor.UNKNOWN);
					service.performImport(monitor);
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};

		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			ImportPlugin.getDefault().getMsgDialog().displayError(
					ImportResources.ImportConfigurationWizard_error,
					realException.getMessage());
			return false;
		}

		// Save the import path to preference store.
		ImportUIPreferences.addImportConfigDir(data.llData.getParentFolder());

		return true;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#getWizardExtenderExtensionPointId()
	 */
	public String getWizardExtenderExtensionPointId() {
		return WIZARD_EXTENSION_POINT_ID;
	}
	
}
