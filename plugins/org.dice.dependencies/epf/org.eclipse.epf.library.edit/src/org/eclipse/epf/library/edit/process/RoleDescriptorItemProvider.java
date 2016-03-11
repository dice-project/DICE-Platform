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

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.util.ProcessUtil;


public class RoleDescriptorItemProvider extends DescriptorItemProvider {

	/**
	 * @param adapterFactory
	 */
	public RoleDescriptorItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	public Collection getEClasses() {
		return ProcessUtil.getOBSEclasses();
	}

}
