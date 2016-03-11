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
package org.eclipse.epf.library.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.configuration.ConfigurationFilter;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.DefaultElementRealizer;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.TransientGroupItemProvider;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.configuration.GuidanceGroupingItemProvider;
import org.eclipse.epf.library.edit.configuration.GuidanceItemProvider;
import org.eclipse.epf.library.edit.configuration.MethodConfigurationItemProvider;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.DescriptorPropUtil;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.edit.util.MethodElementPropertyHelper;
import org.eclipse.epf.library.edit.util.MethodLibraryPropUtil;
import org.eclipse.epf.library.edit.util.MethodPluginPropUtil;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.persistence.PersistenceService;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.MultiFileXMISaveImpl;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.MethodUnit;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.EProperty;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.util.ExtendedAttribute;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.IMeVisitor;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.eclipse.epf.uma.util.QualifiedReference;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class LibraryUtil {

	public static boolean PUBLISH_MODE = false;
	
	public static Comparator<EClass> typeComparator = new Comparator<EClass>() {

		public int compare(EClass o1, EClass o2) {
			return o1.getName().compareTo(o2.getName());
		}
		
	};

	private static List<EClass> includedElementTypes;
	
	private static final Collection<EClass> excludedTypes = Arrays.asList(new EClass[] {
			UmaPackage.eINSTANCE.getBreakdownElement(),
			UmaPackage.eINSTANCE.getCompositeRole(),
			UmaPackage.eINSTANCE.getDescribableElement(),
			UmaPackage.eINSTANCE.getDescriptor(),
			UmaPackage.eINSTANCE.getFulfillableElement(),
			UmaPackage.eINSTANCE.getKind(),
			UmaPackage.eINSTANCE.getProcessComponentDescriptor(),
			UmaPackage.eINSTANCE.getProcessComponentInterface(),
			UmaPackage.eINSTANCE.getProcessElement(),
			UmaPackage.eINSTANCE.getProcessPlanningTemplate(),
			UmaPackage.eINSTANCE.getRoleDescriptor(),
			UmaPackage.eINSTANCE.getTaskDescriptor(),
			UmaPackage.eINSTANCE.getTeamProfile(),
			UmaPackage.eINSTANCE.getWorkBreakdownElement(),
			UmaPackage.eINSTANCE.getWorkOrder(),
			UmaPackage.eINSTANCE.getWorkProductDescriptor()
	});
	
	/**
	 * Check is given plugin name is valid name in the library
	 * 
	 * @param name
	 * @return String
	 */
	public static String checkPluginName(MethodPlugin plugin, String newName) {
		MethodLibrary lib = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		return IValidatorFactory.INSTANCE.createValidator(lib,
				UmaPackage.Literals.METHOD_LIBRARY__METHOD_PLUGINS,
				(IFilter) null, null, UmaPackage.Literals.NAMED_ELEMENT__NAME)
				.isValid(newName);		
	}

	/**
	 * method to check if the element is a plugin or package so that is can be selected in configuration editor. 
	 * May need a better name for this.
	 * @param element EObject
	 * @return boolean
	 */
	public static boolean selectable(EObject element) {
		return (element instanceof MethodLibrary
				|| element instanceof MethodPlugin || element instanceof MethodPackage);
	}

	/**
	 * get the container of the element that is selectable.
	 * @param element EObject
	 * @return EObject
	 */
	public static EObject getSelectable(EObject element) {
		if (element instanceof BreakdownElement) {
			EObject pkg = element.eContainer();
			while (pkg != null && !(pkg instanceof ProcessComponent) ) {
				pkg = pkg.eContainer();
			}
			return pkg;
			
		} else {
			EObject parent = element;
			while ((parent != null) && !selectable(parent)) {
				parent = parent.eContainer();
			}

			return parent;
		}
	}

	/**
	 * get the method plugin for the element
	 * 
	 * @param element EObject
	 * @return MethodPlugin
	 */
	public static MethodPlugin getMethodPlugin(EObject element) {
		// EObject parent = element;
		// while ((parent != null) && !(parent instanceof MethodPlugin)) {
		// parent = parent.eContainer();
		// }
		//
		// return (MethodPlugin) parent;

		return UmaUtil.getMethodPlugin(element);
	}

	/**
	 * get a printable name string for the element. Note, this is not the element name attribute
	 * @param element Object
	 * @return String
	 */
	public static String getName(Object element) {
		if (element == null)
			return LibraryResources.unknown_text; 

		if (element instanceof MethodElement) {
			return getFullName((MethodElement) element);
		}

		return element.toString();
	}

	/**
	 * get a printable name for the element.
	 * @param element MethodElement
	 * @return String
	 */
	public static String getFullName(MethodElement element) {
		if (selectable(element)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("[").append(element.getName()).append("]"); //$NON-NLS-1$ //$NON-NLS-2$
			MethodElement parent = element;
			while ((parent = (MethodElement) parent.eContainer()) != null) {
				if (parent instanceof MethodLibrary) {
					break;
				}
				buffer.insert(0, "[" + parent.getName() + "]."); //$NON-NLS-1$ //$NON-NLS-2$
			}
			return buffer.toString();
		} else {
			return element.getName();
		}
	}

	/**
	 * get the element's type:name as a localized string
	 * @param element MethodElement
	 * @return String
	 */
	public static String getLocalizeTypeName(MethodElement element) {
		if (element == null) {
			return ""; //$NON-NLS-1$
		}
		if (element instanceof DescribableElement) {
			String nameStr = TngUtil.getTypeText(element) + LibraryResources.colon_with_space ;
			
			if (((DescribableElement) element).getPresentationName() != null) {
				nameStr += "(" + ((DescribableElement) element).getPresentationName() //$NON-NLS-1$
						+ ") " + ((DescribableElement) element).getName(); //$NON-NLS-1$
			} else {
				nameStr += element.getName();
			}
			return nameStr ;
		} else {
			return getTypeName(element);
		}
	}
	
	/**
	 * get the element's type:name
	 * 
	 * @param element MethodElement
	 * @return String
	 */
	public static String getTypeName(MethodElement element) {
		return element == null ? "" : element.getType().getName() + ":" + element.getName(); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	/**
	 * get the element's type:name
	 * 
	 * @param element MethodElement
	 * @return String
	 */
	public static String getTypePath(MethodElement element) {
		return element == null ? "" : element.getType().getName() + ":" + TngUtil.getLabelWithPath(element); //$NON-NLS-1$ //$NON-NLS-2$
	}

	
	/**
	 * get the method plugins in the library
	 * @param library MethodLibrary
	 * @return List a list of MethodPlugin objects
	 */
	public static List<MethodPlugin> getMethodPlugins(MethodLibrary library) {
		return new ArrayList<MethodPlugin>(library.getMethodPlugins());
	}

	/**
	 * get a list of guid strings of the method plugins in the library
	 * @param library MethodLibrary
	 * @return List
	 */
	public static List getMethodPluginGuids(MethodLibrary library) {
		List items = new ArrayList();
		List elements = library.getMethodPlugins();
		if (elements != null) {
			for (Iterator it = elements.iterator(); it.hasNext();) {
				MethodPlugin element = (MethodPlugin) it.next();
				items.add(element.getGuid());
			}
		}

		return items;
	}

	/**
	 * get the MethodPlugin in the library with the given plugin guid.
	 * 
	 * @param library MethodLibrary
	 * @param pluginGuid String
	 * @return MethodPlugin
	 */
	public static MethodPlugin getMethodPlugin(MethodLibrary library,
			String pluginGuid) {
		if (pluginGuid == null) {
			return null;
		}

		List elements = library.getMethodPlugins();
		if (elements != null) {
			for (Iterator it = elements.iterator(); it.hasNext();) {
				MethodPlugin element = (MethodPlugin) it.next();
				if (pluginGuid.equals(element.getGuid())) {
					return element;
				}
			}
		}

		return null;
	}
	
	/**
	 * get the MethodPlugin in the library with the given plugin name.
	 * 
	 * @param library MethodLibrary
	 * @param name String
	 * @return MethodPlugin
	 */
	public static MethodPlugin getMethodPluginByName(MethodLibrary library,
			String name) {
		if (name == null) {
			return null;
		}

		List elements = library.getMethodPlugins();
		if (elements != null) {
			for (Iterator it = elements.iterator(); it.hasNext();) {
				MethodPlugin element = (MethodPlugin) it.next();
				if (name.equals(element.getName())) {
					return element;
				}
			}
		}

		return null;
	}


	/**
	 * get all the contained MethodPackages for the element, return empty list if nothing found.
	 * @param element MethodElement
	 * @return List
	 */
	public static List getMethodPackages(MethodElement element) {
		List items = new ArrayList();
		for (Iterator it = element.eAllContents(); it.hasNext();) {
			EObject e = (EObject) it.next();
			if (e instanceof MethodPackage) {
				items.add(e);
			}
		}

		return items;
	}

	/**
	 * upwrap the object
	 * @param obj Object
	 * @return Object
	 */
	public static Object unwrap(Object obj) {
		return TngUtil.unwrap(obj);
	}

	/**
	 * unwrap the command
	 * @param cmd Command
	 * @return Command
	 */
	public static Command unwrap(Command cmd) {
		return TngUtil.unwrap(cmd);
	}

	/**
	 * clear the resource the elements associated to. So that the element can be
	 * added to another library and got new resource assigned.
	 * 
	 * @param importLibraty
	 *            MethodLibrary
	 */
	public static void detachFromResource(MethodLibrary importLibraty) {
		ResourceSet resSet = null;
		Resource res = importLibraty.eResource();
		if (res != null) {
			resSet = res.getResourceSet();
		}

		if (resSet != null) {
			for (TreeIterator it = resSet.getAllContents(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof MultiResourceEObject) {
					((MultiResourceEObject) obj).eSetResource(null);
				}
			}
		}

		// clear all the unresolved proxies
		clearProxies(importLibraty);
	}

	/**
	 * clear proxy for the element. This is used to fix the element when the proxy can't be resolved.
	 * @param element EObject
	 */
	public static void clearProxies(EObject element) {
		if (element.eIsProxy()) {
			// reset the proxy to null
			setProxyURI(element, null);
		} else {
			// iterate the children
			for (TreeIterator it = element.eAllContents(); it.hasNext();) {
				EObject o = (EObject) it.next();
				if (o.eIsProxy()) {
					setProxyURI(o, null);
				}
			}
		}
	}

	/**
	 * set the object's proxy uri
	 * @param obj EObject
	 * @param uri org.eclipse.emf.common.util.URI
	 */
	public static void setProxyURI(EObject obj,
			org.eclipse.emf.common.util.URI uri) {
		((org.eclipse.emf.ecore.InternalEObject) obj).eSetProxyURI(uri);
	}

	public static Collection<Resource> getLoadedResources(MethodLibrary lib, Collection<Resource> excludes) {

		Collection<Resource> loadedres = new HashSet<Resource>();

		for (Iterator<Resource> it = lib.eResource().getResourceSet()
				.getResources().iterator(); it.hasNext(); ) {
			Resource res = (Resource)it.next();
			if ( res.isLoaded() ) {
				if ( (excludes == null) || !excludes.contains(res) ) {
					loadedres.add(res);
				}
			}
		}	
		
		return loadedres;
	}
	
	/**
	 * load all elements in the library
	 * @param lib MethodLibrary
	 */
	public static Collection<Resource> loadAll(MethodLibrary lib) {
		return loadAll(lib, null, false);
	}
	
	public static Collection<Resource> loadAll(MethodLibrary lib, boolean skipContent) {
		return loadAll(lib, null, skipContent);
	}
	
	public static Collection<Resource> loadAll(MethodLibrary lib, MethodConfiguration config) {
		return loadAll(lib, config, true);
	}
	
	public static Collection<Resource> loadAll(MethodLibrary lib, MethodConfiguration config, boolean skipContent) {
		if (lib == null) {
			lib = LibraryService.getInstance().getCurrentMethodLibrary();
		}

		Collection<Resource> loadedres = getLoadedResources(lib, null);
		MeVisitor meVisitor = new MeVisitor();
		
		if (config == null) {
			if (skipContent) {
				loadAllSkipContents(lib, meVisitor);
				
			} else {
				loadAllContained(lib);
			}
		} else {
			loadAllPlugins(config, meVisitor);
		}
		meVisitor.processElements();
		
		return getLoadedResources(lib, loadedres);
	}

	public static void loadAllContained(MethodElement me) {
		for (Iterator<EObject> iter = me.eAllContents(); iter.hasNext();) {
			try {
				EObject element = (EObject) iter.next();
				for (Iterator<EObject> iterator = element.eCrossReferences().iterator(); iterator
						.hasNext();) {
					iterator.next();
				}
			} catch (Exception e) {
				LibraryPlugin.getDefault().getLogger().logError(e);
			}
		}
	}

	public static void loadAllSkipContents(MethodLibrary lib) {
		MeVisitor meVisitor = new MeVisitor();
		loadAllSkipContents(lib, meVisitor);
		meVisitor.processElements();
	}
	
	public static void loadAllSkipContents(MethodLibrary lib, IMeVisitor vistor) {
		List<MethodPlugin> pluigns = lib.getMethodPlugins();
		Set<MethodElement> processed = new HashSet<MethodElement>();
		for (MethodPlugin plugin: pluigns) {
			loadImmidiateChildren(plugin, true, processed, vistor);
		}
	}
	
	public static void loadAllPlugins(MethodConfiguration config) {
		MeVisitor meVisitor = new MeVisitor();
		loadAllPlugins(config, meVisitor);
		meVisitor.processElements();
	}
	
	public static void loadAllPlugins(MethodConfiguration config, IMeVisitor vistor) {
		boolean skipContent = true;
		List<MethodPlugin> pluigns = config.getMethodPluginSelection();
		loadPlugins(vistor, skipContent, pluigns);
	}
	
	private static void loadPlugins(IMeVisitor vistor, boolean skipContent,
			Collection<MethodPlugin> pluigns) {
		Set<MethodElement> processed = new HashSet<MethodElement>();
		for (MethodPlugin plugin: pluigns) {
			if (skipContent) {
				loadImmidiateChildren(plugin, true, processed, vistor);
			} else {
				loadAllContained(plugin);
			}
		}
	}
	
	public static void loadPlugins(Collection<MethodPlugin> pluigns) {
		MeVisitor meVisitor = new MeVisitor();
		loadPlugins(meVisitor, true, pluigns);
		meVisitor.processElements();
	}
	
	private static void loadImmidiateChildren(MethodElement me,
			boolean skipContent, Set<MethodElement> processed, IMeVisitor vistor) {
		if (processed.contains(me)) {
			return;
		}
		if (vistor != null) {
			vistor.visit(me);
		}
		processed.add(me);
		EList<EReference> refList = me.eClass().getEAllReferences();
		if (refList == null || refList.isEmpty()) {
			return;
		}
		for (EReference ref : refList) {
			if (skipContent && isContentRef(ref)) {
				continue;
			}
			Object obj = me.eGet(ref);
			if (obj instanceof MethodElement) {
				loadImmidiateChildren((MethodElement) obj, skipContent, processed, vistor);
				
			} else if (obj instanceof List) {
				List list = (List) obj;
				for (Object itemObj : list) {
					if (itemObj instanceof MethodElement) {
						loadImmidiateChildren((MethodElement) itemObj, skipContent, processed, vistor);
					}
				}
			}
		}
	}
	
	public static boolean isContentRef(EReference ref) {
		if (ref == UmaPackage.eINSTANCE.getDescribableElement_Presentation()) {
			return true;
		}
		if (ref == UmaPackage.eINSTANCE.getTask_Steps()) {
			return true;
		}
		if (ref == UmaPackage.eINSTANCE.getTaskDescriptor_SelectedSteps()) {
			return true;
		}
		return false;
	}
	
	/**
	 * load all processes in the library
	 * @param lib MethodLibrary
	 */
	public static void loadAllProcesses(MethodLibrary lib) {

		for (Iterator iter = lib.getMethodPlugins().iterator(); iter.hasNext();) {
			try {
				MethodPlugin plugin = (MethodPlugin) iter.next();
				TngUtil.getAllProcesses(plugin);
			} catch (Exception e) {
				LibraryPlugin.getDefault().log(e);
			}
		}
	}
	
	/**
	 * save all elements in the library not matter the element is changed or not. 
	 * Don't refresh the workspace.
	 * @param lib MethodLibrary
	 * @throws Exception
	 */
	public static void saveAll(MethodLibrary lib) throws Exception {
		saveLibrary(lib, true, false);
	}

	/**
	 * save the specified method library based on the library resourceset. You
	 * need to set the resource set before calling this method to save the
	 * library
	 * 
	 * @param lib
	 *            MethodLibrary
	 * @param saveAll
	 *            boolean if true, force saving all the resources even if they
	 *            are not modified.
	 */
	public static void saveLibrary(MethodLibrary lib, boolean saveAll,
			boolean refresh) throws Exception {
		ILibraryResourceSet libResourceSet = (ILibraryResourceSet) lib.eResource().getResourceSet();
		if(libResourceSet instanceof MultiFileResourceSetImpl) {
			MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) libResourceSet;

			ILibraryManager manager = LibraryService.getInstance()
			.getCurrentLibraryManager();
			Map saveOptions = manager != null ? manager.getSaveOptions()
					: new HashMap();

			// back up current REFRESH_NEW_RESOURCE option
			Object old = saveOptions.get(MultiFileXMISaveImpl.REFRESH_NEW_RESOURCE);
			Object oldCheckModify = saveOptions
			.get(MultiFileXMISaveImpl.CHECK_MODIFY);
			try {
				// disable workspace refresh when new file is created
				saveOptions.put(MultiFileXMISaveImpl.REFRESH_NEW_RESOURCE,
						refresh ? "true" : "false"); //$NON-NLS-1$ //$NON-NLS-2$
				saveOptions.put(MultiFileXMISaveImpl.CHECK_MODIFY, "false"); //$NON-NLS-1$

				// save resource set here
				resourceSet.save(saveOptions, saveAll);
			} finally {
				// restore REFRESH_NEW_RESOURCE option
				saveOptions.put(MultiFileXMISaveImpl.REFRESH_NEW_RESOURCE, old);
				saveOptions.put(MultiFileXMISaveImpl.CHECK_MODIFY, oldCheckModify);
			}
		}
		else {
			libResourceSet.save(Collections.EMPTY_MAP);
		}
	}

	/**
	 * load the library at the specified path. return the MethodLibrary.
	 * This method is different from the the open library in LibraryService. 
	 * It only load the row library object but will not impact the current EPF environment.
	 * @param libraryPath String
	 * @return MethodLibrary
	 * @throws Exception
	 */
	public static MethodLibrary loadLibrary(String libraryPath)
			throws Exception {
		ILibraryResourceSet resourceSet = PersistenceService.INSTANCE.createResourceSet(Services.XMI_PERSISTENCE_TYPE);
		resourceSet.loadMethodLibraries(URI.createFileURI(libraryPath), Collections.EMPTY_MAP);
		return resourceSet.getFirstMethodLibrary();
	}

	/**
	 * get the root path of the method library 
	 * @param lib MethodLibrary
	 * @return File
	 */
	public static File getLibraryRootPath(MethodLibrary lib) {
		Resource res = lib.eResource();
		if (res == null) {
			return null;
		}

		URI uri = res.getURI();
		String path = uri.toFileString();
		File f = new File(path);
		return f.getParentFile();
	}

	/**
	 * get all packages in the plugin
	 * @param plugin MethodPlugin
	 * @return List
	 */
	public static List getAllPackages(MethodPlugin plugin) {
		List allPkgs = new ArrayList();

		List pkgs = plugin.getMethodPackages();
		allPkgs.addAll(pkgs);

		for (Iterator it = pkgs.iterator(); it.hasNext();) {
			getAllChildPackages((MethodPackage) it.next(), allPkgs);
		}

		return allPkgs;
	}

	/**
	 * get all child packages of the given MethodPackage and add to the list
	 * @param pkg MethodPackage
	 * @param result List the packages found
	 */
	public static void getAllChildPackages(MethodPackage pkg, List result) {
		List pkgs = pkg.getChildPackages();
		result.addAll(pkgs);

		for (Iterator it = pkgs.iterator(); it.hasNext();) {
			getAllChildPackages((MethodPackage) it.next(), result);
		}
	}

	/**
	 * get all configurations referenced by this plugin
	 * 
	 * @param plugin
	 * @return List of MethodConfiguration
	 */
	public static List<MethodConfiguration> getAssociatedConfigurations(MethodPlugin plugin) {
		// get the configs that references this method plugin
		Set<MethodConfiguration> allConfigs = new HashSet<MethodConfiguration>();
/*		List configs = (List) ((MultiResourceEObject) plugin)
				.getOppositeFeatureValue(AssociationHelper.MethodPlugin_MethodConfigurations);
		addUniqueItems(configs, allConfigs);

		// get the configs that references the packages in this plugin
		List pkgs = getAllPackages(plugin);
		for (Iterator it = pkgs.iterator(); it.hasNext();) {
			MultiResourceEObject o = (MultiResourceEObject) it.next();

			configs = (List) o
					.getOppositeFeatureValue(AssociationHelper.MethodPackage_MethodConfigurations);
			addUniqueItems(configs, allConfigs);
		}
*/
		// get the congigurations that referenced by the processes in this
		// plugin
		MethodLibrary lib = UmaUtil.getMethodLibrary(plugin);		
		
		List procs = TngUtil.getAllProcesses(plugin);
		for (Iterator it = procs.iterator(); it.hasNext();) {
			org.eclipse.epf.uma.Process p = (org.eclipse.epf.uma.Process) it
					.next();
			MethodConfiguration c = p.getDefaultContext();
			if ((c != null && !(c instanceof Scope))) {
				allConfigs.add(c);
				allConfigs.addAll(p.getValidContext());
			} else if (lib != null) {
				for (MethodConfiguration config : lib.getPredefinedConfigurations()) {
					if (ConfigurationHelper.inConfig(p, config)) {
						allConfigs.add(config);
					}
				}
			}
//			addUniqueItems(p.getValidContext(), allConfigs);
		}

		return new ArrayList<MethodConfiguration>(allConfigs);
	}

	private static void addUniqueItems(List from, List to) {
		if (from == null || to == null || from.size() == 0) {
			return;
		}

		for (Iterator it = from.iterator(); it.hasNext();) {
			Object o = it.next();
			if (!to.contains(o)) {
				to.add(o);
			}
		}
	}

	/**
	 * validate the configuration by forcing to select the global packages of
	 * the selected method plugins, this is needed for configuration exporting.
	 * If global packages are missing, the exported configuration is not valid
	 * 
	 * @param actionMgr if not null, will use the given IActionManager to change the configuration, otherwise configuration will be changed directly
	 * @param plugin
	 */
	public static void validateMethodConfiguration(IActionManager actionMgr, MethodConfiguration config) {
//		List plugins = config.getMethodPluginSelection();
//		List pkgSels = config.getMethodPackageSelection();
//
//		for (Iterator itp = plugins.iterator(); itp.hasNext();) {
//			MethodPlugin plugin = (MethodPlugin) itp.next();
//			List pkgs = TngUtil.getAllSystemPackages(plugin);
//			for (Iterator it = pkgs.iterator(); it.hasNext();) {
//				Object pkg = it.next();
//				if (!pkgSels.contains(pkg)) {
//					pkgSels.add(pkg);
//				}
//			}
//		}
		
		// moved to TngUtil
		TngUtil.validateMethodConfiguration(actionMgr, config);
	}

	/**
	 * validate the configuration by forcing to select the global packages of
	 * the selected method plugins, this is needed for configuration exporting.
	 * If global packages are missing, the exported configuration is not valid
	 * @param plugin
	 */
	public static void validateMethodConfiguration(MethodConfiguration config) {
		validateMethodConfiguration(null, config);
	}
	
	/**
	 * get the copyright object for an element
	 * @param element MethodElement
	 * @return SupportingMaterial
	 */
	public static SupportingMaterial getCopyright(MethodElement element) {
		SupportingMaterial sm = null;
		if (element instanceof MethodUnit) {
			sm = ((MethodUnit) element).getCopyrightStatement();
		} else if (element instanceof DescribableElement) {
			sm = ((DescribableElement) element).getPresentation()
					.getCopyrightStatement();
		}

		// if no copyright of it's own, get the copyright from the plugin
		if (sm == null) {
			MethodPlugin p = getMethodPlugin(element);
			if (p != null) {
				sm = p.getCopyrightStatement();
			}
		}

		return sm;
	}

	/**
	 * for the given container and elements list, check if the element in the list is contained by the container or not. 
	 * return all the contained elements for a given container
	 * @param container Object
	 * @param elements Collection
	 * @return Collection
	 */
	public static Collection getContainedElements(Object container,
			Collection elements) {
		if (container instanceof TransientGroupItemProvider) {
			container = ((TransientGroupItemProvider) container).getTarget();
		}
		ArrayList contained = new ArrayList();
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof EObject
					&& UmaUtil.isContainedBy((EObject) element, container)) {
				contained.add(element);
			}
		}
		return contained;
	}

	/**
	 * check if two method element are identical or not. Two elements are
	 * identical if and only if: all the attribute values are equal all the
	 * referenced elements are equal all the contained elements are identical
	 * 
	 * @param oldObj
	 *            MethodElement
	 * @param newObj
	 *            MethodElement
	 * @return boolean
	 */
	public static boolean isIdentical(MethodElement oldObj, MethodElement newObj) {

		if ((oldObj == null) && (newObj == null)) {
			return true;
		}

		if ((oldObj == null) || (newObj == null)) {
			return false;
		}

		// this does not work, the toString contains the object instance info
		// return oldObj.toString().equals(newObj.toString());

		List features = getStructuralFeatures(oldObj);
//		List properties = oldObj.getInstanceProperties();
		if (features != null) {
			for (int i = 0; i < features.size(); i++) {
				EStructuralFeature feature = (EStructuralFeature) features
						.get(i);
				Object oldValue = oldObj.eGet(feature);
				Object newValue = newObj.eGet(feature);
				if (oldValue == null && newValue == null) {
					continue;
				}

				if (oldValue == null || newValue == null) {
					return false;
				}

				if (oldValue instanceof MethodElement) {

					// if it'c containment feature value, iterate it
					MethodElement olde = (MethodElement) oldValue;
					if (olde.eContainer() == oldObj) {
						if (!isIdentical(olde, (MethodElement) newValue)) {
							return false;
						}
					} else if (oldValue != newValue) {
						return false;
					}
				} else if (oldValue instanceof List) {
					List oldl = (List) oldValue;
					List newl = (List) newValue;
					if (oldl.size() != newl.size()) {
						return false;
					}

					for (int x = 0; x < oldl.size(); x++) {
						Object o = oldl.get(x);
						Object n = newl.get(x);
						if (o instanceof MethodElement) {
							// if it'c containment feature value, iterate it
							MethodElement olde = (MethodElement) o;
							if (olde.eContainer() == oldObj) {
								if (!isIdentical(olde, (MethodElement) n)) {
									return false;
								}
							} else if (oldValue != newValue) {
								return false;
							}
						} else {
							if (!o.equals(n)) {
								return false;
							}
						}
					}
				} else {
					if (!oldValue.equals(newValue)) {
						return false;
					}
				}
			}
		}

		return true;
	}
	
	/**
	 * check if the element is a process or not. 
	 * A Process is a CapabilityPattern or DeliveryProcess object 
	 * that is contained by a ProcessComponent.
	 * @param e EObject
	 * @return boolean
	 */
	public static boolean isProcess(EObject e) {
		return (e instanceof org.eclipse.epf.uma.Process) && 
			(e.eContainer() instanceof ProcessComponent);
	}
	
	public static List getValidViews(MethodConfiguration config) {
		
		List views = new ArrayList();
		for ( Iterator it = config.getProcessViews().iterator(); it.hasNext(); ) {
			ContentCategory view = (ContentCategory)it.next();
			if ( !ConfigurationHelper.isContributor(view) ) {
				view = (ContentCategory)ConfigurationHelper.getCalculatedElement(view, config);
				if ( view != null && !views.contains(view) ) {
					views.add(view);
				} 
			}	
		}
				
		return views;
	}
	
//	public static List getSuperActivities(BreakdownElement element) {
//		List items = new ArrayList();
//
//		// build the parent nodes
//		BreakdownElement parent = element.getSuperActivities(); 
//		while ( parent != null ) {
//			items.add(0, parent);
//			if (LibraryUtil.isProcess(parent) ) {
//				break;
//			}
//			parent = parent.getSuperActivities();
//		}
//
//		return items;
//	}
	
	/**
	 * Returns all structural features for the given method element
	 * @param element
	 * @return
	 */
	public static List getStructuralFeatures(MethodElement element, boolean addExtended) {
		List list = getStructuralFeatures(element);
						
		if (! addExtended) {
			return list;
		}
		
		if (element instanceof Practice) {
			PracticePropUtil practicePropUtil = PracticePropUtil.getPracticePropUtil();
			Practice practice = (Practice) element;
			UserDefinedTypeMeta meta = practicePropUtil.getUdtMeta(practice);
			if (meta != null && !meta.getQualifiedReferences().isEmpty()) {
				list.addAll(meta.getQualifiedReferences());
			}
		}
		
		PropUtil propUtil = PropUtil.getPropUtil();	
		list.add(UmaUtil.MethodElement_UdtList);
		
		
		ModifiedTypeMeta meta = propUtil.getGlobalMdtMeta(element);
		if (meta != null) {
			for (ExtendedReference eRef : meta.getReferences()) {
				list.add(eRef.getReference());
				for (QualifiedReference qRef : eRef.getQualifiedReferences()) {
					list.add(qRef.getReference());
				}
			}
		}
				
		if (! (element instanceof ContentDescription)) {
			return list;
		}
					
		EObject owner = element.eContainer();
		if (! (owner instanceof MethodElement)) {
			return list;
		}
		
		meta = propUtil.getGlobalMdtMeta((MethodElement) owner);
		if (meta == null) {
			return list;
		}
		for (ExtendedAttribute eAtt : meta.getRtes()) {
			list.add(eAtt.getAttribute());
		}
		for (ExtendedAttribute eAtt : meta.getAttributes()) {
			list.add(eAtt.getAttribute());
		}
		return list;
	}
	
	public static List getStructuralFeatures(MethodElement element) {
		List properties = element.getInstanceProperties();

		if (properties != null) {
			List<EStructuralFeature> features = new ArrayList<EStructuralFeature>();
			// get all features
			for (int i = 0; i < properties.size(); i++) {
				EProperty property = (EProperty) properties.get(i);
				if (property != null) {
					EStructuralFeature feature = property
							.getEStructuralFeature();
					features.add(feature);
				}
			}
			return features;
		} else
			return null;
	}
	
	public static List<EClass> getIncludedElementTypes() {
		if (includedElementTypes == null) {
			synchronized (LibraryUtil.class) {
				if (includedElementTypes == null) {
					includedElementTypes = new ArrayList<EClass>();
					for (EClassifier cls : UmaPackage.eINSTANCE
							.getEClassifiers()) {
						if (cls instanceof EClass
								&& UmaPackage.eINSTANCE.getDescribableElement()
										.isSuperTypeOf((EClass) cls)) {
							includedElementTypes.add((EClass) cls);
						}
					}
					includedElementTypes.removeAll(excludedTypes);
					Collections.sort(includedElementTypes, typeComparator);
					includedElementTypes = Collections.unmodifiableList(includedElementTypes);
				}
			}
		}
		return includedElementTypes;
	}
	
	public static List<DescribableElement> getIncludedElements(CustomCategory category, MethodConfiguration config) {
		List<DescribableElement> includedElements = new ArrayList<DescribableElement>();
		if (config == null) {
			return includedElements;
		}
		MethodElementProperty prop = MethodElementPropertyHelper.getProperty(category, MethodElementPropertyHelper.CUSTOM_CATEGORY__INCLUDED_ELEMENTS);
		EClassifier cls = prop == null ? null : UmaPackage.eINSTANCE.getEClassifier(prop.getValue());
		String udtId = null;		
		if (cls == null && prop != null && isUdtTypeId(prop.getValue())) {
			udtId = UserDefinedTypeMeta.getPracticeUdtId(prop.getValue());
			cls = UmaPackage.eINSTANCE.getPractice();
		}
		if(cls instanceof EClass) {
			EClass eClass = (EClass) cls;
			if (UmaPackage.eINSTANCE.getDescribableElement().isSuperTypeOf(eClass)) {
				MethodLibrary lib = (MethodLibrary) config
						.eContainer();
//				Iterator<EObject> iter = lib.eAllContents();
//				while (iter.hasNext()) {
//					EObject eObject = iter.next();
//					if (eClass.isInstance(eObject)) {
//						includedElements.add((DescribableElement) eObject);
//					}
				// }
				for (DescribableElement element : LibraryEditUtil.getInstance().getTypedElements(lib, eClass)) {
					boolean toAdd = true;
					if (udtId != null) {
						toAdd = false;
						if (element instanceof Practice) {
							UserDefinedTypeMeta meta = PracticePropUtil.getPracticePropUtil().getUdtMeta((Practice) element);
							if (meta != null && meta.getId().equals(udtId)) {
								toAdd = true;
							}
						}
					} else if (eClass == UmaPackage.eINSTANCE.getPractice()) {
						if (PracticePropUtil.getPracticePropUtil().isUdtType(element)) {
							toAdd = false;
						}
					}
					if (toAdd) {
						includedElements.add(element);
					}
				}
			}
		}
		return includedElements;
	}
	
	private static Adapter nameTrackPresentationNameMark = new AdapterImpl();		
	public static void addNameTrackPresentationNameMark(MethodElement element) {
		element.eAdapters().add(nameTrackPresentationNameMark);
	}
	public static void removeNameTrackPresentationNameMark(MethodElement element) {
		element.eAdapters().remove(nameTrackPresentationNameMark);
	}
	public static boolean hasNameTrackPresentationNameMark(MethodElement element) {
		for (Adapter adpter: element.eAdapters()) {
			if (adpter == nameTrackPresentationNameMark) {
				return true;
			}
		}		
		return false;
	}
	
	public static boolean markLibrarySynFree(final MethodLibrary lib, boolean toSave) {
		boolean b = markLibrarySynFree(lib, toSave, true);
		return b;
	}
	
	public static boolean markLibrarySynFree(final MethodLibrary lib, boolean toSave, boolean synFreeValue) {
		if (lib == null) {
			return true;
		}
		MethodLibraryPropUtil propUtil = MethodLibraryPropUtil.getMethodLibraryPropUtil();
		if (propUtil.isSynFree(lib) == synFreeValue) {
			return true;
		}

		final List<MethodPlugin> plugins = lib.getMethodPlugins();
		final List<Resource> resourcesToSave = new ArrayList<Resource>(); 
		if (toSave) {
			MethodPluginPropUtil pluginPropUtil = MethodPluginPropUtil.getMethodPluginPropUtil();
			for (int i = 0; i < plugins.size(); i++) {
				MethodPlugin plugin = plugins.get(i);
				if (pluginPropUtil.isSynFree(plugin) != synFreeValue) {
					Resource res = plugin.eResource();
					if (res != null) {
						resourcesToSave.add(res);
					}
				}
			}
			resourcesToSave.add(lib.eResource());			
			if (! checkModify(resourcesToSave)) {
				return false;
			}
		}
		
		propUtil.setSynFree(lib, synFreeValue);
		for (int i = 0; i < plugins.size(); i++) {
			markPluginSynFree(plugins.get(i), true, synFreeValue);
		}
		
		if (toSave) {
			SafeUpdateController.syncExec(new Runnable() {
				public void run() {
					ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil
							.getCurrentPersister().getFailSafePersister();
					try {
						for (Resource res : resourcesToSave) {
							persister.save(res);
						}
						persister.commit();
					} catch (Exception e) {
						persister.rollback();
						LibraryPlugin.getDefault().getLogger().logError(e);
					}
				}
			});
		}
		
		return true;
	}
	
	public static boolean markPluginSynFree(final MethodPlugin plugin, boolean toSave) {
		return markPluginSynFree(plugin, toSave, true);
	}
	
	public static boolean markPluginSynFree(final MethodPlugin plugin, boolean toSave, boolean synFreeValue) {
		if (plugin == null) {
			return true;
		}
		MethodPluginPropUtil propUtil = MethodPluginPropUtil.getMethodPluginPropUtil();
		if (propUtil.isSynFree(plugin) == synFreeValue) {
			return true;
		}
		
		if (toSave && plugin.eResource() != null) {
			List<Resource> resourcesToSave = new ArrayList<Resource>(); 
			resourcesToSave.add(plugin.eResource());
			if (! checkModify(resourcesToSave)) {
				return false;
			}
		}
		

		propUtil.setSynFree(plugin, synFreeValue);

		if (toSave && plugin.eResource() != null) {
			SafeUpdateController.syncExec(new Runnable() {
				public void run() {
					ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil
							.getCurrentPersister().getFailSafePersister();
					try {
						persister.save(plugin.eResource());
						persister.commit();
					} catch (Exception e) {
						persister.rollback();
						LibraryPlugin.getDefault().getLogger().logError(e);		
					}
				}
			});
		}
	
		return true;
		
	}
	
	private static boolean checkModify(List<Resource> resourcesToSave) {
		IStatus status = UserInteractionHelper.checkModify(resourcesToSave,
				Display.getCurrent().getActiveShell());

		if (!status.isOK()) {
			// To do: display error msg
			return false;
		}
		
		return true;
	}
	
	public static List<Practice> getPractices(MethodConfiguration config) {
		boolean oldValue = MethodConfigurationItemProvider.isFinalOnGetChildrenCall();
		MethodConfigurationItemProvider.setFinalOnGetChildrenCall(true);
		List<Practice> list = null;
		try { 
			list = getPractices_(config);
		} finally {
			MethodConfigurationItemProvider.setFinalOnGetChildrenCall(oldValue);
		}		
		return list;
	}
	
	private static List<Practice> getPractices_(MethodConfiguration config) {
		List<Practice> practiceList = new ArrayList<Practice>();
		if (config == null) {
			return practiceList;
		}
				
		Set<Practice> practiceSet = new HashSet<Practice>();
		
		IFilter filter = new ConfigurationFilter(config);
		AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE.getConfigurationView_AdapterFactory(filter);
		ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory.adapt(config, ITreeItemContentProvider.class);
		GuidanceGroupingItemProvider guidanceGroupingItemProvider = null;
		for (Object child : adapter.getChildren(config)) {
			if(child instanceof GuidanceGroupingItemProvider) {
				guidanceGroupingItemProvider = (GuidanceGroupingItemProvider) child;
				break;
			}
		}
		
		if(guidanceGroupingItemProvider != null) {
			GuidanceItemProvider practicesItemProvider = null;
			Collection<?> children = guidanceGroupingItemProvider.getChildren(guidanceGroupingItemProvider);
			for (Object child : children) {
				if(child instanceof GuidanceItemProvider) {
					GuidanceItemProvider ip = (GuidanceItemProvider) child;
					if(ip.getGuidanceFilter() == GuidanceGroupingItemProvider.practiceFilter) {
						practicesItemProvider = ip;
						break;
					}
				}
			}
			if(practicesItemProvider != null) {
				children = practicesItemProvider.getChildren(practicesItemProvider);
				for (Object child : children) {
					if(child instanceof Practice && ! practiceSet.contains(child)) {
						Practice p = (Practice) child;
						practiceList.add(p);
						practiceSet.add(p);
					}
				}
			}
		}
		
		return practiceList;
	}
	
	private static CustomCategory createCustomCategory(MethodPlugin plugin, CustomCategory parent, String name) {
		ContentPackage pkg = UmaUtil.findContentPackage(plugin, ModelStructure.DEFAULT.customCategoryPath);
		CustomCategory cc = UmaFactory.eINSTANCE.createCustomCategory();

		pkg.getContentElements().add(cc);
		if ( parent != null ) {
			((CustomCategory)parent).getCategorizedElements().add(cc);
		}
		else {
			TngUtil.getRootCustomCategory(plugin).getCategorizedElements().add(cc);
		}
		cc.setName(name);
		cc.setPresentationName(name);
		cc.setGuid(EcoreUtil.generateUUID());
		
		return cc;
	}
	
	public static class ConfigAndPlugin {
		public MethodConfiguration config;
		public MethodPlugin plugin;
	}
	
	public static ConfigAndPlugin addTempConfigAndPluginToCurrentLibrary(List<Process> configFreeProcesses) {
		if (configFreeProcesses == null || configFreeProcesses.isEmpty()) {
			return null;
		}
		MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
		if (lib == null) {
			return null;
		}
		MethodElementPropUtil proppUtil = MethodElementPropUtil.getMethodElementPropUtil();
		ConfigAndPlugin tempConfigAndPlugin = new ConfigAndPlugin();	
		MethodConfiguration tempConfig = UmaFactory.eINSTANCE.createMethodConfiguration();
		tempConfig.setName("Generated_Configuration");		//$NON-NLS-1$
		MethodPlugin tempPlugin = null;
		CustomCategory defaultView = null;
		proppUtil.setTransientElement(tempConfig, true);

		try {
			tempPlugin = LibraryServiceUtil.createMethodPlugin("viewPlugin", null, null, null); //$NON-NLS-1$
			proppUtil.setTransientElement(tempPlugin, true);
			tempConfigAndPlugin.plugin = tempPlugin;
			defaultView = createCustomCategory(tempPlugin, null, LibraryResources.configFreeProcessView_title);
			proppUtil.setTransientElement(defaultView, true);
			tempConfig.getMethodPluginSelection().add(tempPlugin);
			tempConfig.getMethodPackageSelection().addAll(UmaUtil.getAllMethodPackages(tempPlugin));
			tempConfig.getProcessViews().add(defaultView);
			tempConfig.setDefaultView(defaultView);
			
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
			return null;
		}

		Set<Task> taskSet = new HashSet<Task>();
		Set<Role> roleSet = new HashSet<Role>();
		Set<WorkProduct> wpSet = new HashSet<WorkProduct>();
		Set<Guidance> guidanceSet = new HashSet<Guidance>();
		
		Set<MethodPlugin> addedPlugins = new HashSet<MethodPlugin>();
		for (Process proc : configFreeProcesses) {
			Scope scope = ProcessScopeUtil.getInstance().loadScope(proc);
			if (scope == null) {
				continue;
			}
			for (MethodPlugin plugin : scope.getMethodPluginSelection()) {
				if (addedPlugins.contains(plugin)) {
					continue;
				}				
				addedPlugins.add(plugin);
				tempConfig.getMethodPluginSelection().add(plugin);
				tempConfig.getMethodPackageSelection().addAll(UmaUtil.getAllMethodPackages(plugin));
				for (TreeIterator it = plugin.eAllContents(); it.hasNext();) {
					Object obj = it.next();
					if (obj instanceof Task) {
						taskSet.add((Task) obj);
					} else if (obj instanceof Role) {
						roleSet.add((Role) obj);
					} else if (obj instanceof WorkProduct) {
						wpSet.add((WorkProduct) obj);
					} else if (obj instanceof Guidance) {
						guidanceSet.add((Guidance) obj);
					}
				}				
			}
		}
		
		createSubGroup(tempPlugin, defaultView, taskSet,  LibraryEditPlugin.INSTANCE.getString("_UI_Tasks_group"));//$NON-NLS-1$
		createSubGroup(tempPlugin, defaultView, roleSet, LibraryEditPlugin.INSTANCE.getString("_UI_Roles_group"));//$NON-NLS-1$
		createSubGroup(tempPlugin, defaultView, wpSet, LibraryEditPlugin.INSTANCE.getString("_UI_WorkProducts_group"));//$NON-NLS-1$
		createSubGroup(tempPlugin, defaultView, guidanceSet, LibraryEditPlugin.INSTANCE.getString("_UI_Guidances_group"));//$NON-NLS-1$
		createSubGroup(tempPlugin, defaultView, configFreeProcesses, LibraryEditPlugin.INSTANCE.getString("_UI_Processes_group"));//$NON-NLS-1$
				
		boolean oldDeliver = lib.eDeliver();
		lib.eSetDeliver(false);
		try {
			lib.getPredefinedConfigurations().add(tempConfig);
		} finally {
			if (oldDeliver) {
				lib.eSetDeliver(oldDeliver);
			}
		}
		
		LibraryService.getInstance().getConfigurationManager(tempConfig);
		
		tempConfigAndPlugin.config = tempConfig;
		tempConfigAndPlugin.plugin = null;
		
		return tempConfigAndPlugin;
	}

	private static void createSubGroup(MethodPlugin tempPlugin, CustomCategory defaultView,
			Collection<? extends DescribableElement> elements, String name) {
		MethodElementPropUtil propUtil = MethodElementPropUtil.getMethodElementPropUtil();
		if (elements != null && ! elements.isEmpty()) {
			CustomCategory cc = createCustomCategory(tempPlugin, defaultView, name);
			propUtil.setTransientElement(cc, true);
			cc.setBriefDescription(LibraryResources.systemCreatedCustomCategory_brief);
			cc.getCategorizedElements().addAll(elements);
		}
	}
	
	public static void removeTempConfigAndPluginFromCurrentLibrary(
			ConfigAndPlugin tempConfigAndPlugin) {
		if (tempConfigAndPlugin == null) {
			return;
		}
		MethodConfiguration tempConfig = tempConfigAndPlugin.config;
		MethodPlugin tempPlugin = tempConfigAndPlugin.plugin;
		if (tempConfig == null && tempPlugin == null) {
			return;
		}
		
		MethodLibrary lib = LibraryService.getInstance()
				.getCurrentMethodLibrary();
		if (lib != null) {
			boolean oldDeliver = lib.eDeliver();
			lib.eSetDeliver(false);
			try {
				lib.getPredefinedConfigurations().remove(tempConfig);
				lib.getMethodPlugins().remove(tempPlugin);
			} finally {
				if (oldDeliver) {
					lib.eSetDeliver(oldDeliver);
				}
			}
		}
		LibraryService.getInstance().removeConfigurationManager(tempConfig);
	}
	
	public static List<EReference> getGuidancesRefList(MethodElement element) {
		List<EReference> list = new ArrayList<EReference>();
		if (element != null) {
			for (EReference ref : element.eClass().getEAllReferences()) {
				if (LibraryEditUtil.getInstance().isGuidanceListReference(ref)) {
					list.add(ref);
				}
			}		
		}		
		return list;
	}
	
	public static List<? extends Guidance> getGuidances(MethodElement element,
			MethodConfiguration config) {
		ElementRealizer realizer = config == null ? null
				: DefaultElementRealizer.newElementRealizer(config);

		List<Guidance> guidanceList = new ArrayList<Guidance>();
		Set<Guidance> seen = new HashSet<Guidance>();

		for (EReference ref : getGuidancesRefList(element)) {
			List<Guidance> valueList = realizer == null ? (List<Guidance>) element
					.eGet(ref)
					: ConfigurationHelper.calc0nFeatureValue(element, ref,
							realizer);
			if (valueList == null || valueList.isEmpty()) {
				continue;
			}
			for (Guidance g : valueList) {
				if (seen.contains(g)) {
					continue;
				}
				seen.add(g);
				guidanceList.add(g);
			}
		}

		return guidanceList;
	}
	
	public 	static IWorkbenchWindow getActiveWorkbenchWindow() {
		if (ConfigurationHelper.serverMode) {
			return null;
		}
		try {
			IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			if (window == null) {
				final IWorkbenchWindow[] windows = new IWorkbenchWindow[1]; 
				SafeUpdateController.syncExec(new Runnable() {	
					public void run() {
						windows[0] = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
					}
				});
				window = windows[0];
			}		
			return window;
		} catch (Exception e) {
			
		}
		
		return null;
	}
	
	public static class MeVisitor implements IMeVisitor {
		
		private Set<MethodElement> elementsToProcess = new HashSet<MethodElement>(); 
		
		public void visit(MethodElement element) {
			PropUtil propUtil = PropUtil.getPropUtil();
			if (element instanceof WorkProductDescriptor) {
					boolean b = DescriptorPropUtil.getDesciptorPropUtil().isCreatedByReference((WorkProductDescriptor) element);
					propUtil.setExcludedFromPublish(element, b);
			}			
			if (propUtil.getGlobalMdtMeta(element) != null) {
				elementsToProcess.add(element);
			
			} else if (propUtil.hasReferences(element)) {
				elementsToProcess.add(element);
				
			} else if (element instanceof Practice) {
				PracticePropUtil practicePropUtil = PracticePropUtil.getPracticePropUtil();
				UserDefinedTypeMeta meta = practicePropUtil.getUdtMeta((Practice) element);
				if (meta != null && !meta.getQualifiedReferences().isEmpty()) {
					elementsToProcess.add(element);
				}
			}
		}
		
		public void processElements() {
			PropUtil propUtil = PropUtil.getPropUtil();
			for (MethodElement element : elementsToProcess) {
				ModifiedTypeMeta modifiedTypeMeta = propUtil.getGlobalMdtMeta(element);
				if (modifiedTypeMeta != null) {
					for (ExtendedReference eRef : modifiedTypeMeta.getReferences()) {
						propUtil.getExtendedReferenceList(element, eRef, false);
						for (QualifiedReference qRef : eRef.getQualifiedReferences()) {
							propUtil.getExtendedReferenceList(element, qRef, false);
						}
					}
				}
				if (propUtil.hasReferences(element)) {
					propUtil.getUdtList(element, false);					
					ModifiedTypeMeta meta = propUtil.getGlobalMdtMeta(element);
					if (meta != null) {
						for (ExtendedReference ref : meta.getReferences()) {
							propUtil.getExtendedReferenceList(element, ref, false);
							for (ExtendedReference qref : ref.getQualifiedReferences()) {
								propUtil.getExtendedReferenceList(element, qref, false);
							}
						}
					}
					
				}				
				if (element instanceof Practice) {
					PracticePropUtil practicePropUtil = PracticePropUtil.getPracticePropUtil();
					UserDefinedTypeMeta meta = practicePropUtil.getUdtMeta((Practice) element);
					if (meta != null && !meta.getQualifiedReferences().isEmpty()) {
						for (EReference ref : meta.getQualifiedReferences()) {
							practicePropUtil.getQReferenceListById(element, ref.getName(), false);
						}
					}
				}
			}
		}
	}
	
	public static boolean isUdtTypeId(String typeId) {
		ILibraryManager mgr = LibraryService.getInstance().getCurrentLibraryManager();
		return isUdtTypeId(typeId, mgr);
	}
	
	private static boolean isUdtTypeId(String typeId, ILibraryManager mgr) {
		if (mgr == null) {
			return false;
		}
		String id = UserDefinedTypeMeta.getPracticeUdtId(typeId);		
		return mgr.getUserDefineType(id) != null;
	}
	
	public static boolean isUnderSupporting(MethodElement element) {
		MethodPlugin plugin = UmaUtil.getMethodPlugin(element);
		if (plugin == null) {
			return false;
		}
		if (plugin.isSupporting()) {
			return true;
		}
		
		EObject parent = getSelectable(element);
		if (parent instanceof ContentPackage) {
			MethodElementPropUtil propUtil = MethodElementPropUtil.getMethodElementPropUtil();
			return propUtil.isSupporting((ContentPackage) parent);
		}
		
		return false;
	}
	
	public static List<UserDefinedTypeMeta> getAllUDTMetas() {
		List<UserDefinedTypeMeta> result = new ArrayList<UserDefinedTypeMeta>();
		
		ILibraryManager libMgr = LibraryService.getInstance().getCurrentLibraryManager();
		if (libMgr != null) {
			Collection<UserDefinedTypeMeta> metas = libMgr.getUserDefinedTypes();
			if (metas != null) {
				result.addAll(metas);
			}
			Collections.sort(result, new UdtMetaComparator());
		}
		
		return result;
	}
	
	public static UserDefinedTypeMeta getUDTMetaFromId(String id) {
		List<UserDefinedTypeMeta> metas = getAllUDTMetas();
		
		for (UserDefinedTypeMeta meta : metas) {
			String metaId = meta.getRteNameMap().get(UserDefinedTypeMeta._typeId);
			if (id.equals(metaId)) {
				return meta;
			}			
		}
		
		return null;
	}
	
	private static class UdtMetaComparator implements Comparator<UserDefinedTypeMeta> {
	    public int compare(UserDefinedTypeMeta obj1, UserDefinedTypeMeta obj2) {
	    	String name1 = obj1.getRteNameMap().get(UserDefinedTypeMeta._typeName);
	    	String name2 = obj2.getRteNameMap().get(UserDefinedTypeMeta._typeName);
	    	
	    	return name1.compareTo(name2);
	    }		
	}
	
}
