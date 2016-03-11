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
package org.eclipse.graphiti.pattern.mapping;

import org.eclipse.graphiti.features.IMappingProvider;
import org.eclipse.graphiti.pattern.mapping.data.IDataMapping;


/**
 * The Class StructureMapping.
 */
public abstract class StructureMapping extends MappingProviderProvider implements IStructureMapping {

	private IDataMapping dataMapping;

	/**
	 * Creates a new {@link StructureMapping}.
	 * 
	 * @param dataMapping
	 *            the data mapping
	 * @param mappingProvider
	 *            the mapping provider
	 */
	public StructureMapping(IDataMapping dataMapping, IMappingProvider mappingProvider) {
		super(mappingProvider);
		setDataMapping(dataMapping);
	}

	public IDataMapping getDataMapping() {
		return dataMapping;
	}

	private void setDataMapping(IDataMapping dataMapping) {
		this.dataMapping = dataMapping;
	}

}
