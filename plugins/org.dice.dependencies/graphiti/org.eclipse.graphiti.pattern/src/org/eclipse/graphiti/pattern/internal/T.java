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
package org.eclipse.graphiti.pattern.internal;

import org.eclipse.graphiti.internal.util.AbstractTracer;

/**
 * This class inherits from
 * <code>org.eclipse.graphiti.util.AbstractTracer</code>. Use it to add trace
 * output for the plugin <code>org.eclipse.graphiti.pattern</code>
 * 
 * @see org.eclipse.graphiti.internal.util.AbstractTracer
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public final class T extends AbstractTracer {

	public static final String PLUGIN_ID = "org.eclipse.graphiti.pattern"; //$NON-NLS-1$

	private static T t = new T(PLUGIN_ID);

	/**
	 * instantiate via <code>T.racer()</code>
	 */
	private T(String location) {
		super(location);
	}

	/**
	 * returns an instance of
	 * <code>org.eclipse.graphiti.util.AbstractTracer</code>
	 */
	public static T racer() {
		return t;
	}
}
