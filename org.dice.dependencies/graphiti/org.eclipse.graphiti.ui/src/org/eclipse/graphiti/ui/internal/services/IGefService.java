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
package org.eclipse.graphiti.ui.internal.services;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.mm.algorithms.styles.Font;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.ui.internal.util.draw2d.ChopboxAnchorFixed;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IGefService {

	/**
	 * Selects the EditPart for the given model Object.
	 * 
	 * @param viewer
	 *            The viewer, which contains the EditParts.
	 * @param modelObject
	 *            The model Object, which EditPart to select.
	 */
	void selectEditPart(EditPartViewer viewer, Object modelObject);

	/**
	 * Returns the translation between the coordinate-systems of EditParts. It
	 * will translate from the coordinate-system of the source EditPart to the
	 * coordinate-system of the target EditPart. Both EditParts must be an
	 * instance of GraphicalEditPart. Otherwise it throws an Exception.
	 * 
	 * @param source
	 *            The EditPart with the source coordinate-system.
	 * @param target
	 *            The EditPart with the target coordinate-system.
	 * @return Returns the translation between the coordinate-systems of
	 *         EditParts.
	 */
	Point calculateTranslation(EditPart source, EditPart target);

	/**
	 * Returns the layout constraint for the given EditPart. It returns null, if
	 * the constraint can not be determined, for example because the child has
	 * no parent or the parent has no LayoutManager.
	 * 
	 * @param editPart
	 *            The EditPart for which to return the layout constraint.
	 * @return Returns the layout constraint for the given EditPart.
	 */
	Object getLayoutConstraint(EditPart editPart);

	EditPart findEditPartAt(EditPartViewer viewer, Point location, boolean includeConnections);

	/**
	 * Computes EditParts of the connections and related ConnectionDecorators
	 * which live inside the containment hierarchy of the given edit part.
	 * 
	 * @param ep
	 *            the EditPart whose inner connection and ConnectionDecorators
	 *            should be computed
	 * @returns List of EditParts (connection and Decorator) which live in
	 *          EditPart
	 */
	List<EditPart> getConnectionsContainedInEditPart(EditPart ep);

	/**
	 * @param c
	 * @param d
	 * @return
	 */
	Point getConnectionPointAt(Connection c, double d);

	/**
	 * This method has to be kept in sync with
	 * {@link ChopboxAnchorFixed#getLocation(Point)}.
	 */
	Point getChopboxLocationOnBox(Point reference, Rectangle box);

	/**
	 * @param c
	 * @param absDistance
	 *            absDistance > 0 means distance from beginning. absDistance < 0
	 *            means distance from connection end.
	 * @return
	 */
	Point getAbsolutePointOnConnection(Connection c, double distance);

	Point getDistantPoint(Point start, Point end, double distance);

	Point getDistantPoint(int startX, int startY, int endX, int endY, double distance);

	/**
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @param d
	 * @return
	 */
	Point getPointAt(int startX, int startY, int endX, int endY, double d);

	Point getPointAt(Point start, Point end, double d);

	double getDistance(Point[] points);

	/**
	 * Calculates and returns the size of the text.
	 * 
	 * @param text
	 * @param font
	 * 
	 * @return the size of the text
	 */
	IDimension calculateTextSize(String text, Font font);

	/**
	 * @param draw2dPoints
	 */
	void mirrorArray(Point[] draw2dPoints);

	/**
	 * Provides the direct children of the edit part.
	 * 
	 * @param editPart
	 * @return the direct child edit parts
	 */
	public List<EditPart> getEditPartChildren(EditPart editPart);

	/**
	 * Provides the source connections of the graphical edit part.
	 * 
	 * @param graphicalEditPart
	 * @return the source connections of the graphical edit part
	 */
	public List<GraphicalEditPart> getSourceConnections(GraphicalEditPart graphicalEditPart);

	/**
	 * Provides the target connections of the graphical edit part.
	 * 
	 * @param graphicalEditPart
	 * @return the target connections of the graphical edit part
	 */
	public List<GraphicalEditPart> getTargetConnections(GraphicalEditPart graphicalEditPart);

	/**
	 * Provides the selected edit parts of the viewer.
	 * 
	 * @param editPartViewer
	 * @return the selected edit parts of the viewer
	 */
	public List<EditPart> getSelectedEditParts(EditPartViewer editPartViewer);
}