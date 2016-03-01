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

import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodPlugin;

/**
 * This class handles plugin dependency info for circular dependency check
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class PluginDepInfo {

	private PluginDependencyInfoMgr mgr;
	private MethodPlugin plugin;
	private HashMap parentMap;
	private boolean complete = false;
	private boolean debug = false;
	
	protected PluginDepInfo(PluginDependencyInfoMgr mgr, MethodPlugin plugin) {
		this.mgr = mgr;
		this.plugin = plugin;
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
		Object info = mgr.getProcessedInfo(getPlugin());
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
		List parents = getPlugin().getBases();
		int sz = parents == null ? 0 : parents.size();
		if (sz == 0) {
			complete = true;
			return;
		}
		
		parentMap = new HashMap();
		List processedParents = null;
		for (int i = 0; i< sz; i++) {
			MethodPlugin parentElem = (MethodPlugin) parents.get(i);
			PluginDepInfo pinfo = (PluginDepInfo) mgr.getProcessedInfo(parentElem);
			if (pinfo == null){
				pinfo = new PluginDepInfo(mgr, parentElem);
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
				reachableBy((PluginDepInfo) processedParents.get(i));
			}
		}
	}
	
	public boolean reachableBy(PluginDepInfo info)  {
		if (! (info instanceof PluginDepInfo)) {
			throw new UnsupportedOperationException();
		}
		if (debug) {
			System.out.println("LD> Entry reachableBy: this -> " + this);	//$NON-NLS-1$
			System.out.println("LD> Entry reachableBy: info -> " + info);	//$NON-NLS-1$
			System.out.println("");											//$NON-NLS-1$
		}
		return reachableBy((PluginDepInfo) info, new Stack(), new HashMap());
	}
	
	private boolean reachableBy(PluginDepInfo info, Stack stack, Map seen)  {
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
	
	private boolean reachableBy_(PluginDepInfo info, Stack stack, Map seen)  {
		Map testMap = info.parentMap;		
		if (testMap == null || testMap.isEmpty()) {
			return false;
		}
		
		if (testMap.containsKey(getPlugin().getGuid())) {
			if (debug) {
				System.out.println("LD> Contained in parentMap of: " + info);		//$NON-NLS-1$									
			}
			stack.push(this);
			mgr.logCircularDependency((Stack) stack.clone());
			stack.pop();
			return true;
		}
		
		if (seen.containsKey(info.getPlugin().getGuid())) {
			return false;
		}
		
		
		seen.put(info.getPlugin().getGuid(), info);
		
		for (Iterator it = testMap.values().iterator(); it.hasNext();) {
			PluginDepInfo parentInfo = (PluginDepInfo) it.next();
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
	
	public MethodPlugin getPlugin() {
		return plugin;
	}
				
	public String toString() {
		return TngUtil.getLabelWithPath(plugin);
	}
	
	//This would scale up for performance when infoList gets large
	private boolean containedIn(List infoList) {
		int sz = infoList == null ? 0 : infoList.size();
		if (sz == 0) {
			return false;
		}
		String guid = getPlugin().getGuid();
		if (sz == 1) {
			return guid.equals(((PluginDepInfo) infoList.get(0)).getPlugin().getGuid());
		}
		Map listMap = new HashMap();
		for (int i=0; i<sz; i++) {
			PluginDepInfo info = (PluginDepInfo) infoList.get(i);
			listMap.put(info.getPlugin().getGuid(), info);
		}
		
		return listMap.containsKey(guid);
	}
	
}
