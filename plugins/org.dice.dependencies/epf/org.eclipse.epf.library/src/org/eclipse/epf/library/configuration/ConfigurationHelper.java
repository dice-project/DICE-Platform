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

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.ConfigHelperDelegate;
import org.eclipse.epf.library.LibraryPlugin;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.meta.TypeDefUtil;
import org.eclipse.epf.library.edit.util.CategorySortHelper;
import org.eclipse.epf.library.edit.util.MethodElementPropUtil;
import org.eclipse.epf.library.edit.util.PracticePropUtil;
import org.eclipse.epf.library.edit.util.PropUtil;
import org.eclipse.epf.library.edit.util.SectionList;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ResourceHelper;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.FulfillableElement;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleDescriptor;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.epf.uma.ecore.impl.MultiResourceEObject;
import org.eclipse.epf.uma.ecore.util.OppositeFeature;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.Scope;
import org.eclipse.epf.uma.util.UserDefinedTypeMeta;


/**
 * @author Jinhua Xi
 * @author Phong Nguyen Le
 * @author Weiping Lu
 * @since 1.0
 */
public class ConfigurationHelper {
	
	public static boolean serverMode = false;
	private static ConfigHelperDelegate delegate;

	static {
		ConfigHelperDelegate extendedDelegate = (ConfigHelperDelegate) ExtensionHelper
				.getExtension(LibraryPlugin.getDefault().getId(),
						"configHelperDelegateExt");//$NON-NLS-1$
		if (extendedDelegate == null) {
			delegate = new ConfigHelperDelegate();
		} else {
			delegate = extendedDelegate;
		}
	}
	
	public static ConfigHelperDelegate getDelegate() {
		return delegate;
	}
	
	private static boolean inheritingSlotFeatures = false; 
	
	public static final String ATTRIBUTE_VALUE_SEPERATOR = "<p/>"; //$NON-NLS-1$

	private static boolean debug = LibraryPlugin.getDefault().isDebugging();

	/**
	 * check if the element is a ContentDescription
	 * 
	 * @param element {@link MethodElement}
	 * @return boolean
	 */
	public static boolean isDescriptionElement(MethodElement element) {
		if ( element == null ) {
			return false;
		}
		
		return (element instanceof ContentDescription || element.eContainer() instanceof ContentDescription);

	}

	/**
	 * check if the method pacj=kage is a global package
	 * 
	 * @param pkg {@link MethodPackage}
	 * @return boolean
	 */
	public static boolean isGlobalPackage(MethodPackage pkg) {
		if (pkg == null) {
			if (debug) {
				System.out
						.println("ConfigurationHelper.isGlobalPackage: method package is null"); //$NON-NLS-1$
			}
			return false;
		}

		MethodPlugin p = LibraryUtil.getMethodPlugin(pkg);
		if (p == null) {
			if (debug) {
				System.out
						.println("ConfigurationHelper.isGlobalPackage: Unable to find method plug-in for " + pkg.getName() + ": " + pkg.getGuid()); //$NON-NLS-1$ //$NON-NLS-2$
			}
			return false;
		}

		return getDelegate().isSystemPackage(p, pkg);
	}

	/**
	 * check if the element is in the configuration
	 * 
	 * @param element
	 * @param config
	 * @return
	 */
	public static boolean inConfig(MethodElement element,
			MethodConfiguration config) {
		return inConfig(element, config, true);
	}
	
	
	/**
	 * check if the element is in the configuration
	 * 
	 * @param element
	 * @param config
	 * @return
	 */
	public static boolean inConfig(MethodElement element,
			MethodConfiguration config, boolean checkSubtracted) {
		return inConfig(element, config, checkSubtracted, true);
	}
	
	/**
	 * check if the element is in the configuration
	 * 
	 * @param element
	 * @param config
	 * @return
	 */
	public static boolean inConfig(MethodElement element,
			MethodConfiguration config, boolean checkSubtracted, boolean checkBase) {
		if (!isOwnerSelected(element, config, checkSubtracted)) {
			return false;
		}

		//Bug 207429 - Configration: Warring info should shown when deselect the replaced element
		// for configuration closure checking, the missing base should be reported 
		// so the element been checked still treated as in config
		// added the checkBase flag to ignore the base checking
		// for configuration realization, this flag should be set to true
		// so the replacer missing base is not included in the config.
		
		// if the element is a repalcer, and it's base element has more than one
		// replacer
		// none of the replacers should be included into the configuration
		if (checkBase && (element instanceof VariabilityElement) ) {
			VariabilityElement ve = (VariabilityElement) element;
			if (isReplacer(ve)) {
				VariabilityElement base = ve.getVariabilityBasedOnElement();
				if (inConfig(base, config)) {
					
//					// Invalid hotspot created for locally replaced activity in activity diagram
//					// this is because the activity has more than one local replacers
//					// in version 7.0, local replacement is modeled the same way as nomal replacement
//					// we need to ignore this checking for activity
//					if ( element instanceof Activity ) {
//						return true;
//					}
					
					for (Iterator it = AssociationHelper.getImmediateVarieties(
							base).iterator(); it.hasNext();) {
						VariabilityElement e = (VariabilityElement) it.next();
						if ((e != element)
								&& (e.getVariabilityType() == VariabilityType.REPLACES)
								&& isOwnerSelected(e, config, checkSubtracted)) {
							if (debug) {
								System.out
										.println("ConfigurationHelper.inConfig: Ignoring replacing element '" + LibraryUtil.getTypeName(element) + "' since its base element has more than one replacer in the configuration"); //$NON-NLS-1$ //$NON-NLS-2$
							}
							return false;
						}
					}
				} else {
					return false;  // base must be in the configuration
				}
			}
		}

		return true;
	}

	private static boolean isOwnerSelected(MethodElement element,
			MethodConfiguration config, boolean checkSubtracted) {
		return getDelegate().isOwnerSelected(element, config, checkSubtracted);
	}

	/**
	 * is the element a contributor?
	 * @param element
	 * @return boolean
	 */
	public static boolean isContributor(VariabilityElement element) {
		if (element == null || element.getVariabilityBasedOnElement() == null)
			return false;

		return element.getVariabilityType() == VariabilityType.CONTRIBUTES;
	}

	/**
	 * is the element a replacer?
	 * @param element
	 * @return boolean
	 */
	public static boolean isReplacer(VariabilityElement element) {
		if (element == null || element.getVariabilityBasedOnElement() == null)
			return false;

		return element.getVariabilityType() == VariabilityType.REPLACES;
	}
	
	/**
	 * is "b" an ancestor of "a" through a "contribute chain" relationship
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean contrubuteChain(VariabilityElement a, VariabilityElement b) {	
		VariabilityElement element = a;
		while (element != null) {
			if (element.getVariabilityType() != VariabilityType.CONTRIBUTES) {
				return false;
			}
			element = element.getVariabilityBasedOnElement();
			if (element == null) {
				return false;
			}
			if (element == b) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * is the element a extend-replacer?
	 * @param element
	 * @return boolean
	 */
	public static boolean isExtendReplacer(VariabilityElement element) {
		if (element == null || element.getVariabilityBasedOnElement() == null)
			return false;

		return element.getVariabilityType() == VariabilityType.EXTENDS_REPLACES;
	}
	
	/**
	 * is the element an extender?
	 * @param element
	 * @return boolean
	 */
	public static boolean isExtender(VariabilityElement element) {
		if (element == null || element.getVariabilityBasedOnElement() == null)
			return false;

		return element.getVariabilityType() == VariabilityType.EXTENDS;
	}

	/**
	 * get the replacer of the element in the configuration. Only one replacer
	 * is allowed. If more than one replacer is found, then none of them will be
	 * returned.
	 * 
	 * @param element
	 *            VariabilityElement the element
	 * @param config
	 *            MethodConfiguration
	 * @return VariabilityElement the replacer if there is one and ONLY one,
	 *         null otherwise
	 */
	public static VariabilityElement getReplacer(VariabilityElement element,
			MethodConfiguration config) {
		VariabilityElement ve = null;

		// this will get all replacers recursively. we only need the immediate
		// ones
		// for(Iterator iterator = TngUtil.getGeneralizers(element,
		// VariabilityType.REPLACES_LITERAL); iterator.hasNext();)
		for (Iterator it = AssociationHelper.getImmediateVarieties(element)
				.iterator(); it.hasNext();) {
			VariabilityElement e = (VariabilityElement) it.next();
			if ( e == null || !inConfig(e, config) ) {
				continue;
			}

			VariabilityType type = e.getVariabilityType();
			if ( type == VariabilityType.REPLACES 
					|| type == VariabilityType.EXTENDS_REPLACES) {
				if (ve != null) {
					if (debug) {
						System.out
								.println("ConfigurationHelper.getReplacer: Replacer ignored for element '" + LibraryUtil.getTypeName(element) + "' since it has more than one replacerin the configuration"); //$NON-NLS-1$ //$NON-NLS-2$
					}
					return null; // if more than one replacer, return null
				}

				ve = e;
			}
		}

		return ve;
	}

	/**
	 * get the immediate contributors of the element within the configuration.
	 * If a contributor has immediate replacer, it is repalced with the replacer
	 * 
	 * @param element
	 * @param config
	 * @return
	 */
	public static List getContributors(VariabilityElement element,
			MethodConfiguration config) {
		List items = new ArrayList();

		if ( element == null ) {
			return items;
		}
		
		// This method get all contributors recursively,
		// we only need the first level
		// for (Iterator it = TngUtil.getContributors(element); it.hasNext(); )
		for (Iterator it = AssociationHelper.getImmediateVarieties(element)
				.iterator(); it.hasNext();) {
			VariabilityElement e = (VariabilityElement) it.next();
			if ((e != null)
					&& (e.getVariabilityType() == VariabilityType.CONTRIBUTES)
					&& inConfig(e, config)) {
				VariabilityElement replacer = getReplacer(e, config);
				if (replacer != null) {
					items.add(replacer);
				} else {
					items.add(e);
				}
			}
		}

		if (element instanceof Activity) {
			Comparator<MethodElement> comparator = getContributorComparator(items);
			Collections.sort(items, comparator);
		}		
		
		return items;
	}

	private static Comparator<MethodElement> getContributorComparator(List<MethodElement> contributors) {
		for (MethodElement contributor : contributors) {			
			if (! isRefineRuleSyntax(contributor)) {				
				Comparator<MethodElement> comparator = new Comparator<MethodElement>() {

					public int compare(MethodElement object1, MethodElement object2) {
						PropUtil propUtil = PropUtil.getPropUtil();
						String v1 = propUtil.getContributionOrder(object1);
						if (v1 == null) {
							v1 = "";//$NON-NLS-1$
						}
						String v2 =  propUtil.getContributionOrder(object2);
						if (v2 == null) {
							v2 = "";//$NON-NLS-1$
						}
						if (v1.length() == 0) {
							return v2.length() == 0 ? 0 : 1;
						} else if (v2.length() == 0) {
							return v1.length() == 0 ? 0 : -1;	
						}
						return v1.compareTo(v2);
					}

				    public boolean equals(Object object) {
				    	return this == object;
				    }
				};
				
				return comparator;
			}			
		}
		
		Comparator<MethodElement> comparator = new Comparator<MethodElement>() {

			public int compare(MethodElement object1, MethodElement object2) {
				PropUtil propUtil = PropUtil.getPropUtil();
				String v1 = propUtil.getContributionOrder(object1);
				if (v1 == null) {
					v1 = "";//$NON-NLS-1$
				}
				String v2 =  propUtil.getContributionOrder(object2);
				if (v2 == null) {
					v2 = "";//$NON-NLS-1$
				}
				if (v1.length() == 0) {
					return v2.length() == 0 ? 0 : 1;
				} else if (v2.length() == 0) {
					return v1.length() == 0 ? 0 : -1;	
				}
				
				String[] str1 = v1.split("\\.");	//$NON-NLS-1$
				String[] str2 = v2.split("\\.");	//$NON-NLS-1$
				int sz = Math.min(str1.length, str2.length);
				for (int i = 0; i < sz; i++) {
					int n1 = 0;
					int n2 = 0;
					try {
						n1 = Integer.parseInt(str1[i]);
					} catch (Exception e) {
					}
					try {
						n2 = Integer.parseInt(str2[i]);
					} catch (Exception e) {						
					}
					if (n1 != n2) {
						return n1 < n2 ? -1 : 1; 
					}
				}
				if (str1.length == str2.length ) {
					return 0;
				}
				return str1.length < str2.length ? -1 : 1;
			}

		    public boolean equals(Object object) {
		    	return this == object;
		    }
		};
				
		return comparator;
	}

	private static boolean isRefineRuleSyntax(MethodElement contributor) {
		PropUtil propUtil = PropUtil.getPropUtil();
		String value = propUtil.getContributionOrder(contributor);		
		if (value == null || value.length() == 0) {
			return true;
		}
		boolean dotIsAllowed = false;
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			if (c == '.') {
				if (!dotIsAllowed) {
					return false;
				}
				dotIsAllowed = false;
				
			} else if ('9' >= c && c >= '0') {				
				dotIsAllowed = true;
				
			} else {
				return false;
			}			
		}
		return true;
	}
	
	public static List getExtenders(VariabilityElement element,
			MethodConfiguration config) {
		List items = new ArrayList();

		if ( element == null ) {
			return items;
		}
		
		for (Iterator it = AssociationHelper.getImmediateVarieties(element)
				.iterator(); it.hasNext();) {
			VariabilityElement e = (VariabilityElement) it.next();
			if ((e != null)
					&& (e.getVariabilityType() == VariabilityType.EXTENDS)
					&& inConfig(e, config)) {
					items.add(e);
			}
		}

		return items;
	}
	
	public static Set<VariabilityElement> getLocalContributersAndReplacers(VariabilityElement element,
			MethodConfiguration config) {
		Set<VariabilityElement> items = new HashSet<VariabilityElement>();

		if ( element == null ) {
			return items;
		}
		
		for (Iterator it = AssociationHelper.getImmediateVarieties(element)
				.iterator(); it.hasNext();) {
			VariabilityElement e = (VariabilityElement) it.next();
			if ((e != null)
					&& (isLocalContributerOrReplacer(e))
					&& inConfig(e, config)) {
					items.add(e);
			}
		}

		return items;
	}
	
	private static boolean isLocalContributerOrReplacer(
			VariabilityElement ve) {
		VariabilityType type = ve.getVariabilityType();
		return type == VariabilityType.LOCAL_CONTRIBUTION
				|| type == VariabilityType.LOCAL_REPLACEMENT;
	}
	
	public static boolean canShow(MethodElement element,
			MethodConfiguration config) {
		return canShow(element,config, true);
	}
	

	/**
	 * element can't show in the configuration tree if 1. the element is not in
	 * the configuration 2. the element is a contribution to another element 3.
	 * if the element is a replacer to a 3. the element has a replacement
	 * element in the configuration
	 * 
	 * @param element
	 * @param config
	 * @return boolean
	 */
	public static boolean canShow(MethodElement element,
			MethodConfiguration config, boolean checkSubtracted) {
		if (element == null) {
			return false;
		}

		if (!inConfig(element, config, checkSubtracted)) {
			return false;
		}

//		// if it;s an activity, return since contributors are local contribution and needs to be shown
//		if (element instanceof Activity) {
//			return true;
//		}

//		/////////////////////////////////////////////////////////////////////////////////////////////////
//		// this is not needed any more since the suppression state is determined by the activity adaptor factory
//		// just leave it here for now since it does not hurt. take it away in next release
		// 
		// NO, we still need to keep this to check the suppression of descriptors referenced by another descriptor. 
		// for example, when displaying a TD page, we need to handle the suppression of the referenced WPDs and RDs. 
		// 00395278 - Browsing TD with suppressed RD/WPD under it  -- suppressed ones still showing
		// Note: this does not handle the case when the descriptor is with an inherited activity. 
		// in that case, the suppression need to be determined by the activity path. TODO
		Boolean supressed = element.getSuppressed();
		if (supressed != null && supressed.booleanValue() == true) {
			return false;
		}
//		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		if ( element instanceof VariabilityElement) {
			if ( !canShowByCheckingVE((VariabilityElement) element,config) ) {
				return  false;
			}
		}

		return true;
	}
	
	/**
	 * 
	 * @param e
	 * @param config
	 * @return
	 */
	public static boolean canShowByCheckingVE(VariabilityElement e, MethodConfiguration config){		

		// if this is an extender, always show, even though it extends a
		// contributor
		if (isExtender(e)) {
			return true;
		}

		if (isContributor(e) || getReplacer(e, config) != null) {
			return false;
		}

		// for activity, don't show the ones that contains contributors 
		// the resaon for this is to simplify the logic.
		// assuming that user uses this activity to contribute to other activities
		// so that part should not be involved in the navigation
		// 
		// Jinhua Xi, 07/14/06, 
		if ( (e instanceof Activity) && hasContributor((Activity)e) ) {
			return false;
		}

		while ((e != null) && (isReplacer(e) || isExtendReplacer(e))) {
			e = (VariabilityElement) e.getVariabilityBasedOnElement();
			if (isContributor(e)) {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * get the name of the element in the configuration. 1. if it's a
	 * contributor, show the name of the base element 2. if it has a
	 * replacemenet, show the name of the replacement element
	 * 
	 * @param element
	 * @param config
	 * @return String
	 */
	public static String getName(MethodElement element,
			MethodConfiguration config) {
		if (element instanceof VariabilityElement) {
			VariabilityElement e = (VariabilityElement) element;
			if (isContributor(e)) {
				return getName(e.getVariabilityBasedOnElement(), config);
			} else {
				VariabilityElement rep = getReplacer(e, config);
				if (rep != null) {
					return getName(rep, config);
				}
			}
		}

		return element.getName();
	}

	/**
	 * get the presentation name for an element in the configuration
	 * @param element
	 * @param config
	 * @return String
	 */
	public static String getPresentationName(MethodElement element,
			MethodConfiguration config) {
		
		// [Bug 196399] P-name is not inherited from its base element in an extending element
		String name = null;

		name = element.getPresentationName();
		if ( StrUtil.isBlank(name) && (element instanceof VariabilityElement) ) {
			EStructuralFeature f = UmaPackage.eINSTANCE.getVariabilityElement_VariabilityBasedOnElement();
			ElementRealizer r = DefaultElementRealizer.newElementRealizer(config);	
			MethodElement me = element;

			Set<MethodElement> seens = new HashSet<MethodElement>();
			do {
				VariabilityElement oldMe = (VariabilityElement) me;
				me = (DescribableElement)ConfigurationHelper.calc01FeatureValue(me, f, r);
				if ( me == null ) {
					break;
				} else if (oldMe == me || seens.contains(me)) {
					me = oldMe.getVariabilityBasedOnElement();
					if (me == null) {
						break;
					}
				}					
				name = ((DescribableElement)me).getPresentationName();
				if (seens.contains(me)) {	//to prevent loop in case such as											
					break;					//both extend-replacer and base have empty pres name
				}
				seens.add(oldMe);
				seens.add(me);
			} while ( StrUtil.isBlank(name) );
		}
		
		if ( StrUtil.isBlank(name) ) {
			name = TngUtil.getPresentationName(element);
		}

		return name;
	}

	/**
	 * get the variability element owner. If the element is a
	 * VariabilityElement, return itself otherwise, find it's owner. For
	 * example, a ContentDescriotion object is ownered by a VariabilityElement
	 * 
	 * @param e
	 *            MethodElement
	 * @return VariabilityElement
	 */
	public static VariabilityElement getVariableOwner(MethodElement e) {
		if (e instanceof VariabilityElement) {
			return (VariabilityElement) e;
		} else if (e instanceof ContentDescription) {
			// return AssociationHelper.getOwner( (ContentDescription)e );
			EObject eObj = e.eContainer();
			if (eObj instanceof VariabilityElement) {
				return (VariabilityElement) eObj;
			}
		}

		return null;
	}

	/**
	 * check if the feature value is mergable or not
	 * @param feature
	 * @return boolean
	 */
	public static boolean isMergableAttribute(EStructuralFeature feature) {
		if (!feature.getEType().getInstanceClassName().equals(
				"java.lang.String")) //$NON-NLS-1$
		{
			return false;
		}

		// feature id is not globally unique, can't do a switch here
		// compare the acture featrue instead
		/*
		 * switch ( feature.getFeatureID() ) { case
		 * UmaPackage.METHOD_ELEMENT__GUID: case
		 * UmaPackage.METHOD_ELEMENT__NAME:
		 * Contributing a Unique ID for work product renders
		 * bad html // don't merge Unique ID: case
		 * UmaPackage.WORK_PRODUCT_DESCRIPTION__EXTERNAL_ID: return false; }
		 */

		if (feature == UmaPackage.eINSTANCE.getMethodElement_Guid()
				|| feature == UmaPackage.eINSTANCE.getNamedElement_Name()
				|| feature == UmaPackage.eINSTANCE
						.getContentDescription_ExternalId() 
				|| feature == UmaPackage.eINSTANCE.getMethodElement_PresentationName()) {
			return false;
		}

		return true;
	}

	/**
	 * is attribute feature value?
	 * @param feature
	 * @return boolean
	 */
	public static boolean isAttributeFeature(EStructuralFeature feature) {
		return (feature.getEType() instanceof EAttribute);
	}
	
	/**
	 * is this a to-one feature?
	 * @param feature
	 * @return boolean
	 */
	public static boolean is01Feature(EStructuralFeature feature) {
		return (feature.getEType() instanceof EClass) && !feature.isMany();
	}

	/**
	 * is this a to-many feature?
	 * @param feature
	 * @return boolean
	 */
	public static boolean is0nFeature(EStructuralFeature feature) {
		return (feature.getEType() instanceof EClass) && feature.isMany();
	}

	
	/**
	 * calculate the value of the specified element and feature
	 * 
	 * @param element
	 * @param feature
	 * @param config
	 * @param values
	 *            The List of values of the feature. if the feature is a 0..1
	 *            association, the List holds a single value if any for
	 *            reference list feature, it's a list of references for
	 *            attribute feature, it's a list of attribute values to be
	 *            mergered for 0..1 association, the list may contain 1 value.
	 */
//	private static void calculateFeature(MethodElement element,
//			EStructuralFeature feature, MethodConfiguration config,
//			List values, ElementRealizer realizer) {
//		calculateFeature(element, null, feature, config, values, realizer);
//	}

	private static void calculateFeature(MethodElement element,
			MethodElement OwnerElement, EStructuralFeature feature,
			MethodConfiguration config, FeatureValue values, ElementRealizer realizer) {
		
		TypeDefUtil typeDefUtil = TypeDefUtil.getInstance();
		
		// make sure this is a valid feature 
		// for example, if an activity contributes to a Capability Pattern,
		// some CapabilityPattern specific feature may not be a valid feature for the Activity
//		List features = element.getInstanceProperties();
		List features = LibraryUtil.getStructuralFeatures(element, true);
		if ( !features.contains(feature)) {
			if (element instanceof Practice) {
				PracticePropUtil practicePropUtil = PracticePropUtil.getPracticePropUtil();
				Practice practice = (Practice) element;
				UserDefinedTypeMeta meta = practicePropUtil.getUdtMeta(practice);
				if (meta == null || ! meta.isQualifiedRefernce((EReference) feature)) {
					return;
				}
			} else {	
				return;
			}
		}
		
		// EClassifier type = feature.getEType();
		VariabilityElement ve = getVariableOwner((OwnerElement == null) ? element
				: OwnerElement);

//		Object value = element.eGet(feature);
		Object value = typeDefUtil.eGet(element, feature);

		values.add(ve, value);

		if (config == null || config instanceof Scope) {
			return;
		}

		// realize the variability relationship
		if (ve == null) {
			return;
		}
		
		// according to Peter, the realization should be always top down.
		// i.e realize the base first, then include the contributions
		__mergeBase(element, ve, feature, config, values, realizer);
		
		if (element instanceof FulfillableElement) {
			if (feature != UmaPackage.eINSTANCE
					.getFulfillableElement_Fulfills()) {
				mergeSlotFeatureValues((FulfillableElement) element, feature,
						config, values, realizer);
			}
		} else if (OwnerElement instanceof FulfillableElement) {
			mergeSlotFeatureValues(element, (FulfillableElement) OwnerElement,
					feature, config, values, realizer);	
		}
		
		if (realizer != null) {
			realizer.addExtraFeatureValues(element, feature, values);
		}
		
		if (!is01Feature(feature) || (values.size() == 0) ) {
			__mergeContributors(element, ve, feature, config, values, realizer);
		}
	}
	
//	public static List<FulfillableElement> calcFulfillableElement_Fulfills(FulfillableElement element,
//			MethodConfiguration config);
	
	//Change the signature and use a try/catch block in case there is any regression with some corner case.
	public static List<FulfillableElement> calcFulfillableElement_Fulfills(FulfillableElement element,
			ElementRealizer realizer) {
		try {
			return calcFulfillableElement_Fulfills_(element, realizer);
		} catch (Exception e) {
			return Collections.EMPTY_LIST;
		}
	}
	
	private static List<FulfillableElement> calcFulfillableElement_Fulfills_(FulfillableElement element,
					ElementRealizer realizer) {
		List<FulfillableElement> resultList = new ArrayList<FulfillableElement>();
		
		MethodConfiguration config = realizer.getConfiguration();
//		ElementRealizer realizer = DefaultElementRealizer.newElementRealizer(config);
		Object fullfillsObj = calc0nFeatureValue(element,
				UmaPackage.eINSTANCE.getFulfillableElement_Fulfills(),
				realizer);	
		
		if (! (fullfillsObj instanceof List)) {
			return resultList;
		}

		EStructuralFeature feature = UmaPackage.eINSTANCE.getFulfillableElement_Fulfills();
		for (FulfillableElement slot : (List<FulfillableElement>) fullfillsObj) {
			slot = (FulfillableElement) getCalculatedElement(slot, config);
			if (slotMatching(slot, element, realizer)) {
				resultList.add(slot);
			}
		}
		
		if (resultList.size() > 1) {
			Comparator comparator = PresentationContext.INSTANCE.getPresNameComparator();
			Collections.<FulfillableElement>sort(resultList, comparator);
		}
		
		return resultList;
	}
	
	private static void mergeSlotFeatureValues(FulfillableElement element,
			EStructuralFeature feature, MethodConfiguration config,
			FeatureValue values, ElementRealizer realizer) {
		if (!inheritingSlotFeatures) {
			return;
		}
		
		if (feature == UmaPackage.eINSTANCE.getFulfillableElement_Fulfills()) {			
			return;
		}
		if (!(element instanceof WorkProduct)) {
			return;
		}
		if (values.size() > 0) {
			return;
		}
		
		List<FulfillableElement> slots = calcFulfillableElement_Fulfills(element, realizer);

		for (FulfillableElement slot : slots) {
			if (slot instanceof WorkProduct) {
				slot = (FulfillableElement) getCalculatedElement(slot, config);
				calculateFeature(slot, null, feature, config, values,
						realizer);
			}
		}
	}
	
	private static void mergeSlotFeatureValues(MethodElement element, FulfillableElement OwnerElement,
			EStructuralFeature feature, MethodConfiguration config,
			FeatureValue values, ElementRealizer realizer) {
		if (!inheritingSlotFeatures) {
			return;
		}
		
		if (! (element instanceof ContentDescription)) {
			return;
		}
		
		if (!(OwnerElement instanceof WorkProduct)) {
			return;
		}
		if (values.size() > 0) {
			return;
		}
		
		List<FulfillableElement> slots = calcFulfillableElement_Fulfills(OwnerElement, realizer);

		for (FulfillableElement slot : slots) {
			if (slot instanceof WorkProduct) {
				slot = (FulfillableElement) getCalculatedElement(slot, config);
				ContentDescription slotOwnedElement = slot.getPresentation();				
				calculateFeature(slotOwnedElement, slot, feature, config, values,
						realizer);
			}
		}
	}
	
	private static void mergeSlotOppositeFeatureValues(FulfillableElement element,
			OppositeFeature feature, MethodConfiguration config,
			FeatureValue values, ElementRealizer realizer) {
		if (!inheritingSlotFeatures) {
			return;
		}
		
		if (feature == AssociationHelper.FulFills_FullFillableElements) {
			return;
		}
		
		if (!(element instanceof WorkProduct)) {
			return;
		}
		if (values.size() > 0) {
			return;
		}
		
		List<FulfillableElement> slots = calcFulfillableElement_Fulfills(element, realizer);

		for (FulfillableElement slot : slots) {
			slot = (FulfillableElement) getCalculatedElement(slot, config);
			calculateOppositeFeature(slot, feature, realizer, values);
		}
	}
	
	public static List<FulfillableElement> calcFulfills_FulfillableElement(
			FulfillableElement slot, MethodConfiguration config) {
		List<FulfillableElement> resultList = new ArrayList<FulfillableElement>();

		ElementRealizer realizer = DefaultElementRealizer
				.newElementRealizer(config);

		List fulfillingList = calc0nFeatureValue(slot, AssociationHelper.FulFills_FullFillableElements, realizer);

		for (FulfillableElement element : (List<FulfillableElement>) fulfillingList) {
			element = (FulfillableElement) getCalculatedElement(element, config);
			if (slotMatching(slot, element, realizer)) {
				resultList.add(element);
			}
		}
		
		if (resultList.size() > 1) {
			Comparator comparator = PresentationContext.INSTANCE.getPresNameComparator();
			Collections.<FulfillableElement>sort(resultList, comparator);
		}
		
		return resultList;
	}		
	
	private static boolean slotMatching(FulfillableElement slot,
			FulfillableElement element, ElementRealizer realizer) {
		if (slot == null || !slot.getIsAbstract()) {
			return false;
		}		

		if (realizer != null) {	
			return realizer.slotMatching(slot, element);
		}

		return true;
	}
	
	private static void __mergeBase(MethodElement element,
			VariabilityElement ve, EStructuralFeature feature,
			MethodConfiguration config, FeatureValue values, ElementRealizer realizer) {
	
		// if the element is an extended element, get the base element's
		// properties if needed
		boolean extendReplace = isExtendReplacer(ve) || 
				ElementRealizer.isExtendReplaceEnabled() && isReplacer(ve);
		boolean isExtender = isExtender(ve);
		
		if (extendReplace && PropUtil.getPropUtil().isCustomize(ve)) {
			return;
		}
		
		if (isExtender || extendReplace) {
			boolean mergebase = false;
			if (is0nFeature(feature)) {
				mergebase = true;
				if ( extendReplace 
						|| isExtender && ElementRealizer.ignoreBaseToManyAssociations()) {
					mergebase = (values.size() == 0);
				}
			} else if (is01Feature(feature)) {
				mergebase = (values.size() == 0);
			} else {
				mergebase = (isMergableAttribute(feature) && (values.size() == 0));
			}

			if (mergebase) {
				// Authoring: Extending a base that has been
				// replaced, does not extend the replacement
				// need to get the realized element,
				// the base element might be replaced by another one,
				// or might be a contributor to another base
				MethodElement e = ve.getVariabilityBasedOnElement();
				if ( !extendReplace ) {
					e= getCalculatedElement(e, config);
				}					
				MethodElement o = e;

				// if it's a containment feature, such as sub-artifacts
				// the base should not be the container
				// 162154 - Check circular references with parent-/sub-artifacts and practices/sub-practices				
				if ( isContainmentFeature(feature) ) {
					List containers = getContainers(ve, config);
					if (containers.contains(e) ) {
						mergebase = false;
					}
				}

				if (mergebase && (ve != e) && inConfig(e, config)) {
					// if the current element is a description,
					// get the the description object of the base
					if (element instanceof ContentDescription) {
						try {
							e = ((DescribableElement) e).getPresentation();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}

					calculateFeature(e, o, feature, config, values, realizer);

					// for extender, we need to re-sort the steps based on the
					// extender defined order
					if ((ve instanceof ContentElement)
							&& (feature == UmaPackage.eINSTANCE
									.getContentDescription_Sections())) {
						orderSections((ContentElement) ve, (List)values.getValue());
					}
				}
			}
		}
	
	}

	private static void __mergeContributors(MethodElement element,
			VariabilityElement ve, EStructuralFeature feature,
			MethodConfiguration config, FeatureValue values, ElementRealizer realizer) {
		boolean mergeable = true;
		if ( isAttributeFeature(feature) && !isMergableAttribute(feature) ) {
			mergeable = false;
		} 
		
		if ( mergeable ) {
			// if the element has contributors in the configuration, get the
			// reference properties
			// if a contributor has replacer, it's replacer is used
			List items = getContributors(ve, config);
			if (items != null && items.size() > 0) {
				for (Iterator it = items.iterator(); it.hasNext();) {
					MethodElement e = (MethodElement) it.next();
					MethodElement o = e;  // the owner

					// if the current element is a description,
					// get the the description object of the contributor
					if (element instanceof ContentDescription) {
						e = ((DescribableElement) e).getPresentation();
					}
					calculateFeature(e, o, feature, config, values, realizer);
				}
			}
		}
		
	}
	
	/**
	 * calculate the reflist for the specified element and OppositeFeature
	 * 
	 * @param element
	 * @param feature
	 *            OppositeFeature
	 * @param realizer ElementRealizer
	 * @param values
	 *            The List of values of the feature. for reference list feature,
	 *            it's a list of references for attribute feature, it's a list
	 *            of attribute values to be mergered
	 */
	private static void calculateOppositeFeature(MethodElement element,
			OppositeFeature feature, ElementRealizer realizer, FeatureValue values) {
		calculateOppositeFeature(element, feature, true, false, realizer, values);
	}
	
	
	/**
	 * calculate the reflist for the specified element and OppositeFeature
	 * 
	 * @param element
	 * @param feature
	 *            OppositeFeature
	 * @param realizer ElementRealizer
	 * @param values
	 *            The List of values of the feature. for reference list feature,
	 *            it's a list of references for attribute feature, it's a list
	 *            of attribute values to be mergered
	 */
	private static void calculateOppositeFeature(MethodElement element,
			OppositeFeature feature, boolean mergeReplacerBase, boolean mergeExtenderBase, 
			ElementRealizer realizer, FeatureValue values) {
		// must be a MultiResourceEObject
		if (!(element instanceof MultiResourceEObject)) {
			return;
		}

		MethodConfiguration config = realizer.getConfiguration();

		VariabilityElement ve = getVariableOwner(element);

		Object value = ((MultiResourceEObject) element)
				.getOppositeFeatureValue(feature);
		values.add(ve, value);

		if (element instanceof VariabilityElement && config != null) {
			VariabilityElement ce = (VariabilityElement) element;

			// if the element has contributors in the configuration, get the
			// reference properties
			List items = getContributors(ce, config);
			if (items != null && items.size() > 0) {
				for (Iterator it = items.iterator(); it.hasNext();) {
					MethodElement e = (MethodElement) it.next();
					calculateOppositeFeature(e, feature, mergeReplacerBase, mergeExtenderBase, realizer, values);
				}
			}

			// if the element is a replacer of a base element, 
			// get the base element's properties
			// only cover 01 or 0m references
			// attributes can't be opposite feature

			// replaced element does not show incoming
			// relationships
			// imcoming relationships are represented by opposite feature
			// so keep the references to the base element
			
			// Inconsistency between Published Work Product: Workload Analysis Page and Referenced Pages
			// this is from the base of the extender. 
			// incoming associations from the base should be discarded for extenders
			boolean mergebase = false;
			boolean isExtender = isExtender(ce);
			boolean isReplacer = isReplacer(ce);
			boolean isExtendReplacer = isExtendReplacer(ce) || 
				ElementRealizer.isExtendReplaceEnabled() && isReplacer;
			
//			//This is a conservative fix for "Extends-replace displine rendered wrong in conf tree view"
//			if (isExtendReplacer(ce) &&  realizer.getClass() == DefaultElementRealizer.class) {
//				if (feature == AssociationHelper.Discipline_DisciplineGroupings) {
//					isReplacer = true;
//				}
//				if (feature == AssociationHelper.RoleSet_RoleSetGrouppings) {
//					isReplacer = true;
//				}				
//			}
			
			// extenderReplacer behaves like replacer when realizing incoming associations
			// Bug 199694 - Extends-Replace ignores incoming relationships of contributer
			if ( isExtendReplacer ) {
				isReplacer = true;
			}
			
			if (isReplacer) {
				mergebase = mergeReplacerBase && ((value instanceof List) || (values.size() == 0));
			} else if (isExtender) {
				mergebase = mergeExtenderBase;
			}

			if (mergebase) {
				// resolve the base contributor to the base if i am not a
				// replacer
				// don't resolve the contributors to the base if i am a
				// replacer,
				// the call is coming from the base. So if resolve to base again
				// will cause deadlock.
				// don't resolve to the replacer since we need to carry down all
				// the incoming 0n associations from base
				// actually should resolve to replacer if i am not a replacer
				// (i.e. i am an entender)
				// this handles the senario: G2 replaces G1 and G3 extends G1
				ElementRealizer realizer2 = 
					DefaultElementRealizer.newElementRealizer(config, (!isReplacer), (!isReplacer));

				MethodElement e = getCalculatedElement(ce
						.getVariabilityBasedOnElement(), realizer2);
				
				// if the base element resolved to null, 
				// this is because canShow() don't allow replaced element to show.
				// we don't want to take the risk to fix ElementRealizer
				// so fix here since 7.0.1_ifix1, 
				// Jinhua Xi, 4/17/06
				if ( e == null ) {
					e = ce.getVariabilityBasedOnElement();
				}
				
				if ((e != ce) && inConfig(e, config)) {
					calculateOppositeFeature(e, feature, mergeReplacerBase, mergeExtenderBase, realizer, values);
				}
			}
			
			if (element instanceof FulfillableElement) {
				mergeSlotOppositeFeatureValues((FulfillableElement) element, feature, config, values, realizer);
			}
			
		}
	}

	/**
	 * calculate the elements in the list, and returns a new list of unique
	 * elements
	 * 
	 * @param elements
	 * @param config
	 * @return List
	 */
	public static List getCalculatedElements(List elements,
			MethodConfiguration config) {
		return getCalculatedElements(elements, DefaultElementRealizer.newElementRealizer(config) );
	}
	
	/**
	 * calculate the elements in the list, and returns a new list of unique
	 * elements
	 * 
	 * @param elements
	 * @param realizer ElementRealizer
	 * @return List
	 */
	public static List getCalculatedElements(List elements, ElementRealizer realizer ) {
		// calculate each element in the list and return a new list of elements
		List values = new ArrayList();
		for (Iterator it = elements.iterator(); it.hasNext();) {
			MethodElement e = (MethodElement) it.next();
			e = ConfigurationHelper.getCalculatedElement(e, realizer);
			if ((e != null) && (!values.contains(e))) {
				values.add(e);
			}
		}

		return values;
	}

	/**
	 * calculate the element based on tle configuration. If nothing can be
	 * shown, return null.
	 * 
	 * @param element
	 * @param config
	 * @return
	 */
	public static MethodElement getCalculatedElement(MethodElement element,
			MethodConfiguration config) {
		ElementRealizer realizer = DefaultElementRealizer.newElementRealizer(config, true, true);

		return getCalculatedElement(element, realizer);
	}
	
	public static MethodElement getCalculatedElement(MethodElement element,
			ElementRealizer realizer) {

		MethodElement e = element;
		MethodElement e2;
		while ((e2 = realizer.realize(e)) != e) {
			e = e2;
		}
		return e;
	}

	/**
	 * get the calculated 0..1 feature value of the specipied element and
	 * feature
	 * 
	 * @return MethodElement
	 */
	public static MethodElement calc01FeatureValue(MethodElement element,
			EStructuralFeature feature, ElementRealizer realizer) {
		return calc01FeatureValue(element, null, feature, realizer);
	}
	
	/**
	 * get the calculated 0..1 feature value of the specipied element and
	 * feature
	 * 
	 * @return MethodElement
	 */
	public static MethodElement calc01FeatureValue(MethodElement element, MethodElement ownerElement, 
			EStructuralFeature feature, ElementRealizer realizer) {
		// presentation object feature should never be realized. always keep
		// it's own value
		Object v = element.eGet(feature);
		if (v instanceof ContentDescription) {
			return (MethodElement) v;
		}

		ToOneFeatureValue values = new ToOneFeatureValue(element, ownerElement, feature, realizer);
		calculateFeature(element, ownerElement, feature, realizer.getConfiguration(), values, realizer);
//		if (values.size() > 0) {
//			v = (MethodElement) values.get(0);
//			if (v instanceof MethodElement) {
//				return getCalculatedElement((MethodElement) v, realizer.getConfiguration());
//			}
//		}

		return (MethodElement)values.getValue();
	}


	/**
	 * is this feature for containd children? such as artifact's contained artifact.
	 * @param feature 
	 * @return boolean
	 */
	public static boolean isContainmentFeature(EStructuralFeature feature) {
		return feature == UmaPackage.eINSTANCE.getArtifact_ContainedArtifacts()
				|| feature == UmaPackage.eINSTANCE.getPractice_SubPractices();
	}

	/**
	 * check if the element allow contained element
	 * @param element
	 * @return boolean
	 */
	public static boolean isContainmentElement(Object element) {
		return element instanceof Artifact || element instanceof Practice;
	}

	private static List getContainers(MethodElement element,
			MethodConfiguration config) {
		List items = new ArrayList();

		EObject o = element;
		while ((o != null) && ((o = o.eContainer()) != null)
				&& o.getClass().isInstance(element)) {
			if (o instanceof VariabilityElement) {
				o = getCalculatedElement((VariabilityElement) o, config);
			}

			if ((o != null) && !items.contains(o)) {
				items.add(o);
			}
		}

		return items;
	}

	/**
	 * get the 01 opposite feature (if any) for the given feature. returns null
	 * if no such feature
	 * 
	 * @param feature
	 *            EStructuralFeature
	 * @return OppositeFeature the 01 opposite feature of the given feature,
	 *         null if no such thing
	 */
	public static OppositeFeature get01OppositeFeature(
			EStructuralFeature feature) {
		
		// since 1.0m4, these are not 01 feature any more
//		if (feature == UmaPackage.eINSTANCE.getDiscipline_Tasks()) {
//			return AssociationHelper.Task_Discipline;
//		} else if (feature == UmaPackage.eINSTANCE.getRole_ResponsibleFor()) {
//			return AssociationHelper.WorkProduct_ResponsibleRole;
//		}
		
//		if ( feature == UmaPackage.eINSTANCE.getDomain_WorkProducts() ) {
//			return AssociationHelper.WorkProduct_Domains;
//		} 
		
		return null;
	}


	/**
	 * get the target feature for the opposite feature if the target feature is a to-One feature, 
	 * otherwise, return null.
	 * @param oFeature
	 * @return EStructuralFeature
	 */
	public static EStructuralFeature get01Feature(OppositeFeature oFeature) {		
/*		if ( oFeature == AssociationHelper.Role_Primary_Tasks 
				|| oFeature == AssociationHelper.RoleDescriptor_PrimaryTaskDescriptors ) {
			return oFeature.getTargetFeature();
		}*/
		
		return null;
	}
	
	/**
	 * get the calculated 0..n feature value of the specipied element, it's
	 * owner element and feature
	 * 
	 * @param element {@link MethodElement}
	 * @param feature {@link EStructuralFeature}
	 * @param realizer {@link ElementRealizer}
	 * @return List a list of {@link MethodElement}
	 */
	public static List calc0nFeatureValue(MethodElement element,
			EStructuralFeature feature, ElementRealizer realizer) {
		TypeDefUtil typeDefUtil = TypeDefUtil.getInstance();
		if (realizer == null) {
			Object value = typeDefUtil.eGet(element, feature);
			return value instanceof List ? (List) value : null;
		}
		
		if (realizer != null && realizer.getConfiguration() instanceof Scope) {
//			Object value = element.eGet(feature);
			Object value = typeDefUtil.eGet(element, feature);
			if (value instanceof List) {
				List listValue = new ArrayList((List) value); 
				for (int i = 0; i < listValue.size(); i++) {
					if (listValue.get(i) instanceof MethodElement) {
						MethodElement e0 = (MethodElement) listValue.get(i);
						MethodElement e1 = realizer.realize(e0);
						if (e1 != null && e1 != e0) {
							listValue.set(i, e1);
						}
					} else {
						break;
					}
				}
				return listValue;
			}
			return null;
		}
		return calc0nFeatureValue(element, null, feature, realizer);
	}
	
	/**
	 * get the calculated 0..n feature value of the specipied element and
	 * feature. if the opposite feature on the other end is a to-one feature,
	 * the feature value item can't be in the value list unless it's opposite
	 * feature value is the current element. For example, for discipline_tasks,
	 * the tasks can be selected if and ONLY if the task's task_discipline
	 * opposite feature value is the current discipline
	 * 
	 * @param element MethodElement
	 * @param ownerElement {@link MethodElement}
	 * @param feature {@link EStructuralFeature}
	 * @param realizer {@link ElementRealizer}
	 * @return List a list of {@link MethodElement}
	 */
	public static List calc0nFeatureValue(MethodElement element, MethodElement ownerElement, 
			EStructuralFeature feature, ElementRealizer realizer) {
		List ret = calc0nFeatureValue_(element, ownerElement, feature, realizer);
		if (ret != null && !ret.isEmpty() && (element instanceof Task) && feature == UmaPackage.eINSTANCE.getTask_OptionalInput()) {
			List mInputs = calc0nFeatureValue_(element, ownerElement, UmaPackage.eINSTANCE.getTask_MandatoryInput(), realizer);
			if (mInputs != null && ! mInputs.isEmpty()) {
				ret.removeAll(mInputs);
			}			
		}		
		return ret;
	}
	
	private static List calc0nFeatureValue_(MethodElement element, MethodElement ownerElement, 
			EStructuralFeature feature, ElementRealizer realizer) {
				
		List v = null;
		MethodConfiguration config = realizer.getConfiguration();
		
		// Wrong "modifies" information published in team allocation view for small configuration
		// need to manually calculate the modify features
		// can't rely on the uma model since that is not configuration specific.
		// role modifies product means:
		// role performs on tasks, and tasks produce workproducts as output
		// so handle these scenarios as special cases
		if ( element instanceof Role && feature == UmaPackage.eINSTANCE.getRole_Modifies() ) {
//			List v2 = new ArrayList();
//			calculateOppositeFeature(element, AssociationHelper.Role_Primary_Tasks, realizer, v2);
//			if ( v2.size() > 0 ) {
//				for (Iterator it = v2.iterator(); it.hasNext(); ) {
//					MethodElement e = (MethodElement)it.next();
//					calculateFeature(e, ownerElement, UmaPackage.eINSTANCE.getTask_Output(), config, v, realizer);					
//				}
//			}	
			v = calcModifiedWorkProducts((Role)element, ownerElement, realizer);
		}else if ( element instanceof RoleDescriptor && feature == UmaPackage.eINSTANCE.getRoleDescriptor_Modifies() ) {
//			List v2 = new ArrayList();
//			calculateOppositeFeature(element, AssociationHelper.RoleDescriptor_PrimaryTaskDescriptors, realizer, v2);
//			if ( v2.size() > 0 ) {
//				for (Iterator it = v2.iterator(); it.hasNext(); ) {
//					MethodElement e = (MethodElement)it.next();
//					calculateFeature(e, ownerElement, UmaPackage.eINSTANCE.getTaskDescriptor_Output(), config, v, realizer);					
//				}
//			}
			v = calcModifiedWorkProductDescriptors((RoleDescriptor)element, ownerElement, realizer);
		} else {
			ToManyFeatureValue fv = new ToManyFeatureValue(element, ownerElement, feature, realizer);
			calculateFeature(element, ownerElement, feature, config, fv, realizer);
			v = (List)fv.getValue();
		}
		
		List values = getCalculatedElements(v, realizer);
		if (values.contains(element)) {
			values.remove(element);
		}

		// * if the opposite feature on the other end is a to-one feature,
		// * the feature value item can't be in the value list unless it's
		// opposite feature value is the current element.
		// * For example, for discipline_tasks, the tasks can be selected
		// * if and ONLY if the task's task_discipline opposite feature value is
		// the current discipline
		OppositeFeature of = get01OppositeFeature(feature);
		if (of != null) {
			int i = 0;
			while (i < values.size()) {
				MethodElement o = (MethodElement) values.get(i);

				// calculate it's opposite feature value, the value must be the
				// given element
				// otherwise, remove it
				// note: don't use the current realizer
				// use the default realizer since we need to realize the element in the default way
				// 158924 - wrong categories in configuration view

				
				// workaround to allow show/hide subtracted elements
				ElementRealizer r = DefaultElementRealizer.newElementRealizer(config);
				r.setShowSubtracted(realizer.showSubtracted());
				
				MethodElement oo = calc01FeatureValue(o, of, r);
				if (oo != element) {
					values.remove(i);
				} else {
					i++;
				}
			}
		}

		// containment feature value should not contain the owner element or its
		// parents
		// for example, artifact_containedArtifacts should not be the artifact
		// itself or its parents
		if (isContainmentFeature(feature)) {
			List containers = getContainers(element, config);
			int i = 0;
			while (i < values.size()) {
				Object o = (Object) values.get(i);
				if (o == element || containers.contains(o)) {
					values.remove(i);
				} else {
					i++;
				}
			}
		}
		
		List returnList = realizer.realize(element, feature, values);
		if (CategorySortHelper.needToSort(element, feature)) {
			Map map = MethodElementPropUtil.getMethodElementPropUtil().getExtendedPropertyMap(element, false);
			if (map == null || ! map.containsKey(ConfigurationFilter.isEmptyCheckLock)) {
				returnList = CategorySortHelper.sortCategoryElements(element, returnList.toArray(), true, feature, config);
			}
		}

		// the following part might not be general to all cases
		// so put it into the realizer
		return returnList;
		
//		// if the feature value is containment element such as artifact
//		// the child element can't show if any of the parent(s) are in the list
//		// Published site: Display of WPs under responsible role
//		if (feature.isMany() && values.size() > 0
//				&& isContainmentElement(values.get(0))) {
//			int i = 0;
//			while (i < values.size()) {
//				MethodElement o = (MethodElement) values.get(i);
//
//				// if the container of the element is in the list, remove this
//				// element from the list
//				if (isContainerInList(o, values, config)) {
//					values.remove(i);
//				} else {
//					i++;
//				}
//			}
//		}
//
//		// need to sort the concept and papers by type
//		if ((feature == UmaPackage.eINSTANCE
//				.getContentElement_ConceptsAndPapers())
//				&& (values.size() > 0)) {
//			List papers = new ArrayList();
//			int i = 0;
//			while (i < values.size()) {
//				Object o = values.get(i);
//				if (o instanceof Whitepaper) {
//					papers.add(o);
//					values.remove(i);
//				} else {
//					i++;
//				}
//			}
//
//			if (papers.size() > 0) {
//				values.addAll(papers);
//			}
//		}
//
//		return values;
	}

	/**
	 * check if the container of the element is in the list or not
	 * 
	 * @param element
	 * @param items
	 * @param config
	 * @return
	 */
	public static boolean isContainerInList(MethodElement element, List items,
			MethodConfiguration config) {
		EObject o = element;
		while ((o != null) && ((o = o.eContainer()) != null)
				&& o.getClass().isInstance(element)) {
			if (items.contains(o)) {
				return true;
			}

			if (o instanceof VariabilityElement) {
				o = getCalculatedElement((VariabilityElement) o, config);
			}

			if ((o != null) && items.contains(o)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * get the calculated attribute feature value of the specipied element and
	 * feature
	 * 
	 * @param element {@link MethodElement}
	 * @param feature {@link EStructuralFeature}
	 * @param config {@link MethodConfiguration}
	 * @return Object
	 */
	public static Object calcAttributeFeatureValue(MethodElement element,
			EStructuralFeature feature, MethodConfiguration config) {
		return calcAttributeFeatureValue(element, null, feature, config);
	}

	/**
	 * get the calculated attribute feature value of the specipied element and
	 * feature
	 * 
	 * @param element MethodElement
	 * @param ownerElement {@link MethodElement}
	 * @param feature {@link EStructuralFeature}
	 * @param config {@link MethodConfiguration}
	 * @return Object
	 */
	public static Object calcAttributeFeatureValue(MethodElement element,
			MethodElement ownerElement, EStructuralFeature feature,
			MethodConfiguration config) {
		if (config instanceof Scope) {
//			Object value = element.eGet(feature);
			Object value = TypeDefUtil.getInstance().eGet(element, feature);
			if (value instanceof MethodElement) {
				ElementRealizer realizer = DefaultElementRealizer.newElementRealizer(config);
				value = realizer.realize((MethodElement) value);
			}
			return value;
		}

		//Inherent externalId for a extends and extends-replaces variability element
		if (ownerElement instanceof DescribableElement
				&& ownerElement instanceof VariabilityElement
				&& element instanceof ContentDescription
				&& feature == UmaPackage.eINSTANCE
						.getContentDescription_ExternalId()) {
			VariabilityElement va = (VariabilityElement) ownerElement;
			ContentDescription presentation = (ContentDescription) element;
			while (presentation != null) {
				String extenalId = presentation.getExternalId();
				if (extenalId != null && extenalId.trim().length() > 0) {
					return extenalId;
				}
				presentation = null;
				if (va.getVariabilityType() == VariabilityType.EXTENDS
						|| va.getVariabilityType() == VariabilityType.EXTENDS_REPLACES) {
					va = va.getVariabilityBasedOnElement();
					if (va != null) {
						presentation = ((DescribableElement) va)
								.getPresentation();
					}
				}
			}
		}
		
		if (isMergableAttribute(feature)) {
			// merge the attribute values
			ElementRealizer realizer = DefaultElementRealizer.newElementRealizer(config);
			AttributeFeatureValue values = new AttributeFeatureValue(element, ownerElement, feature, realizer);
			calculateFeature(element, ownerElement, feature, config, values,
						realizer);

//			StringBuffer buffer = new StringBuffer();
//			for (Iterator it = values.iterator(); it.hasNext();) {
//				FeatureValue av = (FeatureValue) it.next();
//				if (av.text == null || av.text.toString().length() == 0) {
//					continue;
//				}
//
//				if (feature == UmaPackage.eINSTANCE
//						.getMethodElement_PresentationName()) {
//					if (values.size() > 1) {
//						// something wrong here, will not happen but put test
//						// message here just in case
//						if (debug) {
//							System.out
//									.println("ConfigurationHelper.calcAttributeFeatureValue: Presentation Name get more then one entry: " + LibraryUtil.getTypeName(element)); //$NON-NLS-1$
//						}
//					}
//					return av.text;
//				}
//
//				if (buffer.length() > 0) {
//					buffer.append(ATTRIBUTE_VALUE_SEPERATOR); 
//				}
//
//				if (av.element == element) {
//					buffer.append(av.text);
//				} else {
//					String contentPath = ResourceHelper
//							.getElementPath((av.element instanceof ContentDescription) ? (MethodElement) av.element
//									.eContainer()
//									: av.element);
//
//					String backPath = ResourceHelper
//							.getBackPath((element instanceof ContentDescription) ? ((ownerElement != null) ? ownerElement
//									: (MethodElement) element.eContainer())
//									: element);
//
//					buffer.append(ResourceHelper.fixContentUrlPath(av.text,
//							contentPath, backPath));
//				}
//			}
//
//			return buffer.toString();
			
			return values.getValue();
		}

//		return element.eGet(feature);
		return TypeDefUtil.getInstance().eGet(element, feature);
	}

	/**
	 * get the calculated 0..n opposite feature value of the specipied element
	 * and opposite feature
	 * 
	 * @param element MethodElement
	 * @param feature {@link EStructuralFeature}
	 * @param realizer {@link ElementRealizer}
	 * @return List a list of {@link MethodElement}
	 */
	public static List calc0nFeatureValue(MethodElement element,
			OppositeFeature feature, ElementRealizer realizer) {
		return calc0nFeatureValue(element, feature, true, false, realizer);
	}

	/**
	 * get the calculated 0..n opposite feature value of the specipied element
	 * and opposite feature
	 * 
	 * @param element MethodElement
	 * @param ownerElement {@link MethodElement}
	 * @param feature {@link OppositeFeature}
	 * @param mergeReplacerBase boolean
	 * @param mergeExtenderBase boolean
	 * @param realizer {@link ElementRealizer}
	 * @return List a list of {@link MethodElement}
	 */
	public static List calc0nFeatureValue(MethodElement element,
			OppositeFeature feature, boolean mergeReplacerBase, boolean mergeExtenderBase, ElementRealizer realizer) {
		ToManyOppositeFeatureValue values = new ToManyOppositeFeatureValue(element, feature, realizer);
		calculateOppositeFeature(element, feature, mergeReplacerBase, mergeExtenderBase, realizer, values);
		List ret = (List)values.getValue();
		if (feature == AssociationHelper.DescribableElement_CustomCategories) {
			Set<CustomCategory> set = ConfigurationHelper.getDelegate().getDynamicCustomCategories(element);
			if (set != null && !set.isEmpty()) {
				set.removeAll(ret);
				if (! set.isEmpty()) {
					ret.addAll(set);
				}
			}
		}				
		return ret;
	}
	
	/**
	 * get the calculated 0..1 feature value of the specipied element and
	 * opposite feature
	 * @param element {@link MethodElement}
	 * @param feature {@link OppositeFeature}
	 * @param realizer {@link ElementRealizer}
	 * 
	 * @return MethodElement
	 */
	public static MethodElement calc01FeatureValue(MethodElement element,
			OppositeFeature feature, ElementRealizer realizer) {
		ToOneOppositeFeatureValue values = new ToOneOppositeFeatureValue(element, feature, realizer);
		calculateOppositeFeature(element, feature, realizer, values);
//		if (values.size() > 0) {
//			return getCalculatedElement((MethodElement) values.get(0), realizer);
//		}
//
//		return null;
		
		return (MethodElement)values.getValue();
		
	}

	/**
	 * order the sections
	 * 
	 * @param element {@link ContentElement}
	 * @param values {@link List} 
	 */
	public static void orderSections(ContentElement element, List values) {
		String orderingGuide = element.getOrderingGuide();
		if (orderingGuide == null || orderingGuide.trim().length() == 0) {
			return;
		}
		SectionList slist = new SectionList(element,
				SectionList.STEPS_FOR_ELEMENT_AND_PARENTS);

		if (isSameList(values, slist)) {
			values.clear();
			values.addAll(slist);
			return;
		}

		// otherwise, need to reorder the list
		OrderedListComparator comp = new OrderedListComparator(new ArrayList(
				values), slist);
		TreeSet s = new TreeSet(comp);
		s.addAll(values);

		values.clear();
		values.addAll(s);
	}

	private static boolean isSameList(List l1, List l2) {
		if (l1.size() != l2.size()) {
			return false;
		}

		for (Iterator it = l1.iterator(); it.hasNext();) {
			Object o = it.next();
			if (!l2.contains(o)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * calculate the copyright of the element int he configuration. Rule: 1.
	 * contributors' copyright statment should be merged to the base 2. extended
	 * element inherites the copyrights from the base 3. replacer don't inherite
	 * the copyrights from the base 4. copyright elements should be unique, no
	 * duplicate entry
	 * 
	 * @param element
	 * @param config
	 * @param values
	 */
	private static void calculateCopyright(MethodElement element,
			MethodConfiguration config, List values) {
		SupportingMaterial copyright = (SupportingMaterial) getCalculatedElement(
				LibraryUtil.getCopyright(element), config);
		if (copyright != null && !values.contains(copyright)) {
			values.add(copyright);
		}

		VariabilityElement ve = getVariableOwner(element);
		if (ve == null) {
			return;
		}

		if (config == null) {
			return;
		}

		// merge copyrights of contributors
		List items = getContributors(ve, config);
		if (items != null && items.size() > 0) {
			for (Iterator it = items.iterator(); it.hasNext();) {
				MethodElement e = (MethodElement) it.next();
				calculateCopyright(e, config, values);
			}
		}

		// if the element is an extended element, get the base element's
		// copyright
		// the base element's copyright should appear first
		if (isExtender(ve)) {
			// realize the base element first.
			MethodElement e = getCalculatedElement(ve
					.getVariabilityBasedOnElement(), config);
			if ((ve != e) && inConfig(e, config)) {
				List baseItems = new ArrayList();
				calculateCopyright(e, config, baseItems);

				// add the base items to the begining
				if (baseItems.size() > 0) {
					// Authoring: Duplicate copyrights when 2
					// elements with the same coyright are extending each other
					// remove the duplicate ones
					int i = 0;
					while (i < values.size()) {
						if (baseItems.contains(values.get(i))) {
							values.remove(i);
						} else {
							i++;
						}
					}

					values.addAll(0, baseItems);
				}
			}
		}

	}

	/**
	 * get the copyright text
	 * 
	 * @param element {@link MethodElement}
	 * @param config {@link MethodConfiguration}
	 * @return String
	 */
	public static String getCopyrightText(MethodElement element,
			MethodConfiguration config) {
		StringBuffer copyrights = new StringBuffer();
		List items = new ArrayList();
		ConfigurationHelper.calculateCopyright(element, config, items);
		if (items.size() > 0) {
			SupportingMaterial copyright;
			for (Iterator it = items.iterator(); it.hasNext();) {
				copyright = (SupportingMaterial) it.next();

				// Contributing support material does not show
				// up in copy right
				// need to resolve the feature value. can't just get the value,
				// it may have contributors
				// String statement =
				// copyright.getPresentation().getMainDescription();
				//											
				String statement = (String) calcAttributeFeatureValue(copyright
						.getPresentation(), copyright, UmaPackage.eINSTANCE
						.getContentDescription_MainDescription(), config);
				if (statement != null && statement.length() > 0) {
					// need to fix the content for relative links.
					// since the link is a relative path to the
					// SupportingMaterial location,
					// need to convert to relative to the current element
					// so re-calcuate the back path
					// jxi, 06/28/05
					String contentPath = ResourceHelper
							.getElementPath(copyright);
					String backPath = ResourceHelper.getBackPath(element);
					statement = ResourceHelper.fixContentUrlPath(statement,
							contentPath, backPath);

					if (copyrights.length() > 0) {
						copyrights.append("<p/>"); //$NON-NLS-1$
					}
					copyrights.append(statement);
				}
			}
		}

		return copyrights.toString();
	}

	/**
	 * Gets String attribute values of an activity
	 * 
	 * @param e Activity or it's ContentDescription
	 * @param attrib
	 * @param config
	 * @return String
	 */
	public static String getActivityStringAttribute(MethodElement e, MethodElement ownerElement, 
			EAttribute attrib, MethodConfiguration config) {
		if(!String.class.isAssignableFrom(attrib.getEAttributeType().getInstanceClass())) {
			throw new IllegalArgumentException("The specified attribute is not of type String: " + attrib); //$NON-NLS-1$
		}
		
		Object str = null;
		boolean isDesc;
		VariabilityElement ve;
		if ( e instanceof ContentDescription )
		{
			ve = (VariabilityElement) e.eContainer();
			if ( ve == null ) {
				ve = (VariabilityElement)ownerElement;
			}
			isDesc = true;
		}
		else if ( e instanceof VariabilityElement)
		{
			ve = (VariabilityElement)e;
			isDesc = false;
		} else {
//			str = e.eGet(attrib);
			str = TypeDefUtil.getInstance().eGet(e, attrib);
			return (str==null) ? "" : str.toString(); //$NON-NLS-1$
		}
		
		
		if(attrib == UmaPackage.eINSTANCE.getMethodElement_Guid() || attrib == UmaPackage.eINSTANCE.getNamedElement_Name()) {
			return (String)e.eGet(attrib);
		}		
					
		VariabilityElement base = ve.getVariabilityBasedOnElement();
		VariabilityType variabilityType = ve.getVariabilityType();
		if( base != null && variabilityType == VariabilityType.LOCAL_CONTRIBUTION) {
			// for local contribution, append the text to the base
			Object strBase;
			if ( isDesc ) {
//				str = ((DescribableElement)ve).getPresentation().eGet(attrib);
				str = TypeDefUtil.getInstance().eGet(((DescribableElement)ve).getPresentation(), attrib);
				strBase = calcAttributeFeatureValue( ((DescribableElement)base).getPresentation(), base, attrib, config);
			} else {
				str = ve.eGet(attrib);
				strBase = calcAttributeFeatureValue(base, null, attrib, config);				
			}
			
			if ( strBase != null && strBase.toString().length() > 0 ) {
				if ( str != null && str.toString().length() > 0 ) {
					str = strBase + ATTRIBUTE_VALUE_SEPERATOR + str;
				} else {
					str = strBase;
				}
			}
		} else {
			str = calcAttributeFeatureValue(e, ve, attrib, config);
		}

		return (str==null) ? "" : str.toString(); //$NON-NLS-1$
		
//		List values = new ArrayList();
//		VariabilityElement base;
//		VariabilityType variabilityType;	
//		boolean concat = attrib != UmaPackage.eINSTANCE.getMethodElement_PresentationName();
//		while(true) {
//			base = ve.getVariabilityBasedOnElement();
//			if(base == null) {
//				Object str;
//				if ( isDesc ) {
//					str = ((DescribableElement)ve).getPresentation().eGet(attrib); 
//				} else {
//					str =  ve.eGet(attrib); 
//				}
//				if(str == null || str.toString().length() == 0) {
//					if(values.isEmpty()) {
//						values.add(str);
//					}
//				}
//				else {
//					values.add(0, str);
//				}
//				break;
//			}
//			else if(base instanceof Activity) {
//				Object str;
//				if ( isDesc ) {
//					str = ((DescribableElement)ve).getPresentation().eGet(attrib); 
//				} else {
//					str = ve.eGet(attrib); 
//				}
//
//				variabilityType = ve.getVariabilityType();
//				if(variabilityType == VariabilityType.EXTENDS_LITERAL) {
//					if(str == null || str.toString().length() == 0) {
//						// use the value of the base
//						//
//						ve = base;
//					}
//					else {
//						values.add(0, str);
//						break;
//					}
//				}
//				else if(variabilityType == VariabilityType.LOCAL_CONTRIBUTION_LITERAL) {					
//					if(str != null && str.toString().length() > 0) {
//						// add to the list
//						//
//						values.add(0, str);
//						if(!concat) {
//							break;
//						}
//					}
//					// go to the base
//					ve = base;
//				}
//				else if(variabilityType == VariabilityType.LOCAL_REPLACEMENT_LITERAL) {
//					values.add(0, str);
//					break;
//				}
//				//TODO: handle CONTRIBUTES AND REPLACES
//			}
//		}
//		
//		if ( values.size() == 0 ) {
//			return ""; //$NON-NLS-1$
//		}
//		else if ( values.size() ==1 ) {
//			return (String)values.get(0);
//		}
//		else {
//			StringBuffer buffer = new StringBuffer();
//			for (Iterator it = values.iterator(); it.hasNext(); ) {
//				if ( buffer.length() > 0 ) {
//					buffer.append(ATTRIBUTE_VALUE_SEPERATOR);
//				}
//				buffer.append(it.next());
//			}
//			return buffer.toString();			
//		}
	}
	
	/**
	 * get all processes in the configuration
	 * 
	 * @param config
	 * @return List
	 */
	public static List getAllProcesses(MethodConfiguration config) {
		List processes = new ArrayList();
		List plugins = config.getMethodPluginSelection();
		for (Iterator it = plugins.iterator(); it.hasNext(); ) {
			List items = TngUtil.getAllProcesses((MethodPlugin)it.next());
			for ( Iterator itp = items.iterator(); itp.hasNext(); ) {
				org.eclipse.epf.uma.Process p = (org.eclipse.epf.uma.Process)itp.next();
				if ( canShow(p, config) && !processes.contains(p) ) {
					processes.add(p);
				}
			}
		}
		
		return processes;
	}
	
	/**
	 * get all processes in the plugin and the configuration
	 * 
	 * @param plugin
	 * @param config
	 * @return List
	 */
	public static List getAllProcesses(MethodPlugin plugin, MethodConfiguration config) {
		List processes = new ArrayList();
		List items = TngUtil.getAllProcesses(plugin);
		for ( Iterator itp = items.iterator(); itp.hasNext(); ) {
			org.eclipse.epf.uma.Process p = (org.eclipse.epf.uma.Process)itp.next();
			if ( canShow(p, config) && !processes.contains(p) ) {
				processes.add(p);
			}
		}
	
		return processes;
	}
	
	/**
	 * get all delivery processes in the plugin and configuration
	 * @param plugin
	 * @param config
	 * @return List
	 */
	public static List getAllDeliveryProcesses(MethodPlugin plugin, MethodConfiguration config) {
		List processes = new ArrayList();
		List items = TngUtil.getAllProcesses(plugin);
		for ( Iterator itp = items.iterator(); itp.hasNext(); ) {
			org.eclipse.epf.uma.Process p = (org.eclipse.epf.uma.Process)itp.next();
			if ( p instanceof DeliveryProcess ) {
				if ( canShow(p, config) && !processes.contains(p) ) {
					processes.add(p);
				}
			}
		}
	
		return processes;
	}
	
	/**
	 * get all CapabilityPatterns in the plugin and configuration
	 * 
	 * @param plugin
	 * @param config
	 * @return List
	 */
	public static List getAllCapabilityPatterns(MethodPlugin plugin, MethodConfiguration config) {
		List processes = new ArrayList();
		List items = TngUtil.getAllProcesses(plugin);
		for ( Iterator itp = items.iterator(); itp.hasNext(); ) {
			org.eclipse.epf.uma.Process p = (org.eclipse.epf.uma.Process)itp.next();
			if ( p instanceof CapabilityPattern ) {
				if ( canShow(p, config) && !processes.contains(p) ) {
					processes.add(p);
				}
			}
		}
	
		return processes;
	}
	
	/**
	 * calculate the work products modified by the specified role, in the configuration
	 * 
	 * @param element Role
	 * @param ownerElement MethodElement the owner to accept the calculated value
	 * @param realizer
	 * @return List list of work products being modified by the role
	 */
	public static List calcModifiedWorkProducts(Role element, MethodElement ownerElement, ElementRealizer realizer) {
		List v = new ArrayList();
		OppositeFeature ofeature = AssociationHelper.Role_Primary_Tasks;
		ToManyOppositeFeatureValue fv2 = new ToManyOppositeFeatureValue(element, ofeature, realizer);
		calculateOppositeFeature(element, ofeature, realizer, fv2);
		
		if ( fv2.size() > 0 ) {
			List v2 = (List)fv2.getValue();
			EStructuralFeature feature = UmaPackage.eINSTANCE.getTask_Output();
			Set set = new LinkedHashSet();
			for (Iterator it = v2.iterator(); it.hasNext(); ) {
				MethodElement e = (MethodElement)it.next();
				ToManyFeatureValue fv = new ToManyFeatureValue(element, ownerElement, feature, realizer);
				
//				This is wrong: fv.getValue() may return a copy instead of the reference of the internal value field! 				
//				((List)).addAll(v);   
				
				calculateFeature(e, ownerElement, 
						feature, 
						realizer.getConfiguration(), fv, realizer);	
//				v = (List)fv.getValue();
				List list = (List)fv.getValue();
				if (list != null && !list.isEmpty()) {
					set.addAll(list);
				}								
			}
			if (! set.isEmpty()) {
				v.addAll(set);
			}
		}	
		
		return v;
	}
		
	/**
	 * calculate the work product descriptors modified by the specified role descriptor, in the configuration
	 * 
	 * @param element RoleDescriptor
	 * @param ownerElement MethodElement the owner to accept the calculated value
	 * @param realizer
	 * @return List list of work product descriptors being modified by the role descriptor
	 * @deprecated use the RoleDescriptorLayout to calculate the feature value
	 */
	public static List calcModifiedWorkProductDescriptors(RoleDescriptor element, MethodElement ownerElement, ElementRealizer realizer) {
		List v = new ArrayList();
		OppositeFeature ofeature = AssociationHelper.RoleDescriptor_PrimaryTaskDescriptors;
		ToManyOppositeFeatureValue fv2 = new ToManyOppositeFeatureValue(element, ofeature, realizer);
		calculateOppositeFeature(element, ofeature, realizer, fv2);
		if ( fv2.size() > 0 ) {
			List v2 = (List)fv2.getValue();
			EStructuralFeature feature = UmaPackage.eINSTANCE.getTaskDescriptor_Output();
			for (Iterator it = v2.iterator(); it.hasNext(); ) {
				MethodElement e = (MethodElement)it.next();
				
				ToManyFeatureValue fv = new ToManyFeatureValue(element, ownerElement, feature, realizer);
				((List)fv.getValue()).addAll(v);
				
				calculateFeature(e, ownerElement, 
						feature, 
						realizer.getConfiguration(), fv, realizer);	
				v = (List)fv.getValue();
			}
		}
		
		return v;
	}
	
	/**
	 * calculate the roles that modifies the specified work product in the configuration
	 * @param element WorkProduct
	 * @param realizer
	 * @return List the roles
	 */
	public static List calcModifyRoles(WorkProduct element, ElementRealizer realizer) {
		List tasks = ConfigurationHelper.calc0nFeatureValue(
				element, 
				AssociationHelper.WorkProduct_OutputFrom_Tasks, 
				realizer);
		List modifyRoles = new ArrayList();
		for (Iterator it = tasks.iterator(); it.hasNext(); ) {
			Task t = (Task)it.next();
			List<Role> rList = (List<Role>)ConfigurationHelper.calc0nFeatureValue(
					t, 
					UmaPackage.eINSTANCE.getTask_PerformedBy(), 
					realizer);
			for (Role r: rList) {
				if ( (r != null) && !modifyRoles.contains(r) ) {
					modifyRoles.add(r);
				}
			}
		}
		
		return modifyRoles;
	}
	
	/**
	 * calculate the role descriptors that modifies the specified work product descriptor in the configuration
	 * @param element WorkProductDescriptor
	 * @param realizer
	 * @return List the role descriptors
	 * @deprecated use the WorkProductDescriptorlayout to calculate the feature value
	 */
	public static List calcModifyRoleDescriptors(WorkProductDescriptor element, ElementRealizer realizer) {
		List taskDescriptors = ConfigurationHelper.calc0nFeatureValue(
				element, 
				AssociationHelper.WorkProductDescriptor_OutputFrom_TaskDescriptors, 
				realizer);
		List modifyRoles = new ArrayList();
		for (Iterator it = taskDescriptors.iterator(); it.hasNext(); ) {
			TaskDescriptor t = (TaskDescriptor)it.next();
			RoleDescriptor r = (RoleDescriptor)ConfigurationHelper.calc01FeatureValue(
					t, 
					UmaPackage.eINSTANCE.getTaskDescriptor_PerformedPrimarilyBy(), 
					realizer);
			if ( (r != null) && !modifyRoles.contains(r) ) {
				modifyRoles.add(r);
			}
		}
		
		return modifyRoles;
	}
	
	/**
	 * check if the activity is a contributor 
	 * or contains any sub-activities that are contributors
	 * @param element Activity
	 * @return boolean
	 */
	public static boolean hasContributor(Activity element) {
		if ( isContributor(element)) {
			return true;
		}

		for ( Iterator it = element.getBreakdownElements().iterator(); it.hasNext(); ) {
			Object o = it.next();
			if ( !(o instanceof Activity) ) {
				continue;
			}
			
			if ( hasContributor( (Activity)o ) ) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * get the base processes for an {@link Activity} in the Configuration
	 * @param element
	 * @param config
	 * @return List
	 */
	public static List getBaseProcesses(Activity element, MethodConfiguration config) {
		List value = new ArrayList();
		getBaseProcesses(element, config, value);
		return value;
	}
	
	/**
	 * get all base processes for an activity in the configuration
	 * @param element
	 * @param config
	 * @param value List the bases processes
	 */
	public static void getBaseProcesses(Activity element, MethodConfiguration config, List value) {
		
		// get it's own base
		if ( ConfigurationHelper.isExtender(element) ) {
			Activity base = (Activity)element.getVariabilityBasedOnElement();
			if ( base != null 
					&& !value.contains(base) 
					&& (base instanceof org.eclipse.epf.uma.Process) ) {
				if ( canShow(base, config) ) {
					value.add(base);
				}
				getBaseProcesses(base, config, value);
			}
		}
		
		// if the sub-activities have base, process it
		for ( Iterator it = element.getBreakdownElements().iterator(); it.hasNext(); ) {
			Object o = it.next();
			if ( o instanceof Activity ) {
				getBaseProcesses((Activity)o, config, value);
			}
		}
	}
	
	public static URI getInheritingUri(DescribableElement obj, URI uri,
			VariabilityElement[] uriInheritingBases, MethodConfiguration config, int uriType) {
		VariabilityElement ve = (VariabilityElement) obj;
		VariabilityElement base = ve.getVariabilityBasedOnElement();
		if (base != null
				&& (ve.getVariabilityType() == VariabilityType.EXTENDS || ve
						.getVariabilityType() == VariabilityType.EXTENDS_REPLACES)) {
			base = (VariabilityElement) ConfigurationHelper.getCalculatedElement(base, config);
			if (base != null) {
				if (uriType == 0) {
					uri = ((DescribableElement) base).getNodeicon();
				} else if (uriType == 1) {
					uri = ((DescribableElement) base).getShapeicon();
				}
				if (uri != null) {
					uriInheritingBases[0] = base;
				}
			}
		}
		return uri;
	}
}