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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodElementProperty;

/**
 * Class managing method element properties
 * 
 * @author Weiping Lu - May 10, 2008
 * @since 1.5
 */
public class MethodElementPropertyMgr {

	private static MethodElementPropertyMgr instance = new MethodElementPropertyMgr();

	private Map<MethodElement, MethodElementProperties> registeredMap = 
							new HashMap<MethodElement, MethodElementProperties>();

	private ListenerList listeners = new ListenerList();
	
	private MethodElementPropertyMgr() {
	}

	public static MethodElementPropertyMgr getInstance() {
		return instance;
	}

	public MethodElementProperties register(MethodElement element,
			String[] propNames) {
		MethodElementProperties props = new MethodElementProperties(element, propNames);
		register(element, props);
		return props;
	}
	
	public void register(MethodElement element,
			MethodElementProperties props) {
		unregister(element);
		registeredMap.put(element, props);
	}

	public MethodElementProperties unregister(MethodElement element) {
		MethodElementProperties props = registeredMap.remove(element);
		if (props != null) {
			props.dispose();
		}		
		return props;
	}
	
	public MethodElementProperty getProperty(MethodElement e, String propName) {
		MethodElementProperties props = registeredMap.get(e);
		if (props != null && props.propNameRegistered(propName)) {
			return props.getProperty(propName);
		} 			
		return MethodElementPropertyHelper.getProperty(e, propName);
	}
	
	public void setProperty(MethodElement e, String propName, String propValue) {
		MethodElementProperties props = registeredMap.get(e);
		if (props != null && props.propNameRegistered(propName)) {
			props.setProperty(propName, propValue);
		} else {
			MethodElementPropertyHelper.setProperty(e, propName, propValue);
		}
	}
		
	public void addListener(ChangeEventListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(ChangeEventListener listener) {
		listeners.remove(listener);
	}


	public static class ChangeEventListener {
		public void notifyChange(ChangeEvent event) {
		}
	}

	public static class ChangeEvent {
		public String propName;
		public MethodElementProperty propElement;
		public String oldValue;
		public String newValue;
		public ChangeEvent(String propName, String oldValue, String newValue) {
			this.propElement = propElement;
			this.oldValue = oldValue;
			this.newValue = newValue;
		}
		public ChangeEvent(MethodElementProperty propElement, String oldValue, String newValue) {
			this(propElement == null ? null : propElement.getName(), oldValue, newValue);
			this.propElement = propElement;
		}
	}

}
