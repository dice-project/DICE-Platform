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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.graphiti.util.ColorUtil;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Resource;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ResourceRegistry implements IResourceRegistry {
	private Map<Object, Resource> registry = new HashMap<Object, Resource>();

	/**
	 * Returns a SWT color for the given red, green and blue values expressed as
	 * ints in the range 0 to 255 (where 0 is black and 255 is full brightness).
	 * <p>
	 * If this this registry disposes, this returned color will also disposed.
	 * </p>
	 * 
	 * @param red
	 *            the amount of red in the color
	 * @param green
	 *            the amount of green in the color
	 * @param blue
	 *            the amount of blue in the color
	 * @return a SWT color
	 * 
	 * @see org.eclipse.swt.graphics.Color
	 */
	public Color getSwtColor(int red, int green, int blue) {
		RGB rgb = new RGB(red, green, blue);
		Resource ret = registry.get(rgb);
		if (ret == null) {
			ret = new Color(null, rgb);
			registry.put(rgb, ret);
			ResourceManager.getResourceManager().manageResource(this, ret);
		}

		Color retColor = null;
		if (ret instanceof Color) {
			retColor = (Color) ret;
		}
		return retColor;
	}

	/**
	 * Calls dispose() on all registered resources.
	 */
	public void dispose() {
		ResourceManager.getResourceManager().cleanUpResources(this);
		registry.clear();
	}

	/**
	 * Returns a SWT color for for a given String, which defines the RGB values
	 * in hexadecimal format. This means, that the String must have a length of
	 * 6 characters. Example: <code>getColor("FF0000")</code> returns a red
	 * color.
	 * <p>
	 * If this this registry disposes, this returned color will also disposed.
	 * </p>
	 * 
	 * @param hexRGBString
	 *            The RGB values in hexadecimal format.
	 * @return a SWT color
	 * 
	 * @see org.eclipse.swt.graphics.Color
	 */
	public Color getSwtColor(String hexRGBString) {
		return getSwtColor(ColorUtil.getRedFromHex(hexRGBString), ColorUtil.getGreenFromHex(hexRGBString), ColorUtil
				.getBlueFromHex(hexRGBString));
	}
}
