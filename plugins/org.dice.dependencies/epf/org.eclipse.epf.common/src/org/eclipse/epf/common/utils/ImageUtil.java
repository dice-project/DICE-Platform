//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.common.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for creating image files.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ImageUtil {

	// The BufferedImage cache.
	private static HashMap<String, BufferedImage> imageCache = new HashMap<String, BufferedImage>();

	/**
	 * Private constructor to prevent this class from being instantiated. All
	 * methods in this class should be static.
	 */
	private ImageUtil() {
	}

	/**
	 * Creates a <code>BufferedImage</code> from the given image file.
	 * 
	 * @param imageFile
	 *            an image file
	 * @param c
	 *            an AWT component
	 */
	public static BufferedImage getBufferedImage(String imageFile, Component c) {
		if (imageFile == null || c == null) {
			return null;
		}

		BufferedImage bufferedImage = (BufferedImage) imageCache.get(imageFile);
		if (bufferedImage != null) {
			return bufferedImage;
		}

		Image image = c.getToolkit().getImage(imageFile);
		MediaTracker tracker = new MediaTracker(c);
		tracker.addImage(image, 0);
		try {
			tracker.waitForAll();
		} catch (InterruptedException e) {
		}

		bufferedImage = new BufferedImage(image.getWidth(c),
				image.getHeight(c), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.drawImage(image, 0, 0, c);
		imageCache.put(imageFile, bufferedImage);

		return bufferedImage;
	}

	/**
	 * Returns <code>true</code> if the given image is loaded successfully.
	 * 
	 * @param image
	 *            an AWT image
	 * @param c
	 *            an AWT component
	 */
	public static boolean waitForImage(Image image, Component c) {
		MediaTracker tracker = new MediaTracker(c);
		tracker.addImage(image, 0);
		try {
			tracker.waitForAll();
		} catch (InterruptedException ie) {
		}
		return (!tracker.isErrorAny());
	}

	/**
	 * Draws the given text.
	 * 
	 * @param g2d
	 *            the <code>Graphics2D</code> context
	 * @param text
	 *            the text to be rendered
	 * @param font
	 *            the font used for rendering the text
	 * @param color
	 *            the color used for rendering the text
	 * @param x
	 *            the horizontal coordinate used for rendering the text
	 * @param y
	 *            the verticalcoordinate used for rendering the text
	 */
	public static void drawText(Graphics2D g2d, String text, Font font,
			Color color, int x, int y) {
		if (text == null || text.length() == 0) {
			return;
		}
		g2d.setColor(color);
		FontRenderContext frc = g2d.getFontRenderContext();
		TextLayout textlayout = new TextLayout(text, font, frc);
		textlayout.draw(g2d, x, y);
	}

	/**
	 * Draws the given text and wraps it based on the given width.
	 * 
	 * @param g2d
	 *            the <code>Graphics2D</code> context
	 * @param text
	 *            the text to be rendered
	 * @param font
	 *            the font used for rendering the text
	 * @param color
	 *            the color used for rendering the text
	 * @param x
	 *            the horizontal coordinate used for rendering the text
	 * @param y
	 *            the verticalcoordinate used for rendering the text
	 * @param width
	 *            the maximum width used for wrapping the text
	 * @param maxLine
	 *            the maximum number of line of wrapped text
	 */
	public static void drawText(Graphics2D g2d, String text, Font font,
			Color color, int x, int y, float width, int maxLine) {
		if (text == null || text.length() == 0) {
			return;
		}
		float maxWrapWidth = width - 10;
		g2d.setColor(color);
		Point2D.Float pen = new Point2D.Float(x, y);
		Map<TextAttribute, Font> attributes = new HashMap<TextAttribute, Font>();
		attributes.put(TextAttribute.FONT, font);
		AttributedCharacterIterator textIterator = new AttributedString(text,
				attributes).getIterator();
		FontRenderContext frc = g2d.getFontRenderContext();
		LineBreakMeasurer measurer = new LineBreakMeasurer(textIterator, frc);
		int line = 1;
		while (line <= maxLine) {
			TextLayout layout = measurer.nextLayout(maxWrapWidth);
			if (layout == null)
				break;
			pen.y += layout.getAscent();
			float dx = 0;
			if (layout.isLeftToRight()) {
				dx = (width - layout.getAdvance());
			}
			layout.draw(g2d, pen.x + dx / 2, pen.y);
			pen.y += layout.getDescent() + layout.getLeading() - 1;
			line++;
		}
	}

}
