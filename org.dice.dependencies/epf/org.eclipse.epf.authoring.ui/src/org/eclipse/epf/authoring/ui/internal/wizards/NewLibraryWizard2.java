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
package org.eclipse.epf.authoring.ui.internal.wizards;

import java.util.Iterator;

import org.eclipse.epf.authoring.ui.AuthoringPerspective;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.wizards.NewLibraryMainPage;
import org.eclipse.epf.authoring.ui.wizards.SaveAllEditorsPage;
import org.eclipse.epf.common.ui.util.PerspectiveUtil;
import org.eclipse.epf.library.LibraryManagerFactory;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.library.ui.wizards.NewLibraryWizardPage;
import org.eclipse.epf.library.ui.wizards.NewLibraryWizardPageFactory;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * A wizard that guides the user to create a new method library.
 * 
 * @author Kelvin Low
 * @since 1.2
 * @deprecated
 */
public class NewLibraryWizard2 extends Wizard implements INewWizard {

	/**
	 * The wizard ID.
	 */
	public static final String WIZARD_ID = NewLibraryWizard2.class.getName();

	// The main wizard page.
	private NewLibraryMainPage mainPage;

	/**
	 * Creates a new instance.
	 */
	public NewLibraryWizard2() {
		super();
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWizard#init(IWorkbench,
	 *      IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle(AuthoringUIResources.newLibraryWizard_title);
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		SaveAllEditorsPage.addPageIfNeeded(this, true, null, null, null);
		mainPage = new NewLibraryMainPage();
		addPage(mainPage);

		for (Iterator it = LibraryManagerFactory.getInstance()
				.getLibraryTypes().keySet().iterator(); it.hasNext();) {
			String persistenceId = (String) it.next();
			String pageId = persistenceId + "Page"; //$NON-NLS-1$
			NewLibraryWizardPage page = NewLibraryWizardPageFactory
					.getInstance().createWizardPage(pageId, persistenceId);
			if (page != null) {
				page.setImageDescriptor(AuthoringUIPlugin.getDefault()
						.getImageDescriptor("full/wizban/New.gif")); //$NON-NLS-1$					
				addPage(page);
			}
		}
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(Composite)
	 */
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		pageContainer.getShell().setImage(
				AuthoringUIPlugin.getDefault().getSharedImage(
						"full/obj16/MethodLibrary.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	public boolean canFinish() {
		IWizardPage[] pages = getPages();
		for (int i = 1; i < pages.length; i++) {
			if (!pages[i].isPageComplete()) {
				return false;
			}
		}
		return getContainer().getCurrentPage() != mainPage;
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		String name = mainPage.getLibraryName();
		String type = mainPage.getLibraryType();
		NewLibraryWizardPage page = NewLibraryWizardPageFactory.getInstance()
				.getWizardPage(type);
		try {
			MethodLibrary library = LibraryService.getInstance()
					.createMethodLibrary(name, type, page.getSelections());
			if (library != null) {
				addRecentlyOpenedLibrary(library);
				PerspectiveUtil
						.openPerspective(AuthoringPerspective.PERSPECTIVE_ID);
				return true;
			}
		} catch (Exception e) {
			String reason = e.getMessage();
			if (reason == null) {
				reason = AuthoringUIResources.newLibraryInternlError_reason;
			}
			reason += "\n\n" + AuthoringUIResources.newLibraryError_advice;  //$NON-NLS-1$
			if (e.getMessage() != null) {
				LibraryUIPlugin.getDefault().getMsgDialog().displayError(
						AuthoringUIResources.newLibraryWizard_title,
						AuthoringUIResources.newLibraryError_msg, reason);
			} else {
				LibraryUIPlugin.getDefault().getMsgDialog().displayError(
						AuthoringUIResources.newLibraryWizard_title,
						AuthoringUIResources.newLibraryError_msg, reason, e);
			}
		}
		return false;
	}

	/**
	 * Adds the newly opened or created method library to the recently opened
	 * method libraries preference.
	 * 
	 * @param library
	 *            a method library
	 */
	protected void addRecentlyOpenedLibrary(MethodLibrary library) {
		if (library != null) {
			String libraryURI = LibraryService.getInstance().getLibraryManager(
					library).getMethodLibraryURI().toString();
			LibraryUIPreferences.addOpenLibraryURI(libraryURI);
		}
	}

}
