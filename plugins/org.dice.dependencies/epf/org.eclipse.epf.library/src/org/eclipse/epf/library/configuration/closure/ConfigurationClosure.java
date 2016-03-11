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
package org.eclipse.epf.library.configuration.closure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.library.IConfigurationClosure;
import org.eclipse.epf.library.IConfigurationManager;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigDataBase;
import org.eclipse.epf.library.configuration.ConfigurationData;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.configuration.ConfigurationProperties;
import org.eclipse.epf.library.configuration.SupportingElementData;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.util.ConfigurationUtil;
import org.eclipse.epf.library.edit.util.MethodElementPropertyHelper;
import org.eclipse.epf.library.edit.util.MethodElementPropertyMgr;
import org.eclipse.epf.library.edit.util.MethodElementPropertyMgr.ChangeEvent;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.util.LibraryProblemMonitor;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * A method configuration closure.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ConfigurationClosure implements IConfigurationClosure {

	// If true, generate debug traces.
	protected static boolean debug = LibraryPlugin.getDefault().isDebugging();

	protected MethodConfiguration config = null;

	protected MethodLibrary library = null;

	protected IConfigurationManager configManager = null;

	protected DependencyManager dependencyManager = null;
	
	protected IActionManager actionMgr;

	// Node change information. The object are the model objects
	// check the linked objects in needed
	protected List selectedNotes = new ArrayList();
	
	private List<ElementError> errors = new ArrayList<ElementError>();
	
	private boolean abortCheckError = false;
	
	public static ProcessNodeLock processNodeLock = new ProcessNodeLock();
	
	// marker ID 
	public static final String multipleReplacersMARKER_ID = "org.eclipse.epf.library.multipleReplacers"; //$NON-NLS-1$
	
	public static final String replacerGuids = "replacerGuids"; //$NON-NLS-1$
	
	// A map of invalid nodes to ElementDependencyError objects.
	protected Map<Object, ElementDependencyError> invalidNodesMap =
		new HashMap<Object, ElementDependencyError>();

	//protected List changedNodes = new ArrayList();
	
	private List<ClosureListener> listeners;

	private MethodElementPropertyMgr.ChangeEventListener configPropListener;
	
	private Map<MethodElement, IMarker> replacerElementMarkerMap;
	/**
	 * Creates a new instance.
	 * 
	 * @param actionMgr
	 * 			  The IActionManager to use to change the MethodConfiguration.  If null, config will be changed directly.
	 * @param config
	 *            A method configuration.
	 */
	public ConfigurationClosure(IActionManager actionMgr, MethodConfiguration config) {
		this.config = config;
		this.actionMgr = actionMgr;

		configManager = LibraryService.getInstance().getConfigurationManager(
				config);
		if (configManager != null) {
			library = configManager.getMethodLibrary();
			//dependencyManager = configManager.getDependencyManager();
			
			ConfigurationProperties props = configManager.getConfigurationProperties();
			configPropListener = new MethodElementPropertyMgr.ChangeEventListener() {
				public void notifyChange(ChangeEvent event) {
					if (event.propElement != null) {
						String name = event.propElement.getName();
						if (MethodElementPropertyHelper.CONFIG_UPDATE_ON_CLICK
								.equals(name)
								|| MethodElementPropertyHelper.CONFIG_NO_UPDATE_ON_CLICK
										.equals(name)) {
							return;
						}
					}
					refreshErrormarks();
				}
			};			
			props.addListener(configPropListener);
		}

		// configuration changed, re-build the analyze the configuration for errors
		checkError();
	}

	/**
	 * Returns the method configuration manager.
	 * 
	 * @return A <code>ConfigurationManager</code>.
	 */
	public IConfigurationManager getConfigurationManager() {
		return configManager;
	}

	/**
	 * Returns the method configuration.
	 * 
	 * @return A <code>MethodConfiguration</code>.
	 */
	public MethodConfiguration getConfiguration() {
		return config;
	}

	/**
	 * Returns the containining method library.
	 * 
	 * @return A <code>MethodConfiguration</code>.
	 */
	public MethodLibrary getLibrary() {
		return library;
	}

//	/**
//	 * Sets the method plug-ins and packages selection.
//	 * 
//	 * @param elements
//	 *            an array of method plug-ins and packages
//	 */
//	public void setSelections(Object[] elements) {
//		
//		if ( elements == null ) {
//			return;
//		}
//		
//		// clear the old selections
//		selectedNotes.clear();
//		for (int i = 0; i < elements.length; i++ ) {
//			selectedNotes.add(elements[i]);
//		}
//	}

	
	/**
	 * Builds the selection list based on the method configuration.
	 * 
	 */
	public void checkError() {
		if (isRunningCheckError()) {
			if (ConfigDataBase.localDebug) {
				System.out.println("LD> checkError skipped");//$NON-NLS-1$
			}
			return;
		}
		ILibraryResourceSet libResourceSet = null;
		Resource resource = config.eResource();
		if(resource != null && resource.getResourceSet() instanceof ILibraryResourceSet) {
			libResourceSet = (ILibraryResourceSet) resource.getResourceSet();
		}
		Object oldValue = null;
		Map<Object, Object> loadOptions = null;
		if(libResourceSet != null) {
			loadOptions = libResourceSet.getLoadOptions();;
			oldValue = loadOptions.get(MultiFileResourceSetImpl.SKIP_RETRY_PROXY_RESOLUTION);
			loadOptions.put(MultiFileResourceSetImpl.SKIP_RETRY_PROXY_RESOLUTION, Boolean.TRUE);
		}
		try {
			if (ConfigDataBase.localDebug) {
				System.out.println("LD> checkError_ ->");//$NON-NLS-1$
			}
			checkError_();
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		} finally {
			if (ConfigDataBase.localDebug) {
				System.out.println("LD> checkError_ <-");//$NON-NLS-1$
			}
			setRunningCheckError(false);
			if(loadOptions != null) {
				if(oldValue == null) {
					loadOptions.remove(MultiFileResourceSetImpl.SKIP_RETRY_PROXY_RESOLUTION);
				} else {
					loadOptions.put(MultiFileResourceSetImpl.SKIP_RETRY_PROXY_RESOLUTION, oldValue);
				}
			}
		}	
	}
	
	private boolean runningCheckError = false;
	
	//Be aware of side effect of the call
	private synchronized boolean isRunningCheckError()  {
		boolean ret = runningCheckError;
		if (! ret) {	//make sure no 2 threads can be runningCheckError at the same time
			runningCheckError = true;
		}
		return ret;
	}
	private synchronized void setRunningCheckError(boolean b)  {
		runningCheckError = b;
	}
	
	private void checkError_() {
		dependencyManager = new DependencyManager(library, getConfiguration());
		
		// Bug 206724 - SCM: Always prompt check out elements for a opened configuration when refresh source control status
		// don't need to validate the configuration, rely on the caller to validate it before call this method.
		// Validate the method configuration with the action manager.
		//LibraryUtil.validateMethodConfiguration(actionMgr, config);

		// initial fill the selections
		selectedNotes.clear();
		selectedNotes.addAll(config.getMethodPluginSelection());
		selectedNotes.addAll(config.getMethodPackageSelection());

		// Re-build the selections to auto add the process packages.
		clearErrorMarks();
		
		// Cleanup the old status and rebuild the list.
		invalidNodesMap.clear();

		synchronized (processNodeLock) {
			processNodeLock.setLockingThread(Thread.currentThread());
			try {
				processChangedNodes(getSelection());
			} finally {
				processNodeLock.setLockingThread(null);
			}
		}

		processReplacers();
		
	}

	private void processReplacers() {
		clearReplacerElementMarkerMap();
		
		Set<VariabilityElement> replacerSet = dependencyManager.getReplacerSet();
		if (replacerSet == null || replacerSet.isEmpty()) {
			return;
		}			

		Map<VariabilityElement, List> baseReplacersMap = new HashMap<VariabilityElement, List>();
		for (VariabilityElement ve: replacerSet) {
			if (! ConfigurationHelper.inConfig(ve, config, true, false)) {
				continue;
			}
			VariabilityElement base = ve.getVariabilityBasedOnElement();
			if (! ConfigurationHelper.inConfig(base, config, true, false)) {
				continue;
			}
			List<MethodElement> replacers = baseReplacersMap.get(base);
			if (replacers == null) {
				replacers = new ArrayList<MethodElement>();
				baseReplacersMap.put(base, replacers);
			}
			replacers.add(ve);
		}
		
		//clearReplacerElementMarkerMap();	
		replacerElementMarkerMap = new HashMap<MethodElement, IMarker>();
		for (Map.Entry<VariabilityElement, List> entry: baseReplacersMap.entrySet()) {
			VariabilityElement base = entry.getKey();
			List replacers = entry.getValue();
			processReplacerError(replacerElementMarkerMap, base, replacers);
		}
	}
	
	private static void processReplacerError(Map<MethodElement, IMarker> elementMarkerMap, 
			MethodElement base, Collection<MethodElement> replacers) {
		if (replacers == null || replacers.isEmpty() || replacers.size() < 2) {
			return;
		}
		IMarker marker = elementMarkerMap.get(base);
		if (marker == null) {
			Resource res = base.eResource();
			if (res == null) {
				return;
			}
		
			URI containerURI = res.getURI();
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IPath path = new Path(containerURI.toFileString());
			IFile file = workspace.getRoot().getFileForLocation(path);
			if (file == null) {
				return;
			}
			String location = containerURI != null ? containerURI
					.toFileString() : ""; //$NON-NLS-1$	

			try {
				marker = file.createMarker(multipleReplacersMARKER_ID);
				marker.setAttribute(IMarker.LOCATION, location);				
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
				marker.setAttribute(LibraryProblemMonitor.Guid, base.getGuid());
				elementMarkerMap.put(base, marker);
					
			} catch (Exception e) {
				LibraryPlugin.getDefault().getLogger().logError(e);
			}
		}
		
		
		String replacerGuidsValue = "";		//$NON-NLS-1$
		for (MethodElement replacer: replacers) {
			if (replacerGuidsValue.length() != 0) {
				replacerGuidsValue += ", "; //$NON-NLS-1$	
			}
			replacerGuidsValue += replacer.getGuid();
		}
		String errMsg = LibraryResources.bind(LibraryResources.ElementError_having_multiple_replacers, 
				(new String[] {LibraryUtil.getTypePath(base), 
						replacerGuidsValue }));
		
		try {
			marker.setAttribute(replacerGuids, replacerGuidsValue);
			marker.setAttribute(IMarker.MESSAGE, errMsg);
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}
				
	}

	private void clearErrorMarks() {
		clearErrorMarks(true);
	}

	private void clearErrorMarks(boolean checkAbort) {
		// Re-build the selections to auto add the process packages.

		for (Iterator<ElementError> it = errors.iterator(); it.hasNext(); ) {
			if (checkAbort && isAbortCheckError()) {
				return;
			}
			ElementError error = it.next();
			notifyError(error, ClosureListener.ERROR_REMOVED);
		}	
	}
	
	public void refreshErrormarks() {
		clearErrorMarks();
		for (Iterator<ElementError> it = errors.iterator(); it.hasNext(); ) {
			if (isAbortCheckError()) {
				return;
			}
			ElementError error = it.next();
			notifyError(error, ClosureListener.ERROR_ADDED);
		}	
	}
	
//	private void selectProcessPackages(Activity a) {
//		if (a == null) {
//			return;
//		}
//
//		for (Iterator it = a.getBreakdownElements().iterator(); it.hasNext();) {
//			BreakdownElement e = (BreakdownElement) it.next();
//			Object pkg = e.eContainer();
//			if (!selectedNotes.contains(pkg)) {
//				selectedNotes.add(pkg);
//				changedNodes.add(pkg);
//			}
//
//			if (e instanceof Activity) {
//				selectProcessPackages((Activity) e);
//			}
//		}
//	}

//	/**
//	 * Adds a method plug-in or package to the closure.
//	 * 
//	 * @param element
//	 *            A method element.
//	 * @param addChildren
//	 *            if <code>true</code>, add all child method elements.
//	 */
//	private void add(EObject element, boolean addChildren) {
//		if (!LibraryUtil.selectable(element)) {
//			return;
//		}
//
//		if (!selectedNotes.contains(element)) {
//			selectedNotes.add(element);
//
//			// Save the changed nodes so that we can update the status later.
//			addChanged(element);
//
//			if (element instanceof MethodPlugin) {
//				selectSystemPackages((MethodPlugin) element);
//			}
//		}
//
//		// Add the parent method element as well.
//		EObject parent = element.eContainer();
//		if ((parent != null) && !selectedNotes.contains(parent)) {
//			add(parent, false);
//		}
//
//		// Add children as needed.
//		if (addChildren) {
//			EList elements = element.eContents();
//			if (elements != null) {
//				for (Iterator it = elements.iterator(); it.hasNext();) {
//					EObject child = (EObject) it.next();
//					add(child, true);
//				}
//			}
//		}
//	}
//
//	private void addChanged(Object element) {
//		if (!changedNodes.contains(element)) {
//			changedNodes.add(element);
//		}
//	}

//	private void selectSystemPackages(MethodPlugin plugin) {
//		List pkgs = TngUtil.getAllSystemPackages(plugin);
//		for (Iterator it = pkgs.iterator(); it.hasNext();) {
//			EObject pkg = (EObject) it.next();
//			add(pkg, false);
//		}
//	}

	/**
	 * Checks whether a method plug-in or package is selected.
	 * 
	 * @return <code>true</code> if the given element is selected.
	 */
	public boolean isSelected(Object input) {
		if ((input instanceof MethodLibrary) || input == config
				|| selectedNotes.contains(input)) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the element dependency error for a method element.
	 * 
	 * @element A method element.
	 * 
	 * @return An <code>ElementDependencyError</code>.
	 */
	public ElementDependencyError getError(Object element) {
		return getError(element, false);
	}

	private ElementDependencyError getError(Object element, boolean create) {
		ElementDependencyError error = (ElementDependencyError) invalidNodesMap
				.get(element);
		if (error == null && create) {
			error = new ElementDependencyError(element);
			invalidNodesMap.put(element, error);
		}
		return error;
	}

	/**
	 * check if there is error in this configuration closure
	 * 
	 * @return boolean
	 */
	public boolean hasError() {
		for (Iterator it = invalidNodesMap.values().iterator(); it.hasNext();) {
			ElementDependencyError error = (ElementDependencyError) it.next();
			if (error.isError()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * check if there is any problem (error/warning) with this configuration closure.
	 * @return boolean
	 */
	public boolean hasProblem() {
		return invalidNodesMap.size() > 0;
	}

	/**
	 * Returns all the errors.
	 * 
	 */
	public List<ElementError> getAllErrors() {
		return errors;
	}
	
	private List<PackageError> getAllPackageErrors() {
		List<PackageError> perrors = new ArrayList<PackageError>();
		for (Iterator it = invalidNodesMap.values().iterator(); it.hasNext();) {
			ElementDependencyError error = (ElementDependencyError) it.next();
			perrors.addAll(error.getAll());
		}
		return perrors;
	}

	/**
	 * Returns all the dependency errors.
	 * 
	 * @return An array of <code>ElementDependencyError</code>
	 */
	public Object[] getDependencyErrors() {
		return invalidNodesMap.values().toArray();
	}

	/**
	 * Returns all the invalid elements.
	 * 
	 * @return A list of invalid elements.
	 */
	public List<Object> getInvalidElements() {
		return new ArrayList<Object>(invalidNodesMap.keySet());
	}

	private void removeError(Object element) {
		if (invalidNodesMap.containsKey(element)) {
			invalidNodesMap.remove(element);

//			// Error status changed, add it to the changed list.
//			if (!changedNodes.contains(element)) {
//				changedNodes.add(element);
//			}
		}
	}

//	/**
//	 * Returns all the changed elements. These are elements whose check states
//	 * have changed or whose image have changed due to error.
//	 * 
//	 * @return A list of changed method elements.
//	 */
//	public List getChangedElements() {
//		List items = new ArrayList(changedNodes);
//		for (Iterator it = invalidNodesMap.keySet().iterator(); it.hasNext();) {
//			Object item = it.next();
//			if (!items.contains(item)) {
//				items.add(item);
//			}
//		}
//		return items;
//	}

	/**
	 * Returns the method plug-ins and packages selection.
	 * 
	 * @return An array of method plug-ins and packages.
	 */
	public Object[] getSelection() {
		return selectedNotes.toArray();
	}

	private void processChangedNodes(Object[] changedNodes) {		
		if (getConfigurationManager().getSupportingElementData() != null) {
			getConfigurationManager().getSupportingElementData().beginUpdateSupportingElements();
		}
		
		replacerMap.clear();
		processChangedNodes_(changedNodes);
		replacerMap.clear();
		
/*		if (isAbortCheckError()) {
			return;
		}*/
		Map<String, ElementReference> refMap = new HashMap<String, ElementReference>();			
		getConfigurationManager().getSupportingElementData().endUpdateSupportingElements(refMap);
		if (isAbortCheckError()) {
			return;
		}
		for (ElementReference ref : refMap.values()) {
			ElementError error = ConfigurationErrorMatrix.getError(config, ref);
			if (error != null) {
				errors.add(error);
/*				notifyError(error, ClosureListener.ERROR_ADDED);
				processPackageError(LibraryUtil.getSelectable(ref.element),
						LibraryUtil.getSelectable(ref.refElement), error
								.getErrorLevel());*/
			}
		}
		
		List<ElementError> updatedErrors = new ArrayList<ElementError>();
		for (ElementError error: errors) {
			if (error.causeElement instanceof MethodElement) {
				MethodElement referenced = (MethodElement) error.causeElement;
				if (! ConfigurationHelper.inConfig(referenced, getConfiguration())) {				
					notifyError(error, ClosureListener.ERROR_ADDED);
					updatedErrors.add(error);
					if (error.ownerElement instanceof MethodElement) {
						MethodElement referencing = (MethodElement) error.ownerElement;
						processPackageError(LibraryUtil.getSelectable(referencing),
								LibraryUtil.getSelectable(referenced), error
										.getErrorLevel());
					}
				}
			}
		}
		errors = updatedErrors;
	}
	
	private void processChangedNodes_(Object[] changedNodes) {	
		SupportingElementData seData = getConfigurationManager().getSupportingElementData();	

		// for all the changed notes,
		// all all contained elements and check if the elements are in config or not
		// if the elements are in config, the referenced elements should also be in config
		
		for (int i = 0; i <changedNodes.length; i++) {
			if (isAbortCheckError()) {
				return;
			}
			
			// the elements are either plugin or package
			Object changedElement = changedNodes[i];
			if (seData != null && seData.isSupportingSelectable((MethodElement) changedElement)) {
				continue;
			}
			
			PackageDependency dependency = dependencyManager
					.getDependency((MethodElement) changedElement);
			if (dependency == null) {
				continue;
			}

			List refs = dependency.getAllElementReferences();
			if ( refs == null || refs.size() == 0 ) {
				continue;
			}
			
			for (Iterator itr = refs.iterator(); itr.hasNext(); ) {
				ElementReference ref = (ElementReference)itr.next();
				checkReference(ref);
			}
		}
		
		// for all the elements in the added category, check their references as well
		List<MethodElement> list = new ArrayList<MethodElement>();
		list.addAll(configManager.getConfigurationData().getAddedElements());
		for ( Iterator<MethodElement> it = list.iterator(); it.hasNext(); ) {
			MethodElement e = it.next();
			
			PackageDependency dependency = 
				dependencyManager.getDependency((MethodElement)e.eContainer());
			if (dependency == null) {
				continue;
			}
		
			List refs = dependency.getAllElementReferences();
			if ( refs == null || refs.size() == 0 ) {
				continue;
			}
			
			for (Iterator itr = refs.iterator(); itr.hasNext(); ) {
				ElementReference ref = (ElementReference)itr.next();
				if ( ref.getElement() == e ) {
					checkReference(ref);
				}
			}
	
		}
		
	}
	
	private Map<VariabilityElement, VariabilityElement> replacerMap = new HashMap<VariabilityElement, VariabilityElement>();
	private VariabilityElement getReplacer(VariabilityElement e) {
		VariabilityElement r = replacerMap.get(e);
		if (r == e) {
			return null;
		}
		if (r == null) {
			r = ConfigurationHelper.getReplacer((VariabilityElement)e, config);
			if (r == null) {
				replacerMap.put(e, e);
			} else {
				replacerMap.put(e, r);
			}
		}
		return r;
	}
	
	private void checkReference(ElementReference ref) {
		MethodElement e = ref.getElement();
		MethodElement e_ref = ref.getRefElement();
		SupportingElementData seData = getConfigurationManager().getSupportingElementData();
		ConfigurationData configData = getConfigurationManager().getConfigurationData();

		
		//System.out.println("LD> e: " + e);
		//System.out.println("LD> e_ref: " + e_ref);
				
		if ( e instanceof MethodPackage || e instanceof MethodConfiguration ) {
			return;
		}
		
		// Bug 207609 - Replaced elements are not considered for configuration error "missing mandatory input"
		// if the element is replaced, ignore the reference
		if ( e instanceof VariabilityElement ) {
			VariabilityElement replacer = getReplacer((VariabilityElement)e);
			if ( replacer != null ) {
				return;
			}
		}
		
		// the element might be subtracted, so ignore it
/*		if ( !ConfigurationHelper.inConfig(e, config, true, false)) {
			return;
		}*/
		if ( configData.isSubstracted(e)) {
			return;
		}
				
		// if the referenced element is not in  config, log error
		if ( !ConfigurationHelper.inConfig(e_ref, config)
				&& (seData == null || !seData.isSupportingElementCallDuringUpdating(ref))) {
			if (!ConfigurationHelper.inConfig(e, config)) {
				return;
			}
			
			/*
			String message = LibraryResources.bind(LibraryResources.ElementError_missing_element, 
					(new String[] {LibraryUtil.getTypeName(e), 
							LibraryUtil.getTypeName(e_ref) }));
			
			ElementError error = new ElementError(
					this, ErrorInfo.WARNING,
					message, e, e_ref, ErrorInfo.REFERENCE_TO); //$NON-NLS-1$
			*/
			ElementError error = ConfigurationErrorMatrix.getError(config, ref);
			if ( error == null ) {
				return;
			}
			
			errors.add(error);
			//notifyError(error, ClosureListener.ERROR_ADDED);
			
			// process package error
			//processPackageError(LibraryUtil.getSelectable(e), LibraryUtil.getSelectable(e_ref), error.getErrorLevel() );
		}	
	}
	
	private void processPackageError(Object pkg, Object pkg_ref, int errorLevel ) {
		if ( errorLevel > 0 ) {
			ElementDependencyError error = getError(pkg, true);
			
			PackageError pkgerror = error.getError(pkg_ref);
			if ( pkgerror == null ) {
				String message = LibraryResources.configClosureWarning_msg1;
	
				pkgerror =  new PackageError(errorLevel, message, 
						pkg, pkg_ref, ErrorInfo.REFERENCE_TO);				
				error.addError(pkgerror);
				
			} else {
				pkgerror.setErrorLevel(errorLevel);
				
				// need to recalc the error bits
				error.calculateErrorBits();
			}
		} else {
			// remove the error
			removeError(pkg);
		}
		
		updateParentsForErrors((EObject)pkg);

	}
	
	private void notifyError(ElementError error, int type ) {
		
		if ( error == null || listeners == null || listeners.size() == 0 ) {
			return;
		}
		
		try {
							
			for (Iterator<ClosureListener> iter = listeners.iterator();iter.hasNext();) {
				ClosureListener listener = iter.next();
				
				listener.fireEvent(type, config, error);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
//	/**
//	 * Validates the element dependency when the element is selected. When the
//	 * element is selected, we need to do the following: 1. Check error for
//	 * references 2. Remove error for dependents associated with element 3.
//	 * Update parents: if the selection is valid, remove all errors from parents
//	 * associated with this element if the selection is invalid, set error to
//	 * all parents.
//	 * 
//	 * @param dependency
//	 *            ElementDependency
//	 */
//	private void validateSelected(PackageDependency dependency) {
//		Object changedElement = dependency.getElement();
//
//		// Since this element is selected, remove all previous errors.
//		removeError(changedElement);
//
//		if (changedElement instanceof MethodLibrary) {
//			return;
//		}
//
//		// Since this element is selected, check all dependency errors in the
//		// dependent elements.
//		List dependents = dependency.getDependents();
//		if (dependents != null && dependents.size() > 0) {
//			Object element;
//			for (Iterator itr = dependents.iterator(); itr.hasNext();) {
//				element = itr.next();
//
//				ElementDependencyError error = getError(element, false);
//				if (error != null) {
//					PackageError pkgerror = error.removeError(changedElement);
//					if (error.size() == 0) {
//						removeError(element);
//
//						// Clear the parent error introduced by this element.
//						updateParentsForErrors((EObject) element);
//					}
//					
//					// notify the error listener about the error removed
//					notifyErrorRemoved(pkgerror);
//
//				}
//			}
//		}
//
//		// If an element is checked, check the element it depends on,
//		// which should be checked as well.
//		List refs = dependency.getReferences();
//		if (refs != null && refs.size() > 0) {
//			PackageReference ref;
//			Object element;
//			for (Iterator itr = refs.iterator(); itr.hasNext();) {
//				ref = (PackageReference) itr.next();
//				element = ref.getRefElement();
//
//				if (element instanceof MethodLibrary) {
//					continue;
//				}
//
//				// Don't warn on optional inputs not being
//				// present, so added the canIgnore() method
//				if (!isSelected(element) && !canIgnore(ref)) {
//					String message;
//					int errorType = 0;
//					if (ref.hasBaseReference()) {
//						errorType = ErrorInfo.ERROR;
//						message = LibraryResources.configClosureWarning_msg2;
//					} else {
//						errorType = ErrorInfo.WARNING;
//						message = LibraryResources.configClosureWarning_msg3;
//					}
//
//					ElementDependencyError error = getError(changedElement,
//							true);
//					
//					PackageError pkgerror =  new PackageError(this, errorType, message,
//							changedElement, element, ErrorInfo.REFERENCE_TO);				
//					error.addError(pkgerror);
//
//					// notify the error listener about the error added
//					notifyErrorAdded(pkgerror);
//
//				} else {
//					ElementDependencyError error = getError(changedElement,
//							false);
//					if (error != null) {
//						PackageError pkgerror = error.removeError(element);
//						
//						// notify the error listener about the error removed
//						notifyErrorRemoved(pkgerror);
//
//					}
//				}
//			}
//		}
//
//		// Finally, update the parents.
//		updateParentsForErrors((EObject) changedElement);
//	}
//
//	/**
//	 * Validates the ElementDependency when the element is unselected. When the
//	 * element is unselected, we need to do the following: 1. check error for
//	 * dependencts 2. remove error for references associated with element, in
//	 * case of any added when the element was check 3. update parents: if the
//	 * selection is valid, remove all errors from parents associated with this
//	 * element if the selection is invalid, set error to all parents.
//	 * 
//	 * @param dependency
//	 *            A <code>ElementDependency</code> object.
//	 */
//	private void validateUnSelected(PackageDependency dependency) {
//		Object changedElement = dependency.getElement();
//
//		// Since this element is un-selected, remove all previous errors.
//		removeError(changedElement);
//
//		if (changedElement instanceof MethodLibrary) {
//			return;
//		}
//
//		// Since this element is un-selected, remove all errors in the
//		// referenced elements.
//		List refs = dependency.getReferences();
//		if (refs != null && refs.size() > 0) {
//			ElementReference ref;
//			Object element;
//			for (Iterator itr = refs.iterator(); itr.hasNext();) {
//				ref = (ElementReference) itr.next();
//				element = ref.getRefElement();
//
//				ElementDependencyError error = getError(element, false);
//				if (error != null) {
//					PackageError pkgerror = error.removeError(changedElement);
//					if (error.size() == 0) {
//						removeError(element);
//
//						// Clear the parent error introduced by this element.
//						updateParentsForErrors((EObject) element);
//
//					}
//					
//					// notify the error listener about the error removed
//					notifyErrorRemoved(pkgerror);
//
//				}
//			}
//		}
//
//		// If an element is unchecked, check the dependent elements.
//		// If there are check elements depending on it, the element can't be
//		// unchecked.
//		List dependents = dependency.getDependents();
//		if (dependents != null && dependents.size() > 0) {
//			Object element;
//			for (Iterator itr = dependents.iterator(); itr.hasNext();) {
//				element = itr.next();
//
//				if (element instanceof MethodLibrary) {
//					continue;
//				}
//
//				if (isSelected(element)) {
//					// Determine the type of dependency.
//					PackageDependency childDep = dependencyManager
//							.getDependency((MethodElement) element);
//
//					validateSelected(childDep);
//				} else {
//					removeError(changedElement);
//				}
//			}
//		}
//
//		// finally, update the parents
//		updateParentsForErrors((EObject) changedElement);
//	}
//
	private void updateParentError(EObject parent, EObject element,
			int errorType) {
		if (parent == null || (parent instanceof MethodLibrary)) {
			return;
		}

//		if ((parent instanceof MethodPackage)
//				&& ConfigurationHelper.isGlobalPackage((MethodPackage) parent)) {
//			updateParentError(parent.eContainer(), element, errorType);
//			return;
//		}

		// Remove the error associated with this element from all parents.
		ElementDependencyError error = getError(parent, false);
		if (error != null && error.size() > 0) {
			error.removeError(element);
		}

		if (errorType != ErrorInfo.NONE) {
			// Propegate the error to all parents.
			error = getError(parent, true);
			String message = LibraryResources.configClosureWarning_msg1;
			PackageError pkgerr = new PackageError(errorType, message, parent, element,
					ErrorInfo.NONE);
			error.addError(pkgerr);
		} else if ((error != null) && (error.size() == 0)) {
			removeError(parent);
		}

		updateParentError(parent.eContainer(), element, errorType);
	}

	private void updateParentsForErrors(EObject element) {
		int errorType = ErrorInfo.NONE;

		ElementDependencyError error = getError(element);
		if (error != null && error.size() > 0) {
			if (error.isError() || error.isChildError()) {
				errorType = PackageError.CHILD_ERROR;
			} else if (error.isWarning() || error.isChildWarning()) {
				errorType = PackageError.CHILD_WARNING;
			}
		}

		updateParentError(element.eContainer(), element, errorType);
	}

	/**
	 * accept the cutrrent selection, fix the errors and ignore any warning message.
	 * 
	 */
	public void fixErrors() {
		boolean changed = true;
		boolean forceCheck = true;
		while (changed) {
			changed = fixProblems(true, forceCheck);
			forceCheck = false;
		}
	}

	/**
	 * fix all error(s) and warnign(s)
	 * 
	 */
	public void fixProblems() {
		boolean changed = true;
		boolean forceCheck = true;
		while (changed) {
			changed = fixProblems(false, forceCheck);
			forceCheck = false;
		}
	}

	private boolean fixProblems(boolean errorOnly, boolean forceCheck) {
		// Note: make closure will select elements as needed.
		// so we need to make a copy of the current selcted ones
		// in order to trace the status
		//List currentSelected = new ArrayList(selectedNotes);

		// if force check is set, run check anyway
		boolean changed = forceCheck;

		// list of errorInfo objects
		List errors = getAllPackageErrors();
		if (errors.size() > 0) {			
			invalidNodesMap.clear();
			PackageError error;
			EObject causeElement;
			//boolean ownerSelected, causeSelected;
			for (Iterator it = errors.iterator(); it.hasNext();) {
				error = (PackageError) it.next();

				//ownerElement = (EObject) error.getOwnerElement();
				causeElement = (EObject) error.getCauseElement();
				//addChanged(ownerElement);
				//addChanged(causeElement);
//				if (error.isChildError() || error.isChildWarning()) {
//					continue;
//				}

				boolean isError = error.isError();
				boolean isWarning = error.isWarning();
				if (!isError && !isWarning || !isError && errorOnly) {
					continue;
				}
			
				if ( !isSelected(causeElement) && selectErrorElement(causeElement) ) {
					changed = true;
				}
			}
		}

		if ( changed ) {
			checkError();
		}
		
		return changed;
	}
	
	private boolean selectErrorElement(EObject element) {
		boolean ret = false;		
		boolean oldNotify = config.eDeliver();
		config.eSetDeliver(false);
		try {
			ret = selectErrorElement_(element);
		} finally {
			config.eSetDeliver(oldNotify);
		}
		return ret;
	}
	
	private boolean selectErrorElement_(EObject element) {
		
		boolean selected = true;
		List toAdd = Collections.singletonList(element);
		if (element instanceof MethodPlugin 
				&& !config.getMethodPluginSelection().contains(element)) {
			//config.getMethodPluginSelection().add((MethodPlugin) element);
			ConfigurationUtil.addCollToMethodPluginList(actionMgr, config, toAdd);
			config.eResource().setModified(true);
		} else if ( element instanceof MethodPackage 
				&& !config.getMethodPackageSelection().contains(element)){
			//config.getMethodPackageSelection().add((MethodPackage) element);
			ConfigurationUtil.addCollToMethodPackageList(actionMgr, config, toAdd);
			config.eResource().setModified(true);
		} else {
			selected = false;
		}
		
		return selected;
	}

//	/**
//	 * update the method configuration in the library with the current selections
//	 */
//	public void saveMethodConfiguration() {
//		List plugins = config.getMethodPluginSelection();
//		List packages = config.getMethodPackageSelection();
//
//		plugins.clear();
//		packages.clear();
//
//		EObject element;
//		for (Iterator it = selectedNotes.iterator(); it.hasNext();) {
//			element = (EObject) it.next();
//			if (element instanceof MethodPlugin) {
//				if (!plugins.contains(element)) {
//					plugins.add(element);
//				}
//			} else if ((element instanceof MethodPackage)
//					&& !ConfigurationHelper
//							.isGlobalPackage((MethodPackage) element)) {
//				if (!packages.contains(element)) {
//					packages.add(element);
//				}
//			}
//		}
//	}

	/**
	 * Packages the library based on the selection.
	 * <p>
	 * Note: This will change the current library. Before calling this method, a
	 * copy of the current library should be created with the following steps:
	 * 1. Create a new <code>ConfigurationManager</code> with a copy of the
	 * original library, 2. Rebuild the dependency, 3. Create a
	 * <code>ConfigurationClosure</code> with the current configuration.
	 * 
	 * @return A <code>MethodLibrary</code>.
	 */
	public MethodLibrary packageLibrary(boolean removeBrokenReferences) {
		processSelection(library, removeBrokenReferences);

		// Remove the configurations except for the current one.
		List configs = library.getPredefinedConfigurations();
		configs.clear();
		configs.add(config);

		return library;
	}

	/**
	 * process the selected package by removeing all unselected elements and any
	 * missing references
	 * 
	 * @param element
	 */
	private void processSelection(EObject element,
			boolean removeBrokenReferences) {
		if (removeBrokenReferences) {
			// Iterator the references and remove broken references.
			EList references = element.eCrossReferences();
			if (references != null) {
				for (Iterator it = new ArrayList(references).iterator(); it
						.hasNext();) {
					EObject ref = (EObject) it.next();
					EObject pkgRef = LibraryUtil.getSelectable(ref);
					if (pkgRef != null && !isSelected(pkgRef)) {
						removeReference(element, ref);
					}
				}
			}
		}

		EList elements = element.eContents();
		if (elements != null) {
			for (Iterator it = new ArrayList(elements).iterator(); it.hasNext();) {
				EObject child = (EObject) it.next();

				// If the child element is selectable but it is not in the
				// configuration, remove it.
				if (LibraryUtil.selectable(child) && !isSelected(child)) {
					EcoreUtil.remove(child);
				} else {
					processSelection(child, removeBrokenReferences);
				}
			}
		}
	}

	private void removeReference(EObject ownerElement, EObject refElement) {
		AdapterFactoryContentProvider provider = configManager
				.getContentProvider();
		IPropertySource ps = provider.getPropertySource(ownerElement);
		IPropertyDescriptor[] pds = ps.getPropertyDescriptors();
		if (pds != null && pds.length > 0) {
			for (int i = 0; i < pds.length; i++) {
				IPropertyDescriptor descriptor = (IPropertyDescriptor) pds[i];
				Object id = descriptor.getId();
				Object value = ps.getPropertyValue(id);

				// Check whether the value needs to be converted to an editable
				// value.
				IPropertySource source = provider.getPropertySource(value);
				if (source != null) {
					value = source.getEditableValue();
				}
				if (value instanceof EList) {
					EList refList = (EList) value;
					if (refList.contains(refElement)) {
						if (debug) {
							System.out
									.println("Reference [" + LibraryUtil.getName(refElement) //$NON-NLS-1$
											+ "] removed from [" //$NON-NLS-1$
											+ LibraryUtil.getName(ownerElement)
											+ "]'s reference list"); //$NON-NLS-1$
						}
						refList.remove(refElement);
						ps.setPropertyValue(id, refList);
					}
				} else if (value instanceof MethodElement) {
					if (debug) {
						System.out
								.println("Reference [" + LibraryUtil.getName(refElement) //$NON-NLS-1$
										+ "] removed from [" //$NON-NLS-1$
										+ LibraryUtil.getName(ownerElement)
										+ "]"); //$NON-NLS-1$
					}
					ps.setPropertyValue(id, null);
				}

			}
		}
	}

	/**
	 * Disposes resources allocated by this closure.
	 */
	public void dispose() {
		replacerMap = null;
		
		clearErrorMarks(false);
		
		if (configManager != null) {
			ConfigurationProperties props = configManager.getConfigurationProperties();
			if (props != null) {
				props.removeListener(configPropListener);
			}
		}
				
		configManager = null;
		config = null;
		library = null;
		if (dependencyManager != null) {
			dependencyManager.dispose();
		}
		dependencyManager = null;
		actionMgr = null;
		//selectedNotes.clear();
		//changedNodes.clear();
		if (invalidNodesMap != null) {
			invalidNodesMap.clear();
		}
		if (listeners != null) {
			listeners.clear();
		}
		clearReplacerElementMarkerMap();
	}

	private void clearReplacerElementMarkerMap() {
		if (replacerElementMarkerMap != null) {
			for (IMarker marker: replacerElementMarkerMap.values()) {
				try {
					marker.delete();
				} catch (Exception e) {
					LibraryPlugin.getDefault().getLogger().logError(e);
				}
			}
		}		
		replacerElementMarkerMap = null;
	}
	
//	private boolean canIgnore(PackageReference pkgRef) {
//		return pkgRef.canIgnore();
//	}

	public void addListener(ClosureListener listener) {
		if (listeners == null) {
			listeners = new ArrayList<ClosureListener>();
		}
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
		
		notifyListener(listener, this.errors);
	}
	
	public void removeListener(ClosureListener listener) {
		if (listeners != null) {
			if (listeners.contains(listener)) {
				listeners.remove(listener);
			}
		}
	}
	
//	private void notifyErrorAdded(PackageError pkgerror) {
//			//notifyListeners(ClosureListener.ERROR_ADDED, pkgerror);
//	}
//	
//	private void notifyErrorRemoved(PackageError pkgerror) {
//		//notifyListeners(ClosureListener.ERROR_REMOVED, pkgerror);	
//	}
	
	private void notifyListener(ClosureListener listener, List errors) {
									
		for ( Iterator it = errors.iterator(); it.hasNext(); ) {
			ElementError error = (ElementError)it.next();
			listener.fireEvent(ClosureListener.ERROR_ADDED, config, error);
		}
	}

	public synchronized boolean isAbortCheckError() {
		return abortCheckError;
	}

	public synchronized void setAbortCheckError(boolean abortCheckError) {
		this.abortCheckError = abortCheckError;
		if (ConfigDataBase.localDebug) {
			System.out.println("LD> setAbortCheckError: " + abortCheckError);//$NON-NLS-1$
		}
	}
	
//	/**
//	 * add this element into the configuration
//	 * this is for quick fix action
//	 * our default implementation is to select the owning package
//	 * @param element
//	 * @return IStatus
//	 */
//	public IStatus selectElement(MethodElement element) {
//		
//		//if the element is subtracted, we can't select the element
//		// return an error
//		if ( configManager.getConfigurationData().isElementInSubtractedCategory(element)) {
//			String message = LibraryResources.bind(LibraryResources.QuickfixError_reason1, 
//					(new String[] {LibraryUtil.getTypeName(element)}));
//			return new Status(IStatus.ERROR,
//					LibraryPlugin.getDefault().getId(), IStatus.OK, message, null);
//		}
//		
//		// for quick fix, we just select the package of the element 
//		// and the child packages
//		Object owner = LibraryUtil.getSelectable(element);
//		if ( owner != null && owner instanceof MethodPackage) {
//			config.getMethodPackageSelection().add(owner);
//		}	
//		
//		return Status.OK_STATUS;
//	}
	
	public static class ProcessNodeLock {
		private Thread lockingThread;

		public synchronized Thread getLockingThread() {
			return lockingThread;
		}

		public synchronized void setLockingThread(Thread lockingThread) {
			this.lockingThread = lockingThread;
		}
		
		
		
	}
	
}
