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
package org.eclipse.epf.authoring.ui.dialogs;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;



/**
 * A {@link ItemsFilterDialog} dialog viewer sorter to order the display for certain element and packages.
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class FilterSorter extends ViewerSorter {

	public FilterSorter() {
		super();
	}

	public int category(Object element) {
		if (element instanceof ContentPackage)
			return 0;
		else
			return 1;
	}

	/**
	 * @param collator
	 */
	public FilterSorter(Collator collator) {
		super(collator);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public int compare(Viewer viewer, Object e1, Object e2) {

		if (!(e1 instanceof ContentPackage) || !(e2 instanceof ContentPackage)) {
			int cat1 = category(e1);
			int cat2 = category(e2);

			if (cat1 != cat2)
				return cat1 - cat2;
		}

		if (e1 instanceof MethodPlugin && e2 instanceof MethodPlugin) {
			return 0;
		}

		String name1;
		String name2;

		if (viewer == null || !(viewer instanceof ContentViewer)) {
			name1 = e1.toString();
			name2 = e2.toString();
		} else {
			IBaseLabelProvider prov = ((ContentViewer) viewer)
					.getLabelProvider();
			if (prov instanceof ILabelProvider) {
				ILabelProvider lprov = (ILabelProvider) prov;
				name1 = lprov.getText(e1);
				name2 = lprov.getText(e2);
			} else {
				name1 = e1.toString();
				name2 = e2.toString();
			}
		}
		if (name1 == null)
			name1 = "";//$NON-NLS-1$
		if (name2 == null)
			name2 = "";//$NON-NLS-1$
		return collator.compare(name1, name2);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerComparator#sort(org.eclipse.jface.viewers.Viewer, java.lang.Object[])
	 */
	public void sort(final Viewer viewer, Object[] elements) {
		Arrays.sort(elements, new Comparator() {
			public int compare(Object a, Object b) {
				return FilterSorter.this.compare(viewer, a, b);
			}
		});
	}

}
