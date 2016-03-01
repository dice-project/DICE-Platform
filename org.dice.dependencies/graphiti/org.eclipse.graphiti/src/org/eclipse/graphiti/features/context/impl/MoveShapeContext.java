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

import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

/**
 * The Class MoveShapeContext.
 */
public class MoveShapeContext extends MoveContext implements IMoveShapeContext {

	private int deltaX;

	private int deltaY;

	private Shape shape;

	private ContainerShape sourceContainer;

	private ContainerShape targetContainer;

	private Connection targetConnection;

	/**
	 * Creates a new {@link MoveShapeContext}.
	 * 
	 * @param shape
	 *            the shape
	 */
	public MoveShapeContext(Shape shape) {
		super();
		setShape(shape);
	}

	public int getDeltaX() {
		return this.deltaX;
	}

	public int getDeltaY() {
		return this.deltaY;
	}

	public PictogramElement getPictogramElement() {
		return getShape();
	}

	/**
	 * Gets the shape.
	 * 
	 * @return Returns the shape.
	 */
	public Shape getShape() {
		return this.shape;
	}

	public ContainerShape getSourceContainer() {
		return this.sourceContainer;
	}

	public Connection getTargetConnection() {
		return this.targetConnection;
	}

	public ContainerShape getTargetContainer() {
		return this.targetContainer;
	}

	/**
	 * Sets the delta x.
	 * 
	 * @param deltaX
	 *            the new delta x
	 */
	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	/**
	 * Sets the delta y.
	 * 
	 * @param deltaY
	 *            the new delta y
	 */
	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

	/**
	 * @param shape
	 *            The shape to set.
	 */
	private void setShape(Shape shape) {
		this.shape = shape;
	}

	/**
	 * Sets the source container.
	 * 
	 * @param sourceContainer
	 *            The sourceContainer to set.
	 */
	public void setSourceContainer(ContainerShape sourceContainer) {
		this.sourceContainer = sourceContainer;
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
		ret = ret + "(deltaX=" + getDeltaX() + ", deltaY=" + getDeltaY() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return ret;
	}
}
