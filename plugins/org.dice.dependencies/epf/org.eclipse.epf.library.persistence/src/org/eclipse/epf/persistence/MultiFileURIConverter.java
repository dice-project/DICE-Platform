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

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.impl.URIConverterImpl;
import org.eclipse.epf.resourcemanager.ResourceDescriptor;
import org.eclipse.epf.resourcemanager.ResourceManager;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * URI converter that converts a file-based URI to a UMA-based URI and vice
 * versa using the mapping information stored in ResourceDescriptors of
 * ResourceManager. A UMA-based URI has the following format:
 * uma://GUID1#GUID2 where GUID1 is the GUID of the file and GUID2 is the
 * object's GUID. 
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MultiFileURIConverter extends URIConverterImpl {

	public static final String SCHEME = "uma"; //$NON-NLS-1$

	private static final HashSet lockedGUIDs = new HashSet();

	protected ResourceManager resMgr;

	private URI libraryURI;

	private URI libraryUmaURI;

	private MultiFileResourceSetImpl resourceSet;

	private boolean resolveProxy;

	public MultiFileURIConverter(MultiFileResourceSetImpl resourceSet) {
		this.resourceSet = resourceSet;
	}

	void setResolveProxy(boolean resolveProxy) {
		this.resolveProxy = resolveProxy;
	}

	void setLibraryURIs(MethodLibrary library) {
		libraryURI = library.eResource().getURI();
		libraryUmaURI = URI.createURI(new StringBuffer(SCHEME)
				.append("://").append(library.getGuid()).toString()); //$NON-NLS-1$
	}

	private static void lockGUID(String guid) {
		synchronized (lockedGUIDs) {
			lockedGUIDs.add(guid);
		}
	}

	private static boolean isLockedGUID(String guid) {
		synchronized (lockedGUIDs) {
			return lockedGUIDs.contains(guid);
		}
	}

	private static boolean unlockGUID(String guid) {
		synchronized (lockedGUIDs) {
			return lockedGUIDs.remove(guid);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.resource.impl.URIConverterImpl#normalize(org.eclipse.emf.common.util.URI)
	 */
	public URI normalize(URI uri) {
		return normalize(uri, null);
	}

	public URI normalize(URI uri, EObject resolver) {
		if (SCHEME.equalsIgnoreCase(uri.scheme())) {
			String guid = uri.authority();
			if (isLockedGUID(guid)) {
				return null;
			}
			try {
				lockGUID(guid);

				ResourceDescriptor desc = null;
				HashSet excludedResManagers = null;
				if (resolver != null && resolver.eResource() != null) {
					ResourceManager resMgr = MultiFileSaveUtil
							.getResourceManager(resolver.eResource());
					if (resMgr != null) {
						desc = MultiFileSaveUtil.findResourceDescriptor(resMgr,
								guid, null, resolveProxy);
						if (desc == null) {
							// add this ResourceManager and its submanagers to
							// excludedResManagers
							// so the next search for ResourceDescriptor will
							// ignore them
							//
							excludedResManagers = new HashSet();
							Iterator iter = new AbstractTreeIterator(resMgr) {

								protected Iterator getChildren(Object object) {
									return ((ResourceManager) object)
											.getSubManagers().iterator();
								}

							};
							while (iter.hasNext()) {
								excludedResManagers.add(iter.next());
							}
						}
					}
				}
				if (desc == null && getResourceManager() != null) {
					desc = MultiFileSaveUtil.findResourceDescriptor(
							getResourceManager(), guid, excludedResManagers,
							resolveProxy);
				}
				if (desc != null) {
					URI normalized = desc.getResolvedURI();

					// check if the resource with this normalized URI has
					// temporary URI
					//
					URI tempURI = (URI) resourceSet.getURIToTempURIMap().get(
							normalized);
					if (tempURI != null) {
						normalized = tempURI;
					}

					if (uri.hasFragment()) {
						normalized = normalized.appendFragment(uri.fragment());
					}

					return normalized;
				}
				return null;
			} finally {
				unlockGUID(guid);
			}
		}

		return super.normalize(uri);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.resource.impl.URIConverterImpl#createInputStream(org.eclipse.emf.common.util.URI)
	 */
	public InputStream createInputStream(URI uri) throws IOException {
		if (uri == null)
			return null;
		return super.createInputStream(uri);
	}

	public static URI createURI(String guid) {
		return URI.createURI(new StringBuffer(SCHEME)
				.append("://").append(guid).toString()); //$NON-NLS-1$
	}

	public static URI createURI(MethodElement e) {
		return URI.createURI(createUriString(e));
	}

	public static String createUriString(Object e) {
		InternalEObject o = (InternalEObject) e;

		URI objectURI = o.eProxyURI();
		if (objectURI != null && SCHEME.equals(objectURI.scheme())) {
			return objectURI.toString();
		}

		String guid = MultiFileSaveUtil.getGuid(e);
		if (guid == null) {
			return null;
		}
		return new StringBuffer(SCHEME).append("://").append(guid).toString(); //$NON-NLS-1$
	}

	// public URI toUmaUri(URI uri) {
	// // System.out.println("MultiFileURIConverter.toUmaUri(): uri="+uri);
	// if(uri.scheme().equals("uma") || uriDescMap == null) return uri;
	// if(libraryURI.equals(uri)) return libraryUmaURI;
	// ResourceDescriptor desc = (ResourceDescriptor)
	// uriDescMap.get(uri.trimFragment().toString());
	// return desc == null ? uri : URI.createURI("uma://" +
	// desc.getId()).appendFragment(uri.fragment());
	// }

	// public void setResourceManager(ResourceManager resMgr) {
	// this.resMgr = resMgr;
	// }

	public ResourceManager getResourceManager() {
		if (resMgr == null) {
			resMgr = resourceSet.getRootResourceManager();
		}
		return resMgr;
	}

	/**
	 * Finds ResourceDescriptor for the given GUID from root ResourceManager
	 * 
	 * @param guid
	 * @return
	 */
	public ResourceDescriptor findResourceDescriptor(String guid) {
		return MultiFileSaveUtil.findResourceDescriptor(getResourceManager(),
				guid, null, resolveProxy);
	}

	public void dispose() {
		if (resMgr != null) {
			resMgr.dispose();
			resMgr = null;
		}
	}

	/**
	 * 
	 * @param e
	 * @param uri
	 * @param modifiedResources
	 *            output of resources that have been changed after this call.
	 */
	public void setURIMapping(EObject e, URI uri, Set modifiedResources) {
		setURIMapping(e, uri, modifiedResources, true);
	}

	public void setURIMapping(EObject e, URI uri, Set modifiedResources,
			boolean afterMove) {
		String guid = MultiFileSaveUtil.getGuid(e);
		ResourceManager resMgr = MultiFileSaveUtil.getResourceManagerFor(e,
				modifiedResources);
		ResourceDescriptor resDesc = resMgr.getResourceDescriptor(guid);
		if (resDesc != null) {
			if (afterMove) {
				resDesc.setResolvedURI(uri);
			} else {
				// preserve the current resolved URI
				resDesc.getResolvedURI();
				// set new URI string for the upcoming move
				resDesc.setUri(uri.deresolve(
						MultiFileSaveUtil.getFinalURI(resDesc.eResource()))
						.toString());
			}
			if (modifiedResources != null)
				modifiedResources.add(resDesc.eResource());
		} else {
			// find the ResourceDescriptor in other ResourceManagers
			//
			MultiFileResourceSetImpl resourceSet = (MultiFileResourceSetImpl) e
					.eResource().getResourceSet();
			ResourceManager libResMgr = resourceSet.getRootResourceManager();
			resDesc = MultiFileSaveUtil.findResourceDescriptor(libResMgr, guid,
					Collections.singletonList(resMgr), resolveProxy);
			if (resDesc != null) {
				// ResourceDescriptor already exists
				// change its uri, move it and its sub manager, if there is any,
				// to new ResourceManager
				//

				// find sub manager
				ResourceManager currentResMgr = (ResourceManager) resDesc
						.eContainer();
				ResourceManager subMgr = null;
				for (Iterator iter = currentResMgr.getSubManagers().iterator(); iter
						.hasNext();) {
					ResourceManager mgr = (ResourceManager) iter.next();
					URI mgrURI = ((MultiFileXMIResourceImpl) mgr.eResource())
							.getFinalURI();
					if (mgrURI.equals(uri)) {
						subMgr = mgr;
						break;
					}
				}

				// move ResourceDescriptor
				resMgr.getResourceDescriptors().add(resDesc);
				if (modifiedResources != null) {
					modifiedResources.add(currentResMgr.eResource());
					modifiedResources.add(resMgr.eResource());
				}

				// move sub ResourceManager
				if (subMgr != null) {
					resMgr.getSubManagers().add(subMgr);
				}

				// set new URI
				if (afterMove) {
					resDesc.setResolvedURI(uri);
				} else {
					// preserve the current resolved URI
					resDesc.getResolvedURI();
					// set new URI string for the upcoming move
					resDesc.setUri(uri.deresolve(resDesc.eResource().getURI())
							.toString());
				}
				if (modifiedResources != null)
					modifiedResources.add(resDesc.eResource());
			} else {
				MultiFileSaveUtil.registerWithResourceManager(resMgr, e, uri);
				if (modifiedResources != null)
					modifiedResources.add(resMgr.eResource());
			}
		}
	}

	public static void main(String[] args) {
		URI uri = URI
				.createURI("uma://_LPzdAGZ7EdmAm_xSX2EdUA#//@breakdownElements.0"); //$NON-NLS-1$
		System.out.println(uri);
		System.out
				.println("deresolved URI: " + uri.deresolve(URI.createURI("uma://_LPzdAGZ7EdmAm_xSX2EdUA"))); //$NON-NLS-1$ //$NON-NLS-2$
	}

}