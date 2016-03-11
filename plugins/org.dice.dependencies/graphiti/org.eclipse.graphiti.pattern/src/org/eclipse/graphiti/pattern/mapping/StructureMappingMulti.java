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

import java.util.Collections;
import java.util.List;

import org.eclipse.graphiti.features.IMappingProvider;
import org.eclipse.graphiti.pattern.mapping.data.IDataMapping;



/**
 * The Class StructureMappingMulti.
 */
public abstract class StructureMappingMulti extends StructureMapping implements IStructureMappingMulti {

	/**
	 * Creates a new {@link StructureMappingMulti}.
	 * 
	 * @param dataMapping
	 *            the data mapping
	 * @param mappingProvider
	 *            the mapping provider
	 */
	public StructureMappingMulti(IDataMapping dataMapping, IMappingProvider mappingProvider) {
		super(dataMapping, mappingProvider);
	}

	public List<ILinkCreationInfo> getLinkCreationInfos(Object mainBusinessObject) {
		ILinkCreationInfo linkCreationInfo = new LinkCreationInfo(mainBusinessObject);
		return Collections.singletonList(linkCreationInfo);
	}
}
