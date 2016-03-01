/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.util;

import java.util.Comparator;

import org.eclipse.emf.ecore.ENamedElement;

/**
 * Compares {@link ENamedElement EMF model elements} by their
 * {@link ENamedElement#getName() name}
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public final class ModelElementNameComparator implements Comparator<ENamedElement> {

	/**
	 * A comparator comparing case-sensitively
	 */
	public static final Comparator<ENamedElement> INSTANCE = new ModelElementNameComparator(false);

	/**
	 * A comparator comparing case-insensitively, i.e. ignoring case
	 */
	public static final Comparator<ENamedElement> INSTANCE_IGNORING_CASE = new ModelElementNameComparator(true);

	private final boolean mIgnoringCase;

	/**
	 * @see Comparator#compare(Object, Object)
	 */
	public int compare(ENamedElement o1, ENamedElement o2) {
		if (o1 == o2) {
			return 0;
		} else if (o1 == null) {
			return -1;
		} else if (o2 == null) {
			return 1;
		}

		if (this.mIgnoringCase) {
			return o1.getName().compareToIgnoreCase(o2.getName());
		} else {
			return o1.getName().compareTo(o2.getName());
		}
	}

	/**
	 * Creates a comparator which compares case-sensitively or insensitve
	 * depending on the given flag
	 * 
	 * @param compareIgnoringCase
	 *            <code>true</code> if case should be ignored,
	 *            <code>false</code> otherwise
	 * 
	 * @see #INSTANCE
	 * @see #INSTANCE_IGNORING_CASE
	 */
	private ModelElementNameComparator(boolean compareIgnoringCase) {
		this.mIgnoringCase = compareIgnoringCase;
	}

	@Override
	public String toString() {
		return this.mIgnoringCase ? "NOT case-sensitive" : "Case-sensitive"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
