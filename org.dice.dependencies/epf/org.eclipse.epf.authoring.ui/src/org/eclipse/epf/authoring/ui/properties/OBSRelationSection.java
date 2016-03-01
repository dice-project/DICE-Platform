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
package org.eclipse.epf.authoring.ui.properties;

import java.util.ArrayList;

import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.filters.DescriptorConfigurationFilter;
import org.eclipse.epf.authoring.ui.filters.ProcessRoleFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The base OBS relation section
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class OBSRelationSection extends AbstractSection {
	protected IStructuredContentProvider contentProvider = null;

	protected ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory());

	private FormToolkit toolkit;

	private Button ctrl_add_1, ctrl_add_2, ctrl_remove;

	private Table ctrl_selected;

	protected TableViewer viewer;

	private String title;
	
	private String desc;
	
	private String table1;

	// element
	protected BreakdownElement element;

	// action manager
	protected IActionManager actionMgr;

	// by default this button is false
	protected boolean showAddFromProcessButton = false;

	// filter
	private IFilter filter = null;

	public IFilter getFilter() {
		if (filter == null) {
			filter = new ProcessRoleFilter(getConfiguration(), null,
					FilterConstants.ROLES);
		} else if (filter instanceof DescriptorConfigurationFilter) {
			((DescriptorConfigurationFilter) filter).setMethodConfiguration(getConfiguration());
		}
		return filter;
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.AbstractSection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {

		super.createControls(parent, tabbedPropertySheetPage);
		init();

		parent.setLayout(new GridLayout());
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));

		// create section
		createSection(parent);

		// add listeners
		addListeners();

		// update controls
		updateControls();

	}

	/**
	 * Initialize
	 */
	protected void init() {
		// get breakdownelement object
		element = (BreakdownElement) getElement();

		// get toolkit
		toolkit = getWidgetFactory();

		// get action manager
		actionMgr = EPFPropertySheetPage.getActionManager();
	}

	protected void setTabData(String title, String desc, String table1) {
		this.title = title;
		this.desc = desc;
		this.table1 = table1;
	}

	private void createSection(Composite parent) {
		// create section
		Section aSection = FormUI.createSection(toolkit, parent,
				title, desc);

		// create general composite
		Composite composite = FormUI.createComposite(toolkit, aSection, 2,
				false);

		// create table composite
		Composite pane1 = FormUI.createComposite(toolkit, composite,
				GridData.FILL_BOTH);
		FormUI.createLabel(toolkit, pane1, table1);

		ctrl_selected = FormUI.createTable(toolkit, pane1);
		viewer = new TableViewer(ctrl_selected);

		initContentProvider();

		viewer.setLabelProvider(labelProvider);
		viewer.setInput(element);

		// create button composite
		Composite pane2 = FormUI.createComposite(toolkit, composite,
				GridData.VERTICAL_ALIGN_CENTER
						| GridData.HORIZONTAL_ALIGN_CENTER);

		// create buttons
		ctrl_add_1 = FormUI.createButton(toolkit, pane2, PropertiesResources.Process_Add); 
		if (showAddFromProcessButton) {
			ctrl_add_2 = FormUI.createButton(toolkit, pane2,
					PropertiesResources.Process_AddFromProcess); 
		}
		ctrl_remove = FormUI.createButton(toolkit, pane2, PropertiesResources.Process_Remove); 

		toolkit.paintBordersFor(composite);
		toolkit.paintBordersFor(pane1);
		toolkit.paintBordersFor(pane2);

	}

	/**
	 * Get Default configuration
	 * 
	 * @return MethodConfiguration
	 */
	protected MethodConfiguration getDefaultConfiguration() {
		ItemProviderAdapter adapter = (ItemProviderAdapter) getAdapter();
		Object parent = null;
		if (adapter instanceof IBSItemProvider) {
			IBSItemProvider bsItemProvider = (IBSItemProvider) adapter;
			parent = bsItemProvider.getTopItem();
			MethodConfiguration config = ((org.eclipse.epf.uma.Process) parent)
					.getDefaultContext();
			return config;
		} else {
			logger
					.logError("OBSRelationSection::getDefaultConfiguration - IBSItemProvider is null"); //$NON-NLS-1$
			return null;
		}
	}

	/**
	 * Add listeners
	 * 
	 */
	private void addListeners() {
		// Add focus listener on primary tasks list
		ctrl_selected.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				if ((selection.size() > 0) && editable)
					ctrl_remove.setEnabled(true);
			}
		});

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				if ((selection.size() > 0) && editable)
					ctrl_remove.setEnabled(true);
			}
		});

		ctrl_add_1.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				openAddDialog();
				viewer.refresh();
			}
		});

		if (showAddFromProcessButton) {
			ctrl_add_2.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					openAddFromProcessDialog();
					viewer.refresh();
				}
			});
		}

		ctrl_remove.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				if (selection.size() > 0) {
					// update the model
					ArrayList rmItems = new ArrayList();
					rmItems.addAll(selection.toList());
					remove(rmItems);
					viewer.refresh();

					// clear the selection
					viewer.setSelection(null, true);
				}
				ctrl_remove.setEnabled(false);
			}
		});
	}

	public void updateControls() {
		// System.out.println("Element "+element.getName() +
		// "Editable-"+editable);
		if (ctrl_add_1 != null)
			ctrl_add_1.setEnabled(editable);
		if (ctrl_add_2 != null)
			ctrl_add_2.setEnabled(editable);

		IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		if (selection.size() > 0 && editable) {
			if (ctrl_remove != null)
			ctrl_remove.setEnabled(true);
		} else {
			if (ctrl_remove != null)
			ctrl_remove.setEnabled(false);
		}

	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	public void refresh() {
		updateControls();

		super.refresh();
	}

	/**
	 * Get parent activity
	 * 
	 * @param element
	 * @return
	 */
	// protected Activity getParentActivity(BreakdownElement element)
	// {
	// Activity activity = null;
	// AdapterFactory aFactory =
	// TngAdapterFactory.INSTANCE.getOBS_ComposedAdapterFactory();
	// ItemProviderAdapter adapter = (ItemProviderAdapter)
	// aFactory.adapt(element, ITreeItemContentProvider.class);
	// Object parent = adapter.getParent(element);
	// if (parent instanceof Activity)
	// {
	// activity = (Activity) parent;
	// }
	//		
	// return activity;
	// }
	
	/**
	 * Initialize content provider
	 */
	protected void initContentProvider() {
	}

	/**
	 * Open UI Dialog to add method elements
	 *
	 */
	protected void openAddDialog() {
	}

	/**
	 * Open UI Dialog to add descriptor elements
	 *
	 */
	protected void openAddFromProcessDialog() {
	}

	// protected void add(Object[] items) {}
	protected void remove(ArrayList rmItems) {
	}

	/**
	 * Dispose
	 *
	 */
	public void disose() {
		super.dispose();
		if (labelProvider != null) {
			labelProvider.dispose();
		}
		if (contentProvider != null) {
			contentProvider.dispose();
		}
	}

}