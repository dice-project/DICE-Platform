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
package org.eclipse.epf.authoring.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.epf.authoring.ui.wizards.SynchronizationChoices;
import org.eclipse.epf.authoring.ui.wizards.SynchronizationWizard;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.process.command.SynchronizeCommand;
import org.eclipse.epf.library.ui.actions.LibraryLockingOperationRunner;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.UmaPackage;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

/**
 * Synchronizaiton action for process elements. Synchronization takes place from method
 * element to process elements
 * 
 * @author Phong Le
 * @since 1.0
 *
 */
public class SynchronizationAction extends ProcessAutoSynchronizeAction 
implements IWorkbenchPartAction
{

	private Process selectedProcess;
	
	/**
	 * Creates an instance
	 *
	 */
	public SynchronizationAction() {
		super(LibraryEditResources.ManualSynchronizeCommand_label); 
	}
	
	/*
	 * Set Process
	 */
	public void setProcess(Process aProcess) {
		this.selectedProcess = aProcess;
	}
	
	private void superRun() {
		super.run();
	}
	
	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
	 */
	public void run() {
		LibraryLockingOperationRunner runner = new LibraryLockingOperationRunner();
		runner.run(new IRunnableWithProgress() {

			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				SynchronizationChoices choices = new SynchronizationChoices();
				choices.setSelectedProcess(selectedProcess);
				
				// Instantiate and initialize the wizard.
				SynchronizationWizard wizard = new SynchronizationWizard(choices);

				// Instantiate the wizard container with the wizard and open it.
				WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), 
											wizard);
				dialog.create();
				dialog.open();	
//				System.out.println("$$$ DEBUG: " + choices); //$NON-NLS-1$
				
				if (!choices.isFinishPressed())
					return;
				
				// Set synchFeatures 'syncFeatureSet'
				HashSet syncFeatureSet = new HashSet();
				addChoicesToSet(syncFeatureSet, choices);
				// Method config can be obtained by 'choices.getSelectedConfig()'
				
				SynchronizeCommand cmd = (SynchronizeCommand)command;
				cmd.setMethodConfiguration(choices.getSelectedConfig());
				cmd.setSynchronizationFeatures(syncFeatureSet);
				
				superRun();
			}
		});
		
	}

	private void addChoicesToSet(HashSet syncSet, SynchronizationChoices choices) {
		if (choices.getSyncName() == SynchronizationChoices.SYNC_FROM_CONTENT){
			syncSet.add(UmaPackage.eINSTANCE.getNamedElement_Name());
		}
		if (choices.getSyncPresName() == SynchronizationChoices.SYNC_FROM_CONTENT){
			syncSet.add(UmaPackage.eINSTANCE.getMethodElement_PresentationName());
		}
		if (choices.getSyncBriefDesc() == SynchronizationChoices.SYNC_FROM_CONTENT){
			syncSet.add(UmaPackage.eINSTANCE.getMethodElement_BriefDescription());
		}
		if (choices.getSyncOptInput() == SynchronizationChoices.SYNC_FROM_CONTENT){
			syncSet.add(UmaPackage.eINSTANCE.getTask_OptionalInput());
		}
		if (choices.getSyncManInput() == SynchronizationChoices.SYNC_FROM_CONTENT){
			syncSet.add(UmaPackage.eINSTANCE.getTask_MandatoryInput());
		}
		if (choices.getSyncOutput() == SynchronizationChoices.SYNC_FROM_CONTENT){
			syncSet.add(UmaPackage.eINSTANCE.getTask_Output());
		}
		if (choices.getSyncPrimPerformer() == SynchronizationChoices.SYNC_FROM_CONTENT){
			syncSet.add(UmaPackage.eINSTANCE.getTask_PerformedBy());
		}
		if (choices.getSyncAddnPerformer() == SynchronizationChoices.SYNC_FROM_CONTENT){
			syncSet.add(UmaPackage.eINSTANCE.getTask_AdditionallyPerformedBy());
		}
		if (choices.getSyncRespRole() == SynchronizationChoices.SYNC_FROM_CONTENT){
			syncSet.add(UmaPackage.eINSTANCE.getRole_ResponsibleFor());
		}
		if (choices.getSyncContArtifact() == SynchronizationChoices.SYNC_FROM_CONTENT){
			syncSet.add(UmaPackage.eINSTANCE.getArtifact_ContainedArtifacts());
		}
		if (choices.getSyncDelivPart() == SynchronizationChoices.SYNC_FROM_CONTENT){
			syncSet.add(UmaPackage.eINSTANCE.getDeliverable_DeliveredWorkProducts());
		}
		if (choices.getSyncSelStep() == SynchronizationChoices.SYNC_FROM_CONTENT){
			syncSet.add(UmaPackage.eINSTANCE.getTask_Steps());
		}
		if (choices.getSyncGuidance() == SynchronizationChoices.SYNC_FROM_CONTENT) {
			syncSet.add(UmaPackage.eINSTANCE.getContentElement_Checklists());
			syncSet.add(UmaPackage.eINSTANCE.getContentElement_ConceptsAndPapers());
			syncSet.add(UmaPackage.eINSTANCE.getContentElement_Examples());
			syncSet.add(UmaPackage.eINSTANCE.getContentElement_Guidelines());
			syncSet.add(UmaPackage.eINSTANCE.getContentElement_Assets());
			syncSet.add(UmaPackage.eINSTANCE.getContentElement_SupportingMaterials());
		}
	}
}
