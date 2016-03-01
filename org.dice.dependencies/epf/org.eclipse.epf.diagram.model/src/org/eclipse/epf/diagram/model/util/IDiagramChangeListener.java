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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.epf.diagram.model.Diagram;

/**
 * Adapter to listen to diagram changes
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface IDiagramChangeListener extends Adapter {
	Diagram getDiagram();
}
