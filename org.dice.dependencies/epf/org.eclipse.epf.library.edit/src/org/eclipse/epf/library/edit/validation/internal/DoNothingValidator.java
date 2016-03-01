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
package org.eclipse.epf.library.edit.validation.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.library.edit.validation.IValidator;

/**
 * Validator that validates nothing.
 * 
 * @author Phong Nguyen Le - Aug 4, 2005
 * @since 1.0
 */
public class DoNothingValidator implements IValidator {
	public static final DoNothingValidator INSTANCE = new DoNothingValidator();

	private DoNothingValidator() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.validation.IValidator#isValid(java.lang.String)
	 */
	public String isValid(String newText) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.validation.IValidator#isValid(java.lang.Object)
	 */
	public IStatus isValid(Object value) {
		return Status.OK_STATUS;
	}
}
