//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.ui.wizards;

import org.eclipse.jface.wizard.IWizardPage;

/**
 * The interface for a new wizard page contribution.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public interface INewWizardPageContribution {

	/**
	 * Gets the new wizard page that is being added to a wizard.
	 * 
	 * @return a <code>IWizardPage</code> object
	 */
	public IWizardPage getWizardPage();

	/**
	 * Gets the name of the base wizard page that is used as a reference for
	 * inserting the new wizard page.
	 * 
	 * @return the name of the referenced base wizard page
	 */
	public String getTargetWizardPage();

	/**
	 * Gets the insertion option for the new wizard page.
	 * 
	 * @return <code>true</code> if the new wizard page should be inserted
	 *         after the target wizard page; <code>false</code> if it should
	 *         be inserted before the target wizard page
	 */
	public boolean getInsertAfter();

}
