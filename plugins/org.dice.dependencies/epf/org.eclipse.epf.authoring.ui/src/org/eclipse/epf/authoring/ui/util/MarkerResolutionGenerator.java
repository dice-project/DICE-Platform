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
package org.eclipse.epf.authoring.ui.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.persistence.MultiFileResourceSetImpl;
import org.eclipse.epf.persistence.refresh.RefreshJob;
import org.eclipse.epf.persistence.util.PersistenceUtil;
import org.eclipse.epf.persistence.util.UnresolvedProxyMarkerManager;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;
import org.eclipse.ui.views.markers.WorkbenchMarkerResolution;

/**
 * Create resolutions for the given marker
 * 
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class MarkerResolutionGenerator implements IMarkerResolutionGenerator {
	private static final IMarkerResolution[] EMPTY_RESOLUTIONS = new IMarkerResolution[0];
	
	private static class ShowOwnerInLibraryView implements IMarkerResolution {

		private Object owner;

		/**
		 * @param owner
		 */
		private ShowOwnerInLibraryView(Object owner) {
			super();
			this.owner = owner;
		}

		/**
		 * @see org.eclipse.ui.IMarkerResolution#getLabel()
		 */
		public String getLabel() {
			return AuthoringUIResources.MarkerResolutionGenerator_showOwnerInLibraryView;
		}

		/**
		 * @see org.eclipse.ui.IMarkerResolution#run(org.eclipse.core.resources.IMarker)
		 */
		public void run(IMarker marker) {
			LibraryView.getView().setSelectionToViewer(owner);
		}
		
	}
	
	private static class MarkerResolution extends WorkbenchMarkerResolution {
		private IMarker currentMarker;
		private ReferenceInfo referenceInfo;

		public MarkerResolution(IMarker currentMarker, ReferenceInfo referenceInfo) {
			this.currentMarker = currentMarker;
			this.referenceInfo = referenceInfo;
		}
		
		/**
		 * @see org.eclipse.ui.IMarkerResolution#getLabel()
		 */
		public String getLabel() {
			return AuthoringUIResources.MarkerResolutionGenerator_removeUnresolvedReference;
		}

		/**
		 * @see org.eclipse.ui.IMarkerResolution#run(org.eclipse.core.resources.IMarker)
		 */
		public void run(IMarker marker) {
			ReferenceInfo refInfo = currentMarker.equals(marker) ? referenceInfo : getReferenceInfo(marker);
			if(refInfo == null) {
				return;
			}
			UnresolvedProxyMarkerManager mgr = null;
			Resource resource = refInfo.owner.eResource();
			ResourceSet resourceSet = resource.getResourceSet();
			if(resourceSet instanceof MultiFileResourceSetImpl) {
				mgr = ((MultiFileResourceSetImpl)resourceSet).getMarkerMananger();
				mgr.setIgnoreNewException(true);
			}

			try {
			if(refInfo.reference.isMany()) {
				((List<?>)refInfo.owner.eGet(refInfo.reference)).remove(refInfo.index);
			}
			else {
				refInfo.owner.eSet(refInfo.reference, null);
			}
			boolean validate = false;
//			Resource resource = refInfo.owner.eResource();
			ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil
					.getPersisterFor(resource).getFailSafePersister();
			try {
				persister.save(resource);
				persister.commit();
				validate = true;
			}
			catch(Exception e) {
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
				persister.rollback();
			}
			if(validate) { 				
//				ResourceSet resourceSet = resource.getResourceSet();
//				if(resourceSet instanceof MultiFileResourceSetImpl) {
//					((MultiFileResourceSetImpl)resourceSet).getMarkerMananger().doValidateMarkers(resource);
//				}
				if (mgr != null) {
					mgr.removeMarker(marker);					
				}
			}
			} finally {
				if (mgr != null) {
					mgr.setIgnoreNewException(false);
				}
			}
		}

		@Override
		public IMarker[] findOtherMarkers(IMarker[] markers) {
			ArrayList<IMarker> similarMarkerList = new ArrayList<IMarker>();
			for (int i = 0; i < markers.length; i++) {
				IMarker marker = markers[i];
				if(!currentMarker.equals(marker)) {
					try {
						if(UnresolvedProxyMarkerManager.MARKER_ID.equals(marker.getType())) {
							similarMarkerList.add(marker);
						}
					} catch (CoreException e) {
						//
					}
				}
			}
			IMarker[] similarMarkers = new IMarker[similarMarkerList.size()];
			similarMarkerList.toArray(similarMarkers);
			return similarMarkers;
		}

		public String getDescription() {
			// TODO provide detailed description
			return null;
		}

		public Image getImage() {
			// TODO provide image
			return null;
		}
		
	}
	
	private static class ReferenceInfo {
		private EObject owner;
		private EReference reference;
		private int index;
		
		public ReferenceInfo(EObject owner, EReference reference, int index) {
			super();
			this.owner = owner;
			this.reference = reference;
			this.index = index;
		}
				
	}
	
	private static ReferenceInfo getReferenceInfo(IMarker marker) {
		try {
			String proxyURI = (String) marker.getAttribute(UnresolvedProxyMarkerManager.PROXY_URI);
			String ownerGUID = (String) marker.getAttribute(UnresolvedProxyMarkerManager.OWNER_GUID);
			if(proxyURI != null && ownerGUID != null) {
				Resource resource = RefreshJob.getInstance().getResource(marker.getResource());
				if(resource != null) {
					EObject owner = resource.getEObject(ownerGUID);
					if(owner != null) {
						URI uri = URI.createURI(proxyURI);
						// find the proxy
						//
						int index = -1;
						EReference reference = null;
						find_proxy_loop:
						for (EReference ref : owner.eClass().getEAllReferences()) {
							if(ref.isChangeable() && !ref.isDerived()) {
								if(ref.isMany()) {
									InternalEList list = (InternalEList) owner.eGet(ref);
									int id = 0;
									for (Iterator iterator = list.basicIterator(); iterator
											.hasNext();) {
										InternalEObject o = (InternalEObject) iterator.next();
										if(o.eIsProxy() && uri.equals(o.eProxyURI())) {
											index = id;
											reference = ref;
											break find_proxy_loop;
										}
										id++;
									}
								}
								else {
									InternalEObject o = (InternalEObject) owner.eGet(ref, false);
									if(o != null && o.eIsProxy() && uri.equals(o.eProxyURI())) {
										reference = ref;
										break find_proxy_loop;
									}
								}
							}
						}
						if(reference != null) {
							return new ReferenceInfo(owner, reference, index);
						}
					}
				}
			}
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
		}
		return null;
	}

	/**
	 * @see org.eclipse.ui.IMarkerResolutionGenerator#getResolutions(org.eclipse.core.resources.IMarker)
	 */
	public IMarkerResolution[] getResolutions(IMarker marker) {
		ReferenceInfo refInfo = getReferenceInfo(marker);
		if(refInfo != null) {
			Object selectableOwner = getSelectableObject(refInfo.owner);
			IMarkerResolution markerResolution = new MarkerResolution(marker, refInfo);
			if(selectableOwner != null) {
				return new IMarkerResolution[] {
						markerResolution,
						new ShowOwnerInLibraryView(selectableOwner)
				};
			}
			else {
				return new IMarkerResolution[] {
						markerResolution
				};
			}
		}
		return EMPTY_RESOLUTIONS;
	}

	/**
	 * Get selectable object 
	 * @param owner
	 * @return
	 * 			object itself
	 */
	private static Object getSelectableObject(EObject owner) {
		if(owner instanceof MethodPlugin || owner instanceof ContentElement || owner instanceof ContentPackage 
				|| owner instanceof ProcessComponent) {
			return owner;
		}
		if(owner instanceof MethodElement) {
			ProcessComponent procComp = UmaUtil.getProcessComponent((MethodElement) owner);
			if(procComp != null) {
				return procComp;
			}
		}
		if(owner instanceof ProcessPackage) {
			return owner;
		}
		if(owner instanceof ContentDescription && owner.eContainer() instanceof ContentElement) {
			return owner.eContainer(); 
		}
		Resource resource = owner.eResource();
		MethodElement e = PersistenceUtil.getMethodElement(resource);
		if(e != owner) {
			return getSelectableObject(e);
		}
		return null;
	}

}