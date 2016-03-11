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

import java.util.HashMap;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.epf.persistence.PersistencePlugin;
import org.eclipse.epf.services.Services;
import org.osgi.framework.Bundle;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class PersistenceService {
	private static final boolean DEBUG = PersistencePlugin.getDefault().isDebugging();
	
	public static final PersistenceService INSTANCE = new PersistenceService();

	private static HashMap<String, FactoryDef> typeToFactoryDefMap;

	private PersistenceService() {

	}

	/**
	 * Creates a new ResourceSet for the given persistence type
	 * 
	 * @param type
	 * @return
	 */
	public ILibraryResourceSet createResourceSet(String type) {
		ILibraryResourceSetFactory factory = getFactory(type);
		if(factory != null) {
			return factory.createLibraryResourceSet();
		}
		else {
			throw new IllegalArgumentException("Unknown type: " + type); //$NON-NLS-1$
		}
	}

	private static class FactoryDef {
		Bundle bundle;

		String type;

		String className;
		
		int version;
		
		ILibraryResourceSetFactory instance;

		/**
		 * @param bundle
		 * @param type
		 * @param className
		 * @param instance
		 */
		private FactoryDef(Bundle bundle, String type, String className, int version) {
			super();
			this.bundle = bundle;
			this.type = type;
			this.className = className;
			this.version = version;
		}
				
	}
	
	public static final ILibraryResourceSetFactory getFactory(String persistenceType) {
		if (typeToFactoryDefMap == null) {
			typeToFactoryDefMap = new HashMap<String, FactoryDef>();
			// Process the "org.eclipse.epf.library.persistence.migrators"
			// extension point
			// contributors.
			IExtensionRegistry extensionRegistry = Platform
					.getExtensionRegistry();
			IExtensionPoint extensionPoint = extensionRegistry
					.getExtensionPoint(
							"org.eclipse.epf.library.persistence", "resourceSetFactories"); //$NON-NLS-1$ //$NON-NLS-2$
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
									.getAttribute("class"); //$NON-NLS-1$
							String type = configElement
									.getAttribute("type"); //$NON-NLS-1$
							if (className != null
									&& className.trim().length() > 0
									&& type != null
									&& type.trim().length() > 0) {
								int version = Services.parseVersion(configElement.getAttribute("version")); //$NON-NLS-1$
								FactoryDef factoryDef = typeToFactoryDefMap.get(type);								
								if(factoryDef == null || factoryDef.version < version) {
									typeToFactoryDefMap.put(type, new FactoryDef(bundle,
											type, className, version));									
								}
							}
						} catch (Exception e) {
							CommonPlugin.INSTANCE.log(e);
						}
					}
				}
			}
		}
		FactoryDef factory = (FactoryDef) typeToFactoryDefMap.get(persistenceType);
		if (factory != null) {
			if (factory.instance == null) {
				try {
					ILibraryResourceSetFactory resourceSetFactory = (ILibraryResourceSetFactory) factory.bundle.loadClass(
							factory.className).newInstance();
					if(persistenceType.equals(resourceSetFactory.getPersistenceType())) {
						factory.instance = resourceSetFactory;
					}
					else {
						PersistencePlugin.getDefault().getLogger().logError(factory.className +
								" is registered with the wrong persistence type '" + persistenceType + //$NON-NLS-1$
								"'. The correct type must be '" + resourceSetFactory.getPersistenceType() + "'"); //$NON-NLS-1$ //$NON-NLS-2$
					}
				} catch (Exception e) {
					PersistencePlugin.getDefault().getLogger().logError(e);
					if(DEBUG) {
						e.printStackTrace();
					}
				}
			}
			return factory.instance;
		}
		return null;
	}

}
