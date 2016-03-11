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
package org.eclipse.epf.library.edit.configuration;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;

/**
 * The item provider adapter for an activity in the Configuration view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ActivityItemProvider extends
		org.eclipse.epf.library.edit.process.ActivityItemProvider {

	private Process process;

	/**
	 * Creates a new instance.
	 */
	public ActivityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	private Process getProcess() {
		if (process == null) {
			Object top = getTopItem();
			if (top instanceof Process) {
				process = (Process) top;
			}
		}
		return process;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.process.ActivityItemProvider#acceptAsChild(java.lang.Object)
	 */
	protected boolean acceptAsChild(Object child) {
		return  child instanceof Milestone 
			|| (child instanceof Activity
				&& !TngUtil.isContributorOrReplacer((VariabilityElement) child)
				&& ProcessUtil.accept((Activity) child, getFilter(), false));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.process.BSActivityItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		Collection children = super.getChildren(object);

		// remove any suppressed child
		//
		Process proc = getProcess();
		if (proc != null) {
			// getProcess() might return null if getChildren(Object) is called
			// on base activity to
			// retrieve its children
			//
			Suppression suppression = Suppression.getSuppression(proc);
			for (Iterator iter = children.iterator(); iter.hasNext();) {
				Object child = (Object) iter.next();
				if (suppression.isSuppressed(child)) {
					iter.remove();
				}
			}
		}

		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.process.BSActivityItemProvider#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		if (notification.getFeatureID(Activity.class) == UmaPackage.ACTIVITY__SUPPRESSED) {
			// refresh parent content
			//
			fireNotifyChanged(new ViewerNotification(notification,
					getParent(notification.getNotifier()), true, false));
			return;
		}

		super.notifyChanged(notification);
	}
}
