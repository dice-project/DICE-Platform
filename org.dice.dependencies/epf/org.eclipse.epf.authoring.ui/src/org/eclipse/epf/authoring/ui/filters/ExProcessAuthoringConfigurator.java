/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.authoring.ui.filters;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.epf.library.configuration.ProcessAuthoringConfigurator;
import org.eclipse.epf.library.edit.itemsfilter.FilterHelper;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.jface.viewers.Viewer;

/**
 * Configuration filter that still shows certain elements during process authoring that
 * ProcessConfigurator does not show. Filter elements based on search pattern and type.
 * @author skannoor
 *
 */
public class ExProcessAuthoringConfigurator extends
		ProcessAuthoringConfigurator {

	String filterStr;

	MethodConfiguration methodConfiguration;
	Viewer viewer;
	
	protected FilterHelper helper;
	/**
	 * @param methodConfig
	 * @param viewer
	 */
	public ExProcessAuthoringConfigurator(MethodConfiguration methodConfig,
			Viewer viewer) {
		super(methodConfig);
		this.viewer = viewer;
		//this.filterStr = filterStr;
		this.methodConfiguration = methodConfig;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(final Notification msg) {
		if (viewer == null) {
			return;
		}

		SafeUpdateController.syncExec(new Runnable() {
			public void run() {
				switch (msg.getEventType()) {
				case Notification.ADD:
				case Notification.ADD_MANY:
				case Notification.REMOVE:
				case Notification.REMOVE_MANY:
					viewer.refresh();
				}
			}
		});

	}
	
	public boolean accept(Object obj) {

		if(obj == null) return false;
		
		if (obj instanceof MethodConfiguration) {
			if (methodConfiguration == null) {
				return true;
			}
			return methodConfiguration.equals(obj);
		}

//		if (!super.accept(obj))
//			return false;
		if (methodConfig == null)
			return true;

		obj = LibraryUtil.unwrap(obj);

//		if (obj instanceof MethodPackage) {
//			return methodConfig.getMethodPackageSelection().contains(obj);
//		} else if (obj instanceof ItemProviderAdapter) {
//			return true;
//		} else {
//			if(Log.DEBUG) {
//				System.out.println("Object filtered: " + (obj == null ? null : obj.toString())); //$NON-NLS-1$
//			}
//		}
		if (helper.isShowPresentationName()) {
			if (!helper.matchPatternOnPresentationName(obj))
				return false;
		} else {
			if (!helper.matchPattern(obj))
				return false;
		}
		if (!helper.isObjectInSelectedItems(obj))
			return false;
		if (childAccept(obj))
			return true;
		return false;

	}

	public boolean childAccept(Object obj) {
		return false;
	}

	public void setHelper(FilterHelper helper) {
		this.helper = helper;
	}
	
	public void setMethodConfiguration(MethodConfiguration config)
	{
		super.setMethodConfiguration(config);
		this.methodConfiguration = config;
	}
}
