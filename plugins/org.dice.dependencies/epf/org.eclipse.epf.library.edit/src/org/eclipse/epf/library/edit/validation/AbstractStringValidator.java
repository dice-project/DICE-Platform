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
package org.eclipse.epf.library.edit.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.library.edit.LibraryEditPlugin;

/**
 * @author Phong Nguyen Le
 * @since  1.0
 */
public abstract class AbstractStringValidator implements IValidator {
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.validation.IValidator#isValid(java.lang.Object)
	 */
	public IStatus isValid(Object value) {
		if(value instanceof String) {
			String msg = isValid((String)value);
			if(msg == null) {
				return Status.OK_STATUS;
			}
			else {
				return new Status(IStatus.ERROR, LibraryEditPlugin.getDefault().getId(),
						0, msg, null);
			}
		}
		throw new IllegalArgumentException();
	}
}
