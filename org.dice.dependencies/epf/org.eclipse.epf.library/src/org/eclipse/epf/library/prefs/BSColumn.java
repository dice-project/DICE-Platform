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
package org.eclipse.epf.library.prefs;

/**
 * Simple data structure to identify a breakdown layout column.
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class BSColumn {

	public String id;
	public String label;
	
	public BSColumn(String id, String label) {
		this.id = id;
		this.label = label;
	}
}
