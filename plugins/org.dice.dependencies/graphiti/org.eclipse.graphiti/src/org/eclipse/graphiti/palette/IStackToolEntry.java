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

import java.util.List;

/**
 * The Interface IStackToolEntry.
 */
public interface IStackToolEntry extends IToolEntry {

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription();

	/**
	 * Gets the creation tool entries.
	 * 
	 * @return the creation tool entries
	 */
	public List<ICreationToolEntry> getCreationToolEntries();
}
