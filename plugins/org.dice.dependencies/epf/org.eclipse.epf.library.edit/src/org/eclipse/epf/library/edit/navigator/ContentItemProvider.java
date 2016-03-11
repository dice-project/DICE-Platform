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
package org.eclipse.epf.library.edit.navigator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IGroupContainer;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.category.StandardCategoriesItemProvider;
import org.eclipse.epf.library.edit.element.IElementItemProvider;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for the "Method Content" folder in the Library
 * view.
 * <p>
 * This class will be renamed as "MethodContentItemProvider" in EPF M5.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ContentItemProvider extends ItemProviderAdapter implements
		IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource,
		IElementItemProvider, IGroupContainer {

	protected MethodPlugin plugin;

	protected ModelStructure modelStruct;

	protected Map groupItemProviderMap;

	protected ArrayList children;

	protected ContentPackage coreContentPkg;

	/**
	 * Creates a new instance.
	 */
	public ContentItemProvider(AdapterFactory adapterFactory,
			MethodPlugin plugin, ModelStructure modelStruct) {
		super(adapterFactory);
		this.plugin = plugin;
		this.modelStruct = modelStruct;
	}
	
	public ContentPackage getCoreContentPackage() {
		return coreContentPkg;
	}
	
	public Notifier getTarget() {
		return coreContentPkg;
	}

	public Collection getChildren(Object object) {
		if (children == null) {
			children = new ArrayList();
			groupItemProviderMap = new HashMap();
			String name;
			ILibraryItemProvider child;

			// find core content package
			ContentPackage contentPkg = UmaUtil.findContentPackage(plugin,
					modelStruct.coreContentPath);
			coreContentPkg = contentPkg;
			if (contentPkg != null) {
				name = LibraryEditPlugin.INSTANCE
						.getString("_UI_MethodContent_group"); //$NON-NLS-1$
				child = new MethodPackagesItemProvider(adapterFactory,
						contentPkg, name);
				child.setParent(this);
				children.add(child);
				groupItemProviderMap.put(name, child);

				// contentPkg = UmaUtil.findContentPackage(plugin,
				// modelStruct.disciplineDefinitionPath);
				// if(contentPkg != null) {
				// name =
				// LibraryEditPlugin.INSTANCE.getString("_UI_Disciplines_group");
				// child = new DisciplineCategoriesItemProvider(adapterFactory,
				// contentPkg, name);
				// child.setParent(this);
				// children.add(child);
				// groupItemProviderMap.put(name, child);
				// }
				//	        
				// contentPkg = UmaUtil.findContentPackage(plugin,
				// modelStruct.domainPath);
				// if(contentPkg != null) {
				// name =
				// LibraryEditPlugin.INSTANCE.getString("_UI_Domains_group");
				// child = new DomainsItemProvider(adapterFactory, contentPkg,
				// name);
				// child.setParent(this);
				// children.add(child);
				// groupItemProviderMap.put(name, child);
				// }
				//	
				// contentPkg = UmaUtil.findContentPackage(plugin,
				// modelStruct.workProductTypePath);
				// if(contentPkg != null) {
				// name =
				// LibraryEditPlugin.INSTANCE.getString("_UI_WorkProductTypes_group");
				// child = new WorkProductTypesItemProvider(adapterFactory,
				// contentPkg, name);
				// child.setParent(this);
				// children.add(child);
				// groupItemProviderMap.put(name, child);
				// }
				//	        
				// contentPkg = UmaUtil.findContentPackage(plugin,
				// modelStruct.roleSetPath);
				// if(contentPkg != null) {
				// name =
				// LibraryEditPlugin.INSTANCE.getString("_UI_Role_Sets_group");
				// child = new RoleSetsItemProvider(adapterFactory, contentPkg,
				// name);
				// child.setParent(this);
				// children.add(child);
				// groupItemProviderMap.put(name, child);
				// }
				//	        
				// contentPkg = UmaUtil.findContentPackage(plugin,
				// modelStruct.toolPath);
				// if(contentPkg != null) {
				// name =
				// LibraryEditPlugin.INSTANCE.getString("_UI_Tools_group");
				// child = new ToolsItemProvider(adapterFactory, contentPkg,
				// name);
				// child.setParent(this);
				// children.add(child);
				// groupItemProviderMap.put(name, child);
				// }

				// create the standard categories folder
				contentPkg = UmaUtil.findContentPackage(plugin,
						modelStruct.standardCategoryPath);
				if (contentPkg != null) {
					name = LibraryEditPlugin.INSTANCE
							.getString("_UI_Standard_Categories_group"); //$NON-NLS-1$
					child = new StandardCategoriesItemProvider(adapterFactory,
							contentPkg, name);
					child.setParent(this);
					children.add(child);
					groupItemProviderMap.put(name, child);
				}

				// create the custome categories folder
				// contentPkg = UmaUtil.findContentPackage(plugin,
				// modelStruct.customCategoryPath);
				// if(contentPkg != null) {
				// name =
				// LibraryEditPlugin.INSTANCE.getString("_UI_Custom_Categories_group");
				// child = new CustomCategoriesItemProvider(adapterFactory,
				// contentPkg, name);
				// child.setParent(this);
				// children.add(child);
				// groupItemProviderMap.put(name, child);
				// }

				CustomCategory rootCustomCategory = TngUtil
						.getRootCustomCategory(plugin);
				if (rootCustomCategory != null) {
					boolean notify = rootCustomCategory.eDeliver();
					try {
						rootCustomCategory.eSetDeliver(false);
						rootCustomCategory.setName(LibraryEditPlugin.INSTANCE
								.getString("_UI_Custom_Categories_group")); //$NON-NLS-1$
					} finally {
						rootCustomCategory.eSetDeliver(notify);
					}
					children.add(rootCustomCategory);
				}

				// IConfigurable adapter = (IConfigurable)
				// TngUtil.getBestAdapterFactory(adapterFactory).adapt(contentPkg,
				// ITreeItemContentProvider.class);
				// adapter.setLabel(LibraryEditPlugin.INSTANCE.getString("_UI_MethodContent_group"));
				// children.add(contentPkg);
			}
		}

		return children;
	}

	public Object getImage(Object object) {
		return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Content"); //$NON-NLS-1$
	}

	public String getText(Object object) {
		return LibraryEditPlugin.INSTANCE.getString("_UI_Content_group"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IGroupContainer#getGroupItemProvider(java.lang.String)
	 */
	public Object getGroupItemProvider(String name) {
		return groupItemProviderMap != null ? groupItemProviderMap.get(name)
				: null;
	}

	public Collection getNewChildDescriptors(Object object,
			EditingDomain editingDomain, Object sibling) {
		return Collections.EMPTY_LIST;
	}

	public Object getParent(Object object) {
		return plugin;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		if (groupItemProviderMap != null) {
			for (Iterator iter = groupItemProviderMap.values().iterator(); iter
					.hasNext();) {
				Object adapter = iter.next();
				if (adapter instanceof IDisposable) {
					((IDisposable) adapter).dispose();
				}
			}
			groupItemProviderMap.clear();
			groupItemProviderMap = null;
		}
		if(children != null) {
			children.clear();
		}
		plugin = null;
		coreContentPkg = null;
		
		super.dispose();
	}

}