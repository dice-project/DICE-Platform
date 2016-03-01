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
package org.eclipse.graphiti.ui.internal;

import org.eclipse.swt.graphics.Color;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IResourceRegistry extends IDisposable {
	/**
	 * Provides a SWT color instance with the given RGB values.
	 * 
	 * @param red the value for the red part of the color
	 * @param green the value for the green part of the color
	 * @param blue the value for the blue part of the color
	 * 
	 * @return a SWT color instance
	 * 
	 * @see org.eclipse.swt.graphics.Color
	 */
	Color getSwtColor(int red, int green, int blue);

	/**
	 * @param hexRGBString

	 * @return a SWT color instance
	 * 
	 * @see org.eclipse.swt.graphics.Color
	 */
	Color getSwtColor(String hexRGBString);
}