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

import org.eclipse.emf.ecore.EValidator;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.epf.validation.LibraryEValidator;
import org.eclipse.ui.IStartup;

/**
 * @author Phong Nguyen Le
 * @since  1.0
 */
public class Startup implements IStartup {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 */
	public void earlyStartup() {
		EValidator.Registry.INSTANCE.put(
				UmaPackage.eINSTANCE,
				new LibraryEValidator());
	}

}
