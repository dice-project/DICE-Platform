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
package org.eclipse.epf.library.xmi;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.common.service.versioning.VersionUtil;
import org.eclipse.epf.common.serviceability.DebugTrace;
import org.eclipse.epf.library.AbstractLibraryManager;
import org.eclipse.epf.library.ILibraryResourceManager;
import org.eclipse.epf.library.LibraryAlreadyExistsException;
import org.eclipse.epf.library.LibraryNotFoundException;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.LibraryServiceException;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.util.DebugUtil;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.layout.LayoutResources;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.persistence.PersistenceService;
import org.eclipse.epf.library.project.MethodLibraryProject;
import org.eclipse.epf.library.util.ModelStorage;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.persistence.migration.MappingUtil;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.util.ExtendedOpposite;
import org.eclipse.epf.uma.util.ExtendedReference;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.osgi.util.NLS;

import com.ibm.icu.util.Calendar;

/**
 * The default XMI Library Manager implementation.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * 
 * @since 1.0
 */
public class XMILibraryManager extends AbstractLibraryManager {
	/**
	 * The supported library type.
	 */
	public static final String LIBRARY_TYPE = Services.XMI_PERSISTENCE_TYPE;

	/**
	 * The library XMI file name.
	 */
	public static final String LIBRARY_XMI = MultiFileSaveUtil.DEFAULT_LIBRARY_MODEL_FILENAME;

	/**
	 * The plugin and config spec export file name.
	 */
	public static final String exportFile = MultiFileSaveUtil.DEFAULT_PLUGIN_EXPORT_FILENAME;

	/**
	 * The library path.
	 */
	public static final String ARG_LIBRARY_PATH = "library.path"; //$NON-NLS-1$
	
	// The absolute path to the managed library.
	protected String path;
	
	private ILibraryResourceManager resourceMgr;

	private IProject project;
	
	private String registerType = "Default";		//$NON-NLS-1$ 
	
	public XMILibraryManager() {
		super();
		resourceMgr = new XMILibraryResourceManager();
	}
	
	protected boolean isReopeningLib = false;
	protected boolean isReopeningSynLib = false;

	/**
	 * Creates a new method library.
	 * 
	 * @param name
	 *            a name for the new method library
	 * @param args
	 *            method library specific arguments
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary createMethodLibrary(final String name, final Map<String, Object> args)
			throws LibraryServiceException {
		final MethodLibrary[] resultHolder = new MethodLibrary[1];
		final LibraryServiceException[] exceptionHolder = new LibraryServiceException[1];
		IWorkspaceRunnable action = new IWorkspaceRunnable() {

			public void run(IProgressMonitor monitor) throws CoreException {
				try {
					resultHolder[0] = doCreateMethodLibrary(name, args);
				}
				catch(LibraryServiceException e) {
					exceptionHolder[0] = e;
				}
			}
			
		};
		try {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			workspace.run(action, workspace.getRuleFactory().createRule(workspace.getRoot()), IWorkspace.AVOID_UPDATE, null);
		} catch (CoreException e) {
			XMILibraryPlugin.getDefault().getLogger().logError(e);
			throw new LibraryServiceException(e);
		}
		
		if(exceptionHolder[0] != null) {
			throw exceptionHolder[0];
		}
		return resultHolder[0];
	}
		
	private MethodLibrary doCreateMethodLibrary(String name, Map<String, Object> args)
	throws LibraryServiceException {
		if (debug) {
			DebugTrace.print(this, "createMethodLibrary", "name=" + name); //$NON-NLS-1$ //$NON-NLS-2$
		}

		if (name == null || name.length() == 0 || args == null) {
			throw new IllegalArgumentException();
		}

		String path = (String) args.get(ARG_LIBRARY_PATH);
		if (path == null || path.length() == 0) {
			throw new IllegalArgumentException();
		}

		File libraryPath = new File(path);
		File libraryXMIFile = new File(libraryPath, LIBRARY_XMI);
		if (libraryXMIFile.exists()) {
			String msg = NLS.bind(
					XMILibraryResources.libraryAlreadyExistsError_msg,
					libraryPath.getAbsolutePath());
			throw new LibraryAlreadyExistsException(msg);
		}

		if (!libraryPath.exists()) {
			libraryPath.mkdirs();
		}

		try {
			skipEventProcessing = true;
			String regType = (String) args.get(ARG_LIBRARY_REGISTER_TYPE);
			if (regType != null && regType.equals("ConfigExport")) { //$NON-NLS-1$
				String time = Long.toHexString(Calendar.getInstance().getTimeInMillis());
				MethodLibraryProject.openProject(libraryPath.getAbsolutePath(), "ExportLib" + time, //$NON-NLS-1$
						null);		
			} else { 				
				// Open the method library project file.
				project = MethodLibraryProject.openProject(libraryPath.getAbsolutePath(),
						null);
			}
			// Create the resource set.
			ILibraryResourceSet resourceSet = (ILibraryResourceSet) editingDomain
					.getResourceSet();

			// Create a new method library.
			ModelStorage.newLibrary(resourceSet, name, libraryPath
					.getAbsolutePath(), true);
			library = resourceSet.getFirstMethodLibrary();

			// Add a listener to monitor library resource changes.
			addResourceChangedListeners();

			if (debug) {
				DebugTrace.print(this,
						"createMethodLibrary", "library=" + library); //$NON-NLS-1$ //$NON-NLS-2$
			}

			return library;
		} catch (Exception e) {
			XMILibraryPlugin.getDefault().getLogger().logError(e);
			throw new LibraryServiceException(e);
		} finally {
			skipEventProcessing = false;

			// // event processed in LibraryService
			// notifyListeners(ILibraryChangeListener.OPTION_CREATED, null);
		}
	}

	/**
	 * Opens a method library.
	 * 
	 * @param uri
	 *            a method library URI
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary openMethodLibrary(java.net.URI uri)
			throws LibraryServiceException {
		if (debug) {
			DebugTrace.print(this, "openMethodLibrary"); //$NON-NLS-1$
		}

		if (uri == null) {
			throw new IllegalArgumentException();
		}

		try {
			File file = new File(uri);
			library = openMethodLibrary(file);
		} 
		catch(LibraryServiceException e) {
			throw e;
		}
		catch (Exception e) {
			library = null;
		}

		if (debug) {
			DebugTrace.print(this, "openMethodLibrary", "library=" + library); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return library;
	}

	/**
	 * Opens a method library.
	 * 
	 * @param path
	 *            a <code>File</code> object that contains the path to the
	 *            method library.
	 * @return a <code>MethodLibrary</code>.
	 * @throw <code>LibraryServiceException</code> if an error occurred while
	 *        performing the operation.
	 */
	protected MethodLibrary openMethodLibrary(final File path)
			throws LibraryServiceException {
		final MethodLibrary[] libHolder = new MethodLibrary[1];
		final LibraryServiceException[] exceptionHolder = new LibraryServiceException[1];
		if (ResourcesPlugin.getWorkspace().isTreeLocked()) {
			// if user opens a project from Navigator view, the
			// workspace-runable cannot be run successfully because the
			// workspace is locked at that time and an exception will be
			// thrown.
			// TODO: this work-around however will not defer the notification
			// of resource change events until after the library is opened.
			// This might cause concurrent modification exception.
			//
			try {
				libHolder[0] = doOpenMethodLibrary(path);
			} catch (LibraryServiceException e) {
				exceptionHolder[0] = e;
			}
		} else {
			IWorkspaceRunnable workspaceRunnable = new IWorkspaceRunnable() {

				public void run(IProgressMonitor monitor) throws CoreException {
					try {
						libHolder[0] = doOpenMethodLibrary(path);
					} catch (LibraryServiceException e) {
						exceptionHolder[0] = e;
					}
				}
				
			};
			try {
				ResourcesPlugin.getWorkspace().run(workspaceRunnable,
						ResourcesPlugin.getWorkspace().getRoot(),
						IWorkspace.AVOID_UPDATE, new NullProgressMonitor());
			} catch (CoreException e) {
				throw new LibraryServiceException(e);
			}
		}
		if(exceptionHolder[0] != null) {
			throw exceptionHolder[0];
		}
		return libHolder[0];
	}
	
	protected MethodLibrary doOpenMethodLibrary(File path) throws LibraryServiceException {
		File libraryXMIFile = new File(path, LIBRARY_XMI);
		if (!libraryXMIFile.exists()) {
			throw new LibraryNotFoundException();
		}

		VersionUtil.VersionCheckInfo info = VersionUtil.checkLibraryVersion(libraryXMIFile);
		IStatus status = XMILibraryUtil.checkVersion(libraryXMIFile, info);
		if(!status.isOK()) {
			throw new LibraryServiceException(status.getMessage());
		}
		else if(MappingUtil.conversionRequired(libraryXMIFile.getAbsolutePath(), info)) {
			throw new LibraryServiceException(LibraryResources.libUpgradeRequired_err_msg);
		}
		
		try {
			skipEventProcessing = true;

			// Open the method library project file.
			project = MethodLibraryProject.openProject(path.getAbsolutePath(), null);

			// Create the resource set.
			ILibraryResourceSet resourceSet = ((ILibraryResourceSet) editingDomain
					.getResourceSet());

			// Load the method library.
			resourceSet.loadMethodLibraries(URI.createFileURI(libraryXMIFile
					.getAbsolutePath()), Collections.EMPTY_MAP);
			library = resourceSet.getFirstMethodLibrary();

			// Add a listener to monitor library resource changes.
			addResourceChangedListeners();

			System.gc();
			
			return library;
		} catch (Exception e) {
			if (debug) {
				DebugTrace.print(e);
			}
			throw new LibraryServiceException(e);
		} finally {
			firePropertyChange(library, PROP_DIRTY);
			skipEventProcessing = false;
		}
	}

	/**
	 * Opens a method library.
	 * 
	 * @param args
	 *            method library specific arguments
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary openMethodLibrary(Map args)
			throws LibraryServiceException {
		if (debug) {
			DebugTrace.print(this, "openMethodLibrary"); //$NON-NLS-1$
		}

		if (args == null) {
			throw new IllegalArgumentException();
		}

		String path = (String) args.get(ARG_LIBRARY_PATH);
		if (path == null || path.length() == 0) {
			throw new IllegalArgumentException();
		}

		library = openMethodLibrary(new File(path));

		if (debug) {
			DebugTrace.print(this, "openMethodLibrary", "library=" + library); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return library;
	}

	/**
	 * Reopens the managed method library.
	 * 
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary reopenMethodLibrary() throws LibraryServiceException {
		if (debug) {
			DebugTrace.print(this, "reopenMethodLibrary"); //$NON-NLS-1$
		}

		try {
			isReopeningLib = true;
			if (library != null && ProcessUtil.isSynFree()) {
				isReopeningSynLib = true;
			}
			library = openMethodLibrary(new File(getMethodLibraryLocation()));
		} finally {
			isReopeningLib = false;
			isReopeningSynLib = false;
		}

		if (debug) {
			DebugTrace.print(this, "reopenMethodLibrary", "library=" + library); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return library;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.AbstractLibraryManager#getLibraryPersisterType()
	 */
	protected String getLibraryPersisterType() {
		return Services.XMI_PERSISTENCE_TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.AbstractLibraryManager#createResourceSet()
	 */
	protected ILibraryResourceSet createResourceSet() {
		return PersistenceService.INSTANCE
				.createResourceSet(Services.XMI_PERSISTENCE_TYPE);
	}

	/**
	 * Gets the absolute path to the managed method library.
	 * For distributed library, this is the library's workspace path.
	 * 
	 * @return an absolute path to the method library
	 */
	public String getMethodLibraryLocation() {
		if (debug) {
			DebugTrace.print(this, "getMethodLibraryPath"); //$NON-NLS-1$
		}

		java.net.URI libraryURI = getMethodLibraryURI();
		if (libraryURI != null) {
			File libraryXMIFile = new File(libraryURI);
			if (libraryXMIFile.getName().equalsIgnoreCase(LIBRARY_XMI)) {
				libraryXMIFile = libraryXMIFile.getParentFile();
			}
			return libraryXMIFile.getAbsolutePath();
		}
		return null;
	}
	
	/**
	 * Gets the project of the method library managed by this manager.
	 * 
	 * @return the method library project
	 */
	public IProject getMethodLibraryProject() {
		return project;
	}
	
	public void handleLibraryMoved() {
		if(library == null) {
			return;
		}
		String location = getMethodLibraryLocation();
		if(!project.isOpen() || project.getLocation().equals(new Path(location))) {
			// not moved
			//
			return;
		}
		
		// update URI of all library resources
		//
		Resource libResource = library.eResource();
		if(libResource == null || libResource.getResourceSet() == null) {
			return;
		}
		String newLocation = project.getLocation().toOSString();
		if(libResource.getResourceSet() instanceof MultiFileResourceSetImpl) {
			((MultiFileResourceSetImpl)libResource.getResourceSet()).handleLibraryMoved(newLocation);
		}
		else {
			PersistenceUtil.replaceURIPrefix(new ArrayList<Resource>(libResource.getResourceSet().getResources()), 
					location, newLocation);
		}
	}
	

	/**
	 * get the resource manager for the library
	 * @return ILibraryResourceManager
	 */
	public ILibraryResourceManager getResourceManager() {
		return resourceMgr;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.ILibraryManager#backupMethodLibrary(java.lang.String)
	 */
	public void backupMethodLibrary(String path) {
		String libPathStr = getMethodLibraryLocation();
		File libPath = new File(libPathStr);
		// excude the non-library files that might be locked by rmc.
		// these files may cause backup to fail due to file lock.
		String excludes = ".lock"; //$NON-NLS-1$
		LayoutResources.copyDir(libPath, new File(path), "**", excludes); //$NON-NLS-1$
	}

	private String getRegisterType() {
		return registerType;
	}

	private void setRegisterType(String registerType) {
		this.registerType = registerType;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.ILibraryManager#registerMethodLibrary(org.eclipse.epf.uma.MethodLibrary, java.util.Map)
	 */
	public void registerMethodLibrary(MethodLibrary lib, 
			Map<String, Object> params) throws LibraryServiceException {
		String regType = (String) params.get(ARG_LIBRARY_REGISTER_TYPE);
		if (regType != null) {
			setRegisterType(regType);
		}
		regType = getRegisterType();
		if (regType.equals("ConfigExport")) {//$NON-NLS-1$ 
			//For now, still simply wraping up old implementation (copied from ConfigurationExportService) for smoother change; will definetly change later
			//so that creating a new lib would not be needed		
			String name = "library.xmi";	//$NON-NLS-1$;	
			createMethodLibrary(name, params);
			setMethodLibrary(lib);
			return;
		}
		
		//Minimum implementation for defaul register type, to minimize caller code change
		this.library = lib;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.ILibraryManager#unRegisterMethodLibrary()
	 */
	public void unRegisterMethodLibrary() throws LibraryServiceException {
		if (getRegisterType().equals("ConfigExport")) {//$NON-NLS-1$ 
			closeMethodLibrary();
			return;
		}
		//Minimum implementation for defaul register type, to minimize caller code change
		this.library = null;
	}
	
	private Map<String, UserDefinedTypeMeta> userDefinedTypeMap;
	public Collection<UserDefinedTypeMeta> getUserDefinedTypes() {
		return userDefinedTypeMap == null ? null : userDefinedTypeMap.values();
	}
	
	public void addUserDefineType(UserDefinedTypeMeta meta) {
		if (userDefinedTypeMap == null) {
			userDefinedTypeMap = new HashMap<String, UserDefinedTypeMeta>();
		}
		userDefinedTypeMap.put(meta.getId(), meta);
	}
		
	public UserDefinedTypeMeta getUserDefineType(String id) {
		return userDefinedTypeMap == null ? null : userDefinedTypeMap.get(id);
	}
	
	private boolean userDefinedTypeLoaded = false;
	public boolean isUserDefinedTypeLoaded() {
		return userDefinedTypeLoaded;
	}
	
	public void setUserDefinedTypeLoaded(boolean b) {
		if (DebugUtil.udtDebug && !userDefinedTypeLoaded && b) {
			String str = TypeDefUtil.getMTypesDebugString(getModifiedTypes(), "User defined types loaded");//$NON-NLS-1$
			DebugUtil.print(str);
		}
		userDefinedTypeLoaded = b;
	}
	
	public void prepareToLoadUserDefinedTypes() {
		userDefinedTypeMap = null;
		modifiedTypeMap = null;
	}
	
	private Map<String, ModifiedTypeMeta> modifiedTypeMap;
	public Collection<ModifiedTypeMeta> getModifiedTypes() {
		return modifiedTypeMap == null ? null : modifiedTypeMap.values();		
	}
	
	public void addModifiedType(ModifiedTypeMeta meta) {
		if (modifiedTypeMap == null) {
			modifiedTypeMap = new HashMap<String, ModifiedTypeMeta>();
		}
		modifiedTypeMap.put(meta.getId(), meta);
	}
		
	private ModifiedTypeMeta noneValue = (ModifiedTypeMeta) TypeDefUtil
			.getInstance().createMetaDef(ModifiedTypeMeta.class);

	public ModifiedTypeMeta getModifiedType(String id) {
		if (modifiedTypeMap == null) {
			return null;
		}		
		
		ModifiedTypeMeta meta = modifiedTypeMap.get(id);
		if (meta == noneValue) {
			return null;
		} else if (meta != null) {
			return meta;
		}
		
		try {
			Class cls = Class.forName(id);
			if (cls == null) {
				modifiedTypeMap.put(id, noneValue);
				return null;
			}
			List<Class> list = new ArrayList<Class>();
			list.add(cls);
			cls = TypeDefUtil.getSuperClass(cls);
			while (cls != null) {
				meta = modifiedTypeMap.get(cls.getName());
				if (meta != null) {
					break;
				}
				list.add(cls);
				cls = TypeDefUtil.getSuperClass(cls);
			}
			if (meta == null) {
				meta = noneValue;
			}
			for (Class c : list) {
				modifiedTypeMap.put(c.getName(), meta);
			}
			
		} catch (Exception e) {
//			e.printStackTrace();
		}
		return meta == noneValue ? null : meta;
	}
	
	private List<ExtendedOpposite> opposites;
	public List<ExtendedOpposite> getLoadedOpposites() {
		if (! isUserDefinedTypeLoaded() || modifiedTypeMap == null) {
			return Collections.EMPTY_LIST;
		}
		if (opposites == null) {
			opposites = new ArrayList<ExtendedOpposite>();
			Set<ExtendedOpposite> set = new HashSet<ExtendedOpposite>();
			for (ModifiedTypeMeta meta : modifiedTypeMap.values()) {
				for (ExtendedReference ref : meta.getReferences()) {
					ExtendedOpposite o = ref.getOpposite();
					if (o != null && set.add(o)) {
						opposites.add(o);
					}
				}
			}
			Collections.sort(opposites);
		}
		return opposites;
	}
	
}