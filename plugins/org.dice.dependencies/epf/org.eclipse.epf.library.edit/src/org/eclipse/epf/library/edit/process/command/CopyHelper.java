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
package org.eclipse.epf.library.edit.process.command;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.VariabilityElement;

public class CopyHelper extends CopyCommand.Helper {
	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 5264389393955639004L;

	private Map copyToOriginalMap = new HashMap();
	
	private Map baseToVariabilityElementMap = new HashMap();
	private Map baseToBackupVariabilityElementMap = new HashMap();

	private Map<String, BreakdownElement> wrapperPathToCopyMap = new HashMap<String, BreakdownElement>();
	
	/* (non-Javadoc)
	 * @see java.util.HashMap#clear()
	 */
	public void clear() {		
		super.clear();
		copyToOriginalMap.clear();
		baseToBackupVariabilityElementMap.clear();
		baseToVariabilityElementMap.clear();
		wrapperPathToCopyMap.clear();
	}
	
	public Object getOriginal(Object copy) {
		return copyToOriginalMap.get(copy);
	}
	
	public Object putVariabilityElement(VariabilityElement base, VariabilityElement ve) {
		return baseToVariabilityElementMap.put(base, ve);
	}
	
	public Object removeVariabilityElement(VariabilityElement base) {
		return baseToVariabilityElementMap.remove(base);
	}
	
	public Object putBackupCopy(VariabilityElement base, VariabilityElement backup) {
		return baseToBackupVariabilityElementMap.put(base, backup);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.command.CopyCommand.Helper#put(java.lang.Object, java.lang.Object)
	 */
	public EObject put(EObject key, EObject value) {
		copyToOriginalMap.put(value, key);
		return super.put(key, value);
	}
	
	public Object basicPut(EObject key, EObject value) {
		Object old = super.put(key, value);
		initializationList.remove(key);
		return old;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.command.CopyCommand.Helper#getCopy(org.eclipse.emf.ecore.EObject)
	 */
	public EObject getCopy(EObject object) {
		if(object instanceof VariabilityElement) {
			Object ve = getVariabilityElement(baseToVariabilityElementMap, object);
			if(ve != null) {
				return super.getCopy((EObject) ve);
			}
		}
		EObject copy = super.getCopy(object);
		if(copy == null) {
			// get backup copy
			//
			Object ve = getVariabilityElement(baseToBackupVariabilityElementMap, object);
			if(ve != null) {
				return super.getCopy((EObject) ve);
			}
		}
		return copy;
	}
	
	private static Object getVariabilityElement(Map baseToVariabilityElementMap, Object base) {
		Object ve = null;		
		do {
			base = baseToVariabilityElementMap.get(base);
			if(base != null) {
				ve = base;
			}
		} while (base != null);
		return ve;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.edit.command.CopyCommand.Helper#getCopyTarget(org.eclipse.emf.ecore.EObject, boolean)
	 */
	public EObject getCopyTarget(EObject target, boolean copyRequired) {
		if(copyRequired) {
			return super.getCopy(target);
		}
		else {
			EObject copied = getCopy(target);
			if (copied == null)
			{
				return target;
			}
			return copied;
		}
	}
	
	public Map getObjectToCopyMap() {
		HashSet objects = new HashSet(baseToBackupVariabilityElementMap.keySet());
		objects.addAll(baseToVariabilityElementMap.keySet());
		objects.addAll(keySet());		
		Map map = new HashMap();
		for (Iterator iter = objects.iterator(); iter.hasNext();) {
			Object object = iter.next();
			map.put(object, getCopy((EObject) object));
		}
		return map;
	}		

	public void putWrapperCopy(String guidPath, BreakdownElement e) {
		wrapperPathToCopyMap .put(guidPath, e);
	}
	
	public BreakdownElement getWrapperCopy(String guidPath) {
		return wrapperPathToCopyMap.get(guidPath);
	}

	public Map<String, BreakdownElement> getWrapperPathToCopyMap() {
		return wrapperPathToCopyMap;
	}
}