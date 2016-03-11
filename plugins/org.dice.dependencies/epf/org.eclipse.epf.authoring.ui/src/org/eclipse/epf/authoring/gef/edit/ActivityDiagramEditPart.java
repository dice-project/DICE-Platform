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
package org.eclipse.epf.authoring.gef.edit;

import org.eclipse.epf.diagram.model.ActivityDiagram;

/**
 * EditPart for ActivityDiagram.
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ActivityDiagramEditPart extends DiagramEditPart {

	/**
	 * Creates a editpart for Activity Diagram. 
	 * 
	 * @param model {@link ActivityDiagram}
	 */
	public ActivityDiagramEditPart(ActivityDiagram model) {
		super(model);
	}

}