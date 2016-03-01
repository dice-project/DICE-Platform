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
package org.eclipse.epf.persistence;

import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.persistence.ILibraryResourceSetFactory;
import org.eclipse.epf.services.Services;

/**
 * @author Phong Nguyen Le - Oct 27, 2006
 * @since  1.0
 */
public class XMILibraryResourceSetFactory implements ILibraryResourceSetFactory {

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSetFactory#createLibraryResourceSet()
	 */
	public ILibraryResourceSet createLibraryResourceSet() {
		return new MultiFileResourceSetImpl();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.persistence.ILibraryResourceSetFactory#getPersistenceType()
	 */
	public String getPersistenceType() {
		return Services.XMI_PERSISTENCE_TYPE;
	}

}
