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

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProvider;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderHolder;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class AbstractCommand extends Command implements IConfigurationProviderHolder {

	private IConfigurationProvider configurationProvider;

	public AbstractCommand(IConfigurationProvider configurationProvider, String label) {
		super(label);
		this.configurationProvider = configurationProvider;
	}

	public AbstractCommand(IConfigurationProvider configurationProvider) {
		super();
		this.configurationProvider = configurationProvider;
	}

	public IConfigurationProvider getConfigurationProvider() throws IllegalStateException {
		return configurationProvider;
	}

	protected IFeatureProvider getFeatureProvider() {
		return getConfigurationProvider().getDiagramTypeProvider().getFeatureProvider();
	}

	protected TransactionalEditingDomain getTransactionalEditingDomain() {
		return configurationProvider.getDiagramEditor().getEditingDomain();
	}
}