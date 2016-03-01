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
package org.eclipse.epf.library.edit.util.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * Collection of order information
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 * @model
 */
public interface OrderInfoCollection extends EObject {

	/**
	 * @return
	 * @model type="com.ibm.library.edit.util.model.OrderInfo"
	 *        containement="true"
	 */
	EList getOrderInfos();

}
