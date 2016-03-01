/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.diagram.core.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/**
 * 
 * @author Shashidhar Kannoori
 *
 */
public abstract class ActionDelegate extends Action implements IObjectActionDelegate {

	protected IWorkbenchPart targetPart;
	protected List<EditPart> selectedParts = new ArrayList<EditPart>();
	protected Request targetRequest;

	public ActionDelegate(){
		
	}
	/**
	 * 
	 */
	public ActionDelegate(IAction action, IWorkbenchPart targetPart) {
		this.targetPart = targetPart;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub
		this.targetPart = targetPart;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		// TODO Auto-generated method stub
		execute(getCommand(), new NullProgressMonitor());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		dropMenuItems();
		selectedParts.clear();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() >0
					&& structuredSelection.getFirstElement() instanceof EditPart) {
				selectedParts.addAll(structuredSelection.toList());
			}
		}
	}
	
	/**
	 * gives access to the diagram command stack
	 * 
	 * @return the diagram command stack
	 */
	protected DiagramCommandStack getDiagramCommandStack() {
		Object stack = targetPart.getAdapter(CommandStack.class);
		return (stack instanceof DiagramCommandStack) ? (DiagramCommandStack) stack
			: null;
	}
	
	protected final IWorkbenchPart getWorkbenchPart() {
		return targetPart;
	}
	
	/**
	 * Gets the contextmenuprovider.
	 * @return
	 */
	protected DiagramContextMenuProvider getDiagramContextMenuProvider() {
		if(targetPart != null){
			DiagramGraphicalViewer viewer = (DiagramGraphicalViewer) targetPart
				.getAdapter(GraphicalViewer.class);
			DiagramContextMenuProvider provider = (DiagramContextMenuProvider) viewer
				.getContextMenu();
			return provider;
		}
		return null;
	}
	
	/**
	 * Executes the given {@link Command}.
	 * 
	 * @param command
	 *            the command to execute
	 * @param progressMonitor
	 *            the progress monitor to use during execution
	 */
	protected final void execute(Command command,
			IProgressMonitor progressMonitor) {
		if (command == null || !command.canExecute())
			return;
		if (getDiagramCommandStack() != null)
			getDiagramCommandStack().execute(command, progressMonitor);
	}
	
	protected abstract Command getCommand();
	
	protected void dropMenuItems(){
	}
	
	 /**
     * Gets editing domain
     * 
     * @return my editing domain
     */
    protected TransactionalEditingDomain getEditingDomain() {
        
        // try adapting the workbench part
        IWorkbenchPart part = getWorkbenchPart();

        if (part != null) {
            IEditingDomainProvider edProvider = (IEditingDomainProvider) part
                .getAdapter(IEditingDomainProvider.class);

            if (edProvider != null) {
            	EditingDomain domain = edProvider.getEditingDomain();
            	
            	if (domain instanceof TransactionalEditingDomain) {
            		return (TransactionalEditingDomain) domain;
            	}
            }
        }
        return null;
    }
    
    /**
	 * Gets a request to be addressed to the operation set
	 * 
	 * @return a target request
	 */
	protected Request getTargetRequest() {
		if (targetRequest == null)
			targetRequest = createTargetRequest();
		return targetRequest;
	}

	/**
	 * Creates a new target request
	 * 
	 * @return the new target request
	 */
	protected abstract Request createTargetRequest();
	
}
