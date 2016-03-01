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
package org.eclipse.epf.export.xml.services;

import java.io.File;

import org.eclipse.epf.dataexchange.util.FileLogger;


/**
 * Logger class for xml export
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class ExportXMLLogger extends FileLogger {

	private static final String LOGS_FOLDER = "logs"; //$NON-NLS-1$
	
	/**
	 * Creates a new instance.
	 */
	public ExportXMLLogger(File logRoot) {
		super(new File(logRoot, LOGS_FOLDER));
	}
}
