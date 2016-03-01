/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.platform;

import org.eclipse.graphiti.platform.IExtension;

/**
 * The interface IImageProvider.
 * 
 * Instead of implementing this interface directly the clients should extend the
 * class <code>AbstractImageProvider</code>.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * 
 */
public interface IImageProvider extends IExtension {

	/**
	 * @param pluginId
	 *            The pluginId of the plug-in containing the image files
	 *            provided by this image-provider
	 */
	void setPluginId(String pluginId);

	/**
	 * @return The pluginId of the plug-in containing the image files provided
	 *         by this image-provider
	 */
	String getPluginId();

	/**
	 * This is a method simply returns the location of the image file in the
	 * plug-in. The path is relative to the root of the plug-in. The path must
	 * not have a leading "." or path separator. Clients should use a path like
	 * "icons/mysample.gif" rather than "./icons/mysample.gif" or
	 * "/icons/mysample.gif". </p>
	 * 
	 * @return imageFilePath the relative path of the image file, relative to
	 *         the root of the plug-in; the path must be legal
	 */
	String getImageFilePath(String imageId);
}
