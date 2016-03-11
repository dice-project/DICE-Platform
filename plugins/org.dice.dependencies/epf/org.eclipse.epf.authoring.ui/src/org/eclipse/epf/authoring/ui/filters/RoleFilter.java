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

import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Role;


/**
 * Filters {@link Role} 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class RoleFilter extends AllFilter {

	public boolean accept(Object obj) {
		Object contentElement = getHelper().getContentElement();
		if (contentElement != null) {
			if (obj.equals(contentElement))
				return false;
		}

		if (getHelper().getAlreadySelectedList() != null) {
			if (getHelper().getAlreadySelectedList().contains(obj))
				return false;
		}
		if (!getHelper().matchPattern(obj))
			return false;
		if (obj instanceof MethodPlugin) {
			if (obj instanceof MethodPlugin) {
				if (contentElement != null) {
					if (MethodElementUtil.getAllModels(contentElement)
							.contains(obj))
						return true;
					else
						return false;
				} else {
					return true;
				}
			}
		}
		if (obj instanceof ContentPackage)
			return true;
		if (obj instanceof Role)
			return true;
		return false;
	}

}
