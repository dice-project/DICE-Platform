//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.ui.service;

import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.library.layout.diagram.IActivityDiagramService;

/**
 * Interface for the generating diagram image.  
 * @author Shashidhar Kannoori
 */
public interface IDiagramImageService extends IActivityDiagramService {

	/**
	 * Generates the diagram image for given activity and returns diagram image path string.
	 * This method don't calculate the image map for generated diagram image.  In order to calculate
	 * Image for generated diagram use {@link IActivityDiagramService}'s saveDiagram(Object wraper, String imgPath, String diagramType,
			IFilter filter, Suppression sup); 
	 * @param wrapper
	 * @param imgPath
	 * @param diagramType
	 * @param filter
	 * @param sup
	 * @param realizedDiagram
	 * @return
	 */
	public String generateDiagramImage(final Object wrapper, final String imgPath, 
			final int diagramType, final IFilter filter,
			final Suppression sup, final boolean realizedDiagram);
}
