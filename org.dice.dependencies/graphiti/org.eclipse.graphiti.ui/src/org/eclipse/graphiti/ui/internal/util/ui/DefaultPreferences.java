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
package org.eclipse.graphiti.ui.internal.util.ui;

/**
 * A generic interface for preference containers (e.g. print, saveas, ...),
 * which serves as abstraction for generic edit fields which store their values
 * in a preference object
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface DefaultPreferences {

	public void setDefaults();

	public void setIntPreference(int atIndex, int value);

	public void setDoublePreference(int atIndex, double value);

	public int getIntPreference(int atIndex);

	public double getDoublePreference(int atIndex);
}