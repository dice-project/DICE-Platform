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
package org.eclipse.graphiti.internal.util;

import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.graphiti.util.ILook;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DynamicLook implements ILook {

	public DynamicLook() {
		super();
	}

	public IColorConstant getFieldErrorBackgroundColor() {
		return randomColorConstant();
	}

	public IColorConstant getGridBackgroundColor() {
		return randomColorConstant();
	}

	public int getGridLineThickness() {
		return randomInt(1, 3);
	}

	public IColorConstant getMajorGridLineColor() {
		return randomColorConstant();
	}

	public int getMajorGridLineDistance() {
		return randomInt(1, 10);
	}

	public IColorConstant getMinorGridLineColor() {
		return randomColorConstant();
	}

	public int getMinorGridLineDistance() {
		return randomInt(1, 5);
	}

	private int random255() {
		return randomInt(255);
	}

	private int randomInt(int i) {
		return randomInt(0, i);
	}

	private int randomInt(int from, int to) {
		int ret = (int) (Math.random() * (to - from)) + from;
		return ret;
	}

	private ColorConstant randomColorConstant() {
		return new ColorConstant(random255(), random255(), random255());
	}
}
