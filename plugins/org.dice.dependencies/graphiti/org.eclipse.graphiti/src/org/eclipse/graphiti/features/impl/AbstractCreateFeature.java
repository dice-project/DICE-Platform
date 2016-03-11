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
 * Created on 06.07.2005
 */
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.internal.util.T;

/**
 * The Class AbstractCreateFeature.
 */
public abstract class AbstractCreateFeature extends AbstractFeature implements ICreateFeature {

	private String description;

	private String name;

	/**
	 * Creates a new {@link AbstractCreateFeature}.
	 * 
	 * @param fp
	 *            feature provider
	 * @param name
	 *            the name
	 * @param description
	 *            the description
	 */
	public AbstractCreateFeature(IFeatureProvider fp, String name, String description) {
		super(fp);
		setName(name);
		setDescription(description);
	}

	public String getCreateDescription() {
		return description;
	}

	public String getCreateName() {
		return name;
	}

	/**
	 * @param description
	 *            The description to set.
	 */
	private void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	private void setName(String name) {
		this.name = name;
	}

	public boolean canExecute(IContext context) {
		final String SIGNATURE = "canExecute(IContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractCreateFeature.class, SIGNATURE, new Object[] { context });
		}
		boolean ret = false;
		if (context instanceof ICreateContext) {
			ret = canCreate((ICreateContext) context);
		}

		if (info) {
			T.racer().exiting(AbstractCreateFeature.class, SIGNATURE, ret);
		}
		return ret;
	}

	public void execute(IContext context) {
		final String SIGNATURE = "execute(IContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractCreateFeature.class, SIGNATURE, new Object[] { context });
		}
		if (context instanceof ICreateContext) {
			create((ICreateContext) context);
		}
		if (info) {
			T.racer().exiting(AbstractCreateFeature.class, SIGNATURE);
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
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}
}