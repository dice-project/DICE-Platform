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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * Keeps track of a list of method elements in a method configuration.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodConfigurationElementList {

	private MethodConfiguration methodConfig;

	private List filterList;

	protected class ElementIterator extends AbstractTreeIterator {

		/**
		 * Comment for <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = -2291438970123985906L;

		protected ElementIterator(ContentPackage contentPkg) {
			super(contentPkg, false);
		}
		
		protected List getChildrenList(Object object) {
			ArrayList children = new ArrayList();
			if(object instanceof ContentPackage) {
				ContentPackage pkg = (ContentPackage) object;
				List packages = pkg.getChildPackages();
				int size = packages.size();
				for (int i = 0; i < size; i++) {
					Object obj = packages.get(i);
					if (obj instanceof ContentPackage
							&& (methodConfig instanceof Scope || methodConfig.getMethodPackageSelection().contains(obj))) {
						children.add(obj);
					}
				}
				if(checkAcceptance()) {
					List elements = pkg.getContentElements();
					size = elements.size();
					for (int i = 0; i < size; i++) {
						Object obj = elements.get(i);
						if (accept(obj)) {
							children.add(obj);
						}
					}
				}
				else {
					children.addAll(pkg.getContentElements());
				}
			}
			return children;
		}

		/* (non-Javadoc)
		 * @see org.eclipse.emf.common.util.AbstractTreeIterator#getChildren(java.lang.Object)
		 */
		protected Iterator getChildren(Object object) {
			return getChildrenList(object).iterator();
		}
		
	};
	
	public MethodConfigurationElementList(MethodConfiguration methodConfig,
			List filterList) {
		this.methodConfig = methodConfig;
		this.filterList = filterList;
	}

	protected boolean checkAcceptance() {
		return true;
	}
	
	protected boolean accept(Object object) {
		int size = filterList.size();
		for (int i = 0; i < size; i++) {
			IFilter filter = (IFilter) filterList.get(i);
			if (!filter.accept(object))
				return false;
		}
		return true;
	}
	
	protected Iterator newIterator(ContentPackage pkg) {
		return new ElementIterator(pkg);
	}

	private void getAllElements(ContentPackage pkg, List list) {
		for(Iterator iter = newIterator(pkg); iter.hasNext();) {
			Object e = iter.next();
			if(!(e instanceof ContentPackage)) {
				list.add(e);
			}
		}
	}

	public List getList() {
		List list = new ArrayList();
		for (Iterator iter = methodConfig.getMethodPluginSelection().iterator(); iter
				.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) iter.next();
			ContentPackage pkg = UmaUtil.findContentPackage(plugin,
					ModelStructure.DEFAULT.coreContentPath);
			if (pkg != null) {
				getAllElements(pkg, list);
			}
		}

		return list;
	}

}
