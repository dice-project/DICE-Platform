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
import org.eclipse.graphiti.features.IMoveConnectionDecoratorFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IMoveConnectionDecoratorContext;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;

/**
 * The Class DefaultMoveConnectionDecoratorFeature.
 */
public class DefaultMoveConnectionDecoratorFeature extends AbstractFeature implements IMoveConnectionDecoratorFeature {

	/**
	 * Creates a new {@link DefaultMoveConnectionDecoratorFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public DefaultMoveConnectionDecoratorFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canMoveConnectionDecorator(IMoveConnectionDecoratorContext context) {
		return true;
	}

	public void moveConnectionDecorator(IMoveConnectionDecoratorContext context) {
		if (!getUserDecision()) {
			return;
		}
		int posX = context.getX();
		int posY = context.getY();

		ConnectionDecorator connectionDecorator = context.getConnectionDecorator();
		GraphicsAlgorithm ga = connectionDecorator.getGraphicsAlgorithm();
		ga.setX(posX);
		ga.setY(posY);
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IMoveConnectionDecoratorContext) {
			ret = canMoveConnectionDecorator((IMoveConnectionDecoratorContext) context);
		}
		return ret;
	}

	public void execute(IContext context) {
		if (context instanceof IMoveConnectionDecoratorContext) {
			IMoveConnectionDecoratorContext moveConnectionDecoratorContext = (IMoveConnectionDecoratorContext) context;
			if (moveConnectionDecoratorContext.isExecuteAllowed()) {
				moveConnectionDecorator(moveConnectionDecoratorContext);
			}
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.DefaultMoveConnectionDecoratorFeature_0_xfld;
}