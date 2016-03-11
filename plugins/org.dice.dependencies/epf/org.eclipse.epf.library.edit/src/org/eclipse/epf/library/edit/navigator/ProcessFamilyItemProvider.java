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

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.util.TngUtil;

/**
 * The item provider adapter for the process family in the Library view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 * @deprecated
 */
public class ProcessFamilyItemProvider extends
		org.eclipse.epf.uma.provider.ProcessFamilyItemProvider {

	/**
	 * Creates a new instance.
	 */
	public ProcessFamilyItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		EObject lib = (EObject) super.getParent(object);
		if (lib == null)
			return null;
		MethodLibraryItemProvider itemProvider = (MethodLibraryItemProvider) TngUtil
				.getAdapter(lib, MethodLibraryItemProvider.class);
		return itemProvider != null ? itemProvider.getProcessFamilies() : null;
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
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_ProcessFamily_type")); //$NON-NLS-1$
	}

}
