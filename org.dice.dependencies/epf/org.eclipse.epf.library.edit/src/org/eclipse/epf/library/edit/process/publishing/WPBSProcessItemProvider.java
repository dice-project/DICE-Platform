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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.process.PBSProcessItemProvider;
import org.eclipse.epf.library.edit.util.ProcessUtil;

/**
 * @author Phong Nguyen Le - Mar 22, 2006
 * @since  1.0
 */
public class WPBSProcessItemProvider extends PBSProcessItemProvider {

	/**
	 * @param adapterFactory
	 * @param delegateItemProvider
	 */
	public WPBSProcessItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	public Collection getChildren(Object object) {
		if(isRolledUp()) {
			if(cachedRollupChildren == null) {
				cachedRollupChildren = new ArrayList(super.getChildren(object));
			}
			return cachedRollupChildren;
		}
		else {
			if(cachedChildren == null) {
				cachedChildren = new ArrayList(super.getChildren(object));
			}
			return cachedChildren;
		}
	}

	protected List removeSubartifactsFromChildren(Collection children, boolean unwrap) {
		return ProcessUtil.removeSubartifactsFromChildren(children, unwrap, adapterFactory);
	}

}
