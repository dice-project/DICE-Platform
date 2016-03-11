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
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.dialogs.ItemsFilterDialog;
import org.eclipse.epf.authoring.ui.filters.DescriptorProcessFilter;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.itemsfilter.FilterConstants;
import org.eclipse.epf.library.edit.itemsfilter.FilterHelper;
import org.eclipse.epf.library.edit.process.ActivityWrapperItemProvider;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.CompositeRoleWrapperItemProvider;
import org.eclipse.epf.library.edit.process.RoleDescriptorWrapperItemProvider;
import org.eclipse.epf.library.edit.process.command.AddRoleToTeamCommand;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.CompositeRole;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;


/**
 * The team profile - role/composite role section
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class TeamProfileRoleSection extends OBSRelationSection {

	List roleList = null;

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
				List newList = new ArrayList();
				List teamRoles = ((TeamProfile) element).getTeamRoles();
				for (Iterator itor = teamRoles.iterator(); itor.hasNext();) {
					RoleDescriptor roleDesc = (RoleDescriptor) itor.next();
					if ((roleDesc.getSuperActivities() == null)
							|| (roleDesc.getSuperActivities() == null))
						newList.add(roleDesc);
				}

				// return ((TeamProfile)element).getTeamRoles().toArray();
				return getFilteredList(newList).toArray();
			}
		};

		viewer.setContentProvider(contentProvider);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#init()
	 */
	protected void init() {
		super.init();
		element = (TeamProfile) element;

		setTabData(PropertiesResources.TeamProfile_Role_SectionTitle, 
				PropertiesResources.TeamProfile_Role_SectionDescription,
				PropertiesResources.TeamProfile_Role_Table1); 
		showAddFromProcessButton = true;
	}


	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#refresh()
	 */
	public void refresh() {
		try {
			if (getElement() instanceof TeamProfile) {
				super.refresh();
				element = (TeamProfile) getElement();
				viewer.refresh();
			}
		} catch (Exception ex) {
			logger.logError("Error refreshing Team Profile role section ", ex); //$NON-NLS-1$
		}
	}

	private boolean isPartOfTeam(Role role, TeamProfile team) {
		List teamRoles = team.getTeamRoles();
		List roles = new ArrayList();
		for (Iterator itor = teamRoles.iterator(); itor.hasNext();) {
			RoleDescriptor roleDesc = (RoleDescriptor) itor.next();
			roles.add(roleDesc.getRole());
		}

		if (roles.contains(role))
			return true;

		return false;
	}

	private void add(List items) {
		if (items != null) {
			List roles = new ArrayList();

			for (Iterator itor = items.iterator(); itor.hasNext();) {
				Object obj = itor.next();
				if ((obj instanceof Role)
						&& (!isPartOfTeam((Role) obj, (TeamProfile) element))) {
					roles.add((Role) obj);
				}
			}

			if (!roles.isEmpty()) {
				AddRoleToTeamCommand cmd = new AddRoleToTeamCommand(
						(TeamProfile) element, roles);
				actionMgr.execute(cmd);
			}
		}
	}

	private void addFromProcess(List items) {
		// update the model
		if (items != null) {
			for (Iterator itor = items.iterator(); itor.hasNext();) {
				Object obj = itor.next();
				if (obj instanceof CompositeRole) {
					CompositeRole compRole = ProcessUtil
							.createCompositeRole((CompositeRole) obj);
					actionMgr.doAction(IActionManager.ADD, element,
							UmaPackage.eINSTANCE.getTeamProfile_TeamRoles(),
							compRole, -1);
					actionMgr.doAction(IActionManager.ADD, element,
							UmaPackage.eINSTANCE.getTeamProfile_TeamRoles(),
							obj, -1);
				} else if (obj instanceof RoleDescriptor) {
					Role role = ((RoleDescriptor) obj).getRole();
					if (role != null) {
						if (!isPartOfTeam(role, (TeamProfile) element)) {
							RoleDescriptor roleDesc = ProcessUtil
									.createRoleDescriptor(role);
							actionMgr.doAction(IActionManager.ADD, element,
									UmaPackage.eINSTANCE
											.getTeamProfile_TeamRoles(),
									roleDesc, -1);
						}
						actionMgr
								.doAction(IActionManager.ADD, element,
										UmaPackage.eINSTANCE
												.getTeamProfile_TeamRoles(),
										obj, -1);

						// fire Notification
						AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
								.getOBS_ComposedAdapterFactory();
						Activity parent = (Activity) getParentActivity(element);
						ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
								.adapt(parent, ITreeItemContentProvider.class);
						Collection children = adapter.getChildren(parent);

						fireNotification(children, (RoleDescriptor) obj);

					} else {
						MessageFormat mf = new MessageFormat(
								PropertiesResources.Process_TeamAssignError); 
						Object[] args = { ((RoleDescriptor) obj).getName(),
								((TeamProfile) element).getName() };
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

	private void fireNotification(Collection children, RoleDescriptor roleDesc) {
		for (Iterator itor = children.iterator(); itor.hasNext();) {
			Object obj = itor.next();
			if (obj instanceof Activity) {
				AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
						.getOBS_ComposedAdapterFactory();
				ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
						.adapt((Activity) obj, ITreeItemContentProvider.class);
				children = adapter.getChildren((Activity) obj);
				fireNotification(children, roleDesc);
			}
			if (obj instanceof ActivityWrapperItemProvider) {
				Object o1 = ((WrapperItemProvider) obj).getValue();
				ActivityWrapperItemProvider activityAdapter = (ActivityWrapperItemProvider) obj;
				fireNotification(activityAdapter.getChildren(o1), roleDesc);
			}
			if (obj instanceof RoleDescriptorWrapperItemProvider) {
				RoleDescriptorWrapperItemProvider provider = (RoleDescriptorWrapperItemProvider) obj;
				if (((RoleDescriptor) provider.getValue()).getRole().equals(
						roleDesc.getRole())) {
					provider.fireNotifyChanged(new ViewerNotification(
							new NotificationImpl(Notification.SET, provider,
									provider), provider, false, true));
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
				Object obj = (Object) itor.next();
				if (obj instanceof RoleDescriptor) {
					actionMgr.doAction(IActionManager.REMOVE, element,
							UmaPackage.eINSTANCE.getTeamProfile_TeamRoles(),
							(RoleDescriptor) obj, -1);
				}

				// find matching roleDescriptor in team roles
				List roleDescList = findRoleDescriptor((Object) obj);
				for (Iterator roleDescItor = roleDescList.iterator(); roleDescItor
						.hasNext();) {
					RoleDescriptor roleDesc = (RoleDescriptor) roleDescItor
							.next();
				actionMgr.doAction(IActionManager.REMOVE, element,
						UmaPackage.eINSTANCE.getTeamProfile_TeamRoles(),
						roleDesc, -1);
				}
			}
		}
	}

	private List findRoleDescriptor(Object obj) {
		List teamRoles = ((TeamProfile) element).getTeamRoles();
		List roleDescList = new ArrayList();
		for (Iterator itor = teamRoles.iterator(); itor.hasNext();) {
			Object itorObject = itor.next();
			if (obj instanceof CompositeRole) {
				if (itorObject instanceof CompositeRole) {
					List firstObjectAggRoles = ((CompositeRole) obj)
							.getAggregatedRoles();
					List secondObjectAggRoles = ((CompositeRole) itorObject)
							.getAggregatedRoles();
					if (firstObjectAggRoles.equals(secondObjectAggRoles)) {
						roleDescList.add(itorObject);
					}
				}
			} else if (obj instanceof RoleDescriptor) {
				if ((itorObject instanceof RoleDescriptor)
						&& (!(itorObject instanceof CompositeRole))) {
					Object objRole = ((RoleDescriptor) obj).getRole();
					Object itorObjRole = ((RoleDescriptor) itorObject)
							.getRole();
					if (objRole.equals(itorObjRole)) {
						roleDescList.add(itorObject);
					}
				}
			}
		}

		return roleDescList;
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#openAddDialog()
	 */
	protected void openAddDialog() {
		List existingElements = ProcessUtil
				.getAssociatedElementList(((TeamProfile) element)
						.getTeamRoles());
		ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), getFilter(), element,
				FilterConstants.ROLE_DESCRIPTORS, existingElements);
		fd.setBlockOnOpen(true);
		fd.setTitle(FilterConstants.ROLES);
		String[] str = new String[]{FilterConstants.ROLES};
		fd.setTypes(str);
		fd.open();
		add(fd.getSelectedItems());
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.properties.OBSRelationSection#openAddFromProcessDialog()
	 */
	protected void openAddFromProcessDialog() {
		List existingElements = ((TeamProfile) element).getTeamRoles();
		Process process = (Process) getProcess(element);
		IFilter descriptorFilter = getDescriptorFilter();

		if (descriptorFilter != null && process != null) {
			ItemsFilterDialog fd = new ItemsFilterDialog(PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getShell(),
					descriptorFilter, process,
					FilterConstants.ROLE_DESCRIPTORS, existingElements);
			fd.setBlockOnOpen(true);
			fd.setTitle(FilterConstants.ROLE_DESCRIPTORS);
			fd.open();
			addFromProcess(fd.getSelectedItems());
		}
	}

	/**
	 * Get parent Find parent until it's activity.
	 * 
	 * @param element
	 * @return
	 * 			Process
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
	 * Get descriptor filter
	 */
	protected IFilter getDescriptorFilter() {
		return new DescriptorProcessFilter(getConfiguration()) {
			protected boolean childAccept(Object obj) {
				if (obj instanceof Activity) {
					AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
							.getOBS_ComposedAdapterFactory();
					List parentsList = new ArrayList();
					getActivitiesInScope(adapterFactory, element, parentsList);
					if (parentsList.contains(obj))
						return true;
					if (obj.equals(getParentActivity(element)))
						return true;
					List childList = ((Activity) obj).getBreakdownElements();
					if (childList.contains(getParentActivity(element)))
						return true;
					List list = getAllowableActivities();
					if (list.contains(obj)){
						return true;
//						List belements = ((Activity)obj).getBreakdownElements();
//						if(!belements.isEmpty()){
//							if(containsType(belements, RoleDescriptor.class,
//											getHelper()))
//							return true;
//						}
					}
				}
				if (obj instanceof RoleDescriptor) {
					Object parentActivity = getParentActivity((BreakdownElement) obj);
					Object parent = getParentActivity(element);
					if (parent.equals(parentActivity))
						return true;

					if (parentActivity instanceof Activity) {
						if (getBaseActivities((Activity) parent).contains(
								parentActivity))
							return true;
						if (getAllowableActivities().contains(parentActivity))
							return true;
					}
				}
				if (obj instanceof RoleDescriptorWrapperItemProvider
						|| obj instanceof CompositeRoleWrapperItemProvider) {
					Object object = ((BreakdownElementWrapperItemProvider) obj)
							.getParent(obj);
					if (getParentActivity(element).equals(object))
						return true;
					if (object instanceof Activity
							|| object instanceof ActivityWrapperItemProvider) {
						if (object instanceof ActivityWrapperItemProvider) {
							object = ((ActivityWrapperItemProvider) object)
									.getValue();
						}
						if (getAllowableActivities().contains(object))
							return true;
					}
				}
				if (obj instanceof ActivityWrapperItemProvider) {
					Object parent = ((BreakdownElementWrapperItemProvider) obj)
							.getParent(obj);
					if (getParentActivity(element).equals(parent))
						return true;
					Object object = ((BreakdownElementWrapperItemProvider) obj)
							.getValue();
					if (object instanceof Activity) {
						List list = getAllowableActivities();
						if (list.contains(object)){
							return true;
//							List belements = ((Activity)obj).getBreakdownElements();
//							if(!belements.isEmpty()){
//								if(containsType(belements, RoleDescriptor.class,getHelper()))
//								return true;
//							}
						}
					}
				}
				return false;
			}
		};
	}
	
	
	private List getBaseActivities(Activity activity) {
		List list = new ArrayList();
		while (!activity.getVariabilityType()
				.equals(VariabilityType.NA)) {
			VariabilityElement ve = activity.getVariabilityBasedOnElement();

			if ((ve != null) && (ve instanceof Activity)) {

				list.add(ve);

				activity = (Activity) ve;
			} else {
				break;
			}
		}
		return list;
	}

	/**
	 * Get parent activity for breakdownelement
	 * 
	 * @param brElement
	 * @return
	 * 			Parent activity
	 */
	private Object getParentActivity(BreakdownElement brElement) {
		AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
				.getOBS_ComposedAdapterFactory();
		ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
				.adapt(brElement, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(brElement);
		while (!(parent instanceof Activity)) {
			brElement = (BreakdownElement) parent;
			adapter = (ItemProviderAdapter) adapterFactory.adapt(brElement,
					ITreeItemContentProvider.class);
			parent = adapter.getParent(brElement);
		}

		return parent;
	}

	/**
	 * Get all children of the activity parent recursively.
	 * 
	 * @param adapterFactory
	 * @param parent
	 * @param activityList
	 */
	private void getChildActivitiesInScope(AdapterFactory adapterFactory,
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

	private boolean containsType(List list, Class class1, FilterHelper helper) {
		for(Iterator iterator = list.iterator(); iterator.hasNext();){
			Object obj = iterator.next();
			if(obj instanceof MethodElement 
					&& class1.isInstance(obj)
					&& helper.matcher(obj)) 
				return true;
			if(obj instanceof Activity){
				return containsType(((Activity)obj).getBreakdownElements(),
						class1, helper);
			}
		}
		return false;
	}

	/**
	 * Get all activities in scope
	 * 
	 * @return
	 * 		List of activities in scope
	 */
	private List getAllowableActivities() {
		List list = new ArrayList();
		// parent activity
		Object object = getParentActivity(element);
		if (object instanceof Activity) {
			AdapterFactory adapterFactory = TngAdapterFactory.INSTANCE
					.getOBS_ComposedAdapterFactory();
			// get all child activities in scope
			getChildActivitiesInScope(adapterFactory, (Activity) object, list);
		}
		// get base activities for all activities in scope
		HashSet baseActivties = new HashSet();
		for (Iterator itor = list.iterator(); itor.hasNext();) {
			Object obj = itor.next();
			if (obj instanceof Activity) {
				baseActivties.addAll(getBaseActivities((Activity) obj));
			}
		}

		// return complete list of child activities and it's base activities
		list.addAll(baseActivties);
		return list;
	}
}