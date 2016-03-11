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
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.ILocationContext;
import org.eclipse.graphiti.internal.features.context.impl.base.DefaultContext;

/**
 * The Class LocationContext.
 */
public class LocationContext extends DefaultContext implements ILocationContext {

	private int x = -1;

	private int y = -1;

	/**
	 * Creates a new {@link LocationContext}.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public LocationContext(int x, int y) {
		super();
		setLocation(x, y);
	}

	/**
	 * Creates a new {@link LocationContext}.
	 */
	public LocationContext() {
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	/**
	 * Sets the location.
	 * 
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets the x.
	 * 
	 * @param x
	 *            The x to set.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the y.
	 * 
	 * @param y
	 *            The y to set.
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		ret = ret + "(x=" + getX() + ", y=" + getY() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return ret;
	}
}
