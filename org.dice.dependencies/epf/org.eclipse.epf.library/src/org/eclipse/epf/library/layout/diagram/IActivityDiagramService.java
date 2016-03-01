//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.layout.diagram;

import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.library.edit.util.Suppression;
import org.eclipse.epf.uma.Activity;

public interface IActivityDiagramService {

	/**
	 * save the element diagram image and returns the image file url.
	 * 
	 * @param wraper Object the item provider wrapper of the activity element
	 * @param imgPath String the generated image path relative to the publishing dir.
	 * @param diagramType
	 * @param filter
	 *            IFilter
	 * @param sup
	 *            Suppression
	 * @return DiagramInfo the diagram info
	 */
	public DiagramInfo saveDiagram(Object wraper, String imgPath, String diagramType,
			IFilter filter, Suppression sup);
	
	
	public Activity getRealizedForUnmodified(Object wrapper, IFilter filter, 
			Suppression suppression);

}
