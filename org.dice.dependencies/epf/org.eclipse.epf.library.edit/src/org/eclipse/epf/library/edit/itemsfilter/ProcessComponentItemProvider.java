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
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.IStatefulItemProvider;

/**
 * The item provider adapter for a process component in the method element
 * selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ProcessComponentItemProvider extends
		org.eclipse.epf.library.edit.process.ProcessComponentItemProvider implements
		IConfigurable, IStatefulItemProvider {

	private IFilter filter;

	public ProcessComponentItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	public void setLabel(String label) {
	}

	public void setParent(Object parent) {
	}

	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (!filter.accept(child))
				iter.remove();
		}
		return children;
	}

}
