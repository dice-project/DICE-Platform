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
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class DefaultDirectEditingInfo.
 */
public class DefaultDirectEditingInfo implements IDirectEditingInfo {

	private PictogramElement mainPictogramElement;

	private PictogramElement pictogramElement;

	private GraphicsAlgorithm graphicsAlgorithm;

	private boolean isActive = false;

	public GraphicsAlgorithm getGraphicsAlgorithm() {
		return this.graphicsAlgorithm;
	}

	public PictogramElement getMainPictogramElement() {
		return this.mainPictogramElement;
	}

	public PictogramElement getPictogramElement() {
		return this.pictogramElement;
	}

	public boolean isActive() {
		return this.isActive;
	}

	public void reset() {
		setMainPictogramElement(null);
		setPictogramElement(null);
		setGraphicsAlgorithm(null);
		setActive(false);
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setGraphicsAlgorithm(GraphicsAlgorithm graphicsAlgorithm) {
		this.graphicsAlgorithm = graphicsAlgorithm;
	}

	public void setMainPictogramElement(PictogramElement pictogramElement) {
		this.mainPictogramElement = pictogramElement;
	}

	public void setPictogramElement(PictogramElement pictogramElement) {
		this.pictogramElement = pictogramElement;
	}
}
