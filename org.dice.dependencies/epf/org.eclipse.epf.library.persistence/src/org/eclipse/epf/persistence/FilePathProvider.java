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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.MethodElement;

/**
 * File-based URI provider
 * 
 * @author Phong Nguyen Le - Aug 8, 2006
 * @since  1.0
 */
public class FilePathProvider implements IURIProvider {
	protected FilePathProvider() {
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.persistence.IURIProvider#getURI(org.eclipse.emf.ecore.EObject)
	 */
	public URI getURI(EObject object) {
		if(object instanceof MethodElement) {
			return MultiFileSaveUtil.createFileURI((MethodElement) object);
		}
		return null;
	}

}
