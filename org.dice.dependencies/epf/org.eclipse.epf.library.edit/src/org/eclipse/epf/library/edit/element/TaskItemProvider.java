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
package org.eclipse.epf.library.edit.element;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.library.edit.util.TngUtil;

/**
 * The item provider adapter for a task.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class TaskItemProvider extends org.eclipse.epf.uma.provider.TaskItemProvider {

	/**
	 * Creates a new instance.
	 */
	public TaskItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getParent(java.lang.Object)
	 */
	public Object getParent(Object object) {
		EObject contentPkg = (EObject) super.getParent(object);
		if (contentPkg == null)
			return null;
		ContentPackageItemProvider itemProvider = (ContentPackageItemProvider) TngUtil
				.getAdapter(contentPkg, ContentPackageItemProvider.class);
		return itemProvider != null ? itemProvider.getTasks() : null;
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
		}
		return childrenFeatures;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#createCommand(java.lang.Object,
	 *      org.eclipse.emf.edit.domain.EditingDomain, java.lang.Class,
	 *      org.eclipse.emf.edit.command.CommandParameter)
	 */
	// public Command createCommand(Object object, EditingDomain domain,
	// Class commandClass, CommandParameter commandParameter) {
	// // if(commandClass == RemoveCommand.class) {
	// // commandParameter.setOwner(((EObject)object).eContainer());
	// // }
	// Command cmd = super.createCommand(object, domain, commandClass,
	// commandParameter);
	// System.out.println(getClass().getName() + "#createCommand: " +
	// commandClass + ", can execute: " + cmd.canExecute() + ", owner: " +
	// commandParameter.getOwner() + ", collection: " +
	// commandParameter.getCollection());
	// return cmd;
	// }
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_Task_type")); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#dispose()
	 */
	public void dispose() {
		super.dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.provider.TaskItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

//		switch(notification.getEventType()) {
//		case EventTypes.MAKE_PROXY:
//			// refresh parent
//			//
//			fireNotifyChanged(new ViewerNotification(notification, getParent(notification.getNotifier()), true, false));
//			
//			// refresh itself
//			//
//			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, true));
//			
//			return;
//		}
		
		TngUtil.refreshParentIfNameChanged(notification, this);
		
		super.notifyChanged(notification);
	}
	
	@Override
	public Object getImage(Object object) {
		Object image = TngUtil.getCustomNodeIcon(object);
		if(image != null) {
			return image;
		}
		return super.getImage(object);
	}
}
