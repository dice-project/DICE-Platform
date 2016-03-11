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
package org.eclipse.epf.library.edit.process.consolidated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.IBreakdownElementWrapperItemProviderFactory;
import org.eclipse.epf.library.edit.util.Comparators;
import org.eclipse.epf.library.edit.util.TngUtil;


/**
 * WorkProductDescriptor Item provider for consolidated view
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class WorkProductDescriptorItemProvider extends
		org.eclipse.epf.library.edit.process.WorkProductDescriptorItemProvider {

	/**
	 * @param adapterFactory
	 * @param delegateItemProvider
	 */
	public WorkProductDescriptorItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#isWrappingNeeded(java.lang.Object)
	 */
	protected boolean isWrappingNeeded(Object object) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getCreateChildText(java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.util.Collection)
	 */
	public String getCreateChildText(Object owner, Object feature,
			Object child, Collection selection) {
		return getFeatureText(feature);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getCreateChildImage(java.lang.Object,
	 *      java.lang.Object, java.lang.Object, java.util.Collection)
	 */
	public Object getCreateChildImage(Object owner, Object feature,
			Object child, Collection selection) {
		Object img = TngUtil.getImage(child);
		return img != null ? img : super.getCreateChildImage(owner, feature, child, selection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createWrapper(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object, int)
	 */
	protected Object createWrapper(EObject object, EStructuralFeature feature,
			Object value, int index) {
		BreakdownElementWrapperItemProvider wrapper = (BreakdownElementWrapperItemProvider) IBreakdownElementWrapperItemProviderFactory.INSTANCE
				.createWrapper(value, object, feature, index, adapterFactory);
		wrapper.setReadOnly(false);
		return wrapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		// switch(notification.getFeatureID(RoleDescriptor.class)) {
		// case UmaPackage.ROLE_DESCRIPTOR__RESPONSIBLE_FOR:
		// refreshChildren(notification);
		// }

		super.notifyChanged(notification);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.DescriptorItemProvider#getAttribute(java.lang.Object, java.lang.String)
	 */
	public String getAttribute(Object object, String property) {
		return super.getAttribute(object, property);
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.WorkProductDescriptorItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object obj) {
		Collection children = super.getChildren(obj);
		List newChildren = new ArrayList();
		newChildren.addAll(children);
		// sort the children
		Collections.sort(newChildren, Comparators.PRESENTATION_NAME_COMPARATOR);

		return newChildren;
	}
}
