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
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.edit.util.ExtensionManager;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.Whitepaper;

/**
 * Default implementation of the realizer.
 * Realizes the element based on the configuration and realize options.
 * 
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * @author Weiping Lu
 * @since 1.0
 */
public class DefaultElementRealizer extends ElementRealizer {
	
	public static final String DefaultElementRealizer_Type = "DefaultElementRealizer"; //$NON-NLS-1$
	public static final String PublishingElementRealizer_Type = "PublishingElementRealizer"; //$NON-NLS-1$
	public static final String ProcessPublishingElementRealizer_Type = "ProcessPublishingElementRealizer"; //$NON-NLS-1$
		
	protected static final ElementRealizer createElementRealizerExtension(MethodConfiguration config, String type) {
		Object ext = ExtensionManager.createExtension(LibraryPlugin.getDefault().getId(), "elementRealizerFactory"); //$NON-NLS-1$
		return ext instanceof IElementRealizerFactory ?
			((IElementRealizerFactory) ext).createRealizer(config, type) : null;
	}
	
	public static final ElementRealizer newElementRealizer(
			MethodConfiguration config, boolean resolveContributor,
			boolean resolveReplacer) {
		ElementRealizer realizer = createElementRealizerExtension(config, DefaultElementRealizer_Type);
		if (realizer == null) {
			realizer = new DefaultElementRealizer(config);
		}
		realizer.setResolveContributor(resolveContributor);
		realizer.setResolveReplacer(resolveReplacer);
		return realizer;
	}
	
	public static final ElementRealizer newElementRealizer(MethodConfiguration config) {
		return newElementRealizer(config, true, true);
	}
	
	/**
	 * construct an instance with the give configuration
	 * @param config MethodConfiguration
	 */
	protected DefaultElementRealizer(MethodConfiguration config) {
		super(config);
	}

	/**
	 * construct an instance with the given configuration and additional realization options.
	 * 
	 * @param config MethodConfiguration
	 * @param resolveContributor boolean if true, contrubutors from feature value list will be resolved. default to false.
	 * @param resolveReplacer boolean if ture, element with a replacer will be resolved to the replacer. default to true.
	 */
	protected DefaultElementRealizer(MethodConfiguration config,
			boolean resolveContributor, boolean resolveReplacer) {
		super(config, resolveContributor, resolveReplacer);
	}
	
	/**
	 * @see ElementRealizer.realize
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
		if ( values == null || values.size() == 0 ) {
			return values;
		}
		
		// Wrong "modifies" information published in team allocation view for small configuration
		// added filter to clean up un-wanted items
		if ( super.filter != null ) {
			int i = 0;
			while (i < values.size()) {
				MethodElement o = (MethodElement) values.get(i);

				if ( !filter.accept(o) ) {
					values.remove(i);
				} else {
					i++;
				}
			}
		}
				
		// if the feature value is containment element such as artifact
		// the child element can't show if any of the parent(s) are in the list
		// 00384619 - Published site: Display of WPs under responsible role
		
		// this is not a general rule. only apply to the published navigation tree.
		// so move this logic to the tree generation code in publishing
		// geenrally, show all elements, see bug
		// 00386765 - Role responsibilities need to show (contributed) sub-artifacts	
//		if (feature.isMany() && values.size() > 0
//				&& ConfigurationHelper.isContainmentElement(values.get(0))) {
//			int i = 0;
//			while (i < values.size()) {
//				MethodElement o = (MethodElement) values.get(i);
//
//				// if the container of the element is in the list, remove this
//				// element from the list
//				if (ConfigurationHelper.isContainerInList(o, values, config)) {
//					values.remove(i);
//				} else {
//					i++;
//				}
//			}
//		}

		// need to sort the concept and papers by type
		if ((feature == UmaPackage.eINSTANCE
				.getContentElement_ConceptsAndPapers())
				&& (values.size() > 0)) {
			List papers = new ArrayList();
			int i = 0;
			while (i < values.size()) {
				Object o = values.get(i);
				if (o instanceof Whitepaper) {
					papers.add(o);
					values.remove(i);
				} else {
					i++;
				}
			}

			if (papers.size() > 0) {
				values.addAll(papers);
			}
		}
		
		return values;
	}
}
