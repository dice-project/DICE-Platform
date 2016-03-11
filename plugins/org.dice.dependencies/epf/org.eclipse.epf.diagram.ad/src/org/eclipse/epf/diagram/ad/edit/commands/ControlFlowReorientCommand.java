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
package org.eclipse.epf.diagram.ad.edit.commands;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.core.util.DiagramCoreValidation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.DecisionNode;
import org.eclipse.uml2.uml.ForkNode;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.MergeNode;

/**
 * @generated
 * @author Shashidhar Kannoori
 * @since 1.2
 */
public class ControlFlowReorientCommand extends EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	private EditPart host;

	/**
	 * @generated
	 */
	public ControlFlowReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (!(getElementToEdit() instanceof ControlFlow)) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @modified
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof ActivityNode && newEnd instanceof ActivityNode)) {
			return false;
		}
		ActivityNode target = getLink().getTarget();
//		if (!(getLink().eContainer() instanceof StructuredActivityNode)) {
//			return false;
//		}
//		StructuredActivityNode container = (StructuredActivityNode) getLink()
//				.eContainer();
//		return UMLBaseItemSemanticEditPolicy.LinkConstraints
//				.canExistControlFlow_3001(container, getNewSource(), target);
		if(!canConnect((ActivityNode)newEnd, target))
			return false;
		
		if (newEnd instanceof MergeNode || newEnd instanceof JoinNode) {
			ActivityNode join = (ActivityNode) newEnd;
			List<ActivityEdge> list = join.getOutgoings();
			if (list != null && list.size() >= 1) {
				return false;
			}
		}
		
		return true;
		
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof ActivityNode && newEnd instanceof ActivityNode)) {
			return false;
		}
		ActivityNode source = getLink().getSource();
//		if (!(getLink().eContainer() instanceof StructuredActivityNode)) {
//			return false;
//		}
//		StructuredActivityNode container = (StructuredActivityNode) getLink()
//				.eContainer();
//		return UMLBaseItemSemanticEditPolicy.LinkConstraints
//				.canExistControlFlow_3001(container, source, getNewTarget());
		
		if(!canConnect(source, (ActivityNode)newEnd))
			return false;
		
		if (newEnd instanceof DecisionNode || newEnd instanceof ForkNode) {
			ActivityNode join = (ActivityNode) newEnd;
			List<ActivityEdge> list = join.getIncomings();
			if (list != null && list.size() >= 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setSource(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setTarget(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected ControlFlow getLink() {
		return (ControlFlow) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected ActivityNode getOldSource() {
		return (ActivityNode) oldEnd;
	}

	/**
	 * @generated
	 */
	protected ActivityNode getNewSource() {
		return (ActivityNode) newEnd;
	}

	/**
	 * @generated
	 */
	protected ActivityNode getOldTarget() {
		return (ActivityNode) oldEnd;
	}

	/**
	 * @generated
	 */
	protected ActivityNode getNewTarget() {
		return (ActivityNode) newEnd;
	}
	
	/*
	 * @modified
	 */
	public void setHost(EditPart host){
		this.host = host;
	}
	
	/*
	 * 
	 */
	public EditPart getHost(){
		return this.host;
	}
	
	private boolean canConnect(ActivityNode source, ActivityNode target){
		if (!(getLink().eContainer() instanceof Activity)) {
			return false;
		}
		if(DiagramCoreValidation.isDuplicateRelationship(source, target))
			return false;
		if(getHost() != null){
			if (DiagramCoreValidation.canConnect((EditPart) getHost(), source, 
					target) != null)
				return false;
		}
		return true;
	}
}
