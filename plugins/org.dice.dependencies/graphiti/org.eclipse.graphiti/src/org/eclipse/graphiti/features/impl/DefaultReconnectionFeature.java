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
 * Created on 13.12.2005
 */
package org.eclipse.graphiti.features.impl;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.internal.Messages;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;

/**
 * The Class DefaultReconnectionFeature.
 */
public class DefaultReconnectionFeature extends AbstractFeature implements IReconnectionFeature {

	/**
	 * Creates a new {@link DefaultReconnectionFeature}.
	 * 
	 * @param fp
	 *            the feature provider
	 */
	public DefaultReconnectionFeature(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canReconnect(IReconnectionContext context) {
		Connection connection = context.getConnection();
		Anchor newAnchor = getNewAnchor(context);
		boolean ret = (connection != null) && (newAnchor != null) && (connection.getStart() != null) && (connection.getEnd() != null)
				&& !(newAnchor.getParent() instanceof Diagram);
		return ret;
	}

	/**
	 * Gets the new anchor.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return the new anchor
	 */
	protected Anchor getNewAnchor(IReconnectionContext context) {
		return context.getNewAnchor();
	}

	public final void reconnect(IReconnectionContext context) {
		if (!getUserDecision()) {
			return;
		}
		Connection connection = context.getConnection();
		Anchor newAnchor = context.getNewAnchor();
		Anchor oldAnchor = context.getOldAnchor();

		preReconnect(context);

		if (connection.getStart().equals(oldAnchor)) {
			connection.setStart(newAnchor);
		} else {
			connection.setEnd(newAnchor);
		}

		postReconnect(context);
	}

	public void preReconnect(IReconnectionContext context) {
	}

	public void postReconnect(IReconnectionContext context) {
	}

	public boolean canExecute(IContext context) {
		boolean ret = false;
		if (context instanceof IReconnectionContext) {
			ret = canReconnect((IReconnectionContext) context);
		}
		return ret;
	}

	public void execute(IContext context) {
		if (context instanceof IReconnectionContext) {
			reconnect((IReconnectionContext) context);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}

	private static final String NAME = Messages.DefaultReconnectionFeature_0_xfld;
}