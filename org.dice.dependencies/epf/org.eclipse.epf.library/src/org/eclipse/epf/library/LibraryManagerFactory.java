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
package org.eclipse.epf.library;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.library.internal.LibraryManagerElement;
import org.eclipse.epf.services.Services;
import org.osgi.framework.Bundle;

/**
 * Manages the creation of Library Manager instances.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class LibraryManagerFactory {

	/**
	 * The extension point namespace.
	 */
	public static final String EXTENSION_POINT_NAMESPACE = "org.eclipse.epf.library"; //$NON-NLS-1$

	/**
	 * The extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "libraryManagers"; //$NON-NLS-1$

	/**
	 * The "class" attribute name.
	 */
	public static final String CLASS_ATTRIB_NAME = "class"; //$NON-NLS-1$

	/**
	 * The "type" attribute name.
	 */
	public static final String TYPE_ATTRIB_NAME = "type"; //$NON-NLS-1$

	/**
	 * The "typeName" attribute name.
	 */
	public static final String TYPE_NAME_ATTRIB_NAME = "typeName"; //$NON-NLS-1$

	private static final String VERSION_ATTRIB_NAME = "version"; //$NON-NLS-1$

	// The shared instance.
	private static LibraryManagerFactory instance = null;

	// A map of library manager configuration elements.
	private Map<String, LibraryManagerElement> libraryManagerElements = new HashMap<String, LibraryManagerElement>();

	/**
	 * Returns the shared instance.
	 */
	public static LibraryManagerFactory getInstance() {
		if (instance == null) {
			synchronized (LibraryManagerFactory.class) {
				if (instance == null) {
					instance = new LibraryManagerFactory();
				}
			}
		}
		return instance;
	}

	/**
	 * Creates a new instance.
	 */
	private LibraryManagerFactory() {
		init();
	}

	/**
	 * Performs the necessary initialization.
	 */
	protected void init() {
		// Process the "org.eclipse.epf.library.libraryManagers" extension point
		// contributors.
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(
				EXTENSION_POINT_NAMESPACE, EXTENSION_POINT_NAME);
		if (extensionPoint != null) {
			IExtension[] extensions = extensionPoint.getExtensions();
			for (int i = 0; i < extensions.length; i++) {
				IExtension extension = extensions[i];
				String pluginId = extension.getNamespaceIdentifier();
				Bundle bundle = Platform.getBundle(pluginId);
				IConfigurationElement[] configElements = extension
						.getConfigurationElements();
				for (int j = 0; j < configElements.length; j++) {
					IConfigurationElement configElement = configElements[j];
					try {
						String className = configElement
								.getAttribute(CLASS_ATTRIB_NAME);
						String typeId = configElement
								.getAttribute(TYPE_ATTRIB_NAME);
						String typeName = configElement
								.getAttribute(TYPE_NAME_ATTRIB_NAME);
						if (className != null && className.trim().length() > 0
								&& typeId != null && typeId.trim().length() > 0
								&& typeName != null
								&& typeName.trim().length() > 0) {							
							int version = Services.parseVersion(configElement.getAttribute(VERSION_ATTRIB_NAME));
							LibraryManagerElement provider = libraryManagerElements.get(typeId);
							if(provider == null || provider.getVersion() < version) {
								provider = new LibraryManagerElement(
										bundle, className, typeId, typeName, version);
								libraryManagerElements.put(typeId, provider);
							}
						}
					} catch (Exception e) {
						LibraryPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
	}

	/**
	 * Creates a new library manager instance.
	 * 
	 * @param type
	 *            the method library type
	 * @return a library manager
	 * @throw <code>CreateLibraryManagerException</code> if an error occurs
	 *        while performing the operation
	 */
	public ILibraryManager createLibraryManager(String type)
			throws CreateLibraryManagerException {
		LibraryManagerElement provider = (LibraryManagerElement) libraryManagerElements
				.get(type);
		if (provider == null) {
			throw new CreateLibraryManagerException();
		}
		try {
			Class clazz = provider.getBundle().loadClass(
					provider.getClassName());
			return (ILibraryManager) clazz.newInstance();
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
			throw new CreateLibraryManagerException(e);
		}
	}

	/**
	 * Returns all the library types registered via the
	 * "org.eclipse.epf.library.libraryManagers" extension point.
	 * 
	 * @return a map of library types
	 */
	public Map getLibraryTypes() {
		Map<String, String> types = new HashMap<String, String>();
		for (Iterator it = libraryManagerElements.keySet().iterator(); it
				.hasNext();) {
			String typeId = (String) it.next();
			String typeName = ((LibraryManagerElement) libraryManagerElements
					.get(typeId)).getTypeName();
			types.put(typeId, typeName);
		}
		return types;
	}

}
