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

import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.search.ui.SearchUIPlugin;

/**
 * @author Phong Nguyen Le
 * @since 1.5
 *
 */
public class MethodSearchHelper {
	public static final IMethodSearchOperation newSearchOperation() {
		Object ext = ExtensionManager.createExtension(SearchUIPlugin.getDefault().getId(), "operation"); //$NON-NLS-1$
		if(ext instanceof IMethodSearchOperation) {
			return (IMethodSearchOperation) ext;
		}
		return new MethodSearchOperation();
	}
}
