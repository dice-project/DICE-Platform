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
package org.eclipse.epf.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.uma.MethodElement;

/**
 * The interface for a Method Library Persister.
 * <p>
 * A Method Library Persister is responsible for persisting the method library
 * content.
 * 
 * @author Phong Nguyen Le - Oct 9, 2006
 * @since 1.0
 */
public interface ILibraryPersister {
	/**
	 * Saves an existing resource.
	 * 
	 * @param resource
	 *            a resource
	 */
	public void save(Resource resource) throws Exception;
	
	/**
	 * Saves the given MethodElement in its own resource. The specified MethodElement must be part of a library
	 * and it is allowed to have its own resource, to which no other resource refers. In other words, calling 
	 * {@link #hasOwnResourceWithoutReferrer(Object)} on the given MethodElement must return <code>true</code>.
	 * 
	 * @param element
	 * @throws Exception
	 */
	public void save(MethodElement element) throws Exception;

	/**
	 * Deletes the resources associated with a method element.
	 * 
	 * @param e
	 *            a method element
	 */
	public void delete(MethodElement e);
	
	/**
	 * Deletes the resources associated with a method element.
	 * 
	 * @param e
	 *            a method element
	 */
	public void delete(Collection<MethodElement> elems);

	/**
	 * Adjusts the location of a resource and saves all the resources that have
	 * been changed as the result of this adjustment.
	 * 
	 * @param resource
	 *            a resource
	 */
	public void adjustLocation(Resource resource);

	/**
	 * Gets a list of warnings associated with a resource.
	 * 
	 * @return a list of <code>Exception</code> objects
	 */
	public List getWarnings();

	/**
	 * Checks whether a method element can have its own resource.
	 * 
	 * @param e
	 *            a method element
	 * @return <code>true</code> if the method element is owned by a resource
	 */
	public boolean hasOwnResource(Object e);

	/**
	 * Checks whether a method element can have its own resource, which no other resource refers to.
	 * 
	 * @param e
	 * @return
	 */
	public boolean hasOwnResourceWithoutReferrer(Object e);
	
	/**
	 * Checks if <code>containerResource</code> contains <code>resource</code>.
	 * 
	 * @param resource
	 * @param containerResource
	 * @return <code>true</code> if the specified resource is contained by <code>containerResource</code>
	 */
	public boolean isContainedBy(Resource resource, Resource containerResource);

	/**
	 * Gets the fail safe method library persister associated with this library
	 * persister.
	 * 
	 * @return a fail safe method library persister
	 */
	public FailSafeMethodLibraryPersister getFailSafePersister();

	public static interface FailSafeMethodLibraryPersister extends
			ILibraryPersister {
		/**
		 * Direct persister to overwrite during save without checking on change conflicts.
		 */
		public static final String OPTIONS_OVERWRITABLE_RESOURCES = "OVERWRITABLE_RESOURCES"; //$NON-NLS-1$

		/**
		 * Gets the current transaction ID.
		 * 
		 * @return the current transaction ID
		 */
		String getCurrentTxID();

		/**
		 * Adjusts the location of the given resource and save all the resources
		 * that have been changed as the result of this adjustment. This call
		 * needs a new transaction and the transaction will be committed in this
		 * method.
		 * 
		 * @param resources
		 *            the resources whose locations need to be adjusted
		 */
		void adjustLocation(Collection resources);

		/**
		 * Commits the transaction.
		 */
		void commit();

		/**
		 * Roll backs the transaction.
		 */
		void rollback();
		
		/**
		 * Gets save options.
		 * 
		 * @return
		 */
		Map getSaveOptions();
	}

}
