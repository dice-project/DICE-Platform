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
package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.NamedElement;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.WorkProduct;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * Many methods of this class had been moved to org.eclipse.epf.uma.util.UmaUtil
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public final class Misc {

	public static void getAllWorkProducts(ContentPackage pkg, List list) {
		List elements = pkg.getContentElements();
		for (int i = 0; i < elements.size(); i++) {
			Object obj = elements.get(i);
			if (obj instanceof WorkProduct) {
				list.add(obj);
			}
		}
		List packages = pkg.getChildPackages();
		for (int i = 0; i < packages.size(); i++) {
			Object obj = packages.get(i);
			if (obj instanceof ContentPackage) {
				getAllWorkProducts((ContentPackage) obj, list);
			}
		}
	}

	public static List getAllWorkProducts(ContentPackage pkg) {
		List list = new ArrayList();
		getAllWorkProducts(pkg, list);
		return list;
	}

	private static void getAllTasks(ContentPackage pkg, List list) {
		List elements = pkg.getContentElements();
		for (int i = 0; i < elements.size(); i++) {
			Object obj = elements.get(i);
			if (obj instanceof Task) {
				list.add(obj);
			}
		}
		List packages = pkg.getChildPackages();
		for (int i = 0; i < packages.size(); i++) {
			Object obj = packages.get(i);
			if (obj instanceof ContentPackage) {
				getAllTasks((ContentPackage) obj, list);
			}
		}
	}

	public static List getAllTasks(ContentPackage pkg) {
		List list = new ArrayList();
		getAllTasks(pkg, list);
		return list;
	}

	public static List getAllUncategorizedTasks(ContentPackage pkg) {
		List list = new ArrayList();
		getAllUncategorizedTasks(pkg, list);
		return list;
	}

	private static void getAllUncategorizedTasks(ContentPackage pkg, List list) {
		List elements = pkg.getContentElements();
		for (int i = 0; i < elements.size(); i++) {
			Object obj = elements.get(i);
			if (obj instanceof Task
					&& AssociationHelper.getDisciplines((Task) obj).isEmpty()) {
				list.add(obj);
			}
		}
		List packages = pkg.getChildPackages();
		for (int i = 0; i < packages.size(); i++) {
			Object obj = packages.get(i);
			if (obj instanceof ContentPackage) {
				getAllUncategorizedTasks((ContentPackage) obj, list);
			}
		}
	}

	public static String[] getPathRelativeToPlugin(NamedElement e) {
		MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
		ArrayList list = new ArrayList();
		list.add(String.valueOf(e.getName()));
		for (e = (NamedElement) ((EObject) e).eContainer(); e != null
				&& e != plugin; e = (NamedElement) ((EObject) e).eContainer()) {
			list.add(0, String.valueOf(e.getName()));
		}
		String[] path = new String[list.size()];
		list.toArray(path);
		return path;
	}
	
	public static Collection<NamedElement> getObjectTreeRelativeToPlugin(NamedElement e) {
		MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
		ArrayList<NamedElement> list = new ArrayList<NamedElement>();
		list.add(e);
		for (e = (NamedElement)e.eContainer(); e != null
				&& e != plugin; e = (NamedElement)e.eContainer()) {
			list.add(0, e);
		}
		return list;
	}


	public static String getPathRelativeToLibrary(NamedElement e) {
		if (e == null)
			return ""; //$NON-NLS-1$
		MethodPlugin plugin = UmaUtil.getMethodPlugin(e);
		if (plugin == null)
			return e.getName();
		StringBuffer path = new StringBuffer(e.getName());
		String[] arr = getPathRelativeToPlugin(e);
		for (int i = 0; i < arr.length; i++) {
			path.append('/').append(arr[i]);
		}
		return path.toString();
	}
	
	private static Map<MethodPlugin, List> map = new HashMap<MethodPlugin, List>();
	public static void clearCachedMap() {
		map.clear();
	}
	public static List getAllBase1(MethodPlugin plugin) {
		List list = map.get(plugin);
		if (list == null) {
			list = new ArrayList();
			getAllBase(plugin, list, new HashSet<MethodPlugin>());
			map.put(plugin, list);
		}
		return list;
	}
	
	/**
	 * Gets all base plugins of the given plugin
	 * 
	 * @param plugin
	 * @return
	 */
	public static List getAllBase(MethodPlugin plugin) {
		List list = new ArrayList();
		getAllBase(plugin, list, new HashSet<MethodPlugin>());
		return list;
	}

//	public static void getAllBase(MethodPlugin plugin, List list) {
//		List base = plugin.getBases();
//
//		// Check if the bases in the list already.
//		if (!list.containsAll(base)) {
//			list.addAll(base);
//		}
//		for (int i = 0; i < base.size(); i++) {
//			plugin = (MethodPlugin) base.get(i);
//			getAllBase(plugin, list);
//		}
//	}

	//wlu: 0819-2011 rewrite
	public static void getAllBase(MethodPlugin plugin, List list, Set<MethodPlugin> inListSet) {
		if (plugin == null) {
			return;
		}
		for (MethodPlugin base : plugin.getBases()) {
			if (base != plugin && ! inListSet.contains(base)) {
				list.add(base);
				inListSet.add(base);
				getAllBase(base, list, inListSet);
			}
		}						
	}
	
	public static boolean isBaseOf(MethodPlugin base, MethodPlugin plugin,
			Map<String, Boolean> resultCacheMap) {
		if (resultCacheMap == null) {
			return isBaseOf_(base, plugin, resultCacheMap);
		}
		String key = base.getGuid() + plugin.getGuid();
		Boolean value = resultCacheMap.get(key);
		if (value != null) {
			return value.booleanValue();
		}
		
		boolean b = isBaseOf_(base, plugin, resultCacheMap);
		resultCacheMap.put(key, Boolean.valueOf(b));
		return b;
	}
	
	private static boolean isBaseOf_(MethodPlugin base, MethodPlugin plugin,
			Map<String, Boolean> resultCacheMap) {
		if (plugin == null) {
			System.out.println();
		}
		List bases = plugin.getBases();
		int size = bases.size();
		for (int i = 0; i < size; i++) {
			if (base == bases.get(i))
				return true;
		}
		for (int i = 0; i < size; i++) {
			if (isBaseOf(base, (MethodPlugin) bases.get(i), resultCacheMap))
				return true;
		}
		return false;
	}

}
