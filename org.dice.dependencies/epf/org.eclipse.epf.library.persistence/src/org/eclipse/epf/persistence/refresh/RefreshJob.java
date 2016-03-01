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
package org.eclipse.epf.persistence.refresh;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.persistence.MultiFileXMIResourceImpl;
import org.eclipse.epf.persistence.PersistencePlugin;
import org.eclipse.epf.persistence.util.LibrarySchedulingRule;
import org.eclipse.epf.persistence.util.PersistenceResources;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;

/**
 * Background job that keeps notifying refresh handlers about changes in
 * resources
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class RefreshJob extends WorkspaceJob implements IResourceChangeListener {

	private static final long UPDATE_DELAY = 200;

	private static final boolean DEBUG = PersistencePlugin.getDefault().isDebugging();

	private static final String DEBUG_PREFIX = "EPF Auto-refresh:"; //$NON-NLS-1$

	private ResourceSet resourceSet;

	private Collection<Resource> addedResources = new UniqueEList<Resource>();

	private Collection<Resource> changedResources = new UniqueEList<Resource>();

	private Collection<Resource> removedResources = new UniqueEList<Resource>();
	
	private Collection<Resource> movedResources = new UniqueEList<Resource>();

	private UniqueEList<Resource> savedResources = new UniqueEList<Resource>();

	private Collection<Resource> loadedBeforeRefreshResources = new ArrayList<Resource>();

	private IRefreshHandler refreshHandler;

	private boolean enabled = true;

	private Collection<IResource> addedWorkspaceResources = new UniqueEList<IResource>();

	private RefreshJob() {
		super(PersistenceResources.resourceAutoRefreshJob_name);
	}

	public void setEnabled(boolean b) {
		enabled = b;
	}

	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param resourceSet
	 *            The resourceSet to set.
	 */
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
	
	public ResourceSet getResourceSet() {
		return resourceSet;
	}

	public void setRefreshHandler(IRefreshHandler handler) {
		refreshHandler = handler;
	}

	/**
	 * Gets existing resources that reappear in workspaces
	 * 
	 * @return the addedResources
	 */
	public Collection<Resource> getAddedResources() {
		removeResources(addedResources, savedResources);
		return addedResources;
	}

	public Collection<IResource> getAddedWorkspaceResources() {
		return addedWorkspaceResources;
	}

	/**
	 * @return Returns the changedResources.
	 */
	public Collection<Resource> getChangedResources() {
		removeResources(changedResources, savedResources);
		removeResources(changedResources, loadedBeforeRefreshResources);
		return changedResources;
	}

	/**
	 * Removes <code>resourcesToRemove</code> from <code>resources</code> if
	 * the resources are synchronized with their storage.
	 * 
	 * @param resourcesToRemove
	 */
	private static void removeResources(Collection<Resource> resources,
			Collection<Resource> resourcesToRemove) {
		synchronized (resourcesToRemove) {
			if (!resourcesToRemove.isEmpty()) {
				for (Iterator<Resource> iter = resourcesToRemove.iterator(); iter
						.hasNext();) {
					Object resource = iter.next();
					boolean canRemove = true;
					if (resource instanceof MultiFileXMIResourceImpl) {
						MultiFileXMIResourceImpl mfResource = ((MultiFileXMIResourceImpl) resource);
						canRemove = mfResource.isSynchronized();
					}
					if (canRemove) {
						if (resources.remove(resource)) {
							iter.remove();
						}
					}
				}
			}
		}
	}

	/**
	 * @return Returns the removedResources.
	 */
	public Collection<Resource> getRemovedResources() {
		return removedResources;
	}
	
	/**                                    
	 * @return Returns the movedResources. 
	 */                                    
	public Collection<Resource> getMovedResources() {
		return movedResources;             
	}                                      

	public void resourceSaved(Resource resource) {
		if(resource.getResourceSet() == resourceSet) {
			synchronized (savedResources) {
				savedResources.add(resource);
			}
		}
	}

	/**
	 * @return the loadedBeforeRefreshResources
	 */
	public Collection<Resource> getReloadedBeforeRefreshResources() {
		return loadedBeforeRefreshResources;
	}

	public void reset() {
		changedResources.clear();
		removedResources.clear();
		movedResources.clear();
		savedResources.clear();
		loadedBeforeRefreshResources.clear();
		addedResources.clear();
		addedWorkspaceResources.clear();
	}
	
	private MethodLibrary getMethodLibrary() {
		if(resourceSet instanceof MultiFileResourceSetImpl) {
			return ((MultiFileResourceSetImpl)resourceSet).getMethodLibrary();
		}
		return null;
	}

	private void scheduleRefresh() {	
		if (isSuspendRefresh()) {
			return;
		}
		if (getState() == Job.NONE) {
			MethodLibrary lib = getMethodLibrary();
			if(lib != null) {
				setRule(new LibrarySchedulingRule(lib));
				schedule(UPDATE_DELAY);
			}
		}
	}	

	/*
	 * (non-Javadoc)
	 * 
	 * @see WorkspaceJob#runInWorkspace
	 */
	public IStatus runInWorkspace(IProgressMonitor monitor) {
		if (refreshHandler == null)
			return Status.OK_STATUS;

		// wait for all workspace refresh jobs to finish first
		//
		try {
			getJobManager().join(ResourcesPlugin.FAMILY_AUTO_REFRESH, new NullProgressMonitor());
		} catch (OperationCanceledException e1) {
		} catch (InterruptedException e1) {
		}
		
		long start = System.currentTimeMillis();
		Throwable error = null;
		try {
			if (DEBUG)
				System.out.println(DEBUG_PREFIX + " starting refresh job"); //$NON-NLS-1$
			monitor.beginTask("", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
			if (monitor.isCanceled())
				throw new OperationCanceledException();
			try {
				refreshHandler.refresh(monitor);
			} catch (Throwable e) {
				error = e;
			}
		} finally {
			monitor.done();
			if (DEBUG)
				System.out
						.println(DEBUG_PREFIX
								+ " finished refresh job in: " + (System.currentTimeMillis() - start) + "ms"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (error != null)
			return new Status(IStatus.ERROR, FileManager.PLUGIN_ID, 0,
					"Refresh error", error); //$NON-NLS-1$
		return Status.OK_STATUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.Job#shouldRun()
	 */
	public synchronized boolean shouldRun() {
		return shouldRefresh();
	}

	private boolean shouldRefresh() {
		return !removedResources.isEmpty() || !changedResources.isEmpty()
				|| !addedResources.isEmpty() || !movedResources.isEmpty()
				|| !loadedBeforeRefreshResources.isEmpty()
				|| !addedWorkspaceResources.isEmpty();
	}

	/**
	 * Starts the refresh job
	 */
	public void start() {
		if (DEBUG) {
			System.out.println("RefreshJob.start()"); //$NON-NLS-1$
		}
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	/**
	 * Stops the refresh job
	 */
	public void stop() {
		if (DEBUG) {
			System.out.println("RefreshJob.stop()"); //$NON-NLS-1$
		}
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		cancel();
	}

	public Resource getResource(IResource wsRes) {
		if(resourceSet instanceof MultiFileResourceSetImpl) {
			return ((MultiFileResourceSetImpl)resourceSet).getResource(wsRes);
		}
		IPath path = wsRes.getLocation();
		return path != null ? PersistenceUtil.getResource(path, resourceSet) : null;
	}

	/**
	 * Checks if the given resource can be accepted as a new resource of
	 * resource set of this refresh job that needs to be loaded.
	 * 
	 * @param resource
	 * @return
	 */
	private boolean accept(IResource resource) {
		if (resourceSet instanceof MultiFileResourceSetImpl) {
			return ((MultiFileResourceSetImpl) resourceSet)
					.isNewResourceToLoad(resource);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	public void resourceChanged(IResourceChangeEvent event) {
		if (!isEnabled() || resourceSet == null)
			return;
		IResourceDelta delta = event.getDelta();
		if (delta == null)
			return;		
		try {
			class ResourceDeltaVisitor implements IResourceDeltaVisitor {
				private Collection<Resource> changedResources = new ArrayList<Resource>();

				private Collection<Resource> removedResources = new ArrayList<Resource>();
				
				private Collection<Resource> movedResources = new ArrayList<Resource>();

				private Collection<Resource> addedResources = new ArrayList<Resource>();

				private ArrayList<IResource> addedWorkspaceResources = new ArrayList<IResource>();
				
				public boolean visit(IResourceDelta delta) throws CoreException {
					Resource resource = null;
//					System.out.println("type=" + delta.getResource().getType());
//					System.out.println("kind=" + delta.getKind());
//					System.out.println(delta.getFlags() != IResourceDelta.MARKERS);
//					if(delta.getResource().getFullPath().toString().endsWith("plugin.xmi")) {
//						System.out.println(delta.getResource().getFullPath().toString());
//					}
					if (delta.getResource().getType() == IResource.FILE) {
						switch (delta.getKind()) {
						case IResourceDelta.ADDED:
							// handle added resource
							//
							try {
								resource = getResource(delta.getResource());
							} catch (Exception e) {
								if (MultiResourceEObject.epfDebug(1)) {
									CommonPlugin.INSTANCE.log(e);
								}
							}
							if (resource != null) {
								if (!resource.isLoaded()) {
									// the resource was created but not
									// loaded b/c the workspace resource
									// was not added to the workspace at the
									// time of loading
									//
									MethodElement me = PersistenceUtil
									.getMethodElement(resource);
									if (!(me instanceof ContentDescription)) {
										// no auto-reload for content
										// description
										// MethodElementEditor will detect
										// the change and load it
										// when activated.
										//
										addedResources.add(resource);
									}
								}
							} else if (accept(delta.getResource())) {
								addedWorkspaceResources.add(delta
										.getResource());
							}
							
							// handle file move
							//
							if ((IResourceDelta.MOVED_FROM & delta.getFlags()) != 0) {
//								resource = getResource(delta.getResource());          
//								if (resource != null) {                           
//									movedResources.add(resource);                 
//								}                                                 
								//
								if (DEBUG) {
									IPath movedFromPath = delta.getResource()
											.getLocation();
									IPath movedToPath = delta.getMovedToPath();
									System.out
											.println("Resource moved from '" + movedFromPath + "' to '" + movedToPath + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
								}

							}
							break;
						case IResourceDelta.REMOVED:
							resource = getResource(delta.getResource());
							if (resource != null) {
								removedResources.add(resource);
							}
							break;
						case IResourceDelta.CHANGED:
							boolean encodingChanged = ((IResourceDelta.ENCODING & delta
									.getFlags()) != 0);
							boolean contentChanged = ((IResourceDelta.CONTENT & delta
									.getFlags()) != 0);
							if (encodingChanged || contentChanged) {
								resource = getResource(delta.getResource());
								if (resource != null
										&& MultiFileSaveUtil
										.checkSynchronized(resource) != 1) {
									changedResources.add(resource);
								}
							}
							break;
						}
						
						notiFyfileChangeListeners(delta);
					}
					
					return true;
				}

			};                                             

			ResourceDeltaVisitor visitor = new ResourceDeltaVisitor();
			delta.accept(visitor);

			removedResources.addAll(visitor.removedResources);
			movedResources.addAll(visitor.movedResources);
			changedResources.addAll(visitor.changedResources);
			addedResources.addAll(visitor.addedResources);
			addedWorkspaceResources.addAll(visitor.addedWorkspaceResources);

			if (shouldRefresh()) {
				scheduleRefresh();
			}
		} catch (CoreException e) {
			CommonPlugin.INSTANCE.log(e);
		}
	}

	/**
	 * Resolves the proxy and its containers
	 * 
	 * @param proxy
	 * @return
	 */
	public EObject resolve(EObject proxy) {
		return PersistenceUtil.resolve(proxy, resourceSet);
	}

	public static RefreshJob getInstance() {
		return instance;
	}

	private static RefreshJob instance = new RefreshJob();
	
	private boolean suspendRefresh = false;
	public boolean isSuspendRefresh() {
		return suspendRefresh;
	}

	public void setSuspendRefresh(boolean suspendRefresh) {
		this.suspendRefresh = suspendRefresh;
	}
	
	public void resumeRefresh() {
		if (shouldRefresh()) {
			scheduleRefresh();
		}
	}
	
	public static class FileChangeListener {
		//Make sure that notifyChange method is a very quick and short execution
		//Otherwise, use some other mechanism
		public void notifyChange(IResourceDelta delta) {			
		}
	}
	
	private List<FileChangeListener> fileChangeListeners = new ArrayList<FileChangeListener>();
	
	public void addFileChangeListener(FileChangeListener listener) {
		fileChangeListeners.add(listener);
	}
	
	public void removeFileChangeListener(FileChangeListener listener) {
		fileChangeListeners.remove(listener);
	}
	
	private void notiFyfileChangeListeners(IResourceDelta delta) {
		for (FileChangeListener lis : fileChangeListeners) {
			lis.notifyChange(delta);
		}		
	}
	
}