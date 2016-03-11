//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui.providers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.osgi.framework.Bundle;

/**
 * Manages the <ColumnElement> elements defined via the
 * "org.eclipse.epf.authoring.ui.descriptionPageColumnProvider" extension point.
 * 
 * @author Shilpa Toraskar
 * @since 1.5
 */
public class DescriptionPageColumnProvider {

	/**
	 * The extension namespace.
	 */
	public static final String COLUMN_PROVIDERS_EXTENSION_NAMESPACE = "org.eclipse.epf.authoring.ui"; //$NON-NLS-1$

	/**
	 * The extension name.
	 */
	public static final String COLUMN_PROVIDERS_EXTENSION_NAME = "descriptionPageColumnProvider"; //$NON-NLS-1$

	/**
	 * The extension Attributes
	 */
	public static final String COLUMN_PROVIDER_EXTENSION_ATTR_ID = "id"; //$NON-NLS-1$

	public static final String COLUMN_PROVIDER_EXTENSION_ATTR_WIDTH = "width"; //$NON-NLS-1$

	public static final String COLUMN_PROVIDER_EXTENSION_ATTR_ALIGNMENT = "align"; //$NON-NLS-1$
	
	public static final String COLUMN_PROVIDER_EXTENSION_ATTR_CLASS = "class"; //$NON-NLS-1$
	
	private static final int COLUMN_DEFAULT_WIDTH = 20; //$NON-NLS-1$

	// Contains the editor column providers loaded via the
	// "org.eclipse.epf.authoring.ui.descriptionPageColumnProvider" extension point.
	private ArrayList columnProviders = new ArrayList();

	//	 The shared instance.
	private static DescriptionPageColumnProvider instance = null;
	
	public static DescriptionPageColumnProvider getInstance() {
		if (instance == null) {
			synchronized (DescriptionPageColumnProvider.class) {
				if (instance == null) {
					instance = new DescriptionPageColumnProvider();
				}
			}
		}
		return instance;
	}

	
	/**
	 * Creates a new instance.
	 */
	private DescriptionPageColumnProvider() {

	}

	/**
	 * Returns all the column providers
	 * 
	 * @return all the column providers.
	 */
	public List getColumnProviders() {
//		List columns = new ArrayList();
//		for (int i = 0; i < columnProviders.size(); i++) {
//			ColumnElement element = (ColumnElement) columnProviders
//					.get(i);
//			Object contributorClass = null;
//			try {
//				contributorClass = element.getContributorClass();
//			} catch (Exception e) {
//			}
//			columns.add(contributorClass);
//		}
//		return columns;
		return columnProviders;
	}
	
	/**
	 * Loads the configuration providers specified via the
	 * "descriptionPageColumnProvider" extension point.
	 */
	public void loadProviders() {
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(
				COLUMN_PROVIDERS_EXTENSION_NAMESPACE,
				COLUMN_PROVIDERS_EXTENSION_NAME);
		if (extensionPoint != null) {
			IExtension[] extensions = extensionPoint.getExtensions();
			for (int i = 0; i < extensions.length; i++) {
				IExtension extension = extensions[i];
				String pluginId = extension.getNamespaceIdentifier();
				Bundle bundle = Platform.getBundle(pluginId);
				
				IConfigurationElement[] configElements = extension.getConfigurationElements();
				for (int j = 0; j < configElements.length; j++) {
					IConfigurationElement configElement = configElements[j];
					try {
						String id = configElement
								.getAttribute(COLUMN_PROVIDER_EXTENSION_ATTR_ID);
						String widthStr = configElement
								.getAttribute(COLUMN_PROVIDER_EXTENSION_ATTR_WIDTH);
						int width = parseWidth(widthStr);
						String alignment = configElement.getAttribute(COLUMN_PROVIDER_EXTENSION_ATTR_ALIGNMENT);
						String contributorClass = configElement
								.getAttribute(COLUMN_PROVIDER_EXTENSION_ATTR_CLASS); 
						ColumnElement element = new ColumnElement(bundle,
								id, width, alignment, contributorClass);
						columnProviders.add(element);
					} catch (Exception e) {
						AuthoringUIPlugin.getDefault().getLogger().logError(
								"Failed to load Editor column Provider", e); //$NON-NLS-1$
					}
				}
			}
		}
	}
	
	
	private static int parseWidth(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
			return COLUMN_DEFAULT_WIDTH;
		}		
	}
}
