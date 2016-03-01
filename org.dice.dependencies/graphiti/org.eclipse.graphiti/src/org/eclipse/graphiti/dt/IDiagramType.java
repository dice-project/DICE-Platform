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
package org.eclipse.graphiti.dt;

import org.eclipse.graphiti.platform.IExtension;

/**
 * The Interface IDiagramType.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IDiagramType extends IExtension {

	/**
	 * Gets the id.
	 * 
	 * @return the id of the new diagram type
	 */
	String getId();

	/**
	 * Gets the name.
	 * 
	 * @return the name of the new diagram type
	 */
	String getName();

	/**
	 * Gets the description.
	 * 
	 * @return the description of the new diagram type
	 */
	String getDescription();
}
