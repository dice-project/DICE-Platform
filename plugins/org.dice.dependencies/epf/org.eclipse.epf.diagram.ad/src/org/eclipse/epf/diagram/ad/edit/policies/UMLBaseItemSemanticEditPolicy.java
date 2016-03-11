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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.epf.diagram.ad.custom.commands.CreateIncomingControlFlowCommand;
import org.eclipse.epf.diagram.ad.custom.commands.CreateOutgoingControlFlowCommand;
import org.eclipse.epf.diagram.ad.edit.commands.ControlFlowReorientCommand;
import org.eclipse.epf.diagram.ad.edit.helpers.UMLBaseEditHelper;
import org.eclipse.epf.diagram.ad.edit.parts.ControlFlowEditPart;
import org.eclipse.epf.diagram.ad.part.UMLVisualIDRegistry;
import org.eclipse.epf.diagram.ad.providers.UMLElementTypes;
import org.eclipse.epf.diagram.core.util.DiagramCoreValidation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.SemanticEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IEditHelperContext;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyReferenceRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.GetEditContextRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class UMLBaseItemSemanticEditPolicy extends SemanticEditPolicy {

	/**
	 * Extended request data key to hold editpart visual id.
	 * 
	 * @generated
	 */
	public static final String VISUAL_ID_KEY = "visual_id"; //$NON-NLS-1$

	/**
	 * Add visual id of edited editpart to extended data of the request so
	 * command switch can decide what kind of diagram element is being edited.
	 * It is done in those cases when it's not possible to deduce diagram
	 * element kind from domain element.
	 * 
	 * @generated
	 */
	public Command getCommand(Request request) {
		if (request instanceof ReconnectRequest) {
			Object view = ((ReconnectRequest) request).getConnectionEditPart()
					.getModel();
			if (view instanceof View) {
				Integer id = new Integer(UMLVisualIDRegistry
						.getVisualID((View) view));
				request.getExtendedData().put(VISUAL_ID_KEY, id);
			}
		}
		return super.getCommand(request);
	}

	/**
	 * Returns visual id from request parameters.
	 * 
	 * @generated
	 */
	protected int getVisualID(IEditCommandRequest request) {
		Object id = request.getParameter(VISUAL_ID_KEY);
		return id instanceof Integer ? ((Integer) id).intValue() : -1;
	}

	/**
	 * @generated
	 */
	protected Command getSemanticCommand(IEditCommandRequest request) {
		IEditCommandRequest completedRequest = completeRequest(request);
		Object editHelperContext = completedRequest.getEditHelperContext();
		if (editHelperContext instanceof View
				|| (editHelperContext instanceof IEditHelperContext && ((IEditHelperContext) editHelperContext)
						.getEObject() instanceof View)) {
			// no semantic commands are provided for pure design elements
			return null;
		}
		if (editHelperContext == null) {
			editHelperContext = ViewUtil
					.resolveSemanticElement((View) getHost().getModel());
		}
		IElementType elementType = ElementTypeRegistry.getInstance()
				.getElementType(editHelperContext);
		if (elementType == ElementTypeRegistry.getInstance().getType(
				"org.eclipse.gmf.runtime.emf.type.core.default")) { //$NON-NLS-1$ 
			elementType = null;
		}
		Command epCommand = getSemanticCommandSwitch(completedRequest);
		if (epCommand != null) {
			ICommand command = epCommand instanceof ICommandProxy ? ((ICommandProxy) epCommand)
					.getICommand()
					: new CommandProxy(epCommand);
			completedRequest.setParameter(
					UMLBaseEditHelper.EDIT_POLICY_COMMAND, command);
		}
		Command ehCommand = null;
		if (elementType != null) {
			ICommand command = elementType.getEditCommand(completedRequest);
			if (command != null) {
				if (!(command instanceof CompositeTransactionalCommand)) {
					TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
							.getEditingDomain();
					command = new CompositeTransactionalCommand(editingDomain,
							null).compose(command);
				}
				ehCommand = new ICommandProxy(command);
			}
		}
		boolean shouldProceed = true;
		if (completedRequest instanceof DestroyRequest) {
			shouldProceed = shouldProceed((DestroyRequest) completedRequest);
		}
		if (shouldProceed) {
			if (completedRequest instanceof DestroyRequest) {
				TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
						.getEditingDomain();
				Command deleteViewCommand = new ICommandProxy(
						new DeleteCommand(editingDomain, (View) getHost()
								.getModel()));
				ehCommand = ehCommand == null ? deleteViewCommand : ehCommand
						.chain(deleteViewCommand);
			}
			return ehCommand;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getSemanticCommandSwitch(IEditCommandRequest req) {
		if (req instanceof CreateRelationshipRequest) {
			return getCreateRelationshipCommand((CreateRelationshipRequest) req);
		} else if (req instanceof CreateElementRequest) {
			return getCreateCommand((CreateElementRequest) req);
		} else if (req instanceof ConfigureRequest) {
			return getConfigureCommand((ConfigureRequest) req);
		} else if (req instanceof DestroyElementRequest) {
			return getDestroyElementCommand((DestroyElementRequest) req);
		} else if (req instanceof DestroyReferenceRequest) {
			return getDestroyReferenceCommand((DestroyReferenceRequest) req);
		} else if (req instanceof DuplicateElementsRequest) {
			return getDuplicateCommand((DuplicateElementsRequest) req);
		} else if (req instanceof GetEditContextRequest) {
			return getEditContextCommand((GetEditContextRequest) req);
		} else if (req instanceof MoveRequest) {
			return getMoveCommand((MoveRequest) req);
		} else if (req instanceof ReorientReferenceRelationshipRequest) {
			return getReorientReferenceRelationshipCommand((ReorientReferenceRelationshipRequest) req);
		} else if (req instanceof ReorientRelationshipRequest) {
			return getReorientRelationshipCommand((ReorientRelationshipRequest) req);
		} else if (req instanceof SetRequest) {
			return getSetCommand((SetRequest) req);
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getConfigureCommand(ConfigureRequest req) {
		return null;
	}

	/**
	 * @modified
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (UMLElementTypes.ControlFlow_3001 == req.getElementType()) {
			return req.getTarget() == null ? getCreateOutgoing(req)
					: getCreateIncomingComplete(req);
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getSetCommand(SetRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getEditContextCommand(GetEditContextRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDestroyReferenceCommand(DestroyReferenceRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getMoveCommand(MoveRequest req) {
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(
			ReorientReferenceRelationshipRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

	/*
	 * @modified
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case ControlFlowEditPart.VISUAL_ID:
			ControlFlowReorientCommand cmd = new ControlFlowReorientCommand(req);
			cmd.setHost(getHost());
			return getMSLWrapper(cmd);
		}
		return UnexecutableCommand.INSTANCE;
	}

	/**
	 * @generated
	 */
	protected Command getMSLWrapper(ICommand cmd) {
		return new ICommandProxy(cmd);
	}

	/**
	 * @generated
	 */
	protected EObject getSemanticElement() {
		return ViewUtil.resolveSemanticElement((View) getHost().getModel());
	}

	/**
	 * Finds container element for the new relationship of the specified type.
	 * Default implementation goes up by containment hierarchy starting from the
	 * specified element and returns the first element that is instance of the
	 * specified container class.
	 * 
	 * @generated
	 */
	protected EObject getRelationshipContainer(Object uelement,
			EClass containerClass, IElementType relationshipType) {
		if (uelement instanceof EObject) {
			EObject element = (EObject) uelement;
			for (; element != null; element = element.eContainer()) {
				if (containerClass.isSuperTypeOf(element.eClass())) {
					return element;
				}
			}
		}
		return null;
	}

	/**
	 * Returns editing domain from the host edit part.
	 * 
	 * @generated
	 */
	protected TransactionalEditingDomain getEditingDomain() {
		return ((IGraphicalEditPart) getHost()).getEditingDomain();
	}

	/**
	 * Creates command to destroy the link.
	 * 
	 * @modified
	 */
	protected Command getDestroyElementCommand(View view, boolean confirm) {
		EditPart editPart = (EditPart) getHost().getViewer()
				.getEditPartRegistry().get(view);

		// some views are suppressed and might not have a edit part
		//
		if(editPart == null) {
			return null;
		}
		
		DestroyElementRequest request = new DestroyElementRequest(
				getEditingDomain(), confirm);
		return editPart.getCommand(new EditCommandRequestWrapper(request,
				Collections.EMPTY_MAP));
	}

	/**
	 * Creates commands to destroy all host incoming and outgoing links.
	 * 
	 * @modified
	 */
	protected CompoundCommand getDestroyEdgesCommand(boolean confirm) {
		CompoundCommand cmd = new CompoundCommand();
		View view = (View) getHost().getModel();
		for (Iterator it = view.getSourceEdges().iterator(); it.hasNext();) {
			Command destroyCmd = getDestroyElementCommand((Edge) it.next(), confirm);
			if(destroyCmd != null) {
				cmd.add(destroyCmd);
			}
		}
		for (Iterator it = view.getTargetEdges().iterator(); it.hasNext();) {
			Command destroyCmd = getDestroyElementCommand((Edge) it.next(), confirm);
			if(destroyCmd != null) {
				cmd.add(destroyCmd);
			}
		}
		return cmd;
	}

	/**
	 * @generated
	 */
	public static class LinkConstraints {
		/**
		 * @generated
		 */
		private static final String OPPOSITE_END_VAR = "oppositeEnd"; //$NON-NLS-1$

		/**
		 * @generated
		 */
		public static boolean canCreateControlFlow_3001(
				StructuredActivityNode container, ActivityNode source,
				ActivityNode target) {
			return canExistControlFlow_3001(container, source, target);
		}

		/**
		 * @generated
		 */
		public static boolean canExistControlFlow_3001(
				StructuredActivityNode container, ActivityNode source,
				ActivityNode target) {
			return true;
		}

	}

	/**
	 * Children should override this, for any extra validations.
	 * 
	 * @modified
	 */
	protected Command getCreateIncomingComplete(CreateRelationshipRequest req) {
		Command command;
		if (req.getSource() == req.getTarget()) {
			return new Command() {
			};
		}

		if (!(req.getSource() instanceof ActivityNode)) {
			return UnexecutableCommand.INSTANCE;
		}
		if (req.getTarget() instanceof ActivityPartition) {
			return UnexecutableCommand.INSTANCE;
		}

		final EObject element = (EObject) req.getSource();
		if (element == null || !(element instanceof ActivityNode)) {
			return UnexecutableCommand.INSTANCE;
		}
		if (isDuplicateRelationship(req.getSource(), req.getTarget())) {
			return UnexecutableCommand.INSTANCE;
		}

		if (DiagramCoreValidation.canConnect((EditPart) getHost(), req
				.getSource(), req.getTarget()) != null)
			return UnexecutableCommand.INSTANCE;

		if (element instanceof ActivityNode) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Edge());
			}
			command = getMSLWrapper(new CreateIncomingControlFlowCommand(req,
					element.eContainer()));

			// if (req.getTarget() != req.getSource()) {
			// command = command.chain(new CreatePredecessorCommand(req));
			// }
			return command;

		}
		return null;
	}

	/**
	 * Check if the link between two ends already exists.
	 * 
	 * @modified
	 */
	private boolean isDuplicateRelationship(EObject source, EObject target) {
		EObject container = source.eContainer();
		if (container instanceof Activity) {
			Activity activity = (Activity) container;
			List list = activity.getEdges();
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				ActivityEdge element = (ActivityEdge) iter.next();
				if ((element.getSource() == source)
						&& element.getTarget() == target) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @modified
	 */
	protected Command getCreateOutgoing(CreateRelationshipRequest req) {
		if (!(req.getSource() instanceof ActivityNode)) {
			return null;
		}
		final EObject element = (EObject) req.getTarget();
		if (element == null || !(element instanceof ActivityNode)) {
			return new Command() {
			};
		}
		if (element instanceof ActivityNode) {
			// TODO: check the same type, don't create connection.
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivity_Edge());
			}
		}
		return getMSLWrapper(new CreateOutgoingControlFlowCommand(req));
	}

}
