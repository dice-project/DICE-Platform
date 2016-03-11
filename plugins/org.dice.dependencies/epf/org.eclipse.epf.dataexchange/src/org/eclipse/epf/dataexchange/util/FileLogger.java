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
package org.eclipse.epf.dataexchange.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * file Logger class
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class FileLogger extends DefaultLogger {

	public static final String ERROR_LOG_FILENAME = "error.log"; //$NON-NLS-1$

	public static final String WARNING_LOG_FILENAME = "warning.log"; //$NON-NLS-1$

	public static final String INFO_LOG_FILENAME = "info.log"; //$NON-NLS-1$

	protected File logPath;

	public FileLogger(File logPath) {
		this.logPath = logPath;

		if (!this.logPath.exists()) {
			this.logPath.mkdirs();
		}

		super.info = getStream(new File(logPath, INFO_LOG_FILENAME));
		super.warning = getStream(new File(logPath, WARNING_LOG_FILENAME));
		super.error = getStream(new File(logPath, ERROR_LOG_FILENAME));

	}

	private PrintStream getStream(File f) {
		try {
			File dir = f.getParentFile();
			dir.mkdirs();

			if (!f.exists()) {
				f.createNewFile();
			}

			return new PrintStream(new FileOutputStream(f), true);
		} catch (Exception e) {

		}

		return null;
	}

	public void dispose() {
		info.close();
		warning.close();
		error.close();

	}
	
	public File getLogPath() {
		return logPath;
	}
}
