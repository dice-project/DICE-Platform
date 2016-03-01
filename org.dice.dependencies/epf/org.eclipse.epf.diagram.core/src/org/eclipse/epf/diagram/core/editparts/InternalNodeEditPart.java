//------------------------------------------------------------------------------
// Copyright (c) 2005, 2009 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.diagram.core.editparts;

import org.eclipse.gef.ConnectionEditPart;

/**
 * 
 * @author Phong Nguyen Le
 * @since 1.5.0.4
 *
 */
public interface InternalNodeEditPart {
	void primAddSourceConnection(ConnectionEditPart connection, int index);
	void primAddTargetConnection(ConnectionEditPart connection, int index);
}
