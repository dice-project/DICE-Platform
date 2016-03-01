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

import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.VariabilityType;

/**
 * Interface for dependency info used in circular dependency checking
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public interface IDependencyInfo {
	
	void build(boolean checkCircular);
	
	boolean isComplete();
	
	boolean reachableBy(IDependencyInfo info);
	
	boolean inheritAncestor(VariabilityType type);
	
	MethodElement getElement();
	
}
