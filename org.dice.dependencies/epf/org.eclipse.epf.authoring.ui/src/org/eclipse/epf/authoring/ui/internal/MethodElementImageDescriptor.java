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
package org.eclipse.epf.authoring.ui.internal;

import org.eclipse.epf.authoring.ui.AuthoringUIImages;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.util.Assert;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
/**
 * Code taken from org.eclipse.jdt.ui.JavaElementImageDescriptor
 *
 */
public class MethodElementImageDescriptor extends CompositeImageDescriptor {
	
	/** Flag to render the error adornment. */
	public final static int ERROR=			0x040;

	private ImageDescriptor fBaseImage;
	private int fFlags;
	private Point fSize;

	/**
	 * Creates a new MethodElementImageDescriptor.
	 * 
	 * @param baseImage an image descriptor used as the base image
	 * @param flags flags indicating which adornments are to be rendered. See <code>setAdornments</code>
	 * 	for valid values.
	 * @param size the size of the resulting image
	 * @see #setAdornments(int)
	 */
	public MethodElementImageDescriptor(ImageDescriptor baseImage, int flags, Point size) {
		fBaseImage= baseImage;
		Assert.isNotNull(fBaseImage);
		fFlags= flags;
		Assert.isTrue(fFlags >= 0);
		fSize= size;
		Assert.isNotNull(fSize);
	}
	
	/**
	 * Sets the descriptors adornments. Valid values are: <code>ABSTRACT</code>, <code>FINAL</code>,
	 * <code>SYNCHRONIZED</code>, </code>STATIC<code>, </code>RUNNABLE<code>, </code>WARNING<code>, 
	 * </code>ERROR<code>, </code>OVERRIDDES<code>, <code>IMPLEMENTS</code>, <code>CONSTRUCTOR</code>,
	 * <code>DEPRECATED</code>,  or any combination of those.
	 * 
	 * @param adornments the image descriptors adornments
	 */
	public void setAdornments(int adornments) {
		Assert.isTrue(adornments >= 0);
		fFlags= adornments;
	}

	/**
	 * Returns the current adornments.
	 * 
	 * @return the current adornments
	 */
	public int getAdronments() {
		return fFlags;
	}

	/**
	 * Sets the size of the image created by calling <code>createImage()</code>.
	 * 
	 * @param size the size of the image returned from calling <code>createImage()</code>
	 * @see ImageDescriptor#createImage()
	 */
	public void setImageSize(Point size) {
		Assert.isNotNull(size);
		Assert.isTrue(size.x >= 0 && size.y >= 0);
		fSize= size;
	}
	
	/**
	 * Returns the size of the image created by calling <code>createImage()</code>.
	 * 
	 * @return the size of the image created by calling <code>createImage()</code>
	 * @see ImageDescriptor#createImage()
	 */
	public Point getImageSize() {
		return new Point(fSize.x, fSize.y);
	}
	
	/* (non-Javadoc)
	 * Method declared in CompositeImageDescriptor
	 */
	protected Point getSize() {
		return fSize;
	}
	
	/* (non-Javadoc)
	 * Method declared on Object.
	 */
	public boolean equals(Object object) {
		if (object == null || !MethodElementImageDescriptor.class.equals(object.getClass()))
			return false;
			
		MethodElementImageDescriptor other= (MethodElementImageDescriptor)object;
		return (fBaseImage.equals(other.fBaseImage) && fFlags == other.fFlags && fSize.equals(other.fSize));
	}
	
	/* (non-Javadoc)
	 * Method declared on Object.
	 */
	public int hashCode() {
		return fBaseImage.hashCode() | fFlags | fSize.hashCode();
	}
	
	/* (non-Javadoc)
	 * Method declared in CompositeImageDescriptor
	 */
	protected void drawCompositeImage(int width, int height) {
		ImageData bg= getImageData(fBaseImage);
			
		
		drawImage(bg, 0, 0);
				
		drawTopRight();
		drawBottomRight();
		drawBottomLeft();
		

	}
	
	private ImageData getImageData(ImageDescriptor descriptor) {
		ImageData data= descriptor.getImageData(); // see bug 51965: getImageData can return null
		if (data == null) {
			data= DEFAULT_IMAGE_DATA;
			AuthoringUIPlugin.getDefault().getLogger().logError("Image data not available: " + descriptor.toString()); //$NON-NLS-1$
		}
		return data;
	}
	
	
	private void drawTopRight() {		
	}		
	
	private void drawBottomRight() {
	}		
	
	private void drawBottomLeft() {
		Point size= getSize();
		int x= 0;
		if ((fFlags & ERROR) != 0) {
			ImageData data= getImageData(AuthoringUIImages.IMG_DESC_OVR_ERROR);
			drawImage(data, x, size.y - data.height);
			x+= data.width;
		}
	}		
}
