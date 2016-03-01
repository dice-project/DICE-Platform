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
package org.eclipse.epf.library.edit.itemsfilter;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Milestone;

/**
 * The item provider adapter for an activity in the method element selection
 * dialogs.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ActivityItemProvider extends
		org.eclipse.epf.library.edit.process.ActivityItemProvider {

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 */
	public ActivityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected boolean acceptAsChild(Object child) {
		// TODO Auto-generated method stub
		if(child instanceof Milestone)return true;
		
		return super.acceptAsChild(child);
	}
	public Collection getChildren(Object object) {
		// TODO Auto-generated method stub
		Collection col = super.getChildren(object);
		FilterUtil
				.iterateCollection(col, ProcessUtil.getFilter(adapterFactory));
		return col;
	}

}
