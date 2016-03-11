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

import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

/**
 * The interface for a generic wizard extender.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public interface IWizardExtender {

	/**
	 * Gives the wizard extender a chance to perform some initialization.
	 */
	public void init(Wizard wizard);

	/**
	 * Gives the wizard extender a chance to save references to wizard pages.
	 * 
	 * @param wizardPages
	 *            a collection of <code>IWizardPage</code> owned by the wizard
	 */
	public void initWizardPages(List<IWizardPage> wizardPages);

	/**
	 * Adds a replace wizard page contribition.
	 * 
	 * @param contribution
	 *            the replace wizard page contribution
	 */
	public void addReplaceWizardPageContribution(
			IReplaceWizardPageContribution contribution);

	/**
	 * Gets the wizard page that replaces a base wizard page.
	 * 
	 * @param target
	 *            the name of the base wizard to be replaced
	 * @return a <code>IWizardPage</code> object or <code>null</code> if no
	 *         replacement page is available
	 */
	public IWizardPage getReplaceWizardPage(String target);

	/**
	 * Adds a new wizard page contribution.
	 * 
	 * @param contribution
	 *            the new wizard page contribution
	 */
	public void addNewWizardPageContribution(
			INewWizardPageContribution contribution);

	/**
	 * Gets an array of new wizard pages to be added to a base wizard.
	 * 
	 * @return an collection of <code>NewWizardPageExetnsion</code> objects or
	 *         <code>null</code>
	 */
	public List<INewWizardPageContribution> getNewWizardPageContributions();

	/**
	 * Allows a wizard extender to control the flow of the wizard pages.
	 * 
	 * @param page
	 *            the current wizard page
	 * @return the next wizard page
	 */
	public IWizardPage getNextPage(IWizardPage page);

	/**
	 * Allows a wizard extender to decide whether the wizard could be finished
	 * without further user interaction.
	 * 
	 * @return <code>true</code> if the wizard could be finished
	 */
	public boolean canFinish();

	/**
	 * Allows a wizard extender to perform the finish operation.
	 * 
	 * @return <code>true</code> if the finish operation is executed
	 *         successfully
	 */
	public boolean doFinish();
	
	/**
	 * Give the wizard extender the chance to adjust the wizard pages
	 */
	public void adjustWizardPages(List<IWizardPage> pages);
	
    public IWizardPage getStartingPage();
	
}
