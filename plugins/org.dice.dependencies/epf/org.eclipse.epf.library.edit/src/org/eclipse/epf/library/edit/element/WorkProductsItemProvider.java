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

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Outcome;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for the "Work Products" folder in the Library view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class WorkProductsItemProvider extends
		TransientContentPackageItemProvider {

	/**
	 * Creates a new instance.
	 */
	public WorkProductsItemProvider(AdapterFactory adapterFactory,
			ContentPackage contentPkg) {
		super(adapterFactory, contentPkg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getContentPackage_ContentElements());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_WorkProducts_group"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/WorkProducts"); //$NON-NLS-1$
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
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createArtifact()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createDeliverable()));

		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getContentPackage_ContentElements(), UmaFactory.eINSTANCE
				.createOutcome()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.tng.element.TransientContentPackageItemProvider#acceptAsChild(java.lang.Object)
	 */
	protected boolean acceptAsChild(Object obj) {
		if (!super.acceptAsChild(obj))
			return false;
		return obj instanceof WorkProduct;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#setDefaultName(java.lang.Object)
	 */
	public void setDefaultName(Object obj) {
		String baseName = null;
		if (obj instanceof Artifact) {
			baseName = LibraryEditConstants.NEW_ARTIFACT;
		} else if (obj instanceof Deliverable) {
			baseName = LibraryEditConstants.NEW_DELIVERABLE;
		} else if (obj instanceof Outcome) {
			baseName = LibraryEditConstants.NEW_OUTCOME;
		} else if (obj instanceof WorkProduct) {
			baseName = LibraryEditConstants.NEW_WORKPRODUCT;
		}
		if (baseName != null) {
			TngUtil.setDefaultName(((ContentPackage) target)
					.getContentElements(), (MethodElement) obj, baseName);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.element.TransientContentPackageItemProvider#newChildAdded(org.eclipse.emf.common.notify.Notification)
	 */
	protected void childrenChanged(Notification msg) {
		TngUtil.refreshUncategorizedWorkProductsItemProviders(UmaUtil
				.getMethodPlugin((EObject) target), msg);
	}

}
