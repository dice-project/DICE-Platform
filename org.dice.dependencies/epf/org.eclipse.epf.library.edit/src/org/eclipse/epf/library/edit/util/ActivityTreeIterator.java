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

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.epf.uma.Activity;

/**
 * Interator used to iterate the breakdown element tree of an activity
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ActivityTreeIterator extends AbstractTreeIterator {

	private static final long serialVersionUID = 6402017768205642068L;

	public ActivityTreeIterator(Activity act) {
		super(act, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.common.util.AbstractTreeIterator#getChildren(java.lang.Object)
	 */
	protected Iterator getChildren(Object object) {
		if (object instanceof Activity) {
			return ((Activity) object).getBreakdownElements().iterator();
		}
		return Collections.EMPTY_LIST.iterator();
	}

}
