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

import java.util.Map;

import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.uma.VariabilityElement;

/**
 * This class implements a filter for circular dependency check
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class CircularDependencyCheckFilter extends CircularDependencyCheck implements IFilter {	
	
	private Map ancestorMap;
	
	
	public CircularDependencyCheckFilter(DependencyInfoMgr mgr, VariabilityElement ve) {
		super(mgr, ve, true, false);
		
		
		
	}			
	
	public boolean accept(Object obj) {
		return super.accept(obj);
	}
	
/*	public static boolean VeToCheck(Object obj) {
		return 	obj instanceof Domain || 				//getSubdomains
				obj instanceof CustomCategory || 		//getSubCategories
				obj instanceof Artifact || 				//getContainedArtifacts
				obj instanceof Practice || 				//getSubPractices
				obj instanceof Deliverable ||			//getDeliveredWorkProducts
				obj instanceof Activity;				//getBreakdownElements
	}*/
	
	
}
