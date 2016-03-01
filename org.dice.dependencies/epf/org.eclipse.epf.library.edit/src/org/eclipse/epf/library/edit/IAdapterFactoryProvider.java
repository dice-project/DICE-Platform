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
package org.eclipse.epf.library.edit;

import org.eclipse.emf.common.notify.AdapterFactory;

/**
 * @author Phong Nguyen Le
 * @since  1.1
 */
public interface IAdapterFactoryProvider {
	/**
	 * Gets adapter factory for work breakdown structure.
	 * 
	 * @return
	 */
	AdapterFactory getWBSAdapterFactory();

	/**
	 * Gets adapter factory for team usage
	 * 
	 * @return
	 */
	AdapterFactory getTBSAdapterFactory();

	/**
	 * Gets adapter factory for work product usage
	 * 
	 * @return
	 */
	AdapterFactory getWPBSAdapterFactory();

	/**
	 * Gets adapter factory for consolidated view of the process.
	 * 
	 * @return
	 */
	AdapterFactory getCBSAdapterFactory();
}
