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
 * Created on 12.07.2005
 */
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class AbstractAddFeature.
 */
public abstract class AbstractAddFeature extends AbstractFeature implements IAddFeature {

	/**
	 * The Constant EMPTY.
	 */
	protected static final PictogramElement[] EMPTY = new PictogramElement[0];

	/**
	 * Creates a new {@link AbstractAddFeature}.
	 * 
	 * @param fp
	 *            the fp
	 */
	public AbstractAddFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canExecute(IContext context) {
		final String SIGNATURE = "canExecute(IContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractAddFeature.class, SIGNATURE, new Object[] { context });
		}
		boolean ret = false;
		if (context instanceof IAddContext) {
			ret = canAdd((IAddContext) context);
		}
		if (info) {
			T.racer().exiting(AbstractAddFeature.class, SIGNATURE, ret);
		}
		return ret;
	}

	public void execute(IContext context) {
		final String SIGNATURE = "execute(IContext)"; //$NON-NLS-1$
		boolean info = T.racer().info();
		if (info) {
			T.racer().entering(AbstractAddFeature.class, SIGNATURE, new Object[] { context });
		}
		if (context instanceof IAddContext) {
			add((IAddContext) context);
		}
		if (info) {
			T.racer().exiting(AbstractAddFeature.class, SIGNATURE);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.AbstractAddFeature_0_xfld;
}