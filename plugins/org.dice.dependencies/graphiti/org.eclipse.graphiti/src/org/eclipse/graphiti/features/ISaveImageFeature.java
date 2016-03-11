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

import org.eclipse.graphiti.features.context.ISaveImageContext;
import org.eclipse.graphiti.features.impl.DefaultSaveImageFeature;

/**
 * The Interface ISaveImageFeature.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link DefaultSaveImageFeature} instead.
 */
public interface ISaveImageFeature extends IFeature {

	/**
	 * Can save.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canSave(ISaveImageContext context);

	/**
	 * Pre save.
	 * 
	 * @param context
	 *            the context
	 */
	void preSave(ISaveImageContext context);

	/**
	 * Post save.
	 * 
	 * @param context
	 *            the context
	 */
	void postSave(ISaveImageContext context);
}
