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
package org.eclipse.epf.persistence.refresh;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Instance of this class will be notify about changes in resources so it can
 * react accordingly
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IRefreshHandler {

	void refresh(IProgressMonitor monitor);

}
