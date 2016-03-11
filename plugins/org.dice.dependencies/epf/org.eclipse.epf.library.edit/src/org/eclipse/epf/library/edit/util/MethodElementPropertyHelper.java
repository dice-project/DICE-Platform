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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.UmaFactory;

/**
 * @author Phong Nguyen Le
 * @author Weiping Lu
 *
 * @since 1.2
 */
public class MethodElementPropertyHelper {
	public static final String WORK_ORDER__PREDECESSOR_PROCESS_PATH = "pred_process_path"; //$NON-NLS-1$
	public static final String WORK_ORDER__SUCCESSOR = "successor"; //$NON-NLS-1$
	public static final String WORK_ORDER__PREDECESSOR_IS_SIBLING = "predecessor_is_sibling"; //$NON-NLS-1$
	public static final String CONFIG_UPDATE_ON_CLICK = "update_on_click"; //$NON-NLS-1$
	public static final String CONFIG_NO_UPDATE_ON_CLICK = "no_update_on_click"; //$NON-NLS-1$
	public static final String CONFIG_PROPBLEM_HIDE_ERRORS = "hide_errors"; //$NON-NLS-1$
	public static final String CONFIG_PROPBLEM_HIDE_WARNINGS = "hide_warnings"; //$NON-NLS-1$
	public static final String CONFIG_PROPBLEM_HIDE_INFOS = "hide_infos"; //$NON-NLS-1$
	public static final String CUSTOM_CATEGORY__INCLUDED_ELEMENTS = "include"; //$NON-NLS-1$
	
	public static final MethodElementProperty getProperty(MethodElement e, String propertyName) {
		if (e != null) {
			for (Iterator iter = e.getMethodElementProperty().iterator(); iter.hasNext();) {
				MethodElementProperty prop = (MethodElementProperty) iter.next();
				if(prop.getName() != null && prop.getName().equals(propertyName)) {
					return prop;
				}
			}
		}
		return null;
	}
	
	public static final void setProperty(MethodElement e, String propName, String propValue) {
		MethodElementProperty prop = getProperty(e, propName);
		if (prop == null) {
			prop = UmaFactory.eINSTANCE.createMethodElementProperty();
			prop.setName(propName);
			e.getMethodElementProperty().add(prop);
		}
		if (propValue != null && propValue.equals(prop.getValue())) return;
		if (propValue == null && prop.getValue() == null) return;
		prop.setValue(propValue);
	}

	public static final void setProperty(MethodElement e, String propName, boolean b) {
		setProperty(e, propName, String.valueOf(b));
	}

	public static final void removeProperty(MethodElement e, String propName) {
		MethodElementProperty property = getProperty(e, propName);
		if (property != null) {
			e.getMethodElementProperty().remove(property);
		}
	}
	
	public static final List<MethodElementProperty> getPropertyList(MethodElement e, String propertyName) {
		if (e == null) {
			return null;
		}		
		List<MethodElementProperty> allProps = e.getMethodElementProperty();		
		if (allProps == null || allProps.isEmpty()) {
			return null;
		}
	
		List<MethodElementProperty> propList = new ArrayList<MethodElementProperty>();
		for (MethodElementProperty prop: allProps) {
			if(prop.getName().equals(propertyName)) {
				propList.add(prop);
			}
		}

		return propList.isEmpty() ? null : propList;
	}
	
	public static final Map<String, List<MethodElementProperty>> getPropertyMap(MethodElement e, Collection<String> propertyNames) {
		Map propertyMap = new HashMap<String, List<MethodElementProperty>>();
		if (e == null || propertyNames == null || propertyNames.isEmpty()) {
			return propertyMap;
		}
	
		for (String propName: propertyNames) {
			List<MethodElementProperty> propList = getPropertyList(e, propName);
			propertyMap.put(propName, propList);
		}
		return propertyMap;
	}
	
}
