//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.configuration.closure;

import org.eclipse.epf.uma.MethodElement;

/**
 * Interface for Configuration errors
 * @author Jeff Hardy
 * @author Jinhua Xi
 *
 */
public interface IConfigurationError {
	
	/**
	 * 
	 * @return true iff error is a warning
	 */
	public boolean isWarning();
	
	/**
	 * 
	 * @return true iff error is an error
	 */
	public boolean isError();
	
	/**
	 * 
	 * @return the MethodElement containing the error, ie, the element referring to something that is missing
	 */
	public MethodElement getErrorMethodElement();
	
	/**
	 * 
	 * @return the MethodElement causing the error, ie, the element that can be selected to fix the error
	 */
	public MethodElement getCauseMethodElement();
	
	/**
	 * 
	 * @return a unique ID for this error
	 */
	public String getId();
	
	/**
	 * 
	 * @return one of IMarker.SEVERITY_ERROR, IMarker.SEVERITY_WARNING, IMarker.SEVERITY_INFO
	 * @see org.eclipse.core.resources.IMarker
	 */
	public int getSeverity();
	
	/**
	 * get the message string for this error
	 * @return String
	 */
	public String getErrorMessage();
	
	/**
	 * get the error category
	 * 
	 * @return String
	 */
	public String getCategory();
	
	/**
	 * get the error message id
	 * @return
	 */
	public String getMessageId();
	
}
