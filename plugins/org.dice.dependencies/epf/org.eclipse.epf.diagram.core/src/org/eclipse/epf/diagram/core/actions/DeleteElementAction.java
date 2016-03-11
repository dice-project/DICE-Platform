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
package org.eclipse.epf.diagram.core.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.ui.actions.ProcessDeleteAction;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.WorkProductDescriptor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.actions.AbstractDeleteFromAction;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Replaces the default the Delete from Model and Delete from Diagram actions of GMF.
 * Delete the diagram element and its associated diagram and references. 
 * @author Shashidhar Kannoori.
 */
public class DeleteElementAction extends AbstractDeleteFromAction {

	boolean removeModel = true;
	/** the target request */
	private Request secondaryTargetRequest;
	/**
	 * @param part
	 */
	public DeleteElementAction(IWorkbenchPart part) {
		super(part);
		
	}
	@Override
	protected boolean calculateEnabled() {
		
		List operationSet = getOperationSet();
		if (operationSet.isEmpty()) {
			return false;
		}
		Request request = getTargetRequest();
		Iterator editParts = operationSet.iterator();
		while (editParts.hasNext()) {
			EditPart editPart = (EditPart) editParts.next();
			// disable on diagram links 
			if (editPart instanceof IGraphicalEditPart) {
				IGraphicalEditPart gEditPart = (IGraphicalEditPart) editPart;
				View view = (View) gEditPart.getModel();
				EObject element = ViewUtil.resolveSemanticElement(view);
				if ((element.eIsProxy())
					|| (element instanceof Diagram)
					|| BridgeHelper.isReadOnly(view)) {
					return false;
				}
			} else {
				Command curCommand = editPart.getCommand(request);
				if (curCommand == null || (curCommand.canExecute() == false)) {
					return false;
				}
			}
		}
		//return super.calculateEnabled();
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		setEnabled(true);
		List list = getSelectedObjects();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EditPart element = (EditPart) iter.next();
			View view = (View)element.getModel();
			if(BridgeHelper.isReadOnly(view)){
				setEnabled(false);
				return false;
			}
		}
		return super.isEnabled();
	}
	
	
	
	protected Request createTargetRequest() {
		return 	new EditCommandRequestWrapper(new DestroyElementRequest(getEditingDomain(), false));
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

	@Override
	protected boolean isSelectionListener() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected Request getTargetRequest() {
		// TODO Auto-generated method stub
		return super.getTargetRequest();
	}
	
	protected boolean showInContextMenu(){
		List operationSet = getOperationSet();
		if (operationSet.isEmpty()) {
			return false;
		}
		Request request = getTargetRequest();
		Iterator editParts = operationSet.iterator();
		while (editParts.hasNext()) {
			EditPart editPart = (EditPart) editParts.next();
			// disable on diagram links 
			if (editPart instanceof IGraphicalEditPart) {
				IGraphicalEditPart gEditPart = (IGraphicalEditPart) editPart;
				View view = (View) gEditPart.getModel();
				EObject element = ViewUtil.resolveSemanticElement(view);
				if ((element.eIsProxy())
					|| (element instanceof Diagram)) {
					return false;
				}
			} else {
				Command curCommand = editPart.getCommand(request);
				if (curCommand == null || (curCommand.canExecute() == false)) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	protected Command getCommand(Request request) {
		//return super.getCommand(request);
		List operationSet = getOperationSet();
		Iterator editParts = operationSet.iterator();
		CompositeTransactionalCommand command =
			new CompositeTransactionalCommand(getEditingDomain(), getCommandLabel());
		while (editParts.hasNext()) {
			EditPart editPart = (EditPart) editParts.next();
			// disable on diagram links 
			if (editPart instanceof IGraphicalEditPart){
				IGraphicalEditPart gEditPart = 
					(IGraphicalEditPart) editPart;
				View view = (View)gEditPart.getModel();
				EObject element = ViewUtil.resolveSemanticElement(view);
				if(element instanceof Diagram)
					return null;
			}
			Command curCommand = null;
			if(editPart instanceof IGraphicalEditPart && isCanonical((IGraphicalEditPart)editPart)){
				curCommand = editPart.getCommand(request);
			}else{
				curCommand = editPart.getCommand(getSecondaryTargetRequest());
			}
			if (curCommand != null) {
				command.compose(new CommandProxy(curCommand));				
			}
		}
		
		if ((command.isEmpty())
			|| (command.size() != operationSet.size())){
			return UnexecutableCommand.INSTANCE;
		}
		return new ICommandProxy(command);
	}
	@Override
	protected void doRun(IProgressMonitor progressMonitor) {
		
		List operationSet = getOperationSet();
		List<BreakdownElement> elements = new ArrayList<BreakdownElement>();
		for (Iterator iter = operationSet.iterator(); iter
				.hasNext();) {
			Object editPart = iter.next();
			if (editPart instanceof EditPart) {
				Object obj = ((EditPart) editPart).getModel();
				if (obj instanceof Node) {
					MethodElement element = BridgeHelper.getMethodElement((Node)obj);
					if (element != null && element instanceof BreakdownElement) {
						elements.add((BreakdownElement)element);
					}
				}
			}
		}
		if (!elements.isEmpty()) {
			ProcessDeleteAction deleteAction = new ProcessDeleteAction() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.epf.authoring.ui.actions.MethodElementDeleteAction#createCommand(java.util.Collection)
				 */
				public org.eclipse.emf.common.command.Command createCommand(
						Collection selection) {
					domain = null;
					for (Iterator iter = selection.iterator(); iter
							.hasNext();) {
						Object element = iter.next();
						if (element instanceof WorkProductDescriptor) {
							domain = new AdapterFactoryEditingDomain(
									TngAdapterFactory.INSTANCE
											.getPBS_ComposedAdapterFactory(),
									new BasicCommandStack());
							break;
						}
					}
					if (domain == null) {
						domain = new AdapterFactoryEditingDomain(
								TngAdapterFactory.INSTANCE
										.getWBS_ComposedAdapterFactory(),
								new BasicCommandStack());
					}
					return super.createCommand(selection);
				}

			};
			deleteAction.updateSelection(new StructuredSelection(
					elements));
			deleteAction.run();
			if (deleteAction.isDeletionConfirmed()) {
				super.doRun(progressMonitor);

				// save the editor
				// 
				BusyIndicator.showWhile(getWorkbenchPart().getSite().getShell()
						.getDisplay(), new Runnable() {

					public void run() {
						((AbstractDiagramEditor)getWorkbenchPart()).doSave(new NullProgressMonitor());
					}
				});
			}

		} else {
			super.doRun(progressMonitor);
		}
	}
	
	@Override
	public void init() {
		super.init();
		setId(ActionFactory.DELETE.getId());
		setText(DiagramCoreResources.deleteCommand_label);
		setToolTipText(DiagramCoreResources.deleteCommand_label);
		ISharedImages workbenchImages = PlatformUI.getWorkbench().getSharedImages();
		setHoverImageDescriptor(
			workbenchImages.getImageDescriptor(
				ISharedImages.IMG_TOOL_DELETE));
		setImageDescriptor(
			workbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		setDisabledImageDescriptor(
			workbenchImages.getImageDescriptor(
				ISharedImages.IMG_TOOL_DELETE_DISABLED));
	}
	
	@Override
	protected List createOperationSet() {
		// TODO Auto-generated method stub
		return super.createOperationSet();
	}
	
	/**
	 * @param gep
	 * @return
	 */
	private boolean isCanonical(IGraphicalEditPart gep) {
		EditPart parent = gep.getParent();
		if (parent instanceof IGraphicalEditPart) {
			CanonicalEditPolicy cep = (CanonicalEditPolicy)parent.getEditPolicy(EditPolicyRoles.CANONICAL_ROLE);
			if ( cep != null ) {
				if (cep.isEnabled())
					return true;
			}
		}
		return false;
	}
	
	public Request getSecondaryTargetRequest(){
		if(secondaryTargetRequest == null){
			secondaryTargetRequest = new GroupRequest(
					RequestConstants.REQ_DELETE);
		}
		return secondaryTargetRequest;
	}
}
