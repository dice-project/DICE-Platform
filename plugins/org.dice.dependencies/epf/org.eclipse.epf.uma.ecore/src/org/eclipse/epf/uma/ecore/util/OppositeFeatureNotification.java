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

import org.eclipse.emf.common.notify.impl.NotificationImpl;

/**
 * Notification associated with an opposite feature.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class OppositeFeatureNotification extends NotificationImpl {

	private OppositeFeature oppositeFeature;

	private Object notifier;

	/**
	 * @param eventType
	 *            the type of event
	 * @param oppositeFeature
	 *            the opposite feature associated with this notification
	 * @param oldValue
	 *            the old value
	 * @param newValue
	 *            the new value
	 * @param position
	 *            the positition at which the change occurred
	 * @param wasSet
	 */
	public OppositeFeatureNotification(Object notifier, int eventType,
			OppositeFeature oppositeFeature, Object oldValue, Object newValue,
			int position, boolean wasSet) {
		super(eventType, oldValue, newValue, position, wasSet);
		this.notifier = notifier;
		this.oppositeFeature = oppositeFeature;
	}

	/**
	 * Gets the opposite feature that is associated with this notification.
	 * 
	 * @return the opposite feature associated with this notification
	 */
	public OppositeFeature getOppsiteFeature() {
		return oppositeFeature;
	}

	/**
	 * @see org.eclipse.emf.common.notify.impl.NotificationImpl#getNotifier()
	 */
	public Object getNotifier() {
		return notifier;
	}

}
