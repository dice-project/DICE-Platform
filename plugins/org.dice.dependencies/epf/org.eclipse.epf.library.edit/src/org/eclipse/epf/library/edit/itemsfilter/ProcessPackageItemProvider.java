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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;

/**
 * The item provider adapter for a process package in the method element
 * selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ProcessPackageItemProvider extends
		org.eclipse.epf.library.edit.navigator.ProcessPackageItemProvider implements
		IConfigurable {

	private IFilter filter;

	//private Object parent;

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 */
	public ProcessPackageItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = getChildrenIncludingEmptyObjects(object);
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			if (isEmptyPackage(iter.next())) {
				iter.remove();
			}
		}
		return children;
	}

	private Collection getChildrenIncludingEmptyObjects(Object object) {
		ArrayList col = new ArrayList();
		Collection children = super.getChildren(object);
		if (filter != null) {
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				Object element = iter.next();
				Object child = element;
				if (element instanceof ProcessComponent) {
					child = ((ProcessComponent) element).getProcess();
				}
				if (!filter.accept(child)) {
					iter.remove();
				} else {
					col.add(child);
				}
			}
		}
		return col;
	}

	/**
	 * @param object
	 * @return
	 */
	private boolean isEmptyPackage(Object object) {
		if (object instanceof ProcessComponent
				|| !(object instanceof ProcessPackage))
			return false;
		Collection children = this.getChildrenIncludingEmptyObjects(object);
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			if (!isEmptyPackage(iter.next())) {
				return false;
			}
		}
		return true;
	}

	// private boolean anyProcessComponent(Object parentProcesPackage) {
	// List list = ((ProcessPackage) parentProcesPackage).getProcessElements();
	// for (Iterator itor = list.iterator(); itor.hasNext();) {
	// Object obj = itor.next();
	// if (obj instanceof ProcessComponent) {
	// if (filter.accept(obj))
	// return true;
	// }
	// }
	// return false;
	// }

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	public void setLabel(String label) {
	}

	public void setParent(Object parent) {
		//this.parent = parent;
	}

}
