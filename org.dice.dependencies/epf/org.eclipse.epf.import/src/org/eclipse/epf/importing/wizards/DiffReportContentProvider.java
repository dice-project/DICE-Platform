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
package org.eclipse.epf.importing.wizards;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.importing.services.ElementDiffTree;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * The content provider for the diff report.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class DiffReportContentProvider implements ITreeContentProvider {

	/**
	 * Creates a new instance.
	 */
	public DiffReportContentProvider() {
	}


	/**
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof ElementDiffTree) {
			
			MethodElement e = ((ElementDiffTree)inputElement).getBaseElement();
			
			// don't show sub-packages under process component, hide the process detial
			if ( (e instanceof ProcessComponent) ) {
				return new Object[] {};
			}

			List items = ((ElementDiffTree) inputElement).getChildren();
			if (items != null) {
				List dirtyItems = new ArrayList();
				for (Iterator it = items.iterator(); it.hasNext();) {
					ElementDiffTree item = (ElementDiffTree) it.next();
					if (item.isOldOnly()) {
						continue;
					}
					
					// 150895Import Config: CustomCategory did not sync up by import
					// to fix this problem, we need to include the Hidden root customCategory package 
					// but need to hide here
					MethodElement base = item.getBaseElement();
					if ( (base instanceof MethodPackage) 
							&& TngUtil.isRootCutomCategoryPackage((MethodPackage)base) ) {
						continue;
					}
					
					dirtyItems.add(item);
				}

				if (e instanceof MethodLibrary) {
					Comparator comparator = new Comparator<ElementDiffTree>() {
						public int compare(ElementDiffTree o1, ElementDiffTree o2) {
							if (o1 == o2) {
								return 0;
							}
							Collator collator = Collator.getInstance();
	
							return collator.compare(o1.getName(), o2.getName());
						}
					};
					Collections.<ElementDiffTree> sort(dirtyItems, comparator);
				}
				
				return dirtyItems.toArray();
			}
		}
		return new Object[] {};
	}

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
	}

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		return getElements(parentElement);
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		if (element instanceof ElementDiffTree) {
			return ((ElementDiffTree) element).getParent();
		}
		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof ElementDiffTree) {
			return ((ElementDiffTree) element).hasChildren();
		}
		return false;
	}

}
