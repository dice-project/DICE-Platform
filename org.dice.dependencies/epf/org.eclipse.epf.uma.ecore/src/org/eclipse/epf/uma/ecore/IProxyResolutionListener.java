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
package org.eclipse.epf.uma.ecore;

/**
 * The interface for a proxy resolution listener attached to a UMA resource set.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IProxyResolutionListener {

	/**
	 * Receives notification of an exception that has occurred while resolving a
	 * proxy object.
	 * 
	 * @param e
	 *            the exception that has occurred
	 */
	void notifyException(Exception e);

	/**
	 * Receives notification that a proxy has been resolved successfully.
	 * 
	 * @param proxy
	 *            a proxy object
	 * @param resolved
	 *            the resolved object
	 */
	void proxyResolved(Object proxy, Object resolved);

}
