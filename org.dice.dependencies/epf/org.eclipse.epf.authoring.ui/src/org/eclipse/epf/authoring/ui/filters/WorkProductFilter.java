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
package org.eclipse.epf.authoring.ui.filters;

import org.eclipse.epf.uma.WorkProduct;

/**
 * Filters {@link WorkProduct} based on search pattern
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class WorkProductFilter extends ContentFilter {

	protected boolean childAccept(Object obj) {
		if (obj instanceof WorkProduct) {
			return true;
		}
		return false;
	}

}
