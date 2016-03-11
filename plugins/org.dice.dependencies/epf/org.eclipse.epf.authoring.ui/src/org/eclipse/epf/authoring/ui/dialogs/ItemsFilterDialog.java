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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.filters.AllFilter;
import org.eclipse.epf.authoring.ui.filters.DescriptorConfigurationFilter;
import org.eclipse.epf.authoring.ui.filters.ExProcessAuthoringConfigurator;
import org.eclipse.epf.authoring.ui.properties.AbstractSection;
import org.eclipse.epf.authoring.ui.util.AuthoringAccessibleListener;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.itemsfilter.FilterHelper;
import org.eclipse.epf.library.edit.itemsfilter.ICategoryFilter;
import org.eclipse.epf.library.edit.itemsfilter.IProcessFilter;
import org.eclipse.epf.library.edit.navigator.MethodPluginItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.search.internal.core.text.PatternConstructor;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;

/**
 * Filter Dialog - allow the user to filter the content element, content
 * categories, custom categories, All types of contentelements based on location of the use of the filter
 * dialog.
 * 
 * @author Lokanath Jagga
 * @author Shashidhar Kannoori
 */
public class ItemsFilterDialog extends BaseItemsFilterDialog implements
		ISelectionChangedListener, IDoubleClickListener {

	protected Button okButton, cancelButton;

	protected Text ctrl_pattern, ctrl_brief_desc;

	private String pattern, filterTypeStr;

	protected Combo filterType;

	private String dialogTitle, tabStr;

	private Object contentElement;

	private ArrayList selectedList = new ArrayList();

	private boolean viewerSelectionSingle = false;
	
	private String viewerLabel = null;
	
	private ProcessScopeUtil processUtil = ProcessScopeUtil.getInstance();
	
	private Combo selectCombo;
	
	private final String[] selectComboItems = {
			AuthoringUIResources.FilterDialog_Process_Scope_Grp_referencedPluginsBtn,
			AuthoringUIResources.FilterDialog_Process_Scope_Grp_selectedPluginsBtn,
			AuthoringUIResources.FilterDialog_Process_Scope_Grp_libBtn,
			AuthoringUIResources.FilterDialog_Process_Scope_Grp_configBtn
			};

	private Button viewBtn;
	
	private boolean enableProcessScope = false;
	
	private AbstractSection section;
	
	private Process configFreeProcess;
	
	private List selectedMethodPlugins = new ArrayList();
	
	private String PLUGIN_LIST_SECTION = ".Plugin_List_Section"; //$NON-NLS-1$
	
	private String PLUGIN_LIST_KEY = "Plugin_List_Key"; //$NON-NLS-1$
	
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
			} else if (object instanceof MethodPlugin) {
				MethodPluginItemProvider methodPluginItemProvider = (MethodPluginItemProvider)TngUtil.getAdapter((MethodPlugin)object, MethodPluginItemProvider.class);
				if (methodPluginItemProvider != null) {
					return methodPluginItemProvider.getFullName(object);
				}
			} 
			return super.getText(object);
		}
		
		
		public String getColumnText(Object object, int index) {
			return getText(object);
		}
		
		public Image getColumnImage(Object object, int index) {
			return super.getImage(object);
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

	private String STORE_EXPANDED_LEVEL_ID = ".LEVEL_ID"; //$NON-NLS-1$
	private String PATTERN_ID = ".Pattern"; //$NON-NLS-1$
	private String TYPE_ID = ".Type"; //$NON-NLS-1$

	private String DIALOG_NAME;

	private Button expandButton;

	private Button collapseButton;

	private Object input;

	private String[] types;

	/**
	 * Constructs a new FilterDialog. This constructor will use filter based on
	 * tabStr, filters out the list of elements based on already existing
	 * selected element in the selected table. Initializes the ContentProvider
	 * for the treeviewer.
	 * 
	 */
	public ItemsFilterDialog(Shell parentShell, Object contentElement,
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

	public ItemsFilterDialog(Shell parentShell, IFilter filter, String tabStr,
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
	public ItemsFilterDialog(Shell parentShell, IFilter filter,
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
	public ItemsFilterDialog(Shell parentShell, IFilter filter,
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
	public ItemsFilterDialog(Shell parentShell) {
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

	}

	/**
	 * Notifies that the cancel button of this dialog has been pressed.
	 */
	protected void cancelPressed() {
	    if (treeJob != null) {
	    	treeJob.cancel();
	    }
		super.cancelPressed();
		if(composedAdapterFactory != null){
			composedAdapterFactory.dispose();
		}
	}
	
	@Override
	protected void okPressed() {
	    if (treeJob != null) {
	    	treeJob.cancel();
	    }
		super.okPressed();
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
		shell.setText(AuthoringUIResources.FilterDialog_title + dialogTitle); 
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
		layout.numColumns = 3;

		Label typeLabel = new Label(composite, SWT.NONE);
		{
			typeLabel.setText(AuthoringUIResources.FilterDialog_Type_text); 
			GridData gD1 = new GridData(GridData.BEGINNING);
			typeLabel.setLayoutData(gD1);

		}
		filterType = new Combo(composite, SWT.SINGLE | SWT.READ_ONLY);
		{
			GridData gD1 = new GridData(GridData.FILL_HORIZONTAL);
			gD1.horizontalSpan = 2;
			filterType.setLayoutData(gD1);
		}

		if (types == null) {
			fillFilterTypeCombo();
			if(helper != null) {
				helper.setFilterTypeStr(filterTypeStr);
			}
		} else {
			filterType.setItems(types);
			if (helper != null) {
				filterTypeStr = filterType.getItem(0);
				helper.setFilterTypeStr(filterTypeStr);
				if (tabStr == null) {
					tabStr = filterTypeStr;
					helper.setTabStr(tabStr);
				}
			}
		}
		filterType.select(0);
		
		//for config free process
		if (supportProcessScope(contentElement)) {
			createSelectionScope(composite);
		}
		
		Label ctrl_patternLabel = new Label(composite, SWT.NONE);
		{
			ctrl_patternLabel.setText(AuthoringUIResources.FilterDialog_Pattern_text); 
			GridData gD = new GridData();
			gD.horizontalSpan = 3;
			ctrl_patternLabel.setLayoutData(gD);
		}

		ctrl_pattern = new Text(composite, SWT.BORDER);
		{
			GridData gD1 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gD1.horizontalSpan = 3;
			ctrl_pattern.setLayoutData(gD1);
			ctrl_pattern.getAccessible().addAccessibleListener(
					new AuthoringAccessibleListener(
							AuthoringUIResources.FilterDialog_Pattern_text +  
							AuthoringUIResources.FilterDialog_Pattern_description));
		}
		Label ctrl_patternLabel1 = new Label(composite, SWT.WRAP);
		{
			ctrl_patternLabel1.setText(AuthoringUIResources.FilterDialog_Pattern_description); 
			GridData gD = new GridData(SWT.DEFAULT, SWT.DEFAULT, false, false, 3, 1);
			gD.widthHint = 500;
			ctrl_patternLabel1.setLayoutData(gD);
		}
		
		Composite buttonsComposite = new Composite(composite, SWT.NONE);		
		
		GridData gd2 = new GridData(GridData.FILL_HORIZONTAL
			| GridData.HORIZONTAL_ALIGN_END);
		gd2.horizontalSpan = 3;
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

//		gd2 = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING
//				| GridData.GRAB_HORIZONTAL);

//		expandButton.setLayoutData(gd2);

		collapseButton = new Button(buttonsComposite, SWT.PUSH);
		collapseButton.setImage(AuthoringUIPlugin.getDefault().getSharedImage(
				"collapseall.gif")); //$NON-NLS-1$

		collapseButton.setToolTipText(AuthoringUIResources.FilterDialog_CollapseAll); 
		collapseButton.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
				AuthoringUIResources.FilterDialog_CollapseAll));
		
//		gd2 = new GridData(GridData.HORIZONTAL_ALIGN_END);

//		collapseButton.setLayoutData(gd2);
		
		createLine(composite, 3);
		createViewerLabel(composite);
		// create a treeviewer area.
		createViewer(composite);

		restoreLastSettings();

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
		
		//initialize for process scope
		if (supportProcessScope(contentElement)) {
			selectCombo.select(0);
			updateBtnStatus();
			updateFilterDialog();
		}
		
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

		// Set the input to treeViewer
		treeViewer.setInput(getInput());

		treeViewer.addSelectionChangedListener(this);
		treeViewer.addDoubleClickListener(this);
		treeViewer.getTree().setFont(parent.getFont());

		if (tabStr.equalsIgnoreCase(FilterConstants.ALL_ELEMENTS)) { 
			treeViewer.setSorter(new FilterSorterForAll());
		} else {
			treeViewer.setSorter(new FilterSorter());
		}

		GridData spec = new GridData(GridData.FILL_BOTH);
		{
			spec.heightHint = 300;
			spec.horizontalSpan = 3;
			treeViewer.getControl().setLayoutData(spec);
		}
	}

	/**
	 * Returns input if externally set by setInput invoker.
	 * Else, calculates the input based on the Object set to the dialog.
	 */
	private Object getInput(){
		if(input != null){
			return input;
		}else{
			if (contentElement != null) {
				if (contentElement instanceof Process) {
						Process process = (Process)contentElement;
						if (process.eContainer() instanceof ProcessComponent) {
							if (!(tabStr.equals(FilterConstants.GUIDANCE)
									|| tabStr.equals(FilterConstants.ROADMAP) 
									|| tabStr.equals(FilterConstants.SUPPORTING_MATERIALS))) {
							return process.eContainer();
						} else
							return process;
						}
				} 
				return UmaUtil.getMethodLibrary((EObject) contentElement);
			} 
			return LibraryService.getInstance().getCurrentMethodLibrary();
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
		if (element instanceof ContentElement || element instanceof Process
				|| element instanceof ProcessComponent
				|| element instanceof BreakdownElement
				|| element instanceof ProcessPackage) {
			selectedList.add(element);
		} 
		saveLastSettings();
		close();
		if(composedAdapterFactory != null){
			composedAdapterFactory.dispose();
		}
	}

	/**
	 * Return the dialog store to cache values into
	 */
	protected IDialogSettings getDialogSettings() {
		DialogSettings dialogSettings = (DialogSettings) AuthoringUIPlugin
				.getDefault().getDialogSettings();
		DialogSettings section = (DialogSettings) dialogSettings
				.getSection(DIALOG_NAME + STORE_EXPANDED_LEVEL_ID);
		if (section == null)
			section = (DialogSettings) dialogSettings.addNewSection(DIALOG_NAME
					+ STORE_EXPANDED_LEVEL_ID);
		return section;
	}

	/**
	 * Use the dialog settings to restore widget values to the values that they
	 * held last time this dialog was used to completion.
	 */
	protected void restoreLastSettings() {
		DIALOG_NAME = tabStr;
		DialogSettings dialogSettings = (DialogSettings) getDialogSettings();
		String temp = dialogSettings.get(DIALOG_NAME + PATTERN_ID); 
		if (temp != null && temp.length() > 0) { 
			pattern = dialogSettings.get(DIALOG_NAME + PATTERN_ID); 
			filterTypeStr = dialogSettings.get(DIALOG_NAME + TYPE_ID); 
			filterType.setText(filterTypeStr);
			regexPattern = getRegEx(pattern);
			
			//Trim "*" from pattern string before display. This action is just for display.
//			if(pattern != null){
//				if(pattern.lastIndexOf(FilterConstants.ANY_STRING) 
//						== pattern.length()-1){
//					pattern = pattern.substring(0, pattern.length()-1);
//				}
//			}
			ctrl_pattern.setText(pattern);
			
			if (helper != null) {
				helper.setPattern(pattern);
				helper.setRegexPattern(regexPattern);
				helper.setFilterTypeStr(filterTypeStr);
			}
			initProviderForTabs();

		}
		List elements = (List) FilterConstants.hashMap.get(DIALOG_NAME
				+ STORE_EXPANDED_LEVEL_ID);
		if (elements == null || elements.isEmpty()) {
			treeViewer.expandAll();
			return;
		}

		if (elements != null && !elements.isEmpty()) {
			try {
				treeViewer.setExpandedElements(elements.toArray());
				treeViewer.refresh();
			} catch (Exception e) {
				treeViewer.expandAll();
			}
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
	 * Since OK was pressed, write widget values to the dialog store so that
	 * they will persist into the next invocation of this dialog
	 */
	protected void saveValues() {
		IStructuredSelection selection = (IStructuredSelection) treeViewer
				.getSelection();
		if (selection.size() > 0) {
			Object[] objectArr = selection.toArray();
			for (int i = 0; i < objectArr.length; i++) {
				Object obj = LibraryUtil.unwrap(objectArr[i]);
				if (obj instanceof ContentElement 
						|| obj instanceof Process 
						|| obj instanceof ProcessComponent
						|| obj instanceof BreakdownElement
						|| obj instanceof ProcessPackage) {
					selectedList.add(obj);
				} 
			}
		}
		saveLastSettings();
	}

	/**
	 * Saves the last settings of dialog.
	 */
	public void saveLastSettings() {

		// eclipse docs forIDialogSettings for understanding.
		IDialogSettings settings = getDialogSettings();
		DIALOG_NAME = tabStr;
		Object[] expandedElements = treeViewer.getExpandedElements();
		List expandList = new ArrayList();
		// String[] expandedCategoryIds = new String[expandedElements.length];
		for (int i = 0; i < expandedElements.length; i++) {
			Object obj = expandedElements[i];
			if (obj instanceof NamedElement) {
				expandList.add(expandedElements[i]);
			}
		}
		settings.put(DIALOG_NAME + PATTERN_ID, pattern);
		settings.put(DIALOG_NAME + TYPE_ID, filterTypeStr);
		FilterConstants.hashMap.put(DIALOG_NAME + STORE_EXPANDED_LEVEL_ID,
				expandList);
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

	public void fillFilterTypeCombo() {
		if (filter instanceof ConfigurationFilter) {
			String[] str = new String[1];
			str[0] = (String) tabStr;
			filterType.setItems(str);
			filterTypeStr = str[0];
		} else if (FilterConstants.ONLY_CONTENT_ELEMENTS.equals(tabStr)) {
			String space = "-"; //$NON-NLS-1$
			String[] str = new String[27];
			int i = 0;
			str[i++] = FilterConstants.ALL_ELEMENTS;
			str[i++] = FilterConstants.CONTENT_PACKAGES;
			str[i++] = space + FilterConstants.ROLES;
			str[i++] = space + FilterConstants.TASKS;
			str[i++] = space + FilterConstants.WORKPRODUCTS;
			str[i++] = space + FilterConstants.UDTs;
			str[i++] = space + FilterConstants.GUIDANCE;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.CHECKLISTS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.CONCEPTS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.ESTIMATE_CONSIDERATIONS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.EXAMPLES;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.GUIDELINES;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.PRACTICES;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.REPORTS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.REUSABLE_ASSETS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.ROADMAP;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.SUPPORTING_MATERIALS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.TEMPLATES;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.TERM_DEFINITIONS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.TOOL_MENTORS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.WHITE_PAPERS;
			str[i++] = FilterConstants.DISCIPLINES;
			str[i++] = FilterConstants.ROLESETS;
			str[i++] = FilterConstants.WORKPRODUCTTYPES;
			str[i++] = FilterConstants.DOMAINS;
			str[i++] = FilterConstants.TOOLS;
			str[i++] = FilterConstants.CUSTOM_CATEGORIES;
			filterType.setItems(str);
			filterTypeStr = str[0];
		} else if (FilterConstants.categoryStrs.contains(tabStr)) {
			String[] str = new String[2];
			str[0] = (String) tabStr;
			str[1] = FilterConstants.METHO_PLUGINS;
			filterType.setItems(str);
			filterTypeStr = str[0];
		} else if (FilterConstants.CUSTOM_CATEGORIES.equalsIgnoreCase(tabStr)) {
			String[] str = new String[2];
			str[0] = (String) tabStr;
			str[1] = FilterConstants.METHO_PLUGINS;
			filterType.setItems(str);
			filterTypeStr = str[0];
		} else if (FilterConstants.breakdownElements.contains(tabStr)) {
			String[] str = new String[1];
			str[0] = (String) tabStr;
			filterType.setItems(str);
			filterTypeStr = str[0];
		} else if (FilterConstants.contentElementStrs.contains(tabStr)) {
			String[] str = new String[3];
			str[0] = tabStr;
			str[1] = FilterConstants.CONTENT_PACKAGES;
			str[2] = FilterConstants.METHO_PLUGINS;
			filterType.setItems(str);
			filterTypeStr = str[0];
		} else if (FilterConstants.ALL_ELEMENTS.equals(tabStr)) { // 172956
			String space = "-"; //$NON-NLS-1$
			String[] str = new String[29];
			int i = 0;
			if (filter instanceof AllFilter) {
				AllFilter allFilter = (AllFilter) filter;
				int sz = allFilter.getSelectedTypeStrings() == null ? 0 : allFilter.getSelectedTypeStrings().size();
				if (sz != 0) {
					str = new String[sz];
					for (String typeString : allFilter.getSelectedTypeStrings()) {
						str[i++] = typeString;
					}
					filterType.setItems(str);
					filterTypeStr = str[0];
					return;
				}
			}
			str[i++] = FilterConstants.ALL_ELEMENTS;
			str[i++] = FilterConstants.CONTENT_PACKAGES;
			str[i++] = space + FilterConstants.ROLES;
			str[i++] = space + FilterConstants.TASKS;
			str[i++] = space + FilterConstants.WORKPRODUCTS;
			str[i++] = space + FilterConstants.UDTs;
			str[i++] = space + FilterConstants.GUIDANCE;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.CHECKLISTS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.CONCEPTS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.ESTIMATE_CONSIDERATIONS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.EXAMPLES;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.GUIDELINES;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.PRACTICES;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.REPORTS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.REUSABLE_ASSETS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.ROADMAP;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.SUPPORTING_MATERIALS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.TEMPLATES;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.TERM_DEFINITIONS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.TOOL_MENTORS;
			str[i++] = FilterConstants.space + FilterConstants.space
					+ FilterConstants.WHITE_PAPERS;
			str[i++] = FilterConstants.DISCIPLINES;
			str[i++] = FilterConstants.ROLESETS;
			str[i++] = FilterConstants.WORKPRODUCTTYPES;
			str[i++] = FilterConstants.DOMAINS;
			str[i++] = FilterConstants.TOOLS;
			str[i++] = FilterConstants.CUSTOM_CATEGORIES;
			str[i++] = FilterConstants.PROCESSES;
			str[i++] = FilterConstants.METHO_PLUGINS;
			filterType.setItems(str);
			filterTypeStr = str[0];
		} else if (FilterConstants.CONFIG_CONTENT_ELEMENT.equals(tabStr)) {
			String[] str = new String[1];
			str[0] = (String) tabStr;
			filterType.setItems(str);
			filterTypeStr = str[0];
		} else {
			String[] str = new String[1];
			str[0] = (String) tabStr;
			filterType.setItems(str);
			filterTypeStr = str[0];
		}
	}

	public long delay = 800;
	
	private class UpdateTreeJob extends Job {

		public UpdateTreeJob(String name) {
			super(name);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {

			// need to run this in UI thread
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {

				public void run() {
					if (treeViewer == null || 
							treeViewer.getControl() == null ||
							treeViewer.getControl().isDisposed()) {
						return;
					}
					treeViewer.getTree().clearAll(true);
					if (ctrl_pattern.getText() != null
							&& ctrl_pattern.getText().trim().length() > 0) {
						pattern = ctrl_pattern.getText().trim();
						// if(pattern.indexOf(FilterConstants.ANY_STRING)> -1
						// || pattern.indexOf(FilterConstants.ANY_CHARACTER) >
						// -1){
						//              
						// }else{
						// pattern = pattern+FilterConstants.ANY_STRING;
						// }
					} else {
						pattern = FilterConstants.ANY_STRING;
						// return;
					}
					regexPattern = getRegEx(pattern);
					if (helper != null) {
						helper.setPattern(pattern);
						helper.setRegexPattern(regexPattern);
						helper.setFilterTypeStr(filterTypeStr);
						helper.setTabStr(filterTypeStr);
					}
					refreshTreeViewer();
				}

			});
			return Status.OK_STATUS;
		}

	}
	
	private UpdateTreeJob treeJob = new UpdateTreeJob(AuthoringUIResources.ItemsFilterDialog_UpdateTreeJob_name);
	
	public void addListener() {
		ctrl_pattern.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				
			  if (treeJob.getState() == Job.NONE) {
			    // We should schedule the job to run
			    treeJob.schedule(delay);
			  } else if (treeJob.getState() == Job.WAITING ||
					  treeJob.getState() == Job.SLEEPING){
			    // Cancel the previous job, and reschedule
			    treeJob.cancel();
			    treeJob.schedule(delay);
			  }
			}
		});

		filterType.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				// updateFontList();
				if (filterType.getSelectionIndex() > -1) {
					filterTypeStr = filterType.getItem(filterType
							.getSelectionIndex());
				}
				filterTypeStr.trim();
				if (helper != null) {
					helper.setPattern(pattern);
					helper.setRegexPattern(regexPattern);
					helper.setFilterTypeStr(filterTypeStr);
					helper.setTabStr(filterTypeStr);
				}
				// initProviderForTabs();
				refreshTreeViewer();
			}

			public void widgetDefaultSelected(SelectionEvent selectionevent) {
			}
		});
		expandButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {				
				expandOrCollapse(true);
			}
		});
		collapseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				expandOrCollapse(false);
			}
		});
		
		if (supportProcessScope(contentElement)) {			
			selectCombo.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					updateBtnStatus();
					updateFilterDialog();
				}
			});
						
			viewBtn.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					int index = selectCombo.getSelectionIndex();
					
					if (index == 0) {					
						showPluginListDialog(true);
					} 
					
					if (index == 1) {
						showPluginListDialog(false);
					}
				}
			});
		}
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
								.getPBS_ComposedAdapterFactory()) {
					public String getColumnText(Object object, int index) {
						return super.getText(object);
					}
					
					public Image getColumnImage(Object object, int index) {
						return super.getImage(object);
					}
				};
			} else if (tabStr
					.equalsIgnoreCase(FilterConstants.ROLE_DESCRIPTORS)) {
				adapterFactory = TngAdapterFactory.INSTANCE
				.getOBSFilter_AdapterFactory(filter); 
				contentProvider = new AdapterFactoryContentProvider(adapterFactory);
				composedAdapterFactory = TngAdapterFactory.INSTANCE.getOBSFilter_ComposedAdapterFactory();
				labelProvider = new AdapterFactoryLabelProvider(
						TngAdapterFactory.INSTANCE
								.getOBS_ComposedAdapterFactory()){
					public String getColumnText(Object object, int index) {
						return super.getText(object);
					}
					
					public Image getColumnImage(Object object, int index) {
						return super.getImage(object);
					}
				};
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
	 * Passing String[] array fills the FilterType list in dropdown combo box.
	 */
	public void setTypes(String[] str) {
		this.types = str;
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
	/*
	 * 
	 */
	public void refreshTreeViewer(){
		try {
			// To improve performance of filter, filter first and reset visible to
			// true.This will avoid the flickering in UI at time of filtering
			// elements in the tree structure
			
			treeViewer.getTree().clearAll(true);
			treeViewer.getTree().setVisible(false);
			treeViewer.refresh();
			treeViewer.expandAll();
			treeViewer.getTree().setVisible(true);
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
	}
	/*
	 * 
	 */
	public void expandOrCollapse(boolean expand){
		treeViewer.getTree().setVisible(false);
		if(expand){
			treeViewer.expandAll();
		}else{
			treeViewer.collapseAll();
		}
		treeViewer.getTree().setVisible(true);
	}
	
	private Pattern getRegEx(String pattern){
		try{
			return PatternConstructor.createPattern(pattern, false, false);
		}catch(Exception e){
			return PatternConstructor.createPattern(FilterConstants.ANY_STRING, false, false); 
		}
	}
	
	private void createSelectionScope(Composite parent) {
		Label selectLabel = new Label(parent, SWT.NONE);
		selectLabel.setText(AuthoringUIResources.FilterDialog_Process_Scope_Grp);
		
		selectCombo = new Combo(parent, SWT.READ_ONLY);		
		selectCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		selectCombo.setItems(selectComboItems);
		
		viewBtn = new Button(parent, SWT.PUSH);
		viewBtn.setText(AuthoringUIResources.FilterDialog_Process_Scope_Grp_viewBtn);
		viewBtn.setEnabled(false);
	}
		
	private boolean supportProcessScope(Object inputElement) {
		boolean result = false;
		
		if (enableProcessScope) {
			if (inputElement instanceof BreakdownElement) {
				Process process = getProcess((BreakdownElement)inputElement);
				result = processUtil.isConfigFree(process);
				if (result) {
					configFreeProcess = process;
				}
			}	
		}

		return result;
	}
	
	private Process getProcess(BreakdownElement element) {
		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				element, ITreeItemContentProvider.class);
		Object obj = ProcessUtil.getRootProcess(aFactory, adapter, element);
		
		return (Process) obj;
	}
	
	private void updateBtnStatus() {
		int index = selectCombo.getSelectionIndex();
		
		if ((index == 0) || (index == 1)) {
			viewBtn.setEnabled(true);
		} else {
			viewBtn.setEnabled(false);
		}
	}
	
	private void updateFilterDialog() {
		int index = selectCombo.getSelectionIndex();
		
		if (index == 0) {
			processUtil.setElemementSelectionScopeType(ProcessScopeUtil.ScopeType_Process);				
		}
		
		if (index == 1) {
			processUtil.setElemementSelectionScopeType(ProcessScopeUtil.ScopeType_Plugins);
			restoreDialogSettingsForPluginList();
			Scope pluginScope = processUtil.getPluginScope();
			pluginScope.clearAll();
			for (Object plugin : selectedMethodPlugins) {
				pluginScope.addPlugin((MethodPlugin)plugin);
			}			
		}
		
		if (index == 2) {
			processUtil.setElemementSelectionScopeType(ProcessScopeUtil.ScopeType_Library);
		}
		
		if (index == 3) {
			processUtil.setElemementSelectionScopeType(ProcessScopeUtil.ScopeType_Config);
		}
		
		updateFilter();
		
		initProviderForTabs();
		refreshTreeViewer();
	}
	
	/**
	 * Methods setEnableProcessScope() and setSection() must be used together, client should
	 * call those two methods together when want to support process scope selection under
	 * config-free process situation.
	 * 
	 * Example: please see /org.eclipse.epf.authoring.ui/src/org/eclipse/epf/authoring/ui/properties/RelationSection.java
	 * 
	 */	
	public void setEnableProcessScope(boolean enableProcessScope) {
		this.enableProcessScope = enableProcessScope;
	}
	
	public void setSection(AbstractSection section) {
		this.section = section;
	}
	
	private void updateFilter() {
		if (section != null) {
			if (filter instanceof DescriptorConfigurationFilter) {
				((DescriptorConfigurationFilter)filter).setMethodConfiguration(section.getConfiguration());
			}
		}	
	}
	
	private void showPluginListDialog(boolean readOnly) {
		List referencedMethodPlugins = processUtil.getScope(configFreeProcess).getMethodPluginSelection();
		PluginListDialog dialog = null;
		
		if (readOnly) {
			dialog = new PluginListDialog(this.getShell(), true, referencedMethodPlugins);
			dialog.open();
		} else {
			dialog = new PluginListDialog(this.getShell(), false, selectedMethodPlugins);
			if (dialog.open() == Dialog.OK) {
				List plugins = dialog.getResults();
				Scope pluginScope = processUtil.getPluginScope();
				pluginScope.clearAll();
				selectedMethodPlugins.clear();
				for (Object obj : plugins) {
					if (obj instanceof MethodPlugin) {
						pluginScope.addPlugin((MethodPlugin)obj);
						selectedMethodPlugins.add(obj);
					}
				}				
				refreshTreeViewer();
				saveDialogSettingsForPluginList();
			}
		}
	}
	
	private IDialogSettings getDialogSettingsForPluginList() {
		IDialogSettings dialogSettings = AuthoringUIPlugin.getDefault().getDialogSettings();
		String processId = configFreeProcess.getGuid();
		IDialogSettings section = dialogSettings.getSection(processId + PLUGIN_LIST_SECTION);
		if (section == null) {
			section = dialogSettings.addNewSection(processId + PLUGIN_LIST_SECTION);
		}
		
		return section;
	}
	
	private void saveDialogSettingsForPluginList() {
		IDialogSettings settings = getDialogSettingsForPluginList();
		String[] pluginId = new String[selectedMethodPlugins.size()];
		
		for (int i = 0; i < selectedMethodPlugins.size(); i++) {
			pluginId[i] = ((MethodPlugin)selectedMethodPlugins.get(i)).getGuid();			
		}
		
		settings.put(PLUGIN_LIST_KEY, pluginId);
	}
	
	private void restoreDialogSettingsForPluginList() {
		IDialogSettings settings = getDialogSettingsForPluginList();
		String[] pluginId = settings.getArray(PLUGIN_LIST_KEY);
		MethodLibrary lib = LibraryEditUtil.getInstance().getCurrentMethodLibrary();
		if (lib != null) {
			List allMethodPluginsInLibrary = lib.getMethodPlugins();		
			if (pluginId != null) {
				selectedMethodPlugins.clear();
				for (Object plugin : allMethodPluginsInLibrary) {
					String tempId = ((MethodPlugin)plugin).getGuid();
					for (String id : pluginId) {
						if (tempId.equals(id)) {
							selectedMethodPlugins.add(plugin);
							break;
						}
					}
				}		
			}
		}
	}
	
}
