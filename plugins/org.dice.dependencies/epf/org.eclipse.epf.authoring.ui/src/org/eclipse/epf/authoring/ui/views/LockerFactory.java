/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.authoring.ui.views;

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
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.osgi.framework.Bundle;

public class LockerFactory {

	/**
	 * The extension point namespace.
	 */
	public static final String EXTENSION_POINT_NAMESPACE = "org.eclipse.epf.authoring.ui"; //$NON-NLS-1$

	/**
	 * The extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "CustomizedLocker"; //$NON-NLS-1$

	/**
	 * The "class" attribute name.
	 */
	public static final String CLASS_ATTRIB_NAME = "class"; //$NON-NLS-1$

	/**
	 * The "id" attribute name.
	 */
	public static final String ID_ATTRIB_NAME = "id"; //$NON-NLS-1$

	// The shared instance.
	private static LockerFactory instance = null;

	// A map of library manager configuration elements.
	private Map<String, LockerElement> lockerElements = new HashMap<String, LockerElement>();
	
	//A map to manage id and locker pairs.
	private Map<String, AbstractLocker> lockerMap = new HashMap<String, AbstractLocker>();

	/**
	 * Returns the shared instance.
	 */
	public static LockerFactory getInstance() {
		if(instance == null){
			synchronized(LockerFactory.class){
				if (instance == null) {
					instance = new LockerFactory();
				}
			}
		}
		return instance;
	}

	/**
	 * Creates a new instance.
	 */
	private LockerFactory() {
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
						String className = configElement
						.getAttribute(CLASS_ATTRIB_NAME);
						String id = configElement
						.getAttribute(ID_ATTRIB_NAME);
						if (className != null && className.trim().length() > 0
								&& id != null && id.trim().length() > 0) {							
							LockerElement provider = lockerElements.get(id);
							if(provider == null ) {
								provider = new LockerElement(
										bundle, className, id);
								lockerElements.put(id, provider);
							}
						}
					} catch (Exception e) {
						LibraryPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
	}

	public AbstractLocker getLocker(Object obj) {
		if ( obj instanceof MethodElement ) {
			MethodElement e = ((MethodElement)obj);			
			if (e != null ) {
				List props = e.getMethodElementProperty();
				if ( props != null && props.size() > 0 ) {
					for (Iterator itp = props.iterator(); itp.hasNext(); ) {
						MethodElementProperty prop = (MethodElementProperty)itp.next();
						String id = prop.getName();
						AbstractLocker locker= getLocker(id);
						if ( locker != null ) {						
							return locker;
						}
					}
				}	
			}
		}
		return null;
	}

	private AbstractLocker getLocker(String id) {

		if ( !lockerElements.containsKey(id) ) {
			return null;
		}

		//return created instance
		if(lockerMap.containsKey(id) ){
			return lockerMap.get(id);
		}
		
		LockerElement provider = (LockerElement) lockerElements.get(id);
		try {
			Class clazz = provider.bundle.loadClass(provider.className);
			AbstractLocker lockerInstance = (AbstractLocker) clazz.newInstance();
			lockerMap.put(id, lockerInstance);
			return lockerInstance;
		} catch (Exception e) {
			LibraryPlugin.getDefault().getLogger().logError(e);
		}

		return null;
	}

	private class LockerElement {
		Bundle bundle;
		String className;
		String id;
		private LockerElement(Bundle bundle, String className, String id) {
			this.bundle = bundle;
			this.className = className;
			this.id = id;
		}
	}

}
