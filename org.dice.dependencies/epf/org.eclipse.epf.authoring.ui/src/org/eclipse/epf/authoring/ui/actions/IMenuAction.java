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
package org.eclipse.epf.authoring.ui.actions;

import org.eclipse.ui.IViewPart;


/**
 * Interface to be used to add menus for configuration view
 * 
 * @author Shilpa Toraskar
 * @since 1.5
 */
public interface IMenuAction  {


	/**
	 * Initialize config view menu action provider
	 * @param toolkit
	 * @param parent
	 * @param element
	 * @return
	 */
	public void init(IViewPart view);
	
	
}
