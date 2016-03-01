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
package org.eclipse.graphiti.ui.internal.requests;

import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.graphiti.tb.ContextButtonEntry;

/**
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class ContextButtonDragRequest extends CreateConnectionRequest {

	private ContextButtonEntry contextButtonEntry;

	public ContextButtonEntry getContextButtonEntry() {
		return contextButtonEntry;
	}

	public void setContextButtonEntry(ContextButtonEntry contextButtonEntry) {
		this.contextButtonEntry = contextButtonEntry;
	}

}
