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

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.notify.impl.NotifyingListImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

/**
 * A list for resolving multi-valued opposite features.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class OppositeFeatureResolvingEList extends NotifyingListImpl<EObject> {

	private static final long serialVersionUID = 3690198763000051768L;

	private InternalEObject owner;

	private OppositeFeature oppositeFeature;

	/**
	 * Creates a new instance.
	 * 
	 * @param owner
	 *            the owner of an opposite feature
	 * @param oppositeFeature
	 *            an opposite feature
	 */
	public OppositeFeatureResolvingEList(EObject owner,
			OppositeFeature oppositeFeature) {
		super();
		this.owner = (InternalEObject) owner;
		this.oppositeFeature = oppositeFeature;
	}

	/**
	 * @see org.eclipse.emf.common.notify.impl.NotifyingListImpl#getNotifier()
	 */
	public Object getNotifier() {
		return owner;
	}

	protected EObject resolveProxy(EObject eObject) {
		return eObject.eIsProxy() ? owner
				.eResolveProxy((InternalEObject) eObject) : eObject;
	}

	protected NotificationImpl createNotification(int eventType,
			Object oldObject, Object newObject, int index, boolean wasSet) {
		return new OppositeFeatureNotification(owner, eventType,
				oppositeFeature, oldObject, newObject, index, wasSet);
	}

	protected boolean isNotificationRequired() {
		return owner.eNotificationRequired();
	}

	protected EObject resolve(int index, EObject object) {
		if (!(object instanceof EObject))
			return object;
		EObject eObject = (EObject) object;
		EObject resolved = resolveProxy(eObject);
		if (resolved != eObject) {
			Object oldObject = data[index];
			assign(index, validate(index, resolved));

			didSet(index, resolved, (EObject) oldObject);

			// if (isNotificationRequired())
			// {
			// owner.eNotify(createNotification(Notification.RESOLVE, eObject,
			// resolved, index, false));
			// }

			return resolved;
		} else {
			return eObject;
		}

	}

	protected void removeDanglingObjects() {
		for (Iterator<EObject> iter = iterator(); iter.hasNext();) {
			EObject obj = iter.next();
			if (obj.eResource() == null) {
				iter.remove();
			}
		}
	}

	/**
	 * Returns an unmodifiable list that stores the opposite feature values.
	 * 
	 * @return an unmodifiable list
	 */
	public List<?> getUnmodifiableList() {
		removeDanglingObjects();
		return new UnmodifiableEList(size, data);
	}

}
