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
package org.eclipse.graphiti.util;

import org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas;

/**
 * The constants provided by this interface can be used with
 * {@link AdaptedGradientColoredAreas}. If <i>VERTICAL</i> is set, the gradient
 * will go from top to bottom. If <i>HORIZONTAL</i>, it will go from left to
 * right.
 */
public interface IGradientType {

	public final Integer VERTICAL = 0;

	public final Integer HORIZONTAL = 1;

}
