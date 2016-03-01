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

import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;


/**
 * Type mapper for the logic example. We want to get the GEF model object from
 * the selected element in the outline view and the diagram. We can then filter
 * on the model object type.
 * 
 * @author Shilpa Toraskar
 * @since 1.0
 * 
 */
public class ElementTypeMapper implements ITypeMapper {


	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITypeMapper#mapType(java.lang.Object)
	 */
	public Class mapType(Object object) {
//		Class type = effectiveType;
		Class type = null;
		if (object != null) {
			type = object.getClass();
			if (object instanceof BreakdownElement) {
				type = ((BreakdownElement) object).getClass();
			}
		}
	
		return type;
	}
}