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
package org.eclipse.epf.persistence.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBuffer;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.common.utils.IMarkerAttributeContributer;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.MultiFileSaveUtil;
import org.eclipse.epf.persistence.PersistencePlugin;
import org.eclipse.epf.uma.ecore.IProxyResolutionListener;
import org.eclipse.epf.uma.ecore.ResolveException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.osgi.util.NLS;


/**
 * Background job that manage markers for unresolved proxies
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class UnresolvedProxyMarkerManager extends WorkspaceJob implements IProxyResolutionListener {

	private static final long DELAY = 200;
	
	public static final String MARKER_ID = PersistencePlugin.getDefault().getId() + ".unresolvedProxyMarker"; //$NON-NLS-1$
	public static final String PROXY_URI = "proxyURI"; //$NON-NLS-1$
	public static final String OWNER_GUID = "ownerGUID"; //$NON-NLS-1$
	
	private static class ValidObject {
		boolean valid;
		Object object;
		
		/**
		 * @param valid
		 * @param object
		 */
		public ValidObject(Object object) {
			super();
			this.object = object;
			valid = true;			
		}
				
	}
	
	private Map<URI, Set<ResolveException>> uriToExceptionsMap;
	private List<ResolveException> exceptions;
	private Map<String, ValidObject> resourceGUIDToMarkersMap;
	private Map<String, Collection<ValidObject>> elementGUIToMarkersMap;
	private ResourceSet resourceSet;
	private boolean enabled = true;
	private List<Resource> resourcesToValidateMarkers;
	private boolean autoScheduled = true;
	public boolean ignoreNewException = false;

	public UnresolvedProxyMarkerManager(ResourceSet resourceSet) {
		super(PersistenceResources.unresolvedProxyLoggerJob_name);		
		setPriority(Job.BUILD);
		this.resourceSet = resourceSet;
//		unresolvedResourceGUIDToMarkersMap = new HashMap();
		uriToExceptionsMap = new HashMap<URI, Set<ResolveException>>();
		exceptions = new ArrayList<ResolveException>(); 
		resourceGUIDToMarkersMap = new HashMap<String, ValidObject>();
		elementGUIToMarkersMap = new HashMap<String, Collection<ValidObject>>();
		resourcesToValidateMarkers = new UniqueEList<Resource>();
	}
	private IMarker findMarker(IFile file, String proxyURI, String ownerGUID, int start, int end) throws CoreException {
		IMarker[] markers = file.findMarkers(MARKER_ID, false, IResource.DEPTH_ZERO);
		for (int i = 0; i < markers.length; i++) {
			IMarker marker = markers[i];
			if(proxyURI.equals(marker.getAttribute(PROXY_URI))
					&& marker.getAttribute(IMarker.CHAR_START, -1) == start
					&& marker.getAttribute(IMarker.CHAR_END, -1) == end) 
			{
				return marker;
			}
		}
		return null;
	}
	
	private IMarker findMarker(Collection<IMarker> markers, int start, int end) throws CoreException {
		for (IMarker marker : markers) {
			if(marker.getAttribute(IMarker.CHAR_START, -1) == start
					&& marker.getAttribute(IMarker.CHAR_END, -1) == end) 
			{
				return marker;
			}
		}
		return null;
	}


	private boolean addException(ResolveException re) {
		synchronized(exceptions) {
			URI uri = ((InternalEObject)re.getProxy()).eProxyURI();
			Set<ResolveException> exceptions = uriToExceptionsMap.get(uri);		
			if(exceptions == null) {
				exceptions = new HashSet<ResolveException>();
				uriToExceptionsMap.put(uri, exceptions);
			}
			if(exceptions.add(re)) {
				this.exceptions.add(re);
				return true;
			}
			return false;
		}
	}
	
	/**
	 * Document based character sequence.
	 */
	private static class DocumentCharSequence implements CharSequence {

		/** Document */
		private IDocument fDocument;

		/**
		 * Initialize with the sequence of characters in the given
		 * document.
		 *
		 * @param document the document
		 */
		public DocumentCharSequence(IDocument document) {
			fDocument= document;
		}

		/*
		 * @see java.lang.CharSequence#length()
		 */
		public int length() {
			return fDocument.getLength();
		}

		/*
		 * @see java.lang.CharSequence#charAt(int)
		 */
		public char charAt(int index) {
			try {
				return fDocument.getChar(index);
			} catch (BadLocationException x) {
				throw new IndexOutOfBoundsException(x.getLocalizedMessage());
			}
		}

		/*
		 * @see java.lang.CharSequence#subSequence(int, int)
		 */
		public CharSequence subSequence(int start, int end) {
			try {
				return fDocument.get(start, end - start);
			} catch (BadLocationException x) {
				throw new IndexOutOfBoundsException(x.getLocalizedMessage());
			}
		}
	}
	
	private static class MarkersAndLocations {
		Collection<IMarker> markers;
		Collection<int[]> locations;				
	}
	
	private void updateMarker(IMarker[] markers, Resource resource, IProgressMonitor monitor) {
		URI containerURI = resource.getURI();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IPath path = new Path(containerURI.toFileString());
		IFile file = workspace.getRoot().getFileForLocation(path);
		if (file != null) {
			try {
				file.refreshLocal(IResource.DEPTH_ZERO, null);
				
				Map<String, MarkersAndLocations> proxyURIToMarkersAndLocationsMap = new HashMap<String, MarkersAndLocations>();
				for (int i = 0; i < markers.length; i++) {
					IMarker marker = markers[i];
					Object proxyURI = marker.getAttribute(PROXY_URI);
					if(proxyURI != null) {
						String proxyURIStr = proxyURI.toString();
						MarkersAndLocations markersAndLocations = proxyURIToMarkersAndLocationsMap.get(proxyURIStr);
						if(markersAndLocations == null) {
							markersAndLocations = new MarkersAndLocations();
							markersAndLocations.markers = new HashSet<IMarker>();
							markersAndLocations.locations = new HashSet<int[]>();
							proxyURIToMarkersAndLocationsMap.put(proxyURIStr, markersAndLocations);
						}
						markersAndLocations.markers.add(marker);
					}
				}
				
				// locate the text of unresolved URI in file
				//
				ITextFileBufferManager manager= FileBuffers.getTextFileBufferManager();
				try {
					manager.connect(path, LocationKind.LOCATION, monitor);
					ITextFileBuffer fileBuffer= manager.getTextFileBuffer(path, LocationKind.LOCATION);
					fileBuffer.requestSynchronizationContext();
					fileBuffer.getDocument();
					IDocument doc = fileBuffer.getDocument();
					if(doc != null) {
						for (Map.Entry<String, MarkersAndLocations> entry : proxyURIToMarkersAndLocationsMap.entrySet()) {
							String proxyURI = entry.getKey();
							Pattern pattern = PatternConstructor.createPattern(proxyURI, true, false);
							Matcher matcher = pattern.matcher(new DocumentCharSequence(doc));
							while(matcher.find()) {
								int start = matcher.start();
								int end = matcher.end();
								if(start != end) {
									entry.getValue().locations.add(new int[] { start, end });
								}
							}
						}
					}
					fileBuffer.releaseSynchronizationContext();
				}
				finally {
					manager.disconnect(path, LocationKind.LOCATION, monitor);
				}
				
				// make sure that resolver is still in the open library by checking whether its resource
				// still belongs to a resource set
				//
				if(resource != null && resource.getResourceSet() != null) {
					for (MarkersAndLocations markersAndLocations : proxyURIToMarkersAndLocationsMap.values()) {
						process_entry:
						for (Iterator<int[]> iterator = markersAndLocations.locations.iterator(); iterator.hasNext();) {
							if(markersAndLocations.markers.isEmpty()) {
								break process_entry;
							}
							int[] loc = iterator.next();
							int start = loc[0];
							int end = loc[1];
							IMarker marker = findMarker(markersAndLocations.markers, start, end);
							if(marker == null) {
								Iterator<IMarker> iter = markersAndLocations.markers.iterator();
								marker = iter.next();
								iter.remove();
								marker.setAttribute(IMarker.CHAR_START, start);	
								marker.setAttribute(IMarker.CHAR_END, end);
							}
							else {
								markersAndLocations.markers.remove(marker);
							}
						}
						if(!markersAndLocations.markers.isEmpty()) {
							// invalid markers, must be deleted
							//
							for (IMarker marker : markersAndLocations.markers) {
								if(marker.exists()) {
									try {
										marker.delete();
									}
									catch(CoreException e) {
										PersistencePlugin.getDefault().getLogger().logError(e);
									}
								}
							}
						}
					}
				}

			} catch (CoreException ex) {
				PersistencePlugin.getDefault().getLogger().logError(ex);
				if (MultiFileSaveUtil.DEBUG) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	private void addMarker(ResolveException re, Resource resource, URI proxyURI, String errMsg, String ownerGUID, IProgressMonitor monitor) {
		URI containerURI = resource.getURI();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IPath path = new Path(containerURI.toFileString());
		IFile file = workspace.getRoot().getFileForLocation(path);
		if (file != null) {
			String location = containerURI != null ? containerURI
					.toFileString() : StrUtil.EMPTY_STRING;					
			
			try {
				file.refreshLocal(IResource.DEPTH_ZERO, null);
				IMarker marker = findMarker(file, proxyURI.toString(), ownerGUID, 0, 0);
				if (marker != null) {
					return;
				}
				createMarker(re, proxyURI, errMsg, ownerGUID, file,
						location, proxyURI.toString(), 0, 0);
			} catch (CoreException e) {
				IStatus status = e.getStatus();
				int code;
				if(status instanceof IResourceStatus && 
						((code = ((IResourceStatus) status).getCode()) == IResourceStatus.MARKER_NOT_FOUND
								|| code == IResourceStatus.RESOURCE_NOT_FOUND)) {
					// do nothing
				} else {
					PersistencePlugin.getDefault().getLogger().logError(e);
					if (MultiFileSaveUtil.DEBUG) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private IMarker createMarker(ResolveException re, URI proxyURI,
			String errMsg, String ownerGUID, IFile file, String location,
			String proxyURIStr, int start, int end) throws CoreException {
		IMarker marker;
		marker = file.createMarker(MARKER_ID);
		marker.setAttribute(IMarker.SEVERITY,
				IMarker.SEVERITY_ERROR);
		marker.setAttribute(IMarker.MESSAGE, errMsg);
		marker.setAttribute(IMarker.LOCATION, location);
		marker.setAttribute(IMarker.TRANSIENT, true);
		marker.setAttribute(IMarker.CHAR_START, start);	
		marker.setAttribute(IMarker.CHAR_END, end);
		marker.setAttribute(PROXY_URI, proxyURIStr);
		marker.setAttribute(OWNER_GUID, ownerGUID);
		
		IMarkerAttributeContributer attAdder = ExtensionHelper.getMarkerAttributeContributer();
		if (attAdder != null) {
			attAdder.addAddtionalAttributes(marker, re);
		}

		// cache marker to it can be found easily and deleted
		//
		cacheMarker(marker, proxyURI);
		return marker;
	}
	
	private void addMarker(ResolveException re, IProgressMonitor monitor) {
		InternalEObject proxy = (InternalEObject) re.getProxy();
		URI containerURI = null;
		Resource resource = null;
		if(re.getResolver() != null) {
			resource = re.getResolver().eResource();
			
			// make sure that resolver is still in the open library by checking whether its resource
			// still belongs to a resource set
			//
//			System.out.println("UnresolvedProxyMarkerManager.addMarker(): resourceSet=" + resource.getResourceSet().hashCode());
			if(resource != null && resource.getResourceSet() != null) {				
				containerURI = resource.getURI();
			}
		}				
		if (containerURI != null) {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IPath path = new Path(containerURI.toFileString());
			IFile file = workspace.getRoot().getFileForLocation(path);
			if (file != null) {
				String errMsg;
				if (re.exception() != null && re.exception().getLocalizedMessage() != null && 
						re.exception().getLocalizedMessage().trim().length() > 0) {
					errMsg = re.exception().getLocalizedMessage();
				}
				else {
					errMsg = re.getMessage() == null ? NLS.bind(PersistenceResources.UnresolvedProxyMarkerManager_couldNotResolveProxy, proxy.eProxyURI()) : re
								.getMessage();
				}
				addMarker(re, resource, proxy.eProxyURI(), errMsg, MultiFileSaveUtil.getGuid(re.getResolver()), monitor);
			}
		}
	}

	/**
	 * @param marker
	 * @param uri proxy uri 
	 */
	private void cacheMarker(IMarker marker, URI uri) {
		synchronized(elementGUIToMarkersMap) {
			String resourceGUID = uri.authority();
			if(resourceGUID != null) {
				ValidObject vo = (ValidObject) resourceGUIDToMarkersMap.get(resourceGUID);
				if(vo == null) {
					vo = new ValidObject(new HashSet<IMarker>());
					resourceGUIDToMarkersMap.put(resourceGUID, vo);
				}
				Collection<IMarker> markers = (Collection<IMarker>) vo.object;
				markers.add(marker);
			}
			String elementGUID = uri.fragment();
			Collection<ValidObject> markers = elementGUIToMarkersMap.get(elementGUID);
			if(markers == null) {
				markers = new HashSet<ValidObject>();
				elementGUIToMarkersMap.put(elementGUID, markers);
			}
			markers.add(new ValidObject(marker));
		}
	}

	public void clearAll() {
		boolean oldEnabled = enabled;
		try {
			enabled = false;
			cancel();

			synchronized (exceptions) {
				uriToExceptionsMap.clear();
				exceptions.clear();
			}
			synchronized(elementGUIToMarkersMap) {
				if(!elementGUIToMarkersMap.isEmpty()) {
					for (Collection<ValidObject> markers : elementGUIToMarkersMap.values()) {
						for (ValidObject vo : markers) {
							IMarker marker = (IMarker) vo.object;
							try {
								if(marker.exists()) {
									marker.delete();
								}
							}
							catch(Exception e) {
								CommonPlugin.INSTANCE.log(e);
							}
						}
						markers.clear();
					}
					elementGUIToMarkersMap.clear();
					resourceGUIDToMarkersMap.clear();
				}	
			}
			synchronized(resourcesToValidateMarkers) {
				resourcesToValidateMarkers.clear();
			}
		}
		finally {
			enabled = oldEnabled;
//			System.out.println("UnresolvedProxyMarkerManager.clearAll(): end");
		}
	}

	public boolean hasUnresolvedProxy() {
		return !elementGUIToMarkersMap.isEmpty();
	}
	
	private ResolveException nextException() {
		synchronized(exceptions) {			
			// synchronized: in order to atomically obtain and clear requests
			if(exceptions.isEmpty()) {
				return null;
			}
			else {
				ResolveException e = (ResolveException) this.exceptions.remove(0);
				URI uri = ((InternalEObject)e.getProxy()).eProxyURI();
				Set<ResolveException> exceptions = uriToExceptionsMap.get(uri);
				if(exceptions != null) {
					exceptions.remove(e);
					if(exceptions.isEmpty()) {
						uriToExceptionsMap.remove(uri);
					}
				}
				return e;
			}
		}
	}
	
	private Resource nextResourceToValidateMarkers() {
		synchronized(resourcesToValidateMarkers) {
			if(resourcesToValidateMarkers.isEmpty()) {
				return null;
			}
			else {
				return (Resource) resourcesToValidateMarkers.remove(0);
			}
		}
	}
	
	private void yield() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			//
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.WorkspaceJob#runInWorkspace(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
//		System.out.println("UnresolvedProxyMarkerManager.runInWorkspace(): begin");
		monitor.beginTask("Process unsolved references", IProgressMonitor.UNKNOWN);
		try {
			if(!enabled) return Status.OK_STATUS;

			try {
				// add markers
				//
				monitor.subTask("Creating problem markers...");
				ResolveException re;
				while(!monitor.isCanceled() && (re = nextException()) != null) {
					try {
						addMarker(re, monitor);
					}
					catch(Exception e) {
						PersistencePlugin.getDefault().getLogger().logError(e);
					}	
					yield();
				}

				if(monitor.isCanceled()) {
					throw new OperationCanceledException();
				}

				// remove invalid markers
				//
				monitor.subTask("Removing invalid markers...");
				removeMarkers(monitor);
				yield();

				// validate resource markers
				//
				Resource resource;
				while(!monitor.isCanceled() && (resource = nextResourceToValidateMarkers()) != null) {
					monitor.subTask(NLS.bind("Validating ''{0}''...", FileManager.toFileString(resource.getURI())));
					doValidateMarkers(resource);
					yield();
				}
			}
			catch(Exception e) {
				if(e instanceof OperationCanceledException) {
					return Status.CANCEL_STATUS;
				}
				PersistencePlugin.getDefault().getLogger().logError(e);
			}

			return Status.OK_STATUS;
		}
		finally {
//			System.out.println("UnresolvedProxyMarkerManager.runInWorkspace(): end");
			monitor.done();
		}
	}
	
	private boolean removeFromResourceGUIDToMarkersMap(String proxyURI, IMarker marker) {
		URI uri = URI.createURI(proxyURI);
		String resourceGUID = uri.authority();
		if(resourceGUID != null) {
			ValidObject vo = (ValidObject) resourceGUIDToMarkersMap.get(resourceGUID);
			if(vo != null) {
				Collection markers = ((Collection)vo.object);
				boolean ret = markers.remove(marker);
				if(markers.isEmpty()) {
					resourceGUIDToMarkersMap.remove(resourceGUID);
				}
				return ret;
			}
		}
		return false;
	}
	
	private void removeFromElementGUIDToMarkersMap(String proxyURI, IMarker marker) {
		URI uri = URI.createURI(proxyURI);
		String elementGUID = uri.fragment();
		if(elementGUID != null) {
			Collection<ValidObject> validObjects = elementGUIToMarkersMap.get(elementGUID);
			if(validObjects != null) {
				for (Iterator<ValidObject> iterator1 = validObjects.iterator(); iterator1
				.hasNext();) {
					ValidObject vo1 = (ValidObject) iterator1.next();
					if(vo1.object == marker) {
						iterator1.remove();
					}
				}
				if(validObjects.isEmpty()) {
					elementGUIToMarkersMap.remove(elementGUID);
				}	
			}
		}
	}
	
	private void removeMarkers(IProgressMonitor monitor) {
		synchronized(elementGUIToMarkersMap) {
		for (Collection<ValidObject> markers : elementGUIToMarkersMap.values()) {
			for (Iterator<ValidObject> iterator = markers.iterator(); iterator.hasNext();) {
				ValidObject vo = (ValidObject) iterator.next();
				if(!vo.valid) {
					try {
						IMarker marker = (IMarker) vo.object;
						if(marker.exists()) {
							String proxyURI = (String) marker.getAttribute(PROXY_URI);

							marker.delete();

							// remove this marker from resourceGUIToMarkersMap
							//
							removeFromResourceGUIDToMarkersMap(proxyURI, marker);
						}
						iterator.remove();

					} catch (Exception e) {
						CommonPlugin.INSTANCE.log(e);
					}
				}
			}
		}
		for (Iterator<ValidObject> iter = resourceGUIDToMarkersMap.values().iterator(); iter.hasNext();) {
			ValidObject vo = (ValidObject) iter.next();
			if(!vo.valid) {
				vo.valid = true;
				Collection<IMarker> markers = (Collection<IMarker>) vo.object;
				for (Iterator<IMarker> iterator = markers.iterator(); iterator.hasNext();) {
					IMarker marker;
					try {
						marker = (IMarker) iterator.next();
						if(marker.exists()) {
							String proxyURIStr = (String) marker.getAttribute(PROXY_URI);
							URI proxyURI = URI.createURI(proxyURIStr);

							// proxy is in the same resource that is just loaded 
							// try to get the object with this proxy URI without loading new resource
							//
//							EObject o = resourceSet.getEObject(proxyURI, false);
							String elementGUID = proxyURI.fragment();							
							EObject o = (EObject) ((MultiFileResourceSetImpl)resourceSet).getGuidToMethodElementMap().get(elementGUID);
							if(o != null && !o.eIsProxy()) {
								marker.delete();
								iterator.remove();	

								// remove this marker from elementGUIToMarkersMap
								//
								removeFromElementGUIDToMarkersMap(proxyURIStr, marker);
							}
						}
						else {
							iterator.remove();
						}
					}
					catch(Exception e) {
						CommonPlugin.INSTANCE.log(e);
					}
				}
				if(markers.isEmpty()) {
					iter.remove();
				}
			}
		}
		}
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.ecore.IProxyResolutionListener#notifyException(java.lang.Exception)
	 */
	public void notifyException(Exception e) {
		if(!enabled) return;
		if (isIgnoreNewException()) {
			return;
		}
		
		if(e instanceof ResolveException) {
			ResolveException re = (ResolveException) e;
			
//			URI uri = ((InternalEObject)re.getProxy()).eProxyURI();
//			if("_2J4YoPTDEdmkpYARNN468A".equals(uri.fragment())) {
//				System.out.println();
//			}
			
			if(addException(re) && autoScheduled) {			
				start();
			}
		}
	}
	
	public void setAutoScheduled(boolean b) {
		autoScheduled = b;
	}
	
	public boolean isAutoScheduled() {
		return autoScheduled;
	}
	
	@Override
	public boolean shouldSchedule() {
		return !exceptions.isEmpty() || !elementGUIToMarkersMap.isEmpty() || !resourceGUIDToMarkersMap.isEmpty();
	}
	
	/**
	 * Schedules this job if exceptions are available to log
	 */
	public boolean start() {
		if(!enabled) return false;
		schedule(DELAY);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.ecore.IProxyResolutionListener#proxyResolved(java.lang.Object, java.lang.Object)
	 */
	public void proxyResolved(Object proxy, Object resolved) {
		if(!enabled) return;
		
		if(proxy instanceof InternalEObject) {
			URI uri = ((InternalEObject)proxy).eProxyURI();
			
//			if("_2J4YoPTDEdmkpYARNN468A".equals(uri.fragment())) {
//				System.out.println();
//			}
			
			// remove all exceptions for this URI
			//
			removeExceptions(uri);
			
			// invalidate all markers for this URI
			//
			if(invalidateMarkers(uri)) {
				start();
			}						
		}		
	}
	
	/**
	 * @param uri
	 */
	private boolean invalidateMarkers(URI uri) {
		synchronized(elementGUIToMarkersMap) {
			boolean ret = false;
			String resourceGUID = uri.authority();
			if(resourceGUID != null) {
				ValidObject vo = (ValidObject) resourceGUIDToMarkersMap.get(resourceGUID);
				if(vo != null) {
					vo.valid = false;
					ret = true;
				}
			}
			String elementGUID = uri.fragment();
			if(elementGUID != null) {
				Collection<ValidObject> markers = elementGUIToMarkersMap.get(elementGUID);
				if(markers != null && !markers.isEmpty()) {
					ret = true;
					for (ValidObject vo : markers) {
						vo.valid = false;
					}
				}
			}
			return ret;
		}
	}

	/**
	 * @param uri
	 */
	private void removeExceptions(URI uri) {
		synchronized (exceptions) {
			Set<ResolveException> exceptions = uriToExceptionsMap.get(uri);
			if(exceptions == null || exceptions.isEmpty()) return;
			this.exceptions.removeAll(exceptions);
			uriToExceptionsMap.remove(uri);
		}
	}

	/**
	 * Clears markers for unresolved proxies of the given resource
	 *  
	 * @param resource
	 */
	public void clearMarkers(Resource resource) {
		try {
			IResource file = FileManager.getResourceForLocation(resource.getURI().toFileString());
			if(file instanceof IFile) {
				IMarker[] markers = file.findMarkers(MARKER_ID, false, IResource.DEPTH_ZERO);
				for (int i = 0; i < markers.length; i++) {
					IMarker marker = markers[i];
					removeMarker(marker);
				}
			}
		}
		catch(Exception e) {
			PersistencePlugin.getDefault().getLogger().logError(e);
		}
	}
	
	public void removeMarker(IMarker marker) {
		String proxyURI = null;
		try {
			if(marker.exists()) {
				proxyURI = (String) marker.getAttribute(PROXY_URI);
				marker.delete();
			}
		} catch (Exception e) {
			PersistencePlugin.getDefault().getLogger().logError(e);
		}
		if(proxyURI != null) {
			synchronized(elementGUIToMarkersMap) {
				removeFromElementGUIDToMarkersMap(proxyURI, marker);
				removeFromResourceGUIDToMarkersMap(proxyURI, marker);
			}
		}		
	}

	private static class MarkerInfo {
		String proxyURI;
		String ownerGUID;
		String message;
		
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object obj) {
			if(obj instanceof MarkerInfo) {
				MarkerInfo info = (MarkerInfo) obj;
				if(proxyURI != null && ownerGUID != null && proxyURI.equals(info.proxyURI) && ownerGUID.equals(info.ownerGUID)) {
					return true;
				}
			}
			return super.equals(obj);
		}
		
		@Override
		public int hashCode() {
			int result = proxyURI.hashCode();
			result ^= ownerGUID.hashCode();
			result ^= message.hashCode();
			return result;
		}
	}
	
	/**
	 * Validates markers for unresolved proxies of the given resource
	 * 
	 * @param resource
	 */
	public void validateMarkers(Resource resource) {
		boolean newlyAdded = false;
		synchronized(resourcesToValidateMarkers) {
			newlyAdded = resourcesToValidateMarkers.add(resource);
		}
		if(newlyAdded) {
			start();
		}
	}

	public void validateAllMarkers() {
		boolean newlyAdded = false;
		boolean invalid = false;
		if(!elementGUIToMarkersMap.isEmpty()) {
			ArrayList<Collection<ValidObject>> validObjectCollections = null;
			synchronized (elementGUIToMarkersMap) {
				if(!elementGUIToMarkersMap.isEmpty()) {					
					validObjectCollections = new ArrayList<Collection<ValidObject>>();
					for (Collection<ValidObject> collection : elementGUIToMarkersMap.values()) {
						if(!collection.isEmpty()) {
							validObjectCollections.add(new ArrayList<ValidObject>(collection));
						}
					}
				}
			}
			if(validObjectCollections != null) {
				synchronized(resourcesToValidateMarkers) {
					for (Collection<ValidObject> markers : validObjectCollections) {
						for (ValidObject vo : markers) {
							IMarker marker = (IMarker) vo.object;
							try {
								if(marker.exists()) {
									String location = (String)marker.getAttribute(IMarker.LOCATION);
									URI uri = URI.createFileURI(location);
									Resource resource = resourceSet.getResource(uri, true);
									newlyAdded = resourcesToValidateMarkers.add(resource) || newlyAdded;
								} else {
									vo.valid = false;
									invalid = true;
								}
							} catch (CoreException e) {
								PersistencePlugin.getDefault().getLogger().logError(e);
							}
						}
					}
				}

			}
		}
		if(newlyAdded) {
			start();
		} else if(invalid) {
			removeMarkers(new NullProgressMonitor());
		}
	}	
	
	public void doValidateMarkers(Resource resource) {
		try {
			IResource file = FileManager.getResourceForLocation(resource.getURI().toFileString());
			if(file instanceof IFile && file.exists()) {
				IMarker[] markers = file.findMarkers(MARKER_ID, false, IResource.DEPTH_ZERO);
				HashSet<IMarker> markersToUpdate = new HashSet<IMarker>();
				for (int i = 0; i < markers.length; i++) {
					IMarker marker = markers[i];
					String proxyURI = (String) marker.getAttribute(PROXY_URI);
					if(proxyURI != null) {
						URI uri = URI.createURI(proxyURI);
						// try to get the object with this proxy URI without loading new resource
						//
						String elementGUID = uri.fragment();							
						EObject o = (EObject) ((MultiFileResourceSetImpl)resourceSet).getGuidToMethodElementMap().get(elementGUID);
						if(o == null || o.eIsProxy()) {
							// marker still can exist, need to update it
							//
							markersToUpdate.add(marker);
						}
						else {
							// object is resolved, must delete marker
							//
							if(marker.exists()) {
								try {
									marker.delete();
								}
								catch(Exception e) {
									PersistencePlugin.getDefault().getLogger().logError(e);									
								}
							}
							synchronized(elementGUIToMarkersMap) {
								removeFromElementGUIDToMarkersMap(proxyURI, marker);
								removeFromResourceGUIDToMarkersMap(proxyURI, marker);
							}
						}
					}
				}
				if(!markersToUpdate.isEmpty()) {
					IMarker[] markerArray = new IMarker[markersToUpdate.size()];
					markersToUpdate.toArray(markerArray);
					updateMarker(markerArray, resource, new NullProgressMonitor());
				}
			}
		}
		catch(Exception e) {
			PersistencePlugin.getDefault().getLogger().logError(e);		
		}
	}

	public boolean isIgnoreNewException() {
		return ignoreNewException;
	}

	public void setIgnoreNewException(boolean ignoreNewException) {
		this.ignoreNewException = ignoreNewException;
	}

}
