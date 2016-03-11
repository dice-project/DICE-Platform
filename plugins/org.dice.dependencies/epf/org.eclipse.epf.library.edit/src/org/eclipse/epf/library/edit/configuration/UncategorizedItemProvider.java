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
package org.eclipse.epf.library.edit.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.uma.MethodConfiguration;

/**
 * The item provider adapter for the uncategorized folders and elements in the
 * Configuration view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class UncategorizedItemProvider extends ItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IConfigurable {

	protected List children;

	protected MethodConfiguration methodConfig;

	private IFilter filter;

	private String label;

	private IFilter uncategorizedFilter;

	private Object image;

	/**
	 * Creates a new instance.
	 */
	public UncategorizedItemProvider(AdapterFactory adapterFactory,
			MethodConfiguration methodConfig, IFilter filter, String label,
			Object image) {
		super(adapterFactory);
		this.methodConfig = methodConfig;
		this.uncategorizedFilter = filter;
		this.label = label;
		this.image = image;
	}

	protected MethodConfigurationElementList createMethodConfigurationElementList(
			List filterList) {
		return new MethodConfigurationElementList(methodConfig, filterList);
	}

	protected List createFilterList() {
		ArrayList filterList = new ArrayList();
		if (uncategorizedFilter != null) {
			filterList.add(uncategorizedFilter);
		}
		if (filter != null) {
			filterList.add(filter);
		}
		return filterList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		return createMethodConfigurationElementList(createFilterList())
				.getList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object object) {
		// Always return true to avoid expensive call to getChildren() that walk
		// thru the
		// whole library to find all the tasks that is not assigned to any
		// discipline.
		//
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		if (label != null)
			return label;
		return LibraryEditPlugin.INSTANCE.getString("_UI_Uncategorized_text"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getNewChildDescriptors(java.lang.Object,
	 *      org.eclipse.emf.edit.domain.EditingDomain, java.lang.Object)
	 */
	public Collection getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		if (image != null)
			return image;
		return super.getImage(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setFilter(com.ibm.library.edit.IFilter)
	 */
	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setLabel(java.lang.String)
	 */
	public void setLabel(String label) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setParent(java.lang.Object)
	 */
	public void setParent(Object parent) {
	}

}
