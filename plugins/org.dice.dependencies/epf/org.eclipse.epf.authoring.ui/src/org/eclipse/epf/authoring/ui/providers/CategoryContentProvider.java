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
package org.eclipse.epf.authoring.ui.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.library.edit.FeatureValueWrapperItemProvider;
import org.eclipse.epf.library.edit.TransientGroupItemProvider;
import org.eclipse.epf.library.edit.category.DisciplineCategoriesItemProvider;
import org.eclipse.epf.library.edit.category.DomainsItemProvider;
import org.eclipse.epf.library.edit.category.RoleSetsItemProvider;
import org.eclipse.epf.library.edit.category.ToolsItemProvider;
import org.eclipse.epf.library.edit.category.WorkProductTypesItemProvider;
import org.eclipse.epf.library.edit.navigator.ConfigurationsItemProvider;
import org.eclipse.epf.library.edit.navigator.PluginUIPackagesItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.Viewer;

/**
 * Content provider for Custom Category view of configuration page
 * 
 * @author Jeff Hardy
 */

public class CategoryContentProvider extends AdapterFactoryContentProvider {

	// there is a problem in the passed in AdapterFactory
	// it can't find the correct parent from the child
	// need to build a map of child to it's parent if the child is a UI
	// folder
	// clear the map when the input is set
	private Map<Object, Object> childUIParentMap = new HashMap<Object, Object>();
	
	private MethodConfiguration config;

	/**
	 * Create an instance
	 * @param adapterFactory
	 */
	public CategoryContentProvider(AdapterFactory adapterFactory, MethodConfiguration config) {
		super(adapterFactory);
		this.config = config;
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		Collection<Object> children = new ArrayList<Object>();
		if (parentElement instanceof MethodPlugin) {
			// add standard categories UI folders as appropriate
			DisciplineCategoriesItemProvider disciplines = (DisciplineCategoriesItemProvider)TngUtil.getDisciplineCategoriesItemProvider((MethodPlugin)parentElement);
			if (disciplines != null && !disciplines.getChildren(disciplines).isEmpty()) {
				children.add(disciplines);
			}
			DomainsItemProvider domains = (DomainsItemProvider)TngUtil.getDomainsItemProvider((MethodPlugin)parentElement);
			if (domains != null && !domains.getChildren(domains).isEmpty()) {
				children.add(domains);
			}
			WorkProductTypesItemProvider wpTypes = (WorkProductTypesItemProvider)TngUtil.getWorkProductTypesItemProvider((MethodPlugin)parentElement);
			if (wpTypes != null && !wpTypes.getChildren(wpTypes).isEmpty()) {
				children.add(wpTypes);
			}
			RoleSetsItemProvider roleSets = (RoleSetsItemProvider)TngUtil.getRoleSetsItemProvider((MethodPlugin)parentElement);
			if (roleSets != null && !roleSets.getChildren(roleSets).isEmpty()) {
				children.add(roleSets);
			}
			ToolsItemProvider tools = (ToolsItemProvider)TngUtil.getToolsItemProvider((MethodPlugin)parentElement);
			if (tools != null && !tools.getChildren(tools).isEmpty()) {
				children.add(tools);
			}
			
			// add CustomCategories provider
			// get root CC of plugin
			CustomCategory rootCC = (TngUtil.getRootCustomCategory((MethodPlugin)parentElement));
			if (rootCC != null && !rootCC.getCategorizedElements().isEmpty()) {
				children.add(rootCC);
			}
//			children = unwrapFVWIPs(children);
//			children = filterCategoryElements(parentElement, children);
		} else if (parentElement instanceof PluginUIPackagesItemProvider) {
			children.addAll(Arrays.asList(super.getChildren(parentElement)));
			children = filterPlugin(children);
		} else if (parentElement instanceof ContentCategory) {
			children.addAll(Arrays.asList(super.getChildren(parentElement)));
//			children = unwrapFVWIPs(children);
			children = filterCategoryElements(parentElement, children);
		} else if (parentElement instanceof FeatureValueWrapperItemProvider) {
			children.addAll(Arrays.asList(super.getChildren(parentElement)));
//			children = unwrapFVWIPs(children);
			children = filterCategoryElements(parentElement, children);
		} else if (parentElement instanceof MethodLibrary) {
			children.addAll(Arrays.asList(super.getElements(parentElement)));
			children = filterPlugin(children);
		} else if (parentElement instanceof TransientGroupItemProvider) {
			children.addAll(Arrays.asList(super.getChildren(parentElement)));
		}
		createChildParentMap(parentElement, children);
		return children.toArray();
	}
	
	private void createChildParentMap(Object parent, Collection children) {
		if (children == null || children.size() == 0) {
			return;
		}
		for (Iterator iter = children.iterator();iter.hasNext();) {
			Object child = iter.next();
			// if the parent is a UI folder, map the child to the parent
			// so that we can get the parent later
			// if ( !(parent instanceof MethodElement) )
			{
				childUIParentMap.put(child, parent);
			}
		}
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}
	
	private Collection<Object> filterPlugin(Collection<Object> elements) {
		for (Iterator iter = elements.iterator();iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof MethodPlugin) {
				// filter out plugins that have no CC's
				if (!hasChildren((MethodPlugin)element) || !config.getMethodPluginSelection().contains(element)) {
					iter.remove();
				}
			} else if (element instanceof PluginUIPackagesItemProvider) {
				// filter out plugin packages that have no plugins with CC's
				if (!hasChildren((PluginUIPackagesItemProvider)element)) {
					iter.remove();
				}
			} else if (element instanceof ConfigurationsItemProvider) {
				// remove "Configurations" folder
				iter.remove();
			}
		}
		return elements;
	}
	
	private Collection<Object> filterCategoryElements(Object parent, Collection<Object> elements) {
		for (Iterator iter = elements.iterator();iter.hasNext();) {
			Object element = iter.next();
			element = TngUtil.unwrap(element);
			if (!(element instanceof ContentCategory)) {
				// remove non-ContentCategory
				iter.remove();
			}
		}
		return elements;
	}
	
//	private Collection<Object> unwrapFVWIPs(Collection<Object> elements) {
//		Collection<Object> result = new ArrayList<Object>();
//		for (Iterator iter = elements.iterator();iter.hasNext();) {
//			Object element = iter.next();
//			element = TngUtil.unwrap(element);
//			if (element instanceof CustomCategory) {
//				result.add(getFirstTreeItemForCC((CustomCategory)element));
//			} else if (element instanceof ContentCategory) {
//				result.add(element);
//			} else if (element instanceof TransientCategoryPackageItemProvider) {
//				result.add(element);
//			}
//		}
//		return result;
//	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		if (childUIParentMap.containsKey(element)) {
			return childUIParentMap.get(element);
		} else {
			return null;
			// try to determine parent
//			Object parent = super.getParent(element);
//			if (parent instanceof ContentPackage && 
//					element instanceof ContentCategory) {
//				return LibraryUtil.getMethodPlugin((ContentCategory)element);
//			} else if (parent instanceof CustomCategory) {
//				parent = getFirstTreeItemForCC((CustomCategory)parent);
//			}
//			else if (parent instanceof FeatureValueWrapperItemProvider) {
//				parent = TngUtil.unwrap(parent);
//			}
//			return parent;
		}
	}
	
	public Collection<Object> getTreeItems(Object item) {
		List<Object> result = new ArrayList<Object>();
		// get ITreeContentProvider adapater
		List notifyChangedListeners = TngUtil.getNotifyChangedListeners(adapterFactory, item);
		if (!notifyChangedListeners.isEmpty()) {
			for (Iterator iterator = notifyChangedListeners.iterator(); iterator.hasNext();) {
				Object listener = iterator.next();
				if(listener instanceof ITreeItemContentProvider
						&& TngUtil.unwrap(listener) == item) {
					result.add(listener);
				}
			}
		} else {
			result.add(item);
		}
		return result;
	}


	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		Object[] children = getChildren(element);
		return (children != null && children.length > 0);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		super.inputChanged(viewer, oldInput, newInput);
		childUIParentMap.clear();
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);

		if ((notification instanceof IViewerNotification)) {
			final IViewerNotification vnt = (IViewerNotification) notification;
			final Object element = vnt.getElement();
			final CheckboxTreeViewer ctrl = ((CheckboxTreeViewer) super.viewer);
			if (element != null && (vnt.getEventType() == Notification.ADD
					|| vnt.getEventType() == Notification.SET)) {

				SafeUpdateController.syncExec(new Runnable() {
					public void run() {
						ctrl.refresh();
					}
				});
			}
		}
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#dispose()
	 */
	public void dispose() {
		super.dispose();
		childUIParentMap.clear();
	}
}
