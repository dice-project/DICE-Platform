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
package org.eclipse.epf.library.edit.navigator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.epf.library.edit.util.ModelStructure;

/**
 * Adapter factory for configuration plugin/package selection page
 * @author Shilpa Toraskar
 * @since 1.5
 *
 */
public class ConfigPageItemProviderAdapterFactory extends
		ItemProviderAdapterFactory {

	@Override
	public Adapter createMethodPluginAdapter() {
		ConfigPageMethodPluginItemProvider adapter = new ConfigPageMethodPluginItemProvider(this);
		adapter.setModelStructure(ModelStructure.DEFAULT);
		return adapter;
	}
	
	@Override
	public Adapter createContentPackageAdapter() {
		Adapter adapter = new ConfigContentPackageItemProvider(this);
		// adapters.add(adapter);
		return adapter;
	}
}
