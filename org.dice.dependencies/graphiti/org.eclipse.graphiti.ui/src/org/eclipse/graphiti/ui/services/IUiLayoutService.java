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
package org.eclipse.graphiti.ui.services;

import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.services.ILayoutService;

/**
 * Extends the layout service interface to be able to offer layout methods which
 * depend on UI.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be sub-classed by clients.
 */
public interface IUiLayoutService extends ILayoutService {

	/**
	 * Calculates the width and height of the given text in the given font.
	 * 
	 * @param text
	 *            the string to calculate the rendering size for
	 * @param font
	 *            the font which should be considered for the string
	 * @return
	 */
	public IDimension calculateTextSize(String text, Font font);

}
