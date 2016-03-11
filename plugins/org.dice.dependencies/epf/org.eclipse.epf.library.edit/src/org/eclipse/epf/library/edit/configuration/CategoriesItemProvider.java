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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The base item provider adapter class for the standard categories in
 * a configuration.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class CategoriesItemProvider extends ItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IConfigurable, ILibraryItemProvider {

	protected List children;

	private String label;

	private Object parent;

	protected String[] categoryPkgPath;

	protected IFilter filter;

	private Object image;

	protected IFilter uncategorizedFilter;

	protected String uncategorizedLabel;

	protected IFilter categorizedFilter;

	protected Object uncategorizedImage;

	protected MethodConfiguration methodConfig;

	private UncategorizedItemProvider uncategorizedItemProvider;

	/**
	 * Creates a new instance.
	 */
	public CategoriesItemProvider(AdapterFactory adapterFactory,
			MethodConfiguration methodConfig, String name, Object image,
			String[] categoryPkgPath) {
		super(adapterFactory);
		// methodConfig.eAdapters().add(this);
		this.methodConfig = methodConfig;
		label = name;
		this.categoryPkgPath = categoryPkgPath;
		this.image = image;
	}

	protected UncategorizedItemProvider createUncategorizedItemProvider() {
		if (uncategorizedItemProvider == null) {
			uncategorizedItemProvider = new UncategorizedItemProvider(
					adapterFactory, methodConfig, uncategorizedFilter,
					uncategorizedLabel, uncategorizedImage);
		}
		return uncategorizedItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();
		} else {
			children.clear();
		}
		// MethodConfiguration methodConfig = (MethodConfiguration) target;
		for (Iterator iter = methodConfig.getMethodPluginSelection().iterator(); iter
				.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) iter.next();
			ContentPackage pkg = UmaUtil.findContentPackage(plugin,
					categoryPkgPath);
			if (pkg != null /*
							 * &&
							 * methodConfig.getMethodPackageSelection().contains(pkg)
							 */) {
				for (Iterator iterator = pkg.getContentElements().iterator(); iterator
						.hasNext();) {
					ContentCategory element = (ContentCategory) iterator.next();
					if (categorizedFilter.accept(element)
							&& (filter != null ? filter.accept(element) : true)) {
						children.add(element);
					}
				}
			}
		}

		// set parent
		//
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object adapter = (TngAdapterFactory.INSTANCE)
					.getConfigurationView_ComposedAdapterFactory().adapt(
							iter.next(), ITreeItemContentProvider.class);
			// Object adapter =
			// (TngAdapterFactory.INSTANCE).getItemsFilter_AdapterFactory(filter).adapt(iter.next(),
			// ITreeItemContentProvider.class);
			if (adapter instanceof ILibraryItemProvider) {
				((ILibraryItemProvider) adapter).setParent(this);
			}
			// This is need forcing to set the IConfigurator to Childrens
			if (adapter instanceof IConfigurable) {
				((IConfigurable) adapter).setFilter(filter);
			}
		}

		if (uncategorizedFilter != null) {
			children.add(createUncategorizedItemProvider());
		}

		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getNewChildDescriptors(java.lang.Object,
	 *      org.eclipse.emf.edit.domain.EditingDomain, java.lang.Object)
	 */
	public Collection getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		// return super.getNewChildDescriptors(target, editingDomain, sibling);
		return super.getNewChildDescriptors(methodConfig, editingDomain,
				sibling);
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

	public void setCategorizedFilter(IFilter filter) {
		categorizedFilter = filter;
	}

	public void setUncategorizedFilter(IFilter filter) {
		uncategorizedFilter = filter;
	}

	public void setUncategorizedLabel(String label) {
		uncategorizedLabel = label;
	}

	public void setUncategorizedImage(Object img) {
		uncategorizedImage = img;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		return super.getChildrenFeatures(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		if (uncategorizedItemProvider != null) {
			uncategorizedItemProvider.dispose();
		}
		super.dispose();
	}
}
