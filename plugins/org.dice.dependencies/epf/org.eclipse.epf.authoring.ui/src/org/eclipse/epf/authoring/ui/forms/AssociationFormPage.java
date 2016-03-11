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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIExtensionManager;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.actions.LibraryViewFindElementAction;
import org.eclipse.epf.authoring.ui.dialogs.ContentElementsOrderDialog;
import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditor;
import org.eclipse.epf.authoring.ui.providers.FormPageProviderExtender;
import org.eclipse.epf.authoring.ui.util.EditorsContextHelper;
import org.eclipse.epf.authoring.ui.util.UIHelper;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.MoveInListCommand;
import org.eclipse.epf.library.edit.util.CategorySortHelper;
import org.eclipse.epf.library.edit.util.ContentElementOrderList;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.library.util.LibraryManager;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;


/**
 * The base class for all Association type form pages in the Method editors.
 * 
 * @author Jeff Hardy
 * @author Shashidhar Kannoori
 * @author Kelvin Low
 * @since 1.0
 */
public class AssociationFormPage extends BaseFormPage implements IMenuListener {

	protected static final String PACKAGE_PREFIX = "AuthoringUI_"; //$NON-NLS-1$	

	private	Font boldFont;
	
	protected FormPageProviderExtender providerExtender;

	protected IStructuredContentProvider contentProviderSelected;

	protected ILabelProvider labelProviderSelected = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public String getColumnText(Object object, int columnIndex) {			
			return getDecorator(object, null) + TngUtil.getLabelWithPath(object); 
		}
	};

	protected IStructuredContentProvider contentProviderSelected2;

	protected ILabelProvider labelProviderSelected2 = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public String getColumnText(Object object, int columnIndex) {
			return TngUtil.getLabelWithPath(object);
		}
	};

	protected IStructuredContentProvider contentProviderSelected3;

	protected ILabelProvider labelProviderSelected3 = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public String getColumnText(Object object, int columnIndex) {
			return TngUtil.getLabelWithPath(object);
		}
	};

	protected Section aSection;

	protected Composite aComposite;
	
	protected Composite category1pane1;
	protected Composite category1pane2;
	protected Composite category2pane1;
	protected Composite category2pane2;
	protected Composite category3pane1;
	protected Composite category3pane2;

	protected TableViewer viewer_selected, viewer_selected2, viewer_selected3;

	protected Table ctrl_selected, ctrl_selected2, ctrl_selected3;

	protected Button ctrl_add, ctrl_remove, ctrl_add2, ctrl_remove2, ctrl_add3,
			ctrl_remove3;

	protected Button ctrl_up1, ctrl_down1, ctrl_up2, ctrl_down2, ctrl_up3,
			ctrl_down3;

	protected Text ctrl_briefDesc;

	protected Button ctrl_select, ctrl_clear;

	protected ArrayList usedCategories = new ArrayList();

	protected Label label_Category, label_Category2, label_Category3;

	private boolean useCategory1 = true;

	private boolean useCategory2 = true;

	private boolean useCategory3 = true;

	private boolean allowChange1 = true;

	private boolean allowChange2 = true;

	private boolean allowChange3 = true;

	protected boolean isUpAndDownButtonsRequired1 = false;

	protected boolean isUpAndDownButtonsRequired2 = false;

	protected boolean isUpAndDownButtonsRequired3 = false;

	protected boolean categoryIsSingleSelection1 = false;

	protected boolean categoryIsSingleSelection2 = false;

	protected boolean categoryIsSingleSelection3 = false;

	protected LibraryViewFindElementAction libraryViewFindElementAction;
	// strings used in the form
	protected String formNamePrefix = "Form prefix"; //$NON-NLS-1$

	protected String sectionName = "Section name"; //$NON-NLS-1$

	protected String sectionDesc = "Section description"; //$NON-NLS-1$

	protected String selectedLabel = "Selected Label 1"; //$NON-NLS-1$

	protected String selectedLabel2 = "Selected Label 2"; //$NON-NLS-1$

	protected String selectedLabel3 = "Selected Label 3"; //$NON-NLS-1$

	public void addItemsToModel(List items, int ix) {
		ArrayList newItems = items instanceof ArrayList ? (ArrayList) items : new ArrayList(items);		
		if (ix == 1) {
			addItemsToModel1(newItems);
		}
		if (ix == 2) {
			addItemsToModel2(newItems);	
		}
		if (ix == 3) {
			addItemsToModel3(newItems);	
		}
	}
	
	public void removeItemsFromModel(List items, int ix) {
		ArrayList newItems = items instanceof ArrayList ? (ArrayList) items : new ArrayList(items);		
		if (ix == 1) {
			removeItemsFromModel1(newItems);
		}
		if (ix == 2) {
			removeItemsFromModel2(newItems);	
		}
		if (ix == 3) {
			removeItemsFromModel3(newItems);	
		}
	}
	
	// Data model methods.
	protected void addItemsToModel1(ArrayList newItems) {
	}

	protected void addItemsToModel2(ArrayList newItems) {
	}

	protected void addItemsToModel3(ArrayList newItems) {
	}

	protected void removeItemsFromModel1(ArrayList rmItems) {
	}

	protected void removeItemsFromModel2(ArrayList rmItems) {
	}

	protected void removeItemsFromModel3(ArrayList rmItems) {
	}

	protected void moveUpItemsInSelectedItems1(ArrayList moveUpItems) {
		if (!moveUpItems.isEmpty()) {
			EStructuralFeature feature = getOrderFeature();
			ContentElementOrderList orderList = getContentElementOrderList();
			if (feature != null && orderList != null) {
				MoveInListCommand cmd = new MoveInListCommand(
						contentElement, moveUpItems, orderList,
						feature, MoveInListCommand.UP);
				getActionManager().execute(cmd);
			}
		}
	}

	protected void moveDownItemsInSelectedItems1(ArrayList moveDownItems) {
		if (!moveDownItems.isEmpty()) {
			EStructuralFeature feature = getOrderFeature();
			ContentElementOrderList orderList = getContentElementOrderList();
			if (feature != null && orderList != null) {
				MoveInListCommand cmd = new MoveInListCommand(
						contentElement, moveDownItems, orderList,
						feature, MoveInListCommand.DOWN);
				getActionManager().execute(cmd);
			}
		}
	}
	
	// Viewer methods.
	protected void initContentProviderSelected() {
	}

	protected void initContentProviderSelected2() {
	}

	protected void initContentProviderSelected3() {
	}

	public ContentElementOrderList getContentElementOrderList() {
		return null;
	}
	
	protected String[] getModelStructurePath() {
		return null;
	}
	
	protected Object getContentElement() {
		return null;
	}

	protected String getTabString() {
		return "contentelement";} //$NON-NLS-1$

	protected String getTabString2() {
		return getTabString();
	}

	protected String getTabString3() {
		return getTabString();
	}

	/*
	 * getFilterTypes should be override to pass types.
	 */
	protected String[] getFilterTypes(){
		return null;
	}
	/*
	 * getFilterTypes should be override to pass types.
	 */
	protected String[] getFilterTypes2(){
		return null;
	}
	/*
	 * getFilterTypes should be override to pass types.
	 */
	protected String[] getFilterTypes3(){
		return null;
	}

	protected MethodElementEditor editor = (MethodElementEditor) getEditor();

	// Filter info.
	protected IFilter filter, filter2, filter3;

	protected IFilter getFilter() {
		return filter;
	}

	protected IFilter getFilter2() {
		return filter;
	}

	protected IFilter getFilter3() {
		return filter;
	}

	public IFilter getFilter(int ix) {
		if (ix == 1) {
			return getFilter();
		}
		if (ix == 2) {
			return getFilter2();
		}
		if (ix == 3) {
			return getFilter3();
		}
		return null;
	}
	
	// Ordering of contributed categorized elements in custom
	// categories
	protected Button ctrl_ordering;

	protected ComboViewer viewer_sort1;
	
	protected IStructuredContentProvider contentProviderSort = new AdapterFactoryContentProvider(
			TngAdapterFactory.INSTANCE.getNavigatorView_ComposedAdapterFactory()) {
		public Object[] getElements(Object object) {
			List<String> sortTypesList = CategorySortHelper.getCategorySortTypes();
			return sortTypesList.toArray();
		}
	};

	protected ILabelProvider labelProviderSort = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public String getText(Object object) {
			if (object instanceof String) {
				String str = ((String)object);
				return CategorySortHelper.getSortTypeDisplayName(str);
			}
			return null;
		}
	};
	
	/**
	 * Creates a new instance.
	 */
	public AssociationFormPage(FormEditor editor, String id, String name) {
		super(editor, id, name);
		this.editorTabName = name;
		providerExtender = AuthoringUIExtensionManager.getInstance().createFormPageProviderExtender(this);
		AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE.getNavigatorView_ComposedAdapterFactory();
		labelProviderSelected = providerExtender.newLabelProvider(adapterFactory, 1);
		labelProviderSelected2 = providerExtender.newLabelProvider(adapterFactory, 2);
		labelProviderSelected3 = providerExtender.newLabelProvider(adapterFactory, 3);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.BaseFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
	}

	/**
	 * @see org.eclipse.ui.forms.editor.createFormContent(IManagedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {
		super.createFormContent(managedForm);
		createFormContent_(managedForm);
	}
	
	protected void createFormContent_(IManagedForm managedForm) {
		UIHelper.setFormText(form, contentElement);
		
		// Create a section.
		aSection = toolkit.createSection(form.getBody(), Section.TITLE_BAR
				| Section.DESCRIPTION | Section.TWISTIE | Section.EXPANDED);
		TableWrapData td = new TableWrapData(TableWrapData.FILL_GRAB);
		aSection.setLayoutData(td);
		aSection.setText(getSectionName());
		aSection.setDescription(getSectionDescription());
		aSection.setLayout(new GridLayout());

		aComposite = toolkit.createComposite(aSection);
		aComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		aComposite.setLayout(new GridLayout(2, false));
		aSection.setClient(aComposite);

		int numTablesUsed = 0;

		if (useCategory1)
			numTablesUsed++;
		if (useCategory2)
			numTablesUsed++;
		if (useCategory3)
			numTablesUsed++;

		if (useCategory1) {
			category1pane1 = createComposite(toolkit, aComposite, 1);
			label_Category = createLabel(toolkit, category1pane1, getSelectedLabel());

			if (categoryIsSingleSelection1)
				ctrl_selected = createTable(toolkit, category1pane1, SINGLE_ROW);
			else {
				if (numTablesUsed == 1)
					ctrl_selected = createTable(toolkit, category1pane1, LARGE_SIZE);
				else if (numTablesUsed == 2)
					ctrl_selected = createTable(toolkit, category1pane1, MEDIUM_SIZE);
				else
					ctrl_selected = createTable(toolkit, category1pane1, SMALL_SIZE);
			}

			libraryViewFindElementAction = new LibraryViewFindElementAction(AuthoringUIResources.actionLabel_findElementInLibNav); 
			viewer_selected = new TableViewer(ctrl_selected);
			viewer_selected.setLabelProvider(labelProviderSelected);
			initContentProviderSelected();
			viewer_selected.setInput(contentElement);
			if (!isUpAndDownButtonsRequired1)
				viewer_selected.setSorter(new ViewerSorter());

			createContextMenuFor(viewer_selected);
			toolkit.paintBordersFor(category1pane1);

			category1pane2 = createCompositeForButtons(toolkit, aComposite);
			if (allowChange1) {
				ctrl_add = createButton(toolkit, category1pane2,
						(categoryIsSingleSelection1) ? SELECT_BUTTON
								: ADD_BUTTON);
				ctrl_remove = createButton(toolkit, category1pane2,
						(categoryIsSingleSelection1) ? CLEAR_BUTTON
								: REMOVE_BUTTON);

				if (isUpAndDownButtonsRequired1) {
					ctrl_up1 = createButton(toolkit, category1pane2, UP_BUTTON);
					ctrl_down1 = createButton(toolkit, category1pane2, DOWN_BUTTON);
					if (contentElement instanceof ContentCategory) {
						ctrl_ordering = createButton(toolkit, category1pane2, ORDER_BUTTON);
						ctrl_ordering.setEnabled(true);
						Combo ctrl_sort1 = createComboWithLabel(toolkit, category1pane2, LibraryEditResources.SortType_Label);
						viewer_sort1 = new ComboViewer(ctrl_sort1);
						viewer_sort1.setContentProvider(contentProviderSort);
						viewer_sort1.setLabelProvider(labelProviderSort);
						viewer_sort1.setInput(contentElement);
						// set initial selection
						String sortType = CategorySortHelper.getCategorySortValue(contentElement);
						viewer_sort1.setSelection(new StructuredSelection(sortType), true);
					}

					// ctrl_ordering.setLayoutData(new
					// GridData(GridData.FILL_HORIZONTAL));

				}
			}
			toolkit.paintBordersFor(category1pane2);
		}

		if (useCategory2) {
			category2pane1 = createComposite(toolkit, aComposite, 1);
			label_Category2 = createLabel(toolkit, category2pane1, getSelectedLabel2());
			if (categoryIsSingleSelection2)
				ctrl_selected2 = createTable(toolkit, category2pane1, SINGLE_ROW);
			else {
				if (numTablesUsed == 1)
					ctrl_selected2 = createTable(toolkit, category2pane1, LARGE_SIZE);
				else if (numTablesUsed == 2)
					ctrl_selected2 = createTable(toolkit, category2pane1, MEDIUM_SIZE);
				else
					ctrl_selected2 = createTable(toolkit, category2pane1, SMALL_SIZE);
			}
			viewer_selected2 = new TableViewer(ctrl_selected2);
			viewer_selected2.setLabelProvider(labelProviderSelected2);
			initContentProviderSelected2();
			viewer_selected2.setInput(contentElement);
			viewer_selected2.setSorter(new ViewerSorter());
			createContextMenuFor(viewer_selected2);

			toolkit.paintBordersFor(category2pane1);

			category2pane2 = createCompositeForButtons(toolkit, aComposite);
			if (allowChange2) {
				ctrl_add2 = createButton(toolkit, category2pane2, ADD_BUTTON);
				ctrl_remove2 = createButton(toolkit, category2pane2, REMOVE_BUTTON);

				if (isUpAndDownButtonsRequired2) {
					ctrl_up2 = createButton(toolkit, category2pane2, UP_BUTTON);
					ctrl_down2 = createButton(toolkit, category2pane2, DOWN_BUTTON);
				}
			}
			toolkit.paintBordersFor(category2pane2);
		}

		if (useCategory3) {
			category3pane1 = createComposite(toolkit, aComposite, 1);
			label_Category3 = createLabel(toolkit, category3pane1, getSelectedLabel3());
			if (categoryIsSingleSelection3)
				ctrl_selected3 = createTable(toolkit, category3pane1, SINGLE_ROW);
			else {
				if (numTablesUsed == 1)
					ctrl_selected3 = createTable(toolkit, category3pane1, LARGE_SIZE);
				else if (numTablesUsed == 2)
					ctrl_selected3 = createTable(toolkit, category3pane1, MEDIUM_SIZE);
				else
					ctrl_selected3 = createTable(toolkit, category3pane1, SMALL_SIZE);
			}
			viewer_selected3 = new TableViewer(ctrl_selected3);
			viewer_selected3.setLabelProvider(labelProviderSelected3);
			initContentProviderSelected3();
			viewer_selected3.setInput(contentElement);
			viewer_selected3.setSorter(new ViewerSorter());
			createContextMenuFor(viewer_selected3);

			toolkit.paintBordersFor(category3pane1);

			category3pane2 = createCompositeForButtons(toolkit, aComposite);
			if (allowChange3) {
				ctrl_add3 = createButton(toolkit, category3pane2, ADD_BUTTON);
				ctrl_remove3 = createButton(toolkit, category3pane2, REMOVE_BUTTON);

				if (isUpAndDownButtonsRequired3) {
					ctrl_up3 = createButton(toolkit, category3pane2, UP_BUTTON);
					ctrl_down3 = createButton(toolkit, category3pane2, DOWN_BUTTON);
				}
			}
			toolkit.paintBordersFor(category3pane2);
		}

		setEnabledAddButtons(true);

		setWidgetWidths();
		Composite pane7 = createComposite(toolkit, aComposite,
				GridData.FILL_BOTH, 1, 1, 1);
		ctrl_briefDesc = createTextEditWithLabelLarge(
				toolkit,
				pane7,
				AuthoringUIResources.AssociationFormPage_BriefDescriptionLabel); 
		ctrl_briefDesc.setEditable(false);
		toolkit.paintBordersFor(pane7);
		toolkit.paintBordersFor(aComposite);

		addListeners();

		setContextHelp();

	}

	protected void setEnabledAddButtons(boolean value) {
		if (allowChange1)
			ctrl_add.setEnabled(value);
		if (useCategory2 && allowChange2)
			ctrl_add2.setEnabled(value);
		if (useCategory3 && allowChange3)
			ctrl_add3.setEnabled(value);
	}

	protected void setEnabledRemoveButtons(boolean value) {
		if (allowChange1)
			ctrl_remove.setEnabled(value);
		if (useCategory2 && allowChange2)
			ctrl_remove2.setEnabled(value);
		if (useCategory3 && allowChange3)
			ctrl_remove3.setEnabled(value);
	}

	protected void disableButtonsAndDeselectTables() {
		// setEnabledAddButtons(false);
		setEnabledRemoveButtons(false);
		viewer_selected.setSelection(null, true);
		if (useCategory2)
			viewer_selected2.setSelection(null, true);
		if (useCategory3)
			viewer_selected3.setSelection(null, true);
		ctrl_briefDesc.setText(""); //$NON-NLS-1$
	}

	protected void refreshViewers() {

		viewer_selected.refresh();
		// Check if existing element is contributor/contributes for standard
		// categories
		// should not shown in the tableviewer.
		// Commented below to allow contributors to display in the list box
		// if(contentElement instanceof ContentCategory){
		// if(!(contentElement instanceof CustomCategory)){
		// Object[] elements =
		// contentProviderSelected.getElements(contentElement);
		// for(int i=0; i<elements.length; i++){
		// Object element = elements[i];
		// if(element instanceof ContentElement){
		// if(UIHelper.isContributor((ContentElement)element)){
		// viewer_selected.remove(element);
		// }
		// }
		// }
		// }
		// }

		if (useCategory2) {
			viewer_selected2.refresh();
			// if(contentElement instanceof ContentCategory){
			// Object[] elements =
			// contentProviderSelected2.getElements(contentElement);
			// for(int i=0; i<elements.length; i++){
			// Object element = elements[i];
			// if(element instanceof ContentElement){
			// if(UIHelper.isContributor((ContentElement)element)){
			// viewer_selected2.remove(element);
			// }
			// }
			// }
			// }

		}
		if (useCategory3) {
			viewer_selected3.refresh();
			// if(contentElement instanceof ContentCategory){
			// Object[] elements =
			// contentProviderSelected3.getElements(contentElement);
			// for(int i=0; i<elements.length; i++){
			// Object element = elements[i];
			// if(element instanceof ContentElement){
			// if(UIHelper.isContributor((ContentElement)element)){
			// viewer_selected3.remove(element);
			// }
			// }
			// }
			// }
		}
		ctrl_briefDesc.setText(""); //$NON-NLS-1$

	}

	/**
	 * Add listeners
	 * 
	 */
	protected void addListeners() {
		form.addListener(SWT.Activate, new Listener() {
			public void handleEvent(Event e) {
				if (form != null && !form.isDisposed()) {
					UIHelper.setFormText(form, contentElement);
				}
				refreshViewers();
				if (TngUtil.isLocked(contentElement)) {
					enableControls(false);
				} else {
					enableControls(true);
				}
			}
		});
		
		form.addListener(SWT.Deactivate, new Listener() {
			public void handleEvent(Event e) {
				if (form != null && !form.isDisposed()) {
					UIHelper.setFormText(form, contentElement);
				}
				refreshViewers();
				if (TngUtil.isLocked(contentElement)) {
					enableControls(false);
				} else {
					enableControls(true);
				}
			}
		});

		if (useCategory1) {
			// Add focus listener on primary tasks list
			ctrl_selected.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					disableButtonsAndDeselectTables();

					IStructuredSelection selection = (IStructuredSelection) viewer_selected
							.getSelection();
					if (allowChange1 && selection.size() > 0
							&& !TngUtil.isLocked(contentElement)) {
						ctrl_remove.setEnabled(true);
					}
				}
			});

			viewer_selected
					.addSelectionChangedListener(new ISelectionChangedListener() {

						public void selectionChanged(SelectionChangedEvent event) {
							IStructuredSelection selection = (IStructuredSelection) viewer_selected
									.getSelection();
							libraryViewFindElementAction.selectionChanged(event);
							if (allowChange1 && selection.size() > 0
									&& !TngUtil.isLocked(contentElement)) {
								ctrl_remove.setEnabled(true);
							}
							enableUpDownButtons1();

							if (selection.size() == 1) {
								String desc = ((MethodElement) selection
										.getFirstElement())
										.getBriefDescription();
								if (desc == null) {
									desc = ""; //$NON-NLS-1$
								}
								ctrl_briefDesc.setText(desc);
							} else if (selection.size() > 1) {
								ctrl_briefDesc
										.setText(getMultipleSelectDescription(selection
												.size()));
							}
						}

					});

			if (allowChange1) {
				ctrl_add.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						TableItem[] items = viewer_selected.getTable()
								.getItems();
						List selectedList = new ArrayList();
						for (int i = 0; i < items.length; i++) {
							selectedList.add(items[i].getData());
						}
						if (getProviderExtender().useContentProviderAPIs()) {
							if (getProviderExtender().handleAddItems(viewer_selected.getSelection(), 1)) {
								refreshViewers();
								return;
							}
						}
						if (getFilter() != null) {
							ItemsFilterDialog fd = new ItemsFilterDialog(
									PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow()
											.getShell(), getFilter(),
									getContentElement(), getTabString());
							fd.setBlockOnOpen(true);
							fd.setTitle(getTabString());
							if(categoryIsSingleSelection1){
								fd.setViewerSelectionSingle(true);
							}
							fd.setExistingElements(selectedList);
							if(getFilterTypes() != null){
								fd.setTypes(getFilterTypes());
							}
							fd.open();
							addItemsToModel1(fd.getSelectedItems());
						}
						// viewer_selected.refresh();
						refreshViewers();

					}
				});

				ctrl_remove.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						IStructuredSelection selection = (IStructuredSelection) viewer_selected
								.getSelection();
						if (getProviderExtender().useContentProviderAPIs()) {
							if (getProviderExtender().handleRemoveItems(selection, 1)) {
								refreshViewers();
								return;
							}
						}
						if (selection.size() > 0) {
							// update the model
							ArrayList rmItems = new ArrayList();
							rmItems.addAll(selection.toList());
							removeItemsFromModel1(rmItems);
							// viewer_selected.refresh();
							refreshViewers();
							// clear the selection
							viewer_selected.setSelection(null, true);
							ctrl_briefDesc.setText(""); //$NON-NLS-1$
						}
						ctrl_remove.setEnabled(false);
					}
				});
			}
		}

		if (useCategory2) {
			// Add focus listener on primary tasks list
			ctrl_selected2.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					disableButtonsAndDeselectTables();

					IStructuredSelection selection = (IStructuredSelection) viewer_selected2
							.getSelection();
					if (allowChange2 && selection.size() > 0
							&& !TngUtil.isLocked(contentElement)) {
						ctrl_remove2.setEnabled(true);
					}
				}
			});

			viewer_selected2
					.addSelectionChangedListener(new ISelectionChangedListener() {

						public void selectionChanged(SelectionChangedEvent event) {
							IStructuredSelection selection = (IStructuredSelection) viewer_selected2
									.getSelection();
							libraryViewFindElementAction.selectionChanged(event);
							if (allowChange2 && selection.size() > 0
									&& !TngUtil.isLocked(contentElement)) {
								ctrl_remove2.setEnabled(true);
							}
							if (selection.size() == 1) {
								String desc = ((MethodElement) selection
										.getFirstElement())
										.getBriefDescription();
								if (desc == null) {
									desc = ""; //$NON-NLS-1$
								}
								ctrl_briefDesc.setText(desc);
							} else if (selection.size() > 1) {
								ctrl_briefDesc
										.setText(getMultipleSelectDescription(selection
												.size()));
							}
						}

					});

			if (allowChange2) {
				ctrl_add2.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						TableItem[] items = viewer_selected2.getTable()
								.getItems();
						List selectedList = new ArrayList();
						for (int i = 0; i < items.length; i++) {
							selectedList.add(items[i].getData());
						}
						if (getProviderExtender().useContentProviderAPIs()) {
							if (getProviderExtender().handleAddItems(viewer_selected2.getSelection(), 2)) {
								refreshViewers();
								return;
							}
						}
						if (getFilter2() != null) {
							ItemsFilterDialog fd = new ItemsFilterDialog(
									PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow()
											.getShell(), getFilter2(),
									getContentElement(), getTabString2());
							fd.setBlockOnOpen(true);
							fd.setTitle(getTabString2());
							fd.setExistingElements(selectedList);
							fd.open();
							addItemsToModel2(fd.getSelectedItems());
						}
						viewer_selected2.refresh();
					}
				});

				ctrl_remove2.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						IStructuredSelection selection = (IStructuredSelection) viewer_selected2
								.getSelection();
						if (getProviderExtender().useContentProviderAPIs()) {
							if (getProviderExtender().handleRemoveItems(selection, 2)) {
								refreshViewers();
								return;
							}
						}
						if (selection.size() > 0) {
							// update the model
							ArrayList rmItems = new ArrayList();
							rmItems.addAll(selection.toList());
							removeItemsFromModel2(rmItems);
							viewer_selected2.refresh();

							// clear the selection
							viewer_selected2.setSelection(null, true);
							ctrl_briefDesc.setText(""); //$NON-NLS-1$
						}
						ctrl_remove2.setEnabled(false);
					}
				});
			}
		}
		if (useCategory3) {
			// Add focus listener on primary tasks list
			ctrl_selected3.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					disableButtonsAndDeselectTables();

					IStructuredSelection selection = (IStructuredSelection) viewer_selected3
							.getSelection();
					if (allowChange3 && selection.size() > 0
							&& !TngUtil.isLocked(contentElement)) {
						ctrl_remove3.setEnabled(true);
					}
				}
			});

			viewer_selected3
					.addSelectionChangedListener(new ISelectionChangedListener() {

						public void selectionChanged(SelectionChangedEvent event) {
							IStructuredSelection selection = (IStructuredSelection) viewer_selected3
									.getSelection();
							libraryViewFindElementAction.selectionChanged(event);
							if (allowChange3 && selection.size() > 0
									&& !TngUtil.isLocked(contentElement)) {
								ctrl_remove3.setEnabled(true);
							}
							if (selection.size() == 1) {
								String desc = ((MethodElement) selection
										.getFirstElement())
										.getBriefDescription();
								if (desc == null) {
									desc = ""; //$NON-NLS-1$
								}
								ctrl_briefDesc.setText(desc);
							} else if (selection.size() > 1) {
								ctrl_briefDesc
										.setText(getMultipleSelectDescription(selection
												.size()));
							}
						}

					});

			if (allowChange3) {
				ctrl_add3.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						TableItem[] items = viewer_selected3.getTable()
								.getItems();
						List selectedList = new ArrayList();
						for (int i = 0; i < items.length; i++) {
							selectedList.add(items[i].getData());
						}
						if (getProviderExtender().useContentProviderAPIs()) {
							if (getProviderExtender().handleAddItems(viewer_selected3.getSelection(), 3)) {
								refreshViewers();
								return;
							}
						}
						if (getFilter3() != null) {
							ItemsFilterDialog fd = new ItemsFilterDialog(
									PlatformUI.getWorkbench()
											.getActiveWorkbenchWindow()
											.getShell(), getFilter3(),
									getContentElement(), getTabString3());
							fd.setBlockOnOpen(true);
							fd.setTitle(getTabString3());
							fd.setExistingElements(selectedList);
							fd.open();
							addItemsToModel3(fd.getSelectedItems());
						}
						viewer_selected3.refresh();

					}
				});

				ctrl_remove3.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						IStructuredSelection selection = (IStructuredSelection) viewer_selected3
								.getSelection();
						if (getProviderExtender().useContentProviderAPIs()) {
							if (getProviderExtender().handleRemoveItems(selection, 3)) {
								refreshViewers();
								return;
							}
						}
						if (selection.size() > 0) {
							// update the model
							ArrayList rmItems = new ArrayList();
							rmItems.addAll(selection.toList());
							removeItemsFromModel3(rmItems);
							viewer_selected3.refresh();

							// clear the selection
							viewer_selected3.setSelection(null, true);
							ctrl_briefDesc.setText(""); //$NON-NLS-1$
						}
						ctrl_remove3.setEnabled(false);
					}
				});
			}
		}

		if (allowChange1 && isUpAndDownButtonsRequired1) {
			ctrl_up1.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					IStructuredSelection selection = (IStructuredSelection) viewer_selected
							.getSelection();
					if (selection.size() > 0) {
						ArrayList moveUpItems = new ArrayList();
						moveUpItems.addAll(selection.toList());
						moveUpItemsInSelectedItems1(moveUpItems);
					}
					// viewer_selected.refresh();
					refreshViewers();
					enableUpDownButtons1();
				}
			});

			ctrl_down1.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					IStructuredSelection selection = (IStructuredSelection) viewer_selected
							.getSelection();
					if (selection.size() > 0) {
						ArrayList moveDownItems = new ArrayList();
						moveDownItems.addAll(selection.toList());
						moveDownItemsInSelectedItems1(moveDownItems);
					}
					// viewer_selected.refresh();
					refreshViewers();
					enableUpDownButtons1();
				}
			});

			if (ctrl_ordering != null) {
				ctrl_ordering.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(SelectionEvent e) {
						ContentElementsOrderDialog dlg = new ContentElementsOrderDialog(
								Display.getCurrent().getActiveShell(),
								contentElement, getActionManager());
						if (getProviderExtender().useContentProviderAPIs()) {
							dlg.setConfig(getProviderExtender().getConfig());
						}
						dlg.setFeature(getOrderFeature());
						if(dlg.open() == Window.OK){
							if (allowChange1 && isUpAndDownButtonsRequired1) {
								String sortType = CategorySortHelper.getCategorySortValue(contentElement);
								viewer_sort1.setSelection(new StructuredSelection(sortType), true);
								viewer_sort1.refresh();
							}
						} else {
							//undo the type change 
							String newSortType = CategorySortHelper.getCategorySortValue(contentElement);
							String oldSortType =  
								(String)((IStructuredSelection)viewer_sort1
									.getSelection()).getFirstElement();
							if(!oldSortType.equals(newSortType)){
								MethodElementProperty prop = CategorySortHelper
								.getCategorySortProperty(contentElement);

								if (prop == null) {
									prop = CategorySortHelper
											.createNewSortProperty(oldSortType);
									getActionManager()
											.doAction(
													IActionManager.ADD,
													contentElement,
													UmaPackage.eINSTANCE
															.getMethodElement_MethodElementProperty(),
													prop, -1);
								} else {
									getActionManager()
											.doAction(IActionManager.SET, prop,
													UmaPackage.eINSTANCE
														.getMethodElementProperty_Value(),
													oldSortType, -1);
								}
								viewer_selected.refresh();
							}
						}
					}
				});
			}
			
			if (viewer_sort1 != null) {
				viewer_sort1.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) viewer_sort1
								.getSelection();
						String sortType = (String)selection.getFirstElement();
						if (!CategorySortHelper.getCategorySortValue(contentElement).equals(sortType)) {
							MethodElementProperty prop = CategorySortHelper.getCategorySortProperty(contentElement);
							
							if (prop == null) {			
								prop = CategorySortHelper.createNewSortProperty(sortType);
								getActionManager()
								.doAction(
										IActionManager.ADD,
										contentElement,
										UmaPackage.eINSTANCE
												.getMethodElement_MethodElementProperty(),
										prop, -1);
							}
							else {
								getActionManager().doAction(IActionManager.SET,
									prop,
									UmaPackage.eINSTANCE
											.getMethodElementProperty_Value(),
											sortType, -1);
							}
							LibraryView.getView().refreshViews();
						}
						refreshViewers();
						enableUpDownButtons1();
					}
				});
			}
		}
	}

	protected EStructuralFeature getOrderFeature() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void enableControls(boolean editable) {
		if (useCategory1) {
			if (allowChange1) {
				ctrl_add.setEnabled(editable);
				IStructuredSelection selection = (IStructuredSelection) viewer_selected
						.getSelection();
				if (allowChange1 && selection.size() > 0
						&& !TngUtil.isLocked(contentElement)) {
					ctrl_remove.setEnabled(true);
				} else {
					ctrl_remove.setEnabled(false);

				}
			}
		}

		if (useCategory2) {
			if (allowChange2) {
				ctrl_add2.setEnabled(editable);
				IStructuredSelection selection = (IStructuredSelection) viewer_selected2
						.getSelection();
				if (allowChange2 && selection.size() > 0
						&& !TngUtil.isLocked(contentElement)) {
					ctrl_remove2.setEnabled(true);
				} else {
					ctrl_remove2.setEnabled(false);

				}
			}
		}
		if (useCategory3) {
			if (allowChange3) {
				ctrl_add3.setEnabled(editable);
				IStructuredSelection selection = (IStructuredSelection) viewer_selected3
						.getSelection();
				if (allowChange3 && selection.size() > 0
						&& !TngUtil.isLocked(contentElement)) {
					ctrl_remove3.setEnabled(true);
				} else {
					ctrl_remove3.setEnabled(false);

				}
			}
		}
		enableUpDownButtons1();
		if (isUpAndDownButtonsRequired1 && viewer_sort1 != null) {
			if (!TngUtil.isLocked(contentElement)) {
				viewer_sort1.getCombo().setEnabled(true);
			} else {
				viewer_sort1.getCombo().setEnabled(false);
			}
		}
	}

	void setDirty(boolean b) {
		// Phong (03/07/2005): the dirty state of the editor is already taken
		// care in MethodElementEditor, transparent from user
		// interaction. No firePropertyChange(PROP_DIRTY) should be called
		// somewhere else.
		// NOTE: unnecessary calls to firePropertyChange(PROP_DIRTY) will cause
		// performance problem.
		// firePropertyChange(PROP_DIRTY);
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#dispose()
	 */
	public void dispose() {
		if (usedCategories.size() > 0)
			LibraryManager.getInstance().releaseCategories(contentElement,
					usedCategories);

		if (labelProviderSelected != null) {
			labelProviderSelected.dispose();
		}
		if (labelProviderSelected2 != null) {
			labelProviderSelected2.dispose();
		}
		if (labelProviderSelected3 != null) {
			labelProviderSelected3.dispose();
		}
		if (providerExtender != null) {
			providerExtender.dispose();
		}
		super.dispose();
	}

	/**
	 * Returns the section name.
	 */
	protected String getSectionName() {
		return null;
	}

	/**
	 * Returns the section description.
	 */
	protected String getSectionDescription() {
		// TVT issue single quote 
		// Format should be done only for CategoryGuidancePage, AuthoringUI.categoryGuidancesPage.sectionDescription key 
		// have has replacement variable in its value. For more information check NLS_MESSAGEFORMAT_VAR
	   /* 
	    * NLS_MESSAGEFORMAT_VAR
	    Strings which contain replacement variables are processed by the  
	    MessageFormat class (single quote must be coded as 2 consecutive single  
	    quotes ''). Strings which do NOT contain replacement variables are NOT   
	    processed by the MessageFormat class (single quote must be coded as 1
		single quote ').*/
		
		if (methodElement instanceof ContentElement
				&& "categoryGuidancesPage".equalsIgnoreCase(getId())) { //$NON-NLS-1$
			return NLS.bind(getId() + "_sectionDescription", LibraryUIText.getUITextLower(methodElement)); //$NON-NLS-1$
		}
		return null;
	}

	/**
	 * Returns the first selected lable.
	 */
	protected String getSelectedLabel() {
		// if (methodElement instanceof ContentCategory) {
		// return AuthoringUIResources.formatString(PACKAGE_PREFIX + getId() +
		// ".selectedLabel", LibraryUIText.getUIText(methodElement));
		// //$NON-NLS-1$
		// }
		return null;
	}

	/**
	 * Returns the second selected lable.
	 */
	protected String getSelectedLabel2() {
		return null;
	}

	/**
	 * Returns the third selected lable.
	 */
	protected String getSelectedLabel3() {
		return null;
	}

	/**
	 * Returns the multiple section description.
	 */
	protected String getMultipleSelectDescription(int count) {
		return getMultipleSelectDescription(count, getMultipleSelectDescriptionString()); //$NON-NLS-1$
	}

	/**
	 * Returns the multiple section description.
	 */
	protected String getMultipleSelectDescription(int count, String message) {
		return AuthoringUIResources.bind(message, new Integer(count));
	}
	
	protected String getMultipleSelectDescriptionString() {
		return AuthoringUIResources.roleCategoriesPage_multipleSelectDescription;
	}

	/**
	 * Set description for section
	 * @param sectionDesc
	 * 			Description
	 */
	public void setSectionDesc(String sectionDesc) {
		this.sectionDesc = sectionDesc;
	}

	/**
	 * Set first label
	 * @param selectedLabel1
	 */
	public void setSelectedLabel(String selectedLabel1) {
		this.selectedLabel = selectedLabel1;
	}

	/**
	 * Set second label
	 * @param selectedLabel2
	 */
	public void setSelectedLabel2(String selectedLabel2) {
		this.selectedLabel2 = selectedLabel2;
	}

	/**
	 * Set third label
	 * @param selectedLabel3
	 */
	public void setSelectedLabel3(String selectedLabel3) {
		this.selectedLabel3 = selectedLabel3;
	}

	/**
	 * Set an option for the first category to be either single selection or multiple selection
	 * @param categoryIsSingleSelection1
	 */
	public void setCategoryIsSingleSelection1(boolean categoryIsSingleSelection1) {
		this.categoryIsSingleSelection1 = categoryIsSingleSelection1;
	}

	/**
	 * Set an option for the second category to be either single selection or multiple selection
	 * @param categoryIsSingleSelection2
	 */
	public void setCategoryIsSingleSelection2(boolean categoryIsSingleSelection2) {
		this.categoryIsSingleSelection2 = categoryIsSingleSelection2;
	}

	/**
	 * Set an option for the third category to be either single selection or multiple selection
	 * @param categoryIsSingleSelection3
	 */
	public void setCategoryIsSingleSelection3(boolean categoryIsSingleSelection3) {
		this.categoryIsSingleSelection3 = categoryIsSingleSelection3;
	}

	/**
	 * Set an option to use first category
	 * @param useCategory1
	 */
	public void setUseCategory1(boolean useCategory1) {
		this.useCategory1 = useCategory1;
	}

	/**
	 * Set an option to use second category
	 * @param useCategory2
	 */
	public void setUseCategory2(boolean useCategory2) {
		this.useCategory2 = useCategory2;
	}

	
	/**
	 * Set an option to use third category
	 * @param useCategory3
	 */
	public void setUseCategory3(boolean useCategory3) {
		this.useCategory3 = useCategory3;
	}

	/**
	 * Set an option to allow changes for first table in UI
	 * @param allowChange1
	 */
	public void setAllowChange1(boolean allowChange1) {
		this.allowChange1 = allowChange1;
	}

	/**
	 * Set an option to allow changes for the second table in UI
	 * @param allowChange2
	 */
	public void setAllowChange2(boolean allowChange2) {
		this.allowChange2 = allowChange2;
	}

	/**
	 * Set an option to allow changes for third table.
	 * @param allowChange3
	 */
	public void setAllowChange3(boolean allowChange3) {
		this.allowChange3 = allowChange3;
	}

	/**
	 * Set an option to show first set of up/down buttons
	 * @param isUpAndDownButtonsRequired1
	 */
	public void setIsUpAndDownButtonsRequired1(
			boolean isUpAndDownButtonsRequired1) {
		this.isUpAndDownButtonsRequired1 = isUpAndDownButtonsRequired1;
	}

	/**
	 * Set an option to show second set of up/down buttons
	 * @param isUpAndDownButtonsRequired2
	 */
	public void setIsUpAndDownButtonsRequired2(
			boolean isUpAndDownButtonsRequired2) {
		this.isUpAndDownButtonsRequired2 = isUpAndDownButtonsRequired2;
	}

	/**
	 * Set an option to show third set of up/down buttons.
	 * @param isUpAndDownButtonsRequired3
	 */
	public void setIsUpAndDownButtonsRequired3(
			boolean isUpAndDownButtonsRequired3) {
		this.isUpAndDownButtonsRequired3 = isUpAndDownButtonsRequired3;
	}

	// calculate widths for labels and tables, based on width of labels
	protected void setWidgetWidths() {
		int allCategoriesWidth = 0, categoryWidth = 0, category2Width = 0, category3Width = 0;
		int largestWidth = 0;
		// if (useCategory1) allCategoriesWidth =
		// label_allCategories.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
		if (useCategory1)
			categoryWidth = label_Category
					.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
		if (useCategory2)
			category2Width = label_Category2.computeSize(SWT.DEFAULT,
					SWT.DEFAULT).x;
		if (useCategory3)
			category3Width = label_Category3.computeSize(SWT.DEFAULT,
					SWT.DEFAULT).x;

		if (useCategory1)
			largestWidth = Math.max(allCategoriesWidth, categoryWidth);
		if (useCategory2)
			largestWidth = Math.max(largestWidth, category2Width);
		if (useCategory3)
			largestWidth = Math.max(largestWidth, category3Width);

		if (useCategory1) {
			// ((GridData)label_allCategories.getLayoutData()).widthHint =
			// largestWidth;
			// ((GridData)ctrl_allAvailable.getLayoutData()).widthHint =
			// largestWidth;
			((GridData) label_Category.getLayoutData()).widthHint = largestWidth;
			((GridData) ctrl_selected.getLayoutData()).widthHint = largestWidth;
		}
		if (useCategory2) {
			((GridData) label_Category2.getLayoutData()).widthHint = largestWidth;
			((GridData) ctrl_selected2.getLayoutData()).widthHint = largestWidth;
		}
		if (useCategory3) {
			((GridData) label_Category3.getLayoutData()).widthHint = largestWidth;
			((GridData) ctrl_selected3.getLayoutData()).widthHint = largestWidth;
		}
	}

	protected IActionManager getActionManager() {
		FormEditor editor = getEditor();
		if (editor instanceof MethodElementEditor) {
			return ((MethodElementEditor) editor).getActionManager();
		}
		return null;
	}

	private void setContextHelp() {
		// in the future, we should check the subclass type to set up a
		// different
		// context page for ech type of associations
		EditorsContextHelper.setHelp(getPartControl(), getContentElement());
	}
	
	/**
	 * Create context menu for the given viewer
	 * @param viewer
	 */
	public void createContextMenuFor(final StructuredViewer viewer) {
		MenuManager contextMenu = new MenuManager("#PopUp"); //$NON-NLS-1$
		contextMenu.add(new Separator(org.eclipse.ui.IWorkbenchActionConstants.MB_ADDITIONS));
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(this);
		Menu menu = contextMenu.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(contextMenu, viewer);
	}

	/**
	 * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
	 */
	public void menuAboutToShow(IMenuManager menuManager) {
		menuManager.add(new Separator(org.eclipse.ui.IWorkbenchActionConstants.MB_ADDITIONS));
		menuManager.add(new ActionContributionItem(libraryViewFindElementAction));
	}
	
	protected void enableUpDownButtons1() {
		if (ctrl_up1 != null) {
			if (isShouldEnableUp1()) {
				ctrl_up1.setEnabled(true);
			} else {
				ctrl_up1.setEnabled(false);
			}
		}
		if (ctrl_down1 != null) {
			if (isShouldEnableDown1()) {
				ctrl_down1.setEnabled(true);
			} else {
				ctrl_down1.setEnabled(false);
			}
		}
	}
	
	protected boolean isShouldEnableUp1() {
		if (viewer_selected != null && contentElement != null) {
			IStructuredSelection selection = (IStructuredSelection) viewer_selected
				.getSelection();
			if (isUpAndDownButtonsRequired1 &&
					selection.size() == 1 &&
					viewer_selected.getTable().getSelectionIndex() > 0 &&
					!TngUtil.isLocked(contentElement) && 
					CategorySortHelper.isManualCategorySort(contentElement)) {
				return true;
			}
		} 
		return false;
	}
	
	protected boolean isShouldEnableDown1() {
		IStructuredSelection selection = (IStructuredSelection) viewer_selected
			.getSelection();
		if (isUpAndDownButtonsRequired1 &&
				selection.size() == 1 &&
				(viewer_selected.getTable().getSelectionIndex() < viewer_selected
					.getTable().getItemCount() - 1) &&
				!TngUtil.isLocked(contentElement) &&
				CategorySortHelper.isManualCategorySort(contentElement)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getDecorator(Object object, EReference ref) {
		return getDecorator(object);
	}
	
	public String getDecorator(Object object) {
		return ""; //$NON-NLS-1$
	}
	
	public Font getBoldFont() {
		if (boldFont == null) {
			boldFont = createFont(SWT.BOLD);
		}
		return boldFont;
	}
	
    private Font createFont(int style) {
		FontData[] fontdata = Display.getCurrent().getSystemFont().getFontData();
		for (FontData data : fontdata) {
			data.setStyle(style);
		}
		
		return new Font(Display.getCurrent(), fontdata);    	
    }
    
	public FormPageProviderExtender getProviderExtender() {
		return providerExtender;
	}

	public List<?> retrieveTableViewerContents(TableViewer viewer) {
		if (getProviderExtender().useContentProviderAPIs()) {
			return Collections.EMPTY_LIST;
		}
		return super.retrieveTableViewerContents(viewer);
	}
	
	public EReference getReference(int ix) {
		if (ix == 1 && 	getOrderFeature() instanceof EReference) {
			return (EReference) getOrderFeature();
		}
		return null;
	}
	
	public OppositeFeature getOppositeFeature(int ix) {
		return null;
	}
	
}
