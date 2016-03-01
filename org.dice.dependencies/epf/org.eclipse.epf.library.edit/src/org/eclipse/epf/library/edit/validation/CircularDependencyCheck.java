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

import org.eclipse.epf.uma.VariabilityElement;

/**
 * This class does circular dependency check
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class CircularDependencyCheck {
		
	private DependencyInfoMgr depInfoMgr;
	private IDependencyInfo veInfo;
	private boolean move = false;
	
	public CircularDependencyCheck(DependencyInfoMgr mgr, VariabilityElement ve,
								boolean filter, boolean move) {
		depInfoMgr = mgr;
		veInfo = depInfoMgr.registerVariabilityElement(ve, filter, false);
		this.move = move;
	}
	
	protected DependencyInfoMgr getDepInfoMgr() {
		return depInfoMgr;
	}
	
	protected IDependencyInfo getVeInfo() {
		return veInfo;
	}
		
	public boolean accept(Object obj) {

		if (reachable(obj, true, true)) {
			return false;
		}
		
		if (move) {
			if(!(obj instanceof VariabilityElement)) return true;
			VariabilityElement objVeBase = ((VariabilityElement) obj).getVariabilityBasedOnElement();
			if (objVeBase != null) {
				IDependencyInfo info = depInfoMgr.registerVariabilityElement(objVeBase, false, false);	
				if (info.reachableBy(veInfo)) {
					return false;
				}
			}
		}
				
		return true;
	}
	
	private boolean reachable(Object obj, boolean checkReachableByObj, boolean checkReachableToObj) {
		if (! (obj instanceof VariabilityElement)) {
			return false;
		}
		
		VariabilityElement ve = (VariabilityElement) veInfo.getElement();
		if (ve == obj) {
			return true;			
		}

		IDependencyInfo objVeInfo = depInfoMgr.registerVariabilityElement( (VariabilityElement) obj, false, move);		
		
		if (checkReachableByObj && veInfo.reachableBy(objVeInfo)) {
			return true;
		}
		
		if (checkReachableToObj && objVeInfo.reachableBy(veInfo)) {
			return true;
		}		
		
		return false;
	}	
	
	public boolean reachableBy(Object obj) {
		return reachable(obj, true, false);
	}
	
	public boolean reachableTo(Object obj) {
		return reachable(obj, false, true);
	}	
	
}
