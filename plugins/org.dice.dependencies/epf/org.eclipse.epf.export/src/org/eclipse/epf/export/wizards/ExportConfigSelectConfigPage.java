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

import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.export.services.ConfigurationExportData;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page that prompts the user to select a method configuration to
 * export.
 * 
 * @author Bingxue Xu
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportConfigSelectConfigPage extends BaseWizardPage implements
		ISelectionChangedListener, Listener {

	public static final String PAGE_NAME = ExportConfigSelectConfigPage.class
			.getName();

	private Table table;

	protected TableViewer ctrl_tableViewer;

	private Text ctrl_briefDesc;

	private ConfigurationExportData data;

	/**
	 * Creates a new instance.
	 */
	public ExportConfigSelectConfigPage(ConfigurationExportData data) {
		super(PAGE_NAME);
		setTitle(ExportResources.selectConfigPage_title);
		setDescription(ExportResources.selectConfigPage_desc);
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

		createLabel(container,
				ExportResources.selectConfigPage_configsLabel_text);

		ctrl_tableViewer = createTableViewer(container, 360, 120, 1);
		table = ctrl_tableViewer.getTable();

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
				return LibraryUIImages.IMG_METHOD_CONFIGURATON;
			}

			public String getText(Object element) {
				if (element instanceof MethodConfiguration) {
					return ((MethodConfiguration) element).getName();
				} else {
					return element.toString();
				}
			}
		};
		ctrl_tableViewer.setLabelProvider(labelProvider);
		ctrl_tableViewer.setContentProvider(new ArrayContentProvider());
		if (configs != null) {
			ctrl_tableViewer.setInput(configs);
		}

		createLabel(container, AuthoringUIText.BRIEF_DESCRIPTION_TEXT);

		ctrl_briefDesc = createMultiLineText(container, "", 360, 80, 3); //$NON-NLS-1$

		if (configs != null && configs.length > 0) {
			table.select(0);
			setDisplayAttributes((MethodConfiguration) configs[0]);
		}

		addListeners();

		setControl(container);
		setPageComplete(true);
	}

	private void addListeners() {
		ctrl_tableViewer.addSelectionChangedListener(this);
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

	protected void saveDataToModel() {
		IStructuredSelection selection = (IStructuredSelection) ctrl_tableViewer
				.getSelection();
		Object[] configs = selection.toArray();
		MethodConfiguration config = (MethodConfiguration) configs[0];
		List arrList = new ArrayList();
		arrList.add(config);
		data.selectedConfigs = arrList;
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		saveDataToModel();
		ExportConfigCheckingPage page = ((ExportConfigurationWizard) getWizard()).configCheckingPage;
		page.onEnterPage(null);
		return page;
	}
	
    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
     */
    public boolean isPageComplete() {
		IStructuredSelection selection = (IStructuredSelection) ctrl_tableViewer
											.getSelection();
    	if (selection == null || selection.isEmpty()) {
    		return false;
    	}
        return super.isPageComplete();
    }

}
