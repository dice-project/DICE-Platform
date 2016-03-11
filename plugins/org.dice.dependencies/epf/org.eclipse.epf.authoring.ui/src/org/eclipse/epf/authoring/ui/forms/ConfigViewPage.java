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
package org.eclipse.epf.authoring.ui.forms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIHelpContexts;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.dialogs.ConfigurationAddViewsDialog;
import org.eclipse.epf.authoring.ui.dialogs.ConfigurationOrderDialog;
import org.eclipse.epf.authoring.ui.editors.ConfigurationEditor;
import org.eclipse.epf.authoring.ui.editors.ConfigurationEditorInput;
import org.eclipse.epf.authoring.ui.filters.ProcessViewFilter;
import org.eclipse.epf.authoring.ui.providers.ConfigurationLabelProvider;
import org.eclipse.epf.authoring.ui.views.ConfigurationViewFilter;
import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;


/**
 * Page for customizing publishing views
 * 
 * @author Shilpa Toraskar
 * @author Jinhua Xi
 * @since 1.0
 * fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=173827
 * fix for https://bugs.eclipse.org/bugs/show_bug.cgi?id=174260
 */
public class ConfigViewPage extends FormPage {

	private MethodConfiguration config = null;

	private String formPrefix = AuthoringUIResources.configViewPage_configuration; 

	private FormToolkit toolkit;

	private CTabFolder tabFolder;
	private CTabItem tabDefaultviewItem;

	private Button removeButton, makeDefaultButton, orderButton;

	private Object selectedItem = null;

	private IActionManager actionMgr = null;

	private Logger logger = null;

	/**
	 * Creates an instance
	 * @param editor
	 */
	public ConfigViewPage(FormEditor editor) {
		super(
				editor,
				AuthoringUIResources.configViewPage_configurationViews, AuthoringUIResources.configViewPage_views); 
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		setSite(site);
		setInput(input);

		ConfigurationEditorInput configInput = (ConfigurationEditorInput) input;
		config = configInput.getConfiguration();

		actionMgr = ((ConfigurationEditor) getEditor()).getActionManager();

		// get plugin logger
		logger = AuthoringUIPlugin.getDefault().getLogger();
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {
		ScrolledForm form = managedForm.getForm();
		form.setText(formPrefix + config.getName());
		toolkit = managedForm.getToolkit();
		form.getBody().setLayout(new GridLayout());

		// Create the view section.
		Section viewSection = toolkit.createSection(form.getBody(),
				Section.DESCRIPTION | Section.TWISTIE | Section.EXPANDED
						| Section.TITLE_BAR);
		GridData td = new GridData(GridData.FILL_BOTH);
		viewSection.setLayoutData(td);
		viewSection
				.setText(AuthoringUIResources.configViewPage_configurationViewSectionTitle); 
		viewSection
				.setDescription(AuthoringUIResources.configViewPage_configurationViewSectionMessage); 
		viewSection.setLayout(new GridLayout());

		Composite parent = toolkit.createComposite(viewSection);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		parent.setLayout(new GridLayout());
		viewSection.setClient(parent);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent.getParent(),
				AuthoringUIHelpContexts.CONFIGURATION_EDITOR_ALL_CONTEXT);

		// create button pane
		createButtonPane(parent);

		// create tabfolder pane
		createTabPane(parent);

		toolkit.paintBordersFor(parent);
		loadData();
	}

	/**
	 * 
	 * @param parent
	 */
	private void createTabPane(Composite parent) {
		tabFolder = new CTabFolder(parent, SWT.FLAT | SWT.TOP | SWT.BORDER);
		tabFolder.setLayout(new GridLayout());
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		// set tabfolder background color
		Color selectedColor = toolkit.getColors().getColor(FormColors.TB_BG);
		tabFolder.setSelectionBackground(new Color[] { selectedColor, toolkit.getColors().getBackground() }, new int[] { 50 });
		
		toolkit.paintBordersFor(tabFolder);

	}

	/**
	 * 
	 * @param parent
	 */
	private void createButtonPane(Composite parent) {
		Composite buttonPane = toolkit.createComposite(parent);
		buttonPane.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		buttonPane.setLayout(new GridLayout(4, false));

		Button addButton = toolkit
				.createButton(
						buttonPane,
						AuthoringUIResources.configViewPage_addViewButtonText, SWT.NONE); 
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			addButton.setLayoutData(gridData);
		}
		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// ConfigProcessViewDialog fd = new
				// ConfigProcessViewDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				// config);
				// ConfigProcessViewDialog.setDefaultImage(Display.getCurrent().getActiveShell().getImage());
				IFilter filter = new ProcessViewFilter(config, null,
						FilterConstants.ALL_ELEMENTS);
				ConfigurationAddViewsDialog fd = new ConfigurationAddViewsDialog(
						PlatformUI.getWorkbench().getActiveWorkbenchWindow()
								.getShell());
				fd.setTypes(getTypes());
				fd
						.setTitle(AuthoringUIResources.ConfigProcessViewDialog_SelectCategories); 
				fd.setInput(config);
				fd.setFilter(filter);
				fd.setExistingElements(config.getProcessViews());
				fd.setContentProvider(new AdapterFactoryContentProvider(
						TngAdapterFactory.INSTANCE
								.getItemsFilter_AdapterFactory(filter)),
								TngAdapterFactory.INSTANCE.getItemsFilter_ComposedAdapterFactory());
				fd.setBlockOnOpen(true);
				fd.open();
				List views = fd.getSelectedItems();

				// add view to configuration
				for (Iterator itor = views.iterator(); itor.hasNext();) {
					Object obj = itor.next();
					if (obj instanceof ContentCategory) {

						if (config.getProcessViews().contains(
								(ContentCategory) obj)) {
							String name = ((ContentCategory) obj).getName();
							String title = AuthoringUIResources.configViewPageViewExistsDialog_title; 
							String message = AuthoringUIResources.bind(AuthoringUIResources.configViewPageViewExistsDialog_message, name);
							AuthoringUIPlugin.getDefault().getMsgDialog()
									.displayWarning(title, message);
						} else {
							boolean status = actionMgr
							.doAction(
									IActionManager.ADD,
									config,
									UmaPackage.eINSTANCE
											.getMethodConfiguration_ProcessViews(),
									(ContentCategory) obj, -1);
							if(status){
								addView((ContentCategory) obj);
							}
						}
					}
				}
			}
		});

		// remove button
		removeButton = toolkit
				.createButton(
						buttonPane,
						AuthoringUIResources.configViewPage_removeViewButtonText, SWT.NONE); 
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			removeButton.setLayoutData(gridData);
		}
		removeButton.setEnabled(false);
		removeButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				removeView();
			}
		});

		// make default button
		makeDefaultButton = toolkit
				.createButton(
						buttonPane,
						AuthoringUIResources.configViewPage_makeDefaultButtonText, SWT.NONE); 
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			makeDefaultButton.setLayoutData(gridData);
		}
		makeDefaultButton.setEnabled(false);
		makeDefaultButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				makeDefaultView();
			}
		});
		
		orderButton = toolkit.createButton(
				buttonPane,
				AuthoringUIResources.orderButton_text, SWT.NONE); 
		{
			GridData gridData = new GridData(GridData.BEGINNING);
			removeButton.setLayoutData(gridData);
		}
		orderButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				ConfigurationOrderDialog dlg = new ConfigurationOrderDialog(
						Display.getCurrent().getActiveShell(),
						config,
						getActionManager());
				int ret = dlg.open();
				if(ret == Window.OK) refresh();
			}
		});

		toolkit.paintBordersFor(buttonPane);
	}

	/**
	 * Remove the selected view
	 * 
	 */
	private void removeView() {
		CTabItem item = tabFolder.getSelection();
		if (item == null)
			return;
		
		// don't change default view if the view is not removed.
		// 155025 - error when removing the view in configuration
		String itemText = item.getText();		
		String title = AuthoringUIResources.configViewPageRemoveViewDialog_title; 
		String message = AuthoringUIResources.bind(AuthoringUIResources.configViewPageRemoveViewDialog_message, item 
		.getText());
		boolean ret = AuthoringUIPlugin.getDefault().getMsgDialog()
				.displayConfirmation(title, message);

		if (!ret) {
			return;
		}
		
		boolean removeDefault = false;
		if (tabDefaultviewItem != null && item == tabDefaultviewItem) {
			removeDefault = true;
		}

			List views = config.getProcessViews();
			// if there is a default view tab, and it's the one slected to be removed
			if (removeDefault) {
				String defaultText = " " //$NON-NLS-1$
					+ AuthoringUIResources.processDescription_default; 
				int idx = itemText.lastIndexOf(defaultText);
				if (idx > 0) {
					itemText = itemText.substring(0,idx);
				}
			}
			
			for (Iterator itor = views.iterator(); itor.hasNext();) {
				ContentCategory category = (ContentCategory) itor.next();
				
				if (category.getName().equals(itemText)) {
					// remove it from views
					actionMgr.doAction(IActionManager.REMOVE, config,
							UmaPackage.eINSTANCE
									.getMethodConfiguration_ProcessViews(),
							category, -1);

					ContentCategory defaultView = config.getDefaultView();

					if ((defaultView != null) && (defaultView.equals(category))) {
						if (views.size() > 0) {
							actionMgr
									.doAction(
											IActionManager.SET,
											config,
											UmaPackage.eINSTANCE
													.getMethodConfiguration_DefaultView(),
											(ContentCategory) views.get(0), -1);
						} else {
							actionMgr
									.doAction(
											IActionManager.SET,
											config,
											UmaPackage.eINSTANCE
													.getMethodConfiguration_DefaultView(),
											null, -1);
						}
					}
					// remove tab item
					item.dispose();
					break;
				}
			}

		views = config.getProcessViews();
		if ((views == null) || (views.size() <= 0)) {
			removeButton.setEnabled(false);
			makeDefaultButton.setEnabled(false);
		}
		
		// make the 1st tab as the default view if the default one is removed
		if (removeDefault) {
			Control[] tabList = tabFolder.getTabList();
			if (tabList != null && tabList.length > 0) {
				tabFolder.setSelection(0);
				makeDefaultView();
			}
		}
	}

	/**
	 * Add view
	 * 
	 * @param obj
	 */
	private void addView(ContentCategory obj) {
		selectedItem = obj;
		Composite composite = new Composite(tabFolder, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		TreeViewer treeViewer = new TreeViewer(composite, SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.BORDER);
		treeViewer.getTree().setLayout(new GridLayout());
		treeViewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));

		IFilter configFilter = new ConfigurationViewFilter(config, treeViewer);
		AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
				.getConfigurationView_AdapterFactory(configFilter);

		AdapterFactoryContentProvider contentProvider = new AdapterFactoryContentProvider(
				adapterFactory) {
			public Object[] getElements(Object object) {
				// Get the adapter from the factory.
				ITreeItemContentProvider itemProvider = (ITreeItemContentProvider) adapterFactory
						.adapt(selectedItem, ITreeItemContentProvider.class);

				return itemProvider.getChildren(selectedItem).toArray();
				// return itemProvider.getElements(selectedItem).toArray();
			}
		};

		treeViewer.setContentProvider(contentProvider);
		treeViewer.setLabelProvider(new ConfigurationLabelProvider(config,
				adapterFactory));
		try {
			treeViewer.setInput(config);
		} catch (Exception ex) {
			logger.logError("Error creating treeviewer for config", ex); //$NON-NLS-1$
		}

		CTabItem item = new CTabItem(tabFolder, SWT.TOP);
		item.setText(((ContentCategory) obj).getName());
		item.setControl(composite);

		// make all buttons enabled
		removeButton.setEnabled(true);
		makeDefaultButton.setEnabled(true);

		toolkit.paintBordersFor(composite);
		tabFolder.setSelection(item);
	}

	/**
	 * Make selected as default view
	 * 
	 */
	private void makeDefaultView() {
		CTabItem item = tabFolder.getSelection();
		List views = config.getProcessViews();
		for (Iterator itor = views.iterator(); itor.hasNext();) {
			ContentCategory category = (ContentCategory) itor.next();
			if (category.getName().equals(item.getText())) {
				clearDefaultViewIndicator();
				// make that view default
				actionMgr.doAction(IActionManager.SET, config,
						UmaPackage.eINSTANCE
								.getMethodConfiguration_DefaultView(),
						category, -1);
				setDefaultViewIndicator(item);
				break;
			}
		}
	}

	/**
	 * Load initial data
	 * 
	 */
	private void loadData() {
		// get process views
		List views = config.getProcessViews();
		for (Iterator itor = views.iterator(); itor.hasNext();) {
			// create view in UI
			addView((ContentCategory) itor.next());
		}

		// get default view
		ContentCategory defaultView = config.getDefaultView();
		int cnt = tabFolder.getItemCount();
		if (defaultView != null) {
			// if default view is defined, select that view
			CTabItem[] items = tabFolder.getItems();
			for (int i = 0; i < items.length; i++) {
				String name = items[i].getText();
				if (name.equals(defaultView.getName())) {
					tabFolder.setSelection(items[i]);
					setDefaultViewIndicator(items[i]);
					break;
				}
			}
		} else if (cnt > 0) {
			// else select first view
			tabFolder.setSelection(0);
		}
	}
	
	private void refresh(){
		ContentCategory defaultView = config.getDefaultView();
		String defaultViewName = ""; //$NON-NLS-1$
		if(defaultView!=null) {
			defaultViewName = defaultView.getName();
			clearDefaultViewIndicator();
		}
		String selectedView = tabFolder.getSelection().getText();
		Map origViews = new HashMap();
		for(int i=0;i<tabFolder.getItemCount();i++){
			CTabItem item = tabFolder.getItem(i);
			origViews.put(item.getText(), item.getControl());
		}
		List views = config.getProcessViews();
		int index = 0;
		int selectedIndex = 0;
		for (Iterator itor = views.iterator(); itor.hasNext() && index<tabFolder.getItemCount();) {
			String view = ((ContentCategory)itor.next()).getName();
			CTabItem item = tabFolder.getItem(index++);
			item.setText(view);
			item.setControl((Control)origViews.get(view));
			if(view.equals(defaultViewName)) setDefaultViewIndicator(item);
			if(view.equals(selectedView)) selectedIndex = index-1;
		}
		tabFolder.setSelection(selectedIndex);
	}

	/**
	 * This method removes the appended "(default)" from the tab for the default
	 * view. This method must be called before changing the default view!
	 */
	private void clearDefaultViewIndicator() {
		ContentCategory defaultView = config.getDefaultView();
		if (defaultView != null) {
			String defaultViewTabName = defaultView.getName()
					+ " " //$NON-NLS-1$
					+ AuthoringUIResources.processDescription_default; 
			CTabItem[] items = tabFolder.getItems();
			for (int i = 0; i < items.length; i++) {
				String name = items[i].getText();
				if (name.equals(defaultViewTabName)) {
					items[i].setText(defaultView.getName());
				}
			}
		}
	}
	
	/**
	 * This method appends "(default)" to the tab of the default view.
	 */
	private void setDefaultViewIndicator(CTabItem item) {
		tabDefaultviewItem = item;
		
		String name = item.getText()
				+ " " //$NON-NLS-1$
				+ AuthoringUIResources.processDescription_default; 
		item.setText(name);
	}

	private String[] getTypes() {
		String[] Categories = new String[] { FilterConstants.CUSTOM_CATEGORIES,
				FilterConstants.ALL_ELEMENTS, FilterConstants.DISCIPLINES,
				FilterConstants.DOMAINS, FilterConstants.ROLESETS,
				FilterConstants.WORKPRODUCTTYPES, FilterConstants.TOOLS };
		return Categories;
	}
	
	protected IActionManager getActionManager() {
		return actionMgr;
	}

}