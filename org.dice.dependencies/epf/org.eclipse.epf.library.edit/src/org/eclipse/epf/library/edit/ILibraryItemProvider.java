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
 * This interface is used by item providers that want to include UI folders
 * and feature derived children. 
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface ILibraryItemProvider {

	/**
	 * Sets the parent of this item provider.
	 * 
	 * @param parent
	 *            a parent object, typically a method element
	 */
	void setParent(Object parent);

	/**
	 * Gets the children features.
	 * 
	 * @param object
	 *            a method element
	 * @return a collection of features for retrieving the child elements
	 */
	Collection getChildrenFeatures(Object object);

}
