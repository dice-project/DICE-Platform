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
package org.eclipse.epf.authoring.ui.editors;

/**
 * abstract class for editor openenr extension
 * 
 * @author Jinhua Xi
 *
 */
public abstract class EditorOpener {

	public EditorOpener() {
		
	}
	
	public abstract boolean canOpen(Object obj);
	public abstract void openEditor(Object obj) throws Exception ;
	
}
