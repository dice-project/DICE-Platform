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
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IFilter;

/**
 * The item provider adapter for a breakdown structure activity in the
 * method element selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public abstract class BSActivityItemProvider extends
		org.eclipse.epf.library.edit.process.BSActivityItemProvider {

	private IFilter filter;

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 */
	public BSActivityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	protected Collection addInherited(Object object, List myChildren) {
		Collection col = super.addInherited(object, myChildren);
		for (Iterator iter = col.iterator(); iter.hasNext();) {
			Object child = iter.next();
			if (filter != null) {
				if (!filter.accept(child))
					iter.remove();
			}
		}
		return col;
	}

	public void setFilter(IFilter filter) {
		super.setFilter(filter);
		this.filter = filter;
	}

}
