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
package org.eclipse.epf.library.ui.wizards;

import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.ui.LibraryUIPlugin;
import org.eclipse.epf.library.ui.LibraryUIResources;
import org.eclipse.epf.library.ui.views.ProcessTreeViewer;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.Process;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * A wizard page that prompts the user to select a process to export.
 * 
 * @author Kelvin Low
 * @since 7.0
 */
public class SelectProcessPage extends BaseWizardPage {

	public static final String PAGE_NAME = SelectProcessPage.class.getName();

	protected ProcessTreeViewer processTreeViewer;

	private Text briefDescText;

	private Process process;

	/**
	 * Creates a new instance.
	 */
	public SelectProcessPage() {
		super(PAGE_NAME);
		setTitle(LibraryUIResources.selectProcessWizardPage_title);
		setDescription(LibraryUIResources.selectProcessWizardPage_text);
		setImageDescriptor(LibraryUIPlugin.getDefault().getImageDescriptor(
				"full/wizban/ExportProcess.gif")); //$NON-NLS-1$		
	}

	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite composite = createGridLayoutComposite(parent, 1);

		createLabel(composite, LibraryUIResources.processesLabel_text);

		createControl_(composite);
	}

	protected void createControl_(Composite composite) {
		processTreeViewer = new ProcessTreeViewer(composite);
		GridData gridData = new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL);
		gridData.heightHint = 250;
		processTreeViewer.getTree().setLayoutData(gridData);

		createLabel(composite, LibraryUIResources.briefDescriptionLabel_text);

		briefDescText = createMultiLineText(composite, "", 100, 70, 1); //$NON-NLS-1$	

		initControls();

		setControl(composite);
	}

	/**
	 * Initializes the wizard page controls with data.
	 */
	protected void initControls() {
		processTreeViewer.setInput(LibraryService.getInstance()
				.getCurrentMethodLibrary());
		processTreeViewer.expandAll();

		addListeners();
	}

	/**
	 * Adds event handlers to the wizard page controls.
	 */
	protected void addListeners() {
		processTreeViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						Object selection = event.getSelection();
						if (selection instanceof TreeSelection) {
							Object element = ((TreeSelection) selection)
									.getFirstElement();
							if (element instanceof Process) {
								process = (Process) element;
								briefDescText.setText(process
										.getBriefDescription());
							} else {
								process = null;
								briefDescText.setText(""); //$NON-NLS-1$
							}
						}
						setPageComplete(isPageComplete());
						getWizard().getContainer().updateButtons();
					}
				});
	}

	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		if (LibraryService.getInstance().getCurrentMethodLibrary() == null) {
			setErrorMessage(LibraryUIResources.noOpenLibraryWarning_msg);
			return false;
		}

		if (getErrorMessage() != null) {
			return false;
		}

		return getProcess() != null;
	}

	/**
	 * @see org.eclipse.epf.ui.wizards.BaseWizardPage#getNextPageData()
	 */
	public Object getNextPageData() {
		return getProcess();
	}

	/**
	 * Gets the user selected process.
	 */
	public Process getProcess() {
		return process;
	}

}