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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.epf.resourcemanager.ResourceDescriptor;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * XMLHelper implementation for library XMI persistence
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MultiFileXMIHelperImpl extends XMIHelperImpl {

	/**
	 * Platform-specific line separator.
	 */
	private static final String LINE_SEP = System.getProperty("line.separator"); //$NON-NLS-1$

	protected boolean logError = true;

	protected List sameDocReferences;

	private boolean discardUnresolvedRef;
	
	/**
	 * A temporary flag to have a quick fix for getValue method call without modifying the passed
	 * object. Should be removed with future refactoring.
	 */
	public static boolean unmodifiedGetValue = false;		

	/**
	 * Creates a new instance.
	 */
	public MultiFileXMIHelperImpl(MultiFileXMIResourceImpl xmiRes) {
		super(xmiRes);
		sameDocReferences = new UniqueEList();
		Boolean b = (Boolean) ((MultiFileResourceSetImpl) xmiRes
				.getResourceSet()).getDefaultSaveOptions().get(
				MultiFileXMISaveImpl.DISCARD_UNRESOLVED_REFERENCES);
		discardUnresolvedRef = b != null ? b.booleanValue() : false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl#setResource(org.eclipse.emf.ecore.xmi.XMLResource)
	 */
	public void setResource(XMLResource resource) {
		super.setResource(resource);
		if(resource instanceof MultiFileXMIResourceImpl) {
			resourceURI = ((MultiFileXMIResourceImpl)resource).getFinalURI();
			deresolve = resourceURI != null && !resourceURI.isRelative() && resourceURI.isHierarchical();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl#getHREF(org.eclipse.emf.ecore.EObject)
	 */
	public String getHREF(EObject obj) {
		if (discardUnresolvedRef) {
			// remove unresolved references
			//
			if (obj instanceof MultiResourceEObject
					&& !((MultiResourceEObject) obj).isValid()) {
				return null;
			}
		}

		if (obj instanceof MultiResourceEObject) {
			InternalEObject o = (InternalEObject) obj;

			URI objectURI = o.eProxyURI();
			if (objectURI == null) {
				Resource otherResource = obj.eResource();
				if (otherResource == null) {
					objectURI = handleDanglingHREF(obj);
					if (objectURI == null) {
						return null;
					}
				} else if (resource == otherResource) {
					return "#" + resource.getURIFragment(obj); //$NON-NLS-1$
				} else {
					if (obj instanceof MethodConfiguration) {
						// Special handling for MethodConfiguration.
						// See
						// org.eclipse.epf.persistence.MultiFileResourceSetImpl.loadConfigurations()
						//
						EObject lib = obj.eContainer();
						if (lib != null) {
							otherResource = lib.eResource();
						} else {
							return null;
						}
					}
					if (otherResource != null) {
						ResourceSet resourceSet = otherResource.getResourceSet();
						if(resourceSet instanceof MultiFileResourceSetImpl 
								&& ((MultiFileResourceSetImpl)resourceSet).getResourceManager() == null) {
							objectURI = MultiFileSaveUtil.getFinalURI(otherResource).appendFragment(getURIFragment(otherResource, obj));
							return deresolve(objectURI).toString();
						}
						ResourceDescriptor resDesc = MultiFileSaveUtil
								.getResourceDescriptor(otherResource);
						if (resDesc != null) {
							objectURI = MultiFileURIConverter.createURI(
									resDesc.getId()).appendFragment(
									getURIFragment(otherResource, obj));
							return objectURI.toString();
						} else {
							return null;
						}
					}
				}
			} else {
				if (!objectURI.scheme().equals(MultiFileURIConverter.SCHEME)) {
					String fragment = objectURI.fragment();
					if (resource instanceof MultiFileXMIResourceImpl
							&& ((MultiFileXMIResourceImpl) resource)
									.getEObjectByID(fragment) != null) {
						return "#" + fragment; //$NON-NLS-1$
					} else {
						objectURI = deresolve(objectURI);
					}
				}
				return objectURI.toString();
			}
		}

		return super.getHREF(obj);
	}

	protected static class ProxyReference {
		private InternalEObject owner;

		private EReference reference;

		private InternalEObject proxy;

		/**
		 * @param owner
		 * @param reference
		 * @param proxy
		 */
		public ProxyReference(InternalEObject owner, EReference reference,
				InternalEObject proxy) {
			super();
			this.owner = owner;
			this.reference = reference;
			this.proxy = proxy;
		}

		public InternalEObject getOwner() {
			return owner;
		}

		public InternalEObject getProxy() {
			return proxy;
		}

		public EReference getReference() {
			return reference;
		}

	}

	public void setValue(EObject object, EStructuralFeature feature,
			Object value, int position) {
		
		if(feature.isDerived()) {
			return;
		}
		
		int kind = getFeatureKind(feature);

		try {
			// TODO: temporary fix to avoid ArrayIndexOutOfBoundsException,
			// needs revisit
			//
			if (kind == IS_MANY_MOVE) {
				List list = (List) object.eGet(feature);
				if (position > -1 && list.indexOf(value) == -1) {
					return;
				}
			}

			super.setValue(object, feature, value, position);
			if (feature instanceof EReference) {
				EReference ref = (EReference) feature;
				if (ref.isContainment()) {
					if (value instanceof InternalEObject) {
						if (!((InternalEObject) value).eIsProxy()) {
							if (value instanceof MethodElement) {
								if (! UmaUtil.unresolvedGuidSet.isEmpty()) {
									UmaUtil.unresolvedGuidSet.remove(((MethodElement) value).getGuid());
								}
							}
						}
					}

				}
			}

			switch (kind) {
			case IS_MANY_ADD:
			case IS_MANY_MOVE:
				InternalEList list = (InternalEList) object.eGet(feature);

				// save same document forward (unidirectional) references to
				// resolve at the end of document parsing
				//				
				if (feature instanceof EReference) {
					EReference ref = (EReference) feature;
					if (ref.getEOpposite() != null) {
						InternalEObject owner = (InternalEObject) object;
						for (Iterator iter = list.basicIterator(); iter
								.hasNext();) {
							InternalEObject element = (InternalEObject) iter
									.next();
							if (element.eIsProxy()
									&& element.eProxyURI().trimFragment()
											.equals(resourceURI)) {
								sameDocReferences.add(new ProxyReference(owner,
										ref, element));
							}
						}
					}
				}

				break;
			}
		} catch (RuntimeException e) {
			if (logError) {
				if(MultiFileSaveUtil.DEBUG) {
					e.printStackTrace();
				}
				CommonPlugin.INSTANCE.log(e);
				String errMsg = new StringBuffer(
						"MultiFileXMIHelperImpl.setValue(): ERROR") //$NON-NLS-1$
						.append("\n  object: ").append(object) //$NON-NLS-1$
						.append("\n  feature: ").append(feature) //$NON-NLS-1$
						.append("\n  value: ").append(value) //$NON-NLS-1$
						.append("\n  position: ").append(position) //$NON-NLS-1$
						.append("\n  kind: ").append(kind) //$NON-NLS-1$
						.toString();
				if(MultiFileSaveUtil.DEBUG) {
					System.err.println(errMsg);
				}
				CommonPlugin.INSTANCE.log(errMsg);
			}
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl#getValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public Object getValue(EObject obj, EStructuralFeature f) {
		Object value = super.getValue(obj, f);
		
		if (unmodifiedGetValue) {
			return value;
		}
		
		// remove any value that does not belong to any resource
		//
		if(f instanceof EReference) {
			if(f.isMany()) {
				if(f.isChangeable() && value instanceof InternalEList) {
					InternalEList list = (InternalEList) value;
					for (Iterator iter = list.iterator(); iter.hasNext();) {
						EObject o = (EObject) iter.next();
						if(!o.eIsProxy() && o.eResource() == null) {
							iter.remove();
						}
					}
				}
			}
			else {
				EObject o = (EObject) value;
				if(!o.eIsProxy() && o.eResource() == null) {
					value = null;
				}
			}
		}
		
		return value;
	}
		
}
