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
package org.eclipse.epf.library.ui.wizards;

import java.util.Map;

import org.eclipse.epf.ui.wizards.BaseWizardPage;

/**
 * The abstract class for a Open Method Library wizard page.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public abstract class OpenLibraryWizardPage extends BaseWizardPage {

	/**
	 * Creates a new instance.
	 */
	public OpenLibraryWizardPage(String name) {
		super(name);
	}

	/**
	 * Returns the library specific user selections.
	 */
	public abstract Map getSelections();

}
