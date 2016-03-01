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
package org.eclipse.epf.library.persistence.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.persistence.PersistencePlugin;
import org.osgi.framework.Bundle;

/**
 * Synchronizes the document with external resource changes.
 * 
 * @author Phong Nguyen Le
 *
 * @since 1.2
 */
public class FileSynchronizer implements IResourceChangeListener {

	/**
	 * Bundle of all required information to allow files as underlying document resources.
	 */
	public static class FileInfo //extends StorageInfo 
	{
		/** The time stamp at which this provider changed the file. */
		public long fModificationStamp= IResource.NULL_STAMP;
		public boolean fCanBeSaved;
		public IFile fFile;

		/**
		 * Creates and returns a new file info.
		 *
		 * @param document the document
		 * @param model the annotation model
		 * @param fileSynchronizer the file synchronizer
		 */
		public FileInfo(IFile file) {
			fFile = file;
		}
	}

	/**
	 * A flag indicating whether this synchronizer is installed or not.
	 *
	 * 
	 */
	protected boolean fIsInstalled= false;
	
	/** Element information of all connected elements */
	private Map<Object, FileInfo> fFileInfoMap= new HashMap<Object, FileInfo>();


	/**
	 * Creates a new file synchronizer. Is not yet installed on a resource.
	 */
	public FileSynchronizer() {
		install();
	}

	public boolean accept(IFile file) {
		return false;
	}
	
	public boolean isInstalled() {
		return fIsInstalled;
	}
	
	public void updateModificationStamp(IFile file) {
		FileInfo info = getFileInfo(file);
		if(info != null) {
			info.fModificationStamp = computeModificationStamp(file);
		}
	}
	
	/**
	 * Returns the element info object for the given element.
	 *
	 * @param element the element
	 * @return the element info object, or <code>null</code> if none
	 */
	public FileInfo getFileInfo(Object element) {
		return (FileInfo) fFileInfoMap.get(element);
	}

	/**
	 * Installs the synchronizer on the input's file.
	 */
	public void install() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
		fIsInstalled= true;
	}

	/**
	 * Uninstalls the synchronizer from the input's file.
	 */
	public void uninstall() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		fIsInstalled= false;
	}

	/*
	 * @see IResourceChangeListener#resourceChanged(IResourceChangeEvent)
	 */
	public void resourceChanged(IResourceChangeEvent e) {
		IResourceDelta delta= e.getDelta();
		try {
			if (delta != null && fIsInstalled) {
				ResourceDeltaVisistor visitor = new ResourceDeltaVisistor();
				delta.accept(visitor);
				Runnable runnable = visitor.getRunnable();
				if(runnable != null) {
					update(runnable);
				}
			} 
		} catch (CoreException x) {
			handleCoreException(x, "Error handling resource change event."); //$NON-NLS-1$
		}
	}

	/**
	 * Defines the standard procedure to handle <code>CoreExceptions</code>. Exceptions
	 * are written to the plug-in log.
	 *
	 * @param exception the exception to be logged
	 * @param message the message to be logged
	 * 
	 */
	protected void handleCoreException(CoreException exception, String message) {

		Bundle bundle = Platform.getBundle(PersistencePlugin.getDefault().getId());
		ILog log= Platform.getLog(bundle);

		if (message != null)
			log.log(new Status(IStatus.ERROR, PersistencePlugin.getDefault().getId(), 0, message, exception));
		else
			log.log(exception.getStatus());
	}
	
	/**
	 * Computes the initial modification stamp for the given resource.
	 *
	 * @param resource the resource
	 * @return the modification stamp
	 */
	public static long computeModificationStamp(IResource resource) {
		if(resource == null) {
			System.err.println("FATAL ERROR: resource is null."); //$NON-NLS-1$
			return 0;
		}
		long modificationStamp= resource.getModificationStamp();

		IPath path= resource.getLocation();
		if (path == null)
			return modificationStamp;

		modificationStamp= path.toFile().lastModified();
		return modificationStamp;
	}
	
	private class ResourceDeltaVisistor implements IResourceDeltaVisitor {
		private Collection<IFile> changedFiles = new ArrayList<IFile>();
		private Map<IFile, IPath> movedFileToNewPathMap = new HashMap<IFile, IPath>();
		private Collection<IFile> deletedFiles = new ArrayList<IFile>();
		private Collection<IFile> addedFiles = new ArrayList<IFile>();

		/*
		 * @see IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			if (delta == null)
				return false;
			if (delta.getResource().getType() == IResource.FILE) {
				switch (delta.getKind()) {
				case IResourceDelta.CHANGED:
					FileInfo info = (FileInfo) getFileInfo(delta.getResource());
					if (info == null || info.fCanBeSaved)
						break;
					IFile file = (IFile) delta.getResource();
					boolean isSynchronized = computeModificationStamp(file) == info.fModificationStamp;
					if (((IResourceDelta.ENCODING & delta.getFlags()) != 0 && isSynchronized)
							|| ((IResourceDelta.CONTENT & delta.getFlags()) != 0 && !isSynchronized)) {
						changedFiles.add(file);
					}
					break;

				case IResourceDelta.REMOVED:
					if ((IResourceDelta.MOVED_TO & delta.getFlags()) != 0) {
						info = (FileInfo) getFileInfo(delta.getResource());
						if (info == null)
							break;
						final IPath path = delta.getMovedToPath();
						movedFileToNewPathMap.put((IFile) delta.getResource(),
								path);
					} else {
						info = (FileInfo) getFileInfo(delta.getResource());
						if (info != null && !info.fCanBeSaved) {
							deletedFiles.add((IFile) delta.getResource());
						}
					}
					break;
				case IResourceDelta.ADDED:
					file = (IFile) delta.getResource();
					if(accept(file)) {
						addedFiles.add(file);
					}

				}
			}
			return true;
		}
		
		public Runnable getRunnable() {
			// consider the file is newly added if it is accepted and not a moved file
			//
			if (!addedFiles.isEmpty() && !movedFileToNewPathMap.isEmpty()) {
				for (Iterator<IFile> iter = addedFiles.iterator(); iter
						.hasNext();) {
					IFile file = iter.next();
					if (movedFileToNewPathMap.containsValue(file)) {
						iter.remove();
					}
				}
			}
			if(changedFiles.isEmpty() && movedFileToNewPathMap.isEmpty() && deletedFiles.isEmpty() && addedFiles.isEmpty()) {
				return null;
			}
			
			return new Runnable() {

				public void run() {
					handleChanged(ResourceDeltaVisistor.this);
				}
				
			};
		}
	}

	/**
	 * Posts the update code "behind" the running operation.
	 *
	 * @param runnable the update code
	 */
	protected void update(Runnable runnable) {
		runnable.run();
	}
	
	public void monitor(IFile file) {
		FileInfo info= (FileInfo) fFileInfoMap.get(file);
		if (info == null) {
			info = new FileInfo(file);
			info.fModificationStamp = computeModificationStamp(file);
			fFileInfoMap.put(file, info);
		}
	}
	
	public void unmonitor(IFile file) {
		fFileInfoMap.remove(file);
	}
	
	public void unmonitorAll() {
		fFileInfoMap.clear();
	}
	
	protected void handleChanged(ResourceDeltaVisistor visitor) {
		if(!visitor.changedFiles.isEmpty()) {
			handleChangedFiles(visitor.changedFiles);
		}
		if(!visitor.movedFileToNewPathMap.isEmpty()) {
			handleMovedFiles(visitor.movedFileToNewPathMap);
		}
		if(!visitor.deletedFiles.isEmpty()) {
			handleDeletedFiles(visitor.deletedFiles);
		}
		if(!visitor.addedFiles.isEmpty()) {
			handleAddedFiles(visitor.addedFiles);
		}
	}

	protected Collection<IFile> handleDeletedFiles(Collection<IFile> deletedFiles) {
		return Collections.emptyList();
	}

	protected Collection<IFile> handleMovedFiles(Map<IFile, IPath> movedFileToNewPathMap) {
		return Collections.emptyList();
	}

	protected Collection<IFile> handleChangedFiles(Collection<IFile> changedFiles) {
		return Collections.emptyList();
	}
	
	protected Collection<IFile> handleAddedFiles(Collection<IFile> addedFiles) {
		return Collections.emptyList();
	}
}

