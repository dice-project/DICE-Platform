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
 * The default new wizard page contribution implementation.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class NewWizardPageContribution implements INewWizardPageContribution {

	private IWizardPage page;

	private String target;

	private boolean insertAfter = true;

	/**
	 * Creates a new instance.
	 * 
	 * @param page
	 *            the new wizard page
	 * @param target
	 *            the name of the base wizard page that is used as a reference
	 *            for insering the new wizard page
	 * @param insertAfter
	 *            if <code>true</code>, insert the new wizard page after the
	 *            target wizard page; if <code>false</code>, insert the new
	 *            wizard page before the target wizard page
	 */
	public NewWizardPageContribution(IWizardPage page, String target,
			boolean insertAfter) {
		this.page = page;
		this.target = target;
		this.insertAfter = insertAfter;
	}

	/**
	 * Gets the wizard page.
	 */
	public IWizardPage getWizardPage() {
		return page;
	}

	/**
	 * Gets the name of the base wizard page that is used as a reference for
	 * inserting the new wizard page
	 */
	public String getTargetWizardPage() {
		return target;
	}

	/**
	 * Gets the insertion option for the new wizard page.
	 * 
	 * @return <code>true</code> if the new wizard page should be inserted
	 *         after the target wizard page; <code>false</code> if it should
	 *         be inserted before the target wizard page
	 */
	public boolean getInsertAfter() {
		return insertAfter;
	}

}
