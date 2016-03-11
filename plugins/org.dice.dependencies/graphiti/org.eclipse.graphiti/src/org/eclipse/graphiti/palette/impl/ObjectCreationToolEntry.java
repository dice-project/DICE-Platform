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
package org.eclipse.graphiti.palette.impl;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.palette.IObjectCreationToolEntry;

/**
 * The Class ObjectCreationToolEntry creates an palette tool which creates an
 * object.
 */
public class ObjectCreationToolEntry extends AbstractPaletteToolEntry implements IObjectCreationToolEntry {

	private ICreateFeature createFeature;

	/**
	 * Creates a new {@link ObjectCreationToolEntry}.
	 * 
	 * @param label
	 *            the label
	 * @param description
	 *            the description
	 * @param iconId
	 *            the icon id
	 * @param largeIconId
	 *            the large icon id
	 * @param createFeature
	 *            the feature which should be called on creation
	 */
	public ObjectCreationToolEntry(String label, String description, String iconId, String largeIconId, ICreateFeature createFeature) {
		super(label, description, iconId, largeIconId);
		this.createFeature = createFeature;
	}

	/**
	 * Gets the create feature.
	 * 
	 * @return the associated creation tool
	 */
	public ICreateFeature getCreateFeature() {
		return this.createFeature;
	}

}
