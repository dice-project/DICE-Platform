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

import org.eclipse.epf.uma.MethodElement;

/**
 * Holds the a list of package references of one package element it also holds a
 * list of dependant package elements
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ElementDependency {

	// the package element
	protected MethodElement element;

	/**
	 * list of ElementReference objects, each ElementReference object holds a
	 * list of references of the same type
	 */
	protected List references = new ArrayList();

	/**
	 * dependents
	 */
	protected List dependents = new ArrayList();

	protected ElementDependency(MethodElement element) {
		this.element = element;
	}

	/**
	 * get the element
	 * @return Object
	 */
	public MethodElement getElement() {
		return element;
	}

	/**
	 * get a list of elements this element depends on
	 * 
	 * @return List a list of ElementReference objects
	 */
	public List getReferences() {
		return references;
	}

	/**
	 * get the ElementReference with the given refElement that the current
	 * element recerences to.
	 * 
	 * @param RefElement
	 *            the element to which the current element references
	 * @return ElementReference
	 */
	public ElementReference getReference(MethodElement refElement) {
		ElementReference ref;
		Object refEl;
		for (Iterator it = references.iterator(); it.hasNext();) {
			ref = (ElementReference) it.next();
			refEl = ref.getRefElement();
			if (refEl != null && refEl == refElement) {
				return ref;
			}
		}

		return null;
	}

	/**
	 * get the list of elements that depend on this element
	 * @return List
	 */
	public List getDependents() {
		return dependents;
	}

	/**
	 * add a dependent
	 * @param el Object
	 */
	public void addDependent(MethodElement el) {
		if (!dependents.contains(el)) {
			dependents.add(el);
		}
	}

	/**
	 * add a reference
	 * @param ref ElementReference
	 */
	public void addReference(ElementReference ref) {
		references.add(ref);
	}

	/**
	 * print out the references. This is a debugging method.
	 *
	 */
	public void print() {
		for (Iterator it = references.iterator(); it.hasNext();) {
			Object ref = it.next();
			if (ref instanceof PackageReference) {
				((PackageReference) ref).print();
			} else {
				((ElementReference) ref).print();
			}
		}
	}

}
