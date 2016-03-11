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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;


/**
 * This class is a helper class for method element unique name handling
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class UniqueNameHandler {
	private Set<String> names = new HashSet<String>();
	
	/**
	 * Empty constructor
	 */
	public UniqueNameHandler() {
	}
	
	/**
	 * @param elements
	 */
	public void registerNames(Collection<? extends MethodElement> elements) {
		if (elements == null) {
			return;
		}
		for (Iterator<? extends MethodElement> it = elements.iterator(); it.hasNext();) {
			MethodElement element = it.next();
			register(element.getName());
		}
	}
	
	/**
	 * @param elements
	 */
	public void registerPresentationNames(Collection<? extends MethodElement> elements) {
		if (elements == null) {
			return;
		}
		for (Iterator<? extends MethodElement> it = elements.iterator(); it.hasNext();) {
			MethodElement element = it.next();
			register(element.getPresentationName());
		}
	}
	
	/**
	 * @param element
	 */
	public void register(String name) {
		names.add(name.toUpperCase());		
	}
	
	/**
	 * @param element
	 */
	public String getUniqueName(String oldName) {		
		String name = oldName;
		int i = 0;
		while(names.contains(name.toUpperCase())) {
			i++;
			name = oldName + "_" + i;		//$NON-NLS-1$ 
		}			
		return name;
	}
	
	public void ensureUnique(MethodElement element) {
		String name = getUniqueName(element.getName());
		if (! name.equals(element.getName())) {
			element.setName(name);		
		}
		register(name);
	}
	
	
}
