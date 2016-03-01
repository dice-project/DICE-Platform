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
 * list of dependant package elements.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class PackageDependency extends ElementDependency {

	private boolean loaded = false;

	/**
	 * construct a package dependency for the element
	 * @param element
	 */
	public PackageDependency(MethodElement element) {
		super(element);
	}

	/**
	 * set the loaded flag
	 * @param loaded boolean
	 */
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}

	/**
	 * check if is loaded or not
	 * @return boolean
	 */
	public boolean isLoaded() {
		return this.loaded;
	}

	/**
	 * get the reference for an element
	 * @param refElement Object
	 * @param create boolean if true create if not exist.
	 * @return PackageReference
	 */
	public PackageReference getReference(MethodElement refElement, boolean create) {
		PackageReference ref = (PackageReference) super
				.getReference(refElement);
		if (ref == null && create) {
			ref = new PackageReference(element, refElement);
			super.addReference(ref);
		}

		return ref;
	}

	public List getPackageReferences() {
		return super.getReferences();
	}
	
	public List getAllElementReferences() {
		List allRefs = new ArrayList();
		
		for ( Iterator it = getPackageReferences().iterator(); it.hasNext(); ) {
			PackageReference pkgRef = (PackageReference)it.next();
			allRefs.addAll(pkgRef.getReferences());
		}
		
		return allRefs;
	}
	
	/**
	 * remove all references with the given owner element
	 * @param ownerElement Object
	 */
	public void removeReference(MethodElement ownerElement) {
		PackageReference ref;
		for (Iterator it = super.getReferences().iterator(); it.hasNext();) {
			ref = (PackageReference) it.next();
			ref.removeReference(ownerElement);
		}
	}

	/**
	 * debug method to print out the references
	 */
	public void print() {
		super.print();
	}

}
