/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.authoring.ui.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IViewerNotification;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.library.edit.navigator.ConfigPageCategoriesItemProvider;
import org.eclipse.epf.library.edit.navigator.ContentItemProvider;
import org.eclipse.epf.library.edit.navigator.MethodPackagesItemProvider;
import org.eclipse.epf.library.edit.navigator.PluginUIPackagesItemProvider;
import org.eclipse.epf.library.edit.navigator.ProcessesItemProvider;
import org.eclipse.epf.library.edit.navigator.ConfigContentPackageItemProvider.LeafElementsItemProvider;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

public class ConfigPackageContentProvider extends AdapterFactoryContentProvider {

	// there is a problem in the passed in AdapterFactory
	// it can't find the correct parent from the child
	// need to build a map of child to it's parent if the child is a UI
	// folder
	// clear the map when the input is set
	Map<Object, Object> childUIParentMap = new HashMap<Object, Object>();

	List<Object> uiFolders = new ArrayList<Object>();

	public List<Object> getUIElements() {
		return uiFolders;
	}

	/**
	 * Create an instance
	 * @param adapterFactory
	 */
	public ConfigPackageContentProvider(//ConfigurationFactory configFactory,
			AdapterFactory adapterFactory/* , boolean showMethodModelOnly */) {
		super(adapterFactory);
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		Object[] items = getValidItems(parentElement, super
				.getChildren(parentElement));
		return items;
	}
		
	public LeafElementsItemProvider getLeafElementsNode(Object parentElement) {
		if (! (parentElement instanceof ContentPackage)) {
			return null;
		}
		Object[] children = getChildren(parentElement);
		if (children == null || children.length == 0) {
			return null;
		}
		return children[0] instanceof LeafElementsItemProvider ? (LeafElementsItemProvider) children[0] : null;
	}	

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {

		Object[] items = getValidItems(inputElement, super
				.getElements(inputElement));
		return items;
	}

	private boolean isUIFolder(Object e) {
		return (e instanceof ContentItemProvider
				|| e instanceof ProcessesItemProvider || e instanceof MethodPackagesItemProvider ||
				e instanceof PluginUIPackagesItemProvider || e instanceof ConfigPageCategoriesItemProvider
				|| e instanceof LeafElementsItemProvider
		);
	}

	public Object getUITargetElement(Object e) {
		if (e instanceof CustomCategory) {
			return ((CustomCategory) e).eContainer();
		} else if (e instanceof MethodElement) {
			return e;
		} else if (e instanceof ContentItemProvider) {
			return ((ContentItemProvider) e).getParent(null);
		} else if (e instanceof ProcessesItemProvider) {
			return ((ProcessesItemProvider) e).getParent(null);
		} else if (e instanceof ItemProviderAdapter) {
			Object target = ((ItemProviderAdapter) e).getTarget();
			if (target != null && target instanceof MethodElement) {
				return target;
			}
		}

		return null;
	}

	private Object[] getValidItems(Object parent, Object[] elements) {
		if (elements == null || elements.length == 0) {
			return elements;
		}

		List<Object> pkgs = new ArrayList<Object>();
		for (int i = 0; i < elements.length; i++) {
			Object e = LibraryUtil.unwrap(elements[i]);
			boolean uiFolder = isUIFolder(e);
			if (uiFolder || e instanceof MethodPackage
					|| e instanceof MethodPlugin) {
				pkgs.add(e);

				if (uiFolder) {
					uiFolders.add(e);
				}

				// if the parent is a UI folder, map the child to the parent
				// so that we can get the parent later
				// if ( !(parent instanceof MethodElement) )
				{
					childUIParentMap.put(e, parent);
				}
			} else {
			}
		}

		return pkgs.toArray();
	}

	/**
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		if (childUIParentMap.containsKey(element)) {
			return childUIParentMap.get(element);
		} else {
			return super.getParent(element);
		}
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
			final ContainerCheckedTreeViewer ctrl = ((ContainerCheckedTreeViewer) super.viewer);
			if (element != null && (vnt.getEventType() == Notification.ADD
					/*|| vnt.getEventType() == Notification.SET*/)) {

				SafeUpdateController.syncExec(new Runnable() {
					public void run() {
						ctrl.setChecked(element, true);
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
		uiFolders.clear();
		childUIParentMap.clear();
	}

}
