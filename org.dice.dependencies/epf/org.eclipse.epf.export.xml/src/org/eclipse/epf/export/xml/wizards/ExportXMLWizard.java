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
package org.eclipse.epf.export.xml.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.epf.authoring.ui.wizards.SaveAllEditorsPage;
import org.eclipse.epf.common.ui.util.MsgBox;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.export.services.ConfigurationExportData;
import org.eclipse.epf.export.services.ConfigurationExportService;
import org.eclipse.epf.export.services.PluginExportData;
import org.eclipse.epf.export.xml.ExportXMLPlugin;
import org.eclipse.epf.export.xml.ExportXMLResources;
import org.eclipse.epf.export.xml.preferences.ExportXMLPreferences;
import org.eclipse.epf.export.xml.services.ExportXMLData;
import org.eclipse.epf.export.xml.services.ExportXMLService;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.ui.LibraryUIManager;
import org.eclipse.epf.library.ui.wizards.OpenLibraryWizard;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

import com.ibm.icu.util.Calendar;

/**
 * The Export XML wizard.
 * <p>
 * This wizard is used to export method library content to XML files.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportXMLWizard extends Wizard implements IExportWizard {

	private SelectExportTypePage selectExportTypePage;

	protected SelectPluginPage selectPluginPage;

	protected ViewPluginInfoPage viewPluginInfoPage;

	protected ViewExportSummaryPage viewExportSummaryPage;

	protected SelectConfigPage selectConfigPage;

	protected SelectXMLFilePage selectXMLFilePage;

	protected ExportXMLData xmlData = new ExportXMLData();

	protected PluginExportData pluginData = new PluginExportData();

	protected ConfigurationExportData configData = new ConfigurationExportData();
	
	private String currLibPathToResume;
	
	private File tempExportFolder; 

	/**
	 * Creates a new instance.
	 */
	public ExportXMLWizard() {
		super();
		setWindowTitle(ExportXMLResources.exportXMLWizard_title);
		setNeedsProgressMonitor(true);
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWizard#init(IWorkbench,
	 *      IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		selectExportTypePage = new SelectExportTypePage();
		selectPluginPage = new SelectPluginPage(pluginData);
		viewPluginInfoPage = new ViewPluginInfoPage(pluginData);
		viewExportSummaryPage = new ViewExportSummaryPage(pluginData);
		selectConfigPage = new SelectConfigPage(configData);
		selectXMLFilePage = new SelectXMLFilePage();

		SaveAllEditorsPage.addPageIfNeeded(this, true, null, null, ExportXMLPlugin
				.getDefault().getImageDescriptor(
						"full/wizban/ExportXML.gif")); //$NON-NLS-1$
		
		addPage(selectExportTypePage);
		addPage(selectPluginPage);
		addPage(viewPluginInfoPage);
		addPage(viewExportSummaryPage);
		addPage(selectConfigPage);
		addPage(selectXMLFilePage);
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
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 */
	public boolean canFinish() {
		return getContainer().getCurrentPage() == selectXMLFilePage
				&& selectXMLFilePage.isPageComplete();
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#performFinish()
	 */
	public boolean performFinish() {
		String xmlFilePath = selectXMLFilePage.getPath();
		File xmlFile = new File(xmlFilePath);
		File folder = xmlFile.getParentFile();
		String folderPath = folder.getAbsolutePath();
		if (xmlFile.exists()) {
			boolean ok = ExportXMLPlugin.getDefault().getMsgDialog()
					.displayPrompt(
							ExportXMLResources.exportXMLWizard_title,
							ExportXMLResources.bind(
									ExportXMLResources.overwriteText_msg1,
									new String[] { xmlFilePath, folderPath }));
			if (!ok) {
				return false;
			}
		} else if (folder.exists() && folder.listFiles() != null && folder.listFiles().length > 0) {
			boolean ok = ExportXMLPlugin.getDefault().getMsgDialog()
					.displayPrompt(
							ExportXMLResources.exportXMLWizard_title,
							ExportXMLResources.bind(
									ExportXMLResources.overwriteText_msg,
									new String[] { folderPath }));
			if (!ok) {
				return false;
			}
		}
		try {
			if (folder.exists()) {
				FileUtil.deleteAllFiles(folderPath);
			}
		} catch (Throwable e) {
			ExportXMLPlugin.getDefault().getLogger().logError(e);
		}
		

		//hot fix
		final boolean exportConfig = xmlData.getExportType() == ExportXMLData.EXPORT_METHOD_CONFIGS;
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException {
				ExportXMLService service = null;
				try {
					monitor.beginTask(ExportXMLResources.exportingXML_text,
							IProgressMonitor.UNKNOWN);					
					xmlData.setXMLFile(selectXMLFilePage.getPath());
					service = ExportXMLService.newInstance(xmlData);
					
					if (exportConfig) {
						if (! preExportConfig(monitor)) {
							return;
						}
						monitor.setTaskName(ExportXMLResources.exportingXML_text);
					}
					
					service.doExport(monitor);
					ExportXMLPreferences.setExportType(xmlData.getExportType());
					ExportXMLPreferences.setXMLFile(xmlData.getXMLFile());
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				} finally {
					try {
						if (exportConfig) {
							postExportConfig(monitor);
						}
					} finally {						
						monitor.done();
						if (service != null) {
							service.dispose();
						}
					}
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			ExportXMLPlugin.getDefault().getMsgDialog().displayError(
					ExportXMLResources.exportXMLWizard_title,
					NLS.bind(ExportXMLResources.exportXMLWizard_error,
							realException.getMessage()), realException);
			return false;
		}

		String msg = ExportXMLResources.exportXMLWizard_reviewLog;
		MsgBox.prompt(msg, SWT.OK);

		return true;
	}
	
	private boolean preExportConfig(final IProgressMonitor monitor) {
		String userHome = System.getProperty("user.home"); //$NON-NLS-1$
		String desLibFolderPath = userHome + File.separator
				+ "EPF" + File.separator + "Export" + File.separator //$NON-NLS-1$ //$NON-NLS-2$
				+ Long.toHexString(Calendar.getInstance().getTimeInMillis()) + File.separator;		
		tempExportFolder = new File(desLibFolderPath);
			
		MethodLibrary currLib = LibraryService.getInstance().getCurrentMethodLibrary();
		currLibPathToResume = currLib.eResource().getURI().toFileString();	
		
		List configs = xmlData.getSelectedConfigs();
		if (configs == null || configs.isEmpty()) {
			return false;
		}
		MethodConfiguration config = (MethodConfiguration) configs.get(0);
		
		final ConfigurationExportData data = new ConfigurationExportData();
		data.llData.setLibName(currLib.getName());
		data.llData.setParentFolder(tempExportFolder.getAbsolutePath());
		data.exportOneConfig = true;
		data.exportConfigSpecs = false;
		data.selectedConfigs = new ArrayList();
		data.selectedConfigs.add(config);
		
		final Exception[] ex = new Exception[1];
		ex[0] = null;		
		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				try {
					monitor.setTaskName(ExportXMLResources.export_config_to_temp_location);
					(ConfigurationExportService.newInstance(data)).run(new NullProgressMonitor());
					monitor.setTaskName(ExportXMLResources.open_lib_from_temp_exported_location);
					OpenLibraryWizard wizard = new OpenLibraryWizard();
					wizard.openMethodLibrary(tempExportFolder.getAbsolutePath(), "xmi", false); //$NON-NLS-1$ 
				} catch (Exception e) {
					ex[0] = e;
				}
			}
		});
		
		if (ex[0] != null) {
			throw new RuntimeException(ex[0]);
		}
		
		return true;
	}
	
	private void postExportConfig(final IProgressMonitor monitor) {
		if (currLibPathToResume == null) {
			return;
		}
		MethodLibrary currLib = LibraryService.getInstance().getCurrentMethodLibrary();
		String currLibPath = currLib.eResource().getURI().toFileString();
		if (currLibPathToResume.equals(currLibPath)) {
			return;
		}		
		final File libFolder = new File(currLibPathToResume).getParentFile();
				
		final Exception[] ex = new Exception[1];
		ex[0] = null;		
		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				boolean oldB = LibraryUIManager.isSkipInstallPathCheck();
				try {
					LibraryUIManager.setSkipInstallPathCheck(true);
					OpenLibraryWizard wizard = new OpenLibraryWizard();					
					wizard.openMethodLibrary(libFolder.getAbsolutePath(), "xmi", false); //$NON-NLS-1$
				} catch (Exception e) {
					ex[0] = e;
				} finally {
					LibraryUIManager.setSkipInstallPathCheck(oldB);
				}
			}
		});
		
		if (ex[0] != null) {
			throw new RuntimeException(ex[0]);
		}
		
		if (tempExportFolder != null && tempExportFolder.exists()) {
			FileUtil.deleteAllFiles(tempExportFolder.getAbsolutePath());
			tempExportFolder = null;
		}
		
		currLibPathToResume = null;
	}
	
}
