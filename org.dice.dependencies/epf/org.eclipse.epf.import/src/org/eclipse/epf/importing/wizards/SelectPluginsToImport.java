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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.importing.ImportPlugin;
import org.eclipse.epf.importing.ImportResources;
import org.eclipse.epf.importing.services.PluginImportData;
import org.eclipse.epf.importing.services.PluginImportingService;
import org.eclipse.epf.importing.services.PluginImportData.PluginInfo;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page that prompts the user to the method plug-ins to import.
 * 
 * @author Jeff Hardy
 * @author Kelvin Low
 * @since 1.0
 */
public class SelectPluginsToImport extends BaseWizardPage implements
		ISelectionChangedListener, ICheckStateListener, Listener {

	public static final String PAGE_NAME = SelectPluginsToImport.class
			.getName();

	private static final String libraryPluginExists = ImportResources.SelectPluginsToImport_libraryPluginExists;

	private static final String libraryPluginNotExists = ImportResources.SelectPluginsToImport_libraryPluginNotExists;

	private static final String pluginNoSelection = ImportResources.SelectPluginsToImport_pluginNoSelection;

	private static final String pluginDataLabel = ImportResources.SelectPluginsToImport_pluginDataLabel;

	private CheckboxTableViewer ctrl_chkboxTableViewer;

	private Text ctrl_authorImport;

	private Text ctrl_versionImport;

	private Text ctrl_briefDescImport;

	private Label importPluginLabel;

	private Text ctrl_authorLibrary;

	private Text ctrl_versionLibrary;

	private Text ctrl_briefDescLibrary;

	private Label libraryPluginLabel;

	private Composite container;

	private int checkedCount = 0;

	private List checkedPluginList = new ArrayList();

	private PluginImportData data;

	private PluginImportingService service;
	
	private Button selectAllButton;
	
	private Button deselectAllButton;

	/**
	 * Creates a new instance.
	 */
	public SelectPluginsToImport(PluginImportData data,
			PluginImportingService service) {
		super(PAGE_NAME);
		setTitle(ImportResources.selectPluginsWizardPage_title);
		setDescription(ImportResources.selectPluginsWizardPage_text);
		setImageDescriptor(ImportPlugin.getDefault().getImageDescriptor(
				"full/wizban/imp_meth_plugin_wizban.gif")); //$NON-NLS-1$		
		this.service = service;
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
		createLabel(container1,
				ImportResources.SelectPluginsToImport_label_plugins);

		selectAllButton = createButton(
				container1,
				AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_SelectAllButtonLabel);
		deselectAllButton = createButton(
				container1,
				AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_DeselectAllButtonLabel);
				
		ctrl_chkboxTableViewer = createCheckboxTableViewer(tableContainer, 2);

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
		ctrl_chkboxTableViewer.setLabelProvider(labelProvider);
		ctrl_chkboxTableViewer.setContentProvider(new ArrayContentProvider());

		Composite importContainer = new Composite(container, SWT.NONE);
		importContainer.setLayout(new GridLayout());

		createImportPluginInfo(importContainer);

		Composite libraryContainer = new Composite(container, SWT.NONE);
		libraryContainer.setLayout(new GridLayout());

		createLibraryPluginInfo(libraryContainer);

		addListeners();

		setControl(container);
		setPageComplete(false);
	}

	private void createImportPluginInfo(Composite container) {
		importPluginLabel = createLabel(container, pluginNoSelection, 1);
		createLine(container, 1);

		createLabel(container,
				ImportResources.SelectPluginsToImport_label_author, 1);
		ctrl_authorImport = createMultiLineText(container, "", 275, 40, 1); //$NON-NLS-1$

		createLabel(container,
				ImportResources.SelectPluginsToImport_label_version, 1);
		ctrl_versionImport = createText(container, "", 275, 1); //$NON-NLS-1$

		createLabel(container,
				ImportResources.SelectPluginsToImport_label_desc, 1);
		ctrl_briefDescImport = createMultiLineText(container, "", 275, 100, 1); //$NON-NLS-1$
	}

	private void createLibraryPluginInfo(Composite container) {
		libraryPluginLabel = createLabel(container, pluginNoSelection, 1);
		createLine(container, 1);

		createLabel(container,
				ImportResources.SelectPluginsToImport_label_author, 1);
		ctrl_authorLibrary = createMultiLineText(container, "", 275, 40, 1); //$NON-NLS-1$

		createLabel(container,
				ImportResources.SelectPluginsToImport_label_version, 1);
		ctrl_versionLibrary = createText(container, "", 275, 1); //$NON-NLS-1$

		createLabel(container,
				ImportResources.SelectPluginsToImport_label_desc, 1);
		ctrl_briefDescLibrary = createMultiLineText(container, "", 275, 100, 1); //$NON-NLS-1$
	}

	private void addListeners() {
		ctrl_chkboxTableViewer.addSelectionChangedListener(this);
		ctrl_chkboxTableViewer.addCheckStateListener(this);
		
		selectAllButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				ctrl_chkboxTableViewer.setAllChecked(true);
				ImportPluginWizard wizard = (ImportPluginWizard) getWizard();
				if (! wizard.data.getPlugins().isEmpty()) {
					checkedPluginList.clear();
					checkedPluginList.addAll(wizard.data.getPlugins());
					checkedCount = checkedPluginList.size();
					setAllSelected(wizard.data, true);
					String message = service.validateSelection();
					if (message != null && message.length() > 0) {
						setErrorMessage(message);
					} else {
						setErrorMessage(null);
					}
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
				checkedPluginList.clear();
				checkedCount = 0;
				ImportPluginWizard wizard = (ImportPluginWizard) getWizard();
				setAllSelected(wizard.data, false);
				
				setPageComplete(isPageComplete());
				getWizard().getContainer().updateButtons();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});
		
	}
	
	private void setAllSelected(PluginImportData data, boolean selected) {
		for (PluginImportData.PluginInfo info: data.getPlugins()) {
			info.selected = selected;
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.ICheckStateListener#checkStateChanged(org.eclipse.jface.viewers.CheckStateChangedEvent)
	 */
	public void checkStateChanged(CheckStateChangedEvent event) {
		Object obj = event.getElement();

		// Set the flag to import the selected plug-ins.
		if (obj instanceof PluginImportData.PluginInfo) {
			((PluginImportData.PluginInfo) obj).selected = event.getChecked();
		}
		String message = service.validateSelection();
		if (message != null && message.length() > 0) {
			super.setErrorMessage(message);
		} else {
			super.setErrorMessage(null);
		}

		if (event.getChecked()) {
			checkedCount++;
			checkedPluginList.add(obj);
		} else {
			checkedCount--;
			checkedPluginList.remove(obj);
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
			Object[] plugin = selection.toArray();
			setDisplayAttributes((PluginInfo) plugin[0]);
		} else {
			clearDisplayAttributes();
		}
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
	}

	private void setDisplayAttributes(PluginInfo plugin) {
		ctrl_authorImport.setText(plugin.authors == null ? "" : plugin.authors); //$NON-NLS-1$
		ctrl_versionImport
				.setText(plugin.version == null ? "" : plugin.version); //$NON-NLS-1$
		ctrl_briefDescImport
				.setText(plugin.brief_desc == null ? "" : plugin.brief_desc); //$NON-NLS-1$
		importPluginLabel.setText(pluginDataLabel);

		if (plugin.existingPlugin != null) {
			libraryPluginLabel.setText(libraryPluginExists);
			ctrl_authorLibrary.setText(plugin.existingPlugin.getAuthors());
			ctrl_versionLibrary.setText(plugin.existingPlugin.getVersion());
			ctrl_briefDescLibrary.setText(plugin.existingPlugin
					.getBriefDescription());
		} else {
			libraryPluginLabel.setText(libraryPluginNotExists);
			ctrl_authorLibrary.setText(""); //$NON-NLS-1$
			ctrl_versionLibrary.setText(""); //$NON-NLS-1$
			ctrl_briefDescLibrary.setText(""); //$NON-NLS-1$
		}
	}

	private void clearDisplayAttributes() {
		importPluginLabel.setText(pluginNoSelection);
		ctrl_authorImport.setText(""); //$NON-NLS-1$
		ctrl_versionImport.setText(""); //$NON-NLS-1$
		ctrl_briefDescImport.setText(""); //$NON-NLS-1$

		libraryPluginLabel.setText(pluginNoSelection);
		ctrl_authorLibrary.setText(""); //$NON-NLS-1$
		ctrl_versionLibrary.setText(""); //$NON-NLS-1$
		ctrl_briefDescLibrary.setText(""); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		if (getErrorMessage() != null)
			return false;
		return (checkedCount > 0);
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#onEnterPage(Object)
	 */
	public void onEnterPage(Object obj) {
		List plugins = data.getPlugins();
		if (plugins != null) {
			ctrl_chkboxTableViewer.setInput(plugins.toArray());
			if (!plugins.isEmpty()) {
				StructuredSelection selection = new StructuredSelection(plugins
						.get(0));
				ctrl_chkboxTableViewer.setSelection(selection);
			}
		}
	}

}
