//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.util;

import org.eclipse.emf.edit.provider.IItemLabelProvider;

/**
 * @author Phong Nguyen Le
 * @since 1.5
 *
 */
public class ItemLabelProvider implements IItemLabelProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.IItemLabelProvider#getImage(java.lang.Object)
	 */
	public Object getImage(Object object) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.IItemLabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object object) {
		return object == null ? "" : object.toString();//$NON-NLS-1$
	}

}
