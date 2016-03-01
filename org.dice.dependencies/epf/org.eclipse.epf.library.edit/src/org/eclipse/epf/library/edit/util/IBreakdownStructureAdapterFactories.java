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
package org.eclipse.epf.library.edit.util;

import org.eclipse.emf.common.notify.AdapterFactory;

/**
 * Interface to provides a related set of adapter factories for breakdown
 * structure views
 * 
 * @author Phong Nguyen Le - Mar 20, 2006
 * @since 1.0
 */
public interface IBreakdownStructureAdapterFactories {
	AdapterFactory getWBSAdapterFactory();

	AdapterFactory getTBSAdapterFactory();

	AdapterFactory getWPBSAdapterFactory();

	AdapterFactory getCBSAdapterFactory();
}
