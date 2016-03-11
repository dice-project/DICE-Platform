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
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.Outcome;
import org.eclipse.epf.uma.provider.UmaEditPlugin;

/**
 * The item provider adapter for a work product.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class WorkProductItemProvider extends
		org.eclipse.epf.uma.provider.WorkProductItemProvider {

	private ItemProviderAdapter delegateItemProvider;

	/**
	 * Creates a new instance.
	 */
	public WorkProductItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public WorkProductItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory);
		this.delegateItemProvider = delegateItemProvider;
	}

	public List getPropertyDescriptors(Object object) {
		if (delegateItemProvider != null) {
			return delegateItemProvider.getPropertyDescriptors(object);
		}
		return super.getPropertyDescriptors(object);
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
			return itemProvider.getWorkProducts();
		}
		return eObj;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
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
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		if (object instanceof Artifact) {
			return TngUtil.getLabel(object, getString("_UI_Artifact_type")); //$NON-NLS-1$
		} else if (object instanceof Deliverable) {
			return TngUtil.getLabel(object, getString("_UI_Deliverable_type")); //$NON-NLS-1$
		} else if (object instanceof Outcome) {
			return TngUtil.getLabel(object, getString("_UI_Outcome_type")); //$NON-NLS-1$
		}

		return TngUtil.getLabel(object, getString("_UI_WorkProduct_type")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		Object image = TngUtil.getCustomNodeIcon(object);
		if(image != null) {
			return image;
		}
		if (delegateItemProvider != null) {
			return delegateItemProvider.getImage(object);
		}
		if (object instanceof Artifact) {
			return UmaEditPlugin.INSTANCE.getImage("full/obj16/Artifact"); //$NON-NLS-1$
		} else if (object instanceof Deliverable) {
			return UmaEditPlugin.INSTANCE.getImage("full/obj16/Deliverable"); //$NON-NLS-1$
		} else if (object instanceof Outcome) {
			return UmaEditPlugin.INSTANCE.getImage("full/obj16/Outcome"); //$NON-NLS-1$
		} else {
			return super.getImage(object);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.provider.WorkProductItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		TngUtil.refreshParentIfNameChanged(notification, this);
		
		super.notifyChanged(notification);
	}
}
