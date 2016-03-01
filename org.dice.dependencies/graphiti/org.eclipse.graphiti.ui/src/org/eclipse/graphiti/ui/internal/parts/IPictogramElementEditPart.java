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
package org.eclipse.graphiti.ui.internal.parts;

import java.util.List;

import org.eclipse.graphiti.features.IFeatureProviderHolder;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.internal.config.IConfigurationProviderHolder;

/**
 * The Interface IPictogramElementEditPart.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public interface IPictogramElementEditPart extends IConfigurationProviderHolder, IFeatureProviderHolder {

	/**
	 * Gets the pictogram element.
	 * 
	 * @return the pictogram element
	 */
	PictogramElement getPictogramElement();

	/**
	 * Gets the model children.
	 * 
	 * @return the model children
	 */
	List<PictogramElement> getModelChildren();

	/**
	 * Gets the model source connections.
	 * 
	 * @return the model source connections
	 */
	List<Connection> getModelSourceConnections();

	/**
	 * Gets the model target connections.
	 * 
	 * @return the model target connections
	 */
	List<Connection> getModelTargetConnections();

	/**
	 * Gets the pictogram element delegate.
	 * 
	 * @return the pictogram element delegate
	 */
	IPictogramElementDelegate getPictogramElementDelegate();
}