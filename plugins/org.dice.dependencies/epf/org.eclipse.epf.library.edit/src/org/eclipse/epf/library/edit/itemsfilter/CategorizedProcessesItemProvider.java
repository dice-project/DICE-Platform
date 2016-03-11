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
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;

/**
 * The item provider adapter for the "Capability Patterns" and "Delivery
 * Processes" folders in the method element selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class CategorizedProcessesItemProvider extends ItemProviderAdapter
		implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IConfigurable {
	private String label;

	// private String[] pkgPath;
	private IFilter filter;

	private Object image;

	// private MethodPlugin plugin;
	private MethodPackage pkg;

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 */
	public CategorizedProcessesItemProvider(AdapterFactory adapterFactory,
			MethodPackage pkg, String name, Object image) {
		super(adapterFactory);
		label = name;
		this.image = image;
		this.pkg = pkg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = new ArrayList();
		for (Iterator iterator = pkg.getChildPackages().iterator(); iterator
				.hasNext();) {
			Object element = iterator.next();
			Object child = element;
			if (element instanceof ProcessComponent) {
				child = ((ProcessComponent) element).getProcess();
			}
			if (filter.accept(child)) {
				if (!isEmptyPackage(child)) {
					children.add(child);
				}
			}
		}
		return children;
	}

	private Collection childPackages(Object object) {
		Collection children = ((ProcessPackage) object).getChildPackages();
		List newList = new ArrayList();
		newList.addAll(children);
		if (filter != null) {
			for (Iterator iter = newList.iterator(); iter.hasNext();) {
				if (!filter.accept(iter.next())) {
					iter.remove();
				}
			}
		}
		return newList;
	}

	/**
	 * @param object
	 * @return
	 */
	private boolean isEmptyPackage(Object object) {
		if (object instanceof ProcessComponent || object instanceof Process)
			return false;
		Collection children = this.childPackages(object);
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			if (!isEmptyPackage(iter.next())) {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getNewChildDescriptors(java.lang.Object,
	 *      org.eclipse.emf.edit.domain.EditingDomain, java.lang.Object)
	 */
	public Collection getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		return super.getNewChildDescriptors(target, editingDomain, sibling);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		return image;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		if (label != null)
			return label;
		return super.getText(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.IConfigurable#setFilter(com.ibm.library.edit.IFilter)
	 */
	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.IConfigurable#setLabel(java.lang.String)
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	public void setParent(Object parent) {
	}

}
