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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.authoring.ui.editors.MethodElementEditorInput;
import org.eclipse.epf.library.edit.LibraryEditResources;
import org.eclipse.epf.library.edit.process.BreakdownElementWrapperItemProvider;
import org.eclipse.epf.library.edit.process.command.SynchronizeCommand;
import org.eclipse.epf.library.edit.ui.UserInteractionHelper;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.actions.ProcessDeleteAction;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.Descriptor;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.TeamProfile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;


/**
 * Default Synchronize action for process elements.
 * Synchronization takes place from method to process elements.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class ProcessAutoSynchronizeAction extends CommandActionHandler 
implements IWorkbenchPartAction, IModifyingAction
{
	protected IWorkbenchPart activeWorkbenchPart;
	protected boolean canRun;

	/**
	 * Creates an instance
	 */
	public ProcessAutoSynchronizeAction() {
		super(null, LibraryEditResources.AutoSynchronizeCommand_label); 
	}

	/**
	 * Creates an instance with label text
	 */
	public ProcessAutoSynchronizeAction(String labelText) {
		super(null, labelText); 
	}
	
	/**
	 * 
	 * @see org.eclipse.epf.authoring.ui.actions.IWorkbenchPartAction#setActiveWorkbenchPart(org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart) {
		activeWorkbenchPart = workbenchPart;
		if (workbenchPart instanceof IEditingDomainProvider) {
			domain = ((IEditingDomainProvider) workbenchPart)
					.getEditingDomain();
		}
	}

	/**
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#createCommand(java.util.Collection)
	 */
	public Command createCommand(Collection selection) {
		return new SynchronizeCommand(getText(), selection) {
			/*
			 * 
			 * 
			 * @see AutoSynchronizeCommand#delete(java.util.Collection)
			 */
			protected Command delete(List elements) {
				try {
					return ProcessDeleteAction.delete(elements);
				}
				catch(OperationCanceledException e) {
					aborted = true;
					return null;
				}
			}
		};
	}
	
	/**
	 * Return the process 
	 * @return
	 */
	protected Process getProcess() {
		if(activeWorkbenchPart instanceof IEditorPart) {
			IEditorInput input = ((IEditorPart)activeWorkbenchPart).getEditorInput();
			if(input instanceof MethodElementEditorInput) {
				MethodElement e = ((MethodElementEditorInput)input).getMethodElement();
				if(e instanceof ProcessComponent) {
					return ((ProcessComponent)e).getProcess();
				}
			}
		}
		return null;
	}

	/** 
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public boolean updateSelection(IStructuredSelection selection) {
		ArrayList selected = new ArrayList();
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			
			if (element instanceof BreakdownElementWrapperItemProvider 
					&& !((BreakdownElementWrapperItemProvider) element).isReadOnly()) {
				element = TngUtil.unwrap(element);
			}
			
			boolean valid = false;
			if (element instanceof BreakdownElement) {
				if(element instanceof Descriptor) {
					Descriptor desc = (Descriptor) element;
					
					// descriptor must be linked and is not suppressed
					//
					valid = ProcessUtil.getAssociatedElement(desc) != null && !desc.getSuppressed().booleanValue();					
				}
				else {
					// exclude breakdown element without a super activity like TeamProfile's own RoleDescriptor
					//
					if (((BreakdownElement)element).getSuperActivities() == null) 
					{
						if(element instanceof Process && ((Process)element).eContainer() instanceof ProcessComponent) {
							valid = true;
						}
					}
					else {
						valid = element instanceof Activity || element instanceof TeamProfile;
					}
				}
			}
			if(valid) {
				selected.add(element);
			}
		}
		if (selected.isEmpty()) {
			return false;
		}
		return super.updateSelection(new StructuredSelection(selected));
	}


   /**
	 * 
	 * @see org.eclipse.emf.edit.ui.action.CommandActionHandler#run()
	 */
	public void run() {
		org.eclipse.epf.library.edit.util.IRunnableWithProgress runnable = new org.eclipse.epf.library.edit.util.IRunnableWithProgress() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
			 */
			public void run(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				canRun = ((SynchronizeCommand) command).initilize();
			}
		};
		UserInteractionHelper.runWithProgress(runnable, LibraryEditResources.ProcessAutoSynchronizeAction_prepare); 

		if (canRun) {
			super.run();
			// if(((AutoSynchronizeCommand)command).isSucessful()) {
			// AuthoringUIPlugin.getDefault().getMsgDialog().displayInfo(getText(),
			// "Synchronization has been completed successfully.");
			//				
			// }
		} else {
			AuthoringUIPlugin
					.getDefault()
					.getMsgDialog()
					.displayInfo(
							getText(),
							LibraryEditResources.ProcessAutoSynchronizeAction_noDescriptorToSynch); 
		}
	}

}
