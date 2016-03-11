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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.TransientGroupItemProvider;
import org.eclipse.epf.library.edit.util.LibraryEditConstants;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.ProcessFamily;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;

/**
 * The item provider adapter for the "Process Families" folder in the Library
 * view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 * @deprecated
 */
public class ProcessFamiliesItemProvider extends TransientGroupItemProvider {

	/**
	 * Creates a new instance.
	 */
	public ProcessFamiliesItemProvider(AdapterFactory adapterFactory,
			MethodLibrary parent, String name) {
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
				.getMethodLibrary_PredefinedConfigurations(),
				UmaFactory.eINSTANCE.createProcessFamily()));
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
					.getMethodLibrary_PredefinedConfigurations());
		}
		return childrenFeatures;
	}

	protected boolean acceptAsChild(Object obj) {
		return obj instanceof ProcessFamily;
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE
				.getImage("full/obj16/ProcessFamilies"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getInterestedFeatureID()
	 */
	public int getInterestedFeatureID() {
		return UmaPackage.METHOD_LIBRARY__PREDEFINED_CONFIGURATIONS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#setDefaultName(java.lang.Object)
	 */
	public void setDefaultName(Object obj) {
		if (obj instanceof ProcessFamily)
			TngUtil.setDefaultName(((MethodLibrary) target)
					.getPredefinedConfigurations(), (MethodElement) obj,
					LibraryEditConstants.NEW_PROCESS_FAMILY); 
		else if (obj instanceof MethodConfiguration) {
			TngUtil.setDefaultName(((MethodLibrary) target)
					.getPredefinedConfigurations(), (MethodElement) obj,
					LibraryEditConstants.NEW_CONFIGURATION); 
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.TransientGroupItemProvider#getInterestedFeatureClass()
	 */
	public Class getInterestedFeatureOwnerClass() {
		return MethodLibrary.class;
	}

}
