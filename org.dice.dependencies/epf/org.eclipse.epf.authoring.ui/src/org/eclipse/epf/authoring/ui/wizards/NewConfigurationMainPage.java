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

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.ui.LibraryUIText;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodLibrary;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page that prompts the user to enter a name and description for a new
 * method configuration.
 * 
 * @author Bingxue Xu
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class NewConfigurationMainPage extends BaseWizardPage {

	/**
	 * The wizard page name.
	 */
	public static final String PAGE_NAME = NewConfigurationMainPage.class
			.getName();

	protected Composite composite;

	protected Text nameText;

	protected Text briefDescText;

	/**
	 * Creates a new instance.
	 */
	public NewConfigurationMainPage(String pageName) {
		super(pageName);
		setTitle(AuthoringUIResources.AuthoringUIPlugin_NewConfigurationMainPage_pageTitle);
		setDescription(AuthoringUIResources.AuthoringUIPlugin_NewConfigurationMainPage_pageDescription);
		setImageDescriptor(AuthoringUIPlugin.getDefault().getImageDescriptor(
				"full/wizban/New.gif")); //$NON-NLS-1$		
	}

	/**
	 * Creates a new instance.
	 */
	public NewConfigurationMainPage() {
		this(PAGE_NAME);
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		composite = createGridLayoutComposite(parent, 3);

		createVerticallyAlignedLabel(composite, AuthoringUIText.NAME_TEXT);

		nameText = createEditableText(composite, 2);

		createVerticallyAlignedLabel(composite,
				AuthoringUIText.DESCRIPTION_TEXT);

		briefDescText = createEditableText(composite, 400, 80, 2);

		initControls();

		addListeners();

		setControl(composite);
	}

	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		MethodLibrary lib = LibraryService.getInstance().getCurrentMethodLibrary();
		String name = "new_config"; //$NON-NLS-1$
		if(lib != null) {
			name = TngUtil.getNextAvailableName(lib.getPredefinedConfigurations(), name);
		}
		nameText.setText(name); 
	}

	/**
	 * Adds event handlers to the wizard page controls.
	 */
	protected void addListeners() {
		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPageComplete(isPageComplete());
				getWizard().getContainer().updateButtons();
			}
		});
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#onEnterPage(Object)
	 */
	public void onEnterPage(Object obj) {
		if (nameText != null) {
			nameText.setFocus();
			nameText.selectAll();
		}
		setPageComplete(isPageComplete());
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		if (LibraryService.getInstance().getCurrentMethodLibrary() == null) {
			setErrorMessage(LibraryUIResources.noOpenLibraryWarning_msg);
			return false;
		}

		String configName = getConfigurationName();

		if (!validateConfigurationName(configName)) {
			return false;
		}

		if (configurationExists(configName)) {
			setErrorMessage(LibraryEditResources.duplicateElementNameError_simple_msg);
			return false;
		} else {
			setErrorMessage(null);
		}

		return getErrorMessage() == null;
	}

	/**
	 * Checks whether a method configuration with the same name already exists
	 * in the current method library.
	 * 
	 * @param configName
	 *            the name for a method configuration
	 * @return <code>true</code> if a method configuration with the same name
	 *         already exists
	 */
	protected boolean configurationExists(String configName) {
		String[] configNames = LibraryServiceUtil
				.getMethodConfigurationNames(LibraryService.getInstance()
						.getCurrentMethodLibrary());
		for (int i = 0; i < configNames.length; i++) {
			if (configName.equalsIgnoreCase(configNames[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Validates a method configuration name.
	 * 
	 * @param name
	 *            the name of a method configuration
	 * 
	 * @return <code>true</code> if the configuration name is valid
	 */
	protected boolean validateConfigurationName(String name) {
		// TODO: Call the configuration name validator when it is ready.
		String errmsg = TngUtil.checkElementName(name,
				LibraryUIText.TEXT_METHOD_CONFIGURATON);
		if (errmsg != null) {
			errmsg = errmsg.replaceAll("\n\n", " "); //$NON-NLS-1$ //$NON-NLS-2$
		}
		setErrorMessage(errmsg);
		return errmsg == null;
	}

	/**
	 * Gets the user specified the method configuration name.
	 * 
	 * @return the name for the new method configuration
	 */
	public String getConfigurationName() {
		return nameText.getText().trim();
	}

	/**
	 * Gets the user specified method configuration brief description.
	 * 
	 * @return the brief description for the new method configuration
	 */
	public String getBriefDescription() {
		return briefDescText.getText().trim();
	}

}
