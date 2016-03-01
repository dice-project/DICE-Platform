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
package org.eclipse.epf.library.edit.validation;


import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.library.edit.LibraryEditPlugin;

/**
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class ValidationStatus extends Status {

	private Object checkedObject;
	private EStructuralFeature feature;

	/**
	 * @param severity
	 * @param code
	 * @param message
	 * @param checkedObject
	 */
	public ValidationStatus(int severity, int code, String message, Object checkedObject, EStructuralFeature feature) {
		super(severity, LibraryEditPlugin.PLUGIN_ID, code, message, null);
		this.checkedObject = checkedObject;
		this.feature = feature;
	}
	
	public ValidationStatus(String pluginId, int severity, int code, String message, Object checkedObject, EStructuralFeature feature) {
		super(severity, pluginId, code, message, null);
		this.checkedObject = checkedObject;
		this.feature = feature;
	}

	public Object getCheckedObject() {
		return checkedObject;
	}

	public EStructuralFeature getFeature() {
		return feature;
	}
	
}
