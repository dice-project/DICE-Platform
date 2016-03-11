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
package org.eclipse.epf.authoring.ui.dialogs;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.AbstractDiagramEditor;
import org.eclipse.epf.authoring.ui.editors.ActivityDetailDiagramEditor;
import org.eclipse.epf.authoring.ui.filters.DescriptorConfigurationFilter;
import org.eclipse.epf.authoring.ui.filters.ExProcessAuthoringConfigurator;
import org.eclipse.epf.authoring.ui.util.AuthoringAccessibleListener;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.itemsfilter.FilterHelper;
import org.eclipse.epf.library.edit.itemsfilter.ICategoryFilter;
import org.eclipse.epf.library.edit.itemsfilter.IProcessFilter;
import org.eclipse.epf.library.edit.process.BreakdownElementItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

/**
 * Filter Dialog - allow the user to filter the content element, content
 * categories, custom categories, All types of contentelements based on location of the use of the filter
 * dialog.
 * 
 * @author Shashidhar Kannoori
 */
public class ADDInfoDialog extends Dialog implements
		ISelectionChangedListener, IDoubleClickListener {

	protected Button okButton, cancelButton;

	protected Text ctrl_brief_desc;

	//private String pattern, filterTypeStr;

	//protected Combo filterType;

	private String dialogTitle, tabStr;

	private Object contentElement;

	private ArrayList selectedList = new ArrayList();

	private boolean viewerSelectionSingle = false;
	private String viewerLabel = null;
	
	/*
	 * Treeviewer for ContentElements to display.
	 */
	private TreeViewer treeViewer;

	protected IStructuredContentProvider contentProvider;

	protected ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
			TngAdapterFactory.INSTANCE
					.getNavigatorView_ComposedAdapterFactory()) {
		public String getText(Object object) {
			// return super.getText(object);
			if (object instanceof VariabilityElement) {
				return TngUtil.getLabel((VariabilityElement) object, "", true); //$NON-NLS-1$
			} else {
				return super.getText(object);
			}
		}
	};

	private AdapterFactory adapterFactory;
	private ComposedAdapterFactory composedAdapterFactory;

	/*
	 * Dialog Attributes
	 */
	// private boolean anyChilds = true;
	private List alreadySelectedList = new ArrayList();

	private IFilter filter;

	Pattern regexPattern;

	// private MethodConfiguration methodConfiguration;
	protected FilterHelper helper;

	private Button expandButton;

	private Button collapseButton;

	private Object input;

	//private String[] types;

	/**
	 * Constructs a new FilterDialog. This constructor will use filter based on
	 * tabStr, filters out the list of elements based on already existing
	 * selected element in the selected table. Initializes the ContentProvider
	 * for the treeviewer.
	 * 
	 */
	public ADDInfoDialog(Shell parentShell, Object contentElement,
			String tabStr, List alreadyExists) {
		super(parentShell);
		this.contentElement = contentElement;
		this.tabStr = tabStr;
		this.filter = null;
		this.alreadySelectedList = alreadyExists;
		helper = new FilterHelper(contentElement, tabStr, null, null,
				this.alreadySelectedList);
		initProviderForTabs();
	}

	public ADDInfoDialog(Shell parentShell, IFilter filter, String tabStr,
			List alreadyExists, Object input) {
		this(parentShell, filter, null, tabStr, alreadyExists);
		this.input = input;
	}

	/**
	 * Constructs a new FilterDialog. This constructor will use passed filter.
	 * tabStr is used for the searchtype only. Initializes the ContentProvider
	 * for the treeviewer.
	 * 
	 */
	public ADDInfoDialog(Shell parentShell, IFilter filter,
			Object contentElement, String tabStr) {
		super(parentShell);
		this.filter = filter;
		this.tabStr = tabStr;

		helper = new FilterHelper(contentElement, tabStr, null, null, null);
		initProviderForTabs();
	}

	/**
	 * Constructs a new FilterDialog. This constructor will use passed filter.
	 * tabStr is used for the searchtype only. Initializes the ContentProvider
	 * for the treeviewer.
	 * 
	 */
	public ADDInfoDialog(Shell parentShell, IFilter filter,
			Object contentElement, String tabStr, List alreadyExists) {
		super(parentShell);
		this.filter = filter;
		this.tabStr = tabStr;
		this.alreadySelectedList = alreadyExists;
		helper = new FilterHelper(contentElement, tabStr, null, null,
				alreadyExists);
		this.contentElement = contentElement;
		initProviderForTabs();
	}

	/**
	 * Constructs a new FilterDialog. Only for methodconfiguration. This
	 * constructor will use passed filter. Initializes the ContentProvider for
	 * the treeviewer.
	 * 
	 */
	// public ItemsFilterDialog(Shell parentShell, Object contentElement,
	// MethodConfiguration methodConfiguration, String tabStr) {
	// super(parentShell);
	// this.contentElement = contentElement;
	// this.tabStr = tabStr;
	// this.filter = null;
	// this.methodConfiguration = methodConfiguration;
	// helper = new FilterHelper(contentElement, tabStr, null, null, null);
	// initProviderForTabs();
	// }
	/**
	 * This constructor is very generic, in order to initialize filter, input
	 * and contentprovider should be handled explicitly by calling below method.
	 * setFilter(IFilter) setInput(Object)
	 * setContentProvider(IStructuredProvider)
	 * 
	 */
	public ADDInfoDialog(Shell parentShell) {
		super(parentShell);
		helper = new FilterHelper(null, null, null, null, null);
	}

	/**
	 * This method is called if a button has been pressed.
	 */
	protected void buttonPressed(int buttonId) {
		if (buttonId == IDialogConstants.OK_ID)
			saveValues();
		super.buttonPressed(buttonId);
		if(composedAdapterFactory != null){
			composedAdapterFactory.dispose();
		}
		close();

	}

	public void saveValues(){
		IStructuredSelection selection = (IStructuredSelection) treeViewer
		.getSelection();
		if (selection.size() > 0) {
			Object[] objectArr = selection.toArray();
			for (int i = 0; i < objectArr.length; i++) {
				Object obj = LibraryUtil.unwrap(objectArr[i]);
				if (obj instanceof Activity) {
					selectedList.add(obj);
				} 
			}
		}
	}
		
	/**
	 * Notifies that the cancel button of this dialog has been pressed.
	 */
	protected void cancelPressed() {
		if(composedAdapterFactory != null){
			composedAdapterFactory.dispose();
		}
		super.cancelPressed();
	}

	/**
	 * Set title for dialog box eg: Select Dialog For Role, dialogTitle needs to
	 * "Role"
	 */
	public void setTitle(String dialogTitle) {
		this.dialogTitle = dialogTitle;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		if (dialogTitle == null)
			dialogTitle = tabStr;
		shell.setText("Activity Detail Diagram Info: " + dialogTitle); //$NON-NLS-1$
	}

	/**
	 * (non-Javadoc) Adds buttons to this dialog's button bar.
	 * 
	 * @param parent
	 *            the button bar composite
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
	}

	/**
	 * Creates dialog area and controls and viewer.
	 * 
	 * @param the
	 *            parent composite to contain the dialog area
	 * @return the dialog area control
	 */
	protected Control createDialogArea(Composite parent) {

		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setFont(parent.getFont());

		GridLayout layout = (GridLayout) composite.getLayout();
		layout.marginWidth = 10;
		layout.marginHeight = 10;
		//GridData gD = (GridData) composite.getLayoutData();
		layout.numColumns = 3;

		Label typeLabel = new Label(composite, SWT.NONE);
		{
			typeLabel.setText("Select the activity to modify ADD"); //$NON-NLS-1$
			GridData gD1 = new GridData(GridData.BEGINNING);
			typeLabel.setLayoutData(gD1);

		}
//		filterType = new Combo(composite, SWT.SINGLE | SWT.READ_ONLY);
//		{
//			GridData gD1 = new GridData(GridData.BEGINNING);
//			gD1.horizontalSpan = 2;
//			gD1.widthHint = 390;
//			filterType.setLayoutData(gD1);
//		}
//
//		if (types == null) {
//			fillFilterTypeCombo();
//		} else {
//			filterType.setItems(types);
//			if (helper != null) {
//				filterTypeStr = filterType.getItem(0);
//				helper.setFilterTypeStr(filterType.getItem(0));
//				if (tabStr == null) {
//					tabStr = filterTypeStr;
//					helper.setTabStr(tabStr);
//				}
//			}
//		}
//		filterType.select(0);

//		Label ctrl_patternLabel = new Label(composite, SWT.NONE);
//		{
//			ctrl_patternLabel.setText(AuthoringUIResources.FilterDialog_Pattern_text); //$NON-NLS-1$
//			gD.horizontalSpan = 3;
//			ctrl_patternLabel.setLayoutData(gD);
//		}

//		ctrl_pattern = new Text(composite, SWT.BORDER);
//		{
//			GridData gD1 = new GridData(GridData.FILL_HORIZONTAL);
//			gD1.horizontalSpan = 3;
//			ctrl_pattern.setLayoutData(gD1);
//			ctrl_pattern.getAccessible().addAccessibleListener(
//					new AuthoringAccessibleListener(
//							AuthoringUIResources.FilterDialog_Pattern_text +  
//							AuthoringUIResources.FilterDialog_Pattern_description));
//		}
//		Label ctrl_patternLabel1 = new Label(composite, SWT.NONE);
//		{
//			ctrl_patternLabel1.setText(AuthoringUIResources.FilterDialog_Pattern_description); //$NON-NLS-1$
//			ctrl_patternLabel1.setLayoutData(gD);
//		}
		
		Composite buttonsComposite = new Composite(composite, SWT.NONE);		
		
		GridData gd2 = new GridData(GridData.FILL_HORIZONTAL
			| GridData.HORIZONTAL_ALIGN_END);
		gd2.horizontalSpan = 2;
		buttonsComposite.setLayoutData(gd2);
		GridLayout buttonsLayout = new GridLayout();
		buttonsLayout.numColumns = 2;
		buttonsLayout.marginRight = 0;
		buttonsComposite.setLayout(buttonsLayout);
		
		expandButton = new Button(buttonsComposite, SWT.BUTTON1);

		expandButton.setImage(AuthoringUIPlugin.getDefault().getSharedImage(
				"expandall.gif")); //$NON-NLS-1$
		expandButton.setToolTipText(AuthoringUIResources.FilterDialog_ExpandAll); 
		
		expandButton.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
						AuthoringUIResources.FilterDialog_ExpandAll));		

		gd2 = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
				| GridData.GRAB_HORIZONTAL);

		expandButton.setLayoutData(gd2);

		collapseButton = new Button(buttonsComposite, SWT.PUSH);
		collapseButton.setImage(AuthoringUIPlugin.getDefault().getSharedImage(
				"collapseall.gif")); //$NON-NLS-1$

		collapseButton.setToolTipText(AuthoringUIResources.FilterDialog_CollapseAll); 
		collapseButton.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
				AuthoringUIResources.FilterDialog_CollapseAll));
		
		gd2 = new GridData(GridData.HORIZONTAL_ALIGN_END);

		collapseButton.setLayoutData(gd2);
		
		createLine(composite, 3);
		createViewerLabel(composite);
		// create a treeviewer area.
		createViewer(composite);


		new Label(composite, SWT.NONE).setText(AuthoringUIResources.FilterDialog_BriefDescription); 
		ctrl_brief_desc = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP
				| SWT.V_SCROLL | SWT.READ_ONLY);
		{
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 3;
			gd.heightHint = 60;
			ctrl_brief_desc.setLayoutData(gd);
		}
		// Restore the last state
		createLine(composite, 3);

		// Return results.
		addListener();
		// System.out.println("$$$ createDialogArea.filterTypeStr = " +
		// filterTypeStr);
		return composite;
	}

	/**
	 * Create a new viewer in the parent.
	 * 
	 * @param parent
	 *            the parent <code>Composite</code>.
	 */
	private void createViewer(Composite parent) {
		if (viewerSelectionSingle) {
			treeViewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER);
		} else {
			treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER);
		}
		treeViewer.setLabelProvider(labelProvider);
		treeViewer.setContentProvider(contentProvider);
		treeViewer.setUseHashlookup(true);

		if (input != null) {
			treeViewer.setInput(input);
		} else {
			// Phong: this is too messy. If you relies on the contentElement to
			// determine the input for the viewer
			// then you should declare a input field instead and move this code
			// to constructor
			//
			// sorter needs or not. TODO
			if (contentElement != null) {
				if (contentElement instanceof Activity) {
					if (contentElement instanceof Process) {
						if (((Process) contentElement).eContainer() instanceof ProcessComponent) {
							if (tabStr.equals(FilterConstants.GUIDANCE)
									|| tabStr.equals(FilterConstants.ROADMAP) 
									|| tabStr.equals(FilterConstants.SUPPORTING_MATERIALS)) {
								treeViewer
										.setInput(UmaUtil
												.getMethodLibrary((EObject) contentElement));
							} else {
								treeViewer.setInput(((Process) contentElement)
										.eContainer());
							}
						} else
							treeViewer.setInput(contentElement);
					} else
						treeViewer.setInput(UmaUtil
								.getMethodLibrary((EObject) contentElement));
				} else {
					treeViewer.setInput(UmaUtil
							.getMethodLibrary((EObject) contentElement));
				}
			} else {
				treeViewer
						.setInput(LibraryService.getInstance().getCurrentMethodLibrary());
			}
		}

		treeViewer.addSelectionChangedListener(this);
		treeViewer.addDoubleClickListener(this);
		treeViewer.getTree().setFont(parent.getFont());

		if (tabStr.equalsIgnoreCase(FilterConstants.ALL_ELEMENTS)) { 
			treeViewer.setSorter(new FilterSorterForAll());
		} else {
			treeViewer.setSorter(new FilterSorter());
		}

		// treeViewer.expandToLevel(3);
		// treeViewer.expandAll();

		GridData spec = new GridData(GridData.FILL_BOTH);
		{
			spec.widthHint = 300;
			spec.heightHint = 300;
			spec.horizontalSpan = 3;
			treeViewer.getControl().setLayoutData(spec);
		}
	}

	private void createLine(Composite parent, int ncol) {
		Label line = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL
				| SWT.BOLD);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = ncol;
		line.setLayoutData(gridData);
	}

	private void createViewerLabel(Composite parent) {
   		if (viewerLabel != null) {
   			Label label = new Label(parent, SWT.NONE);
			label.setText(viewerLabel);
   		}
   	}
	
	public void setViewerSelectionSingle(boolean single) {
		viewerSelectionSingle = single;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
	 */
	public void doubleClick(DoubleClickEvent event) {
		IStructuredSelection s = (IStructuredSelection) event.getSelection();
		Object element = s.getFirstElement();
		element = LibraryUtil.unwrap(element);
		if (element instanceof ContentElement || element instanceof Process) {
			selectedList.add(element);
		} else if (element instanceof ProcessComponent) {
			selectedList.add(element);
		} else if (element instanceof BreakdownElement) {
			// if(!(element instanceof Activity))
			selectedList.add(element);
		} else if (element instanceof ProcessPackage) {
			// if(!(element instanceof Activity))
			selectedList.add(element);
		}
		close();
		if(composedAdapterFactory != null){
			composedAdapterFactory.dispose();
		}
	}

	
	public void traverseTree(TreeItem[] items, List list) {
		for (int i = 0; i < items.length; i++) {
			TreeItem[] itemsx = items[i].getItems();
			if (itemsx.length > 1) {
				list.add(items[i].getData());
				traverseTree(itemsx, list);
			} else {
				list.add(items[i].getData());
			}
		}
	}

	


	/**
	 * Notifies that the selection has changed.
	 * 
	 * @param event
	 *            event object describing the change
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		updateSelection(event);
		updateButtons();
	}

	/**
	 * Update the button enablement state.
	 */
	protected void updateButtons() {
		// okButton.setEnabled(getSelection().length > 0);
	}

	public Object getParentObject(Object contentElement) {
		return MethodElementUtil.getMethodModel(contentElement);
	}

	/**
	 * Update the selection object.
	 */
	protected void updateSelection(SelectionChangedEvent event) {
		// ArrayList descs = new ArrayList();
		IStructuredSelection sel = (IStructuredSelection) event.getSelection();
		Object selected = sel.getFirstElement();
		if (selected instanceof BreakdownElementWrapperItemProvider ||
				selected instanceof BreakdownElementItemProvider){
			Object unwrap =  TngUtil.unwrap(selected);
			setBriefDescription(unwrap);
		}else {
			setBriefDescription(selected);
		}
	}
	/**
	 * Set the brief description to control
	 */
	private void setBriefDescription(Object element) {
		if (element instanceof MethodElement) {
			String desc = ((MethodElement) element).getBriefDescription();
			if (desc != null) {
				ctrl_brief_desc.setText(desc);
			}
		}
	}

	

	public void addListener() {

		expandButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				treeViewer.expandAll();

			}
		});
		collapseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				treeViewer.collapseAll();

			}
		});

	}

	public ArrayList getSelectedItems() {
		return selectedList;
	}

	private void initProviderForTabs() {

		if (filter != null) {
			if (filter instanceof org.eclipse.epf.library.edit.itemsfilter.IFilter) {
				if(filter instanceof ICategoryFilter){
					// special handling for contentcategory.
					// https://bugs.eclipse.org/bugs/show_bug.cgi?id=157452
					helper.setContentCategoryTabStr(tabStr);
				}
				((org.eclipse.epf.library.edit.itemsfilter.IFilter) filter)
						.setHelper(helper);
			} else if (filter instanceof DescriptorConfigurationFilter) {
				((DescriptorConfigurationFilter) filter).setHelper(helper);
			}else if (filter instanceof ExProcessAuthoringConfigurator) {
				((ExProcessAuthoringConfigurator) filter).setHelper(helper);
			}
		}
		if (filter instanceof IProcessFilter) {
			if (tabStr
					.equalsIgnoreCase(FilterConstants.WORK_PRODUCT_DESCRIPTORS)) {
				adapterFactory =TngAdapterFactory.INSTANCE.getPBSFilter_AdapterFactory(filter);
				contentProvider = new AdapterFactoryContentProvider(
						adapterFactory);
				composedAdapterFactory = TngAdapterFactory.INSTANCE.getPBSFilter_ComposedAdapterFactory();
				labelProvider = new AdapterFactoryLabelProvider(
						TngAdapterFactory.INSTANCE
								.getPBS_ComposedAdapterFactory());
			} else if (tabStr
					.equalsIgnoreCase(FilterConstants.ROLE_DESCRIPTORS)) {
				adapterFactory = TngAdapterFactory.INSTANCE
				.getOBSFilter_AdapterFactory(filter); 
				contentProvider = new AdapterFactoryContentProvider(adapterFactory);
				composedAdapterFactory = TngAdapterFactory.INSTANCE.getOBSFilter_ComposedAdapterFactory();
				labelProvider = new AdapterFactoryLabelProvider(
						TngAdapterFactory.INSTANCE
								.getOBS_ComposedAdapterFactory());
			}
		} else {
			adapterFactory = TngAdapterFactory.INSTANCE
						.getItemsFilter_AdapterFactory(filter); 
			contentProvider = new AdapterFactoryContentProvider(adapterFactory);
			composedAdapterFactory = TngAdapterFactory.INSTANCE.getItemsFilter_ComposedAdapterFactory();
		}
	}

	public void setExistingElements(List list) {
		this.alreadySelectedList = list;
		if (helper != null) {
			helper.setAlreadySelectedList(list);
		}
	}

	public FilterHelper getHelper() {
		return this.helper;
	}

	public String[] getFilterTypeArray() {
		return null;
	}

	/*
	 * sets the ContentProvider used by TreeViewer.
	 */
	public void setContentProvider(IStructuredContentProvider contentProvider,
			ComposedAdapterFactory composedAdapterFactory) {
		this.contentProvider = contentProvider;
		this.composedAdapterFactory = composedAdapterFactory;
	}

	/*
	 * set the Filter.
	 */
	public void setFilter(IFilter filter) {
		this.filter = filter;
		if (helper != null) {
			if (this.filter instanceof org.eclipse.epf.library.edit.itemsfilter.IFilter) {
				((org.eclipse.epf.library.edit.itemsfilter.IFilter) this.filter)
						.setHelper(helper);
			} else if (this.filter instanceof DescriptorConfigurationFilter) {
				((DescriptorConfigurationFilter) this.filter).setHelper(helper);
			}
		}
	}

	public void setInput(Object input) {
		if (input != null) {
			this.input = input;
		}
	}

	public void setViewerLabel(String viewerLabel) {
		this.viewerLabel = viewerLabel;
	}
}