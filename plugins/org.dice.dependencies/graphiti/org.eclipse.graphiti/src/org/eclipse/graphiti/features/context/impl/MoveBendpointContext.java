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
/*
 * Created on 16.11.2005
 */
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.IMoveBendpointContext;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;

/**
 * The Class MoveBendpointContext.
 */
public class MoveBendpointContext extends MoveContext implements IMoveBendpointContext {

	private Point bendpoint;

	private int bendpointIndex;

	private FreeFormConnection connection;

	/**
	 * Creates a new {@link MoveBendpointContext}.
	 * 
	 * @param bendpoint
	 *            the bendpoint
	 */
	public MoveBendpointContext(Point bendpoint) {
		super();
		setBendpoint(bendpoint);
	}

	/**
	 * Gets the bendpoint.
	 * 
	 * @return Returns the bendpoint.
	 */
	public Point getBendpoint() {
		return this.bendpoint;
	}

	/**
	 * @param bendpoint
	 *            The bendpoint to set.
	 */
	private void setBendpoint(Point bendpoint) {
		this.bendpoint = bendpoint;
	}

	public int getBendpointIndex() {
		return this.bendpointIndex;
	}

	public FreeFormConnection getConnection() {
		return this.connection;
	}

	/**
	 * Sets the bendpoint index.
	 * 
	 * @param bendpointIndex
	 *            the new bendpoint index
	 */
	public void setBendpointIndex(int bendpointIndex) {
		this.bendpointIndex = bendpointIndex;
	}

	/**
	 * Sets the connection.
	 * 
	 * @param freeFormConnection
	 *            the new connection
	 */
	public void setConnection(FreeFormConnection freeFormConnection) {
		this.connection = freeFormConnection;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		return ret + " bendpoint: " + getBendpoint() + " bendpointIndex: " + getBendpointIndex() + " connection: " + getConnection(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

}
