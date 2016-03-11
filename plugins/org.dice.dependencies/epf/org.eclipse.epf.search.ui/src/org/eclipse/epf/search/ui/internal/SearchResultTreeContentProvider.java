//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.search.ui.internal;

import org.eclipse.epf.library.ui.providers.AbstractElementTreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

/**
 * The content provider for the method search result tree viewer.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @author Pierre Padovani
 * @since 1.0
 */
public class SearchResultTreeContentProvider extends AbstractElementTreeContentProvider {

	private static final Object[] EMPTY_LIST = new Object[0];

	private MethodSearchResult searchResult;

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof MethodSearchResult) {
			Object[] elements = ((MethodSearchResult) inputElement)
					.getElements();
			if (elements.length == 0) {
				return EMPTY_LIST;
			}
			getElementMap().clear();
			for (int i = 0; i < elements.length; i++) {
				insert(elements[i], false);
			}

			insertUIFolders(inputElement);
		}
		return getChildren(inputElement);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (newInput != null && newInput instanceof MethodSearchResult) {
			setTreeViewer((TreeViewer) viewer);
			searchResult = (MethodSearchResult) newInput;
		}
	}

	/**
	 * @param updatedElements
	 */
	public void elementsChanged(Object[] updatedElements) {
		for (int i = 0; i < updatedElements.length; i++) {
			if (searchResult.getMatchCount(updatedElements[i]) > 0) {
				if (getTreeViewer().testFindItem(updatedElements[i]) != null) {
					insert(updatedElements[i], true);
				} else {
					remove(updatedElements[i], true);
				}
			} else {
				getTreeViewer().remove(updatedElements[i]);
			}
		}
		getTreeViewer().refresh();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.ui.providers.AbstractElementTreeContentProvider#remove(java.lang.Object, boolean)
	 */
	protected void remove(Object element, boolean refreshViewer) {
		if (hasChildren(element)) {
			if (refreshViewer) {
				getTreeViewer().refresh(element);
			}
		} else {
			if (searchResult.getMatchCount(element) == 0) {
				getElementMap().remove(element);
				Object parent = getParent(element);
				if (parent != null) {
					removeFromSiblings(element, parent);
					remove(parent, refreshViewer);
				} else {
					removeFromSiblings(element, searchResult);
					if (refreshViewer) {
						getTreeViewer().refresh();
					}
				}
			} else {
				if (refreshViewer) {
					getTreeViewer().refresh(element);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.ui.providers.AbstractElementTreeContentProvider#getContentSource()
	 */
	@Override
	public Object getContentSource() {
		return searchResult;
	}

}