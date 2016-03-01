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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.epf.library.edit.itemsfilter.CategorizedProcessesItemProvider;
import org.eclipse.epf.library.edit.itemsfilter.IAllFilter;
import org.eclipse.epf.library.edit.itemsfilter.ProcessesItemProvider;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;


/**
 * Filters the {@link Process}s for reference workflows of {@link Discipline}. 
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ReferenceWorkFlowFilter extends AbstractBaseFilter implements
		IAllFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.itemsfilter.IAllFilter#getObject()
	 */
	public Object getObject() {
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

		Object contentElement = helper.getContentElement();
		if (contentElement != null) {
			if (obj.equals(helper.getContentElement()))
				return false;
		}

		if (helper.getAlreadySelectedList() != null) {
			if (obj instanceof ProcessComponent) {
				if (helper.getAlreadySelectedList().contains(
						((ProcessComponent) obj).getProcess()))
					return false;
			}
			if(helper.getAlreadySelectedList().contains(obj))
				return false;
		}
		if (!helper.matchPattern(obj))
			return false;

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
		if (obj instanceof ProcessesItemProvider) {
			Collection list = ((ProcessesItemProvider) obj).getChildren(obj);
			for (Iterator ior = list.iterator(); ior.hasNext();) {
				Object object = ior.next();
				if (((CategorizedProcessesItemProvider) object).getChildren(
						object).isEmpty())
					ior.remove();
			}
			if (list.isEmpty())
				return false;
			else
				return true;
		}
		if (obj instanceof Activity)
			return true;
		if (obj instanceof ProcessPackage) {
			if (!(obj instanceof ProcessComponent)) {
				if (((ProcessPackage) obj).getProcessElements().isEmpty()
						&& ((ProcessPackage) obj).getChildPackages().isEmpty())
					return false;
				else
					return true;
			} else {
				return true;
			}
		}

		return false;
	}

}
