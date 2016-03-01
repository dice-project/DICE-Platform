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
package org.eclipse.epf.library.configuration;

import java.util.Comparator;
import java.util.List;

/**
 * a comparator to sort an element list.
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class OrderedListComparator implements Comparator {

	// sort the baselist according to the sorted list
	// any item in sorted list but not in base list are ignored
	// any item in base list but not in sorted list keeps the original order
	List baseList;

	List sortedList;

	OrderedListComparator(List baseList, List sortedList) {
		this.baseList = baseList;
		this.sortedList = sortedList;
	}

	/**
	 * compare to objects.
	 */
	public int compare(Object e1, Object e2) {
		int i1, i2;
		if (sortedList != null) {
			i1 = sortedList.lastIndexOf(e1);
			i2 = sortedList.lastIndexOf(e2);
			if (i1 >= 0 || i2 >= 0) {
				// if not in the sorted list, find the immediate predicessor
				// that is in the list and compare to it.
				// if no such element, compare to the original list
				if (i1 < 0) {
					i1 = findComparableIndex(e1);
				}

				if (i2 < 0) {
					i2 = findComparableIndex(e2);
				}

				if (i1 >= 0 && i2 >= 0 && i1 != i2) {
					return i1 - i2;
				}
			}
		}

		return originalOrder(e1, e2);
	}

	private int findComparableIndex(Object e) {
		int i2 = -1;
		for (int i = baseList.lastIndexOf(e); i >= 0; i--) {
			Object o = baseList.get(i);
			i2 = sortedList.lastIndexOf(o);
			if (i2 >= 0) {
				break;
			}
		}

		return i2;
	}

	private int originalOrder(Object e1, Object e2) {
		int i1, i2;
		if (baseList != null) {
			i1 = baseList.lastIndexOf(e1);
			i2 = baseList.lastIndexOf(e2);

			return i1 - i2;
		}

		return 0;
	}

}
