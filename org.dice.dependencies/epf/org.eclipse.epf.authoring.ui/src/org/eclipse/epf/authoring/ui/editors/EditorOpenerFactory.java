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
package org.eclipse.epf.authoring.ui.editors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.MethodPlugin;
import org.osgi.framework.Bundle;

/**
 * Manages the Editor Openenr Extensions
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class EditorOpenerFactory {

	/**
	 * The extension point namespace.
	 */
	public static final String EXTENSION_POINT_NAMESPACE = "org.eclipse.epf.authoring.ui"; //$NON-NLS-1$

	/**
	 * The extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "EditorOpener"; //$NON-NLS-1$

	/**
	 * The "class" attribute name.
	 */
	public static final String CLASS_ATTRIB_NAME = "class"; //$NON-NLS-1$

	/**
	 * The "id" attribute name.
	 */
	public static final String ID_ATTRIB_NAME = "id"; //$NON-NLS-1$

	// The shared instance.
	private static EditorOpenerFactory instance = null;

	// A map of library manager configuration elements.
	private Map<String, EditorOpenerElement> openerElements = new HashMap<String, EditorOpenerElement>();

	/**
	 * Returns the shared instance.
	 */
	public static synchronized EditorOpenerFactory getInstance() {
		if (instance == null) {
			instance = new EditorOpenerFactory();
		}
		return instance;
	}

	/**
	 * Creates a new instance.
	 */
	private EditorOpenerFactory() {
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
						String id = configElement
								.getAttribute(ID_ATTRIB_NAME);
						if (className != null && className.trim().length() > 0
								&& id != null && id.trim().length() > 0) {							
							EditorOpenerElement provider = openerElements.get(id);
							if(provider == null ) {
								provider = new EditorOpenerElement(
										bundle, className, id);
								openerElements.put(id, provider);
							}
						}
					} catch (Exception e) {
						LibraryPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
	}

	public EditorOpener getOpener(Object obj) {
		if ( obj instanceof MethodElement ) {
			MethodElement e = ((MethodElement)obj);
			
			while (e != null ) {
				List props = e.getMethodElementProperty();
				if ( props != null && props.size() > 0 ) {
					for (Iterator itp = props.iterator(); itp.hasNext(); ) {
						MethodElementProperty prop = (MethodElementProperty)itp.next();
						String id = prop.getName();
						EditorOpener opener= createExtension(id);
						if ( opener != null ) {						
							return opener;
						}
					}
				}	
				
				if ( e instanceof MethodPlugin) {
					break;
				}
				
				// try if the id is defined in the plugin
				e = LibraryUtil.getMethodPlugin(e);
			}
		}

		return null;
	}
	
	public boolean hasExtension(String id) {
		return openerElements.containsKey(id);
	}
	
	/**
	 * Creates layout extension.
	 * 
	 * @param id the extension id
	 * @return LayoutExtension
	 */
	private EditorOpener createExtension(String id) {
		
		if ( !hasExtension(id) ) {
			return null;
		}
		
		EditorOpenerElement provider = (EditorOpenerElement) openerElements.get(id);
		try {
			Class clazz = provider.bundle.loadClass(provider.className);
			return (EditorOpener) clazz.newInstance();
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}
		
		return null;
	}

	private class EditorOpenerElement {
		Bundle bundle;
		String className;
		String id;
		private EditorOpenerElement(Bundle bundle, String className, String id) {
			this.bundle = bundle;
			this.className = className;
			this.id = id;
		}
	}
}
