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

import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.jface.viewers.TableViewer;


/**
 * Filters {@link WorkProduct}s based on {@link MethodConfiguration} and search pattern.
 * and {@link WorkProductType}  
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ProcessSpecificWorkProductFilter extends ProcessWorkProductFilter {

	Class workProductType = null;

	public ProcessSpecificWorkProductFilter(MethodConfiguration config,
			TableViewer viewer, String tabName, Class type) {
		super(config, viewer, tabName);
		this.workProductType = type;
	}

	public boolean childAccept(Object obj) {
		if (super.childAccept(obj)) {
			if (obj instanceof WorkProduct) {
				if (workProductType == null)
					return true;
				else if (obj.getClass().equals(this.workProductType)) {
					return true;
				} else {
					return false;
				}

			} else {
				return true;
			}
		} else {
			return false;
		}
	}

}
