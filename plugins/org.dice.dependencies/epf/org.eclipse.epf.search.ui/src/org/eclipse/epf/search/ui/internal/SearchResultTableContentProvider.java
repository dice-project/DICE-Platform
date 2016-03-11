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
package org.eclipse.epf.search.ui.internal;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

/**
 * The content provider for the method search result table viewer.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class SearchResultTableContentProvider implements
		IStructuredContentProvider {

	private static final Object[] EMPTY_LIST = new Object[0];

	private TableViewer tableViewer;

	private MethodSearchResult searchResult;

	/**
	 * @see IStructuredContentProvider#getElements(Object)
	 */
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof MethodSearchResult) {
			return ((MethodSearchResult) inputElement).getElements();
		}
		return EMPTY_LIST;
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (newInput != null && newInput instanceof MethodSearchResult) {
			tableViewer = (TableViewer) viewer;
			searchResult = (MethodSearchResult) newInput;
		}
	}

	public void elementsChanged(Object[] updatedElements) {
		for (int i = 0; i < updatedElements.length; i++) {
			if (searchResult.getMatchCount(updatedElements[i]) > 0) {
				if (tableViewer.testFindItem(updatedElements[i]) != null) {
					tableViewer.refresh(updatedElements[i]);
				} else {
					tableViewer.add(updatedElements[i]);
				}
			} else {
				tableViewer.remove(updatedElements[i]);
			}
		}
		tableViewer.refresh();
	}

	public void clear() {
		tableViewer.refresh();
	}

	public void dispose() {
	}

}
