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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.util.AssociationHelper;
import org.eclipse.epf.uma.util.UmaUtil;

/**
 * Class with static utility methods for querying method element
 * 
 * @author Shilpa Toraskar
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MethodElementUtil {

	/**
	 * Return method model of the object
	 * 
	 * @param obj
	 * @return
	 */
	public static MethodPlugin getMethodModel(Object obj) {
		return UmaUtil.getMethodPlugin((MethodElement) obj);
	}

	/**
	 * Returns list of models as specified.. 1. Method model of the object 2.
	 * Referenced models of the method model of the object (e.g. reference
	 * relationship)
	 * 
	 * @param object
	 * @return
	 */
	public static List getAllModels(Object object) {
		List allModels = new ArrayList();

		// get method model of the object
		MethodPlugin model = getMethodModel(object);

		if (model != null) {

			// get all base models
			//
			//Misc.getAllBase(model, allModels);
			allModels.addAll(Misc.getAllBase1(model));

			allModels.add(model);

		}

		return allModels;
	}

	/**
	 * Returns references guidances for the given object
	 * 
	 * @param object
	 * @return
	 */
	public static List getSelectedGuidances(EObject object) {
		List itemList = new ArrayList();

		List references = ((EObject) object).eCrossReferences();
		if (references != null && references.size() > 0) {
			for (Iterator it = references.iterator(); it.hasNext();) {
				Object obj = (Object) it.next();
				if (obj instanceof Guidance) {
					itemList.add(obj);
				}
			}
		}
		
//		This is a bug
//		itemList
//				.addAll(AssociationHelper.getPractices((ContentElement) object));

		List<Practice> utdItems = MethodElementPropUtil.getMethodElementPropUtil().getUdtList((ContentElement) object, false);
		if (utdItems != null && ! utdItems.isEmpty()) {
			Set set = new HashSet();
			set.addAll(itemList);
			for (Practice p : utdItems) {
				if (! set.contains(p)) {
					itemList.add(p);
					set.add(p);
				}
			}
		}		
		return itemList;
	}
	
	/**
	 * Returns a map built from the given elements. The map has guids as keys
	 */
	public static Map<String, MethodElement> buildMap(Collection<? extends MethodElement> elements) {
		Map<String, MethodElement> map = new HashMap<String, MethodElement>();
		
		if (elements != null && !elements.isEmpty()) {
			for (Iterator<? extends MethodElement> it = elements.iterator(); it.hasNext();) {
				MethodElement element = it.next();
				map.put(element.getGuid(), element);
			}
		}
		
		return map;
	}

}