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
package org.eclipse.epf.library.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.library.edit.validation.DependencyChecker;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.uma.util.AssociationHelper;

/**
 * utility class to check plugin references
 * @author ???
 * @since 1.0
 */
public class PluginReferenceChecker {

	/**
	 * 
	 * @return List
	 */
//	public static List checkCircularDependentPluginsInLibrary() {
//		List cdList = new ArrayList();
//
//		List allPluginList = ModelStorage.getBaseModels();
//		for (Iterator iter = allPluginList.iterator(); iter.hasNext();) {
//			MethodPlugin element = (MethodPlugin) iter.next();
//			if (hasCircularConflictWithPlugin(element))
//				cdList.add(element);
//		}
//		return cdList;
//	}

	/**
	 * 
	 * @param aPlugin
	 * @return boolean
	 */
	public static boolean hasCircularConflictWithPlugin(MethodPlugin aPlugin) {		
//		boolean answer = false;
//
//		List allowableBaseList = getApplicableBasePlugins(aPlugin);
//		List extendedBaseList = aPlugin.getBases();
//
//		if (!allowableBaseList.containsAll(extendedBaseList)) {
//			System.out
//					.println("$$$: circular dependency detected for " + aPlugin.getName()); //$NON-NLS-1$
//			printPluginList("allowable plugin list", allowableBaseList); //$NON-NLS-1$
//			printPluginList("current base list", extendedBaseList); //$NON-NLS-1$
//			answer = true;
//		}
//
//		return answer;
		
		for (Iterator iter = aPlugin.getBases().iterator(); iter.hasNext();) {
			Object base = (Object) iter.next();
			if(!DependencyChecker.checkCircularDependency(aPlugin, UmaPackage.Literals.METHOD_PLUGIN__BASES, base).isOK()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param aPlugin
	 * @return List
	 */
	public static List<MethodPlugin> getApplicableBasePlugins(MethodPlugin aPlugin) {
		List<MethodPlugin> models = ModelStorage.getBaseModels();

		List<MethodPlugin> descendantList = getDescendants(aPlugin);
		for (Iterator<MethodPlugin> iter = descendantList.iterator(); iter.hasNext();) {
			MethodPlugin element = (MethodPlugin) iter.next();
			if (aPlugin.getBases().contains(element)) {		//part of 170367: allow it to show for unselect
				continue;
			}
			models.remove(element);
		}

		return models;
	}

	// those two getDescendants() methods should be moved into lower layer
	private static List<MethodPlugin> getDescendants(MethodPlugin methodObject) {
		List<MethodPlugin> descendantList = new ArrayList<MethodPlugin>();

		List<MethodPlugin> objList = new ArrayList<MethodPlugin>();
		objList.add(methodObject);

		getDescendants(descendantList, objList);

		return descendantList;
	}

	private static List<MethodPlugin> getDescendants(List<MethodPlugin> allDescendantList,
			List<MethodPlugin> methodObjectList) {
		if (methodObjectList.isEmpty())
			return allDescendantList;

		List<MethodPlugin> combDescendantList = new ArrayList<MethodPlugin>();

		for (Iterator<MethodPlugin> iter = methodObjectList.iterator(); iter.hasNext();) {
			MethodPlugin element = (MethodPlugin) iter.next();
			List<MethodPlugin> descendantList = AssociationHelper
					.getPluginDirectExtensions(element);
			combDescendantList.addAll(descendantList);

		}

		allDescendantList.addAll(methodObjectList);
		List<MethodPlugin> nextCheckList = new ArrayList<MethodPlugin>();
		for (Iterator<MethodPlugin> iter = combDescendantList.iterator(); iter.hasNext();) {
			MethodPlugin element = iter.next();
			if (!allDescendantList.contains(element))
				nextCheckList.add(element);
		}

		return getDescendants(allDescendantList, nextCheckList);
	}

	/**
	 * 
	 * @param desc
	 * @param pluginList
	 */
	public static void printPluginList(String desc, List pluginList) {
		System.out.print("$$$ " + desc + ": ["); //$NON-NLS-1$ //$NON-NLS-2$
		for (Iterator iterator = pluginList.iterator(); iterator.hasNext();) {
			MethodPlugin plugin = (MethodPlugin) iterator.next();
			System.out.print(plugin.getName() + ", "); //$NON-NLS-1$
		}
		System.out.println("]"); //$NON-NLS-1$
	}

}
