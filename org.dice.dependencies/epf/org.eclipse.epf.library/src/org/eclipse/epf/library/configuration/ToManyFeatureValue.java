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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;

/**
 * realized feature value for a toMany feature
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class ToManyFeatureValue extends FeatureValue {

	List values = new ArrayList();
	
	/**
	 * @see org.eclipse.epf.library.configuration.FeatureValue#FeatureValue(MethodElement, MethodElement, Object, ElementRealizer)
	 * @param element
	 * @param ownerElement
	 * @param feature
	 * @param realizer
	 */
	public ToManyFeatureValue(MethodElement element, MethodElement ownerElement, Object feature, ElementRealizer realizer) {
		super(element, ownerElement, feature, realizer);
	}

	/**
	 * add a feature value to the value list
	 * @param owner VariabilityElement
	 * @param value Object
	 */
	public void add(VariabilityElement owner, Object value) {
		if ( !(value instanceof List) ) {	
			return;
		}
			
		HashSet seenValues = new HashSet(values);
		for (Iterator it = ((List) value).iterator(); it.hasNext();) {
			Object obj = it.next();
			if ( obj instanceof MethodElement ) {
				MethodElement e = (MethodElement) obj;
				//MethodElement ce = realizer.realize(e);
				MethodElement ce = ConfigurationHelper.getCalculatedElement(e, realizer);
				
				// calculated element can be null if it can't show
				if (ce != null && !seenValues.contains(ce)) {
					values.add(ce);
					seenValues.add(ce);
				}
			}
		}
		
		if ( (owner instanceof ContentElement)
				&& (feature == UmaPackage.eINSTANCE
						.getContentDescription_Sections())) {
			ConfigurationHelper.orderSections((ContentElement) owner, values);
		}
						
	}
	

	/**
	 * get the realized value
	 * @return Object
	 */
	public Object getValue() {		
		if ( isExtendReplaceEnabled() ) {
			List items = new ArrayList();
			
			// remove the _NULL_ item. that is used as an indicator to blank out the base 
			// for the extend-replace
			for (Iterator it = values.iterator(); it.hasNext(); ) {
				Object o = (Object) it.next();
				if ( isBlankIndicator(o) ) {
					continue;
				}
				items.add(o);
			}
			
			return items;
		}
		
		return values;

	}

	/**
	 * get the size of the value list.
	 */
	public int size() {
		return values.size();
	}
}
