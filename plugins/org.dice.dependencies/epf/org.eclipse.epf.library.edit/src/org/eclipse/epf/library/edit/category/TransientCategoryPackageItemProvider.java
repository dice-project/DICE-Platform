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
package org.eclipse.epf.library.edit.category;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.IGroupContainer;
import org.eclipse.epf.library.edit.TransientGroupItemProvider;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.UmaPackage;

/**
 * The item provider adapter for the category folders.
 * <p>
 * This class will be renamed as CategoriesItemProvider in EPF M5.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class TransientCategoryPackageItemProvider extends
		TransientGroupItemProvider implements IGroupContainer {

	protected Map groupItemProviderMap;

	/**
	 * Creates a new instance.
	 */
	public TransientCategoryPackageItemProvider(AdapterFactory adapterFactory,
			Notifier parent, String name) {
		super(adapterFactory, parent, name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#acceptAsChild(java.lang.Object)
	 */
	protected boolean acceptAsChild(Object obj) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		Object uncategorized = createUncategorizedItemProvider();
		if (uncategorized != null) {
			children.add(uncategorized);
		}
		return children;
	}

	/**
	 * @return
	 */
	protected Object createUncategorizedItemProvider() {
		return null;
	}

	public void notifyChanged(Notification msg) {
		if (msg.getNotifier() == target) {
			int featureId = msg.getFeatureID(ContentPackage.class);
			if (featureId == UmaPackage.CONTENT_PACKAGE__CONTENT_ELEMENTS) {
				boolean notify = false;
				switch (msg.getEventType()) {
				case Notification.ADD:
				case Notification.MOVE:
					Object obj = msg.getNewValue();
					notify = acceptAsChild(obj);
					break;
				case Notification.REMOVE:
					obj = msg.getOldValue();
					notify = acceptAsChild(obj);
					break;
				case Notification.ADD_MANY:
					Collection collection = (Collection) msg.getNewValue();
					for_check: for (Iterator iter = collection.iterator(); iter
							.hasNext();) {
						if (acceptAsChild(iter.next())) {
							notify = true;
							break for_check;
						}
					}
					break;
				case Notification.REMOVE_MANY:
					collection = (Collection) msg.getOldValue();
					for_check: for (Iterator iter = collection.iterator(); iter
							.hasNext();) {
						if (acceptAsChild(iter.next())) {
							notify = true;
							break for_check;
						}
					}
					break;
				}
				if (notify) {
					fireNotifyChanged(new ViewerNotification(msg, this, true,
							false));
				}
			}
		}
		super.notifyChanged(msg);
	}

	protected boolean isInherited(ContentCategory category) {
		return acceptAsChild(category)
				&& category.getVariabilityBasedOnElement() != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getInterestedFeatureID()
	 */
	public int getInterestedFeatureID() {
		return UmaPackage.CONTENT_PACKAGE__CONTENT_ELEMENTS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getInterestedFeatureClass()
	 */
	public Class getInterestedFeatureOwnerClass() {
		return ContentPackage.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IGroupContainer#getGroupItemProvider(java.lang.String)
	 */
	public Object getGroupItemProvider(String name) {
		if (groupItemProviderMap == null)
			return null;
		return groupItemProviderMap.get(name);
	}

}