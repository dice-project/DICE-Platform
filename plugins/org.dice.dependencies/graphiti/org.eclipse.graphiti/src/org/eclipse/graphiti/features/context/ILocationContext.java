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
/*
 * Created on 20.06.2005
 *


 */
package org.eclipse.graphiti.features.context;

/**
 * The Interface ILocationContext.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ILocationContext extends IContext {

	/**
	 * Gets the x.
	 * 
	 * @return the x position
	 */
	int getX();

	/**
	 * Gets the y.
	 * 
	 * @return the y position
	 */
	int getY();

}