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
package org.eclipse.graphiti.internal.command;

import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

/**
 * The Class AddFeatureCommandWithContext.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class AddFeatureCommandWithContext extends GenericFeatureCommandWithContext {

	/**
	 * The added pictogram element.
	 */
	PictogramElement addedPictogramElement;

	/**
	 * Instantiates a new adds the feature command with context.
	 * 
	 * @param feature
	 *            the feature
	 * @param context
	 *            the context
	 */
	public AddFeatureCommandWithContext(IFeature feature, IContext context) {
		super(feature, context);
	}

	/**
	 * Gets the added pictogram elements.
	 * 
	 * @return the added pictogram elements
	 */
	public PictogramElement getAddedPictogramElements() {
		return addedPictogramElement;
	}

	private void setAddedPictogramElements(PictogramElement newPictogramElements) {
		this.addedPictogramElement = newPictogramElements;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.graphiti.internal.command.GenericFeatureCommandWithContext
	 * #execute()
	 */
	@Override
	public boolean execute() {
		boolean ret = false;
		if (getContext() instanceof IAddContext && getFeature() instanceof IAddFeature) {
			IAddFeature addFeature = (IAddFeature) getFeature();
			IAddContext addContext = (IAddContext) getContext();
			if (addFeature.canAdd(addContext)) {
				setAddedPictogramElements(addFeature.add(addContext));
				ret = true;
			}
		}
		return ret;
	}
}
