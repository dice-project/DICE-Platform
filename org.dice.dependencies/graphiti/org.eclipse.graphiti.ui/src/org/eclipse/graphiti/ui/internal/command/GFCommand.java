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
package org.eclipse.graphiti.ui.internal.command;

import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFCommand extends AbstractCommand {

	private IContext context = null;

	private IFeature feature = null;

	public GFCommand(IConfigurationProvider configurationProvider) {
		super(configurationProvider);
	}

	public GFCommand(IConfigurationProvider configurationProvider, String label) {
		super(configurationProvider, label);
	}

	public IContext getContext() {
		return context;
	}

	public IFeature getFeature() {
		return feature;
	}

	public void setFeature(IFeature feature) {
		this.feature = feature;
	}

	public void setContext(IContext context) {
		this.context = context;
	}

}
