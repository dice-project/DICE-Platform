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

import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Diagram;

/**
 * Manages diagrams of activity
 * 
 * @author Phong Nguyen Le - Jun 27, 2006
 * @since  1.0
 */
public interface IDiagramManager {
	/** Diagram type constants */
	public static final int ACTIVITY_DIAGRAM = 0;

	public static final int WORK_PRODUCT_DEPENDENCY_DIAGRAM = 1;

	public static final int ACTIVITY_DETAIL_DIAGRAM = 2;

	/**
	 * Gets diagram of given type for the given activity
	 * 
	 * @param act
	 * @param type can be one of the constants {@link #ACTIVITY_DIAGRAM}, {@link #ACTIVITY_DETAIL_DIAGRAM}, or {@link #WORK_PRODUCT_DEPENDENCY_DIAGRAM}
	 * @return
	 */
	Diagram getDiagram(Activity act, int type);
}
