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
package org.eclipse.graphiti.ui.internal.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.graphiti.internal.services.GraphitiInternal;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.util.DataTypeTransformation;

/**
 * The Class FreeFormConnectionEditPart.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class FreeFormConnectionEditPart extends ConnectionEditPart {

	/**
	 * The Constructor.
	 * 
	 * @param configurationProvider
	 *            the configuration provider
	 * @param connection
	 *            the connection
	 */
	public FreeFormConnectionEditPart(IConfigurationProvider configurationProvider, Connection connection) {
		super(configurationProvider, connection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.ui.internal.parts.ConnectionEditPart#createFigure ()
	 */
	@Override
	public IFigure createFigure() {
		IFigure ret = super.createFigure();
		if (ret instanceof org.eclipse.draw2d.Connection) {
			org.eclipse.draw2d.Connection draw2dConnection = (org.eclipse.draw2d.Connection) ret;
			draw2dConnection.setConnectionRouter(new BendpointConnectionRouter());
		}
		return ret;
	}

	private void refreshBendpoints() {
		FreeFormConnection ffc = (FreeFormConnection) getConnection();
		if (!GraphitiInternal.getEmfService().isObjectAlive(ffc)) {
			return;
		}

		List<Bendpoint> pointList = new ArrayList<Bendpoint>();
		List<Point> bendpoints = ffc.getBendpoints();
		for (Point point : bendpoints) {
			Bendpoint draw2dBendPoint = DataTypeTransformation.toDraw2dBendPoint(point);
			pointList.add(draw2dBendPoint);
		}
		getConnectionFigure().setRoutingConstraint(pointList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.parts.ConnectionEditPart#
	 * createEditPolicies()
	 */
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE, getConfigurationProvider().getEditPolicyFactory()
				.createConnectionBendpointsEditPolicy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.graphiti.ui.internal.parts.ConnectionEditPart#
	 * refreshVisuals()
	 */
	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		refreshBendpoints();
	}
}
