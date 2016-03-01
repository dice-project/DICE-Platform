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

import org.eclipse.epf.library.edit.itemsfilter.IContentFilter;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodPlugin;


/**
 * Filter implements {@link IContentFilter}. Filter content element in content package. 
 * 
 * @author Shashidhar Kannoori
 * @author Jinhua Xi
 * @since 1.0
 */
public class ContentFilter extends AbstractBaseFilter implements IContentFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.itemsfilter.IContentFilter#getContentPackagePath()
	 */
	public String[] getContentPackagePath() {
		return helper.getPathBasedOnTabString(helper.getTabStr());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.itemsfilter.IContentFilter#getObject()
	 */
	public Object getObject() {
		return helper.getContentElement();
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

		// further filtering
		if (!helper.checkObjectAccepted(obj))
			return false;

		if (obj instanceof MethodPlugin) {
			return helper.acceptMethodPlugin(obj);
		}

		if (childAccept(obj))
			return true;

		if (obj instanceof ContentPackage) {
			return helper.acceptContentPackage(obj);
		}

		return false;
	}

	protected boolean childAccept(Object obj) {
		return false;
	}

}
