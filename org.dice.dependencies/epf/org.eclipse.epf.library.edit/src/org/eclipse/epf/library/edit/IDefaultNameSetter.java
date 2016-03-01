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
 * This interface is used by item providers that want to assign a default name
 * for each newly created method element.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IDefaultNameSetter {

	/**
	 * Sets the default name for the given method element.
	 * 
	 * @param obj
	 *            a method element
	 */
	public void setDefaultName(Object obj);

	/**
	 * Gets the ID of the feature that contains method elements whose default
	 * name set through this interface.
	 * 
	 * @return the ID of a feature
	 */
	public int getInterestedFeatureID();

	/**
	 * Gets the owner class of the feature that contains method elements whose
	 * default name set through this interface.
	 * 
	 * @return the owner class of a feature
	 */
	public Class getInterestedFeatureOwnerClass();

}
