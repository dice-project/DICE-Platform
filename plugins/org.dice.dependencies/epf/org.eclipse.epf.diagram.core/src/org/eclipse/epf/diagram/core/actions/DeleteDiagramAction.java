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
/**
 * 
 */
package org.eclipse.epf.diagram.core.actions;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.bridge.NodeAdapter;
import org.eclipse.epf.diagram.core.services.DiagramHelper;
import org.eclipse.epf.persistence.FileManager;
import org.eclipse.epf.services.Services;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Activity;

/**
 * Deletes the diagram.
 * @author Shashidhar Kannoori
 * @author Phong Nguyen Le
 */
public class DeleteDiagramAction extends ActionDelegate {

	public DeleteDiagramAction(){
		super();
	}
	public DeleteDiagramAction(IAction action, IWorkbenchPart targetPart) {
		super(action, targetPart);
	}

	private EditPart mySelectedElement;
	//private IWorkbenchPart targetPart;
	/**
	 * 
	 */
	

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
	 */
//	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
//		// TODO Auto-generated method stub
//		this.targetPart = targetPart;
//	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		if (DiagramCorePlugin
				.getDefault()
				.getMsgDialog()
				.displayPrompt(
						DiagramCoreResources.DeleteDiagram_text, //$NON-NLS-1$
						DiagramCoreResources.DeleteDiagram_prompt)) { //$NON-NLS-1$
			if(mySelectedElement != null){
				Diagram diagram = (Diagram)mySelectedElement.getModel();
				if(diagram != null){
					try {
						Resource resource = diagram.eResource();
						if(resource != null) {
							IStatus status = Services.getAccessController().checkModify(new Resource[] { resource }, targetPart.getSite().getShell());
							if(!status.isOK()) {
								return;
							}
						}
						
						DiagramHelper.deleteDiagram(diagram, true);
						targetPart.getSite().getPage().closeEditor(
								targetPart.getSite().getPage()
										.getActiveEditor(), false);
					} catch(Exception ex) {
						DiagramCorePlugin.getDefault().getLogger().logError(ex);
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		dropMenuItems();
		mySelectedElement = null;
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() == 1
					&& structuredSelection.getFirstElement() instanceof EditPart) {
				mySelectedElement = (EditPart) structuredSelection
						.getFirstElement();
			}
		}
		if(mySelectedElement != null){
			action.setChecked(false);
			if(mySelectedElement instanceof DiagramEditPart){
				if(!((DiagramEditPart)mySelectedElement).isEditModeEnabled()){
					action.setEnabled(false);
					IContributionItem item = getDiagramContextMenuProvider().find(ActionIds.MENU_EDIT);
					if(item != null) item.setVisible(false); 
				}
			}
			
			Diagram diagram = (Diagram)mySelectedElement.getModel();
			if(diagram != null){
				EObject model = diagram.getElement();
				if(model instanceof Activity){
					NodeAdapter adapter= BridgeHelper.getNodeAdapter(model);
					if(adapter != null && adapter.isTargetReadOnly()){
						action.setEnabled(false);
						IContributionItem item = getDiagramContextMenuProvider().find(ActionIds.MENU_EDIT);
						if(item != null) item.setVisible(false); 
					}
				}else if(model instanceof org.eclipse.epf.diagram.model.Diagram){
//					if(((org.eclipse.epf.diagram.model.Diagram)diagram).isReadOnly()){
//						action.setEnabled(false);
//					}
				}
				
			}
		}else{
			action.setEnabled(false);
		}
	}

	@Override
	protected Request createTargetRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Command getCommand() {
		// TODO Auto-generated method stub
		return null;
	}
}
