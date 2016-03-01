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

import java.util.Iterator;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.services.ConfigSpecs;
import org.eclipse.epf.importing.services.ConfigurationImportData;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page that prompts the user to select the configuration
 * specificaitons to import.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class SelectConfigSpecsToImportPage extends BaseWizardPage implements
		ISelectionChangedListener, ICheckStateListener {

	public static final String PAGE_NAME = SelectConfigSpecsToImportPage.class
			.getName();

	private static final String libraryConfigSpecExists = ImportResources.SelectConfigSpecsToImportPage_libraryConfigSpecExists;

	private static final String libraryConfigSpecNotExists = ImportResources.SelectConfigSpecsToImportPage_libraryConfigSpecNotExists;

	private static final String configSpecNoSelection = ImportResources.SelectConfigSpecsToImportPage_configSpecNoSelection;

	private static final String configSpecDataLabel = ImportResources.SelectConfigSpecsToImportPage_configSpecDataLabel;

	private CheckboxTableViewer ctrl_chkboxTableViewer;

	// 2 controls for displaying configspec info for plugins in import directory
	private Text ctrl_briefDescImport;

	private Label importConfigSpecLabel;

	// 2 controls for displaying configspec info for plugins in the library
	private Text ctrl_briefDescLibrary;

	private Label libraryConfigSpecLabel;

	private Composite container;

	private ConfigurationImportData data;
	
	private Button selectAllButton;
	
	private Button deselectAllButton;

	/**
	 * Creates a new instance.
	 */
	public SelectConfigSpecsToImportPage(ConfigurationImportData data) {
		super(PAGE_NAME);
		setTitle(ImportResources.selectConfigSpecsWizardPage_title);
		setDescription(ImportResources.selectConfigSpecsWizardPage_text);
		setImageDescriptor(ImportPlugin.getDefault().getImageDescriptor(
				"full/wizban/imp_lib_conf_wizban.gif")); //$NON-NLS-1$
		this.data = data;
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, true));

		Composite tableContainer = new Composite(container, SWT.NONE);
		tableContainer.setLayout(new GridLayout());
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;
		tableContainer.setLayoutData(gridData);
		
		Composite container1 = new Composite(tableContainer, SWT.NONE);
		container1.setLayout(new GridLayout(3, false));
		//createLabel(container1,
		//		ImportResources.SelectConfigSpecsToImportPage_label_configs, 2);
		createLabel(container1,
				ImportResources.SelectConfigSpecsToImportPage_label_configs);

		selectAllButton = createButton(
				container1,
				AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_SelectAllButtonLabel);
		deselectAllButton = createButton(
				container1,
				AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_DeselectAllButtonLabel);

		
		ctrl_chkboxTableViewer = createCheckboxTableViewer(tableContainer, 1);

		ILabelProvider labelProvider = new LabelProvider() {
			public Image getImage(Object element) {
				return LibraryUIImages.IMG_METHOD_CONFIGURATON;
			}

			public String getText(Object element) {
				return super.getText(element);
			}
		};

		ctrl_chkboxTableViewer.setLabelProvider(labelProvider);
		ctrl_chkboxTableViewer.setContentProvider(new ArrayContentProvider());

		Composite importContainer = new Composite(container, SWT.NONE);
		importContainer.setLayout(new GridLayout());
		importContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createImportConfigSpecInfo(importContainer);

		Composite libraryContainer = new Composite(container, SWT.NONE);
		libraryContainer.setLayout(new GridLayout());
		libraryContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createLibraryConfigSpecInfo(libraryContainer);

		addListeners();

		setControl(container);
		setPageComplete(false);
	}

	private void createImportConfigSpecInfo(Composite container) {
		importConfigSpecLabel = createLabel(container, configSpecNoSelection, 1);
		createLine(container, 1);

		createLabel(container,
				ImportResources.SelectConfigSpecsToImportPage_label_desc, 1);
		ctrl_briefDescImport = createMultiLineText(container, "", 275, 100, 1); //$NON-NLS-1$
	}

	private void createLibraryConfigSpecInfo(Composite container) {
		libraryConfigSpecLabel = createLabel(container, configSpecNoSelection,
				1);
		createLine(container, 1);

		createLabel(container,
				ImportResources.SelectConfigSpecsToImportPage_label_desc, 1);
		ctrl_briefDescLibrary = createMultiLineText(container, "", 275, 100, 1); //$NON-NLS-1$
	}

	private void addListeners() {
		ctrl_chkboxTableViewer.addSelectionChangedListener(this);
		ctrl_chkboxTableViewer.addCheckStateListener(this);
		
		selectAllButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				ctrl_chkboxTableViewer.setAllChecked(true);
				setPageComplete(isPageComplete());
				getWizard().getContainer().updateButtons();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		deselectAllButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				ctrl_chkboxTableViewer.setAllChecked(false);
				setPageComplete(isPageComplete());
				getWizard().getContainer().updateButtons();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});
	}

	/**
	 * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
	 */
	public void checkStateChanged(CheckStateChangedEvent event) {
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
	}

	/**
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		StructuredSelection selection = (StructuredSelection) event
				.getSelection();
		if (!selection.isEmpty()) {
			Object[] configs = selection.toArray();
			setDisplayAttributes((ConfigSpecs.Entry) configs[0]);
		} else {
			clearDisplayAttributes();
		}
	}

	private void setDisplayAttributes(ConfigSpecs.Entry spec) {
		ctrl_briefDescImport
				.setText(spec.configSpec.brief_desc == null ? "" : spec.configSpec.brief_desc); //$NON-NLS-1$
		importConfigSpecLabel.setText(configSpecDataLabel);

		if (spec.existingConfig != null) {
			ctrl_briefDescLibrary.setText(spec.existingConfig
					.getBriefDescription());
			libraryConfigSpecLabel.setText(libraryConfigSpecExists);
		} else {
			libraryConfigSpecLabel.setText(libraryConfigSpecNotExists);
			ctrl_briefDescLibrary.setText(""); //$NON-NLS-1$
		}
	}

	private void clearDisplayAttributes() {
		ctrl_briefDescImport.setText(""); //$NON-NLS-1$
		ctrl_briefDescLibrary.setText(""); //$NON-NLS-1$
		libraryConfigSpecLabel.setText(configSpecNoSelection);
		importConfigSpecLabel.setText(configSpecNoSelection);
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isCompleted()
	 */
	public boolean isPageComplete() {
		if (getErrorMessage() != null)
			return false;

		if (ctrl_chkboxTableViewer.getCheckedElements().length > 0) {
			// Set data.specs.configs list, fix the selected field of each
			// entry.
			Iterator iter = data.specs.configs.iterator();
			while (iter.hasNext()) {
				ConfigSpecs.Entry e = (ConfigSpecs.Entry) iter.next();
				if (ctrl_chkboxTableViewer.getChecked(e))
					e.selected = true;
				else
					e.selected = false;
			}
			((ImportConfigurationWizard) getWizard()).okToComplete = true;
		} else {
			((ImportConfigurationWizard) getWizard()).okToComplete = false;
		}

		// Always return false so that the "Next" button is never enabled.
		return false;
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage() {
		return isPageComplete();
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#onEnterPage(Object)
	 */
	public void onEnterPage(Object obj) {
		ctrl_chkboxTableViewer.setInput(data.specs.configs);
		if (data.specs.configs.size() > 0) {
			IStructuredSelection sel = new StructuredSelection(
					data.specs.configs.get(0));
			ctrl_chkboxTableViewer.setSelection(sel);
		}
	}

}
