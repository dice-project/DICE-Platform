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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

/**
 * The default wizard extender implementation.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class WizardExtender implements IWizardExtender {

	protected Wizard wizard;

	protected Map<String, IWizardPage> replaceWizardPages = new HashMap<String, IWizardPage>();

	protected List<INewWizardPageContribution> newWizardPageContribitions = new ArrayList<INewWizardPageContribution>();

	/**
	 * Creates a new instance.
	 */
	public WizardExtender() {
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.IWizardExtender#init(Wizard)
	 */
	public void init(Wizard wizard) {
		this.wizard = wizard;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.IWizardExtender#init(Wizard)
	 */
	public void initWizardPages(List<IWizardPage> wizardPages) {
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.IWizardExtender#addReplaceWizardPageContribution(IReplaceWizardPageContribution)
	 */
	public void addReplaceWizardPageContribution(
			IReplaceWizardPageContribution contribution) {
		replaceWizardPages.put(contribution.getTargetWizardPage(), contribution
				.getWizardPage());
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.IWizardExtender#getReplaceWizardPage(String)
	 */
	public IWizardPage getReplaceWizardPage(String target) {
		return (IWizardPage) replaceWizardPages.get(target);
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.IWizardExtender#addNewWizardPageContribution(INewWizardPageContribution)
	 */
	public void addNewWizardPageContribution(
			INewWizardPageContribution contribution) {
		newWizardPageContribitions.add(contribution);
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.IWizardExtender#getNewWizardPageContributions()
	 */
	public List<INewWizardPageContribution> getNewWizardPageContributions() {
		return newWizardPageContribitions;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.IWizardExtender#getNextPage(IWizardPage)
	 */
	public IWizardPage getNextPage(IWizardPage page) {
		// Let the base wizard drives the page flow.
		return null;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.IWizardExtender#canFinish()
	 */
	public boolean canFinish() {
		return true;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.IWizardExtender#doFinish()
	 */
	public boolean doFinish() {
		return true;
	}
	
	public void adjustWizardPages(List<IWizardPage> pages) {
		
	}
	
    public IWizardPage getStartingPage() {
    	return null;
    }
    
}
