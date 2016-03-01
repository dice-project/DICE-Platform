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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.LibraryEditUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.AssociationHelper;

/**
 * The item provider adapter for the "Custom Categories" folder.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class CustomCategoriesItemProvider extends
		TransientCategoryPackageItemProvider {

	/**
	 * Creates a new instance.
	 */
	public CustomCategoriesItemProvider(AdapterFactory adapterFactory,
			ContentPackage target, String name) {
		super(adapterFactory, target, name);
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
				.createCustomCategory()));
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
					.getContentPackage_ContentElements());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#acceptAsChild(java.lang.Object)
	 */
	protected boolean acceptAsChild(Object obj) {
		if (!super.acceptAsChild(obj))
			return false;
		return accept(obj, null);

	}
	
	public static boolean accept(Object obj, MethodConfiguration config) {
		if (obj instanceof CustomCategory) {
			List myCategories = null;
			if (config == null) {
				myCategories = AssociationHelper
						.getCustomCategories((CustomCategory) obj);
			} else {
				myCategories = LibraryEditUtil
						.getInstance()
						.calc0nFeatureValue(
								(CustomCategory) obj,
								AssociationHelper.DescribableElement_CustomCategories,
								config);
			}
			if (myCategories.isEmpty()) {
				return true;
			}

//			if (myCategories.size() == 1) {
//				// root custom category is unde the hidden package and will not
//				// be processed.
//				// need to show sub categories under it
//				CustomCategory c = (CustomCategory) myCategories.get(0);
//				if (TngUtil.isRootCustomCategory(c)) {
//					return true;
//				}
//			}
			
			for (CustomCategory c : (List<CustomCategory>) myCategories) {
				if (! TngUtil.isRootCustomCategory(c)) {
					return false;
				}
			}
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE
				.getImage("full/obj16/CustomCategories"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		return super.getChildren(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#setDefaultName(java.lang.Object)
	 */
	public void setDefaultName(Object obj) {
		if (obj instanceof CustomCategory) {
			TngUtil.setDefaultName(TngUtil.extract(((ContentPackage) target)
					.getContentElements(), CustomCategory.class),
					(MethodElement) obj,
					LibraryEditConstants.NEW_CUSTOM_CATEGORY);
		}
	}

}
