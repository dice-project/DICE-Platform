//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.VariabilityElement;

/**
 * This class manages all dependency checks for validation
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class DependencyValidationMgr {
	
	private DependencyInfoMgr veDepInfoMgr;
	private PluginDependencyInfoMgr pluginDepInfoMgr;
	
	public DependencyValidationMgr(MethodLibrary lib) {
		veDepInfoMgr = new DependencyInfoMgr(lib);
		pluginDepInfoMgr = new PluginDependencyInfoMgr(lib);
	}
	
	public IStatus checkCircularDependnecy(MethodElement element) {
		IStatus status = Status.OK_STATUS;
		if (element instanceof VariabilityElement) {
			return veDepInfoMgr.checkCircularDependnecy((VariabilityElement) element);
		}
		
		if (element instanceof MethodPlugin) {
			return pluginDepInfoMgr.checkCircularDependnecy((MethodPlugin) element);
		}		
		
		return status;
	}
	
}
