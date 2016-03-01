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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.common.utils.ProfilingUtil;
import org.eclipse.epf.library.edit.LibraryEditPlugin;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPlugin;

/**
 * This class manages circular dependency info for plugins.
 * 
 * @author Weiping Lu
 * @since 1.2
 */
public class PluginDependencyInfoMgr {
	
	private static boolean profiling = false;
	private static boolean debug = false;
	private MethodLibrary lib;
	private Map processed;
	private List circularList;
	
	public PluginDependencyInfoMgr(MethodLibrary lib) {
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
	
	private void log(String msg) {
		LibraryEditPlugin.INSTANCE.log(msg);
	}
	
	private PluginDepInfo registerPlugin(MethodPlugin plugin, boolean checkCircular) {		
		PluginDepInfo info =  getProcessedInfo(plugin);
		if (info == null) {		
			info = new PluginDepInfo(this, plugin);
		}
		if (! info.isComplete()) {
			info.build(checkCircular);
		}
		return info;
	}
	
	public IStatus checkCircularDependnecy(MethodPlugin plugin) {
		if (debug) {
			System.out.println("LD> plugin: " + TngUtil.getLabelWithPath(plugin));		//$NON-NLS-1$
		}
		PluginDepInfo info = registerPlugin(plugin, true);
		List cirList = getAndClearCircularList();
		boolean loop = cirList != null && !cirList.isEmpty();
		if (loop) {
			log("Error> Circular dependency detected: ");		//$NON-NLS-1$
			for (int i=0; i<cirList.size(); i++) {
				PluginDepInfo loopInfo = (PluginDepInfo) cirList.get(i);
				MethodElement elem = loopInfo.getPlugin();							
				log("Error> " + i + ": " + TngUtil.getLabelWithPath(elem));//$NON-NLS-1$ //$NON-NLS-2$
			}
				log("");								//$NON-NLS-1$
			return new ValidationStatus(IStatus.ERROR, 0, genErrorMsg(cirList), plugin, null);
		}
		return Status.OK_STATUS;
	}
	
	private String genErrorMsg(List cirList) {
		String msg = LibraryEditResources.variability_element_circular_loop_error_msg;
		for (int i=0; i<cirList.size(); i++) {
			PluginDepInfo loopInfo = (PluginDepInfo) cirList.get(i);
			MethodElement elem = loopInfo.getPlugin();	
			if (i > 0) {
				msg += ": "; //$NON-NLS-1$ 
			}
			msg += TngUtil.getLabelWithPath(elem);	
		}
		return msg;
	}
			
	protected boolean processed(MethodPlugin plugin) {
		return processed != null && processed.containsKey(plugin.getGuid());
	}
	
	protected PluginDepInfo getProcessedInfo(MethodPlugin plugin) {
		return processed == null ? null : (PluginDepInfo) processed.get(plugin.getGuid());
	}
	
	protected void addToProcessed(PluginDepInfo info) {
		if (processed == null) {
			processed = new HashMap();
		}
		processed.put(info.getPlugin().getGuid(), info);
	}
	
	/*
	 * Check circular dependency among the plugins of the library.
	 */
	public CheckResult checkCircularDependnecy(Tracer tracer, boolean accumErrors) {
		if (tracer == null) {
			tracer = new Tracer() {
				public void trace(String line) {}
			};
		}
		long usedMem0 = 0;
		Runtime rt = Runtime.getRuntime(); 
		if (profiling) {
			for (Iterator it = lib.eAllContents(); it.hasNext();) {
				it.next();
			}
			ProfilingUtil.fullGC();
			usedMem0 = rt.totalMemory() - rt.freeMemory();
		}

		CheckResult result = checkCircularDependnecy_(tracer, accumErrors);
		
		if (profiling) {
			ProfilingUtil.fullGC();
			System.out.println("LD> usedMem0: " + usedMem0/1000 + " k bytes"); //$NON-NLS-1$ //$NON-NLS-2$
			long usedMem1 = rt.totalMemory() - rt.freeMemory();
			System.out.println("LD> usedMem1: " + usedMem1/1000 + " k bytes"); //$NON-NLS-1$ //$NON-NLS-2$
			System.out.println("LD> diffMem: " + (usedMem1 - usedMem0)/1000 + " k bytes"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return result;
	}
	
	private CheckResult checkCircularDependnecy_(Tracer tracer, boolean accumErrors) {
		CheckResult result = new CheckResult();
		
		List plugins = lib.getMethodPlugins();
		for (Iterator it = plugins.iterator(); it.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) it.next();
			
			PluginDepInfo info = registerPlugin(plugin, true);
			List cirList = getAndClearCircularList();			
			boolean loop = cirList != null && !cirList.isEmpty();
			if (loop) {
				tracer.trace("Error> Circular dependency detected: ");		//$NON-NLS-1$
				for (int i=0; i<cirList.size(); i++) {
					PluginDepInfo loopInfo = (PluginDepInfo) cirList.get(i);
					MethodElement elem = loopInfo.getPlugin();							
					tracer.trace("Error> " + i + ": " + TngUtil.getLabelWithPath(elem));//$NON-NLS-1$ //$NON-NLS-2$
				}
				result.circularList.add(cirList);
				if (! accumErrors) {
					return result;
				}
			}
		}				
		return result;
	}
	
	public static class CheckResult {
		public List circularList = new ArrayList();
		
		public int getErrorCount() {
			return circularList.size();
		}		
	}
	
}
