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
package org.eclipse.graphiti.pattern.config;

import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Interface IColorConfiguration.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IColorConfiguration extends IPatternConfiguration {

	/**
	 * Gets the background color.
	 * 
	 * @return the background color
	 */
	IColorConstant getBackgroundColor();

	/**
	 * Gets the foreground color.
	 * 
	 * @return the foreground color
	 */
	IColorConstant getForegroundColor();

	/**
	 * Gets the text color.
	 * 
	 * @return the text color
	 */
	IColorConstant getTextColor();

	/**
	 * Sets the background color.
	 * 
	 * @param color
	 *            the new background color
	 */
	void setBackgroundColor(IColorConstant color);

	/**
	 * Sets the foreground color.
	 * 
	 * @param color
	 *            the new foreground color
	 */
	void setForegroundColor(IColorConstant color);

	/**
	 * Sets the text color.
	 * 
	 * @param color
	 *            the new text color
	 */
	void setTextColor(IColorConstant color);

	/**
	 * Gets the transparency.
	 * 
	 * @return the transparency
	 */
	double getTransparency();

	/**
	 * Sets the transparency.
	 * 
	 * @param transparency
	 *            the new transparency
	 */
	void setTransparency(double transparency);

}
