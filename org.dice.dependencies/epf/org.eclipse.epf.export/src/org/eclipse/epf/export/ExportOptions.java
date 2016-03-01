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
package org.eclipse.epf.export;

import java.io.File;

/**
 * The export options.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class ExportOptions {

	protected File exportDir;

	/**
	 * Creates a new instance.
	 */
	public ExportOptions() {
	}

	/**
	 * Gets the user selected export directory.
	 * 
	 * @return a directory for storing the exported content
	 */
	public File getExportDir() {
		return exportDir;
	}

	/**
	 * Sets the user selected export directory.
	 * 
	 * @param exportDir
	 *            a directory for storing the exported content
	 */
	public void setExportDir(File exportDir) {
		this.exportDir = exportDir;
	}

}
