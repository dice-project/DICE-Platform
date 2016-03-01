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

import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

/**
 * The Class CreateContext.
 */
public class CreateContext extends AreaContext implements ICreateContext {

	private ContainerShape targetContainer;

	private Connection targetConnection;

	/**
	 * Creates a new {@link CreateContext}.
	 */
	public CreateContext() {
		super();
	}

	public Connection getTargetConnection() {
		return this.targetConnection;
	}

	public ContainerShape getTargetContainer() {
		return this.targetContainer;
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

	/**
	 * Sets the target container.
	 * 
	 * @param targetContainer
	 *            The targetContainer to set.
	 */
	public void setTargetContainer(ContainerShape targetContainer) {
		this.targetContainer = targetContainer;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		return ret + " targetConnection: " + getTargetConnection() + " targetContainer: " + getTargetContainer(); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
