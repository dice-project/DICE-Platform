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
package org.eclipse.epf.publishing.ui.wizards;

/**
 * Models a "publishOption" configuration element in the
 * "org.eclipse.epf.publishing.ui.publishConfigurationWizard" extension point.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class PublishOptionContribution {

	private String name;

	private String description;

	private boolean defaultSelection;

	/**
	 * Creates a new instance.
	 * 
	 * @param name
	 *            the export option name
	 * @param description
	 *            the export option description
	 * @param defaultSelection
	 *            if <code>true</code>, this option is selected by default
	 */
	public PublishOptionContribution(String name, String description,
			boolean defaultSelection) {
		this.name = name;
		this.description = description;
		this.defaultSelection = defaultSelection;
	}

	/**
	 * Returns the name of this export option.
	 * 
	 * @return the export option name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the description of this export option.
	 * 
	 * @return the export option description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the default seleciton of this export option.
	 * 
	 * @return <code>true</code> if this option should be selected by default
	 */
	public boolean getDefaultSelection() {
		return defaultSelection;
	}

}
