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
package org.eclipse.graphiti.ui.internal.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.ExclusionSearch;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.TextUtilities;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.datatypes.ILocation;
import org.eclipse.graphiti.internal.datatypes.impl.DimensionImpl;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.ChopboxAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ILayoutService;
import org.eclipse.graphiti.ui.internal.parts.ShapeEditPart;
import org.eclipse.graphiti.ui.internal.services.IGefService;
import org.eclipse.graphiti.ui.internal.util.DataTypeTransformation;
import org.eclipse.graphiti.ui.internal.util.gef.ScalableRootEditPartAnimated;

/**
 * A collection of static helper methods regarding GEF.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GefService implements IGefService {

	protected static final String PE = "*PE* "; //$NON-NLS-1$

	public void selectEditPart(EditPartViewer viewer, Object modelObject) {
		if (modelObject == null)
			return;

		viewer.getControl().forceFocus();
		Object editpart = viewer.getEditPartRegistry().get(modelObject);
		if (editpart instanceof EditPart) {
			viewer.flush();
			viewer.select((EditPart) editpart);
		}
	}

	public Point calculateTranslation(EditPart source, EditPart target) {
		if (!(source instanceof GraphicalEditPart && target instanceof GraphicalEditPart))
			throw new RuntimeException("Both EditParts must be an instance of GraphicalEditPart: " + source + " " + target); //$NON-NLS-1$ //$NON-NLS-2$

		Point result = new Point(0, 0);
		if (source == target) // quick-check
			return result;

		((GraphicalEditPart) source).getContentPane().translateToAbsolute(result);
		((GraphicalEditPart) target).getContentPane().translateToRelative(result);
		return result;
	}

	public Object getLayoutConstraint(EditPart editPart) {
		if (editPart instanceof GraphicalEditPart) {
			IFigure childFigure = ((GraphicalEditPart) editPart).getFigure();
			if (childFigure.getParent() != null && childFigure.getParent().getLayoutManager() != null) { // should
				// always be
				// true
				// ask the parent for the current constraints
				Object constraint = childFigure.getParent().getLayoutManager().getConstraint(childFigure);
				return constraint;
			}
		}
		return null;
	}

	public EditPart findEditPartAt(EditPartViewer viewer, Point location, boolean includeConnections) {

		EditPart editPart = findObjectAt(viewer, location, includeConnections);

		if (editPart instanceof ScalableRootEditPartAnimated) {
			List<EditPart> children = getEditPartChildren(editPart);
			if (children.size() > 0) {
				editPart = children.get(0);
			}
		}
		return editPart;
	}

	private EditPart findObjectAt(final EditPartViewer viewer, Point pt, boolean includeConnections) {
		class ConditionalTreeSearch extends ExclusionSearch {
			ConditionalTreeSearch(Collection<?> coll) {
				super(coll);
			}

			@Override
			public boolean accept(IFigure figure) {
				EditPart editpart = null;
				while (editpart == null && figure != null) {
					editpart = (EditPart) viewer.getVisualPartMap().get(figure);
					figure = figure.getParent();
				}
				return editpart != null;
			}
		}

		ScalableFreeformRootEditPart rootEditPart = (ScalableFreeformRootEditPart) viewer.getRootEditPart();
		IFigure figure = null;

		if (includeConnections) {
			IFigure connectionLayer = rootEditPart.getLayer(LayerConstants.CONNECTION_LAYER);
			figure = connectionLayer.findFigureAt(pt.x, pt.y, new ConditionalTreeSearch(Collections.EMPTY_LIST));
		}
		// if figure not searched or not found on connection layer try to find a
		// figure on the primary layer
		if (figure == null) {
			IFigure primaryLayer = rootEditPart.getLayer(LayerConstants.PRIMARY_LAYER);
			figure = primaryLayer.findFigureAt(pt.x, pt.y, new ConditionalTreeSearch(Collections.EMPTY_LIST));
		}

		EditPart part = null;
		while (part == null && figure != null) {
			part = (EditPart) viewer.getVisualPartMap().get(figure);
			figure = figure.getParent();
		}
		if (part == null)
			return viewer.getContents();
		return part;
	}

	public List<EditPart> getConnectionsContainedInEditPart(EditPart ep) {
		// Compute connections whose start and target are somewhere in the
		// editpart sub hierarchy of ep
		List<EditPart> childEditParts = getAllEditPartChildren(ep);
		List<Connection> sourceConnections = new ArrayList<Connection>();
		List<Connection> targetConnections = new ArrayList<Connection>();
		for (EditPart cep : childEditParts) {
			if (cep instanceof ShapeEditPart) {
				ShapeEditPart sep = (ShapeEditPart) cep;
				sourceConnections.addAll(sep.getModelSourceConnections());
				targetConnections.addAll(sep.getModelTargetConnections());
			}
		}
		sourceConnections.retainAll(targetConnections);

		// Extract edit parts and decorators from the connections
		List<EditPart> result = new ArrayList<EditPart>();
		for (Connection connection : sourceConnections) {
			Object connectionEditPart = ep.getRoot().getViewer().getEditPartRegistry().get(connection);
			result.add((EditPart) connectionEditPart);
			EList<ConnectionDecorator> connectionDecorators = connection.getConnectionDecorators();
			for (ConnectionDecorator decorator : connectionDecorators) {
				Object decoratorEditPart = ep.getRoot().getViewer().getEditPartRegistry().get(decorator);
				result.add((EditPart) decoratorEditPart);
			}
		}
		return result;

	}

	private List<EditPart> getAllEditPartChildren(EditPart ep) {
		List<EditPart> res = new ArrayList<EditPart>();
		List<EditPart> children = getEditPartChildren(ep);
		for (EditPart editPart : children) {
			res.add(editPart);
			res.addAll(getAllEditPartChildren(editPart));
		}
		return res;
	}

	public Point getConnectionPointAt(Connection c, double d) {
		Point ret = null;

		Anchor startAnchor = c.getStart();
		final ILayoutService layoutService = Graphiti.getLayoutService();
		ILocation startLocation = layoutService.getLocationRelativeToDiagram(startAnchor);
		Point startPoint = new Point(startLocation.getX(), startLocation.getY());

		Anchor endAnchor = c.getEnd();
		ILocation endLocation = layoutService.getLocationRelativeToDiagram(endAnchor);
		Point endPoint = new Point(endLocation.getX(), endLocation.getY());

		// special solutions for chopbox anchors
		if (startAnchor instanceof ChopboxAnchor || endAnchor instanceof ChopboxAnchor) {
			if (startAnchor instanceof ChopboxAnchor) {
				ChopboxAnchor cbStartAnchor = (ChopboxAnchor) startAnchor;
				GraphicsAlgorithm parentGa = cbStartAnchor.getParent().getGraphicsAlgorithm();
				Shape shape = (Shape) cbStartAnchor.getParent();
				ILocation location = layoutService.getLocationRelativeToDiagram(shape);
				Rectangle parentRect = new Rectangle(location.getX(), location.getY(), parentGa.getWidth(), parentGa.getHeight());

				Point pointNextToStartAnchor = endPoint.getCopy();

				if (c instanceof FreeFormConnection) {
					FreeFormConnection ffc = (FreeFormConnection) c;
					List<org.eclipse.graphiti.mm.algorithms.styles.Point> bendpoints = ffc.getBendpoints();
					if (!bendpoints.isEmpty()) {
						org.eclipse.graphiti.mm.algorithms.styles.Point firstBendpoint = bendpoints.get(0);
						pointNextToStartAnchor.setLocation(firstBendpoint.getX(), firstBendpoint.getY());
					}
				}

				Point chopboxLocationOnBox = getChopboxLocationOnBox(pointNextToStartAnchor, parentRect);
				startPoint.setLocation(chopboxLocationOnBox);
			}

			if (endAnchor instanceof ChopboxAnchor) {
				ChopboxAnchor cbEndAnchor = (ChopboxAnchor) endAnchor;
				GraphicsAlgorithm parentGa = cbEndAnchor.getParent().getGraphicsAlgorithm();
				Shape shape = (Shape) cbEndAnchor.getParent();
				ILocation location = layoutService.getLocationRelativeToDiagram(shape);
				Rectangle parentRect = new Rectangle(location.getX(), location.getY(), parentGa.getWidth(), parentGa.getHeight());

				Point pointNextToEndAnchor = startPoint.getCopy();

				if (c instanceof FreeFormConnection) {
					FreeFormConnection ffc = (FreeFormConnection) c;
					List<org.eclipse.graphiti.mm.algorithms.styles.Point> bendpoints = ffc.getBendpoints();
					if (!bendpoints.isEmpty()) {
						org.eclipse.graphiti.mm.algorithms.styles.Point lastBendpoint = bendpoints.get(bendpoints.size() - 1);
						pointNextToEndAnchor.setLocation(lastBendpoint.getX(), lastBendpoint.getY());
					}
				}

				Point chopboxLocationOnBox = getChopboxLocationOnBox(pointNextToEndAnchor, parentRect);
				endPoint.setLocation(chopboxLocationOnBox);
			}

		}

		if (c instanceof FreeFormConnection) {
			FreeFormConnection ffc = (FreeFormConnection) c;
			List<org.eclipse.graphiti.mm.algorithms.styles.Point> bendpoints = ffc.getBendpoints();
			Point[] draw2dPoints = new Point[bendpoints.size() + 2];
			{
				draw2dPoints[0] = startPoint;
				int i = 1;
				for (org.eclipse.graphiti.mm.algorithms.styles.Point pictogramsPoint : bendpoints) {
					draw2dPoints[i] = new Point(pictogramsPoint.getX(), pictogramsPoint.getY());
					i++;
				}
				draw2dPoints[i] = endPoint;
			}

			double completeDistance = getDistance(draw2dPoints);
			double absDistanceToRelPoint = completeDistance * d;

			double distanceSum = 0;
			for (int i = 0; i < draw2dPoints.length - 1; i++) {
				double oldDistanceSum = distanceSum;
				Point currentPoint = draw2dPoints[i];
				Point nextPoint = draw2dPoints[i + 1];
				// no need to check both points
				// actually this would lead to division-by-zero in the following
				// calculation
				if (currentPoint.equals(nextPoint))
					continue;

				double additionalDistanceToNext = getDistance(currentPoint, nextPoint);
				distanceSum += additionalDistanceToNext;
				if (distanceSum >= absDistanceToRelPoint) {
					double thisRelative = ((completeDistance * d) - oldDistanceSum) / additionalDistanceToNext;
					ret = getPointAt(currentPoint, nextPoint, thisRelative);
					break; // or return ret;
				}
			}

		} else {
			ret = getPointAt(startPoint.x, startPoint.y, endPoint.x, endPoint.y, d);
		}
		return ret;
	}

	public Point getChopboxLocationOnBox(Point reference, Rectangle box) {
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(box);
		r.translate(-1, -1);
		r.resize(1, 1);

		// getOwner().translateToAbsolute(r);
		float centerX = r.x + 0.5f * r.width;
		float centerY = r.y + 0.5f * r.height;

		// CHANGED: returning the center in case of a divide-by-zero is not a
		// good result
		// if (r.isEmpty() || (reference.x == (int)centerX && reference.y ==
		// (int)centerY))
		// return new Point((int)centerX, (int)centerY); //This avoids
		// divide-by-zero

		float dx = reference.x - centerX;
		float dy = reference.y - centerY;

		// r.width, r.height, dx, and dy are guaranteed to be non-zero.

		// CHANGED: in case of "nearly zero" (divide-by-zero or
		// rounding-problems) would happen.
		// Instead return a point on the border of the figure.
		// Doesn't matter which one, because it is directly in the center, so
		// take top-middle.
		float max = Math.max(Math.abs(dx) / r.width, Math.abs(dy) / r.height);
		if (max <= 0.001f) {
			return new Point((int) centerX, r.y);
		}

		float scale = 0.5f / max;

		dx *= scale;
		dy *= scale;
		centerX += dx;
		centerY += dy;

		return new Point(Math.round(centerX), Math.round(centerY));
	}

	public Point getAbsolutePointOnConnection(Connection c, double distance) {
		Point ret = null;

		Anchor startAnchor = c.getStart();
		ILocation startLocation = Graphiti.getLayoutService().getLocationRelativeToDiagram(startAnchor);
		Point startPoint = new Point(startLocation.getX(), startLocation.getY());

		Anchor endAnchor = c.getEnd();
		ILocation endLocation = Graphiti.getLayoutService().getLocationRelativeToDiagram(endAnchor);
		Point endPoint = new Point(endLocation.getX(), endLocation.getY());

		// special solutions for chopbox anchors
		if (startAnchor instanceof ChopboxAnchor || endAnchor instanceof ChopboxAnchor) {
			if (startAnchor instanceof ChopboxAnchor) {
				ChopboxAnchor cbStartAnchor = (ChopboxAnchor) startAnchor;
				GraphicsAlgorithm parentGa = cbStartAnchor.getParent().getGraphicsAlgorithm();
				Rectangle parentRect = new Rectangle(parentGa.getX(), parentGa.getY(), parentGa.getWidth(), parentGa.getHeight());

				Point pointNextToStartAnchor = endPoint.getCopy();

				if (c instanceof FreeFormConnection) {
					FreeFormConnection ffc = (FreeFormConnection) c;
					List<org.eclipse.graphiti.mm.algorithms.styles.Point> bendpoints = ffc.getBendpoints();
					if (!bendpoints.isEmpty()) {
						org.eclipse.graphiti.mm.algorithms.styles.Point firstBendpoint = bendpoints.get(0);
						pointNextToStartAnchor.setLocation(firstBendpoint.getX(), firstBendpoint.getY());
					}
				}

				Point chopboxLocationOnBox = getChopboxLocationOnBox(pointNextToStartAnchor, parentRect);
				startPoint.setLocation(chopboxLocationOnBox);
			}

			if (endAnchor instanceof ChopboxAnchor) {
				ChopboxAnchor cbEndAnchor = (ChopboxAnchor) endAnchor;
				GraphicsAlgorithm parentGa = cbEndAnchor.getParent().getGraphicsAlgorithm();
				Rectangle parentRect = new Rectangle(parentGa.getX(), parentGa.getY(), parentGa.getWidth(), parentGa.getHeight());

				Point pointNextToEndAnchor = startPoint.getCopy();

				if (c instanceof FreeFormConnection) {
					FreeFormConnection ffc = (FreeFormConnection) c;
					List<org.eclipse.graphiti.mm.algorithms.styles.Point> bendpoints = ffc.getBendpoints();
					if (!bendpoints.isEmpty()) {
						org.eclipse.graphiti.mm.algorithms.styles.Point lastBendpoint = bendpoints.get(bendpoints.size() - 1);
						pointNextToEndAnchor.setLocation(lastBendpoint.getX(), lastBendpoint.getY());
					}
				}

				Point chopboxLocationOnBox = getChopboxLocationOnBox(pointNextToEndAnchor, parentRect);
				endPoint.setLocation(chopboxLocationOnBox);
			}

		}

		if (c instanceof FreeFormConnection) {
			FreeFormConnection ffc = (FreeFormConnection) c;
			List<org.eclipse.graphiti.mm.algorithms.styles.Point> bendpoints = ffc.getBendpoints();
			Point[] draw2dPoints = new Point[bendpoints.size() + 2];
			{
				draw2dPoints[0] = startPoint;
				int i = 1;
				for (org.eclipse.graphiti.mm.algorithms.styles.Point pictogramsPoint : bendpoints) {
					draw2dPoints[i] = new Point(pictogramsPoint.getX(), pictogramsPoint.getY());
					i++;
				}
				draw2dPoints[i] = endPoint;
			}

			if (distance < 0) {
				// mirror the array
				mirrorArray(draw2dPoints);
			}

			double absDistance = Math.abs(distance);

			double distanceSum = 0;
			for (int i = 0; i < draw2dPoints.length - 1; i++) {
				Point currentPoint = draw2dPoints[i];
				Point nextPoint = draw2dPoints[i + 1];
				double additionalDistanceToNext = getDistance(currentPoint, nextPoint);
				distanceSum += additionalDistanceToNext;
				if (distanceSum >= absDistance) {
					// double thisDistance = (distance - oldDistanceSum) /
					// additionalDistanceToNext;
					// ret = getMidpoint(currentPoint, nextPoint, thisDistance);
					ret = getDistantPoint(currentPoint, nextPoint, absDistance);
					break; // or return ret;
				}
			}

		} else {
			ret = getDistantPoint(startLocation.getX(), startLocation.getY(), endLocation.getX(), endLocation.getY(), distance);
		}

		if (ret == null) {
			ret = startPoint;
		}

		return ret;
	}

	public Point getDistantPoint(Point start, Point end, double distance) {
		Point ret = getDistantPoint(start.x, start.y, end.x, end.y, distance);
		return ret;
	}

	public Point getDistantPoint(int startX, int startY, int endX, int endY, double distance) {
		Point ret;
		double completeDistance = Math.sqrt(((endX - startX) * (endX - startX)) + ((endY - startY) * (endY - startY)));
		double relative;
		if (distance >= 0) {
			relative = (distance > completeDistance) ? 1 : distance / completeDistance;
			ret = getPointAt(startX, startY, endX, endY, relative);
		} else {
			relative = (-distance > completeDistance) ? 0 : 1 - (distance / completeDistance);
			ret = getPointAt(startX, startY, endX, endY, relative);
		}

		return ret;
	}

	public Point getPointAt(int startX, int startY, int endX, int endY, double d) {
		Point ret;
		int midX = (int) Math.round((startX + d * (endX - startX)));
		int midY = (int) Math.round((startY + d * (endY - startY)));
		ret = new Point(midX, midY);
		return ret;
	}

	public Point getPointAt(Point start, Point end, double d) {
		Point ret = getPointAt(start.x, start.y, end.x, end.y, d);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.util.gef.IGefService#getDistance(org
	 * .eclipse.draw2d.geometry.Point[])
	 */
	public double getDistance(Point[] points) {
		double ret = 0;
		for (int i = 0; i < points.length - 1; i++) {
			Point currentPoint = points[i];
			Point nextPoint = points[i + 1];
			ret += getDistance(currentPoint, nextPoint);
		}
		return ret;
	}

	private double getDistance(Point start, Point end) {
		int xDist = end.x - start.x;
		int yDist = end.y - start.y;
		double ret = Math.sqrt((xDist * xDist) + (yDist * yDist));
		return ret;
	}

	public IDimension calculateTextSize(String text, Font font) {
		IDimension dimension = null;
		if (text == null || font == null || font.getName() == null) {
			return dimension;
		}

		org.eclipse.swt.graphics.Font swtFont = DataTypeTransformation.toSwtFont(font);
		if (swtFont != null) {
			Dimension se = TextUtilities.INSTANCE.getStringExtents(text, swtFont);
			if (se != null) {
				dimension = new DimensionImpl(se.width, se.height);
			}
			if (!swtFont.isDisposed()) {
				swtFont.dispose();
			}
		}
		return dimension;
	}

	public void mirrorArray(Point[] draw2dPoints) {
		int length = draw2dPoints.length;
		for (int i = 0; i < length / 2; i++) {
			Point pTemp = draw2dPoints[length - 1 - i];
			draw2dPoints[length - 1 - i] = draw2dPoints[i];
			draw2dPoints[i] = pTemp;
		}
	}

	public List<EditPart> getEditPartChildren(EditPart editPart) {
		@SuppressWarnings("unchecked")
		List<EditPart> ret = editPart.getChildren();
		return ret;
	}

	@Override
	public List<GraphicalEditPart> getSourceConnections(GraphicalEditPart graphicalEditPart) {
		@SuppressWarnings("unchecked")
		List<GraphicalEditPart> sourceConnections = graphicalEditPart.getSourceConnections();
		return sourceConnections;
	}

	@Override
	public List<GraphicalEditPart> getTargetConnections(GraphicalEditPart graphicalEditPart) {
		@SuppressWarnings("unchecked")
		List<GraphicalEditPart> targetConnections = graphicalEditPart.getTargetConnections();
		return targetConnections;
	}

	@Override
	public List<EditPart> getSelectedEditParts(EditPartViewer editPartViewer) {
		@SuppressWarnings("unchecked")
		List<EditPart> selectedEditParts = editPartViewer.getSelectedEditParts();
		return selectedEditParts;
	}
}
