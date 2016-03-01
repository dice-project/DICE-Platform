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
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.common.service.versioning.VersionUtil;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceException;
import org.eclipse.epf.library.util.ResourceUtil;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.osgi.util.NLS;

/**
 * Helper utilities for accessing a XMI method library.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class XMILibraryUtil {

	private static final String EMPTY_METHOD_LIBRARY_PATH_MSG = LibraryResources.emptyLibraryPathError_reason;

	private static final String MISSING_LIBRARY_FILE_MSG = LibraryResources.missingLibraryFileError_reason;

	private static final String COULD_NOT_LOCK_LIBRARY_MSG = LibraryResources.couldNotLockLibrary_reason;

	public static final IStatus EMPTY_METHOD_LIBRARY_PATH_STATUS = new Status(
			Status.ERROR, LibraryPlugin.getDefault().getId(), 1,
			EMPTY_METHOD_LIBRARY_PATH_MSG, null);

	public static final IStatus MISSING_LIBRARY_FILE_STATUS = new Status(
			Status.ERROR, LibraryPlugin.getDefault().getId(), 1,
			MISSING_LIBRARY_FILE_MSG, null);

	public static final IStatus COULD_NOT_LOCK_LIBRARY_STATUS = new Status(
			Status.ERROR, LibraryPlugin.getDefault().getId(), 1,
			COULD_NOT_LOCK_LIBRARY_MSG, null);

	static {
		try {
			// TODO: This call is needed to initialize the persistence layer
			// to avoid a null pointer exception in
			// PersistenceUtil.conversionRequired(). A better solution is
			// needed.
			new MultiFileResourceSetImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks whether a method library exists on a given path.
	 * 
	 * @param path
	 *            an absolute path to a method library
	 * @return <code>true</code> if the given path contains a valid method
	 *         library
	 */
	public static boolean hasMethodLibrary(String path) {
		if (path == null) {
			throw new IllegalArgumentException();
		}

		File libraryDir = new File(path);
		if (!libraryDir.exists()) {
			return false;
		}

		File libraryFile = new File(libraryDir, XMILibraryManager.LIBRARY_XMI);
		if (!libraryFile.exists()) {
			return false;
		}

		return true;
	}

	/**
	 * Returns the absolute path to a method library model (library.xmi) file.
	 * 
	 * @param path
	 *            an absolute path to a method library
	 * @returnt an absolute path to a method library model file (if one exists)
	 */
	public static String getLibraryModelFile(String path) {
		if (!StrUtil.isBlank(path)) {
			String libPath = new File(path).getAbsolutePath();
			String suffix = File.separator
					+ MultiFileSaveUtil.DEFAULT_LIBRARY_MODEL_FILENAME;
			if (libPath.endsWith(suffix)) {
				return libPath;
			} else {
				return libPath + suffix;
			}
		}
		return path;
	}

	/**
	 * Validates a method library path.
	 * 
	 * @param path
	 *            an absolute path to a method library
	 * @param validateModelFile
	 *            if <code>true</code>, validate that the library.xmi file
	 *            also exists.
	 * @return <code>true</code> if the given path is a valid method library
	 *         folder
	 */
	public static IStatus isValidLibrary(String path, boolean validateModelFile) {
		if (path == null || path.trim().length() == 0) {
			return EMPTY_METHOD_LIBRARY_PATH_STATUS;
		}

		if (validateModelFile) {
			File libFile = new File(getLibraryModelFile(path));
			if (!libFile.exists()) {
				return MISSING_LIBRARY_FILE_STATUS;
			}
		}

		File libPath = new File(path);
		if (path.endsWith(MultiFileSaveUtil.DEFAULT_LIBRARY_MODEL_FILENAME)) {
			libPath = libPath.getParentFile();
		}

		String libFullPath = libPath.getAbsolutePath();

		if (libFullPath.startsWith("\\")) { //$NON-NLS-1$
			// the first part must be a machine name, and you must specify a
			// folder

			boolean isValid = true;
			String uncPath;
			int index = libFullPath.indexOf(File.separatorChar, 2);
			if (index < 0) {
				isValid = false;
			} else {
				uncPath = libFullPath.substring(index + 1);
				if (uncPath.length() == 0) {
					// must specify a valid path
					isValid = false;
				} else {
					// the top level path must be valid
					index = libFullPath.indexOf(File.separatorChar, index + 1);
					if (index < 0) {
						uncPath = libFullPath;
					} else {
						uncPath = libFullPath.substring(0, index);
					}

					File f = new File(uncPath);
					if (!f.exists()) {
						isValid = false;
					}
				}
			}

			if (!isValid) {
				// no new resource string, wait later
				// return new Status(Status.ERROR,
				// LibraryPlugin.getDefault().getId(), 1, "Invalid path", null);
				return EMPTY_METHOD_LIBRARY_PATH_STATUS;
			}
		}

		// Check for presence of a .project file.
		IProjectDescription description = null;
		try {
			description = ResourcesPlugin
					.getWorkspace()
					.loadProjectDescription(
							new Path(libFullPath + File.separator
									+ IProjectDescription.DESCRIPTION_FILE_NAME));
			if (description != null) {
				return Status.OK_STATUS;
			}
		} catch (CoreException e) {
		}

		// If a .project file does not exists, use it to validate the path.
		// only call validateProjectLocation if path is not on workspace
		// for some reason, validateProjectLocation only works on paths not on
		// the workspace
		if (Platform.getLocation().isPrefixOf(new Path(libFullPath))) {
			return Status.OK_STATUS;
		}
		IProject project = ResourceUtil.findProject(libFullPath);
		if (project == null) {
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(
					libPath.getName());
		}

		return ResourcesPlugin.getWorkspace().validateProjectLocation(project,
				new Path(libFullPath));
	}

	/**
	 * Validates a method library path.
	 * 
	 * @param path
	 *            an absolute path to a method library
	 * @return <code>true</code> if the given path is a valid method library
	 *         folder
	 */
	public static IStatus isValidLibrary(String path) {
		return isValidLibrary(path, false);
	}

	/**
	 * Checks whether a method library needs to be upgraded to a new format.
	 * 
	 * @param path
	 *            An absolute path to a method library
	 * @return <code>true</code> if the method library needs to be upgraded
	 */
	public static boolean isMethodLibraryUpgradeRequired(String path,
			VersionUtil.VersionCheckInfo info) {
		return isMethodLibraryUpgradeRequired(path,
				XMILibraryManager.LIBRARY_XMI, info);
	}

	public static boolean isMethodLibraryUpgradeRequired(String path,
			String libXmi, VersionUtil.VersionCheckInfo info) {
		return org.eclipse.epf.persistence.migration.MappingUtil
				.conversionRequired(path + File.separator + libXmi, info);
	}

	/**
	 * Creates a new XMI method library.
	 * 
	 * @param name
	 *            a name for the new method library
	 * @param path
	 *            an absolute path to a method library
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public static MethodLibrary createMethodLibrary(String name, String path)
			throws LibraryServiceException {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put(XMILibraryManager.ARG_LIBRARY_PATH, path);
		return LibraryService.getInstance().createMethodLibrary(name,
				XMILibraryManager.LIBRARY_TYPE, args);
	}

	/**
	 * Opens an existing method library.
	 * 
	 * @param path
	 *            an absolute path to a method library
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public static MethodLibrary openMethodLibrary(String path)
			throws LibraryServiceException {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put(XMILibraryManager.ARG_LIBRARY_PATH, path);
		return LibraryService.getInstance().openMethodLibrary(
				XMILibraryManager.LIBRARY_TYPE, args);
	}

	/**
	 * Opens the method library project associated with a method library.
	 * 
	 * @param library
	 *            a method library
	 * @param monitor
	 *            a progress monitor
	 */
	public static void openMethodLibraryProject(MethodLibrary library,
			IProgressMonitor monitor) {
		if (library != null && library.eResource() != null
				&& library.eResource().getURI().isFile()) {
			IProject project = ResourceUtil.findProject(library);
			if (project != null && !project.isOpen()) {
				try {
					project.open(IResource.BACKGROUND_REFRESH, monitor);
				} catch (CoreException e) {
					XMILibraryPlugin.getDefault().getLogger().logError(e);
				}
			}
		}
	}

	/**
	 * Check if the directory given by specified path contains a plugin or
	 * config spec export file.
	 * 
	 * @param path
	 *            an absolute path to a method library
	 */
	public static boolean containsPluginOrConfigSpecExportFile(String path) {
		if (path == null || path.trim().length() == 0) {
			return false;
		}
		path = new File(path).getAbsolutePath();
		path += File.separator + XMILibraryManager.exportFile;
		File exportFile = new File(path);
		return exportFile.exists();
	}

	public static IStatus checkVersion(File file,
			VersionUtil.VersionCheckInfo info) {
		if (info != null && info.result > 0) {
			String message;
			if (info.toolID.equals(VersionUtil.getPrimaryToolID())) {
				message = NLS.bind(LibraryResources.oldToolVersion_err_msg,
						new Object[] { Platform.getProduct().getName(),
								info.toolVersion, file });
			} else {
				message = NLS.bind(
						LibraryResources.toolVersionMismatch_err_msg,
						new Object[] { Platform.getProduct().getName() });
			}
			return new Status(IStatus.ERROR, XMILibraryPlugin.getDefault()
					.getId(), 0, message, null);
		}
		return Status.OK_STATUS;
	}
}
