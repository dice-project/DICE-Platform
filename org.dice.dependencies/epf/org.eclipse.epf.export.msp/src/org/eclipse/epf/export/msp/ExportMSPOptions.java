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

import org.eclipse.epf.export.ExportProcessOptions;

/**
 * The export Microsoft Project options.
 * 
 * @author Kelvin Low
 * @since 7.2
 */
public class ExportMSPOptions extends ExportProcessOptions {

	private String msprojectName;

	/**
	 * Gets the Microsoft Project name.
	 * 
	 * @return the name of the exported Microsoft Project name
	 */
	public String getMSProjectName() {
		return msprojectName;
	}

	/**
	 * Sets the Microsoft Project name.
	 * 
	 * @param templateName
	 *            the name of the exported Microsoft Project
	 */
	public void setMSProjectName(String msprojectName) {
		this.msprojectName = msprojectName;
	}

}
