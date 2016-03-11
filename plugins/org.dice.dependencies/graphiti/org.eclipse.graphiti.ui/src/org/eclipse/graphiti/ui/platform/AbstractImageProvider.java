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

import java.util.Hashtable;

import org.eclipse.graphiti.platform.AbstractExtension;
import org.eclipse.graphiti.ui.internal.T;

/**
 * The Class AbstractImageProvider.
 * 
 * Instead of implementing IImageProvider directly the clients should extend
 * this AbstractImageProvider. They can simply override the method
 * addAvailableImages().
 */
public abstract class AbstractImageProvider extends AbstractExtension implements IImageProvider {

	private String pluginId;

	private Hashtable<String, String> htKeyImage = new Hashtable<String, String>();

	/**
	 * Creates a new {@link AbstractImageProvider}.
	 */
	public AbstractImageProvider() {
		super();
		addAvailableImages();
	}

	final public String getPluginId() {
		return this.pluginId;
	}

	/**
	 * Sets the pluginId.
	 * 
	 * @param pluginId
	 *            The pluginId to set.
	 */
	final public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	final public String getImageFilePath(String imageId) {
		Object htObject = this.htKeyImage.get(imageId);
		if (htObject instanceof String) {
			return (String) htObject;
		}
		return null;
	}

	/**
	 * Add image file path.
	 * 
	 * @param imageId
	 *            the image id
	 * @param imageFilePath
	 *            the image file path
	 */
	final protected void addImageFilePath(String imageId, String imageFilePath) {
		if (this.htKeyImage.get(imageId) != null) {
			T.racer().error("Image with ID '" + imageId + "' is already registered"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		this.htKeyImage.put(imageId, imageFilePath);
	}

	/**
	 * Add all available images with addImageFilePath(String imageId, String
	 * imageFilePath);
	 */
	abstract protected void addAvailableImages();
}
