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

import org.eclipse.epf.publishing.services.PublishOptions;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Abstract publishing wizard class. 
 * use the org.eclipse.epf.publishing.ui.publishers extension point to extend this class 
 * if you need to customize the publishing ui.
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public abstract class AbstractPublishWizard extends Wizard implements INewWizard {

	// The publishing options.
	protected PublishOptions dataModel = null;

	/**
	 * constructor
	 *
	 */
	public AbstractPublishWizard() {
		super();
	}
	
	/**
	 * finish the wizard
	 * 
	 * @return boolean return true if successful
	 */
	public abstract boolean performFinish();

	/**
	 * initialize the wizard.
	 * 
	 * @param workbench IWorkbench
	 * @param selection IStructuredSelection
	 */
	public abstract void init(IWorkbench workbench, IStructuredSelection selection);
	
	/**
	 * get the publish options
	 * 
	 * @return PublishOptions
	 */
	public abstract PublishOptions getPublishOptions();
}
