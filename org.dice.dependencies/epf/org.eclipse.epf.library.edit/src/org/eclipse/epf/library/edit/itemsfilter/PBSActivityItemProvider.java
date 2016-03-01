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
package org.eclipse.epf.library.edit.itemsfilter;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodConfiguration;

/**
 * The item provider adapter for activities in the Work Breakdown Structure
 * page of a Process editor.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class PBSActivityItemProvider extends BSActivityItemProvider {

	private IFilter filter;

	// IFilter fitler;
	public PBSActivityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public Collection getChildren(Object object) {
		super.setFilter(filter);
		Collection col = super.getChildren(object);
		// This call to PBSActiivityItemProvider(process package) is done
		// - in order remove duplicate subartifact.
		MethodConfiguration config = getConfigurator() == null ? null : getConfigurator().getMethodConfiguration();
		col = ProcessUtil.removeSubartifactsFromChildren(config, col, false);
		return col;
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	protected Object getObject(Descriptor descriptor) {
		return null;
	}

	public Collection getEClasses() {
		return ProcessUtil.getPBSEclasses();
	}
	
}
