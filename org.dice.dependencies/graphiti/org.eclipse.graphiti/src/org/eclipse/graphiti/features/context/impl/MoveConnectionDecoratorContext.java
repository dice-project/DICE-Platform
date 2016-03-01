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

import org.eclipse.graphiti.features.context.IMoveConnectionDecoratorContext;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;

/**
 * The Class MoveConnectionDecoratorContext.
 */
public class MoveConnectionDecoratorContext extends LocationContext implements IMoveConnectionDecoratorContext {

	private ConnectionDecorator connectionDecorator;

	private boolean executeAllowed;

	/**
	 * Creates a new {@link MoveConnectionDecoratorContext}.
	 * 
	 * @param connectionDecorator
	 *            the connection decorator
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param executeAllowed
	 *            the execute allowed
	 */
	public MoveConnectionDecoratorContext(ConnectionDecorator connectionDecorator, int x, int y, boolean executeAllowed) {
		super(x, y);
		setConnectionDecorator(connectionDecorator);
		setExecuteAllowed(executeAllowed);
	}

	public ConnectionDecorator getConnectionDecorator() {
		return this.connectionDecorator;
	}

	/**
	 * Sets the connection decorator.
	 * 
	 * @param connectionDecorator
	 *            the new connection decorator
	 */
	protected void setConnectionDecorator(ConnectionDecorator connectionDecorator) {
		this.connectionDecorator = connectionDecorator;
	}

	public boolean isExecuteAllowed() {
		return this.executeAllowed;
	}

	/**
	 * Sets the execute allowed.
	 * 
	 * @param executeAllowed
	 *            the new execute allowed
	 */
	protected void setExecuteAllowed(boolean executeAllowed) {
		this.executeAllowed = executeAllowed;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		return ret + " connectionDecorator: " + getConnectionDecorator() + " executeAllowed: " + isExecuteAllowed(); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
