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

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Descriptor;

/**
 * Item provider for activity used by configuration view and filter dialog. This item provider return
 * children list that contain only its own activities after realization against selected configuration.
 * 
 * @author Phong Nguyen Le - Jun 30, 2006
 * @since  1.0
 */
public class ActivityItemProvider extends BSActivityItemProvider {

	/**
	 * @param adapterFactory
	 */
	public ActivityItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BSActivityItemProvider#getObject(org.eclipse.epf.uma.Descriptor)
	 */
	protected Object getObject(Descriptor descriptor) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.IBSItemProvider#getEClasses()
	 */
	public Collection getEClasses() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BSActivityItemProvider#acceptAsChild(java.lang.Object)
	 */
	protected boolean acceptAsChild(Object child) {
		return child instanceof Activity;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BSActivityItemProvider#isRolledUp()
	 */
	public boolean isRolledUp() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BSActivityItemProvider#basicSetRolledUp(boolean)
	 */
	public void basicSetRolledUp(boolean b) {
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.process.BSActivityItemProvider#setRolledUp(boolean)
	 */
	public void setRolledUp(boolean b) {
	}
	
}
