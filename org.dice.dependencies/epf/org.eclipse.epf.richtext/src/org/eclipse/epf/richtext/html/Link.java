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
 * Models a simplified HTML &lt;link&gt; tag.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class Link {

	// The link name.
	private String name = ""; //$NON-NLS-1$

	// The link URL.
	private String url = ""; //$NON-NLS-1$

	/**
	 * Creates a new instance.
	 */
	public Link() {
	}

	/**
	 * Gets the link name.
	 * 
	 * @return the link name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the link name.
	 * 
	 * @param name
	 *            the link name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the link URL.
	 * 
	 * @return the link URL
	 */
	public String getURL() {
		return url;
	}

	/**
	 * Sets the link URL.
	 * 
	 * @param url
	 *            the link URL
	 */
	public void setURL(String url) {
		this.url = url;
	}

	/**
	 * @Gets the HTML representation of this link.
	 * 
	 * @return the HTML representation of this link
	 */
	public String toHTML() {
		return null;
	}
}
