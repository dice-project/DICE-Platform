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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.UmaPackage;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ActivityWrapperItemProvider extends
		BreakdownElementWrapperItemProvider {

	/**
	 * @param value
	 * @param owner
	 * @param adapterFactory
	 */
	public ActivityWrapperItemProvider(Activity value, Object owner,
			AdapterFactory adapterFactory) {
		super(value, owner, adapterFactory);
	}

	/**
	 * @param activity
	 * @param owner
	 * @param feature
	 * @param index
	 * @param adapterFactory
	 */
	public ActivityWrapperItemProvider(Activity activity, Object owner,
			EStructuralFeature feature, int index, AdapterFactory adapterFactory) {
		super(activity, owner, feature, index, adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.DescribableElementWrapperItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		if (getRefreshElement(notification) == getDelegateValue()) {
			switch (notification.getFeatureID(Activity.class)) {
			case UmaPackage.VARIABILITY_ELEMENT__VARIABILITY_BASED_ON_ELEMENT:
			case UmaPackage.ACTIVITY__BREAKDOWN_ELEMENTS:
				Process proc = (Process) getTopItem();
				if (proc != null) {
					BSActivityItemProvider itemProvider = (BSActivityItemProvider) adapterFactory
							.adapt(proc, ITreeItemContentProvider.class);
					if (itemProvider instanceof WBSActivityItemProvider) {
						itemProvider.setRefreshAllIDsRequired(true);
					}
				}
			}
		}

		super.notifyChanged(notification);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		IBSItemProvider itemProvider = null;
		boolean oldRolledUp = false;
		try {
			if (delegateItemProvider instanceof IBSItemProvider) {
				itemProvider = (IBSItemProvider) delegateItemProvider;
				oldRolledUp = itemProvider.isRolledUp();
				if(isRolledUp() != oldRolledUp) {
					if(itemProvider instanceof BSActivityItemProvider) {
						((BSActivityItemProvider)itemProvider).basicSetRolledUp(isRolledUp());
					}
					else {
						itemProvider.setRolledUp(isRolledUp());
					}
				}
				else {
					itemProvider = null;
				}
			}
			return super.getChildren(object);
		}
		finally {
			if(itemProvider != null) {
				// restore rolled-up flag
				//
				if(itemProvider instanceof BSActivityItemProvider) {
					((BSActivityItemProvider)itemProvider).basicSetRolledUp(oldRolledUp);
				}
				else {
					itemProvider.setRolledUp(oldRolledUp);
				}
			}
		}
	}
	
	public Object getDelegatingItemProvider(){
		return delegateItemProvider;
	}
	
}
