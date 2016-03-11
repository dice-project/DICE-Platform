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

/**
 * The Interface IColorConstant.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IColorConstant {

	/**
	 * The "white" color.
	 */
	IColorConstant WHITE = new ColorConstant(255, 255, 255);

	/**
	 * The "light light gray" color.
	 */
	IColorConstant LIGHT_LIGHT_GRAY = new ColorConstant(250, 250, 250);

	/**
	 * The "light gray" color.
	 */
	IColorConstant LIGHT_GRAY = new ColorConstant(192, 192, 192);

	/**
	 * The "gray" color.
	 */
	IColorConstant GRAY = new ColorConstant(128, 128, 128);

	/**
	 * The "dark gray" color.
	 */
	IColorConstant DARK_GRAY = new ColorConstant(64, 64, 64);

	/**
	 * The "black" color.
	 */
	IColorConstant BLACK = new ColorConstant(0, 0, 0);

	/**
	 * The "red" color.
	 */
	IColorConstant RED = new ColorConstant(255, 0, 0);

	/**
	 * The "orange" color.
	 */
	IColorConstant ORANGE = new ColorConstant(255, 196, 0);

	/**
	 * The "yellow" color.
	 */
	IColorConstant YELLOW = new ColorConstant(255, 255, 0);

	/**
	 * The "green" color.
	 */
	IColorConstant GREEN = new ColorConstant(0, 255, 0);

	/**
	 * The "light green" color.
	 */
	IColorConstant LIGHT_GREEN = new ColorConstant(96, 255, 96);

	/**
	 * The "dark green" color.
	 */
	IColorConstant DARK_GREEN = new ColorConstant(0, 127, 0);

	/**
	 * The "cyan" color.
	 */
	IColorConstant CYAN = new ColorConstant(0, 255, 255);

	/**
	 * The "light blue" color.
	 */
	IColorConstant LIGHT_BLUE = new ColorConstant(127, 127, 255);

	/**
	 * The "blue" color.
	 */
	IColorConstant BLUE = new ColorConstant(0, 0, 255);

	/**
	 * The "dark blue" color.
	 */
	IColorConstant DARK_BLUE = new ColorConstant(0, 0, 127);
	//
	// === Colors aligned with Visual Composer ===
	//
	// Color Style: BLUE
	//
	/**
	 * The "Visual Composer very light blue" color.
	 */
	IColorConstant VC_VERY_LIGHT_BLUE = new ColorConstant(245, 247, 250);

	/**
	 * The "Visual Composer light blue" color.
	 */
	IColorConstant VC_LIGHT_BLUE = new ColorConstant(224, 231, 247);

	/**
	 * The "Visual Composer medium blue" color.
	 */
	IColorConstant VC_MEDIUM_BLUE = new ColorConstant(176, 192, 216);

	/**
	 * The "Visual Composer dark blue" color.
	 */
	IColorConstant VC_DARK_BLUE = new ColorConstant(51, 102, 153);
	//
	// Color Style: GREY
	//
	/**
	 * The "Visual Composer light gray" color.
	 */
	IColorConstant VC_LIGHT_GREY = new ColorConstant(221, 221, 221);

	/**
	 * The "Visual Composer medium gray" color.
	 */
	IColorConstant VC_MEDIUM_GREY = new ColorConstant(178, 178, 178);

	/**
	 * The "Visual Composer dark gray" color.
	 */
	IColorConstant VC_DARK_GREY = new ColorConstant(77, 77, 77);
	//
	// Color Style: PURPLE
	//
	/**
	 * The "Visual Composer light purple" color.
	 */
	IColorConstant VC_LIGHT_PURPLE = new ColorConstant(242, 242, 255);

	/**
	 * The "Visual Composer medium purple" color.
	 */
	IColorConstant VC_MEDIUM_PURPLE = new ColorConstant(223, 223, 255);

	/**
	 * The "Visual Composer dark purple" color.
	 */
	IColorConstant VC_DARK_PURPLE = new ColorConstant(139, 157, 213);
	//
	// Color Style: GREEN
	//
	/**
	 * The "Visual Composer very light green" color.
	 */
	IColorConstant VC_VERY_LIGHT_GREEN = new ColorConstant(242, 247, 242);

	/**
	 * The "Visual Composer light green" color.
	 */
	IColorConstant VC_LIGHT_GREEN = new ColorConstant(179, 217, 179);

	/**
	 * The "Visual Composer medium green" color.
	 */
	IColorConstant VC_MEDIUM_GREEN = new ColorConstant(111, 149, 111);

	/**
	 * The "Visual Composer dark green" color.
	 */
	IColorConstant VC_DARK_GREEN = new ColorConstant(0, 102, 0);
	//
	// Color Style: BROWN
	//
	/**
	 * The "Visual Composer light brown" color.
	 */
	IColorConstant VC_LIGHT_BROWN = new ColorConstant(213, 214, 189);

	/**
	 * The "Visual Composer medium brown" color.
	 */
	IColorConstant VC_MEDIUM_BROWN = new ColorConstant(162, 149, 126);

	/**
	 * The "Visual Composer dark brown" color.
	 */
	IColorConstant VC_DARK_BROWN = new ColorConstant(147, 128, 88);
	//
	// Color Style: ORANGE
	//
	/**
	 * The "Visual Composer light orange" color.
	 */
	IColorConstant VC_LIGHT_ORANGE = new ColorConstant(252, 207, 153);

	/**
	 * The "Visual Composer medium orange" color.
	 */
	IColorConstant VC_MEDIUM_ORANGE = new ColorConstant(255, 153, 51);

	/**
	 * The "Visual Composer dark orange" color.
	 */
	IColorConstant VC_DARK_ORANGE = new ColorConstant(187, 102, 0);
	//
	// Color Style: BEIGE
	//
	/**
	 * The "Visual Composer light beige" color.
	 */
	IColorConstant VC_LIGHT_BEIGE = new ColorConstant(255, 238, 221);

	/**
	 * The "Visual Composer medium beige" color.
	 */
	IColorConstant VC_MEDIUM_BEIGE = new ColorConstant(255, 219, 181);

	/**
	 * The "Visual Composer dark beige" color.
	 */
	IColorConstant VC_DARK_BEIGE = new ColorConstant(254, 192, 164);
	//
	// Color Style: GREYISH-BLUE
	//
	/**
	 * The "Visual Composer light greyish blue" color.
	 */
	IColorConstant VC_LIGHT_GREYISH_BLUE = new ColorConstant(222, 231, 239);

	/**
	 * The "Visual Composer medium greyish blue" color.
	 */
	IColorConstant VC_MEDIUM_GREYISH_BLUE = new ColorConstant(199, 208, 217);

	/**
	 * The "Visual Composer dark greyish blue" color.
	 */
	IColorConstant VC_DARK_GREYISH_BLUE = new ColorConstant(113, 131, 152);
	//
	// selection and selection handles
	//
	/**
	 * The handle foreground color.
	 */
	IColorConstant HANDLE_FG = VC_DARK_ORANGE;

	/**
	 * The handle background color.
	 */
	IColorConstant HANDLE_BG = VC_MEDIUM_ORANGE;

	/**
	 * The foreground selection color of a shape.
	 */
	IColorConstant SHAPE_SELECTION_FG = VC_LIGHT_ORANGE;

	/**
	 * The foreground selection color of a connection.
	 */
	IColorConstant CONNECTION_SELECTION_FG = VC_MEDIUM_ORANGE;

	/**
	 * Returns the amount of red in the color, from 0 to 255.
	 * 
	 * @return the red component of the color
	 */
	int getRed();

	/**
	 * Returns the amount of green in the color, from 0 to 255.
	 * 
	 * @return the green component of the color
	 */
	int getGreen();

	/**
	 * Returns the amount of blue in the color, from 0 to 255.
	 * 
	 * @return the blue component of the color
	 */
	int getBlue();
}
