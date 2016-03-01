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
package org.eclipse.graphiti.ui.internal.services;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IUiService {

	/**
	 * Returns the bytes of an encoded image for the specified IFigure in the
	 * specified format.
	 * 
	 * @param image
	 *            The Figure to create an image for.
	 * @param format
	 *            One of SWT.IMAGE_BMP, SWT.IMAGE_BMP_RLE, SWT.IMAGE_GIF
	 *            SWT.IMAGE_ICO, SWT.IMAGE_JPEG, or SWT.IMAGE_PNG
	 * @param scaleFactor
	 *            The scale factor, which is applied to the created the image.
	 * 
	 * @return The bytes of an encoded image for the specified Figure
	 * @throws Exception
	 *             if GIF is th format and the image contains more than 256
	 *             colors
	 */
	public abstract byte[] createImage(Image image, int format) throws Exception;

	/**
	 * Creates an image with 8 bit color depth and an indexed palette from the
	 * given image. This is the format required for GIF images.
	 * 
	 * @param image
	 *            The image, from which to create the new image.
	 * 
	 * @return The new image with 8 bit color depth and an indexed palette.
	 * 
	 * @throws Exception
	 *             If the image has more than 256 colors (not supported yet).
	 */
	public abstract ImageData create8BitIndexedPaletteImage(Image image) throws Exception;

	/**
	 * Start the procedure to save the given GraphicalViewer as an image:
	 * <ul>
	 * <li>opens a dialog to select the figure, image-format and the
	 * scale-factor</li>
	 * <li>opens a dialog to select the filename</li>
	 * <li>saves the image to the file</li>
	 * </ul>
	 * 
	 * @param graphicalViewer
	 *            The GraphicalViewer, which to save as an image.
	 */
	public abstract void startSaveAsImageDialog(GraphicalViewer graphicalViewer);



}