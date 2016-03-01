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
package org.eclipse.epf.persistence.util;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.epf.uma.MethodLibrary;

/**
 * Scheduling rule used to lock a library during a operation and defer the
 * broadcasting of resource change event until the operation is finished.
 * 
 * @author Phong Nguyen Le - Aug 15, 2006
 * @since 1.0
 */
public class LibrarySchedulingRule implements ISchedulingRule {
	private MethodLibrary library;

	/**
	 * @param lib
	 */
	public LibrarySchedulingRule(MethodLibrary lib) {
		this.library = lib;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule#contains(org.eclipse.core.runtime.jobs.ISchedulingRule)
	 */
	public boolean contains(ISchedulingRule rule) {
		if (this == rule) {
			return true;
		}
		if (rule instanceof LibrarySchedulingRule) {
			return ((LibrarySchedulingRule) rule).library == library;
		}
		if (rule instanceof MultiRule) {
			MultiRule mrule = (MultiRule) rule;
			ISchedulingRule[] childRules = mrule.getChildren();
			for (int i=0; i<childRules.length; i++) {
				if (! (childRules[i] instanceof IResource)) {
					return false;
				}
			}
			return true;
		}

		// allow any IResource rule to nest within the same thread
		//
		return rule instanceof IResource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule#isConflicting(org.eclipse.core.runtime.jobs.ISchedulingRule)
	 */
	public boolean isConflicting(ISchedulingRule rule) {
		return equals(rule);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return this == obj
				|| (obj instanceof LibrarySchedulingRule && ((LibrarySchedulingRule) obj).library == library);
	}

	@Override
	public int hashCode() {
		return library.hashCode();
	}
}
