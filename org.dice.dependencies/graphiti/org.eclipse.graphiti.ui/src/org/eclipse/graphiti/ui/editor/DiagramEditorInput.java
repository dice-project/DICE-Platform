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
package org.eclipse.graphiti.ui.editor;

import java.util.Collections;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.internal.T;
import org.eclipse.graphiti.ui.internal.editor.DiagramEditorInternal;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

/**
 * The Class DiagramEditorInput.
 * 
 * The editor input for {@link DiagramEditor} diagram editors. Basically a
 * {@link TransactionalEditingDomain} with an already existing
 * {@link ResourceSet} is hosted to resolve an {@link EObject} from an
 * {@link URI} String. Some helper methods are added.
 * <p>
 * Note that neither the {@link URI} nor the {@link EObject} is held by an
 * instance of this object. Only the {@link TransactionalEditingDomain} (needs
 * to be passed to the constructor) is held and the creator of the domain is
 * also responsible for disposing it, unless the flag
 * <code>disposeEditingDomain</code> is set to <code>true</code> in one of the
 * appropriate constructors.
 * 
 * @see {@link IEditorInput}
 * @see {@link IPersistableElement}
 * @see {@link DiagramEditorFactory}
 * @see {@link DiagramEditor}
 */
public class DiagramEditorInput implements IEditorInput, IPersistableElement {

	/**
	 * The memento key for the stored {@link URI} string
	 */
	public static final String KEY_URI = "org.eclipse.graphiti.uri"; //$NON-NLS-1$

	/**
	 * The memento key for the stored object name
	 */
	public static final String KEY_OBJECT_NAME = "org.eclipse.graphiti.objectName"; //$NON-NLS-1$

	/**
	 * The memento key for the ID of the diagram type provider.
	 */
	public static String KEY_PROVIDER_ID = "org.eclipse.graphiti.providerId"; //$NON-NLS-1$

	/**
	 * The stored {@link URI} string
	 */
	private final String uriName;

	/**
	 * The stored {@link URI} in normalized form.
	 */
	private URI normalizedUri;

	/**
	 * The ID of the diagram type provider.
	 */
	private String providerId;

	/**
	 * The cached input name (e.g. for displaying the name in the navigation
	 * history without having to instantiate the {@link EObject})
	 * 
	 * @see #getLiveName()
	 */
	private String name;

	/**
	 * The cached input tooltip
	 * 
	 * @see #getLiveToolTipText()
	 */
	private String tooltip;

	/**
	 * The editor's {@link TransactionalEditingDomain} set by
	 * {@link #setEditorEditingDomain(TransactionalEditingDomain)}. Its
	 * ResourcSet is used to resolve {@link #uriName} into live objects.
	 * 
	 * @see #getEditingDomain()
	 */
	protected TransactionalEditingDomain editingDomain;

	/**
	 * Controls if this instance is responsible for disposing its
	 * {@link TransactionalEditingDomain} member or not.
	 */
	private boolean disposeEditingDomain;

	/**
	 * Creates a new {@link DiagramEditorInput} out of a {@link URI} string and
	 * a transactional editing domain. For resolving the {@link URI} to an
	 * {@link EObject} its {@link ResourceSet} is used. <br>
	 * The ResourceSet of the editing domain must have been already set from
	 * outside and has to contain an instance of {@link IWorkspaceCommandStack}
	 * as the used command stack. <br>
	 * A diagram type provider ID is held in this class.
	 * 
	 * @param diagramUriString
	 *            A {@link URI} string as returned by {@link URI#toString()}
	 *            that denotes the input's {@link EObject}
	 * @param domain
	 *            A {@link TransactionalEditingDomain} which contains the
	 *            {@link ResourceSet}. Note that the caller is responsible for
	 *            disposing this instance of the domain when it is no longer
	 *            needed!
	 * @param providerId
	 *            A {@link String} which holds the diagram type id. When it is
	 *            null, it is set later in {@link DiagramEditorInternal}
	 * @throws IllegalArgumentException
	 *             if <code>uriString</code> parameter is null <br>
	 *             if the command stack of the passed <code>domain</code> is no
	 *             <code>IWorkspaceCommandStack</code>
	 * @see URI
	 */
	public DiagramEditorInput(String diagramUriString, TransactionalEditingDomain domain, String providerId) {
		this(diagramUriString, domain, providerId, false);
	}

	/**
	 * Creates a new {@link DiagramEditorInput} out of a {@link URI} string and
	 * a transactional editing domain. For resolving the {@link URI} to an
	 * {@link EObject} its {@link ResourceSet} is used. <br>
	 * The ResourceSet of the editing domain must have been already set from
	 * outside and has to contain an instance of {@link IWorkspaceCommandStack}
	 * as the used command stack. <br>
	 * A diagram type provider ID is hold in this class.
	 * 
	 * @param diagramUriString
	 *            A {@link URI} string as returned by {@link URI#toString()}
	 *            that denotes the input's {@link EObject}
	 * @param domain
	 *            A {@link TransactionalEditingDomain} which contains the
	 *            {@link ResourceSet}. Unless <code>disposeEditingDomain</code>
	 *            is set, the caller is responsible for disposing this instance
	 *            of the domain when it is no longer needed!
	 * @param providerId
	 *            A {@link String} which holds the diagram type id. When it is
	 *            null, it is set later in {@link DiagramEditorInternal}
	 * @param disposeEditingDomain
	 *            If set to <code>true</code> this instance of
	 *            {@link DiagramEditorInput} will on dispose care about
	 *            disposing the passed {@link TransactionalEditingDomain} as
	 *            well. If <code>false</code> is passed the caller (or rather
	 *            the creator of the domain needs to care about that.
	 * @throws IllegalArgumentException
	 *             if <code>uriString</code> parameter is null <br>
	 *             if the command stack of the passed <code>domain</code> is no
	 *             <code>IWorkspaceCommandStack</code>
	 * @see URI
	 */
	public DiagramEditorInput(String diagramUriString, TransactionalEditingDomain domain, String providerId, boolean disposeEditingDomain) {
		if (diagramUriString == null) {
			throw new IllegalStateException("diagramUriString must not be null"); //$NON-NLS-1$
		}
		this.uriName = diagramUriString;
		setEditorEditingDomain(domain);
		this.disposeEditingDomain = disposeEditingDomain;
		setProviderId(providerId);
		this.normalizedUri = createNormalizedUri();
	}

	/**
	 * Creates a new {@link DiagramEditorInput} out of a {@link URI} string and
	 * a transactional editing domain. For resolving the {@link URI} to an
	 * {@link EObject} its {@link ResourceSet} is used. <br>
	 * The ResourceSet of the editing domain must have been already set from
	 * outside and has to contain an instance of {@link IWorkspaceCommandStack}
	 * as the used command stack. <br>
	 * A diagram type provider ID is hold in this class.Creates an input out of
	 * a {@link URI} string and a transactional editing domain. For resolving
	 * the {@link URI} to an {@link EObject} its {@link ResourceSet} is used.
	 * The ResourceSet of the editing domain must have been already set from
	 * outside. A diagram type provider ID is hold in this class.
	 * 
	 * @param diagramUri
	 *            a {@link URI} that denotes the input's {@link EObject}
	 * @param domain
	 *            A {@link TransactionalEditingDomain} which contains the
	 *            {@link ResourceSet}. Note that the caller is responsible for
	 *            disposing this instance of the domain when it is no longer
	 *            needed!
	 * @param providerId
	 *            A {@link String} which holds the diagram type id. When it is
	 *            null, it is set later in {@link DiagramEditorInternal}
	 * @throws IllegalArgumentException
	 *             if <code>uri</code> parameter is null <br>
	 *             if the command stack of the passed <code>domain</code> is no
	 *             <code>IWorkspaceCommandStack</code>
	 * @see #DiagramEditorInput(String, TransactionalEditingDomain)
	 * @see URI
	 */
	public DiagramEditorInput(URI diagramUri, TransactionalEditingDomain domain, String providerId) {
		this(diagramUri, domain, providerId, false);
	}

	/**
	 * Creates a new {@link DiagramEditorInput} out of a {@link URI} string and
	 * a transactional editing domain. For resolving the {@link URI} to an
	 * {@link EObject} its {@link ResourceSet} is used. <br>
	 * The ResourceSet of the editing domain must have been already set from
	 * outside and has to contain an instance of {@link IWorkspaceCommandStack}
	 * as the used command stack. <br>
	 * A diagram type provider ID is hold in this class.Creates an input out of
	 * a {@link URI} string and a transactional editing domain. For resolving
	 * the {@link URI} to an {@link EObject} its {@link ResourceSet} is used.
	 * The ResourceSet of the editing domain must have been already set from
	 * outside. A diagram type provider ID is hold in this class.
	 * 
	 * @param diagramUri
	 *            a {@link URI} that denotes the input's {@link EObject}
	 * @param domain
	 *            A {@link TransactionalEditingDomain} which contains the
	 *            {@link ResourceSet}. Unless <code>disposeEditingDomain</code>
	 *            is set, the caller is responsible for disposing this instance
	 *            of the domain when it is no longer needed!
	 * @param providerId
	 *            A {@link String} which holds the diagram type id. When it is
	 *            null, it is set later in {@link DiagramEditorInternal}
	 * @param disposeEditingDomain
	 *            If set to <code>true</code> this instance of
	 *            {@link DiagramEditorInput} will on dispose care about
	 *            disposing the passed {@link TransactionalEditingDomain} as
	 *            well. If <code>false</code> is passed the caller (or rather
	 *            the creator of the domain needs to care about that.
	 * @throws IllegalArgumentException
	 *             if <code>uri</code> parameter is null <br>
	 *             if the command stack of the passed <code>domain</code> is no
	 *             <code>IWorkspaceCommandStack</code>
	 * @see #DiagramEditorInput(String, TransactionalEditingDomain)
	 * @see URI
	 */
	public DiagramEditorInput(URI diagramUri, TransactionalEditingDomain domain, String providerId, boolean disposeEditingDomain) {
		if (diagramUri == null) {
			throw new IllegalStateException("diagramUri must not be null"); //$NON-NLS-1$
		}
		this.uriName = diagramUri.toString();
		setEditorEditingDomain(domain);
		this.disposeEditingDomain = disposeEditingDomain;
		setProviderId(providerId);
		this.normalizedUri = createNormalizedUri();
	}

	/**
	 * Creates a new {@link DiagramEditorInput} with a self created {@link}
	 * TransactionalEditingDomain editing domain, which must be disposed later
	 * on. <br>
	 * The ResourceSet of the editing domain must have been already set from
	 * outside and has to contain an instance of {@link IWorkspaceCommandStack}
	 * as the used command stack. <br>
	 * 
	 * @param diagram
	 *            A {@link Diagram}
	 * @param domain
	 *            A {@link TransactionalEditingDomain} which contains the
	 *            {@link ResourceSet}
	 * @param providerId
	 *            A {@link String} which holds the diagram type id.
	 * @param disposeEditingDomain
	 *            If set to <code>true</code> the created instance of
	 *            {@link DiagramEditorInput} will on dispose care about
	 *            disposing the passed {@link TransactionalEditingDomain} as
	 *            well. If <code>false</code> is passed the caller (or rather
	 *            the creator of the domain needs to care about that.
	 * @return A {@link DiagramEditorInput} editor input
	 */
	public static DiagramEditorInput createEditorInput(Diagram diagram, TransactionalEditingDomain domain, String providerId,
			boolean disposeEditingDomain) {
		final Resource resource = diagram.eResource();
		if (resource == null) {
			throw new IllegalArgumentException();
		}
		final String fragment = resource.getURIFragment(diagram);
		final URI fragmentUri = resource.getURI().appendFragment(fragment);
		DiagramEditorInput diagramEditorInput;
		if (disposeEditingDomain) {
			diagramEditorInput = new DiagramEditorInput(fragmentUri, domain, providerId, true);
		} else {
			diagramEditorInput = new DiagramEditorInput(fragmentUri, domain, providerId);
		}
		return diagramEditorInput;
	}

	/**
	 * Returns the diagram instance of this input
	 * 
	 * @return The diagram.
	 */
	public Diagram getDiagram() {
		return (Diagram) getAdapter(Diagram.class);
	}

	/**
	 * Returns the diagram type provider id.
	 * 
	 * @return The providerId.
	 */
	public String getProviderId() {
		return this.providerId;
	}

	/**
	 * Sets the diagram type provider id.
	 * 
	 * @param providerId
	 *            The providerId to set.
	 */
	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	/**
	 * Returns the factory ID for creating {@link DiagramEditorInput}s from
	 * mementos.
	 * 
	 * @return The ID of the associated factory
	 */
	@Override
	public String getFactoryId() {
		return DiagramEditorFactory.class.getName();
	}

	public void dispose() {
		if (this.disposeEditingDomain && this.editingDomain != null) {
			// Clear the editing domain of all resources with potentially
			// unsaved changes
			try {
				editingDomain.runExclusive(new Runnable() {

					@Override
					public void run() {
						editingDomain.getResourceSet().getResources().retainAll(Collections.EMPTY_LIST);
					}
				});
			} catch (final InterruptedException e) {
				T.racer().error(e.getMessage(), e);
			}

			// And dispose the editing domain
			this.editingDomain.dispose();
			this.editingDomain = null;
		}
	}

	/**
	 * @return Simply returns <code>null</code>.
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	/**
	 * @return The cached name or the input's {@link URI} string
	 * @see #getLiveName()
	 */
	@Override
	public String getName() {
		if (this.name != null) {
			return this.name;
		}
		return this.uriName;
	}

	/**
	 * Checks if a name is set for this instance
	 * 
	 * @return <code>true</code> in case a name is set, <code>false</code> in
	 *         name is <code>null</code>.
	 */
	protected boolean hasName() {
		return this.name != null;
	}

	/**
	 * Sets the name for this instance.
	 * 
	 * @param name
	 *            The name to set.
	 */
	protected void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The cached tooltip or the input's {@link URI} string
	 * @see #getLiveToolTipText()
	 */
	@Override
	public String getToolTipText() {
		if (this.tooltip != null) {
			return this.tooltip;
		}
		return getName();
	}

	/**
	 * Returns a name for the input from the {@link Diagram}, when possible.
	 * Otherwise it tries to get a human readable name from the EObject
	 * (retrieved via its URI) of the editor input.
	 * 
	 * @return A name from Diagram or from the EObject of the editor input or
	 *         <code>null</code> to indicate that a it can currently not be
	 *         obtained.
	 * @see IEditorInput#getName()
	 * @see #getLiveName()
	 */
	protected String getLiveName() {
		final Diagram diagram = getDiagram();
		if (diagram != null) {
			return diagram.getName();
		}
		final EObject object = getEObject();
		if (object != null) {
			final String name = GraphitiUiInternal.getEmfService().getObjectName(object);
			return name;
		}
		return null;
	}

	/**
	 * Returns a tooltip for the input from the {@link Diagram} object, when
	 * possible. Otherwise it tries to return the object's live name and type.
	 * 
	 * @return a tooltip from the Diagram or the hold object or
	 *         <code>null</code> to indicate that a it can currently not be
	 *         obtained.
	 * @see IEditorInput#getToolTipText()
	 */
	protected String getLiveToolTipText() {
		final Diagram diagram = getDiagram();
		if (diagram != null && diagram.getDiagramTypeId() != null) {
			final String id = diagram.getDiagramTypeId();
			if (id != null) {
				String tip = getLiveName();
				tip += " (" + id + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				return tip;
			}
		}
		final EObject object = getEObject();
		if (object != null) {
			final String name = getLiveName();
			final String typeName = object.eClass().getName();
			final String tip = name + " (" + typeName + ")"; //$NON-NLS-1$ //$NON-NLS-2$
			return tip;
		}
		return null;
	}

	/**
	 * Returns the concrete model element represented by the stored URI, if the
	 * requested adapter is either the same class or super class. In addition
	 * the method examines all composition parents and checks whether or not
	 * they are of the requested class or a sub class.
	 * <ul>
	 * <li>get model element for {@link URI}</li>
	 * <li>if adapter == modelElement.class return modelElement</li>
	 * <li>if adapter == modelElement.superclass return modelElement</li>
	 * <li>if adapter == modelElement.compositionParent.class return
	 * modelElement</li>
	 * <li>if adapter == ResourceSet return resourceSet</li>
	 * <li>if adapter == TransactionalEditingDomain return editingDomain</li>
	 * </ul>
	 * 
	 * @param adapter
	 *            The adapter class to look up
	 * @return A object castable to the given class, or <code>null</code> if
	 *         this object does not have an adapter for the given class
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {

		if (Diagram.class.isAssignableFrom(adapter)) {
			final EObject obj = getEObject();
			if (obj instanceof Diagram) {
				return obj;
			}
		} else if (EObject.class.isAssignableFrom(adapter)) {
			EObject eObject = null;
			try {
				eObject = getEObject(adapter);
			} catch (final Exception e) {
				// Not able to adapt to eObject, object has been deleted, file
				// was deleted...
				// adapt must deliver null
				T.racer().debug(e.getMessage());
				return null;
			}
			return eObject;
		} else if (TransactionalEditingDomain.class.isAssignableFrom(adapter)) {
			return getEditingDomain();
		} else if (ResourceSet.class.isAssignableFrom(adapter)) {
			return getEditingDomain().getResourceSet();
		}
		return null;
	}

	/**
	 * Saves {@link URI} string, object name and provider ID to the given
	 * {@link IMemento}.
	 * 
	 * @param memento
	 *            The memeto to store the information in
	 */
	@Override
	public void saveState(IMemento memento) {
		// Do not store anything for deleted objects
		boolean exists = exists();
		if (!exists) {
			return;
		}
		// Store object name, URI and diagram type provider ID
		memento.putString(KEY_URI, this.uriName);
		memento.putString(KEY_OBJECT_NAME, getName());
		memento.putString(KEY_PROVIDER_ID, this.providerId);
	}

	/**
	 * Called from constructor to populate the editor's
	 * {@link TransactionalEditingDomain} to its input. The resourceSet of the
	 * TransactionalEditingDomain is needed on requests to resolve the stored
	 * {@link #uriName}.
	 * 
	 * @param editingDomain
	 *            the {@link TransactionalEditingDomain} to set
	 * @throws IllegalArgumentException
	 *             if <code>editingDomain</code> parameter is null
	 * @see #getEditingDomain()
	 */
	private void setEditorEditingDomain(TransactionalEditingDomain editingDomain) {
		if (editingDomain == null) {
			throw new IllegalArgumentException("EditingDomain must not be null"); //$NON-NLS-1$
		}

		this.editingDomain = editingDomain;
		CommandStack commandStack = this.editingDomain.getCommandStack();
		if (commandStack == null || !(commandStack instanceof IWorkspaceCommandStack)) {
			throw new IllegalStateException("Command stack of the editing domain must be an IWorkspaceCommandStack"); //$NON-NLS-1$
		}
		final EObject obj = getEObject(EObject.class);
		if (obj != null) {
			if (this.name == null) {
				this.name = getLiveName();
			} else {
				final String newName = getLiveName();
				if (newName != null) {
					this.name = newName;
				}
			}
			this.tooltip = getLiveToolTipText();
		}
	}

	/**
	 * Returns the model object the editor input is working on.
	 * 
	 * @return The object or <code>null</code> if no such object can be
	 *         obtained, e.g. if the input's editor is not yet initialised.
	 */
	public final EObject getEObject() {
		final EObject object = getEObject(EObject.class);
		return object;
	}

	/**
	 * @return The {@link URI} string this input and its editors operate on
	 */
	public final String getUriString() {
		return this.uriName;
	}

	/**
	 * Checks if the diagram this input represents exist.
	 * <p>
	 * Note: The editor gets only restored, when <code>true</code> is returned.
	 * 
	 * @return <code>true</code> if the input's state denotes a living EMF
	 *         object <br>
	 */
	@Override
	public boolean exists() {
		if (this.uriName == null) {
			return false;
		}
		final URI uri = getUri();
		if (uri == null) {
			return false;
		}
		EObject eObject = null;
		try {
			eObject = getEObject();
		} catch (final Exception e) {
			// The object cannot be resolved --> it does not exist (prevent to
			// appear in navigation history)
			return false;
		}
		return eObject != null;
	}

	/**
	 * @return this input if it is persistable, otherwise null
	 */
	@Override
	public IPersistableElement getPersistable() {
		IPersistableElement element = null;
		EObject obj = null;
		try {
			obj = getEObject();
		} catch (WrappedException e) {
			// Ignore (object may not exists, even resource may be gone
		}
		if (obj != null) {
			// The input is only persistable in case an EObject can be retrieved
			// (this is e.g. relevant for removing entries from the editor
			// navigation history for deleted objects)
			element = this;
		}
		return element;
	}

	/**
	 * This method returns an instance which is either of the same
	 * <code>Class</code> object a sub class or sub interface of the class
	 * defined by <code>adapter</code> - <code>null</code> otherwise. The
	 * implementation also checks composite children.
	 * 
	 * @param adapter
	 * 
	 * @return
	 */
	private EObject getEObject(Class<EObject> adapter) {
		final URI uri = getUri();
		if (uri != null) {
			final TransactionalEditingDomain editingDomain = getEditingDomain();
			if (editingDomain != null) {
				// First try the URI resolution without loading not yet loaded
				// resources
				// because calling with loadOnDemand will _always_ create a new
				// Resource
				// instance for newly created and not yet saved Resources, no
				// matter if
				// they already exist within the ResourceSet or not
				EObject modelElement = editingDomain.getResourceSet().getEObject(uri, false);
				if (modelElement == null) {
					modelElement = editingDomain.getResourceSet().getEObject(uri, true);
					if (modelElement == null) {
						return null;
					}
				}

				modelElement.eResource().setTrackingModification(true);

				// Climb up the chain of composition. Also uses a counter to
				// prevent infinite loops in case refImmediateComposite does not
				// return null
				int i = 0;
				EObject o = modelElement;
				do {
					if (adapter.isAssignableFrom(o.getClass())) {
						return o;
					}

					o = o.eContainer();
				} while (o != null && ++i < 1000);
			}
		}
		return null;
	}

	/**
	 * @return the resolved {@link URI} or <code>null</code> in case of failures
	 */
	private URI getUri() {
		if (this.uriName == null) {
			return null;
		}
		final URI uri = URI.createURI(this.uriName);
		return uri;
	}

	/**
	 * @return The current {@link TransactionalEditingDomain} or
	 *         <code>null</code>
	 */
	private TransactionalEditingDomain getEditingDomain() {
		return this.editingDomain;
	}

	/**
	 * Checks if this instance of the input represents the same object as the
	 * given instance.
	 * 
	 * @param obj
	 *            The object to compare this instance with.
	 * @return <code>true</code> if the represented objects are the same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DiagramEditorInput other = (DiagramEditorInput) obj;
		if (normalizedUri == null) {
			if (other.normalizedUri != null) {
				return false;
			}
		} else if (!normalizedUri.equals(other.normalizedUri)) {
			return false;
		}
		if (providerId == null) {
			if (other.providerId != null) {
				return false;
			}
		} else if (!providerId.equals(other.providerId)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((normalizedUri == null) ? 0 : normalizedUri.hashCode());
		result = prime * result + ((providerId == null) ? 0 : providerId.hashCode());
		return result;
	}

	/**
	 * Used for logging only!
	 */
	@Override
	public String toString() {
		final String s = super.toString() + " uriName: " + this.uriName + " on TransactionalEditingDomain " //$NON-NLS-1$ $NON-//$NON-NLS-2$
				+ String.valueOf(getEditingDomain()) + " (null=OK)"; //$NON-NLS-1$
		return s;
	}

	private URI createNormalizedUri() {
		URI ret = null;
		if (getEditingDomain() != null && getEditingDomain().getResourceSet() != null) {
			URIConverter uriConverter = getEditingDomain().getResourceSet().getURIConverter();
			if (uriConverter != null) {
				ret = uriConverter.normalize(this.getUri());
			}
		}
		return ret;
	}
}