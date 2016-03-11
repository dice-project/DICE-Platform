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
package org.eclipse.epf.library.persistence;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.persistence.refresh.IRefreshListener;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.services.Services;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.ecore.IUmaResourceSet;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;

/**
 * A resource set that manages a collection of {@link Resource}
 * 
 * @author Phong Nguyen Le
 * @since 1.1
 */
public interface ILibraryResourceSet extends IUmaResourceSet {
	/**
	 * Gets the persistence type this resource set supports
	 * 
	 * @return the persistence type
	 * @see Services#getDefaultLibraryPersistenceType()
	 * @see Services#XMI_PERSISTENCE_TYPE
	 */
	String getPersistenceType();

	/**
	 * Loads method libraries into this resource set from the given URI.
	 * 
	 * @param parameters
	 * @return
	 */
	void loadMethodLibraries(URI uri, Map<?, ?> parameters)
			throws LibraryResourceException;

	/**
	 * Gets the list of method libraries that have been loaded into this
	 * resource set.
	 * 
	 * @return
	 */
	List<MethodLibrary> getMethodLibraries();

	/**
	 * Gets the first loaded method library of this resource set.
	 * 
	 * @return the method library or <code>null</code> if it is not loaded
	 *         yet.
	 * @see #loadLibrary(URI, Map)
	 */
	MethodLibrary getFirstMethodLibrary();

	/**
	 * Gets the object with the specified GUID (Globally Unique Identifier)
	 * 
	 * @param guid
	 * @return
	 */
	EObject getEObject(String guid);

	Map<?, ?> getDefaultSaveOptions();

	/**
	 * Loads the given opposite features of all loaded objects in this resource
	 * set.
	 * 
	 * @param oppositeFeatures
	 */
	void loadOppositeFeatures(List<OppositeFeature> oppositeFeatures);

	/**
	 * Loads the given opposite features of those objects in this resource set
	 * whose GUIDs are specified in <code>GUIDs</code> set.
	 * 
	 * @param oppositeFeatures
	 * @param GUIDs
	 */
	void loadOppositeFeatures(List<OppositeFeature> oppositeFeatures, Set<String> GUIDs);

	/**
	 * Saves this resource set with the given save options.
	 * 
	 * @param options
	 *            save options
	 * @throws LibraryResourceException
	 */
	void save(Map<?, ?> options) throws LibraryResourceException;

	/**
	 * Unloads the given object in this resource set
	 * 
	 * @param object
	 * @return boolean <code>true</code> if the given object was unloaded
	 *         successfully
	 */
	boolean unload(EObject object);

	/**
	 * Unloads the specified resource of this resource set.
	 * 
	 * @param resource
	 * @param options
	 * @return TODO
	 */
	boolean unload(Resource resource, Map<?, ?> options);

	/**
	 * Reloads the given resources
	 * 
	 * @param resources
	 * @return resources that had been reloaded
	 */
	Collection<Resource> reloadResources(Collection<Resource> resources);

	/**
	 * Reloads the given persisted objects.
	 * 
	 * @param objects
	 * @return objects that have been reloaded
	 */
	Collection<EObject> reloadObjects(Collection<EObject> objects);

	/**
	 * Unloads this resource set. This will unload every resource in this
	 * resource set and reset the resource set to the state before loading the
	 * library.
	 * 
	 */
	void unload();

	/**
	 * Checks if there is any unresolved proxy detected in the library
	 * 
	 * @return
	 */
	boolean hasUnresolvedProxy();

	/**
	 * Subscribes the specified listener to listen to refresh events broadcasted
	 * whenever resources or objects of this resource set is refreshed/reloaded.
	 * 
	 * @param listener
	 * @see #reloadResources(Collection)
	 */
	void addRefreshListener(IRefreshListener listener);
	
	/**
	 * Unsubscribes the specified listener from listening to refresh events.
	 * 
	 * @param listener
	 */
	void removeRefreshListenter(IRefreshListener listener);

	ILibraryPersister getPersister();

	void checkModify(Resource[] resources, Object context)
			throws LibraryResourceException;

	IStatus checkModify(Collection<EObject> eObjects, Object context);
	
	Collection<Resource> loadNewResources();
}
