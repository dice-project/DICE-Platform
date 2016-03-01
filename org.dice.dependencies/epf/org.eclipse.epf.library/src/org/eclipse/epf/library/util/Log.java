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
package org.eclipse.epf.library.util;

import org.eclipse.epf.common.serviceability.Logger;
import org.eclipse.epf.library.LibraryPlugin;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class Log {

	public static final boolean DEBUG = LibraryPlugin.getDefault()
			.isDebugging();

	public static final Logger INSTANCE = LibraryPlugin.getDefault()
			.getLogger();

}
