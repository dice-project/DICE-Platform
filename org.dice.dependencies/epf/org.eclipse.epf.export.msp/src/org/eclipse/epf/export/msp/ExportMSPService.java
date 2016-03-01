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

import org.eclipse.epf.uma.Process;

/**
 * The default Export Microsoft Project Service implementation.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ExportMSPService implements IExportMSPService {

	// The shared instance.
	private static ExportMSPService instance = null;

	/**
	 * Returns the shared instance.
	 */
	public static ExportMSPService getInstance() {
		if (instance == null) {
			synchronized (ExportMSPService.class) {
				if (instance == null) {
					instance = new ExportMSPService();
				}
			}
		}
		return instance;
	}

	/**
	 * @see org.eclipse.epf.export.msp.IExportMSPService#exportMSProject(Process,
	 *      ExportMSPOptions)
	 */
	public boolean exportMSProject(Process process,
			ExportMSPOptions exportOptions) throws ExportMSPServiceException {
		return new ExportMSPXMLService().export(process, exportOptions);
	}

}
