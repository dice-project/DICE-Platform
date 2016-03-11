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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.DefaultElementRealizer;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The base class used to display relationships between descriptors
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class RelationSection extends AbstractSection {
//	protected ILabelProvider labelProvider = null;

	protected IStructuredContentProvider contentProvider = null;

	// element
	protected BreakdownElement element;

	// action manager
	protected IActionManager actionMgr;

	protected FormToolkit toolkit;

	protected Button ctrl_add_1, ctrl_add_2, ctrl_add_3, ctrl_add_4;

	protected Button ctrl_remove_1, ctrl_remove_2, ctrl_remove_3, ctrl_remove_4;

	private Button ctrl_add_proc_1, ctrl_add_proc_2, ctrl_add_proc_3,
			ctrl_add_proc_4;;

	private Table ctrl_table_1, ctrl_table_2, ctrl_table_3, ctrl_table_4;

	protected TableViewer tableViewer1, tableViewer2, tableViewer3,
			tableViewer4;

	protected Text ctrl_brief_desc;

	protected String tabString;
	
	private String title, desc, table1, table2, table3, table4;

	private int tableCount;

	private boolean[] changesAllowed;

	Process process;

	IFilter descriptorProcessfilter;
	
	protected DescriptorPropUtil propUtil = DescriptorPropUtil.getDesciptorPropUtil();
	
	protected Process getProcess() {
		return process;
	}

	protected IFilter getFilterForDescriptors() {
		return descriptorProcessfilter;
	}

	protected String getDescriptorTabName() {
		return null;
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

	protected void setTabData(String title, String desc, String table1, String table2, String table3, String table4, String tabString) {
		this.title = title;
		this.desc = desc;
		this.table1 = table1;
		this.table2 = table2;
		this.table3 = table3;
		this.table4 = table4;
		
		this.tabString = tabString;
	}

	protected void setTableData(int tableCount, boolean[] changesAllowed) {
		this.tableCount = tableCount;
		this.changesAllowed = changesAllowed;
	}

	protected void init() {
		// get descriptor object
		element = (BreakdownElement) getElement();

		// get toolkit
		toolkit = getWidgetFactory();

		// get action manager
		actionMgr = EPFPropertySheetPage.getActionManager();
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof BreakdownElement) {
				element = (BreakdownElement) getElement();

				initContentProvider1();
				initContentProvider2();
				initContentProvider3();
				initContentProvider4();
				
				initLabelProvider1();
				initLabelProvider2();
				initLabelProvider3();
				initLabelProvider4();

				// hide/show controls based on editable
				updateControls();
			}
		} catch (Exception ex) {
			logger
					.logError(
							"Error refreshing descriptor relation section: " + element, ex); //$NON-NLS-1$
		}
	}

	/**
	 * Update controls based on editable boolean value. Controls could be
	 * editable or read-only after this.
	 */
	public void updateControls() {
		// System.out.println("Element "+element.getName() +
		// "Editable-"+editable);
		if (ctrl_add_1 != null)
			ctrl_add_1.setEnabled(editable);
		if (ctrl_add_2 != null)
			ctrl_add_2.setEnabled(editable);
		if (ctrl_add_3 != null)
			ctrl_add_3.setEnabled(editable);
		if (ctrl_add_4 != null)
			ctrl_add_4.setEnabled(editable);

		if (ctrl_add_proc_1 != null)
			ctrl_add_proc_1.setEnabled(editable);
		if (ctrl_add_proc_2 != null)
			ctrl_add_proc_2.setEnabled(editable);
		if (ctrl_add_proc_3 != null)
			ctrl_add_proc_3.setEnabled(editable);
		if (ctrl_add_proc_4 != null)
			ctrl_add_proc_4.setEnabled(editable);

		IStructuredSelection selection = (IStructuredSelection) tableViewer1
				.getSelection();
		if (selection.size() > 0 && editable) {
			ctrl_remove_1.setEnabled(true);
		} else {
			ctrl_remove_1.setEnabled(false);
		}

		if (tableViewer2 != null) {
			selection = (IStructuredSelection) tableViewer2.getSelection();
			if (selection.size() > 0 && editable) {
					if (ctrl_remove_2 != null)
				ctrl_remove_2.setEnabled(true);
			} else {
					if (ctrl_remove_2 != null)
				ctrl_remove_2.setEnabled(false);
			}
		}

		if (tableViewer3 != null) {
			selection = (IStructuredSelection) tableViewer3.getSelection();
			if (selection.size() > 0 && editable) {
				ctrl_remove_3.setEnabled(true);
			} else {
				ctrl_remove_3.setEnabled(false);
			}
		}

		if (tableViewer4 != null) {
			selection = (IStructuredSelection) tableViewer4.getSelection();
			if (selection.size() > 0 && editable) {
				ctrl_remove_4.setEnabled(true);
			} else {
				ctrl_remove_4.setEnabled(false);
			}
		}
	}

	/**
	 * Dispose all label and content providers
	 */
	public void dispose() {
		super.dispose();
//		if (labelProvider != null) {
//			labelProvider.dispose();
//		}
		if (contentProvider != null) {
			contentProvider.dispose();
		}
	}

	/**
	 * Initialize content provider for the first table in properties view
	 *
	 */
	protected void initContentProvider1() {
	}

	/**
	 * Initialize content provider for the second table in properties view
	 *
	 */
	protected void initContentProvider2() {
	}

	/**
	 * Initialize content provider for the third table in properties view
	 *
	 */
	protected void initContentProvider3() {
	}

	/**
	 * Initialize content provider for the fourth table in properties view
	 *
	 */
	protected void initContentProvider4() {
	}
	
	protected void initLabelProvider1() {
		
	}
	
	protected void initLabelProvider2() {
		
	}

	protected void initLabelProvider3() {
		
	}
	
	protected void initLabelProvider4() {
		
	}

	/**
	 * Get list of descriptors from the process
	 * @return
	 * 			List of descriptors
	 */
	protected List getDescriptorsFromProcess() {
		return null;
	}

	/**
	 * Returns filter which allows filtering element based on method configuration 
	 * @return
	 * 		Filter
	 */
	protected IFilter getFilter() {
		return null;
	}

	protected void createSection(Composite composite) {
		int tableHeight = 80;

		// create section
		if (isSyncFree()) {
			desc = desc + " " + PropertiesResources.Process_SyncFree_FontStyle;  //$NON-NLS-1$
		}		
		Section aSection = FormUI.createSection(toolkit, composite,
				title, desc);

		// create composite
		Composite sectionComposite = FormUI.createComposite(toolkit, aSection,
				2, false);

		// create table 1
		int count = 0;
		{
			Composite pane1 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.FILL_BOTH);
			FormUI.createLabel(toolkit, pane1, table1);

			ctrl_table_1 = FormUI.createTable(toolkit, pane1, tableHeight);
			tableViewer1 = new TableViewer(ctrl_table_1);
			initContentProvider1();
			initLabelProvider1();			
			tableViewer1.setInput(element);

			// create buttons for table1
			Composite pane2 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.VERTICAL_ALIGN_CENTER
							| GridData.HORIZONTAL_ALIGN_CENTER);

			if (changesAllowed[count]) {
				ctrl_add_1 = FormUI.createButton(toolkit, pane2,
						PropertiesResources.Process_Add); 
				ctrl_add_proc_1 = FormUI
						.createButton(toolkit, pane2, PropertiesResources.Process_AddFromProcess); 
				ctrl_remove_1 = FormUI.createButton(toolkit, pane2,
						PropertiesResources.Process_Remove); 
				ctrl_remove_1.setEnabled(false);
				
				createAddtionalButton1(pane2);
			}
			toolkit.paintBordersFor(pane1);
		}
		count++;

		// create table2
		if (count < tableCount) {
			Composite pane3 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.FILL_BOTH);
			FormUI.createLabel(toolkit, pane3, table2);

			ctrl_table_2 = FormUI.createTable(toolkit, pane3, tableHeight);
			tableViewer2 = new TableViewer(ctrl_table_2);
			initContentProvider2();
			initLabelProvider2();
			tableViewer2.setInput(element);

			// create buttons for table2
			Composite pane4 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.VERTICAL_ALIGN_CENTER
							| GridData.HORIZONTAL_ALIGN_CENTER);
			if (changesAllowed[count]) {
				ctrl_add_2 = FormUI.createButton(toolkit, pane4,
						PropertiesResources.Process_Add); 
				ctrl_add_proc_2 = FormUI
						.createButton(toolkit, pane4, PropertiesResources.Process_AddFromProcess); 
				ctrl_remove_2 = FormUI.createButton(toolkit, pane4,
						PropertiesResources.Process_Remove); 
				ctrl_remove_2.setEnabled(false);
				
				createAddtionalButton2(pane4);
			}
			toolkit.paintBordersFor(pane3);
		}

		count++;

		// Create table3
		if (count < tableCount) {
			Composite pane5 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.FILL_BOTH);
			FormUI.createLabel(toolkit, pane5, table3);

			ctrl_table_3 = FormUI.createTable(toolkit, pane5, tableHeight);
			tableViewer3 = new TableViewer(ctrl_table_3);
			initContentProvider3();
			initLabelProvider3();
			tableViewer3.setInput(element);

			// create buttons for table2
			Composite pane6 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.VERTICAL_ALIGN_CENTER
							| GridData.HORIZONTAL_ALIGN_CENTER);
			if ((new Boolean(changesAllowed[count])).booleanValue()) {
				ctrl_add_3 = FormUI.createButton(toolkit, pane6,
						PropertiesResources.Process_Add); 
				ctrl_add_proc_3 = FormUI
						.createButton(toolkit, pane6, PropertiesResources.Process_AddFromProcess); 
				ctrl_remove_3 = FormUI.createButton(toolkit, pane6,
						PropertiesResources.Process_Remove); 
				ctrl_remove_3.setEnabled(false);
				
				createAddtionalButton3(pane6);
			}
			toolkit.paintBordersFor(pane5);
		}
		count++;

		if (count < tableCount) {
			Composite pane7 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.FILL_BOTH);
			FormUI.createLabel(toolkit, pane7, table4);

			ctrl_table_4 = FormUI.createTable(toolkit, pane7, tableHeight);
			tableViewer4 = new TableViewer(ctrl_table_4);
			initContentProvider4();
			initLabelProvider4();
			tableViewer4.setInput(element);

			// create buttons for table4
			Composite pane8 = FormUI.createComposite(toolkit, sectionComposite,
					GridData.VERTICAL_ALIGN_CENTER
							| GridData.HORIZONTAL_ALIGN_CENTER);
			if ((new Boolean(changesAllowed[count])).booleanValue()) {
				ctrl_add_4 = FormUI.createButton(toolkit, pane8,
						PropertiesResources.Process_Add); 
				ctrl_add_proc_4 = FormUI
						.createButton(toolkit, pane8, PropertiesResources.Process_AddFromProcess); 
				ctrl_remove_4 = FormUI.createButton(toolkit, pane8,
						PropertiesResources.Process_Remove); 
				ctrl_remove_4.setEnabled(false);
				
				createAddtionalButton4(pane8);
			}
			toolkit.paintBordersFor(pane8);

		}

		// create brief description
		int heightHint = 40;
		// int horizontalSpan = 2;
		FormUI.createLabel(toolkit, sectionComposite, PropertiesResources.Process_briefDescription, heightHint); 
		ctrl_brief_desc = FormUI.createText(toolkit, sectionComposite,
				heightHint, heightHint);
		ctrl_brief_desc.setEditable(false);

		toolkit.paintBordersFor(sectionComposite);
	}

	/**
	 * addListerers
	 * 
	 */
	protected void addListeners() {
		/**
		 * First set of listeners
		 */
		int count = 0;
		if (count < tableCount) {
			ctrl_table_1.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					IStructuredSelection selection = (IStructuredSelection) tableViewer1
							.getSelection();
					if ((selection.size() > 0) && editable)
						ctrl_remove_1.setEnabled(true);
				}
			});

			tableViewer1
					.addSelectionChangedListener(new ISelectionChangedListener() {
						public void selectionChanged(SelectionChangedEvent event) {
							IStructuredSelection selection = (IStructuredSelection) tableViewer1
									.getSelection();
							if ((selection.size() > 0) & editable) {
								if (isSyncFree()) {
									syncFreeUpdateBtnStatus1(selection);
								} else {
									ctrl_remove_1.setEnabled(true);
								}
							}
							
							if (selection.size() == 1) {
								String desc = ((MethodElement) selection
										.getFirstElement())
										.getBriefDescription();
								if (desc == null) {
									desc = ""; //$NON-NLS-1$
								}
								ctrl_brief_desc.setText(desc);
							} else if (selection.size() > 1) {
								ctrl_brief_desc
										.setText(PropertiesResources.Process_MultipleSelection + " - " + selection.size()); //$NON-NLS-1$ 
							}
						}
					});

			if (changesAllowed[count]) {
				ctrl_add_1.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						if (isSyncFree()) {
							IStructuredSelection selection = (IStructuredSelection) tableViewer1.getSelection();
							if (syncFreeAdd1(selection)) { 
								tableViewer1.refresh();
								return;
							}
						}
						
						IFilter filter = getFilter();
						List existingElements = null;
						if (isSyncFree()) {
							existingElements = getExistingContentElements1();
						} else {
							existingElements = ProcessUtil.getAssociatedElementList(getExistingElements1());
						}
						ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), filter, element, tabString,
								existingElements);

						fd.setBlockOnOpen(true);
						fd.setTitle(tabString);
						fd.setEnableProcessScope(true);
						fd.setSection(getSection());
						fd.open();
						addItems1(fd.getSelectedItems());
						tableViewer1.refresh();
					}
					
				});

				ctrl_add_proc_1.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {						
						descriptorProcessfilter = getFilterForDescriptors();
						process = getProcess();
						if (descriptorProcessfilter != null && process != null) {
							List existingElements = getExistingElements1();
							ItemsFilterDialog fd = new ItemsFilterDialog(
									PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow()
											.getShell(),
									descriptorProcessfilter, process,
									getDescriptorTabName(), existingElements);

							fd.setBlockOnOpen(true);
							fd.open();
							addFromProcessItems1(fd.getSelectedItems());
							tableViewer1.refresh();
						}						
					}
				});

				ctrl_remove_1.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						if (isSyncFree()) {
							IStructuredSelection selection = (IStructuredSelection) tableViewer1.getSelection();
							if (syncFreeRemove1(selection)) {
								tableViewer1.refresh();
								ctrl_remove_1.setEnabled(false);
								return;
							}							
						} 
						
						IStructuredSelection selection = (IStructuredSelection) tableViewer1
								.getSelection();
						if (selection.size() > 0) {
							// update the model
							ArrayList rmItems = new ArrayList();
							rmItems.addAll(selection.toList());
							removeItems1(rmItems);
							tableViewer1.refresh();

							// clear the selection
							tableViewer1.setSelection(null, true);
							ctrl_brief_desc.setText(""); //$NON-NLS-1$
						}
						ctrl_remove_1.setEnabled(false);
					}
				
				});
			}
		}
		count++;

		/**
		 * Second set of listeners
		 */
		if (count < tableCount) {
			ctrl_table_2.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					IStructuredSelection selection = (IStructuredSelection) tableViewer2
							.getSelection();
					if ((selection.size() > 0) && editable) {
						if (ctrl_remove_2 != null)
							ctrl_remove_2.setEnabled(true);
					}
				}
			});

			tableViewer2
					.addSelectionChangedListener(new ISelectionChangedListener() {
						public void selectionChanged(SelectionChangedEvent event) {
							IStructuredSelection selection = (IStructuredSelection) tableViewer2
									.getSelection();
							if ((selection.size() > 0) && editable) {
								if (isSyncFree()) {
									syncFreeUpdateBtnStatus2(selection);
								} else {
									ctrl_remove_2.setEnabled(true);
								}
							}
							if (selection.size() == 1) {
								String desc = ((MethodElement) selection
										.getFirstElement())
										.getBriefDescription();
								if (desc == null) {
									desc = ""; //$NON-NLS-1$
								}
								ctrl_brief_desc.setText(desc);
							} else if (selection.size() > 1) {
								ctrl_brief_desc
										.setText(PropertiesResources.Process_MultipleSelection + " - " + selection.size()); //$NON-NLS-1$ 
							}
						}
					});

			if (changesAllowed[count]) {
				ctrl_add_2.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						if (isSyncFree()) {
							IStructuredSelection selection = (IStructuredSelection) tableViewer2.getSelection();
							if (syncFreeAdd2(selection)) {								 	
								tableViewer2.refresh();
								return;
							}
						} 
						
						IFilter filter = getFilter();
						List existingElements = null;
						if (isSyncFree()) {
							existingElements = getExistingContentElements2();
						} else {
							existingElements = ProcessUtil.getAssociatedElementList(getExistingElements2());
						}
						ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), filter, element, tabString,
								existingElements);

						fd.setBlockOnOpen(true);
						fd.setTitle(tabString);
						fd.setEnableProcessScope(true);
						fd.setSection(getSection());
						fd.open();
						addItems2(fd.getSelectedItems());
						tableViewer2.refresh();
					}
				});

				ctrl_add_proc_2.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						descriptorProcessfilter = getFilterForDescriptors();
						process = getProcess();
						if (descriptorProcessfilter != null && process != null) {
							List existingElements = getExistingElements2();
							ItemsFilterDialog fd = new ItemsFilterDialog(
									PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow()
											.getShell(),
									descriptorProcessfilter, process,
									getDescriptorTabName(), existingElements);

							fd.setBlockOnOpen(true);
							fd.open();
							addFromProcessItems2(fd.getSelectedItems());
							tableViewer2.refresh();
						}
					}
				});

				ctrl_remove_2.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						if (isSyncFree()) {
							IStructuredSelection selection = (IStructuredSelection) tableViewer2.getSelection();
							if (syncFreeRemove2(selection)) {
								tableViewer2.refresh();
								ctrl_remove_2.setEnabled(false);
								return;
							}							
						}						
						
						IStructuredSelection selection = (IStructuredSelection) tableViewer2
								.getSelection();
						if (selection.size() > 0) {
							// update the model
							ArrayList rmItems = new ArrayList();
							rmItems.addAll(selection.toList());
							removeItems2(rmItems);
							tableViewer2.refresh();

							// clear the selection
							tableViewer2.setSelection(null, true);
							ctrl_brief_desc.setText(""); //$NON-NLS-1$
						}
						ctrl_remove_2.setEnabled(false);
					}
				});
			}
		}
		count++;

		/**
		 * Third set of listeners
		 */
		if (count < tableCount) {
			ctrl_table_3.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					IStructuredSelection selection = (IStructuredSelection) tableViewer3
							.getSelection();
					if ((selection.size() > 0) && editable)
						ctrl_remove_3.setEnabled(true);
				}
			});

			tableViewer3
					.addSelectionChangedListener(new ISelectionChangedListener() {
						public void selectionChanged(SelectionChangedEvent event) {
							IStructuredSelection selection = (IStructuredSelection) tableViewer3
									.getSelection();
							if ((selection.size() > 0) && editable)
								ctrl_remove_3.setEnabled(true);
							if (selection.size() == 1) {
								String desc = ((MethodElement) selection
										.getFirstElement())
										.getBriefDescription();
								if (desc == null) {
									desc = ""; //$NON-NLS-1$
								}
								ctrl_brief_desc.setText(desc);
							} else if (selection.size() > 1) {
								ctrl_brief_desc
										.setText(PropertiesResources.Process_MultipleSelection + " - " + selection.size()); //$NON-NLS-1$ 
							}
						}
					});

			if ((new Boolean(changesAllowed[count])).booleanValue()) {
				ctrl_add_3.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						IFilter filter = getFilter();
						List existingElements = ProcessUtil
								.getAssociatedElementList(getExistingElements3());
						ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), filter, element, tabString,
								existingElements);

						fd.setBlockOnOpen(true);
						fd.setTitle(tabString);
						fd.setEnableProcessScope(true);
						fd.setSection(getSection());
						fd.open();
						addItems3(fd.getSelectedItems());
						tableViewer3.refresh();

					}
				});

				ctrl_add_proc_3.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						descriptorProcessfilter = getFilterForDescriptors();
						process = getProcess();
						if (descriptorProcessfilter != null && process != null) {
							List existingElements = getExistingElements3();
							ItemsFilterDialog fd = new ItemsFilterDialog(
									PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow()
											.getShell(),
									descriptorProcessfilter, process,
									getDescriptorTabName(), existingElements);

							fd.setBlockOnOpen(true);
							fd.open();
							addFromProcessItems3(fd.getSelectedItems());
							tableViewer3.refresh();
						}

					}
				});

				ctrl_remove_3.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						IStructuredSelection selection = (IStructuredSelection) tableViewer3
								.getSelection();
						if (selection.size() > 0) {
							// update the model
							ArrayList rmItems = new ArrayList();
							rmItems.addAll(selection.toList());
							removeItems3(rmItems);
							tableViewer3.refresh();

							// clear the selection
							tableViewer3.setSelection(null, true);
							ctrl_brief_desc.setText(""); //$NON-NLS-1$
						}
						ctrl_remove_3.setEnabled(false);
					}
				});
			}
		}
		count++;

		/**
		 * Fouth set of listeners
		 */
		if (count < tableCount) {
			ctrl_table_4.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					IStructuredSelection selection = (IStructuredSelection) tableViewer4
							.getSelection();
					if ((selection.size() > 0) && editable)
						ctrl_remove_4.setEnabled(true);
				}
			});

			tableViewer4
					.addSelectionChangedListener(new ISelectionChangedListener() {
						public void selectionChanged(SelectionChangedEvent event) {
							IStructuredSelection selection = (IStructuredSelection) tableViewer4
									.getSelection();
							if ((selection.size() > 0) & editable) {
								if (isSyncFree()) {
									syncFreeUpdateBtnStatus4(selection);
								} else {
									ctrl_remove_4.setEnabled(true);
								}
							}
							if (selection.size() == 1) {
								String desc = ((MethodElement) selection
										.getFirstElement())
										.getBriefDescription();
								if (desc == null) {
									desc = ""; //$NON-NLS-1$
								}
								ctrl_brief_desc.setText(desc);
							} else if (selection.size() > 1) {
								ctrl_brief_desc
										.setText(PropertiesResources.Process_MultipleSelection + " - " + selection.size()); //$NON-NLS-1$ 
							}
						}
					});

			if ((new Boolean(changesAllowed[count])).booleanValue()) {
				ctrl_add_4.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						if (isSyncFree()) {
							IStructuredSelection selection = (IStructuredSelection) tableViewer4.getSelection();
							if (syncFreeAdd4(selection)) {								 	
								tableViewer4.refresh();
								return;
							}
						} 
						
						IFilter filter = getFilter();
						List existingElements = null;
						if (isSyncFree()) {
							existingElements = getExistingContentElements4();
						} else {
							existingElements = ProcessUtil.getAssociatedElementList(getExistingElements4());
						}
						ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
								.getWorkbench().getActiveWorkbenchWindow()
								.getShell(), filter, element, tabString,
								existingElements);

						fd.setBlockOnOpen(true);
						fd.setTitle(tabString);
						fd.setEnableProcessScope(true);
						fd.setSection(getSection());
						fd.open();
						addItems4(fd.getSelectedItems());
						tableViewer4.refresh();
					}
				});

				ctrl_add_proc_4.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						descriptorProcessfilter = getFilterForDescriptors();
						process = getProcess();
						if (descriptorProcessfilter != null && process != null) {
							List existingElements = getExistingElements4();
							ItemsFilterDialog fd = new ItemsFilterDialog(
									PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow()
											.getShell(),
									descriptorProcessfilter, process,
									getDescriptorTabName(), existingElements);

							fd.setBlockOnOpen(true);
							fd.open();
							addFromProcessItems4(fd.getSelectedItems());
							tableViewer4.refresh();
						}
					}
				});

				ctrl_remove_4.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						if (isSyncFree()) {
							IStructuredSelection selection = (IStructuredSelection) tableViewer4.getSelection();
							if (syncFreeRemove4(selection)) {
								tableViewer4.refresh();
								ctrl_remove_4.setEnabled(false);
								return;
							}							
						}
						
						IStructuredSelection selection = (IStructuredSelection) tableViewer4
								.getSelection();
						if (selection.size() > 0) {
							// update the model
							ArrayList rmItems = new ArrayList();
							rmItems.addAll(selection.toList());
							removeItems4(rmItems);
							tableViewer4.refresh();

							// clear the selection
							tableViewer4.setSelection(null, true);
							ctrl_brief_desc.setText(""); //$NON-NLS-1$
						}
						ctrl_remove_4.setEnabled(false);
					}
				});
			}
		}
	}
	
	protected void addItems1(List list) {
	};

	protected void addItems2(List list) {
	};

	protected void addItems3(List list) {
	};

	protected void addItems4(List list) {
	};

	protected void addFromProcessItems1(List list) {
	};

	protected void addFromProcessItems2(List list) {
	};

	protected void addFromProcessItems3(List list) {
	};

	protected void addFromProcessItems4(List list) {
	};

	protected void removeItems1(List list) {
	};

	protected void removeItems2(List list) {
	};

	protected void removeItems3(List list) {
	};

	protected void removeItems4(List list) {
	};

	protected List getExistingElements1() {
		return null;
	};

	protected List getExistingElements2() {
		return null;
	};

	protected List getExistingElements3() {
		return null;
	};

	protected List getExistingElements4() {
		return null;
	};
	
	protected List getExistingContentElements1() {
		return null;
	}
	
	protected List getExistingContentElements2() {
		return null;
	}
	
	protected List getExistingContentElements4() {
		return null;
	}
	
	protected boolean syncFreeAdd1(IStructuredSelection selection) {
		return false;
	}
	
	protected boolean syncFreeAdd2(IStructuredSelection selection) {
		return false;
	}
	
	protected boolean syncFreeAdd4(IStructuredSelection selection) {
		return false;
	}
	
	protected boolean syncFreeRemove1(IStructuredSelection selection) {
		return false;
	}
	
	protected boolean syncFreeRemove2(IStructuredSelection selection) {
		return false;
	}
	
	protected boolean syncFreeRemove4(IStructuredSelection selection) {
		return false;
	}
	
	protected void syncFreeUpdateBtnStatus1(IStructuredSelection selection) {
		
	}
	
	protected void syncFreeUpdateBtnStatus2(IStructuredSelection selection) {
		
	}
	
	protected void syncFreeUpdateBtnStatus4(IStructuredSelection selection) {
		
	}
	
	protected void createAddtionalButton1(Composite parent) {
		
	}
	
	protected void createAddtionalButton2(Composite parent) {
		
	}
	
	protected void createAddtionalButton3(Composite parent) {
		
	}
	
	protected void createAddtionalButton4(Composite parent) {
		
	}
	
	protected void mixWithExcluded(Descriptor des,
			List<MethodElement> elements, EReference linkedElementFeature,
			EReference excludedFeature, EReference desFeature) {
		ElementRealizer realizer = DefaultElementRealizer
				.newElementRealizer(getConfiguration());
		MethodElement element = ProcessUtil.getAssociatedElement(des);
		if (element == null) {
			return;
		}
		List<MethodElement> elementList = ConfigurationHelper
				.calc0nFeatureValue(element, linkedElementFeature, realizer);
		List<MethodElement> excludedList = (List<MethodElement>) des
				.eGet(excludedFeature);
		for (MethodElement e : excludedList) {
			if (elementList.contains(e)) {
				elements.add(e);
			}
		}
		
		// The following handle additional logic for config-free process - only
		// showing the non-realized results, since realization results could get
		// set into the model in memory due to browsing and publishing.
		Process process = ProcessUtil.getProcess(des.getSuperActivities());
		if (ProcessScopeUtil.getInstance().isConfigFree(process)) {
			DescriptorPropUtil propUtil = DescriptorPropUtil
					.getDesciptorPropUtil();
			Set<Object> set = new HashSet<Object>();
			Object value = element.eGet(linkedElementFeature);			//not realized value
			if (value instanceof List) {
				set.addAll((List) value);
			}			
			List<MethodElement> modifiedElements = new ArrayList<MethodElement>();
			for (MethodElement item : elements) {
				if (item instanceof Descriptor) {
					Descriptor desItem = (Descriptor) item;
					MethodElement elemItem = ProcessUtil
							.getAssociatedElement(desItem);
					if (elemItem == null || set.contains(elemItem)
							|| propUtil.localUse(desItem, des, desFeature)) {
						modifiedElements.add(desItem);
					}
				} else {
					modifiedElements.add(item);
				}
			}
			elements.clear();
			elements.addAll(modifiedElements);
		}
	}
	
}
