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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.Process;

/**
 * realized feature value for a toMany opposite feature
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class ToManyOppositeFeatureValue extends ToManyFeatureValue {
	
	/**
	 * @see construct a feature value object for to-many opposite feature
	 * 
	 * @param element
	 * @param feature
	 * @param realizer
	 */
	public ToManyOppositeFeatureValue(MethodElement element, OppositeFeature feature, ElementRealizer realizer) {
		super(element, null, feature, realizer);
	}

	

	/**
	 * @see org.eclipse.epf.library.configuration.ToManyFeatureValue#add(VariabilityElement, Object)
	 */
	public void add(VariabilityElement owner, Object value) {
		
		// work around for opposite feature value that still return a single value instead of a list
		if ( value instanceof MethodElement ) {
			ArrayList v = new ArrayList();
			v.add(value);
			value = v;
		}
		
		if ( !(value instanceof List) ) {	
			return;
		}
			
		OppositeFeature of = (OppositeFeature)feature;
		EStructuralFeature f = of.getTargetFeature();

		for (Iterator it = ((List) value).iterator(); it.hasNext();) {
			MethodElement e = (MethodElement) it.next();
			
			//A replaced process should not be shown as a process usage 
			if (of == AssociationHelper.Task_TaskDescriptors && e instanceof TaskDescriptor) {
				TaskDescriptor td = (TaskDescriptor) e;				
				Process proc = ProcessUtil.getProcess(td.getSuperActivities());
				MethodElement realized = ConfigurationHelper.getCalculatedElement(proc, getRealizer());
				if (realized != proc) {
					continue;
				}
			}

			// Replace does not completely remove
			// outgoing associations
			// if the opposite feature value has replacer in the
			// configuration
			// it's outgoing associations (i.e., this element) will be
			// replaced by the replacing element
			// as a result, the opposite feature value should drop the
			// replaced element
			//
			// for example, R1 -> responsible for A1, R2 responsible for
			// A2
			// if R2 replaces R1, then R2 still responsible for A2
			// but A1 does not have a responsible role (not R2)
			// so for A1's responsible role opposite feature,
			// the value R1 should be dropped instead of realize to R2
			// Jinhua Xi, 10/27/2005
			
			// check the replacer is not enough. If the base element is replaced, 
			// all the contributors to the base should also be considered as replaced.
			// so realize the element, see if it is a replacer
			// Bug 199686 - Replacing roles does not update responsible work product's pages
			VariabilityElement replacer = null;
			if ( e instanceof VariabilityElement ) {
				//replacer = ConfigurationHelper.getReplacer((VariabilityElement) e, realizer.getConfiguration());
				replacer = (VariabilityElement)ConfigurationHelper.getCalculatedElement(e, realizer);
				if ( (replacer == e) || !ConfigurationHelper.isReplacer(replacer) || 
						ConfigurationHelper.contrubuteChain((VariabilityElement) e, (VariabilityElement)replacer)) {					
					replacer = null;
				}
				
			}
			boolean isValueReplaced = (replacer != null);

			boolean keep = !isValueReplaced;
			if ( isValueReplaced && 
					(ConfigurationHelper.isExtendReplacer(replacer) 
					|| ElementRealizer.isExtendReplaceEnabled()) ) {
				// if the value is replaced, but the out going feature is inherited by the replacer
				// then we should keep this value, so we need to calculate the feature value 
				// of the replacer
				if ( f.isMany() ) {
					List items = ConfigurationHelper.calc0nFeatureValue(replacer, f, realizer);
					keep = items.contains(owner);
				} else {
					MethodElement item = ConfigurationHelper.calc01FeatureValue(replacer, f, realizer);
					keep = (item == owner);
				}
			}
			
			// if the value's feature to this element is to-one, 
			// then this value's feature value should be the element,
			// otherwise drop the value
			// for example, 
			// T1 -> R1 (primary performaer)
			// T2 -> R2
			// T2 contributes to T1
			// since T1 already has R1, so R2 is droped
			// so when calculating R2's performing tasks, T1 should not be included
			if ( keep ) {
				MethodElement ve = ConfigurationHelper.getCalculatedElement(e, realizer);
				if ( ve != null ) {
					
					// check if this is a to-one feature, currently only Task->Primary Role and TD -> Primary RD
					EStructuralFeature f1 = ConfigurationHelper.get01Feature(of);
					if ( f1 != null ) {
						MethodElement item = ConfigurationHelper.calc01FeatureValue(ve, f1, realizer);
						
						// if the value element's to-one feature value item is not the current element, 
						// then the element's opposite feature value should not contain this value.
						// say, the value element is a task and the current element is a role,
						// then if the task's primary performer (item above) is not this role (the current element), 
						// then the role's performing tasks (the opposite feature value) 
						// should not contain this task (the value, ve above)
						if ( item != element) {
							keep = false;
						}
					}
				}
			}
			
			
			if ( keep ) {
					
				MethodElement ce = ConfigurationHelper.getCalculatedElement(e, realizer);

				// calculated element can be null if it can't show
				if (ce != null && !values.contains(ce)) {
					values.add(ce);
				}
			}
			
			
			// if the element e has extenders and the extender's feature is the owner element, 
			// then we also need to include the extender
			processExtenders(e, f);
		}	
	}
	
	private void processExtenders(MethodElement e, EStructuralFeature f) {
		if ( !(e instanceof VariabilityElement) ) {
			return;
		}
		
		VariabilityElement ve = (VariabilityElement)e;
		
		List items = ConfigurationHelper.getExtenders( ve, realizer.getConfiguration());
		if ( items.size() > 0 ) {		
			for (Iterator it = items.iterator(); it.hasNext();) {
				MethodElement me = (MethodElement) it.next();
				if (ConfigurationHelper.getCalculatedElement(me, realizer) == null) {
					continue;
				}
				boolean keep = false;
				if ( f.isMany() ) {
					List vx = ConfigurationHelper.calc0nFeatureValue(me, f, realizer);
					// if the value contains the owner element, then include it
					keep = (vx != null) && vx.contains(super.element);
				} else {
					MethodElement v = ConfigurationHelper.calc01FeatureValue(me, f, realizer);
					
					// if the feature value is the owner element, then this value should be included
					keep = (v == super.element);
				}
				
				if (keep && !super.values.contains(me) ) {
					super.values.add(me);
					processExtenders(me, f);
				}
			}
		}
		
		// if the element is a contributor or replacer, also need to check the extenders of the base element
		if ( ConfigurationHelper.isContributor(ve) || ConfigurationHelper.isReplacer(ve) ) {
			processExtenders(ve.getVariabilityBasedOnElement(), f);
		}
	}
}
