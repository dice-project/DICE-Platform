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
package org.eclipse.epf.authoring.ui.dialogs;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.filters.AllFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.itemsfilter.CategorizedProcessesItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.ContentCategoriesGroupItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.itemsfilter.ProcessesItemProvider;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.ui.PlatformUI;


/**
 * The dialog for showing and possibly selecting any variability related to the
 * given element.
 * 
 * @author Jeff Hardy
 * @since 1.1
 */
public class VariabilitySelection {

	private HashSet filterElementList = new HashSet();

	private class VariabilityFilter extends AllFilter {
		public boolean accept(Object obj) {
			Object contentElement = helper.getContentElement();
			if (contentElement != null) {
				if (obj.equals(helper.getContentElement()))
					return false;
			}

			if (helper.getAlreadySelectedList() != null) {
				if (obj instanceof ProcessComponent) {
					if (helper.getAlreadySelectedList().contains(
							((ProcessComponent) obj).getProcess()))
						return false;
				} else {
					if (helper.getAlreadySelectedList().contains(obj))
						return false;
				}
			}

			if (obj instanceof CustomCategory) {
				if (TngUtil.isRootCustomCategory((CustomCategory) obj)) {
					if (((CustomCategory) obj).getCategorizedElements()
							.isEmpty())
						return false;
				}
			}
			if (!helper.matchPattern(obj))
				return false;

			if (obj instanceof MethodPlugin) {
				if (obj instanceof MethodPlugin) {
					if (contentElement != null) {
						if (MethodElementUtil.getAllModels(contentElement)
								.contains(obj))
							return true;
						else
							return false;
					} else {
						return true;
					}
				}
			}
			if (obj instanceof ContentPackage)
				return true;
			if (obj instanceof ContentCategoriesGroupItemProvider) {
				Collection list = ((ContentCategoriesGroupItemProvider) obj)
						.getChildren(obj);
				if (list.isEmpty())
					return false;
				else
					return true;
			}
			if (obj instanceof ProcessesItemProvider) {
				Collection list = ((ProcessesItemProvider) obj)
						.getChildren(obj);
				for (Iterator ior = list.iterator(); ior.hasNext();) {
					Object object = ior.next();
					if (((CategorizedProcessesItemProvider) object)
							.getChildren(object).isEmpty())
						ior.remove();
				}
				if (list.isEmpty())
					return false;
				else
					return true;
			}

			if (childAccept(obj))
				return true;
			return false;
		}

		public boolean childAccept(Object obj) {
			if ((obj instanceof ContentElement)
					&& filterElementList.contains(obj)) {
				return true;
			}
			return false;
		}
	};

	/**
	 * Display variability selection dialog which lists elements and it's
	 * variabilities.
	 * 
	 */
	public VariabilitySelection() {
	}

	/**
	 * It shows dialog box for all the contributors. User is expected to select
	 * one.
	 * 
	 * @param element
	 * @return
	 * 		Object selected by user
	 */
	public Object getSelectedVariability(VariabilityElement element) {
		Set elementSet = new HashSet();
		elementSet.add(element);
		elementSet = AssociationHelper.getVariabilityElements(elementSet, true, true);

		filterElementList.addAll(elementSet);
		if (!elementSet.isEmpty()) {

			for (Iterator itor = elementSet.iterator(); itor.hasNext();) {
				ContentElement contentElement = (ContentElement) itor.next();
				List groupings = null;
				if (contentElement instanceof Discipline) {
					groupings = AssociationHelper
							.getDisciplineGroups((Discipline) contentElement);

				}
				if (contentElement instanceof RoleSet) {
					groupings = AssociationHelper
							.getRoleSetGroups((RoleSet) contentElement);
				}
				
				if (contentElement instanceof CustomCategory) {
					groupings = getAncestors((CustomCategory)contentElement);
				}

				if ((groupings != null) && (!groupings.isEmpty()))
					filterElementList.addAll(groupings);

				if (contentElement instanceof Artifact) {
					filterElementList.addAll(getAllContainerArtifact((Artifact)contentElement));
				}				
			}

			IFilter filter = new VariabilityFilter();
			String[] str = new String[] { FilterConstants.CONFIG_CONTENT_ELEMENT };
			ItemsFilterDialog dlg = new ItemsFilterDialog(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell());
			dlg.setFilter(filter);
			dlg.setTitle(FilterConstants.CONFIG_CONTENT_ELEMENT);
			dlg.setViewerSelectionSingle(true);
			dlg.setTypes(str);
			dlg.setInput(UmaUtil.getMethodLibrary((EObject) element));
			dlg.setContentProvider(new AdapterFactoryContentProvider(
					TngAdapterFactory.INSTANCE
							.getItemsFilter_AdapterFactory(filter)),
							TngAdapterFactory.INSTANCE.getItemsFilter_ComposedAdapterFactory());
			dlg.setBlockOnOpen(true);

			String elementName = element.getName();
			if (element instanceof DescribableElement
					&& PresentationContext.INSTANCE.isShowPresentationNames()) {
				if (((DescribableElement) element).getPresentationName().trim()
						.length() > 0)
					elementName = ((DescribableElement) element)
							.getPresentationName();
			}
			dlg
					.setViewerLabel(AuthoringUIResources.bind(AuthoringUIResources.VariabilitySelection_filterdialog_viewerLabel, (new String[] { elementName })));

			String title = MessageFormat
					.format(
							AuthoringUIResources.VariabilitySelection_filterdialog_title, 
							new String[] { elementName });

			dlg.setTitle(title);
			dlg.setBlockOnOpen(true);
			dlg.open();

			List result = dlg.getSelectedItems();
			if ((result == null) || (result.isEmpty()))
				return null;
			else
				return result.get(0);
		}
		return element;
	}
	
	private List getAllContainerArtifact(Artifact artifact) {
		List result = new ArrayList();
		
		if (artifact.getContainerArtifact() != null) {
			Artifact parent = artifact.getContainerArtifact();
			result.add(parent);
			result.addAll(getAllContainerArtifact(parent));
		}
		
		return result;		
	}
	
	/**
	 * Returns the ancestors for the given Custom Category.
	 * taken from authoring.ui.CustomCategoryAssignPage
	 */
	private List getAncestors(CustomCategory methodObject) {
		List ancestorList = new ArrayList();
		List objList = new ArrayList();
		objList.add(methodObject);
		getAncestors(ancestorList, objList);
		return ancestorList;
	}

	private List getAncestors(List ancestorList, List methodObjectList) {
		if (methodObjectList.isEmpty())
			return ancestorList;

		List allParentCustCats = new ArrayList();

		for (Iterator iter = methodObjectList.iterator(); iter.hasNext();) {
			CustomCategory element = (CustomCategory) iter.next();
			List parentList = AssociationHelper.getCustomCategories(element);
			allParentCustCats.addAll(parentList);
		}

		ancestorList.addAll(methodObjectList);
		List nextCheckList = new ArrayList();
		for (Iterator iter = allParentCustCats.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (!ancestorList.contains(element)) {
				nextCheckList.add(element);
			}
		}

		return getAncestors(ancestorList, nextCheckList);
	}


}
