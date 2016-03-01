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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.epf.common.utils.ProfilingUtil;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.VariabilityElement;
import org.eclipse.epf.uma.VariabilityType;

/**
 * This class manages circular dependency info.
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class DependencyInfoMgr {
	
	private static boolean profiling = false;
	private static boolean debug = false;
	private MethodLibrary lib;
	private Map reachableInfoMap;	//{guid, upwardReachableInfo}
	private Map replacerMap;
	private Map processed;
	private MethodElement filterElement;
	private MethodElement moveElement;
	private List circularList;
	private boolean dndBit = false;
	
	public DependencyInfoMgr(MethodLibrary lib) {
		this.lib = lib;
	}
	
	public void logCircularDependency(Stack trace) {
		if (circularList == null) {
			circularList = new ArrayList();
		}
		circularList.addAll(trace);
	}
	
	private List getAndClearCircularList() {
		List ret = circularList;
		circularList = null;
		return ret;
	}
	
	public boolean isFilterElement(MethodElement elem) {
		return filterElement == elem;
	}	
	
	public boolean isMoveElement(MethodElement elem) {
		return moveElement == elem;
	}
	
	private void log(String msg) {
		LibraryEditPlugin.INSTANCE.log(msg);
	}
	
	public IStatus checkCircularDependnecy(VariabilityElement ve) {
		if (debug) {
			System.out.println("LD> ve: " + TngUtil.getLabelWithPath(ve));		//$NON-NLS-1$
		}
		IDependencyInfo info = registerVariabilityElement(ve, false, false, true);
		List cirList = getAndClearCircularList();
		boolean loop = cirList != null && !cirList.isEmpty();
		boolean replacingAncestor = info.inheritAncestor(VariabilityType.REPLACES);
		boolean actInherentingAncestor = getActInerentingAncestor(ve);
		
		MultiStatus multiStatus = new MultiStatus(LibraryEditPlugin.PLUGIN_ID, 0, "", null); //$NON-NLS-1$
		if (loop) {
			log("Error> Circular dependency detected: ");		//$NON-NLS-1$
			for (int i=0; i<cirList.size(); i++) {
				IDependencyInfo loopInfo = (IDependencyInfo) cirList.get(i);
				MethodElement elem = loopInfo.getElement();							
				log("Error> " + i + ": " + TngUtil.getLabelWithPath(elem));//$NON-NLS-1$ //$NON-NLS-2$
			}
			
			multiStatus.add(new ValidationStatus(IStatus.ERROR, 0, genErrorMsg(cirList), ve, null));			
		}
		if (replacingAncestor) {
			MethodElement elem = info.getElement();
			log("Error> " + TngUtil.getLabelWithPath(elem) + " is replacing an ancestor.");//$NON-NLS-1$ //$NON-NLS-2$
			
			multiStatus.add(new ValidationStatus(IStatus.ERROR, 0, LibraryEditResources.replacing_ancestor_error_msg, ve, null));							
		}
		if (actInherentingAncestor) {
			MethodElement elem = info.getElement();
			multiStatus.add(new ValidationStatus(IStatus.ERROR, 0, LibraryEditResources.circular_dependency_error_msg, ve, null));							
		}

		
		
		return multiStatus;
	}

	private boolean getActInerentingAncestor(VariabilityElement ve) {
		if (ve instanceof Activity) {
			Activity base = (Activity) ve.getVariabilityBasedOnElement();
			if (base != null) {
				for (Activity act = ((Activity) ve).getSuperActivities(); 
									act != null; act = act.getSuperActivities()) {
					if (act == base) {
						return true;	
					}
				}
			}
		}
		return false;
	}
	
	private String genErrorMsg(List cirList) {
		String msg = LibraryEditResources.variability_element_circular_loop_error_msg;
		for (int i=0; i<cirList.size(); i++) {
			IDependencyInfo loopInfo = (IDependencyInfo) cirList.get(i);
			MethodElement elem = loopInfo.getElement();	
			if (i > 0) {
				msg += ": "; //$NON-NLS-1$ 
			}
			msg += TngUtil.getLabelWithPath(elem);	
		}
		return msg;
	}
			
	public IDependencyInfo registerVariabilityElement(VariabilityElement ve, 
			boolean	isFilterElement, boolean isMoveElement) {
		return registerVariabilityElement(ve, isFilterElement, isMoveElement, false);
	}
	
	private IDependencyInfo registerVariabilityElement(VariabilityElement ve, 
			boolean	isFilterElement, boolean isMoveElement, boolean checkCircular) {
		if (isFilterElement) {
			filterElement = ve;
		}
		if (isMoveElement) {
			moveElement = ve;
		}		
		IDependencyInfo info =  getProcessedInfo(ve);
		if (info == null) {		
			info = newDependencyInfo(this, ve);
		}
		if (! info.isComplete()) {
			info.build(checkCircular);
		}
		return info;
	}
	
	protected boolean processed(MethodElement element) {
		return processed != null && processed.containsKey(element.getGuid());
	}
	
	protected IDependencyInfo getProcessedInfo(MethodElement element) {
		return processed == null ? null : (IDependencyInfo) processed.get(element.getGuid());
	}
	
	protected void addToProcessed(IDependencyInfo info) {
		if (processed == null) {
			processed = new HashMap();
		}
		processed.put(info.getElement().getGuid(), info);
	}
	
	protected void addToReplacerMap(IDependencyInfo replacerInfo) {
		if (replacerMap == null) {
			replacerMap = new HashMap();
		}
		replacerMap.put(replacerInfo.getElement().getGuid(), replacerInfo);
	}	
		
	private IDependencyInfo addToReachableInfoMap(MethodElement elem) {
		if (reachableInfoMap == null) {
			reachableInfoMap = new HashMap();
		}
		IDependencyInfo info = (IDependencyInfo) reachableInfoMap.get(elem.getGuid());
		if (info == null) {		
			info = newDependencyInfo(this, elem);
			reachableInfoMap.put(elem.getGuid(), info);
		}		
		return info;
	}

	/*
	 * Detect circular dependency for the whole library
	 */
	public boolean hasCircularDependency()  {
		buildReachableInfoMap();
		
		boolean replacingAcestor = false;
		if (replacerMap != null) {
			for (Iterator it = replacerMap.values().iterator(); it.hasNext();) {
				UpwardReachableInfo info = (UpwardReachableInfo) it.next();
				if (info.inheritAncestor(VariabilityType.REPLACES)) {
					replacingAcestor = true;
				}
			}
		}
		if (debug && circularList != null) {
			System.out.println("LD> Circular loops detected: ");	//$NON-NLS-1$
			for (Iterator it = circularList.iterator(); it.hasNext();) {
				Stack stack = (Stack) it.next();
				for (int i=0; i<stack.size(); i++) {
					System.out.println("LD> " + (i) + ": " + stack.get(i));	//$NON-NLS-1$ //$NON-NLS-2$
				}
			}
		}
		
		return replacingAcestor || numCircularDependency() > 0;
	}
	
	private int numCircularDependency() {
		return circularList == null ? 0 : circularList.size();
	}
	
	//to be specialized by sub classes
	protected IDependencyInfo newDependencyInfo(DependencyInfoMgr mgr, MethodElement elem) {
		return  new UpwardReachableInfo(this, elem);
	}
	
	private void buildReachableInfoMap() {
		long usedMem0 = 0;
		Runtime rt = Runtime.getRuntime(); 
		if (profiling) {
			for (Iterator it = lib.eAllContents(); it.hasNext();) {
				it.next();
			}
			ProfilingUtil.fullGC();
			usedMem0 = rt.totalMemory() - rt.freeMemory();
		}
		
		buildReachableInfoMap_();
		if (profiling) {
			ProfilingUtil.fullGC();
			System.out.println("LD> usedMem0: " + usedMem0/1000 + " k bytes"); //$NON-NLS-1$ //$NON-NLS-2$
			long usedMem1 = rt.totalMemory() - rt.freeMemory();
			System.out.println("LD> usedMem1: " + usedMem1/1000 + " k bytes"); //$NON-NLS-1$ //$NON-NLS-2$
			System.out.println("LD> diffMem: " + (usedMem1 - usedMem0)/1000 + " k bytes"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	private void buildReachableInfoMap_() {
		reachableInfoMap = new HashMap();
		List plugins = lib.getMethodPlugins();
		for (int i = 0; i < plugins.size(); i++) {
			MethodPlugin plugin = (MethodPlugin) plugins.get(i);
			for (Iterator it = plugin.eAllContents(); it.hasNext();) {
				Object obj = it.next();
				if (VeToCheck(obj)) {
					addToReachableInfoMap((MethodElement) obj);
				}
			}
		}
		
		for (Iterator it = reachableInfoMap.values().iterator(); it.hasNext();) {
			IDependencyInfo info = (IDependencyInfo) it.next();
			if (! processed(info.getElement())) {
				info.build(true);
			}
		}	

	}	

	public static boolean VeToCheck(Object obj) {
		return 	obj instanceof Domain || 
				obj instanceof CustomCategory || 
				obj instanceof Artifact || 
				obj instanceof Practice || 
				obj instanceof Deliverable ||
				obj instanceof Activity;
	}
	
	/*
	 * Check circular dependency for the library.
	 */
	public CheckResult checkCircularDependnecy(Tracer tracer) {
		long usedMem0 = 0;
		Runtime rt = Runtime.getRuntime(); 
		if (profiling) {
			for (Iterator it = lib.eAllContents(); it.hasNext();) {
				it.next();
			}
			ProfilingUtil.fullGC();
			usedMem0 = rt.totalMemory() - rt.freeMemory();
		}

		CheckResult result = checkCircularDependnecy_(tracer);
		
		if (profiling) {
			ProfilingUtil.fullGC();
			System.out.println("LD> usedMem0: " + usedMem0/1000 + " k bytes"); //$NON-NLS-1$ //$NON-NLS-2$
			long usedMem1 = rt.totalMemory() - rt.freeMemory();
			System.out.println("LD> usedMem1: " + usedMem1/1000 + " k bytes"); //$NON-NLS-1$ //$NON-NLS-2$
			System.out.println("LD> diffMem: " + (usedMem1 - usedMem0)/1000 + " k bytes"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return result;
	}
	
	private CheckResult checkCircularDependnecy_(Tracer tracer) {
		CheckResult result = new CheckResult();
		
		HashSet seen = new HashSet();
		List plugins = lib.getMethodPlugins();
		for (Iterator it = plugins.iterator(); it.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) it.next();
			for (Iterator iit = plugin.eAllContents(); iit.hasNext();) {
				Object obj = iit.next();
				if (seen.contains(obj)) {
					continue;
				}
				seen.add(obj);				
				if (VeToCheck(obj)) {
					IDependencyInfo info = registerVariabilityElement((VariabilityElement) obj, false, false, true);
					List cirList = getAndClearCircularList();
					
					boolean loop = cirList != null && !cirList.isEmpty();
					boolean replacingAncestor = info.inheritAncestor(VariabilityType.REPLACES);
					
					if (loop) {
						tracer.trace("Error> Circular dependency detected: ");		//$NON-NLS-1$
						for (int i=0; i<cirList.size(); i++) {
							IDependencyInfo loopInfo = (IDependencyInfo) cirList.get(i);
							MethodElement elem = loopInfo.getElement();							
							tracer.trace("Error> " + i + ": " + TngUtil.getLabelWithPath(elem));//$NON-NLS-1$ //$NON-NLS-2$
						}
						result.circularList.add(cirList);
					}
					if (replacingAncestor) {
						MethodElement elem = info.getElement();
						tracer.trace("Error> " + TngUtil.getLabelWithPath(elem) + " is replacing an ancestor.");//$NON-NLS-1$ //$NON-NLS-2$
						result.replacingAncestorList.add(info);
					}
				}
			}
		}				
		return result;
	}
	
	public static class CheckResult {
		public List circularList = new ArrayList();
		public List replacingAncestorList = new ArrayList();
		
		public int getErrorCount() {
			return circularList.size() + replacingAncestorList.size();
		}
		
		public int getCircularElementCount() {
			int count = replacingAncestorList.size();
			for (int i=0; i<circularList.size(); i++) {
				List list = (List) circularList.get(i);
				int sz = list == null ? 0 : list.size();
				count += sz;
			}
			return count;
		}
	}
	
	protected boolean isDndElement(MethodElement elem) {
		return dndBit && moveElement == elem;
	}
	
	protected void setDndBit(boolean b) {
		dndBit = b;
	}
	
}
