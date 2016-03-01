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
package org.eclipse.graphiti.platform.ga;

/**
 * The Interface IVisualStateHolder provides access to an {@link IVisualState}.
 */
public interface IVisualStateHolder {

	// TODO
	// The visual state must be inherited from the parent, like the colors
	// => getVisualState() and getOwnVisualState()

	/**
	 * Returns the {@link IVisualState}. Must not be null.
	 * 
	 * @return The {@link IVisualState}
	 */
	IVisualState getVisualState();
}
