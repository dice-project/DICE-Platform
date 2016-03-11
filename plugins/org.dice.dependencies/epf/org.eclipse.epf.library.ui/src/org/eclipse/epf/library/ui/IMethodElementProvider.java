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
package org.eclipse.epf.library.ui;

import org.eclipse.epf.uma.MethodElement;

/**
 * The interface for a method element provider.
 * 
 * @author Phong Nguyen Le
 * 
 * @since 1.2
 */
public interface IMethodElementProvider {

	/**
	 * Gets the method element associated with this provider.
	 * 
	 * @return a method element
	 */
	public MethodElement getMethodElement();

}
