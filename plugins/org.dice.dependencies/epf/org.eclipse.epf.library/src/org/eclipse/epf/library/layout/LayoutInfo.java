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
package org.eclipse.epf.library.layout;

/**
 * lLyout info for an element.
 * <p>
 * This is used when an element output more then one layout, such as activity
 * tabs
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class LayoutInfo {

	public LayoutInfo(String name, String xsl, String fileName,
			boolean isPrimary) {
		this.name = name;
		this.layout_xsl = xsl;
		this.fileName = fileName;
		this.isPrimary = isPrimary;
	}

	/**
	 * the name of the layout
	 */
	public String name;

	/**
	 * the layout xsl file name
	 */
	public String layout_xsl;

	/**
	 * the outout file name
	 */
	public String fileName;

	/**
	 * is this the primary layout? this apply to the case when one element has more than one layout.
	 *  For example, an activity element has 4 layouts.
	 * 
	 */
	public boolean isPrimary;

}
