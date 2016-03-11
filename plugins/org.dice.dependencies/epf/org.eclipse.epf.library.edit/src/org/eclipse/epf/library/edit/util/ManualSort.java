//------------------------------------------------------------------------------
// Copyright (c) 2005, 2009 IBM Corporation and others.
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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.util.model.OrderInfo;
import org.eclipse.epf.uma.ContentCategory;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.VariabilityType;

/**
 * Helper class to handle manual sorting of Category elements
 * @author Weiping Lu
 */
public class ManualSort {
	
	public ManualSort() {		
	}
	
	public List<Object> sort(ContentCategory cc, List<Object> elementList,
			EStructuralFeature feature, MethodConfiguration config) {
		
		List<OrderInfo> orderInfoList = new ArrayList<OrderInfo>();
		for (Iterator iter = TngUtil.getContributors(cc); iter.hasNext();) {
			Object obj = iter.next();
			if (obj instanceof ContentCategory) {
				ContentCategory contributor = (ContentCategory) obj;
				if (config != null && !LibraryEditUtil.getInstance().inConfig(contributor, config)) {
					continue;
				}
				OrderInfo orderInfo = TngUtil.getOrderInfo(contributor, ContentElementOrderList.ORDER_INFO_NAME);
				if (orderInfo != null) {
					orderInfoList.add(orderInfo);
				}
			}
		}
		
		if (orderInfoList.size() == 1){
			return sort(elementList, orderInfoList.get(0));
		}
		
		OrderInfo orderInfo = TngUtil.getOrderInfo(cc, ContentElementOrderList.ORDER_INFO_NAME);
		elementList = sort(elementList, orderInfo);
		if (orderInfoList.isEmpty()) {
			return elementList;
		}
		
		//Sorting by time-stamp to make sure contributing order is deterministic
		Collections.sort(orderInfoList, orderInfoComp);		
		SortData sortData = new SortData(cc, elementList, feature);
		
		for (OrderInfo info : orderInfoList) {
			sortData.processOrderInfo(info);
		}		
		return sortData.getSortedList();
	}
	
	static class SortData {
		private Map<String, MethodElement> guidMap;
		private Set<String> processedGuidSet;
		private LinkedHashMap<String, List<MethodElement>> orderedMap;
		private List<Object> elementList;
		public SortData(ContentCategory cc, List<Object> elementList, EStructuralFeature feature) {
			this.elementList = elementList;
			
			//Build baseCategorizedElementGuids
			Set<String> baseCategorizedElementGuids = new HashSet<String>();
			ContentCategory base = cc;
			while (base != null) {
				addToBaseCategorizedElementGuids(feature, baseCategorizedElementGuids, base);
				
				if (base != cc) {
					for (Iterator iter = TngUtil.getContributors(base); iter.hasNext();) {
						Object obj = iter.next();
						if (obj instanceof ContentCategory) {
							ContentCategory contributor = (ContentCategory) obj;
							addToBaseCategorizedElementGuids(feature, baseCategorizedElementGuids, contributor);
						}
					}
				}
								
				if (base.getVariabilityType() == VariabilityType.EXTENDS || 
						base.getVariabilityType() == VariabilityType.EXTENDS_REPLACES) {
					base = (ContentCategory) base.getVariabilityBasedOnElement();
				} else {
					base = null;
				}
			}
						
			//Build guidMap and orderedMap
			guidMap = new HashMap<String, MethodElement>();
			orderedMap = new LinkedHashMap<String, List<MethodElement>>();
			for (Object obj : elementList) {
				if (obj instanceof MethodElement) {
					MethodElement element = (MethodElement) obj;
					String guid = element.getGuid();
					guidMap.put(guid, element);
					if (baseCategorizedElementGuids.contains(guid)) {
						List<MethodElement> list = new ArrayList<MethodElement>();
						orderedMap.put(guid, list);
					}
				}
			}						
			
			processedGuidSet = new HashSet<String>();
		}

		private void addToBaseCategorizedElementGuids(EStructuralFeature feature,
				Set<String> baseCategorizedElementGuids, ContentCategory cc) {
			Object value = cc.eGet(feature);
			if (value instanceof List) {
				for (Object obj : (List) value) {
					if (obj instanceof MethodElement) {
						baseCategorizedElementGuids.add(((MethodElement) obj).getGuid());
					}
				}
			}
		}
		
		public void processOrderInfo(OrderInfo orderInfo) {
			List<MethodElement> addedList = new ArrayList<MethodElement>();
			for (String guid : (List<String>) orderInfo.getGUIDs()) {
				MethodElement element = (MethodElement) guidMap.get(guid);
				if (element != null) {
					boolean processed = processedGuidSet.contains(guid);
					if (! processed) {
						processedGuidSet.add(guid);
						addedList.add(element);
					}
					List list = orderedMap.get(guid);
					if (list != null) {
						addedList.addAll(list);
						orderedMap.put(guid, addedList);
						addedList =  new ArrayList<MethodElement>();
					}
				} 
			}
		}
		
		public List<Object> getSortedList() {
			List<Object> sortedList = new ArrayList<Object>();
			for (Map.Entry entry : orderedMap.entrySet()) {
				String guid = (String) entry.getKey();
				List list = (List) entry.getValue();				
				if (list.isEmpty()) {
					sortedList.add(guidMap.get(guid));
				} else {
					for (Object element : list) {
						sortedList.add(element);
					}
				}
			}
			for (Object obj : elementList) {
				if (obj instanceof MethodElement) {					
					if (! processedGuidSet.contains(((MethodElement) obj).getGuid())) {
						sortedList.add(obj);
					}
				} else {
					sortedList.add(obj);
				}
			}			
			return sortedList;
		}
	}		
	
	private static Comparator<OrderInfo> orderInfoComp = new Comparator<OrderInfo>() {
		public int compare(OrderInfo o1, OrderInfo o2) {
			long t1 = o1.getTimestamp();
			long t2 = o2.getTimestamp();
			if (t1 < t2) {
				return -1;
			} else if (t2 < t1) {
				return 1;
			}			
			return 0;
		}
	};
	
	private List<Object> sort(List<Object> elementList, OrderInfo orderInfo) {
		if (orderInfo == null) {
			return elementList;
		}
		
		Map<String, MethodElement> guidMap = new HashMap<String, MethodElement>();
		for (Object obj : elementList) {
			if (obj instanceof MethodElement) {
				guidMap.put(((MethodElement) obj).getGuid(), (MethodElement) obj);
			}
		}
		
		List<Object> returnList = new ArrayList<Object>();
		for (String guid : (List<String>) orderInfo.getGUIDs()) {
			Object element = guidMap.get(guid);
			if (element != null) {
				returnList.add(element);
				guidMap.remove(guid);
			} 
		}
		for (Object obj : elementList) {
			if (obj instanceof MethodElement) {
				if (guidMap.containsKey(((MethodElement) obj).getGuid())) {
					returnList.add(obj);
				}
			} else {	
				returnList.add(obj);
			}
		}
		
		return returnList;
	}
	
	
}
