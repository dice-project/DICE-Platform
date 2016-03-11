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

import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.configuration.CategoriesItemProvider;
import org.eclipse.epf.library.edit.configuration.DomainItemProvider;
import org.eclipse.epf.library.edit.configuration.UncategorizedItemProvider;
import org.eclipse.epf.library.edit.element.WorkProductItemProvider;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.jface.viewers.TableViewer;


/**
 * Filters {@link WorkProduct}s with in {@link MethodConfiguration}. 
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ProcessWorkProductFilter extends DescriptorConfigurationFilter {

	public ProcessWorkProductFilter(MethodConfiguration config,
			TableViewer viewer, String tabName) {
		super(config, viewer, tabName);
	}

	public boolean childAccept(Object obj) {
		if (obj instanceof CategoriesItemProvider) {
			if (((CategoriesItemProvider) obj).getText(obj).equalsIgnoreCase(
					LibraryEditPlugin.INSTANCE
							.getString("_UI_WorkProductTypes_group"))) //$NON-NLS-1$
				return true;
		}
		if (obj instanceof CategoriesItemProvider) {
			if (((CategoriesItemProvider) obj).getText(obj).equalsIgnoreCase(
					LibraryEditPlugin.INSTANCE.getString("_UI_Domains_group"))) //$NON-NLS-1$
				return true;
		}
		if ((obj instanceof WorkProductItemProvider)
				|| (obj instanceof WorkProduct)
				|| (obj instanceof UncategorizedItemProvider)
				|| (obj instanceof Domain)
				|| (obj instanceof DomainItemProvider)
				|| (obj instanceof WorkProductType))
			return true;
		else
			return false;
	}
}
