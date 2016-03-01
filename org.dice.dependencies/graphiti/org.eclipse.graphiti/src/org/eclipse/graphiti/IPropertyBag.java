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

/**
 * The Interface IPropertyBag.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 * 
 *           Extend {@link PropertyBag} instead
 */
public interface IPropertyBag {
	/**
	 * Associates the specified value with the specified key. If a mapping for
	 * the key already exists, the old value is replaced.
	 * 
	 * @param key
	 *            key with which the specified value is to be associated
	 * @param value
	 *            value to be associated with the specified key
	 * @return the previous value associated with <tt>key</tt>, or <tt>null</tt>
	 *         if there was no mapping for <tt>key</tt>.
	 * 
	 * @see #getProperty(Object)
	 */
	Object putProperty(Object key, Object value);

	/**
	 * Returns the value to which the specified key is mapped, or {@code null}
	 * if there is no mapping for the key.
	 * 
	 * @see #putProperty(Object, Object)
	 */
	Object getProperty(Object key);
}
