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
package org.eclipse.epf.uma.ecore.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.epf.uma.ecore.IModelObject;
import org.eclipse.epf.uma.ecore.IProxyResolutionListener;
import org.eclipse.epf.uma.ecore.IUmaResourceSet;
import org.eclipse.epf.uma.ecore.Property;
import org.eclipse.epf.uma.ecore.ResolveException;
import org.eclipse.epf.uma.ecore.Type;
import org.eclipse.epf.uma.ecore.util.DefaultValueManager;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.ecore.util.OppositeFeatureResolvingEList;

/**
 * The base class for all UMA model objects.
 * <p>
 * By default, EMF stores model objects related via a containment relationship
 * in a single XMI file. This class extends the default EMF persistence behavior
 * by providing a mechanism for the containing and contained objects to be
 * stored in separate XMI files. Contained objects are lazy loaded, when
 * required, similar to the proxy object behavior of EMF's standard resource
 * implementation.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MultiResourceEObject extends EObjectImpl implements
		IModelObject {

	private static final long serialVersionUID = 3258126947153097273L;
	
	private static final boolean DEBUG = false;
	
	private static final DefaultValueManager defaultValueManager = DefaultValueManager.INSTANCE;
	
	/**
	 * Sets the default value for a feature.
	 * <p>
	 * Note: This method is designed for migration use only. Do not call this
	 * method to override the default value of a feature since it will slow down
	 * the loading of a method library.
	 * 
	 * @param feature
	 *            a feature
	 * @param defaultValue
	 *            the default value for the feature
	 */
	public static final void setDefaultValue(EStructuralFeature feature,
			Object defaultValue) {		
		setDefaultValue(feature.getEContainingClass(), feature, defaultValue);
	}
	
	public static final synchronized void setDefaultValue(EClass type, EStructuralFeature feature, Object defaultValue) {
		defaultValueManager.setDefaultValue(type, feature, defaultValue);
	}
	
	/**
	 * Removes the default value for a feature.
	 * <p>
	 * Note: This method is designed for migration use only. Do not call this
	 * method to override the default value of a feature since it will slow down
	 * the loading of a method library.
	 * 
	 * @param feature
	 *            a feature
	 */
	public static final void removeDefaultValue(EStructuralFeature feature) {
		defaultValueManager.removeDefaultValue(feature);
	}
	
	public static final void removeDefaultValue(EStructuralFeature feature, EClass type) {
		defaultValueManager.removeDefaultValue(feature, type);
	}
	
	/**
	 * A map of entries of OppositeFeature / OppositeFeature's value
	 */
	private Map oppositeFeatureMap;

	private boolean hasOppositeFeature = true;

	private Boolean notifyOpposite = null;

	private boolean valid = true;
	
	private ExtendObject extendObject;
	
	public ExtendObject getExtendObject() {
		return extendObject;
	}

	public void setExtendObject(ExtendObject extendObject) {
		this.extendObject = extendObject;
	}

	/**
	 * Checks the validity of this object.
	 * 
	 * @return <code>true</code> if this object is valid
	 */
	public boolean isValid() {
		return valid;
	}

	public void oppositeAdd(OppositeFeature oppositeFeature, Object object) {
		List list = (List) getOppositeFeatureMap().get(oppositeFeature);
		if (list == null) {
			list = new OppositeFeatureResolvingEList(this, oppositeFeature);
			getOppositeFeatureMap().put(oppositeFeature, list);
		}
		if (!list.contains(object)) {
			boolean oldDeliver = eDeliver();
			eSetDeliver(false);
			try {
				list.add(object);
			} finally {
				eSetDeliver(oldDeliver);
			}
		}
	}

	public void oppositeRemove(OppositeFeature oppositeFeature, Object object) {
		List list = (List) getOppositeFeatureMap().get(oppositeFeature);
		if (list == null) {
			list = new OppositeFeatureResolvingEList(this, oppositeFeature);
			getOppositeFeatureMap().put(oppositeFeature, list);
		}
		boolean oldDeliver = eDeliver();
		eSetDeliver(false);
		try {
			list.remove(object);
		} finally {
			eSetDeliver(oldDeliver);
		}
	}

	/**
	 * Resolves the given proxy object.
	 * 
	 * @param object
	 *            a proxy object to resolve
	 * @return the resolved object
	 */
	public Object resolve(Object object) {
		if (object instanceof InternalEObject
				&& ((InternalEObject) object).eIsProxy()) {
			return eResolveProxy((InternalEObject) object);
		}
		return object;
	}

	private void replace(EStructuralFeature feature, Object oldValue,
			InternalEObject newValue) {
		if (newValue != null && !newValue.eIsProxy() && newValue != oldValue) {
			boolean notify = eDeliver();
			try {
				eSetDeliver(false);
				EcoreUtil.replace(this, feature, oldValue, newValue);
			} catch (Exception e) {
				if (DEBUG) {
					CommonPlugin.INSTANCE.log(e);
					e.printStackTrace();
					System.out.println("MultiResourceEObject.replace():"); //$NON-NLS-1$
					System.out.println("  object: " + this); //$NON-NLS-1$
					System.out.println("  feature: " + feature); //$NON-NLS-1$
					System.out.println("  proxy: " + oldValue); //$NON-NLS-1$
					System.out.println("  resolved: " + newValue); //$NON-NLS-1$
				}
			} finally {
				eSetDeliver(notify);
			}
		}

	}

	/**
	 * @see org.eclipse.emf.common.notify.impl.BasicNotifierImpl#eNotify(org.eclipse.emf.common.notify.Notification)
	 */
	public void eNotify(Notification msg) {
		/*
		 * if(!OppositeFeature.isDeliverOnResolve() && msg.getEventType() ==
		 * Notification.RESOLVE) { return; }
		 */

		if (msg.getEventType() == Notification.RESOLVE) {
			return;
		}
		
		Object f = msg.getFeature();
		if (f instanceof EStructuralFeature) {
			EStructuralFeature feature = (EStructuralFeature) f;
			OppositeFeature oppositeFeature = OppositeFeature
					.getOppositeFeature(feature);
			if (oppositeFeature != null) {
				MultiResourceEObject oldOtherEnd;
				MultiResourceEObject otherEnd;
				if (oppositeFeature.isMany()) {
					switch (msg.getEventType()) {
					case Notification.SET:
						oldOtherEnd = (MultiResourceEObject) msg.getOldValue();
						if (oppositeFeature.resolveOwner()) {
							oldOtherEnd = (MultiResourceEObject) resolve(oldOtherEnd);
						}
						if (oldOtherEnd != null) {
							oldOtherEnd.oppositeRemove(oppositeFeature, msg
									.getNotifier());
						}
					case Notification.ADD:
						otherEnd = (MultiResourceEObject) msg.getNewValue();
						if (oppositeFeature.resolveOwner()) {
							otherEnd = (MultiResourceEObject) resolve(otherEnd);
							replace(feature, msg.getNewValue(), otherEnd);
						}
						if (otherEnd != null) {
							otherEnd.oppositeAdd(oppositeFeature, msg
									.getNotifier());
						}
						break;
					case Notification.ADD_MANY:
						for (Iterator iter = ((Collection) msg.getNewValue())
								.iterator(); iter.hasNext();) {
							Object obj = iter.next();
							otherEnd = (MultiResourceEObject) obj;
							if (oppositeFeature.resolveOwner()) {
								otherEnd = (MultiResourceEObject) resolve(otherEnd);
								replace(feature, obj, otherEnd);
							}
							otherEnd.oppositeAdd(oppositeFeature, msg
									.getNotifier());
						}
						break;
					case Notification.REMOVE:
						otherEnd = (MultiResourceEObject) msg.getOldValue();
						if (oppositeFeature.resolveOwner()) {
							otherEnd = (MultiResourceEObject) resolve(otherEnd);
						}
						if (otherEnd != null)
							otherEnd.oppositeRemove(oppositeFeature, msg
									.getNotifier());
						break;
					case Notification.REMOVE_MANY:
						for (Iterator iter = ((Collection) msg.getOldValue())
								.iterator(); iter.hasNext();) {
							otherEnd = (MultiResourceEObject) iter.next();
							if (oppositeFeature.resolveOwner()) {
								otherEnd = (MultiResourceEObject) resolve(otherEnd);
							}
							otherEnd.oppositeRemove(oppositeFeature, msg
									.getNotifier());
						}
						break;
					}
				} else {
					switch (msg.getEventType()) {
					case Notification.ADD_MANY:
						for (Iterator iter = ((Collection) msg.getNewValue())
								.iterator(); iter.hasNext();) {
							Object obj = iter.next();
							otherEnd = (MultiResourceEObject) obj;
							if (oppositeFeature.resolveOwner()) {
								otherEnd = (MultiResourceEObject) resolve(otherEnd);
								replace(feature, obj, otherEnd);
							}
							if (otherEnd != null) {
								EObject oldValue = (EObject) otherEnd
										.getOppositeFeatureMap().get(
												oppositeFeature);
								if (oldValue != null) {
									// remove otherEnd from target feature of
									// oldValue
									((Collection) oldValue
											.eGet((EStructuralFeature) f))
											.remove(otherEnd);
								}
								otherEnd.getOppositeFeatureMap().put(
										oppositeFeature, msg.getNotifier());
							}
						}
						break;
					case Notification.REMOVE_MANY:
						for (Iterator iter = ((Collection) msg.getOldValue())
								.iterator(); iter.hasNext();) {
							otherEnd = (MultiResourceEObject) iter.next();
							if (oppositeFeature.resolveOwner()) {
								otherEnd = (MultiResourceEObject) resolve(otherEnd);
							}
							otherEnd.getOppositeFeatureMap().put(
									oppositeFeature, null);
						}
						break;
					case Notification.ADD:
						otherEnd = (MultiResourceEObject) msg.getNewValue();
						if (oppositeFeature.resolveOwner()) {
							otherEnd = (MultiResourceEObject) resolve(otherEnd);
							replace(feature, msg.getNewValue(), otherEnd);
						}
						if (otherEnd != null) {
							EObject oldValue = (EObject) otherEnd
									.getOppositeFeatureMap().get(
											oppositeFeature);
							if (oldValue != null) {
								// remove otherEnd from target feature of
								// oldValue
								((Collection) oldValue
										.eGet((EStructuralFeature) f))
										.remove(otherEnd);
							}
							otherEnd.getOppositeFeatureMap().put(
									oppositeFeature, msg.getNotifier());
						}
						break;
					case Notification.SET:
						otherEnd = (MultiResourceEObject) msg.getNewValue();
						if (oppositeFeature.resolveOwner()) {
							otherEnd = (MultiResourceEObject) resolve(otherEnd);
							replace(feature, msg.getNewValue(), otherEnd);
						}
						if (otherEnd != null) {
							EObject oldValue = (EObject) otherEnd
									.getOppositeFeatureMap().get(
											oppositeFeature);
							if (oldValue != null) {
								// set the target feature of oldValue to null
								oldValue.eSet((EStructuralFeature) f, null);
							}
							otherEnd.getOppositeFeatureMap().put(
									oppositeFeature, msg.getNotifier());
						}
						else {
							EStructuralFeature targetFeature = (EStructuralFeature) f;
							if(!targetFeature.isMany()) {
								oldOtherEnd = (MultiResourceEObject) msg.getOldValue();
								if(oldOtherEnd != null) {
									oldOtherEnd.getOppositeFeatureMap().put(oppositeFeature, null);
								}
							}
						}
						break;
					case Notification.REMOVE:
						// case Notification.UNSET:
						otherEnd = (MultiResourceEObject) msg.getOldValue();
						if (oppositeFeature.resolveOwner()) {
							otherEnd = (MultiResourceEObject) resolve(otherEnd);
						}
						if (otherEnd != null)
							otherEnd.getOppositeFeatureMap().put(
									oppositeFeature, null);
						break;

					}
				}
			}
		}

		super.eNotify(msg);
	}

	/**
	 * @see org.eclipse.emf.common.notify.impl.BasicNotifierImpl#eNotificationRequired()
	 */
	public boolean eNotificationRequired() {
		if (!eDeliver())
			return false;

		Resource resource = eResource();
		if (resource != null && !resource.eDeliver())
			return false;

		if (notifyOpposite == null) {
			if (OppositeFeature.featureOppositeFeatureMap.isEmpty()) {
				notifyOpposite = Boolean.FALSE;
			} else {
				Collection features = OppositeFeature.featureOppositeFeatureMap
						.keySet();
				for (Iterator iter = eClass().getEAllReferences().iterator(); iter
						.hasNext();) {
					if (features.contains(iter.next())) {
						notifyOpposite = Boolean.TRUE;
						break;
					}
				}
				if (notifyOpposite == null) {
					notifyOpposite = Boolean.FALSE;
				}
			}
		}
		if (notifyOpposite.booleanValue())
			return true;
		return super.eNotificationRequired();
	}

	/*
	 * private void removeChildResources() { List children = eContents(); int
	 * size = children.size(); for (int i = 0; i < size; i++) {
	 * MultiResourceEObject child = (MultiResourceEObject) children.get(i);
	 * Resource resource = child.eResource(); if (resource != null && resource !=
	 * eResource()) { child.removeChildResources(); } ((InternalEList)
	 * resource.getContents()).basicRemove(this, null);
	 * child.eProperties().setEResource(null); } }
	*/

	/**
	 * @see org.eclipse.emf.ecore.InternalEObject#eBasicSetContainer(org.eclipse.emf.ecore.InternalEObject,
	 *      int, org.eclipse.emf.common.notify.NotificationChain)
	 */
	public NotificationChain eBasicSetContainer(InternalEObject newContainer,
			int newContainerFeatureID, NotificationChain msgs) {
		Resource.Internal oldResource = this.eDirectResource();
		Resource.Internal newResource = newContainer == null ? null
				: newContainer.eInternalResource();
		// if (oldResource != newResource && oldResource != null) {
		// removeChildResources();
		// }

		int oldIndex = -1;
		int oldSize = -1;
		if (oldResource != null) {
			oldIndex = oldResource.getContents().indexOf(this);
			oldSize = oldResource.getContents().size();
		}

		NotificationChain messages = super.eBasicSetContainer(newContainer,
				newContainerFeatureID, msgs);
		if (oldResource != newResource && oldResource != null) {
			// remove any newly added ModificationTrackingAdapters from this
			// object's adapter list
			//
			if (newResource != null)
				newResource.detached(this);

			// Override the default semantic: MultiResourceEObject is allowed to
			// be owned by both container and resource.
			// Add this object back to the old resource.
			//
			BasicEList contents = ((BasicEList) oldResource.getContents());
			if (oldSize != contents.size()) {
				// this object has been removed from the resource's contents
				// add it back
				//
				if (contents.isEmpty()) {
					// this will flag resource as loaded
					//
					contents.clear();
					contents.setData(1, new Object[] { this });
				} else {
					Object[] data = contents.toArray();
					Object[] newData = new Object[data.length + 1];
					if (oldIndex > 0) {
						System.arraycopy(data, 0, newData, 0, oldIndex);
					}
					newData[oldIndex] = this;
					if (oldIndex < data.length) {
						System.arraycopy(data, oldIndex, newData, oldIndex + 1,
								data.length - oldIndex);
					}
					contents.setData(newData.length, newData);
				}
			}
			eSetResource(oldResource);
			// don't have to re-attach this object to oldResource since it was
			// not dettached in super method
			//
			// oldResource.attached(this);

			// if(newResource != null) newResource.attached(this);
		}
		return messages;
	}

	/**
	 * Sets the container and container feature ID for this object only if it is
	 * not contained by any container.
	 * 
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eBasicSetContainer(org.eclipse.emf.ecore.InternalEObject,
	 *      int)
	 */
	public void eSetContainer(InternalEObject newContainer,
			int newContainerFeatureID) {
		EObject container = eInternalContainer();
		if (container != null && !container.eIsProxy())
			return;
		super.eBasicSetContainer(newContainer, newContainerFeatureID);
	}

	/**
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eBasicSetContainer(org.eclipse.emf.ecore.InternalEObject,
	 *      int)
	 */
	public void eBasicSetContainer(InternalEObject newContainer,
			int newContainerFeatureID) {
		super.eBasicSetContainer(newContainer, newContainerFeatureID);
	}

	/**
	 * Sets the containing resource for this object.
	 * 
	 * @param res
	 *            a resource
	 */
	public void eSetResource(Resource.Internal res) {
		eProperties().setEResource(res);
	}

	/**
	 * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eDirectResource()
	 */
	public Internal eDirectResource() {
		return super.eDirectResource();
	}

	/**
	 * Returns the resolved object represented by proxy. Proxy chains are
	 * followed. If resourceSet is null, the global package registry is
	 * consulted to obtain a package registered against the proxy URI, less its
	 * fragment, in the same manner as the default resource set implementation's
	 * fallback behaviour.
	 * 
	 * @param proxy
	 *            the proxy to be resolved.
	 * @param resourceSet
	 *            the resource set in which to resolve.
	 * @return the resolved object, or the proxy if unable to resolve.
	 * @see org.eclipse.emf.ecore.util.EcoreUtil#resolve(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.resource.ResourceSet)
	 */
	private EObject resolveProxy(EObject proxy) {
		ResourceSet resourceSet = eResource() != null ? eResource()
				.getResourceSet() : null;

		URI proxyURI = ((InternalEObject) proxy).eProxyURI();
		if (proxyURI != null) {
			try {
				EObject resolvedObject = null;

				if (resourceSet != null) {
					if (resourceSet instanceof IUmaResourceSet) {
						resolvedObject = ((IUmaResourceSet) resourceSet)
								.getEObject(this, proxyURI, true);
					} else {
						resolvedObject = resourceSet.getEObject(proxyURI, true);
					}
				} else {
					EPackage ePackage = EPackage.Registry.INSTANCE
							.getEPackage(proxyURI.trimFragment().toString());
					if (ePackage != null) {
						Resource resource = ePackage.eResource();
						if (resource != null) {
							resolvedObject = resource.getEObject(proxyURI
									.fragment().toString());
						}
					}
				}

				if (resolvedObject != null) {
					if(resolvedObject != proxy) {
//						return resolveProxy(resolvedObject);
						return resolvedObject;
					}
				} else {
					if (resourceSet instanceof IProxyResolutionListener) {
						((IProxyResolutionListener) resourceSet)
								.notifyException(new ResolveException(proxy,
										(String) null, this));
					}
				}
			} catch (RuntimeException exception) {
				if (resourceSet instanceof IProxyResolutionListener) {
					((IProxyResolutionListener) resourceSet)
							.notifyException(new ResolveException(proxy,
									exception, this));
				}
			}
		}
		return proxy;
	}

	private Map<InternalEObject, EObject> proxyMap = new HashMap<InternalEObject, EObject>();
	/**
	 * @see org.eclipse.emf.ecore.InternalEObject#eResolveProxy(org.eclipse.emf.ecore.InternalEObject)
	 */
	public EObject eResolveProxy(InternalEObject proxy) {
		EObject cachedEObejct = proxyMap.get(proxy);
		if (cachedEObejct != null) {
			return cachedEObejct;
		}
		EObject ret = null;
		try {		
			ret = eResolveProxy_(proxy);
		} finally {
			proxyMap.remove(proxy);
		}
		
		return ret;
	}
	
	private EObject eResolveProxy_(InternalEObject proxy) {
		EObject container = proxy.eContainer();
		int featureID = proxy.eContainerFeatureID();
		EObject result = null;

		result = resolveProxy(proxy);
		if (result != null) {
			proxyMap.put(proxy, result);
		}

		if (result != null && result instanceof MultiResourceEObject) {
			if (proxy.eIsProxy() && result == proxy) {
				// proxy could not be resolved
				//
				((MultiResourceEObject) result).valid = false;
			} else {
				((MultiResourceEObject) result).valid = true;

				if (container != null) {
					((MultiResourceEObject) result).eSetContainer(
							(InternalEObject) container, featureID);
				}

				// merge the opposite feature map
				//
				if (result != proxy) {
					if(proxy instanceof MultiResourceEObject) {
						MultiResourceEObject myObj = (MultiResourceEObject) proxy;

						if (myObj.oppositeFeatureMap != null
								&& !myObj.oppositeFeatureMap.isEmpty()) {
							Map newMap = ((MultiResourceEObject) result)
							.getOppositeFeatureMap();
							for (Iterator iter = myObj.getOppositeFeatureMap()
									.entrySet().iterator(); iter.hasNext();) {
								Map.Entry entry = (Map.Entry) iter.next();
								if (entry.getValue() != null) {
									OppositeFeature oppositeFeature = (OppositeFeature) entry
									.getKey();
									if (oppositeFeature.isMany()) {
										List values = (List) newMap
										.get(oppositeFeature);
										if (values == null) {
											newMap.put(oppositeFeature, entry
													.getValue());
										} else {
											values.addAll((Collection) entry
													.getValue());
										}
									} else {
										newMap.put(oppositeFeature, entry
												.getValue());
									}
								}
							}
							myObj.getOppositeFeatureMap().clear();
						}
					}
					
					ResourceSet resourceSet = eResource() != null ? eResource()
							.getResourceSet() : null;
					if (resourceSet instanceof IProxyResolutionListener) {
						((IProxyResolutionListener) resourceSet).proxyResolved(
								proxy, result);
					}
				}
			}

		}

		return result;
	}

	private Map createOppositeFeatureMap() {
		Map map = new HashMap();
		for (Iterator iter = OppositeFeature.classOppositeFeaturesMap
				.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			Class cls = (Class) entry.getKey();
			if (cls.isInstance(this)) {
				for (Iterator iterator = ((Collection) entry.getValue())
						.iterator(); iterator.hasNext();) {
					map.put(iterator.next(), null);
				}
			}
		}
		if (map.isEmpty()) {
			hasOppositeFeature = false;
			return Collections.EMPTY_MAP;
		}
		return map;
	}

	/**
	 * Gets the opposite feature map.
	 * 
	 * @return a map containing the opposite features mapped to their values
	 */
	public Map getOppositeFeatureMap() {
		if (oppositeFeatureMap == null && hasOppositeFeature) {
			oppositeFeatureMap = createOppositeFeatureMap();
		}
		if(oppositeFeatureMap == null) {
			return Collections.EMPTY_MAP;
		}
		return oppositeFeatureMap;
	}

	/**
	 * Gets the opposite feature map.
	 * 
	 * @return a map containing the opposite features mapped to their values or
	 *         <code>null</code> if this object does not have any opposite
	 *         feature or the map has not been created
	 */
	public Map basicGetOppositeFeatureMap() {
		return oppositeFeatureMap;
	}

	/**
	 * Gets the value of an opposite feature.
	 * 
	 * @param feature
	 *            an opposite feature
	 * @return the value for the opposite feature
	 */
	public Object getOppositeFeatureValue(OppositeFeature feature) {
		ExtendObject extendObject = getExtendObject();
		if (extendObject != null && extendObject.handleOppostie(feature)) {
			return extendObject.getOppositeFeatureValue(feature);
		}
				
		Object value = getOppositeFeatureMap().get(feature);

		// System.out.println("MultiResourceEObject.getOppositeFeatureValue():");
		// System.out.println(" feature: " + feature);
		// System.out.println(" value: " + value);
		// System.out.println(" this: " + this);

		if (feature.isMany()) {
			if (value == null) {
				return Collections.EMPTY_LIST;
			}

			return ((OppositeFeatureResolvingEList) value)
					.getUnmodifiableList();
		} else if (value instanceof EObject
				&& ((EObject) value).eResource() == null) {
			getOppositeFeatureMap().put(feature, null);
			return null;
		}

		if (value instanceof InternalEObject
				&& ((InternalEObject) value).eIsProxy()) {
			EObject resolved = eResolveProxy((InternalEObject) value);
			if (resolved != value) {
				getOppositeFeatureMap().put(feature, resolved);
				value = resolved;
			}
		}
		return value;
	}

	/**
	 * Gets the resource at a given location.
	 * 
	 * @param location
	 *            the resource location
	 * @return a resource in the workspace
	 */
	public static IResource getResourceForLocation(String location) {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath path = new Path(location);
		IResource resource;
		File file =  new File(location);
		if (file.isFile()) {
			resource = workspaceRoot.getFileForLocation(path);
			if(resource == null) {
				IResource parentResource = getResourceForLocation(file
						.getParent());
				if(parentResource != null) {
					try {
						parentResource.refreshLocal(IResource.DEPTH_ONE, null);
					} catch (CoreException e) {
						CommonPlugin.INSTANCE.log(e);
					}
					resource = workspaceRoot.getFileForLocation(path);
				}
			}
		} else {
			resource = workspaceRoot.getContainerForLocation(path);
		}
		return resource;
	}

//	/* (non-Javadoc)
//	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
//	 */
//	public Object getAdapter(Class adapter) {
//		if(adapter == IResource.class) {
//			Resource resource = eResource();
//			if(resource != null) {
//				return getResourceForLocation(resource.getURI().toFileString());
//			}
//		}
//		return null;
//	}
	
	/**
	 * Removes all opposite features registered with the system.
	 */
	public void removeFromAllOppositeFeatures() {
		// find all features that have opposite features and clear those
		// features. This will remove the references to
		// unloaded object by those opposite features
		//
		for (Iterator iter = eClass().getEAllReferences().iterator(); iter
				.hasNext();) {
			EReference ref = (EReference) iter.next();
			OppositeFeature oppositeFeature = OppositeFeature.getOppositeFeature(ref);
			if(oppositeFeature != null) {
				if(ref.isMany()) {
					List list = (List) eGet(ref, false);					
					if(!list.isEmpty()) {
						if(!oppositeFeature.resolveOwner()) {
							list.clear();
						}
						else if(list instanceof InternalEList) {
							List basicList = ((InternalEList)list).basicList();
							for(int i = basicList.size() - 1; i > -1; i--) {
								EObject e = (EObject) basicList.get(i);
								if(!e.eIsProxy()) {
									list.remove(e);
								}
							}
						}
					}
				}
				else {
					EObject e = (EObject) eGet(ref, false);
					if(e != null && !e.eIsProxy()) {
						eSet(ref, null);
					}
				}
			}
		}

	}

	/**
	 * @see org.eclipse.epf.uma.ecore.IModelObject#getOppositeFeatures()
	 */
	public Collection getOppositeFeatures() {
		return getOppositeFeatureMap().keySet();
	}

	/**
	 * @see org.eclipse.epf.uma.ecore.IModelObject#getDefaultValue(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public Object getDefaultValue(EStructuralFeature feature) {
		Object value = getFeatureToDefaultValueMap().get(feature);
		if(value == null) {
			value = feature.getDefaultValue();
		}
		return value;
	}
	
	private Map<EStructuralFeature, Object> getFeatureToDefaultValueMap() {
		return defaultValueManager.getFeatureToDefaultValueMap(eClass());
	}
	
	/**
	 * Unregisters a feature whose default value has been overriden.
	 * 
	 * @param feature
	 *            a feature
	 */
	public void removeFeatureWithOverridenDefaultValue(
			EStructuralFeature feature) {
		Map featureToDefaultValueMap = getFeatureToDefaultValueMap();
		if(featureToDefaultValueMap != null) {
			try {
				featureToDefaultValueMap.remove(feature);
			} catch (Exception e) {
				//
			}
		}
	}

	/**
	 * Reassign default values that are defined statically or dynamically for this class
	 */
	protected void reassignDefaultValues() {
		defaultValueManager.assignDefaultValues(this);
	}
	
	protected EStructuralFeature getFeatureWithOverridenDefaultValue(
			int featureID) {
		Map featureToDefaultValueMap = getFeatureToDefaultValueMap();
		if(!featureToDefaultValueMap.isEmpty()) {
			EStructuralFeature feature = eClass().getEStructuralFeature(
					featureID);
			if(feature != null && featureToDefaultValueMap.containsKey(feature)) {
				return feature;
			}
		}
		return null;
	}
	
	protected boolean isFeatureWithOverridenDefaultValueSet(
			EStructuralFeature feature) {
		Object defaultValue = getDefaultValue(feature);
		Object value = eGet(feature, false);
		if(feature.isMany()) {
			return value != null && !((Collection) value).isEmpty()
			&& value != defaultValue;
		} else {
			return defaultValue == null ? value != null : !defaultValue
					.equals(value);
		}
		
//		// Always return true so the feature (with default value overriden)
//		// will be saved regardless that its value is the default value or not.
//		//
//		return true;
	}
	
	
	//=======================================================
	// EDataObject methods
	//=======================================================
	
	public Type getType() {
		return Type.getInstance(eClass());
	}
	
	public IModelObject getContainer() {
		return (IModelObject) eContainer();
	}

	public List<Property> getInstanceProperties() {
		List<Property> list = new ArrayList<Property>();
		for (EStructuralFeature feature : eClass().getEAllStructuralFeatures()) {
			list.add(new Property(feature));
		}
		return list;
	}
	
	public List getList(int propertyIndex) {
		EStructuralFeature feature = eClass().getEStructuralFeature(propertyIndex);
		Object obj = eGet(feature);
		return obj instanceof List ? (List) obj : null;
	}
	
	public void set(int propertyIndex, Object value) {
		EStructuralFeature feature = eClass().getEStructuralFeature(propertyIndex);
		eSet(feature, value);
	}	
	
	public static class ExtendObject {
		
		public boolean handleOppostie(OppositeFeature oFeature) {
			return false;
		}
		
		public Object getOppositeFeatureValue(OppositeFeature feature) {
			return null;
		}
		
	}
	
	//-2: unknown
	//-1: no debug
	//0: 		all
	//1: (0000,0000,0000,0001)
	//2: (0000,0000,0000,0010)
	private static int epfDebugIx = -2;
	private static int getEpfDebugIx() {
		if (epfDebugIx == -2) {
			epfDebugIx = -1;
			String[] appArgs = Platform.getApplicationArgs();
			if (appArgs == null) {
				return epfDebugIx;
			}
			epfDebugIx = 0;
			try {
				for (int i = 0; i < appArgs.length; i++) {
					String str = appArgs[i].toLowerCase();
					if (str.startsWith("-epfdebug")) {
						epfDebugIx = Integer.parseInt(str.substring(9));
						break;
					}
				}
			} catch (Throwable e) {
			}
		}

		return epfDebugIx;
	}
	
	public static boolean epfDebug(int debugIndex) {
		int ix = getEpfDebugIx();
		if (ix < 0) {
			return false;
		}
		if (ix == 0) {
			return true;
		}
		if ((ix & debugIndex) > 0) {
			return true;
		}		
		return false;
	}
	
}