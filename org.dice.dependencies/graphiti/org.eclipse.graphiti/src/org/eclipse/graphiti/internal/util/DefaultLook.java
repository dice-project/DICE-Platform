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

import java.awt.Color;

import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.graphiti.util.ILook;

/**
 * The Class DefaultLook.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class DefaultLook implements ILook {

	private IColorConstant fieldErrorBackgroundColor;

	private IColorConstant gridBackgroundColor;

	private IColorConstant majorGridLineColor;

	private IColorConstant minorGridLineColor;

	public IColorConstant getFieldErrorBackgroundColor() {
		if (fieldErrorBackgroundColor == null) {
			// final Color decode = Color.decode("#FFFFFF");
			// fieldErrorBackgroundColor = new ColorConstant(decode.getRed(),
			// decode.getGreen(), decode.getBlue());
			fieldErrorBackgroundColor = IColorConstant.YELLOW;
		}
		return fieldErrorBackgroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.util.ILook#getGridBackgroundColor()
	 */
	public IColorConstant getGridBackgroundColor() {
		if (gridBackgroundColor == null) {
			final Color decode = Color.decode("#FFFFFF"); //$NON-NLS-1$
			gridBackgroundColor = new ColorConstant(decode.getRed(), decode.getGreen(), decode.getBlue());
		}
		return gridBackgroundColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.util.ILook#getGridLineThickness()
	 */
	public int getGridLineThickness() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.util.ILook#getMajorGridLineColor()
	 */
	public IColorConstant getMajorGridLineColor() {
		if (majorGridLineColor == null) {
			final Color decode = Color.decode("#CEE0F2"); //$NON-NLS-1$
			majorGridLineColor = new ColorConstant(decode.getRed(), decode.getGreen(), decode.getBlue());
		}
		return majorGridLineColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.util.ILook#getMajorGridLineDistance()
	 */
	public int getMajorGridLineDistance() {
		return 50;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.util.ILook#getMinorGridLineColor()
	 */
	public IColorConstant getMinorGridLineColor() {
		if (minorGridLineColor == null) {
			final Color decode = Color.decode("#E3EEF9"); //$NON-NLS-1$
			minorGridLineColor = new ColorConstant(decode.getRed(), decode.getGreen(), decode.getBlue());
		}
		return minorGridLineColor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.util.ILook#getMinorGridLineDistance()
	 */
	public int getMinorGridLineDistance() {
		return 10;
	}
}
