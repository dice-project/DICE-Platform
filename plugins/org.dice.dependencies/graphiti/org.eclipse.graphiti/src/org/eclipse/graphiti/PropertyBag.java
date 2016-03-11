/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti;

import java.util.HashMap;

/**
 * The class PropertyBag.
 */
public class PropertyBag implements IPropertyBag {
	private HashMap<Object, Object> propertyMap;

	@Override
	public Object getProperty(Object key) {
		return getPropertyMap().get(key);
	}

	@Override
	public Object putProperty(Object key, Object value) {
		return getPropertyMap().put(key, value);
	}

	private HashMap<Object, Object> getPropertyMap() {
		if (this.propertyMap == null) {
			this.propertyMap = new HashMap<Object, Object>();
		}
		return this.propertyMap;
	}

}
