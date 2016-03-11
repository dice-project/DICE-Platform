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
package org.eclipse.graphiti.palette;

/**
 * The Interface ICreationToolEntry.
 */
public interface ICreationToolEntry extends IToolEntry {

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription();

	/**
	 * Gets the large icon id.
	 * 
	 * @return the large icon id
	 */
	String getLargeIconId();
}
