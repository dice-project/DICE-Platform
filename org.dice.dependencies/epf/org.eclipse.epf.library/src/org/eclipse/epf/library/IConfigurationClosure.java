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
package org.eclipse.epf.library;

import java.util.List;

import org.eclipse.epf.library.configuration.closure.ElementDependencyError;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * The interface for a Method Configuration Closure.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public interface IConfigurationClosure {

	/**
	 * Returns the method configuration manager.
	 * 
	 * @return a configuration manager
	 */
	public IConfigurationManager getConfigurationManager();

	/**
	 * Returns the method configuration.
	 * 
	 * @return a method configuration
	 */
	public MethodConfiguration getConfiguration();

	/**
	 * Returns the containining method library.
	 * 
	 * @return a method library
	 */
	public MethodLibrary getLibrary();

	/**
	 * Sets the method plug-ins and packages selection.
	 * 
	 * @param elements
	 *            an array of method plug-ins and packages
	 */
	//public void setSelections(Object[] elements);

	/**
	 * Checks whether a method plug-in or package is selected.
	 * 
	 * @return <code>true</code> if the given element is selected
	 */
	public boolean isSelected(Object input);

	/**
	 * Gets the element dependency error for a method element.
	 * 
	 * @element A method element.
	 * 
	 * @return an array of <code>ElementDependencyError</code>
	 */
	public ElementDependencyError getError(Object element);

	/**
	 * Checks whether the closure has any error.
	 * 
	 * @return <code>true</code> if the closure has one or more errors
	 */
	public boolean hasError();

	/**
	 * Checks whether the closure has any problem.
	 * 
	 * @return <code>true</code> if the closure has one or more problems.\
	 */
	public boolean hasProblem();

	/**
	 * Returns all the errors.
	 * 
	 * @return a list of <code>ErrorInfo</code>
	 */
	public List getAllErrors();

	/**
	 * Returns all the dependency errors.
	 * 
	 * @return an array of <code>ElementDependencyError</code>
	 */
	public Object[] getDependencyErrors();

	/**
	 * Returns all the invalid elements.
	 * 
	 * @return a list of invalid elements
	 */
	public List getInvalidElements();

	/**
	 * Returns all the changed elements. These are elements whose check states
	 * have changed or whose image have changed due to error.
	 * 
	 * @return a list of changed method elements
	 */
	//public List getChangedElements();

	/**
	 * Returns the method plug-ins and packages selection.
	 * 
	 * @return an array of method plug-ins and packages
	 */
	public Object[] getSelection();

	/**
	 * fix the errors in the configurations
	 */
	public void fixErrors();

	/**
	 * Fixes all errors and warnings in the closure.
	 */
	public void fixProblems();

	/**
	 * Saves the method configuration.
	 */
	//public void saveMethodConfiguration();

	/**
	 * Packages the library based on the selection.
	 * <p>
	 * Note: This will change the current library. Before calling this method, a
	 * copy of the current library should be created with the following steps:
	 * 1. Create a new <code>ConfigurationManager</code> with a copy of the
	 * original library, 2. Rebuild the dependency, 3. Create a
	 * <code>ConfigurationClosure</code> with the current configuration.
	 * 
	 * @param removeBrokenReferences
	 *            if <code>true</code>, remove all broken references
	 * @return a method library
	 */
	public MethodLibrary packageLibrary(boolean removeBrokenReferences);

	/**
	 * Disposes resources allocated by this closure.
	 */
	public void dispose();

}
