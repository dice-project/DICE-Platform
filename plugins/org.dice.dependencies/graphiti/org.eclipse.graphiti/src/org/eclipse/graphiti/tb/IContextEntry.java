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
package org.eclipse.graphiti.tb;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;

/**
 * The Interface IContextEntry.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IContextEntry {

	/**
	 * Can execute.
	 * 
	 * @return true, if successful
	 */
	boolean canExecute();

	/**
	 * Execute.
	 */
	void execute();

	/**
	 * Gets the context.
	 * 
	 * @return the context
	 */
	IContext getContext();

	/**
	 * Gets the feature.
	 * 
	 * @return the feature
	 */
	IFeature getFeature();

	/**
	 * Gets the icon id.
	 * 
	 * @return the icon id
	 */
	String getIconId();

	/**
	 * Sets the description.
	 * 
	 * @param text
	 *            the new description
	 */
	void setDescription(String text);

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	String getDescription();

	/**
	 * Sets the text.
	 * 
	 * @param text
	 *            the new text
	 */
	void setText(String text);

	/**
	 * Gets the text.
	 * 
	 * @return the text
	 */
	String getText();

	/**
	 * Sets the icon id.
	 * 
	 * @param iconId
	 *            the new icon id
	 */
	void setIconId(String iconId);

}
