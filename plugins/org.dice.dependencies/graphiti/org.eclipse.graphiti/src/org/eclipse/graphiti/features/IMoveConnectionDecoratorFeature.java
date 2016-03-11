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
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.features.context.IMoveConnectionDecoratorContext;
import org.eclipse.graphiti.features.impl.DefaultMoveConnectionDecoratorFeature;

/**
 * The Interface IMoveConnectionDecoratorFeature.
 * 
 * Moving shapes means to change their coordinates or/and their parent.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link DefaultMoveConnectionDecoratorFeature} instead.
 */
public interface IMoveConnectionDecoratorFeature extends IMoveFeature {

	/**
	 * Can move connection decorator.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canMoveConnectionDecorator(IMoveConnectionDecoratorContext context);

	/**
	 * Move connection decorator.
	 * 
	 * @param context
	 *            the context
	 */
	void moveConnectionDecorator(IMoveConnectionDecoratorContext context);
}