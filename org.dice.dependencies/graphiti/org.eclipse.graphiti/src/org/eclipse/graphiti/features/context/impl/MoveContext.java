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
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.IMoveContext;

/**
 * The Class MoveContext.
 */
public class MoveContext extends LocationContext implements IMoveContext {

	/**
	 * Creates a new {@link MoveContext}.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public MoveContext(int x, int y) {
		super(x, y);
	}

	/**
	 * Instantiates a new move context.
	 */
	public MoveContext() {
	}
}
