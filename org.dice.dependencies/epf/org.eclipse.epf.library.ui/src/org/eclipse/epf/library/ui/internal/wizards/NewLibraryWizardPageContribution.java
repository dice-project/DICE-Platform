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
package org.eclipse.epf.library.ui.internal.wizards;

import org.osgi.framework.Bundle;

/**
 * Models the <newLibraryWizardPage> element in the
 * "org.eclipse.epf.librray.ui.newLibraryWizardPages" extension point.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class NewLibraryWizardPageContribution {

	// The plug-in that contributed this provider.
	private Bundle bundle;

	// The wizard page class name.
	private String className;

	// The library type identifier.
	private String typeId;

	// The wizard page title.
	private String title;

	// The wizard page description.
	private String description;

	/**
	 * Creates a new instance.
	 */
	public NewLibraryWizardPageContribution(Bundle bundle, String className,
			String typeId, String title, String description) {
		this.bundle = bundle;
		this.className = className;
		this.typeId = typeId;
		this.title = title;
		this.description = description;
	}

	/**
	 * Returns the plug-in that contributed this provider.
	 */
	public Bundle getBundle() {
		return bundle;
	}

	/**
	 * Returns the wizard page class name.
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * Returns the method library type identifier.
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 * Returns the wizard page title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Returns the wizard page description.
	 */
	public String getDescription() {
		return description;
	}

}
