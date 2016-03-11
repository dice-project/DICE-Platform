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
package org.eclipse.epf.authoring.ui.filters;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.epf.library.configuration.ConfigurationFilter;
import org.eclipse.epf.library.edit.itemsfilter.FilterHelper;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.jface.viewers.Viewer;


/**
 *  Method configuration specific filter for filtering elements based on filter pattern
 *  and type after elements are realized within the specified configuraiton.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class DescriptorConfigurationFilter extends ConfigurationFilter {

	String filterStr;

	MethodConfiguration methodConfiguration;

	protected FilterHelper helper;

	protected Viewer viewer;
	
	/**
	 * @param methodConfig
	 * @param viewer
	 */
	public DescriptorConfigurationFilter(MethodConfiguration methodConfig,
			Viewer viewer, String filterStr) {
		super(methodConfig);
		this.viewer = viewer;
		this.filterStr = filterStr;
		this.methodConfiguration = methodConfig;
	}

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

		if (obj instanceof MethodConfiguration) {
			if (methodConfiguration != null) {
				return methodConfiguration.equals(obj);
			} else {
				return false;
			}
		}

		if (!super.accept(obj))
			return false;
		if(helper != null) {
			if (helper.isShowPresentationName()) {
				if (!helper.matchPatternOnPresentationName(obj))
					return false;
			} else {
				if (!helper.matchPattern(obj))
					return false;
			}
			if (!helper.isObjectInSelectedItems(obj))
				return false;
		}
		if (childAccept(obj))
			return true;
		return false;

	}

	public java.util.Collection getChildren(Object obj,
			org.eclipse.emf.ecore.EStructuralFeature childFeature) {
		// return super.getChildren(obj, childFeature);
		// Modified code to handle the filtering
		// to check if the variability of parent is not N/A, filter out already
		// exisitng
		// element from children list.
		Collection col = super.getChildren(obj, childFeature);
		if(col != null){
		for (Iterator itor = col.iterator(); itor.hasNext();) {
			Object element = itor.next();
			if (!accept(element))
				itor.remove();
		}
		}else{
			col = Collections.EMPTY_LIST;
		}

		return col;
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
