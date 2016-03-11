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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.EditorChooser;
import org.eclipse.epf.authoring.ui.preferences.AuthoringUIPreferences;
import org.eclipse.epf.authoring.ui.views.LibraryView;
import org.eclipse.epf.library.ILibraryManager;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.ui.actions.LibraryLockingOperationRunner;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.ui.wizards.BaseWizard;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * A wizard that guides the user to create a new method Plug-in.
 * 
 * @author Kelvin Low
 * @author Shashidhar Kannoori
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class NewPluginWizard extends BaseWizard implements INewWizard {

	/**
	 * The wizard ID.
	 */
	public static final String WIZARD_ID = NewPluginWizard.class.getName();

	/**
	 * The New Method Plug-in wizard extension point ID.
	 */
	public static final String WIZARD_EXTENSION_POINT_ID = "org.eclipse.epf.authoring.ui.newPluginWizard"; //$NON-NLS-1$	

	// The main wizard page.
	protected NewPluginMainPage mainPage;

	// The new method plug-in.
	protected MethodPlugin newPlugin;

	/**
	 * Creates a new instance.
	 */
	public NewPluginWizard() {
		super();
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#getWizardExtenderExtensionPointId()
	 */
	public String getWizardExtenderExtensionPointId() {
		return WIZARD_EXTENSION_POINT_ID;
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchWizard#init(IWorkbench,
	 *      IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(AuthoringUIResources.newPluginWizard_title);
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void addPages() {
		if (wizardExtender == null) {
			mainPage = createMainPage();
			super.addPage(mainPage);
		} else {
			List<IWizardPage> wizardPages = new ArrayList<IWizardPage>();

			IWizardPage page = wizardExtender
					.getReplaceWizardPage(NewPluginMainPage.PAGE_NAME);
			if (page != null) {
				wizardPages.add(page);
			} else {
				mainPage = createMainPage();
				wizardPages.add(mainPage);
			}

			super.getNewWizardPages(wizardPages);
			
			wizardExtender.adjustWizardPages(wizardPages);

			for (Iterator it = wizardPages.iterator(); it.hasNext();) {
				IWizardPage wizardPage = (IWizardPage) it.next();
				super.addPage(wizardPage);
			}

			wizardExtender.initWizardPages(wizardPages);
		}
	}

	/**
	 * Creates the main wizard page.
	 * 
	 * @return the main wizard page
	 */
	protected NewPluginMainPage createMainPage() {
		return new NewPluginMainPage();
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(Composite)
	 */
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		pageContainer.getShell().setImage(
				AuthoringUIPlugin.getDefault().getSharedImage(
						"full/obj16/MethodPlugin.gif")); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#doFinish()
	 */
	public boolean doFinish() {
		try {
			newPlugin = LibraryServiceUtil.createMethodPlugin(mainPage
					.getPluginName(), mainPage.getBriefDescription(), mainPage
					.getAuthors(), mainPage.getReferencedPlugins());

			initMethodPlugin(newPlugin);

			return true;
		} catch (Exception e) {
			String reason = e.getMessage() != null ? e.getMessage()
					: AuthoringUIResources.newPluginError_reason;
			AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
					AuthoringUIResources.newPluginWizard_title,
					AuthoringUIResources.newPluginError_msg, reason, e);
		}

		return false;
	}

	/**
	 * Adds a new method plug-in to the current method library, updates the
	 * Librray view and opens the method plug-in editor.
	 * 
	 * @param plugin
	 *            a newly created method plug-in
	 * @return <code>true</code> if the plug-in is initialized correctly
	 */
	public boolean initMethodPlugin(final MethodPlugin plugin) {
		try {
			ILibraryManager manager = (ILibraryManager) LibraryService
					.getInstance().getCurrentLibraryManager();
			if (manager != null) {
				manager.addMethodPlugin(plugin);
			}

			Display.getCurrent().asyncExec(new Runnable() {
				public void run() {
					// Select the new method plug-in in the Library view.
					List<MethodPlugin> selection = new ArrayList<MethodPlugin>();
					selection.add(plugin);
					LibraryView.getView().setSelectionToViewer(selection);

					// Open the new method plug-in editor.
					if (AuthoringUIPreferences.getEnableAutoNameGen()) {
						LibraryUtil.addNameTrackPresentationNameMark(plugin);
					}
					EditorChooser.getInstance().openEditor(plugin);
				}
			});

			return true;
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getLogger().logError(
					"Failed to initialize method plug-in '" //$NON-NLS-1$
							+ plugin.getName() + "'", e); //$NON-NLS-1$
			return false;
		}
	}
	
	private boolean superPerformFinish() {
		return super.performFinish();
	}

	@Override
	public boolean performFinish() {
		final boolean[] resultHolder = new boolean[1];
		LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
		runner.run(new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				resultHolder[0] = superPerformFinish();
			}
			
		});
		return resultHolder[0];
	}
}