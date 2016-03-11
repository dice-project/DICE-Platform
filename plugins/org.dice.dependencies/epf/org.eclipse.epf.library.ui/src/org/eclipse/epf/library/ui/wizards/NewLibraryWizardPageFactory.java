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
package org.eclipse.epf.library.ui.wizards;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.serviceability.DebugTrace;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.internal.wizards.NewLibraryWizardPageContribution;
import org.osgi.framework.Bundle;

/**
 * Manages the New Library wizard pages defined via the
 * "org.eclipse.epf.library.ui.newLibraryWizardPages" extension point.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class NewLibraryWizardPageFactory {

	/**
	 * The extension point namespace.
	 */
	public static final String EXTENSION_POINT_NAMESPACE = "org.eclipse.epf.library.ui"; //$NON-NLS-1$

	/**
	 * The extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "newLibraryWizardPages"; //$NON-NLS-1$

	// If true, debug tracing is enabled.
	private static boolean debug = LibraryUIPlugin.getDefault().isDebugging();

	// The shared instance.
	private static NewLibraryWizardPageFactory instance = null;

	// A map of wizard page providers.
	private Map<String, NewLibraryWizardPageContribution> wizardPageProviders = new HashMap<String, NewLibraryWizardPageContribution>();

	// A list of wizard page instances.
	private Map<String, NewLibraryWizardPage> wizardPages = new HashMap<String, NewLibraryWizardPage>();

	/**
	 * Returns the shared instance.
	 */
	public static NewLibraryWizardPageFactory getInstance() {
		if (instance == null) {
			synchronized (NewLibraryWizardPageFactory.class) {
				if (instance == null) {
					instance = new NewLibraryWizardPageFactory();
				}
			}
		}
		return instance;
	}

	/**
	 * Creates a new instance.
	 */
	private NewLibraryWizardPageFactory() {
		init();
	}

	/**
	 * Performs the necessary initialization.
	 */
	protected void init() {
		if (debug) {
			DebugTrace.print(this, "init"); //$NON-NLS-1$
		}
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
						if (debug) {
							DebugTrace.print(this, "init", //$NON-NLS-1$ 
									"configElement, class=" + className + //$NON-NLS-1$
											", type=" + typeId + //$NON-NLS-1$
											", title=" + title + //$NON-NLS-1$
											", description=" + description); //$NON-NLS-1$							
						}
						if (className != null && className.trim().length() > 0
								&& typeId != null && typeId.trim().length() > 0
								&& title != null && title.trim().length() > 0
								&& description != null
								&& description.trim().length() > 0) {
							if (!wizardPageProviders.containsKey(typeId)) {
								NewLibraryWizardPageContribution provider = new NewLibraryWizardPageContribution(
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
	 * @return a <code>NewLibraryWizardPage</code>
	 */
	public NewLibraryWizardPage createWizardPage(String pageId, String typeId) {
		NewLibraryWizardPage wizardPage = (NewLibraryWizardPage) wizardPages
				.get(typeId);
		if (wizardPage != null) {
			return wizardPage;
		}

		NewLibraryWizardPageContribution provider = (NewLibraryWizardPageContribution) wizardPageProviders
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
				wizardPage = (NewLibraryWizardPage) constructor
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
	public NewLibraryWizardPage getWizardPage(String typeId) {
		return (NewLibraryWizardPage) wizardPages.get(typeId);
	}

}
