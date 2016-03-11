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
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.impl.DefaultMoveAnchorFeature;

/**
 * The Interface IMoveAnchorFeature.
 * 
 * Layouting shapes means to change their ccordinates or/and their parent.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link DefaultMoveAnchorFeature} instead.
 */
public interface IMoveAnchorFeature extends IMoveFeature {

	/**
	 * Can move anchor.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canMoveAnchor(IMoveAnchorContext context);

	/**
	 * Move anchor.
	 * 
	 * @param context
	 *            the context
	 */
	void moveAnchor(IMoveAnchorContext context);

	/**
	 * Pre move anchor.
	 * 
	 * @param context
	 *            the context
	 */
	void preMoveAnchor(IMoveAnchorContext context);

	/**
	 * Post move anchor.
	 * 
	 * @param context
	 *            the context
	 */
	void postMoveAnchor(IMoveAnchorContext context);
}