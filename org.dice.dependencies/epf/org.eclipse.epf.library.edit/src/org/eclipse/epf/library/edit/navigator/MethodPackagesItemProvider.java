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
package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.TransientGroupItemProvider;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;

/**
 * The item provider adapter for the "Content Packages" folder in the Library
 * view.
 * <p>
 * This class will be renamed as "ContentPackagesItemProvider" in EPF M5.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodPackagesItemProvider extends TransientGroupItemProvider {

	/**
	 * Creates a new instance.
	 */
	public MethodPackagesItemProvider(AdapterFactory adapterFactory,
			Notifier parent, String name) {
		super(adapterFactory, parent, name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getMethodPackage_ChildPackages(), UmaFactory.eINSTANCE
				.createContentPackage()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getMethodPackage_ChildPackages());
		}
		return childrenFeatures;
	}

	protected boolean acceptAsChild(Object obj) {
		return obj instanceof ContentPackage;
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/MethodPackages"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification msg) {
		if (msg.getNotifier() == target) {
			int featureId = msg.getFeatureID(ContentPackage.class);
			if (featureId == UmaPackage.CONTENT_PACKAGE__CHILD_PACKAGES) {
				boolean notify = false;
				switch (msg.getEventType()) {
				case Notification.ADD:
					if (msg.getNewValue() instanceof ContentPackage) {
						MethodElementPropUtil propUtil = MethodElementPropUtil.getMethodElementPropUtil();
						ContentPackage pkg = (ContentPackage) msg.getNewValue();
						if (propUtil.ancestorIsSupporting(pkg)) {
							propUtil.setSupporting(pkg, true);
						}
					}					
					
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#setDefaultName(java.lang.Object)
	 */
	public void setDefaultName(Object obj) {
		if (obj instanceof ContentPackage) {
			TngUtil.setDefaultName(
					((ContentPackage) target).getChildPackages(),
					(MethodElement) obj,
					LibraryEditConstants.NEW_CONTENT_PACKAGE); 
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getInterestedFeatureID()
	 */
	public int getInterestedFeatureID() {
		return UmaPackage.CONTENT_PACKAGE__CHILD_PACKAGES;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getInterestedFeatureOwnerClass()
	 */
	public Class getInterestedFeatureOwnerClass() {
		return ContentPackage.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		List children = (List) super.getChildren(object);

		// sorting the content packages
		//
		Collections.sort(children, Comparators.DEFAULT_COMPARATOR);

		// set parent for children
		//
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object child = (Object) iter.next();
			Object adapter = adapterFactory.adapt(child,
					ITreeItemContentProvider.class);
			if (adapter instanceof ILibraryItemProvider) {
				((ILibraryItemProvider) adapter).setParent(object);
			}
		}

		return children;
	}

}
