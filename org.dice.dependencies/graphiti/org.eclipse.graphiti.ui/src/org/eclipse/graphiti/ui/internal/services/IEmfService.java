/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services;

import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Provides EMF Services, in particular with respect to {@link EObject},
 * {@link URI}, {@link ResourceSet}, and {@link TransactionalEditingDomain}.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IEmfService {

	/**
	 * Returns the human readable name of a given object or the EMF id if no
	 * name can be found. The method tries to access a modeled attribute "name"
	 * or "id" in this order. If no attribute with this name is modeled, the
	 * first attribute whose value is a String or
	 * {@link TranslatableTextFragment} is returned, otherwise <code>null</code>
	 * .
	 * 
	 * @param obj
	 *            the object to get a name for
	 * @return the value of attribute "name" or the EMF id if no attribute
	 *         "name" exists
	 * 
	 */
	public abstract String getObjectName(final Object obj);

	/**
	 * Tries to convert the given object to a {@link EObject}
	 * <ul>
	 * <li>using {@link #adaptObject(Object, Class)},</li>
	 * <li>unwrapping an {@link IStructuredSelection}.</li>
	 * </ul>
	 * 
	 * @param object
	 *            the object to convert
	 * @return the target object or <code>null</code>
	 * 
	 * @see #getEObject(Object)
	 */
	public abstract EObject getEObject(Object object);


	/**
	 * Returns the Eclipse file for the given {@link EObject}'s {@link Resource}
	 * .
	 * 
	 * Note that the file is <code>null</code> for objects in
	 * <ul>
	 * <li>archives,</li>
	 * <li>closed projects,</li>
	 * <li>not yet persisted resources or not yet persisted EObjects in already
	 * persisted resources. In this respect this methods behaves asymmetric to
	 * the handle-only resource APIs like {@link IProject#getFile(String)}.</li>
	 * </ul>
	 * 
	 * @param object
	 *            the model object to get the file for
	 * @return the partition file or <code>null</code> under the mentioned
	 *         circumstances
	 * 
	 * @see #getFile(URI, ResourceSet)
	 */
	public abstract IFile getFile(EObject object);

	/**
	 * Returns the Eclipse file for the given {@link URI}.
	 * 
	 * Note that the file is <code>null</code> for objects in
	 * <ul>
	 * <li>archives,</li>
	 * <li>closed projects,</li>
	 * <li>not yet persisted resources or not yet persisted EObjects in already
	 * persisted resources. In this respect this methods behaves asymmetric to
	 * the handle-only resource APIs like {@link IProject#getFile(String)}.</li>
	 * </ul>
	 * 
	 * @param uri
	 *            the URI to get the file for
	 * @param editingDomain
	 *            the respective TransactionalEditingDomain
	 * @return the file or <code>null</code> under the mentioned circumstances
	 * 
	 * @see #getFile(EObject)
	 */
	public abstract IFile getFile(URI uri, TransactionalEditingDomain editingDomain);

	/**
	 * Returns the Eclipse file for the given {@link URI}.
	 * 
	 * Note that the file is <code>null</code> for objects in
	 * <ul>
	 * <li>archives,</li>
	 * <li>closed projects,</li>
	 * <li>not yet persisted resources or not yet persisted EObjects in already
	 * persisted resources. In this respect this methods behaves asymmetric to
	 * the handle-only resource APIs like {@link IProject#getFile(String)}.</li>
	 * </ul>
	 * 
	 * @param uri
	 *            the URI to get the file for
	 * @param resourceSet
	 *            the respective ResourceSet
	 * @return the file or <code>null</code> under the mentioned circumstances
	 * 
	 * @see #getFile(EObject)
	 */
	public abstract IFile getFile(URI uri, ResourceSet resourceSet);


	/**
	 * Saves the given {@link TransactionalEditingDomain} by saving all its
	 * dirty {@link Resource}s.
	 * 
	 * @param editingDomain
	 *            The {@link TransactionalEditingDomain} to be saved.
	 */
	public abstract void save(TransactionalEditingDomain editingDomain) throws WrappedException;
	
	
	/**
	 * Saves the given {@link TransactionalEditingDomain} by saving all its
	 * dirty {@link Resource}s.
	 * 
	 * @param editingDomain
	 *            The {@link TransactionalEditingDomain} to be saved.
	 * @param options
	 *            Save options for each of the {@link Resource}s to save.
	 */
	public abstract void save(final TransactionalEditingDomain editingDomain, final Map<Resource, Map<?, ?>> options);

	/**
	 * Creates an extended string presentation of the given {@link EObject},
	 * including its type and attributes
	 * 
	 * @param o
	 *            the object to create a string presentation for
	 * @param result
	 *            the string buffer to store the result into
	 * @return the same string buffer as <code>result</code> per convenience
	 * 
	 * @see #toString(EObject)
	 */
	public abstract StringBuilder toString(final EObject o, final StringBuilder result);

	/**
	 * If given file is a valid emf resource and it contains a diagram as first
	 * root element, this methods return the diagram. Otherwise this method
	 * returns null.
	 * 
	 * @param file
	 *            a valid emf (diagram) resource
	 * @return diagram or null
	 */
	public abstract Diagram getDiagramFromFile(IFile file, ResourceSet resourceSet);

	/**
	 * Retrieves the workspace-local string location of the given {@link IFile},
	 * constructs a potentially normalized platform resource {@link URI} from it
	 * and returns it.
	 * 
	 * @param file
	 *            The file to construct the URI for
	 * @param resourceSet
	 *            The {@link ResourceSet} to use for the normalization (can be
	 *            <code>null</code>, in this case no normalization is done).
	 * @return The platform resource URI for the given file.
	 */
	public abstract URI getFileURI(IFile file, ResourceSet resourceSet);

	/**
	 * Maps the fileURI to an URI which points directly to the Diagram Object.
	 * This methods assumes the Diagram object is the first root object in the
	 * given file.
	 * 
	 * @param diagramFileUri
	 *            URI of the diagramFile
	 * @return URI of the diagram
	 */
	public abstract URI mapDiagramFileUriToDiagramUri(URI diagramFileUri);

}