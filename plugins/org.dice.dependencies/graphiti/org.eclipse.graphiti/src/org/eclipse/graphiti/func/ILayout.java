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
package org.eclipse.graphiti.func;

import org.eclipse.graphiti.features.context.ILayoutContext;

/**
 * The Interface ILayout.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ILayout {

	/**
	 * Checks whether the current pictogram element of the given context can be
	 * layouted.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true if update is possible
	 */
	boolean canLayout(ILayoutContext context);

	/**
	 * Layoutes the pictogram element.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if update process was successfull
	 */
	boolean layout(ILayoutContext context);
}
