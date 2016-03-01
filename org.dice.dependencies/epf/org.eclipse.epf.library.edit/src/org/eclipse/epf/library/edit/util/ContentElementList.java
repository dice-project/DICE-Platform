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
package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.epf.uma.ContentPackage;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public abstract class ContentElementList {

	private ContentPackage contentPkg;

	protected class ElementIterator extends AbstractTreeIterator {

		/**
		 * Comment for <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = -2291438970123985906L;

		protected ElementIterator() {
			super(contentPkg, false);
		}
		
		protected List getChildrenList(Object object) {
			ArrayList children = new ArrayList();
			if(object instanceof ContentPackage) {
				ContentPackage pkg = (ContentPackage) object;
				children.addAll(pkg.getChildPackages());
				List elements = pkg.getContentElements();
				for (int i = 0; i < elements.size(); i++) {
					Object obj = elements.get(i);
					if (accept(obj)) {
						children.add(obj);
					}
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
	
	public ContentElementList(ContentPackage contentPkg) {
		this.contentPkg = contentPkg;
	}

	public abstract boolean accept(Object obj);

	protected Iterator elementIterator() {
		return new ElementIterator();
	}
	
	private void getAllElements(ContentPackage pkg, List list) {
//		List elements = pkg.getContentElements();
//		for (int i = 0; i < elements.size(); i++) {
//			Object obj = elements.get(i);
//			if (accept(obj)) {
//				list.add(obj);
//			}
//		}
//		List packages = pkg.getChildPackages();
//		for (int i = 0; i < packages.size(); i++) {
//			Object obj = packages.get(i);
//			if (obj instanceof ContentPackage) {
//				getAllElements((ContentPackage) obj, list);
//			}
//		}
		
		for(Iterator iter = elementIterator(); iter.hasNext();) {
			Object e = iter.next();
			if(!(e instanceof ContentPackage)) {
				list.add(e);
			}
		}
	}

	public List getList() {
		List list = new ArrayList();
		getAllElements(contentPkg, list);
		return list;
	}

}
