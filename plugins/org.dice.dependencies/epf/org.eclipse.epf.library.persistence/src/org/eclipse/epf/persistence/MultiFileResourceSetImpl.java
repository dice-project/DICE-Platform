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
package org.eclipse.epf.persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLInfoImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLMapImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.persistence.LibraryResourceException;
import org.eclipse.epf.persistence.refresh.IRefreshEvent;
import org.eclipse.epf.persistence.refresh.IRefreshListener;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.persistence.refresh.internal.RefreshEvent;
import org.eclipse.epf.persistence.util.PersistenceResources;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.persistence.util.UnresolvedProxyMarkerManager;
import org.eclipse.epf.resourcemanager.ResourceDescriptor;
import org.eclipse.epf.resourcemanager.ResourceManager;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.Diagram;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.SemanticModelBridge;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UMASemanticModelBridge;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkOrder;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.IProxyResolutionListener;
import org.eclipse.epf.uma.ecore.IUmaResourceSet;
import org.eclipse.epf.uma.ecore.ResolveException;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.util.EventTypes;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.ContentDescriptionFactory;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MultiFileResourceSetImpl extends ResourceSetImpl implements
		IProxyResolutionListener, IUmaResourceSet, ILibraryResourceSet {
	public static final String SKIP_RETRY_PROXY_RESOLUTION = "SKIP_RETRY_PROXY_RESOLUTION"; //$NON-NLS-1$

	private static final String[] DEFAULT_DELIVERY_PROCESS_PATH = { "DeliveryProcesses" }; //$NON-NLS-1$

	private static final String[] DEFAULT_CAPABILITY_PATTERN_PATH = {
			"Content", "CapabilityPatterns" }; //$NON-NLS-1$ //$NON-NLS-2$

	private static final String[] DEFAULT_PROCESS_CONTRIBUTION_PATH = { "ProcessContributions" }; //$NON-NLS-1$		

	public static final String[][] PROCESS_PACKAGE_PATHS = {
			DEFAULT_DELIVERY_PROCESS_PATH, DEFAULT_CAPABILITY_PATTERN_PATH,
			DEFAULT_PROCESS_CONTRIBUTION_PATH };

	public static boolean REPORT_ERROR = true;

	static final Map<Object, Object> DEFAULT_SAVE_OPTIONS = new HashMap<Object, Object>();

	static final Set DEFAULT_SAVE_SEPARATELY_CLASS_SET = new HashSet();

	static {
		DEFAULT_SAVE_SEPARATELY_CLASS_SET.add(UmaPackage.eINSTANCE
				.getMethodPlugin());
		DEFAULT_SAVE_SEPARATELY_CLASS_SET.add(UmaPackage.eINSTANCE
				.getProcessComponent());
		DEFAULT_SAVE_SEPARATELY_CLASS_SET.add(UmaPackage.eINSTANCE
				.getContentDescription());
		// eClasses.add(ResourcemanagerPackage.eINSTANCE.getResourceManager());
		DEFAULT_SAVE_SEPARATELY_CLASS_SET.add(UmaPackage.eINSTANCE
				.getMethodConfiguration());

		XMLResource.XMLMap xmlMap = createSaveXMLMap();

		HashSet saveTogether = new HashSet();
		saveTogether.add(UmaPackage.eINSTANCE.getBreakdownElementDescription());

		DEFAULT_SAVE_OPTIONS.put(
				MultiFileXMISaveImpl.SAVE_SEPARATELY_CLASS_SET,
				DEFAULT_SAVE_SEPARATELY_CLASS_SET);
		DEFAULT_SAVE_OPTIONS.put(MultiFileXMISaveImpl.SAVE_ALL, "false"); //$NON-NLS-1$
		DEFAULT_SAVE_OPTIONS.put(MultiFileXMISaveImpl.SAVE_TOGETHER_CLASS_SET,
				saveTogether);
		DEFAULT_SAVE_OPTIONS.put(MultiFileXMISaveImpl.REFRESH_NEW_RESOURCE,
				"true"); //$NON-NLS-1$
		DEFAULT_SAVE_OPTIONS.put(MultiFileXMISaveImpl.CHECK_MODIFY, "true"); //$NON-NLS-1$
		DEFAULT_SAVE_OPTIONS.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
		DEFAULT_SAVE_OPTIONS.put(XMLResource.OPTION_PROCESS_DANGLING_HREF,
				XMLResource.OPTION_PROCESS_DANGLING_HREF_DISCARD);
		DEFAULT_SAVE_OPTIONS.put(XMLResource.OPTION_XML_MAP, xmlMap);
		DEFAULT_SAVE_OPTIONS.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);

		AssociationHelper.init();
	}

	public static final String RESMGR_XMI = "resmgr.xmi"; //$NON-NLS-1$

	private HashMap<Object, Object> defaultSaveOptions;

	private HashMap<String, EObject> guidToMethodElementMap;

	private boolean loading;

	private boolean loadingResourceManagerTree;

	private Map URIToTempURIMap;

	private UnresolvedProxyMarkerManager markerMananger;

	private UniqueEList<IRefreshListener> refreshListeners;

	protected IURIProvider uriProvider;

	private Path configFolderPath;

	private Path libFolderPath;

	private static XMLResource.XMLMap createLoadXMLMap() {
		XMLResource.XMLMap xmlMap = new XMLMapImpl();
		Set excludedAttributes = new HashSet();
		excludedAttributes.add(UmaPackage.eINSTANCE.getMethodElement_Guid());
		excludedAttributes.add(UmaPackage.eINSTANCE.getNamedElement_Name());

		for (Iterator iter = UmaPackage.eINSTANCE.getEClassifiers().iterator(); iter
				.hasNext();) {
			EClassifier eClassifier = (EClassifier) iter.next();
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;

				for (Iterator iterator = eClass.getEAttributes().iterator(); iterator
						.hasNext();) {
					EAttribute attrib = (EAttribute) iterator.next();
					if (!excludedAttributes.contains(attrib)) {
						XMLResource.XMLInfo xmlInfo = new XMLInfoImpl();
						xmlInfo.setName(attrib.getName());
						xmlInfo
								.setXMLRepresentation(XMLResource.XMLInfo.ELEMENT);
						xmlMap.add(attrib, xmlInfo);
					}
				}
			}
		}

		return xmlMap;
	}

	private static boolean saveAttributeAsElement(EClass eClass,
			Collection selectedEClasses) {
		for (Iterator iter = selectedEClasses.iterator(); iter.hasNext();) {
			EClass base = (EClass) iter.next();
			if (base.isSuperTypeOf(eClass)) {
				return true;
			}
		}
		return false;
	}

	private static XMLResource.XMLMap createSaveXMLMap() {
		XMLResource.XMLMap xmlMap = new XMLMapImpl();
		Set excludedAttributes = new HashSet();
		excludedAttributes.add(UmaPackage.eINSTANCE.getMethodElement_Guid());
		excludedAttributes.add(UmaPackage.eINSTANCE.getNamedElement_Name());

		// select only ContentDescription and Section
		Set selectedEClasses = new HashSet();
		selectedEClasses.add(UmaPackage.eINSTANCE.getContentDescription());
		selectedEClasses.add(UmaPackage.eINSTANCE.getSection());

		for (Iterator iter = UmaPackage.eINSTANCE.getEClassifiers().iterator(); iter
				.hasNext();) {
			EClassifier eClassifier = (EClassifier) iter.next();
			if (eClassifier instanceof EClass) {
				EClass eClass = (EClass) eClassifier;

				if (saveAttributeAsElement(eClass, selectedEClasses)) {
					for (Iterator iterator = eClass.getEAttributes().iterator(); iterator
							.hasNext();) {
						EAttribute attrib = (EAttribute) iterator.next();
						if (!excludedAttributes.contains(attrib)) {
							XMLResource.XMLInfo xmlInfo = new XMLInfoImpl();
							xmlInfo.setName(attrib.getName());
							xmlInfo
									.setXMLRepresentation(XMLResource.XMLInfo.ELEMENT);
							xmlMap.add(attrib, xmlInfo);
						}
					}
				}
			}
		}

		return xmlMap;
	}

	public MultiFileResourceSetImpl() {
		super();
		setURIResourceMap(new HashMap<URI, Resource>());
		markerMananger = new UnresolvedProxyMarkerManager(this);
	}

	public MultiFileResourceSetImpl(boolean reportError) {
		this();
		markerMananger.setEnabled(reportError);
	}

	public UnresolvedProxyMarkerManager getUnresolvedProxyMarkerManager() {
		return markerMananger;		
	}
	
	public IURIProvider getURIProvider() {
		if(uriProvider == null) {
			uriProvider = new FilePathProvider();
		}
		return uriProvider;
	}
	
	private void handleResourceChange(IResource wsRes) {
		System.out
				.println("MultiFileResourceSetImpl.handleResourceChange(): " + wsRes.toString()); //$NON-NLS-1$

		MultiFileXMIResourceImpl resource = (MultiFileXMIResourceImpl) getResource(
				URI.createFileURI(wsRes.getLocation().toString()), false);
		if (resource != null) {
			try {
				ArrayList proxies = new ArrayList();
				resource.reload(proxies);

				// notify MAKE_PROXY event for all proxies
				//
				Notification msg = new NotificationImpl(EventTypes.MAKE_PROXY,
						false, true);
				for (Iterator iter = proxies.iterator(); iter.hasNext();) {
					EObject o = (EObject) iter.next();
					o.eNotify(msg);
				}

			} catch (IOException e) {
				CommonPlugin.INSTANCE.log(e);
			}
		}

	}

	public Map<Object, Object> getLoadOptions() {
		Map<Object, Object> options = super.getLoadOptions();
		options.put(XMLResource.OPTION_ENCODING, "UTF-8"); //$NON-NLS-1$
		options.put(XMLResource.OPTION_XML_MAP, createLoadXMLMap());
		return options;
	}

	public Map<Object, Object> getDefaultSaveOptions() {
		if (defaultSaveOptions == null) {
			defaultSaveOptions = new HashMap<Object, Object>();
			defaultSaveOptions.putAll(DEFAULT_SAVE_OPTIONS);
		}

		return defaultSaveOptions;
	}

	private Resource getMethodLibraryResource() {
		if (getResources().isEmpty())
			return null;
		return (Resource) getResources().get(0);
	}

	public MethodLibrary getMethodLibrary() {
		Resource resource = getMethodLibraryResource();
		return resource == null ? null : (MethodLibrary) PersistenceUtil
				.getMethodElement(resource);
	}

	protected ResourceManager getRootResourceManager() {
		Resource resource = getMethodLibraryResource();
		return resource == null ? null : MultiFileSaveUtil
				.getResourceManager(resource);
	}

	/**
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceSetImpl#getURIConverter()
	 */
	public URIConverter getURIConverter() {
		if (uriConverter == null) {
			uriConverter = new MultiFileURIConverter(this);
		}
		return uriConverter;
	}

	/**
	 * @param uri
	 * @return null if the resource with the given URI could not be found
	 */
	public Resource getResource(URI uri) {
		if (getURIConverter().normalize(uri) == null)
			return null;
		Resource res = null;
		try {
			res = super.getResource(uri, false);
			if (res == null) {
				Map map = getURIResourceMap();
				res = createResource(uri);

				try {
					demandLoadHelper(res);
				} catch (RuntimeException e) {
					// remove the resource from resource set if it could not be
					// loaded
					//
					getResources().remove(res);
					throw e;
				}

				if (map != null) {
					map.put(uri, res);
				}
			}
		} catch (WrappedException e) {
			if (e.exception() instanceof FileNotFoundException) {
				return null;
			}
			throw e;
		}
		return res;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceSetImpl#demandLoad(org.eclipse.emf.ecore.resource.Resource)
	 */
	protected void demandLoad(Resource resource) throws IOException {
		super.demandLoad(resource);

		// refresh workspace resource of this resource to make them synchronized
		//
		boolean ret = FileManager.getInstance().refresh(resource);
		if(MultiFileSaveUtil.DEBUG) {
			if(!ret) {
				System.err.println("MultiFileResourceSetImpl.demandLoad(): could not refresh resource " + resource); //$NON-NLS-1$
			}
		}
	}
	
	/**
	 * Resolves container of the MethodElement in the given resource.
	 * 
	 * @param res
	 */
	public void resolveContainer(Resource res) {
		MethodLibrary lib = getMethodLibrary();
		if(loading && lib == null) {
			return;
		}
		for (Iterator iterator = res.getContents().iterator(); iterator.hasNext();) {
			Object element = (Object) iterator.next();
			if(element instanceof MethodElement) {
				MethodElement e = (MethodElement) element;
				if(e != lib && e.eContainer() == null) {
					// the method element might have been loaded before its container accessed it
					//
					ResourceDescriptor resDesc = MultiFileSaveUtil.getResourceDescriptor(res);
					if(resDesc != null) {
						Resource containerResource = resDesc.eResource();
						if(containerResource instanceof XMLResourceImpl) {
							Object proxy = ((XMLResourceImpl)containerResource).getIDToEObjectMap().get(e.getGuid());
							if(proxy instanceof EObject) {
								EObject proxyEObject = ((EObject)proxy); 
								EReference ref = proxyEObject.eContainmentFeature();
								EObject container = proxyEObject.eContainer();
								if(container != null) {
									if (ref.isMany()) {
										List values = (List) container.eGet(ref);
										for (Iterator iter = values.iterator(); iter.hasNext(); iter
										.next())
											;
									} else {
										container.eGet(ref);
									}
									resolveContainer(containerResource);
								}
							}
						}
					}
				}
			}			
		}
	}
	
	private Set<URI> errorUriSet;
	private void handleLoadResourceException(URI uri, RuntimeException e) {
		if (errorUriSet == null) {
			errorUriSet = new HashSet<URI>();
		}
		if (errorUriSet.contains(uri)) {
			return;
		}
		errorUriSet.add(uri);
		String msg = null;
		if (e.getMessage() != null) {
			msg = NLS.bind(
					PersistenceResources.loadResourceErrorWithReason_msg,
					(new Object[] { uri, e.getMessage() }));
		}
		else {
			msg = NLS.bind(PersistenceResources.loadResourceError_msg, uri);
			PersistencePlugin.getDefault().getLogger().logError(e);
		}
		handleException(msg);
		throw e;

	}

	/**
	 * @see org.eclipse.emf.ecore.resource.impl.ResourceSetImpl#getResource(org.eclipse.emf.common.util.URI,
	 *      boolean)
	 */
	public Resource getResource(URI uri, boolean loadOnDemand) {
		if (getURIConverter().normalize(uri) == null)
			return null;
		Resource res = null;
		try {
			res = super.getResource(uri, loadOnDemand);
		} catch (RuntimeException e) {
			// Somehow the resource list of the resource set has null value that
			// will cause NullPointerException in the following call. Remove the null resource and retry here.
			// TODO: need to find out the cause of this and fix it.
			if(e instanceof NullPointerException) {
				boolean hasNull = false;
				for (Iterator<Resource> iterator = getResources().iterator(); iterator
						.hasNext();) {
					Resource resource = iterator.next();
					if(resource == null) {
						iterator.remove();
						hasNull = true;
					}
				}
				if(hasNull) {
					try {
						return super.getResource(uri, loadOnDemand);
					} catch(RuntimeException re) {
						handleLoadResourceException(uri, re);
					}
				}
			}
			
			handleLoadResourceException(uri, e);
		}
		return res;
	}

	public void addRefreshListener(IRefreshListener listener) {
		if (refreshListeners == null) {
			refreshListeners = new UniqueEList<IRefreshListener>();
		}
		refreshListeners.add(listener);
	}

	public void removeRefreshListenter(IRefreshListener listener) {
		if (refreshListeners != null) {
			refreshListeners.remove(listener);
		}
	}
	
	public void notifyRefreshListeners(IRefreshEvent event) {
		if(refreshListeners != null && !refreshListeners.isEmpty()) {
			for (IRefreshListener listener : refreshListeners) {
				try {
					listener.notifyRefreshed(event);
				} catch (Exception e) {
					PersistencePlugin.getDefault().getLogger().logError(e);
				}
			}
		}
	}

	/**
	 * Reloads the given resources
	 * 
	 * @param resources
	 * @return resources that had been reloaded
	 */
	public synchronized Collection reloadResources(Collection resources) {		
		// save the ResourceDescriptor for the resources to create URI for their
		// proxies after unload
		//
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			MultiFileXMIResourceImpl resource = (MultiFileXMIResourceImpl) iter
					.next();
			
			if(MultiFileSaveUtil.DEBUG) {
				System.out
						.println("MultiFileResourceSetImpl.reloadResources(): resource=" + resource + ", element=" + PersistenceUtil.getMethodElement(resource)); //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			ResourceDescriptor resDesc = MultiFileSaveUtil
					.getResourceDescriptor(resource);
			if (resDesc != null) {
				resource.setResourceDescriptor(resDesc);
			}
		}

		ArrayList reloadedResources = new ArrayList();
		HashSet proxies = new HashSet();
		ArrayList resourceList = new ArrayList(resources);

		Resource libResource = getMethodLibraryResource();
		boolean reloadAllConfigs = false;
		if (resourceList.remove(libResource)) {
			// MethodLibrary resource is to reload, it must be reloaded first
			//			
			MultiFileXMIResourceImpl resource = (MultiFileXMIResourceImpl) libResource;

			// clear markers for this resource
			//
			markerMananger.clearMarkers(resource);

			try {
				if (resource.reload(proxies)) {
					reloadedResources.add(resource);

					// reset the resource manager reference in URIConverter of
					// this resource set
					// since the resource manager is reloaded
					//
					MultiFileURIConverter uriConverter = (MultiFileURIConverter) getURIConverter();
					uriConverter.resMgr = null;
					
					reloadAllConfigs = true;
				}
			} catch (IOException e) {
				CommonPlugin.INSTANCE.log(e);
			}

		}
		// reload the resources
		//
		for (Iterator iter = resourceList.iterator(); iter.hasNext();) {
			MultiFileXMIResourceImpl resource = (MultiFileXMIResourceImpl) iter
					.next();

			// clear markers for this resource
			//
			markerMananger.clearMarkers(resource);

			try {
				if (resource.reload(proxies)) {
					reloadedResources.add(resource);
				}
			} catch (IOException e) {
				CommonPlugin.INSTANCE.log(e);
			}
		}

		if (!reloadedResources.isEmpty()) {
			// reinitialize the resource set
			//
			initialize(reloadAllConfigs);

			// notify the listeners
			//
			if (refreshListeners != null) {
				IRefreshEvent event = new RefreshEvent(reloadedResources,
						proxies);
				for (Iterator iter = new ArrayList(refreshListeners).iterator(); iter
						.hasNext();) {
					IRefreshListener listener = (IRefreshListener) iter.next();
					listener.notifyRefreshed(event);
				}
			}
		}

		return reloadedResources;
	}

	public void initialize(boolean loadConfigurations) {
		// load the ResourceManager tree and opposite features
		//
		loadResourceManagerTree();

		setContainersOfLoadedElements();
		
		if(loadConfigurations) {
			loadConfigurations();
		}

		loadOppositeFeatures();

		// reset the resource manager reference in URIConverter of this resource
		// set
		//
		MultiFileURIConverter uriConverter = (MultiFileURIConverter) getURIConverter();
		uriConverter.resMgr = null;
	}
	
	private void setContainersOfLoadedElements() {
		ResourceManager resMgr = getRootResourceManager();
		Iterator iterator = new AbstractTreeIterator(resMgr, false) {

			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 2172691017987702506L;

			protected Iterator getChildren(Object object) {
				ResourceManager resMgr = (ResourceManager) object;
				Collection GUIDs = new HashSet();
				List subMgrs = new ArrayList(resMgr.getSubManagers());
				for (Iterator iter = subMgrs.iterator(); iter.hasNext();) {
					try {
						InternalEObject subMgr = (InternalEObject) iter
								.next();
						if (subMgr != null && !subMgr.eIsProxy()) {
							MethodElement e = PersistenceUtil
									.getMethodElement(subMgr.eResource());
							GUIDs.add(e.getGuid());
						}
					} catch (RuntimeException e) {
						throw e;
					}
				}
				Resource resource = resMgr.eResource();
				if(resource != null) {
					MethodElement parent = PersistenceUtil
							.getMethodElement(resource);

					// resolve the proxy of this element in the parent
					//
					MultiFileSaveUtil.resolveProxies(GUIDs, parent);
				}

				return resMgr.getSubManagers().iterator();
			}

		};
		for (; iterator.hasNext(); iterator.next())
			;
	}

	/**
	 * Checks if the given workspace resource represents a library resource
	 * 
	 * @param wsResource
	 * @return
	 */
	public boolean isLibraryResource(IResource wsResource) {
		return libFolderPath != null && libFolderPath.isPrefixOf(wsResource.getLocation());
	}
	
	/**
	 * Checks if the given workspace resource is a new resource that can be loaded 
	 * using {@link #loadNewResources(Collection)}
	 *  
	 * @return
	 */
	public boolean isNewResourceToLoad(IResource wsResource) {
		IPath loc = wsResource.getLocation();
		return (loc != null && configFolderPath != null 
				&& MultiFileSaveUtil.DEFAULT_FILE_EXTENSION.equalsIgnoreCase("." + loc.getFileExtension()) //$NON-NLS-1$
				&& configFolderPath.isPrefixOf(loc)
				&& !FileManager.getInstance().isTeamPrivate(loc.toString()));

	}
	
	/**
	 * Loads new resources specified by the given collection of {@link IResource work space resources}
	 * into this resource set
	 * TODO: merge this code to {@link #getResource(URI, boolean)}
	 * @param wsResources collection of {@link IResource} objects
	 */
	public void loadNewResources(Collection<IResource> wsResources) {
		ArrayList newConfigFiles = new ArrayList();
		for (Iterator iter = wsResources.iterator(); iter.hasNext();) {
			Object wsResource = (Object) iter.next();
			if(wsResource instanceof IResource) {
				IResource wsRes = (IResource) wsResource;
				IPath loc = wsRes.getLocation();
				String configFile;
				if(loc != null && configFolderPath != null 
						&& MultiFileSaveUtil.DEFAULT_FILE_EXTENSION.equalsIgnoreCase("." + loc.getFileExtension()) //$NON-NLS-1$
						&& configFolderPath.isPrefixOf(loc)
						&& !FileManager.getInstance().isTeamPrivate(configFile = loc.toString())) {
					newConfigFiles.add(configFile);
				}
			}
		}
		
		// load new configurations
		//
		for (Iterator iter = newConfigFiles.iterator(); iter.hasNext();) {
			String configFile = (String) iter.next();
			loadConfiguration(new File(configFile));
		}
	}

	/**
	 * Special handling for MethodConfiguration since MethodLibrary will no
	 * longer keeps the references to MethodConfigurations and their files. Any
	 * XMI file under folder ./configurations and its subfolders will be treated
	 * as a configuration file. They will be loaded and added to MethodLibrary
	 * as its configurations.
	 * @return TODO
	 */
	protected Collection<Resource> loadConfigurations() {
		MethodLibrary lib = getMethodLibrary();
		File configDir = new File(new File(lib.eResource().getURI()
				.toFileString()).getParent(),
				MultiFileSaveUtil.METHOD_CONFIGURATION_FOLDER_NAME);
		return loadConfigurations(configDir);
	}
	
	/**
	 * Loads method configurations from the specified directory.
	 * 
	 * @param configDir
	 * @return TODO
	 */
	protected Collection<Resource> loadConfigurations(File configDir) {
		ArrayList<Resource> resources = new ArrayList<Resource>();
		Iterator dirIter = MultiFileSaveUtil.listConfigFiles(configDir);
		HashSet<File> configFiles = new HashSet<File>();
		while (dirIter.hasNext()) {
			File configFile = (File) dirIter.next();
			if(configFile.isFile()) {
				configFiles.add(configFile);
			}
		}
		MethodLibrary lib = getMethodLibrary();
		for (Iterator iter = lib.getPredefinedConfigurations().iterator(); iter
				.hasNext();) {
			MethodConfiguration config = (MethodConfiguration) iter.next();
			File file = new File(config.eResource().getURI().toFileString());
			if (configFiles.contains(file)) {
				configFiles.remove(file);
			}
		}

		for (Iterator iter = configFiles.iterator(); iter.hasNext();) {
			File file = (File) iter.next();
			Resource resource = loadConfiguration(file);
			if(resource != null) {
				resources.add(resource);
			}
		}
		return resources;
	}

	protected Resource loadConfiguration(File configFile) {
		try {
			MethodLibrary lib = getMethodLibrary();
			URI uri = URI.createFileURI(configFile.getCanonicalPath());
			Resource resource = super.getResource(uri, true);
			if (resource != null) {
				MethodElement e = PersistenceUtil
						.getMethodElement(resource);
				if (e instanceof MethodConfiguration) {
					if(PersistenceUtil.hasDuplicateGUID(e, lib.getPredefinedConfigurations())) {
						PersistencePlugin.getDefault().getLogger().logError(
								NLS.bind(PersistenceResources.loadConfiguration_couldNotLoad_logMsg, configFile));
					}
					else {
						lib.getPredefinedConfigurations().add((MethodConfiguration) e);
						return resource;
					}
				} else {
					PersistencePlugin.getDefault().getLogger().logError(
							NLS.bind(PersistenceResources.loadConfiguration_notConfigFile_logMsg, configFile)
							);
				}
				resource.unload();
				getResources().remove(resource);
			}
		} catch (Exception e) {
			PersistencePlugin.getDefault().getLogger().logError(e);
		}
		return null;
	}

	/**
	 * @param string
	 */
	protected void handleException(Object err) {
		if (err instanceof Throwable) {
			PersistencePlugin.getDefault().getLogger().logError((Exception)err);
		} else {
			PersistencePlugin.getDefault().getLogger().logError(err.toString());
		}
		if(MultiFileSaveUtil.DEBUG) {
			if (err instanceof Throwable) {
				((Exception) err).printStackTrace();
			} else {
				System.err.println(err.toString());
			}
		}
	}

	public void reset() {
		if (uriConverter instanceof MultiFileURIConverter) {
			((MultiFileURIConverter) uriConverter).dispose();
		}
		errorUriSet = null;
		uriConverter = null;
		boolean notify = eDeliver();
		boolean reportError = REPORT_ERROR;
		boolean refresh = RefreshJob.getInstance().isEnabled();
		boolean oldMarkerManagerEnabled = markerMananger.isEnabled();

		try {
			if(RefreshJob.getInstance().getResourceSet() == this) {
				RefreshJob.getInstance().setEnabled(false);
				RefreshJob.getInstance().reset();
			}
			eSetDeliver(false);
			REPORT_ERROR = false;
			markerMananger.setEnabled(false);
			markerMananger.clearAll();

			for (Iterator iter = new ArrayList(getResources()).iterator(); iter
					.hasNext();) {
				Resource resource = (Resource) iter.next();
				try {
					resource.unload();
				} catch (Exception e) {
					// System.err.println("Error unloading resource: " +
					// resource.getURI());
					// e.printStackTrace();
				}
			}

			clearErrors();
		} finally {
			eSetDeliver(notify);
			REPORT_ERROR = reportError;
			markerMananger.setEnabled(oldMarkerManagerEnabled);
			if(RefreshJob.getInstance().getResourceSet() == this) {
				RefreshJob.getInstance().setEnabled(refresh);
			}
		}

		if (guidToMethodElementMap != null) {
			guidToMethodElementMap.clear();
		}

		getResources().clear();

		if (URIToTempURIMap != null) {
			URIToTempURIMap.clear();
		}
		
		Map<URI, Resource> map = getURIResourceMap();
		if (map != null) {
			map.clear();
		}

		MultiFileXMIResourceImpl.clearDetachedEObjectToIDMap();
	}

	private void clearErrors() {
		// clear all markers
		markerMananger.clearAll();
	}

	public MethodLibrary loadLibrary(String path) throws Exception {
		long begin = System.currentTimeMillis();

		reset();

		long afterReset = System.currentTimeMillis();
		
		if (MultiFileSaveUtil.DEBUG) {
			System.out.println("Reset time: " + (afterReset - begin)); //$NON-NLS-1$
		}

		MethodLibrary lib = loadLibraryWithoutReset(path);

		if (MultiFileSaveUtil.DEBUG) {
			System.out
					.println("Load time: " + (System.currentTimeMillis() - begin)); //$NON-NLS-1$
		}

		if (MultiFileSaveUtil.DEBUG) {
			// check if any resource is marked as modified after loading
			//
			System.err.println("Modified resources after loading:"); //$NON-NLS-1$
			for (Iterator iter = getResources().iterator(); iter.hasNext();) {
				Resource resource = (Resource) iter.next();
				if (resource.isModified()) {
					System.err.println("  " + resource.getURI()); //$NON-NLS-1$
				}
			}
		}

		return lib;
	}
	
	protected Resource loadLibraryResource(String path) {
		URI uri = URI.createFileURI(path);
		Resource res = super.getResource(uri, true);
		if (!res.getErrors().isEmpty()) {
			StringBuffer strBuf = new StringBuffer();
			strBuf.append(PersistenceResources.loadLibraryError_msg);
			for (Iterator<?> iter = res.getErrors().iterator(); iter.hasNext();) {
				Resource.Diagnostic error = (Resource.Diagnostic) iter
						.next();
				strBuf.append(NLS.bind(
						PersistenceResources.loadLibraryError_details,
						(new Object[] { String.valueOf(error.getLine()),
								String.valueOf(error.getColumn()),
								error.getMessage() })));
			}
			String msg = strBuf.toString();
			CommonPlugin.INSTANCE.log(msg);
			if(MultiFileSaveUtil.DEBUG) {
				System.err.println(msg);
			}
		}
		return res;
	}

	public MethodLibrary loadLibraryWithoutReset(String path) throws Exception {
		loading = true;

		// no refresh during load
		//
		boolean b = RefreshJob.getInstance().isEnabled();
		if(RefreshJob.getInstance().getResourceSet() == this) {
			RefreshJob.getInstance().setEnabled(false);
			RefreshJob.getInstance().reset();
		}
		
		// defer adding markers during load
		//
		boolean autoScheduled = markerMananger.isAutoScheduled();
		markerMananger.setAutoScheduled(false);
		try {		
			File libFile = new File(path);
			configFolderPath = new Path(new File(libFile.getParentFile(),
					MultiFileSaveUtil.METHOD_CONFIGURATION_FOLDER_NAME).getCanonicalPath());
			libFolderPath = new Path(libFile.getParentFile().getCanonicalPath());
			Resource res = loadLibraryResource(libFile.getCanonicalPath()); 
			try {
				initialize(true);
				
				// Set modified flag of library resource to false in case it had been modified during initialization
				//
				if(res != null) {
					res.setModified(false);
				}
				
				return getMethodLibrary();
			} catch (Exception e) {
				PersistencePlugin.getDefault().getLogger().logError(e);
				throw new Exception(NLS.bind(
						PersistenceResources.invalidLibraryFileError_msg, path));
			}
		} finally {
			if(RefreshJob.getInstance().getResourceSet() == this) {
				RefreshJob.getInstance().setEnabled(b);
			}
					
			markerMananger.setAutoScheduled(autoScheduled);
			markerMananger.start();
			
			loading = false;
		}
	}

	/**
	 * Loads opposite features for all loaded objects
	 */
	private void loadOppositeFeatures() {
		ArrayList oppositeFeatures = new ArrayList();
		for (Iterator iter = AssociationHelper.getPredefinedOppositeFeatures()
				.iterator(); iter.hasNext();) {
			OppositeFeature oppositeFeature = (OppositeFeature) iter.next();
			if (oppositeFeature.resolveOwner()) {
				oppositeFeatures.add(oppositeFeature);
			}
		}
		loadOppositeFeatures(oppositeFeatures);
	}

	/**
	 * Loads ResourceManager tree of the open library and connect any loaded
	 * MethodUnit to the library tree as well.
	 */
	private void loadResourceManagerTree() {
		long begin = 0;
		
		if(MultiFileSaveUtil.DEBUG) {
			begin = System.currentTimeMillis();
		}

		// disable logging unresolved proxies during loading resource manager
		// tree
		//
		boolean oldMarkerManagerEnabled = markerMananger.isEnabled();
		markerMananger.setEnabled(false);

		MultiFileURIConverter uriConverter = (MultiFileURIConverter) getURIConverter();
		try {
			// this prevents uriConverter from loading resources while
			// normalizing URI
			//
			uriConverter.setResolveProxy(false);

			ResourceManager resMgr = getRootResourceManager();
			Iterator<?> iterator = new AbstractTreeIterator<Object>(resMgr, false) {

				/**
				 * Comment for <code>serialVersionUID</code>
				 */
				private static final long serialVersionUID = 2172691017987702506L;

				protected Iterator<?> getChildren(Object object) {
					ResourceManager resMgr = (ResourceManager) object;
					return resMgr.getSubManagers().iterator();
				}

			};
			loadingResourceManagerTree = true;
			for (; iterator.hasNext(); iterator.next())
				;
		} finally {
			markerMananger.setEnabled(oldMarkerManagerEnabled);
			uriConverter.setResolveProxy(true);
			loadingResourceManagerTree = false;

			// Reporting of unnormalized URIs was disabled while loading
			// resource manager tree. Now we need
			// to go thru all the loaded objects to find proxies with
			// unnormalized URIs and report them
			//
			try {
				for (Iterator<?> iter = new ArrayList<Object>(getGuidToMethodElementMap().values())
						.iterator(); iter.hasNext();) {
					InternalEObject o = (InternalEObject) iter.next();
					if (o.eIsProxy() && o.eContainer() != null) {
						URI uri = o.eProxyURI();
						URI normalizedURI = getURIConverter().normalize(uri);
						if (normalizedURI == null) {
							// this call will log error about unresolved proxy
							//
							((InternalEObject) o.eContainer()).eResolveProxy(o);
						}
					}
				}
			}
			catch(Exception e) {
				PersistencePlugin.getDefault().getLogger().logError(e);
			}
			
			if(MultiFileSaveUtil.DEBUG) {
				System.out
						.println("MultiFileResourceSetImpl.loadResourceManagerTree(): " + (System.currentTimeMillis() - begin)); //$NON-NLS-1$
			}
		}
	}

	protected ResourceManager getResourceManager() {
		return ((MultiFileURIConverter) getURIConverter()).getResourceManager();
	}

	protected EObject findEObjectInUnloadedResources(String id, boolean skipContent) {
		return findEObjectInUnloadedResources(getResourceManager(), id, skipContent);
	}

	private EObject findEObjectInUnloadedResources(ResourceManager resMgr,
			String id, boolean skipContent) {
		boolean isLibraryResMgr = getResourceManager() == resMgr;
		for (Iterator iter = resMgr.getResourceDescriptors().iterator(); iter
				.hasNext();) {
			ResourceDescriptor desc = (ResourceDescriptor) iter.next();
			if (! isLibraryResMgr && skipContent) {
				if (! (desc.getUri().endsWith(MultiFileSaveUtil.DEFAULT_PLUGIN_MODEL_FILENAME) ||
						desc.getUri().endsWith(MultiFileSaveUtil.DEFAULT_MODEL_FILENAME))	) {
					continue;				
				}
			}	
			
			Resource resource = super.getResource(desc.getResolvedURI(), false);
			if (resource == null || !resource.isLoaded()) {
				try {
					resource = super.getResource(desc.getResolvedURI(), true);
				} catch (Exception e) {
					//
				}
				if (resource != null) {
					EObject eObject = resource.getEObject(id);
					if (eObject != null) {
						return eObject;
					}
				}
			}
		}
		for (Iterator iter = resMgr.getSubManagers().iterator(); iter.hasNext();) {
			EObject eObject = findEObjectInUnloadedResources(
					(ResourceManager) iter.next(), id, skipContent);
			if (eObject != null)
				return eObject;
		}
		return null;
	}

	/**
	 * Gets object with the given id
	 */
	public EObject getEObject(String id) {
		return getEObject(id, false);
	}
	
	/**
	 * Gets object with the given id
	 */
	public EObject getEObject(String id, boolean skipContent) {
		// first, try to get the object from the cache guidToMethodElement
		//
		EObject eObject = (EObject) getGuidToMethodElementMap().get(id);
		if (eObject != null)
			return eObject;

		URI uri = MultiFileURIConverter.createURI(id).appendFragment(id);
		URI normalized = getURIConverter().normalize(uri);
		if (normalized == null) {
			// the object with the given id might not have direct resource,
			// hence, it does not have
			// its own ResourceDescriptor in ResourceManager tree.
			// go thru all unloaded resources, load them, and try to find the
			// object with the given id.
			//
			eObject = findEObjectInUnloadedResources(id, skipContent);
			if (eObject == null && REPORT_ERROR) {
				handleException(NLS.bind(
						PersistenceResources.objNotFoundError_msg, id));
			}
			return eObject;
		}

		try {
			return getEObject(uri, true);
		} catch (RuntimeException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.resource.ResourceSet#getEObject(org.eclipse.emf.common.util.URI,
	 *      boolean)
	 */
	public EObject getEObject(URI uri, boolean loadOnDemand) {
		return getEObject(null, uri, loadOnDemand);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.resource.ResourceSet#createResource(org.eclipse.emf.common.util.URI)
	 */
	public Resource createResource(URI uri) {
		Resource result = doCreateResource(uri);
		result.setTrackingModification(true);
		getResources().add(result);
		return result;
	}
	
	/*
	 * Javadoc copied from interface.
	 */
	public Resource createResource(URI uri, String contentType) {
		return createResource(uri);
	}
	
	protected Resource doCreateResource(URI uri) {
		return new MultiFileXMIResourceImpl(uri);
	}

	private static boolean backupRequired(Map options) {
		Boolean backup = (Boolean) options
				.get(MultiFileXMISaveImpl.BACK_UP_BEFORE_SAVE);
		return backup != null && backup.booleanValue();
	}

	private void backup(Resource res) {
		try {
			if (res.getContents().isEmpty())
				return;
			Object object = res.getContents().get(0);
			if (!(object instanceof MethodLibrary
					|| object instanceof ResourceManager || object instanceof MethodPlugin))
				return;

			MethodLibrary library = getMethodLibrary();
			String backupDir = new File(library.eResource().getURI()
					.toFileString()).getParent()
					+ File.separator + ".bak"; //$NON-NLS-1$
			SimpleDateFormat dateFormatter = new SimpleDateFormat(
					"yyMMddHHmmss.S"); //$NON-NLS-1$
			if (res == library.eResource()) {
				DateFormat.getInstance().format(
						Calendar.getInstance().getTime());
				File backupFile = new File(backupDir,
						MultiFileSaveUtil.DEFAULT_LIBRARY_MODEL_FILENAME
								+ '_'
								+ dateFormatter.format(Calendar.getInstance()
										.getTime()));
				if (!backupFile.getParentFile().exists()) {
					backupFile.getParentFile().mkdirs();
				}
				FileUtil.moveFile(new File(res.getURI().toFileString()), backupFile);
			} else {
				String relativePath = res.getURI().deresolve(
						library.eResource().getURI()).toFileString();
				File backupFile = new File(backupDir, relativePath
						+ '_'
						+ dateFormatter
								.format(Calendar.getInstance().getTime()));
				if (!backupFile.getParentFile().exists()) {
					backupFile.getParentFile().mkdirs();
				}
				FileUtil.moveFile(new File(res.getURI().toFileString()), backupFile);
			}
		} catch (Exception e) {
			handleException(e);
			handleException(NLS.bind(PersistenceResources.backupError_msg, res));
		}
	}

	void removeURIMappings(EObject eObj, Set modifiedResources) {
		ResourceManager resMgr = MultiFileSaveUtil.getResourceManager(eObj
				.eResource());
		if (resMgr != null && resMgr.eContainer() != null) {
			EcoreUtil.remove(resMgr);
		}
		resMgr = getRootResourceManager();
		if(resMgr != null) {
			ResourceDescriptor desc = MultiFileSaveUtil.findResourceDescriptor(
					resMgr, MultiFileSaveUtil.getGuid(eObj),
					null, true);
			if (desc != null) {
				if (modifiedResources != null && desc.eResource() != null) {
					modifiedResources.add(desc.eResource());
				}
				EcoreUtil.remove(desc);
			}
		}
	}

	protected void cleanUp(Resource removedResource) throws IOException {
		cleanUp(removedResource, null);
	}

	// protected void cleanUp(EObject obj, Set modifiedResources) {
	// try {
	// // remove all the URI mappings
	// //
	// removeURIMappings(obj, modifiedResources);
	// }
	// finally {
	// Resource resource = obj.eResource();
	// if(resource != null && resource.getContents().contains(obj)) {
	// resource.unload();
	// getResources().remove(resource);
	// }
	// }
	// }

	void cleanUp(Resource removedResource, Set modifiedResources)
			throws IOException {
		try {
			//
			if (removedResource.getContents().isEmpty()) {
				removedResource.unload();
				try {
					removedResource.load(null);
				} catch (FileNotFoundException e) {
					// the file might already be deleted, moved or never
					// creared.
					//
					return;
				}
			}

			EObject obj = PersistenceUtil.getMethodElement(removedResource);

			// if(obj == null) return;
			// boolean cleanFolder;
			// if(obj instanceof MethodPlugin) {
			// cleanFolder = true;
			// }
			// else {
			// cleanFolder = false;
			// }

			// MultiFileURIConverter umaUriConverter =
			// ((MultiFileURIConverter)getURIConverter());
			// umaUriConverter.getResourceManager().removeResourceDescriptor(removedResource.getURI(),
			// cleanFolder);

			// remove all the URI mappings
			//
			removeURIMappings(obj, modifiedResources);

			// if(obj instanceof ProcessPackage) {
			// cleanUpResourceDescriptors((ProcessPackage)obj);
			// }

			removedResource.unload();
		} finally {
			getResources().remove(removedResource);
			URI oldURI = ((MultiFileXMIResourceImpl) removedResource)
					.getOldURI();
			if (oldURI != null) {
				getURIToTempURIMap().remove(oldURI);
			}
		}
	}

	private void cleanUp(ProcessPackage procPkg) {
		// remove ContentDescription of all ProcessElements in this
		// ProcessPackage
		//
		removeContentDescriptions(procPkg);

		EcoreUtil.remove(procPkg);
	}

	/**
	 * @param procPkg
	 */
	private void removeContentDescriptions(ProcessPackage procPkg) {
		for (Iterator iter = procPkg.getProcessElements().iterator(); iter
				.hasNext();) {
			ProcessElement element = (ProcessElement) iter.next();
			if (ContentDescriptionFactory.hasPresentation(element)
					&& element.getPresentation().eResource() != null) {
				removePresentation(element);
			}
		}

		for (Iterator iter = procPkg.getChildPackages().iterator(); iter
				.hasNext();) {
			Object pkg = iter.next();
			if (pkg instanceof ProcessPackage) {
				removeContentDescriptions((ProcessPackage) pkg);
			}
		}
	}

	/**
	 * @param element
	 */
	private void removePresentation(ProcessElement element) {
		Resource resource = element.getPresentation().eResource();
		resource.getContents().remove(element.getPresentation());
	}

	private boolean save(Collection resources, Map options, boolean prepareSave)
			throws Exception {
		if (options == null) {
			options = getDefaultSaveOptions();
		}

		Set modifiedResources = new HashSet();
		options.put(MultiFileXMISaveImpl.MODIFIED_RESOURCE_SET,
				modifiedResources);
		try {
			for (Iterator iter = resources.iterator(); iter.hasNext();) {
				Resource res = (Resource) iter.next();
				save(res, options, prepareSave);
			}
			saveModifiedResources(options);
			return true;
		} finally {
			options.remove(MultiFileXMISaveImpl.MODIFIED_RESOURCE_SET);
		}
	}

	boolean save(Resource res, Map options, boolean prepareSave)
			throws Exception {
		Set modifiedResources = (Set) options
				.get(MultiFileXMISaveImpl.MODIFIED_RESOURCE_SET);
		TxRecord txRecord = (TxRecord) options
				.get(MultiFileXMISaveImpl.TX_RECORD);
		List resourcesToCommit = txRecord != null ? txRecord
				.getResourcesToCommit() : null;

		try {
			MethodElement eObj = PersistenceUtil.getMethodElement(res);
			if (eObj != null) {
				if (prepareSave) {
					if (eObj instanceof ProcessComponent) {
						ProcessComponent procComp = (ProcessComponent) eObj;
						Resource content = procComp.getProcess() != null
								&& ContentDescriptionFactory
										.hasPresentation(procComp.getProcess()) ? procComp
								.getProcess().getPresentation().eResource()
								: null;
						boolean alreadyModified = content != null
								&& content.isModified();
						prepareSave(procComp);
						if (content != null && content.isModified()
								&& !alreadyModified) {
							// content is modified as result of prepareSave(),
							// it needs to be saved after model is saved
							//
							modifiedResources.add(content);
						}
					}
					else if (eObj instanceof MethodPlugin) {
						MultiFileSaveUtil.addResourceManager(res);
					}
				}
				if (res.isModified()) {
					// System.out.println("----> Start saving " + res);
					boolean backupRequired = backupRequired(options);
					if (backupRequired) {
						// back up resource before saving
						//
						backup(res);
					}

					if (resourcesToCommit != null) {
						((MultiFileXMIResourceImpl) res).setTempURI(txRecord
								.getTxID());
						resourcesToCommit.add(res);
					}

					MultiFileSaveUtil.save(res, options);

					// System.out.println("<---- Saved resource: " + res);

					return true;
				}
			}
		} catch (Exception exception) {
			handleException(exception);
			throw exception;
		}

		return false;
	}

	/**
	 * Save a resource of this resource set with the given save options
	 * 
	 * @param res
	 * @param options
	 * @return <code>true</code> if there is at least a resource has been saved in this call
	 * @throws Exception
	 */
	public boolean save(Resource res, Map options) throws Exception {
//		// clear all error markers for this resource
//		//
//		markerMananger.clearMarkers(res);

		if (res == null || res.getContents().isEmpty())
			return false;

		if (options == null) {
			options = getDefaultSaveOptions();
		}

		Set modifiedResources = new HashSet();
		options.put(MultiFileXMISaveImpl.MODIFIED_RESOURCE_SET,
				modifiedResources);
		boolean notFailSafe = options.get(MultiFileXMISaveImpl.TX_RECORD) == null;
		try {
			Collection savedResources = null;
			if(notFailSafe) {
				savedResources = new ArrayList();
			}
			boolean ret = save(res, options, true);
			if(ret && notFailSafe) {
				savedResources.add(res);
			}
			Collection collection = saveModifiedResources(options);
			if(!collection.isEmpty()) {
				ret = true;
				if(notFailSafe) {
					savedResources.addAll(collection);
				}
			}
			
			if(notFailSafe && ret) {
				// update resource cached timestamps
				//
				for (Iterator iter = savedResources.iterator(); iter.hasNext();) {
					Object resource = iter.next();
					if(resource instanceof MultiFileXMIResourceImpl) {
						((MultiFileXMIResourceImpl)resource).updateTimeStamps();
					}
				}								
			}
			
			return ret;
		} finally {
			options.remove(MultiFileXMISaveImpl.MODIFIED_RESOURCE_SET);
		}
	}

	/**
	 * Saves only the modified resources in this resource set.
	 * 
	 * @param options
	 */
	public void save(Map options) throws LibraryResourceException {
		try {
			save(options, false, true);
		} catch (Exception e) {
			if(e instanceof RuntimeException) {
				throw (RuntimeException)e;
			}
			throw new LibraryResourceException(e);
		}
	}

	/**
	 * 
	 * @param options
	 * @return resources that had been saved by this call
	 * @throws LibraryResourceException 
	 */
	Collection saveModifiedResources(Map options) throws LibraryResourceException {
		Set modifiedResources = (Set) options
				.get(MultiFileXMISaveImpl.MODIFIED_RESOURCE_SET);
		TxRecord txRecord = (TxRecord) options
				.get(MultiFileXMISaveImpl.TX_RECORD);
		List resourcesToCommit = txRecord != null ? txRecord
				.getResourcesToCommit() : null;
		if (modifiedResources == null)
			return Collections.EMPTY_LIST;
		boolean backupRequired = backupRequired(options);
		boolean checkModify = MultiFileXMISaveImpl.checkModifyRequired(options);
		Collection savedResources = new ArrayList();
		while (!modifiedResources.isEmpty()) {
			List<Resource> resourcesToSave = new ArrayList<Resource>(modifiedResources);
			
			modifiedResources.clear();
			if (checkModify) {
				checkModify(resourcesToSave);
				if(resourcesToCommit != null) {
					// check for out-of-synch only in fail-safe saving for now
					//
					MultiFileSaveUtil.checkOutOfSynch(resourcesToSave, options);
				}
			}
			for(Iterator iter = resourcesToSave.iterator(); iter.hasNext();) {
				Resource resource = (Resource) iter.next();
				// if(resource.isModified()) {
				if (backupRequired) {
					// back up resource before saving
					//
					backup(resource);
				}

				if (resourcesToCommit != null) {
					((MultiFileXMIResourceImpl) resource).setTempURI(txRecord
							.getTxID());
					resourcesToCommit.add(resource);
				}

				if(MultiFileSaveUtil.save(resource, options)) {
					savedResources.add(resource);
				}
				// }
			}
		}
		return savedResources;
	}

	/**
	 * 
	 * @param options
	 * @param saveAll
	 *            if true, force saving all the resources even if they are not
	 *            modified.
	 * @throws Exception
	 */
	public void save(Map options, boolean saveAll) throws Exception {
		save(options, saveAll, false);
	}
	
	protected void save(Map options, boolean saveAll, boolean failSafe)
			throws Exception {
		if (options == null) {
			options = new HashMap(getDefaultSaveOptions());
		}

		MethodLibrary library = getMethodLibrary();
		MultiFileSaveUtil.setGuid(library);

		prepareSave(library);

		// do clean-up this resource set before saving
		//
		cleanUp();

		MultiFileURIConverter umaUriConverter = ((MultiFileURIConverter) getURIConverter());
		umaUriConverter.setLibraryURIs(library);

		Set modifiedResources = new HashSet();
		options.put(MultiFileXMISaveImpl.MODIFIED_RESOURCE_SET,
				modifiedResources);
		MethodLibraryPersister.FailSafePersister persister = null;
		if (failSafe) {
			persister = MethodLibraryPersister.INSTANCE
					.getFailSafePersister(options);
			options = persister.getSaveOptions();
		}
		try {
			List list = getResources();
			int size = list.size();
			// System.out.println("Start saving " + size + " loaded resources: "
			// + list);
			// System.out.println();
			boolean backupRequired = backupRequired(options);
			List saveResources = new ArrayList();
			List moveResources = new ArrayList();
			for (int i = 0; i < size; i++) {
				Resource res = (Resource) list.get(i);

				 if (res.isModified() && 
						 MultiFileSaveUtil.adjustLocationRequired((MultiFileXMIResourceImpl) res)) {
					 moveResources.add(res);
				 }

				MethodElement eObj = PersistenceUtil.getMethodElement(res);
				if (eObj != null && eObj instanceof MethodElement) {
					if (saveAll || res.isModified()) {
//						// System.out.println("----> Start saving " + res);
//						if (failSafe) {
//							saveResources.add(res);
//						} else {
//							if (backupRequired) {
//								// back up resource before saving
//								//
//								backup(res);
//							}
//
//							if(MultiFileSaveUtil.save(res, options)) {
//								savedResources.add(res);
//								if(res instanceof MultiFileXMIResourceImpl) {
//									((MultiFileXMIResourceImpl)res).updateTimeStamps();
//								}
//							}
//						}
//						// System.out.println("<---- Saved resource: " + res);
						
						saveResources.add(res);
					}
				}
			}

			if(!saveResources.isEmpty()) {
				Resource libResource = getMethodLibraryResource();
				if(!saveResources.contains(libResource) && PersistenceUtil.checkToolVersion(libResource) != 0) {
					saveResources.add(libResource);
				}
				if(MultiFileXMISaveImpl.checkModifyRequired(options)) {
					checkModify(saveResources);
				}
				if (failSafe) {
					try {
						save(saveResources, options, false);
						persister.commit();
						persister.adjustLocation(moveResources);
					} catch (Exception e) {
						persister.rollback();
						throw e;
					}
				} else {
					for (Iterator iter = saveResources.iterator(); iter
							.hasNext();) {
						Resource resource = (Resource) iter.next();
						if(MultiFileSaveUtil.save(resource, options)) {
							if(resource instanceof MultiFileXMIResourceImpl) {
								((MultiFileXMIResourceImpl)resource).updateTimeStamps();
							}
						}
					}
					for (Iterator iter = moveResources.iterator(); iter.hasNext();) {
						Resource resource = (Resource) iter.next();
						MultiFileSaveUtil.adjustLocation(resource,
								modifiedResources);
					}
					Collection saved = saveModifiedResources(options);
					if (saved != null) {
						for (Iterator iter = saved.iterator(); iter.hasNext();) {
							Resource resource = (Resource) iter.next();
							if(resource instanceof MultiFileXMIResourceImpl) {
								((MultiFileXMIResourceImpl)resource).updateTimeStamps();
							}
						}
					}
				}
			}
		} catch (Exception exception) {
			handleException(exception);
			throw exception;
		} finally {
			options.remove(MultiFileXMISaveImpl.MODIFIED_RESOURCE_SET);
		}
	}

	/**
	 * Saves the open library under a new location
	 * 
	 * @param newLocation
	 *            directory to save the open library to
	 * @param if
	 *            true, regenerateGUID regenerate GUID for all MethodElements in
	 *            the open library
	 */
	public void saveAs(String newLocation, boolean regenerateGUID,
			IProgressMonitor monitor) throws Exception {
		MethodLibrary lib = getMethodLibrary();
		if (lib == null)
			return;

		// load all the resources in memory
		//
		if (monitor != null)
			monitor.setTaskName(PersistenceResources.loadResourcesTask_name);
		for (Iterator iter = lib.eAllContents(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			if (regenerateGUID && element instanceof MethodElement) {
				((MethodElement) element).setGuid(UmaUtil.generateGUID());
			}
			for (Iterator iterator = element.eCrossReferences().iterator(); iterator
					.hasNext();) {
				iterator.next();
			}
		}

		// change URI of all loaded resources for new location
		//
		URI oldDir = URI.createFileURI(new File(lib.eResource().getURI()
				.toFileString()).getParent()
				+ File.separator);
		URI newDir = URI.createFileURI(newLocation + File.separator);
		for (Iterator iter = getResources().iterator(); iter.hasNext();) {
			Resource resource = (Resource) iter.next();
			resource.setURI(resource.getURI().replacePrefix(oldDir, newDir));
		}

		// clear the ResourceManager
		//
		ResourceManager resMgr = ((MultiFileURIConverter) getURIConverter())
				.getResourceManager();
		if (resMgr != null) {
			resMgr.getResourceDescriptors().clear();
		}

		if (monitor != null)
			monitor.setTaskName(PersistenceResources.saveLibraryTask_name);
		save(null, true);
	}
	
	protected boolean unloadWithoutRemove(MultiFileXMIResourceImpl resource) {
		MethodElement e = PersistenceUtil.getMethodElement(resource);
		resource.unloadWithoutRemove();
		if(e instanceof MethodConfiguration) {
			MethodLibrary lib = getMethodLibrary();
			if(lib != null) {
				lib.getPredefinedConfigurations().remove(e);
			}
		}
		return true;
	}

	/**
	 * @throws IOException
	 * 
	 */
	private void cleanUp() throws IOException {
		MethodLibrary library = getMethodLibrary();
		List removedResources = new ArrayList();

		List list = getResources();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Resource res = (Resource) list.get(i);
			boolean remove = false;
			if (res.getContents().isEmpty()) {
				System.out
						.println("MultiFileResourceSetImpl.cleanUp(): empty resource: " + res); //$NON-NLS-1$
				remove = true;
			} else {
				MethodElement eObj = PersistenceUtil.getMethodElement(res);
				if (eObj == null
						|| (eObj != library && eObj.eContainer() == null)) {
					if (eObj == null) {
						System.out
								.println("MultiFileResourceSetImpl.cleanUp(): resource without any MethodElement: " + res); //$NON-NLS-1$
						remove = true;
					}
					// some resources might be loaded directly, not thru its
					// container. Therefore, we cannot assume
					// that a object without a container in a loaded resource is
					// a removed object.
					//
					else if (eObj != library && eObj.eContainer() == null) {
						System.out
								.println("MultiFileResourceSetImpl.cleanUp(): resource without a container: " + res); //$NON-NLS-1$
					}
				}
			}
			if (remove) {
				// This is a removed object. Add its path to the list for
				// removing its files later.
				//
				removedResources.add(res);
			}
		}

		// do clean-up
		//
		System.out
				.println("MultiFileResourceSetImpl.cleanUp(): removed resources: " + removedResources); //$NON-NLS-1$

		for (Iterator iter = removedResources.iterator(); iter.hasNext();) {
			Resource res = (Resource) iter.next();

			try {
				// remove ResourceDescriptor of the removed resource
				//
				cleanUp(res);
			} catch (Exception e) {
				handleException(e);
			}
		}

		// getResources().removeAll(removedResources);
	}
	
	/**
	 * Prepares the library for saving
	 */
	private void prepareSave(MethodLibrary library) {
		// check if the library already has a ResourceManager
		// if not, create ResourceManager for it
		//
		MultiFileXMIResourceImpl resource = (MultiFileXMIResourceImpl) library
				.eResource();
		ResourceManager libResMgr = MultiFileSaveUtil.addResourceManager(resource);
		if(libResMgr != null) {
			MultiFileSaveUtil.registerWithResourceManager(libResMgr, library,
					resource.getFinalURI());
		}

		List models = library.getMethodPlugins();
		int size = models.size();
		for (int i = 0; i < size; i++) {
			prepareSave((MethodPlugin) models.get(i));
		}

		for (int i = 0; i < size; i++) {
			cleanUp((MethodPlugin) models.get(i));
		}
	}

	private static void cleanUp(MethodPlugin plugin) {
		for (int i = 0; i < PROCESS_PACKAGE_PATHS.length; i++) {
			ProcessPackage pkg = (ProcessPackage) UmaUtil.findMethodPackage(
					plugin, PROCESS_PACKAGE_PATHS[i]);
			if (pkg != null) {
				cleanUpProcesses(pkg);
			}
		}
	}

	private void prepareSave(MethodPlugin model) {
		for (int i = 0; i < PROCESS_PACKAGE_PATHS.length; i++) {
			ProcessPackage pkg = (ProcessPackage) UmaUtil.findMethodPackage(
					model, PROCESS_PACKAGE_PATHS[i]);
			if (pkg != null) {
				prepareSave(pkg);
			}
		}
	}

	/**
	 * Removes process packages of unused activities.
	 * 
	 * @param pkg
	 */
	private static void cleanUpProcesses(ProcessPackage pkg) {
		List list = pkg.getChildPackages();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Object obj = list.get(i);
			if (obj instanceof ProcessComponent) {
				ProcessComponent procComp = (ProcessComponent) obj;
				List packages = new ArrayList();
				for (Iterator iter = procComp.getChildPackages().iterator(); iter
						.hasNext();) {
					Object element = (Object) iter.next();
					if (element instanceof ProcessPackage) {
						getRemovedPackages((ProcessPackage) element, packages);
					}
				}
				for (Iterator iterator = packages.iterator(); iterator
						.hasNext();) {
					EcoreUtil.remove((EObject) iterator.next());
				}
			}
		}
	}

	private static void getRemovedPackages(ProcessPackage pkg,
			List removedPackages) {
		// find activity
		//
		Activity act = getActivity(pkg);

		if (act == null || getParent(act) == null) {
			// Invalid process package. It does not contain any activity.
			// Add it to the list of packages to be removed.
			//
			removedPackages.add(pkg);
		} else {
			for (Iterator iter = pkg.getChildPackages().iterator(); iter
					.hasNext();) {
				Object element = (Object) iter.next();
				if (element instanceof ProcessPackage) {
					getRemovedPackages((ProcessPackage) element,
							removedPackages);
				}
			}
		}
	}

	private static Activity getActivity(ProcessPackage pkg) {
		// find activity
		//
		Activity act = null;
		for (Iterator iter = pkg.getProcessElements().iterator(); iter
				.hasNext();) {
			Object element = iter.next();
			if (element instanceof Activity) {
				act = (Activity) element;
				break;
			}
		}
		return act;
	}

	private static Object getParent(BreakdownElement e) {
		// ItemProviderAdapter adapter = (ItemProviderAdapter)
		// TngUtil.getAdapter(e, IBSItemProvider.class);
		// return adapter == null ? null : adapter.getParent(e);

		return UmaUtil.getParentActivity(e);
	}

	/**
	 * Creates process package for those activities that still don't belong to
	 * any process package.
	 */
	private void prepareSave(ProcessPackage pkg) {
		List list = pkg.getChildPackages();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Object obj = list.get(i);
			if (obj instanceof ProcessComponent) {
				prepareSave((ProcessComponent) obj);
			}
		}
	}

	private void prepareSave(ProcessComponent procComp) {
		Process proc = procComp.getProcess();
		if (proc != null) {
			// associateAllAdapters(proc);
			prepareSave(proc);

			cleanUpDanglingDescriptors(procComp);

			List danglingPkgs = new ArrayList();
			for (Iterator iter = new ArrayList(procComp.getChildPackages())
					.iterator(); iter.hasNext();) {
				Object element = (Object) iter.next();
				if (element instanceof ProcessPackage) {
					ProcessPackage pkg = (ProcessPackage) element;
					reorganize(pkg, danglingPkgs);
				}
			}

			// clean up dangling ProcessPackages
			//
			for (Iterator iter = danglingPkgs.iterator(); iter.hasNext();) {
				cleanUp((ProcessPackage) iter.next());
			}

		}
	}

	// /**
	// * @param activity
	// */
	// private static void associateAllAdapters(Activity activity) {
	// AdapterFactory adapterFactory =
	// TngAdapterFactory.INSTANCE.getWBS_ComposedAdapterFactory();
	// ItemProviderAdapter adapter = (ItemProviderAdapter)
	// adapterFactory.adapt(activity, ITreeItemContentProvider.class);
	// for (Iterator iter = adapter.getChildren(activity).iterator();
	// iter.hasNext();) {
	// Object element = iter.next();
	// if(element instanceof Activity) {
	// associateAllAdapters((Activity) element);
	// }
	// }
	// }

	/**
	 * @param activity
	 */
	private static void prepareSave(Activity activity) {
		ProcessPackage parentPkg = (ProcessPackage) activity.eContainer();
		List elements = activity.getBreakdownElements();

		// add new descriptors and other non-activity breakdown elements of the
		// activity to its process package
		//
		for (Iterator iter = elements.iterator(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			if (!(element instanceof Activity) && element.eContainer() == null) {
				parentPkg.getProcessElements().add((ProcessElement) element);
			}
		}

		// add new WorkOrder objects or remove unused WorkOrder objects of this
		// activity
		//
		for (WorkOrder workOrder : activity.getLinkToPredecessor()) {
			if (workOrder.eContainer() == null) {
				if (workOrder.getPred() != null
						&& getParent(workOrder.getPred()) != null) {
					parentPkg.getProcessElements().add(workOrder);
				}
			} else {
				if (workOrder.getPred() == null
						|| getParent(workOrder.getPred()) == null) {
					if (MultiFileSaveUtil.DEBUG) {
						System.err.println("Invalid WorkOrder: " + workOrder); //$NON-NLS-1$
					}
					parentPkg.getProcessElements().remove(workOrder);
				}
			}
		}

		// removed unused diagrams
		//
		for (Iterator iter = parentPkg.getDiagrams().iterator(); iter.hasNext();) {
			Diagram diagram = (Diagram) iter.next();
			SemanticModelBridge bridge = diagram.getSemanticModel();
			if (bridge instanceof UMASemanticModelBridge) {
				MethodElement element = ((UMASemanticModelBridge) bridge)
						.getElement();
				if (element instanceof Activity
						&& (!(element instanceof Process) && getParent((Activity) element) == null)) {
					iter.remove();
				}
			}
		}

		int size = elements.size();
		for (int i = 0; i < size; i++) {
			Object e = elements.get(i);
			if (e instanceof Activity) {
				Activity act = (Activity) e;
				if (act.eContainer() == null) {
					ProcessPackage pkg = UmaFactory.eINSTANCE
							.createProcessPackage();
					pkg.setName(act.getName());
					pkg.getProcessElements().add(act);
					List descriptors = new ArrayList();
					for (int j = 0; j < act.getBreakdownElements().size(); j++) {
						Object obj = act.getBreakdownElements().get(j);
						if (obj instanceof Descriptor) {
							descriptors.add(obj);
						}
					}
					pkg.getProcessElements().addAll(descriptors);

					parentPkg.getChildPackages().add(pkg);
					// System.out.println("Process package created for activity:
					// '" + act.getName() + "'");
				}
				prepareSave(act);
			}
		}
	}

	private static void cleanUpDanglingDescriptors(ProcessPackage pkg) {
		for (Iterator iter = pkg.getProcessElements().iterator(); iter
				.hasNext();) {
			Object element = iter.next();
			if (element instanceof BreakdownElement
					&& ((BreakdownElement) element).getSuperActivities() == null) {
				boolean remove = false;
				if (element instanceof RoleDescriptor) {
					if (AssociationHelper.getTeamProfiles(
							(RoleDescriptor) element).isEmpty()) {
						remove = true;
					}
				} else if (element instanceof WorkProductDescriptor) {
					if (AssociationHelper.getDeliverableDescriptors(
							(WorkProductDescriptor) element).isEmpty()) {
						remove = true;
					}
				} else if (element instanceof TeamProfile) {
					if (((TeamProfile) element).getSuperTeam() == null) {
						remove = true;
					}
				} else {
					remove = true;
				}

				if (remove) {
					iter.remove();
				}
			}
		}
	}

	/**
	 * Moves the process packages to the right location in the tree if needed.
	 * 
	 * @param pkg
	 * @param danglingPkgs
	 */
	private void reorganize(ProcessPackage pkg, List danglingPkgs) {
		Activity act = getActivity(pkg);
		if (act == null)
			return;

		// make sure the package has the same name as the activity's name
		//
		if (!pkg.getName().equals(act.getName())) {
			pkg.setName(act.getName());
		}

		// check if the container of pkg is actually holding the parent activity
		// of act
		//
		EObject parentAct = (EObject) getParent(act);

		if (parentAct == null) {
			// cleanUpResourceDescriptors(pkg);
			// cleanUp(pkg);
			// return;
			danglingPkgs.add(pkg);
		} else if (parentAct != getActivity((ProcessPackage) pkg.eContainer())) {
			// move the package to the right location, under the process package
			// of parentAct
			//
			ProcessPackage parentPkg = (ProcessPackage) parentAct.eContainer();
			parentPkg.getChildPackages().add(pkg);
		}

		if (parentAct != null) {
			cleanUpDanglingDescriptors(pkg);
		}

		for (Iterator iter = pkg.getChildPackages().iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			if (element instanceof ProcessPackage) {
				reorganize((ProcessPackage) element, danglingPkgs);
			}
		}
	}

	public Map<String, EObject> getGuidToMethodElementMap() {
		if (guidToMethodElementMap == null) {
			guidToMethodElementMap = new HashMap<String, EObject>();
		}
		return guidToMethodElementMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.impl.BasicNotifierImpl#eNotify(org.eclipse.emf.common.notify.Notification)
	 */
	public void eNotify(Notification notification) {
		try {
			super.eNotify(notification);
		} catch (RuntimeException e) {
			CommonPlugin.INSTANCE
					.log("ERROR notifying changes: resourceSet=" + this + ", notification=" + notification); //$NON-NLS-1$ //$NON-NLS-2$
			CommonPlugin.INSTANCE.log(e);
			e.printStackTrace();
		}
	}

	public void addMarker(Exception e) {
		try {
			// insert problem marker
			//
			markerMananger.notifyException(e);
		} catch (RuntimeException ex) {
			CommonPlugin.INSTANCE.log(ex);
			if (MultiFileSaveUtil.DEBUG) {
				ex.printStackTrace();
			}
		}
	}

	public void setLoading(boolean b) {
		loading = b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.ecore.IExceptionCollector#collect(java.lang.Exception)
	 */
	public void notifyException(Exception e) {
		if (loadingResourceManagerTree) {
			if (e instanceof ResolveException) {
				if (((ResolveException) e).exception() instanceof MultiFileIOException) {
					return;
				}
			}
		}
		try {
			addMarker(e);
		} catch (Exception ex) {
			//
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.ecore.IProxyResolutionListener#proxyResolved(java.lang.Object,
	 *      java.lang.Object)
	 */
	public void proxyResolved(Object proxy, Object resolved) {
		if(resolved instanceof MethodElement) {
			String guid = MultiFileSaveUtil.getGuid(resolved);
			if (guid != null) {
				getGuidToMethodElementMap().put(guid, (EObject) resolved);
			}
		}
		markerMananger.proxyResolved(proxy, resolved);
	}

	protected EObject getEObjectByGUID(String guid) {
		return (EObject) getGuidToMethodElementMap().get(guid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.ecore.IUmaResourceSet#getEObject(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.common.util.URI, boolean)
	 */
	public EObject getEObject(EObject resolver, URI uri, boolean loadOnDemand) {
		// long start;
		// if(MultiFileSaveUtil.PROFILE) {
		// start = System.currentTimeMillis();
		// }

		URI normalized = ((MultiFileURIConverter) getURIConverter()).normalize(
				uri, resolver);
		if (normalized == null) {
			// work-around to look up for object by its GUID in case it had been
			// moved to other resource
			//
			String guid = uri.fragment();
			EObject eObject = getEObjectByGUID(guid);
			if (eObject != null) {
				return eObject;
			}

			String msg = NLS.bind(PersistenceResources.normalizeURIError_msg,
					uri);
			throw new UnnormalizedURIException(uri, msg);
		}
		Resource resource = getResource(normalized.trimFragment(), loadOnDemand);
		if (resource != null) {
			String fragment = normalized.fragment();
			if (fragment == null || fragment.length() == 0) {
				return PersistenceUtil.getMethodElement(resource);
			}
			EObject eObject = resource.getEObject(fragment);
			if (eObject == null && !fragment.equals(uri.authority())) {
				// work-around to look up for object by its GUID in case it had
				// been moved
				//
				return getEObjectByGUID(fragment);
			}
			return eObject;
		} else {
			return null;
		}

		// if(MultiFileSaveUtil.PROFILE) {
		//			
		// }
	}
	
	private ArrayList copyCollectionToList(Collection c) {
		int max = 5;
		for (int i = 0; i < max; i++) {	//Try up to max times for any concurrent exception
			try {
				return new ArrayList(c);				
			} catch (Exception e) {
				if (i == max - 1) {
					CommonPlugin.INSTANCE.log(e);
				} else {
					try {
						Thread.sleep(1000);
					} catch (Exception ee) {						
					}
				}
			}
		}
		return new ArrayList();
	}

	// /**
	// * @return Returns the exceptions.
	// */
	// public List getExceptions() {
	// return exceptions;
	// }

	public void loadOppositeFeatures(List oppositeFeatures) {
		int max = oppositeFeatures.size() - 1;
		if (max < 0) {
			return;
		}
		ArrayList elements = copyCollectionToList(getGuidToMethodElementMap().values());
		HashSet loadedElements = new HashSet();
		while (!elements.isEmpty()) {
			for (Iterator iter = elements.iterator(); iter.hasNext();) {
				Object obj = iter.next();
				if (obj instanceof MethodElement) {
					MethodElement element = (MethodElement) obj;
					for (int i = max; i > -1; i--) {
						EStructuralFeature eFeature = ((OppositeFeature) oppositeFeatures
								.get(i)).getTargetFeature();
						if (eFeature.getContainerClass().isInstance(element)) {
							if (eFeature.isMany()) {
								InternalEList list = (InternalEList) element
										.eGet(eFeature);
								if (!list.isEmpty()) {
									for (Iterator iterator = list.iterator(); iterator
											.hasNext();) {
										iterator.next();
									}
								}
							} else {
								element.eGet(eFeature);
							}
						}
					}
				}
			}

			// gets the newly loaded elements to load their opposite features
			//
			loadedElements.addAll(elements);
			elements = copyCollectionToList(getGuidToMethodElementMap().values());
			elements.removeAll(loadedElements);
		}
	}

	public void loadOppositeFeatures(List oppositeFeatures, Set deletedGUIDs) {
		int max = oppositeFeatures.size() - 1;
		if (max < 0) {
			return;
		}
		ArrayList elements = copyCollectionToList(getGuidToMethodElementMap().values());
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
			elements = copyCollectionToList(getGuidToMethodElementMap().values());
			elements.removeAll(loadedElements);
		}
	}

	public boolean hasUnresolvedProxy() {
		return markerMananger.hasUnresolvedProxy();
	}

	protected Map getURIToTempURIMap() {
		if (URIToTempURIMap == null) {
			URIToTempURIMap = new HashMap();
		}

		return URIToTempURIMap;
	}

	public UnresolvedProxyMarkerManager getMarkerMananger() {
		return markerMananger;
	}
	
	private void checkModify(Collection<Resource> resources) throws LibraryResourceException {
		if(!resources.isEmpty()) {
			Resource[] resourceArray = new Resource[resources.size()];
			resources.toArray(resourceArray);
			checkModify(resourceArray, PersistencePlugin.getDefault().getContext()/*MsgBox.getDefaultShell()*/);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSet#checkModify(org.eclipse.epf.library.persistence.ILibraryResource[], java.lang.Object)
	 */
	public void checkModify(Resource[] resources, Object context) throws LibraryResourceException {		
		MultiFileSaveUtil.doCheckModify(Arrays.asList(resources));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSet#checkModify(java.util.Collection<EObject>, java.lang.Object)
	 */
	public IStatus checkModify(Collection<EObject> eObjects, Object context) {
		HashSet pathSet = new HashSet();
		for (Iterator iter = eObjects.iterator(); iter.hasNext();) {
			EObject o = (EObject) iter.next();
			Resource resource = o.eResource();
			if(resource != null && resource.getResourceSet() == this) {
				MultiFileXMIResourceImpl mfResource = (MultiFileXMIResourceImpl) resource;
				if(!mfResource.hasTempURI()) {
					String path = mfResource.getURI().toFileString();
					pathSet.add(path);
				}
			}
		}
		if(pathSet.isEmpty()) {
			return Status.OK_STATUS;
		}
		String[] paths = new String[pathSet.size()];
		pathSet.toArray(paths);
		return FileManager.getInstance().checkModify(paths, context);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSet#getPersister()
	 */
	public ILibraryPersister getPersister() {
		return MethodLibraryPersister.INSTANCE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSet#unload(org.eclipse.emf.ecore.resource.Resource, java.util.Map)
	 */
	public boolean unload(Resource resource, Map options) {
		if(resource instanceof MultiFileXMIResourceImpl) {
			return unloadWithoutRemove((MultiFileXMIResourceImpl) resource);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSet#unload()
	 */
	public void unload() {
		reset();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSet#getFirstMethodLibrary()
	 */
	public MethodLibrary getFirstMethodLibrary() {
		return getMethodLibrary();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSet#getMethodLibraries()
	 */
	public List getMethodLibraries() {
		MethodLibrary lib = getMethodLibrary();
		if(lib == null) {
			return Collections.EMPTY_LIST;
		}
		return Collections.singletonList(lib);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSet#loadMethodLibraries(org.eclipse.emf.common.util.URI, java.util.Map)
	 */
	public void loadMethodLibraries(URI uri, Map<?, ?> parameters) throws LibraryResourceException {
		try {
			loadLibrary(uri.toFileString());
		} catch (Exception e) {
			if(e instanceof RuntimeException) {
				throw (RuntimeException)e;
			}
			throw new LibraryResourceException(e);
		}

	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSet#getPersistenceType()
	 */
	public String getPersistenceType() {
		return Services.XMI_PERSISTENCE_TYPE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSet#unload(org.eclipse.emf.ecore.EObject)
	 */
	public boolean unload(EObject object) {
		Resource resource = object.eResource();
		if(resource instanceof MultiFileXMIResourceImpl) {
			((MultiFileXMIResourceImpl)resource).unloaded((InternalEObject) object);
			return true;
		}		
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSet#reloadObjects(java.util.Collection)
	 */
	public Collection reloadObjects(Collection objects) {
		// TODO Auto-generated method stub
		return null;
	}

	public Resource getResource(IResource wsRes) {
		IPath path = wsRes.getLocation();
		return path != null ? PersistenceUtil.getResource(path, this) : null;
	}

//	public Collection<Resource> getContainedResources(IResource wsRes) {
//		return Collections.EMPTY_LIST;		
//	}
	
	public Collection<Resource> loadNewResources() {
		return loadConfigurations();
	}
	
	public void handleLibraryMoved(String newFolder) {
		PersistenceUtil.replaceURIPrefix(new ArrayList<Resource>(getResources()), libFolderPath.toOSString(), newFolder);
		
		// clear cached resolved URI in all ResourceDescriptors
		//
		clearCachedResolvedURIs();
	}
	
	protected void clearCachedResolvedURIs() {
		ResourceManager resMgr = getRootResourceManager();
		Iterator iterator = new AbstractTreeIterator(resMgr, true) {
			private static final long serialVersionUID = 1L;

			protected Iterator getChildren(Object object) {
				ResourceManager resMgr = (ResourceManager) object;
				return resMgr.getSubManagers().iterator();
			}

		};
		while (iterator.hasNext()) {
			ResourceManager mgr = (ResourceManager) iterator.next();
			for(ResourceDescriptor resDesc : mgr.getResourceDescriptors()) {
				resDesc.clearResolvedURI();
			}
		}
	}
	
	public boolean hasOwnFolder(Object e) {
		return MultiFileSaveUtil.hasOwnFolder(e);
	}	
	
}