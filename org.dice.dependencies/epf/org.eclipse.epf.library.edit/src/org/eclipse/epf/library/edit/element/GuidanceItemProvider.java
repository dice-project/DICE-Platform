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
package org.eclipse.epf.library.edit.element;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.util.TngUtil;

/**
 * The item provider adapter for a guidance in the Library view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class GuidanceItemProvider extends
		org.eclipse.epf.uma.provider.GuidanceItemProvider {

	/**
	 * A map of EClass / ItemProviderAdapter for each type of guidance
	 */
	private static Map itemProviderMap = new HashMap();

	/**
	 * Creates a new instance.
	 */
	public GuidanceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		itemProviderMap.clear();

		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.GuidanceItemProvider#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.DescribableElementItemProvider#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.GuidanceItemProvider#getPropertyDescriptors(java.lang.Object)
	 */
	public List getPropertyDescriptors(Object object) {
		if (object instanceof EObject) {
			return getAdapter((EObject) object).getPropertyDescriptors(object);
		}
		return super.getPropertyDescriptors(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getPropertyDescriptor(java.lang.Object,
	 *      java.lang.Object)
	 */
	public IItemPropertyDescriptor getPropertyDescriptor(Object object,
			Object propertyId) {
		return getAdapter((EObject) object).getPropertyDescriptor(object,
				propertyId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getEditableValue(java.lang.Object)
	 */
	public Object getEditableValue(Object object) {
		return getAdapter((EObject) object).getEditableValue(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.GuidanceItemProvider#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		Object image = TngUtil.getCustomNodeIcon(object);
		if(image != null) {
			return image;
		}
		if (object instanceof EObject) {
			return getAdapter((EObject) object).getImage(object);
		}
		return super.getImage(object);
	}

	public static ItemProviderAdapter getAdapter(EObject eObj) {
		ItemProviderAdapter adapter = (ItemProviderAdapter) itemProviderMap
				.get(eObj.eClass());
		if (adapter == null) {
			adapter = (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
					.createAdapter(eObj);
			itemProviderMap.put(eObj.eClass(), adapter);
		}
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.GuidanceItemProvider#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		EObject eObj = (EObject) super.getParent(object);
		if (eObj == null)
			return null;
		ContentPackageItemProvider itemProvider = (ContentPackageItemProvider) TngUtil
				.getAdapter(eObj, ContentPackageItemProvider.class);
		if (itemProvider != null) {
			return itemProvider.getGuidances();
		}
		return eObj;
	}

	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		return new MethodElementAddCommand((AddCommand) super.createAddCommand(
				domain, owner, feature, collection, index));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.provider.GuidanceItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		TngUtil.refreshParentIfNameChanged(notification, this);
		
		super.notifyChanged(notification);
	}
}
