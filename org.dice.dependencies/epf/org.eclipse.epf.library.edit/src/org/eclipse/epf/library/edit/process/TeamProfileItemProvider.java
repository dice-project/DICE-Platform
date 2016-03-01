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
package org.eclipse.epf.library.edit.process;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.command.IActionManager;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.process.command.GenericDropCommand;
import org.eclipse.epf.library.edit.process.command.OBSDragAndDropCommand;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class TeamProfileItemProvider extends BreakdownElementItemProvider {

	// private Collection eClasses;
	// private Collection teamRoleWrappers;
	private GenericDropCommand.ElementAdapter dropAdapter;

	/**
	 * @param adapterFactory
	 */
	public TeamProfileItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
		newChildDescriptors.add(createChildParameter(UmaPackage.eINSTANCE
				.getTeamProfile_SubTeam(), UmaFactory.eINSTANCE
				.createTeamProfile()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();

			childrenFeatures.add(UmaPackage.eINSTANCE.getTeamProfile_SubTeam());
		}

		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#createDropCommand(java.lang.Object,
	 *      java.util.List)
	 */
	public IResourceAwareCommand createDropCommand(Object owner,
			List dropElements) {
		return new GenericDropCommand((TeamProfile) owner, UmaPackage.eINSTANCE
				.getTeamProfile_TeamRoles(), dropElements,
				getDropAdapter(owner));
	}

	private GenericDropCommand.ElementAdapter getDropAdapter(Object owner) {
		BreakdownElementItemProvider adapter = (BreakdownElementItemProvider) adapterFactory
				.adapt(owner, IEditingDomainItemProvider.class);
		return adapter.createDropAdapter();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.WBSActivityItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);

		// add team roles
		//
		IFilter filter = ProcessUtil.getFilter(adapterFactory);
		boolean nullFilter = filter == null;
		TeamProfile teamProfile = (TeamProfile) object;
		for (Iterator iter = teamProfile.getTeamRoles().iterator(); iter
				.hasNext();) {
			RoleDescriptor desc = (RoleDescriptor) iter.next();
			if (desc.getSuperActivities() == null && (nullFilter || filter.accept(desc))) {
				children.add(desc);
			}
		}

		// set parent for the children's item providers
		//
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			ProcessUtil.setParent(iter.next(), object, getRootAdapterFactory());
		}

		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	// public void dispose() {
	// for (Iterator iter = teamRoleWrappers.iterator(); iter.hasNext();) {
	// IWrapperItemProvider wrapper = (IWrapperItemProvider) iter.next();
	// wrapper.dispose();
	// }
	// teamRoleWrappers.clear();
	//		
	// super.dispose();
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(TeamProfile.class)) {
		case UmaPackage.TEAM_PROFILE__SUB_TEAM:
			int eventType = notification.getEventType();
			if (eventType == Notification.ADD
					|| eventType == Notification.ADD_MANY) {
				List subTeams = ProcessUtil.getAffectedElements(notification,
						null);
				for (Iterator iter = subTeams.iterator(); iter.hasNext();) {
					TeamProfile subTeam = (TeamProfile) iter.next();
					ProcessUtil.addToContainer(subTeam, this);
				}
			}
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			return;
		case UmaPackage.TEAM_PROFILE__TEAM_ROLES:
			List elements = ProcessUtil.getAffectedElements(notification, null);
			eventType = notification.getEventType();
			boolean refreshTeam = false;
			for (Iterator iter = elements.iterator(); iter.hasNext();) {
				BreakdownElement e = (BreakdownElement) iter.next();
				if (eventType == Notification.ADD
						|| eventType == Notification.ADD_MANY) {
					ProcessUtil.addToContainer(e, this, false);
				}
				if (e.getSuperActivities() == null) {
					refreshTeam = true;					
				}
				else {
					fireNotifyChanged(new ViewerNotification(notification, e,
						false, true));
				}
			}

			if (refreshTeam) {
				fireNotifyChanged(new ViewerNotification(notification,
						notification.getNotifier(), true, false));
			}

			return;
		case UmaPackage.TEAM_PROFILE__PRESENTATION_NAME:
			TeamProfile teamProfile = (TeamProfile) notification.getNotifier();
			fireNotifyChanged(new ViewerNotification(notification, teamProfile,
					false, true));
			for (Iterator iter = teamProfile.getTeamRoles().iterator(); iter
					.hasNext();) {
				RoleDescriptor roleDescriptor = (RoleDescriptor) iter.next();
				fireNotifyChanged(new ViewerNotification(notification,
						roleDescriptor, false, true));
			}
			return;
		case UmaPackage.ACTIVITY__SUPPRESSED:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, true));
			return;
		}
		
		super.notifyChanged(notification);
	}

	public Collection getEClasses() {
		return ProcessUtil.getOBSEclasses();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createDragAndDropCommand(org.eclipse.emf.edit.domain.EditingDomain,
	 *      java.lang.Object, float, int, int, java.util.Collection)
	 */
	protected Command createDragAndDropCommand(EditingDomain domain,
			Object owner, float location, int operations, int operation,
			Collection collection) {
		return new OBSDragAndDropCommand(domain, owner, location, operations,
				operation, collection)
		// {
		// public Collection getAffectedObjects() {
		// Collection wrappers = new ArrayList();
		// for (Iterator iter = super.getAffectedObjects().iterator();
		// iter.hasNext();) {
		// RoleDescriptor desc = (RoleDescriptor) iter.next();
		// wrappers.add(TngUtil.getWrapper(teamRoleWrappers, desc.getRole()));
		// }
		// return wrappers;
		// }
		// }
		;
	}

	private boolean descriptorExists(Object role) {
		TeamProfile teamProfile = (TeamProfile) getTarget();
		for (Iterator iter = teamProfile.getTeamRoles().iterator(); iter
				.hasNext();) {
			RoleDescriptor desc = (RoleDescriptor) iter.next();
			if (desc.getRole() == role)
				return true;
		}
		return false;
	}

	public GenericDropCommand.ElementAdapter createDropAdapter() {
		if (dropAdapter == null) {
			dropAdapter = new GenericDropCommand.ElementAdapter() {

				public Object adapt(Object dropElement) {
					if (dropElement instanceof Role
							&& !descriptorExists(dropElement)
					// && TngUtil.getWrapper(teamRoleWrappers, dropElement) ==
					// null
					) {
						return ProcessUtil
								.createRoleDescriptor((Role) dropElement);
					}
					return null;
				}

			};
		}
		return dropAdapter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#getAttribute(java.lang.Object,
	 *      java.lang.String)
	 */
	public String getAttribute(Object object, String property) {
		if (property == IBSItemProvider.COL_TEAMS) {
			return TngUtil.getPresentationName(((TeamProfile) object)
					.getSuperTeam());
		}
		return super.getAttribute(object, property);
	}

	public void moveUp(Object obj, IActionManager actionMgr) {
		Object parent = this.getParent(obj);
		if ((parent != null) && (parent instanceof Activity)) {
			TngUtil.moveUp((Activity) parent, obj, getEClasses(), actionMgr);
		}
		if ((parent != null) && (parent instanceof TeamProfile)) {
			TeamProfile team = ((TeamProfile) parent);
			List list = team.getSubTeam();
			int location = 0;
			for (int i = 0; i < list.size(); i++) {
				if (obj.equals(list.get(i))) {
					location = i;
					break;
				}
			}

			((EList) team.getSubTeam()).move(location, location - 1);

			Activity activity = this.getActivity(obj);
			TngUtil.moveUp(activity, obj, getEClasses(), actionMgr);
		}
	}

	public void moveDown(Object obj, IActionManager actionMgr) {
		Object parent = this.getParent(obj);
		if ((parent != null) && (parent instanceof Activity)) {
			TngUtil.moveDown((Activity) parent, obj, getEClasses(), actionMgr);
		}
		if ((parent != null) && (parent instanceof TeamProfile)) {
			TeamProfile team = (TeamProfile) parent;
			List list = team.getSubTeam();
			int location = 0;

			for (int i = 0; i < list.size(); i++) {
				if (obj.equals(list.get(i))) {
					location = i;
					break;
				}
			}

			((EList) team.getSubTeam()).move(location, location + 1);

			Activity activity = this.getActivity(obj);
			TngUtil.moveDown(activity, obj, getEClasses(), actionMgr);
		}
	}

	public Activity getActivity(Object object) {
		for (Object parent = getParent(object); parent != null;) {
			if (parent instanceof Activity)
				return (Activity) parent;
			AdapterFactory aFactory = TngUtil
					.getBestAdapterFactory(adapterFactory);
			ItemProviderAdapter adapter = (ItemProviderAdapter) aFactory.adapt(
					parent, ITreeItemContentProvider.class);
			parent = adapter.getParent(parent);
		}
		return null;
	}
}
