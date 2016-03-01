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
package org.eclipse.graphiti.pattern;

import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Interface IFeatureProviderWithPatterns.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients. Extend
 *           {@link DefaultFeatureProviderWithPatterns} instead
 */
public interface IFeatureProviderWithPatterns extends IPatternContainer {

	/**
	 * Activate direct editing for patterns.
	 * 
	 * @param mainPictogramElement
	 *            the main pictogram element
	 * @param bo
	 *            the business object
	 */
	void activateDirectEditingForPatterns(PictogramElement mainPictogramElement, Object bo);

	/**
	 * Activate direct editing for patterns.
	 * 
	 * @param mainPictogramElement
	 *            the main pictogram element
	 * @param bo
	 *            the business object
	 * @param keyProperty
	 *            the key property
	 */
	void activateDirectEditingForPatterns(PictogramElement mainPictogramElement, Object bo, String keyProperty);

	/**
	 * Gets the pattern for pictogram element.
	 * 
	 * @param pictogramElement
	 *            the pictogram element
	 * 
	 * @return the pattern for pictogram element
	 */
	IPattern getPatternForPictogramElement(PictogramElement pictogramElement);
}
