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

/**
 * The Interface IAddContext.
 * 
 * This context contains the information, needed to let a feature add a
 * pictogram element, which has to be linked to any domain model element, to a
 * container shape.
 * 
 * @see org.eclipse.graphiti.mm.pictograms.PictogramElement,
 *      org.eclipse.graphiti.mm.pictograms.ContainerShape
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IAddContext extends IAreaContext, ITargetContext, ITargetConnectionContext {

	/**
	 * A pictogram element has to be added. This pictogram element has to link
	 * to a domain model element.
	 * 
	 * @return instance of a domain model element
	 */
	Object getNewObject();

}