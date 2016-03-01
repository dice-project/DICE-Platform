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
package org.eclipse.epf.export.wizards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.export.services.ConfigurationExportData;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page that prompts the user to select one or more method
 * configuration specifications to export.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportConfigSelectSpecsPage extends BaseWizardPage implements
		ISelectionChangedListener, ICheckStateListener, Listener {

	public static final String PAGE_NAME = ExportConfigSelectSpecsPage.class
			.getName();

	private Table table;

	private CheckboxTableViewer ctrl_chkboxTableViewer;

	private Text ctrl_briefDesc;

	private int checkedCount = 0;

	private List checkedConfigList = new ArrayList();

	private ConfigurationExportData data;	
	
	private Button selectAllButton;
	
	private Button deselectAllButton;

	/**
	 * Creates a new instance.
	 */
	public ExportConfigSelectSpecsPage(ConfigurationExportData data) {
		super(PAGE_NAME);
		setTitle(ExportResources.selectConfigSpecsPage_title);
		setDescription(ExportResources.selectConfigSpecsPage_desc);
		setImageDescriptor(ExportPlugin.getDefault().getImageDescriptor(
				"full/wizban/exp_lib_conf_wizban.gif")); //$NON-NLS-1$
		this.data = data;
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout());

		Composite container1 = new Composite(container, SWT.NONE);
		container1.setLayout(new GridLayout(3, false));
		createLabel(container1,
				ExportResources.selectConfigSpecsPage_configsLabel_text);

		selectAllButton = createButton(
				container1,
				AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_SelectAllButtonLabel);
		deselectAllButton = createButton(
				container1,
				AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_DeselectAllButtonLabel);

		ctrl_chkboxTableViewer = createCheckboxTableViewer(container, 1);
		table = ctrl_chkboxTableViewer.getTable();

/*		MethodConfiguration[] configs = LibraryServiceUtil
				.getMethodConfigurations(LibraryService.getInstance()
						.getCurrentMethodLibrary());*/
		
		List<MethodConfiguration> configList = new ArrayList<MethodConfiguration>(
				LibraryService.getInstance().getCurrentMethodLibrary()
						.getPredefinedConfigurations());
		if (configList.size() > 1) {
			Comparator comparator = PresentationContext.INSTANCE
					.getComparator();
			Collections.<MethodConfiguration> sort(configList, comparator);
		}
		
		MethodConfiguration[] a = new MethodConfiguration[configList.size()];
		MethodConfiguration[] configs = configList.toArray(a);

		ILabelProvider labelProvider = new LabelProvider() {
			public Image getImage(Object element) {
				return LibraryUIImages.IMG_METHOD_PLUGIN;
			}

			public String getText(Object element) {
				if (element instanceof MethodConfiguration) {
					return ((MethodConfiguration) element).getName();
				} else {
					return element.toString();
				}
			}
		};
		ctrl_chkboxTableViewer.setLabelProvider(labelProvider);
		ctrl_chkboxTableViewer.setContentProvider(new ArrayContentProvider());
		if (configs != null) {
			ctrl_chkboxTableViewer.setInput(configs);
		}

		createLabel(container, AuthoringUIText.BRIEF_DESCRIPTION_TEXT);
		ctrl_briefDesc = createMultiLineText(container, "", 360, 80, 3); //$NON-NLS-1$

		if (configs != null && configs.length > 0) {
			setDisplayAttributes((MethodConfiguration) configs[0]);
		}

		addListeners();

		setControl(container);
		setPageComplete(true);
	}

	private void addListeners() {
		ctrl_chkboxTableViewer.addSelectionChangedListener(this);
		ctrl_chkboxTableViewer.addCheckStateListener(this);

		final MethodLibrary lib = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		selectAllButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				ctrl_chkboxTableViewer.setAllChecked(true);
				if (lib != null) {
					checkedConfigList.clear();
					checkedConfigList.addAll(lib.getPredefinedConfigurations());
					checkedCount = checkedConfigList.size();
				}

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
				if (lib != null) {
					checkedConfigList.clear();
					checkedCount = 0;
				}

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
		Object obj = event.getElement();

		if (event.getChecked()) {
			checkedCount++;
			checkedConfigList.add(obj);
		} else {
			checkedCount--;
			checkedConfigList.remove(obj);
		}

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
			setDisplayAttributes((MethodConfiguration) configs[0]);
		}
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
	}

	private void setDisplayAttributes(MethodConfiguration config) {
		ctrl_briefDesc.setText(config.getBriefDescription());
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		if (getErrorMessage() != null)
			return false;
		return checkedCount > 0;
	}

	protected void saveDataToModel() {
		data.selectedConfigs = checkedConfigList;
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		saveDataToModel();
		ExportConfigDestinationPage page = ((ExportConfigurationWizard) getWizard()).destinationPage;
		return page;
	}

}
