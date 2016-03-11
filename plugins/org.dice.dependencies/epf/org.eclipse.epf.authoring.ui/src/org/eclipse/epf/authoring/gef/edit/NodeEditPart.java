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
package org.eclipse.epf.authoring.gef.edit;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.epf.authoring.gef.commands.CreateLinkCommand;
import org.eclipse.epf.authoring.gef.commands.DeleteCommand;
import org.eclipse.epf.authoring.gef.commands.ReconnectLinkCommand;
import org.eclipse.epf.authoring.gef.util.ConnectionAnchorLocator;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.requests.ReconnectRequest;

/**
 * Provides support for connections, and installs EditPolicy for (
 * {@link EditPolicy.COMPONENT_ROLE}, {@link EditPolicy.GRAPHICAL_NODE_ROLE},
 * {@linkEditPolicy.SELECTION_FEEDBACK_ROLE})
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public abstract class NodeEditPart extends BaseEditPart implements
		org.eclipse.gef.NodeEditPart {

	public NodeEditPart(Node model) {
		super(model);
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new ComponentEditPolicy() {
			protected Command createDeleteCommand(GroupRequest deleteRequest) {
				Boolean bool = (Boolean) deleteRequest.getExtendedData().get(
						DeleteCommand.KEY_PERM_DELETE);
				boolean permDelete = bool == null ? false : bool.booleanValue();
				DeleteCommand cmd = new DeleteCommand(permDelete);
				cmd.setPartToBeDeleted(getHost().getModel());
				return cmd;
			}
		});

		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new GraphicalNodeEditPolicy() {
					protected Command getConnectionCompleteCommand(
							CreateConnectionRequest request) {
						CreateLinkCommand cmd = (CreateLinkCommand) request
								.getStartCommand();
						cmd.setTarget((Node) getHost().getModel());

						if (request.getTargetEditPart() instanceof ConnectionAnchorLocator) {
							if (request.getTargetEditPart().equals(
									request.getSourceEditPart())) {

							} else {
								cmd
										.setTargetEndPoint(((ConnectionAnchorLocator) request
												.getTargetEditPart())
												.getLocation(request
														.getLocation()));
							}
						}

						return cmd;
					}

					protected Command getConnectionCreateCommand(
							CreateConnectionRequest request) {
						CreateLinkCommand cmd = new CreateLinkCommand(
								ModelFactory.eINSTANCE.createLink(),
								(Node) getHost().getModel());
						if (request.getTargetEditPart() instanceof ConnectionAnchorLocator) {
							cmd
									.setSourceEndPoint(((ConnectionAnchorLocator) request
											.getTargetEditPart())
											.getLocation(request.getLocation()));
						} else {
							cmd.setSourceEndPoint(request.getLocation());
						}
						request.setStartCommand(cmd);

						return cmd;

					}

					protected Command getReconnectSourceCommand(
							ReconnectRequest request) {
						Link link = (Link) request.getConnectionEditPart()
								.getModel();
						ReconnectLinkCommand cmd = new ReconnectLinkCommand(
								link, (Node) getHost().getModel(), true);
						if (request.getTarget() instanceof ConnectionAnchorLocator) {
							cmd.setEndPoint(((ConnectionAnchorLocator) request
									.getTarget()).getLocation(request
									.getLocation()));
						}

						return cmd;
					}

					protected Command getReconnectTargetCommand(
							ReconnectRequest request) {
						Link link = (Link) request.getConnectionEditPart()
								.getModel();
						ReconnectLinkCommand cmd = new ReconnectLinkCommand(
								link, (Node) getHost().getModel(), false);
						if (request.getTarget() instanceof ConnectionAnchorLocator) {
							cmd.setEndPoint(((ConnectionAnchorLocator) request
									.getTarget()).getLocation(request
									.getLocation()));
						}
						return cmd;
					}
				});

		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
		// By default, can't add anything to a node
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new LayoutEditPolicy() {
			protected EditPolicy createChildEditPolicy(EditPart child) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}

			protected Command getDeleteDependantCommand(Request request) {
				return null;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}
		});
	}

	protected Node getNode() {
		return (Node) getModel();
	}

	protected List getModelSourceConnections() {
		return getNode().getOutgoingConnections();
	}

	protected List getModelTargetConnections() {
		return getNode().getIncomingConnections();
	}

	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return new ChopboxAnchor(getFigure());
	}

	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		return new ChopboxAnchor(getFigure());
	}

	protected void handlePropertyChanged(Notification msg) {
		switch (msg.getFeatureID(Node.class)) {
		case ModelPackage.NODE__LOCATION:
		case ModelPackage.NODE__WIDTH:
		case ModelPackage.NODE__HEIGHT:			
			refreshVisuals();
			break;
		case ModelPackage.NODE__READ_ONLY:
			refreshVisuals();
			for (Iterator iter = getTargetConnections().iterator(); iter
					.hasNext();) {
				ConnectionEditPart conn = (ConnectionEditPart) iter.next();
				conn.refresh();
			}
			break;

		case ModelPackage.NODE__OUTGOING_CONNECTIONS:
			refreshSourceConnections();
			break;

		case ModelPackage.NODE__INCOMING_CONNECTIONS:
			refreshTargetConnections();
			break;
		}
	}

	protected void refreshVisuals() {
		super.refreshVisuals();
		Rectangle constraint = new Rectangle(0, 0, -1, -1);
		if (getNode().getLocation() != null)
			constraint.setLocation(getNode().getLocation());
		constraint.width = getNode().getWidth();
		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), constraint);
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.BaseEditPart#activate()
	 */
	public void activate() {
		super.activate();
		((Node) getModel()).addConsumer(this);
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.BaseEditPart#deactivate()
	 */
	public void deactivate() {
		((Node) getModel()).removeConsumer(this);
		super.deactivate();
	}

}