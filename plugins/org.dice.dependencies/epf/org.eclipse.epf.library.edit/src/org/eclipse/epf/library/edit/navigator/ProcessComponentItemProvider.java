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
package org.eclipse.epf.library.edit.navigator;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.provider.UmaEditPlugin;

/**
 * The item provider adapter for a process component in the Library view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ProcessComponentItemProvider extends
		org.eclipse.epf.uma.provider.ProcessComponentItemProvider {

	/**
	 * Creates a new instance.
	 */
	public ProcessComponentItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#collectNewChildDescriptors(java.util.Collection,
	 *      java.lang.Object)
	 */
	protected void collectNewChildDescriptors(Collection newChildDescriptors,
			Object object) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		// if(childrenFeatures == null) {
		// childrenFeatures = new ArrayList();
		// childrenFeatures.add(UmaPackage.eINSTANCE.getProcessComponent_Process());
		// //
		// childrenFeatures.add(UmaPackage.eINSTANCE.getProcessPackage_BreakdownElements());
		// //
		// childrenFeatures.add(UmaPackage.eINSTANCE.getProcessPackage_WBS());
		// //
		// childrenFeatures.add(UmaPackage.eINSTANCE.getProcessPackage_OBS());
		// //
		// childrenFeatures.add(UmaPackage.eINSTANCE.getProcessPackage_PBS());
		// }
		// return childrenFeatures;

		return Collections.EMPTY_LIST;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		Process proc = ((ProcessComponent) object).getProcess();
		if (proc instanceof CapabilityPattern) {
			return UmaEditPlugin.INSTANCE
					.getImage("full/obj16/CapabilityPattern"); //$NON-NLS-1$
		} else if (proc instanceof DeliveryProcess) {
			return UmaEditPlugin.INSTANCE
					.getImage("full/obj16/DeliveryProcess"); //$NON-NLS-1$
		}

		return super.getImage(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_ProcessComponent_type")); //$NON-NLS-1$
	}

	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ProcessComponent.class)) {
		case UmaPackage.PROCESS_COMPONENT__NAME:
			ProcessComponent pc = (ProcessComponent) notification.getNotifier();
			if (pc.getProcess() != null) {
				boolean old = pc.getProcess().eDeliver();
				try {
					pc.getProcess().eSetDeliver(false);
					pc.getProcess().setName(pc.getName());
				} finally {
					pc.getProcess().eSetDeliver(old);
				}
			}
			break;
		}

		TngUtil.refreshParentIfNameChanged(notification, this);

		super.notifyChanged(notification);
	}

}
