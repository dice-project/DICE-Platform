//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui.providers;

import org.osgi.framework.Bundle;


/**
 * Models a <column> element in the "org.eclipse.epf.authoring.ui.descriptionPageColumnProvider"
 * extension point.
 * 
 * @author Shilpa Toraskar
 * @since 1.5
 */
public class ColumnElement {

	// plugin bundle
	Bundle bundle;
	
	// The column ID.
	private String id;
	
	// The column width.
	private int width;
	
	// The column alignment
	private String alignment;

	// The contributor class for the column.
	private String contributorClass;
	
	/**
	 * Creates a new instance.
	 * 
	 * @param bundle
	 * 			  Installed Bundle
	 * @param id
	 *            The column ID.
	 * @param name
	 *            The width of the column     
	 * @param contributorClass
	 *            The column contributor class.
	 */
	public ColumnElement(Bundle bundle, String id, int width, String alignment, String contributorClass) {
		this.bundle = bundle;
		this.id = id;
		this.width = width;
		this.alignment = alignment;
		this.contributorClass = contributorClass;
	}

	/**
	 * Returns the column ID.
	 * 
	 * @return The column ID.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Returns the column width
	 * 
	 * @return The column width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns the column alignment
	 * 
	 * @return The column alignment
	 */
	public String getAlignment() {
		return alignment;
	}

	/**
	 * Returns the column contributor class.
	 * 
	 * @return The column contributor class.
	 */
	public Object getContributorClass() throws Exception {
		
		Class clazz = bundle.loadClass(contributorClass);
		return clazz.newInstance();	
	}
}