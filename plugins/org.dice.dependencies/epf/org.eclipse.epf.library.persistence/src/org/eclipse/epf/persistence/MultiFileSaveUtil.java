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
import java.io.FileFilter;
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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.persistence.LibraryResourceException;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.persistence.util.PersistenceResources;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.resourcemanager.ResourceDescriptor;
import org.eclipse.epf.resourcemanager.ResourceManager;
import org.eclipse.epf.resourcemanager.ResourcemanagerFactory;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.BreakdownElementDescription;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.MethodUnit;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

/**
 * Utility class with static routines for XMI persistence
 * 
 * @author Phong Nguyen Le
 * @author Jinhua Xi
 * @since 1.0
 */
public final class MultiFileSaveUtil {

	public static final String DEFAULT_MODEL_FILENAME = "model.xmi"; //$NON-NLS-1$

	public static final String DEFAULT_CONTENT_FILENAME = "content.xmi"; //$NON-NLS-1$

	public static final String DEFAULT_LIBRARY_MODEL_FILENAME = "library.xmi"; //$NON-NLS-1$
	
	public static final String DEFAULT_PLUGIN_EXPORT_FILENAME = "export.xmi"; //$NON-NLS-1$

	public static final String DEFAULT_PLUGIN_MODEL_FILENAME = "plugin.xmi"; //$NON-NLS-1$

	public static final String DEFAULT_FILE_EXTENSION = ".xmi"; //$NON-NLS-1$

	public static final String LIBRARY_FILE_EXTENSION = "uma"; //$NON-NLS-1$

	public static final String CAPABILITY_PATTERN_PATH = "capabilitypatterns"; //$NON-NLS-1$

	public static final String DELIVERY_PROCESS_PATH = "deliveryprocesses"; //$NON-NLS-1$

	public static final String PROCESS_CONTRIBUTION_PATH = "processcontributions"; //$NON-NLS-1$

	public static final String METHOD_CONFIGURATION_FOLDER_NAME = "configurations"; //$NON-NLS-1$

	public static final boolean DEBUG = PersistencePlugin.getDefault()
			.isDebugging();

	static final boolean PROFILE = false;

	private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyMMddHHmmss.S"); //$NON-NLS-1$

	public static boolean isValidFileName(String name) {
		if (name == null)
			return false;
		if (name.indexOf('/') != -1 || name.indexOf('\\') != -1
				|| name.indexOf(':') != -1 || name.indexOf('*') != -1
				|| name.indexOf('?') != -1 || name.indexOf('"') != -1
				|| name.indexOf('<') != -1 || name.indexOf('>') != -1
				|| name.indexOf('|') != -1)
			return false;
		return true;
	}

	public static String getPath(MethodElement e) {
		StringBuffer strBuff = new StringBuffer(String.valueOf(e.getName()));
		for (e = (MethodElement) ((EObject) e).eContainer(); e != null; e = (MethodElement) ((EObject) e)
				.eContainer()) {
			strBuff.insert(0, String.valueOf(e.getName()) + " > "); //$NON-NLS-1$
		}
		return strBuff.toString();
	}

	public static String createDirName(MethodElement obj) {
		String name = ((MethodElement) obj).getName();
		if (isValidFileName(name))
			return name;
		throw new MultiFileIOException(NLS.bind(
				PersistenceResources.invalidNameError_msg, name), obj);
	}

	public static EObject resolve(EObject proxy) {
		if (!(proxy instanceof InternalEObject))
			return proxy;
		InternalEObject obj = (InternalEObject) proxy;
		if (obj.eProxyURI() == null)
			return proxy;
		XMIResourceImpl res = new XMIResourceImpl(obj.eProxyURI());
		try {
			res.load(null);
			return PersistenceUtil.getMethodElement(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return proxy;
	}

	static Resource save(EObject o, URI uri, Map options) {
		ResourceSet resourceSet = o.eResource().getResourceSet();
		MultiFileXMIResourceImpl resource = save(resourceSet, o, uri, options);

		if (options == null) {
			options = ((MultiFileResourceSetImpl) resourceSet)
					.getDefaultSaveOptions();
		}
		String str = (String) options
				.get(MultiFileXMISaveImpl.REFRESH_NEW_RESOURCE);
		if (str != null && Boolean.valueOf(str).booleanValue()) {
			// notify RefreshJob the this resource is saved so it will not be
			// reloaded after refreshing it
			//
			RefreshJob.getInstance().resourceSaved(resource);

			// refresh the newly created resource so it is in synch with the
			// workspace
			//
			FileManager.getInstance().refresh(resource);
		}

		return resource;
	}

	public static boolean canSaveTogether(Map options, Object obj) {
		Set saveTogetherClasses = (Set) options
				.get(MultiFileXMISaveImpl.SAVE_TOGETHER_CLASS_SET);
		if (saveTogetherClasses == null)
			return false;
		for (Iterator iter = saveTogetherClasses.iterator(); iter.hasNext();) {
			EClass eCls = (EClass) iter.next();
			if (eCls.isInstance(obj))
				return true;
		}
		return false;
	}

	/**
	 * Adds the given object to the given resource while still preserving
	 * object's container reference.
	 * 
	 * @param resource
	 * @param o
	 */
	private static void addTo(Resource resource, MultiResourceEObject o) {
		BasicEList contents = ((BasicEList) resource.getContents());
		if (contents.isEmpty()) {
			// this will flag resource as loaded
			//
			contents.clear();

			contents.setData(1, new Object[] { o });
		} else {
			Object[] data = contents.toArray();
			Object[] newData = new Object[data.length + 1];
			System.arraycopy(data, 0, newData, 0, data.length);
			newData[data.length] = o;
			contents.setData(newData.length, newData);
		}
		o.eSetResource((Resource.Internal) resource);
	}

	/**
	 * Saves a new contained EObject in its own new resource.
	 * 
	 * @param o
	 * @param uri
	 * @param options
	 */
	private static MultiFileXMIResourceImpl save(ResourceSet resourceSet,
			EObject o, URI uri, Map options) {
		return save(resourceSet, o, uri, options, true);
	}
	
	static MultiFileXMIResourceImpl save(ResourceSet resourceSet,
			EObject o, URI uri, Map options, boolean registerWithResourceManager) {
		// Detach the object from container resource
		//
		MultiResourceEObject mrEObj = (MultiResourceEObject) o;
		MultiFileXMIResourceImpl currentResource = (MultiFileXMIResourceImpl) o
				.eResource();

		if (currentResource != null) {
			currentResource.detached(o);
		}

		List newResourceDescriptors = null;
		Set modifiedResources = (Set) options
				.get(MultiFileXMISaveImpl.MODIFIED_RESOURCE_SET);

		MultiFileXMIResourceImpl res = null;
		if (canSaveTogether(options, o)) {
			MultiFileResourceSetImpl mfResourceSet = ((MultiFileResourceSetImpl) resourceSet);

			// if the uri has a temporary URI, use it instead to locate the
			// resource that is in process of saving
			//
			URI tempURI = (URI) mfResourceSet.getURIToTempURIMap().get(uri);
			if (tempURI != null) {
				uri = tempURI;
			}

			res = (MultiFileXMIResourceImpl) mfResourceSet.getResource(uri);
			if (res != null) {
				if (!res.getContents().contains(o)) {
					if (res.getFinalURI() == res.getURI()) {
						if (MultiFileXMISaveImpl.checkModifyRequired(options)) {
							// not a temp file of fail-safe persistence
							// transaction
							// check for writeable
							//
							MultiFileSaveUtil.checkModify(res);
						}
					}

					addTo(res, mrEObj);

					ResourceDescriptor desc = null;
					try {
						if(registerWithResourceManager) {
							// register this new object with ResourceManager
							//
							ResourceManager resMgr = getResourceManagerFor(o,
									modifiedResources);
							desc = registerWithResourceManager(resMgr, o, res
									.getFinalURI());
							if (desc != null && modifiedResources != null) {
								modifiedResources.add(resMgr.eResource());
							}
						}

						res.setModified(true);
						mfResourceSet.save(res, options, false);

						res.attachedAll(o);
					} catch (Exception e) {
						// rollback
						//

						// remove this object from the resource
						res.getContents().remove(mrEObj);
						mrEObj.eSetResource((Resource.Internal) null);

						// remove the created ResourceDescriptor
						if (desc != null) {
							EcoreUtil.remove(desc);
						}

						// re-attach to container resource
						if (currentResource != null) {
							currentResource.attached(o);
						}

						throw new MultiFileIOException(e.getMessage());
					}
				}
				return res;
			}
		}

		res = (MultiFileXMIResourceImpl) resourceSet.createResource(uri);
		addTo(res, mrEObj);
		if(mrEObj instanceof MethodPlugin) {
			addResourceManager(res);
		}

		Map objToContainerMap = null;
		try {
			if(registerWithResourceManager) {
				newResourceDescriptors = registerWithResourceManager(res,
					modifiedResources);
			}

			// back up the container references and set content object's
			// container to null
			//
			objToContainerMap = removeContainers(res);

			res.save(options);

			res.attachedAll(o);
		} catch (Exception e) {
			CommonPlugin.INSTANCE.log(e);

			// rollback
			//

			// remove the failed reousrce from the resource set
			resourceSet.getResources().remove(res);
			mrEObj.eSetResource((Resource.Internal) null);

			// remove the created ResourceDescriptors
			if(newResourceDescriptors != null && !newResourceDescriptors.isEmpty()) {
				for (Iterator iter = newResourceDescriptors.iterator(); iter
				.hasNext();) {
					EcoreUtil.remove((EObject) iter.next());
				}
			}

			// re-attach to container resource
			if (currentResource != null) {
				currentResource.attached(o);
			}

			throw new MultiFileIOException(e.getMessage());
		} finally {
			// restore the container references for the content objects
			//
			restoreContainers(res, objToContainerMap);
		}

		if(registerWithResourceManager) {
			ResourceManager resMgr = addNewResourceManager(res);
			if (resMgr == null) {
				if (o instanceof MethodPlugin) {
					// create new ResourceManager for new MethodPlugin
					//
					resMgr = getResourceManagerFor(o, modifiedResources);
				}
			} else if (modifiedResources != null) {
				modifiedResources.add(resMgr.eContainer().eResource());
			}
		}

		res.updateTimeStamps();

		return res;
	}

	private static Map removeContainers(Resource resource) {
		int size = resource.getContents().size();
		Map objToContainerMap = new HashMap();
		for (int i = 0; i < size; i++) {
			MultiResourceEObject multiResEObj = (MultiResourceEObject) resource
					.getContents().get(i);
			InternalEObject container = (InternalEObject) multiResEObj
					.eContainer();
			if (container != null) {
				objToContainerMap.put(multiResEObj, container);
				multiResEObj.eBasicSetContainer(null, multiResEObj
						.eContainerFeatureID());
			}
		}
		return objToContainerMap;
	}

	/**
	 * Gets the containers of objects in resource's contents
	 * 
	 * @param resource
	 * @return map of object to container entries
	 */
	static Map getContainers(Resource resource) {
		int size = resource.getContents().size();
		Map objToContainerMap = new HashMap();
		for (int i = 0; i < size; i++) {
			MultiResourceEObject multiResEObj = (MultiResourceEObject) resource
					.getContents().get(i);
			InternalEObject container = (InternalEObject) multiResEObj
					.eContainer();
			if (container != null) {
				objToContainerMap.put(multiResEObj, container);
			}
		}
		return objToContainerMap;

	}

	/**
	 * Restores the containers of the objects in resource's contents that
	 * previously saved in a map returned from getContainers(Resource resource)
	 * or removeContainers(Resource resource)
	 * 
	 * @param resource
	 * @param objToContainerMap
	 * @see #getContainers(Resource)
	 * @see #removeContainers(Resource)
	 */
	private static void restoreContainers(Resource resource,
			Map objToContainerMap) {
		int size = resource.getContents().size();
		for (int i = 0; i < size; i++) {
			MultiResourceEObject multiResEObj = (MultiResourceEObject) resource
					.getContents().get(i);
			InternalEObject container = (InternalEObject) objToContainerMap
					.get(multiResEObj);
			if (container != null) {
				multiResEObj.eBasicSetContainer(container, multiResEObj
						.eContainerFeatureID());
			}
		}
	}

	public static void checkModify(Resource resource) {
		checkModify(Collections.singleton(resource));
	}

	static void doCheckModify(Collection<Resource> resources) {
		ArrayList<String> pathList = new ArrayList<String>();
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			Resource resource = (Resource) iter.next();
			// skip checking on resource that is currently being saved to a temporary
			// file
			//
			if(resource instanceof MultiFileXMIResourceImpl && ((MultiFileXMIResourceImpl)resource).hasTempURI()) {
				continue;
			}
			pathList.add(FileManager.toFileString(resource.getURI()));
		}
		if(!pathList.isEmpty()) {
			String[] paths = new String[pathList.size()];
			pathList.toArray(paths);
			IStatus status = FileManager.getInstance().checkModify(paths,
					PersistencePlugin.getDefault().getContext()/*MsgBox.getDefaultShell()*/);
			if (!status.isOK()) {
				String msg = UmaUtil.getMessage(status);
				if (msg == null) {
					msg = NLS.bind(PersistenceResources.modifyFileError_msg, Arrays
							.asList(paths));
				}
				throw new MultiFileIOException(msg);
			}
		}
	}
	
	public static void checkModify(Collection<Resource> resources) {
		Map<ILibraryResourceSet, Collection<Resource>> libResourceSetToResourcesMap = new HashMap<ILibraryResourceSet, Collection<Resource>>();
		Collection<Resource> otherResources = new ArrayList<Resource>();
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			Resource resource = (Resource) iter.next();
			ResourceSet resourceSet = resource.getResourceSet();
			if(resourceSet instanceof ILibraryResourceSet) {
				Collection<Resource> collection = libResourceSetToResourcesMap.get(resourceSet);
				if(collection == null) {
					collection = new ArrayList<Resource>();
					libResourceSetToResourcesMap.put((ILibraryResourceSet) resourceSet, collection);					
				}
				collection.add(resource);
			}
			else {
				otherResources.add(resource);
			}
		}
		if(!libResourceSetToResourcesMap.isEmpty()) {
			for (Iterator<Map.Entry<ILibraryResourceSet, Collection<Resource>>> iter = libResourceSetToResourcesMap.entrySet().iterator(); iter.hasNext();) {
				Map.Entry<ILibraryResourceSet, Collection<Resource>> entry = iter.next();
				ILibraryResourceSet resourceSet = entry.getKey();			
				Resource[] resourceArray = new Resource[entry.getValue().size()];
				entry.getValue().toArray(resourceArray);
				try {
					resourceSet.checkModify(resourceArray, PersistencePlugin.getDefault().getContext()/*MsgBox.getDefaultShell()*/);
				} catch (LibraryResourceException e) {
					throw new MultiFileIOException(e.getMessage());
				}
			}
		}
		if(!otherResources.isEmpty()) {
			doCheckModify(otherResources);
		}
	}
	
	public static void checkOutOfSynch(Collection<Resource> resources, Map saveOptions) {
		Collection<Resource> resourcesToCheckOutOfSynch;
		Collection<Resource> overwritableResources = (Collection<Resource>) saveOptions.get(
				ILibraryPersister.FailSafeMethodLibraryPersister.OPTIONS_OVERWRITABLE_RESOURCES);				
		if(overwritableResources != null && !overwritableResources.isEmpty()) {
			resourcesToCheckOutOfSynch = new ArrayList<Resource>(resources);
			resourcesToCheckOutOfSynch.removeAll(overwritableResources);
		}
		else {
			resourcesToCheckOutOfSynch = resources;
		}
		if(!resourcesToCheckOutOfSynch.isEmpty()) {
			checkOutOfSynch(resourcesToCheckOutOfSynch);
		}
	}

	private static void checkOutOfSynch(Collection resources) {
		// check for out-of-synch
		//
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			MultiFileXMIResourceImpl res = (MultiFileXMIResourceImpl) iter
					.next();
			if(res.isLoaded() && !res.hasTempURI()) {
				File file = new File(res.getURI().toFileString());
				if(file.exists()) {
//					long lastModified = file.lastModified();
//					if (res.getFileLastModified() != lastModified && !same(lastModified, res.getFileLastModified())) {
//						String msg = NLS.bind(
//								PersistenceResources.resourceOutOfSynch_msg, res
//								.getURI().toFileString());
//						throw new MultiFileIOException(msg);
//					}
					
					if(!res.isSynchronized()) {
						String msg = NLS.bind(
								PersistenceResources.resourceOutOfSynch_msg,
								res.getURI().toFileString());
						throw new MultiFileIOException(msg);						
					}
				}
			}
		}
	}

	/**
	 * Saves the existing own resource of a contained EObject. Adds resources
	 * that have been modifed after this call to the MODIFIED_RESOURCE_SET of
	 * the save options.
	 * 
	 * @param resource
	 * @param options
	 * @return true if the given resource has been saved successfully, false otherwise
	 */
	public static boolean save(Resource resource, Map options) {
		if (resource.getContents().isEmpty())
			return false;

		Set modifiedResources = (Set) options
				.get(MultiFileXMISaveImpl.MODIFIED_RESOURCE_SET);

		// should not change resource location automatically without letting
		// user know about it
		//
		// adjustLocation(resource, modifiedResources);

		// back up the container references and set content object's container
		// to null
		//
		Map objToContainerMap = removeContainers(resource);
		try {
			resource.save(options);
		} catch (IOException e) {
			e.printStackTrace();
			throw new MultiFileIOException(e.getMessage());
		} finally {
			// restore the container references for the content objects
			//
			restoreContainers(resource, objToContainerMap);
		}

		ResourceManager resMgr = addNewResourceManager(resource);
		if (resMgr != null && modifiedResources != null) {
			modifiedResources.add(resMgr.eContainer().eResource());
		}
		
		return true;
	}

	/**
	 * 
	 * @param resource
	 * @return ResourceManager that is just added to the resource manager tree
	 *         or null.
	 */
	private static ResourceManager addNewResourceManager(Resource resource) {
		ResourceManager resMgr = getResourceManager(resource);
		MethodElement e = PersistenceUtil.getMethodElement(resource);
		if (e == null) {
			return null;
		}
		EObject container = e.eContainer();
		if (resMgr != null && container != null && resMgr.eContainer() == null) {
			// new ResourceManager is added to the resource
			//
			ResourceManager parentResMgr = getResourceManager(container
					.eResource());

			// check if resMgr is already a sub manager of parentResMgr before
			// adding it
			//
			if (!parentResMgr.getSubManagers().contains(resMgr)) {
				parentResMgr.getSubManagers().add(resMgr);				
				registerWithResourceManager(parentResMgr, e, MultiFileSaveUtil.getFinalURI(resource));
				return resMgr;
			}

			// registerWithResourceManager(parentResMgr, resMgr,
			// resMgr.eResource().getURI().appendFragment(resMgr.getGuid()));
			// return resMgr;
		}
		return null;
	}

	private static String toFileString(EObject eObj) {
		return eObj.eResource().getResourceSet().getURIConverter().normalize(
				eObj.eResource().getURI()).toFileString();
	}

	static boolean hasOwnFolder(Object e) {
		return e instanceof MethodPlugin || e instanceof ProcessComponent;
	}

	static boolean hasOwnResource(Object obj, Collection saveSeparatelyClassSet) {
		if (obj instanceof MethodUnit)
			return true;
		if (saveSeparatelyClassSet == null)
			return false;
		for (Iterator iter = saveSeparatelyClassSet.iterator(); iter.hasNext();) {
			EClass eCls = (EClass) iter.next();
			if (eCls.isInstance(obj))
				return true;
		}
		return false;
	}

	static URI createFileURI(MethodElement e) {
		if (e.eContainer() == null)
			return ((MultiFileXMIResourceImpl) e.eResource()).getFinalURI();

		// Handle ProcessComponent specially. ProcessComponent objects are
		// stored as following in the method library:
		// <Method Library>
		// |_ <Method Plugin>
		// |_processes
		// |_capability_patterns
		// |_delivery_processes
		// |_process_contributions
		//
		if (e instanceof ProcessComponent) {
			MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
			if(plugin == null) {
				return null;
			}
			Resource resource = plugin.eResource();
			if (resource == null) {
			}
			String pluginDir = null;
			if (! UmaUtil.hasDirectResource(plugin)) {
				resource = null;
			}
			if(resource != null) {
				pluginDir = new File(getFinalURI(resource).toFileString()).getParent();
			}
			else {
				MethodLibrary lib = (MethodLibrary) plugin.eContainer();
				if (lib != null) {
					String libDir = new File(((MultiFileXMIResourceImpl) lib
							.eResource()).getFinalURI().toFileString()).getParent();
					pluginDir = libDir + File.separator + plugin.getName();
				}
			}
			if(pluginDir == null) {
				// plugin is already deleted
				//
				return null;
			}

			String relativeDir;
			org.eclipse.epf.uma.Process proc = ((ProcessComponent) e)
					.getProcess();
			if (proc instanceof CapabilityPattern) {
				relativeDir = CAPABILITY_PATTERN_PATH;
			} else if (proc instanceof DeliveryProcess) {
				relativeDir = DELIVERY_PROCESS_PATH;
			} else {
				relativeDir = ""; //$NON-NLS-1$
			}

			String path = pluginDir + File.separator + relativeDir
					+ File.separator + e.getName() + File.separator
					+ DEFAULT_MODEL_FILENAME;
			return URI.createFileURI(path);
		} else if (e instanceof BreakdownElementDescription) {
			String dir = null;
			try {
				dir = new File(((MultiFileXMIResourceImpl) UmaUtil
						.getProcessComponent(e).eResource()).getFinalURI()
						.toFileString()).getParent();
			} catch (RuntimeException ex) {
				throw ex;
			}
			return URI.createFileURI(dir + File.separator
					+ DEFAULT_CONTENT_FILENAME);
		} else if (e instanceof ContentDescription) {
			URI uri;
			ContentDescription content = (ContentDescription) e;
			String path = MethodLibraryPersister.getCorrectPath(content);
			if (path == null) {
				String dir = ((MultiFileXMIResourceImpl) e.eResource())
						.getFinalURI().trimSegments(1).toFileString()
						+ File.separator;
				path = MethodLibraryPersister.getNextAvailableFileName(dir,
						content);
			}
			uri = URI.createFileURI(path);
			return uri;
		} else if (e instanceof MethodConfiguration) {			
			String dir = new StringBuffer(((MultiFileXMIResourceImpl) e
					.eContainer().eResource()).getFinalURI().trimSegments(1)
					.toFileString()).append(File.separator).append(
					METHOD_CONFIGURATION_FOLDER_NAME).append(File.separator)
					.toString();
			String path = MethodLibraryPersister.getNextAvailableFileName(dir,
					StrUtil.makeValidFileName(e.getName()),
					(MultiResourceEObject) e);
			return URI.createFileURI(path);
		}

		StringBuffer path = new StringBuffer();
		EObject lastContainer = null;
		for (MethodElement obj = (MethodElement) e.eContainer(); obj != null; obj = (MethodElement) obj
				.eContainer()) {
			lastContainer = obj;
			if (obj instanceof MethodLibrary) {
				path.insert(0, new File(((MultiFileXMIResourceImpl) obj
						.eResource()).getFinalURI().toFileString())
						.getParentFile().getAbsolutePath());
				break;
			} else {
				path.insert(0, obj.getName()).insert(0, File.separatorChar);
			}
		}
		if (lastContainer == null) {
			return e.eResource() != null ? ((MultiFileXMIResourceImpl) e
					.eResource()).getFinalURI() : null;
		}
		if (!(lastContainer instanceof MethodLibrary)) {
			path.insert(0, new File(toFileString(lastContainer))
					.getParentFile().getParentFile().getAbsolutePath());
		}

		String modelFileName;
		if (e instanceof MethodPlugin) {
			modelFileName = DEFAULT_PLUGIN_MODEL_FILENAME;
		} else {
			modelFileName = MultiFileSaveUtil.DEFAULT_MODEL_FILENAME;
		}
		URI uri = URI.createFileURI(path.toString() + File.separator
				+ MultiFileSaveUtil.createDirName(e) + File.separator
				+ modelFileName);
		return uri;
	}

	public static void delete(File file) {
		File[] files = file.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				delete(files[i]);
			}
		}
		file.delete();
	}

	/**
	 * 
	 * @param e
	 * @param uri
	 * @param modifiedResources
	 *            output of resources that have been changed after this call.
	 */
	static void setURIMapping(EObject e, URI uri, Set modifiedResources) {
		MultiFileURIConverter uriConverter = (MultiFileURIConverter) e
				.eResource().getResourceSet().getURIConverter();
		uriConverter.setURIMapping(e, uri, modifiedResources);
	}

	/**
	 * 
	 * @param resource
	 * @param modifiedResources
	 *            output of resources that have been changed after this call
	 */
	static void updateURIMappings(MultiFileXMIResourceImpl resource,
			Set modifiedResources) {
		updateURIMappings(resource, modifiedResources, true);
	}

	/**
	 * Checks if the given resourceSet has the loaded resource with the given
	 * uri
	 * 
	 * @param resourceSet
	 * @param uri
	 * @return
	 */
	static boolean hasLoadedResource(ResourceSet resourceSet, URI uri) {
		for (Iterator iter = resourceSet.getResources().iterator(); iter
				.hasNext();) {
			Resource resource = (Resource) iter.next();
			if (resource.isLoaded() && resource.getURI().equals(uri)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Update the URIMappings with the final URI of the given resource
	 * 
	 * @param resource
	 */
	static void updateURIMappings(MultiFileXMIResourceImpl resource,
			Set modifiedResources, boolean afterMove) {
		updateURIMappings(resource, resource.getFinalURI(), modifiedResources,
				afterMove);
	}

	static void updateURIMappings(MultiFileXMIResourceImpl resource, URI uri,
			Set modifiedResources, boolean afterMove) {
		// the resource URI has been changed, reset all the cached resolved URI
		// in all offstring resource descriptors
		// of its manager, if it has one.
		//
		HashMap oldURIToResourceDescriptorMap = null;
		ResourceManager resMgr = getResourceManager(resource);
		if (resMgr != null) {
			oldURIToResourceDescriptorMap = new HashMap();
			for (Iterator iter = resMgr.eAllContents(); iter.hasNext();) {
				Object obj = iter.next();
				if (obj instanceof ResourceDescriptor) {
					ResourceDescriptor desc = ((ResourceDescriptor) obj);
					oldURIToResourceDescriptorMap.put(desc.getResolvedURI(),
							desc);
					if (afterMove) {
						desc.clearResolvedURI();
					}
				}
			}
		}

		if (!resource.getContents().isEmpty()) {
			EObject element = PersistenceUtil.getMethodElement(resource);
			// setURIMapping(element, resource.getFinalURI(),
			// modifiedResources);
			MultiFileURIConverter uriConverter = (MultiFileURIConverter) resource
					.getResourceSet().getURIConverter();
			uriConverter.setURIMapping(element, uri, modifiedResources,
					afterMove);
		}

		// for (Iterator iter = resource.getContents().iterator();
		// iter.hasNext();) {
		// EObject element = (EObject) iter.next();
		// setURIMapping(element, resource.getURI(), modifiedResources);
		// }

		if (oldURIToResourceDescriptorMap != null) {
			// go thru the list of loaded resources in resource set to update
			// the URI
			//
			for (Iterator iter = resource.getResourceSet().getResources()
					.iterator(); iter.hasNext();) {
				Resource res = (Resource) iter.next();
				ResourceDescriptor desc = (ResourceDescriptor) oldURIToResourceDescriptorMap
						.get(res.getURI());
				if (desc != null) {
					if (afterMove) {
						res.setURI(desc.getResolvedURI());
					} else if (res.isLoaded()) {
						desc.clearResolvedURI();
						res.setURI(desc.getResolvedURI());
					}
				}
			}
		}
	}

	/**
	 * @param resMgr
	 * @param o
	 * @param uri
	 */
	// private static void setUri(ResourceManager resMgr, MethodElement e, URI
	// uri) {
	// for (Iterator iter = resMgr.getResourceDescriptors().iterator();
	// iter.hasNext();) {
	// ResourceDescriptor resDesc = (ResourceDescriptor) iter.next();
	// if(resDesc.getId().equals(e.getGuid())) {
	// // change other URIs that are changed as result of this URI change
	// //
	// if(uri.fragment() == null) {
	// URI oldDir = URI.createFileURI(new
	// File(resDesc.getResolvedURI().toFileString()).getParent() +
	// File.separator);
	// URI newDir = URI.createFileURI(new File(uri.toFileString()).getParent() +
	// File.separator);
	// for (Iterator iterator = resMgr.getResourceDescriptors().iterator();
	// iterator
	// .hasNext();) {
	// ResourceDescriptor element = (ResourceDescriptor) iterator.next();
	// URI currentUri = element.getResolvedURI();
	// URI newUri = currentUri.replacePrefix(oldDir, newDir);
	// if(newUri != null) {
	// element.setResolvedURI(newUri);
	// }
	// }
	// }
	// resDesc.setResolvedURI(uri);
	// return;
	// }
	// }
	// ResourceDescriptor resDesc =
	// ResourcemanagerFactory.eINSTANCE.createResourceDescriptor();
	// resDesc.setId(e.getGuid());
	// resDesc.setResolvedURI(uri);
	// resMgr.getResourceDescriptors().add(resDesc);
	// }
	/**
	 * Gets the right ResourceManager for the given EObject, creates new
	 * ResourceManager if it does not exist yet.
	 * 
	 * @param modifiedResources
	 *            output of resources that have been changed after this call.
	 */
	static ResourceManager getResourceManagerFor(EObject eObj,
			Set modifiedResources) {
		Resource resource = eObj.eContainer() != null ? eObj.eContainer()
				.eResource() : eObj.eResource();
		ResourceManager resMgr = getResourceManager(resource);
		if (resMgr == null) {
			resMgr = ResourcemanagerFactory.eINSTANCE.createResourceManager();

			// add to beginning of the resource's contents
			//
			resource.getContents().add(0, resMgr);

			if (modifiedResources != null) {
				modifiedResources.add(resource);
			}
			EObject container = getContainerWithDirectResource((InternalEObject) eObj);
			if (container != null && container.eResource() != resource) {
				ResourceManager parentResMgr = getResourceManagerFor(container,
						modifiedResources);
				if (parentResMgr != null) {
					parentResMgr.getSubManagers().add(resMgr);
					if (modifiedResources != null) {
						modifiedResources.add(parentResMgr.eResource());
					}
				}
			}
		}

		return resMgr;

	}

	static List registerWithResourceManager(MultiFileXMIResourceImpl resource,
			Set modifiedResources) {
		ResourceManager resMgr = getResourceManagerFor(
				PersistenceUtil.getMethodElement(resource), modifiedResources);

		List resourceDescriptors = new ArrayList();
		for (Iterator iter = resource.getContents().iterator(); iter.hasNext();) {
			Object element = iter.next();
			if(element instanceof MethodElement) {
				ResourceDescriptor resDesc = registerWithResourceManager(resMgr,
						element, resource.getFinalURI());
				if (resDesc != null) {
					resourceDescriptors.add(resDesc);
				}
			}
		}
		if (!resourceDescriptors.isEmpty() && modifiedResources != null) {
			modifiedResources.add(resMgr.eResource());
		}
		return resourceDescriptors;
	}

	public static ResourceDescriptor registerWithResourceManager(
			ResourceManager resMgr, Object element, URI uri) {
		String guid = getGuid(element);
		if (resMgr.getResourceDescriptor(guid) == null) {
			return createResourceDescriptor(resMgr, guid, uri);
		} else {
			return null;
		}
	}

	// /**
	// * Creates a new ResourceDescriptor for the given MethodElement and add it
	// to the given ResourceManager
	// *
	// * @param e
	// * @return newly added ResourceDescriptor
	// */
	// private static ResourceDescriptor
	// createResourceDescriptor(ResourceManager resMgr, MethodElement e) {
	// return createResourceDescriptor(resMgr, e.getGuid(), createFileURI(e));
	// }

	/**
	 * Creates a new ResourceDescriptor with the given id and resolvedURI, then
	 * adds it to the given resMgr
	 * 
	 * @param resMgr
	 * @param id
	 * @param resolvedURI
	 * @return
	 */
	private static ResourceDescriptor createResourceDescriptor(
			ResourceManager resMgr, String id, URI resolvedURI) {
		ResourceDescriptor resDesc = ResourcemanagerFactory.eINSTANCE
				.createResourceDescriptor();
		resDesc.setId(id);
		resMgr.getResourceDescriptors().add(resDesc);
		resDesc.setResolvedURI(resolvedURI);
		return resDesc;
	}

	// static void setUri(MultiFileURIConverter uriConverter, MethodElement e,
	// URI uri) {
	// setUri(uriConverter, e, uri, false);
	// }

	// static void setUri(MultiFileURIConverter uriConverter, MethodElement e,
	// URI uri, boolean saveNow) {
	// ResourceManager resMgr = uriConverter.getResourceManager();
	// if(resMgr == null) {
	// resMgr = ResourcemanagerFactory.eINSTANCE.createResourceManager();
	// uriConverter.setResourceManager(resMgr);
	// }
	// setUri(resMgr, e, uri);
	// resMgr.eResource();
	// }

	/**
	 * @param element
	 */
	public static void setGuid(MethodElement e) {
		if (e.getGuid() == null || e.getGuid().trim().length() == 0) {
			e.setGuid(EcoreUtil.generateUUID());
		}
	}

	/**
	 * Saves the given MethodElement in its own file while still preserving the
	 * existing containment association if there is any
	 * 
	 * @param e
	 * @throws LibraryResourceException 
	 */
	public static void save(MethodElement e, Map options) throws LibraryResourceException {
		MultiFileXMIResourceImpl resource = (MultiFileXMIResourceImpl) e
				.eResource();
		MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) resource
				.getResourceSet();
		if (options == null) {
			options = resourceSet.getDefaultSaveOptions();
		}
		EObject container = e.eContainer();

		Set modifiedResources = new HashSet();
		options.put(MultiFileXMISaveImpl.MODIFIED_RESOURCE_SET,
				modifiedResources);
		try {
			URI uri = MultiFileSaveUtil.createURI(e, resourceSet);
			boolean createResource = resource == null
					|| (container != null && resource == container.eResource());
			if (createResource) {
				MultiFileSaveUtil.save(e, uri, options);
			} else {
				URI oldUri = resource.getURI();
				if (!oldUri.equals(uri)) {

					// resource's location is changed.
					// move the resource, then change its URI if the resource
					// move is successful.
					//   
					if (FileManager.getInstance().move(oldUri.toFileString(),
							uri.toFileString())) {
						// MultiFileURIConverter uriConverter =
						// (MultiFileURIConverter)
						// resource.getResourceSet().getURIConverter();
						// MultiFileSaveUtil.setUri(uriConverter, e, uri);
						resource.setURI(uri);
						updateURIMappings(resource, modifiedResources);
					}
				}

				if (resource.isModified()) {
					MultiFileSaveUtil.save(resource, options);
				}
			}

			resourceSet.saveModifiedResources(options);
		} finally {

		}

	}
	
	public static URI createURI(MethodElement e, ResourceSet resourceSet) {
		URI uri = null;
		if(resourceSet instanceof MultiFileResourceSetImpl) {
			IURIProvider uriProvider = ((MultiFileResourceSetImpl)resourceSet).getURIProvider();
			if(uriProvider != null) {
				uri = uriProvider.getURI(e);
			}
		}
		if(uri == null) {
			uri = createFileURI(e);
		}
		return uri;
	}

	public static void save(ResourceSet resourceSet, MethodElement e,
			Map options) {
		URI uri = createURI(e, resourceSet);
		save(resourceSet, e, uri, options);
	}

	static void adjustLocation(Resource resource, Set modifiedResources) {
		MethodElement e = PersistenceUtil.getMethodElement(resource);
		if (e == null)
			return;
		URI newFile = createURI(e, resource.getResourceSet());
		if (newFile != null && adjustLocation(resource, newFile)) {
			resource.setURI(newFile);
			updateURIMappings((MultiFileXMIResourceImpl) resource,
					modifiedResources);
		}
	}

	static URI getNewURI(MultiFileXMIResourceImpl resource) {
		MethodElement e = PersistenceUtil.getMethodElement(resource);
		if (e == null) {
			return null;
		}
		URI newFile = createURI(e, resource.getResourceSet());
		if (newFile != null && adjustLocationRequired(resource, newFile)) {
			return newFile;
		}
		return null;
	}

	static boolean prepareAdjustLocation(MultiFileXMIResourceImpl resource,
			Set modifiedResources) {
		URI newFile = getNewURI(resource);
		if (newFile != null) {
			resource.backUpURI();
			resource.setFinalURI(newFile);
			updateURIMappings((MultiFileXMIResourceImpl) resource,
					modifiedResources, false);
			return true;
		}
		return false;
	}

	public static String getGuid(Object e) {
		if(e instanceof InternalEObject) {
			InternalEObject o = (InternalEObject) e;
			if(o.eIsProxy()) {
				URI uri = o.eProxyURI();
				if(uri != null && MultiFileURIConverter.SCHEME.equals(uri.scheme())) {
					String guid = uri.fragment();
					if(guid != null) {
						return guid;
					}
				}
			}
		}
		if (e instanceof MethodElement) {
			return ((MethodElement) e).getGuid();
		} else if (e instanceof ResourceManager) {
			return ((ResourceManager) e).getGuid();
		}
		return null;
	}

	static boolean adjustLocationRequired(MultiFileXMIResourceImpl resource,
			URI newURI) {
		File oldFile = new File(resource.getFinalURI().toFileString());
		File newFile = new File(newURI.toFileString());
		return !oldFile.equals(newFile);
	}

	/**
	 * Checks if the resource's URI has been changed and adjust the resource's
	 * location.
	 * 
	 * @param oldUri
	 * @param newUri
	 */
	static boolean adjustLocation(Resource resource, URI newUri) {
		File oldFile = new File(resource.getURI().toFileString());
		File newFile = new File(newUri.toFileString());
		if (oldFile.equals(newFile)) {
			return false;
		}
		return move(resource, oldFile, newFile);
	}

	static boolean move(Resource resource, File oldFile, File newFile) {
		boolean ret = true;
		if (oldFile.equals(newFile)) {
			return false;
		}
		Object obj = PersistenceUtil.getMethodElement(resource);
		if (hasOwnFolder(obj)) {
			String oldDir = oldFile.getParentFile().toString();
			if (!oldFile.getParentFile().equals(newFile.getParentFile())) {
				if (FileManager.getInstance().rename(oldFile.getParentFile(),
						newFile.getParentFile())) {
					if (DEBUG) {
						System.out
								.println("Directory '" + oldDir + "' is successfully moved to '" + newFile.getParentFile() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
				} else {
					ret = false;
					if (DEBUG) {
						System.out
								.println("Could not move directory '" + oldDir + "' to '" + newFile.getParentFile() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
				}
			}
			if (!oldFile.getName().equals(newFile.getName())) {
				oldFile = new File(newFile.getParentFile(), oldFile.getName());
				if (FileManager.getInstance().rename(oldFile, newFile)) {
					if (DEBUG) {
						System.out
								.println("File '" + oldFile + "' is successfully moved to '" + newFile + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
				} else {
					ret = false;
					if (DEBUG) {
						System.out
								.println("Could not move file '" + oldFile + "' to '" + newFile + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					}
				}
			}
			return ret;
		} else if (oldFile.exists()) /* if(oldFile.getParentFile().equals(newFile.getParentFile())) */{
			String oldFileStr = oldFile.toString();
			if (FileManager.getInstance().rename(oldFile, newFile)) {
				if (DEBUG) {
					System.out
							.println("File '" + oldFileStr + "' is successfully moved to '" + newFile + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
				return true;
			} else {
				if (DEBUG) {
					System.out
							.println("Could not move file '" + oldFileStr + "' to '" + newFile + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}
			}
		}
		return false;
	}

	/**
	 * 
	 * @param resMgr
	 * @param guid
	 * @param excludedResManagers
	 *            ResourceManager that are excluded from this search
	 * @return
	 */
	public static ResourceDescriptor findResourceDescriptor(
			ResourceManager resMgr, String guid,
			Collection excludedResManagers, boolean resolveProxy) {
		try {
			ResourceDescriptor desc = resMgr.getResourceDescriptor(guid);
			if (desc != null)
				return desc;
			// TODO: (Phong) performance improvement needed to avoid loading
			// resources excessively
			//
			Iterator iter;
			if (resolveProxy) {
				iter = new ArrayList(resMgr.getSubManagers()).iterator();
			} else {
				iter = ((InternalEList) resMgr.getSubManagers())
						.basicIterator();
			}
			while (iter.hasNext()) {
				ResourceManager mgr = (ResourceManager) iter.next();
				if (mgr != null
						&& !mgr.eIsProxy()
						&& (excludedResManagers == null || !excludedResManagers
								.contains(mgr))) {
					desc = findResourceDescriptor(mgr, guid,
							excludedResManagers, resolveProxy);
					if (desc != null)
						return desc;
				}
			}
			return null;
		} catch (RuntimeException e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param resMgr
	 * @return how many ResourceDescriptors are in the resMgr and its
	 *         SubManagers
	 */
	public static int getCountResourceDescriptors(ResourceManager resMgr) {
		int count = resMgr.getResourceDescriptors().size();
		for (Iterator iter = resMgr.getSubManagers().iterator(); iter.hasNext();) {
			ResourceManager mgr = (ResourceManager) iter.next();
			count += getCountResourceDescriptors(mgr);
		}
		return count;
	}

	/**
	 * Gets the resource descriptor for the given resource
	 * 
	 * @param resource
	 * @return
	 */
	public static ResourceDescriptor getResourceDescriptor(Resource resource) {
		ResourceManager containerResMgr = null;
		MethodElement me = null;
		for (Iterator iter = resource.getContents().iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof MethodLibrary) {
				ResourceManager resMgr = getResourceManager(resource);
				if (resMgr != null) {
					ResourceDescriptor desc = resMgr
							.getResourceDescriptor(((MethodElement) element)
									.getGuid());
					// hack to add missing ResourceDescriptor for library's
					// resource
					//
					if (desc == null) {
						desc = registerWithResourceManager(resMgr, element,
								((MultiFileXMIResourceImpl) resource)
										.getFinalURI());
					}
					return desc;
				}
			}
			if (me == null && element instanceof MethodElement) {
				me = (MethodElement) element;
			}
			if (containerResMgr == null && element instanceof InternalEObject) {
				EObject container = getContainerWithDirectResource((InternalEObject) element);
				if (container != null) {
					containerResMgr = getResourceManager(container.eResource());
				}
			}
			if (me != null && containerResMgr != null) {
				break;
			}
		}

		if(me != null) {
			if (containerResMgr != null) {
				return containerResMgr.getResourceDescriptor(((MethodElement) me)
						.getGuid());
			}
			else if(me.eContainer() == null) {
				// this element has been loaded before its container accessed it
				// try to find its resource descriptor using root resource manager
				//
				ResourceSet resourceSet = resource.getResourceSet();
				if(resourceSet instanceof MultiFileResourceSetImpl) {
					ResourceManager rootResMgr = ((MultiFileResourceSetImpl)resourceSet).getRootResourceManager();
					Iterator iter = new AbstractTreeIterator(rootResMgr) {

						/**
						 * Comment for <code>serialVersionUID</code>
						 */
						private static final long serialVersionUID = 1L;

						protected Iterator getChildren(Object object) {
							if(object instanceof ResourceManager) {
								ArrayList children = new ArrayList();
								ResourceManager resMgr = (ResourceManager)object; 
								children.addAll(resMgr.getResourceDescriptors());
								children.addAll(resMgr.getSubManagers());
								return children.iterator();
							}
							return Collections.EMPTY_LIST.iterator();
						}
						
					};
					URI uri = getFinalURI(resource);
					while(iter.hasNext()) {
						Object o = iter.next();
						if(o instanceof ResourceDescriptor) {
							ResourceDescriptor resDesc = (ResourceDescriptor) o;
							if(uri.equals(resDesc.getResolvedURI())) {
								return resDesc;
							}
						}
					}
				}
			}
		}
		return null;
	}

	// public static ResourceDescriptor findResourceDescriptor(MethodElement e)
	// {
	// ResourceManager resMgr = null;
	// if(e.eResource() != null) {
	// resMgr = getResourceManager(e.eResource());
	// }
	// ResourceDescriptor desc = null;
	// Collection excludedResMgrs = null;
	// if(resMgr != null) {
	// desc = findResourceDescriptor(resMgr, e.getGuid());
	// if(desc != null) return desc;
	// excludedResMgrs = Collections.singletonList(resMgr);
	// }
	//		
	// MethodLibrary lib = UmaUtil.getMethodLibrary(e);
	// resMgr = getResourceManager(lib.eResource());
	//		
	// return findResourceDescriptor(resMgr, e.getGuid(), excludedResMgrs);
	// }

	/**
	 * Gets the ResourceManager in the contents of the given resource
	 * 
	 * @param resource
	 * @return
	 */
	public static ResourceManager getResourceManager(Resource resource) {
		ResourceManager resMgr = null;
		for (Iterator iter = resource.getContents().iterator(); iter.hasNext();) {
			Object element = (Object) iter.next();
			if (element instanceof ResourceManager) {
				resMgr = (ResourceManager) element;
				break;
			}
		}

		return resMgr;
	}

	static InternalEObject getContainerWithDirectResource(InternalEObject obj) {
		if (obj.eContainer() == null)
			return null;
		InternalEObject container = (InternalEObject) ((InternalEObject) obj
				.eContainer());
		if (container.eDirectResource() != null) {
			return container;
		}
		return getContainerWithDirectResource(container);
	}

	static String getHREF(Resource resource, Object obj) {
		MethodElement owner = PersistenceUtil.getMethodElement(resource);
		return MultiFileURIConverter.createURI(owner.getGuid()).appendFragment(
				getGuid(obj)).toString();
	}

	public static String getBackupFileSuffix() {
		return "_" + dateFormatter.format(Calendar.getInstance().getTime()); //$NON-NLS-1$
	}

	/**
	 * Resolve the proxy identified by the given <code>guid</code> without
	 * loading all other proxies in <code>parent</code>
	 * 
	 * @param guid
	 * @param parent
	 * @return
	 */
	public static void resolveProxies(Collection GUIDs, EObject parent) {
		for (Iterator iterator = parent.eClass().getEAllContainments()
				.iterator(); !GUIDs.isEmpty() && iterator.hasNext();) {
			EStructuralFeature feature = (EStructuralFeature) iterator.next();
			if (feature.isMany()) {
				InternalEList list = (InternalEList) parent.eGet(feature);
				int index = 0;
				for (Iterator iter1 = list.basicIterator(); !GUIDs.isEmpty()
						&& iter1.hasNext(); index++) {
					InternalEObject child = (InternalEObject) iter1.next();
					if (child.eIsProxy()) {
						String guid = child.eProxyURI().fragment();
						if (GUIDs.contains(guid)) {
							// this will resolve the object with guid
							//
							list.get(index);
							GUIDs.remove(guid);
						}
					} else {
						resolveProxies(GUIDs, child);
					}
				}
			} else {
				InternalEObject child = (InternalEObject) parent.eGet(feature,
						false);
				if (child != null) {
					if (child.eIsProxy()) {
						String guid = child.eProxyURI().fragment();
						if (GUIDs.contains(guid)) {
							// this will resolve the object with guid
							//
							parent.eGet(feature);
							GUIDs.remove(guid);
						}
					} else {
						resolveProxies(GUIDs, child);
					}
				}
			}
		}
	}

	public static URI getFinalURI(Resource resource) {
		return resource instanceof MultiFileXMIResourceImpl ? ((MultiFileXMIResourceImpl) resource)
				.getFinalURI()
				: resource.getURI();
	}

	/**
	 * @param impl
	 * @return
	 */
	static boolean adjustLocationRequired(MultiFileXMIResourceImpl resource) {
		MethodElement e = PersistenceUtil.getMethodElement(resource);		
		if(e != null && hasOwnFolder(e)) {
			URI newURI = createURI(e, resource.getResourceSet());
			if(adjustLocationRequired(resource, newURI) && !new File(newURI.toFileString()).exists()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the specified resource is currently synchronized with its data store
	 * 
	 * @return <li> -1 don't know
	 *         <li> 0 no
	 *         <li> 1 yes
	 */
	public static int checkSynchronized(Resource resource) {
		if(resource instanceof MultiFileXMIResourceImpl) {
			return ((MultiFileXMIResourceImpl)resource).checkSynchronized();
		}
		return -1;
	}

	/**
	 * Some team providers changed the timestamp of last file modification by removing second fraction
	 * even the file was not changed. This method check the current and old time stamps for this.
	 * 
	 * @param currentTimeStamp
	 * @param lastTimeStamp
	 * @return
	 */
	public static boolean same(long currentTimeStamp, long lastTimeStamp) {
		return currentTimeStamp < lastTimeStamp && (currentTimeStamp & 7) == 0 && (lastTimeStamp - currentTimeStamp) < 1000;
	}

	/**
	 * Adds an empty resource manager for the given resource if it does not have one.
	 * 
	 * @param resource
	 * @return true if a new resource manager is added, false otherwise
	 */
	static ResourceManager addResourceManager(Resource resource) {
		ResourceManager resMgr = getResourceManager(resource);
		if (resMgr == null) {
			resMgr = ResourcemanagerFactory.eINSTANCE.createResourceManager();
			resource.getContents().add(0, resMgr);
			return resMgr;
		}
		return null;
	}
	
	static void checkFilePathLength(Collection resources) {
		List<String> pathList = new ArrayList<String>();
		for (Iterator iter = resources.iterator(); iter.hasNext();) {
			Resource resource = (Resource) iter.next();
			if(resource instanceof MultiFileXMIResourceImpl && ((MultiFileXMIResourceImpl)resource).hasTempURI()) {
				continue;
			}
			pathList.add(resource.getURI().toFileString());
		}
		
		if(!pathList.isEmpty()) {
			String[] paths = new String[pathList.size()];
			pathList.toArray(paths);

			for (int i=0; i < paths.length; i++) {
				String path = paths[i];
				if (paths[i].length() > 255) {
					String msg = NLS.bind(PersistenceResources.filePathNameTooLong_msg, Arrays
								.asList(paths));
					throw new MultiFileIOException(msg);
				}
			}
		}
	}

	/**
	 * Lists all possible configuration files in the specified directory <code>configDir</code> and its sub directories.
	 * 
	 * @param configDir
	 * @return
	 */
	public static Iterator listConfigFiles(File configDir) {
		return listLibraryFiles(configDir);
	}
	
	public static Iterator<File> listLibraryFiles(File dir) {
		return new AbstractTreeIterator<File>(dir, false) {
			/**
			 * Comment for <code>serialVersionUID</code>
			 */
			private static final long serialVersionUID = 2758436193000640907L;

			FileFilter filter = new FileFilter() {

				public boolean accept(File pathname) {
					return !FileManager.getInstance().isTeamPrivate(pathname.getAbsolutePath()) &&
							(pathname.isDirectory()
							|| pathname.getName().endsWith(
									MultiFileSaveUtil.DEFAULT_FILE_EXTENSION));
				}

			};

			protected Iterator<File> getChildren(Object object) {
				File[] files = ((File) object).listFiles(filter);
				if (files != null && files.length > 0) {
					return Arrays.asList(files).iterator();
				}
				return Collections.EMPTY_LIST.iterator();
			}

		};
	}
}