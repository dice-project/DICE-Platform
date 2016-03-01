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
package org.eclipse.epf.library.persistence;

/**
 * Factory to create {@link ILibraryResourceSet} instance of the particular
 * persistence type
 * 
 * @author Phong Nguyen Le - Oct 26, 2006
 * @since 1.0
 */
public interface ILibraryResourceSetFactory {

	/**
	 * Gets the persistence type of the resource set created by this factory
	 * 
	 * @return the persistence type
	 */
	String getPersistenceType();

	/**
	 * Creates a new library resource set
	 * 
	 * @return a new library resource set
	 */
	ILibraryResourceSet createLibraryResourceSet();
}
