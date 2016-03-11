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
package org.eclipse.epf.authoring.gef.util;

import org.eclipse.draw2d.geometry.Point;

/**
 * Constraint for placing anchor location.
 * @author Phong Nguyen Le
 * @since 1.0
 */
public interface ConnectionAnchorLocator {

	/**
	 * Gets relative location based on the given reference point
	 * 
	 * @param reference
	 * @return absolute location
	 */
	Point getLocation(Point reference);

}
