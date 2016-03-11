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
package org.eclipse.epf.migration.diagram.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.configuration.ProcessConfigurator;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * Helper utilities for the Migration Service.
 *
 * @author Kelvin Low
 * @since  1.0
 */
public class MigrationUtil {

	/**
	 * Returns a map of all the <code>CapabilityPattern</code> and <code>DeliveryProcess</code>
	 * in the current Method Library.
	 *
	 * TODO: Move this method to the com.ibm.rmm.library.services.LibraryUtil class.
	 */
	public static Map getProcesses() {
		MethodLibrary library = LibraryService.getInstance()
		.getCurrentMethodLibrary();
		if (library == null) {
			return null;
		}

		Map result = new TreeMap();
		List methodPlugins = LibraryUtil.getMethodPlugins(library);
		for (Iterator i = methodPlugins.iterator(); i.hasNext(); ) {
			MethodPlugin methodPlugin = (MethodPlugin)i.next();
			String capabilityPatternPaths[] = ModelStructure.DEFAULT.capabilityPatternPath;
			MethodPackage methodPackage  = UmaUtil.findMethodPackage(methodPlugin, capabilityPatternPaths);
			if (methodPackage instanceof ProcessPackage) {
				if (methodPackage instanceof ProcessPackage) {
					getCapabilityPatterns((ProcessPackage)methodPackage, result);
				}
			}
			String deliveryProcessPaths[] = ModelStructure.DEFAULT.deliveryProcessPath;
			methodPackage = UmaUtil.findMethodPackage(methodPlugin, deliveryProcessPaths);
			if (methodPackage instanceof ProcessPackage) {
				if (methodPackage instanceof ProcessPackage) {
					getDeliveryProcesses((ProcessPackage)methodPackage, result);
				}
			}
		}
		return result;
	}

	/**
	 * Returns a map of all <code>CapabilityPattern</code> in the current Method Library.
	 *
	 * TODO: Move this method to the com.ibm.rmm.library.services.LibraryUtil class.
	 */
	public static Map getCapabilityPatterns() {
		MethodLibrary library = LibraryService.getInstance()
		.getCurrentMethodLibrary();
		if (library == null) {
			return null;
		}

		Map result = new TreeMap();
		List methodPlugins = LibraryUtil.getMethodPlugins(library);
		for (Iterator i = methodPlugins.iterator(); i.hasNext(); ) {
			MethodPlugin methodPlugin = (MethodPlugin)i.next();
			String capabilityPatternPaths[] = ModelStructure.DEFAULT.capabilityPatternPath;
			MethodPackage methodPackage  = UmaUtil.findMethodPackage(methodPlugin, capabilityPatternPaths);
			if (methodPackage instanceof ProcessPackage) {
				getCapabilityPatterns((ProcessPackage)methodPackage, result);
			}
		}
		return result;
	}

	/**
	 * Returns a map of all the <code>DeliveryProcess</code> in the current Method Library.
	 *
	 * TODO: Move this method to the com.ibm.rmm.library.services.LibraryUtil class.
	 */
	public static Map getDeliveryProcesses() {
		MethodLibrary library = LibraryService.getInstance()
		.getCurrentMethodLibrary();
		if (library == null) {
			return null;
		}

		Map result = new TreeMap();
		List methodPlugins = LibraryUtil.getMethodPlugins(library);
		for (Iterator i = methodPlugins.iterator(); i.hasNext(); ) {
			MethodPlugin methodPlugin = (MethodPlugin)i.next();
			String deliveryProcessPaths[] = ModelStructure.DEFAULT.deliveryProcessPath;
			MethodPackage methodPackage = UmaUtil.findMethodPackage(methodPlugin, deliveryProcessPaths);
			if (methodPackage instanceof ProcessPackage) {
				getDeliveryProcesses((ProcessPackage)methodPackage, result);
			}
		}
		return result;
	}

	/**
	 * Returns a map of all the contexts associated with a process.
	 *
	 * TODO: Move this method to the com.ibm.rmm.library.services.LibraryUtil class.
	 */
	public static Map getContexts(Process process) {
		if (process == null) {
			return null;
		}

		MethodLibrary library = LibraryService.getInstance()
		.getCurrentMethodLibrary();
		if (library == null) {
			return null;
		}

		Map result = new TreeMap();
		MethodConfiguration defaultContext = process.getDefaultContext();
		if (defaultContext != null) {
			result.put(defaultContext.getName(), defaultContext);
		}

		List contexts = process.getValidContext();
		for (Iterator i = contexts.iterator(); i.hasNext(); ) {
			MethodConfiguration context = (MethodConfiguration)i.next();
			if (context != null) {
				result.put(context.getName(), context);
			}
		}

		return result;
	}

	/**
	 * Add a map of all <code>CapabilityPattern</code> in a given <code>ProcessPackage</code>.
	 *
	 * TODO: Move this method to the com.ibm.rmm.library.services.LibraryUtil class.
	 */
	protected static void getCapabilityPatterns(ProcessPackage processPackage, Map result) {
		List childPackages = processPackage.getChildPackages();
		for (Iterator i = childPackages.iterator(); i.hasNext(); ) {
			Object obj = i.next();
			if (obj instanceof ProcessComponent) {
				ProcessComponent processComponent = (ProcessComponent)obj;
				Process process  = processComponent.getProcess();
				if (process instanceof CapabilityPattern) {
					String name = process.getName();
					result.put(name, process);
				}
			}
			else if (obj instanceof ProcessPackage) {
				getCapabilityPatterns((ProcessPackage)obj, result);
			}
		}
	}

	/**
	 * Add a map of all <code>DeliveryProcess</code> in a given <code>ProcessPackage</code>.
	 *
	 * TODO: Move this method to the com.ibm.rmm.library.services.LibraryUtil class.
	 */
	protected static void getDeliveryProcesses(ProcessPackage processPackage, Map result) {
		List childPackages = processPackage.getChildPackages();
		for (Iterator i = childPackages.iterator(); i.hasNext(); ) {
			Object obj = i.next();
			if (obj instanceof ProcessComponent) {
				ProcessComponent processComponent = (ProcessComponent)obj;
				Process process  = processComponent.getProcess();
				if (process instanceof DeliveryProcess) {
					String name = process.getName();
					result.put(name, process);
				}
			}
			else if (obj instanceof ProcessPackage) {
				getDeliveryProcesses((ProcessPackage)obj, result);
			}
		}
	}
	
	public static Map getActivities(Process process){
		return getActivities(process, false);
	}
	
	public static Map getActivities(Process process, boolean linkedHashMap){
		Map result = linkedHashMap ? new LinkedHashMap() : new TreeMap();
		MethodConfiguration defaultContext = process.getDefaultContext();
		ProcessConfigurator filter = new ProcessConfigurator(defaultContext);
		getActivities(process, result, filter);
		return result;
	}
	
	/**
	 * Method returns List of activities (recursively) for particular configuration 
	 * under a Process (<code>DeliveryProcess</code> or <code>CapabilityPattern</code>).  
	 *  @param Activity
	 *  @param Map
	 *  @param ProcessConfigurator
	 */
	protected static void getActivities(Activity process, Map result, ProcessConfigurator filter){
		result.put(process.getName(), process);
		List list = process.getBreakdownElements();
		if(list.size() > 0){
			for(Iterator iterator = list.iterator(); iterator.hasNext();){
				Object object = iterator.next();
				if(object instanceof Activity){
					if(filter != null && filter.accept(object)){
						result.put(((Activity)object).getName(), object);
					}
					if(object instanceof Activity){
						getActivities((Activity)object, result, filter);
					}
				}
			}
		}
	}

}
