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

import org.eclipse.graphiti.ui.internal.parts.directedit.IDirectEditHolder;

/**
 * This direct edit request extends the existing one with a direct edit holder
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class GFDirectEditRequest extends org.eclipse.gef.requests.DirectEditRequest {

	private IDirectEditHolder directEditHolder;

	/**
	 * returns the IDirectEditHolder
	 * 
	 * @return IDirectEditHolder
	 */
	public IDirectEditHolder getDirectEditHolder() {
		return directEditHolder;
	}

	/**
	 * sets the IDirectEditHolder
	 * 
	 * @param directEditHolder
	 */
	public void setDirectEditingContext(IDirectEditHolder directEditHolder) {
		this.directEditHolder = directEditHolder;
	}
}
