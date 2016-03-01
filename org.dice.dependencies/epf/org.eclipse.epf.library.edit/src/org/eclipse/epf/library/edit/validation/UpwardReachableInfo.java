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
package org.eclipse.epf.library.edit.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;
import org.eclipse.epf.uma.util.AssociationHelper;

/**
 * This class handles upward reachable info for circular dependency checking
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class UpwardReachableInfo implements IDependencyInfo {

	private DependencyInfoMgr mgr;
	private MethodElement element;
	private HashMap parentMap;
	private boolean complete = false;
	private boolean debug = false;
	
	protected UpwardReachableInfo(DependencyInfoMgr mgr, MethodElement element) {
		this.mgr = mgr;
		this.element = element;
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	public void build(boolean checkCircular) {
		if (debug) {
			System.out.println("LD> build: " + this);	//$NON-NLS-1$
		}
		buildInner(checkCircular);
	}
	
	private void buildInner(boolean checkCircular) {
		Object info = mgr.getProcessedInfo(element);
		if (info != null) {
			if (info == this && complete) {
				return;
			}
			throw new RuntimeException("Internal error in buildInner: " + info);		//$NON-NLS-1$
		}
		mgr.addToProcessed(this);
		if (debug) {
			System.out.println("LD> buildInner: " + this);	//$NON-NLS-1$
			System.out.println("");							//$NON-NLS-1$
		}
		buildInner_(checkCircular);
	}
	
	private void buildInner_(boolean checkCircular) {
		List parents = getMixedParentList();
		int sz = parents == null ? 0 : parents.size();
		if (sz == 0) {
			complete = true;
			return;
		}
		
		parentMap = new HashMap();
		List processedParents = null;
		for (int i = 0; i< sz; i++) {
			MethodElement parentElem = (MethodElement) parents.get(i);
			UpwardReachableInfo pinfo = (UpwardReachableInfo) mgr.getProcessedInfo(parentElem);
			if (pinfo == null){
				pinfo = new UpwardReachableInfo(mgr, parentElem);
				parentMap.put(parentElem.getGuid(), pinfo);
				pinfo.buildInner(checkCircular);
			} else {
				parentMap.put(parentElem.getGuid(), pinfo);
				if (checkCircular) {
					if (processedParents == null) {
						processedParents = new ArrayList();
					}
					processedParents.add(pinfo);
				}
			}
		}
		complete = true;
			
		if (checkCircular && processedParents != null) {
			for (int i=0; i<processedParents.size(); i++) {
				reachableBy((IDependencyInfo) processedParents.get(i));
			}
		}
	}
	
	public boolean reachableBy(IDependencyInfo info)  {
		if (! (info instanceof UpwardReachableInfo)) {
			throw new UnsupportedOperationException();
		}
		if (debug) {
			System.out.println("LD> Entry reachableBy: this -> " + this);	//$NON-NLS-1$
			System.out.println("LD> Entry reachableBy: info -> " + info);	//$NON-NLS-1$
			System.out.println("");											//$NON-NLS-1$
		}
		return reachableBy((UpwardReachableInfo) info, new Stack(), new HashMap());
	}
	
	private boolean reachableBy(UpwardReachableInfo info, Stack stack, Map seen)  {
		stack.push(info);
		if (debug) {
			System.out.println("LD> reachableBy: this -> " + this);		//$NON-NLS-1$
			System.out.println("LD> reachableBy: info -> " + info);		//$NON-NLS-1$
			System.out.println("");										//$NON-NLS-1$
		}
		boolean ret =  reachableBy_(info, stack, seen);
		stack.pop();
		return ret;
	}
	
	private boolean reachableBy_(UpwardReachableInfo info, Stack stack, Map seen)  {
		Map testMap = info.parentMap;		
		if (testMap == null || testMap.isEmpty()) {
			return false;
		}
		
		if (testMap.containsKey(element.getGuid())) {
			if (debug) {
				System.out.println("LD> Contained in parentMap of: " + info);		//$NON-NLS-1$									
			}
			stack.push(this);
			mgr.logCircularDependency((Stack) stack.clone());
			stack.pop();
			return true;
		}
		
		if (seen.containsKey(info.getElement().getGuid())) {
			return false;
		}
		
		
		seen.put(info.getElement().getGuid(), info);
		
		for (Iterator it = testMap.values().iterator(); it.hasNext();) {
			UpwardReachableInfo parentInfo = (UpwardReachableInfo) it.next();
			if (parentInfo.containedIn(stack)) {
				if (debug) {
					System.out.println("LD> containedIn stack: " + info);		//$NON-NLS-1$									
				}
				stack.push(parentInfo);
				mgr.logCircularDependency((Stack) stack.clone());
				stack.pop();
				return true;
			}
			if  (reachableBy(parentInfo, stack, seen)) {
				return true;
			}
		}	
		
		return false;
	}
	
	public MethodElement getElement() {
		return element;
	}
	
	private List getMixedParentList() {
		List list = new ArrayList();
		collectParentList(element, list);
		VariabilityElement ve = (VariabilityElement) element;
		collectParentListByVariantPaths(ve, list);
		if (ve.getVariabilityType() == VariabilityType.REPLACES ) {
			mgr.addToReplacerMap(this);
		}
		return list;
	}
	
	private void collectParentList(MethodElement elem, List list) {
		List parentList = getParentList(elem);
		
		int sz = parentList == null ? 0 : parentList.size();
		if (mgr.isMoveElement(elem)) {
			if (sz > 1 && !(elem instanceof CustomCategory || elem instanceof Practice)) {
				LibraryEditPlugin.getDefault().getLogger().logError("Error in collectParentList"); //$NON-NLS-1$
			}
		} else if (sz > 0) {
			list.addAll(parentList);
		}

	}

	public static List getParentList(MethodElement elem) {
		List parentList = null;
		
		if (elem instanceof CustomCategory) {
			parentList = AssociationHelper.getCustomCategories((CustomCategory) elem);
			parentList = combine(parentList, AssociationHelper.getPractices((CustomCategory) elem));
						
		} else if (elem instanceof Deliverable) {
			parentList = AssociationHelper.getDeliverables((Deliverable) elem);			
		} else {	
			Object parent = getSameTypeParent(elem);
			if (parent != null) {
				parentList = new ArrayList();
				parentList.add(parent);
			}
			if (elem instanceof Practice) {
				parentList = combine(parentList, AssociationHelper.getCustomCategories((Practice) elem));
				parentList = combine(parentList, AssociationHelper.getPractices((Practice) elem));
			}
		}
		return parentList;
	}
	
	private static List combine(List list1, List list2) {
		List ret = new ArrayList();		
		if (list1 != null) {
			ret.addAll(list1);
		} 
		if (list2 != null) {
			ret.addAll(list2);
		}		
		return ret;
	}
	
	private void collectParentListByVariantPaths(VariabilityElement ve, List list) {
		if (! mgr.isFilterElement(ve)) {
			VariabilityElement parentVe = ve.getVariabilityBasedOnElement();
			VariabilityType type = parentVe == null ? null : ve.getVariabilityType();
			if (type == VariabilityType.CONTRIBUTES || type == VariabilityType.REPLACES) {
				list.add(parentVe);
			}
		}

		if (mgr.isDndElement(ve)) {
			return;
		}
				
		for (Iterator it = TngUtil.getImmediateVarieties(ve, VariabilityType.EXTENDS) ; it.hasNext();) {
			VariabilityElement extender = (VariabilityElement) it.next();
			if (! mgr.isFilterElement(extender)) {
				list.add(extender);
			}
		}
		
	}
	
	private static MethodElement getSameTypeParent(MethodElement elem) {
		if (elem instanceof Activity) {
			return ((Activity) elem).getSuperActivities();
		}
			
		MethodElement parent = (MethodElement) elem.eContainer();
		return parent.getType() == elem.getType() ? parent : null;
	}		
	
	public String toString() {
		return TngUtil.getLabelWithPath(element);
	}
	
	public boolean inheritAncestor(VariabilityType type) {
		VariabilityElement parentVe = ((VariabilityElement) element).getVariabilityBasedOnElement();
		if (parentVe == null) {
			return false;
		}
		if (((VariabilityElement) element).getVariabilityType() != type) {
			return false;
		}
		ArrayList mixParentList = new ArrayList();	
		collectParentList(element, mixParentList);
		if (mixParentList != null && mixParentList.contains(parentVe)) {
			return true;
		}

		UpwardReachableInfo parentVeInfo = (UpwardReachableInfo) parentMap.get(parentVe.getGuid());
		for (Iterator it = parentMap.values().iterator(); it.hasNext();) {
			UpwardReachableInfo parentInfo = (UpwardReachableInfo) it.next();
			if  (parentVeInfo == parentInfo) {
				continue;
			}
			if (parentVeInfo.reachableBy(parentInfo)) {
				return true;
			}
		}
				
		return false;
	}
	
	//This would scale up for performance when infoList gets large
	private boolean containedIn(List infoList) {
		int sz = infoList == null ? 0 : infoList.size();
		if (sz == 0) {
			return false;
		}
		String guid = getElement().getGuid();
		if (sz == 1) {
			return guid.equals(((IDependencyInfo) infoList.get(0)).getElement().getGuid());
		}
		Map listMap = new HashMap();
		for (int i=0; i<sz; i++) {
			IDependencyInfo info = (IDependencyInfo) infoList.get(i);
			listMap.put(info.getElement().getGuid(), info);
		}
		
		return listMap.containsKey(guid);
	}
	
}
