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
 * Created on 17.11.2005
 */
package org.eclipse.graphiti.features.context.impl;

import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IAreaContext;
import org.eclipse.graphiti.features.context.ITargetConnectionContext;
import org.eclipse.graphiti.features.context.ITargetContext;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

/**
 * The Class AddContext.
 */
public class AddContext extends AreaContext implements IAddContext {

	private ContainerShape targetContainer;

	private Connection targetConnection;

	private Object newObject;

	/**
	 * Creates a new {@link AddContext}.
	 */
	public AddContext() {
		super();
	}

	/**
	 * Creates a new {@link AddContext}.
	 * 
	 * @param context
	 *            the context
	 * @param newObject
	 *            the new object
	 */
	public AddContext(IAreaContext context, Object newObject) {
		this();
		final String SIGNATURE = "AddContext(IAreaContext, Object)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AddContext.class, SIGNATURE, new Object[] { context, newObject });
		}
		setNewObject(newObject);
		setLocation(context.getX(), context.getY());
		setSize(context.getWidth(), context.getHeight());
		if (context instanceof ITargetContext) {
			ITargetContext targetContext = (ITargetContext) context;
			setTargetContainer(targetContext.getTargetContainer());
		}
		if (context instanceof ITargetConnectionContext) {
			ITargetConnectionContext targetConnectionContext = (ITargetConnectionContext) context;
			setTargetConnection(targetConnectionContext.getTargetConnection());
		}
		if (info) {
			T.racer().exiting(AddContext.class, SIGNATURE);
		}
	}

	public Object getNewObject() {
		return this.newObject;
	}

	public Connection getTargetConnection() {
		return this.targetConnection;
	}

	public ContainerShape getTargetContainer() {
		return this.targetContainer;
	}

	/**
	 * Sets the new object.
	 * 
	 * @param newObject
	 *            the new object
	 */
	public void setNewObject(Object newObject) {
		this.newObject = newObject;
	}

	/**
	 * Sets the target container.
	 * 
	 * @param targetContainer
	 *            The target container to set.
	 */
	public void setTargetContainer(ContainerShape targetContainer) {
		this.targetContainer = targetContainer;
	}

	/**
	 * Sets the target connection.
	 * 
	 * @param targetConnection
	 *            The target connection to set.
	 */
	public void setTargetConnection(Connection targetConnection) {
		this.targetConnection = targetConnection;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		ret = ret + " newObject: " + getNewObject() + " targetConnection: " + getTargetConnection() + " targetContainer: " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ getTargetContainer();
		return ret;
	}

}
