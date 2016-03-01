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
package org.eclipse.epf.diagram.model.util;

import org.eclipse.epf.library.edit.util.IDiagramManager;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Diagram;

/**
 * Extension to contribute to extension point
 * "org.eclipse.epf.library.edit.diagramManager"
 * 
 * @author Phong Nguyen Le - Jun 27, 2006
 * @since 1.0
 */
public class DiagramManager implements IDiagramManager {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.library.edit.util.IDiagramManager#getDiagram(org.eclipse.epf.uma.Activity,
	 *      int)
	 */
	public Diagram getDiagram(Activity act, int type) {
		return GraphicalDataManager.getInstance().getUMADiagram(act, type,
				false);
	}

}
