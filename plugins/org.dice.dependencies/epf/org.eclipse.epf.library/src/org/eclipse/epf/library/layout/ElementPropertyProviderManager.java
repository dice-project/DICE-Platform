//------------------------------------------------------------------------------
// Copyright (c) 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------

package org.eclipse.epf.library.layout;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.layout.util.XmlElement;
import org.eclipse.epf.library.util.IElementPropertyProvider;
import org.eclipse.epf.uma.MethodElement;

/**
 * This class loads and manages any elementPropertyProvides registered. This class runs as a singleton.
 * 
 * @author Pierre Padovani
 *
 */
public class ElementPropertyProviderManager {

  /**
   * The extension point namespace.
   */
  public static final String EXTENSION_POINT_NAMESPACE = "org.eclipse.epf.library"; //$NON-NLS-1$

  /**
   * The extension point name.
   */
  public static final String EXTENSION_POINT_NAME = "elementPropertyProviders"; //$NON-NLS-1$

  /**
   * The "class" attribute name.
   */
  public static final String CLASS_ATTRIB_NAME = "class"; //$NON-NLS-1$

  private static ElementPropertyProviderManager myself;
  
  private ArrayList<IElementPropertyProvider> providers;
  
  private ElementPropertyProviderManager() {
    // private to avoid outside calls
  }
  
  /**
   * This method returns the singleton instance of this class.
   * 
   * @return ElementPropertyProviderManager
   */
  public static ElementPropertyProviderManager getInstance() {
    if (myself == null) {
      myself = new ElementPropertyProviderManager();
    }
    return myself;
  }
  
  /**
   * This method will leverage the elementPropertyProviders extension point to add additional 
   * custom properties to the xml document used to publish against the XSLT templates. A new child
   * element will be created based on the name provide by the provider.
   * 
   * @param element
   *       MethodElement
   * @param document
   *       XmlElement
   */
  public void loadAdditionalElementProperties(MethodElement element, XmlElement document) {
    // Load the providers if they haven't been loaded already.
    if (providers == null) {
      loadProviders();
    }
  
    // Now for each provider have them load properties as needed. If a null is returned for the name, 
    // skip the provider.
    for (int idx = 0; idx < providers.size(); idx ++) {
      IElementPropertyProvider provider = providers.get(idx);
      String childName = provider.getChildElementName(element);
      if (childName != null && childName.length() > 0) {
        XmlElement childElement = document.newChild(childName);
        provider.publishMethodElementProperties(element, childElement);
      }
    } 
  }
  
  private void loadProviders() {
    providers = new ArrayList<IElementPropertyProvider>();
    IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
    IExtensionPoint extensionPoint = extensionRegistry.getExtensionPoint(
        EXTENSION_POINT_NAMESPACE, EXTENSION_POINT_NAME);
    if (extensionPoint != null) {
      IExtension[] extensions = extensionPoint.getExtensions();
      for (int i = 0; i < extensions.length; i++) {
        IExtension extension = extensions[i];
        IConfigurationElement[] configElements = extension
            .getConfigurationElements();
        for (int j = 0; j < configElements.length; j++) {
          IConfigurationElement configElement = configElements[j];
          try {
            providers.add((IElementPropertyProvider) configElement.createExecutableExtension(CLASS_ATTRIB_NAME));
          } catch (Exception e) {
            LibraryPlugin.getDefault().getLogger().logError(e);
          }
        }
      }
    }
  }
  
}
