//------------------------------------------------------------------------------
// Copyright (c) 2005, 20087 IBM Corporation and others.
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.epf.library.edit.util.MethodElementPropertyMgr.ChangeEvent;
import org.eclipse.epf.library.edit.util.MethodElementPropertyMgr.ChangeEventListener;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.UmaPackage;

/**
 *  Class managing cached method element properties
 * 
 * @author Weiping Lu - May 10, 2008
 * @since 1.5
 */
public class MethodElementProperties extends AdapterImpl {

	private MethodElement element;
	private ListenerList listeners = new ListenerList();
	private Set<String> propNameSet = new LinkedHashSet<String>();
	private Map<String, List<MethodElementProperty>> propertyMap;
	
	public MethodElementProperties(MethodElement element, String[] propNameArray) {
		this.element = element;
		if (propNameArray != null) {
			for (int i = 0; i < propNameArray.length; i++) {
				propNameSet.add(propNameArray[i]);
			}
		}		
		propertyMap = MethodElementPropertyHelper.getPropertyMap(element, getPropNameSet());
	}
	
	protected Set<String> getPropNameSet() {
		return  propNameSet;
	}
	

	public boolean propNameRegistered(String propName) {
		return getPropNameSet() != null && getPropNameSet().contains(propName);
	}
	
	public void notifyChanged(Notification msg) {
		Object obj = msg.getNotifier();
		MethodElementProperty meProp = null;
		if (obj instanceof MethodElementProperty) {
			meProp = (MethodElementProperty) obj;
		} else if (obj == element) {
			if (msg.getFeature() != UmaPackage.eINSTANCE
					.getMethodElement_MethodElementProperty()) {
				return;
			}
		} else {
			return;
		}
		
		//To be implemented: do nothing for now

	}
	
	public MethodElementProperty getProperty(String propName) {
		List<MethodElementProperty> propList = propertyMap.get(propName);
		return propList == null || propList.isEmpty() ? null : propList.get(0);
	}
	
	public void setProperty(String propName, String propValue) {
		List<MethodElementProperty> propList = propertyMap.get(propName);
		if (propList == null) {
			propList = new ArrayList<MethodElementProperty>();
			propertyMap.put(propName, propList);
		}
		MethodElementProperty prop = propList.isEmpty() ? null : propList.get(0);
		if (prop == null) {
			prop = UmaFactory.eINSTANCE.createMethodElementProperty();
			prop.setName(propName);
			element.getMethodElementProperty().add(prop);
			if (propList.isEmpty()) {
				propList.add(prop);
			}
		}
		String oldValue = prop.getValue();		
		if (equal(oldValue, propValue)) {
			return;
		}
		prop.setValue(propValue);		
		notifyListeners(new ChangeEvent(prop, oldValue, propValue));
	}
	
	public boolean getBooleanValue(String propName) {
		MethodElementProperty prop = getProperty(propName);
		if (prop == null) {
			return false;
		}
		String value = prop.getValue();
		return Boolean.TRUE.toString().equals(prop.getValue());
	}
	
	private boolean equal(Object a, Object b) {
		if (a != null) {
			return a.equals(b);
		}		
		return b == null;
	}
		
	public void addListener(ChangeEventListener listener) {
		if (this.listeners != null)
			this.listeners.add(listener);
	}
	
	public void removeListener(ChangeEventListener listener) {
		if (this.listeners != null)
			this.listeners.remove(listener);
	}
	
	private void notifyListeners(ChangeEvent event) {
		if (this.listeners != null) {
			for (Object o : listeners.getListeners()) {
				if (o instanceof ChangeEventListener)
					((ChangeEventListener) o).notifyChange(event);
			}
		}
	}	
	
	public void dispose() {
		element = null;
		listeners = null;
		propNameSet = null;
		propertyMap = null;
		
		//To do: remove from this adapter from all attached Notifier objects
	}
		
}
