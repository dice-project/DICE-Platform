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
package org.eclipse.epf.uma.ecore.util;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.osgi.framework.Bundle;

/**
 * @author Phong Nguyen Le
 * @since 1.5
 *
 */
public class FeatureDelegate implements IFeatureDelegate {
	public static Object createExtension(String namespace, String extensionPointName) {
		// Process the contributors.
		//
		IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(namespace, extensionPointName);
		if (extensionPoint != null) {
			IExtension[] extensions = extensionPoint.getExtensions();
			Object ext = null;
			ext_walk:
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
						if(className != null) {
							ext = bundle.loadClass(className).newInstance();
							break ext_walk;
						}
					} catch (Exception e) {
						CommonPlugin.INSTANCE.log(e);
					}
				}
			}
			return ext;
		}
		return null;
	}

	public static final IFeatureDelegate newInstance() {
		Object ext = createExtension("org.eclipse.epf.uma.ecore", "featureDelegate"); //$NON-NLS-1$ //$NON-NLS-2$
		if(ext instanceof IFeatureDelegate) {
			return (IFeatureDelegate) ext;
		}
		return new FeatureDelegate();
	}
	
	private FeatureDelegate() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.ecore.util.IFeatureDelegate#delegateGet(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public Object delegateGet(EObject object, EStructuralFeature feature) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.ecore.util.IFeatureDelegate#delegateSet(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public void delegateSet(EObject object, EStructuralFeature feature) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.uma.ecore.util.IFeatureDelegate#isDelegated(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	public boolean isDelegated(EObject object, EStructuralFeature feature) {
		return false;
	}

}
