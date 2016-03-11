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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.epf.diagram.core.DiagramCoreResources;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.core.bridge.DiagramAdapter;
import org.eclipse.epf.diagram.core.commands.DeleteDiagramCommand;
import org.eclipse.epf.diagram.core.part.AbstractDiagramEditor;
import org.eclipse.epf.diagram.core.util.DiagramCoreValidation;
import org.eclipse.epf.diagram.model.ActivityDetailDiagram;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.ui.actions.ProcessDeleteAction;
import org.eclipse.epf.uma.Activity;
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
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.ActivityNode;


/**
 * Replaces the default the Delete from Model and Delete from Diagram actions of GMF.
 * Delete the diagram element and its associated diagram and references. 
 * @author Shashidhar Kannoori.
 */
public class DeleteElementActionDelegate extends ActionDelegate {

	public DeleteElementActionDelegate() {
		super();
	}
	public DeleteElementActionDelegate(IAction action, IWorkbenchPart targetPart) {
		super(action, targetPart);
	}


	@Override
	protected Command getCommand() {
		targetRequest = null;
		Request request = getTargetRequest();
		Iterator<EditPart> editParts = selectedParts.iterator();
		
		List<Activity> activities = new ArrayList<Activity>();
		
		
		org.eclipse.epf.uma.Process targetProcess = getOwningProcess();
		
		// Delete the diagram element and diagram model.
		CompositeTransactionalCommand command =
			new CompositeTransactionalCommand(getEditingDomain(), DiagramCoreResources.deleteCommand_label){
			@Override
			public boolean canUndo() {
				Iterator<EditPart> editParts = selectedParts.iterator();
				while (editParts.hasNext()) {
					EditPart editPart = (EditPart) editParts.next();
					// disable on diagram links 
					if (editPart instanceof IGraphicalEditPart) {
						IGraphicalEditPart gEditPart = (IGraphicalEditPart) editPart;
						View view = (View) gEditPart.getModel();
						EObject element = ViewUtil.resolveSemanticElement(view);
						if (view instanceof Diagram || element instanceof Diagram)
							continue;

						MethodElement e = BridgeHelper.getMethodElement(view);
						if (e != null && e instanceof Activity) {
							return false;
						}
					}
				}
				return true;
			}
		};
		while (editParts.hasNext()) {
			EditPart editPart = (EditPart) editParts.next();
			// disable on diagram links 
			if (editPart instanceof IGraphicalEditPart) {
				IGraphicalEditPart gEditPart = (IGraphicalEditPart) editPart;
				View view = (View) gEditPart.getModel();
				EObject element = ViewUtil.resolveSemanticElement(view);
				if (view instanceof Diagram || element instanceof Diagram)
					return null;

				MethodElement e = BridgeHelper.getMethodElement(view);
				if (e != null && e instanceof Activity) {
					activities.add((Activity) e);
				}

				Command curCommand = null;
				if (editPart instanceof IGraphicalEditPart
						&& isCanonical((IGraphicalEditPart) editPart)) {
					curCommand = editPart.getCommand(request);
				} else {
					curCommand = editPart
							.getCommand(getSecondaryTargetRequest());
				}
				if(curCommand == null && isDiagramUIObject(view)){
					curCommand = editPart
					.getCommand(getSecondaryTargetRequest());
				}
				if (curCommand != null) {
					command.compose(new CommandProxy(curCommand));
				}
			}
		}
		if ((command.isEmpty())
			|| (command.size() != selectedParts.size())){
			return UnexecutableCommand.INSTANCE;
		}
		// Delete the related diagram of deleted Activity
		if(!activities.isEmpty()){
			new DeleteDiagramCommand(activities, targetProcess).execute();
		}
		return new ICommandProxy(command);
	}

	private boolean isDiagramUIObject(View view) {
		return view.getElement() == null;
	}
	@Override
	protected Request createTargetRequest() {
		return 	new EditCommandRequestWrapper(new DestroyElementRequest(getEditingDomain(), false));
	}
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		setEnabled(true);
		super.selectionChanged(action, selection);
		for (Iterator<EditPart> iter = selectedParts.iterator(); iter.hasNext();) {
			EditPart element = (EditPart) iter.next();
			View view = (View)element.getModel();
			if(view.getElement() == null) {
				// this view is not associated with any model
				// it can be deleted
				//
				continue;
			}
			if(view instanceof Diagram 
					|| BridgeHelper.isReadOnly(view)
					|| TngUtil.isLocked(getOwningProcess())
					|| (view.getElement() instanceof org.eclipse.epf.diagram.model.LinkedObject && 
							view.getDiagram().getElement() instanceof ActivityDetailDiagram)
					|| ((BridgeHelper.getMethodElement(view) != null || 
							(view.getElement() instanceof ActivityNode && BridgeHelper.isSynchBar((ActivityNode) view.getElement()))) &&
							BridgeHelper.isInherited(view))
							) {
				action.setEnabled(false);
				setEnabled(false);
				break;
			}
			if(view instanceof Edge){
				if(DiagramCoreValidation.checkDelete((Edge)view) != null){
					action.setEnabled(false);
					setEnabled(false);
					break;
				}
			}
		}
	}
	
	@Override
	public void run(IAction action) {
		if(!isEnabled()) return;
		List<BreakdownElement> elements = new ArrayList<BreakdownElement>();
		for (Iterator iter = selectedParts.iterator(); iter
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
				super.run(action);

				// save the editor
				// 
				BusyIndicator.showWhile(targetPart.getSite().getShell()
						.getDisplay(), new Runnable() {

					public void run() {
						((AbstractDiagramEditor)targetPart).doSave(new NullProgressMonitor());
						if(getDiagramCommandStack() != null){
							getDiagramCommandStack().flush();
						}
					}
				});
			}

		} else {
			super.run(action);
		}
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
	private Request secondaryTargetRequest;
	
	private org.eclipse.epf.uma.Process getOwningProcess(){
		if(targetPart != null){
			Diagram d = ((AbstractDiagramEditor)targetPart).getDiagram();
			DiagramAdapter adapter = BridgeHelper.getDiagramAdapter(d.getElement());
			if(adapter != null){
				return TngUtil.getOwningProcess(adapter.getActivity());
			}
		}
		return null;
	}
}
