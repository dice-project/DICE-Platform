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
 * This interface is used by adapter factories for cleaning up references to
 * unused item providers.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IReferencer {

	/**
	 * Removes the given reference to an item provider.
	 * 
	 * @param ref
	 *            a reference to an item provider
	 * @return <code>true</code> if the reference is successfully removed
	 */
	public boolean remove(Object ref);

	/**
	 * Cleans up all unused references.
	 */
	public void cleanUp();

}
