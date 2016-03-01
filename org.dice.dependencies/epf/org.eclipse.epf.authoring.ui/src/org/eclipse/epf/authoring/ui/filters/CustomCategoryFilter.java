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

import org.eclipse.epf.library.edit.itemsfilter.ICustomFilter;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.MethodPlugin;


/**
 * Filter specific for {@link CustomCategory} elements. 
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class CustomCategoryFilter extends AbstractBaseFilter implements
		ICustomFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.itemsfilter.ICustomFilter#getContentPackagePath()
	 */
	public String[] getContentPackagePath() {
		return helper.getPathBasedOnTabString(helper.getTabStr());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.itemsfilter.ICustomFilter#getObject()
	 */
	public Object getObject() {
		return helper.getContentElement();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.itemsfilter.ICustomFilter#getString()
	 */
	public String getString() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.IFilter#accept(java.lang.Object)
	 */
	public boolean accept(Object obj) {
		// if can't be accdepted by super, return.
		if (!super.accept(obj)) {
			return false;
		}

		if (!helper.checkObjectAccepted(obj))
			return false;

		if (obj instanceof MethodPlugin) {
			return helper.acceptMethodPlugin(obj);
		}

		if (childAccept(obj))
			return true;
		return false;
	}

	private boolean childAccept(Object obj) {
		if (obj instanceof CustomCategory)
			return true;
		return false;
	}

}
