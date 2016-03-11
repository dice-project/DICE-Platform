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
package org.eclipse.epf.library.edit.util;

import java.util.Collection;

/**
 * Opposite feature loader
 * 
 * @author Phong Nguyen Le - Sep 14, 2006
 * @since  1.0
 */
public interface IOppositeFeatureLoader {
	/**
	 * Loads opposite features for the given elements
	 * 
	 * @param elements
	 */
	void loadOppositeFeatures(Collection elements);
}
