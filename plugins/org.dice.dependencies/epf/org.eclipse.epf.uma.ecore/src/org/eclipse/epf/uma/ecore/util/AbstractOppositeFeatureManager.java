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
package org.eclipse.epf.uma.ecore.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * 
 * @author Phong Nguyen Le
 * @since 1.5
 *
 */
public abstract class AbstractOppositeFeatureManager {
	protected Map<Class<?>, Set<OppositeFeature>> classOppositeFeaturesMap = new HashMap<Class<?>, Set<OppositeFeature>>();

	/**
	 * Maps EStructuralFeature objects to OppositeFeature objects.
	 */
	protected Map<EStructuralFeature, OppositeFeature> featureOppositeFeatureMap = new HashMap<EStructuralFeature, OppositeFeature>();

	/**
	 * Gets the opposite feature for the given feature.
	 * 
	 * @param feature
	 *            an EMF <code>EStructuralFeature</code> feature
	 * @return an opposite feature that is based on the given feature
	 */
	public OppositeFeature getOppositeFeature(
			EStructuralFeature feature) {
		return (OppositeFeature) featureOppositeFeatureMap.get(feature);
	}

	/**
	 * Registers the given opposite feature.
	 * 
	 * @param oppositeFeature
	 *            the opposite feature to register
	 */
	public void registerOppositeFeature(
			OppositeFeature oppositeFeature) {
		Class<?> cls = oppositeFeature.getOwnerClass();
		Set<OppositeFeature> features = classOppositeFeaturesMap.get(cls);
		if (features == null) {
			features = new HashSet<OppositeFeature>();
			classOppositeFeaturesMap.put(cls, features);
		}
		features.add(oppositeFeature);

		featureOppositeFeatureMap.put(oppositeFeature.getTargetFeature(),
				oppositeFeature);
	}
	
	private ArrayList<OppositeFeature> predefinedOppositeFeatures;

	protected AbstractOppositeFeatureManager() {
		registerPredefinedOppositeFeatures();
	}
	
	/**
	 * Registers the predefined opposite features.
	 */
	private void registerPredefinedOppositeFeatures() {
		predefinedOppositeFeatures = new ArrayList<OppositeFeature>();
		Field[] fields = getClass().getFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			int mod = field.getModifiers();
			if (Modifier.isPublic(mod) && Modifier.isStatic(mod)
					&& Modifier.isFinal(mod)
					&& field.getType() == OppositeFeature.class) {
				try {
					predefinedOppositeFeatures.add((OppositeFeature) field.get(this));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		for (OppositeFeature feature : predefinedOppositeFeatures) {
			registerOppositeFeature(feature);
		}
	}

	/**
	 * Gets the predefined opposite features.
	 * 
	 * @return the predefined opposite features
	 */
	public Collection<OppositeFeature> getPredefinedOppositeFeatures() {
		return predefinedOppositeFeatures;
	}

	/**
	 * Gets the value of an opposite feature.
	 * 
	 * @param feature
	 *            an opposite feature
	 * @return the value for the specified opposite feature
	 */
	public Object getOppositeFeatureValue(EObject eObject, OppositeFeature feature) {
		OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(eObject);
		if(adapter == null) {
			throw new IllegalArgumentException("Object is not managed by this manager.");
		}
		return adapter.getOppositeFeatureValue(feature);
	}

	/**
	 * Gets all the opposite features associated with this model object.
	 * 
	 * @return a collection of opposite features
	 */
	public Collection<OppositeFeature> getOppositeFeatures(EObject eObject) {
		OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(eObject);
		if(adapter == null) {
			throw new IllegalArgumentException("Object is not managed by this manager.");
		}
		return adapter.getOppositeFeatures();
	}
	
	public void manage(EObject eObject) {
		OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(eObject);
		if(adapter == null) {
			adapter = createOppositeFeatureAdapter();
			eObject.eAdapters().add(adapter);
		}
	}
	
	protected OppositeFeatureAdapter createOppositeFeatureAdapter() {
		return new OppositeFeatureAdapter(); 
	}
	
	public void release(EObject eObject) {
		OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(eObject);
		if(adapter != null) {
			eObject.eAdapters().remove(adapter);
		}
	}
	
	private OppositeFeatureAdapter getOppositeFeatureAdapter(EObject eObject) {
		for (Adapter adapter : new ArrayList<Adapter>(eObject.eAdapters())) {
			if(adapter instanceof OppositeFeatureAdapter) {
				return (OppositeFeatureAdapter) adapter;
			}
		}
		return null;
	}
	
	/**
	 * Removes all opposite features registered with the system.
	 */
	public void removeFromAllOppositeFeatures(EObject eObject) {
		// find all features that have opposite features and clear those
		// features. This will remove the references to
		// unloaded object by those opposite features
		//
		for (Iterator iter = eObject.eClass().getEAllReferences().iterator(); iter
				.hasNext();) {
			EReference ref = (EReference) iter.next();
			OppositeFeature oppositeFeature = OppositeFeature.getOppositeFeature(ref);
			if(oppositeFeature != null) {
				if(ref.isMany()) {
					List list = (List) eObject.eGet(ref, false);					
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
					EObject e = (EObject) eObject.eGet(ref, false);
					if(e != null && !e.eIsProxy()) {
						eObject.eSet(ref, null);
					}
				}
			}
		}

	}

	
	protected class OppositeFeatureAdapter extends AdapterImpl {
		private static final boolean DEBUG = false;
		
		/**
		 * A map of entries of OppositeFeature / OppositeFeature's value
		 */
		private Map<OppositeFeature, Object> oppositeFeatureMap;

		private boolean hasOppositeFeature = true;

		
		protected OppositeFeatureAdapter() {
		}
		
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getEventType() == Notification.RESOLVE) {
				return;
			}
			
			Object f = msg.getFeature();
			if (f instanceof EStructuralFeature) {
				EStructuralFeature feature = (EStructuralFeature) f;
				OppositeFeature oppositeFeature = getOppositeFeature(feature);
				if (oppositeFeature != null) {
					EObject oldOtherEnd;
					EObject otherEnd;
					if (oppositeFeature.isMany()) {
						switch (msg.getEventType()) {
						case Notification.SET:
							oldOtherEnd = (EObject) msg.getOldValue();
							if (oppositeFeature.resolveOwner()) {
								oldOtherEnd = (EObject) resolve(oldOtherEnd);
							}
							if (oldOtherEnd != null) {
								OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(oldOtherEnd);
								adapter.oppositeRemove(oppositeFeature, msg
										.getNotifier());
							}
						case Notification.ADD:
							otherEnd = (EObject) msg.getNewValue();
							if (oppositeFeature.resolveOwner()) {
								otherEnd = (EObject) resolve(otherEnd);
								replace(feature, msg.getNewValue(), otherEnd);
							}
							if (otherEnd != null) {
								OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(otherEnd);
								adapter.oppositeAdd(oppositeFeature, msg
										.getNotifier());
							}
							break;
						case Notification.ADD_MANY:
							for (Iterator iter = ((Collection) msg.getNewValue())
									.iterator(); iter.hasNext();) {
								Object obj = iter.next();
								otherEnd = (EObject) obj;
								if (oppositeFeature.resolveOwner()) {
									otherEnd = (EObject) resolve(otherEnd);
									replace(feature, obj, otherEnd);
								}
								OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(otherEnd);
								adapter.oppositeAdd(oppositeFeature, msg
										.getNotifier());
							}
							break;
						case Notification.REMOVE:
							otherEnd = (EObject) msg.getOldValue();
							if (oppositeFeature.resolveOwner()) {
								otherEnd = (EObject) resolve(otherEnd);
							}
							if (otherEnd != null) {
								OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(otherEnd);
								adapter.oppositeRemove(oppositeFeature, msg
										.getNotifier());
							}
							break;
						case Notification.REMOVE_MANY:
							for (Iterator<?> iter = ((Collection) msg.getOldValue())
									.iterator(); iter.hasNext();) {
								otherEnd = (EObject) iter.next();
								if (oppositeFeature.resolveOwner()) {
									otherEnd = (EObject) resolve(otherEnd);
								}
								OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(otherEnd);
								adapter.oppositeRemove(oppositeFeature, msg
										.getNotifier());
							}
							break;
						}
					} else {
						switch (msg.getEventType()) {
						case Notification.ADD_MANY:
							for (Iterator<?> iter = ((Collection) msg.getNewValue())
									.iterator(); iter.hasNext();) {
								Object obj = iter.next();
								otherEnd = (EObject) obj;
								if (oppositeFeature.resolveOwner()) {
									otherEnd = (EObject) resolve(otherEnd);
									replace(feature, obj, otherEnd);
								}
								if (otherEnd != null) {
									OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(otherEnd);
									EObject oldValue = (EObject) adapter
											.getOppositeFeatureMap().get(
													oppositeFeature);
									if (oldValue != null) {
										// remove otherEnd from target feature of
										// oldValue
										((Collection) oldValue
												.eGet((EStructuralFeature) f))
												.remove(otherEnd);
									}
									adapter.getOppositeFeatureMap().put(
											oppositeFeature, msg.getNotifier());
								}
							}
							break;
						case Notification.REMOVE_MANY:
							for (Iterator<?> iter = ((Collection) msg.getOldValue())
									.iterator(); iter.hasNext();) {
								otherEnd = (EObject) iter.next();
								if (oppositeFeature.resolveOwner()) {
									otherEnd = (EObject) resolve(otherEnd);
								}
								OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(otherEnd);
								adapter.getOppositeFeatureMap().put(
										oppositeFeature, null);
							}
							break;
						case Notification.ADD:
							otherEnd = (EObject) msg.getNewValue();
							if (oppositeFeature.resolveOwner()) {
								otherEnd = (EObject) resolve(otherEnd);
								replace(feature, msg.getNewValue(), otherEnd);
							}
							if (otherEnd != null) {
								OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(otherEnd);
								EObject oldValue = (EObject) adapter
										.getOppositeFeatureMap().get(
												oppositeFeature);
								if (oldValue != null) {
									// remove otherEnd from target feature of
									// oldValue
									((Collection) oldValue
											.eGet((EStructuralFeature) f))
											.remove(otherEnd);
								}
								adapter.getOppositeFeatureMap().put(
										oppositeFeature, msg.getNotifier());
							}
							break;
						case Notification.SET:
							otherEnd = (EObject) msg.getNewValue();
							if (oppositeFeature.resolveOwner()) {
								otherEnd = (EObject) resolve(otherEnd);
								replace(feature, msg.getNewValue(), otherEnd);
							}
							if (otherEnd != null) {
								OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(otherEnd);
								EObject oldValue = (EObject) adapter
										.getOppositeFeatureMap().get(
												oppositeFeature);
								if (oldValue != null) {
									// set the target feature of oldValue to null
									oldValue.eSet((EStructuralFeature) f, null);
								}
								adapter.getOppositeFeatureMap().put(
										oppositeFeature, msg.getNotifier());
							}
							else {
								EStructuralFeature targetFeature = (EStructuralFeature) f;
								if(!targetFeature.isMany()) {
									oldOtherEnd = (EObject) msg.getOldValue();
									if(oldOtherEnd != null) {
										OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(oldOtherEnd);
										adapter.getOppositeFeatureMap().put(oppositeFeature, null);
									}
								}
							}
							break;
						case Notification.REMOVE:
							// case Notification.UNSET:
							otherEnd = (EObject) msg.getOldValue();
							if (oppositeFeature.resolveOwner()) {
								otherEnd = (EObject) resolve(otherEnd);
							}
							if (otherEnd != null) {
								OppositeFeatureAdapter adapter = getOppositeFeatureAdapter(otherEnd);
								adapter.getOppositeFeatureMap().put(
										oppositeFeature, null);
							}
							break;

						}
					}
				}
			}
		}
		
		/**
		 * Resolves the given proxy object.
		 * 
		 * @param object
		 *            a proxy object to resolve
		 * @return the resolved object
		 */
		protected Object resolve(Object object) {
			if (object instanceof InternalEObject
					&& ((InternalEObject) object).eIsProxy()) {
				return ((InternalEObject) getTarget()).eResolveProxy((InternalEObject) object);
			}
			return object;
		}

		@Override
		public void setTarget(Notifier newTarget) {
			if(target != null && newTarget != null) {
				throw new IllegalArgumentException("An OppositeFeatureAdapter cannot be associated with 2 targets at the same time.");
			}
			super.setTarget(newTarget);
		}
		
		private Map<OppositeFeature, Object> createOppositeFeatureMap() {
			Map<OppositeFeature, Object> map = new HashMap<OppositeFeature, Object>();
			for (Map.Entry<Class<?>, Set<OppositeFeature>> entry : classOppositeFeaturesMap.entrySet()) {
				Class<?> cls = entry.getKey();
				if (cls.isInstance(getTarget())) {
					for (OppositeFeature oppositeFeature : entry.getValue()) {
						map.put(oppositeFeature, null);
					}
				}
			}
			if (map.isEmpty()) {
				hasOppositeFeature = false;
				return Collections.emptyMap();
			}
			return map;
		}
		
		/**
		 * Gets the opposite feature map.
		 * 
		 * @return a map containing the opposite features mapped to their values
		 */
		protected Map<OppositeFeature, Object> getOppositeFeatureMap() {
			if (oppositeFeatureMap == null && hasOppositeFeature) {
				oppositeFeatureMap = createOppositeFeatureMap();
			}
			if(oppositeFeatureMap == null) {
				return Collections.emptyMap();
			}
			return oppositeFeatureMap;
		}
		
		protected List<?> createOppositeFeatureValueList(EObject eObject, OppositeFeature oppositeFeature) {
			return new OppositeFeatureResolvingEList(eObject, oppositeFeature);
		}

		protected void oppositeAdd(OppositeFeature oppositeFeature, Object object) {
			List list = (List) getOppositeFeatureMap().get(oppositeFeature);
			if (list == null) {
				list = createOppositeFeatureValueList((EObject) getTarget(), oppositeFeature);
				getOppositeFeatureMap().put(oppositeFeature, list);
			}
			if (!list.contains(object)) {
				list.add(object);
			}
		}

		protected void oppositeRemove(OppositeFeature oppositeFeature, Object object) {
			List list = (List) getOppositeFeatureMap().get(oppositeFeature);
			if (list == null) {
				list = createOppositeFeatureValueList((EObject) getTarget(), oppositeFeature);
				getOppositeFeatureMap().put(oppositeFeature, list);
			}
			list.remove(object);
		}

		private void replace(EStructuralFeature feature, Object oldValue, EObject newValue) {
			if (newValue != null && !newValue.eIsProxy() && newValue != oldValue) {
				EObject eObject = (EObject) getTarget();
				boolean notify = eObject.eDeliver();
				try {
					eObject.eSetDeliver(false);
					EcoreUtil.replace(eObject, feature, oldValue, newValue);
				} catch (Exception e) {
					if (DEBUG) {
						CommonPlugin.INSTANCE.log(e);
						e.printStackTrace();
						System.out.println("OppositeFeatureAdapter.replace():"); //$NON-NLS-1$
						System.out.println("  object: " + eObject); //$NON-NLS-1$
						System.out.println("  feature: " + feature); //$NON-NLS-1$
						System.out.println("  proxy: " + oldValue); //$NON-NLS-1$
						System.out.println("  resolved: " + newValue); //$NON-NLS-1$
					}
				} finally {
					eObject.eSetDeliver(notify);
				}
			}

		}

		public Collection<OppositeFeature> getOppositeFeatures() {
			return getOppositeFeatureMap().keySet();
		}
		
		public Object getOppositeFeatureValue(OppositeFeature feature) {
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

			Object resolved = resolve(value);
			if (resolved != value) {
				getOppositeFeatureMap().put(feature, resolved);
				value = resolved;
			}
			return value;
		}

	}
}
