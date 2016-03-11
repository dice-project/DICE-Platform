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
package org.eclipse.epf.library.edit.configuration;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.uma.Practice;

/**
 * The base item provider adapter class for the standard categories in
 * a configuration.
 * 
 * @author Weiping Lu
 * @since 1.5
 */
public class PracticeSubgroupItemProvider extends ItemProviderAdapter implements
		IStructuredItemContentProvider, ITreeItemContentProvider,
		IItemLabelProvider, IConfigurable {

	private String label;
	
	private String prefix;

	private Object parent;
	
	private List children;

	protected IFilter filter;

	private Object image;
	
	private Practice practice;

	/**
	 * Creates a new instance.
	 */
	public PracticeSubgroupItemProvider(AdapterFactory adapterFactory,
			String name, String prefix, Object image, List children, Object parentObject) {
		super(adapterFactory);
		label = name;
		this.prefix = prefix;
		this.image = image;
		this.children = children;
		if (parentObject instanceof Practice) {
			practice = (Practice) parentObject;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		return children == null ? Collections.EMPTY_LIST : children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		if (parent != null)
			return parent;
		if (object instanceof EObject) {
			return super.getParent(object);
		}
		return null;
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
		this.label = label;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IConfigurable#setParent(java.lang.Object)
	 */
	public void setParent(Object parent) {
		this.parent = parent;
	}

	public Practice getPractice() {
		return practice;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPrefix() {
		return prefix;
	}

}
