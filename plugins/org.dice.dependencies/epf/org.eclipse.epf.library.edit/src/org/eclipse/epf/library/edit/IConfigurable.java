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

/**
 * This interface is used by item providers that can be configured to display a
 * filtered list of children and have their default label overwritten at
 * runtime.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IConfigurable {

	/**
	 * Sets the filter that is used to filter this object's children.
	 * 
	 * @param filter
	 *            an <code>IFilter</code> object
	 * @see IFilter
	 */
	void setFilter(IFilter filter);

	/**
	 * Sets the label for this object.
	 * 
	 * @param a
	 *            string label
	 */
	void setLabel(String label);

}
