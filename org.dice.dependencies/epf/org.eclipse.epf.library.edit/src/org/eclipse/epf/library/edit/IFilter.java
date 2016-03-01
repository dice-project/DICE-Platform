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
 * A generic filter that uses the visitor design pattern.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IFilter {

	/**
	 * Checks whether a given object can be accepted by this filter.
	 * 
	 * @param obj
	 *            the visiting object
	 * @return <code>true</code> if the object is accepted, <code>false</code>
	 *         otherwise
	 */
	boolean accept(Object obj);

}
