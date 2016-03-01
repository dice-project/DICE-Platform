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

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.ui.wizards.BaseWizardPage;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * The wizard page which let user select method configuration during
 * synchronization
 * 
 * @author BingXue Xu
 * @since 1.0
 *
 */
public class SynchronizationSelectConfigPage extends BaseWizardPage implements ISelectionChangedListener {

	private SynchronizationChoices syncChoices = null;
	
	private ComboViewer configComboViewer;
	private MethodConfiguration[] validConfigurations;
	
	/**
	 * Creates a new instance.
	 */
	public SynchronizationSelectConfigPage(String pageName,
			SynchronizationChoices choices) {
		super(pageName);
		setTitle(AuthoringUIResources.synchronizationWizard_selectConfigPage_title); 
		setDescription(AuthoringUIResources.synchronizationWizard_selectConfigPage_text); 
		this.syncChoices = choices;
	}
	
	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		// Create the composite to hold the widgets.
		Composite composite = new Composite(parent, SWT.NULL);
		{
			GridLayout layout = new GridLayout(2, false);
			layout.marginWidth = 0;
			layout.marginHeight = 0;
			composite.setLayout(layout);
		}
		
		// add an empty line
		createLabel(composite, " ", 2); //$NON-NLS-1$
		
		createLabel(composite, AuthoringUIResources.synchronizationWizard_selectConfigPage_configLabel); 
		
		configComboViewer = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
		configComboViewer.getCombo().setLayoutData(
				new GridData(GridData.BEGINNING | GridData.FILL_HORIZONTAL));
		
		ILabelProvider labelProvider = new LabelProvider() {
			public String getText(Object element) {
				if (element instanceof MethodElement) {
					return ((MethodElement) element).getName();
				} else {
					return element.toString();
				}
			}
		};
		configComboViewer.setLabelProvider(labelProvider);
		
		// get all valid configurations, and default one, and select and show the default one
		Process selectedProcess = syncChoices.getSelectedProcess();
		validConfigurations = (MethodConfiguration[])selectedProcess.getValidContext().toArray();
		configComboViewer.add(validConfigurations);
		MethodConfiguration defaultConfig = selectedProcess.getDefaultContext();
		syncChoices.setSelectedConfig(defaultConfig);
		StructuredSelection defaultSelection = new StructuredSelection(defaultConfig);
		configComboViewer.setSelection(defaultSelection, true);
		
		addListeners(composite);
		
		setControl(composite);
		
//		System.out.println("$$$ init config =" + syncChoices.toString());
	}

	/**
	 * Adds the listeners for the controls on this page.
	 */
	private void addListeners(final Composite composite) {
		configComboViewer.addSelectionChangedListener(this);
	}
	
	/**
	 * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		StructuredSelection selection = (StructuredSelection) event
				.getSelection();
		if (!selection.isEmpty()) {
			Object[] config = selection.toArray();
			syncChoices.setSelectedConfig((MethodConfiguration)config[0]);
		} 
		
//		System.out.println("$$$ config =" + syncChoices.toString());
	}

}

