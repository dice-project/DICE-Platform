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

import java.util.Collection;

/**
 * This interface is implemented by break down element item providers that want
 * to mantain a list cached children.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface ICachedChildrenItemProvider {

	/**
	 * Gets the cached children.
	 * 
	 * @return a collection of cached children.
	 */
	public Collection getChildrenFromCache();

	/**
	 * Gets the cached rollup childen.
	 * 
	 * @return a collection of cached rollup children.
	 */
	public Collection getRollupChildrenFromCache();

}
