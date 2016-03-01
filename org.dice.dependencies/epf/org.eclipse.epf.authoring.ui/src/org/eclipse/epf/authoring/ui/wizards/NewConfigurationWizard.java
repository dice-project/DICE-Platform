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
import org.eclipse.epf.authoring.ui.preferences.AuthoringUIPreferences;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.library.ui.actions.LibraryLockingOperationRunner;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.services.ILibraryPersister;
import org.eclipse.epf.ui.wizards.BaseWizard;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * A wizard that guides the user to create a new Method Configuration.
 * 
 * @author Kelvin Low
 * @author Bingxue Xu
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class NewConfigurationWizard extends BaseWizard implements INewWizard {

	/**
	 * The wizard ID.
	 */
	public static final String WIZARD_ID = NewConfigurationWizard.class
			.getName();

	/**
	 * The New Method Configuration wizard extension point ID.
	 */
	public static final String WIZARD_EXTENSION_POINT_ID = "org.eclipse.epf.authoring.ui.newConfigurationWizard"; //$NON-NLS-1$	

	// The main wizard page.
	protected NewConfigurationMainPage mainPage;

	// The new method configuration.
	protected MethodConfiguration config;

	/**
	 * Creates a new instance.
	 */
	public NewConfigurationWizard() {
		super();
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#getWizardExtenderExtensionPointId()
	 */
	public String getWizardExtenderExtensionPointId() {
		return WIZARD_EXTENSION_POINT_ID;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#init(IWorkbench,
	 *      IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		super.init(workbench, selection);
		setWindowTitle(AuthoringUIResources.newConfigurationWizard_title);
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
					.getReplaceWizardPage(NewConfigurationMainPage.PAGE_NAME);
			if (page != null) {
				wizardPages.add(page);
			} else {
				mainPage = createMainPage();
				wizardPages.add(mainPage);
			}

			super.getNewWizardPages(wizardPages);

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
	protected NewConfigurationMainPage createMainPage() {
		return new NewConfigurationMainPage();
	}

	/**
	 * @see org.eclipse.jface.wizard.Wizard#createPageControls(Composite)
	 */
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
		pageContainer.getShell().setImage(
				AuthoringUIPlugin.getDefault().getSharedImage(
						"full/obj16/MethodConfiguration.gif")); //$NON-NLS-1$
	}

	/**
	 * Sets the method configuration. This method is called when a method
	 * configuration is created in the Library view via the context menu. Upon
	 * completing this wizard, the newly created method configuration will be
	 * initialized with the name and brief description entered by the user in
	 * tis wizard.
	 * 
	 * @param config
	 *            a method configuration
	 */
	public void setMethodConfiguration(MethodConfiguration config) {
		this.config = config;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizard#doFinish()
	 */
	public boolean doFinish() {
		boolean ret = createMethodConfiguration(mainPage.getConfigurationName(),
				mainPage.getBriefDescription(), mainPage);
		if(ret) {
			MethodLibrary library = LibraryService.getInstance()
					.getCurrentMethodLibrary();
			ILibraryPersister.FailSafeMethodLibraryPersister persister = LibraryServiceUtil
					.getCurrentPersister().getFailSafePersister();
			try {
				persister.save(library.eResource());
				persister.commit();
				if (AuthoringUIPreferences.getEnableAutoNameGen()) {
					LibraryUtil.addNameTrackPresentationNameMark(config);
				}
			} catch (Exception e) {
				try {
					persister.rollback();
				}
				catch(Exception ex) {
					AuthoringUIPlugin.getDefault().getLogger().logError(ex);
				}
				AuthoringUIPlugin.getDefault().getLogger().logError(e);
				AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
						AuthoringUIResources.newConfigurationWizard_title,
						AuthoringUIResources.internalError_msg, e);
			}
		}
		return ret;
	}

	/**
	 * Creates a new method configuration.
	 * 
	 * @param name
	 *            the name for the new configuration
	 * @param briefDesc
	 *            the brief description for the new configuration
	 * @param wizardPage
	 *            the wizard page used for displaying error messages
	 * @return <code>true</code> if the method configuration is created
	 *         successfully
	 */
	public boolean createMethodConfiguration(String name, String briefDesc,
			WizardPage wizardPage) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException();
		}

		try {
			MethodLibrary library = LibraryService.getInstance()
					.getCurrentMethodLibrary();

			MethodConfiguration c = config == null ? UmaFactory.eINSTANCE.createMethodConfiguration() : config;
			c.setName(name);
			
			if (! nameCheck(name, wizardPage, library, c)) {
				return false;
			}
			
			if (config == null) {
				config = LibraryService.getInstance()
						.createMethodConfiguration(name, library);
			}

			if (briefDesc != null) {
				config.setBriefDescription(briefDesc);
			}
		} catch (Exception e) {
			AuthoringUIPlugin.getDefault().getMsgDialog().displayError(
					AuthoringUIResources.newConfigurationWizard_title,
					AuthoringUIResources.internalError_msg, e);
		}

		return true;
	}

	private boolean nameCheck(String name, WizardPage wizardPage, 
			MethodLibrary library, MethodConfiguration methodConfig) {
		// Fix for bugzilla 178462.
		String errmsg = null;
		if (name.indexOf("&") > -1) { //$NON-NLS-1$
			errmsg = NLS
					.bind(
							LibraryEditResources.invalidElementNameError4_msg,
							name);
		} else {
			IValidator validator = IValidatorFactory.INSTANCE
					.createNameValidator(library, methodConfig);
			errmsg = validator.isValid(name);
		}

		if (errmsg != null) {
			wizardPage.setErrorMessage(errmsg);
			return false;
		}
		return true;
	}

	/**
	 * Gets the newly created method confguration.
	 * 
	 * @return a new method configuration
	 */
	public MethodConfiguration getMethodConfiguration() {
		return config;
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
