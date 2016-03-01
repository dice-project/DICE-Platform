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

/**
 * Interface for validator.
 * 
 * @author Phong Nguyen Le - Aug 1, 2005
 * @since 1.0
 */
public interface IValidator {
	/**
	 * Validates the given string. Returns an error message to display if the
	 * new text is invalid. Returns <code>null</code> if there is no error.
	 * Note that the empty string is not treated the same as <code>null</code>;
	 * it indicates an error state but with no message to display.
	 * 
	 * @param newText
	 *            the text to check for validity
	 * 
	 * @return an error message or <code>null</code> if no error
	 */
	public String isValid(String newText);
	
	/**
	 * Validates the given object value.
	 * 
	 * @param value the value to validate
	 * @return a status
	 */
	public IStatus isValid(Object value);
	
	public int MaxFilePathNameLength = 255;
}
