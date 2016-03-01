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
package org.eclipse.graphiti.ui.internal.util.gef;

import java.util.List;

import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.graphiti.features.IFeature;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class MultiCreationFactory implements CreationFactory {
	private List<IFeature> features;

	public MultiCreationFactory(List<IFeature> features) {
		this.features = features;
	}

	public Object getNewObject() {
		return features;
	}

	public Object getObjectType() {
		return null;
	}

}
