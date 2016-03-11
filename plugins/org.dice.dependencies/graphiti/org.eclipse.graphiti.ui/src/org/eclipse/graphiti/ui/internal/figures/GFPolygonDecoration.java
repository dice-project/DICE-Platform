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
package org.eclipse.graphiti.ui.internal.figures;

import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Transform;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.ui.internal.parts.IPictogramElementDelegate;

/////////////////////////////////////////////////////////////////////////
// THIS CLASS SHOULD ALWAYS BE KEPT IN SYNC WITH GFPolylineDecoration //
/////////////////////////////////////////////////////////////////////////

/**
 * A Graphiti Polygon Rotatable Decoration.
 * <p>
 * Idea: a decorator-template defines the form of the shape. Whenever the
 * decorator-location or decorator-reference-point is set, the
 * decorator-template is translated/rotated depending on them. This transformed
 * decorator-template is then set as the points of this Polygon. Afterwards the
 * real functionality comes from the super-class GFPolygon.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFPolygonDecoration extends GFPolygon implements RotatableDecoration {

	/**
	 * The decorator-location as described in {@link #getDecoratorLocation()}.
	 */
	private Point decoratorLocation = new Point();

	/**
	 * The decorator-reference-point as described in
	 * {@link #getDecoratorReferencePoint()}.
	 */
	private Point decoratorReferencePoint = new Point();

	/**
	 * The decorator-template as described in {@link #getDecoratorTemplate()}.
	 */
	private PointList decoratorTemplate = new PointList();

	/**
	 * Creates a new GFPolygonDecoration.
	 * 
	 * @param pictogramElementDelegate
	 *            The PictogramElementDelegate which provides the
	 *            GraphicsAlgorithm.
	 * @param graphicsAlgorithm
	 *            The GraphicsAlgorithm which provides the values to paint this
	 *            Shape.
	 */
	public GFPolygonDecoration(IPictogramElementDelegate pictogramElementDelegate, GraphicsAlgorithm graphicsAlgorithm) {
		super(pictogramElementDelegate, graphicsAlgorithm);
	}

	/**
	 * Sets the decorator-template which defines the form of the shape.
	 * 
	 * @param pointList
	 *            The points to set as template.
	 */
	public void setDecoratorTemplate(PointList points) {
		decoratorTemplate.removeAllPoints();
		decoratorTemplate.addAll(points);
		processRotatableDecorationValues();
	}

	// ======================= interface RotatableDecoration ==================

	/**
	 * Sets the decorator-location.
	 * 
	 * @param p
	 *            The decorator-location to set.
	 * 
	 * @see #getDecoratorLocation()
	 */
	@Override
	public void setLocation(Point p) {
		decoratorLocation.setLocation(p);
		processRotatableDecorationValues();
	}

	/**
	 * Sets the decorator-reference-point.
	 * 
	 * @param ref
	 *            The decorator-reference-point to set.
	 * 
	 * @see #getDecoratorReferencePoint()
	 */
	public void setReferencePoint(Point ref) {
		decoratorReferencePoint.setLocation(ref);
		processRotatableDecorationValues();
	}

	/**
	 * After the decorator-location or decorator-reference-point changed, the
	 * translated/rotated points have to be re-calculated and set.
	 */
	protected void processRotatableDecorationValues() {
		Transform transform = new Transform();
		transform.setTranslation(getDecoratorLocation().x, getDecoratorLocation().y);
		transform.setRotation(Math.atan2(getDecoratorLocation().y - getDecoratorReferencePoint().y, getDecoratorLocation().x
				- getDecoratorReferencePoint().x));
		PointList newPoints = new PointList();
		for (int i = 0; i < decoratorTemplate.size(); i++) {
			newPoints.addPoint(transform.getTransformed(decoratorTemplate.getPoint(i)));
		}
		setPoints(newPoints);
	}

	// ============================== helper methods ==========================

	/**
	 * Returns the decorator-location. The decorator-location is the start-point
	 * of the connection. This means that at this point the decorator and the
	 * figure shall touch.
	 * 
	 * @return The decorator-location.
	 * 
	 * @see #setLocation(Point)
	 */
	protected final Point getDecoratorLocation() {
		return decoratorLocation;
	}

	/**
	 * Returns the decorator-reference-point. The decorator-reference-point is
	 * the end-point of the connection.
	 * 
	 * @return The decorator-reference-point.
	 * 
	 * @see #setReferencePoint(Point)
	 */
	protected final Point getDecoratorReferencePoint() {
		return decoratorReferencePoint;
	}

	/**
	 * Returns the decorator-template <b>by reference</b>. It must not be
	 * changed, use {@link #setDecoratorTemplate(PointList)} instead. The
	 * decorator-template defines the form of this Shape.
	 * 
	 * @return The decorator-template.
	 * 
	 * @see #setDecoratorTemplate(PointList)
	 */
	protected final PointList getDecoratorTemplate() {
		return decoratorTemplate;
	}
}
