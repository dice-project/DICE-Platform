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
package org.eclipse.graphiti.services;

import java.util.Collection;
import java.util.List;

import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.StyleContainer;
import org.eclipse.graphiti.mm.algorithms.AbstractText;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.MultiText;
import org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.util.IColorConstant;

/**
 * The interface IGaCreateService provides services for the creation of all
 * available graphics algorithm's. E.g. Polygon, Rectangle, Text, ...
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IGaCreateService {

	/**
	 * Creates a multitext graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setFont(createFont(DEFAULT_FONT, 8));<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new multiline text
	 */
	public MultiText createDefaultMultiText(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a multitext graphics algorithm with the given text.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setFont(createFont(DEFAULT_FONT, 8));<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @return the new multiline text
	 */
	public MultiText createDefaultMultiText(GraphicsAlgorithmContainer gaContainer, String value);

	/**
	 * Creates a text graphics algorithm with the default font (Arial, size 8).
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setFont(createFont(DEFAULT_FONT, 8));<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new text
	 */
	public Text createDefaultText(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a text graphics algorithm with the default font (Arial, size 8)
	 * and the given text.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setFont(createFont(DEFAULT_FONT, 8));<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @return the new text
	 */
	public Text createDefaultText(GraphicsAlgorithmContainer gaContainer, String value);

	/**
	 * Creates an ellipse graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new ellipse
	 */
	public Ellipse createEllipse(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a font datatype.
	 * 
	 * @param text
	 *            the text
	 * @param name
	 *            the name
	 * @param size
	 *            the size
	 * @return the new font
	 */
	public Font createFont(AbstractText text, String name, int size);

	/**
	 * Creates a font datatype.
	 * 
	 * @param text
	 *            the text
	 * @param name
	 *            the name
	 * @param size
	 *            the size
	 * @param isItalic
	 *            the is italic
	 * @param isBold
	 *            the is bold
	 * @return the new font
	 */
	public Font createFont(AbstractText text, String name, int size, boolean isItalic, boolean isBold);

	/**
	 * Creates a font datatype.
	 * 
	 * @param style
	 *            the style
	 * @param name
	 *            the name
	 * @param size
	 *            the size
	 * @return the new font
	 */
	public Font createFont(Style style, String name, int size);

	/**
	 * Creates a font datatype.
	 * 
	 * @param style
	 *            the style
	 * @param name
	 *            the name
	 * @param size
	 *            the size
	 * @param isItalic
	 *            the is italic
	 * @param isBold
	 *            the is bold
	 * @return the new font
	 */
	public Font createFont(Style style, String name, int size, boolean isItalic, boolean isBold);

	/**
	 * Creates a image graphics algorithm with the given image id.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * image.setId(imageId);<br>
	 * image.setProportional(false);<br>
	 * image.setStretchH(false);<br>
	 * image.setStretchV(false);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param imageId
	 *            the image id
	 * @return the new image
	 */
	public Image createImage(GraphicsAlgorithmContainer gaContainer, String imageId);

	/**
	 * Create an invisible rectangle.
	 * 
	 * @param pe
	 *            the pictogram element to create the rectangle
	 * @return the rectangle
	 */
	public Rectangle createInvisibleRectangle(PictogramElement pe);

	/**
	 * Creates a multitext graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new multiline text
	 */
	public MultiText createMultiText(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a multitext graphics algorithm with the given text.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @return the new multiline text
	 */
	public MultiText createMultiText(GraphicsAlgorithmContainer gaContainer, String value);

	/**
	 * Creates the platform graphics algorithm.
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param id
	 *            the id
	 * @return the platform graphics algorithm
	 */
	public PlatformGraphicsAlgorithm createPlatformGraphicsAlgorithm(GraphicsAlgorithmContainer gaContainer, String id);

	/**
	 * Creates a point datatype for the given x/y coordinates.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @return the new point
	 */
	public Point createPoint(int x, int y);

	/**
	 * Creates a point datatype for the given x/y coordinates. The additional
	 * before/after parameters defined at which distance before/after the point
	 * a rounded curve will start/end.
	 * 
	 * Note, that before/after parameters have only an effect, if the graphics
	 * algorithm support them, e.g. polygon and polyline.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param before
	 *            the distance before, if supported
	 * @param after
	 *            the distance after, if supported
	 * @return the new point
	 */
	public Point createPoint(int x, int y, int before, int after);

	/**
	 * Creates a list of point datatypes for the given x/y coordinates.
	 * 
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ...,xN, yN]
	 * 
	 * @return the point list
	 */
	public List<Point> createPointList(int[] xy);

	/**
	 * Creates a list of point datatypes for the given x/y coordinates.
	 * 
	 * Note, that before/after parameters have only an effect, if the graphics
	 * algorithm support them, e.g. polygon and polyline.
	 * 
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @param beforeAfter
	 *            The before/after parameters: [before0, after0, ..., beforeN,
	 *            afterN]
	 * 
	 * @return the point list
	 */
	public List<Point> createPointList(int[] xy, int beforeAfter[]);

	/**
	 * Creates a polygon graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new polygon
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a polygon graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param points
	 *            collection of point
	 * @return the new polygon
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, Collection<Point> points);

	/**
	 * Creates a polygon graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @return the new polygon
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy);

	/**
	 * Creates a polygon graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @param beforeAfter
	 *            The before/after parameters: [before0, after0, ..., beforeN,
	 *            afterN]
	 * @return the new polygon
	 */
	public Polygon createPolygon(GraphicsAlgorithmContainer gaContainer, int[] xy, int beforeAfter[]);

	/**
	 * Creates a polyline graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(false); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new polyline
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a polyline graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(false); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param points
	 *            collection of point
	 * @return the new polyline
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, Collection<Point> points);

	/**
	 * Creates a polyline graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(false); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @return the new polyline
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy);

	/**
	 * Creates a polyline graphics algorithm with the given points.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(false); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param xy
	 *            The x/y coordinates: [x0, y0, ..., xN, yN]
	 * @param beforeAfter
	 *            The before/after parameters: [before0, after0, ..., beforeN,
	 *            afterN]
	 * @return the new polyline
	 */
	public Polyline createPolyline(GraphicsAlgorithmContainer gaContainer, int[] xy, int beforeAfter[]);

	/**
	 * Creates a rectangle graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new rectangle
	 */
	public Rectangle createRectangle(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a rounded rectangle graphics algorithm with the given corner
	 * dimensions.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param cornerWidth
	 *            the corner width
	 * @param cornerHeight
	 *            the corner height
	 * @return the new rounded rectangle
	 */
	public RoundedRectangle createRoundedRectangle(GraphicsAlgorithmContainer gaContainer, int cornerWidth, int cornerHeight);

	/**
	 * Shifts the color darker or lighter.
	 * 
	 * @param color
	 *            the color to be changed
	 * @param shift
	 *            negative shifts means darken the color
	 * @param diagram
	 *            the diagram
	 * @return a new color datatype with the shiftet values
	 */
	public Color createShiftedColor(Color color, int shift, Diagram diagram);

	/**
	 * Shifts the color constant darker or lighter.
	 * 
	 * @param colorConstant
	 *            the color constant to be changed
	 * @param shift
	 *            negative shifts means darken the color
	 * @return the color constant with the shifted values
	 */
	public IColorConstant createShiftedColor(IColorConstant colorConstant, int shift);

	/**
	 * Creates a style with the given id. The style is aggregated under the
	 * given container style.
	 * 
	 * @param styleContainer
	 *            container style
	 * @param id
	 *            style id
	 * @return the newly created style
	 */
	public Style createStyle(StyleContainer styleContainer, String id);

	/**
	 * Creates a text graphics algorithm.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @return the new text
	 */
	public Text createText(GraphicsAlgorithmContainer gaContainer);

	/**
	 * Creates a text graphics algorithm with the given text.
	 * <p>
	 * The following values are set by default:
	 * <p>
	 * graphicsAlgorithm.setFilled(true); <br>
	 * graphicsAlgorithm.setLineStyle(LineStyleEnum.SOLID); <br>
	 * graphicsAlgorithm.setLineVisible(true); <br>
	 * graphicsAlgorithm.setLineWidth(1);<br>
	 * graphicsAlgorithm.setTransparency(0);<br>
	 * text.setAngle(0);<br>
	 * text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);<br>
	 * text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);<br>
	 * 
	 * @param gaContainer
	 *            the container for the new graphics algorithm
	 * @param value
	 *            initial text
	 * @return the new text
	 */
	public Text createText(GraphicsAlgorithmContainer gaContainer, String value);

}
