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
package org.eclipse.epf.library.layout.diagram;

import java.awt.Rectangle;

/**
 * Encapsulates the HTML AREA tag.
 * 
 * @author Kelvin Low
 * @author Jinhua XI
 * @since 1.0
 */
public class HTMLArea {

	private String guid =  null;
	
	private String href;

	private String shape;

	private Rectangle coordinates;

	private String altTag;

	/**
	 * Creates a new <code>HTMLArea</code>.
	 */
	public HTMLArea(String guid, String href, String shape, Rectangle coordinates,
			String altTag) {
		this.guid = guid;
		this.href = href;
		this.shape = shape;
		this.coordinates = coordinates;
		this.altTag = altTag;
		
		// remove new line characters, otherwise it will caused a white space in the published html
		// not as expected. 
		// see bug 00388978 - Extra space in the flyover text in the Caspian Japanese site		
		if ( this.altTag != null ) {
			this.altTag = this.altTag.replaceAll("(\r|\n)+", "");  //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * get the guid
	 * @return String
	 */
	public String getGuid() {
		return guid;
	}
	
	/**
	 * Returns the HREF.
	 */
	public String getHref() {
		return href;
	}

	/**
	 * Returns the shape.
	 */
	public String getShape() {
		return shape;
	}

	/**
	 * Returns the coordinates.
	 */
	public Rectangle getCoordinates() {
		return coordinates;
	}

	/**
	 * Returns the alt tag.
	 */
	public String getAltTag() {
		return altTag;
	}

}
