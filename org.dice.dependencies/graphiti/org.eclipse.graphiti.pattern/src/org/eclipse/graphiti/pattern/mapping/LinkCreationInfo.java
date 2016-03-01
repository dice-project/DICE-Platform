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
package org.eclipse.graphiti.pattern.mapping;

/**
 * The Class LinkCreationInfo.
 */
public class LinkCreationInfo implements ILinkCreationInfo {

	private Object[] businessObjects;

	private String property;

	/**
	 * Creates a new {@link LinkCreationInfo}.
	 * 
	 * @param businessObject
	 *            the business object
	 */
	public LinkCreationInfo(Object businessObject) {
		this(new Object[] { businessObject });
	}

	/**
	 * Creates a new {@link LinkCreationInfo}.
	 * 
	 * @param businessObjects
	 *            the business objects
	 */
	public LinkCreationInfo(Object[] businessObjects) {
		this(businessObjects, null);
	}

	/**
	 * Creates a new {@link LinkCreationInfo}.
	 * 
	 * @param businessObjects
	 *            the business objects
	 * @param property
	 *            the property
	 */
	public LinkCreationInfo(Object[] businessObjects, String property) {
		super();
		this.businessObjects = businessObjects;
		this.property = property;
	}

	public Object[] getBusinessObjects() {
		return businessObjects;
	}

	public String getProperty() {
		return property;
	}

}
