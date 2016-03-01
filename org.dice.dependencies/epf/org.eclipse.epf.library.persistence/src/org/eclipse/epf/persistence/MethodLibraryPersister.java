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
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epf.library.persistence.internal.IFailSafeSavable;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.persistence.util.PersistenceResources;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.services.IFileBasedLibraryPersister;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.MethodUnit;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;

/**
 * File-based implementation for IMethodLibraryPersister
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MethodLibraryPersister implements IFileBasedLibraryPersister {

	private static class FolderInfo {

		EClass eClazz;

		boolean shared;

		private String name;

		/**
		 * @param clazz
		 * @param shared
		 */
		public FolderInfo(EClass clazz, boolean shared) {
			super();
			eClazz = clazz;
			this.shared = shared;
			this.name = lowerAndPluralize(clazz.getName());
		}

	}

	public static final MethodLibraryPersister INSTANCE = new MethodLibraryPersister();

	// need to expose this, jxi
	public static final String RESOURCE_FOLDER = "resources"; //$NON-NLS-1$

	private static final List folderInfos = new ArrayList();

	static {
		folderInfos.add(new FolderInfo(UmaPackage.eINSTANCE.getGuidance(),
				false));
		folderInfos.add(new FolderInfo(UmaPackage.eINSTANCE.getWorkProduct(),
				true));
		folderInfos
				.add(new FolderInfo(UmaPackage.eINSTANCE.getActivity(), true));
	}

	protected List warnings;

	public MethodLibraryPersister() {
	}

	/**
	 * Returns the correct path (relative to the plugin) of the file of the
	 * given content description or null if the given content does not be long
	 * to any plugin.
	 * 
	 * @param content
	 * @return
	 */
	public static String getCorrectPath(ContentDescription content) {
		return getCorrectPath((DescribableElement) content.eContainer(),
				content);
	}

	private static String getCorrectPath(DescribableElement e,
			ContentDescription content) {
		if (e == null)
			return null;
		MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
		if (plugin == null) {
			return null;
		}
		Resource resource = plugin.eResource();
		URI uri = MultiFileSaveUtil.getFinalURI(resource);
		File pluginDir = new File(uri.toFileString()).getParentFile();
		String folderPath = staticGetFolderPath(e.eClass());
		String dir = new StringBuffer(pluginDir.getAbsolutePath()).append(
				File.separator).append(folderPath).append(File.separator)
				.toString();

		return getNextAvailableFileName(dir, e, content);
	}

	/**
	 * Gets next available file name for the given {@link MultiResourceEObject} in the given directory <code>dir</code>
	 * 
	 * @param dir the directory path that must have a file separator at the end
	 * @param requestedName
	 * @param e
	 * @return
	 */
	public static String getNextAvailableFileName(String dir, String requestedName,
			MultiResourceEObject e) {
		String currentFilename = null;
		File currentFile = null;
		MultiFileXMIResourceImpl resource = (MultiFileXMIResourceImpl) e
				.eDirectResource();
		if (resource != null) {
			// keep existing file name during move if it is not taken yet. Don't
			// try to match the element's name
			//
			String path = ((MultiFileXMIResourceImpl) e.eResource())
					.getFinalURI().toFileString();
			currentFile = new File(path);
			currentFilename = currentFile.getName();
			File file = new File(dir, currentFilename);
			if (!file.exists()) {
				// the element is being moved
				//
				return file.getAbsolutePath();
			}
		}

		// element without a resource or path is already taken
		//
		String path = new StringBuffer(dir).append(requestedName).append(
				MultiFileSaveUtil.DEFAULT_FILE_EXTENSION).toString();
		File file = new File(path);
		if(file.equals(currentFile)) {
			return path;
		}
		if ((currentFilename != null && currentFilename.equals(requestedName))
				|| file.exists()) {
			for (int i = 2; true; i++) {
				path = new StringBuffer(dir).append(requestedName).append(' ')
						.append(i).append(
								MultiFileSaveUtil.DEFAULT_FILE_EXTENSION)
						.toString();
				if (!new File(path).exists()) {
					return path;
				}
			}
		} else {
			return path;
		}
	}

	static String getNextAvailableFileName(String dir,
			ContentDescription content) {
		return getNextAvailableFileName(dir, (DescribableElement) content
				.eContainer(), content);
	}

	private static String getNextAvailableFileName(String dir,
			DescribableElement e, ContentDescription content) {
		return getNextAvailableFileName(dir, e.getName(),
				(MultiResourceEObject) content);
	}

	private static String staticGetFolderPath(EClass eClazz) {
		int size = folderInfos.size();
		FolderInfo info = null;
		for (int i = 0; i < size; i++) {
			FolderInfo folderInfo = (FolderInfo) folderInfos.get(i);
			if (folderInfo.eClazz.isSuperTypeOf(eClazz)) {
				info = folderInfo;
			}
		}
		if (info != null) {
			if (info.eClazz == eClazz || info.shared) {
				return info.name;
			} else {
				return new StringBuffer(info.name).append(File.separatorChar)
						.append(lowerAndPluralize(eClazz.getName())).toString();
			}
		} else {
			return lowerAndPluralize(eClazz.getName());
		}
	}

	public static String lowerAndPluralize(String name) {
		name = name.toLowerCase();
		if (name.endsWith("children")) { //$NON-NLS-1$
			return name;
		} else if (name.endsWith("child")) { //$NON-NLS-1$
			return name + "ren"; //$NON-NLS-1$
		} else if (name.endsWith("data")) { //$NON-NLS-1$
			return name;
		} else if (name.endsWith("datum")) { //$NON-NLS-1$
			return name.substring(0, name.length() - 2) + "a"; //$NON-NLS-1$
		} else if (name.endsWith("by")) { //$NON-NLS-1$
			return name + "s"; //$NON-NLS-1$
		} else if (name.endsWith("y")) { //$NON-NLS-1$
			return name.substring(0, name.length() - 1) + "ies"; //$NON-NLS-1$
		} else if (name.endsWith("ex")) { //$NON-NLS-1$
			return name.substring(0, name.length() - 2) + "ices"; //$NON-NLS-1$
		} else if (name.endsWith("x")) { //$NON-NLS-1$
			return name + "es"; //$NON-NLS-1$
		} else if (name.endsWith("us")) { //$NON-NLS-1$
			return name.substring(0, name.length() - 2) + "i"; //$NON-NLS-1$
		} else if (name.endsWith("ss")) { //$NON-NLS-1$
			return name + "es"; //$NON-NLS-1$
		} else if (name.endsWith("s")) { //$NON-NLS-1$
			return name;
		} else {
			return name + "s"; //$NON-NLS-1$
		}
	}

	/**
	 * Gets the virtual path to the resource folder of the given MethodElement object.
	 * 
	 * @param e
	 * @return
	 * @see #getElementVirtualPath(MethodElement)
	 */
	public String geResourceVirtualPath(MethodElement e) {
		String folderPath = getElementVirtualPath(e);
		if (folderPath == null || folderPath.length() == 0)
		{
			return RESOURCE_FOLDER;
		} else {
			StringBuffer buffer = new StringBuffer(folderPath);
			if ( !folderPath.endsWith(File.separator) ) {
				buffer.append(File.separator);
			} 
			
			buffer.append(RESOURCE_FOLDER);				
			
			return buffer.toString();
		}
	}

	/**
	 * Gets the virtual path to the folder of the given MethodElement object. This path will have the following format:
	 * <plugin_name>\folder_relative_path\
	 * 
	 * @param e
	 * @return
	 */	
	public String getElementVirtualPath(MethodElement e) {
		MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
		if (plugin == null) {
			if (!(e instanceof MethodConfiguration || e instanceof MethodLibrary)) {
				// error: plugin element without a valid plugin set. This
				// problem needs to be fixed.
				// still see it from time to time,
				// for example, when lick on a capability pattern in
				// Configuration Explorer
				System.err
						.println("error in MethodLibraryPersister.getElementPath(): plugin element without a valid plugin. This problem needs to be fixed."); //$NON-NLS-1$
			}
			return ""; //$NON-NLS-1$
		}
		
		String folderPath = getFolderRelativePath(e);
		String pluginName = plugin.getName();
		return (folderPath.length() == 0 ? pluginName + File.separator : new StringBuffer(
				pluginName).append(File.separatorChar).append(folderPath)
				.append(File.separatorChar).toString());
	}
	
	/**
	 * Gets the path relative to the library root directory to the
	 * folder of the given MethodElement object.
	 * 
	 * @param e
	 * @return
	 */	
	private String getRelativeElementPath(MethodElement e) {
		String folderPath = getFolderRelativePath(e);
		MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
		if (plugin == null) {
			if ((e instanceof MethodConfiguration || e instanceof MethodLibrary)) {
				return folderPath;
			}
			else {
				// error: plugin element without a valid plugin set. This
				// problem needs to be fixed.
				// still see it from time to time,
				// for example, when lick on a capability pattern in
				// Configuration Explorer
				System.err
						.println("error in MethodLibraryPersister.getElementPath(): plugin element without a valid plugin. This problem needs to be fixed."); //$NON-NLS-1$
				return ""; //$NON-NLS-1$
			}
		}

		if (plugin.eContainer() == null) {
			// error: plugin without library set. This problem needs to be
			// fixed.
			// still see it from time to time
			System.err
					.println("error in MethodLibraryPersister.getElementPath(): plugin without library set. This problem needs to be fixed."); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}

		String relPluginPath;
		if (plugin.eResource() != null) {
			Resource libRes = UmaUtil.getMethodLibrary(plugin).eResource();
			if (libRes == plugin.eResource()) {
				// the plugin is not saved yet
				relPluginPath = plugin.getName();
			} else {
				URI uri = MultiFileSaveUtil.getFinalURI(plugin.eResource());
				URI libUri = MultiFileSaveUtil.getFinalURI(libRes);
				URI relUri = uri.deresolve(libUri);
				relPluginPath = relUri.trimSegments(1).toFileString();
			}
		} else {
			// the library is not saved yet
			relPluginPath = plugin.getName();
		}

		return (folderPath.length() == 0 ? relPluginPath + File.separator: new StringBuffer(
				relPluginPath).append(File.separatorChar).append(folderPath)
				.append(File.separatorChar).toString());
	}

	public static void main(String[] args) {
		EClass eCls = UmaPackage.eINSTANCE.getTemplate();
		System.out.println(eCls.getName());
	}

	/**
	 * Adjusts the location of the given resource and save all the resources
	 * that have been changed as the result of this adjustment
	 * 
	 * @param resource
	 */
	public void adjustLocation(Resource resource) {
		Set modifiedResources = new HashSet();
		MultiFileSaveUtil.adjustLocation(resource, modifiedResources);

		// save the modified resources
		MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) resource
				.getResourceSet();
		for (Iterator iter = modifiedResources.iterator(); iter.hasNext();) {
			try {
				resourceSet.save((Resource) iter.next(), null);
			} catch (Exception e) {
				CommonPlugin.INSTANCE.log(e);
			}
		}
	}

	public void save(Resource resource) throws Exception {
		MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) resource
				.getResourceSet();
		resourceSet.save(resource, null);
	}

	protected void deleteFiles(MethodElement e, String path,
			ResourceSet resourceSet) {
		if (new File(path).isDirectory() && !e.eIsProxy()) {
			for (Iterator iter = resourceSet.getResources().iterator(); iter
					.hasNext();) {
				Resource res = (Resource) iter.next();
				MethodElement me = PersistenceUtil.getMethodElement(res);
				if (me != null && UmaUtil.isContainedBy(me, e)) {
					res.unload();
					iter.remove();
				}
			}
		}

		try {
			FileManager.getInstance().deleteResource(path, null);
		} catch (CoreException ex) {
			CommonPlugin.INSTANCE.log(ex);
			throw new WrappedException(ex);
		}
	}

	/**
	 * Deletes the files associated with the given MethodElement
	 * 
	 * @param e
	 */
	protected void delete(MethodElement e, Set modifiedResources) {
		if (!UmaUtil.hasDirectResource(e)) {
			return;
		}

		MultiFileXMIResourceImpl resource = (MultiFileXMIResourceImpl) e
				.eResource();
		MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) resource
				.getResourceSet();

		// Don't delete this resource's file if it still contains other
		// MethodElement
		for (Iterator iter = resource.getContents().iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof MethodElement && element != e) {
				resourceSet.removeURIMappings(e, modifiedResources);
				resource.getContents().remove(e);
				modifiedResources.add(resource);
				return;
			}
		}

		String path;
		if (resourceSet.hasOwnFolder(e)) {
			// path is the directory of the MethodPlugin/ProcessComponent
			//
			path = new File(resource.getFinalURI().toFileString()).getParent();

			// unload/remove all resources under the folder
			//
			Collection unloadedResources = new ArrayList();
			do {
				unloadedResources.clear();
				// collect all resources whose root element is contained by the
				// given element
				//
				for (Iterator iter = new ArrayList(resourceSet.getResources())
						.iterator(); iter.hasNext();) {
					Resource res = (Resource) iter.next();
					if (res.isLoaded()) {
						MethodElement me = PersistenceUtil
								.getMethodElement(res);
						if (me != null && UmaUtil.isContainedBy(me, e)) {
							unloadedResources.add(res);
						}
					}
				}
				for (Iterator iter = unloadedResources.iterator(); iter
						.hasNext();) {
					Resource res = (Resource) iter.next();
					res.unload();
				}
				resourceSet.getResources().removeAll(unloadedResources);
			} while (!unloadedResources.isEmpty());
		} else {
			path = resource.getFinalURI().toFileString();
		}

		try {
			resourceSet.cleanUp(resource, modifiedResources);
		} catch (Exception e1) {
			CommonPlugin.INSTANCE.log(e1);
			if (MultiFileSaveUtil.DEBUG) {
				e1.printStackTrace();
			}
		}

		deleteFiles(e, path, resourceSet);
	}

	/**
	 * @param e
	 * @param objectsWithDirectResources
	 */
	private static void getObjectsWithDirectResources(EObject e,
			Collection objectsWithDirectResources) {
		if (UmaUtil.hasDirectResource(e)) {
			objectsWithDirectResources.add(e);
		} else {
			for (Iterator iter = e.eContents().iterator(); iter.hasNext();) {
				getObjectsWithDirectResources((EObject) iter.next(),
						objectsWithDirectResources);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.services.IFileBasedLibraryPersister#delete(org.eclipse.epf.uma.MethodElement)
	 */
	public void delete(MethodElement e) {
		// collect all elements with direct resources that are the given element
		// or its offstring
		ArrayList elements = new ArrayList();
		getObjectsWithDirectResources(e, elements);
		deleteAndSave(elements);
	}

	private void deleteAndSave(ArrayList elements) {
		if (!elements.isEmpty()) {
			Set modifiedResources = new HashSet();
			for (Iterator iter = elements.iterator(); iter.hasNext();) {
				delete((MethodElement) iter.next(), modifiedResources);
			}

			// save modified resources
			//
			for (Iterator iter = modifiedResources.iterator(); iter.hasNext();) {
				Resource resource = (Resource) iter.next();
				try {
					save(resource);
				} catch (Exception ex) {
					throw new WrappedException(ex);
				}
			}
		}
	}

	/**
	 * Gets the relative path of the folder that can store the content of the
	 * given MethodElement. The path is relative to its plugin folder or library
	 * folder if the element cannot be stored in a plugin.
	 */
	private static String staticGetFolderRelativePath(MethodElement e) {
		if (e instanceof MethodPlugin) {
			return ""; //$NON-NLS-1$
		} else if (e instanceof ContentElement) {
			return staticGetFolderPath(e.eClass());
		} else if (e instanceof MethodConfiguration) {
			return MultiFileSaveUtil.METHOD_CONFIGURATION_FOLDER_NAME;
		}

		MethodUnit unit = UmaUtil.getMethodUnit(e);

		if (unit instanceof ProcessComponent) {
			Process proc = ((ProcessComponent) unit).getProcess();
			if (proc instanceof CapabilityPattern) {
				return MultiFileSaveUtil.CAPABILITY_PATTERN_PATH;
			} else if (proc instanceof DeliveryProcess) {
				return MultiFileSaveUtil.DELIVERY_PROCESS_PATH;
			}
		} else if (unit instanceof ContentDescription) {
			return staticGetFolderRelativePath((MethodElement) unit
					.eContainer());
		}

		return ""; //$NON-NLS-1$
	}

	public String getFolderRelativePath(MethodElement e) {
		return staticGetFolderRelativePath(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister#getWarnings()
	 */
	public List getWarnings() {
		if (warnings == null) {
			warnings = new ArrayList();
		}
		return warnings;
	}

	static class FailSafePersister extends MethodLibraryPersister implements
			FailSafeMethodLibraryPersister {

		private Map saveOptions;

		private TxRecord txRecord = new TxRecord() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.epf.persistence.TxRecord#clear()
			 */
			public void clear() {
				if (warnings != null && !warnings.isEmpty()) {
					// copy the warning to persister
					//
					FailSafePersister.this.warnings = Collections
							.unmodifiableList(warnings);
				}
				super.clear();
			}
		};

		private Map elementToInfoMapToDeleteFiles;

		public FailSafePersister() {
			saveOptions = new HashMap(
					MultiFileResourceSetImpl.DEFAULT_SAVE_OPTIONS);
			txRecord = new TxRecord();
			saveOptions.put(MultiFileXMISaveImpl.TX_RECORD, txRecord);
			elementToInfoMapToDeleteFiles = new HashMap();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.epf.persistence.MethodLibraryPersister#getWarnings()
		 */
		public List getWarnings() {
			if (txRecord.getTxID() == null) {
				return super.getWarnings();
			}
			return txRecord.getWarnings();
		}

		public Map getSaveOptions() {
			return saveOptions;
		}

		void checkMove(Resource resource) {
			// disallow new operation on resource which has started operation
			// that is not committed
			MultiFileXMIResourceImpl mfResource = (MultiFileXMIResourceImpl) resource;
			if (mfResource.txStarted()) {
				throw new MultiFileIOException(NLS.bind(
						PersistenceResources.moveResourceError_msg, resource));
			}			
		}				
		
		/**
		 * @see org.eclipse.epf.uma.persistence.MethodLibraryPersister#save(org.eclipse.emf.ecore.resource.Resource)
		 */
		public void save(Resource resource) throws Exception {
			if (resource != null) {
				if(resource.getResourceSet() instanceof MultiFileResourceSetImpl &&
						resource instanceof MultiFileXMIResourceImpl) {
					MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) resource
					.getResourceSet();
					if (resourceSet == null) {
						return;
					}
	
					// update version info in library resource if needed
					//
					Resource libResourceToSave = null;
					MethodElement me = PersistenceUtil.getMethodElement(resource);
					if(me != null) {
						MethodLibrary lib = UmaUtil.getMethodLibrary(me);
						if(lib != null) {
							Resource libResource = lib.eResource();
							if(libResource != null 
									&& libResource != resource
									&& PersistenceUtil.checkToolVersion(libResource) != 0) {
								libResourceToSave = libResource;
							}
						}
					}
	
					if (MultiFileXMISaveImpl.checkModifyRequired(saveOptions)) {
						Collection<Resource> resources;
						if(libResourceToSave != null) {
							resources = new ArrayList<Resource>();
							resources.add(resource);
							resources.add(libResourceToSave);
						}
						else {
							resources = Collections.singletonList(resource);
						}
						MultiFileSaveUtil.checkModify(resources);
						
						// check out-of-sync
						//
						MultiFileSaveUtil.checkOutOfSynch(resources, saveOptions);
						
						MultiFileSaveUtil.checkFilePathLength(resources);
					}
	
					resourceSet.save(resource, saveOptions);
					if(libResourceToSave != null) {
						resourceSet.save(libResourceToSave, saveOptions);
					}
				}
				else if(resource instanceof IFailSafeSavable) {
					IFailSafeSavable failSafeSavable = (IFailSafeSavable) resource;
					failSafeSavable.setTxID(txRecord.getTxID());
					resource.save(saveOptions);
					txRecord.getResourcesToCommit().add(resource);
				}
				else {
					throw new IllegalAccessException("Resource must implement org.eclipse.epf.library.persistence.internal.IFailSafeSavable"); //$NON-NLS-1$
				}
			}
		}

		/**
		 * @see org.eclipse.epf.uma.persistence.MethodLibraryPersister#adjustLocation(org.eclipse.emf.ecore.resource.Resource)
		 */
		public void adjustLocation(Resource resource) {
			checkMove(resource);
			Set modifiedResources = new HashSet();
			if (MultiFileSaveUtil.prepareAdjustLocation(
					(MultiFileXMIResourceImpl) resource, modifiedResources)) {
				txRecord.getResourcesToCommit().add(resource);
				
				HashSet resourcesToCheck = new HashSet(modifiedResources);
				resourcesToCheck.addAll(resourcesToCheck);
				MultiFileSaveUtil.checkModify(resourcesToCheck);
				
				if (!modifiedResources.isEmpty()) {		
					
					MultiFileSaveUtil.checkOutOfSynch(modifiedResources, saveOptions);

					// save the modified resources
					for (Iterator iter = modifiedResources.iterator(); iter
							.hasNext();) {
						try {
							save((Resource) iter.next());
						} catch (Exception e) {
							PersistencePlugin.getDefault().getLogger().logError(e);
							throw new MultiFileIOException(e.toString());
						}
					}
				}
			}
			commit();
		}

		public void adjustLocation(Collection resources) {
			if (resources == null || resources.isEmpty()) {
				return;
			}

			for (Iterator iter = resources.iterator(); iter.hasNext();) {
				checkMove((Resource) iter.next());
			}

			Set modifiedResources = new HashSet();
			HashSet resourcesToCheck = new HashSet();
			for (Iterator iter = resources.iterator(); iter.hasNext();) {
				MultiFileXMIResourceImpl resource = (MultiFileXMIResourceImpl) iter
						.next();
				if (MultiFileSaveUtil.prepareAdjustLocation(
						(MultiFileXMIResourceImpl) resource, modifiedResources)) {
					txRecord.getResourcesToCommit().add(resource);
					resourcesToCheck.add(resource);
				}
			}

			resourcesToCheck.addAll(modifiedResources);
			if(!resourcesToCheck.isEmpty()) {
				MultiFileSaveUtil.checkModify(resourcesToCheck);
			}			
			if (!modifiedResources.isEmpty()) {
				MultiFileSaveUtil.checkOutOfSynch(modifiedResources, saveOptions);

				// save the modified resources
				for (Iterator iter = modifiedResources.iterator(); iter
						.hasNext();) {
					try {
						save((Resource) iter.next());
					} catch (Exception e) {
						CommonPlugin.INSTANCE.log(e);
						throw new MultiFileIOException(e.toString());
					}
				}
			}
			commit();
		}

		/**
		 * @see org.eclipse.epf.uma.persistence.MethodLibraryPersister#deleteFiles(org.eclipse.epf.uma.MethodElement,
		 *      java.lang.String, org.eclipse.emf.ecore.resource.ResourceSet)
		 */
		protected void deleteFiles(MethodElement e, String path,
				ResourceSet resourceSet) {
			// keep the info to really delete the files in commit()
			elementToInfoMapToDeleteFiles.put(e, new Object[] { path,
					resourceSet });
		}

		private void superDeleteFiles(MethodElement e, String path,
				ResourceSet resourceSet) {
			super.deleteFiles(e, path, resourceSet);
		}

		/**
		 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister.FailSafeMethodLibraryPersister#commit()
		 */
		public void commit() {
			// save is done
			// call saveIsDone() on every saved file to rename it to the correct
			// name
			int size = txRecord.getResourcesToCommit().size();
			for (int i = 0; i < size; i++) {
				((IFailSafeSavable) txRecord.getResourcesToCommit()
						.get(i)).commit();
			}

			// notify all commited resources that the transaction is done
			for (int i = 0; i < size; i++) {
				((IFailSafeSavable) txRecord.getResourcesToCommit()
						.get(i)).txFinished(true);
			}

			// delete backup
			for (int i = 0; i < size; i++) {
				((IFailSafeSavable) txRecord.getResourcesToCommit()
						.get(i)).deleteBackup();
			}

			txRecord.clear();

			// delete files of deleted elements
			//
			for (Iterator iter = elementToInfoMapToDeleteFiles.entrySet()
					.iterator(); iter.hasNext();) {
				Map.Entry entry = (Map.Entry) iter.next();
				Object[] info = (Object[]) entry.getValue();
				String path = (String) info[0];
				try {
					superDeleteFiles((MethodElement) entry.getKey(), path,
							(ResourceSet) info[1]);
				} catch (Exception e) {
					if (e instanceof WrappedException) {
						e = ((WrappedException) e).exception();
					}
					String msg = PersistenceResources.ErrMsg_CouldNotDelete;
					String otherMsg = null;
					if (e instanceof CoreException) {
						IStatus status = ((CoreException) e).getStatus();
						if (status != null) {
							otherMsg = UmaUtil.getMessage(status);
						}
					}
					if (otherMsg == null) {
						otherMsg = ""; //$NON-NLS-1$
					}
					msg = MessageFormat.format(msg, new Object[] { path,
							otherMsg });
					e = new Exception(msg, e);
					getWarnings().add(e);
					CommonPlugin.INSTANCE.log(e);
					if (MultiFileSaveUtil.DEBUG) {
						e.printStackTrace();
					}
				}
			}
			elementToInfoMapToDeleteFiles.clear();
		}

		/**
		 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister.FailSafeMethodLibraryPersister#rollback()
		 */
		public void rollback() {
			if (!txRecord.getResourcesToCommit().isEmpty()) {
				try {
					int max = txRecord.getResourcesToCommit().size() - 1;
					ArrayList restoredResources = new ArrayList();

					// Something went wrong, restore from backup
					for (int i = max; i > -1; i--) {
						IFailSafeSavable resource = (IFailSafeSavable) txRecord
								.getResourcesToCommit().get(i);
						if (resource.restore()) {
							restoredResources.add(resource);
						}
					}

					for (Iterator iter = restoredResources.iterator(); iter
							.hasNext();) {
						Resource resource = (Resource) iter
								.next();
						resource.setModified(true);
					}

					// delete temp files
					for (int i = max; i > -1; i--) {
						Resource resource = (Resource) txRecord
								.getResourcesToCommit().get(i);
						if (((IFailSafeSavable)resource).hasTempURI()) {
							// uri keeps the path to temp file
							try {
								new File(resource.getURI().toFileString())
										.delete();
							} catch (Exception e) {
								CommonPlugin.INSTANCE.log(e);
								if (MultiFileSaveUtil.DEBUG) {
									e.printStackTrace();
								}
							}
						}
					}

					// notify all commited resources that the transaction is
					// done
					for (int i = max; i > -1; i--) {
						((IFailSafeSavable) txRecord
								.getResourcesToCommit().get(i))
								.txFinished(false);
					}

				} catch (RuntimeException e) {
					CommonPlugin.INSTANCE.log(e);
					if (MultiFileSaveUtil.DEBUG) {
						e.printStackTrace();
					}
					throw e;
				}
			}
			txRecord.clear();
		}

		/**
		 * @see org.eclipse.epf.services.IFileBasedLibraryPersister#getFailSafePersister()
		 */
		public FailSafeMethodLibraryPersister getFailSafePersister() {
			return this;
		}

		/**
		 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister.FailSafeMethodLibraryPersister#getCurrentTxID()
		 */
		public String getCurrentTxID() {
			return txRecord.getTxID();
		}

	}

	FailSafePersister getFailSafePersister(Map options) {
		FailSafePersister persister = new FailSafePersister();
		persister.saveOptions.putAll(options);

		// make sure that TX_RECORD still keeps the correct value
		persister.saveOptions.put(MultiFileXMISaveImpl.TX_RECORD,
				persister.txRecord);

		return persister;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister#hasOwnFolder(java.lang.Object)
	 */
	public boolean hasOwnFolder(Object e) {
		return MultiFileSaveUtil.hasOwnFolder(e);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister#hasOwnResource(java.lang.Object)
	 */
	public boolean hasOwnResource(Object e) {
		return MultiFileSaveUtil.hasOwnResource(e,
				MultiFileResourceSetImpl.DEFAULT_SAVE_SEPARATELY_CLASS_SET);
	}

	/**
	 * @see org.eclipse.epf.services.IFileBasedLibraryPersister#getFailSafePersister()
	 */
	public FailSafeMethodLibraryPersister getFailSafePersister() {
		return new FailSafePersister();
	}

	public static class NonFailSafePersister extends MethodLibraryPersister
			implements FailSafeMethodLibraryPersister {
		private Map saveOptions = new HashMap();

		/**
		 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister.FailSafeMethodLibraryPersister#commit()
		 */
		public void commit() {
			// do nothing
		}

		/**
		 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister.FailSafeMethodLibraryPersister#rollback()
		 */
		public void rollback() {
			// do nothing
		}

		/**
		 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister.FailSafeMethodLibraryPersister#getCurrentTxID()
		 */
		public String getCurrentTxID() {
			return null;
		}

		/**
		 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister.FailSafeMethodLibraryPersister#adjustLocation(java.util.Collection)
		 */
		public void adjustLocation(Collection resources) {
			for (Iterator iter = resources.iterator(); iter.hasNext();) {
				adjustLocation((Resource) iter.next());
			}
		}

		public Map getSaveOptions() {
			return saveOptions;
		}

	}

	private static final FailSafeMethodLibraryPersister nonFailSafePersister = new NonFailSafePersister();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister#getFileExtension(java.lang.Object)
	 */
	public String getFileExtension(Object e) {
		return MultiFileSaveUtil.DEFAULT_FILE_EXTENSION;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister#save(org.eclipse.epf.uma.MethodElement)
	 */
	public void save(MethodElement element) throws Exception {
		if(!hasOwnResourceWithoutReferrer(element)) {
			return;
		}
		MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) UmaUtil.getMethodLibrary(element).eResource().getResourceSet();
		Map options = resourceSet.getDefaultSaveOptions(); 
		Resource res = ((InternalEObject)element).eDirectResource();
		MultiFileXMIResourceImpl resource;
		if(res instanceof MultiFileXMIResourceImpl) {
			resource = (MultiFileXMIResourceImpl) res;
			resourceSet.save(resource, options);
		}
		else {
			URI uri = MultiFileSaveUtil.createURI(element, resourceSet);
			resource = MultiFileSaveUtil.save(resourceSet, element, uri, options, false);
		}
		resource.updateTimeStamps();
		
		String str = (String) options.get(MultiFileXMISaveImpl.REFRESH_NEW_RESOURCE);
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
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.util.IFileBasedLibraryPersister#hasOwnResourceWithoutReferrer(java.lang.Object)
	 */
	public boolean hasOwnResourceWithoutReferrer(Object e) {
		return e instanceof MethodConfiguration;
	}

	public File createMethodPluginFolder(String pluginName, MethodLibrary library) {
		File libDir = new File(library.eResource().getURI().toFileString()).getParentFile();
		File pluginDir = new File(libDir, pluginName);
		if(!pluginDir.exists()) {
			if(!pluginDir.mkdirs()) {
				throw new MultiFileIOException(NLS.bind(PersistenceResources.cannot_create_dir_msg, pluginDir));				
			}
		}
		return pluginDir;
	}

	public File getDefaultMethodConfigurationFolder(MethodLibrary library) {
		return getDefaultMethodConfigurationFolder(library, true);
	}
	
	public File getDefaultMethodConfigurationFolder(MethodLibrary library,
			boolean create) {
		File libDir = new File(library.eResource().getURI().toFileString()).getParentFile();
		File configDir = new File(libDir, MultiFileSaveUtil.METHOD_CONFIGURATION_FOLDER_NAME);
		if(configDir.exists()) {
			return configDir;
		} else if(create) {
			if(!configDir.mkdirs()) {
				throw new MultiFileIOException(NLS.bind(PersistenceResources.cannot_create_dir_msg, configDir));				
			} else {
				return configDir;
			}
		}
		return null;
	}

	public void setDefaultMethodConfigurationFolder(MethodLibrary library, File file) {
		// not allowed
	}

	public boolean isContainedBy(Resource resource, Resource containerResource) {
		MethodElement e = PersistenceUtil.getMethodElement(containerResource);
		if(hasOwnFolder(e)) {
			String path = FileManager.toFileString(resource.getURI());
			String containerPath = FileManager.toFileString(containerResource.getURI());		
			if(path == null || containerPath == null) {
				return false;
			}
			return new Path(containerPath).removeLastSegments(1).isPrefixOf(new Path(path));
		}
		return false;
	}

	public String getResourceFolderPath(MethodElement e) {
		String folderPath = getFolderAbsolutePath(e);
		return folderPath != null ? new File(folderPath, RESOURCE_FOLDER).getAbsolutePath() : RESOURCE_FOLDER;
	}

	public String getFolderAbsolutePath(MethodElement e) {
		MethodLibrary library = UmaUtil.getMethodLibrary(e);
		File libDir = null;
		if(library == null) {
			MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
			Resource resource;
			if(plugin != null && (resource = ((InternalEObject) plugin).eDirectResource()) != null) {
				URI uri = MultiFileSaveUtil.getFinalURI(resource);
				libDir = new File(uri.toFileString()).getParentFile().getParentFile();
			}			
		}
		else {
			URI uri = MultiFileSaveUtil.getFinalURI(library.eResource());
			libDir = new File(uri.toFileString()).getParentFile();
		}
		if (libDir != null) {
			return new File(libDir, getRelativeElementPath(e))
					.getAbsolutePath();
		} else {
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.services.ILibraryPersister#delete(java.util.Collection)
	 */
	public void delete(Collection<MethodElement> elems) {
		ArrayList elements = new ArrayList();
		for (Iterator<MethodElement> it = elems.iterator(); it.hasNext();) {
			getObjectsWithDirectResources(it.next(), elements);
		}
		deleteAndSave(elements);
	}

	public File getFile(Resource resource) {
		URI uri = MultiFileSaveUtil.getFinalURI(resource);
		return new File(FileManager.toFileString(uri));
	}

}