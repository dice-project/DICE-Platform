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
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.features.context.IConnectionContext;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.pattern.mapping.IStructureMapping;
import org.eclipse.graphiti.pattern.mapping.data.IDataMapping;
import org.eclipse.graphiti.pattern.mapping.data.ITextDataMapping;

/**
 * The Class AbstractConnectionPattern.
 */
public abstract class AbstractConnectionPattern extends AbstractBasePattern implements IConnectionPattern {

	/**
	 * Creates a new {@link AbstractConnectionPattern}.
	 */
	public AbstractConnectionPattern() {
	}

	/**
	 * Gets the adds the connection context.
	 * 
	 * @param context
	 *            the create connection context
	 * 
	 * @return the adds the connection context
	 */
	protected static AddConnectionContext getAddConnectionContext(ICreateConnectionContext context) {
		AddConnectionContext result = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		return result;
	}

	public boolean canCreate(ICreateConnectionContext context) {
		return false;
	}

	public boolean canStartConnection(ICreateConnectionContext context) {
		return false;
	}

	public Connection create(ICreateConnectionContext context) {
		return null;
	}

	/**
	 * Adds the graphical representation.
	 * 
	 * @param context
	 *            the connection context
	 * @param newObject
	 *            the new object
	 * 
	 * @return the connection
	 */
	protected Connection addGraphicalRepresentation(IConnectionContext context, Object newObject) {
		AddConnectionContext newContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		newContext.setNewObject(newObject);
		return (Connection) getFeatureProvider().addIfPossible(newContext);
	}

	/**
	 * Gets the text.
	 * 
	 * @param structureMapping
	 *            the structure mapping
	 * @param link
	 *            the pictogram link
	 * 
	 * @return the text
	 */
	protected String getText(IStructureMapping structureMapping, PictogramLink link) {
		String ret = null;
		IDataMapping dm = structureMapping.getDataMapping();
		if (dm instanceof ITextDataMapping) {
			ret = ((ITextDataMapping) dm).getText(link);
		}
		return ret;
	}

	/**
	 * Layout pictogram element.
	 * 
	 * @param pe
	 *            the pictogram element
	 */
	protected void layoutPictogramElement(PictogramElement pe) {
		LayoutContext context = new LayoutContext(pe);
		getFeatureProvider().layoutIfPossible(context);
	}

	/**
	 * Update pictogram element.
	 * 
	 * @param pe
	 *            the pictogram element
	 */
	protected void updatePictogramElement(PictogramElement pe) {
		UpdateContext context = new UpdateContext(pe);
		getFeatureProvider().updateIfPossible(context);
		layoutPictogramElement(pe);
	}

	public String getCreateDescription() {
		return null;
	}

	public String getCreateImageId() {
		return null;
	}

	public String getCreateLargeImageId() {
		return getCreateImageId();
	}

	public String getCreateName() {
		return null;
	}
}
