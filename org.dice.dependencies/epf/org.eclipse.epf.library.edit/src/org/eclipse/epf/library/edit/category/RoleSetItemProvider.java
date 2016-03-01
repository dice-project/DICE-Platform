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
package org.eclipse.epf.library.edit.category;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.ILibraryItemProvider;
import org.eclipse.epf.library.edit.IStatefulItemProvider;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.internal.IListenerProvider;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * The item provider adapter for a role set.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class RoleSetItemProvider extends
		org.eclipse.epf.uma.provider.RoleSetItemProvider implements
		ILibraryItemProvider, IStatefulItemProvider, IListenerProvider {

	private Object parent;

	/**
	 * Creates a new instance.
	 */
	public RoleSetItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.RoleSetItemProvider#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		if (parent != null) {
			return parent;
		}
		if (object instanceof EObject) {
			MethodPlugin plugin = UmaUtil.getMethodPlugin((EObject) object);
			if (plugin != null) {
				String[] path = {
						LibraryEditPlugin.INSTANCE
								.getString("_UI_Content_group") //$NON-NLS-1$
						,
						LibraryEditPlugin.INSTANCE
								.getString("_UI_Standard_Categories_group") //$NON-NLS-1$
						,
						LibraryEditPlugin.INSTANCE
								.getString("_UI_Role_Sets_group") //$NON-NLS-1$
				};

				return TngUtil.getAdapter(plugin, path);
			}
		}

		return super.getParent(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		// if(childrenFeatures == null) {
		// childrenFeatures = new ArrayList();
		// childrenFeatures.add(UmaPackage.eINSTANCE.getRoleSet_Roles());
		// }
		// return childrenFeatures;

		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);
		TngUtil.refreshParentIfNameChanged(notification, this);
		switch (notification.getFeatureID(RoleSet.class)) {
		case UmaPackage.METHOD_ELEMENT__NAME:
			TngUtil.refreshContributors(this, notification, false, true);
			break;
		case UmaPackage.ROLE_SET__ROLES:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));

			// refresh wrappers
			//
			if (wrappers != null) {
				for (Iterator iter = new ArrayList(wrappers).iterator(); iter
						.hasNext();) {
					fireNotifyChanged(new ViewerNotification(notification, iter
							.next(), true, false));
				}
			}

			// refresh uncategorized roles
			//
			// TngUtil.refreshAdapter(LibraryEditConstants.UNCATEGORIZED_ROLES_PATH,
			// notification);

			return;
		}

		super.notifyChanged(notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel((VariabilityElement) object,
				getString("_UI_RoleSet_type"), true); //$NON-NLS-1$
	}

	protected boolean isWrappingNeeded(Object object) {
		return true;
	}

	protected Object createWrapper(EObject object, EStructuralFeature feature,
			Object value, int index) {
		if (!isWrappingNeeded(object))
			return value;
		return TngUtil.createWrapper(adapterFactory, object, feature, value,
				index);
	}

	public List getNotifyChangedListeners() {
		if(changeNotifier instanceof Collection) {
			return new ArrayList((Collection) changeNotifier);
		}
		return Collections.EMPTY_LIST;
	}

	
	// Change Categories display in Library Navigator
	// Add below method to avoid children for this ItemProvider. +
	// sign too.
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object object) {
		return false;
	}

	// /* (non-Javadoc)
	// * @see
	// org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	// */
	// public Collection getChildren(Object object) {
	// List newChildren = new ArrayList();
	// RoleSet roleSet = (RoleSet) object;
	// for (Iterator iter = roleSet.getRoles().iterator(); iter.hasNext();) {
	// Role role = (Role) iter.next();
	// // look if there is an existing ObjectLinkItemProvider for this task in
	// the current children list first
	// //
	// ObjectLinkItemProvider child = findExistingObjectLinkItemProvider(role);
	// if(child == null) {
	// child = new ObjectLinkItemProvider(adapterFactory, object, role);
	// }
	// else {
	// children.remove(child);
	// }
	// newChildren.add(child);
	// }
	// // dispose the old children
	// //
	// if(children != null) {
	// for (Iterator iter = children.iterator(); iter.hasNext();) {
	// ObjectLinkItemProvider child = (ObjectLinkItemProvider) iter.next();
	// child.dispose();
	// }
	// }
	// children = newChildren;
	// return children;
	// }

	protected Command createAddCommand(EditingDomain domain, EObject owner,
			EStructuralFeature feature, Collection collection, int index) {
		// return new MethodElementAddCommand((AddCommand)
		// super.createAddCommand(domain, owner, feature, collection, index));
		return UnexecutableCommand.INSTANCE;
	}

}
