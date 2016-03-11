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
package org.eclipse.epf.export.msp;

import java.util.HashMap;

/**
 * A HashMap for Export Options
 * 
 * @author Bingxue Xu
 * @author Kelvin Low
 * @since 1.0
 * 
 */
public class ExportOptions extends HashMap {

	private static final long serialVersionUID = -589701215913212273L;
	
	/**
	 * The publish selected configuration option.
	 */
	public static final String PUBLISH_CONFIG = "publishConfig"; //$NON-NLS-1$

	/**
	 * The export only planned workbreakdown elements option.
	 */
	public static final String EXPORT_ONLY_PLANNED_ELEMENTS = "exportOnlyPlannedElements"; //$NON-NLS-1$

	/**
	 * The export effort estimates for workbreakdown elements option.
	 */
	public static final String EXPORT_ESTIMATES = "exportEstimates"; //$NON-NLS-1$

	/**
	 * The estimating model used to calculate the effort estimates of the
	 * exported workbreakdown elements.
	 */
	public static final String ESTIMATING_MODEL = "estimatingModel"; //$NON-NLS-1$
	
	/**
	 * Creates a new instance.
	 */
	public ExportOptions() {
		super();
	}

}
