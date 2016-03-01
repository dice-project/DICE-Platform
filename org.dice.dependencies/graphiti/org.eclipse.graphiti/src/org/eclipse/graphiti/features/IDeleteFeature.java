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

import org.eclipse.graphiti.features.context.IDeleteContext;

/**
 * The Interface IDeleteFeature.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link DefaultDeleteFeature} instead.
 */
public interface IDeleteFeature extends IFeature {

	/**
	 * Delete.
	 * 
	 * @param context
	 *            the context
	 */
	void delete(IDeleteContext context);

	/**
	 * Can delete.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canDelete(IDeleteContext context);

	/**
	 * Pre delete.
	 * 
	 * @param context
	 *            the context
	 */
	void preDelete(IDeleteContext context);

	/**
	 * Post delete.
	 * 
	 * @param context
	 *            the context
	 */
	void postDelete(IDeleteContext context);
}