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
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * The wizard page which let user select properties of method content to 
 * be considered for synchonization. 
 * 
 * @author BingXue Xu
 * @since 1.0
 *
 */
public class SynchronizationSelectDetailsPage extends BaseWizardPage implements Listener {

	private SynchronizationChoices syncChoices = null;
	
	private Button bc_name;
	private Button bc_presName;
	private Button bc_briefDesc;
	private Button bc_optInput;
	private Button bc_manInput;
	private Button bc_output;
	private Button bc_primPerformer;
	private Button bc_addnPerformer;
	private Button bc_respRole;
	private Button bc_contArtifact;
	private Button bc_delivPart;
	private Button bc_selStep;

//	private Button bc_guidance;
	
//	private Button[] br_presName = new Button[2];
//	private Button[] br_optInput = new Button[2];
//	private Button[] br_manInput = new Button[2];
//	private Button[] br_output = new Button[2];
//	private Button[] br_respRole = new Button[2];
//	private Button[] br_contArtifact = new Button[2];
//	private Button[] br_delivPart = new Button[2];
//	private Button[] br_selStep = new Button[2];
	
	/**
	 * Creates a new instance.
	 */
	public SynchronizationSelectDetailsPage(String pageName,
			SynchronizationChoices choices) {
		super(pageName);
		setTitle(AuthoringUIResources.synchronizationWizard_selectDetailsPage_title); 
		setDescription(AuthoringUIResources.synchronizationWizard_selectDetailsPage_text); 
		this.syncChoices = choices;
	}
	
	/**
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		// Create the composite to hold the widgets.
		Composite composite = new Composite(parent, SWT.NULL);
		{
//			GridLayout layout = new GridLayout(3, false);
			GridLayout layout = new GridLayout(1, false);
			layout.marginWidth = 0;
			layout.marginHeight = 0;
			composite.setLayout(layout);
		}
	
		createLabel(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_properties_lable); 
//		createLabel(composite, "Conetnt To Process  ");
//		createLabel(composite, "  Process To Content");
		
		bc_name = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_name_lable); 
		
		bc_presName = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_presentationName_lable); 
//		createTwoRadioGroup(composite, br_presName);
		
		bc_briefDesc = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_briefDesc_lable); 
		
		bc_optInput = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_optionalInouts_label); 
//		createTwoRadioGroup(composite, br_optInput);
		
		bc_manInput = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_mandatoryInputs_lable); 
//		createTwoRadioGroup(composite, br_manInput);
		
		bc_output = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_outputs_label); 
//		createTwoRadioGroup(composite, br_output);
		
		bc_primPerformer = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_primaryPerformer_label); 
		bc_addnPerformer = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_addnPerformers_lable); 
		
		bc_respRole = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_responsibleRoles_lable); 
//		createTwoRadioGroup(composite, br_respRole);
		
		bc_contArtifact = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_contnArtifacts_label); 
//		createTwoRadioGroup(composite, br_contArtifact);
		
		bc_delivPart = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_dilvedParts_label); 
//		createTwoRadioGroup(composite, br_delivPart);
		
		bc_selStep = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_selSteps_label); 
//		createTwoRadioGroup(composite, br_selStep);
		
//		bc_guidance = createCheckbox(composite, AuthoringUIResources.synchronizationWizard_selectDetailsPage_selGuidance_label);
		
		addListeners(composite);
		setAllChecked();
		
		setControl(composite);
		
//		System.out.println("$$$ init detail =" + syncChoices.toString());
	}
	
	private void setAllChecked() {
		bc_name.setSelection(true);
		bc_presName.setSelection(true);
		bc_briefDesc.setSelection(true);
		bc_optInput.setSelection(true);
		bc_manInput.setSelection(true);
		bc_output.setSelection(true);
		bc_primPerformer.setSelection(true);
		bc_addnPerformer.setSelection(true);
		bc_respRole.setSelection(true);
		bc_contArtifact.setSelection(true);
		bc_delivPart.setSelection(true);
		bc_selStep.setSelection(true);	
//		bc_guidance.setSelection(true);
	}
	
	/**
	 * Adds the listeners for the controls on this page.
	 */
	private void addListeners(final Composite composite) {
		bc_name.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (bc_name.getSelection()) {
						syncChoices.setSyncName(SynchronizationChoices.SYNC_FROM_CONTENT);
				} else {
					syncChoices.setSyncName(SynchronizationChoices.SYNC_NONE);
				}
				setPageComplete(isPageComplete());
			}
		});
		bc_presName.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (bc_presName.getSelection()) {
//					br_presName[0].setEnabled(true);
//					br_presName[1].setEnabled(true);
//					if (br_presName[0].getSelection())
						syncChoices.setSyncPresName(SynchronizationChoices.SYNC_FROM_CONTENT);
//					else
//						syncChoices.setSyncPresName(SynchronizationChoices.SYNC_FROM_PROCESS);
				} else {
//					br_presName[0].setEnabled(false);
//					br_presName[1].setEnabled(false);
					syncChoices.setSyncPresName(SynchronizationChoices.SYNC_NONE);
				}
//				System.out.println("$$$ " + syncChoices.toString());
				setPageComplete(isPageComplete());
			}
		});
		bc_briefDesc.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (bc_briefDesc.getSelection()) {
						syncChoices.setSyncBriefDesc(SynchronizationChoices.SYNC_FROM_CONTENT);
				} else {
					syncChoices.setSyncBriefDesc(SynchronizationChoices.SYNC_NONE);
				}
				setPageComplete(isPageComplete());
			}
		});
		bc_optInput.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (bc_optInput.getSelection()) {
//					br_optInput[0].setEnabled(true);
//					br_optInput[1].setEnabled(true);
//					if (br_optInput[0].getSelection())
						syncChoices.setSyncOptInput(SynchronizationChoices.SYNC_FROM_CONTENT);
//					else
//						syncChoices.setSyncOptInput(SynchronizationChoices.SYNC_FROM_PROCESS);
				} else {
//					br_optInput[0].setEnabled(false);
//					br_optInput[1].setEnabled(false);
					syncChoices.setSyncOptInput(SynchronizationChoices.SYNC_NONE);
				}
//				System.out.println("$$$ " + syncChoices.toString());
				setPageComplete(isPageComplete());
			}
		});
		
		bc_manInput.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (bc_manInput.getSelection()) {
//					br_manInput[0].setEnabled(true);
//					br_manInput[1].setEnabled(true);
//					if (br_manInput[0].getSelection())
						syncChoices.setSyncManInput(SynchronizationChoices.SYNC_FROM_CONTENT);
//					else
//						syncChoices.setSyncManInput(SynchronizationChoices.SYNC_FROM_PROCESS);
//					
				} else {
//					br_manInput[0].setEnabled(false);
//					br_manInput[1].setEnabled(false);
					syncChoices.setSyncManInput(SynchronizationChoices.SYNC_NONE);
				}
//				System.out.println("$$$ " + syncChoices.toString());
				setPageComplete(isPageComplete());
			}
		});
		
		bc_output.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (bc_output.getSelection()) {
//					br_output[0].setEnabled(true);
//					br_output[1].setEnabled(true);
//					if (br_output[0].getSelection())
						syncChoices.setSyncOutput(SynchronizationChoices.SYNC_FROM_CONTENT);
//					else
//						syncChoices.setSyncOutput(SynchronizationChoices.SYNC_FROM_PROCESS);
				} else {
//					br_output[0].setEnabled(false);
//					br_output[1].setEnabled(false);
					syncChoices.setSyncOutput(SynchronizationChoices.SYNC_NONE);
				}
//				System.out.println("$$$ " + syncChoices.toString());
				setPageComplete(isPageComplete());
			}
		});
		
		bc_primPerformer.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (bc_primPerformer.getSelection()) {
					syncChoices.setSyncPrimPerformer(SynchronizationChoices.SYNC_FROM_CONTENT);
				} else {
					syncChoices.setSyncPrimPerformer(SynchronizationChoices.SYNC_NONE);
				}
				setPageComplete(isPageComplete());
			}
		});
		
		bc_addnPerformer.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
					if (bc_addnPerformer.getSelection()) {
						syncChoices.setSyncAddnPerformer(SynchronizationChoices.SYNC_FROM_CONTENT);
					} else {
						syncChoices.setSyncAddnPerformer(SynchronizationChoices.SYNC_NONE);
					}
					setPageComplete(isPageComplete());
				}
		});
		
		bc_respRole.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (bc_respRole.getSelection()) {
//					br_respRole[0].setEnabled(true);
//					br_respRole[1].setEnabled(true);
//					if (br_respRole[0].getSelection())
						syncChoices.setSyncRespRole(SynchronizationChoices.SYNC_FROM_CONTENT);
//					else
//						syncChoices.setSyncRespRole(SynchronizationChoices.SYNC_FROM_PROCESS);
				} else {
//					br_respRole[0].setEnabled(false);
//					br_respRole[1].setEnabled(false);
					syncChoices.setSyncRespRole(SynchronizationChoices.SYNC_NONE);
				}
//				System.out.println("$$$ " + syncChoices.toString());
				setPageComplete(isPageComplete());
			}
		});
		
		bc_contArtifact.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (bc_contArtifact.getSelection()) {
//					br_contArtifact[0].setEnabled(true);
//					br_contArtifact[1].setEnabled(true);
//					if (br_contArtifact[0].getSelection())
						syncChoices.setSyncContArtifact(SynchronizationChoices.SYNC_FROM_CONTENT);
//					else
//						syncChoices.setSyncContArtifact(SynchronizationChoices.SYNC_FROM_PROCESS);
				} else {
//					br_contArtifact[0].setEnabled(false);
//					br_contArtifact[1].setEnabled(false);
					syncChoices.setSyncContArtifact(SynchronizationChoices.SYNC_NONE);
				}
//				System.out.println("$$$ " + syncChoices.toString());
				setPageComplete(isPageComplete());
			}
		});
		
		bc_delivPart.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (bc_delivPart.getSelection()) {
//					br_delivPart[0].setEnabled(true);
//					br_delivPart[1].setEnabled(true);
//					if (br_delivPart[0].getSelection())
						syncChoices.setSyncDelivPart(SynchronizationChoices.SYNC_FROM_CONTENT);
//					else
//						syncChoices.setSyncDelivPart(SynchronizationChoices.SYNC_FROM_PROCESS);
				} else {
//					br_delivPart[0].setEnabled(false);
//					br_delivPart[1].setEnabled(false);
					syncChoices.setSyncDelivPart(SynchronizationChoices.SYNC_NONE);
				}
//				System.out.println("$$$ " + syncChoices.toString());
				setPageComplete(isPageComplete());
			}
		});
		
		bc_selStep.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (bc_selStep.getSelection()) {
//					br_selStep[0].setEnabled(true);
//					br_selStep[1].setEnabled(true);
//					if (br_selStep[0].getSelection())
						syncChoices.setSyncSelStep(SynchronizationChoices.SYNC_FROM_CONTENT);
//					else
//						syncChoices.setSyncSelStep(SynchronizationChoices.SYNC_FROM_PROCESS);
				} else {
//					br_selStep[0].setEnabled(false);
//					br_selStep[1].setEnabled(false);
					syncChoices.setSyncSelStep(SynchronizationChoices.SYNC_NONE);
				}
//				System.out.println("$$$ " + syncChoices.toString());
				setPageComplete(isPageComplete());
			}
		});
		
//		bc_guidance.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				syncChoices.setSyncGuidance(bc_guidance.getSelection() ? SynchronizationChoices.SYNC_FROM_CONTENT : SynchronizationChoices.SYNC_NONE);
//				setPageComplete(isPageComplete());
//			}
//		});

//		br_presName[0].addListener(SWT.Selection, this);
//		br_optInput[0].addListener(SWT.Selection, this);
//		br_manInput[0].addListener(SWT.Selection, this);
//		br_output[0].addListener(SWT.Selection, this);
//		br_respRole[0].addListener(SWT.Selection, this);
//		br_contArtifact[0].addListener(SWT.Selection, this);
//		br_delivPart[0].addListener(SWT.Selection, this);
//		br_selStep[0].addListener(SWT.Selection, this);
//		
//		br_presName[1].addListener(SWT.Selection, this);
//		br_optInput[1].addListener(SWT.Selection, this);
//		br_manInput[1].addListener(SWT.Selection, this);
//		br_output[1].addListener(SWT.Selection, this);
//		br_respRole[1].addListener(SWT.Selection, this);
//		br_contArtifact[1].addListener(SWT.Selection, this);
//		br_delivPart[1].addListener(SWT.Selection, this);
//		br_selStep[1].addListener(SWT.Selection, this);
	}
	
	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		
//		if (event.widget == br_presName[0] && br_presName[0].getSelection()) { 
//			syncChoices.setSyncPresName(SynchronizationChoices.SYNC_FROM_CONETNT);
//		}
//		if (event.widget == br_optInput[0] && br_optInput[0].getSelection()) { 
//			syncChoices.setSyncOptInput(SynchronizationChoices.SYNC_FROM_CONETNT);
//		}
//		if (event.widget == br_manInput[0] && br_manInput[0].getSelection()) { 
//			syncChoices.setSyncManInput(SynchronizationChoices.SYNC_FROM_CONETNT);
//		}
//		if (event.widget == br_output[0] && br_output[0].getSelection()) { 
//			syncChoices.setSyncOutput(SynchronizationChoices.SYNC_FROM_CONETNT);
//		}
//		if (event.widget == br_respRole[0] && br_respRole[0].getSelection()) { 
//			syncChoices.setSyncRespRole(SynchronizationChoices.SYNC_FROM_CONETNT);
//		}
//		if (event.widget == br_contArtifact[0] && br_contArtifact[0].getSelection()) {
//			syncChoices.setSyncContArtifact(SynchronizationChoices.SYNC_FROM_CONETNT);
//		}
//		if (event.widget == br_delivPart[0] && br_delivPart[0].getSelection()) { 
//			syncChoices.setSyncDelivPart(SynchronizationChoices.SYNC_FROM_CONETNT);
//		}
//		if (event.widget == br_selStep[0] && br_selStep[0].getSelection()){ 
//			syncChoices.setSyncSelStep(SynchronizationChoices.SYNC_FROM_CONETNT);
//		}
//
//		if (event.widget == br_presName[1] && br_presName[1].getSelection()){ 
//			syncChoices.setSyncPresName(SynchronizationChoices.SYNC_FROM_PROCESS);
//		}
//		if (event.widget == br_optInput[1] && br_optInput[1].getSelection()){ 
//			syncChoices.setSyncOptInput(SynchronizationChoices.SYNC_FROM_PROCESS);
//		}
//		if (event.widget == br_manInput[1] && br_manInput[1].getSelection()){ 
//			syncChoices.setSyncManInput(SynchronizationChoices.SYNC_FROM_PROCESS);
//		}
//		if (event.widget == br_output[1] && br_output[1].getSelection()){ 
//			syncChoices.setSyncOutput(SynchronizationChoices.SYNC_FROM_PROCESS);
//		}
//		if (event.widget == br_respRole[1] && br_respRole[1].getSelection()){ 
//			syncChoices.setSyncRespRole(SynchronizationChoices.SYNC_FROM_PROCESS);
//		}
//		if (event.widget == br_contArtifact[1] && br_contArtifact[1].getSelection()){
//			syncChoices.setSyncContArtifact(SynchronizationChoices.SYNC_FROM_PROCESS);
//		}
//		if (event.widget == br_delivPart[1] && br_delivPart[1].getSelection()){ 
//			syncChoices.setSyncDelivPart(SynchronizationChoices.SYNC_FROM_PROCESS);
//		}
//		if (event.widget == br_selStep[1] && br_selStep[1].getSelection()){
//			syncChoices.setSyncSelStep(SynchronizationChoices.SYNC_FROM_PROCESS);
//		}
////		System.out.println("$$$ " + syncChoices.toString());
	}
	
	/**
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
//		System.out.println("$$$ DEBUG: " + syncChoices.toString());
		if (syncChoices.getSyncName() > 0 ||
			syncChoices.getSyncPresName() > 0 ||
			syncChoices.getSyncBriefDesc() > 0 ||
			syncChoices.getSyncOptInput() > 0 ||
			syncChoices.getSyncManInput() > 0 ||
			syncChoices.getSyncOutput() > 0 ||
			syncChoices.getSyncPrimPerformer() > 0 ||
			syncChoices.getSyncAddnPerformer() > 0 ||
			syncChoices.getSyncRespRole() > 0 ||
			syncChoices.getSyncContArtifact() > 0 ||
			syncChoices.getSyncDelivPart() > 0 ||
			syncChoices.getSyncSelStep() > 0 ||
			syncChoices.getSyncGuidance() > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
//	private void createTwoRadioGroup(Composite parent, Button[] rbs) {
//		Composite aGroup = new Composite(parent, SWT.NULL);
//		
//		GridLayout layout = new GridLayout(2, false);
//		aGroup.setLayout(layout);
//		
//		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
//		gd.horizontalSpan = 2;
//		aGroup.setLayoutData(gd);
//		
//		rbs[0] = createRadioButton(aGroup, "", 1, true); //$NON-NLS-1$
//		rbs[1] = createRadioButton(aGroup, "", 1, false); //$NON-NLS-1$
//		
//		rbs[0].setEnabled(false);
//		rbs[1].setEnabled(false);
//	}

}
