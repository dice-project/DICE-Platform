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
package org.eclipse.graphiti.platform.ga;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IMappingProvider;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.PlatformGraphicsAlgorithm;

/**
 * The Class RendererContext.
 */
public class RendererContext implements IRendererContext {

	private PlatformGraphicsAlgorithm platformGraphicsAlgorithm;

	private IDiagramTypeProvider diagramTypeProvider;

	/**
	 * Creates a new {@link RendererContext}.
	 * 
	 * @param pga
	 *            the platform graphics algorithm
	 * @param dtp
	 *            the diagram type provider
	 */
	public RendererContext(PlatformGraphicsAlgorithm pga, IDiagramTypeProvider dtp) {
		setPlatformGraphicsAlgorithm(pga);
		setDiagramTypeProvider(dtp);
	}

	public IMappingProvider getMappingProvider() {
		return getDiagramTypeProvider().getFeatureProvider();
	}

	public PlatformGraphicsAlgorithm getPlatformGraphicsAlgorithm() {
		return this.platformGraphicsAlgorithm;
	}

	public IDiagramTypeProvider getDiagramTypeProvider() {
		return this.diagramTypeProvider;
	}

	public GraphicsAlgorithm getGraphicsAlgorithm() {
		return getPlatformGraphicsAlgorithm();
	}

	private void setPlatformGraphicsAlgorithm(PlatformGraphicsAlgorithm platformGraphicsAlgorithm) {
		this.platformGraphicsAlgorithm = platformGraphicsAlgorithm;
	}

	/**
	 * @param diagramTypeProvider
	 *            the diagramTypeProvider to set
	 */
	private void setDiagramTypeProvider(IDiagramTypeProvider diagramTypeProvider) {
		this.diagramTypeProvider = diagramTypeProvider;
	}
}
