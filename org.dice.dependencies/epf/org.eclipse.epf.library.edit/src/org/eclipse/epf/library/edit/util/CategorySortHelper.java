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
package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.util.model.OrderInfo;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.util.AssociationHelper;

/**
 * Helper class to handle sorting of Category elements
 * @author Jeff Hardy
 *
 */
public class CategorySortHelper {
	
	public static final String KEY_CATEGORY_ELEMENTS__SORT_TYPE = "CategoryElementsSortType"; //$NON-NLS-1$
	public static final String V_CATEGORY_ELEMENTS__SORT_TYPE_ALPHA = "Alphabetic"; //$NON-NLS-1$
	public static final String V_CATEGORY_ELEMENTS__SORT_TYPE_REVERSE_ALPHA = "ReverseAlphabetic"; //$NON-NLS-1$
	public static final String V_CATEGORY_ELEMENTS__SORT_TYPE_METHOD_TYPE = "MethodType"; //$NON-NLS-1$
	public static final String V_CATEGORY_ELEMENTS__SORT_TYPE_MANUAL = "Manual"; //$NON-NLS-1$

	/**
	 * Creates a new MethodElementProperty to store the Category sort type
	 * @param sortType
	 * @return
	 */
	public static MethodElementProperty createNewSortProperty(String sortType) {
		MethodElementProperty prop = UmaFactory.eINSTANCE.createMethodElementProperty();
		prop.setName(KEY_CATEGORY_ELEMENTS__SORT_TYPE);
		if (sortType != null) {
			prop.setValue(sortType);
		}
		return prop;
	}
	
	/**
	 * Return category sort property for method element
	 * @param element
	 * @return
	 * 		 property if found, else null
	 */
	public static MethodElementProperty getCategorySortProperty(MethodElement element) {
		List props = element.getMethodElementProperty();
		for (Iterator it = props.iterator(); it.hasNext();) {
			MethodElementProperty prop = (MethodElementProperty) it.next();
			if (KEY_CATEGORY_ELEMENTS__SORT_TYPE.equals(prop.getName())) {
				return prop;
			}
		}
		return null;
	}
	
	/**
	 * return true iff the category sort-type is set to manual
	 * @param element
	 * @return
	 * 		 property if found, else null
	 */
	public static boolean isManualCategorySort(MethodElement element) {
		String sortType = getCategorySortValue(element);
		if (V_CATEGORY_ELEMENTS__SORT_TYPE_MANUAL.equals(sortType))
			return true;
		return false;
	}

	/**
	 * Returns the sort-type for the given MethodElement.
	 * If none is set, will return Alphabetic sort (V_CATEGORY_ELEMENTS__SORT_TYPE_ALPHA) as a default
	 * @param element
	 * @return
	 */
	public static String getCategorySortValue(MethodElement element) {
		MethodElementProperty prop = getCategorySortProperty(element);
		if (prop == null)
			return V_CATEGORY_ELEMENTS__SORT_TYPE_MANUAL;
		return prop.getValue();
	}

	public static List<String> getCategorySortTypes() {
		List<String> returnList = Arrays.<String>asList(
				V_CATEGORY_ELEMENTS__SORT_TYPE_MANUAL,
				V_CATEGORY_ELEMENTS__SORT_TYPE_ALPHA,
				V_CATEGORY_ELEMENTS__SORT_TYPE_REVERSE_ALPHA,
				V_CATEGORY_ELEMENTS__SORT_TYPE_METHOD_TYPE);
		return returnList;
	}

	public static String getSortTypeDisplayName(String sortType) {
		if (V_CATEGORY_ELEMENTS__SORT_TYPE_ALPHA.equals(sortType)) {
			return LibraryEditResources.SortType_Alphabetic;
		}
		if (V_CATEGORY_ELEMENTS__SORT_TYPE_REVERSE_ALPHA.equals(sortType)) {
			return LibraryEditResources.SortType_ReverseAlphabetic;
		}
		if (V_CATEGORY_ELEMENTS__SORT_TYPE_METHOD_TYPE.equals(sortType)) {
			return LibraryEditResources.SortType_MethodType;
		}
		if (V_CATEGORY_ELEMENTS__SORT_TYPE_MANUAL.equals(sortType)) {
			return LibraryEditResources.SortType_Manual;
		}
		return ""; //$NON-NLS-1$
	}
	
	/**
	 * Returns a sorted category element list based on the category's sort type
	 * Respects the Name/PresName toggle
	 * @param element 
	 * @param elements array to sort
	 * @return
	 */
	public static List<Object> sortCategoryElements(MethodElement element, Object[] elements) {
		return sortCategoryElements(element, elements, false, null, null);
	}
	
	/**
	 * Returns a sorted category element list based on the category's sort type
	 * Respects the Name/PresName toggle
	 * @param element 
	 * @param elements array to sort
	 * @return
	 */
	public static List<Object> sortCategoryElements(MethodElement element, Object[] elements, MethodConfiguration config) {
		return sortCategoryElements(element, elements, false, null, config);
	}

	/**
	 * Returns a sorted category element list based on the category's sort type
	 * @param element 
	 * @param elements array to sort
	 * @param forcePresNameSort true to always sort by presName. false will respect the toggle
	 * @return
	 */
	public static List<Object> sortCategoryElements(MethodElement element, Object[] elements, 
			boolean forcePresNameSort, EStructuralFeature feature, MethodConfiguration config) {
		/*
		 * TODO: can't use generics here because EMF doesn't use them yet - the elements param
		 * usually comes from ContentElementsOrderList class which extends EMF's BasicEList and so
		 * the toArray() method will only return Object[]
		 */
		List<Object> returnList = new ArrayList<Object>(Arrays.<Object>asList(elements));
		String sortType = getCategorySortValue(element);
		if (V_CATEGORY_ELEMENTS__SORT_TYPE_MANUAL.equals(sortType)) {
			if (element instanceof ContentCategory) {
				return findManualSortOrderInContributors((ContentCategory)element, returnList, feature, config);
			}
			return returnList;
		} else if (V_CATEGORY_ELEMENTS__SORT_TYPE_ALPHA.equals(sortType)) {
			Comparator comparator = PresentationContext.INSTANCE.getComparator();
			if (forcePresNameSort)
				comparator = PresentationContext.INSTANCE.getPresNameComparator();
			Collections.<Object>sort(returnList, comparator);
		} else if (V_CATEGORY_ELEMENTS__SORT_TYPE_REVERSE_ALPHA.equals(sortType)) {
			Comparator comparator = PresentationContext.INSTANCE.getReverseComparator();
			if (forcePresNameSort)
				comparator = PresentationContext.INSTANCE.getPresNameReverseComparator();
			Collections.<Object>sort(returnList, comparator);
		} else if (V_CATEGORY_ELEMENTS__SORT_TYPE_METHOD_TYPE.equals(sortType)) {
			Comparator comparator = PresentationContext.INSTANCE.getMethodElementTypeComparator();
			if (forcePresNameSort)
				comparator = PresentationContext.INSTANCE.getPresNameMethodElementTypeComparator();
			Collections.<Object>sort(returnList, comparator);
		}
		return returnList;
	}
	
	private static List<Object> findManualSortOrderInContributors(ContentCategory cc, List<Object> elementList,
			EStructuralFeature feature, MethodConfiguration config) {
		if (feature == null) {
			return elementList;
		}
		String sortType = getCategorySortValue(cc);
		if (! V_CATEGORY_ELEMENTS__SORT_TYPE_MANUAL.equals(sortType)) {
			return elementList;
		}
		
		ManualSort manualSort = new ManualSort();
		return manualSort.sort(cc, elementList, feature, config);
	}
	
	//Keep this old code for reference
	private static List<Object> findManualSortOrderInContributors(ContentCategory cc, List<Object> elementList) {
		OrderInfo latestInfo = null;
		Map<String, MethodElement> guidMap = new HashMap<String, MethodElement>();

		String sortType = getCategorySortValue(cc);
		if (V_CATEGORY_ELEMENTS__SORT_TYPE_MANUAL.equals(sortType)) {
			// set up GUID map
			for (Iterator iterator = elementList.iterator(); iterator.hasNext();) {
				Object o = iterator.next();
				if (o instanceof MethodElement) {
					guidMap.put(((MethodElement)o).getGuid(),
							(MethodElement)o);
				}
			}
			for (Iterator iter = TngUtil.getContributors(cc);iter.hasNext();) {
				Object obj = iter.next();
				if (obj instanceof ContentCategory) {
					ContentCategory contributor = (ContentCategory)obj;
					OrderInfo orderInfo = TngUtil.getOrderInfo(contributor, ContentElementOrderList.ORDER_INFO_NAME);
					if (orderInfo == null) {
						continue;
					}
					// find latest OrderInfo that contains all these elements
					if (latestInfo == null || orderInfo.getTimestamp() > latestInfo.getTimestamp()) {
						latestInfo = orderInfo;
					}
				}
			}
			if (latestInfo != null) {
				List<Object> returnList = new ArrayList<Object>();
				// sort list according to orderInfo
				int size = latestInfo.getGUIDs().size();
				for (int i = 0; i < size; i++) {
					String guid = (String)latestInfo.getGUIDs().get(i);
					Object element = guidMap.get(guid);
					if (element != null) {
						returnList.add(element);
						guidMap.remove(guid);
					} else {
						// try to find element in the guidMap that has a variable element with "guid"
						MethodElement me = findElementInVariableElementList(guidMap, guid);
						if (me != null) {
							returnList.add(me);
							guidMap.remove(me.getGuid());
						} else {
							// could not locate, will return original list
						} 
					}
				}
				
				if (elementList.size() == returnList.size())
					return returnList;
			}
		}
		return elementList;
	}
	
	private static MethodElement findElementInVariableElementList(Map<String, MethodElement> guidMap, String guid) {
		for (Iterator<MethodElement> iter = guidMap.values().iterator();iter.hasNext();) {
			MethodElement value = iter.next();
			if (value instanceof VariabilityElement) {
				Set set = new HashSet();
				set.add(value);
				Set varElements = AssociationHelper.getVariabilityElements(set, true, true);
				if(varElements != null && varElements.size() > 0){
					for (Iterator varIter = varElements.iterator();varIter.hasNext();) {
						Object variableElement = varIter.next();
						if (variableElement instanceof MethodElement) {
							if (((MethodElement)variableElement).getGuid().equals(guid)) {
								return value;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * @param cc
	 * @param feature
	 * @return
	 */
	public static boolean needToSort(MethodElement element, EStructuralFeature feature) {
		if (! (element instanceof ContentCategory)) {
			return false;
		}
		if (element instanceof CustomCategory && 
				feature == UmaPackage.eINSTANCE.getCustomCategory_CategorizedElements()) {
			return true;
		}
		if (element instanceof Discipline && 
				feature == UmaPackage.eINSTANCE.getDiscipline_Tasks()) {
			return true;
		}
		if (element instanceof Domain && 
				feature == UmaPackage.eINSTANCE.getDomain_WorkProducts()) {
			return true;
		}
		if (element instanceof RoleSet && 
				feature == UmaPackage.eINSTANCE.getRoleSet_Roles()) {
			return true;
		}
		if (element instanceof Tool && 
				feature == UmaPackage.eINSTANCE.getTool_ToolMentors()) {
			return true;
		}
		if (element instanceof WorkProductType && 
				feature == UmaPackage.eINSTANCE.getWorkProductType_WorkProducts()) {
			return true;
		}

		return false;
	}
}
