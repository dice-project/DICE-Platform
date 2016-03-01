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
package org.eclipse.epf.authoring.ui.wizards;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;


/**
 * The wizard that guides user to do custom synchronization for process
 * descriptors from method elements.
 * 
 * @author BingXue Xu
 * @since 1.0
 *
 */
public class SynchronizationWizard extends Wizard implements INewWizard {

	private SynchronizationDescriptionPage descPage;
	private SynchronizationSelectConfigPage selectConfigPage;
	private SynchronizationSelectDetailsPage selectDetailsPage;
//	private SynchronizationPreviewPage previewPage;
	
	private SynchronizationChoices syncChoices = null;
	
	/**
	 * Creates a new instance.
	 */
	public SynchronizationWizard(SynchronizationChoices choices) {
		super();
		this.syncChoices = choices;
		setWindowTitle(AuthoringUIResources.synchronizationWizard_title_text); 
	}
	

	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		ImageDescriptor imageDescriptor = AuthoringUIPlugin.getDefault()
				.getImageDescriptor("full/wizban/synchronization.gif"); //$NON-NLS-1$

		descPage = new SynchronizationDescriptionPage(
				"SynchronizationDescriptionPage", syncChoices); //$NON-NLS-1$
		descPage.setImageDescriptor(imageDescriptor);
		addPage(descPage);

		selectConfigPage = new SynchronizationSelectConfigPage(
				"SynchronizationSelectConfigPage", syncChoices); //$NON-NLS-1$
		selectConfigPage.setImageDescriptor(imageDescriptor);
		addPage(selectConfigPage);
		
		selectDetailsPage = new SynchronizationSelectDetailsPage(
				"SynchronizationSelectDetailsPage", syncChoices); //$NON-NLS-1$
		selectDetailsPage.setImageDescriptor(imageDescriptor);
		addPage(selectDetailsPage);
		
//		previewPage = new SynchronizationPreviewPage(
//				"SynchronizationPreviewPage", syncChoices); //$NON-NLS-1$
//		previewPage.setImageDescriptor(imageDescriptor);
//		addPage(previewPage);
	}
	
	/**
	 * @see org.eclipse.jface.wizard.IWizard#canFinish()
	 */
	public boolean canFinish() {
		return (selectDetailsPage.isPageComplete());
	}
	
	/**
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		syncChoices.setFinishPressed(true);
		return true;
	}

	/**
	 * @see org.eclipse.jface.wizard.IWizard#performCancel()
	 */
	public boolean performCancel() {
		syncChoices.setFinishPressed(false);
		return true;
	}
	
	/**
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
	}

}
