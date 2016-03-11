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
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for a method plug-in in the method element
 * selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class MethodPluginItemProvider extends
		org.eclipse.epf.uma.provider.MethodPluginItemProvider implements
		IConfigurable {
	private IFilter filter;

	/**
	 * Creates a new instance.
	 */
	public MethodPluginItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public Collection getChildren(Object object) {
		List col = new ArrayList();

		if (filter instanceof ICategoryFilter) {
			col.addAll(getCategoryItemProvider(object).getChildren(object));
			return col;
		} else if (filter instanceof IContentFilter) {
			return getContent(object);
		} else if (filter instanceof IAllFilter) {
			return getAllContentElements(object);
		} else if (filter instanceof ICustomFilter) {
			return getCustomCategories(object);
		} else
			return super.getChildren(object);
		// TODO: filter
	}

	private Collection getCustomCategories(Object object) {
		CustomCategory rootCustomCategory = TngUtil
				.getRootCustomCategory((MethodPlugin) object);
		ITreeItemContentProvider adapter = (ITreeItemContentProvider) adapterFactory
				.adapt(rootCustomCategory, ITreeItemContentProvider.class);
		((IConfigurable) adapter).setFilter(filter);
		return adapter.getChildren(rootCustomCategory);
	}

	private ItemProviderAdapter getCategoryItemProvider(Object object) {
		ContentPackage pkg = UmaUtil.findContentPackage((MethodPlugin) object,
				((ICategoryFilter) filter).getCategoryPackagePath());
		ContentCategoryItemProvider contentCategoryItemProvider = new ContentCategoryItemProvider(
				adapterFactory, pkg, ""); //$NON-NLS-1$
		contentCategoryItemProvider.setFilter(filter);
		contentCategoryItemProvider.setParent(object);
		return contentCategoryItemProvider;
	}

	private Collection getContent(Object object) {
		ContentPackage pkg = UmaUtil.findContentPackage((MethodPlugin) object,
				((IContentFilter) filter).getContentPackagePath());
		ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
				.adapt(pkg, ITreeItemContentProvider.class);
		((IConfigurable) adapter).setFilter(filter);
		return adapter.getChildren(pkg);
	}

	// private ItemProviderAdapter getContentElementsItemProvider(Object object)
	// {
	// ContentPackage pkg = UmaUtil.findContentPackage((MethodPlugin)object,
	// ModelStructure.DEFAULT.coreContentPath);
	// ContentElementsItemProvider contentElementsItemProvider = new
	// ContentElementsItemProvider(adapterFactory, pkg);
	// contentElementsItemProvider.setFilter(filter);
	// return contentElementsItemProvider;
	// }

	private Collection getAllContentElements(Object object) {
		List list = new ArrayList();

		String[] coreContentPath = ModelStructure.DEFAULT.coreContentPath;
		ContentPackage corePkg = UmaUtil.findContentPackage(
				(MethodPlugin) object, coreContentPath);
		ItemProviderAdapter contentAdapter = (ItemProviderAdapter) adapterFactory
				.adapt(corePkg, ITreeItemContentProvider.class);
		((IConfigurable) contentAdapter).setFilter(filter);
		list.addAll(contentAdapter.getChildren(corePkg));

		String[][] categoryPaths = new String[][] {
				ModelStructure.DEFAULT.domainPath,
				ModelStructure.DEFAULT.disciplineDefinitionPath,
				ModelStructure.DEFAULT.roleSetPath,
				ModelStructure.DEFAULT.workProductTypePath,
				ModelStructure.DEFAULT.toolPath };

		for (int i = 0; i < categoryPaths.length; i++) {
			String[] path = categoryPaths[i];
			ContentPackage pkg = UmaUtil.findContentPackage(
					(MethodPlugin) object, path);
			ContentCategoriesGroupItemProvider contentCategoriesGroupItemProvider = new ContentCategoriesGroupItemProvider(
					adapterFactory, pkg, getName(path));
			contentCategoriesGroupItemProvider.setFilter(filter);
			contentCategoriesGroupItemProvider.setImage(getImage(path));
			contentCategoriesGroupItemProvider.setParent(object);
			list.add(contentCategoriesGroupItemProvider);
		}

		CustomCategory rootCustomCategory = TngUtil
				.getRootCustomCategory((MethodPlugin) object);
		if (rootCustomCategory != null) {
			list.add(rootCustomCategory);
		}

		// String name =
		// LibraryEditPlugin.INSTANCE.getString("_UI_Processes_group");
		// //$NON-NLS-1$
		ProcessesItemProvider processItemProvider = new ProcessesItemProvider(
				adapterFactory, (MethodPlugin) object);
		processItemProvider.setFilter(filter);
		processItemProvider.setParent(object);
		list.add(processItemProvider);

		for (Iterator iter1 = list.iterator(); iter1.hasNext();) {
			Object child = iter1.next();
			if (!filter.accept(child))
				iter1.remove();
		}
		return list;
	}

	// private Collection getProcesses(Object object) {
	// List list = new ArrayList();
	// // String name =
	// // LibraryEditPlugin.INSTANCE.getString("_UI_Processes_group");
	// // //$NON-NLS-1$
	// ProcessesItemProvider processItemProvider = new ProcessesItemProvider(
	// adapterFactory, (MethodPlugin) object);
	// processItemProvider.setFilter(filter);
	// list.add(processItemProvider);
	// return list;
	// }

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	public void setLabel(String label) {
	}

	public void setParent(Object parent) {
	}

	private Object getImage(String[] path) {
		if (path == ModelStructure.DEFAULT.disciplineDefinitionPath) {
			return LibraryEditPlugin.INSTANCE
					.getImage("full/obj16/Disciplines"); //$NON-NLS-1$
		} else if (path == ModelStructure.DEFAULT.domainPath) {
			return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Domains"); //$NON-NLS-1$
		} else if (path == ModelStructure.DEFAULT.workProductTypePath) {
			return LibraryEditPlugin.INSTANCE
					.getImage("full/obj16/WorkProductTypes"); //$NON-NLS-1$
		} else if (path == ModelStructure.DEFAULT.roleSetPath) {
			return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Roles"); //$NON-NLS-1$
		} else if (path == ModelStructure.DEFAULT.toolPath) {
			return LibraryEditPlugin.INSTANCE.getImage("full/obj16/Tools"); //$NON-NLS-1$
		}
		return null;

	}

	private String getName(String[] path) {
		if (path == ModelStructure.DEFAULT.disciplineDefinitionPath) {
			return LibraryEditPlugin.INSTANCE
					.getString("_UI_Disciplines_group"); //$NON-NLS-1$
		} else if (path == ModelStructure.DEFAULT.domainPath) {
			return LibraryEditPlugin.INSTANCE.getString("_UI_Domains_group"); //$NON-NLS-1$

		} else if (path == ModelStructure.DEFAULT.workProductTypePath) {
			return LibraryEditPlugin.INSTANCE
					.getString("_UI_WorkProductTypes_group"); //$NON-NLS-1$
		} else if (path == ModelStructure.DEFAULT.roleSetPath) {
			return LibraryEditPlugin.INSTANCE.getString("_UI_Role_Sets_group"); //$NON-NLS-1$
		} else if (path == ModelStructure.DEFAULT.toolPath) {
			return LibraryEditPlugin.INSTANCE.getString("_UI_Tools_group"); //$NON-NLS-1$
		}
		return null;
	}

}
