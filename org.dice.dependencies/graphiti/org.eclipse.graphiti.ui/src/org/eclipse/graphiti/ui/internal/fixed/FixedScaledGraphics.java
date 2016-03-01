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
package org.eclipse.graphiti.ui.internal.fixed;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ScaledGraphics;
import org.eclipse.swt.graphics.Path;

/**
 * This class is a subclass of ScaledGraphics, which only purpose is to fix bugs
 * in ScaledGraphics or implement previously not implemented methods. This class
 * does not add any Graphiti specific coding.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class FixedScaledGraphics extends ScaledGraphics {

	private Graphics graphics;

	/**
	 * If draw2d in an appropriate version is installed we use the new clipPath
	 * method. Otherwise we fall back to setClip. To avoid compile and runtime
	 * problems we use reflection.
	 */
	private static Method clipMethod = null;
	static {
		try {
			clipMethod = Graphics.class.getMethod("clipPath", Path.class); //$NON-NLS-1$
		} catch (SecurityException e) {
			// do nothing
		} catch (NoSuchMethodException e) {
			// do nothing
		}
	}

	/**
	 * Instantiates a new fixed scaled graphics.
	 * 
	 * @param graphics
	 *            the graphics
	 */
	public FixedScaledGraphics(Graphics graphics) {
		super(graphics);
		this.graphics = graphics;
	}

	// ========================================================================
	// ===================== overwritten changed methods ======================
	// ========================================================================


	@Override
	// Do not zoom anymore
	// Just forward to the wrapped Graphics
	public void fillGradient(int x, int y, int w, int h, boolean vertical) {
		graphics.fillGradient(x, y, w, h, vertical);
	}

	// ========================================================================
	// ===================== overwritten missing methods ======================
	// ========================================================================


	@Override
	// Just forward to the wrapped Graphics
	public void rotate(float degrees) {
		graphics.rotate(degrees);
	}


	@Override
	// Just forward to translate(int, int)
	public void translate(float dx, float dy) {
		graphics.translate(Math.round(dx), Math.round(dy));
	}


	@Override
	// Just forward to the wrapped Graphics
	public void drawPath(Path path) {
		graphics.drawPath(path);
	}


	@Override
	// Just forward to the wrapped Graphics
	public void fillPath(Path path) {
		graphics.fillPath(path);
	}


	public void clipPath(Path path) {
		if (clipMethod != null) {
			try {
				clipMethod.invoke(graphics, path);
			} catch (IllegalArgumentException e) {
				fallBack(path);
			} catch (IllegalAccessException e) {
				fallBack(path);
			} catch (InvocationTargetException e) {
				fallBack(path);
			}
		} else {
			setClip(path);
		}
	}


	private void fallBack(Path path) {
		clipMethod = null;
		setClip(path);
	}

	@Override
	// Just forward to the wrapped Graphics
	public void setClip(Path path) {
		graphics.setClip(path);
	}
}
