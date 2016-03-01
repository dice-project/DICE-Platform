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
 * Created on 12.12.2005
 */
package org.eclipse.graphiti.features.custom;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.ICustomContext;

/**
 * The Interface ICustomFeature.
 * 
 * @noimplement This interface is not intended to be implemented by clients, use
 *              {@link AbstractCustomFeature} instead
 */
public interface ICustomFeature extends IFeature {

	/**
	 * Gets the image id.
	 * 
	 * @return the image id
	 */
	String getImageId();

	/**
	 * Can execute.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canExecute(ICustomContext context);

	/**
	 * Execute.
	 * 
	 * @param context
	 *            the context
	 */
	void execute(ICustomContext context);
}