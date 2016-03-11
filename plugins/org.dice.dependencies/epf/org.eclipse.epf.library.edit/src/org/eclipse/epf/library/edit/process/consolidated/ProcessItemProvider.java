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
package org.eclipse.epf.library.edit.process.consolidated;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.UmaPackage;


/**
 * Process item provider for consolidated view
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 */
public class ProcessItemProvider extends ActivityItemProvider {

	private ItemProviderAdapter delegateItemProvider;

	public ProcessItemProvider(AdapterFactory adapterFactory,
			ItemProviderAdapter delegateItemProvider) {
		super(adapterFactory);
		this.delegateItemProvider = delegateItemProvider;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.ActivityItemProvider#getPropertyDescriptors(java.lang.Object)
	 */
	public List getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			itemPropertyDescriptors = delegateItemProvider
					.getPropertyDescriptors(object);
		}
		return itemPropertyDescriptors;
	}
	
	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(ProcessComponent.class)) {
		case UmaPackage.PROCESS__PRESENTATION_NAME:
			// need to update the library view
			// but ProcessComponents don't have a Presentation Name, so
			// change the name of the procComp to itself
			// this will cause a refresh
			org.eclipse.epf.uma.Process proc = (org.eclipse.epf.uma.Process) notification.getNotifier();
			if (proc.eContainer() != null) {
				boolean old = proc.eContainer().eDeliver();
				try {
					proc.eContainer().eSetDeliver(true);
					if (proc.eContainer() instanceof ProcessComponent)
						((ProcessComponent)proc.eContainer()).setName(((ProcessComponent)proc.eContainer()).getName());
				} finally {
					proc.eContainer().eSetDeliver(old);
				}
			}
			break;
		}
		super.notifyChanged(notification);
	}
}
