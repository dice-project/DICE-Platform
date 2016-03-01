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

import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.internal.features.context.impl.base.DefaultContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class CreateConnectionContext.
 */
public class CreateConnectionContext extends DefaultContext implements ICreateConnectionContext {

	private Anchor sourceAnchor;

	private Anchor targetAnchor;

	private PictogramElement sourcePictogramElement;

	private PictogramElement targetPictogramElement;

	/**
	 * Creates a new {@link CreateConnectionContext}.
	 */
	public CreateConnectionContext() {
		super();
	}

	public Anchor getSourceAnchor() {
		return this.sourceAnchor;
	}

	public Anchor getTargetAnchor() {
		return this.targetAnchor;
	}

	/**
	 * Sets the source anchor.
	 * 
	 * @param sourceAnchor
	 *            The sourceAnchor to set.
	 */
	public void setSourceAnchor(Anchor sourceAnchor) {
		this.sourceAnchor = sourceAnchor;
	}

	/**
	 * Sets the target anchor.
	 * 
	 * @param targetAnchor
	 *            The targetAnchor to set.
	 */
	public void setTargetAnchor(Anchor targetAnchor) {
		this.targetAnchor = targetAnchor;
	}

	public PictogramElement getSourcePictogramElement() {
		return this.sourcePictogramElement;
	}

	public PictogramElement getTargetPictogramElement() {
		return this.targetPictogramElement;
	}

	/**
	 * Sets the source pictogram element.
	 * 
	 * @param sourcePictogramElement
	 *            the new source pictogram element
	 */
	public void setSourcePictogramElement(PictogramElement sourcePictogramElement) {
		this.sourcePictogramElement = sourcePictogramElement;
	}

	/**
	 * Sets the target pictogram element.
	 * 
	 * @param targetPictogramElement
	 *            the new target pictogram element
	 */
	public void setTargetPictogramElement(PictogramElement targetPictogramElement) {
		this.targetPictogramElement = targetPictogramElement;
	}

	@Override
	public String toString() {
		String ret = super.toString();
		return ret + " sourceAnchor: " + getSourceAnchor() + " targetAnchor: " + getTargetAnchor() + " sourcePictogramElement: " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ getSourcePictogramElement();
	}

}
