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
package org.eclipse.graphiti.internal;

import org.eclipse.core.runtime.Plugin;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GraphitiPlugin extends Plugin {

	public static final String PLUGIN_ID = "org.eclipse.graphiti"; //$NON-NLS-1$
	private static GraphitiPlugin sInstance;

	public GraphitiPlugin() {
		super();
		sInstance = this;
	}

	public static GraphitiPlugin getDefault() {
		return sInstance;
	}

}
