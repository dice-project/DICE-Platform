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
package org.eclipse.graphiti.internal.datatypes.impl;

import org.eclipse.graphiti.datatypes.IAdvancedDimension;
import org.eclipse.graphiti.datatypes.IDimension;

/**
 * The Class DimensionImpl.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DimensionImpl implements IAdvancedDimension {

	private int width;

	private int height;

	public DimensionImpl(int width, int height) {
		setWidth(width);
		setHeight(height);
	}

	public DimensionImpl(IDimension dimension) {
		this(dimension.getWidth(), dimension.getHeight());
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o instanceof DimensionImpl) {
			DimensionImpl d = (DimensionImpl) o;
			return d.width == width && d.height == height;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (width * height) ^ (width + height);
	}

	@Override
	public String toString() {
		return "Dimension(" + width + ", " + height + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public IDimension getDimensionCopy() {
		return new DimensionImpl(this);
	}

	public IDimension setDimension(int width, int height) {
		this.width = width;
		this.height = height;
		return this;
	}

	public IDimension setDimension(IDimension dimension) {
		return setDimension(dimension.getWidth(), dimension.getHeight());
	}

	public void scale(double amount) {
		width = (int) Math.floor(width * amount);
		height = (int) Math.floor(height * amount);
	}

	public IDimension expand(int dw, int dh) {
		width += dw;
		height += dh;
		return this;
	}
}
