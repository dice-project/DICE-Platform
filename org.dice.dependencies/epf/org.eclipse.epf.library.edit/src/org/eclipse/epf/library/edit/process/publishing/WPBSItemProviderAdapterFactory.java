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
package org.eclipse.epf.library.edit.process.publishing;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.process.PBSItemProviderAdapterFactory;
import org.eclipse.epf.library.edit.util.TngUtil;

/**
 * @author Phong Nguyen Le - Mar 22, 2006
 * @since  1.0
 */
public class WPBSItemProviderAdapterFactory extends
		PBSItemProviderAdapterFactory {

	public WPBSItemProviderAdapterFactory() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.PBSItemProviderAdapterFactory#createActivityAdapter()
	 */
	public Adapter createActivityAdapter() {
		return new WPBSActivityItemProvider(this);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.ItemProviderAdapterFactory#createWorkProductDescriptorAdapter()
	 */
	public Adapter createWorkProductDescriptorAdapter() {
		return new WPBSWorkProductDescriptorItemProvider(this,
				(ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
						.createWorkProductDescriptorAdapter());
	}
	
	public Adapter createCapabilityPatternAdapter() {
		return new WPBSProcessItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createCapabilityPatternAdapter());
	}

	public Adapter createDeliveryProcessAdapter() {
		return new WPBSProcessItemProvider(this, (ItemProviderAdapter) TngUtil.umaItemProviderAdapterFactory
				.createDeliveryProcessAdapter());
	}
}
