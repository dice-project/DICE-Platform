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
package org.eclipse.epf.library.edit.validation.internal;

import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.UmaPackage;


/**
 * Name validator for CustomCategory.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class CustomCategoryNameValidator extends ContentElementNameValidator {

	/**
	 * @param container
	 * @param e
	 * @param childFilter
	 */
	public CustomCategoryNameValidator(CustomCategory parent, CustomCategory e,
			IFilter childFilter) {
		super(parent, UmaPackage.Literals.CUSTOM_CATEGORY__CATEGORIZED_ELEMENTS, e, childFilter);
	}

}
