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
import java.security.AccessController;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.internal.resources.ResourceException;
import org.eclipse.core.internal.resources.ResourceStatus;
import org.eclipse.core.internal.utils.Messages;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.persistence.util.PersistenceResources;
import org.eclipse.epf.services.IFileManager;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.osgi.util.NLS;

import sun.security.action.GetPropertyAction;

/**
 * Implementation class for IFileManager.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class FileManager implements IFileManager {

	public static final String PLUGIN_ID = FileManager.class.getPackage()
			.getName();

	private static FileManager instance = null;

	private static String tmpdir;

	public static String getTempDir() {
		if (tmpdir == null) {
			GetPropertyAction a = new GetPropertyAction("java.io.tmpdir"); //$NON-NLS-1$
			tmpdir = ((String) AccessController.doPrivileged(a));
		}
		return tmpdir;
	}

	private boolean validateEditInitialized = false;

	public static final FileManager getInstance() {
		if (instance == null) {
			synchronized (FileManager.class) {
				if (instance == null) {
					instance = new FileManager();
				}
			}
		}

		return instance;
	}

	protected FileManager() {
	}

	public static IResource getResourceForLocation(String location) {
		File file = new File(location);
		if (!file.exists()) {
			return null;
		}
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath path = new Path(location);
		IResource resource;
		if (file.isFile()) {
			resource = workspaceRoot.getFileForLocation(path);
			if (resource == null) {
				IResource parentResource = getResourceForLocation(file
						.getParent());
				if (parentResource != null) {
					try {
						parentResource.refreshLocal(IResource.DEPTH_ONE, null);
					} catch (CoreException e) {
//						CommonPlugin.INSTANCE.log(e);
					}
					resource = workspaceRoot.getFileForLocation(path);
				}
			}
		} else {
			resource = workspaceRoot.getContainerForLocation(path);
		}
		return resource;
	}

	public static boolean refresh(IResource resource) throws CoreException {
		if (!resource.exists()) {
			ArrayList foldersToRefresh = new ArrayList();
			IContainer container;
			for (container = resource.getParent(); !container.exists(); container = container
					.getParent()) {
				foldersToRefresh.add(0, container);
			}
			if (container.exists()) {
				container.refreshLocal(IResource.DEPTH_ONE, null);
			}
			if (!foldersToRefresh.isEmpty()) {
				for (Iterator iter = foldersToRefresh.iterator(); iter
						.hasNext();) {
					IFolder folder = (IFolder) iter.next();
					if (folder.exists()) {
						folder.refreshLocal(IResource.DEPTH_ONE, null);
					} else {
						return false;
					}
				}
			}
		}
		resource.refreshLocal(IResource.DEPTH_ONE, null);
		return true;
	}

	/**
	 * Refreshes file or directory with given local file system
	 * <code>path</code>
	 * 
	 * @param path
	 *            local file system path
	 * @return
	 * @throws CoreException
	 */
	private static boolean refresh(String path) throws CoreException {
		IResource resource = getResourceForLocation(path);
		if (resource != null) {
			return refresh(resource);
		}
		return false;
	}

	public boolean refresh(Resource resource) {
		try {
			return refresh(toFileString(resource.getURI()));
		} catch (CoreException e) {
//			CommonPlugin.INSTANCE.log(e);
			return false;
		}
	}

	public boolean move(String oldPath, String newPath) {
		return move(oldPath, newPath, false);
	}

	public boolean move(String oldPath, String newPath,
			boolean forceRemoveSource) {
		try {
			refresh(oldPath);

			IResource resource = null;
			IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace()
					.getRoot();

			// create the folders of the destination if they did not exist
			IPath destPath = new Path(newPath);
			if (new File(oldPath).isFile()) {
				resource = workspaceRoot.getFileForLocation(destPath);
				if(resource == null) {
					resource = workspaceRoot.getContainerForLocation(destPath);
				}
			} else {				
				resource = workspaceRoot.getContainerForLocation(destPath);
				if(resource == null) {
					resource = workspaceRoot.getFileForLocation(destPath);
				}
			}	
			if(resource != null) {
				if (resource.exists()) {
					resource.refreshLocal(IResource.DEPTH_ZERO, null);
					if(resource.exists()) {
						throw new MultiFileIOException(NLS.bind(
								PersistenceResources.moveError_msg, oldPath, newPath));
					}
				}
				ArrayList foldersToCreate = new ArrayList();
				IContainer container;
				for (container = resource.getParent(); !container.exists(); container = container
				.getParent()) {
					foldersToCreate.add(0, container);
				}
				if (!foldersToCreate.isEmpty()) {
					container.refreshLocal(IResource.DEPTH_ONE, null);
					for (Iterator iter = foldersToCreate.iterator(); iter.hasNext();) {
						IFolder folder = (IFolder) iter.next();
						if (!folder.exists()) {
							folder.create(true, true, null);
						} else {
							folder.refreshLocal(IResource.DEPTH_ONE, null);
						}
					}
				}
				destPath = resource.getFullPath();
			}
			else if(Platform.getLocation().isPrefixOf(destPath)) {
				destPath = new Path(destPath.toOSString().substring(Platform.getLocation().toOSString().length()));						
			}
			
			IPath path = new Path(oldPath);
			IFile file = workspaceRoot.getFileForLocation(path);
			if (file != null && file.exists()) {
				resource = file;
			} else {
				resource = workspaceRoot.getContainerForLocation(path);
			}
			if (resource != null) {
				try {
					resource.move(destPath, true, null);
				} catch (ResourceException e) {
					PersistencePlugin.getDefault().getLog().log(e.getStatus());
					
					if (forceRemoveSource) {
						throw e;
					}

					boolean failed = false;

					// handle situation when Eclipse moves file/directory by
					// copying it to destination then deleting the source
					// but deletion failed
					IStatus[] statuses = e.getStatus().getChildren();
					for (int i = 0; i < statuses.length; i++) {
						IStatus status = statuses[i];
						if (status.getCode() == IResourceStatus.FAILED_DELETE_LOCAL
								&& status.getMessage() == Messages.localstore_deleteProblem) {
							String msg = MessageFormat
									.format(
											"Warning while moving ''{0}'' to ''{1}'': {2}", new Object[] { oldPath, newPath, status.getMessage() }); //$NON-NLS-1$
							PersistencePlugin.getDefault().getLogger().logWarning(msg);
						} else {
							failed = true;
						}
					}
					if (failed || !new File(newPath).exists()) {
						return false;
					}
				}
				return true;
			}
		} catch (CoreException e) {
			PersistencePlugin.getDefault().getLogger().logError(e);
			if (MultiFileSaveUtil.DEBUG) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean rename(File oldFile, File newFile) {
		return move(oldFile.getAbsolutePath(), newFile.getAbsolutePath());
	}

	public void deleteResource(String path, IProgressMonitor monitor)
			throws CoreException {
		// no need to refresh the whole tree from specified path
		// getResourceForLocation() refreshes resource in a more efficient way
		//
		// IWorkspaceRoot workspaceRoot =
		// ResourcesPlugin.getWorkspace().getRoot();
		// try {
		// workspaceRoot.refreshLocal(IResource.DEPTH_INFINITE, monitor);
		// } catch (CoreException e1) {
		// e1.printStackTrace();
		// }

		IResource resource = getResourceForLocation(path);
		if (resource != null) {
			resource.delete(true, monitor);
		}
	}

	public boolean delete(String path) {
		try {
			deleteResource(path, null);
			return true;
		} catch (CoreException e) {
			CommonPlugin.INSTANCE.log(e);
			if (MultiFileSaveUtil.DEBUG) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private static boolean fromCC(IStatus status) {
		String pluginId = status.getPlugin();
		return pluginId != null
				&& pluginId.toLowerCase().indexOf("clearcase") != -1; //$NON-NLS-1$
	}

	public IStatus checkModify(String[] paths, Object context) {
		IStatus status = null;
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IFile[] files = new IFile[paths.length];
		ArrayList<String> notFoundFiles = new ArrayList<String>();
		for (int i = 0; i < paths.length; i++) {
			String path = paths[i];
			try {
				path = new File(path).getCanonicalPath();
				refresh(path);
			} catch (Exception e) {
				PersistencePlugin.getDefault().getLogger().logError(e);
			}
			IFile file = workspace.getRoot().getFileForLocation(new Path(path));
			if (file == null) {
				notFoundFiles.add(path);
			} else {
				files[i] = file;
			}
		}
		if (!notFoundFiles.isEmpty()) {
			return new Status(IStatus.WARNING, PLUGIN_ID, IStatus.WARNING, NLS
					.bind(PersistenceResources.fileNotFoundError_msg,
							notFoundFiles), null);
		}

		if (!validateEditInitialized) {
//			status = workspace.validateEdit(files, context);
			status = FileUtil.validateEdit(workspace, files, context);
			validateEditInitialized = true;
			if (status.isOK()) {
				// double-check after initialization
				//
//				status = workspace.validateEdit(files, context);
				status = FileUtil.validateEdit(workspace, files, context);
			}
		} else {
//			status = workspace.validateEdit(files, context);
			status = FileUtil.validateEdit(workspace, files, context);
		}

		if (status.isOK()) {
			MultiStatus multiStatus = new MultiStatus(PLUGIN_ID,
					IStatus.OK, PersistenceResources.modifyFilesError_msg,
					null);

			// some version control provider still returns OK status even though
			// user cancelled the check out
			// double-check here again to make sure the file is not read-only
			//			
			for (int i = 0; i < files.length; i++) {
				IFile file = files[i];
				try {
					file.refreshLocal(IResource.DEPTH_ZERO, null);
				} catch (CoreException e) {
					CommonPlugin.INSTANCE.log(e);
				}
				if (file.isReadOnly()) {
					String localPath = file.getLocation().toOSString();
					String msg = MessageFormat.format(
							PersistenceResources.FileManager_fileReadOnly,
							new Object[] { localPath });
					multiStatus.add(new ResourceStatus(IStatus.ERROR, 0, file
							.getFullPath(), msg, null));
				}
			}
			if(!multiStatus.isOK()) {
				return multiStatus;
			}
		} else {
			// hack for clearcase
			if (fromCC(status)) {
				String msg = PersistenceResources.modifyFilesError_msg;
				MultiStatus multiStatus = new MultiStatus(PLUGIN_ID, status
						.getCode(), msg, null);
				multiStatus.add(status);
				return multiStatus;
			}
		}

		// convert workspace path to local file system path
		if (status instanceof MultiStatus) {
			MultiStatus ms = (MultiStatus) status;
			for (int i = 0; i < ms.getChildren().length; i++) {
				IStatus childStatus = ms.getChildren()[i];
				ms.getChildren()[i] = toStatusWithLocalPath(childStatus);
			}
		}
		else {
			status = toStatusWithLocalPath(status);
		}

		return status;
	}
	
	private static IStatus toStatusWithLocalPath(IStatus status) {
		if (status instanceof IResourceStatus
				&& status.getCode() == IResourceStatus.READ_ONLY_LOCAL) {
			IResourceStatus resourceStatus = ((IResourceStatus) status);
			IPath path = resourceStatus.getPath();
			IFile file = ResourcesPlugin.getWorkspace().getRoot()
					.getFile(path);
			String localPath = file.getLocation().toOSString();
			String msg = MessageFormat.format(
					PersistenceResources.FileManager_fileReadOnly,
					new Object[] { localPath }); 
			return new ResourceStatus(status
					.getSeverity(), status.getCode(),
					resourceStatus.getPath(), msg, status
							.getException());
		}
		return status;
	}

	/**
	 * @see org.eclipse.epf.services.IFileManager#checkModify(java.lang.String,
	 *      java.lang.Object)
	 */
	public IStatus checkModify(String path, Object context) {
		return checkModify(new String[] { path }, context);
	}

	/**
	 * Checks if the given path is team-private file or folder
	 * 
	 * @param path
	 * @return
	 */
	public boolean isTeamPrivate(String path) {
		IResource resource = getResourceForLocation(path);
		return resource != null && resource.isTeamPrivateMember();
	}

    /**
     * Finds the file corresponding to the specified URI, using a URI converter
     * if necessary (and provided) to normalize it.
     * 
     * @param uri a URI
     * @param converter an optional URI converter (may be <code>null</code>)
     * 
     * @return the file, if available in the workspace
     */
    public static IFile getFile(URI uri, URIConverter converter) {
        IFile result = null;
        
        if ("platform".equals(uri.scheme()) && (uri.segmentCount() > 2)) { //$NON-NLS-1$
            if ("resource".equals(uri.segment(0))) { //$NON-NLS-1$
                IPath path = new Path(URI.decode(uri.path())).removeFirstSegments(1);
                
                result = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
            }
        } else if (uri.isFile() && !uri.isRelative()) {
            result = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(
                new Path(uri.toFileString()));
        } else {
            // normalize, to see whether may we can resolve it this time
            if (converter != null) {
                URI normalized = converter.normalize(uri);
                
                if (!uri.equals(normalized)) {
                    // recurse on the new URI
                    result = getFile(normalized, converter);
                }
            }
        }
        
        return result;
    }

	public static String toFileString(URI uri, URIConverter converter) {
		if(uri.isFile()) {
			return uri.toFileString();
		}
		IFile file = getFile(uri, converter);
		return file != null ? file.getLocation().toOSString() : null;
	}
	
	public static String toFileString(URI uri) {
		return toFileString(uri, null);
	}
	
	public static class FileInfo implements IFileInfo {
		
		private IFile file;

		private FileInfo(IFile file) {
			this.file = file;
		}

		public long getModificationStamp() {
			return file.getModificationStamp();
		}

		public boolean isSynchronized() {
			return file.isSynchronized(IResource.DEPTH_ZERO);
		}

		public File getFile() {
			IPath loc = file.getLocation();
			return loc != null ? file.getLocation().toFile() : null;
		}
		
	}
	
	public IFileInfo getFileInfo(Resource resource) {
		IFile file = WorkspaceSynchronizer.getFile(resource);
		return file != null ? new FileInfo(file) : null;
	}
	
	public static boolean copyFile(File srcFile, File tgtFile) {
		boolean b = FileUtil.copyFile(srcFile, tgtFile) ;
		if (b) {
			IResource wsResource = tgtFile == null ? null : FileManager.getResourceForLocation(tgtFile.getAbsolutePath());
			if(wsResource != null) {
				try {
					FileManager.refresh(wsResource);
				}
				catch(Exception e) {
				}
			}
		}
		return b;
	}		
	
	public static IStatus checkModifyPathList(List<String> modifiedFiles, Object context) {
		if (modifiedFiles == null || modifiedFiles.isEmpty()) {
			return Status.OK_STATUS;
		}
		String[] paths = new String[modifiedFiles.size()];
		modifiedFiles.toArray(paths);
		IFileManager fileMgr = Services.getFileManager();
		IStatus status = fileMgr.checkModify(paths, context);
		return status;
	}
	
	public static IStatus checkModifyResources(Set<Resource> resources, Object context) {
		List<String> modifiedFiles = UmaUtil.getResourceFilePaths(resources);
		IStatus status = checkModifyPathList(modifiedFiles, context);
		return status;
	}
	
	public static IStatus checkModifyElements(Set<MethodElement> elements, Object context) {
		Set<Resource> resources = UmaUtil.getElementResources(elements);
		return checkModifyResources(resources, context);
	}
	
}
