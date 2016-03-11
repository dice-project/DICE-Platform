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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.export.ExportPlugin;
import org.eclipse.epf.export.ExportResources;
import org.eclipse.epf.export.services.PluginExportData;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodLibrary;
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
 * A wizard page that prompts the user to select the method plug-ins to be
 * exported.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class SelectPluginPage extends BaseWizardPage implements
		ISelectionChangedListener, ICheckStateListener, Listener {

	public static final String PAGE_NAME = SelectPluginPage.class.getName();

	private Table table;

	private CheckboxTableViewer ctrl_chkboxTableViewer;

	private Text ctrl_author;

	private Text ctrl_version;

	private Text ctrl_briefDesc;

	private int checkedCount = 0;

	private Collection<MethodPlugin> checkedPluginList = new ArrayList<MethodPlugin>();

	private PluginExportData data;
	
	private Button selectAllButton;
	
	private Button deselectAllButton;

	/**
	 * Creates a new instance.
	 */
	public SelectPluginPage(PluginExportData data) {
		super(PAGE_NAME);
		setTitle(ExportResources.selectPluginsPage_title);
		setDescription(ExportResources.selectPluginsPage_desc);
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

		Composite container1 = new Composite(container, SWT.NONE);
		container1.setLayout(new GridLayout(3, false));
		createLabel(container1,
				ExportResources.selectPluginsPage_pluginsLabel_text);

		selectAllButton = createButton(
				container1,
				AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_SelectAllButtonLabel);
		deselectAllButton = createButton(
				container1,
				AuthoringUIResources.AuthoringUIPlugin_SaveAllEditorsPage_DeselectAllButtonLabel);

		ctrl_chkboxTableViewer = createCheckboxTableViewer(container, 1);
		table = ctrl_chkboxTableViewer.getTable();

		MethodLibrary library = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		List plugins = (library == null) ? new ArrayList() : new ArrayList(library
				.getMethodPlugins());
		
		if (plugins.size() > 1) {
			Comparator comparator = PresentationContext.INSTANCE.getComparator();
			Collections.<MethodPlugin>sort(plugins, comparator);
		}
		
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
		if (plugins != null) {
			ctrl_chkboxTableViewer.setInput(plugins.toArray());
		}

		createLabel(container, AuthoringUIText.AUTHORS_TEXT);
		ctrl_author = createMultiLineText(container, "", 360, 40, 3); //$NON-NLS-1$

		createLabel(container, AuthoringUIText.VERSION_TEXT);
		ctrl_version = createText(container, "", 360, 3); //$NON-NLS-1$

		createLabel(container, AuthoringUIText.BRIEF_DESCRIPTION_TEXT);
		ctrl_briefDesc = createMultiLineText(container, "", 360, 80, 3); //$NON-NLS-1$

		if (plugins != null && !plugins.isEmpty()) {
			table.select(0);
			setDisplayAttributes((MethodPlugin) plugins.get(0));
		}

		addListeners(plugins);

		setControl(container);
		setPageComplete(false);
	}

	private void addListeners(final List<MethodPlugin> plugins) {
		ctrl_chkboxTableViewer.addSelectionChangedListener(this);
		ctrl_chkboxTableViewer.addCheckStateListener(this);
		
		final MethodLibrary lib = LibraryService.getInstance()
						.getCurrentMethodLibrary();
		selectAllButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				ctrl_chkboxTableViewer.setAllChecked(true);
				if (lib != null) {
					checkedPluginList.clear();
					checkedPluginList.addAll(plugins);
					checkedCount = checkedPluginList.size();
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
					checkedPluginList.clear();
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

		if (obj instanceof MethodPlugin) {
			if (event.getChecked()) {
				checkedCount++;
				checkedPluginList.add((MethodPlugin)obj);
			} else {
				checkedCount--;
				checkedPluginList.remove(obj);
			}
			setPageComplete(isPageComplete());
			getWizard().getContainer().updateButtons();
		}
	}

	/**
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		StructuredSelection selection = (StructuredSelection) event
				.getSelection();
		if (!selection.isEmpty()) {
			Object[] plugin = selection.toArray();
			setDisplayAttributes((MethodPlugin) plugin[0]);
		}

	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		setPageComplete(isPageComplete());
		getWizard().getContainer().updateButtons();
	}

	private void setDisplayAttributes(MethodPlugin plugin) {
		ctrl_author.setText(plugin.getAuthors());
		ctrl_version.setText(plugin.getVersion());
		ctrl_briefDesc.setText(plugin.getBriefDescription());
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		if (LibraryService.getInstance().getCurrentMethodLibrary() == null) {
			setErrorMessage(LibraryUIResources.noOpenLibraryWarning_msg);
			return false;
		}

		if (getErrorMessage() != null) {
			return false;
		}

		return checkedCount > 0;
	}

	protected void saveDataToModel() {
		data.setSelectedPlugins(checkedPluginList);
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#getNextPage()
	 */
	public IWizardPage getNextPage() {
		saveDataToModel();
		PluginInfoPage page = ((ExportPluginWizard) getWizard()).page2;
		page.onEnterPage(null);
		return page;
	}

}
