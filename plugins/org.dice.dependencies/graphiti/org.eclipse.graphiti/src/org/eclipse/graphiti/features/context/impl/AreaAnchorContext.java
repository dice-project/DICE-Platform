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

import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;

/**
 * The Class AreaAnchorContext.
 */
public class AreaAnchorContext extends AreaContext implements IMoveAnchorContext {

	private Anchor anchor;

	private AnchorContainer sourceContainer;

	private AnchorContainer targetContainer;

	/**
	 * Creates a new {@link AreaAnchorContext}.
	 * 
	 * @param anchor
	 *            the anchor
	 */
	public AreaAnchorContext(Anchor anchor) {
		super();
		setAnchor(anchor);
	}

	/**
	 * Gets the anchor.
	 * 
	 * @return Returns the anchor.
	 */
	public Anchor getAnchor() {
		return this.anchor;
	}

	public AnchorContainer getSourceContainer() {
		return this.sourceContainer;
	}

	public AnchorContainer getTargetContainer() {
		return this.targetContainer;
	}

	/**
	 * Sets the source container.
	 * 
	 * @param sourceContainer
	 *            The sourceContainer to set.
	 */
	public void setSourceContainer(AnchorContainer sourceContainer) {
		this.sourceContainer = sourceContainer;
	}

	/**
	 * Sets the target container.
	 * 
	 * @param targetContainer
	 *            The targetContainer to set.
	 */
	public void setTargetContainer(AnchorContainer targetContainer) {
		this.targetContainer = targetContainer;
	}

	/**
	 * @param anchor
	 *            The anchor to set.
	 */
	private void setAnchor(Anchor anchor) {
		this.anchor = anchor;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		return ret + " anchor: " + getAnchor() + " sourceContainer: " + getSourceContainer() + " targetContainer: " + getTargetContainer(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

}
