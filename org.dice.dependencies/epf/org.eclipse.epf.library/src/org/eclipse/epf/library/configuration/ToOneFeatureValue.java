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

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.VariabilityElement;

/**
 * realized feature value for a to-one feature
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class ToOneFeatureValue extends FeatureValue {

	// map of element and the value
	LinkedHashMap valueMap = new LinkedHashMap();

	/**
	 * construct the object for a to-one feature value
	 * @param element
	 * @param ownerElement
	 * @param feature
	 * @param realizer
	 */
	public ToOneFeatureValue(MethodElement element, MethodElement ownerElement, Object feature, ElementRealizer realizer) {
		super(element, ownerElement, feature, realizer);
	}
	
	/**
	 * @see org.eclipse.epf.library.configuration.FeatureValue#add(VariabilityElement, Object)
	 */
	public void add(VariabilityElement owner, Object value) {
		if ( (value == null) || !(value instanceof MethodElement) ) {	
			return;
		}
		
		// since this is a to one association, if the base of the owner already has one, 
		// no contributing value should be added
		// 172602 - Variability: Primary role missing in the transitive case of contributing tasks
		if ( ConfigurationHelper.isContributor(owner) ) {
			VariabilityElement base = owner.getVariabilityBasedOnElement();
			if ( base != null && valueMap.containsKey(base) ) {
				return;
			}
		}
		
		//MethodElement e = super.realizer.realize((MethodElement)value);
		MethodElement e = ConfigurationHelper.getCalculatedElement((MethodElement)value, super.realizer);
		
		if ( e != null ) {
			Object key = (owner==null) ? element : owner;
			if ( !valueMap.containsKey(key) ) {
				valueMap.put(key, e);
			}
		}			
	}

	/**
	 * @see org.eclipse.epf.library.configuration.FeatureValue#getValue()
	 */
	public Object getValue() {
		
		Object v = null;
		if ( size() == 0 ) {
			return v;
		}
		
		Object[] entries = valueMap.entrySet().toArray();
		if ( size() == 1 ) {
			// if just one entry, return it
			v = ((Map.Entry)entries[0]).getValue();
		} else {
			// if more than one entry, if the first entry is from the element itself, then return the value
			// otherwise, return null
			Map.Entry entry = (Map.Entry)entries[0];
			if ( entry.getKey() == super.element ) {
				v =  entry.getValue();
			}
		}
		
		if ( isExtendReplaceEnabled() ) {
			if ( isBlankIndicator(v) ) {
				// remove the _NULL_ item. that is used as an indicator to blank out the base 
				// for the extend-replace
				v = null;
			}
		}
		
		return v;
	}

	public int size() {
		return valueMap.size();
	}

}
