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
package org.eclipse.epf.common.utils;

/**
 * Utility class for performing math operations.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MathUtil {

	public static final long ceil1000(long l) {
		return (l - (l & 7));
	}

}
