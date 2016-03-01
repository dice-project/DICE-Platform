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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryResources;
import org.eclipse.epf.library.project.MethodLibraryProject;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * Helper class for managing the Method Library as a project in the Eclipse
 * workspace.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ResourceUtil {

	private static final String methodLibraryNature = "org.eclipse.epf.library.project.MethodLibraryProjectNature"; //$NON-NLS-1$

	private static final String[] methodLibraryNatures = { methodLibraryNature };

	private static final boolean debug = LibraryPlugin.getDefault().isDebugging();

	public static final String LOCK_FILENAME = ".lock"; //$NON-NLS-1$

	/**
	 * 
	 * @param lib MethodLibrary
	 * @param monitor IProgressMonitor
	 * @throws CoreException
	 * @throws IOException
	 */
	public static final void open(MethodLibrary lib, IProgressMonitor monitor)
			throws CoreException, IOException {
		open(getLibraryDirectory(lib), monitor);
	}

	/**
	 * 
	 * @param libDir String
	 * @param monitor IProgressMonitor
	 * @throws CoreException
	 * @throws IOException
	 */
	public static final void open(String libDir, IProgressMonitor monitor)
			throws CoreException, IOException {
		IPath path = new Path(libDir + File.separator
				+ IProjectDescription.DESCRIPTION_FILE_NAME);
		IProjectDescription description = null;

		try {
			description = ResourcesPlugin.getWorkspace()
					.loadProjectDescription(path);
		} catch (CoreException exception) {
			// missing .project file
			if (debug) {
				System.out.println("### - exception in loadProjectDescription"); //$NON-NLS-1$
			}
		}

		IProject project = null;
		try {
			if (description == null) {
				// .project file is missing - create a new project
				if (debug) {
					System.out
							.println("### - creating new method library project"); //$NON-NLS-1$
				}
				project = createValidProjectFromPath(libDir, monitor);
			} else {
				// .project file exists
				if (debug) {
					System.out
							.println("### - opening existing method library project " + description.getName()); //$NON-NLS-1$
				}

				// check if project of this name exists in workspace
				project = ResourcesPlugin.getWorkspace().getRoot().getProject(
						description.getName());

				if (!project.exists()) {
					// project doesn't exist in workspace, so make one with the
					// project name in .project
					try {
						if (debug) {
							System.out
									.println("### - creating existing .project " + description.getName() + " in workspace"); //$NON-NLS-1$ //$NON-NLS-2$
						}
						project = createProject(libDir, project.getName(),
								monitor);
					} catch (CoreException ex) {
						// failed to make this project in the workspace -
						// another project may exist in the workspace at this
						// location
						// so we need to delete that conflicting project and try
						// again
						if (debug) {
							System.out
									.println("### - creating existing .project failed - deleting workspace-project in " + //$NON-NLS-1$
											libDir
											+ " and creating workspace-project " + project.getName()); //$NON-NLS-1$
						}
						deleteProject(libDir, monitor);
						project = createProject(libDir, project.getName(),
								monitor);
					}
				} else {
					// project does exist in workspace - make sure location
					// matches, if not, make a new project
					if (debug) {
						System.out
								.println("### - project " + project.getName() + " exists in workspace"); //$NON-NLS-1$ //$NON-NLS-2$
					}
					if (new File(libDir).compareTo(project.getLocation()
							.toFile()) != 0) {
						if (debug) {
							System.out
									.println("### - project " + project.getName() + " exists in workspace, but location differs - create a new project"); //$NON-NLS-1$ //$NON-NLS-2$
						}
						File projectFile = path.toFile();
						if (projectFile.canWrite()) {
							projectFile.delete();
						} else {
							// this will cause the caller to display a
							// messagebox asking the user to make .project
							// writable
							// TODO: use our own type of Exception?
							throw new IOException(
									"###" + projectFile.getAbsolutePath()); //$NON-NLS-1$
						}
						project = createValidProjectFromPath(libDir, monitor);
					}
				}
			}
			if (project == null) {
				// libDir must be an invalid path (overlaps workspace?)
				// TODO: this exception isn't handled well - it shouldn't happen
				throw new IOException(LibraryResources.invalidLibraryPathError_reason);
			}
			project.open(IResource.BACKGROUND_REFRESH, monitor);						
			
			// Set lock file as team private member so it will not be shared (added to source control).
			// This must be done before the project folder got refreshed. So this portion of code must be
			// executed right after IProject.open() returned. Project must be opened with IResource.BACKGROUND_REFRESH
			// to prevent lock file from being added to the project before it is set as team private member
			//
			File lockFile = new File(libDir, ResourceUtil.LOCK_FILENAME);
			if(lockFile.exists()) {
				try {
					IResource wsRes = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(lockFile.getAbsolutePath()));
					if(wsRes != null) {
						wsRes.refreshLocal(IResource.DEPTH_ZERO, null);
						wsRes.setTeamPrivateMember(true);
					}
				} catch (Exception e) {
					LibraryPlugin.getDefault().getLogger().logError(e);
				}
			}

			// monitor for change in the resource
//			RefreshManager.getInstance().monitor(project);

			description = project.getDescription();

			// make sure project has the MethodLibraryNature
			String[] existingNatures = description.getNatureIds();
			if (!(Arrays.asList(existingNatures).contains(methodLibraryNature))) {
				if (debug) {
					System.out
							.println("### - adding methodlibrarynature to " + description.getName()); //$NON-NLS-1$
				}
				description.setNatureIds(methodLibraryNatures);
				project.setDescription(description, IProject.FORCE
						| IProject.KEEP_HISTORY, monitor);
			}
		} catch (CoreException ex) {
			LibraryPlugin.getDefault().getLogger().logError(ex);
			throw ex;
		}
	}

	/**
	 * Closes a library's project. It finds the project by locating a project
	 * with the same location as the library.
	 * 
	 * @param lib
	 * @param monitor
	 * @throws CoreException
	 */
	public static final void close(MethodLibrary lib, IProgressMonitor monitor)
			throws CoreException {
//		IProject project = findProject(lib);
//		if (project != null && project.isOpen()) {
//			if (debug) {
//				System.out
//						.println("### - closing project " + project.getName()); //$NON-NLS-1$
//			}
//			project.close(monitor);
//		} else {
//			if (debug) {
//				System.out
//						.println("### - ERROR closing project for library in \"" + getLibraryDirectory(lib) + "\": project could not be found."); //$NON-NLS-1$ //$NON-NLS-2$
//			}
//		}
		
		close(getLibraryDirectory(lib), monitor);
	}

	/**
	 * 
	 * @param dir String
	 * @param monitor IProgressMonitor
	 * @throws CoreException
	 */
	public static final void close(String dir, IProgressMonitor monitor)
	throws CoreException {
		IProject project = findProject(dir);
		if (project != null && project.isOpen()) {
			if (debug) {
				System.out
						.println("### - closing project " + project.getName()); //$NON-NLS-1$
			}
			project.close(monitor);
			
			// stop monitoring change on the project
			//
//			RefreshManager.getInstance().unmonitor(project);
			
		} else {
			if (debug) {
				System.out
						.println("### - ERROR closing project for library in \"" + dir + "\": project could not be found."); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}

		
	/**
	 * Locates the project with the same location as the library's directory.
	 * 
	 * @param lib MethodLibrary
	 * @return IProject
	 */
	public static IProject findProject(MethodLibrary lib) {
		return findProject(getLibraryDirectory(lib));
	}

	/**
	 * Locatest the project whose location matches the specified directory.
	 * 
	 * @param dir String
	 * @return IProject
	 */
	public static IProject findProject(String dir) {
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject[] projects = workspace.getRoot().getProjects();
		try {
			for (int i = 0; i < projects.length; i++) {
				IProject project = projects[i];
				if (new File(dir).compareTo(project.getLocation().toFile()) == 0) {
					if (debug) {
						System.out
								.println("### - found project " + project.getName() + " with location " + dir); //$NON-NLS-1$ //$NON-NLS-2$
					}
					return project;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			LibraryPlugin.getDefault().getLogger().logError(ex);
		}
		return null;
	}

	/**
	 * Deletes the project whose location is the same as the library.
	 * 
	 * @param lib MethodLibrary
	 * @param monitor IProgressMonitor
	 * @throws CoreException
	 */
	public static final boolean deleteProject(MethodLibrary lib,
			IProgressMonitor monitor) throws CoreException {
		return deleteProject(getLibraryDirectory(lib), monitor);
	}

	/**
	 * Deletes the project whose location is the same as the given path.
	 * 
	 * @param path
	 * @param monitor
	 * @return
	 * @throws CoreException
	 */
	private static boolean deleteProject(String path, IProgressMonitor monitor)
			throws CoreException {
		// delete any workspace project that exists for this path
		IProject project = findProject(path);
		try {
			if (project != null) {
				if (debug) {
					System.out
							.println("### - deleting project " + project.getName()); //$NON-NLS-1$
				}
				if (monitor == null) {
					monitor = new NullProgressMonitor();
				}
				project.delete(IProject.FORCE
						| IProject.NEVER_DELETE_PROJECT_CONTENT, monitor);
			}
		} catch (CoreException ex) {
			ex.printStackTrace();
			LibraryPlugin.getDefault().getLogger().logError(ex);
			return false;
		}
		return true;
	}

	/**
	 * Creates a project for the library.
	 * 
	 * @return null if the library is not in a valid project location (ie,
	 *         overlaps the workspace).
	 */
	public static IProject createProject(MethodLibrary lib,
			IProgressMonitor monitor) {
		return createValidProjectFromPath(getLibraryDirectory(lib), monitor);
	}

	/**
	 * Creates a project for the path specified, using the last path fragment as
	 * the project name. Will append increasing integers to the project name in
	 * order to create a valid project. First deletes any workspace project
	 * whose location is the given path.
	 * 
	 * @param path
	 * @param monitor
	 * @return IProject
	 */
	public static IProject createValidProjectFromPath(String path,
			IProgressMonitor monitor) {
		// delete any workspace project that exists for this path
		IProject project = findProject(path);
		try {
			if (project != null) {
				project.delete(IProject.FORCE
						| IProject.NEVER_DELETE_PROJECT_CONTENT, monitor);
			}
		} catch (CoreException ex) {
			LibraryPlugin.getDefault().getLogger().logError(ex);
			return null;
		}

		String libName = new File(path).getName();
		String libDir = new File(path).getAbsolutePath();

		String projectName = libName;
		int i = 2;
		while (i < 100) {
			try {
				project = createProject(libDir, projectName, monitor);
				if (debug) {
					System.out
							.println("### - creating new method library project " + projectName); //$NON-NLS-1$
				}
				return project;
			} catch (CoreException ex) {
				projectName = new StringBuffer(libName).append(' ').append(i++)
						.toString();
			}
		}
		return null;
	}

	/**
	 * Attempts to create a project named projectName in the directory dir.
	 * Throws CoreException if this fails for any reason.
	 * 
	 * @param dir
	 * @param projectName
	 * @param monitor
	 * @return
	 * @throws CoreException
	 */
	private static final IProject createProject(String dir, String projectName,
			IProgressMonitor monitor) throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();

		// get a project handle
		IProject newProjectHandle = workspace.getRoot().getProject(projectName);
		IProjectDescription description = workspace
				.newProjectDescription(newProjectHandle.getName());
		description.setNatureIds(methodLibraryNatures);
		IPath projectPath = new Path(dir);
		if (!Platform.getLocation().isPrefixOf(projectPath)) {
			description.setLocation(projectPath);
		}

		newProjectHandle.create(description, monitor);
		return newProjectHandle;
	}

	/**
	 * Closes all (open) projects that have the MethodLibrary nature
	 * 
	 * @param monitor IProgressMonitor
	 */
	public static void closeAllMethodLibraryProjects(IProgressMonitor monitor) {
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject[] projects = workspace.getRoot().getProjects();
		try {
			for (int i = 0; i < projects.length; i++) {
				IProject project = projects[i];
				if (project.isOpen()
						&& project.getNature(methodLibraryNature) != null) {
					if (debug) {
						System.out
								.println("### - closing method library project " + project.getName()); //$NON-NLS-1$
					}
					project.close(monitor);
				}
			}
		} catch (CoreException ex) {
			ex.printStackTrace();
			LibraryPlugin.getDefault().getLogger().logError(ex);
		}
	}

	/**
	 * 
	 * @param lib MethodLibrary
	 * @return String
	 */
	public static String getLibraryDirectory(MethodLibrary lib) {
		return new File(lib.eResource().getURI().toFileString()).getParent();
	}

	/**
	 * 
	 * @param usedResources
	 * @return Collection
	 */
	public static Collection<Resource> getOutOfSyncResources(Collection<Resource> usedResources) {
		ArrayList<Resource> changedResources = new ArrayList<Resource>();
		for (Resource resource : usedResources) {
			String filePath = resource.getURI().toFileString();
			IResource wsRes = FileManager.getResourceForLocation(filePath);
			if(wsRes != null && !wsRes.isSynchronized(IResource.DEPTH_ZERO)) {
				changedResources.add(resource);
			}

		}
		return changedResources;
	}	
	
	/**
	 * 
	 * @param resources Collection
	 * @return boolean
	 */
	public static boolean hasOutOfSynch(Collection<Resource> resources) {
		for (Resource resource : resources) {
			if(resource.getURI().isFile()) {
				String filePath = resource.getURI().toFileString();
				IResource wsRes = FileManager.getResourceForLocation(filePath);
				if(wsRes != null && !wsRes.isSynchronized(IResource.DEPTH_ZERO)) {
					return true;
				}
			}
		}	
		return false;
	}
	
	/**
	 * 
	 * @param resourcesToCheck Collection
	 * @param removedResources Collection
	 * @param changedResources Collection
	 */
	public static void checkOutOfSynchResources(Collection resourcesToCheck, 
			Collection removedResources, Collection changedResources)
	{
		for (Iterator iter = resourcesToCheck.iterator(); iter.hasNext();) {
			Resource resource = (Resource) iter.next();
			String filePath = resource.getURI().toFileString();
			IResource wsRes = FileManager.getResourceForLocation(filePath);
			if(wsRes == null) {
				removedResources.add(resource);
			}
			else if(!wsRes.isSynchronized(IResource.DEPTH_ZERO)) {
				changedResources.add(resource);
			}

		}
	}

	/**
	 * 
	 * @param lib MethodLibrary
	 * @param monitor IProgressMonitor
	 */
	public static void refreshResources(MethodLibrary lib, IProgressMonitor monitor) {
		if(lib != null) {
			IProject prj = MethodLibraryProject.findProject(lib);
			if(prj != null) {
				try {
					prj.refreshLocal(IResource.DEPTH_INFINITE, monitor);
				} catch (CoreException e) {
					LibraryPlugin.getDefault().getLogger().logError(e);
				}
			}
		}
	}
}