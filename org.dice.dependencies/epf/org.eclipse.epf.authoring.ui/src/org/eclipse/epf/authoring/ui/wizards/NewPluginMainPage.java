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

import java.util.Iterator;
import java.util.List;

import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.AuthoringUIText;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.LibraryUIImages;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.library.util.ModelStorage;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page that prompts the user to specify the name, brief description,
 * authors and referenced mathod plug-ins for a new method plug-in.
 * 
 * @author Kelvin Low
 * @author Shashidhar Kannoori
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class NewPluginMainPage extends BaseWizardPage {

	/**
	 * The wizard page name.
	 */
	public static final String PAGE_NAME = NewPluginMainPage.class.getName();
	
	protected Composite composite;

	protected Label nameTextLabel;

	protected Text nameText;

	protected Text briefDescText;

	protected Text authorsText;

	protected Label referencedPluginsLabel;
	
	protected CheckboxTableViewer referencedPluginsViewer;

	protected List<MethodPlugin> plugins;

	/**
	 * Creates a new instance.
	 */
	public NewPluginMainPage(String pageName) {
		super(pageName);
		setTitle(AuthoringUIResources.newPluginWizardPage_title);
		setDescription(AuthoringUIResources.newPluginWizardPage_text);
		setImageDescriptor(AuthoringUIPlugin.getDefault().getImageDescriptor(
				"full/wizban/New.gif")); //$NON-NLS-1$
	}

	/**
	 * Creates a new instance.
	 */
	public NewPluginMainPage() {
		this(PAGE_NAME);
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		composite = createGridLayoutComposite(parent, 2);

		createNameField(composite);

		createBriefDescField(composite);

		createAuthorsField(composite);

		createReferencedModelsViewer(composite);

		initControls();

		addListeners();

		setControl(composite);
	}

	protected void createReferencedModelsViewer(Composite composite) {
		referencedPluginsLabel = createVerticallyAlignedLabel(composite,
				AuthoringUIText.REFERENCED_PLUGINS_SECTION_NAME);

		referencedPluginsViewer = CheckboxTableViewer.newCheckList(composite,
				SWT.BORDER | SWT.FILL | SWT.HORIZONTAL);
		GridData referencedPluginsGridData = new GridData(
				GridData.FILL_HORIZONTAL);
		referencedPluginsGridData.heightHint = 150;
		referencedPluginsViewer.getTable().setLayoutData(
				referencedPluginsGridData);
	}

	protected void createAuthorsField(Composite composite) {
		createVerticallyAlignedLabel(composite, AuthoringUIText.AUTHORS_TEXT);

		authorsText = createEditableText(composite, 400, 40, 1);
	}

	protected void createBriefDescField(Composite composite) {
		createVerticallyAlignedLabel(composite,
				AuthoringUIText.BRIEF_DESCRIPTION_TEXT);

		briefDescText = createEditableText(composite, 400, 80, 1);
	}

	protected void createNameField(Composite composite) {
		nameTextLabel = createVerticallyAlignedLabel(composite, AuthoringUIText.NAME_TEXT);

		nameText = createEditableText(composite);
	}

	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		initNameField();
		
		initReferencedModelsViewer();
	}

	protected void initNameField() {
		String name = "new_plug-in"; //$NON-NLS-1$
		if (LibraryService.getInstance().getCurrentMethodLibrary() != null) {
			name = TngUtil.getNextAvailableName(LibraryService.getInstance()
					.getCurrentMethodLibrary().getMethodPlugins(), name);
		}
		nameText.setText(name);
	}

	protected void initReferencedModelsViewer() {
		ILabelProvider labelProvider = new LabelProvider() {
			public Image getImage(Object element) {
				return LibraryUIImages.IMG_METHOD_PLUGIN;
			}

			public String getText(Object element) {
				if (element instanceof MethodPlugin) {
					return ((MethodPlugin) element).getName();
				} else {
					return element.toString();
				}
			}
		};

		referencedPluginsViewer.setLabelProvider(labelProvider);
		referencedPluginsViewer.setSorter(new ViewerSorter());

		plugins = ModelStorage.getBaseModels();
		String[] items = new String[plugins.size()];
		for (int i = 0; i < plugins.size(); i++) {
			MethodPlugin plugin = (MethodPlugin) plugins.get(i);
			items[i] = plugin.getName();
		}

		if (plugins != null) {
			Iterator<MethodPlugin> it = plugins.iterator();
			while (it.hasNext()) {
				referencedPluginsViewer.add(it.next());
			}
		}
	}

	/**
	 * Adds event listeners to the wizard page controls.
	 */
	protected void addListeners() {
		addNameFieldListeners();
	}

	protected void addNameFieldListeners() {
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
		setErrorMessage(null);
		if (LibraryService.getInstance().getCurrentMethodLibrary() == null) {
			setErrorMessage(LibraryUIResources.noOpenLibraryWarning_msg);
			return false;
		}

		return validatePluginName(getPluginName());
	}

	/**
	 * Validates a method plug-in name.
	 * 
	 * @param name
	 *            the name of a method plug-in
	 * 
	 * @return <code>true</code> if the plug-in name is valid
	 */
	protected boolean validatePluginName(String name) {
		String errmsg = LibraryUtil.checkPluginName(null, name);
		if (errmsg != null) {
			// Remove newline characters from the message to fit it in the error
			// message area of the wizard page.
			errmsg = errmsg.replaceAll("\n\n", " "); //$NON-NLS-1$ //$NON-NLS-2$
		}
		setErrorMessage(errmsg);
		return errmsg == null;
	}

	/**
	 * Gets the user specified method plug-in name.
	 * 
	 * @return the name for the new method plug-in
	 */
	public String getPluginName() {
		return nameText.getText() != null ? nameText.getText().trim() : null;
	}

	/**
	 * Gets the user specified method plug-in brief description.
	 * 
	 * @return the brief description for the new method plug-in
	 */
	public String getBriefDescription() {
		return briefDescText.getText();
	}

	/**
	 * Gets the user specified method plug-in authors.
	 * 
	 * @return the authors for the new method plug-in
	 */
	public String getAuthors() {
		return authorsText.getText();
	}

	/**
	 * Gets the user specified referenced method plug-ins for the new method
	 * plug-in.
	 * 
	 * @return a collection of referenced method plug-ins for the new method
	 *         plug-in
	 */
	public Object[] getReferencedPlugins() {
		return referencedPluginsViewer.getCheckedElements();
	}

}
