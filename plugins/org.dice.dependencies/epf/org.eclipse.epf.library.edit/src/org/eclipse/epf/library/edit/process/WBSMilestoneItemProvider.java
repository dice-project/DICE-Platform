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

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.WorkBreakdownElement;


/**
 * @author Phong Nguyen Le
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class WBSMilestoneItemProvider extends BreakdownElementItemProvider {

	// private Collection eClasses = new HashSet();
	/**
	 * @param adapterFactory
	 */
	public WBSMilestoneItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory, delegateItemProvider);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BreakdownElementItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(WorkBreakdownElement.class)) {
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_REPEATABLE:
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_ONGOING:
		case UmaPackage.WORK_BREAKDOWN_ELEMENT__IS_EVENT_DRIVEN:
			fireNotifyChanged(new ViewerNotification(notification, notification
					.getNotifier(), false, true));
			return;
		}

		super.notifyChanged(notification);
	}

	
	// /* (non-Javadoc)
	// * @see
	// com.ibm.library.edit.process.WBSActivityItemProvider#getChildren(java.lang.Object)
	// */
	// public Collection getChildren(Object object) {
	// return TngUtil.getAllChildren((ProcessContribution) object,
	// super.getChildren(object),
	// TngUtil.getBestAdapterFactory(adapterFactory));
	// }
	//    
	// /* (non-Javadoc)
	// * @see
	// org.eclipse.emf.edit.provider.ItemProviderAdapter#getElements(java.lang.Object)
	// */
	// public Collection getElements(Object object) {
	// Collection elements = super.getElements(object);
	// return elements;
	// }

	// public List getFilteredBreakdownElements(Object activity, Object obj)
	// {
	// List elements = new ArrayList();
	// List breakdownElements = ((Activity)activity).getBreakdownElements();
	// for (Iterator it = breakdownElements.iterator(); it.hasNext();)
	// {
	// Object elementObj = it.next();
	// if ((elementObj instanceof TaskDescriptor) || (elementObj instanceof
	// Milestone) || (elementObj instanceof Activity))
	// {
	// elements.add(elementObj);
	// }
	// }
	// return elements;
	// }

	public Collection getEClasses() {
		return ProcessUtil.getWBSEclasses();
	}

}
