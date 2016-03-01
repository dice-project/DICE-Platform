//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.model;

import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.Suppression;

/**
 * Base diagram interface
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 * @model
 */
public interface Diagram extends NodeContainer {

	/**
	 * Gets the UMA model object for this diagram
	 * 
	 * @return
	 */
	org.eclipse.epf.uma.Diagram getUMADiagram();

	/**
	 * Sets default name for new node
	 * 
	 * @param newNode
	 */
	void setDefaultName(NamedNode newNode);

	/**
	 * Sets a suppression instance for this diagram
	 * 
	 * @param suppression
	 */
	void setSuppression(Suppression suppression);

	/**
	 * Gets the suppression instance used in this diagram
	 * 
	 * @return
	 */
	Suppression getSuppression();

	/**
	 * Sets filter used to to filter diagram elements in this diagram
	 * 
	 * @param filter
	 */
	void setFilter(IFilter filter);

	/**
	 * Checks if this diagram is newly created and not saved yet)
	 * 
	 * @return
	 */
	boolean isNew();

	/**
	 * Sets or unsets the isNew flag of this diagram
	 * 
	 * @param n
	 * @see #isNew()
	 */
	void setNew(boolean n);
}