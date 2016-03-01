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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.epf.library.events.ILibraryChangeListener;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.ExtendedOpposite;
import org.eclipse.epf.uma.util.ModifiedTypeMeta;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;
import org.eclipse.ui.IPropertyListener;

/**
 * The interface for a Library Manager.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public interface ILibraryManager {

	/**
	 * Creates a new method library.
	 * 
	 * @param name
	 *            a name for the new method library
	 * @param args
	 *            method library specific arguments
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary createMethodLibrary(String name,
			Map<String, Object> args) throws LibraryServiceException;

	/**
	 * Opens a method library.
	 * 
	 * @param uri
	 *            a method library URI
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary openMethodLibrary(java.net.URI uri)
			throws LibraryServiceException;

	/**
	 * Opens a method library.
	 * 
	 * @param args
	 *            method library specific arguments
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary openMethodLibrary(Map<String, Object> args)
			throws LibraryServiceException;

	/**
	 * Reopens the managed method library.
	 * 
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public MethodLibrary reopenMethodLibrary() throws LibraryServiceException;

	/**
	 * Saves the managed method library.
	 * 
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void saveMethodLibrary() throws LibraryServiceException;

	/**
	 * Discards all changes made to the managed method library.
	 */
	public void discardMethodLibraryChanges();

	/**
	 * Closes the managed method library.
	 * 
	 * @return a method library
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void closeMethodLibrary() throws LibraryServiceException;

	/**
	 * Gets the managed method library.
	 * 
	 * @return a method library
	 */
	public MethodLibrary getMethodLibrary();

	/**
	 * Sets the managed method library.
	 * 
	 * @param library
	 *            a method library
	 */
	public void setMethodLibrary(MethodLibrary library);

	/**
	 * Gets the URI of the managed method library.
	 * 
	 * @return a <code>org.eclipse.emf.common.util.URI</code>
	 */
	public java.net.URI getMethodLibraryURI();

	/**
	 * Gets the absolute path to the managed method library. For distributed
	 * library, this is the library's workspace path.
	 * 
	 * @return an absolute path to the method library
	 */
	public String getMethodLibraryLocation();

	/**
	 * Gets the adapter factory for the managed method library.
	 * 
	 * @return an adapter factory
	 */
	public ComposedAdapterFactory getAdapterFactory();

	/**
	 * Gets the editing domain for the managed method library.
	 * 
	 * @return an editing domain
	 */
	public AdapterFactoryEditingDomain getEditingDomain();

	/**
	 * Registers an editing domain with the managed method library.
	 * 
	 * @param domain
	 *            an editing domain
	 */
	public void registerEditingDomain(AdapterFactoryEditingDomain domain);

	public void unregisterEditingDomain(AdapterFactoryEditingDomain domain);
	
	/**
	 * Adds a listener to monitor changes to the managed method library.
	 * 
	 * @param listener
	 *            a library change listener
	 */
	public void addListener(ILibraryChangeListener listener);

	/**
	 * Removes a listener that was added to monitor changes to the managed
	 * method library.
	 * 
	 * @param listener
	 *            a library change listener
	 */
	public void removeListener(ILibraryChangeListener listener);

	/**
	 * Adds a listener to monitor resource changes in the managed method
	 * library.
	 * 
	 * @param listener
	 *            a property change listener
	 */
	public void addPropertyListener(IPropertyListener listener);

	/**
	 * Adds a listener to monitor resource changes in the managed method
	 * library.
	 * 
	 * @param listener
	 *            a property change listener.
	 */
	public void removePropertyListener(IPropertyListener listener);

	/**
	 * Starts listening to command processing on a command stack.
	 * 
	 * @param commandStack
	 *            a command stack
	 */
	public void startListeningTo(CommandStack commandStack);

	/**
	 * Stops listening to command processing on a command stack.
	 * 
	 * @param commandStack
	 *            a command stack
	 */
	public void stopListeningTo(CommandStack commandStack);

	/**
	 * Starts listening to change notifications sent from an adapter factory.
	 * 
	 * @param adapterFactory
	 *            an adapter factory
	 */
	public void startListeningTo(ComposedAdapterFactory adapterFactory);

	/**
	 * Stops listening to change notifications sent from an adapter factory.
	 * 
	 * @param adapterFactory
	 *            an adapter factory
	 */
	public void stopListeningTo(ComposedAdapterFactory adapterFactory);

	/**
	 * Gets a method element from the managed method library.
	 * 
	 * @param guid
	 *            the method element's GUID.
	 * 
	 * @return a method element of <code>null</code>
	 */
	public MethodElement getMethodElement(String guid);

	/**
	 * Checks whether the managed method library is read only.
	 * 
	 * @return <code>true</code> if the method library is read only
	 */
	public boolean isMethodLibraryReadOnly();

	/**
	 * Checks whether the managed method library content has been modified.
	 * 
	 * @return <code>true</code> if the managed method library content has
	 *         been modified
	 */
	public boolean isMethodLibraryModified();

	/**
	 * Checks whether the managed method library has any unresolved proxy.
	 * 
	 * @return <code>true</code> if the managed method library has an
	 *         unresolved proxy.
	 */
	public boolean hasUnresolvedProxy();

	/**
	 * Reloads the given resources.
	 * 
	 * @param resources
	 *            a collection of resources
	 * @return a collection of resources that have reloaded
	 */
	public Collection<Resource> reloadResources(Collection<Resource> resources);

	/**
	 * Gets the options used for saving the managed method library.
	 * 
	 * @return a map of method library specific save options
	 */
	public Map<String, Object> getSaveOptions();

	/**
	 * Adds a new method plug-in to the managed method library.
	 * 
	 * @param plugin
	 *            a method plug-in
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void addMethodPlugin(MethodPlugin plugin)
			throws LibraryServiceException;

	/**
	 * Disposes all resources allocated by this library manager.
	 */
	public void dispose();

	/**
	 * Gets the resource manager for the method library.
	 * 
	 * @return the resource manager for the method library
	 */
	public ILibraryResourceManager getResourceManager();
	
	/**
	 * Backup method library
	 * 
	 * @param path
	 * 		backup foler file path	
	 */
	public void backupMethodLibrary(String path);
	
	/**
	 * Register a loaded method library.
	 * 
	 * @param type
	 *            the given loaded method library
	 * @param params
	 *            method library specific arguments
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void registerMethodLibrary(MethodLibrary lib, 
			Map<String, Object> params) throws LibraryServiceException;
	
	/**
	 * Unregister a registered method library.
	 * 
	 * @throw <code>LibraryServiceException</code> if an error occurs while
	 *        performing the operation
	 */
	public void unRegisterMethodLibrary() throws LibraryServiceException;

	public Collection<UserDefinedTypeMeta> getUserDefinedTypes();
	
	public void addUserDefineType(UserDefinedTypeMeta meta);
		
	public UserDefinedTypeMeta getUserDefineType(String id);
	
	public boolean isUserDefinedTypeLoaded();
	
	public void setUserDefinedTypeLoaded(boolean b);
	
	public void prepareToLoadUserDefinedTypes();
	
	public Collection<ModifiedTypeMeta> getModifiedTypes();
	
	public void addModifiedType(ModifiedTypeMeta meta);
		
	public ModifiedTypeMeta getModifiedType(String id);
	
	public List<ExtendedOpposite> getLoadedOpposites();
	
}
