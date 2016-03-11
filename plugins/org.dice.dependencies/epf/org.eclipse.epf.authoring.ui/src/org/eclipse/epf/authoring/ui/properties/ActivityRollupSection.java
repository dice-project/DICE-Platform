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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.editors.ColumnDescriptor;
import org.eclipse.epf.authoring.ui.providers.ExposedAdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.views.ProcessViewer;
import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.IColumnAware;
import org.eclipse.epf.uma.Activity;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The rollup section for activity
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public abstract class ActivityRollupSection extends AbstractSection {
	// controls
	private FormToolkit toolkit;

	protected Section section;

	private Composite rollupComposite;

	private ProcessViewer viewer;

	// private TabbedPropertySheetPage tabbedPropertySheetPage;

	// element
	private Activity element;

	// action manager
	// protected IActionManager actionMgr;

	protected IPreferenceStoreWrapper store;

	protected ColumnDescriptor[] columnDescriptors;

	protected ComposedAdapterFactory adapterFactory;

	private RollupLabelProvider labelProvider;

	private IContentProvider contentProvider;

	private class RollupLabelProvider extends AdapterFactoryLabelProvider {
		public RollupLabelProvider(AdapterFactory factory) {
			super(factory);
		}

		public String getColumnText(Object object, int columnIndex) {
			ColumnDescriptor columnDesc = columnDescriptors[columnIndex];
			String columnText = super.getColumnText(object, columnIndex);
			if (columnDesc.id == IBSItemProvider.COL_IS_EVENT_DRIVEN
					|| columnDesc.id == IBSItemProvider.COL_IS_ONGOING
					|| columnDesc.id == IBSItemProvider.COL_IS_OPTIONAL
					|| columnDesc.id == IBSItemProvider.COL_IS_PLANNED
					|| columnDesc.id == IBSItemProvider.COL_IS_REPEATABLE
					|| columnDesc.id == IBSItemProvider.COL_HAS_MULTIPLE_OCCURRENCES) {
				if (columnText.trim().equals(new Boolean(true).toString())) {
					return PropertiesResources.true_text;
				} else if (columnText.trim().equals(
						new Boolean(false).toString())) {
					return PropertiesResources.false_text;
				}
			}

			return columnText;
		}
	};
	
	/**
	 * Initialize
	 * 
	 */
	private void init() {
		// get Activity object
		element = (Activity) getElement();

		// get toolkit
		toolkit = getWidgetFactory();

		// get preference store
		store = LibraryPlugin.getDefault().getPreferenceStore();
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

		// create rollup section
		createRollupSection(parent);
	}

	/**
	 * Create rollup section on the composite
	 * @param composite
	 */
	private void createRollupSection(Composite composite) {
		// create section
		section = FormUI
				.createSection(toolkit, composite, PropertiesResources.Activity_WorkRollup, 
						PropertiesResources.Activity_WorkRollupDescription); 

		// create rollup composite
		rollupComposite = FormUI.createComposite(toolkit, section, 2, false);

		// set section labels
		setSectionLabels();

		// create viewer
		viewer = new ProcessViewer(rollupComposite, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.verticalIndent = 10;
		// gridData.heightHint = 600;
		viewer.getControl().setLayoutData(gridData);

		// initAdapterFactory
		initAdapterFactory();

		// set up columns
		viewer.setupColumns(columnDescriptors);
		setColumnIndexToNameMap(adapterFactory, columnDescriptors);
	
		// set up providers
		contentProvider = new ExposedAdapterFactoryContentProvider(adapterFactory);
		labelProvider = new RollupLabelProvider(adapterFactory);
		
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(labelProvider);

		// do roll up
		rollUp();

	}

	private void rollUp() {
		// set up columns
		viewer.setInput(element);
		IBSItemProvider adapter = (IBSItemProvider) adapterFactory.adapt(
				element, ITreeItemContentProvider.class);
		adapter.setRolledUp(true);
		viewer.refresh();
	}

	private static void setColumnIndexToNameMap(AdapterFactory factory,
			ColumnDescriptor[] columnDescriptors) {
		Map columnIndexToNameMap = new HashMap();
		for (int i = 0; i < columnDescriptors.length; i++) {
			columnIndexToNameMap.put(new Integer(i), columnDescriptors[i].id);
		}
		((IColumnAware) factory).setColumnIndexToNameMap(columnIndexToNameMap);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.AbstractSection#dispose()
	 */
	public void dispose() {
		super.dispose();
		if (adapterFactory != null) {
			adapterFactory.dispose();
		}
		if (labelProvider != null) {
			labelProvider.dispose();
		}
		if (contentProvider != null) {
			contentProvider.dispose();
		}
	}


	/**
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	public void refresh() {
		try {
			// get Activity object
			if (getElement() instanceof Activity) {
				element = (Activity) getElement();

				rollUp();
			}
		} catch (Exception ex) {
			logger.logError(
					"Error refreshing activity work roll up section", ex); //$NON-NLS-1$
		}
	}

	/**
	 * Initialize adapter factory
	 *
	 */
	protected abstract void initAdapterFactory();

	/**
	 * Set label for the section
	 *
	 */
	protected abstract void setSectionLabels();

}