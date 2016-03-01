//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.library.configuration.ConfigurationData;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.DefaultElementRealizer;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.configuration.SupportingElementData;
import org.eclipse.epf.library.edit.configuration.PracticeSubgroupItemProvider;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.edit.uma.MethodElementExt.MethodConfigurationExt;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.MethodConfigurationPropUtil;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.edit.util.MethodElementPropertyHelper;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.layout.BrowsingLayoutSettings;
import org.eclipse.epf.library.layout.HtmlBuilder;
import org.eclipse.epf.library.layout.IElementLayout;
import org.eclipse.epf.library.layout.elements.AbstractElementLayout;
import org.eclipse.epf.library.layout.elements.AbstractProcessElementLayout;
import org.eclipse.epf.library.layout.elements.ElementLayoutExtender;
import org.eclipse.epf.library.layout.elements.SummaryPageLayout;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.realization.RealizationManagerFactory;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;

/**
 * Delegate class used in ConfigurationHelper
 * 
 * @author Weiping Lu 
 * @since 1.5
 */
public class ConfigHelperDelegate {

	private boolean generateHtmlMode = false;
	private boolean publishingMode = false;
	private boolean authoringPerspective = false;
	private MethodConfiguration config;
	private boolean loadForBrowsingNeeded = true;
	private boolean autoSyncedByBrowsing = false;
	private Map<MethodElement, Set<CustomCategory>> dynamicCustomCategoriesMap; 	
	
	public ConfigHelperDelegate() {
		LibraryService.getInstance().addListener(libServiceListener);		
		IRealizationManager mgr = RealizationManagerFactory.getInstance().newRealizationManager(null);
		LibraryEditUtil.getInstance().setDefaultRealizationManager(mgr);
	}

	protected boolean hasDynamicElements(CustomCategory cc) {
		MethodElementProperty prop = MethodElementPropertyHelper.getProperty(
				cc,
				MethodElementPropertyHelper.CUSTOM_CATEGORY__INCLUDED_ELEMENTS);
		return prop != null;
	}
	
	public void buildDynamicCustomCategoriesMap(MethodConfiguration config) {
		try {
		if (config == null) {
			return;
		}
		ConfigurationHelper.getDelegate().loadUserDefinedType();
		dynamicCustomCategoriesMap = new HashMap<MethodElement, Set<CustomCategory>>();		
		ElementRealizer realizer = DefaultElementRealizer.newElementRealizer(config);		
		Set<CustomCategory> set = new HashSet<CustomCategory>();
		MethodElementPropUtil propUtil = MethodElementPropUtil.getMethodElementPropUtil();
		for (MethodPlugin plugin : config.getMethodPluginSelection()) {		
			for (CustomCategory cc : TngUtil.getAllCustomCategories(plugin)) {
				cc = (CustomCategory) ConfigurationHelper.getCalculatedElement(cc, realizer);
				if (cc != null && hasDynamicElements(cc)) {
					set.add(cc);
				}
			}			
		}
		
		for (CustomCategory cc : set) {
			for (MethodElement element : (List<MethodElement>) ConfigurationHelper
					.calc0nFeatureValue(cc, UmaPackage.eINSTANCE
							.getCustomCategory_CategorizedElements(), realizer)) {
				Set<CustomCategory> ccSet = dynamicCustomCategoriesMap.get(element);
				if (ccSet == null) {
					ccSet = new HashSet<CustomCategory>();
					dynamicCustomCategoriesMap.put(element, ccSet);
				}
				ccSet.add(cc);				
			}
		}
		} catch (Throwable e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}
	}
	
	public void clearDynamicCustomCategoriesMap() {
		dynamicCustomCategoriesMap = null;
	}		
	
	public Set<CustomCategory> getDynamicCustomCategories(MethodElement element) {
		return dynamicCustomCategoriesMap == null ? null : dynamicCustomCategoriesMap.get(element);
	}
	
	/**
	 * Test if pkg is a system package of plugin
	 * @param plugin
	 * @param pkg
	 * @return
	 */
	public boolean isSystemPackage(MethodPlugin plugin, MethodPackage pkg) {
		return TngUtil.getAllSystemPackages(plugin).contains(pkg);
	}
	
	public void loadOppositeFeatures(ILibraryResourceSet resouceSet,
			List<OppositeFeature> oppositeFeatures,  MethodElement element) {
		Set<String> GUIDs = new HashSet<String>();
		GUIDs.add(element.getGuid());
		resouceSet.loadOppositeFeatures(oppositeFeatures, GUIDs);
	}
	
	public boolean isOwnerSelected(MethodElement element,
			MethodConfiguration config, boolean checkSubtracted) {
		if (config instanceof Scope) {
			return ((Scope)config).inScope(element);
		}
		MethodLibrary library = LibraryServiceUtil.getMethodLibrary(config);
		ILibraryManager libraryManager = library == null? null : LibraryService.getInstance().getLibraryManager(library);
		if (libraryManager != null) {
			return LibraryService.getInstance()
						.getConfigurationManager(config)
							.getConfigurationData()
								.isOwnerSelected(element, checkSubtracted);
		}
				
		if (element == null) {
			return false;
		}

		if (config == null || ConfigurationHelper.isDescriptionElement(element)) {
			return true;
		}

		// since UMA 1.0.4, configuration can have added categories 
		// and subtracted categories. The order of filtering is:
		// 1. any element in the subtracted categories should be excluded
		// 2. any element in the added categories should be included
		// 3. any element not in the selected package or plugin should be excluded.
		
		if ( checkSubtracted ) {
			// first check subtracted elements
			List subtractedCategories = config.getSubtractedCategory();
			if ( subtractedCategories.size() > 0 ) {
				for ( Iterator it = subtractedCategories.iterator(); it.hasNext(); ) {
					ContentCategory cc = (ContentCategory)it.next();
					if ( cc == element ) {
						return false;
					}
					
					// need to check all content category types and sub-categories
					// we need to have an efficient algorithm for this checking.
					// for now, only check the custom category's categorised elements
					// TODO. Jinhua Xi, 11/27/2006
					if ( cc instanceof CustomCategory ) {
						if ( ((CustomCategory)cc).getCategorizedElements().contains(element) ) {
							return false;
						}
					} else {
						// TODO, not implemented yet
						System.out.println("TODO, isOwnerSelected: not implemented yet"); //$NON-NLS-1$
					}
				}
			}
		}
		
		// then check added categories
		// TODO
		
		// elements beyond configuration scope should be always visible
		if ((element instanceof MethodLibrary)
				|| (element instanceof MethodConfiguration)) {
			return true;
		} else if (element instanceof MethodPlugin) {
			List plugins = config.getMethodPluginSelection();
			return (plugins != null) && plugins.contains(element);
		} else {
			// if the ownerprocess can't show, can't accept
			if (element instanceof Activity) {
				Activity base = (Activity) ((Activity) element)
						.getVariabilityBasedOnElement();
				if (base != null && base != element) {
					MethodElement owningProc = TngUtil.getOwningProcess(base);
					if ( owningProc != null && owningProc != element 
							&& !ConfigurationHelper.inConfig(owningProc, config, checkSubtracted)) {
						return false;
					}
				}
			}

			EObject pkg = LibraryUtil.getSelectable(element);

			// accept global package if the plugin is in the configuration
			if (pkg instanceof MethodPackage
					&& ConfigurationHelper.isGlobalPackage((MethodPackage) pkg)) {
				MethodPlugin plugin = LibraryUtil.getMethodPlugin(pkg);
				return ConfigurationHelper.inConfig(plugin, config, checkSubtracted);
			}

			List pkgs = config.getMethodPackageSelection();
			if (pkgs == null) {
				return false;
			}

			// per Phong's request, for ProcessPackage, check the
			// ProcessComponent parent instead
			if (pkg instanceof ProcessPackage) {
				while ((pkg != null) && !(pkg instanceof ProcessComponent)
						&& !pkgs.contains(pkg)) {
					pkg = pkg.eContainer();
				}
			}

			// if package not selected, return false
			if ((pkg == null) || !pkgs.contains(pkg)) {
				return false;
			}

			return true;
		}
	}
	
	public void loadOppositeFeatures(Map map, List oppositeFeatures, Set deletedGUIDs) {
		int max = oppositeFeatures.size() - 1;
		if (max < 0) {
			return;
		}
		Collection elements = new HashSet(map.values());
		HashSet loadedElements = new HashSet();
		while (!elements.isEmpty()) {
			for (Iterator iter = elements.iterator(); iter.hasNext();) {
				Object obj = iter.next();
				if (obj instanceof MethodElement) {
					MethodElement element = (MethodElement) obj;
					MultiResourceEObject mrEObject = ((MultiResourceEObject) element);
					for (int i = max; i > -1; i--) {
						OppositeFeature oppositeFeature = ((OppositeFeature) oppositeFeatures
								.get(i));
						EStructuralFeature eFeature = oppositeFeature.getTargetFeature();
						if (eFeature.getContainerClass().isInstance(element)) {
							if (eFeature.isMany()) {
								InternalEList list = (InternalEList) element
										.eGet(eFeature);
								if (!list.isEmpty()) {
									boolean resolve = false;
									check_resolve: for (Iterator iterator = list
											.basicIterator(); iterator
											.hasNext();) {
										InternalEObject e = (InternalEObject) iterator
												.next();
										if (e.eIsProxy()) {
											String guid = e.eProxyURI()
													.fragment();
											if (deletedGUIDs.contains(guid)) {
												resolve = true;
												break check_resolve;
											}
										}
									}
									if (resolve) {
										Collection<Object> deletedElements = new HashSet<Object>();
										for (Iterator iterator = list
												.iterator(); iterator.hasNext();) {
											Object o = iterator.next();
											if(o instanceof MethodElement && deletedGUIDs.contains(((MethodElement) o).getGuid())) {
												deletedElements.add(o);
											}
										}
										for (Object e : deletedElements) {
											if(oppositeFeature.isMany()) {
												mrEObject.oppositeAdd(oppositeFeature, e);
											}
											else {
												mrEObject.getOppositeFeatureMap().put(oppositeFeature, e);
											}
										}										
									}
								}
							} else {
								Object value = element.eGet(eFeature, false);
								if (value instanceof InternalEObject) {
									InternalEObject e = (InternalEObject) value;
									if (e.eIsProxy()) {
										String guid = e.eProxyURI().fragment();
										if (deletedGUIDs.contains(guid)) {
											Object o = element.eGet(eFeature);
											if(oppositeFeature.isMany()) {
												mrEObject.oppositeAdd(oppositeFeature, o);
											}
											else {
												mrEObject.getOppositeFeatureMap().put(oppositeFeature, o);
											}
										}
									}
								}
							}
						}
					}
				}
			}
			// gets the newly loaded elements to load their opposite features
			//
			loadedElements.addAll(elements);
			elements = new HashSet(map.values());
			elements.removeAll(loadedElements);
		}
	}
	
	public String generateHtml(Object raw_element, HtmlBuilder htmlBuilder) {
		ResourceHelper.birt_publishing = false;
		
		loadForBrowsing(raw_element);
		
		IElementLayout layout = null;
		String file_url = "about:blank"; //$NON-NLS-1$
		Object element = LibraryUtil.unwrap(raw_element);
		try {
		setGenerateHtmlMode(true);
		if ( raw_element instanceof ActivityWrapperItemProvider ) {
			ActivityWrapperItemProvider wrapper = (ActivityWrapperItemProvider)raw_element;
			Object proc = wrapper.getTopItem();
			if ( element instanceof MethodElement && proc instanceof org.eclipse.epf.uma.Process ) {
				String path = AbstractProcessElementLayout.getPath(wrapper);
				//System.out.println(topItem);
				layout = htmlBuilder.getLayoutManager()
					.createLayout((MethodElement)element, 
							(org.eclipse.epf.uma.Process)proc, path);
				file_url = htmlBuilder.generateHtml(layout);
			}
		} else if (raw_element instanceof PracticeSubgroupItemProvider) {
			PracticeSubgroupItemProvider provider = (PracticeSubgroupItemProvider) raw_element;
			layout = new SummaryPageLayout(htmlBuilder.getLayoutManager(),
					provider.getPractice(), provider.getPrefix(),
					provider.getText(null), (List) provider.getChildren(null),
					provider.getText(null));
			((SummaryPageLayout) layout).setHtmlBuilder(htmlBuilder);
			file_url = htmlBuilder.generateHtml(layout);
		} else if (element instanceof MethodElement) {
				file_url = htmlBuilder.generateHtml((MethodElement)element);
		} 
		} finally {
			setGenerateHtmlMode(false);
		}
		
		if ( file_url == null ) {
			file_url = "about:blank"; //$NON-NLS-1$
		}
		// on linux, the file path need to be specified as file, otherwise it will be treated as url
		// and casuign encoding/decoding issue
		// Linux: Configuration names containing accented characters cannot be browsed.
		else {			
			if (!SWT.getPlatform().equals("win32") && !file_url.startsWith("file://") && //$NON-NLS-1$ //$NON-NLS-2$
				!file_url.equals("about:blank")) //$NON-NLS-1$
			{
				file_url = "file://" + file_url; //$NON-NLS-1$
			}
			
			// Bug 201335 - Refresh does not work correctly for process pages in browsing mode
			// need to append the query string
			if ( layout instanceof AbstractProcessElementLayout ) {
				file_url += ((AbstractProcessElementLayout)layout).getQueryString();
			}
		}
		
		ResourceHelper.birt_publishing = true;
		
		return file_url;
	}
	
	//Should be overriden by sub classes
	public ElementLayoutExtender newElementLayoutExtender(AbstractElementLayout layout) {
		return null;
	}
	
	public void configViewRefreshNotified() {		
	}
	
	public void debugDump(String msg) {
		System.out.println("LD> " + getClass() + ".debugDump: " + msg);	//$NON-NLS-1$//$NON-NLS-2$
	}

	public boolean isPublishingMode() {
		return publishingMode;
	}

	public void setPublishingMode(boolean publishingMode) {
		this.publishingMode = publishingMode;
	}

	public boolean isAuthoringPerspective() {
		return authoringPerspective;
	}

	public void setAuthoringPerspective(boolean authoringPerspective) {
		this.authoringPerspective = authoringPerspective;
	}
	
	public boolean isAuthoringMode() {
		return isAuthoringPerspective();
	}
	
	private boolean supportingInAuthoringMode = false;
	
	public boolean isSupportingInAuthoringMode() {
		return supportingInAuthoringMode;
	}

	public void setSupportingInAuthoringMode(boolean supportingInAuthoringMode) {
		this.supportingInAuthoringMode = supportingInAuthoringMode;
	}

	private IPerspectiveListener perspectiveListener;

	private void addPerspectiveListener() {
		if (perspectiveListener != null) {
			return;
		}
		
		IWorkbenchWindow window = LibraryUtil.getActiveWorkbenchWindow();
		if (window != null) {
			perspectiveListener = new IPerspectiveListener() {
				public void perspectiveActivated(IWorkbenchPage page,
						IPerspectiveDescriptor desc) {
					handleConfigOrPersepctiveChange();
				}

				public void perspectiveChanged(IWorkbenchPage page,
						IPerspectiveDescriptor desc, String id) {
					handleConfigOrPersepctiveChange();
				}
				
			};
			window.addPerspectiveListener(perspectiveListener);
		}

	}
	
	private ILibraryServiceListener libServiceListener = new LibraryServiceListener() {

		public void configurationSet(MethodConfiguration newConfig) {
			addPerspectiveListener();
			
			if (config == newConfig) {
				return;
			}			
			
			handleConfigOrPersepctiveChange();				
			config = newConfig;
		}

	};
	
	protected void loadForBrowsing(Object raw_element) {
		if (!loadForBrowsingNeeded) {
			return;
		}

		MethodConfiguration config = LibraryService.getInstance()
		.getCurrentMethodConfiguration();
		if (config != null && ! BrowsingLayoutSettings.INSTANCE.isIgnoreDynamicParents()) {
			buildDynamicCustomCategoriesMap(config);
		}
		if (raw_element instanceof BreakdownElement
				|| raw_element instanceof ActivityWrapperItemProvider) {

			if (config == null) {
				LibraryUtil.loadAll(LibraryService.getInstance()
						.getCurrentMethodLibrary());
			} else {
				LibraryUtil.loadAllPlugins(config);
				IRealizationManager mgr = getRealizationManager(config);
				if (mgr != null) {
					mgr.updateAllProcesseModels();
					setAutoSyncedByBrowsing(true);
				}
			}
		}
		
		loadForBrowsingNeeded = false;
	}
	
	public IRealizationManager getRealizationManager(MethodConfiguration config) {
		if (config == null || config instanceof Scope) {
			return null;
		}
		IConfigurationManager configMgr = LibraryService.getInstance().getConfigurationManager(config);
		return configMgr == null ? null : configMgr.getRealizationManager();
	}
	
	private void handleConfigOrPersepctiveChange() {
		loadForBrowsingNeeded = true;
		ConfigurationHelper.getDelegate().loadUserDefinedType();
		clearDynamicCustomCategoriesMap();
	}
	
	//Check to see if a process can be converted to a config-free process
	//To be overridden by sub-class	
	public boolean canBeConfigFree(Process proc) {
		return true;
	}
	
	protected boolean isLoadForBrowsingNeeded() {
		return loadForBrowsingNeeded;
	}

	public void setLoadForBrowsingNeeded(boolean loadForBrowsingNeeded) {
		this.loadForBrowsingNeeded = loadForBrowsingNeeded;
	}
	
	public boolean isAutoSyncedByBrowsing() {
		return autoSyncedByBrowsing;
	}

	public void setAutoSyncedByBrowsing(boolean autoSyncedByBrowsing) {
		this.autoSyncedByBrowsing = autoSyncedByBrowsing;
	}
	
	public ConfigurationData getConfigurationData(MethodConfiguration config) {
		if (config == null) {
			return null;
		}
		IConfigurationManager configMgr = LibraryService.getInstance().getConfigurationManager(config);
		ConfigurationData configData = configMgr == null ? null : configMgr.getConfigurationData();
		if (configData == null) {
			LibraryPlugin.getDefault().getLogger().logError("getConfigurationData() == null for: " + config.getName());//$NON-NLS-1$	
		}
		return configData;
	}
	
	public SupportingElementData getSupportingElementData(MethodConfiguration config) {
		if (config == null) {
			return null;
		}
		IConfigurationManager configMgr = LibraryService.getInstance().getConfigurationManager(config);
		return configMgr == null ? null : configMgr.getSupportingElementData();
	}
	
	//Make closure if conig stores any making closure info
	//Return true if processed otherwise false
	public boolean makeClosure(MethodConfiguration config) {
		return false;
	}
	
	public void loadUserDefinedType() {
	}
	
	public boolean additionShowConfigSelectMenu(IWorkbenchPage activePage) {
		return false;
	}
	
	public boolean filterOutEmptyCategories() {
		if (isAuthoringMode()) {
			return false;
		}		
		if (isPublishingMode()) {
			//Publishing logic does its own filter out, ok to return false here
			return false;	
		}		
		return true;
	}
	
	public void fixupLoadCheckPackages(MethodLibrary lib) {
		if (lib == null) {
			return;
		}
		
		Map<MethodPlugin, Set<MethodPackage>> map = new HashMap<MethodPlugin, Set<MethodPackage>>();
		for (MethodConfiguration config : lib.getPredefinedConfigurations()) {
			fixupLoadCheckPackages(map, config);
		}
					
	}

	public void fixupLoadCheckPackages(MethodConfiguration config) {
		fixupLoadCheckPackages(null, config);
	}
	
	private void fixupLoadCheckPackages(
			Map<MethodPlugin, Set<MethodPackage>> map,
			MethodConfiguration config) {
		MethodConfigurationPropUtil propUtil = MethodConfigurationPropUtil.getMethodConfigurationPropUtil();
		MethodConfigurationExt ext = propUtil.getMethocConfigurationExt(config);
		if (ext == null || ext.isLoadCheckPackagesCalled()) {
			return;
		}
		ext.setLoadCheckPackagesCalled(true);
		
		if (! needFixupLoadCheckPackages(config)) {
				return;
		}		
		if (map == null) {
			map = new HashMap<MethodPlugin, Set<MethodPackage>>();
		}
		Set<MethodPackage> selectedPkgs = new HashSet<MethodPackage>(config.getMethodPackageSelection());
		Set<MethodPackage> toAddCheckPkgs =  new HashSet<MethodPackage>();
		for (MethodPlugin plugin : config.getMethodPluginSelection()) {
			Set<MethodPackage> loadCheckPkgs = map.get(plugin);
			if (loadCheckPkgs == null) {
				loadCheckPkgs = new HashSet<MethodPackage>();
				map.put(plugin, loadCheckPkgs);
			}
			collectLoadCheckPkgs(plugin.getMethodPackages(), loadCheckPkgs);
			toAddCheckPkgs.addAll(loadCheckPkgs);
		}
		
		Set<MethodPackage> doneSet = propUtil.getDoneLoadCheckPkgs(config);
		toAddCheckPkgs.removeAll(doneSet);
		Set<MethodPackage> handkedSet =  new HashSet<MethodPackage>(); 
		for (MethodPackage pkg : toAddCheckPkgs) {
			if (handkedSet.add(pkg)) {
				if (checkParentPackage(handkedSet, config, selectedPkgs, pkg,
						toAddCheckPkgs)) {
					addToConfig(config, selectedPkgs, pkg);
				}
			}
		}
	}
	
	public boolean needFixupLoadCheckPackages(MethodConfiguration config) {
		return config != null;
	}

	private void addToConfig(MethodConfiguration config,
			Set<MethodPackage> selectedPkgs, MethodPackage pkg) {
		config.getMethodPackageSelection().add(pkg);
		selectedPkgs.add(pkg);
	}
	
	private boolean checkParentPackage(Set<MethodPackage> handkedSet, MethodConfiguration config, Set<MethodPackage> selectedPkgs, MethodPackage pkg, Set<MethodPackage> toAddCheckPkgs) {
		EObject container = pkg.eContainer();
		if (! (container instanceof MethodPackage)) {
			return false;
		}
		MethodPackage parentPkg = (MethodPackage) container;		
		if (selectedPkgs.contains(parentPkg)) {
			return true;
		}
		if (! toAddCheckPkgs.contains(parentPkg)) {
			return false;
		}
		if (handkedSet.add(parentPkg)) {
			if (checkParentPackage(handkedSet, config, selectedPkgs, parentPkg, toAddCheckPkgs)) {
				addToConfig(config, selectedPkgs, parentPkg);
			}		
		}
		return false;
	}
	
	private void collectLoadCheckPkgs(List<MethodPackage> pkgList, Set<MethodPackage> loadCheckPkgs) {
		PropUtil propUtil = PropUtil.getPropUtil();
		for (MethodPackage pkg : pkgList) {
			Boolean b = propUtil.getBooleanValue(pkg, PropUtil.Pkg_loadCheck);
			if (b != null && b.booleanValue()) {
				loadCheckPkgs.add(pkg);
			}
			collectLoadCheckPkgs(pkg.getChildPackages(), loadCheckPkgs);
		}
		
	}
	
	public boolean isGenerateHtmlMode() {
		return generateHtmlMode;
	}

	public void setGenerateHtmlMode(boolean generateHtmlMode) {
		this.generateHtmlMode = generateHtmlMode;
	}
	
	public boolean browseOrPublishMode() {
		return isPublishingMode() || isGenerateHtmlMode();
	}
	
}
