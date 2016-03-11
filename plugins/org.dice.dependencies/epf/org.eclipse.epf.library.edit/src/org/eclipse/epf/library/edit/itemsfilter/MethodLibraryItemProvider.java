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
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.epf.library.edit.FeatureValueWrapperItemProvider;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.ProcessScopeUtil;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;

/**
 * The item provider adapter for a method library in the method element
 * selection dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class MethodLibraryItemProvider extends
		org.eclipse.epf.uma.provider.MethodLibraryItemProvider implements IConfigurable {

	AdapterFactory adapterFactory;

	ContentElement contentElement;

	private IFilter filter;

	// private boolean isAdapterConfigurable;
	// private ArrayList children;
	// private MethodPlugin plugin;
	// private ModelStructure modelStruct;

	/**
	 * Creates a new instance.
	 */
	public MethodLibraryItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		this.adapterFactory = adapterFactory;
	}

	/**
	 * @param adapterFactory
	 */
	public MethodLibraryItemProvider(AdapterFactory adapterFactory,
			Object contentElement) {
		super(adapterFactory);
		this.adapterFactory = adapterFactory;
		this.contentElement = (ContentElement) contentElement;
	}

	public MethodLibraryItemProvider(AdapterFactory adapterFactory,
			IFilter filter) {
		super(adapterFactory);
		this.adapterFactory = adapterFactory;
		this.filter = filter;
		if (adapterFactory instanceof IConfigurable) {
			// isAdapterConfigurable = true;
			((IConfigurable) adapterFactory).setFilter(filter);
		}
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to
	 * deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand},
	 * {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in
	 * {@link #createCommand}. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			// super.getChildrenFeatures(object);
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getMethodLibrary_MethodPlugins());
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getMethodLibrary_PredefinedConfigurations());
		}
		return childrenFeatures;
	}

	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);
		children.addAll(ProcessScopeUtil.getInstance().getScopeInEditdSet());
		for (Iterator iter1 = children.iterator(); iter1.hasNext();) {
			Object child = iter1.next();
			if (filter.accept(child) && child instanceof MethodPlugin) {
				MethodPluginItemProvider p = new MethodPluginItemProvider(
						adapterFactory);
				p.setFilter(filter);
				Collection childs = p.getChildren(child);
				for (Iterator childIter = childs.iterator(); childIter
						.hasNext();) {
					Object childObj = childIter.next();
					if (childObj instanceof ITreeItemContentProvider) {
						if (!checkChildrens(childObj))
							childIter.remove();

					} else {
						if (!filter.accept(childObj)) {
							childIter.remove();
						}
					}
				}
				if (childs.isEmpty())
					iter1.remove();
			} else {
				if (!filter.accept(child))
					iter1.remove();
			}
		}
		return children;
	}

	private boolean checkChildrens(Object object) {
		if (object instanceof FeatureValueWrapperItemProvider) {
			object = ((IWrapperItemProvider) object).getValue();
			if (!filter.accept(object)) {
				return false;
			}
		} else {
			Collection childObjChilds = ((ITreeItemContentProvider) object)
					.getChildren(object);
			for (Iterator childObjIter = childObjChilds.iterator(); childObjIter
					.hasNext();) {
				Object childObjChild = childObjIter.next();
				if (childObjChild instanceof ITreeItemContentProvider) {
					return checkChildrens(childObjChild);
				}
				if (!filter.accept(childObjChild)) {
					childObjIter.remove();
				}
			}
			if (childObjChilds.isEmpty())
				// childIter.remove();
				return false;
		}
		return true;
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.IConfigurable#setParent(java.lang.Object)
	 */
	public void setParent(Object parent) {
	}

}
