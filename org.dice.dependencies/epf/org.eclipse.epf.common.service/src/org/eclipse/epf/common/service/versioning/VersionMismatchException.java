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
package org.eclipse.epf.common.service.versioning;

import org.osgi.framework.Version;

/**
 * Signals that the current version of the tool is not compatible with the
 * method library it is trying to open.
 * 
 * @author Jeff Hardy
 * @since 1.0
 */
public class VersionMismatchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Version expected;

	private Version actual;

	public VersionMismatchException(Version expected, String actual) {
		super();
		this.expected = expected;
		if (actual == null) {
			this.actual = Version.emptyVersion;
		} else {
			this.actual = new Version(actual);
		}
	}

	public Version getActual() {
		return actual;
	}

	public Version getExpected() {
		return expected;
	}
}
