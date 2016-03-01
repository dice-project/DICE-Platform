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
/**
 * 
 */
package org.eclipse.graphiti.ui.services;

import org.eclipse.graphiti.dt.IDiagramType;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.Diagram;

/**
 * The Interface IExtensionManager.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 * @noextend This interface is not intended to be extended by clients.
 */
public interface IExtensionManager {

	/**
	 * Gets the diagram type provider id's.
	 * 
	 * @param diagramTypeId
	 *            the diagram type id
	 * 
	 * @return provider id's of all the diagram type providers which can handle
	 *         the given diagram type id.
	 */
	String[] getDiagramTypeProviderIds(String diagramTypeId);

	/**
	 * Gets the diagram type provider id.
	 * 
	 * @param diagramTypeId
	 *            the diagram type id
	 * 
	 * @return provider id of the diagram type providers which can handle the
	 *         given diagram type id. If more then one diagram type providers
	 *         available, the first one will be returned.
	 */
	String getDiagramTypeProviderId(String diagramTypeId);

	/**
	 * Creates a diagram type provider.
	 * 
	 * @param providerId
	 *            the provider id
	 * 
	 * @return new instance of a diagram type provider
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider
	 */
	IDiagramTypeProvider createDiagramTypeProvider(String providerId);

	/**
	 * Gets all the registered diagram types.
	 * 
	 * @return the registered diagram types
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramType
	 */
	IDiagramType[] getDiagramTypes();

	/**
	 * Create an instance of a feature provider (and also the diagram type
	 * provider) without having a diagram editor open. This instance can be used
	 * for the generation of diagrams in the background. All features which do
	 * not expect an open editor can be reused.
	 * 
	 * @param diagram
	 *            The diagram on which the diagram type provider will work
	 * 
	 * @return new instance of a feature provider
	 * 
	 * @see org.eclipse.graphiti.features.IFeatureProvider
	 */
	IFeatureProvider createFeatureProvider(Diagram diagram);

	/**
	 * Create an instance of a diagram type provider (and also the feature
	 * provider) without having a diagram editor open. This instance can be used
	 * for the generation of diagrams in the background. All features which do
	 * not expect an open editor can be reused.
	 * 
	 * @param diagram
	 *            The diagram on which the diagram type provider will work
	 * @param providerId
	 *            Id of the diagram type provider which should be used
	 * 
	 * @return new instance of a diagram type provider
	 * 
	 * @see org.eclipse.graphiti.dt.IDiagramTypeProvider
	 */
	IDiagramTypeProvider createDiagramTypeProvider(Diagram diagram, String providerId);
}
