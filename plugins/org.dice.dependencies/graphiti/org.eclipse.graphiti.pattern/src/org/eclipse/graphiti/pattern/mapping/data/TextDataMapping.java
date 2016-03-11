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
package org.eclipse.graphiti.pattern.mapping.data;

import org.eclipse.graphiti.features.IMappingProvider;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;

/**
 * The Class TextDataMapping.
 */
public abstract class TextDataMapping extends DataMapping implements ITextDataMapping {

	/**
	 * Creates a new {@link TextDataMapping}.
	 * 
	 * @param mappingProvider
	 *            the mapping provider
	 */
	public TextDataMapping(IMappingProvider mappingProvider) {
		super(mappingProvider);
	}

	public String getText(PictogramLink link) {
		return ""; //$NON-NLS-1$
	}
}
