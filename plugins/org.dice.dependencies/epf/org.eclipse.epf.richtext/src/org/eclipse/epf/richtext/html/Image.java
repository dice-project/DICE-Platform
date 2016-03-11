/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
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
package org.eclipse.epf.richtext.html;

/**
 * Models a simplified HTML &lt;image&gt; tag.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class Image {

	// The image URL.
	private String url = ""; //$NON-NLS-1$

	/**
	 * Creates a new instance.
	 */
	public Image() {
	}

	/**
	 * Gets the image URL.
	 * 
	 * @return the image URL
	 */
	public String getURL() {
		return url;
	}

	/**
	 * Sets the image URL.
	 * 
	 * @param url
	 *            the image URL
	 */
	public void setURL(String url) {
		this.url = url;
	}

}
