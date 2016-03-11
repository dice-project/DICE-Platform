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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.filters.AllFilter;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ConfigurationHelper;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.itemsfilter.CategorizedProcessesItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.ContentCategoriesGroupItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.itemsfilter.ProcessesItemProvider;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.ui.PlatformUI;

/**
 * The dialog for selecting element to detetmine which element editor to open in
 * case of variability relationship. This will only take effect if user
 * double-clicks from configuration view.
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ContributionSelection {

	private HashSet filterElementList = new HashSet();

	private class ContributionFilter extends AllFilter {
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

			// if(obj instanceof TermDefinition) return false;

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
			if (obj instanceof CustomCategory) {
				return anyCategorizedElementAccepted((CustomCategory) obj,
						filterElementList);
			}
			if ((obj instanceof ContentElement)
					&& filterElementList.contains(obj)) {
				return true;
			}
			if(obj instanceof Process){ 
				if(filterElementList.contains(obj)){
					return true;
				}
			}
			if(obj instanceof ProcessPackage) {
				return checkProcessPkg(obj);
			}
			if(obj instanceof Process) {
				return checkProcessElements(obj);
			}
			return false;
		}
	};
	
	public boolean checkProcessPkg(Object obj){
		
		List list = new ArrayList();
		if(obj instanceof ProcessPackage){
			if(filterElementList.contains(obj))
				return true;
			if(obj instanceof ProcessComponent){
				if(filterElementList.contains(((ProcessComponent)obj).getProcess()))
					return true;
				list = ((ProcessComponent)obj).getProcess().getBreakdownElements();
			}else{
				list = ((ProcessPackage)obj).getChildPackages();
			}
			
		}
		for(Iterator iterator = list.iterator(); iterator.hasNext();)
		{
			Object child = iterator.next();
			if(child instanceof ProcessComponent){
				if(checkProcessPkg(child)) return true;
			}
			if(child instanceof BreakdownElement){
				if(filterElementList.contains(child)) return true;
			}
		}
		return false;
	}
	
	/**
	 * Check any process elements
	 * @param obj
	 * @return
	 * 			Boolean value 
	 */
	public boolean checkProcessElements(Object obj){
		List list = ((Process)obj).getBreakdownElements();
		for(Iterator iterator = list.iterator(); iterator.hasNext();)
		{
			Object child = iterator.next();
			if(child instanceof BreakdownElement){
				if(filterElementList.contains(((BreakdownElement)child)))
						return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether any categorized elements to be accepted.
	 * @param obj
	 * @param list
	 * @return
	 * 		Boolean value which indicates whether any categoriezed elements to be accepted
	 */
	public boolean anyCategorizedElementAccepted(CustomCategory obj,
			HashSet list) {
		if (list.contains(obj))
			return true;
		List elements = obj.getCategorizedElements();
		for (Iterator iterator = elements.iterator(); iterator.hasNext();) {
			Object object = iterator.next();
			if (object instanceof CustomCategory) {
				if (list.contains(object))
					return true;
				anyCategorizedElementAccepted((CustomCategory) object, list);
			}
		}
		return false;
	}


	/**
	 * Display contribution selection dialog which lists elements and it's
	 * contributors.
	 */
	public ContributionSelection() {
	}

	/**
	 * It shows dialog box for all the contributors. User is expected to select
	 * one.
	 * 
	 * @param element
	 * 			Element for which contributor are shown
	 * @return
	 * 			Selected contributor
	 */
	public Object getSelectedContributor(VariabilityElement element) {
		filterElementList = new HashSet();
		// Get the current configuration.
		MethodConfiguration config = LibraryService.getInstance()
				.getCurrentMethodConfiguration();

		// get list of contributors
		List contributors = ConfigurationHelper
				.getContributors(element, config);
		if ((contributors != null) && (!contributors.isEmpty())) {
			// clear the array
			List elementList = new ArrayList();

			// add element and it's contributors
			elementList.add(element);
			elementList.addAll(contributors);
			filterElementList.addAll(elementList);
			for (Iterator itor = elementList.iterator(); itor.hasNext();) {
				Object e = itor.next();
				List groupings = null;
				if (e instanceof Discipline) {
					groupings = AssociationHelper
							.getDisciplineGroups((Discipline) e);

				}
				if (e instanceof RoleSet) {
					groupings = AssociationHelper
							.getRoleSetGroups((RoleSet) e);
				}

				if ((groupings != null) && (!groupings.isEmpty()))
					filterElementList.addAll(groupings);

			}

			IFilter filter = new ContributionFilter();
			// ItemsFilterDialog dlg = new ItemsFilterDialog(PlatformUI
			// .getWorkbench().getActiveWorkbenchWindow().getShell(),
			// filter, null, FilterConstants.CONFIG_CONTENT_ELEMENT);
			// dlg.setViewerSelectionSingle(true);
			String[] str = new String[] { FilterConstants.CONFIG_CONTENT_ELEMENT };
			ConfigurationAddViewsDialog dlg = new ConfigurationAddViewsDialog(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getShell());
			dlg.setFilter(filter);
			dlg.setTitle(FilterConstants.CONFIG_CONTENT_ELEMENT);
			dlg.setViewerSelectionSingle(true);
			dlg.setTypes(str);
			dlg.setInput(UmaUtil.getMethodLibrary((EObject) element));
			dlg.setContentProvider(new AdapterFactoryContentProvider(
					TngAdapterFactory.INSTANCE
							.getItemsFilter_AdapterFactory(filter)),
					TngAdapterFactory.INSTANCE
							.getItemsFilter_ComposedAdapterFactory());
			dlg.setBlockOnOpen(true);

			String title = MessageFormat
					.format(
							AuthoringUIResources.ContributionSelection_filterdialog_title, 
							new String[] { element.getName() });

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

}
