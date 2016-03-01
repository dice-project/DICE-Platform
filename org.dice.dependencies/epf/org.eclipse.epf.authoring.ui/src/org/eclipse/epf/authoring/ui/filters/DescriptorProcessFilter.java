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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.epf.library.configuration.ProcessConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.itemsfilter.IProcessFilter;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;


/**
 * {@link Process} filter for filtering {@link Process} elements. 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class DescriptorProcessFilter extends AbstractBaseFilter implements
		IProcessFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.itemsfilter.IFilter#getObject()
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

		if (!helper.isObjectInSelectedItems(obj))
			return false;
		if (!matchPattern(obj))
			return false;
		if (obj instanceof ProcessComponent)
			return true;
		if (obj instanceof Process)
			return true;
		if (childAccept(obj))
			return true;

		return false;
	}

	protected boolean childAccept(Object obj) {
		return false;
	}

	public boolean matchPattern(Object obj) {
		// if no valid filter type or pattern, always return true
		String filterTypeStr = helper.getFilterTypeStr();
		// String pattern = helper.getPattern();
		String tabStr = helper.getTabStr();
		Pattern regexPattern = helper.getRegexPattern();
		if (helper.getFilterTypeStr() == null || helper.getPattern() == null
				|| helper.getPattern().equalsIgnoreCase("")) //$NON-NLS-1$
			return true;

		if (filterTypeStr.equalsIgnoreCase(tabStr)) {
			if (obj instanceof BreakdownElement) {
				if (obj instanceof Activity)
					return true;
				Matcher m = regexPattern.matcher(((BreakdownElement) obj)
						.getName());
				boolean found = m.matches();
				if (found)
					return true;
				else {
					return false;
				}
			} else {
				// else for all other types of elements
				return true;
			}
		} else
			// the All case
			return true;
	}

	protected void getActivitiesInScope(AdapterFactory adapterFactory,
			BreakdownElement element, List activityList) {
		ItemProviderAdapter adapter = (ItemProviderAdapter) adapterFactory
				.adapt(element, ITreeItemContentProvider.class);
		Object parent = adapter.getParent(element);
		if (parent instanceof Activity) {
			activityList.add(parent);
			getActivitiesInScope(adapterFactory, (BreakdownElement) parent,
					activityList);
		}
	}
	public DescriptorProcessFilter(MethodConfiguration config) {
		setAdditionalFilters(new IFilter[] { new ProcessConfigurator(config) });
	}

}
