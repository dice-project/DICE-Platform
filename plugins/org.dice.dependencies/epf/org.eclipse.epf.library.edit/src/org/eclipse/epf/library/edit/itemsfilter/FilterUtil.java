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
/**
 * 
 */
package org.eclipse.epf.library.edit.itemsfilter;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.VariabilityElement;

/**
 * A utility class for managing element filtering in the method element selection
 * dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class FilterUtil {
	
	public static void iterateCollection(Collection children, IFilter filter) {
		for (Iterator iterator = children.iterator(); iterator.hasNext();) {
			Object child = iterator.next();
			if (!filter.accept(child))
				iterator.remove();
		}
	}

	/**
	 * Method for collecting the parents chain (upward) of an breakdownelement.
	 * 
	 * @param AdapterFactory
	 * @param BreakdownElement
	 * @param List
	 */
	public static void getParentsInScope(AdapterFactory adapterFactory,
			BreakdownElement element, java.util.List activityList) {
		ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
				.adapt(element, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(element);
		if (parent instanceof Activity) {
			activityList.add(parent);
			getParentsInScope(adapterFactory, (BreakdownElement) parent,
					activityList);
		}
	}

	/**
	 * Get all children of the activity recursively downwards.
	 * 
	 * @param adapterFactory
	 * @param parent
	 * @param activityList
	 */
	public static void getChildActivitiesInScope(AdapterFactory adapterFactory,
			Activity parent, List activityList) {
		ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
				.adapt(parent, ITreeItemContentProvider.class);

		Object o = adapter.getChildren(parent);
		if (o instanceof List) {
			List children = (List) o;
			for (Iterator itor = children.iterator(); itor.hasNext();) {
				Object obj = itor.next();
				if (obj instanceof Activity) {
					activityList.add(obj);
					getChildActivitiesInScope(adapterFactory, (Activity) obj,
							activityList);
				}
				if (obj instanceof ActivityWrapperItemProvider) {
					Object o1 = ((WrapperItemProvider) obj).getValue();
					activityList.add(o1);
					getChildActivitiesInScope(adapterFactory, (Activity) o1,
							activityList);
				}
			}
		}
	}

	/**
	 * Convenient method for retriving all the contributors of
	 * variabilityelement in a list.
	 * 
	 */
	public static void getContributors(List list, List generalizers) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object e = iterator.next();
			if (e instanceof VariabilityElement) {
				Iterator iter = TngUtil.getContributors((VariabilityElement) e);
				while (iter.hasNext()) {
					generalizers.add(iter.next());
				}
			}
		}
	}

	/**
	 * Convenient method for retriving all the contributors of
	 * variabilityelement in a list.
	 * 
	 */
	public static void getGeneralizers(List list, List generalizers) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object e = iterator.next();
			if (e instanceof VariabilityElement) {
				Iterator iter = TngUtil.getGeneralizers((VariabilityElement) e);
				while (iter.hasNext()) {
					generalizers.add(iter.next());
				}
			}
		}
	}

	/**
	 * Convenient method for retriving all the variability bases of
	 * variabilityelement in a list.
	 * 
	 */
	public static void getVariabilityBase(List list, List variabilitybases) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof VariabilityElement) {
				VariabilityElement e = (VariabilityElement) obj;
				for (; e.getVariabilityBasedOnElement() != null; variabilitybases
						.add(e = (VariabilityElement) e
								.getVariabilityBasedOnElement()))
					;
			}
		}
	}

	/**
	 * Get parent activity for breakdownelement
	 * 
	 * @param brElement
	 * @return
	 */
	public static Object getParentActivity(AdapterFactory adapterFactory,
			BreakdownElement brElement) {
		if (brElement != null) {
			ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
					.adapt(brElement, ITreeItemContentProvider.class);
			Object parent = adapter.getParent(brElement);
			while (!(parent instanceof Activity) && parent != null) {
				brElement = (BreakdownElement) parent;
				adapter = (ItemProviderAdapter) adapterFactory.adapt(brElement,
						ITreeItemContentProvider.class);
				parent = adapter.getParent(brElement);
			}

			return parent;
		}
		return null;
	}

	public static void getSubTree(AdapterFactory adapter, Activity activity,
			List treeList) {
		FilterUtil.getParentsInScope(TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory(), activity, treeList);
		FilterUtil.getChildActivitiesInScope(TngAdapterFactory.INSTANCE
				.getWBS_ComposedAdapterFactory(), activity, treeList);
	}

	public static void getSubTree(AdapterFactory adapter, List list,
			List treeList) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object obj = iterator.next();
			if (obj instanceof Activity) {
				getSubTree(adapter, (Activity) obj, treeList);
			}
		}
	}

}
