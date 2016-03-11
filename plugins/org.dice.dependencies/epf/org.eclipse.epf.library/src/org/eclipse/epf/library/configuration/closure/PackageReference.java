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
package org.eclipse.epf.library.configuration.closure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.MethodElement;


/**
 * A package reference holds the reference of the current package element to
 * another package element. If one element in the package references to another
 * element in another package, the this package refereces to that package.
 * Unlike element references, PackageReference hold a one to one relationship
 * between two packages The Packagereference object also hold a list of
 * ElementReferences for between the two packages
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class PackageReference extends ElementReference {
	
	// list of element refereces between the two package
	private List refList = new ArrayList();

	public PackageReference(MethodElement element, MethodElement refElement) {
		super(element, refElement);
	}

	/**
	 * add a new element reference if the reference is not set. call
	 * hasReference(Object element, Object refElement) before calling this
	 * method
	 * 
	 * @param ref
	 */
	public void addReference(ElementReference ref) {
		refList.add(ref);
	}

	/**
	 * get a list of element references among this package reference
	 * 
	 * @return List a list of ElementReference objects
	 */
	public List getReferences() {
		return refList;
	}

	/**
	 * get the element reference in this package reference
	 * 
	 * @param element
	 * @param refElement
	 * @return ElementReference
	 */
	public ElementReference getReference(Object element, Object refElement) {
		for (Iterator it = refList.iterator(); it.hasNext();) {
			ElementReference ref = (ElementReference) it.next();
			if (ref.getElement() == element
					&& ref.getRefElement() == refElement) {
				return ref;
			}
		}

		return null;
	}
	
	/**
	 * check the element reference exists in this package reference
	 * 
	 * @param element
	 * @param refElement
	 * @return
	 */
	public boolean hasReference(MethodElement element, MethodElement refElement) {
		
		return getReference(element, refElement) != null;
	}

//	/**
//	 * check if there is a reference of variability base element
//	 * @return
//	 */
//	public boolean hasBaseReference() {
//		for (Iterator it = refList.iterator(); it.hasNext();) {
//			ElementReference ref = (ElementReference) it.next();
//			if (ref instanceof VariabilityElementReference) {
//				return true;
//			}
//		}
//
//		return false;
//	}

	/**
	 * remove the reference ownerd by the specified element
	 * 
	 * @param ownerElement
	 */
	public void removeReference(MethodElement ownerElement) {
		Object e;
		int i = 0;
		while (i < refList.size()) {
			ElementReference ref = (ElementReference) refList.get(i);
			e = ref.getElement();
			if (e != null && e == ownerElement) {
				refList.remove(i);
			} else {
				i++;
			}
		}
	}

	/**
	 * print out
	 */
	public void print() {
		System.out.println(LibraryUtil.getName(element)
				+ " --> " + LibraryUtil.getName(refElement)); //$NON-NLS-1$

		for (Iterator it = refList.iterator(); it.hasNext();) {
			((ElementReference) it.next()).print();
		}
		System.out.println();

	}

	/**
	 * check if the package reference can be ignored
	 * 
	 */
	public boolean canIgnore() {
		// if the package has no element reference or all element references can
		// be ignored, return true
		// otherwise, false
		List refs = this.getReferences();

		if (refs.size() == 0) {
			return true;
		}

		for (Iterator it = refs.iterator(); it.hasNext();) {
			ElementReference ref = (ElementReference) it.next();
			if (!ref.canIgnore()) {
				return false;
			}
		}

		return true;
	}
	
}
