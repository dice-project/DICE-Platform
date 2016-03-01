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
package org.eclipse.epf.library.edit;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.uma.VariabilityElement;

/**
 * A helper class for storing variability information.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class VariabilityInfo {

	private VariabilityElement owner;

	private List inheritanceList;

	private ArrayList contributors;

	/**
	 * Creates a new instance.
	 * 
	 * @param owner
	 *            a variability element
	 */
	public VariabilityInfo(VariabilityElement owner) {
		this.owner = owner;
	}

	/**
	 * Gets the owner of this VariabilityInfo object.
	 * 
	 * @return the owner of this VariabilityInfo object.
	 */
	public final VariabilityElement getOwner() {
		return owner;
	}

	/**
	 * Gets the realized inheritance list in the order from the resolved object
	 * of the owner to the its top base.
	 * 
	 * @return
	 */
	public List getInheritanceList() {
		if (inheritanceList == null) {
			inheritanceList = new ArrayList();
		}
		return inheritanceList;
	}

	/**
	 * Gets the list of all contributor to
	 * 
	 * @return
	 */
	public List getContributors() {
		if (contributors == null) {
			contributors = new ArrayList();
		}
		return contributors;
	}

}
