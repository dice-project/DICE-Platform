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
package org.eclipse.epf.importing.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.authoring.ui.preferences.LibraryLocationData;


/**
 * Encapsulates the input data required to import a library configuration.
 * 
 * @author Jinhua Xi
 * @since 1.0
 */
public class ConfigurationImportData {

	public LibraryLocationData llData = new LibraryLocationData();

	private ErrorInfo error = new ErrorInfo();

	// If specs != null, it's a configuration specification only.
	public ConfigSpecs specs = null;

	public List importList = new ArrayList();

	// Indicate if you want to replace existing method packages.
	public boolean replaceExisting = true;

	/**
	 * Creates a new instance.
	 */
	public ConfigurationImportData() {
	}

	/**
	 * Returns the error message if any.
	 */
	public ErrorInfo getErrorInfo() {
		return error;
	}

	public class ErrorInfo {

		private StringBuffer errorMessage = new StringBuffer();

		public ErrorInfo() {
		}

		public void addError(String message) {
			errorMessage.append(message);
		}

		public String getError() {
			return errorMessage.toString();
		}

		public void clear() {
			errorMessage.setLength(0);
		}
	}

}
