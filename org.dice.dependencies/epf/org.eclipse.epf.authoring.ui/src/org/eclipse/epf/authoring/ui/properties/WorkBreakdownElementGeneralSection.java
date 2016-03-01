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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.authoring.ui.editors.ColumnDescriptor;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.authoring.ui.filters.MdtElementFilter;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.util.PredecessorList;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkOrderType;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;


/**
 * The general tab section for WorkBreakdownElement
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class WorkBreakdownElementGeneralSection extends
		BreakdownElementGeneralSection {
	protected WorkBreakdownElement element;

	protected Button eventDrivenButton, ongoingButton, repeatableButton;

	// protected Text predecessorText;
	protected Button addButton, editButton, removeButton;

	// private SelectionListener eventDrivenButtonSelectionListener;
	// private SelectionListener ongoingButtonSelectionListener;
	// private SelectionListener repeatableButtonSelectionListener;

	// private Listener predecessorsListener;

	// private ComposedAdapterFactory adapterFactory;

	private TableViewer viewer;

	// private Table table;
	private TextCellEditor textCellEditor;

	private ValidatingTextCellEditor idCellEditor;

	private ComboBoxCellEditor comboBoxCellEditor;

	private Text ctrl_method_element;

	private Button linkButton;
	
	private Button clearButton;
	
	// predecessor map list
	private List predMapList = new ArrayList();

	private class ValidatingTextCellEditor extends TextCellEditor {
		protected Object lastInvalidElement;

		private int columnIndex;

		/**
		 * @param parent
		 */
		public ValidatingTextCellEditor(Composite parent) {
			super(parent);
		}

		void setColumnIndex(int columnIndex) {
			this.columnIndex = columnIndex;
		}

		public int getColumnIndex() {
			return columnIndex;
		}

		public Object getLastInvalidElement() {
			return lastInvalidElement;
		}

		String isValid(String txt) {
			IStructuredSelection selection = (IStructuredSelection) viewer
					.getSelection();
			if (selection.size() == 1) {
				Object e = selection.getFirstElement();
				String msg = isValid(e, txt);
				if (msg != null) {
					lastInvalidElement = e;
				} else {
					lastInvalidElement = null;
				}
				return msg;
			}
			return null;
		}

		protected String isValid(Object e, String txt) {
			return null;
		}
	}

	/** Dependency Literals * */
	private static final String FINISH_TO_START = PropertiesResources.WorkOrderType_FINISH_TO_START; 

	private static final String FINISH_TO_FINISH = PropertiesResources.WorkOrderType_FINISH_TO_FINISH; 

	private static final String START_TO_START = PropertiesResources.WorkOrderType_START_TO_START; 

	private static final String START_TO_FINISH = PropertiesResources.WorkOrderType_START_TO_FINISH; 

	/** Column ids * */
	private static final String COL_ID = IBSItemProvider.COL_ID;

	private static final String COL_PRESENTATION_NAME = IBSItemProvider.COL_PRESENTATION_NAME;

	private static final String COL_DEPENDENCY = "dependency"; //$NON-NLS-1$

	/** Column descriptor constants */
	public static final ColumnDescriptor COL_DESC_ID = new ColumnDescriptor(
			COL_ID,
			PropertiesResources.WorkBreakdownElement_Dependency_COL_ID_TEXT, 0, 100, true, ColumnDescriptor.CELL_EDITOR_TYPE_TEXT, SWT.CENTER); 

	public static final ColumnDescriptor COL_DESC_PRESENTATION_NAME = new ColumnDescriptor(
			COL_PRESENTATION_NAME,
			PropertiesResources.WorkBreakdownElement_Dependency_COL_PRESENTATION_NAME_TEXT, 1, 250, true, ColumnDescriptor.CELL_EDITOR_TYPE_NONE); 

	public static final ColumnDescriptor COL_DESC_DEPENDENCY = new ColumnDescriptor(
			COL_DEPENDENCY,
			PropertiesResources.WorkBreakdownElement_Dependency_COL_DEPENDENCY_TEXT, 2, 100, true, ColumnDescriptor.CELL_EDITOR_TYPE_COMBO_BOOLEAN); 

	ColumnDescriptor[] columnDescriptors = new ColumnDescriptor[] {
			COL_DESC_ID, COL_DESC_PRESENTATION_NAME, COL_DESC_DEPENDENCY };

	private static class CellEditorValidator implements ICellEditorValidator {

		private ValidatingTextCellEditor cellEditor;

		/**
		 * 
		 */
		public CellEditorValidator(ValidatingTextCellEditor cellEditor) {
			this.cellEditor = cellEditor;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
		 */
		public String isValid(Object value) {
			return cellEditor.isValid((String) value);
		}

	}

	private class ValidatingDeactivateListener implements Listener {
		private ValidatingTextCellEditor cellEditor;

		ValidatingDeactivateListener(ValidatingTextCellEditor cellEditor) {
			this.cellEditor = cellEditor;
		}

		public void handleEvent(Event e) {
			Display.getCurrent().asyncExec(new Runnable() {
				public void run() {
					// System.out.println("ValidatDeactivatee::run");
					if (cellEditor.getLastInvalidElement() != null) {
						String message = cellEditor.getErrorMessage();
						String title = getEditor().getTitle();
						String problem = PropertiesResources.ProcessEditorFormProperties_textEditCell_problem_msg; 
						MsgDialog dialog = AuthoringUIPlugin.getDefault()
								.getMsgDialog();
						dialog.displayError(title, problem, message); 
						viewer.editElement(cellEditor.getLastInvalidElement(),
								cellEditor.getColumnIndex());
					}
				}

			});
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementGeneralSection#init()
	 */
	protected void init() {
		super.init();

		// get workbreakdownelement object
		element = (WorkBreakdownElement) getElement();
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementGeneralSection#createGeneralSection(org.eclipse.swt.widgets.Composite)
	 */
	protected void createGeneralSection(Composite composite) {
		createGeneralSection_(composite);
		if (! accpetLink()) {
			return;
		}
		// method element
		FormUI.createLabel(toolkit, generalComposite, PropertiesResources.Linked_Element); 
		ctrl_method_element = FormUI.createText(toolkit, generalComposite,
				SWT.DEFAULT, 1);

		ctrl_method_element.setText(getLinkedElementName(element));
		ctrl_method_element.setEnabled(false);

		Composite buttonComposite = FormUI.createComposite(toolkit,
				generalComposite, SWT.NONE, 2, true);
		linkButton = FormUI.createButton(toolkit, buttonComposite, SWT.PUSH, 1);
		linkButton
				.setText(PropertiesResources.Process_Button_LinkMethodElement); 

		clearButton = FormUI.createButton(toolkit, buttonComposite, SWT.PUSH, 1);
		clearButton
				.setText(PropertiesResources.Process_Button_ClearMethodElement); 
	}
	
	private void createGeneralSection_(Composite composite) {
		super.createGeneralSection(composite);

		// Event Driven
		eventDrivenButton = FormUI.createCheckButton(toolkit, checkBoxComposite,
				1);
		eventDrivenButton.setText(PropertiesResources.WorkBreakdownElement_EventDriven); 

		// Ongoing
		ongoingButton = FormUI.createCheckButton(toolkit, checkBoxComposite,
				1);
		ongoingButton.setText(PropertiesResources.WorkBreakdownElement_Ongoing); 

		// repeatable
		repeatableButton = FormUI.createCheckButton(toolkit, checkBoxComposite,
				2);
		repeatableButton.setText(PropertiesResources.WorkBreakdownElement_Repeatable); 

		// predecessors
		// FormUI.createLabel(toolkit, generalComposite,
		// PropertiesResources.getString("WorkBreakdownElement.Predecessors"));
		// predecessorText = FormUI.createText(toolkit, generalComposite,
		// SWT.DEFAULT, horizontalSpan);
		// predecessorText.setText(getPredecessorsList());

		// depenency type

		FormUI.createLabel(toolkit, generalComposite, PropertiesResources.WorkBreakdownElement_Dependency); 

		try {
			// create viewer
			createViewer(generalComposite, 2);
		} catch (Exception ex) {
			logger
					.logError(
							"Error creating viewer for workbreakdown element general section: ", ex); //$NON-NLS-1$
		}

		// create button pane
		Composite buttonPane = toolkit.createComposite(generalComposite);
		buttonPane.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		buttonPane.setLayout(new GridLayout());

		FormUI.createLabel(toolkit, buttonPane, ""); //$NON-NLS-1$
		addButton = FormUI.createButton(toolkit, buttonPane,
				PropertiesResources.WorkBreakdownElement_Dependency_Add); 
		editButton = FormUI.createButton(toolkit, buttonPane,
				PropertiesResources.WorkBreakdownElement_Dependency_Edit); 
		removeButton = FormUI.createButton(toolkit, buttonPane,
				PropertiesResources.WorkBreakdownElement_Dependency_Remove); 
		editButton.setEnabled(false);
		removeButton.setEnabled(false);
	}

	protected void addListeners() {
		super.addListeners();

		eventDrivenButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				element = (WorkBreakdownElement) getElement();
				boolean status = actionMgr.doAction(IActionManager.SET,
						element, UmaPackage.eINSTANCE
								.getWorkBreakdownElement_IsEventDriven(),
						Boolean.valueOf(eventDrivenButton.getSelection()), -1);
				if (!status)
					eventDrivenButton.setSelection(element.getIsEventDriven()
							.booleanValue());

			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		ongoingButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				element = (WorkBreakdownElement) getElement();
				boolean status = actionMgr.doAction(IActionManager.SET,
						element, UmaPackage.eINSTANCE
								.getWorkBreakdownElement_IsOngoing(), Boolean
								.valueOf(ongoingButton.getSelection()), -1);
				if (!status)
					ongoingButton.setSelection(element.getIsOngoing()
							.booleanValue());

			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		repeatableButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				element = (WorkBreakdownElement) getElement();
				boolean status = actionMgr.doAction(IActionManager.SET,
						element, UmaPackage.eINSTANCE
								.getWorkBreakdownElement_IsRepeatable(),
						Boolean.valueOf(repeatableButton.getSelection()), -1);
				if (!status)
					repeatableButton.setSelection(element.getIsRepeatable()
							.booleanValue());

			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		addButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				// List predList = element.getLinkToPredecessor();
				WorkOrder o = UmaFactory.eINSTANCE.createWorkOrder();
				o.setLinkType(WorkOrderType.FINISH_TO_START);

				// predList.add(o);

				PredecessorMap map = new PredecessorMap(-1, (WorkOrder) o);
				predMapList.add(map);

				viewer.refresh();
				viewer.editElement(map, 0);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		editButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				// get selection
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				if (selection.size() > 0) {
					PredecessorMap predMap = (PredecessorMap) selection
							.getFirstElement();
					IBSItemProvider adapter = (IBSItemProvider) getBSAdapter();
					Object process = adapter.getTopItem();

					PredecessorDialog dlg = new PredecessorDialog(PlatformUI
							.getWorkbench().getActiveWorkbenchWindow()
							.getShell(), element, process, getAdapterFactory(),
							predMap, predMapList, actionMgr);
					dlg.open();

					viewer.refresh();

				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		removeButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				if (selection.size() > 0) {
					PredecessorMap map = (PredecessorMap) selection
							.getFirstElement();
					predMapList.remove(map);

					WorkOrder wo = map.getWorkOrder();
					WorkBreakdownElement wbe = (WorkBreakdownElement) getElement();
					actionMgr
							.doAction(
									IActionManager.REMOVE,
									wbe,
									UmaPackage.eINSTANCE
											.getWorkBreakdownElement_LinkToPredecessor(),
									wo, -1);
					viewer.refresh();

				}

			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) viewer
						.getSelection();
				if (selection.size() == 1 && editable) {
					removeButton.setEnabled(true);
					editButton.setEnabled(true);
				}
			}
		});

		if (! accpetLink()) {
			return;
		}
		
		linkButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ModifiedTypeMeta meta =  TypeDefUtil.getLinkedMdtMeta(element);
				IFilter filter = new MdtElementFilter(getConfiguration(), meta);
				List existingElements = new ArrayList();
				ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
						.getWorkbench().getActiveWorkbenchWindow().getShell(),
						filter, element, meta.getId(),
						existingElements);

				fd.setBlockOnOpen(true);
				fd.setViewerSelectionSingle(true);
				fd.setTitle( meta.getId());
				fd.setEnableProcessScope(true);
				fd.setSection(getSection());
				fd.open();
				if (! fd.getSelectedItems().isEmpty()) {
					Object item = fd.getSelectedItems().get(0);
					if (item instanceof MethodElement) {
						PropUtil.getPropUtil(actionMgr).setLinkedElement(element, (MethodElement) item);
					}
				} else {
					return;
				}
				ctrl_method_element.setText(getLinkedElementName(element));
				getEditor().updateOnLinkedElementChange(element);
			}

			public void widgetDefaultSelected(SelectionEvent e1) {
			}
		});
				
		clearButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				PropUtil.getPropUtil(actionMgr).setLinkedElement(element, null);
				ctrl_method_element.setText("");//$NON-NLS-1$
			}

			public void widgetDefaultSelected(SelectionEvent e1) {
			}
		});
	
	}

	/**
	 * Returns comma separated list of predecessors
	 * 
	 * @return
	 */
	// private String getPredecessorsList()
	// {
	// IBSItemProvider adapter = (IBSItemProvider) getBSAdapter();
	// return adapter.getAttribute(element, IBSItemProvider.COL_PREDECESSORS);
	// }
	// protected void addListeners()
	// {
	// super.addListeners();
	//		
	// eventDrivenButton.addSelectionListener(eventDrivenButtonSelectionListener);
	// ongoingButton.addSelectionListener(ongoingButtonSelectionListener);
	// repeatableButton.addSelectionListener(repeatableButtonSelectionListener);
	// //predecessorText.addListener(SWT.Deactivate, predecessorsListener);
	// }
	//	
	// protected void removeListeners()
	// {
	// super.removeListeners();
	//			
	// eventDrivenButton.removeSelectionListener(eventDrivenButtonSelectionListener);
	// ongoingButton.removeSelectionListener(ongoingButtonSelectionListener);
	// repeatableButton.removeSelectionListener(repeatableButtonSelectionListener);
	// //predecessorText.removeListener(SWT.Deactivate, predecessorsListener);
	// }
	protected void updateControls() {
		super.updateControls();
		eventDrivenButton.setEnabled(editable);
		ongoingButton.setEnabled(editable);
		repeatableButton.setEnabled(editable);
		viewer.getTable().setEnabled(editable);
		addButton.setEnabled(editable);

		IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		if (selection.size() == 1 && editable) {
			removeButton.setEnabled(editable);
			editButton.setEnabled(editable);
		} else {
			removeButton.setEnabled(false);
			editButton.setEnabled(false);
		}

//		if ((EPFPropertySheetPage.formPageID
//				.equals(ProcessEditor.TA_FORM_ID))
//				|| (EPFPropertySheetPage.formPageID
//						.equals(ProcessEditor.WPBS_FORM_ID))) {
		if (!(EPFPropertySheetPage.formPageID.equals(ProcessEditor.WBS_FORM_ID) || EPFPropertySheetPage.formPageID
				.equals(ProcessEditor.CONSOLIDATED_FORM_ID))) {
			addButton.setEnabled(false);
			removeButton.setEnabled(false);
			editButton.setEnabled(false);
			viewer.getTable().setEnabled(false);
		}
	}

	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.BreakdownElementGeneralSection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof WorkBreakdownElement) {
				super.refresh();
				element = (WorkBreakdownElement) getElement();

				eventDrivenButton.setSelection(element.getIsEventDriven()
						.booleanValue());
				ongoingButton.setSelection(element.getIsOngoing()
						.booleanValue());
				repeatableButton.setSelection(element.getIsRepeatable()
						.booleanValue());
				// predecessorText.setText(getPredecessorsList());

				// re-initialize predList
				predMapList = new ArrayList();
				initializePredList();
				
				if (ctrl_method_element != null) {
					String linkedString = getLinkedElementName(element);
					ctrl_method_element.setText(linkedString);
				}
				viewer.refresh();
			}

		} catch (Exception ex) {
			logger
					.logError(
							"Error refreshing Workbreakdown element general section:", ex); //$NON-NLS-1$
		}
	}

	private void createViewer(Composite parent, int horizontalSpan) {
		// create process viewer
		viewer = new TableViewer(parent, SWT.SINGLE | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.verticalIndent = 10;
		gridData.heightHint = 50;
		gridData.horizontalSpan = horizontalSpan;
		viewer.getControl().setLayoutData(gridData);

		// set up columns
		String[] colProps = new String[columnDescriptors.length];
		for (int i = 0; i < columnDescriptors.length; i++) {
			colProps[i] = columnDescriptors[i].id;
			TableColumn column = new TableColumn(viewer.getTable(),
					columnDescriptors[i].alignment);
			column.setText(columnDescriptors[i].label);
			column.setResizable(columnDescriptors[i].resizable);
			column.setWidth(columnDescriptors[i].width);

		}
		viewer.getTable().setHeaderVisible(true);
		viewer.setColumnProperties(colProps);

		// table = viewer.getTable();

		// final TableCursor cursor = new TableCursor(table, SWT.NONE);
		// final ControlEditor editor = new ControlEditor(cursor);
		// editor.grabHorizontal = true;
		// editor.grabVertical = true;

		// final TableCursor cursor = new TableCursor(table, SWT.NONE);
		// // create an editor to edit the cell when the user hits "ENTER"
		// // while over a cell in the table
		// final ControlEditor editor = new ControlEditor(cursor);
		// editor.grabHorizontal = true;
		// editor.grabVertical = true;
		//
		// cursor.addSelectionListener(new SelectionAdapter() {
		// // when the TableEditor is over a cell, select the corresponding row
		// in
		// // the table
		// public void widgetSelected(SelectionEvent e) {
		// table.setSelection(new TableItem[] { cursor.getRow()});
		// }
		// // when the user hits "ENTER" in the TableCursor, pop up a text
		// editor so that
		// // they can change the text of the cell
		// public void widgetDefaultSelected(SelectionEvent e) {
		// final Text text = new Text(cursor, SWT.NONE);
		// TableItem row = cursor.getRow();
		// int column = cursor.getColumn();
		// text.setText(row.getText(column));
		// text.addKeyListener(new KeyAdapter() {
		// public void keyPressed(KeyEvent e) {
		// // close the text editor and copy the data over
		// // when the user hits "ENTER"
		// if (e.character == SWT.CR) {
		// TableItem row = cursor.getRow();
		// int column = cursor.getColumn();
		// if (column == 1) return;
		// row.setText(column, text.getText());
		// text.dispose();
		// }
		// // close the text editor when the user hits "ESC"
		// if (e.character == SWT.ESC) {
		// text.dispose();
		// }
		// }
		// });
		// editor.setEditor(text);
		// text.setFocus();
		// }
		// });
		// // Hide the TableCursor when the user hits the "CTRL" or "SHIFT" key.
		// // This alows the user to select multiple items in the table.
		// cursor.addKeyListener(new KeyAdapter() {
		// public void keyPressed(KeyEvent e) {
		// if (e.keyCode == SWT.CTRL
		// || e.keyCode == SWT.SHIFT
		// || (e.stateMask & SWT.CONTROL) != 0
		// || (e.stateMask & SWT.SHIFT) != 0) {
		// cursor.setVisible(false);
		// }
		// }
		// });
		// // Show the TableCursor when the user releases the "SHIFT" or "CTRL"
		// key.
		// // This signals the end of the multiple selection task.
		// table.addKeyListener(new KeyAdapter() {
		// public void keyReleased(KeyEvent e) {
		// if (e.keyCode == SWT.CONTROL && (e.stateMask & SWT.SHIFT) != 0)
		// return;
		// if (e.keyCode == SWT.SHIFT && (e.stateMask & SWT.CONTROL) != 0)
		// return;
		// if (e.keyCode != SWT.CONTROL
		// && (e.stateMask & SWT.CONTROL) != 0)
		// return;
		// if (e.keyCode != SWT.SHIFT && (e.stateMask & SWT.SHIFT) != 0)
		// return;
		//
		// TableItem[] selection = table.getSelection();
		// TableItem row = (selection.length == 0) ?
		// table.getItem(table.getTopIndex()) : selection[0];
		// table.showItem(row);
		// cursor.setSelection(row, 0);
		// cursor.setVisible(true);
		// cursor.setFocus();
		// }
		// });

		IStructuredContentProvider contentProvider = new AdapterFactoryContentProvider(
				getAdapterFactory()) {
			public Object[] getElements(Object obj) {
				// System.out.println("PredMapList::"+predMapList);
				if (obj instanceof List) {
					return ((List) predMapList).toArray();
				}
				return null;
			}
		};

		viewer.setContentProvider(contentProvider);
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
				getAdapterFactory()) {
			public String getColumnText(Object obj, int i) {
				// System.out.println("WorkBreakdownElementGeneralSection::labelProvider
				// Object- " + obj);
				if (obj instanceof PredecessorMap) {
					int id = ((PredecessorMap) obj).getId();
					WorkOrder wo = ((PredecessorMap) obj).getWorkOrder();

					if (i == 0) {
						return String.valueOf(id);
					}
					if (i == 1) {
						String name = ""; //$NON-NLS-1$
						if (wo.getPred() != null) {
							name = wo.getPred().getPresentationName();
							if (StrUtil.isBlank(name)) {
								return wo.getPred().getName();
							}
						}
						return name;
					}
					if (i == 2) {
						if (wo.getLinkType() == WorkOrderType.FINISH_TO_FINISH)
							return FINISH_TO_FINISH;
						else if (wo.getLinkType() == WorkOrderType.FINISH_TO_START)
							return FINISH_TO_START;
						else if (wo.getLinkType() == WorkOrderType.START_TO_START)
							return START_TO_START;
						else if (wo.getLinkType() == WorkOrderType.START_TO_FINISH)
							return START_TO_FINISH;
						return wo.getLinkType().getName();
					}
				}
				return ""; //$NON-NLS-1$
			}
		};

		viewer.setLabelProvider(labelProvider);

		textCellEditor = new TextCellEditor(viewer.getTable());
		comboBoxCellEditor = new ComboBoxCellEditor(viewer.getTable(),
				new String[] { FINISH_TO_START, FINISH_TO_FINISH,
						START_TO_START, START_TO_FINISH });

		setCellEditors();
		setCellModifiers();

		initializePredList();
		viewer.setInput(predMapList);
		viewer.refresh();

	}

	private int getCellEditorType(String property) {
		for (int i = 0; i < columnDescriptors.length; i++) {
			ColumnDescriptor desc = columnDescriptors[i];
			if (desc.id == property) {
				return desc.cellEditorType;
			}
		}
		return ColumnDescriptor.CELL_EDITOR_TYPE_NONE;
	}

	public CellEditor getCellEditor(String columnID, int columnIndex) {
		if (columnID == COL_ID) {
			if (idCellEditor == null) {
				idCellEditor = new ValidatingTextCellEditor(viewer.getTable()) {
					protected String isValid(Object e, String txt) {
						// System.out.println("ValidatingTextCellEditor::isValid");
						e = TngUtil.unwrap(e);

						IBSItemProvider adapter = (IBSItemProvider) getBSAdapter();
						Object process = adapter.getTopItem();

						List predecessors = new ArrayList();
						WorkBreakdownElement wbe = (WorkBreakdownElement) getElement();
						String str = getPredId();
						String result = ProcessUtil
								.checkPredecessorList(wbe, str,
										getAdapterFactory(), process,
										predecessors);

						return result;
					}
				};

				idCellEditor
						.setValidator(new CellEditorValidator(idCellEditor));
				idCellEditor.getControl().addListener(SWT.Deactivate,
						new ValidatingDeactivateListener(idCellEditor));
			}
			idCellEditor.setColumnIndex(columnIndex);

			return idCellEditor;
		}

		return null;
	}

	private void setCellEditors() {
		CellEditor[] cellEditors = new CellEditor[columnDescriptors.length];
		for (int i = 0; i < columnDescriptors.length; i++) {
			CellEditor cellEditor = getCellEditor(columnDescriptors[i].id, i);
			if (cellEditor != null) {
				cellEditors[i] = cellEditor;
			} else {
				switch (columnDescriptors[i].cellEditorType) {
				case ColumnDescriptor.CELL_EDITOR_TYPE_TEXT:
					cellEditors[i] = textCellEditor;
					break;
				case ColumnDescriptor.CELL_EDITOR_TYPE_COMBO_BOOLEAN:
					cellEditors[i] = comboBoxCellEditor;
					break;
				default:
					cellEditors[i] = null;
				}
			}
		}
		viewer.setCellEditors(cellEditors);
	}

	private void setCellModifiers() {
		// Set the cell modifier
		viewer.setCellModifier(new ICellModifier() {
			public boolean canModify(Object element, String property) {
				// System.out.println("canModify: element="+element+",
				// property="+property);
				if (!(element instanceof PredecessorMap))
					return false;
				if (property == COL_PRESENTATION_NAME) {
					return false;
				}

				return true;
			}

			public Object getValue(Object element, String property) {
				// System.out.println("getValue: element="+element+",
				// property="+property);

				String val = null;
				switch (getCellEditorType(property)) {
				case ColumnDescriptor.CELL_EDITOR_TYPE_TEXT:
					val = new Integer(((PredecessorMap) element).getId())
							.toString();
					return val;
				case ColumnDescriptor.CELL_EDITOR_TYPE_COMBO_BOOLEAN:
					int value = (((PredecessorMap) element).getWorkOrder())
							.getLinkType().getValue();
					if (WorkOrderType.FINISH_TO_START_VALUE == value) {
						return new Integer(0);
					} else if (WorkOrderType.FINISH_TO_FINISH_VALUE == value) {
						return new Integer(1);
					} else if (WorkOrderType.START_TO_START_VALUE == value) {
						return new Integer(2);
					} else
						return new Integer(3);

				default:
					return ""; //$NON-NLS-1$
				}
			}

			public void modify(final Object element, String property,
					Object value) {
				// System.out.println("modify: element="+element+",
				// property="+property+", value="+value);
				TableItem item = (TableItem) element;
				PredecessorMap predMap = (PredecessorMap) item.getData();
				WorkOrder wo = predMap.getWorkOrder();

				if (property.equals(COL_ID)) {
					item.setText(0, value.toString());

					int predId;
					try {
						predId = new Integer(value.toString()).intValue();
						if (predId < 0)
							throw new Exception();
					} catch (Exception e) {
						AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayWarning(
										PropertiesResources.Process_predecessors_validNumberTitle, 
										PropertiesResources.Process_predecessors_validNumberMessage); 
						predMapList.remove(predMap);
						viewer.refresh();
						return;
					}
					predMap.setId(predId);
				}

				final int columnIndex = getColumnIndex(property);
				CellEditor cellEditor = viewer.getCellEditors()[columnIndex];
				String errMsg = cellEditor.getErrorMessage();
				if (errMsg != null) {
					return;
				}

				WorkOrderType type;
				IBSItemProvider adapter = (IBSItemProvider) getBSAdapter();
				WorkBreakdownElement wbe = (WorkBreakdownElement) getElement();
				
				switch (getCellEditorType(property)) {
				case ColumnDescriptor.CELL_EDITOR_TYPE_TEXT:					
					Object process = adapter.getTopItem();

					List<Object> predecessors = new ArrayList<Object>();					
					String str = getPredId();
					String result = ProcessUtil.checkPredecessorList(wbe, str,
							getAdapterFactory(), process, predecessors);

					if (result == null) {
						List<WorkOrder> addList = new ArrayList<WorkOrder>();
						List<WorkOrder> predToBeDeleted = new ArrayList<WorkOrder>();
						PredecessorList.prepareUpdatePredecessors(getAdapterFactory(), wbe, predecessors, addList, predToBeDeleted);
						if(!predToBeDeleted.isEmpty()) {
							actionMgr.doAction(
									IActionManager.REMOVE_MANY,
									wbe,
									UmaPackage.eINSTANCE.getWorkBreakdownElement_LinkToPredecessor(),
									predToBeDeleted, -1);
						}
						if(!addList.isEmpty()) {
							boolean status = actionMgr
									.doAction(
											IActionManager.ADD_MANY,
											wbe,
											UmaPackage.eINSTANCE
													.getWorkBreakdownElement_LinkToPredecessor(),
											addList, -1);
							if(status) {
								//TODO: review this
								predMap.setWorkOrder(addList.get(addList.size() - 1));
							} else {
								predMapList.remove(predMap);
							}
						}
					} else {
						// show error message box
						String title = getEditor().getTitle();
						String problem = PropertiesResources.ProcessEditorFormProperties_cellEditor_invalidPredcessor_problem_msg; 
						MsgDialog dialog = AuthoringUIPlugin.getDefault()
								.getMsgDialog();
						dialog.displayWarning(title, problem, result); 
						predMapList.remove(predMap);
					}

					break;
				case ColumnDescriptor.CELL_EDITOR_TYPE_COMBO_BOOLEAN:
					boolean removed = false;
					WorkBreakdownElement wbeCopy = null;
					WorkOrder o = null;
					int index = ((Number) value).intValue();
					for (Iterator iter = wbe.getLinkToPredecessor()
							.iterator(); iter.hasNext();) {
						o = (WorkOrder) iter.next();
						if (o.getPred() == wo.getPred())
						{							
							if (o.getLinkType().getValue() != index) {
								wbeCopy = o.getPred();
								iter.remove();
								removed = true;
							}
						}
					}					
										
					if (index == 0) {
						type = WorkOrderType.FINISH_TO_START;
					} else if (index == 1) {
						type = WorkOrderType.FINISH_TO_FINISH;
					} else if (index == 2) {
						type = WorkOrderType.START_TO_START;
					} else {
						type = WorkOrderType.START_TO_FINISH;
					}
					
					if (removed && wbeCopy != null) {					
						wo.setPred(wbeCopy);
						wo.setLinkType(type);
						boolean status = actionMgr
								.doAction(
										IActionManager.ADD,
										wbe,
										UmaPackage.eINSTANCE
												.getWorkBreakdownElement_LinkToPredecessor(),
										wo, -1);
						if (status)
							predMap.setWorkOrder(o);
					} else {
						actionMgr.doAction(IActionManager.SET, wo,
								UmaPackage.eINSTANCE.getWorkOrder_LinkType(), type,
								-1);
					}						
					
					break;

				default:
					type = WorkOrderType.FINISH_TO_FINISH;
				}

				viewer.refresh();
			}
		});

	}

	/**
	 * This method returns list of item providers for predecessors
	 * 
	 * @return
	 */
	// private Object getPredItemProviders()
	// {
	// IBSItemProvider adapter = (IBSItemProvider) getBSAdapter();
	// if (adapter != null)
	// return adapter.getPredecessors();
	//    	
	// return null;
	// }
	private ItemProviderAdapter getBSAdapter() {
		try {
			AdapterFactory factory = getAdapterFactory();// TngAdapterFactory.INSTANCE.getProcessComposedAdapterFactory();

			element = (WorkBreakdownElement) getElement();

			ItemProviderAdapter provider = (ItemProviderAdapter) factory.adapt(
					element, ITreeItemContentProvider.class);
			return provider;
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * Return column index for property
	 * 
	 * @param property
	 * @return
	 */
	private int getColumnIndex(String property) {
		Object[] colProps = viewer.getColumnProperties();
		for (int i = 0; i < colProps.length; i++) {
			if (colProps[i] == property)
				return i;
		}
		return -1;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.AbstractSection#getNamePrefix()
	 */
	public String getNamePrefix() {
		if (element instanceof TeamProfile) {
			return LibraryUIText.TEXT_TEAM_PROFILE + ": "; //$NON-NLS-1$
		} else if (element instanceof Milestone) {
			return LibraryUIText.TEXT_MILESTONE + ": "; //$NON-NLS-1$
		}
		return LibraryUIText.TEXT_WORK_PRODUCT_ELEMENT + ": "; //$NON-NLS-1$
	}

	private String getPredId() {
		StringBuffer buf = new StringBuffer();

		for (Iterator itor = predMapList.iterator(); itor.hasNext();) {
			PredecessorMap map = (PredecessorMap) itor.next();
			buf.append(map.getId());
			buf.append(","); //$NON-NLS-1$
		}
		return buf.toString();
	}

	/**
	 * Initialize pred list
	 * 
	 */
	private void initializePredList() {
		try {
			List predProviders = null;

			// get work breakdown element
			element = (WorkBreakdownElement) getElement();

			// get predecessor item providers
			AdapterFactory factory = getAdapterFactory();

			if (factory != null) {
				ItemProviderAdapter provider = (ItemProviderAdapter) factory
						.adapt(element, ITreeItemContentProvider.class);
				if (provider instanceof IBSItemProvider) {
					predProviders = ((IBSItemProvider) provider)
							.getPredecessors();
				}
			}

			// need following for special case (WrapperItemProviders).
			ISelection selection = getSelection();
			if (selection instanceof IStructuredSelection) {
				Object input = ((IStructuredSelection) selection)
						.getFirstElement();
				if (input instanceof BreakdownElementWrapperItemProvider) {
					predProviders = ((BreakdownElementWrapperItemProvider) input)
							.getPredecessors();
				}
			}

			// get link to predecessors
			if ((element != null) && (predProviders != null)) {
				List predList = element.getLinkToPredecessor();
				for (Iterator itemIterator = predProviders.iterator(); itemIterator
						.hasNext();) {
					boolean found = false;
					Object itemObj = (Object) itemIterator.next();
					Object targetObj = null;
					if (itemObj instanceof WrapperItemProvider) {
						targetObj = ((WrapperItemProvider) itemObj).getValue();
					}
					if (itemObj instanceof ItemProviderAdapter) {
						targetObj = ((ItemProviderAdapter) itemObj).getTarget();
					}
					if (targetObj != null) {
						int id = ((IBSItemProvider) itemObj).getId();
						found = addPredecessor(predList, id, targetObj);

						if ((!found)
								&& (targetObj instanceof VariabilityElement)) {
							VariabilityElement ve = (VariabilityElement) targetObj;
							if (ve.getVariabilityBasedOnElement() != null) {
								VariabilityElement el = ve
										.getVariabilityBasedOnElement();
								targetObj = el;

								// add base variability object
								addPredecessor(predList, id, targetObj);
							}
						}
					}
				}

				// for (Iterator itor=predList.iterator(); itor.hasNext();)
				// {
				// boolean found = false;
				// Object o = (Object) itor.next();
				// if (o instanceof WorkOrder)
				// {
				// //System.out.println("getElement::WorkOrder = "+ ((WorkOrder)
				// o).getPred());
				// WorkBreakdownElement wbe = ((WorkOrder) o).getPred();
				// if (wbe != null)
				// {
				// for (Iterator itemIterator=predProviders.iterator();
				// itemIterator.hasNext();)
				// {
				// Object itemObj = (Object) itemIterator.next();
				// Object targetObj = null;
				// if (itemObj instanceof WrapperItemProvider)
				// {
				// targetObj = ((WrapperItemProvider) itemObj).getValue();
				// }
				// if (itemObj instanceof ItemProviderAdapter)
				// {
				// targetObj = ((ItemProviderAdapter)itemObj).getTarget();
				// }
				// if (targetObj != null)
				// {
				// if (targetObj.equals(wbe))
				// {
				// // create mapObjectList
				// int id = ((IBSItemProvider) itemObj).getId();
				// PredecessorMap map = new PredecessorMap(id, (WorkOrder) o);
				// predMapList.add(map);
				//		
				// found=true;
				// break;
				// }
				// }
				// }
				// if (!found)
				// {
				// // if provider is not found, then add with id=0
				// // do it only for TeamAllocation and WorkProductUsage view
				// // if
				// ((EPFPropertySheetPage.formPageID.equals(ProcessFormEditor.TA_FORM_ID))
				// ||
				// //
				// (EPFPropertySheetPage.formPageID.equals(ProcessFormEditor.WPBS_FORM_ID)))
				// {
				// PredecessorMap map = new PredecessorMap(0, (WorkOrder) o);
				// predMapList.add(map);
				// }
				// }
				// }
				// }
				// }
			}
		} catch (Exception e) {
			logger.logError("Initializing Predecessor List", e); //$NON-NLS-1$
		}
	}

	private boolean addPredecessor(List predList, int id, Object targetObj) {
		for (Iterator itor = predList.iterator(); itor.hasNext();) {
			Object o = (Object) itor.next();
			if (o instanceof WorkOrder) {
				// System.out.println("getElement::WorkOrder = "+ ((WorkOrder)
				// o).getPred());
				WorkBreakdownElement wbe = ((WorkOrder) o).getPred();

				if (targetObj.equals(wbe)) {
					// create mapObjectList
					PredecessorMap map = new PredecessorMap(id, (WorkOrder) o);
					predMapList.add(map);

					return true;
				}
			}
		}

		return false;
	}
	
	private String getLinkedElementName(MethodElement element) {
		MethodElement linkedElement = PropUtil.getPropUtil().getLinkedElement(element);
		return linkedElement == null ? PropertiesResources.Process_None : TngUtil.getLabelWithPath(linkedElement);
	}
		
	private boolean accpetLink() {
		if (! ProcessUtil.isSynFree()) {
			return false;
		}
		ModifiedTypeMeta linedMeta = TypeDefUtil.getLinkedMdtMeta(element);
		return linedMeta == null ? false : true;
	}
		
}