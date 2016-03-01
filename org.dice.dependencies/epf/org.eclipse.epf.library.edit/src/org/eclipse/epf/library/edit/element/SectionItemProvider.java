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
package org.eclipse.epf.library.edit.element;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.util.TngUtil;

/**
 * The item provider adapter for a section.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class SectionItemProvider extends
		org.eclipse.epf.uma.provider.SectionItemProvider {

	public SectionItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public String getText(Object object) {
		return TngUtil.getLabel(object, getString("_UI_Section_type")); //$NON-NLS-1$
	}
}
