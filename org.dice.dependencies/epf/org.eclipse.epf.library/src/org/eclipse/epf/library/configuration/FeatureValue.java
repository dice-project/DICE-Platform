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
package org.eclipse.epf.library.configuration;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;

/**
 * Helper class to identify the feature value and the owning element. 
 * this helps to identify where the value is coming from. 
 * For example, the realized value can be from the base element as well as contributors
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public abstract class FeatureValue {
	
	private static final String _NULL_ = "_NULL_"; //$NON-NLS-1$

	protected MethodElement element;  // the element
	protected MethodElement ownerElement;  // the container of the element
	protected Object feature; // EStructrualFeature or Oppositefeature
	protected ElementRealizer realizer = null;
	
	protected static boolean debug = LibraryPlugin.getDefault().isDebugging();

	/**
	 * construct an instance of the object
	 * @param element MethodElement the element that the value is calcuated for. 
	 * @param ownerElement MethodElement the element that owns the value
	 * @param feature Object either EStructuralFeature or OppositeFeature.
	 * @param realizer ElementRealizer
	 */
	public FeatureValue(MethodElement element, MethodElement ownerElement, Object feature, ElementRealizer realizer) {
		this.element = element;
		this.ownerElement = ownerElement;
		this.feature = feature;
		this.realizer = realizer;
	}

	abstract public void add(VariabilityElement owner, Object value);
	abstract public int size();
	abstract public Object getValue();
	
	/**
	 * get the element
	 * @return MethodElement
	 */
	public MethodElement getElement() {
		return element;
	}
	
	/**
	 * get the owner element
	 * @return MethodElement
	 */
	public MethodElement getOwnerElement() {
		return ownerElement;
	}
	
	/**
	 * get the EStructuralFeature. return null if it's not a EStructuralFeature
	 * @return MethodElement
	 */
	public EStructuralFeature getFeature() {
		if ( feature instanceof EStructuralFeature ) {
			return (EStructuralFeature)feature;
		}
		
		return null;
	}
	
	/**
	 * get the OppositeFeature, return null if this is not an OppositeFeature
	 * @return OppositeFeature
	 */
	public OppositeFeature getOppositeFeature() {
		if ( feature instanceof OppositeFeature ) {
			return (OppositeFeature)feature;
		}
		
		return null;
	}
	
	/**
	 * get the realizer
	 * @return ElementRealizer
	 */
	public ElementRealizer getRealizer() {
		return realizer;
	}
	
	public boolean isExtendReplaceEnabled() {
		if ( ElementRealizer.isExtendReplaceEnabled() ) {
			return true;
		}

		MethodElement e = element;
		if ( element instanceof ContentDescription) {
			e = (MethodElement)((ContentDescription)element).eContainer();
			if ( e == null ) {
				e = ownerElement;
			}
		}

		if ( (e instanceof VariabilityElement) && ConfigurationHelper.isExtendReplacer((VariabilityElement)e) ) {
			return true;
		}
			
		return false;
	}
	
	public static boolean isBlankIndicator(Object value) {
		if ( value instanceof String) {
			return _NULL_.equals(value.toString());
		} else if ( value instanceof MethodElement) {
			return _NULL_.equals(((MethodElement)value).getName());
		}
		
		return false;
	}
}
