//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.layout.BrowsingLayoutSettings;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.FulfillableElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.Scope;

/**
 * Realizes the element based on the configuration and realize options.
 * 
 * @author Jinhua Xi
 * @author Weiping Lu
 * @since 1.0
 */
public abstract class ElementRealizer {

	protected MethodConfiguration config;

	// note: discard contributor takes higher priority, if set to true,
	// resolveContributor will be ignored
	private boolean discardContributor = false;

	private boolean resolveContributor = true;

	private boolean resolveReplacer = true;

	private boolean showSubtracted = false;
	
	protected IFilter filter = null;
	
	// this is the default setting based on the preference setting
	// subclass should implement their own method the get the value
	private static boolean defaultEnableExtendReplace = false;
	
	
	//private static boolean defaultIgnoreBaseToManyAssociations = false;

	static {
		String str = LibraryPlugin.getDefault().getString("EnableExtendReplace"); //$NON-NLS-1$
		if ( str != null && str.trim().equals("true") ) { //$NON-NLS-1$
			defaultEnableExtendReplace = true;
		}
		
		// 171882 - Extends-Replace Variability
		//defaultIgnoreBaseToManyAssociations = LibraryPreferences.getUseNewExtendsSemantics();		
	}
	
	/**
	 * construct an instance with the give configuration
	 * @param config MethodConfiguration
	 */
	public ElementRealizer(MethodConfiguration config) {
		this(config, true, true);
	}

	/**
	 * construct an instance with the given configuration and additional realization options.
	 * 
	 * @param config MethodConfiguration
	 * @param resolveContributor boolean if true, contrubutors from feature value list will be resolved. default to false.
	 * @param resolveReplacer boolean if ture, element with a replacer will be resolved to the replacer. default to true.
	 */
	public ElementRealizer(MethodConfiguration config,
			boolean resolveContributor, boolean resolveReplacer) {
		this.config = config;
		this.resolveContributor = resolveContributor;
		this.resolveReplacer = resolveReplacer;
		
	}

	/**
	 * is extend-replace enabled for replacement
	 * 
	 * @return boolean
	 */
	public static boolean isExtendReplaceEnabled() {
		return defaultEnableExtendReplace;
	}
	
	/**
	 * get the global setting for the extend semantics
	 * @return
	 */
	public static boolean ignoreBaseToManyAssociations() {
		return BrowsingLayoutSettings.INSTANCE.isUseNewExtendSemantics();
	}
	
	
	/**
	 * set the flag. if true, the contributors from a realized feature list will be discarded
	 * 
	 * @param discardContributor boolean
	 */
	public void setDiscardContributor(boolean discardContributor) {
		this.discardContributor = discardContributor;
	}

	/**
	 * set the flag. if true the contributors will be resolved.
	 * @param resolveContributor boolean
	 */
	public void setResolveContributor(boolean resolveContributor) {
		this.resolveContributor = resolveContributor;
	}

	/**
	 * set the flag. if true the element with a replacer will be resolved to the replacer.
	 * 
	 * @param resolveReplacer boolean
	 */
	public void setResolveReplacer(boolean resolveReplacer) {
		this.resolveReplacer = resolveReplacer;
	}

	public boolean showSubtracted() {
		return showSubtracted;
	}
	
	public void setShowSubtracted(boolean flag) {
		this.showSubtracted = flag;
	}
	

	/**
	 * set a filter for this realizer
	 * @param filter IFilter
	 */
	public void setFilter(IFilter filter) {
		this.filter = filter;
	}
	
	/**
	 * get tyhe configuration
	 * @return
	 */
	public MethodConfiguration getConfiguration() {
		return config;
	}
	
	/**
	 * realize the element
	 * @param element MethodElement
	 * @return MethodElement
	 */
	
	public MethodElement realize(MethodElement element) {
		MethodElement result = realize_(element);
		if (result == null && element instanceof VariabilityElement) {
			VariabilityElement ve = (VariabilityElement) element;
			if (ve.getVariabilityType() == VariabilityType.REPLACES) {
				if (ve.getVariabilityBasedOnElement() != null) {
					return realize(ve.getVariabilityBasedOnElement());
				}
			}
		}		
		return result;
	}
	
	private MethodElement realize_(MethodElement element) {
		if (getConfiguration() instanceof Scope) {			
			if (element instanceof VariabilityElement) {
				VariabilityElement ve = (VariabilityElement) element;
				while(ve.getVariabilityType() == VariabilityType.CONTRIBUTES) {
					VariabilityElement base = ve.getVariabilityBasedOnElement();
					if (base == null) {
						break;
					}
					ve = base;
				}
				element = ve;
			}
			if (element == null || !inConfig(element)) {
				return null;
			}
			return element;
		}
		
		if (element == null || !inConfig(element)) {
			return null;
		}

		// Work product descriptors that point to work products
		// outside the configuration are still published
		// linked element must be in config as well
		MethodElement linkedElement = null;
		if (element instanceof TaskDescriptor) {
			linkedElement = ((TaskDescriptor) element).getTask();
		} else if (element instanceof WorkProductDescriptor) {
			linkedElement = ((WorkProductDescriptor) element).getWorkProduct();
		} else if (element instanceof RoleDescriptor) {
			linkedElement = ((RoleDescriptor) element).getRole();
		}

		if ((linkedElement != null)
				&& !inConfig(linkedElement)) {
			return null;
		}

		// if no configuration is specified, don't calculate
		if (config == null) {
			return element;
		}

		if (element instanceof VariabilityElement) {
			VariabilityElement ve = (VariabilityElement) element;
			VariabilityElement e;

			// if discardContributor set to true, discard the contributor and
			// return null
			if (discardContributor && ConfigurationHelper.isContributor(ve)) {
				return null;
			}

			if (resolveContributor) {
				// if the element is a contributor, resovle to it's base
				while (ConfigurationHelper.isContributor(ve)) {
					e = ve.getVariabilityBasedOnElement();
					if (inConfig(e)) {
						ve = e;
					} else {
						// if the base is not in the configuration, it's an
						// error
						System.out
								.println("Configuration closure error: Base element '" + LibraryUtil.getTypeName(ve) + "' not in configuration"); //$NON-NLS-1$ //$NON-NLS-2$
						break;
					}
				}
			}

			if (resolveReplacer) {
				e = ConfigurationHelper.getReplacer(ve, config);
				if (e != null) {
					return e;
				}
			} else if (!inConfig(ve)) {
				return null;
			}
			
			// if the element is a replacer, and the base is a contributor, 
			// need to resolve the element to the base if resolveContributor is true
			// 152230 - Browsing: Role<-->WP relationship shows inconsistancy
			e = ve;
			while ((e != null) && (ConfigurationHelper.isReplacer(e)
					|| ConfigurationHelper.isExtendReplacer(e))) {
				e = (VariabilityElement) e.getVariabilityBasedOnElement();
				if (ConfigurationHelper.isContributor(e)) {
					return realize(e);
				}
			}
			
			// can't return here, need to check canShow
			// return ve;
			if (canShow(ve)) {
				return ve;
			}

			return null;
		}

		if (canShow(element)) {
			return element;
		}

		return null;
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
	public abstract List realize(MethodElement element,
			EStructuralFeature feature, List values);
	
	/**
	 * 
	 * @param element
	 * @return boolean
	 */
	public boolean inConfig(MethodElement element) {
		return ConfigurationHelper.inConfig(element, config, !showSubtracted());
	}
	
	/**
	 * 
	 * @param element
	 * @return boolean
	 */
	public boolean canShow(MethodElement element) {
		return ConfigurationHelper.canShow(element, config, !showSubtracted());
	}
	
	public void dispose() {
		this.filter = null;
	}
	
	protected void addExtraFeatureValues(MethodElement element, EStructuralFeature feature,
			FeatureValue values) {
		if (!ElementRealizer.toAddExtraFeatureValues(element, feature, values,
				getConfiguration())) {
			return;
		}
		
		CustomCategory cc = (CustomCategory) element;

		Collection<?> result = LibraryUtil.getIncludedElements(cc, getConfiguration());

		if (result == null || result.isEmpty()) {
			return;
		}

		ToManyFeatureValue manyValues = values instanceof ToManyFeatureValue ?
				(ToManyFeatureValue) values : null;
				
		List oldValues = manyValues == null ? null : (List) manyValues.getValue();

		HashSet<DescribableElement> seenSet = manyValues == null ?
				new HashSet<DescribableElement>() : new HashSet<DescribableElement>(oldValues);	
		seenSet.add(cc);
		List ccList = AssociationHelper.getCustomCategories(cc);
		seenSet.addAll(ccList);					
				
		List<DescribableElement> list = calculateList(result, seenSet);
		values.add(cc, list);
		
	}

	public List<DescribableElement> calculateList(Collection<?> result,
			Set<DescribableElement> seenSet) {
		Set resultSet = null;
		List<DescribableElement> list = new ArrayList<DescribableElement>();
		for (Iterator<?> iter = result.iterator(); iter.hasNext();) {
			Object o = iter.next();
			if (o instanceof DescribableElement) {
				DescribableElement contElem0 = (DescribableElement) o;
				DescribableElement contElem = (DescribableElement) ConfigurationHelper
						.getCalculatedElement(contElem0, this);
				if (contElem != null) {
					if (seenSet.add(contElem)) {
						boolean toAdd = true;
						if (contElem0 instanceof VariabilityElement) {
							VariabilityElement ve = (VariabilityElement) contElem0;
							VariabilityElement base = ve.getVariabilityBasedOnElement();
							if (base != null && ve.getVariabilityType() == VariabilityType.CONTRIBUTES) {
								if (resultSet == null) {
									resultSet = new HashSet<Object>(result);
								}
								if (! resultSet.contains(contElem)) {
									toAdd = false;
								}									
							}
						}
						if (toAdd) {
							list.add(contElem);
						}
					}
				}
			}
		}
		
		if (list.size() > 1) {
			Comparator comparator = PresentationContext.INSTANCE.getPresNameComparator();
			Collections.<DescribableElement>sort(list, comparator);
		}
		
		return list;
	}
	
	protected boolean slotMatching(FulfillableElement slot, FulfillableElement element) {		
		return true;
	}

	protected static boolean toAddExtraFeatureValues(MethodElement element,
			EStructuralFeature feature, FeatureValue values,
			MethodConfiguration config) {
		if (config == null) {
			return false;
		}

		if (!(values instanceof ToManyFeatureValue)) {
			return false;
		}

		if (!(element instanceof CustomCategory)) {
			return false;
		}

		if (feature != UmaPackage.eINSTANCE
				.getCustomCategory_CategorizedElements()) {
			return false;
		}

		return true;
	}
	
}
