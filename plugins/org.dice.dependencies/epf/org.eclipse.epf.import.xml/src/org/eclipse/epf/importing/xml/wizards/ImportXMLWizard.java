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
package org.eclipse.epf.importing.xml.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.epf.authoring.ui.wizards.SaveAllEditorsPage;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.export.xml.ExportXMLPlugin;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.xml.ImportXMLPlugin;
import org.eclipse.epf.importing.xml.ImportXMLResources;
import org.eclipse.epf.importing.xml.preferences.ImportXMLPreferences;
import org.eclipse.epf.importing.xml.services.ImportXMLService;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.ui.actions.LibraryLockingOperationRunner;
import org.eclipse.epf.library.ui.wizards.LibraryBackupUtil;
import org.eclipse.epf.library.util.ResourceUtil;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.services.IFileManager;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.ui.wizards.BaseWizard;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * The Import XML wizard.
 * <p>
 * This wizard is used to import method library content stored in a XML file.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ImportXMLWizard extends BaseWizard implements IImportWizard {

	ImportXMLService service = ImportXMLService.newInstance();

	private boolean succeed = true;
	
	private SelectXMLFilePage filePage = null;
	
	public static final String WIZARD_EXTENSION_POINT_ID = "org.eclipse.epf.import.xml.importXMLWizard"; //$NON-NLS-1$	

	/**
	 * Creates a new instance.
	 */
	public ImportXMLWizard() {
		super();
		setWindowTitle(ImportXMLResources.importXMLWizard_title);
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
		// check out the library first
		IFileManager fileMgr = Services.getFileManager();
		if (LibraryService.getInstance()
				.getCurrentMethodLibrary() != null) {
			fileMgr.checkModify(LibraryService.getInstance()
					.getCurrentMethodLibrary().eResource().getURI().toFileString(),
					MsgBox.getDefaultShell());
		}

		ILibraryManager manager = (ILibraryManager) LibraryService
				.getInstance().getCurrentLibraryManager();
		if (manager != null && manager.isMethodLibraryReadOnly()) {
			File libraryFile = new File(LibraryService.getInstance()
					.getCurrentMethodLibraryLocation(),
					MultiFileSaveUtil.DEFAULT_LIBRARY_MODEL_FILENAME);
			ImportXMLPlugin.getDefault().getMsgDialog().displayError(
					ImportXMLResources.importXMLWizard_title,
					ImportXMLResources.importXMLError_msg,
					NLS.bind(ImportXMLResources.importXMLError_reason,
							libraryFile.getAbsolutePath()));
			return;
		}

		SaveAllEditorsPage.addPageIfNeeded(this, true, null, null, ImportXMLPlugin
				.getDefault().getImageDescriptor(
						"full/wizban/ImportXML.gif")); //$NON-NLS-1$
		
		if (wizardExtender == null) {			
			filePage = new SelectXMLFilePage();
			addPage(filePage);			
			return;
		}
		
		List<IWizardPage> wizardPages = new ArrayList<IWizardPage>();

		IWizardPage page = wizardExtender
				.getReplaceWizardPage(SelectXMLFilePage.PAGE_NAME);
		if (page != null) {
			filePage = (SelectXMLFilePage) page;
			wizardPages.add(page);
		} else {
			filePage = new SelectXMLFilePage();
			addPage(filePage);		
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
				ExportXMLPlugin.getDefault().getSharedImage(
						"full/obj16/XMLFile.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		boolean refresh = RefreshJob.getInstance().isEnabled();
		if (refresh) {
			//Needed: because LibraryLockingOperationRunner adds events to RefreshJob at the
			//end of "run" call!
			RefreshJob.getInstance().setEnabled(false);
		}		
		final boolean ret[] = new boolean[1];		
		LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
		runner.run(new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				ret[0] = performFinish_();
			}			
		});
		if (refresh) {
			try {
				Thread.sleep(1000); // temp solution for potential racing
			} catch (Exception e) {
			}
			RefreshJob.getInstance().setEnabled(true);
		}
		
		
//		runner = new LibraryLockingOperationRunner();
//		runner.run(new IRunnableWithProgress() {
//			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
//				String msg = NLS.bind(ImportXMLResources.review_log_files,
//						service.getLogPath());
//					MsgBox.prompt(msg, SWT.OK);
//			}			
//		});		
		
		ResourceUtil.refreshResources(LibraryService.getInstance()
				.getCurrentMethodLibrary(), new NullProgressMonitor());
		
		return ret[0];
	}
	
	/**
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	private boolean performFinish_() {
		LibraryBackupUtil.promptBackupCurrentLibrary(null, LibraryService
				.getInstance());

		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					monitor.beginTask(ImportXMLResources.importingXML_text,
							IProgressMonitor.UNKNOWN);

					String xmlFile = filePage.getPath();
					boolean mergeOption = filePage.getMergeOption();
					boolean checkBasePlugins = filePage.getCheckBasePlugins();
					service.setOverwrite(!mergeOption);
					service.setMergeLevel(filePage.getMergerLevel());
					service.setCheckBasePlugins(checkBasePlugins);
					succeed = service.loadXml(xmlFile);
					if (! succeed) {
						return;
					}
					
					if (! handleSynFreeFlag()) {
						return;
					}
					
					service.doImport(monitor);
					ImportXMLPreferences.setXMLFile(xmlFile);
					ImportXMLPreferences.setMergeOption(mergeOption);
					ImportXMLPreferences.setCheckBasePluginsOption(checkBasePlugins);
					ImportXMLPreferences.setMergeLevel(filePage.getMergerLevel());
					
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
			ImportXMLPlugin.getDefault().getMsgDialog().displayError(
					ImportXMLResources.importXMLWizard_title,
					realException.getMessage(), realException);
			return false;
		}

		if (!succeed) {
			return false;
		}
		//String msg = NLS.bind(ImportXMLResources.review_log_files,
		//	service.getLogPath());
		//MsgBox.prompt(msg, SWT.OK);

		return true;
	}

	/**
	 * Disposes of this wizard and frees all SWT resources.
	 */
	public void dispose() {
		service.dispose();
	}
	
	private boolean handleSynFreeFlag() {
		final boolean needToConvert = ProcessUtil.isSynFree() && !service.isSynFreeLib();
		final boolean toReject = !ProcessUtil.isSynFree() && service.isSynFreeLib();
		final boolean result[] = new boolean[1];
		result[0] = true;
		
		if (needToConvert || toReject) {
			SafeUpdateController.syncExec(new Runnable() {
				public void run() {
					if (needToConvert) {
//						String message = ImportResources.ImportNoSynLib_ConvertMsg;
//						result[0] = ImportPlugin
//								.getDefault()
//								.getMsgDialog()
//								.displayConfirmation(
//										ImportXMLResources.importXMLWizard_title,
//										message);
						String message = ImportResources.ImportNoSynLibToSynLib_Error;
						ImportPlugin
								.getDefault()
								.getMsgDialog()
								.displayError(
										ImportXMLResources.importXMLWizard_title,
										message);
						result[0] = false;
					} else if (toReject) {
						String message = ImportResources.ImportSynLibToNoSynLib_Error;
						ImportPlugin
								.getDefault()
								.getMsgDialog()
								.displayError(
										ImportXMLResources.importXMLWizard_title,
										message);
						result[0] = false;
					}
				}
			});
		}
		
		return result[0];
	}
	

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#getWizardExtenderExtensionPointId()
	 */
	public String getWizardExtenderExtensionPointId() {
		return WIZARD_EXTENSION_POINT_ID;
	}
}
