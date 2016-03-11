/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.authoring.ui.forms;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.epf.authoring.ui.AuthoringUIHelpContexts;
import org.eclipse.epf.authoring.ui.AuthoringUIImages;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.ConfigurationEditor;
import org.eclipse.epf.authoring.ui.editors.ConfigurationEditorInput;
import org.eclipse.epf.authoring.ui.providers.CategoryContentProvider;
import org.eclipse.epf.authoring.ui.providers.CategoryLabelProvider;
import org.eclipse.epf.authoring.ui.providers.ConfigPackageContentProvider;
import org.eclipse.epf.authoring.ui.providers.ConfigPackageLabelProvider;
import org.eclipse.epf.authoring.ui.providers.HideUncheckedViewerFilter;
import org.eclipse.epf.authoring.ui.util.AuthoringAccessibleListener;
import org.eclipse.epf.authoring.ui.util.ConfigurationMarkerHelper;
import org.eclipse.epf.authoring.ui.views.ConfigurationView;
import org.eclipse.epf.authoring.ui.views.MethodContainerCheckedTreeViewer;
import org.eclipse.epf.authoring.ui.views.MethodContainerCheckedTreeViewer2;
import org.eclipse.epf.library.IConfigurationManager;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationData;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.ConfigurationProperties;
import org.eclipse.epf.library.configuration.closure.ClosureListener;
import org.eclipse.epf.library.configuration.closure.ConfigurationClosure;
import org.eclipse.epf.library.configuration.closure.IConfigurationError;
import org.eclipse.epf.library.edit.IPluginUIPackageContextChangedListener;
import org.eclipse.epf.library.edit.PluginUIPackageContext;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.MethodElementSetPropertyCommand;
import org.eclipse.epf.library.edit.navigator.ConfigContentPackageItemProvider;
import org.eclipse.epf.library.edit.navigator.ConfigContentPackageItemProvider.LeafElementsItemProvider;
import org.eclipse.epf.library.edit.navigator.ConfigPageCategoriesItemProvider;
import org.eclipse.epf.library.edit.navigator.ContentItemProvider;
import org.eclipse.epf.library.edit.navigator.MethodPackagesItemProvider;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ConfigurationUtil;
import org.eclipse.epf.library.edit.util.MethodConfigurationPropUtil;
import org.eclipse.epf.library.edit.util.MethodElementPropertyHelper;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.events.ILibraryChangeListener;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.ui.util.SWTUtil;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.ide.IGotoMarker;

public class ConfigurationPage extends FormPage implements IGotoMarker {
	
//	public static final String TOUCHED_BY_CONFIG_EDITOR = "TouchedByConfigEditor";	//$NON-NLS-1$	
	public static final String TOUCHED_BY_CONFIG_EDITOR = MethodConfigurationPropUtil.TOUCHED_BY_CONFIG_EDITOR;	
	
	private String formPrefix = AuthoringUIResources.ConfigurationPage_FormPrefix; 

	private MethodConfiguration config = null;
	private ConfigurationClosure closure = null;
	
	private MethodContainerCheckedTreeViewer configViewer;
	private ConfigPackageContentProvider contProvider;
	private ConfigPackageLabelProvider labelProvider;

	private MethodContainerCheckedTreeViewer2 addCategoryViewer;
	private MethodContainerCheckedTreeViewer2 subCategoryViewer;
	
	private HideUncheckedViewerFilter configViewerHideUncheckedFilter;
	private HideUncheckedViewerFilter addCategoryHideUncheckedFilter;
	private HideUncheckedViewerFilter subCategoryHideUncheckedFilter;
	

	private ComposedAdapterFactory adapterFactory = TngAdapterFactory.INSTANCE.createConfigPage_LibraryComposedAdapterFactory();

	private ILibraryChangeListener libListener = null;
	
	private static final ConfigurationMarkerHelper markerHelper = ConfigurationMarkerHelper.INSTANCE;
	boolean isDirty = false;
	protected ISelection currentSelection = StructuredSelection.EMPTY;

	private IActionManager actionMgr;
	ScrolledForm form = null;
	private Button updateOnClick;
	private Button noUpdateOnClick;
	private Button closureButton;
	private Button fixWarningButton;
	private Button refreshButton;
	private Button hideButton;
	private Text elemDespContentText;

	private ConfigurationProperties configProperties;
	private Button hideErrorButton;
	private Button hideWarnButton;
	private Button hideInfoButton;

	private ShowErrorJob showErrorJob = new ShowErrorJob(AuthoringUIResources.Configuration_Problem_Refresh); //$NON-NLS-1$

	protected ISelectionChangedListener treeSelectionListener = new ISelectionChangedListener() {
		/**
		 *	Handles the selection of an item in the tree viewer
		 *
		 *	@param event ISelection
		 */
		public void selectionChanged(final SelectionChangedEvent event) {
			BusyIndicator.showWhile(ConfigurationPage.this.getSite().getShell().getDisplay(), new Runnable() {
				public void run() {
					IStructuredSelection selection= (IStructuredSelection) event.getSelection();
					Object selectedElement= selection.getFirstElement();
					selectedElement = TngUtil.unwrap(selectedElement);
					if (selectedElement == null) {
						return;
					} else if (elemDespContentText != null) {
						// display selected element's description
						String briefDesc = getConfigData().getSelectionInfo(selectedElement);
						elemDespContentText.setText(briefDesc != null ? briefDesc : ""); //$NON-NLS-1$
					}
				}
			});
		}
	};

	private static final ClosureListener closureListener = new ClosureListener() {
		@Override
		public void errorAdded(MethodConfiguration config, IConfigurationError error) {
			IConfigurationManager configMgr = LibraryService.getInstance().getConfigurationManager(config);
			if (configMgr.getConfigurationProperties().toHide(error)) {
				return;
			}
			markerHelper.createMarker(config, error);
		}

		@Override
		public void errorRemoved(MethodConfiguration config, IConfigurationError error) {
			markerHelper.deleteMarker(config, error);
		}

		@Override
		public void errorUpdated(MethodConfiguration config, IConfigurationError error) {
			markerHelper.adjustMarker(null, config, error);
		}
	};

	private IPluginUIPackageContextChangedListener layoutListener = new IPluginUIPackageContextChangedListener() {
		public void layoutChanged(boolean isFlat) {
			refreshViewers();
		}
	};

	/**
	 * Creates an instance
	 * @param editor
	 */
	public ConfigurationPage(FormEditor editor) {
		super(editor, AuthoringUIResources.ConfigurationPage_Description1, 
				AuthoringUIResources.ConfigurationPage_Description2); 
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#createFormContent(org.eclipse.ui.forms.IManagedForm)
	 */
	protected void createFormContent(IManagedForm managedForm) {
		// create form toolkit
		form = managedForm.getForm();
		form.setText(formPrefix + config.getName());
		FormToolkit toolkit = managedForm.getToolkit();

		TableWrapLayout layout = new TableWrapLayout();
		form.getBody().setLayout(layout);

		Section treeSection = toolkit.createSection(form.getBody(),
				Section.DESCRIPTION | Section.TWISTIE | Section.EXPANDED
						| Section.TITLE_BAR);
		treeSection.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		treeSection.setText(AuthoringUIResources.ConfigurationPage_ConfigContent); 
		treeSection.setDescription(AuthoringUIResources.ConfigurationPage_ConfigContentDescription); 
		treeSection.setLayout(new GridLayout());
		
		createContent(toolkit, treeSection);
		
		addListeners();

		// finally set the input.
		setInput(LibraryService.getInstance().getCurrentMethodLibrary());
		
		initializeViewersSelection();
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
		configProperties = LibraryService.getInstance()
		.getConfigurationManager(config).getConfigurationProperties();
		
		getConfigData().setBeingEdit(true);
	}

	/**
	 * Create tree content
	 * @param toolkit
	 * @param section
	 */
	public void createContent(FormToolkit toolkit, Section section) {
		Composite sectionClient = toolkit.createComposite(section);
		sectionClient.setLayoutData(new GridData(GridData.FILL_BOTH));
		section.setClient(sectionClient);
		GridLayout layout = new GridLayout(2, true);
		sectionClient.setLayout(layout);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(
				sectionClient.getParent(),
				AuthoringUIHelpContexts.CONFIGURATION_EDITOR_ALL_CONTEXT);

		Composite buttonComposite = toolkit.createComposite(sectionClient);
		GridLayout gridLayout = new GridLayout(6, false);
		buttonComposite.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;
		buttonComposite.setLayoutData(gridData);
		
		Composite radioComposite = toolkit.createComposite(buttonComposite);
		gridLayout = new GridLayout();
		radioComposite.setLayout(gridLayout);
		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 1;
		radioComposite.setLayoutData(gridData);
		
		
		updateOnClick = toolkit.createButton(radioComposite, AuthoringUIResources.ConfigurationPage_updateOnClick, SWT.RADIO);
		updateOnClick.setToolTipText(AuthoringUIResources.ConfigurationPage_updateOnClickToolTip); 
		updateOnClick.setLayoutData(new GridData(SWT.BEGINNING, SWT.DEFAULT, false, false));
		updateOnClick.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
				AuthoringUIResources.ConfigurationPage_updateOnClickToolTip));
		updateOnClick.setSelection(configProperties.isUpdateOnClick());
		
		noUpdateOnClick = toolkit.createButton(radioComposite, AuthoringUIResources.ConfigurationPage_noUpdateOnClick, SWT.RADIO);
		noUpdateOnClick.setToolTipText(AuthoringUIResources.ConfigurationPage_noUpdateOnClickToolTip); 
		noUpdateOnClick.setLayoutData(new GridData(SWT.BEGINNING, SWT.DEFAULT, false, false));
		noUpdateOnClick.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
				AuthoringUIResources.ConfigurationPage_noUpdateOnClickToolTip));
		noUpdateOnClick.setSelection(configProperties.isNoUpdateOnClick());		

		Group group = new Group(buttonComposite, SWT.NONE);
		group.setLayout(new GridLayout(1, false));
		GridData data = new GridData(SWT.DEFAULT, SWT.DEFAULT, false, false);
		group.setLayoutData(data);
		group.setText(AuthoringUIResources.configProblemViewOptionsText);

		hideErrorButton = SWTUtil.createCheckbox(group, AuthoringUIResources.hideErrosText);
		hideWarnButton = SWTUtil.createCheckbox(group, AuthoringUIResources.hideWarningsText);
		hideInfoButton = SWTUtil.createCheckbox(group,AuthoringUIResources.hideInfosText);
		
		hideErrorButton.setSelection(configProperties.isHideErrors());
		hideWarnButton.setSelection(configProperties.isHideWarnings());
		hideInfoButton.setSelection(configProperties.isHideInfos());		

		
		
		hideButton = toolkit.createButton(buttonComposite, "", SWT.PUSH //$NON-NLS-1$
				| GridData.HORIZONTAL_ALIGN_END);
		hideButton.setImage(AuthoringUIPlugin.getDefault().getSharedImage(
				"hideUncheckedElem.gif")); //$NON-NLS-1$
		hideButton.setToolTipText(AuthoringUIResources.ConfigurationPage_hideToolTip); 
		hideButton.setLayoutData(new GridData(GridData.END));
		hideButton.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
				AuthoringUIResources.ConfigurationPage_hideToolTip));
		
		fixWarningButton = toolkit.createButton(buttonComposite, "", SWT.PUSH); //$NON-NLS-1$
		fixWarningButton.setImage(AuthoringUIPlugin.getDefault()
				.getSharedImage("addref_co.gif")); //$NON-NLS-1$
		fixWarningButton.setToolTipText(AuthoringUIResources.ConfigurationPage_AddMissingToolTip); 
		fixWarningButton.setLayoutData(new GridData(GridData.END));
		fixWarningButton.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
				AuthoringUIResources.ConfigurationPage_AddMissingToolTip));

		// Add the closure button.
		closureButton = toolkit.createButton(buttonComposite, "", SWT.PUSH); //$NON-NLS-1$
		closureButton.setImage(AuthoringUIPlugin.getDefault().getSharedImage(
				"closure_co.gif")); //$NON-NLS-1$
		closureButton.setToolTipText(AuthoringUIResources.ConfigurationPage_MakeClosureToolTip); 
		closureButton.setLayoutData(new GridData(GridData.END));
		closureButton.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
				AuthoringUIResources.ConfigurationPage_MakeClosureToolTip));
		// closureButton.setText("");

		refreshButton = toolkit.createButton(buttonComposite, "", SWT.PUSH); //$NON-NLS-1$
		refreshButton.setImage(AuthoringUIImages.IMG_REFRESH);
		refreshButton.setToolTipText(AuthoringUIResources.refreshButton_text);
		{
			GridData gd = new GridData(GridData.END
					| GridData.HORIZONTAL_ALIGN_END);
			gd.horizontalAlignment = 3;
			gd.horizontalSpan = 1;
			refreshButton.setLayoutData(gd);
		}
		refreshButton.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
				AuthoringUIResources.refreshButton_text));

		// Create Viewer and Handle Listener for the viewer.
		createViewers(toolkit, sectionClient);

		Label elemDespLabel = toolkit
				.createLabel(sectionClient, AuthoringUIResources.ConfigurationPage_Description); 
		GridData gd1 = new GridData();
		gd1.horizontalSpan = 6;
		elemDespLabel.setLayoutData(gd1);

		elemDespContentText = toolkit.createText(sectionClient, "", SWT.NONE //$NON-NLS-1$
				| SWT.MULTI | SWT.READ_ONLY | SWT.WRAP);
		GridData gd2 = new GridData(GridData.FILL_BOTH);
		gd2.grabExcessHorizontalSpace = true;
		gd2.horizontalSpan = 6;
		gd2.heightHint = 50;

		toolkit.paintBordersFor(sectionClient);
		toolkit.paintBordersFor(buttonComposite);
		elemDespContentText.setLayoutData(gd2);
		
		// set text widget to viewers so they can update description field
		configViewer.addSelectionChangedListener(treeSelectionListener);
		addCategoryViewer.addSelectionChangedListener(treeSelectionListener);
		subCategoryViewer.addSelectionChangedListener(treeSelectionListener);

		hideButton.setEnabled(true);
		hideButton.setVisible(true);
		refreshButton.setEnabled(true);
		refreshButton.setVisible(true);
		fixWarningButton.setEnabled(true);
		fixWarningButton.setVisible(true);
		closureButton.setEnabled(true);
		closureButton.setVisible(true);
	}


	private void initializeViewersSelection() {
		initializeConfigFactory();

    	initConfigViewer();
    	
		// read from config and check the appropriate items in the CC viewers
		List<ContentCategory> addCats = new ArrayList<ContentCategory>(config.getAddedCategory());
		initializeViewerSelection(addCategoryViewer, addCats);
    	List<ContentCategory> subCats = new ArrayList<ContentCategory>(config.getSubtractedCategory());
    	initializeViewerSelection(subCategoryViewer, subCats);
    	
	}

	private void initConfigViewer() {
		configViewer.setConfigData(getConfigData());
		List<MethodPackage> packages = new ArrayList<MethodPackage>(config.getMethodPackageSelection());
    	List<MethodPlugin> plugins = new ArrayList<MethodPlugin>(config.getMethodPluginSelection());
    	initializeConfigViewerSelection(configViewer, packages, plugins);
    	setStateForCategoriesUIFolder(configViewer, plugins);
	}
	
	/** 
	 * This method is only meant for configuration viewer since there are lot
	 * of special cases taken care in this.
	 * 
	 * @param viewer
	 * @param packages
	 * @param plugins
	 */
	private void initializeConfigViewerSelection(ContainerCheckedTreeViewer viewer,
			List<MethodPackage> packages, List<MethodPlugin> plugins) {
		boolean oldCode = ConfigContentPackageItemProvider.oldCode;
		
		// We need to do following 2 lines because viewer.setChecked doesn't
		// work correctly
		// for content packages if viewer setCheckedElements is not run before
		// Somehow setCheckedElements initializes something...
		// for process package viwer.setChecked works fine.
		if (oldCode) {
			viewer.setCheckedElements(packages.toArray());
		} else {
			List pksWithLeafs = new ArrayList();
			for (MethodPackage element : packages) {
				pksWithLeafs.add(element);
				try {
					Object leaf = contProvider.getLeafElementsNode(element);
					if (leaf != null) {
						pksWithLeafs.add(leaf);
					}
				} catch (Exception e) {
				}
			}
			viewer.setCheckedElements(pksWithLeafs.toArray());
		}
		viewer.setCheckedElements((new ArrayList()).toArray());

		if (!packages.isEmpty()) {
			List<LeafElementsItemProvider> uncheckedLeafs = new ArrayList<LeafElementsItemProvider>();
			
			for (MethodPackage element : packages) {
				try {
					if (!contProvider.hasChildren(element)) {
						viewer.setChecked(element, true);
					}
					if (!oldCode) {
//						System.out.println("LD> element: " + element);
						LeafElementsItemProvider leaf = contProvider.getLeafElementsNode(element);
						if (leaf != null) {
							if (element instanceof ContentPackage
									&& !getConfigData().elementsUnslected(
											(ContentPackage) element)) {
								viewer.setChecked(leaf, true);
							} else {
								uncheckedLeafs.add(leaf);
							}
						}
					}
				} catch (Exception e) {
				}
			}
			if (viewer instanceof MethodContainerCheckedTreeViewer) {
				((MethodContainerCheckedTreeViewer) viewer).handleLeafCrayChecks(uncheckedLeafs);			
			}
		}

		// special case for Method Content when plugin is selected but there are
		// no method content packages
		IContentProvider contProvider = viewer.getContentProvider();
		if (contProvider instanceof ConfigPackageContentProvider) {
			ConfigPackageContentProvider configContProvider = (ConfigPackageContentProvider) contProvider;
			for (MethodPlugin plugin : plugins) {
				Object[] children = configContProvider.getChildren(plugin);
				traverse(viewer, children, configContProvider);
			}
		}
	}
	
	/**
	 * This traverse is only called for plugins when they are included in the configuration.
	 * This method checks special method packages UI folder if there are no method content  packages and plugin is
	 * selected.
	 * 
	 * @param viewer
	 * @param items
	 * @param configContProvider
	 */
	private void traverse(ContainerCheckedTreeViewer viewer, Object[] items,
			ConfigPackageContentProvider configContProvider) {
		for (Object child: items) {
			Object[] children = configContProvider.getChildren(child);
			if (child instanceof MethodPackagesItemProvider) {
				// for MethodPackages if there are no content pacakges then select this UI folder too if plugin is
				// selected. We only call this traverse method for selected plugins, so you don't need to check again
				// if plugin is selected
				if (children.length == 0) {
					viewer.setChecked(child, true);
				}
				break;
			}
			if (child instanceof ContentItemProvider) {
				// No need to traverse other than contentItem provider
				traverse(viewer, children, configContProvider);
			}
		}
	}

	/**
	 * Set state for categories UI folder.
	 * If plugin is selected, categories UI folder should be selected. There are no methods to disable TreeItem.
	 * We achieve similar look and feel by changing background/foreground color.
	 * @param viewer
	 * @param plugins
	 */
	private void setStateForCategoriesUIFolder(ContainerCheckedTreeViewer viewer, List<MethodPlugin>plugins) {	 	
    	for (MethodPlugin plugin : plugins) {
    		// if plugin is selected then select categories folder as well
    		if (viewer.getGrayed(plugin)) {
    			try {
	    			Object[] children = contProvider.getChildren(plugin);
	    			for (Object child : children) {
	    				if (child instanceof ConfigPageCategoriesItemProvider){
	    					viewer.setChecked(child, true);
	    				}	
    				}
    			}
    			catch (Exception e) {			
    			}
    		}
    		
    		// make categories folder enabled/disbled.
			Tree t = viewer.getTree();
			TreeItem[] items = t.getItems();
			for (TreeItem parent : items) {
				if (parent.getData().equals(plugin)) {
					TreeItem[] childItems = parent.getItems();
					for (TreeItem child : childItems) {
						if (child.getData() instanceof ConfigPageCategoriesItemProvider) {
							if (viewer.getChecked(plugin))
								child.setForeground(ColorConstants.gray);
							else
								child.setForeground(ColorConstants.black);
						}
					}
				}
			}
    	}
	}
	
	private void initializeViewerSelection(ContainerCheckedTreeViewer viewer, List<? extends Object> elements) {
		if (!elements.isEmpty())
			viewer.setCheckedElements(elements.toArray());
	}

	private void createViewers(FormToolkit toolkit, Composite sectionClient) {
		Composite configViewerLabelComposite = toolkit.createComposite(sectionClient);
		GridData gd = new GridData(SWT.FILL, SWT.END, true, false);
		configViewerLabelComposite.setLayoutData(gd);
		gd.horizontalSpan = 1;
		configViewerLabelComposite.setLayout(new GridLayout(3, false));

		Composite addCatsViewerLabelComposite = toolkit.createComposite(sectionClient);
		gd = new GridData(SWT.FILL, SWT.END, true, false);
		addCatsViewerLabelComposite.setLayoutData(gd);
		gd.horizontalSpan = 1;
		addCatsViewerLabelComposite.setLayout(new GridLayout(3, false));
		
		// create the library tree viewer
		configViewer = new MethodContainerCheckedTreeViewer(sectionClient);
		
		gd = new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL);
		gd.heightHint = 200;
		gd.verticalSpan = 3;
		configViewer.getTree().setLayoutData(gd);

		contProvider = new ConfigPackageContentProvider(adapterFactory);
		configViewer.setContentProvider(contProvider);
		labelProvider = new ConfigPackageLabelProvider(contProvider);
		configViewer.setLabelProvider(labelProvider);
		createViewerLabelAndButtons(toolkit, configViewerLabelComposite,
				AuthoringUIResources.ConfigurationPage_TreeTitleLabel, configViewer);

		
		
		addCategoryViewer = new MethodContainerCheckedTreeViewer2(sectionClient);
		gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
		gd.heightHint= 200;
		addCategoryViewer.getTree().setLayoutData(gd);
		addCategoryViewer.setContentProvider(new CategoryContentProvider(adapterFactory, config));
		addCategoryViewer.setLabelProvider(new CategoryLabelProvider(adapterFactory));

		createViewerLabelAndButtons(toolkit, addCatsViewerLabelComposite,
				AuthoringUIResources.ConfigurationPage_AddCategoriesTitleLabel, addCategoryViewer);
	
		Composite subCatsViewerLabelComposite = toolkit.createComposite(sectionClient);
		gd = new GridData(SWT.FILL, SWT.END, true, false);
		subCatsViewerLabelComposite.setLayoutData(gd);
		gd.horizontalSpan = 1;
		subCatsViewerLabelComposite.setLayout(new GridLayout(3, false));
		
		subCategoryViewer = new MethodContainerCheckedTreeViewer2(sectionClient);
		gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
		gd.heightHint= 200;
		subCategoryViewer.getTree().setLayoutData(gd);
		subCategoryViewer.setContentProvider(new CategoryContentProvider(adapterFactory, config));
		subCategoryViewer.setLabelProvider(new CategoryLabelProvider(adapterFactory));
		createViewerLabelAndButtons(toolkit, subCatsViewerLabelComposite,
				AuthoringUIResources.ConfigurationPage_SubCategoriesTitleLabel, subCategoryViewer);
		
		// add listener so the 2 Category viewers are in sync
		// that is, when an item is checked in one, it is unchecked in the other
		addCategoryViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(final CheckStateChangedEvent event) {
				//Potentially long operation - show a busy cursor
				BusyIndicator.showWhile(subCategoryViewer.getTree().getDisplay(), new Runnable() {
					public void run() {
						if (event.getChecked())
							subCategoryViewer.setChecked(event.getElement(), false);
					}
				});
			}
		});

		subCategoryViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(final CheckStateChangedEvent event) {
				//Potentially long operation - show a busy cursor
				BusyIndicator.showWhile(addCategoryViewer.getTree().getDisplay(), new Runnable() {
					public void run() {
						if (event.getChecked())
							addCategoryViewer.setChecked(event.getElement(), false);
					}
				});
			}
		});
	}
	
	private void createViewerLabelAndButtons(FormToolkit toolkit, Composite parent, String text, final TreeViewer viewer) {
		Label label = toolkit.createLabel(parent, text);
		GridData gd = new GridData(SWT.FILL, SWT.END, true, false);
		label.setLayoutData(gd);
		
		Button expandButton = toolkit.createButton(parent, null, SWT.PUSH);
		gd = new GridData(SWT.RIGHT, SWT.END, false, false);
		expandButton.setLayoutData(gd);
		expandButton.setImage(AuthoringUIPlugin.getDefault().getSharedImage("expandall.gif")); //$NON-NLS-1$
		expandButton.setToolTipText(AuthoringUIResources.FilterDialog_ExpandAll); 
		expandButton.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
						AuthoringUIResources.FilterDialog_ExpandAll));
		
		expandButton.addSelectionListener(new SelectionListener(){
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
			
			public void widgetSelected(SelectionEvent e) {
				viewer.expandAll();
			}
		});


		Button collapseButton = toolkit.createButton(parent, null, SWT.PUSH);
		gd = new GridData(SWT.RIGHT, SWT.END, false, false);
		collapseButton.setLayoutData(gd);
		collapseButton.setImage(AuthoringUIPlugin.getDefault().getSharedImage("collapseall.gif")); //$NON-NLS-1$
		collapseButton.setToolTipText(AuthoringUIResources.FilterDialog_CollapseAll); 
		collapseButton.getAccessible().addAccessibleListener(new AuthoringAccessibleListener(
				AuthoringUIResources.FilterDialog_CollapseAll));
		collapseButton.addSelectionListener(new SelectionListener(){
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
			
			public void widgetSelected(SelectionEvent e) {
				viewer.collapseAll();
			}
		});

	}

	
	private void reInitializeConfigFactory() {
		// the following may not be the most efficient way
		// need a fast way to update the closure
//		System.out.println("$$$ reInit closure for config add notification!");

		createConfigurationClosure();
	}
	
	private void createConfigurationClosure() {
		closure = new ConfigurationClosure(actionMgr, config);
		closure.addListener(closureListener);
		if (labelProvider != null) {
			labelProvider.setClosure(closure);
		}
	}


	/**
	 * Set input for connfiguration viewer
	 * @param input
	 */
	private void setInput(Object input) {
		configViewer.setInput(input);
		addCategoryViewer.setInput(input);
		subCategoryViewer.setInput(input);
		
		configViewer.expandAll();
		configViewer.collapseAll();
		addCategoryViewer.expandAll();
		addCategoryViewer.collapseAll();
		subCategoryViewer.expandAll();
		subCategoryViewer.collapseAll();

//		updateCheckStates();
	}

	private void refreshViewers() {
		configViewer.refresh();
		addCategoryViewer.refresh();
		subCategoryViewer.refresh();
	}
	
	
	protected void showHideElements() {
		configViewerHideUncheckedFilter.toggleHideUnchecked();
		addCategoryHideUncheckedFilter.toggleHideUnchecked();
		subCategoryHideUncheckedFilter.toggleHideUnchecked();
		refreshViewers();
//		updateCheckStates(); // neded to have this to update the check status
	}
	
	/**
	 * Initialize configuration factory
	 */
	public void initializeConfigFactory() {
		// loading the configuration closure might be slow,
		// display a progress bar
		org.eclipse.epf.library.edit.util.IRunnableWithProgress runnable = new org.eclipse.epf.library.edit.util.IRunnableWithProgress() {
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				createConfigurationClosure();
			}
		};

		UserInteractionHelper.runWithProgress(runnable, AuthoringUIResources.ConfigurationPage_LoadingMessage); 

		configViewerHideUncheckedFilter = new HideUncheckedViewerFilter(configViewer);
		configViewer.addFilter(configViewerHideUncheckedFilter);
		addCategoryHideUncheckedFilter = new HideUncheckedViewerFilter(addCategoryViewer);
		addCategoryViewer.addFilter(addCategoryHideUncheckedFilter);
		subCategoryHideUncheckedFilter = new HideUncheckedViewerFilter(subCategoryViewer);
		subCategoryViewer.addFilter(subCategoryHideUncheckedFilter);
		
	}

	
	private void addListeners() {
		updateOnClick.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleButtonWidgetSelected(e,
						MethodElementPropertyHelper.CONFIG_UPDATE_ON_CLICK);
			}
		});
		
		noUpdateOnClick.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleButtonWidgetSelected(e,
						MethodElementPropertyHelper.CONFIG_NO_UPDATE_ON_CLICK);
			}
		});
		
		hideErrorButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleButtonWidgetSelected(e,
						MethodElementPropertyHelper.CONFIG_PROPBLEM_HIDE_ERRORS);
			}
		});
		
		hideWarnButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleButtonWidgetSelected(e,
						MethodElementPropertyHelper.CONFIG_PROPBLEM_HIDE_WARNINGS);
			}
		});
		
		hideInfoButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleButtonWidgetSelected(e,
						MethodElementPropertyHelper.CONFIG_PROPBLEM_HIDE_INFOS);
			}
		});
		
		closureButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				
				BusyIndicator.showWhile(form.getDisplay(), new Runnable() {
					public void run() {
						makeClosure();
					}
				});
			};
		});
		fixWarningButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				BusyIndicator.showWhile(form.getDisplay(), new Runnable() {
					public void run() {
						fixWarning();
					}
				});				
			};
		});
		refreshButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				BusyIndicator.showWhile(form.getDisplay(), new Runnable() {
					public void run() {
//	Comment out the following "saveConfiguration()" which was added at version 1.3 
//	- not sure why. So leave the commented out trace here for easy future reference.					
//						saveConfiguration();
						showErrors();
					}
				});				
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		hideButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				showHideElements();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		// add a check state change listener
		ICheckStateListener configCheckStateListener = new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent evt) {
				
				BusyIndicator.showWhile(form.getDisplay(), new Runnable() {
					public void run() {
						collapseConfigView();
						// save configurtion
						saveConfiguration();
						if (updateOnClick.getSelection()) {
							// update the closure error
							showErrors();
						}
						actionMgr.execute(new MethodElementSetPropertyCommand(config, TOUCHED_BY_CONFIG_EDITOR, Boolean.TRUE.toString()));
					}
				});
				
			}
		};
		
		ICheckStateListener catsCheckStateListener = new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				
				BusyIndicator.showWhile(form.getDisplay(), new Runnable() {
					public void run() {
						collapseConfigView();
						saveContentCategorySelectionsToConfiguration();
						if (updateOnClick.getSelection()) {
							// update the closure error
							showErrors();
						}
					}
				});
			}
		};
		
		configViewer.addCheckStateListener(configCheckStateListener);
		addCategoryViewer.addCheckStateListener(catsCheckStateListener);
		subCategoryViewer.addCheckStateListener(catsCheckStateListener);

		// listen to the library changes and automatically update the
		// configuration view
		libListener = new ILibraryChangeListener() {
			public void libraryChanged(int option, Collection<Object> changedItems) {
				// for performance reason, we should not response to every
				// library change
				// only cover package and plugin changes
				if (option == ILibraryChangeListener.OPTION_DELETED
						|| option == ILibraryChangeListener.OPTION_NEWCHILD
						|| option == ILibraryChangeListener.OPTION_NEWCHILD) {
					if (changedItems != null && changedItems.size() > 0) {
						Object o = changedItems.toArray()[0];
						if (o instanceof MethodPlugin
								|| o instanceof ProcessComponent
								|| o instanceof MethodPackage
								|| o instanceof CustomCategory) {
							reInitializeConfigFactory();
							refreshViewers();
//							updateCheckStates();
							showErrors();
						}
					}
				}
			}
		};

		ILibraryManager manager = LibraryService.getInstance().getCurrentLibraryManager();
		if (manager != null) {
			manager.addListener(libListener);
		}
		
		// listen to plugin presentation layout changes
		PluginUIPackageContext.INSTANCE.addListener(layoutListener);
	}
	
	private void collapseConfigView() {
		collapseConfigView(config);
	}
	
	public static void collapseConfigView(MethodConfiguration config) {
		final ConfigurationView configView = ConfigurationView.getView();
		if (configView == null) {
			return;
		}
		if (! config.getName().equals(configView.getCurrentConfig())) {
			return;
		}
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			public void run() {
				if (configView.getViewer() instanceof TreeViewer) {
					((TreeViewer) configView.getViewer()).collapseAll();
				}
			}
		});
	}
	
	class ShowErrorJob extends Job {
		
		public ShowErrorJob(String name) {
			super(name);
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {

			List<Object> invalid0 = null;
			List<Object> updateInvalid0 = null;

			try {
				canDisposeClosure = false;
				closure.setAbortCheckError(false);

				// save the previous invalid elements
				// final List<Object> invalid = closure.getInvalidElements();
				invalid0 = closure.getInvalidElements();
				closure.checkError();
				if (closure.isAbortCheckError()) {
					return Status.OK_STATUS;
				}
				updateInvalid0 = closure.getInvalidElements();
			} finally {
				canDisposeClosure = true;
				if (disposed) {
					closure.dispose();
				}
			}
					
			final List<Object> invalid = invalid0;
			final List<Object> updateInvalid = updateInvalid0;
			
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (configViewerHideUncheckedFilter.isHideUnchecked()) {
						configViewer.refresh();
					} else {
						// get the new error elements, add to the previous error elements,
						// and update them to update the error/warning images
//						invalid.addAll(closure.getInvalidElements());
						invalid.addAll(updateInvalid);

						// also add the UI folders
						ConfigPackageContentProvider cp = (ConfigPackageContentProvider) configViewer
								.getContentProvider();
						invalid.addAll(cp.getUIElements());

						configViewer.update(invalid.toArray(), null);
					}
				}

			});
			return Status.OK_STATUS;
		}

	}
	
	public void showErrorsOnSave() {
		if (updateOnClick != null && ! updateOnClick.getSelection()) {
			showErrors();
		}
	}
	
	private void showErrors() {
		if (closure == null || closure.isAbortCheckError()) {
			return;
		}
		closure.setAbortCheckError(true);
		showErrorJob.cancel();
		showErrorJob.schedule();
	}
	
	//old code for showErrors method
	protected void showErrors_old() {
		// save the previous invalid elements
		List<Object> invalid = closure.getInvalidElements();

		closure.checkError();
		
		if (configViewerHideUncheckedFilter.isHideUnchecked()) {
			configViewer.refresh();
		} else {
			// get the new error elements, add to the previous error elements,
			// and update them to update the error/warning images
			invalid.addAll(closure.getInvalidElements());

			// also add the UI folders
			ConfigPackageContentProvider cp = (ConfigPackageContentProvider) configViewer
					.getContentProvider();
			invalid.addAll(cp.getUIElements());

			configViewer.update(invalid.toArray(), null);
		}

	}

	
	/**
	 * Make configuration closure
	 *
	 */
	protected void makeClosure() {
		if (! ConfigurationHelper.getDelegate().makeClosure(config)) {
			closure.fixErrors();
		}
		configViewer.refresh();
		initConfigViewer();
		//refreshViewers();
//		updateCheckStates();
		//saveConfiguration();

	}

	/**
	 * Fix all warnings 
	 */
	protected void fixWarning() {
		if (! ConfigurationHelper.getDelegate().makeClosure(config)) {
			closure.fixProblems();
		}
		configViewer.refresh();
		initConfigViewer();
		//refreshViewers();	
//		updateCheckStates();
		//saveConfiguration();

	}

	private ConfigurationData getConfigData() {
		ConfigurationData configData = LibraryService.getInstance()
		.getConfigurationManager(config)
				.getConfigurationData();		
		return configData;
	}
	
	/**
	 * Save configuration
	 * @return
	 * 		True if configuration is save successfully, false otherwise
	 */
	public boolean saveConfiguration() {	
		getConfigData().setEnableUpdate(false);
		boolean ret = doSaveConfiguration();
		getConfigData().setEnableUpdate(true);
		return ret;
	}
	
	private boolean doSaveConfiguration() {

		boolean oldNotify = config.eDeliver();
	    try
	    {    	
	    	List<MethodPackage> oldPackages = new ArrayList<MethodPackage>(config.getMethodPackageSelection());
	    	List<MethodPlugin> oldPlugins = new ArrayList<MethodPlugin>(config.getMethodPluginSelection());
	    	
	    	Set<MethodPackage> newPackages = getCheckedMethodPackages(configViewer.getCheckedElements());
	    	Set<MethodPlugin> newPlugins = getCheckedMethodPlugins(configViewer.getCheckedElements());
	    	Set<MethodPackage> elementsUnslectedPkgs = configViewer.getElementsUnslectedPkgs(newPackages, contProvider);
	    	
	    	// set categories checked for plugins selected
	    	List<MethodPlugin> plugins = new ArrayList<MethodPlugin>();
	    	plugins.addAll(newPlugins);
	    	plugins.addAll(oldPlugins);
	    	setStateForCategoriesUIFolder(configViewer, plugins);
	    	
	    	oldPackages.removeAll(newPackages);
	    	oldPlugins.removeAll(newPlugins);	
    	
	    	newPackages.removeAll(config.getMethodPackageSelection());
	    	newPlugins.removeAll(config.getMethodPluginSelection());
	
			if (ConfigurationUtil.removeCollFromMethodPluginList(actionMgr, config, oldPlugins) == false)
				return false;
			if (ConfigurationUtil.removeCollFromMethodPackageList(actionMgr, config, oldPackages) == false)
				return false;
			
			if (ConfigurationUtil.addCollToMethodPluginList(actionMgr, config, newPlugins) == false)
				return false;
			if (ConfigurationUtil.addCollToMethodPackageList(actionMgr, config, newPackages) == false)
				return false;			
			getConfigData().updatePackageSelections(actionMgr, elementsUnslectedPkgs, oldPackages, newPackages);
			
	    	// validate before save
			LibraryUtil.validateMethodConfiguration(actionMgr, config);

			actionMgr.execute(new MethodElementSetPropertyCommand(config, TOUCHED_BY_CONFIG_EDITOR, Boolean.TRUE.toString()));
			setDoneLoadCheckPkgs();
			
			return true;
		
		} finally {
			config.eSetDeliver(oldNotify);
		}
	}
	
	private void setDoneLoadCheckPkgs() {		
		Set<MethodPackage> doneLoadCheckPkgs = new HashSet<MethodPackage>();
		for (MethodPlugin plugin : config.getMethodPluginSelection()) {
			collectDoneLoadCheckPkgs(plugin.getMethodPackages(), doneLoadCheckPkgs);
		}
		MethodConfigurationPropUtil propUtil = MethodConfigurationPropUtil.getMethodConfigurationPropUtil(actionMgr);
		propUtil.setDoneLoadCheckPkgs(config, doneLoadCheckPkgs);
	}	
	
	private void collectDoneLoadCheckPkgs(List<MethodPackage> pkgs, Set<MethodPackage> doneLoadCheckPkgs) {
		PropUtil propUtil = PropUtil.getPropUtil();
		for (MethodPackage pkg : pkgs) {
			Boolean b = propUtil.getBooleanValue(pkg, PropUtil.Pkg_loadCheck);
			if (b != null && b.booleanValue()) {
				doneLoadCheckPkgs.add(pkg);
			}
			collectDoneLoadCheckPkgs(pkg.getChildPackages(), doneLoadCheckPkgs);
		}		
	}	
	
	/**
	 * Save configuration
	 * @return
	 * 		True if configuration is save successfully, false otherwise
	 */
	public boolean saveContentCategorySelectionsToConfiguration() {
		
    	List<ContentCategory> oldAddCats = new ArrayList<ContentCategory>(config.getAddedCategory());
    	List<ContentCategory> oldSubCats = new ArrayList<ContentCategory>(config.getSubtractedCategory());
    	
    	Set<ContentCategory> newAddCats = getCheckedContentCategories(addCategoryViewer.getCheckButNotGrayedElements());
    	Set<ContentCategory> newSubCats = getCheckedContentCategories(subCategoryViewer.getCheckButNotGrayedElements());
    	
    	oldAddCats.removeAll(newAddCats);
    	oldSubCats.removeAll(newSubCats);
    	
    	newAddCats.removeAll(config.getAddedCategory());
    	newSubCats.removeAll(config.getSubtractedCategory());
		
		if (ConfigurationUtil.removeCollFromAddedCategoryList(actionMgr, config, oldAddCats) == false)
			return false;
		
		if (!newAddCats.isEmpty()) {
			if (ConfigurationUtil.addCollToAddedCategoryList(actionMgr, config, newAddCats) == false)
				return false;
			
			Map<String, MethodElement> pluginMap = MethodElementUtil.buildMap(config.getMethodPluginSelection());
			HashSet<MethodPlugin> newAddedPlugins = new HashSet<MethodPlugin>();
			for (Iterator<ContentCategory> it = newAddCats.iterator(); it.hasNext(); ) {
				ContentCategory cat = it.next();
				MethodPlugin plugin = UmaUtil.getMethodPlugin(cat);
				if (! pluginMap.containsKey(plugin.getGuid())) {
					if (! newAddedPlugins.contains(plugin)) {
						newAddedPlugins.add(plugin);
					}
				}
			}
			if (! newAddedPlugins.isEmpty()) {
				if (ConfigurationUtil.addCollToMethodPluginList(actionMgr, config, newAddedPlugins) == false)
					return false;
				LibraryUtil.validateMethodConfiguration(actionMgr, config);
			}
			
		}
		if (ConfigurationUtil.removeCollFromSubtractedCategoryList(actionMgr, config, oldSubCats) == false)
			return false;
		
		if (ConfigurationUtil.addCollToSubtractedCategoryList(actionMgr, config, newSubCats) == false)
			return false;

		actionMgr.execute(new MethodElementSetPropertyCommand(config, TOUCHED_BY_CONFIG_EDITOR, Boolean.TRUE.toString()));

		return true;
	}
	
	protected Set<ContentCategory> getCheckedContentCategories(Object[] checkedItems) {
		Set<ContentCategory> result = new HashSet<ContentCategory>();
		for (int i = 0; i < checkedItems.length; i++) {
			Object item = TngUtil.unwrap(checkedItems[i]);
			if (item instanceof ContentCategory) {
//				if (config.getMethodPluginSelection().contains(LibraryUtil.getMethodPlugin((ContentCategory)item))) {
					result.add((ContentCategory)item);
//				}
			}
		}
		return result;
	}
	
	protected Set<MethodPackage> getCheckedMethodPackages(Object[] checkedItems) {
		Set<MethodPackage> result = new HashSet<MethodPackage>();
		for (int i = 0; i < checkedItems.length; i++) {
			Object item = TngUtil.unwrap(checkedItems[i]);
			if (item instanceof MethodPackage) {
				result.add((MethodPackage)item);
			}
		}
		return result;
	}
	
	protected Set<MethodPlugin> getCheckedMethodPlugins(Object[] checkedItems) {
		Set<MethodPlugin> result = new HashSet<MethodPlugin>();
		for (int i = 0; i < checkedItems.length; i++) {
			Object item = TngUtil.unwrap(checkedItems[i]);
			if (item instanceof MethodPlugin) {
				result.add((MethodPlugin)item);
			}
		}
		return result;
	}
	
	private boolean disposed = false;
	private boolean canDisposeClosure = true;
	public void dispose() {
		disposed = true;		
		super.dispose();

		getConfigData().setBeingEdit(false, getEditor().isDirty());

		if (libListener != null) {
			ILibraryManager manager = LibraryService.getInstance().getCurrentLibraryManager();
			if (manager != null) {
				manager.removeListener(libListener);
			}
		}
		
		if (layoutListener != null) {
			PluginUIPackageContext.INSTANCE.removeListener(layoutListener);
		}

		if (closure != null) {
			if (canDisposeClosure) {
				closure.dispose();
				closure = null;
			}
		}
		if (adapterFactory != null) {
			adapterFactory.dispose();
			adapterFactory = null;
		}
		
		markerHelper.removeAllMarkers(config);
	}
	
	public void gotoMarker(IMarker marker) {
		// select problem in main viewer
		
		MethodElement e = ConfigurationMarkerHelper.INSTANCE.getErrorMethodElement(marker);

		if (e != null && configViewer != null) {
			configViewer.setSelection(new StructuredSelection(LibraryUtil.getSelectable(e)), true);
		}
	}
	
	public void doQuickFix(IMarker marker) {
		if ( marker == null ) {
			return;
		}
		MethodElement element = markerHelper.getCauseMethodElement(marker);	
		if ( element == null ) {
			return;
		}
		
		if ( closure.getConfigurationManager().getConfigurationData().isElementSubtracted(element)) {
			String message = AuthoringUIResources.bind(
					AuthoringUIResources.configurationPage_QuickfixError_reason1, 
					(new String[] {LibraryUtil.getTypeName(element)}));
			AuthoringUIPlugin.getDefault().getMsgDialog()
				.displayWarning(
						AuthoringUIResources.configurationPage_quickfixError_title, 
						message);
			return;
		}
		
		Object owner = LibraryUtil.getSelectable(element);
		configViewer.setChecked(owner, true);
		saveConfiguration();
		showErrors();					
   }

	private void handleButtonWidgetSelected(SelectionEvent e, String key) {
		Object obj = e.getSource();
		if (obj instanceof Button) {
			Button button = (Button) obj;
			String value = button.getSelection() ? Boolean.TRUE.toString()
					: Boolean.FALSE.toString();
			actionMgr.execute(new MethodElementSetPropertyCommand(config, key, value));
		}
	}

}
