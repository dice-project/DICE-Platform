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

import org.eclipse.graphiti.mm.algorithms.styles.AbstractStyle;
import org.eclipse.graphiti.mm.algorithms.styles.AdaptedGradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredArea;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredAreas;
import org.eclipse.graphiti.mm.algorithms.styles.GradientColoredLocation;
import org.eclipse.graphiti.services.IGaService;

/**
 * This interface defines the IDs of the predefined rendering styles for
 * gradients, and constants for style adaptations of gradients. Each
 * (predefined) ID for a rendering style of an gradient corresponds to an
 * {@link AdaptedGradientColoredAreas} defined in {@link PredefinedColoredAreas}
 * . Clients should extend this interface with an ID for each definition of an
 * own {@link AdaptedGradientColoredAreas}. Clients can define their own
 * gradient adaptations by extending {@link PredefinedColoredAreas}. A gradient
 * can be set by
 * {@link IGaService#setRenderingStyle(AbstractStyle, AdaptedGradientColoredAreas)}
 * .
 * 
 * @see {@link PredefinedColoredAreas}
 * @see {@link AdaptedGradientColoredAreas}
 * @see {@link GradientColoredAreas}
 * @see {@link GradientColoredArea}
 * @see {@link GradientColoredLocation}
 * 
 */
public interface IPredefinedRenderingStyle {

	/**
	 * The ID for a blue-to-white gradient with a gloss-effect.
	 */
	public static final String BLUE_WHITE_GLOSS_ID = "blue-white-gloss"; //$NON-NLS-1$

	/**
	 * The ID for a blue-to-white gradient.
	 */
	public static final String BLUE_WHITE_ID = "blue-white"; //$NON-NLS-1$

	/**
	 * The ID for a light yellow color.
	 */
	public static final String LIGHT_YELLOW_ID = "light-yellow"; //$NON-NLS-1$

	/**
	 * The ID for a light gray color.
	 */
	public static final String LIGHT_GRAY_ID = "light-gray"; //$NON-NLS-1$

	/**
	 * The ID for a copper-to-white gradient with a gloss-effect.
	 */
	public static final String COPPER_WHITE_GLOSS_ID = "copper-white-gloss"; //$NON-NLS-1$

	/**
	 * The ID for a silver-to-white gradient with a gloss-effect.
	 */
	public static final String SILVER_WHITE_GLOSS_ID = "silver-white-gloss"; //$NON-NLS-1$

	/**
	 * The style-adaptation to use for default, element is not selected.
	 */
	public static final int STYLE_ADAPTATION_DEFAULT = 0;

	/**
	 * The style-adaptation to use for primary selected elements.
	 */
	public static final int STYLE_ADAPTATION_PRIMARY_SELECTED = 1;

	/**
	 * The style-adaptation to use for secondary selected elements.
	 */
	public static final int STYLE_ADAPTATION_SECONDARY_SELECTED = 2;

	/**
	 * The style-adaptation to use to indicate that an action is allowed.
	 */
	public static final int STYLE_ADAPTATION_ACTION_ALLOWED = 3;

	/**
	 * The style-adaptation to use to indicate that an action is forbidden.
	 */
	public static final int STYLE_ADAPTATION_ACTION_FORBIDDEN = 4;

}
