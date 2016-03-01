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
package org.eclipse.graphiti.platform.ga;

/**
 * The Class VisualStateChangedEvent.
 * 
 * @see IVisualStateChangeListener
 */
public class VisualStateChangedEvent {

	public IVisualState.Type changedField;

	public int oldValue;

	public int newValue;

	/**
	 * Creates a new {@link VisualStateChangedEvent}.
	 * 
	 * @param changedField
	 *            the changed field
	 * @param oldValue
	 *            the int old value
	 * @param newValue
	 *            the int new value
	 */
	public VisualStateChangedEvent(IVisualState.Type changedField, int oldValue, int newValue) {
		this.changedField = changedField;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

}
