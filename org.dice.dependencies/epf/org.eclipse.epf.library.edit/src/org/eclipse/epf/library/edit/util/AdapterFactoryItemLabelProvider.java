//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.util;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;

/**
 * @author Phong Nguyen Le
 * @since 1.5
 *
 */
public class AdapterFactoryItemLabelProvider extends ItemLabelProvider {
	private AdapterFactory adapterFactory;

	public AdapterFactoryItemLabelProvider(AdapterFactory adapterFactory) {
		super();
		this.adapterFactory = adapterFactory;
	}
	
	@Override
	public Object getImage(Object object) {
	    // Get the adapter from the factory.
		//
		IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory
				.adapt(object, IItemLabelProvider.class);

		return itemLabelProvider != null ? itemLabelProvider.getImage(object)
				: super.getImage(object);
	}
	
	@Override
	public String getText(Object object) {
	    // Get the adapter from the factory.
		//
		IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory
				.adapt(object, IItemLabelProvider.class);

		return itemLabelProvider != null ? itemLabelProvider.getText(object)
				: super.getText(object);
	}
}
