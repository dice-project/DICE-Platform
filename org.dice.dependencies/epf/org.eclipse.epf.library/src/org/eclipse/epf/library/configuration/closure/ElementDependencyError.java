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
package org.eclipse.epf.library.configuration.closure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class holds the element dependency error in a configuration closure.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ElementDependencyError {

	// the error element, could be a MethodPackage or a UI package
	private Object errorElement;

	/**
	 * map of causeelement to the ErrorInfo object
	 */
	private Map<Object, PackageError> errorInfoMap = new HashMap<Object, PackageError>();

	private int errorBits = 0;

	/**
	 * constructor
	 * @param element
	 */
	public ElementDependencyError(Object element) {
		this.errorElement = element;
	}

	/**
	 * get the element that contains the error
	 * @return Object
	 */
	public Object getErrorElement() {
		return errorElement;
	}

	/**
	 * get the elements that caused the error
	 * @return Object[]
	 */
	public Object[] getCauseElements() {
		return errorInfoMap.keySet().toArray();
	}

	/**
	 * get the count of the error info entry
	 * @return int
	 */
	public int size() {
		return errorInfoMap.size();
	}

	/**
	 * add an error entry
	 * @param errorInfo PackageError
	 */
	public void addError(PackageError errorInfo) {
		errorInfoMap.put(errorInfo.getCauseElement(), errorInfo);
		errorBits |= errorInfo.getErrorLevel();
	}

	/**
	 * remove the error associated with the causeElement
	 * 
	 * @param causeElement
	 *            Object the element that caused the error
	 */
	public PackageError removeError(Object causeElement) {
		PackageError error = errorInfoMap.remove(causeElement);

		// recalculate the error bits
		calculateErrorBits();
		
		return error;
	}

	/**
	 * remove the error associated with the causeElement
	 * 
	 * @param causeElement
	 *            Object the element that caused the error
	 */
	public PackageError getError(Object causeElement) {
		return errorInfoMap.get(causeElement);
	}

	/**
	 * get a list of all ErrInfo objects
	 * 
	 * @return List a list of ErrorInfo objects
	 */
	public List<PackageError> getAll() {
		return new ArrayList<PackageError>(errorInfoMap.values());
	}

	/**
	 * check this is an error or not.
	 * 
	 * @return boolean
	 */
	public boolean isError() {
		return (errorBits & ErrorInfo.ERROR) > 0;
	}

	/**
	 * check if this is a warning
	 * 
	 * @return boolean
	 */
	public boolean isWarning() {
		return (errorBits & ErrorInfo.WARNING) > 0;
	}

	/**
	 * check if this is due to an error in it's children
	 * @return boolean
	 */
	public boolean isChildError() {
		return (errorBits & PackageError.CHILD_ERROR) > 0;
	}

	/**
	 * check if this is due to a warning in it's children
	 * @return boolean
	 */
	public boolean isChildWarning() {
		return (errorBits & PackageError.CHILD_WARNING) > 0;
	}

	public void calculateErrorBits() {
		errorBits = 0;
		for (Iterator it = errorInfoMap.values().iterator(); it.hasNext();) {
			errorBits |= ((ErrorInfo) it.next()).getErrorLevel();
		}
	}
	
}
