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

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.bridge.DiagramAdapter;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.library.edit.command.ActionManager;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

public class EditorActionDelegate implements IEditorActionDelegate {

	protected IEditorPart targetEditor;
	protected List<EditPart> selectedParts = new ArrayList<EditPart>();

	
	public EditorActionDelegate() {
	}

	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.targetEditor = targetEditor;
	}

	public void run(IAction action) {
		// TODO Auto-generated method stub

	}

	public void selectionChanged(IAction action, ISelection selection) {
		selectedParts.clear();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() >0
					&& structuredSelection.getFirstElement() instanceof IGraphicalEditPart) {
				selectedParts.addAll(structuredSelection.toList());
			}
		}
	}
	
	protected ActionManager getActionManager(){
		if(targetEditor != null && targetEditor instanceof AbstractDiagramEditor){
			Diagram diagram = ((AbstractDiagramEditor)targetEditor).getDiagram();
			if(diagram != null){
				DiagramAdapter adapter = BridgeHelper.getDiagramAdapter(diagram.getElement());
				if(adapter != null){
					return (ActionManager)adapter.getActionManager();
				}
			}
		}
		return null;
	}
	
	 /**
     * Gets editing domain
     * 
     * @return my editing domain
     */
    protected TransactionalEditingDomain getEditingDomain() {
        
        // try adapting the workbench part
        IEditorPart part = targetEditor;

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
}
