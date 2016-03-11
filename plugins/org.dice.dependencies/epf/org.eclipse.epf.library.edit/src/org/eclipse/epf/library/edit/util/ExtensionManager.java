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
package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.command.INestedCommandProvider;
import org.eclipse.epf.library.edit.command.IUserInteractionHandler;
import org.osgi.framework.Bundle;

/**
 * Helper class with methods to retrieve extensions
 * 
 * @author Phong Nguyen Le - Jun 27, 2006
 * @since  1.0
 */
public final class ExtensionManager {
	private static List<INestedCommandProvider> nestedCommandProviders;
	private static List oppositeFeatureLoaders;
	
	public static <T>List<T> getExtensions(String namespace, String extensionPointName, Class<T> type) {
		return ExtensionHelper.getExtensions(namespace, extensionPointName, type);
	}
	
	public static Object createExtension(String namespace, String extensionPointName) {
		return ExtensionHelper.createExtension(namespace, extensionPointName);
	}
	
	public static Object getExtension(String namespace, String extensionPointName) {
		return ExtensionHelper.getExtension(namespace, extensionPointName);
	}

	public static ITextReferenceReplacer getTextReferenceReplacer() {
		return (ITextReferenceReplacer) getExtension(LibraryEditPlugin.getDefault().getId(), "textReferenceReplacer"); //$NON-NLS-1$ 
	}
	
	public static ILibraryEditUtilProvider getLibraryEditUtilProvider() {
		return (ILibraryEditUtilProvider) getExtension(LibraryEditPlugin.getDefault().getId(), "libraryEditUtilProvider"); //$NON-NLS-1$ 
	}

	public static List<INestedCommandProvider> getNestedCommandProviders() {
		if(nestedCommandProviders == null) {
			nestedCommandProviders = getExtensions(LibraryEditPlugin.getDefault().getId(), 
					"nestedCommandProviders", INestedCommandProvider.class); //$NON-NLS-1$
		}
		return nestedCommandProviders;
	}

	public static IDiagramManager getDiagramManager() {
		return (IDiagramManager) getExtension(LibraryEditPlugin.getDefault().getId(), "diagramManager"); //$NON-NLS-1$ 
	}
	
	public static List getOppositeFeatureLoaders() {
		if(oppositeFeatureLoaders == null) {
			oppositeFeatureLoaders = getExtensions(LibraryEditPlugin.getDefault().getId(), 
					"oppositeFeatureLoaders", INestedCommandProvider.class); //$NON-NLS-1$
		}
		return oppositeFeatureLoaders;
	}
	
	public static IUserInteractionHandler getDefaultUserInteractionHandler() {
		return (IUserInteractionHandler) getExtension(LibraryEditPlugin.getDefault().getId(), "userInteractionHandler"); //$NON-NLS-1$
	}
}