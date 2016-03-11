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
package org.eclipse.epf.export.msp.ui.wizards;

import org.eclipse.epf.export.msp.ui.ExportMSPUIPlugin;
import org.eclipse.epf.export.msp.ui.ExportMSPUIResources;
import org.eclipse.epf.export.msp.ui.preferences.ExportMSPUIPreferences;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.publishing.ui.PublishingUIResources;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page that prompts the user to select the export options.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class SelectExportOptionsPage extends BaseWizardPage {

	public static final String PAGE_NAME = SelectExportOptionsPage.class
			.getName();

	protected Composite composite;

	protected Composite configComposite;

	protected Text processText;

	protected Combo configCombo;

	protected Composite optionsComposite;

	protected Button publishWebSiteRadioButton;

	protected Composite publishOptionsComposite;

	protected Button publishConfigRadioButton;

	protected Button publishProcessRadioButton;

	protected Button exportOnlyPlannedWBSElementsCheckBox;

	/**
	 * Creates a new instance.
	 */
	public SelectExportOptionsPage(String pageName) {
		super(pageName);
		setTitle(ExportMSPUIResources.selectExportOptionsWizardPage_title);
		setDescription(ExportMSPUIResources.selectExportOptionsWizardPage_text);
		setImageDescriptor(ExportMSPUIPlugin.getDefault().getImageDescriptor(
				"full/wizban/exp_ms_prj_wizban.gif")); //$NON-NLS-1$	
	}

	/**
	 * Creates a new instance.
	 */
	public SelectExportOptionsPage() {
		this(PAGE_NAME);
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		composite = createGridLayoutComposite(parent, 1);

		configComposite = createGridLayoutComposite(composite, 2);

		createLabel(configComposite,
				ExportMSPUIResources.selectedProcessLabel_text);

		processText = createText(configComposite, ""); //$NON-NLS-1$

		createLabel(configComposite,
				ExportMSPUIResources.configurationLabel_text);

		configCombo = new Combo(configComposite, SWT.BORDER | SWT.READ_ONLY);
		configCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		optionsComposite = createGridLayoutComposite(composite, 1);

		publishWebSiteRadioButton = createCheckbox(optionsComposite,
				ExportMSPUIResources.publishWebSiteCheckBox_text);

		publishOptionsComposite = createGridLayoutComposite(optionsComposite, 1);
		GridLayout layout = (GridLayout) publishOptionsComposite.getLayout();
		layout.marginTop = -5;
		layout.marginLeft = 12;

		publishConfigRadioButton = createRadioButton(publishOptionsComposite,
				ExportMSPUIResources.publishConfigButton_text);

		publishProcessRadioButton = createRadioButton(publishOptionsComposite,
				ExportMSPUIResources.publishProcessButton_text);

		exportOnlyPlannedWBSElementsCheckBox = createCheckbox(optionsComposite,
				ExportMSPUIResources.exportOnlyPlannedElementsCheckBox_text);

		initControls();

		addListeners();

		setControl(composite);
	}

	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		if (ExportMSPUIPreferences.getPublishWebSite()) {
			publishWebSiteRadioButton.setSelection(true);
		}

		boolean publishConfig = ExportMSPUIPreferences
				.getPublishConfiguration();
		publishConfigRadioButton.setSelection(publishConfig);
		publishProcessRadioButton.setSelection(!publishConfig);

		updatePublishOptionsButtons();

		exportOnlyPlannedWBSElementsCheckBox
				.setSelection(ExportMSPUIPreferences
						.getExportOnlyPlannedWBSElements());
	}

	/**
	 * Adds event handlers to the wizard page controls.
	 */
	protected void addListeners() {
		configCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPageComplete(isPageComplete());
			}
		});

		publishWebSiteRadioButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent event) {
				updatePublishOptionsButtons();
			}

			public void widgetDefaultSelected(SelectionEvent event) {
			}
		});
	}

	
	private Process selectedProcess;
	/**
	 * Updates the Configuration Combo.
	 */
	protected void updateConfigCombo(Process process) {
		String[] contextNames = LibraryServiceUtil.getContexts(process);
		configCombo.setItems(contextNames);
		String defaultContext = process.getDefaultContext().getName();
		configCombo.setText(defaultContext);
		selectedProcess = process;
	}

	/**
	 * Updates the publishing options radio buttons.
	 */
	protected void updatePublishOptionsButtons() {
		boolean enabled = getPublishWebSiteSelection();
		publishConfigRadioButton.setEnabled(enabled);
		publishProcessRadioButton.setEnabled(enabled);
		setPageComplete(isPageComplete());
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#onEnterPage(Object)
	 */
	public void onEnterPage(Object obj) {
		if (obj != null && obj instanceof Process) {
			Process process = (Process) obj;
			processText.setText(process.getName());
			updateConfigCombo(process);
		}
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageCompleted()
	 */
	public boolean isPageComplete() {
		MethodConfiguration config = getMethodConfiguration();
		if (config == null) {
			return false;
		}

		if (getPublishWebSiteSelection()
				&& config.getProcessViews().size() == 0 && !(config instanceof Scope)) {
			setErrorMessage(PublishingUIResources.missingViewError_msg);
			return false;
		}

		setErrorMessage(null);

		return true;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#getNextPageData()
	 */
	public Object getNextPageData() {
		return getMethodConfiguration();
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		IWizardPage nextPage = null;

		if (getPublishWebSiteSelection()) {
			nextPage = super.getNextPage();
		} else {
			nextPage = super.getNextPage().getNextPage();
		}

		return nextPage;
	}

	/**
	 * Gets the user selected method configuration.
	 */
	public MethodConfiguration getMethodConfiguration() {
		String configName = configCombo.getText().trim();
		Scope scope = selectedProcess == null ? null : ProcessScopeUtil
				.getInstance().loadScope(selectedProcess);
		if (scope != null) {
			if (scope.getName().equals(configName)) {
				return scope;
			}
		}
		MethodLibrary library = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		return LibraryServiceUtil.getMethodConfiguration(library, configName);
	}

	/**
	 * Gets the publish web site selection.
	 */
	public boolean getPublishWebSiteSelection() {
		return publishWebSiteRadioButton.getSelection();
	}

	/**
	 * Gets the publish configuration selection.
	 */
	public boolean getPublishConfigSelection() {
		return publishConfigRadioButton.getSelection();
	}

	/**
	 * Gets the publish process selection.
	 */
	public boolean getPublishProcessSelection() {
		return publishProcessRadioButton.getSelection();
	}

	/**
	 * Gets the publish configuration selection.
	 */
	public boolean getExportOnlyPlannedWBSElementsSelection() {
		return exportOnlyPlannedWBSElementsCheckBox.getSelection();
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#dispose()
	 */
	public void dispose() {
		ExportMSPUIPreferences.setPublishWebSite(getPublishWebSiteSelection());
		ExportMSPUIPreferences
				.setPublishConfiguration(getPublishConfigSelection());
		ExportMSPUIPreferences
				.setExportOnlyPlannedWBSElements(getExportOnlyPlannedWBSElementsSelection());
		MethodConfiguration config = getMethodConfiguration();
		if (config != null) {
			ExportMSPUIPreferences.setConfigurationName(config.getName());
		}
		super.dispose();
	}

}