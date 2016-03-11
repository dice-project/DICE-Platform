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
package org.eclipse.graphiti.tb;

/**
 * The Class ImageDecorator.
 */
public class ImageDecorator extends AbstractDecorator implements IImageDecorator {

	private static final int DEFAULT_LOCATION = 4;

	private String imageId;

	private int y = DEFAULT_LOCATION;

	private int x = DEFAULT_LOCATION;

	/**
	 * Creates a new {@link ImageDecorator}.
	 * 
	 * @param imageId
	 *            the image id
	 */
	public ImageDecorator(String imageId) {
		super();
		setImageId(imageId);
	}

	public String getImageId() {
		return this.imageId;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	private void setImageId(String imageId) {
		this.imageId = imageId;
	}
}
