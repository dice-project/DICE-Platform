//------------------------------------------------------------------------------
// Copyright (c) 2005, 2012 IBM Corporation and others.
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
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.provider.UmaEditPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for the "Roles" folder in the Library view.
 * 
 * @author Weiping Lu
 * @since 1.5.1.5
 */
public class UdtElementsItemProvider extends TransientContentPackageItemProvider {

	/**
	 * Creates a new instance.
	 */
	public UdtElementsItemProvider(AdapterFactory adapterFactory,
			ContentPackage contentPkg) {
		super(adapterFactory, contentPkg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	synchronized public Collection getChildrenFeatures(Object object) {
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
		return LibraryEditPlugin.INSTANCE.getString("_UI_UdtElements_group"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		return overlayImage(object,  UmaEditPlugin.INSTANCE.getImage(
		"full/obj16/UdtNode")); //$NON-NLS-1$
//		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Practices"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		LibraryEditUtil.getInstance().createUserDefinedTypeContextMenuOnGuidanceNode(newChildDescriptors);	
	}

	protected boolean acceptAsChild(Object obj) {
		if (!super.acceptAsChild(obj))
			return false;
		if (! (obj instanceof Practice)) {
			return false;
		}
		return PracticePropUtil.getPracticePropUtil().isUdtType((Practice) obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#setDefaultName(java.lang.Object)
	 */
	public void setDefaultName(Object obj) {
		String baseName = null;
		if (obj instanceof Practice) {
			baseName = GuidancesItemProvider.getDefaultNameForPractice((Practice) obj);
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
		TngUtil.refreshUncategorizedRolesItemProvider(UmaUtil
				.getMethodPlugin((EObject) target), msg);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#setFilter(com.ibm.library.edit.IFilter)
	 */
	public void setFilter(IFilter filter) {
		super.setFilter(filter);
	}

}
