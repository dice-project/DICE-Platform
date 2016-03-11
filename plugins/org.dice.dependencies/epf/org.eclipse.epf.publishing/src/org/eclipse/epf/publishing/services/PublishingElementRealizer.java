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
import org.eclipse.epf.library.configuration.DefaultElementRealizer;
import org.eclipse.epf.library.configuration.ElementRealizer;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;


/**
 * Realizer for publishing.
 * Realizes the element based on the configuration and realize options.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class PublishingElementRealizer extends DefaultElementRealizer {

	PublishingContentValidator validator;
	
	/**
	 * constructor
	 * 
	 * @param config MethodConfiguration
	 * @param validator PublishingContentValidator
	 */
	protected PublishingElementRealizer(MethodConfiguration config, PublishingContentValidator validator) {
		super(config);
		this.validator = validator;
	}

	public static final ElementRealizer newPublishingElementRealizer(MethodConfiguration config,
												PublishingContentValidator validator) {
		PublishingElementRealizer realizer = (PublishingElementRealizer) createElementRealizerExtension(config, PublishingElementRealizer_Type);
		if (realizer == null) {
			realizer = new PublishingElementRealizer(config, null);
		}
		realizer.validator = validator;
		
		return realizer;
	}
	
	/**
	 * realize the element
	 * 
	 * @param element MethodElement
	 */
	public MethodElement realize(MethodElement element) {
		
		element = super.realize(element);
		
		if ( element == null ) {
			return null;
		}
		
		// Publishing should not generate links to unpublished categories
		if ( validator.isDiscarded(null, null, element) ) {
			return null;
		}
		
		return element;
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
		if ( values == null || values.size() == 0 ) {
			return values;
		}
		
		List items = super.realize(element, feature, values);
		if ( validator.hasClosure() ) {
			int i = 0;
			while ( i < items.size() ) {
				MethodElement e = (MethodElement)items.get(i);
				if ( validator.isDiscarded(null, feature, e) ) {
					items.remove(i);
				} else {
					i++;
				}
			}
		}
		
		return items;
	}

	
}
