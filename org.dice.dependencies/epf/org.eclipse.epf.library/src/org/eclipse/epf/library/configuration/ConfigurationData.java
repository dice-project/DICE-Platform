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
package org.eclipse.epf.library.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.library.ConfigHelperDelegate;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.configuration.closure.ConfigurationClosure;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.navigator.ConfigContentPackageItemProvider;
import org.eclipse.epf.library.edit.realization.IRealizationManager;
import org.eclipse.epf.library.edit.util.DebugUtil;
import org.eclipse.epf.library.edit.util.MethodConfigurationPropUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.events.ILibraryChangeListener;
import org.eclipse.epf.library.layout.BrowsingLayoutSettings;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 *  Class managing configuration add/subtracted data calculation and cache
 * 
 * @author Weiping Lu - Mar 20, 2007
 * @author Jinhua Xi
 * @since 1.2
 */
public class ConfigurationData {

	//Flag to toggle off supporting plugin feature
	public static boolean ignoreSupportingPlugin = false;
	
	private static boolean localDebug = false;
	private static boolean profiling = false;
	
	private MethodConfiguration config;
	
	public MethodConfiguration getConfig() {
		return config;
	}

	// make sure the map is created.
	private Map<String, MethodElement> substractedElemMap = new HashMap<String, MethodElement>();
	private Map<String, MethodElement> addedElemMap = new HashMap<String, MethodElement>();

	private boolean needUpdateChanges = true;
	private Map<String, ContentCategory> originalSubstracted;
	private Map<String, ContentCategory> orignalAdded;
	private ILibraryManager libraryManager; 
	private Adapter configListener;
	private ILibraryChangeListener libListener;
	private boolean enableUpdate = true;
	private SupportingElementData supportingElementData;
	
	private Set<MethodPackage> elementsUnslectedPkgs;
	
	public static ConfigurationData newConfigurationData(MethodConfiguration config) {
		Object obj = ExtensionHelper.create(ConfigurationData.class, config);
		if (obj instanceof ConfigurationData) {
			return (ConfigurationData) obj;
		}		
		return new ConfigurationData(config);
	}
	
	public ConfigurationData(MethodConfiguration config) {
		this.config = config;
		elementsUnslectedPkgs = MethodConfigurationPropUtil.getMethodConfigurationPropUtil().getElementsUnslectedPkgs(config);
		
		configListener = new AdapterImpl() {
			public void notifyChanged(Notification msg) {
				if (isNeedUpdateChanges()) {
					return;
				}
				int type = msg.getEventType();
				if (		type == Notification.ADD
						|| 	type == Notification.ADD_MANY
						|| 	type == Notification.REMOVE
						||  type == Notification.REMOVE_MANY) {					
					setNeedUpdateChanges(true);
				}
			}
		};
		config.eAdapters().add(configListener);
				
		libListener = new ILibraryChangeListener() {
			public void libraryChanged(int option, Collection changedItems) {
				if (localDebug) {
					//System.out.println("LD> libraryChanged, option: " + option + ", " + changedItems);//$NON-NLS-1$//$NON-NLS-2$
				}				
				handleLibraryChange(option, changedItems);
			}
		};
		
		MethodLibrary library = LibraryServiceUtil.getMethodLibrary(config);
		libraryManager = LibraryService.getInstance().getLibraryManager(library);
		if(libraryManager != null) {
			libraryManager.addListener(libListener);
		}
	}
	
	protected void handleLibraryChange(int option, Collection changedItems) {
		if (isNeedUpdateChanges()) {
			return;
		}
		for (Iterator it = changedItems.iterator(); it.hasNext();) {
			Object item = it.next();
			if (item instanceof ContentCategory) {
				setNeedUpdateChanges(true);
				return;
			}
		}
	}
	
	protected boolean getUpdatingChanges() {
		return originalSubstracted != null && orignalAdded != null;
	}
	
	private void updateChanges() {
		if (! isEnableUpdate()) {
			return;
		}		
		if (! isNeedUpdateChanges()) {
			return;
		}
		if (getUpdatingChanges()) {
			return;			
		}
		
		long t = 0;
		if (profiling) {
			System.out.println("LD> updateChanges_() -> ");	//$NON-NLS-1$
			t = System.currentTimeMillis();
		}
		updateChanges_();
		if (profiling) {
			t =  System.currentTimeMillis() - t;
			System.out.println("LD> updateChanges_() <- time: " + t);	//$NON-NLS-1$
			System.out.println("");										//$NON-NLS-1$
		}
		
	}
	
	private void updateChanges_() {
		ConfigurationHelper.getDelegate().fixupLoadCheckPackages(getConfig());		
		substractedElemMap.clear();
		addedElemMap.clear();
		
		List subList = config.getSubtractedCategory();
		List addList = config.getAddedCategory();
		if (subList.isEmpty() && addList.isEmpty()) {
			if (localDebug) {
				System.out.println("LD> subList/addList are empty.");	//$NON-NLS-1$
			}
			return;
		}
		
		originalSubstracted = convertToMap(subList);
		orignalAdded = convertToMap(addList);
		
		if (localDebug) {
			DebugUtil.print("originalSubstracted: ", null, originalSubstracted.values(), 2);	//$NON-NLS-1$
			DebugUtil.print("orignalAdded: ", null, orignalAdded.values(), 2);					//$NON-NLS-1$
		}
		
		boolean changed = remove(originalSubstracted, orignalAdded);
		if (changed && localDebug) {
			DebugUtil.print("orignalAdded after remove: ", null, orignalAdded.values(), 2);		//$NON-NLS-1$
		}
		
		Map<String, ContentCategory> calSubstracted = (Map<String, ContentCategory>) 
							((HashMap<String, ContentCategory>)originalSubstracted).clone();
		Map<String, ContentCategory> calAdded = (Map<String, ContentCategory>) 
							((HashMap<String, ContentCategory>)orignalAdded).clone();
					
		changed = handleContributors(calSubstracted, true);
		if (changed && localDebug) {
			DebugUtil.print("calSubstracted after handleContributors: ", null, calSubstracted.values(), 2);//$NON-NLS-1$
		}		
		changed = handleContributors(calAdded, false);
		if (changed && localDebug) {
			DebugUtil.print("calAdded after handleContributors: ", null, calAdded.values(), 2);//$NON-NLS-1$
		}
				
		calSubstracted = handleReplacers(calSubstracted, true);
		calAdded = handleReplacers(calAdded, false);
		
		if (localDebug) {
			DebugUtil.print("calSubstracted: ", null, calSubstracted.values(), 2);//$NON-NLS-1$
			DebugUtil.print("calAdded: ", null, calAdded.values(), 2);//$NON-NLS-1$
		}
		
		changed = remove(calSubstracted, calAdded);
		if (changed && localDebug) {
			DebugUtil.print("calAdded after remove: ", null, orignalAdded.values(), 2);//$NON-NLS-1$
		}				
		
		if (calSubstracted != null) {
			updateElementMap(calSubstracted.values(), substractedElemMap);
		}
		if (calAdded != null) {
			updateElementMap(calAdded.values(), addedElemMap);
		}
		
		if (localDebug) {
			DebugUtil.print("substractedElemMap: ", null, substractedElemMap.values(), 2);	//$NON-NLS-1$
			DebugUtil.print("addedElemMap: ", null, addedElemMap.values(), 2);					//$NON-NLS-1$
		}
		
		setNeedUpdateChanges(false);		
		
		originalSubstracted = null;		//not needed after update
		orignalAdded = null;			//not needed after update
	}
	
	//return true if something gets removed, otherwise false
	private boolean remove(Map<String, ContentCategory> sourceMap, Map<String, ContentCategory> targetMap) {
		if (sourceMap == null || sourceMap.isEmpty() ||
			targetMap == null || targetMap.isEmpty()) {
			return false;
		}
		
		boolean ret = false;				
		for (Iterator<String> it = sourceMap.keySet().iterator(); it.hasNext();) {
			ContentCategory cc = targetMap.remove(it.next());
			if (cc != null) {
				ret = true;
			}
		}
		return ret;
	}
		
	//return true if any change
	private boolean handleContributors(Map<String, ContentCategory> map,  boolean substracted) {
//		if (map == null || ! substracted) {
		if (map == null) {
			return false;
		}
		List<ContentCategory> addedList = new ArrayList<ContentCategory>();
				
		for (Iterator<ContentCategory> it = map.values().iterator(); it.hasNext();) {
			ContentCategory cc = it.next();
			List<ContentCategory> contributers = ConfigurationHelper.getContributors(cc, config);
			addedList.addAll(contributers);
		}
		for (Iterator<ContentCategory> it = addedList.iterator(); it.hasNext();) {
			ContentCategory cc = it.next();
			map.put(cc.getGuid(), cc);
		}
		
		return ! addedList.isEmpty();
	}
	
	private Map<String, ContentCategory> handleReplacers(Map<String, ContentCategory> originals, boolean substracted) {
		if (originals == null || originals.isEmpty()) {
			return originals;
		}
		
		Map<String, ContentCategory> ret = new HashMap<String, ContentCategory>();
		
		for (Iterator<ContentCategory> it = originals.values().iterator(); it.hasNext();) {
			ContentCategory cc = it.next();
			VariabilityElement replacer = ConfigurationHelper.getReplacer(cc, config);
			boolean toKeep = true;
			if (replacer != null) {
				toKeep = false;
				
				VariabilityElement nestedReplacer = replacer;
				while (nestedReplacer != null) {
					replacer = nestedReplacer;
					nestedReplacer = ConfigurationHelper.getReplacer(nestedReplacer, config);
				}
				if (substracted) {
					if (originalSubstracted.containsKey(replacer.getGuid())) {
						toKeep = true;
					}
				}
			}
			if (toKeep) {
				ret.put(cc.getGuid(), cc);
			}			

		}
		return ret;
	}			
	
	private Map<String, ContentCategory> convertToMap(Collection<ContentCategory> collection) {
		if (collection == null || collection.isEmpty()) {
			return new HashMap<String, ContentCategory>();
		}
		Map<String, ContentCategory> map = new LinkedHashMap<String, ContentCategory>();		
		fillMap(collection, map);
		return map;
	}
	
	private void fillMap(Collection<ContentCategory> collection, Map<String, ContentCategory> map) {
		for (Iterator<ContentCategory> it = collection.iterator(); it.hasNext();) {
			ContentCategory cc = it.next();
			if (! map.containsKey(cc.getGuid())) {
				map.put(cc.getGuid(), cc);
			}
		}
	}	
	
	private Collection<? extends ContentCategory> getChildCC(ContentCategory cc) {
		if (cc instanceof CustomCategory) {
			return ((CustomCategory) cc).getSubCategories();
		}
		if (cc instanceof Discipline) {
			return ((Discipline) cc).getSubdiscipline();
		}
		if (cc instanceof DisciplineGrouping) {
			return ((DisciplineGrouping) cc).getDisciplines();
		}
		if (cc instanceof Domain) {
			return ((Domain) cc).getSubdomains();
		}
		if (cc instanceof RoleSet) {
		}
		if (cc instanceof RoleSetGrouping) {
			return ((RoleSetGrouping) cc).getRoleSets();
		}
		if (cc instanceof WorkProductType) {
		}
		return null;
	}	
	
	private void updateElementMap(	Collection<ContentCategory> ccList,
									Map<String, MethodElement> elementMap) {
		if (ccList == null || ccList.isEmpty()) {
			return;
		}
		
		Set<String> seens = new HashSet<String>();
		for (Iterator<ContentCategory> it = ccList.iterator(); it.hasNext();) {
			ContentCategory cc = it.next();
			if (! seens.contains(cc.getGuid())) {
				addToElementMap(cc, elementMap);
				//elementMap.put(cc.getGuid(), cc);
			}
			seens.add(cc.getGuid());
		}
	}			

	//No duplicates in the returned elements
	private Collection<MethodElement> getElements(ContentCategory cc) {
		Collection<MethodElement> elements = new ArrayList<MethodElement>();
		elements.addAll(cc.getAssets());
		elements.addAll(cc.getChecklists());
		elements.addAll(cc.getConceptsAndPapers());
		elements.addAll(cc.getExamples());
		elements.addAll(cc.getGuidelines());
		elements.addAll(cc.getSupportingMaterials());		
		
		if (cc instanceof CustomCategory) {
			elements.addAll(((CustomCategory) cc).getSubCategories());
			elements.addAll(((CustomCategory) cc).getCategorizedElements());
		}
		else if (cc instanceof Discipline) {
			elements.addAll(((Discipline) cc).getReferenceWorkflows());
			elements.addAll(((Discipline) cc).getSubdiscipline());
			elements.addAll(((Discipline) cc).getTasks());
		}
		else if (cc instanceof DisciplineGrouping) {
			elements.addAll(((DisciplineGrouping) cc).getDisciplines());
		}
		else if (cc instanceof Domain) {
			elements.addAll(((Domain) cc).getSubdomains());
			elements.addAll(((Domain) cc).getWorkProducts());
		}
		else if (cc instanceof RoleSet) {
			elements.addAll(((RoleSet) cc).getRoles());
		}
		else if (cc instanceof RoleSetGrouping) {
			elements.addAll(((RoleSetGrouping) cc).getRoleSets());
		}
		else if (cc instanceof Tool) {
			elements.addAll(((Tool) cc).getToolMentors());
		}
		else if (cc instanceof WorkProductType) {
			elements.addAll(((WorkProductType) cc).getWorkProducts());
		}

		return getUniqueCollection(elements);
	}
	
	private Collection<MethodElement> getUniqueCollection(Collection<MethodElement> elements) {
		Collection<MethodElement> ret = new LinkedHashSet<MethodElement>();
		for (Iterator<MethodElement> it = elements.iterator(); it.hasNext();) {
			MethodElement element = it.next();
			if (! ret.contains(element)) {
				ret.add(element);
			}
		}
		return ret;
	}	
	
	public boolean isOwnerSelected(MethodElement element, boolean checkSubtracted) {			
		boolean ret = isOwnerSelected_(element, checkSubtracted);
		if (BrowsingLayoutSettings.INSTANCE.isExcludeUnusedWPDs() && ret && element instanceof WorkProductDescriptor) {
			ConfigHelperDelegate delegate = ConfigurationHelper.getDelegate();
			IRealizationManager mgr = delegate.getRealizationManager(getConfig());
			//mgr == null => not auto synchronized -> skip this check
			if (mgr != null && delegate.browseOrPublishMode()) {
				if (PropUtil.getPropUtil().isExcludedFromPublish(element)) {
					ret = false;
				}
			}
		}
		if (localDebug) {
			System.out.println("LD> isOwnerSelected: " + ret + ", " +  //$NON-NLS-1$ //$NON-NLS-2$
					DebugUtil.toString(element, 2));	
		}				
		return ret;
	}
	
	protected boolean isOwnerSelected_(MethodElement element, boolean checkSubtracted) {
		if (element == null) {
			return false;
		}

		if (ConfigurationHelper.isDescriptionElement(element)) {
			return true;
		}
		
		ConfigurationClosure.ProcessNodeLock lock = ConfigurationClosure.processNodeLock;
		//Synchronized check only on thread different from the locking thread 
		if (lock.getLockingThread() != null && lock.getLockingThread() != Thread.currentThread()) {
			synchronized (lock) {
				Thread tread = lock.getLockingThread();
				if (true) {
					System.out.println("LD> updated locking thread: " + tread);//$NON-NLS-1$ 
				}
			}
		}	
		
		if (getUpdatingChanges()) {
			if (originalSubstracted.containsKey(element.getGuid()) || 
				orignalAdded.containsKey(element.getGuid())) {
				return true;
			}			
		} else {
			updateChanges();
						
			//182831
/*			if (active && element instanceof ContentCategory) {
				MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
				if (config.getMethodPluginSelection().contains(plugin)) {
					return true;
				}
			}*/
			
			// since UMA 1.0.4, configuration can have added categories 
			// and subtracted categories. The order of filtering is:
			// 1. any element in the subtracted categories should be excluded
			// 2. any element in the added categories should be included
			// 3. any element not in the selected package or plugin should be excluded.		
			if ( checkSubtracted) {
				if (isElementSubtracted(element)) {
					return false;
				} else if (element instanceof VariabilityElement){
					if (contributedBaseInSubstracted((VariabilityElement) element)) {
						return false;
					}
				}
			}
			
			if (isElementAdded(element)) {
				if (element instanceof Activity) {
					VariabilityElement base = ((Activity) element).getVariabilityBasedOnElement();
					if (base != null
							&& base != element
							&& !ConfigurationHelper.inConfig(base, config,
									checkSubtracted)) {
						return false;
					}
				}
				return true;
			}
			
/*			if (! ignoreSupportingPlugin) {
				MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
				if (plugin != null && plugin.isSupporting()) {
					SupportingElementData seData = getSupportingElementData();
					if (seData != null && seData.isEnabled()) {
						if (seData.isUpdatingChanges()) {
							return false;
						}
						return seData.isSupportingElement(element);
					}
				}
			}*/
			
			boolean isLibOrPluginOrConfig = element instanceof MethodLibrary ||
			element instanceof MethodConfiguration || element instanceof MethodPlugin;
			
			SupportingElementData seData = getSupportingElementData();
			if (!isLibOrPluginOrConfig && seData != null && !seData.bypassLogic()) {
				if (seData.isNeedUpdateChanges()) {
					seData.updateChanges();
				}
				if (seData.isEnabled()) {
					MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
					if (plugin != null && plugin.isSupporting() || seData.inSupportingPackage(element)) {
						if (! config.getMethodPluginSelection().contains(plugin)) {
							return false;
						}
						int ix = seData.checkInConfigIndex(element);
						//ix: 0 = unknown, 1 = yes, 2 = no
						if (ix == 1 || ix == 2) {
							return ix == 1;
						}
					}
				}
			}				
		} 
		
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
				EObject pkg_old = pkg;
				while ((pkg != null) && !(pkg instanceof ProcessComponent)
						&& !pkgs.contains(pkg)) {
					pkg = pkg.eContainer();
				}
				
				// if no ProcessComponent, this is a real package, use it
				if ( pkg == null ) {
					pkg = pkg_old;
				}
			}

			// if package not selected, return false
			if ((pkg == null) || !pkgs.contains(pkg) || (pkg instanceof MethodPackage && elementsUnslected((MethodPackage) pkg))) {
				return false;
			}

			return true;
		}
	}

	private boolean contributedBaseInSubstracted(VariabilityElement ve) {
		if (ve.getVariabilityType() != VariabilityType.CONTRIBUTES) {
			return false;
		}
		
		VariabilityElement base = ve.getVariabilityBasedOnElement();
		if (base == null) {
			return false;
		}
		
		if (substractedElemMap.containsKey(base.getGuid())) {
			return true;
		}
		
		if (ConfigurationHelper.inConfig(base, config, true)) {
			return contributedBaseInSubstracted(base);
		}

		return false;
	}	
	
	private void addToElementMap(ContentCategory cc, Map<String, MethodElement> elementMap) {
		Collection<MethodElement> elementList = getElements(cc);		
		for (Iterator<MethodElement> it = elementList.iterator(); it.hasNext();) {
			MethodElement element = it.next();
			elementMap.put(element.getGuid(), element);
		}
	}
	
	public void dispose() {
		if (elementsUnslectedPkgs != null) {
			elementsUnslectedPkgs.clear();
			elementsUnslectedPkgs = null;
		}
		config.eAdapters().remove(configListener);
		if (libraryManager != null) {
			libraryManager.removeListener(libListener);
		}
	}

	protected boolean isNeedUpdateChanges() {
		return needUpdateChanges;
	}

	public void setNeedUpdateChanges(boolean needUpdateChanges) {
		this.needUpdateChanges = needUpdateChanges;
		if (localDebug) {
			System.out.println("LD> setNeedUpdateChanges: " + needUpdateChanges);	//$NON-NLS-1$
		}
	}

	private boolean isEnableUpdate() {
		return enableUpdate;
	}

	public void setEnableUpdate(boolean enableUpdate) {
		this.enableUpdate = enableUpdate;
		if (localDebug) {
			System.out.println("LD> setEnableUpdate: " + enableUpdate);	//$NON-NLS-1$
		}
	}
	
	public Collection<MethodElement> getAddedElements() {
		return addedElemMap.values();
	}
	
	public Collection<MethodElement> getSubtractedElements() {
		return substractedElemMap.values();
	}
	
	public boolean isElementSubtracted(MethodElement element) {
		return substractedElemMap.containsKey(element.getGuid());
	}
	
	public boolean isElementAdded(MethodElement element) {
		return addedElemMap.containsKey(element.getGuid());
	}

	private SupportingElementData getSupportingElementData() {
		if (supportingElementData == null) {
			supportingElementData = LibraryService.getInstance()
			.getConfigurationManager(config).getSupportingElementData();
		}
		return supportingElementData;
	}
	
	
	public boolean isSubstracted(MethodElement element) {
		if (getUpdatingChanges()) {
			throw new UnsupportedOperationException();
		}
		updateChanges();

		if (isElementSubtracted(element)) {
			return true;
		} else if (element instanceof VariabilityElement) {
			if (contributedBaseInSubstracted((VariabilityElement) element)) {
				return true;
			}
		}
		return false;
	}
	
	//Handle leaf elements node APIs
	public boolean elementsUnslected(MethodPackage pkg) {
		if (ConfigContentPackageItemProvider.oldCode) {
			return false;
		}
		return elementsUnslectedPkgs == null ? false : elementsUnslectedPkgs.contains(pkg);
	}
	
	protected boolean hasAddedElements() {
		return addedElemMap != null && !addedElemMap.isEmpty();
	}
	
	protected boolean hasSubstractedElements() {
		return substractedElemMap != null && !substractedElemMap.isEmpty();
	}
	
	public boolean hasAddedElements(MethodPackage pkg) {
		if (! hasAddedElements()) {
			return false;
		}
		if ( !(pkg instanceof ContentPackage)) {
			return false;
		}
		for (ContentElement element : ((ContentPackage) pkg).getContentElements()) {
			if (isElementAdded(element)) {
				return true;
			}
		}		
		return false;
	}
	
	public boolean hasSubstractedElements(MethodPackage pkg) {
		if (! hasSubstractedElements()) {
			return false;
		}
		if ( !(pkg instanceof ContentPackage)) {
			return false;
		}
		for (ContentElement element : ((ContentPackage) pkg).getContentElements()) {
			if (isElementSubtracted(element)) {
				return true;
			}
		}		
		return false;
	}
	
	private boolean elementsUnslectedPkgsModified(Set<MethodPackage> updatedElementsUnslectedPkgs) {
		boolean updatedIsEmpty = updatedElementsUnslectedPkgs == null || updatedElementsUnslectedPkgs.isEmpty();
		if (elementsUnslectedPkgs == null || elementsUnslectedPkgs.isEmpty()) {
			return ! updatedIsEmpty;
		}
		if (updatedIsEmpty) {
			return true;
		}
		if (elementsUnslectedPkgs.size() != updatedElementsUnslectedPkgs.size()) {
			return true;
		}
		for (MethodPackage pkg : updatedElementsUnslectedPkgs) {
			if (! (elementsUnslected(pkg))) {
				return true;
			}
		}		
		return false;
	}
	
	protected boolean storeElementsUnslectedPkgsProp(IActionManager actionManager, Set<MethodPackage> updatedElementsUnslectedPkgs) {
		if (ConfigContentPackageItemProvider.oldCode) {
			return false;
		}
		if (! elementsUnslectedPkgsModified(updatedElementsUnslectedPkgs)) {
			return false;
		}
		elementsUnslectedPkgs = updatedElementsUnslectedPkgs;
		MethodConfigurationPropUtil propUtil = MethodConfigurationPropUtil.getMethodConfigurationPropUtil(actionManager);
		propUtil.setElementsUnslectedPkgsProp(config, elementsUnslectedPkgs);
		return true;
	}
	
	public String getSelectionInfo(Object selectedElement) {
		if (selectedElement instanceof MethodElement) {
			return ((MethodElement) selectedElement).getBriefDescription();
		}
		
		return "";	//$NON-NLS-1
	}
	
	public String debugSelString() {
		return "";		//$NON-NLS-1
	}
	
	public SupportingElementData newSupportingElementData() {
		return new SupportingElementData(getConfig());
	}
	
	public void setBeingEdit(boolean b) {
		setBeingEdit(b, false);
	}
	
	public void setBeingEdit(boolean b, boolean editorDirty) {		
	}
	
	public void updatePackageSelections(IActionManager actionManager, 
			Set<MethodPackage> elementsUnslectedPkgs,
			Collection<MethodPackage> toggleToUncheckPkgs,
			Collection<MethodPackage> toggleToCheckPkgs) {
		storeElementsUnslectedPkgsProp(actionManager, elementsUnslectedPkgs);
	}
	
	public boolean isSuppressed(MethodElement element) {
		return false;
	}

	
}
