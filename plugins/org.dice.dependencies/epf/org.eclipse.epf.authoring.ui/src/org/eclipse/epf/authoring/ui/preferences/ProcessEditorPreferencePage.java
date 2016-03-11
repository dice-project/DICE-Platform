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
package org.eclipse.epf.authoring.ui.preferences;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.authoring.ui.editors.ColumnDescriptor;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.common.preferences.IPreferenceStoreWrapper;
import org.eclipse.epf.common.ui.PreferenceStoreWrapper;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.MethodLibraryPropUtil;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.library.ui.wizards.LibraryBackupUtil;
import org.eclipse.epf.library.util.SynFreeProcessConverter;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.dialogs.ListSelectionDialog;

/**
 * Preference page for ProcessEditor
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @author Jinhua Xi
 * @since 1.0
 */
public class ProcessEditorPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage, SelectionListener, ModifyListener {

	// private Text lineWidthText;
	// private Button indentCheckbox;
	// private Text indentSizeText;
	// private Combo bsCombo;
	private static final String WBS_TXT = AuthoringUIResources.ProcessEditor_WorkBreakdownStructure; 

	private static final String TBS_TXT = AuthoringUIResources.ProcessEditor_TeamAllocation; 

	private static final String WPBS_TXT = AuthoringUIResources.ProcessEditor_WorkProductUsage; 

	private ListViewer selectedColumnListViewer;

	// private ListViewer tbsSelectedColList;
	// private ListViewer wpbsSelectedColList;
	private java.util.List wbsColumnDescriptors;

	private java.util.List tbsColumnDescriptors;

	private java.util.List wpbsColumnDescriptors;

	private Button addButton;

	private Button removeButton;

	private Button downButton;

	private Button upButton;

	private static final String[] BS_NAMES = new String[] { WBS_TXT, TBS_TXT,
			WPBS_TXT };

	private List[] columnDescriptorLists;

	private static final List[] ALL_COLUMN_DESCRIPTOR_LISTS = new List[] {
			ProcessEditor.ALL_WBS_COLUMNS, ProcessEditor.ALL_TBS_COLUMNS,
			ProcessEditor.ALL_WPBS_COLUMNS };

	// configuration switching preference
	private Button configSwitchAlwaysButton;

	private Button configSwitchNeverButton;

	private Button configSwitchPromptButton;

	private Button inheritSuppressionButton;
	
	private Button synFreeButton;
	
	private boolean toConvertToSynFree = false;

	private static class ColumnDescriptorProvider implements
			ITreeContentProvider, ILabelProvider {

		public ColumnDescriptorProvider() {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
		 */
		public Object[] getChildren(Object parentElement) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
		 */
		public Object getParent(Object element) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
		 */
		public boolean hasChildren(Object element) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			return ((java.util.List) inputElement).toArray();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
		 *      java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
		 */
		public Image getImage(Object element) {
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
		 */
		public String getText(Object element) {
			return ((ColumnDescriptor) element).label;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
		 */
		public void addListener(ILabelProviderListener listener) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
		 *      java.lang.String)
		 */
		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
		 */
		public void removeListener(ILabelProviderListener listener) {
		}

	}

	/**
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#doGetPreferenceStore()
	 */
	protected IPreferenceStore doGetPreferenceStore() {
		IPreferenceStoreWrapper wrapper = LibraryPlugin.getDefault().getPreferenceStore();
		if ( wrapper instanceof PreferenceStoreWrapper ) {
			return ((PreferenceStoreWrapper)wrapper).getStore();
		}
		return null;
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	protected Control createContents(Composite parent) {
		initData();

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Composite columnsComposite = new Composite(composite, SWT.NONE);
		columnsComposite.setLayout(new GridLayout(1, false));
		columnsComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label label = new Label(columnsComposite, SWT.NONE);
		label.setText(AuthoringUIResources.columnselection_text); 

		final Combo bsCombo = new Combo(columnsComposite, SWT.READ_ONLY
				| SWT.BORDER);
		bsCombo.setItems(BS_NAMES);
		bsCombo.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				Combo combo = (Combo) e.getSource();
				int id = combo.getSelectionIndex();
				if (id != -1) {
					selectedColumnListViewer
							.setInput(columnDescriptorLists[id]);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});
		bsCombo.select(0);

		Composite listComposite = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		listComposite.setLayout(layout);
		listComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Composite subComposite = new Composite(listComposite, SWT.NONE);
		layout = new GridLayout();
		layout.marginLeft = 0;
		layout.marginWidth = 0;
		subComposite.setLayout(layout);
		subComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		selectedColumnListViewer = new ListViewer(subComposite, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		final ColumnDescriptorProvider itemProvider = new ColumnDescriptorProvider();
		selectedColumnListViewer.setContentProvider(itemProvider);
		selectedColumnListViewer.setLabelProvider(itemProvider);
		selectedColumnListViewer.setInput(wbsColumnDescriptors);
		selectedColumnListViewer.addSelectionChangedListener(new ISelectionChangedListener(){

			public void selectionChanged(SelectionChangedEvent event) {
				List removeList = ((IStructuredSelection) selectedColumnListViewer
						.getSelection()).toList();
				if (removeList.contains(ProcessEditor.COL_DESC_ID)
						|| removeList
								.contains(ProcessEditor.COL_DESC_PRESENTATION_NAME)) {
					// if present name or id selected, 
					// disable "remove","up" and "down" button
					//
					if(removeButton.isEnabled()) removeButton.setEnabled(false);
					if(upButton.isEnabled()) upButton.setEnabled(false);
					if(downButton.isEnabled()) downButton.setEnabled(false);
				} else {
					if(!removeButton.isEnabled()) removeButton.setEnabled(true);
					if(!upButton.isEnabled()) upButton.setEnabled(true);
					if(!downButton.isEnabled()) downButton.setEnabled(true);
				}
			}
			
		});
		
	
		selectedColumnListViewer.getControl().setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// wbsSelectedColumnsTable.setItems(new String[] {
		// IBSItemProvider.COL_ID,
		// IBSItemProvider.COL_IS_EVENT_DRIVEN,
		// IBSItemProvider.COL_IS_ONGOING,
		// IBSItemProvider.COL_IS_REPEATABLE,
		// IBSItemProvider.COL_MODEL_INFO,
		// IBSItemProvider.COL_NAME,
		// IBSItemProvider.COL_PREDECESSORS,
		// IBSItemProvider.COL_PREFIX,
		// IBSItemProvider.COL_TYPE,
		// });

		// CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
		// tabItem.setText("Work Breakdown Structure");
		// tabItem.setControl(wbsSelectedColumnsTable.getControl());

		// tbsSelectedColList = new ListViewer(columnsComposite, SWT.CHECK |
		// SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		// tbsSelectedColList.setContentProvider(itemProvider);
		// tbsSelectedColList.setLabelProvider(itemProvider);
		// tbsSelectedColList.setInput(tbsColumnDescriptors);
		// tbsSelectedColList.setItems(new String[] {
		// IBSItemProvider.COL_ID,
		// IBSItemProvider.COL_MODEL_INFO,
		// IBSItemProvider.COL_NAME,
		// IBSItemProvider.COL_PREFIX,
		// IBSItemProvider.COL_TYPE,
		// });
		// tabItem = new CTabItem(tabFolder, SWT.NONE);
		// tabItem.setText("Team Breakdown Structure");
		// tabItem.setControl(tbsSelectedColList.getControl());

		// wpbsSelectedColList = new ListViewer(columnsComposite, SWT.CHECK |
		// SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		// wpbsSelectedColList.setContentProvider(itemProvider);
		// wpbsSelectedColList.setLabelProvider(itemProvider);
		// wpbsSelectedColList.setInput(wpbsColumnDescriptors);
		// tabItem = new CTabItem(tabFolder, SWT.NONE);
		// tabItem.setText("Work Product Breakdown Structure");
		// tabItem.setControl(wpbsSelectedColList.getControl());

		Composite buttonsComposite = new Composite(listComposite, SWT.NONE);
		buttonsComposite.setLayout(new GridLayout());
		GridData gridData = new GridData(GridData.GRAB_HORIZONTAL);
		gridData.widthHint = 100;
		buttonsComposite.setLayoutData(gridData);

		GridData layoutData = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL);

		addButton = new Button(buttonsComposite, SWT.NONE);
		addButton.setText(AuthoringUIText.ADD_BUTTON_TEXT);
		addButton.setLayoutData(layoutData);
		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				int id = bsCombo.getSelectionIndex();
				Object input = ALL_COLUMN_DESCRIPTOR_LISTS[id];
				final List selectedColumns = columnDescriptorLists[id];
				ListSelectionDialog dlg = new ListSelectionDialog(
						Display.getCurrent().getActiveShell(),
						input,
						itemProvider,
						itemProvider,
						AuthoringUIResources.selectColumnstoAdd_text) { 
					protected Control createDialogArea(Composite parent) {
						Control control = super.createDialogArea(parent);
						getViewer().addFilter(new ViewerFilter() {

							public boolean select(Viewer viewer,
									Object parentElement, Object element) {
								return !selectedColumns.contains(element);
							}

						});
						return control;
					}

				};
				if (dlg.open() == Window.OK) {
					selectedColumns.addAll(Arrays.asList(dlg.getResult()));
					selectedColumnListViewer.refresh();
				}

			}
		});

		removeButton = new Button(buttonsComposite, SWT.NONE);
		removeButton.setText(AuthoringUIResources.removeButton_text); 
		removeButton.setLayoutData(layoutData);
		removeButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				java.util.List columns = (java.util.List) selectedColumnListViewer
						.getInput();
				List removeList = ((IStructuredSelection) selectedColumnListViewer
						.getSelection()).toList();
				if (removeList.contains(ProcessEditor.COL_DESC_ID)
						|| removeList
								.contains(ProcessEditor.COL_DESC_PRESENTATION_NAME)) {
					// cannot remove name or ID column
					//
					return;
				}
				columns.removeAll(removeList);
				selectedColumnListViewer.refresh();
			}

		});

		upButton = new Button(buttonsComposite, SWT.NONE);
		upButton.setText(AuthoringUIText.UP_BUTTON_TEXT);
		upButton.setLayoutData(layoutData);
		upButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) selectedColumnListViewer
						.getSelection();
				if (selection.size() > 1)
					return;
				EList columns = (EList) selectedColumnListViewer.getInput();
				Object selected = selection.getFirstElement();
				int id = columns.indexOf(selected);
				if (bsCombo.getSelectionIndex() == 0 && id < 3)
					return;
				if (bsCombo.getSelectionIndex() > 0 && id < 2)
					return;
				columns.move(id - 1, id);
				selectedColumnListViewer.refresh();
				selectedColumnListViewer.setSelection(new StructuredSelection(
						selected), true);
			}

		});

		downButton = new Button(buttonsComposite, SWT.NONE);
		downButton.setText(AuthoringUIText.DOWN_BUTTON_TEXT);
		downButton.setLayoutData(layoutData);
		downButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) selectedColumnListViewer
						.getSelection();
				if (selection.size() > 1)
					return;
				EList columns = (EList) selectedColumnListViewer.getInput();
				Object selected = selection.getFirstElement();
				int id = columns.indexOf(selected);
				
				if (id == columns.size() - 1 || (bsCombo.getSelectionIndex() == 0 && id < 2))
					return;
				if (id == columns.size() - 1 || (bsCombo.getSelectionIndex() > 0 && id < 1))
					return;
				columns.move(id + 1, id);
				selectedColumnListViewer.refresh();
				selectedColumnListViewer.setSelection(new StructuredSelection(
						selected), true);
			}

		});

		Group configSwitchGroup = new Group(composite, SWT.SHADOW_OUT);
		configSwitchGroup.setLayout(new GridLayout(3, false));
		configSwitchGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		configSwitchGroup
				.setText(AuthoringUIResources.ProcessEditorPreferencePage_switchConfiguration); 

		configSwitchAlwaysButton = new Button(configSwitchGroup, SWT.RADIO);
		configSwitchAlwaysButton.setText(LibraryUIResources.alwaysButton_text);
		configSwitchNeverButton = new Button(configSwitchGroup, SWT.RADIO);
		configSwitchNeverButton.setText(LibraryUIResources.neverButton_text);
		configSwitchPromptButton = new Button(configSwitchGroup, SWT.RADIO);
		configSwitchPromptButton.setText(LibraryUIResources.promptButton_text);

		setSwitchConfigButton(LibraryUIPreferences.getSwitchConfig());

		// inherit suppression states
		Group suppressionGroup = new Group(composite, SWT.SHADOW_OUT);
		suppressionGroup.setLayout(new GridLayout());
		suppressionGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		suppressionGroup
				.setText(AuthoringUIResources.ProcessEditorPreferencePage_suppressionGroupTitle); 

		inheritSuppressionButton = new Button(suppressionGroup, SWT.CHECK);
		inheritSuppressionButton
				.setText(AuthoringUIResources.ProcessEditorPreferencePage_inheritSuppressionState); 

		inheritSuppressionButton.setSelection(getPreferenceStore().getBoolean(
				ApplicationPreferenceConstants.PREF_INHERIT_SUPPRESSION_STATE));
		
		// synFreeButton states
		Group synFreeGroup = new Group(composite, SWT.SHADOW_OUT);
		synFreeGroup.setLayout(new GridLayout());
		synFreeGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synFreeGroup
				.setText(AuthoringUIResources.ProcessEditorPreferencePage_synchronizationGroupTitle); 

		synFreeButton = new Button(synFreeGroup, SWT.CHECK);
		
		synFreeButton
				.setText(AuthoringUIResources.ProcessEditorPreferencePage_synchronizationFree); 

		boolean isSynFree = getPreferenceStore().getBoolean(
				ApplicationPreferenceConstants.PREF_SYN_FREE);
		
		MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
		if (lib != null) {
			if (MethodLibraryPropUtil.getMethodLibraryPropUtil().isSynFree(lib)) {
				isSynFree = true;
				synFreeButton.setEnabled(false);
				
			} else {
				isSynFree = false;
				
			}
		}				
		setSynFreeButtonSelection(isSynFree);
		
		synFreeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				toConvertToSynFree = false;

				if (synFreeButton.getSelection()) {
					MethodLibrary lib = LibraryService.getInstance()
							.getCurrentMethodLibrary();

					if (lib != null
							&& !MethodLibraryPropUtil
									.getMethodLibraryPropUtil().isSynFree(lib)) {
						toConvertToSynFree = AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayConfirmation(
										AuthoringUIResources.ProcessEditorPreferencePage_conversionDialogTitle,
										AuthoringUIResources.ProcessEditorPreferencePage_conversionDialogText);
						if (! toConvertToSynFree) {
							synFreeButton.setSelection(false);
							synFreeButton.setEnabled(true);
							return;
						}

					}
				}
			}
		});
		
		return composite;
	}
	
	private void setSynFreeButtonSelection(boolean selected) {
		synFreeButton.setSelection(selected);
	}

	/**
	 * Loads data from preference store
	 */
	private void initData() {
		IPreferenceStore store = getPreferenceStore();

		String str = store
				.getString(ApplicationPreferenceConstants.PREF_WBS_COLUMNS);
		if (str == null) {
			str = store
					.getDefaultString(ApplicationPreferenceConstants.PREF_WBS_COLUMNS);
		}
		wbsColumnDescriptors = ProcessEditor.toColumnDescriptorList(str);

		str = store.getString(ApplicationPreferenceConstants.PREF_TBS_COLUMNS);
		if (str == null) {
			str = store
					.getDefaultString(ApplicationPreferenceConstants.PREF_TBS_COLUMNS);
		}
		tbsColumnDescriptors = ProcessEditor.toColumnDescriptorList(str);

		str = store.getString(ApplicationPreferenceConstants.PREF_WPBS_COLUMNS);
		if (str == null) {
			str = store
					.getDefaultString(ApplicationPreferenceConstants.PREF_WPBS_COLUMNS);
		}
		wpbsColumnDescriptors = ProcessEditor.toColumnDescriptorList(str);

		// load and set to the default if nothing in the store
		// 154758 - Processes: WBS is blank as default preference is not set for the first time
		if ( wbsColumnDescriptors.size() == 0 ) {
			wbsColumnDescriptors.addAll(ProcessEditor.DEFAULT_WBS_COLUMNS);
			store.setValue(ApplicationPreferenceConstants.PREF_WBS_COLUMNS,
					toString(wbsColumnDescriptors));
		
		}
		
		if ( tbsColumnDescriptors.size() == 0 ) {
			tbsColumnDescriptors.addAll(ProcessEditor.DEFAULT_TBS_COLUMNS);
			store.setValue(ApplicationPreferenceConstants.PREF_TBS_COLUMNS,
					toString(tbsColumnDescriptors));
		}
		
		if ( wpbsColumnDescriptors.size() == 0 ) {
			wpbsColumnDescriptors.addAll(ProcessEditor.DEFAULT_WPBS_COLUMNS);
			store.setValue(ApplicationPreferenceConstants.PREF_WPBS_COLUMNS,
					toString(wpbsColumnDescriptors));
		}
		
		columnDescriptorLists = new List[] { wbsColumnDescriptors,
				tbsColumnDescriptors, wpbsColumnDescriptors };
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage.performDefaults
	 */
	protected void performDefaults() {
		super.performDefaults();

		wbsColumnDescriptors.clear();
		wbsColumnDescriptors.addAll(ProcessEditor.DEFAULT_WBS_COLUMNS);

		tbsColumnDescriptors.clear();
		tbsColumnDescriptors.addAll(ProcessEditor.DEFAULT_TBS_COLUMNS);

		wpbsColumnDescriptors.clear();
		wpbsColumnDescriptors.addAll(ProcessEditor.DEFAULT_WPBS_COLUMNS);

		inheritSuppressionButton
				.setSelection(getPreferenceStore()
						.getDefaultBoolean(
								ApplicationPreferenceConstants.PREF_INHERIT_SUPPRESSION_STATE));
		
//		setSynFreeButtonSelection(getPreferenceStore().getDefaultBoolean(
//				ApplicationPreferenceConstants.PREF_SYN_FREE));
				
		setSwitchConfigButton(MessageDialogWithToggle.PROMPT);
	}

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#performOk()
	 */
	public boolean performOk() {
		IPreferenceStore store = getPreferenceStore();

		store.setValue(ApplicationPreferenceConstants.PREF_WBS_COLUMNS,
				toString(wbsColumnDescriptors));
		store.setValue(ApplicationPreferenceConstants.PREF_TBS_COLUMNS,
				toString(tbsColumnDescriptors));
		store.setValue(ApplicationPreferenceConstants.PREF_WPBS_COLUMNS,
				toString(wpbsColumnDescriptors));
		store.setValue(
				ApplicationPreferenceConstants.PREF_INHERIT_SUPPRESSION_STATE,
				inheritSuppressionButton.getSelection());
		store.setValue(
				ApplicationPreferenceConstants.PREF_SYN_FREE,
				synFreeButton.getSelection());
		// switch config preference is in library.ui
		LibraryUIPreferences.setSwitchConfig(getSwitchConfigValue());

		if (toConvertToSynFree) {
			synFreeButton.setEnabled(false);
			toConvertToSynFree = false;

			EditorChooser.getInstance().closeAllMethodEditorsWithSaving();
			final MethodLibrary lib = LibraryService.getInstance()
					.getCurrentMethodLibrary();
			LibraryBackupUtil.promptBackupCurrentLibrary(Display.getCurrent()
					.getActiveShell(), LibraryService.getInstance());

			IRunnableWithProgress op = new IRunnableWithProgress() {
				public void run(IProgressMonitor monitor)
						throws InvocationTargetException {
					try {
						monitor
								.beginTask(
										AuthoringUIResources.ProcessEditorPreferencePage_conversionProgressText,
										IProgressMonitor.UNKNOWN);

						SynFreeProcessConverter converter = new SynFreeProcessConverter();
						converter.convertLibrary(lib);

					} catch (Exception e) {						
						throw new InvocationTargetException(e);
					} finally {
						monitor.done();
					}
				}
			};

			try {
				new ProgressMonitorDialog(Display.getCurrent().getActiveShell())
						.run(true, false, op);
			} catch (Exception e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
				AuthoringUIPlugin
						.getDefault()
						.getMsgDialog()
						.displayError(AuthoringUIResources.ProcessEditorPreferencePage_conversionDialogTitle,
								AuthoringUIResources.ProcessEditorPreferencePage_conversionProgressFailText);
			}

		}
		
		return true;
	}

	/**
	 * Converts given list into string representation
	 * @param columnDescriptors
	 * @return 
	 * 			String representation of the given list
	 */
	public static String toString(java.util.List columnDescriptors) {
		StringBuffer strBuf = new StringBuffer();
		int max = columnDescriptors.size() - 1;
		for (int i = 0; i < max; i++) {
			strBuf.append(((ColumnDescriptor) columnDescriptors.get(i)).id)
					.append(',');
		}
		strBuf.append(((ColumnDescriptor) columnDescriptors.get(max)).id);
		return strBuf.toString();
	}

	/**
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
	}

	/**
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	/**
	 * @see org.eclipse.swt.events.ModifyListener#modifyText(ModifyEvent)
	 */
	public void modifyText(ModifyEvent e) {
	}

	private String getSwitchConfigValue() {
		if (configSwitchAlwaysButton.getSelection())
			return MessageDialogWithToggle.ALWAYS;
		if (configSwitchNeverButton.getSelection())
			return MessageDialogWithToggle.NEVER;
		if (configSwitchPromptButton.getSelection())
			return MessageDialogWithToggle.PROMPT;

		// return Prompt value as a default
		return MessageDialogWithToggle.PROMPT;
	}

	private void setSwitchConfigButton(String value) {
		if (MessageDialogWithToggle.ALWAYS.equals(value)) {
			configSwitchAlwaysButton.setSelection(true);
			configSwitchNeverButton.setSelection(false);
			configSwitchPromptButton.setSelection(false);
		} else if (MessageDialogWithToggle.NEVER.equals(value)) {
			configSwitchAlwaysButton.setSelection(false);
			configSwitchNeverButton.setSelection(true);
			configSwitchPromptButton.setSelection(false);
		} else { // MessageDialogWithToggle.PROMPT
			configSwitchAlwaysButton.setSelection(false);
			configSwitchNeverButton.setSelection(false);
			configSwitchPromptButton.setSelection(true);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#getPreferenceStore()
	 */
	public IPreferenceStore getPreferenceStore() {
		
		// change to use LibraryPlugin store
		// we need to share the preference in Library browsing and publishing
		// Jinhua Xi 08/19/2006
		// changed to use CommonUIPlugin store
		IPreferenceStoreWrapper wrapper = LibraryPlugin.getDefault().getPreferenceStore();
		if ( wrapper instanceof PreferenceStoreWrapper ) {
			return ((PreferenceStoreWrapper)wrapper).getStore();
		}
		return null;
	}
}
