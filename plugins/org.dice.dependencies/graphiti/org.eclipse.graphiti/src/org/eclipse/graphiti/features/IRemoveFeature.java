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

import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;

/**
 * This feature is for removing pictogram elements from a diagram. Remove
 * features must not modify any business content.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link DefaultRemoveFeature} instead.
 */
public interface IRemoveFeature extends IFeature {

	/**
	 * Remove the given object.
	 * 
	 * @param context
	 *            contains object to remove
	 */
	void remove(IRemoveContext context);

	/**
	 * Checks if given object could be removed.
	 * 
	 * @param context
	 *            contains object to remove
	 * 
	 * @return true if remove is possible
	 */
	boolean canRemove(IRemoveContext context);

	/**
	 * called prior to remove call.
	 * 
	 * @param context
	 *            the context
	 */
	void preRemove(IRemoveContext context);

	/**
	 * called after remove call.
	 * 
	 * @param context
	 *            the context
	 */
	void postRemove(IRemoveContext context);
}