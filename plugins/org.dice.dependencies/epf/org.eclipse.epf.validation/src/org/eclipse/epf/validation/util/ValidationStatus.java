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
package org.eclipse.epf.validation.util;


import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.validation.Activator;

/**
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class ValidationStatus extends org.eclipse.epf.library.edit.validation.ValidationStatus {

	public ValidationStatus(int severity, int code, String message, Object checkedObject, EStructuralFeature feature) {
		super(Activator.PLUGIN_ID, severity, code, message, checkedObject, feature);
	}
	
}
