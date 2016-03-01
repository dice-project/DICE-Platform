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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.UmaPackage;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ProcessComponentItemProvider extends
		org.eclipse.epf.uma.provider.ProcessComponentItemProvider implements
		IProcessItemProvider {

	// private static class ProcessListener extends AdapterImpl {
	// private ItemProviderAdapter itemProvider;
	// private Object refreshObj;
	//
	// /**
	// *
	// */
	// public ProcessListener(ItemProviderAdapter itemProvider, Object
	// refreshObj) {
	// this.itemProvider = itemProvider;
	// this.refreshObj = refreshObj;
	// }
	//        
	// /* (non-Javadoc)
	// * @see
	// org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
	// */
	// public void notifyChanged(Notification msg) {
	// switch(msg.getFeatureID(Process.class)) {
	// case UmaPackage.PROCESS__BREAKDOWN_ELEMENTS:
	// itemProvider.fireNotifyChanged(new ViewerNotification(msg, refreshObj,
	// true, false));
	// return;
	// }
	// }
	// };

	/**
	 * @param adapterFactory
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
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE
					.getProcessComponent_Process());
		}
		return childrenFeatures;
	}

	public void notifyChanged(Notification notification) {
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
			
		// avoid refreshing viewers when new process element or diagram is added to the process component
		//
		case UmaPackage.PROCESS_COMPONENT__PROCESS_ELEMENTS:
		case UmaPackage.PROCESS_COMPONENT__DIAGRAMS:
			return;
			
		}		
		super.notifyChanged(notification);
	}

	// public Collection getChildren(Object object) {
	// List children = new ArrayList();
	// Process proc = ((ProcessComponent)object).getProcess();
	// if(proc != null) {
	// if(TngUtil.getAdapter(proc, ProcessListener.class) == null) {
	// proc.eAdapters().add(new ProcessListener(this, object));
	// }
	// for (Iterator iter = proc.getBreakdownElements().iterator();
	// iter.hasNext();) {
	// Object element = iter.next();
	// if(element instanceof Activity) {
	// children.add(element);
	// }
	// }
	// }
	// return children;
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getImage(java.lang.Object)
	 */
	// public Object getImage(Object object) {
	// return super.getImage(object);
	// }
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_ProcessComponent_type")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	// public void dispose() {
	// // remove all process listeners from all targets
	// //
	// if (target != null)
	// {
	// target.eAdapters().remove(this);
	// if (targets != null)
	// {
	// for (Iterator i = targets.iterator(); i.hasNext(); )
	// {
	// Notifier otherTarget = (Notifier)i.next();
	// otherTarget.eAdapters().remove(this);
	// }
	// }
	// }
	//
	// super.dispose();
	// }

}
