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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.epf.authoring.ui.wizards.SaveAllEditorsPage;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.services.PluginImportData;
import org.eclipse.epf.importing.services.PluginImportingService;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.PluginDepInfo;
import org.eclipse.epf.library.edit.validation.PluginDependencyInfoMgr;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.library.ui.wizards.LibraryBackupUtil;
import org.eclipse.epf.services.IFileManager;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.ui.wizards.BaseWizard;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

/**
 * A wizard that imports an exported method plug-ins into the currrent method
 * library.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class ImportPluginWizard extends BaseWizard implements IImportWizard {

	protected SelectImportPluginSource page1;

	protected SelectPluginsToImport page2;

	protected PluginImportData data = new PluginImportData();

	protected PluginImportingService service = PluginImportingService.newInstance(data);
	
	public static final String WIZARD_EXTENSION_POINT_ID = "org.eclipse.epf.import.importPluginWizard"; //$NON-NLS-1$	

	/**
	 * Creates a new instance.
	 */
	public ImportPluginWizard() {
		setWindowTitle(ImportResources.importPluginsWizard_title);
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
		if (LibraryService.getInstance().getCurrentMethodLibrary() != null) {
			fileMgr.checkModify(LibraryService.getInstance()
					.getCurrentMethodLibrary().eResource().getURI()
					.toFileString(), MsgBox.getDefaultShell());
		}
		ILibraryManager manager = (ILibraryManager) LibraryService
				.getInstance().getCurrentLibraryManager();
		if (manager != null && manager.isMethodLibraryReadOnly()) {
			ImportPlugin.getDefault().getMsgDialog().displayError(
					ImportResources.importPluginsWizard_title,
					ImportResources.ImportPluginWizard_readonly);
			return;
		}

		SaveAllEditorsPage.addPageIfNeeded(this, true, null, null, ImportPlugin
				.getDefault().getImageDescriptor(
						"full/wizban/ImportMethodPlugins.gif")); //$NON-NLS-1$

		if (wizardExtender == null) {
			page1 = new SelectImportPluginSource(data, service);
			addPage(page1);
	
			page2 = new SelectPluginsToImport(data, service);
			addPage(page2);
			
			return;
		}
		
		List<IWizardPage> wizardPages = new ArrayList<IWizardPage>();

		IWizardPage page = wizardExtender
				.getReplaceWizardPage(SelectImportPluginSource.PAGE_NAME);
		if (page != null) {
			((SelectImportPluginSource) page).setData(data);
			((SelectImportPluginSource) page).setService(service);
			wizardPages.add(page);
		} else {
			page1 = new SelectImportPluginSource(data, service);
			wizardPages.add(page1);
		}

		page = wizardExtender
				.getReplaceWizardPage(SelectPluginsToImport.PAGE_NAME);
		if (page != null) {
			wizardPages.add(page);
		} else {
			page2 = new SelectPluginsToImport(data, service);
			wizardPages.add(page2);
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
		pageContainer.getShell().setImage(LibraryUIImages.IMG_METHOD_PLUGIN);
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 */
	public boolean canFinish() {
		if (this.getContainer().getCurrentPage() != page2)
			return false;
		return page2.isPageComplete();
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		// Check if the selected plug-ins are updatable or not.
		PluginModifyInfo modifyInfo = checkModify();

		// if only locked, we can auto-unlock and relock the plugins
		// if the pluin is readonly, user need to manually make it updatable
		// customers need to manually unlock and lock plugins
		// during content library upgrade

		if (modifyInfo.readonlyPlugins.size() > 0) {
			// Display an error message.
			String error = modifyInfo.getReadonlyMessage().toString();
			ImportPlugin.getDefault().getMsgDialog().displayError(
					ImportResources.ImportConfigurationWizard_error, error);

			return false;
		}

		StringBuffer buffer = new StringBuffer();
		PluginImportData.PluginInfo info;
		int i = 0;
		for (Iterator it = data.getPlugins().iterator(); it.hasNext();) {
			i++;
			if (i > 20) {
				String msg = "\n\t. . ."; //$NON-NLS-1$
				buffer.append(msg);
				break;
			}
			info = (PluginImportData.PluginInfo) it.next();
			if ((info.existingPlugin != null) && info.selected) {
				if (buffer.length() > 0) {
					buffer.append("\n"); //$NON-NLS-1$
				}
				buffer.append("\t").append(info.name); //$NON-NLS-1$
			}
		}

		if (buffer.length() > 0) {
			boolean yes = ImportPlugin
					.getDefault()
					.getMsgDialog()
					.displayConfirmation(
							ImportResources.importPluginsWizard_title,
							NLS
									.bind(
											ImportResources.ImportPluginWizard_warn_existing_plugins,
											buffer.toString()));

			if (!yes) {
				return false;
			}
		}

		// allow user to proceed with locked plugins
		if (modifyInfo.lockedPlugins.size() > 0) {
			String message = modifyInfo.getLockedMessage().toString()
					+ ImportResources.ImportPluginWizard_confirm_continue;
			boolean yes = ImportPlugin.getDefault().getMsgDialog()
					.displayConfirmation(
							ImportResources.importPluginsWizard_title, message);
			if (!yes) {
				return false;
			}
		}
		
		if (ProcessUtil.isSynFree() && !service.isSynFreeLib()) {
//			String message = ImportResources.ImportNoSynLib_ConvertMsg;
//			boolean yes = ImportPlugin.getDefault().getMsgDialog()
//					.displayConfirmation(
//							ImportResources.importPluginsWizard_title, message);
//			if (!yes) {
//				return false;
//			}
			String message = ImportResources.ImportNoSynLibToSynLib_Error;
			ImportPlugin.getDefault().getMsgDialog()
					.displayError(
							ImportResources.importPluginsWizard_title, message);
			return false;
		} else if (!ProcessUtil.isSynFree() && service.isSynFreeLib()) {
			String message = ImportResources.ImportSynLibToNoSynLib_Error;
			ImportPlugin.getDefault().getMsgDialog()
					.displayError(
							ImportResources.importPluginsWizard_title, message);
			return false;
		}

		// Prompt user to back up library.
		LibraryBackupUtil.promptBackupCurrentLibrary(null, LibraryService
				.getInstance());
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				try {
					monitor.beginTask(ImportResources.ImportPluginWizard_MSG1,
							IProgressMonitor.UNKNOWN);

					// Start the import.
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
					ImportResources.ImportPluginWizard_error, 
					realException.getMessage());
			return false;
		}

		try {
			checkPluginCircularDependency();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		// Save the import path to preference store.
		ImportUIPreferences.addImportPluginDir(data.llData.getParentFolder());

		ImportUIPreferences.setCheckBasePlugins(service.isCheckBasePlugins());
		return true;
	}

	/**
	 * Returns a PluginModifyInfo object.
	 */
	public PluginModifyInfo checkModify() {
		// StringBuffer error = new StringBuffer();
		PluginModifyInfo modifyInfo = new PluginModifyInfo();

		PluginImportData.PluginInfo info;
		for (Iterator it = data.getPlugins().iterator(); it.hasNext();) {
			info = (PluginImportData.PluginInfo) it.next();
			MethodPlugin plugin = info.existingPlugin;
			if ((plugin != null) && info.selected) {
				if (plugin.getUserChangeable().booleanValue() == false) {
					// The plug-in is locked
					modifyInfo.lockedPlugins.add(plugin);
				} else {
					// The plug-in exists in the current library, make sure the
					// plug-in is updatable.
					IStatus status = TngUtil.checkEdit(plugin, getShell());
					if (!status.isOK()) {
						modifyInfo.readonlyPlugins.add(plugin);
					}
				}
			}
		}

		return modifyInfo;
	}

	private void checkPluginCircularDependency() {
		PluginDependencyInfoMgr mgr = new PluginDependencyInfoMgr(
				LibraryService.getInstance().getCurrentMethodLibrary());
		PluginDependencyInfoMgr.CheckResult result = mgr
				.checkCircularDependnecy(null, false);
		if (result.circularList != null && !result.circularList.isEmpty()) {
			List cirPlugins = (List) result.circularList.get(0);
			String msg = ""; //$NON-NLS-1$
			for (int i = 0; i < cirPlugins.size(); i++) {
				PluginDepInfo pinfo = (PluginDepInfo) cirPlugins.get(i);
				if (i != 0) {
					msg += ", "; //$NON-NLS-1$
				}
				msg += pinfo.getPlugin().getName();
			}
			ImportPlugin.getDefault().getMsgDialog().displayWarning(
					ImportResources.ImportPluginWizard_warn_circular_plugins,
					msg);
		}
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#getWizardExtenderExtensionPointId()
	 */
	public String getWizardExtenderExtensionPointId() {
		return WIZARD_EXTENSION_POINT_ID;
	}
	
}