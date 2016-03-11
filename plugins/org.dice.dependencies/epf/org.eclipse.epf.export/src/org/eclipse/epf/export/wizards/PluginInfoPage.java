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
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.authoring.ui.wizards.ConfigurationTableLabelProvider;
import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.export.services.PluginExportData;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
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

/**
 * A wizard page that prompts the user to review the dependencies of the method
 * plug-ins that have been selected for export.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class PluginInfoPage extends BaseWizardPage implements
		ISelectionChangedListener, Listener {

	public static final String PAGE_NAME = PluginInfoPage.class.getName();

	private Table table;

	private TableViewer ctrl_tableViewer;

	private TableViewer ctrl_dependPluginsViewer;

	private TableViewer ctrl_associatedConfigViewer;

	private List dependentPluginList = new ArrayList();

	private List associatedConfigList = new ArrayList();

	private PluginExportData data;

	/**
	 * Creates a new instance.
	 */
	public PluginInfoPage(PluginExportData data) {
		super(PAGE_NAME);
		setTitle(ExportResources.reviewPluginsPage_title);
		setDescription(ExportResources.reviewPluginsPage_desc);
		setImageDescriptor(ExportPlugin.getDefault().getImageDescriptor(
				"full/wizban/exp_meth_plugin_wizban.gif")); //$NON-NLS-1$		
		this.data = data;
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout());

		createLabel(container,
				ExportResources.reviewPluginsPage_pluginsLabel_text);

		ctrl_tableViewer = createTableViewer(container, 360, 60, 1);
		table = ctrl_tableViewer.getTable();

		ILabelProvider labelProvider = new LabelProvider() {
			public Image getImage(Object element) {
				return LibraryUIImages.IMG_METHOD_PLUGIN;
			}

			public String getText(Object element) {
				if (element instanceof MethodPlugin) {
					return ((MethodPlugin) element).getName();
				} else {
					return element.toString();
				}
			}
		};
		ctrl_tableViewer.setLabelProvider(labelProvider);
		ctrl_tableViewer.setContentProvider(new ArrayContentProvider());
		if (data.getSelectedPlugins() != null) {
			ctrl_tableViewer.setInput(data.getSelectedPlugins().toArray());
		}

		createLabel(container,
				ExportResources.reviewPluginsPage_dependentPluginsLabel_text);

		ctrl_dependPluginsViewer = createTableViewer(container, 360, 60, 1);
		ctrl_dependPluginsViewer.setLabelProvider(labelProvider);
		ctrl_dependPluginsViewer.setContentProvider(new ArrayContentProvider());
		ctrl_dependPluginsViewer.setInput(dependentPluginList);

		createLabel(container,
				ExportResources.reviewPluginsPage_associatedConfigsLabel_text);

		ctrl_associatedConfigViewer = createTableViewer(container, 360, 60, 1);
		ctrl_associatedConfigViewer
				.setLabelProvider(new ConfigurationTableLabelProvider());
		ctrl_associatedConfigViewer
				.setContentProvider(new ArrayContentProvider());
		ctrl_associatedConfigViewer.setInput(associatedConfigList);

		if (data.getSelectedPlugins() != null
				&& !data.getSelectedPlugins().isEmpty()) {
			table.select(0);
			displayRelatedInfo(data.getSelectedPlugins().iterator().next());
		}

		addListeners();

		setControl(container);
		setPageComplete(false);
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
			Object[] plugin = selection.toArray();
			displayRelatedInfo((MethodPlugin) plugin[0]);
		}

	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#onEnterPage(Object)
	 */
	public void onEnterPage(Object obj) {
		if (data.getSelectedPlugins() != null
				&& !data.getSelectedPlugins().isEmpty()) {
			ctrl_tableViewer.setInput(data.getSelectedPlugins().toArray());
			table.select(0);
			displayRelatedInfo(data.getSelectedPlugins().iterator().next());
		}
	}

	private void displayRelatedInfo(MethodPlugin plugin) {
		if (plugin == null)
			return;

		dependentPluginList = plugin.getBases();
		ctrl_dependPluginsViewer.setInput(dependentPluginList.toArray());
		associatedConfigList = LibraryUtil.getAssociatedConfigurations(plugin);
		ctrl_associatedConfigViewer.setInput(associatedConfigList.toArray());
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		return true;
	}

	protected void saveDataToModel() {
		// FIXME! Refactor to use: data.buildAssociatedConfigMap()
		if (data.getSelectedPlugins() != null) {
			data.associatedConfigMap.clear();
			for (Iterator iter = data.getSelectedPlugins().iterator(); iter
					.hasNext();) {
				MethodPlugin element = (MethodPlugin) iter.next();
				data.associatedConfigMap.put(element, LibraryUtil
						.getAssociatedConfigurations(element));
			}
		}
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		saveDataToModel();
		ExportPluginSummaryPage page = ((ExportPluginWizard) getWizard()).page3;
		page.onEnterPage(null);
		return page;
	}

}
