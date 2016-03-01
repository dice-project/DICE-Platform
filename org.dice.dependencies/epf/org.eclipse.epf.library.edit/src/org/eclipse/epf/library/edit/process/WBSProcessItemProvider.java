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
package org.eclipse.epf.library.edit.process;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class WBSProcessItemProvider extends WBSActivityItemProvider {

	private ItemProviderAdapter delegateItemProvider;

	public WBSProcessItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory);
		this.delegateItemProvider = delegateItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.ActivityItemProvider#getPropertyDescriptors(java.lang.Object)
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			itemPropertyDescriptors = delegateItemProvider
					.getPropertyDescriptors(object);
		}
		return itemPropertyDescriptors;
	}

}
