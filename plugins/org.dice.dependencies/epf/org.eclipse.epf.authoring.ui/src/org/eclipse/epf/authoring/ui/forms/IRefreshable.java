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
package org.eclipse.epf.authoring.ui.forms;

/**
 * Interface used to refresh a form that implements this in case of model change outside of the form
 * 
 * @author Phong Nguyen Le 
 * @since  1.0
 */
public interface IRefreshable {
	
	/**
	 * Refresh name of the form
	 * @param newName
	 */
	void refreshName(String newName);
}
