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

package org.eclipse.epf.diagram.core.providers;

import java.util.ArrayList;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.impl.NamedNodeImpl;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.diagram.providers.IDiagramIconProvider;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.swt.graphics.Image;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.StructuredActivityNode;

/**
 * This class manages all of the icon providers, and handles querying them for
 * icons based on the node provided. It will decode the node back to the
 * MethodElement then use that to call the provider and retrieve the icon.
 * 
 * 
 * @author Pierre Padovani
 * 
 */
public class DiagramIconProviderManager {
	/**
	 * The extension point namespace.
	 */
	public static final String EXTENSION_POINT_NAMESPACE = "org.eclipse.epf.diagram.core"; //$NON-NLS-1$

	/**
	 * The extension point name.
	 */
	public static final String EXTENSION_POINT_NAME = "diagramIconProviders"; //$NON-NLS-1$

	/**
	 * The "class" attribute name.
	 */
	public static final String CLASS_ATTRIB_NAME = "class"; //$NON-NLS-1$

	/**
	 * Reference to myself for singleton instance
	 */
	private static DiagramIconProviderManager myself;

	/**
	 * The list of registered providers
	 */
	private ArrayList<IDiagramIconProvider> providers;

	/**
	 * Private constructor to avoid outside instantiation
	 */
	private DiagramIconProviderManager() {
		// private to avoid outside calls
	}

	/**
	 * This method returns the singleton instance of this class.
	 * 
	 * @return ElementPropertyProviderManager
	 */
	public static DiagramIconProviderManager getInstance() {
		if (myself == null) {
			myself = new DiagramIconProviderManager();
		}
		return myself;
	}

	/**
	 * This method will attempt to return an Image based on a diagram element.
	 * This method is called from the diagram edit parts. If there are no
	 * providers, a null will be returned.
	 * 
	 * 
	 * @param element
	 *            EObject
	 * @param smallIcon
	 *            TODO
	 * @return Image
	 */
	public Image getIcon(EObject element, boolean smallIcon) {
		Image image = null;

		if (providers == null) {
			loadProviders();
		}

		// We get back to the MethodElement by retrieving the eAnnotation containing the GUID, then
		// look up the element from that.
		if (element instanceof ObjectNode) {
			EAnnotation eAnnotation = ((ObjectNode) element)
					.getEAnnotation(BridgeHelper.UMA_ELEMENT);
			String modelUri = (String) eAnnotation.getDetails().get(
					BridgeHelper.UMA_URI);
			String guid = modelUri.substring(modelUri.indexOf('#') + 1);
			MethodElement me = LibraryService.getInstance()
					.getCurrentLibraryManager().getMethodElement(guid);
			for (int index = 0; index < providers.size() && image == null; index++) {
				IDiagramIconProvider iProvider = providers.get(index);
				image = iProvider.getImageForElement(me, smallIcon);
			}
		} else if (element instanceof StructuredActivityNode) {
			StructuredActivityNode san = (StructuredActivityNode) element;
			EAnnotation eAnnotation = san
					.getEAnnotation(BridgeHelper.UMA_ELEMENT);
			String modelUri = (String) eAnnotation.getDetails().get(
					BridgeHelper.UMA_URI);
			String guid = modelUri.substring(modelUri.indexOf('#') + 1);
			MethodElement me = LibraryService.getInstance()
					.getCurrentLibraryManager().getMethodElement(guid);
			for (int index = 0; index < providers.size() && image == null; index++) {
				IDiagramIconProvider iProvider = providers.get(index);
				image = iProvider.getImageForElement(me, smallIcon);
			}
		} else if (element instanceof NamedNodeImpl) {
			NamedNode wpn = (NamedNode) element;
			MethodElement me = wpn.getLinkedElement();
			for (int index = 0; index < providers.size() && image == null; index++) {
				IDiagramIconProvider iProvider = providers.get(index);
				image = iProvider.getImageForElement(me, smallIcon);
			}
		}

		return image;
	}

	/**
	 * This method loads the icon providers from the extension point
	 */
	private void loadProviders() {
		providers = new ArrayList<IDiagramIconProvider>();
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
						providers.add((IDiagramIconProvider) configElement
								.createExecutableExtension(CLASS_ATTRIB_NAME));
					} catch (Exception e) {
						LibraryPlugin.getDefault().getLogger().logError(e);
					}
				}
			}
		}
	}

}
