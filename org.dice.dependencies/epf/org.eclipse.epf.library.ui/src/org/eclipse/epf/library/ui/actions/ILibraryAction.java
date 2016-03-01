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
package org.eclipse.epf.library.ui.actions;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;

/**
 * The interface for a Library action.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface ILibraryAction extends IAction {
//	void notifyPropertyChange(String propertyName, Object oldValue,
//			Object newValue);
	
	void setProgressMonitor(IProgressMonitor monitor);

}
