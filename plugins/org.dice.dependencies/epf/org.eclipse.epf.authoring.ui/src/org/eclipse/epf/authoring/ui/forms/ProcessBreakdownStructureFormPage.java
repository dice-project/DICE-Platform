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
package org.eclipse.epf.authoring.ui.forms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.gef.figures.Colors;
import org.eclipse.epf.authoring.ui.AuthoringUIHelpContexts;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.celleditors.AbstractCheckBoxCellEditor;
import org.eclipse.epf.authoring.ui.celleditors.ProcessCheckBoxCellEditor;
import org.eclipse.epf.authoring.ui.editors.ColumnDescriptor;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.editors.ProcessEditor;
import org.eclipse.epf.authoring.ui.providers.ExposedAdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.providers.VariabilityElementLabelProvider;
import org.eclipse.epf.authoring.ui.views.ProcessViewer;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBSItemProvider;
import org.eclipse.epf.library.edit.process.IColumnAware;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.PredecessorList;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;


/**
 * Breadown structure editor
 * 
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ProcessBreakdownStructureFormPage extends ProcessFormPage
		implements IViewerProvider {

	protected static final String TXT_TRUE = "true"; //$NON-NLS-1$

	protected static final String TXT_FALSE = "false"; //$NON-NLS-1$

	protected static final Collection<EClass> ECLASSES;
	
	private int tabIndex = 0;
	
	static {
		ECLASSES = new ArrayList<EClass>();
		ECLASSES.add(UmaPackage.eINSTANCE.getMethodPackage());
	}
	
	private class ProcessAdapterFactoryLabelProvider extends
			VariabilityElementLabelProvider implements IColorProvider {

		private DecoratingLabelProvider decoratingLabelProvider;
		
		/**
		 * @param adapterFactory
		 */
		public ProcessAdapterFactoryLabelProvider(AdapterFactory adapterFactory) {
			super(adapterFactory);
			decoratingLabelProvider = new DecoratingLabelProvider(
					new AdapterFactoryLabelProvider(adapterFactory),
					PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator());
		}
		
		public void addListener(ILabelProviderListener listener) {
			super.addListener(listener);
			decoratingLabelProvider.addListener(listener);
		}
		
		public void removeListener(ILabelProviderListener listener) {			
			super.removeListener(listener);
			decoratingLabelProvider.removeListener(listener);
		}
				
		
		/* (non-Javadoc)
		 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getColumnImage(java.lang.Object, int)
		 */
		public Image getColumnImage(Object object, int columnIndex) {
			if(columnIndex == 0) {
				return getImage(object);
			}
			return super.getColumnImage(object, columnIndex);
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getImage(java.lang.Object)
		 */
		public Image getImage(Object object) {
			return decoratingLabelProvider.getImage(object);
		}

		/**
		 * 
		 * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
		 */
		public Color getForeground(Object element) {
			if (isSuppressed(element)) {
				return Colors.SUPRESSED_ELEMENT_LABEL;
			}
			else if (isExternal(element)) {
				return Colors.INHERITED_ELEMENT_LABEL;
			}
			Object obj = TngUtil.unwrap(element);
			if (obj instanceof Descriptor) {
				Descriptor d = (Descriptor) obj;
				if (DescriptorPropUtil.getDesciptorPropUtil().isCreatedByReference(d)) {
					return Colors.DYNAMIC_ELEMENT_LABEL;
				}
			}
			return null;
		}

		private boolean isSuppressed(Object element) {
			if (element instanceof BreakdownElement) {
				return getSuppression()
						.isSuppressed((BreakdownElement) element);
			} else if (element instanceof BreakdownElementWrapperItemProvider) {
				return getSuppression().isSuppressed(
						(BreakdownElementWrapperItemProvider) element);
			}
			return false;
		}

		private Suppression getSuppression() {
			return ((ProcessEditor) ProcessBreakdownStructureFormPage.this
					.getEditor()).getSuppression();
		}

		/**
		 * 
		 * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
		 */
		public Color getBackground(Object element) {
			// let OS take control over tree widget background
			// rather than sending one fixed background.
			return null;
		}

		/**
		 * 
		 * @see com.ibm.library.edit.util.VariabilityElementLabelProvider#isExternal(java.lang.Object)
		 */
		public boolean isExternal(Object element) {
			return ProcessUtil.isInherited(element);
		}

		/**
		 * @see com.ibm.library.edit.util.VariabilityElementLabelProvider#getFont(java.lang.Object)
		 */
		public Font getFont(Object element) {
			if (isExternal(element)) {
				return italicFont;
			}
			if (element instanceof TaskDescriptor) {
				TaskDescriptor td = (TaskDescriptor) element;
				if (DescriptorPropUtil.getDesciptorPropUtil().getGreenParentDescriptor(td) != null) {
					return italicFont;
				}
			}
			return regularFont;
		}
		
		/* (non-Javadoc)
		 * @see org.eclipse.epf.authoring.ui.providers.VariabilityElementLabelProvider#dispose()
		 */
		public void dispose() {
			decoratingLabelProvider.dispose();
			decoratingLabelProvider = null;
			super.dispose();
		}
	}

	protected class ValidatingTextCellEditor extends TextCellEditor {
		protected Object lastInvalidElement;

		private int columnIndex;

		/**
		 * @param parent
		 */
		public ValidatingTextCellEditor(Composite parent) {
			super(parent);
		}

		public void setColumnIndex(int columnIndex) {
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

	protected static class CellEditorValidator implements ICellEditorValidator {

		private ValidatingTextCellEditor cellEditor;

		/**
		 * 
		 */
		public CellEditorValidator(ValidatingTextCellEditor cellEditor) {
			this.cellEditor = cellEditor;
		}

		/**
		 * @see org.eclipse.jface.viewers.ICellEditorValidator#isValid(java.lang.Object)
		 */
		public String isValid(Object value) {
			return cellEditor.isValid((String) value);
		}

	}

	protected class ValidatingDeactivateListener implements Listener {
		private ValidatingTextCellEditor cellEditor;

		public ValidatingDeactivateListener(ValidatingTextCellEditor cellEditor) {
			this.cellEditor = cellEditor;
		}

		public void handleEvent(Event e) {
			Display.getCurrent().asyncExec(new Runnable() {
				public void run() {
					if (cellEditor.getLastInvalidElement() != null) {
						AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayError(
										AuthoringUIResources.editProcessElementErrorDialog_title, 
										cellEditor.getErrorMessage());
						viewer.editElement(cellEditor.getLastInvalidElement(),
								cellEditor.getColumnIndex());
					}
				}

			});
		}
	}

	// private class ValidatingFocusAdapter extends FocusAdapter {
	// private ValidatingTextCellEditor cellEditor;
	//
	// ValidatingFocusAdapter(ValidatingTextCellEditor cellEditor) {
	// this.cellEditor = cellEditor;
	// }
	//
	// public void focusLost(FocusEvent e) {
	// Display.getCurrent().asyncExec(new Runnable() {
	//
	// public void run() {
	// if (cellEditor.getLastInvalidElement() != null) {
	// AuthoringUIPlugin
	// .getDefault()
	// .getMsgDialog()
	// .displayError(
	// AuthoringUIResources
	// .getString("AuthoringUI.editProcessElementErrorDialog.title"),
	// //$NON-NLS-1$
	// cellEditor.getErrorMessage());
	// viewer.editElement(cellEditor.getLastInvalidElement(),
	// cellEditor.getColumnIndex());
	// }
	// }
	//
	// });
	// }
	// }

	// private Composite parent = null;

	protected ColumnDescriptor[] columnDescriptors;

	// private boolean validNamePrompted = false;
	// private int promptCount = 0;

	// private MethodElement promptedElement;

	// private TableTreeViewer tableTreeViewer;

	protected AdapterFactory adapterFactory;

	// private int tabIndex;

	// private Object tabFolder;

	protected ProcessViewer viewer;

	protected TextCellEditor textCellEditor;

	protected ValidatingTextCellEditor nameCellEditor;

	protected ValidatingTextCellEditor predListCellEditor;

	protected ComboBoxCellEditor comboBoxCellEditor;

	protected CheckboxCellEditor checkBoxCellEditor;

	protected ValidatingTextCellEditor presentationNameCellEditor;

	// private HashMap columnIndexToNameMap;
	protected boolean isReadOnly;

	/**
	 * Creates an instance
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ProcessBreakdownStructureFormPage(FormEditor editor, String id,
			String title) {
		super(editor, id, title);
	}
	
	/**
	 * If returns true, editing is disabled or not available for the process 
	 * breakdown structure.
	 * 
	 */
	public boolean isReadOnly() {
		if (isReadOnly) {
			return isReadOnly;
		}
		return TngUtil.isLocked(process);
	}
	
	/**
	 * If returns true, editing is disabled or not available for the process 
	 * breakdown structure.
	 * 
	 */
	public boolean basicIsReadOnly() {
		return isReadOnly;
	}

	/**
	 * Enables/disable modifying process breakdown structure
	 * 
	 * @param isReadOnly
	 */
	public void setReadOnly(boolean isReadOnly) {
		this.isReadOnly = isReadOnly;
	}

	/**
	 * Sets adapter factory to create item provider for each element in 
	 * the breakdown structure
	 *
	 * @param adapterFactory
	 */
	public void setAdapterFactory(AdapterFactory adapterFactory) {
		this.adapterFactory = adapterFactory;
	}

	/**
	 * Sets column descriptors to define the columns of the tree.
	 * 
	 * @param columnDescriptors
	 */
	public void setColumnDescriptors(ColumnDescriptor[] columnDescriptors) {
		this.columnDescriptors = columnDescriptors;

		if (adapterFactory instanceof IColumnAware) {
			Map<Integer, String> columnIndexToNameMap = new HashMap<Integer, String>();
			for (int i = 0; i < columnDescriptors.length; i++) {
				columnIndexToNameMap.put(new Integer(i),
						columnDescriptors[i].id);
			}
			((IColumnAware) adapterFactory)
					.setColumnIndexToNameMap(columnIndexToNameMap);
		}
	}
	
	protected void setText(BreakdownElement e, String prop, String txt) {
		IActionManager actionMgr = ((MethodElementEditor) getEditor())
				.getActionManager();
		if (prop == IBSItemProvider.COL_PREDECESSORS) {
			WorkBreakdownElement wbe = (WorkBreakdownElement) e;
			List<WorkOrder> addList = new ArrayList<WorkOrder>();
			List<WorkOrder> predToBeDeleted = new ArrayList<WorkOrder>();
			if(PredecessorList.prepareUpdatePredecessors(adapterFactory, wbe, txt, addList, predToBeDeleted)) {
				if(!predToBeDeleted.isEmpty()) {
					actionMgr.doAction(
							IActionManager.REMOVE_MANY,
							wbe,
							UmaPackage.eINSTANCE.getWorkBreakdownElement_LinkToPredecessor(),
							predToBeDeleted, -1);
				}
				if(!addList.isEmpty()) {
					actionMgr
					.doAction(
							IActionManager.ADD_MANY,
							wbe,
							UmaPackage.eINSTANCE
									.getWorkBreakdownElement_LinkToPredecessor(),
							addList, -1);
				}
			}
			
			return;
		}
		
		ProcessUtil.setAttribute(actionMgr, e, prop, txt);
	}

	protected String getText(BreakdownElement e, String prop) {
		// handle editing presentation name of extending/contributing activity
		// specially
		//
		if (prop == IBSItemProvider.COL_PRESENTATION_NAME
				&& ProcessUtil.isExtendingOrLocallyContributing(e)) {
			return ((DescribableElement) e).getPresentationName();
		}

		IBSItemProvider adapter = (IBSItemProvider) adapterFactory.adapt(e,
				ITreeItemContentProvider.class);
		return adapter.getAttribute(e, prop);
	}

	/**
	 * Set tab index for the form page
	 * @param id
	 */
	public void setTabIndex(int id) {
		 tabIndex = id;
	}
	
	/**
	 * Return tab index for the form page
	 */
	public int getTabIndex() {
		return tabIndex;
	}

	/**
	 * Set tab folder
	 * @param tabFolder
	 */
	public void setTabFolder(CTabFolder tabFolder) {
		// this.tabFolder = tabFolder;
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {
		// create form toolkit
		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();
		form.setText(getEditor().getPartName());

		TableWrapLayout layout = new TableWrapLayout();
		form.getBody().setLayout(layout);

		Section section = toolkit.createSection(form.getBody(),
				Section.EXPANDED | Section.TITLE_BAR);
		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB,
				TableWrapData.FILL_GRAB);
		section.setLayoutData(td);
		section
				.setText(getTitle()
						+ " " + AuthoringUIResources.processBreakdownStructureFormPage_BSEditor); //$NON-NLS-1$ 
		section.setLayout(new GridLayout());

		Composite composite = toolkit.createComposite(section);
		// composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setLayout(new GridLayout());
		section.setClient(composite);
		Control control = createControl(composite);
		toolkit.adapt(control, true, true);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.verticalIndent = 10;
		gridData.heightHint = 600;
		control.setLayoutData(gridData);

		ProcessEditor editor = (ProcessEditor) getEditor();
		// editor.createContextMenuFor(tableTreeViewer);
		editor.createContextMenuFor(viewer);

		setInput(process);
	}


	/**
	 * Create control
	 * @param parent
	 * @return
	 * 			Control
	 */
	public Control createControl(Composite parent) {
		// this.parent = parent;
		// This is the page for the table tree viewer.
		//
		Composite comp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		comp.setLayout(layout);
		GridData gd = new GridData(GridData.BEGINNING | GridData.FILL_BOTH);
		comp.setLayoutData(gd);
		
		viewer = new ProcessViewer(comp, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		viewer.setUseHashlookup(true);
		viewer.getControl().setLayoutData(gd);
		viewer.setupColumns(columnDescriptors);
		viewer.setContentProvider(new ExposedAdapterFactoryContentProvider(
				adapterFactory));
		viewer.setLabelProvider(new ProcessAdapterFactoryLabelProvider(adapterFactory));


		viewer.setActionManager(((MethodElementEditor) getEditor())
			.getActionManager());

		textCellEditor = new TextCellEditor(viewer.getCellEditorParent());
		comboBoxCellEditor = new ComboBoxCellEditor(viewer
				.getCellEditorParent(), new String[] { TXT_TRUE, TXT_FALSE });
		checkBoxCellEditor = new CheckboxCellEditor(viewer
				.getCellEditorParent());

		//set all editors and modifiers
		setAllEditorsAndModifiers();
		
		viewer.setAutoExpandLevel(3);
		viewer.setInput(process);

//		 int dndOperations = DND.DROP_COPY | DND.DROP_MOVE ;
//		 Transfer[] transfers = new Transfer[] { TNGObjTransfer.getInstance()
//		 };
//		 tableTreeViewer.addDropSupport(DND.DROP_MOVE, transfers, new
//		 BSTabDropAdapter(tableTreeViewer));

		// BSEditorView.getView().createContextMenuFor(tableTreeViewer);
		// return tableTreeViewer.getControl();

		setContextHelp(viewer.getControl());

		return comp;

	}

	
	/**
	 * Set all editors and modifiers for the viewer
	 */
	private void setAllEditorsAndModifiers()
	{
		setCellEditors();
		setCheckBoxCellEditors();

		// Set the cell modifier
		viewer.setCellModifier(new ICellModifier() {
			/**
			 * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
			 */
			public boolean canModify(Object element, String property) {
				// System.out.println("canModify: element="+element+",
				// property="+property);

				if (isReadOnly() ||
						(element instanceof BreakdownElementWrapperItemProvider && ((BreakdownElementWrapperItemProvider) element).isReadOnly())) {
					return false;
				}

//				if (!(element instanceof BreakdownElement)
//						&& !(element instanceof BreakdownElementWrapperItemProvider && !((BreakdownElementWrapperItemProvider) element)
//								.isReadOnly())) {
//					Collection types = new ArrayList();
//					types.add(VariabilityType.EXTENDS_LITERAL);
//					if (TngUtil
//							.isGeneralizer(
//									(VariabilityElement) ((BreakdownElementWrapperItemProvider) element)
//											.getOwner(), types))
//						return false;
//				}
				
				if (property == IBSItemProvider.COL_ID
						|| property == IBSItemProvider.COL_TYPE
						|| property == IBSItemProvider.COL_MODEL_INFO
						|| property == IBSItemProvider.COL_DELIVERABLE) {
					return false;
				}

				element = TngUtil.unwrap(element);
				if (!(element instanceof WorkProductDescriptor)
						&& (property == IBSItemProvider.COL_ENTRY_STATE || property == IBSItemProvider.COL_EXIT_STATE)) {
					return false;
				}
				if (!(element instanceof WorkBreakdownElement)
						&& (property == IBSItemProvider.COL_PREDECESSORS)) {
					return false;
				}

				return true;
			}

			/**
			 * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
			 */
			public Object getValue(Object element, String property) {
				// System.out.println("getValue: element="+element+",
				// property="+property);
				BreakdownElement e = (BreakdownElement) TngUtil.unwrap(element);
				String val = getText(e, property);
				switch (getCellEditorType(property)) {
				case ColumnDescriptor.CELL_EDITOR_TYPE_COMBO_BOOLEAN:
					if (TXT_TRUE.equalsIgnoreCase(val)) {
						return new Integer(0);
					} else {
						return new Integer(1);
					}
				case ColumnDescriptor.CELL_EDITOR_TYPE_TEXT:
					return val;
				case ColumnDescriptor.CELL_EDITOR_TYPE_CHECK_BOOLEAN:
					return Boolean.valueOf(val);
				default:
					return val;
				}
			}

			/**
			 * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
			 */
			public void modify(final Object element, String property,
					Object value) {
				// System.out.println("modify: element="+element+",
				// property="+property+", value="+value);
				Widget item = (Widget) element;
				BreakdownElement be = (BreakdownElement) TngUtil.unwrap(item
						.getData());

				final int columnIndex = getColumnIndex(property);
				CellEditor cellEditor = viewer.getCellEditors()[columnIndex];
				String errMsg = cellEditor.getErrorMessage();
				if (errMsg != null) {
					return;
				}

				String txt;
				switch (getCellEditorType(property)) {
				case ColumnDescriptor.CELL_EDITOR_TYPE_TEXT:
					txt = (String) value;
					break;
				case ColumnDescriptor.CELL_EDITOR_TYPE_COMBO_BOOLEAN:
					int index = ((Number) value).intValue();
					if (index == 0) {
						txt = TXT_TRUE;
					} else {
						txt = TXT_FALSE;
					}
					break;
				case ColumnDescriptor.CELL_EDITOR_TYPE_CHECK_BOOLEAN:
					txt = value.toString();
					break;
				default:
					txt = TngUtil.checkNull(value.toString());
				}
				setText(be, property, txt);
				if (property == IBSItemProvider.COL_PRESENTATION_NAME
						&& StrUtil.isBlank(be.getName())) {
					be.setName(txt);
				}
				
				viewer.refresh();
			}
		});
	}
	
	
	/**
	 * Get cell editor for the given column
	 * @param columnID
	 * @param columnIndex
	 * @return
	 * 			Cell Editor
	 */
	public CellEditor getCellEditor(String columnID, int columnIndex) {
		if (columnID == IBSItemProvider.COL_PRESENTATION_NAME) {
			if (presentationNameCellEditor == null) {
				presentationNameCellEditor = new ValidatingTextCellEditor(
						viewer.getCellEditorParent()) {
					protected String isValid(Object e, String txt) {
						e = TngUtil.unwrap(e);
						Suppression suppression = ((ProcessEditor)getEditor()).getSuppression();
						boolean ignoreSuppressed = true;
						return ProcessUtil
								.checkBreakdownElementPresentationName(
										adapterFactory, (BreakdownElement) e,
										txt, suppression, ignoreSuppressed);
					}
				};

				presentationNameCellEditor
						.setValidator(new CellEditorValidator(
								presentationNameCellEditor));
				// presentationNameCellEditor.getControl().addFocusListener(new
				// ValidatingFocusAdapter(presentationNameCellEditor));
				presentationNameCellEditor.getControl().addListener(
						SWT.Deactivate,
						new ValidatingDeactivateListener(
								presentationNameCellEditor));
			}
			presentationNameCellEditor.setColumnIndex(columnIndex);

			return presentationNameCellEditor;
		} else if (columnID == IBSItemProvider.COL_NAME) {
			if (nameCellEditor == null) {
				nameCellEditor = new ValidatingTextCellEditor(viewer
						.getCellEditorParent()) {
					protected String isValid(Object e, String txt) {
						e = TngUtil.unwrap(e);
						Suppression suppression = ((ProcessEditor)getEditor()).getSuppression();
						return TngUtil
								.checkActivityName(adapterFactory, e, txt, suppression);
					}
				};
				nameCellEditor.setValidator(new CellEditorValidator(
						nameCellEditor));
				// nameCellEditor.getControl().addFocusListener(new
				// ValidatingFocusAdapter(nameCellEditor));
				nameCellEditor.getControl().addListener(SWT.Deactivate,
						new ValidatingDeactivateListener(nameCellEditor));
			}
			nameCellEditor.setColumnIndex(columnIndex);

			return nameCellEditor;
		} else if (columnID == IBSItemProvider.COL_PREDECESSORS) {
			if (predListCellEditor == null) {
				predListCellEditor = new ValidatingTextCellEditor(viewer
						.getCellEditorParent()) {
					protected String isValid(Object e, String txt) {
						e = TngUtil.unwrap(e);
						return ProcessUtil.checkPredecessorList(
								(WorkBreakdownElement) e, txt, adapterFactory,
								process, null);
					}
				};
				predListCellEditor.setValidator(new CellEditorValidator(
						predListCellEditor));
				// predListCellEditor.getControl().addFocusListener(new
				// ValidatingFocusAdapter(predListCellEditor));
				predListCellEditor.getControl().addListener(SWT.Deactivate,
						new ValidatingDeactivateListener(predListCellEditor));
			}
			predListCellEditor.setColumnIndex(columnIndex);

			return predListCellEditor;
		}
		return null;
	}

	/**
	 * Set cell editors for all columns 
	 */
	public void setCellEditors() {
		CellEditor[] cellEditors = new CellEditor[columnDescriptors.length];
		for (int i = 0; i < columnDescriptors.length; i++) {
			CellEditor cellEditor = getCellEditor(columnDescriptors[i].id, i);
			if (cellEditor != null) {
				cellEditors[i] = cellEditor;
			} else {
				switch (columnDescriptors[i].cellEditorType) {
				case ColumnDescriptor.CELL_EDITOR_TYPE_COMBO_BOOLEAN:
					cellEditors[i] = comboBoxCellEditor;
					break;
				case ColumnDescriptor.CELL_EDITOR_TYPE_TEXT:
					cellEditors[i] = textCellEditor;
					break;
				case ColumnDescriptor.CELL_EDITOR_TYPE_CHECK_BOOLEAN:
					cellEditors[i] = checkBoxCellEditor;
					break;
				default:
					cellEditors[i] = null;
				}
			}
		}
		viewer.setCellEditors(cellEditors);
	}
	
	
	

	/**
	 * Set input for viewer
	 * @param newInput
	 */
	public void setInput(Object newInput) {
		if (newInput instanceof Process) {
			process = (Process) newInput;
			if (process.eContainer() instanceof ProcessComponent) {
				viewer.setInput(process.eContainer());
				// viewer.expandAll();
//				viewer.reveal(process);
				return;
			}
		}
		viewer.setInput(null);
	}

	protected int getColumnIndex(String property) {
		Object[] colProps = viewer.getColumnProperties();
		for (int i = 0; i < colProps.length; i++) {
			if (colProps[i] == property)
				return i;
		}
		return -1;
	}

	protected int getCellEditorType(String property) {
		for (int i = 0; i < columnDescriptors.length; i++) {
			ColumnDescriptor desc = columnDescriptors[i];
			if (desc.id == property) {
				return desc.cellEditorType;
			}
		}
		return ColumnDescriptor.CELL_EDITOR_TYPE_NONE;
	}

	/**
	 * Return viewer
	 */
	public Viewer getViewer() {
		return viewer;
	}

	/**
	 * Set process as input
	 * @param proc
	 */
	public void setProcess(Process proc) {
		if (process != proc) {
			setInput(proc);
		}
	}

	/**
	 * Returns the adapterFactory.
	 */
	public AdapterFactory getAdapterFactory() {
		return adapterFactory;
	}

	/**
	 * Update columns with new column descriptors
	 * @param newColumnDescriptors
	 */
	public void updateColumns(ColumnDescriptor[] newColumnDescriptors) {
		setColumnDescriptors(newColumnDescriptors);
		viewer.setupColumns(newColumnDescriptors);	
		setAllEditorsAndModifiers();
		viewer.refresh();
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#dispose()
	 */
	public void dispose() {
		// if(adapterFactory instanceof ComposeableAdapterFactory) {
		// ((ComposedAdapterFactory)adapterFactory).dispose();
		// }

		if (viewer.getContentProvider() != null) {
			viewer.getContentProvider().dispose();
		}

		if (viewer.getLabelProvider() != null) {
			viewer.getLabelProvider().dispose();
		}

		super.dispose();
	}

	protected void setContextHelp(Control control) {
		AdapterFactory adapterFac = getAdapterFactory();
		AdapterFactory allFac = TngAdapterFactory.INSTANCE
				.getProcessComposedAdapterFactory();
		AdapterFactory wbsFac = TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory();
		AdapterFactory obsFac = TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory();
		AdapterFactory pbsFac = TngAdapterFactory.INSTANCE
				.getPBS_ComposedAdapterFactory();

		// System.out.println("$$$ DEBUG: current dadpter fac = " + adapterFac);
		// System.out.println("$$$ DEBUG: all dadpter fac = " + allFac);
		// System.out.println("$$$ DEBUG: wbs dadpter fac = " + wbsFac);
		// System.out.println("$$$ DEBUG: obs dadpter fac = " + obsFac);
		// System.out.println("$$$ DEBUG: pbs dadpter fac = " + pbsFac);

		if (adapterFac == wbsFac)
			PlatformUI.getWorkbench().getHelpSystem().setHelp(control,
					AuthoringUIHelpContexts.FORM_EDITOR_PROCESS_WBS_CONTEXT);
		else if (adapterFac == obsFac)
			PlatformUI.getWorkbench().getHelpSystem().setHelp(control,
					AuthoringUIHelpContexts.FORM_EDITOR_PROCESS_OBS_CONTEXT);
		else if (adapterFac == pbsFac)
			PlatformUI.getWorkbench().getHelpSystem().setHelp(control,
					AuthoringUIHelpContexts.FORM_EDITOR_PROCESS_PBS_CONTEXT);
		else if (adapterFac == allFac)
			PlatformUI.getWorkbench().getHelpSystem().setHelp(control,
					AuthoringUIHelpContexts.FORM_EDITOR_PROCESS_ALLBS_CONTEXT);
	}
	
	/**
	 * Gets additional actions to contribute to the context menu.
	 * 
	 * @return
	 * 			Actions
	 */
	public IAction[] getAdditionalActions() {
		return null;
	}
	
	/**
	 * Set check box cell editors
	 */
	public void setCheckBoxCellEditors() {
		AbstractCheckBoxCellEditor[] cellEditors = new AbstractCheckBoxCellEditor[columnDescriptors.length];
		for (int i = 0; i < columnDescriptors.length; i++) {
			switch (columnDescriptors[i].cellEditorType) {
				case ColumnDescriptor.CELL_EDITOR_TYPE_CHECK_BOOLEAN:
					cellEditors[i] = new ProcessCheckBoxCellEditor(viewer
							.getCellEditorParent());
					break;
				default:
					cellEditors[i] = null;
			}
		}
		((ProcessViewer) viewer).setCheckBoxCellEditors(cellEditors);
	}
	
}