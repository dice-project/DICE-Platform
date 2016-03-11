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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.views.ProcessTreeContentProvider;
import org.eclipse.epf.library.ui.views.ProcessTreeLabelProvider;
import org.eclipse.epf.library.ui.views.ProcessTreeUIFolder;
import org.eclipse.epf.publishing.ui.PublishingUIPlugin;
import org.eclipse.epf.publishing.ui.PublishingUIResources;
import org.eclipse.epf.publishing.ui.preferences.PublishingUIPreferences;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * A wizard page that prompts the user to publish the entire method
 * configuration or only specific processes in the configuration.
 * 
 * @author Kelvin Low
 * @author Bingxue Xu
 * @author Jinhua Xi
 * @since 1.0
 */
public class SelectContentPage extends BaseWizardPage implements Listener {

	/**
	 * The wizard page name.
	 */
	public static final String PAGE_NAME = SelectContentPage.class.getName();

	protected Button publishConfigRadioButton;

	protected Button publishProcessesRadioButton;

	protected CheckboxTreeViewer processViewer;

	protected Button includeBaseProcessesCheckbox;

	protected MethodConfiguration config;

	protected class ProcessViewerContentProvider extends
			ProcessTreeContentProvider {

		private List<MethodPackage> packages = new ArrayList<MethodPackage>();

		/**
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#inputChanged(Viewer,
		 *      Object, Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			super.inputChanged(viewer, oldInput, newInput);
		}

		/**
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(Object)
		 */
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof MethodConfiguration) {
				MethodConfiguration config = (MethodConfiguration) parentElement;
				List<Object> children = new ArrayList<Object>();
				packages = config.getMethodPackageSelection();
				List<MethodPlugin> plugins = config.getMethodPluginSelection();
				for (Iterator<MethodPlugin> it = plugins.iterator(); it
						.hasNext();) {
					MethodPlugin plugin = it.next();
					List<Process> processes = TngUtil.getAllProcesses(plugin);
					if (processes.size() > 0) {
						children.add(plugin);
					}
				}
				return children.toArray();
			} else if (parentElement instanceof ProcessTreeUIFolder) {
				ProcessTreeUIFolder uiFolder = (ProcessTreeUIFolder) parentElement;
				MethodPlugin plugin = (MethodPlugin) uiFolder.getParent();
				if (uiFolder.getName() == CAPABILITY_PATTERNS) {
					List<CapabilityPattern> selectedCapabilityPatterns = new ArrayList<CapabilityPattern>();
					List<CapabilityPattern> capabilityPatterns = LibraryServiceUtil
							.getCapabilityPatterns(plugin);
					for (Iterator<CapabilityPattern> it = capabilityPatterns
							.iterator(); it.hasNext();) {
						CapabilityPattern cp = it.next();
						ProcessPackage pkg = UmaUtil.getProcessPackage(cp);
						if (packages.contains(pkg)) {
							selectedCapabilityPatterns.add(cp);
						}
					}
					return selectedCapabilityPatterns.toArray();
				} else {
					List<DeliveryProcess> selectedDeliveryProcesses = new ArrayList<DeliveryProcess>();
					List<DeliveryProcess> deliveryProcesses = LibraryServiceUtil
							.getDeliveryProcesses(plugin);
					for (Iterator<DeliveryProcess> it = deliveryProcesses
							.iterator(); it.hasNext();) {
						DeliveryProcess dp = it.next();
						ProcessPackage pkg = UmaUtil.getProcessPackage(dp);
						if (packages.contains(pkg)) {
							selectedDeliveryProcesses.add(dp);
						}
					}
					return selectedDeliveryProcesses.toArray();
				}
			} else {
				return super.getChildren(parentElement);
			}
		}
	}

	/**
	 * Creates a new instance.
	 */
	public SelectContentPage() {
		super(PAGE_NAME);
		setTitle(PublishingUIResources.selectContentWizardPage_title);
		setDescription(PublishingUIResources.selectContentWizardPage_text);
		setImageDescriptor(PublishingUIPlugin.getDefault().getImageDescriptor(
				"full/wizban/PublishConfiguration.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = createGridLayoutComposite(parent, 1);

		publishConfigRadioButton = createRadioButton(composite,
				PublishingUIResources.publishConfigRadioButton_text, 1, true);

		publishProcessesRadioButton = createRadioButton(composite,
				PublishingUIResources.publishProcessesRadioButton_text, 1,
				false);

		Composite processComposite = createChildGridLayoutComposite(composite,
				1);

		processViewer = new CheckboxTreeViewer(processComposite);
		GridData gridData = new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL);
		gridData.heightHint = 300;
		processViewer.getTree().setLayoutData(gridData);
//		processViewer.setContentProvider(new ProcessViewerContentProvider());
		processViewer.setLabelProvider(new ProcessTreeLabelProvider());

		includeBaseProcessesCheckbox = createCheckbox(processComposite,
				PublishingUIResources.includeBaseProcessesCheckboxLabel_text);

		initControls();

		addListeners();

		setControl(composite);
	}

	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		String configId = config != null ? config.getGuid() : ""; //$NON-NLS-1$
		
		if ((config != null) && ConfigFreeProcessPublishUtil.getInstance().isSameMethodConfiguration(config)) {
			//For config free process publish
			processViewer.setContentProvider(new ProcessViewerOfConfigFreeContentProvider());
			publishConfigRadioButton.setEnabled(false);
			publishConfigRadioButton.setSelection(false);
			publishProcessesRadioButton.setSelection(true);
			processViewer.getControl().setEnabled(true);
			
			List<String> processIds = PublishingUIPreferences.getProcesses(configId);
			List<Process> processes = new ArrayList<Process>();
			if (processIds != null) {
				for (String guid : processIds) {
					MethodElement e = LibraryService.getInstance().getCurrentLibraryManager().getMethodElement(guid);
					if (e instanceof Process) {
						processes.add((Process) e);
					}
				}
			}
			processViewer.setCheckedElements(processes.toArray());
			
			boolean includeBaseProcess = PublishingUIPreferences.getIncludeBaseProcesses(configId);
			includeBaseProcessesCheckbox.setSelection(includeBaseProcess);
			
			updateCheckedStates();
		} else {		
			processViewer.setContentProvider(new ProcessViewerContentProvider());
			boolean publishConfig = PublishingUIPreferences.getPublishEntireConfig(configId);
			publishConfigRadioButton.setEnabled(true);
			publishConfigRadioButton.setSelection(publishConfig);
			publishProcessesRadioButton.setSelection(!publishConfig);
			processViewer.getControl().setEnabled(!publishConfig);
			
			if (!publishConfig) {
				List<String> processIds = PublishingUIPreferences.getProcesses(configId);
				List<Process> processes = new ArrayList<Process>();
				if (processIds != null) {
					for (String guid : processIds) {
						MethodElement e = LibraryService.getInstance().getCurrentLibraryManager().getMethodElement(guid);
						if (e instanceof Process) {
							processes.add((Process) e);
						}
					}
				}
				processViewer.setCheckedElements(processes.toArray());
			}
			
			boolean includeBaseProcess = PublishingUIPreferences.getIncludeBaseProcesses(configId);
			includeBaseProcessesCheckbox.setSelection(includeBaseProcess);
			
			updateCheckedStates();
		}
	}

	/**
	 * Adds event handlers to the wizard page controls.
	 */
	protected void addListeners() {
		publishConfigRadioButton.addListener(SWT.Selection, this);
		publishProcessesRadioButton.addListener(SWT.Selection, this);
		includeBaseProcessesCheckbox.addListener(SWT.Selection, this);

		processViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				Object checkedElement = event.getElement();
				boolean checked = event.getChecked();

				processViewer.setChecked(checkedElement, checked);
				processViewer.setSubtreeChecked(checkedElement, checked);
				if (includeBaseProcessesCheckbox.getSelection()) {
					selectBaseProcesses();
				}
				updateCheckedStates();

				setPageComplete(isPageComplete());
			}
		});
	}

	/**
	 * Updates the check states of process tree viewer.
	 */
	private void updateCheckedStates() {
		ITreeContentProvider cp = (ITreeContentProvider) processViewer
				.getContentProvider();
		if (config != null) {
			Object[] plugins = null;			
			if (ConfigFreeProcessPublishUtil.getInstance().isSameMethodConfiguration(config)) {
				//For config free process publish
				plugins = cp.getChildren(LibraryService.getInstance().getCurrentMethodLibrary());				
			} else {
				plugins = cp.getChildren(config);
			}			
			for (int i = 0; i < plugins.length; i++) {
				Object[] uiFolders = cp.getChildren(plugins[i]);
				int totalUIFolders = uiFolders.length;
				int checkedUIFolders = 0;
				for (int j = 0; j < uiFolders.length; j++) {
					Object[] processes = cp.getChildren(uiFolders[j]);
					int totalProcesses = processes.length;
					int checkedProcesses = 0;
					for (int k = 0; k < processes.length; k++) {
						if (processViewer.getChecked(processes[k])) {
							checkedProcesses++;
						}
					}
					if (checkedProcesses == 0) {
						processViewer.setGrayChecked(uiFolders[j], false);
					} else if (checkedProcesses == totalProcesses) {
						processViewer.setGrayed(uiFolders[j], false);
						processViewer.setChecked(uiFolders[j], true);
					} else {
						processViewer.setGrayChecked(uiFolders[j], true);
					}
					if (processViewer.getChecked(uiFolders[j])) {
						checkedUIFolders++;
					}
				}
				if (checkedUIFolders == totalUIFolders) {
					processViewer.setGrayed(plugins[i], false);
					processViewer.setChecked(plugins[i], true);
				} else if (checkedUIFolders == 0) {
					processViewer.setGrayChecked(plugins[i], false);
				} else {
					processViewer.setGrayChecked(plugins[i], true);
				}
			}
		}
	}

	/**
	 * Selects the referenced base processes in the process tree viewer.
	 */
	private void selectBaseProcesses() {
		// Get the referenced base processes.
		List<Process> processes = getSelectedProcesses();
		List<Process> baseProcesses = new ArrayList<Process>();
		for (Iterator it = processes.iterator(); it.hasNext();) {
			ConfigurationHelper.getBaseProcesses((Activity) it.next(), config,
					baseProcesses);
		}

		// Update the process tree viewer.
		for (Iterator it = baseProcesses.iterator(); it.hasNext();) {
			Object element = it.next();
			processViewer.setChecked(element, true);
		}
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(Event)
	 */
	public void handleEvent(Event event) {
		processViewer.getControl().setEnabled(
				publishProcessesRadioButton.getSelection());

		if (event.widget == includeBaseProcessesCheckbox) {
			// If selected, select all referenced base processes.
			selectBaseProcesses();
			updateCheckedStates();
		}

		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#onEnterPage(Object)
	 */
	public void onEnterPage(Object obj) {
		if (obj != null && obj instanceof String) {
			String configName = (String) obj;
			config = LibraryServiceUtil.getMethodConfiguration(LibraryService
					.getInstance().getCurrentMethodLibrary(), configName);
			if (config != null) {
				processViewer.setInput(config);				
				initControls();
				processViewer.expandAll();
			} else {
				//For config free process publishing
				config = ConfigFreeProcessPublishUtil.getInstance().getMethodConfigurationForConfigFreeProcess();
				processViewer.setInput(LibraryService.getInstance().getCurrentMethodLibrary());
				initControls();
				processViewer.expandAll();
			}
		}
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageCompleted()
	 */
	public boolean isPageComplete() {
		if (getErrorMessage() != null) {
			return false;
		}
		if (publishConfigRadioButton.getSelection()) {
			return true;
		}
		return getSelectedProcesses().size() > 0;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#getNextPageData()
	 */
	public Object getNextPageData() {
		return config;
	}

	/**
	 * Gets the user specified selected processes to be published.
	 * 
	 * @return a list of <code>Process</code>
	 */
	public boolean getPublishConfigSelection() {
		return publishConfigRadioButton.getSelection();
	}

	/**
	 * Gets all the user selected processes to be published.
	 * 
	 * @return a list of <code>Process</code>
	 */
	public List<Process> getSelectedProcesses() {
		List<Process> processes = new ArrayList<Process>();
		Object[] selected = processViewer.getCheckedElements();
		if (selected != null && selected.length > 0) {
			for (int i = 0; i < selected.length; i++) {
				if (selected[i] instanceof Process) {
					processes.add((Process) selected[i]);
				}
			}
		}
		return processes;
	}
	
	public void savePreferences() {
		if (config != null) {
			String configId = config.getGuid();
			boolean publishConfig = publishConfigRadioButton.getSelection();			
			PublishingUIPreferences.setPublishEntireConfig(configId, publishConfig);
			if (publishConfig) {
				List<String> processIds = new ArrayList<String>();
				PublishingUIPreferences.setProcesses(configId, processIds);
			} else {
				List<String> processIds = new ArrayList<String>();
				for (org.eclipse.epf.uma.Process process : getSelectedProcesses()) {
					processIds.add(process.getGuid());
				}
				PublishingUIPreferences.setProcesses(configId, processIds);
			}			
			PublishingUIPreferences.setIncludeBaseProcesses(configId, includeBaseProcessesCheckbox.getSelection());
		}
	}
	
	protected class ProcessViewerOfConfigFreeContentProvider extends ProcessTreeContentProvider {
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof ProcessTreeUIFolder) {
				List<Process> processes = new ArrayList<Process>();
				Object[] children = super.getChildren(parentElement);
				
				for (Object obj : children) {
					if (obj instanceof Process) {
						Process process = (Process)obj;
						if (ProcessScopeUtil.getInstance().isConfigFree(process)) {
							processes.add(process);
						}
					}
				}
				
				return processes.toArray();
			}
			
			return super.getChildren(parentElement);
		}
	}

}
