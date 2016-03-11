/*
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *
 */
package org.eclipse.epf.diagram.ad.edit.policies;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.epf.diagram.ad.custom.commands.ActivityPartitionDestroyCommand;
import org.eclipse.epf.diagram.ad.custom.commands.CreateActivityNodeInActivityPartition;
import org.eclipse.epf.diagram.ad.edit.commands.ActivityPartitionCreateCommand;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityEditPart;

import org.eclipse.epf.diagram.ad.providers.UMLElementTypes;
import org.eclipse.epf.diagram.core.DiagramCoreResources;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;

import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.commands.AddCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class ActivityPartitionPartitionCampartmentItemSemanticEditPolicy extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @modified
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (UMLElementTypes.ActivityPartition_2001 == req.getElementType()) {		
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE.getActivityPartition_Subpartition());
			}
			return getMSLWrapper(new CreateActivityPartition_2001Command(req));
		}
		
		// modified START
		Command command = getCreateActivityNodeCommand(req);
		if (command != null) {
			return command;
		}
		// modified END
		return super.getCreateCommand(req);
	}

	/**
	 * @modified
	 */
	private static class CreateActivityPartition_2001Command extends
			CreateElementCommand {

		/**
		 * @generated
		 */
		public CreateActivityPartition_2001Command(CreateElementRequest req) {
			super(req);
		}

		/**
		 * @generated
		 */
		protected EClass getEClassToEdit() {
			return UMLPackage.eINSTANCE.getActivityPartition();
		};

		/**
		 * @generated
		 */
		protected EObject getElementToEdit() {
			EObject container = ((CreateElementRequest) getRequest())
					.getContainer();
			if (container instanceof View) {
				container = ((View) container).getElement();
			}
			return container;
		}
	}
	
	
	/**
	 * @modified
	 */
	private Command getCreateActivityNodeCommand(CreateElementRequest req) {
		if (UMLElementTypes.StructuredActivityNode_1007 == req.getElementType()
				|| UMLElementTypes.StructuredActivityNode_1010 == req
						.getElementType()
				|| UMLElementTypes.StructuredActivityNode_1011 == req
						.getElementType()
				|| UMLElementTypes.MergeNode_1002 == req.getElementType()
				|| UMLElementTypes.ForkNode_1003 == req.getElementType()
				|| UMLElementTypes.InitialNode_1004 == req.getElementType()
				|| UMLElementTypes.DecisionNode_1005 == req.getElementType()
				|| UMLElementTypes.JoinNode_1006 == req.getElementType()
				|| UMLElementTypes.ActivityFinalNode_1001 == req
						.getElementType()
				|| UMLElementTypes.ActivityParameterNode_1009 == req
						.getElementType()
				|| UMLElementTypes.ActivityParameterNode_1012 == req
						.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivityPartition_Node());
			}
			return getMSLWrapper(new CreateActivityNodeInActivityPartition(req));
		}
		return null;
	}

	/**
	 * @see org.eclipse.epf.gmf.diagram.edit.policies.UMLBaseItemSemanticEditPolicy#getCreateOutgoing(org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest)
	 * @modified
	 */
	protected Command getCreateOutgoing(CreateRelationshipRequest req) {
		return UnexecutableCommand.INSTANCE;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.diagram.ad.edit.policies.UMLBaseItemSemanticEditPolicy#getDestroyElementCommand(org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest)
	 * @modified
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		/* modify START */
		CompositeCommand cc = new CompositeCommand(DiagramCoreResources.deleteCommand_label);
		getReparentingCommand(req, cc);
		cc.compose(new CommandProxy(getMSLWrapper(new ActivityPartitionDestroyCommand(req, getHost()))));
		return cc.isEmpty() ? null : new ICommandProxy(cc.reduce());
		/* modify END */
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.epf.diagram.ad.edit.policies.UMLBaseItemSemanticEditPolicy#getDestroyReferenceCommand(org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest)
	 * @modified
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return super.getDestroyReferenceCommand(req);
	}

	
	/**
	 * Command to reparenting the elements to Activity before deleting the ActivityPartition.
 	 *
	 * @param request the request
	 * @return command
	 */
	protected Command getReparentingCommand(DestroyElementRequest request, CompositeCommand cc) {
		Iterator editParts = getHost().getChildren().iterator();
		Object parent  =  ((EditPart)getHost()).getParent();
		EReference feature = UMLPackage.eINSTANCE.getActivity_Node();
		
		if(!(parent instanceof ActivityEditPart)){
			return UnexecutableCommand.INSTANCE;
		}
		
		View container = (View)((EditPart)parent).getAdapter(View.class);
		
		EObject containerContext = container == null ? null : ViewUtil.resolveSemanticElement(container);
		while ( editParts.hasNext() ) {
			IGraphicalEditPart ep = (IGraphicalEditPart)editParts.next();
			if ( ep instanceof LabelEditPart ) {
				continue;
			}
			
			View view = (View)ep.getAdapter(View.class);
			if ( view == null ) {
				continue;
			}
			
			EObject semanticContext = ViewUtil.resolveSemanticElement(view);
			if(semanticContext instanceof ActivityPartition)
				continue;
			
			
			 TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
	            .getEditingDomain();
			 
			if ( semanticContext == null ) {
				AddCommand addCommand = new AddCommand(editingDomain, new EObjectAdapter(container),
						  new EObjectAdapter(view));
				cc.compose(addCommand);
			}
			else if ( containerContext != null ) {
				if (containerContext.equals(semanticContext.eContainer())
						&& feature == semanticContext.eContainmentFeature()) {
					cc.compose(new ReparentingElementsCommand(request, semanticContext));
					AddCommand addCommand = new AddCommand(editingDomain, new EObjectAdapter(container),
							  new EObjectAdapter(view));
					cc.compose(addCommand);
				}
				else{
					Command moveSemanticCmd =
					((ActivityEditPart)parent).getCommand(
						new EditCommandRequestWrapper(
							new MoveRequest(editingDomain, containerContext, 
									UMLPackage.eINSTANCE.getActivity_Node(),
									semanticContext)));
					if(moveSemanticCmd  != null){
						cc.compose(new CommandProxy(moveSemanticCmd));
					}
//					cc.compose(new ReparentingElementsCommand(request, semanticContext));
					//move notation
					AddCommand addCommand = new AddCommand(editingDomain, new EObjectAdapter(container),
							  new EObjectAdapter(view));
					cc.compose(addCommand);
				}
			}
		}
		return cc.isEmpty() ? null : new ICommandProxy(cc.reduce());
	}
	
	public class ReparentingElementsCommand extends EditElementCommand{

		private final EObject moveElement;
		
		public ReparentingElementsCommand(DestroyElementRequest req, EObject element) {
			this(DiagramCoreResources.deleteCommand_label, element, req);
		}
		
		protected ReparentingElementsCommand(String label, EObject elementToEdit, 
				IEditCommandRequest request) {
			super(label, elementToEdit, request);
			this.moveElement = elementToEdit; 
		}

		@Override
		protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
			View view = (View)getHost().getModel();
			if(view.getElement() instanceof ActivityPartition){
				if(moveElement instanceof ActivityNode){
					((ActivityNode)moveElement).getInPartitions().remove(view.getElement());
				}
			}
			return null;
		}
		@Override
		public boolean canExecute() {
			if(moveElement != null) return true;
			return false;
		}
	}
	
}
