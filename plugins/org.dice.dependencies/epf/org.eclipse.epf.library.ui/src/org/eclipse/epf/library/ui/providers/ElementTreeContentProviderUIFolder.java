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
package org.eclipse.epf.library.ui.providers;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.swt.graphics.Image;

/**
 * Models a UI folder in the method search result tree view.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ElementTreeContentProviderUIFolder {

	private static final Image DEFAULT_IMAGE = ExtendedImageRegistry.getInstance().getImage(LibraryEditPlugin.INSTANCE
			.getImage("full/obj16/Folder")); //$NON-NLS-1$

	private String name;

	private Image image;

	private Object parent;

	/**
	 * Creates a new instance.
	 * 
	 * @param name
	 *            The name for the folder.
	 */
	public ElementTreeContentProviderUIFolder(String name) {
		this(name, DEFAULT_IMAGE, null);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param name
	 *            a name for the folder
	 * @param parent
	 *            the parent object
	 */
	public ElementTreeContentProviderUIFolder(String name, Object parent) {
		this(name, DEFAULT_IMAGE, parent);
	}

	/**
	 * Creates a new instance.
	 * 
	 * @param name
	 *            a name for the folder
	 * @param image
	 *            an image for the folder
	 * @param parent
	 *            the parent object
	 */
	public ElementTreeContentProviderUIFolder(String name, Image image, Object parent) {
		this.name = name;
		this.image = image;
		this.parent = parent;
	}

	/**
	 * Returns the name.
	 * 
	 * @return the folder name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the image.
	 * 
	 * @return the image for a UI folder
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Returns the parent object.
	 * 
	 * @return the parent object for the folder
	 */
	public Object getParent() {
		return parent;
	}

	/**
	 * Sets the parent object.
	 * 
	 * @param parent
	 *            the parent object
	 * @return the given parent object
	 */
	public Object setParent(Object parent) {
		return this.parent = parent;
	}

	/**
	 * Returns the string representation of this object.
	 * 
	 * @return the folder name
	 */
	public String toString() {
		return name;
	}

}
