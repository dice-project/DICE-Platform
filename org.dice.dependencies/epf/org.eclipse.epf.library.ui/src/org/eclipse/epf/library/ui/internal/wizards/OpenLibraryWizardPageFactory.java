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
package org.eclipse.epf.library.ui.internal.wizards;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.wizards.OpenLibraryWizardPage;
import org.osgi.framework.Bundle;

/**
 * Manages the Open Library wizard pages defined via the
 * "org.eclipse.epf.library.ui.openLibraryWizardPages" extension point.
 * 
 * @author Kelvin Low
 * @since 1.2
 * @deprecated
 */
public class OpenLibraryWizardPageFactory {

	/**
	 * The extension point namespace.
	 */
	public static final String EXTENSION_POINT_NAMESPACE = "org.eclipse.epf.library.ui"; //$NON-NLS-1$

	/**
	 * The extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "openLibraryWizardPages"; //$NON-NLS-1$

	// The shared instance.
	private static OpenLibraryWizardPageFactory instance = new OpenLibraryWizardPageFactory();

	// A map of wizard page providers.
	private Map<String, OpenLibraryWizardPageContribution> wizardPageProviders = new HashMap<String, OpenLibraryWizardPageContribution>();

	// A list of wizard page instances.
	private Map<String, OpenLibraryWizardPage> wizardPages = new HashMap<String, OpenLibraryWizardPage>();

	/**
	 * Returns the shared instance.
	 */
	public static OpenLibraryWizardPageFactory getInstance() {
		return instance;
	}

	/**
	 * Creates a new instance.
	 */
	private OpenLibraryWizardPageFactory() {
		init();
	}

	/**
	 * Performs the necessary initialization.
	 */
	protected void init() {
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
						String className = configElement.getAttribute("class"); //$NON-NLS-1$
						String typeId = configElement.getAttribute("type"); //$NON-NLS-1$
						String title = configElement.getAttribute("title"); //$NON-NLS-1$
						String description = configElement
								.getAttribute("description"); //$NON-NLS-1$						
						if (className != null && className.trim().length() > 0
								&& typeId != null && typeId.trim().length() > 0
								&& title != null && title.trim().length() > 0
								&& description != null
								&& description.trim().length() > 0) {
							if (!wizardPageProviders.containsKey(typeId)) {
								OpenLibraryWizardPageContribution provider = new OpenLibraryWizardPageContribution(
										bundle, className, typeId, title,
										description);
								wizardPageProviders.put(typeId, provider);
							}
						}
					} catch (Exception e) {
						LibraryUIPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
	}

	/**
	 * Creates a wizard page.
	 * 
	 * @return a <code>OpenLibraryWizardPage</code>
	 */
	public OpenLibraryWizardPage createWizardPage(String pageId, String typeId) {
		OpenLibraryWizardPage wizardPage = (OpenLibraryWizardPage) wizardPages
				.get(typeId);
		if (wizardPage != null) {
			return wizardPage;
		}

		OpenLibraryWizardPageContribution provider = (OpenLibraryWizardPageContribution) wizardPageProviders
				.get(typeId);
		if (provider != null) {
			Bundle bundle = provider.getBundle();
			String className = provider.getClassName();
			Class wizardPageClass;
			Class[] argClass = new Class[] { String.class };
			Object[] argValue = new Object[] { pageId };
			Constructor constructor;
			try {
				wizardPageClass = bundle.loadClass(className);
				constructor = wizardPageClass.getConstructor(argClass);
				wizardPage = (OpenLibraryWizardPage) constructor
						.newInstance(argValue);
				wizardPage.setTitle(provider.getTitle());
				wizardPage.setDescription(provider.getDescription());
				wizardPages.put(typeId, wizardPage);
			} catch (Exception e) {
				LibraryUIPlugin.getDefault().getLogger().logError(e);
			}
		}
		return wizardPage;
	}

	/**
	 * Returns a new or cached wizard page.
	 * 
	 * @return a <code>NewLibraryWizardPage</code> object
	 */
	public OpenLibraryWizardPage getWizardPage(String typeId) {
		return (OpenLibraryWizardPage) wizardPages.get(typeId);
	}

}
