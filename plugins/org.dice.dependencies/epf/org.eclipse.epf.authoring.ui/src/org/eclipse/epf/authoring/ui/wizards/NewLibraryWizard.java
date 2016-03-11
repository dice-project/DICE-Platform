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
package org.eclipse.epf.authoring.ui.wizards;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.epf.authoring.ui.AuthoringPerspective;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.common.ui.util.PerspectiveUtil;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.MethodLibraryPropUtil;
import org.eclipse.epf.library.persistence.ILibraryResourceSet;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.ui.preferences.LibraryUIPreferences;
import org.eclipse.epf.library.xmi.XMILibraryManager;
import org.eclipse.epf.library.xmi.XMILibraryUtil;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * A wizard that guides the user to create a new method library.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class NewLibraryWizard extends Wizard implements INewWizard {

	/**
	 * The wizard ID.
	 */
	public static final String WIZARD_ID = NewLibraryWizard.class.getName();

	// The main wizard page.
	private NewLibraryMainPage mainPage;

	/**
	 * Creates a new instance.
	 */
	public NewLibraryWizard() {
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
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {
		try {
			String name = mainPage.getLibraryName();
			String desc = mainPage.getLibraryDescription();
			String type = mainPage.getLibraryType();
			Map<String, Object> options = new HashMap<String, Object>();
			File libraryPath = new File(mainPage.getLibraryPath());
			options.put(XMILibraryManager.ARG_LIBRARY_PATH, libraryPath
					.getAbsolutePath());

			if (XMILibraryUtil.containsPluginOrConfigSpecExportFile(libraryPath
					.getAbsolutePath())) {
				throw new Exception(LibraryUIResources.openLibraryError_reason4);
			}

			MethodLibrary library = LibraryService.getInstance()
					.createMethodLibrary(name, type, options);
			if (library != null) {
				MethodLibraryPropUtil propUtil = MethodLibraryPropUtil.getMethodLibraryPropUtil();
				propUtil.setSynFree(library, mainPage.isSynFree());
				
				if (desc.length() > 0) {
					library.setBriefDescription(desc);
				}
				if (mainPage.isSynFree() || desc.length() > 0) {
					Resource res = library.eResource();
					ResourceSet resourceSet = res == null ? null : res.getResourceSet();
					if (resourceSet instanceof ILibraryResourceSet) {
						try {
							((ILibraryResourceSet) resourceSet).save(null);
						} catch (Exception e) {
							AuthoringUIPlugin.getDefault().getLogger().logError(e);
						}
					}
				}
				
				LibraryUIPreferences.addNewLibraryPath(libraryPath
						.getAbsolutePath());
				PerspectiveUtil
						.openPerspective(AuthoringPerspective.PERSPECTIVE_ID);
				return true;
			}
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(e);
			
			String reason = e.getMessage();
			if (reason == null) {
				reason = AuthoringUIResources.newLibraryInternlError_reason;
			}
			reason += "\n\n" + AuthoringUIResources.newLibraryError_advice; //$NON-NLS-1$
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

}
