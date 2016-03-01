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
 * This interface is used by item providers that want to let clients access its
 * {@link org.eclipse.epf.library.edit.TransientGroupItemProvider <em>group item providers</em>}
 * by name.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IGroupContainer {

	/**
	 * Gets the group item provider with the given name.
	 * 
	 * @param name
	 *            the name of the group item provider
	 * @return a <code>TransientGroupItemProvider<code> object
	 */
	Object getGroupItemProvider(String name);

}
