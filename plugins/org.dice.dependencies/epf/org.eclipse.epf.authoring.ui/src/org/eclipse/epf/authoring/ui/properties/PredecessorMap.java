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
package org.eclipse.epf.authoring.ui.properties;

import org.eclipse.epf.uma.WorkOrder;

/**
 * Map of predecessor ID and corresponding work order
 * @author Shilpa Toraskar
 * @since 1.0
 *
 */
public class PredecessorMap {
	private int id;

	private WorkOrder wo;

	/**
	 * 
	 * @param id
	 * 		Predecessor ID
	 * @param wo
	 * 		WorkOrder object
	 */
	public PredecessorMap(int id, WorkOrder wo) {
		this.id = id;
		this.wo = wo;
	}

	/**
	 * Get predecessor ID
	 * @return
	 * 			Predecessor ID
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Set Predecessor ID
	 * @param id
	 * 			Predecessor ID
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Get work order
	 * @return
	 * 		Work Order
	 */
	public WorkOrder getWorkOrder() {
		return this.wo;
	}

	/**
	 * Set Work Order
	 * @param wo
	 * 			Work Order
	 */
	public void setWorkOrder(WorkOrder wo) {
		this.wo = wo;
	}
}
