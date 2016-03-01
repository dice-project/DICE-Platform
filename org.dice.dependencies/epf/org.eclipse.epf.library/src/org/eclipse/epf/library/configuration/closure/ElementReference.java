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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.WorkProduct;


/**
 * This class defines an Element Reference between two elements.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ElementReference {

	MethodElement element, refElement;
	
	// save the features relating to this reference
	private Set<EStructuralFeature> features = new HashSet<EStructuralFeature>();
	

	/**
	 * construct the reference instance
	 * @param element MethodElement the element
	 * @param refElement MethodElement the referenced element
	 */
	public ElementReference(MethodElement element, MethodElement refElement) {
		this.element = element;
		this.refElement = refElement;
	}

	/**
	 * get the element
	 * @return Object
	 */
	public MethodElement getElement() {
		return element;
	}

	/**
	 * get the referenced element
	 * @return Object
	 */
	public MethodElement getRefElement() {
		return refElement;
	}

	public void addFeature(EStructuralFeature feature) {
		if ( !features.contains(feature) ) {
			features.add(feature);
		}
	}
	
	public boolean hasFeature(EStructuralFeature feature) {
		return features.contains(feature);
	}
	
	/**
	 * debugging method to print out the relationship
	 *
	 */
	public void print() {
		System.out
				.println("    " + LibraryUtil.getName(element) + " --> " + LibraryUtil.getName(refElement)); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * check if the reference can be ignored
	 * 
	 * @return boolean
	 */
	public boolean canIgnore() {
		// if the element has no container, it's a deleted element, ignore it
		if ((element instanceof EObject)
				&& ((EObject) element).eContainer() == null
				|| (refElement instanceof EObject)
				&& ((EObject) refElement).eContainer() == null) {
			return true;
		}

		// Don't warn on optional inputs not being present
		// so added the canIgnore() method
		if ((element instanceof Task) && (refElement instanceof WorkProduct)) {
			// if it's a mandatory input, can ignore
			if (((Task) element).getMandatoryInput().contains(refElement)) {
				return false;
			}

			// not mandatory, but optional, ok, ignore it
			if (((Task) element).getOptionalInput().contains(refElement)) {
				return true;
			}
		}

		// role's modifies feature is actually a dereived opposite feature,
		// it's value can be an element not visible to this plugin
		// ignore it
		// Invalid configuration dependency error reported
		if ((element instanceof Role) && (refElement instanceof WorkProduct)) {
			Role r = (Role) element;
			if (r.getModifies().contains(refElement)
					&& !r.getResponsibleFor().contains(refElement)) {
				return true;
			}
		}

		return false;
	}

	
	public EStructuralFeature getSingleFeature() {
		if (features != null && features.size() == 1) {
			for (EStructuralFeature f: features) {
				return f;
			}
		}		
		return null;
	}
	
}
