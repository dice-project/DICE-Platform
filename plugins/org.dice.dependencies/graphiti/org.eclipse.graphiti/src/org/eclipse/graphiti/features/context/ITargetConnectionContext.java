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
package org.eclipse.graphiti.features.context;

import org.eclipse.graphiti.mm.pictograms.Connection;

/**
 * The Interface ITargetConnectionContext.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface ITargetConnectionContext extends IContext {

	/**
	 * Gets the target connection.
	 * 
	 * @return the target connection where the new pictogram element (currently
	 *         this has to be a shape) has to be inserted
	 */
	Connection getTargetConnection();

}
