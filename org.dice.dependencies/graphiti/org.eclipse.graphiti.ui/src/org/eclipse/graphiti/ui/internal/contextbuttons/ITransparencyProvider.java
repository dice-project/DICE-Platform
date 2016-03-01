/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.contextbuttons;

/**
 * This transparency provider interface can be implemented to provide the
 * 'current transparency'. The 'current transparency' value is multiplied with
 * any other transparency/opacity values of a figure before painting. It is
 * basically a central adjustment of the transparency value(s) of a figure.
 * <p>
 * The idea is, that a transparency provider could increase/decrease the current
 * transparency in a loop, which would result in a fade in/out effect of the
 * figure.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
interface ITransparencyProvider {

	// TODO: enhance this interface
	// If this should become a globally used interface, then we need a set-method instead of a get-method, because otherwise the
	// figures do not know when the current transparency changes.

	/**
	 * Returns the current transparency as described above.
	 * 
	 * @return The current transparency as described above.
	 */
	double getCurrentTransparency();
}
