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

import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.MethodElement;


/**
 * This class is a helper class for unique name and presentation name handling
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class UniqueNamePNameHandler {
	private UniqueNameHandler nameHandler = new UniqueNameHandler();
	private UniqueNameHandler pnameHandler = new UniqueNameHandler();
	
	/**
	 * Empty constructor
	 */
	public UniqueNamePNameHandler(Collection<? extends MethodElement> elements) {
		nameHandler.registerNames(elements);
		pnameHandler.registerPresentationNames(elements);		
	}
	
	/**
	 * @param element
	 */
	public void ensureUnique(DescribableElement element) {
		String name = nameHandler.getUniqueName(element.getName());
		if (! name.equals(element.getName())) {
			element.setName(name);		
		}
		nameHandler.register(name);
		
		String pname = pnameHandler.getUniqueName(element.getPresentationName());
		if (! pname.equals(element.getPresentationName())) {
			element.setPresentationName(pname);		
		}
		pnameHandler.register(pname);
	}
	
}
