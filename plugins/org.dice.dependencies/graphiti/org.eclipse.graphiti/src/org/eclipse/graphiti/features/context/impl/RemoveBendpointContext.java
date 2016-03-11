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
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.IRemoveBendpointContext;
import org.eclipse.graphiti.internal.features.context.impl.base.DefaultContext;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;

/**
 * The Class RemoveBendpointContext.
 */
public class RemoveBendpointContext extends DefaultContext implements IRemoveBendpointContext {

	private FreeFormConnection freeFormConnection;

	private Point bendpoint;

	private int bendpointIndex;

	/**
	 * Creates a new {@link RemoveBendpointContext}.
	 * 
	 * @param freeFormConnection
	 *            the free form connection
	 * @param bendpoint
	 *            the bendpoint
	 */
	public RemoveBendpointContext(FreeFormConnection freeFormConnection, Point bendpoint) {
		super();
		setConnection(freeFormConnection);
		setBendpoint(bendpoint);
	}

	public Point getBendpoint() {
		return this.bendpoint;
	}

	/**
	 * Sets the bendpoint.
	 * 
	 * @param bendpoint
	 *            the new bendpoint
	 */
	public void setBendpoint(Point bendpoint) {
		this.bendpoint = bendpoint;
	}

	/**
	 * Sets the connection.
	 * 
	 * @param freeFormConnection
	 *            the new connection
	 */
	public void setConnection(FreeFormConnection freeFormConnection) {
		this.freeFormConnection = freeFormConnection;
	}

	public int getBendpointIndex() {
		return this.bendpointIndex;
	}

	public FreeFormConnection getConnection() {
		return this.freeFormConnection;
	}

	/**
	 * Sets the bendpoint index.
	 * 
	 * @param index
	 *            the new bendpoint index
	 */
	public void setBendpointIndex(int index) {
		this.bendpointIndex = index;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		return ret + " bendpoint: " + getBendpoint() + " bendpointIndex: " + getBendpointIndex() + " freeFormConnection: " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ getConnection();
	}

}
