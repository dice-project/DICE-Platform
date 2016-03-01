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
package org.eclipse.epf.authoring.ui.properties;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.authoring.ui.filters.DescriptorProcessFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CompositeRole;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The composite role - role section
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class CompositeRoleRoleSection extends OBSRelationSection {
	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#createControls(org.eclipse.swt.widgets.Composite, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
	}

	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#initContentProvider()
	 */
	protected void initContentProvider() {
		contentProvider = new AdapterFactoryContentProvider(getAdapterFactory()) {
			public Object[] getElements(Object object) {
				element = getElement();
				return getFilteredList(
						((CompositeRole) element).getAggregatedRoles())
						.toArray();
			}
		};

		viewer.setContentProvider(contentProvider);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#init()
	 */
	protected void init() {
		super.init();
		element = (CompositeRole) element;

		String title = PropertiesResources.CompositeRole_Role_SectionTitle;
		String desc = PropertiesResources.CompositeRole_Role_SectionDescription;
		String table1 = PropertiesResources.CompositeRole_Role_Table1;
		setTabData(title, desc, table1); 
		showAddFromProcessButton = true;
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof CompositeRole) {
				super.refresh();

				element = (CompositeRole) getElement();
				viewer.refresh();
			}
		} catch (Exception ex) {
			logger.logError(
					"Error refreshing Composite role - role sectin ", ex); //$NON-NLS-1$
		}
	}

	/**
	 * Add composite roles 
	 * @param items
	 * 		List of roles
	 */
	private void add(List items) {
		// update the model
		if (items != null) {
			for (Iterator itor = items.iterator(); itor.hasNext();) {
				Object obj = itor.next();
				if (obj instanceof Role) {
					actionMgr.doAction(IActionManager.ADD,
							(CompositeRole) element, UmaPackage.eINSTANCE
									.getCompositeRole_AggregatedRoles(),
							(Role) obj, -1);
				} else if (obj instanceof RoleDescriptor) {
					Role role = ((RoleDescriptor) obj).getRole();
					if (role != null)
						actionMgr.doAction(IActionManager.ADD,
								(CompositeRole) element, UmaPackage.eINSTANCE
										.getCompositeRole_AggregatedRoles(),
								role, -1);
					else {
						MessageFormat mf = new MessageFormat(
								PropertiesResources.Process_CompositeRoleAssignError); 
						Object[] args = { ((RoleDescriptor) obj).getName(),
								((CompositeRole) element).getName() };
						AuthoringUIPlugin
								.getDefault()
								.getMsgDialog()
								.displayInfo(
										PropertiesResources.Process_AssignmentInfoTitle, mf.format(args)); 
					}
				}
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#remove(java.util.ArrayList)
	 */
	protected void remove(ArrayList rmItems) {
		// update the model
		if (!rmItems.isEmpty()) {
			for (Iterator itor = rmItems.iterator(); itor.hasNext();) {
				Role role = (Role) itor.next();
				actionMgr.doAction(IActionManager.REMOVE,
						(CompositeRole) element, UmaPackage.eINSTANCE
								.getCompositeRole_AggregatedRoles(), role, -1);
			}
		}
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#openAddDialog()
	 */
	protected void openAddDialog() {
		List existingElements = ((CompositeRole) element).getAggregatedRoles();
		ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), getFilter(), element,
				FilterConstants.ROLES, existingElements);

		fd.setBlockOnOpen(true);
		fd.setTitle(FilterConstants.ROLES);
		fd.open();
		add(fd.getSelectedItems());

	}

	private List getExistingElements() {
		return null;
	}

	
	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#openAddFromProcessDialog()
	 */
	protected void openAddFromProcessDialog() {
		String tabName = FilterConstants.ROLE_DESCRIPTORS;
		List existingElements = getExistingElements();
		Process process = (Process) getProcess(element);
		IFilter descriptorFilter = getDescriptorFilter();
		if (descriptorFilter != null && process != null) {
			ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell(),
					descriptorFilter, process, tabName, existingElements);
			fd.setBlockOnOpen(true);
			fd.setTitle(FilterConstants.ROLE_DESCRIPTORS);
			fd.open();
			add(fd.getSelectedItems());
		}
	}

	/**
	 * Get parent Find parent until it's activity.
	 * 
	 * @param element
	 * @return
	 * 		process object
	 */
	protected Object getProcess(BreakdownElement element) {
		AdapterFactory aFactory = TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
				element, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(element);
		if (parent instanceof Process) {
			return parent;

		} else {
			return getProcess((BreakdownElement) parent);
		}
	}

	/**
	 * Return descriptor process filter
	 */
	protected IFilter getDescriptorFilter() {
		return new DescriptorProcessFilter(getConfiguration()) {
			protected boolean childAccept(Object obj) {
				if (obj instanceof Activity) {
					// List list = ((Activity)obj).getBreakdownElements();
					List list = new ArrayList();
					getActivitiesInScope(TngAdapterFactory.INSTANCE
							.getOBS_ComposedAdapterFactory(), element, list);
					if (list.contains(obj))
						return true;
					else
						return false;
				}
				if (obj instanceof CompositeRole) {
					if (obj.equals(element))
						return false;
				}
				if (obj instanceof RoleDescriptor)
					return true;
				return false;
			}
		};
	}

}