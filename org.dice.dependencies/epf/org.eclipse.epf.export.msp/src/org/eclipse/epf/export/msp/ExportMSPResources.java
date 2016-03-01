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
package org.eclipse.epf.export.msp;

import org.eclipse.osgi.util.NLS;

/**
 * The Export Microsoft Project message resource bundle accessor class.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public final class ExportMSPResources extends NLS {

	private static String BUNDLE_NAME = ExportMSPResources.class.getPackage()
			.getName()
			+ ".Resources"; //$NON-NLS-1$

	private ExportMSPResources() {
		// Do not instantiate
	}

	public static String exportMSPWizard_title;

	public static String exportMSPTask_name;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ExportMSPResources.class);
	}
}