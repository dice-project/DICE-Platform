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

import org.eclipse.graphiti.features.context.IAddBendpointContext;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;

/**
 * The Class AddBendpointContext.
 */
public class AddBendpointContext extends LocationContext implements IAddBendpointContext {

	private FreeFormConnection freeFormConnection;

	private int bendpointIndex;

	/**
	 * Creates a new {@link AddBendpointContext}.
	 * 
	 * @param connection
	 *            the connection
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param index
	 *            the index
	 */
	public AddBendpointContext(FreeFormConnection connection, int x, int y, int index) {
		super(x, y);
		setConnection(connection);
		setBendpointIndex(index);
	}

	public FreeFormConnection getConnection() {
		return this.freeFormConnection;
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

	/**
	 * Sets the bendpoint index.
	 * 
	 * @param index
	 *            the new bendpoint index
	 */
	public void setBendpointIndex(int index) {
		this.bendpointIndex = index;
	}

	public Point getBendpoint() {
		return null;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		return ret + " freeFormConnection: " + getConnection() + " bendpointIndex: " + getBendpointIndex(); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
