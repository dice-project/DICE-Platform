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
 * Created on 04.10.2005
 */
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.internal.Messages;

/**
 * The Class AbstractCreateConnectionFeature.
 */
public abstract class AbstractCreateConnectionFeature extends AbstractFeature implements ICreateConnectionFeature {

	private String createDescription;

	private String createName;

	/**
	 * Creates a new {@link AbstractAddShapeFeature}.
	 * 
	 * @param fp
	 *            the fp
	 * @param name
	 *            the name
	 * @param description
	 *            the description
	 */
	public AbstractCreateConnectionFeature(IFeatureProvider fp, String name, String description) {
		super(fp);
		setCreateName(name);
		setCreateDescription(description);
	}

	/**
	 * Gets the create description.
	 * 
	 * @return Returns the description.
	 */
	public String getCreateDescription() {
		return this.createDescription;
	}

	/**
	 * Gets the create name.
	 * 
	 * @return Returns the name.
	 */
	public String getCreateName() {
		return this.createName;
	}

	/**
	 * @param description
	 *            The description to set.
	 */
	private void setCreateDescription(String description) {
		this.createDescription = description;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	private void setCreateName(String name) {
		this.createName = name;
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof ICreateConnectionContext) {
			ret = canCreate((ICreateConnectionContext) context);
		}
		return ret;
	}

	public void execute(IContext context) {
		if (context instanceof ICreateConnectionContext) {
			create((ICreateConnectionContext) context);
		}
	}

	public String getCreateImageId() {
		return null;
	}

	public String getCreateLargeImageId() {
		return getCreateImageId();
	}

	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.AbstractCreateConnectionFeature_0_xfld;
}