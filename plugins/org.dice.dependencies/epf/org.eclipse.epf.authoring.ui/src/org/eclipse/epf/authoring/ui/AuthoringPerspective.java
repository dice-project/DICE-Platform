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
package org.eclipse.epf.authoring.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.epf.authoring.ui.views.ConfigurationView;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.authoring.ui.wizards.NewConfigurationWizard;
import org.eclipse.epf.authoring.ui.wizards.NewLibraryWizard;
import org.eclipse.epf.authoring.ui.wizards.NewPluginWizard;
import org.eclipse.epf.ui.wizards.WizardCategories;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPerspectiveRegistry;
import org.eclipse.ui.IPlaceholderFolderLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.progress.IProgressConstants;
import org.eclipse.ui.wizards.IWizardCategory;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.ui.wizards.IWizardRegistry;

/**
 * The Method Authoring perspective.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class AuthoringPerspective implements IPerspectiveFactory {

	/**
	 * The Authoring perspective ID.
	 */
	public static final String PERSPECTIVE_ID = AuthoringPerspective.class
			.getName();

	private static List<String> newWizardShortcuts = new ArrayList<String>();

	static {
		newWizardShortcuts.add(NewLibraryWizard.WIZARD_ID);
		newWizardShortcuts.add(NewPluginWizard.WIZARD_ID);
		newWizardShortcuts.add(NewConfigurationWizard.WIZARD_ID);
	}

	/**
	 * Opens this perspective.
	 * 
	 * @return the previously active perspective
	 */
	public static IPerspectiveDescriptor open() {
		IWorkbenchPage activePage = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		if (activePage != null) {
			IPerspectiveRegistry registry = PlatformUI.getWorkbench()
					.getPerspectiveRegistry();
			IPerspectiveDescriptor oldPerspective = activePage.getPerspective();
			if (!oldPerspective.getId().equals(PERSPECTIVE_ID)) {
				IPerspectiveDescriptor perspective = registry
						.findPerspectiveWithId(PERSPECTIVE_ID);
				activePage.setPerspective(perspective);
			}
			return oldPerspective;
		}
		return null;
	}

	/**
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(IPageLayout)
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(true);
		String editorArea = layout.getEditorArea();
		IFolderLayout topLeftFolder = layout.createFolder("topLeft", //$NON-NLS-1$
				IPageLayout.LEFT, 0.30f, editorArea);

		IFolderLayout bottomLeftFolder = layout.createFolder("bottomLeft", //$NON-NLS-1$
				IPageLayout.BOTTOM, 0.50f, "topLeft"); //$NON-NLS-1$

		topLeftFolder.addView(LibraryView.VIEW_ID);
		bottomLeftFolder.addView(ConfigurationView.VIEW_ID);

		IPlaceholderFolderLayout outputFolder = layout.createPlaceholderFolder(
				"bottom", IPageLayout.BOTTOM, 0.65f, editorArea); //$NON-NLS-1$
		outputFolder.addPlaceholder(IPageLayout.ID_PROP_SHEET);
		outputFolder.addPlaceholder(IPageLayout.ID_PROBLEM_VIEW);
		outputFolder.addPlaceholder(NewSearchUI.SEARCH_VIEW_ID);
		outputFolder.addPlaceholder(IConsoleConstants.ID_CONSOLE_VIEW);
		outputFolder.addPlaceholder(IPageLayout.ID_BOOKMARKS);
		outputFolder.addPlaceholder(IProgressConstants.PROGRESS_VIEW_ID);

		layout.addNewWizardShortcut(NewLibraryWizard.WIZARD_ID);
		layout.addNewWizardShortcut(NewPluginWizard.WIZARD_ID);
		layout.addNewWizardShortcut(NewConfigurationWizard.WIZARD_ID);

		IWizardRegistry newWizardRegistry = PlatformUI.getWorkbench()
				.getNewWizardRegistry();
		IWizardCategory newWizardCategory = newWizardRegistry
				.findCategory(WizardCategories.NEW_WIZARDS_CATEGORY);
		IWizardDescriptor[] wizardDescriptors = newWizardCategory.getWizards();
		for (int i = 0; i < wizardDescriptors.length; i++) {
			IWizardDescriptor wizardDescriptor = wizardDescriptors[i];
			String wizardId = wizardDescriptor.getId();
			if (!newWizardShortcuts.contains(wizardId)) {
				layout.addNewWizardShortcut(wizardId);
			}
		}

		PerspectiveListUtil.addPerspectiveShortList(layout);
	}

}
