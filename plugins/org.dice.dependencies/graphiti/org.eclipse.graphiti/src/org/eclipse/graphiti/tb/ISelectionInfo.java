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
package org.eclipse.graphiti.tb;

import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The Interface ISelectionInfo.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ISelectionInfo {

	/**
	 * Gets the color.
	 * 
	 * @return the color of the selection border
	 */
	IColorConstant getColor();

	/**
	 * Gets the handle foreground color.
	 * 
	 * @return the foreground color of the selection handle
	 */
	IColorConstant getHandleForegroundColor();

	/**
	 * Gets the handle background color.
	 * 
	 * @return the background color of the selection handle
	 */
	IColorConstant getHandleBackgroundColor();

	/**
	 * Gets the hover color.
	 * 
	 * @param color
	 *            the on hover color
	 */
	IColorConstant getHoverColor();

	/**
	 * Gets the hover color for a shape whose parent is selected.
	 * 
	 * @param color
	 *            the on hover color
	 */
	IColorConstant getHoverColorParentSelected();

	/**
	 * Gets the line style.
	 * 
	 * @return the line style of the selection
	 */
	LineStyle getLineStyle();

	/**
	 * Set the color of the selection.
	 * 
	 * @param color
	 *            the color
	 */
	void setColor(IColorConstant color);

	/**
	 * Sets the hover color.
	 * 
	 * @param color
	 *            the color
	 */
	void setHoverColor(IColorConstant hoverColor);

	/**
	 * Sets the hover color for shapes whose parent is selected.
	 * 
	 * @param color
	 *            the color
	 */
	void setHoverColorParentSelected(IColorConstant hoverColor);

	/**
	 * Set the foreground color of the selection handle.
	 * 
	 * @param color
	 *            the color
	 */
	void setHandleForegroundColor(IColorConstant color);

	/**
	 * Set the background color of the selection handle.
	 * 
	 * @param color
	 *            the color
	 */
	void setHandleBackgroundColor(IColorConstant color);

	/**
	 * Set the line style of the selection.
	 * 
	 * @param lineStyle
	 *            the line style
	 */
	void setLineStyle(LineStyle lineStyle);

	/**
	 * Set hover color.
	 * 
	 * @param color
	 *            the on hover color
	 */
	void setLineStyle(IColorConstant color);

}
