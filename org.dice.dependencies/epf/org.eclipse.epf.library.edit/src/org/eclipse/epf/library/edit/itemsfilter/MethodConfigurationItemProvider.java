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
package org.eclipse.epf.library.edit.itemsfilter;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;

/**
 * The item provider adapter for a method configuration in the method element
 * selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MethodConfigurationItemProvider extends
		org.eclipse.epf.library.edit.configuration.MethodConfigurationItemProvider {

	private IFilter filter;

	public MethodConfigurationItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public void setFilter(IFilter filter) {
		super.setFilter(filter);
		if (filter instanceof IConfigurator) {
			this.filter = filter;
		}
	}

	public Collection getChildren(Object object) {
		children = null;
		Collection col = super.getChildren(object);

		if (filter != null) {
			FilterUtil.iterateCollection(col, filter);
		}
		return col;
	}

}
