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
/*
 * Created on 06.07.2005
 */
package org.eclipse.graphiti.features;

import org.eclipse.graphiti.features.context.IPasteContext;

/**
 * The Interface IPasteFeature.
 * 
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients,
 *              extend {@link AbstractPasteFeature} instead.
 */
public interface IPasteFeature extends IFeature {

	/**
	 * Paste.
	 * 
	 * @param context
	 *            the context
	 */
	void paste(IPasteContext context);

	/**
	 * Can paste.
	 * 
	 * @param context
	 *            the context
	 * 
	 * @return true, if successful
	 */
	boolean canPaste(IPasteContext context);
}