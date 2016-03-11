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
import org.eclipse.epf.uma.Descriptor;

/**
 * The item provider adapter for activities in the Team Allocation page of a
 * Process editor.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class OBSActivityItemProvider extends BSActivityItemProvider {

	/**
	 * Creates a new instance.
	 * 
	 * @param adapterFactory
	 */
	public OBSActivityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.BSActivityItemProvider#getObject(org.eclipse.epf.uma.Descriptor)
	 */
	protected Object getObject(Descriptor descriptor) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.IBSItemProvider#getEClasses()
	 */
	public Collection getEClasses() {
		return null;
	}
	
}
