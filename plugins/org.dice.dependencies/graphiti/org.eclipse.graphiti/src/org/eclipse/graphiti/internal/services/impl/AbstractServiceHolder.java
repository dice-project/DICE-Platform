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
package org.eclipse.graphiti.internal.services.impl;

import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeService;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
abstract class AbstractServiceHolder {

	private IGaService gaService;
	private IPeService peService;

	public AbstractServiceHolder() {
		super();
	}

	protected IGaService getGaService() {
		if (gaService == null) {
			gaService = Graphiti.getGaService();
		}
		return gaService;
	}

	protected IPeService getPeService() {
		if (peService == null) {
			peService = Graphiti.getPeService();
		}
		return peService;
	}
}