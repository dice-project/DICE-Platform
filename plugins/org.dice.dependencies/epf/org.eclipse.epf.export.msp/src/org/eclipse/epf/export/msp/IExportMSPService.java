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

import org.eclipse.epf.uma.Process;

/**
 * The interface for the Export Microsoft Project Service.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public interface IExportMSPService {

	/**
	 * Export a process to a Microsoft Project file.
	 * 
	 * @param process
	 *            a capability pattern or delivery process
	 * @param exportOptions
	 *            a collection of user specified export options
	 * @return <code>true</code> if the operation was completed successfully
	 * @throws ExportMSPServiceException
	 *             if an error occurs while executing the operation
	 */
	public boolean exportMSProject(Process process,
			ExportMSPOptions exportOptions) throws ExportMSPServiceException;

}
