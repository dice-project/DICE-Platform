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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.process.command.GenericDropCommand;
import org.eclipse.epf.library.edit.process.command.OBSDragAndDropCommand;
import org.eclipse.epf.library.edit.process.command.GenericDropCommand.ElementAdapter;
import org.eclipse.epf.uma.CompositeRole;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.UmaPackage;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class CompositeRoleItemProvider extends RoleDescriptorItemProvider {

	private ElementAdapter dropAdapter;

	/**
	 * @param adapterFactory
	 * @param delegateItemProvider
	 */
	public CompositeRoleItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getCompositeRole_AggregatedRoles());
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.DescriptorItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(CompositeRole.class)) {
		case UmaPackage.COMPOSITE_ROLE__AGGREGATED_ROLES:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), true, false));
			return;
		}

		super.notifyChanged(notification);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#isWrappingNeeded(java.lang.Object)
	 */
	protected boolean isWrappingNeeded(Object object) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createWrapper(org.eclipse.emf.ecore.EObject,
	 *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.Object, int)
	 */
	protected Object createWrapper(EObject object, EStructuralFeature feature,
			Object value, int index) {
		DescribableElementWrapperItemProvider wrapper = new DescribableElementWrapperItemProvider(
				(DescribableElement) value, object, getRootAdapterFactory());
		// wrapper.setColumnIndexToNameMap(columnIndexToNameMap);
		return wrapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildren(java.lang.Object)
	 */
	// public Collection getChildren(Object object) {
	// CompositeRole compositeRole = (CompositeRole) object;
	// AdapterFactory adapterFactory = getRootAdapterFactory();
	// Collection children = new ArrayList();
	// for (Iterator iter = compositeRole.getAggregatedRoles().iterator();
	// iter.hasNext();) {
	// children.add(new
	// DescribableElementWrapperItemProvider((DescribableElement) iter.next(),
	// this, adapterFactory));
	// }
	// return children;
	// }
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
				operation, collection);
	}

	public GenericDropCommand.ElementAdapter createDropAdapter() {
		if (dropAdapter == null) {
			dropAdapter = new GenericDropCommand.ElementAdapter() {

				public Object adapt(Object dropElement) {
					if (dropElement instanceof Role
							&& !((CompositeRole) getTarget())
									.getAggregatedRoles().contains(dropElement)) {
						return dropElement;
					}
					return null;
				}

			};
		}
		return dropAdapter;
	}

}
