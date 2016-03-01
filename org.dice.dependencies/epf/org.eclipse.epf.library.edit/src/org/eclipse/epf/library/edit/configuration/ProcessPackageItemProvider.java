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
package org.eclipse.epf.library.edit.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IConfigurable;
import org.eclipse.epf.library.edit.IConfigurator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;

/**
 * The item provider adapter for a process package in the Configuration view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ProcessPackageItemProvider extends
		org.eclipse.epf.library.edit.navigator.ProcessPackageItemProvider
		implements IConfigurable {

	private IFilter filter;

	/**
	 * Creates a new instance.
	 */
	public ProcessPackageItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	protected boolean acceptAsChild(Process process) {
		return !TngUtil.isContributorOrReplacer(process)
				&& !process.getSuppressed().booleanValue();
	}

	public static boolean collectChildren(Collection children, Object element,
			IFilter filter) {
		if (element instanceof ProcessComponent) {
			Process process = ((ProcessComponent) element).getProcess();
			if (process != null) {
				if (filter instanceof IConfigurator) {
					Object child = ((IConfigurator) filter).resolve(process);
					if (!children.contains(child) && filter.accept(child)) {
						children.add(child);
						return true;
					}
				} else if (filter == null || filter.accept(process)) {
					children.add(process);
					return true;
				}
			}
		} else if (element instanceof ProcessPackage) {
			if (filter == null || filter.accept(element)) {
				children.add(element);
				return true;
			}
		}
		return false;
	}

	private boolean collectChildren(Collection children, Object element) {
		return collectChildren(children, element, filter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.process.ProcessPackageItemProvider#getChildren(java.lang.Object)
	 */
	public Collection getChildren(Object object) {
		ArrayList children = new ArrayList();
		for (Iterator iter = super.getChildren(object).iterator(); iter
				.hasNext();) {
			collectChildren(children, iter.next());
		}

		// set parent for the collected children
		//
		for (Iterator iter = children.iterator(); iter.hasNext();) {
			Object child = iter.next();
			ProcessUtil.setParent(child, object, adapterFactory);
		}

		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.provider.ItemProviderAdapter#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object object) {
		ArrayList children = new ArrayList();
		for (Iterator iter = super.getChildren(object).iterator(); iter
				.hasNext();) {
			if (collectChildren(children, iter.next())) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.IConfigurable#setFilter(org.eclipse.epf.library.edit.IFilter)
	 */
	public void setFilter(IFilter filter) {
		this.filter = filter;
		if (filter instanceof IConfigurator) {
			((IConfigurator) filter).getMethodConfiguration();
		}
	}

}
