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
package org.eclipse.epf.library.project;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.eclipse.core.internal.resources.Workspace;
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
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.uma.MethodLibrary;


/**
 * Helper class for managing the Method Library Projects in a workspace.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * 
 * @since 1.0
 */
public class MethodLibraryProject {

	/**
	 * Creates a new method library project in the current workspace.
	 * 
	 * @param path
	 *            An absolute path to a method library.
	 * @param projectName
	 *            A name for the new method library project.
	 * @param monitor
	 *            A progress monitor.
	 * @return A method library project.
	 * @throws CoreException
	 *             if an error occurs while performing the operation.
	 */
	public static IProject createProject(String path, String projectName,
			IProgressMonitor monitor) throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		
		// find the project by path
		IProject project = findProject(path);		
		if (project != null ) {
			project.delete(IProject.FORCE
					| IProject.NEVER_DELETE_PROJECT_CONTENT, monitor);
		}
		
		if ( projectName == null ) {
			File libraryPath = new File(path);
			projectName = libraryPath.getName();
			if(!Workspace.caseSensitive) {
				// this is a non-case-sensitive file system
				// find existing method library project with different case variant
				//
				IProject[] projects = workspace.getRoot().getProjects();
				for (IProject prj : projects) {
					if(isMethodLibraryProject(prj) && prj.getName().equalsIgnoreCase(projectName)) {
						project = prj;
						break;
					}
				}
			}
		}
		if(project == null) {
			project = workspace.getRoot().getProject(projectName);
		}
		
		// if a project of the same name also exists, delete it
		// this is needed to take care of the following scenario:
		// a project is opened but not closed for some reason
		// now create a new library with the same name (same library folder)
		// we need to delete the previous one
		if (project.exists()) {
			project.delete(IProject.FORCE
					| IProject.NEVER_DELETE_PROJECT_CONTENT, monitor);
		}		

		IProjectDescription description = workspace
				.newProjectDescription(projectName);
		description.setNatureIds(MethodLibraryProjectNature.NATURE_IDS);
		IPath projectPath = new Path(path);
		if (!Platform.getLocation().isPrefixOf(projectPath)) {
			description.setLocation(projectPath);
		}
		project.create(description, monitor);
		return project;
	}

	/**
	 * Creates a new method library project in the current workspace.
	 * 
	 * @param path
	 *            An absolute path to a method library.
	 * @param monitor
	 *            A progress monitor.
	 * @return A <code>IProject</code> or <ocde>null</code> if the method
	 *         library project cannot be created.
	 * @throws CoreException
	 *             if an error occurs while performing the operation.
	 */
	public static IProject createProject(String path, IProgressMonitor monitor)
			throws CoreException {
//		IProject project = findProject(path);
//		if (project != null) {
//			project.delete(IProject.FORCE
//					| IProject.NEVER_DELETE_PROJECT_CONTENT, monitor);
//		}
//		File libraryPath = new File(path);
//		String projectPath = libraryPath.getAbsolutePath();
//		String projectName = libraryPath.getName();
//		return createProject(projectPath, projectName, monitor);
		
		return createProject(path, null, monitor);
	}

	/**
	 * Verifies that a workspace project is a method library project.
	 * 
	 * @param project
	 *            A workspace project.
	 * @return <code>true</code> if the given workspace project is a method
	 *         library project, <code>false</clode> otherwise.
	 */
	public static boolean isMethodLibraryProject(IProject project) {
		if (project != null) {
			boolean closeProjectOnExit = false;
			try {
				if (!project.isOpen()) {
					project.open(IResource.BACKGROUND_REFRESH,
							new NullProgressMonitor());
					closeProjectOnExit = true;
				}
				IProjectDescription description = project.getDescription();
				if (description != null) {
					String[] natureIds = description.getNatureIds();
					if (Arrays.asList(natureIds).contains(
							MethodLibraryProjectNature.NATURE_ID)) {
						return true;
					}
				}
			} catch (CoreException e) {
			} finally {
				if (closeProjectOnExit) {
					try {
						project.close(new NullProgressMonitor());
					} catch (CoreException e) {
					}
				}
			}

		}
		return false;
	}

	/**
	 * Locates a method library project in the current workspace.
	 * 
	 * @param path
	 *            An absolute path to a method library.
	 * @return A <code>IProject</code> or <ocde>null</code> if the method
	 *         library project cannot be found.
	 */
	public static IProject findProject(String path) {
		if (path == null) {
			return null;
		}
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject[] projects = workspace.getRoot().getProjects();
		try {
			for (int i = 0; i < projects.length; i++) {
				IProject project = projects[i];
				if (new File(path).compareTo(project.getLocation().toFile()) == 0
						&& isMethodLibraryProject(project)) {
					return project;
				}
				if (new File(path).compareTo(project.getLocation().toFile()) == 0) {
					return project;
				}
			}
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}
		return null;
	}

	/**
	 * Locates a method library project in the current workspace.
	 * 
	 * @param library
	 *            A method library.
	 * @return A <code>IProject</code> or <ocde>null</code> if the method
	 *         library project cannot be found.
	 */
	public static IProject findProject(MethodLibrary library) {
		return findProject(getLibraryPath(library));
	}

	/**
	 * Opens an existing method library project in the current workspace.
	 * <p>
	 * If the method library project cannot be located, create a new one.
	 * 
	 * @param path
	 *            An absolute path to a method library.
	 * @param monitor
	 *            A progress monitor.
	 * @throws IOException
	 *             if an I/O error occurs while performing the operation.
	 * @return A <code>IProject</code>.
	 * @throws CoreException
	 *             if an error occurs while performing the operation.
	 * @throws IOException 
	 */
	public static IProject openProject(String path, IProgressMonitor monitor) 
			throws CoreException, IOException {
		return openProject(path, null,  monitor);
	}

	/**
	 * Opens an existing method library project in the current workspace.
	 * <p>
	 * If the method library project cannot be located, create a new one.
	 * 
	 * @param path
	 *            An absolute path to a method library.
	 * @param monitor
	 *            A progress monitor.
	 * @throws IOException
	 *             if an I/O error occurs while performing the operation.
	 * @return A <code>IProject</code>.
	 * @throws CoreException
	 *             if an error occurs while performing the operation.
	 * @throws IOException 
	 */
	public static IProject openProject(String path, String projectName, IProgressMonitor monitor)
			throws CoreException, IOException {
		IPath projectPath = new Path(path + File.separator
				+ IProjectDescription.DESCRIPTION_FILE_NAME);

		IProjectDescription description;
		try {
			description = ResourcesPlugin.getWorkspace()
					.loadProjectDescription(projectPath);
		} catch (CoreException exception) {
			description = null;
		}

		IProject project = null;

		if (description == null) {
			// .project file is missing, create a new method library
			// project.
			project = MethodLibraryProject.createProject(path, projectName, monitor);
		} else {
			// .project file exists, retrieve the project from the
			// workspace.
			if ( projectName != null ) {
				description.setName(projectName);
			}
			
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(
					description.getName());
			if (!project.exists()) {
				// The project does not exist in the workspace. Create a new
				// method library project using the project name from the
				// .project file.
				project = MethodLibraryProject.createProject(path, project
						.getName(), monitor);
			} else {
				// The project does exist in the workspace.
				// Verify that the location matches. If not, create a new method
				// library project.
				File prjLoc = project.getLocation().toFile();
				File libLoc = new File(path);
				if (libLoc.compareTo(prjLoc) != 0) {
					project = MethodLibraryProject.createProject(path, monitor);
				} else if(!libLoc.getCanonicalPath().equals(prjLoc.getAbsolutePath())) {
					// letter case change in library path from the last time it was opened
					// the project need to be recreate to clear cache of path names
					//
					project.delete(IProject.FORCE
							| IProject.NEVER_DELETE_PROJECT_CONTENT, monitor);
					project = MethodLibraryProject.createProject(path, monitor);
				}
			}
		}

		if (!project.isOpen()) {
			project.open(IResource.BACKGROUND_REFRESH, monitor);
		}

		if (description == null) {
			description = project.getDescription();
		}

		// Make sure the project has the MethodLibraryProjectNature.
		String[] natureIds = description.getNatureIds();
		if (!Arrays.asList(natureIds).contains(
				MethodLibraryProjectNature.NATURE_ID)) {
			description.setNatureIds(MethodLibraryProjectNature.NATURE_IDS);
			project.setDescription(description, IProject.FORCE
					| IProject.KEEP_HISTORY, monitor);
		}

		return project;
	}

	/**
	 * Closes a method library project in the workspace.
	 * 
	 * @param path
	 *            An absolute path to a method library.
	 * @param monitor
	 *            A progress monitor.
	 * @throws CoreException
	 *             if an error occurs while performing the operation.
	 */
	public static void closeProject(String path, IProgressMonitor monitor)
			throws CoreException {
		IProject project = findProject(path);
		// check if workspace is modifiable
		if (project != null && !project.getWorkspace().isTreeLocked() && project.isOpen()) {
			project.close(monitor);
		}
	}

	/**
	 * Closes a method library project in the workspace.
	 * 
	 * @param library
	 *            A method library.
	 * @param monitor
	 *            A progress monitor.
	 * @throws CoreException
	 *             if an error occurs while performing the operation.
	 */
	public static void closeProject(MethodLibrary library,
			IProgressMonitor monitor) throws CoreException {
		closeProject(getLibraryPath(library), monitor);
	}

	/**
	 * Deletes a method library project from the current workspace.
	 * 
	 * @param path
	 *            An absolute path to a method library.
	 * @param monitor
	 *            A progress monitor.
	 * @return <code>true</code> if the method library project is successfully
	 *         deleted, <code>false</code> otherwise.
	 * @throws CoreException
	 *             if an error occurs while performing the operation.
	 */
	public static boolean deleteProject(String path, IProgressMonitor monitor) {
		IProject project = findProject(path);
		if (project != null) {
			try {
				if (monitor == null) {
					monitor = new NullProgressMonitor();
				}
				project.delete(IProject.FORCE
						| IProject.NEVER_DELETE_PROJECT_CONTENT, monitor);
			} catch (CoreException e) {
				LibraryPlugin.getDefault().getLogger().logError(e);
				return false;
			}
		}
		return true;
	}

	/**
	 * Deletes a method library project from the current workspace.
	 * 
	 * @param library
	 *            A method library.
	 * @param monitor
	 *            A progress monitor.
	 * @return <code>true</code> if the method library project is successfully
	 *         deleted, <code>false</code> otherwise.
	 */
	public static boolean deleteProject(MethodLibrary library,
			IProgressMonitor monitor) {
		return deleteProject(getLibraryPath(library), monitor);
	}

	/**
	 * Returns the absolute path to a method library.
	 * 
	 * @param library
	 * @return An absolute path to a method library.
	 */
	private static String getLibraryPath(MethodLibrary library) {
		return new File(library.eResource().getURI().toFileString())
				.getParent();
	}

}
