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
package org.eclipse.graphiti.ui.internal;

import org.eclipse.graphiti.internal.util.AbstractTracer;

/**
 * This class inherits from
 * <code>org.eclipse.graphiti.util.AbstractTracer</code>. Use it to add trace
 * output for the plugin <code>org.eclipse.graphiti.ui</code>
 * 
 * @see org.eclipse.graphiti.internal.util.AbstractTracer
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public final class T extends AbstractTracer {

	private static T t = new T(GraphitiUIPlugin.PLUGIN_ID);

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
