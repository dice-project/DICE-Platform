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
package org.eclipse.graphiti.features.context;

/**
 * The interface IAreaContext.
 * 
 * Types of this context describe an area, where features have to work in.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IAreaContext extends ILocationContext {

	/**
	 * Gets the width.
	 * 
	 * @return the area's width
	 */
	int getWidth();

	/**
	 * Gets the height.
	 * 
	 * @return the area's height
	 */
	int getHeight();
}