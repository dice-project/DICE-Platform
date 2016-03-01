/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.validation;

import org.eclipse.osgi.util.NLS;

public class ValidationResources extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.epf.validation.Resources"; //$NON-NLS-1$

	public static String circularDependency_error;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, ValidationResources.class);
	}

	private ValidationResources() {
	}
}
