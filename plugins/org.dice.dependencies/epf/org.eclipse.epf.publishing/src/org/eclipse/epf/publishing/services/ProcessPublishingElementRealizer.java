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
package org.eclipse.epf.publishing.services;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;


/**
 * ElementRealizer for process publishing.
 * Realizes the element based on the configuration, realize options, and the process closure.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ProcessPublishingElementRealizer extends PublishingElementRealizer {
	
	/**
	 * constructor
	 * 
	 * @param config MethodConfiguration
	 * @param validator ProcessPublishingContentValidator
	 */
	protected ProcessPublishingElementRealizer(MethodConfiguration config, ProcessPublishingContentValidator validator) {
		super(config, validator);
	}


	public static final ElementRealizer newProcessPublishingElementRealizer(MethodConfiguration config,
			ProcessPublishingContentValidator validator) {
		ProcessPublishingElementRealizer realizer = (ProcessPublishingElementRealizer) createElementRealizerExtension(config, ProcessPublishingElementRealizer_Type);
		if (realizer == null) {
			realizer = new ProcessPublishingElementRealizer(config, null);
		}
		realizer.validator = validator;
		
		return realizer;
	}
	
	/**
	 * realize the element. 
	 * 
	 * @param element MethodElement
	 * @return MethodElement the realized element.
	 */
	public MethodElement realize(MethodElement element) {
		
		return super.realize(element);
	}
	
	/**
	 * realize the list of feature values and returns a new list of values
	 * The new might be a re-sorting of the original list 
	 * or some of the values can be filtered out, depending on the detail implementation
	 * Note: the list value passed in might be updated as well.
	 * @param element MethodElement
	 * @param feature EStructuralFeature
	 * @param values List
	 * @return List
	 */
	public List realize(MethodElement element, 
			EStructuralFeature feature, List values)
	{		
		List items = super.realize(element, feature, values);
	
//		// add the feature references in case the validator need feature-based reference information,
//		// for example, when publishing process, the output WPs in a Task should not be published 
//		// it none of the TaskDescriptors for this task has reference to that WP.
//		if ( element instanceof TaskDescriptor ) {
//			Task t = (Task)realize(((TaskDescriptor)element).getTask());
//			
//			((ProcessPublishingContentValidator)validator).setElementReferences(t, items);
//			
//		}
		
		return items;
	}

}
