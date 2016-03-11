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

import java.util.Map;

import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;

/**
 * realized feature value for a to-one opposite feature
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class ToOneOppositeFeatureValue extends ToOneFeatureValue {

	/**
	 * constructor
	 * 
	 * @param element
	 * @param feature
	 * @param realizer
	 */
	public ToOneOppositeFeatureValue(MethodElement element, OppositeFeature feature, ElementRealizer realizer) {
		super(element, null, feature, realizer);
	}
	
	/**
	 * @see org.eclipse.epf.library.configuration.ToOneFeatureValue#add(VariabilityElement, Object)
	 */
	public void add(VariabilityElement owner, Object value) {
		if ( (value == null) || !(value instanceof MethodElement) ) {	
			return;
		}
		
		// Replace does not completely remove outgoing
		// associations
		// if the opposite feature value has replacer in the
		// configuration
		// it's outgoing associations (i.e., this element) will be
		// replaced by the replacing element
		// as a result, the opposite feature value should drop the
		// replaced element
		//
		// for example, R1 -> responsible for A1, R2 responsible for A2
		// if R2 replaces R1, then R2 still responsible for A2
		// but A1 does not have a responsible role (not R2)
		// so for A1's responsible role opposite feature,
		// the value R1 should be dropped instead of realize to R2
		// Jinhua Xi, 10/27/2005
		MethodElement e = (MethodElement) value;
		boolean isValueReplaced = (e instanceof VariabilityElement)
				&& (ConfigurationHelper.getReplacer((VariabilityElement) e, realizer.getConfiguration()) != null);
		if (!isValueReplaced) {
			// contributor can't contribute 0..1 reference if base
			// already has one,
			// or if more than one contributor has the value.
			// for example, if the base task has no discipline, and only
			// one of it's contributors has discipline,
			// then that contributor's discipline will be used,
			// otherwise, no discipline

			// right now, we can't determine if there is more then one
			// contributors has the value
			// so we ignore contributor's 01 imcoming value
			
			// (Submit): Inability to add responsible for relationship to a work product
			// need to fix this issue. always take the first one.
			e = ConfigurationHelper.getCalculatedElement(e, realizer);
			if ( e != null ) {
				Object key = (owner==null) ? element : owner;
				if ( !valueMap.containsKey(key) ) {
					valueMap.put(key, e);
				}
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
				v = entry.getValue();
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
	
}
