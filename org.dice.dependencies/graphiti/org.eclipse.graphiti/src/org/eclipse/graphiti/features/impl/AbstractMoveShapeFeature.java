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

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.internal.Messages;

/**
 * The Class AbstractMoveShapeFeature.
 */
public abstract class AbstractMoveShapeFeature extends AbstractFeature implements IMoveShapeFeature {

	/**
	 * Creates a new {@link AbstractMoveShapeFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public AbstractMoveShapeFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IMoveShapeContext) {
			ret = canMoveShape((IMoveShapeContext) context);
		}
		return ret;
	}

	public void execute(IContext context) {
		if (context instanceof IMoveShapeContext) {
			moveShape((IMoveShapeContext) context);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.AbstractMoveShapeFeature_0_xfld;
}
