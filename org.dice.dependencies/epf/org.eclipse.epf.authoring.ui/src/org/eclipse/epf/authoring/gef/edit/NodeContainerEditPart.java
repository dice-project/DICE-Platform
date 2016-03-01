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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FanRouter;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.authoring.gef.commands.DeleteCommand;
import org.eclipse.epf.authoring.gef.edit.policies.NodeContainerEditPolicy;
import org.eclipse.epf.authoring.gef.edit.policies.NodeContainerFlowLayoutEditPolicy;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.gef.ui.actions.ActionRegistry;

/**
 * NodeContainerEditPart manages the child editparts in a {@link GraphicalViewer}.
 * Provides instructure to handle the notifications from model.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public abstract class NodeContainerEditPart extends AbstractGraphicalEditPart
		implements LayerConstants {

	private BendpointConnectionRouter cRouter;

	protected Adapter modelListener = new AdapterImpl() {
		public void notifyChanged(Notification msg) {
			handlePropertyChanged(msg);
		}
	};

	public NodeContainerEditPart(NodeContainer model) {
		super();
		setModel(model);
	}

	public void activate() {
		super.activate();
		NodeContainer model = (NodeContainer) ((EObject) getModel());
		model.eAdapters().add(modelListener);
		model.addConsumer(this);
	}

	protected void createEditPolicies() {
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
		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
		installEditPolicy(EditPolicy.CONTAINER_ROLE,
				new NodeContainerEditPolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, null);
		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new NodeContainerFlowLayoutEditPolicy());
	}

	public void deactivate() {
		NodeContainer model = (NodeContainer) ((EObject) getModel());
		model.eAdapters().remove(modelListener);
		model.removeConsumer(this);

		super.deactivate();
	}

	protected EditPart findChildByModel(Object model) {
		for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
			EditPart child = (EditPart) iter.next();
			if (child.getModel() == model) {
				return child;
			}
		}
		return null;
	}

	protected void addPartToEdit(Collection partsToEdit, Object node) {
		if (node instanceof NamedNode) {
			EditPart editPart = findChildByModel(node);
			if (editPart instanceof BaseEditPart) {
				DirectEditManager editManager = ((BaseEditPart) editPart)
						.getDirectEditManager();
				if (editManager instanceof ValidatingDirectEditManager
						&& ((ValidatingDirectEditManager) editManager)
								.validate(((NamedNode) node).getName()) != null) {
					partsToEdit.add(editPart);
				}
			}
		}
	}

	protected void handlePropertyChanged(Notification msg) {
		switch (msg.getFeatureID(NodeContainer.class)) {
		case ModelPackage.NODE__LOCATION:
		case ModelPackage.NODE__WIDTH:
			refreshVisuals();
			break;
		case ModelPackage.NODE__OUTGOING_CONNECTIONS:
			refreshSourceConnections();
			break;
		case ModelPackage.NODE__INCOMING_CONNECTIONS:
			refreshTargetConnections();
			break;
		case ModelPackage.NODE_CONTAINER__NODES:
			refreshChildren();

			List partsToEdit = new ArrayList();
			switch (msg.getEventType()) {
			case Notification.ADD:
				addPartToEdit(partsToEdit, msg.getNewValue());
				break;
			case Notification.ADD_MANY:
				for (Iterator iter = ((Collection) msg.getNewValue())
						.iterator(); iter.hasNext();) {
					addPartToEdit(partsToEdit, iter.next());
				}
				break;
			}

			if (getViewer().getControl().isFocusControl()) {
				for (Iterator iter = partsToEdit.iterator(); iter.hasNext();) {
					BaseEditPart editPart = (BaseEditPart) iter.next();
					editPart.performDirectEdit();
				}
			}

			break;
		}
	}

	protected void refreshVisuals() {
		Node node = (Node) getModel();
		Dimension size = new Dimension(node.getWidth(), node.getHeight());
		if (size == null) {
			size = new Dimension(60, 100);
		}
		Point loc = node.getLocation();
		if (loc == null) {
			loc = new Point(10, 10);
		}
		Rectangle r = new Rectangle(loc, size);

		((GraphicalEditPart) getParent()).setLayoutConstraint(this,
				getFigure(), r);

		if (cRouter == null) {
			ConnectionLayer cLayer = (ConnectionLayer) getLayer(CONNECTION_LAYER);
			FanRouter router = new FanRouter();
			router.setSeparation(30);
			cRouter = new BendpointConnectionRouter();
			router.setNextRouter(cRouter);
			cLayer.setConnectionRouter(router);
		}
	}

	protected List getModelChildren() {
		return ((NodeContainer) getModel()).getNodes();
	}
	
	/**
	 * Only useful in case of ADD. Placed here in order to invoke in sub classes.
	 * TODO:  Move this method to utility class.
	 */
	protected void cleanUpDiagram(EditPart part) {
		EditPart dep = (EditPart) part.getParent();
		if(dep instanceof ActivityDetailDiagramEditPart ){
			
			ActivityDetailDiagramEditPart add = ((ActivityDetailDiagramEditPart)dep);
			if(!((Diagram)add.getModel()).isReadOnly()){
				add.getRecentlyAddedParts().addAll(dep.getChildren());
		
				// clean up the diagram
				DefaultEditDomain editingDomain = new DefaultEditDomain(null);
				ActionRegistry actionRegistry = new ActionRegistry();
		
				DiagramActionService actionService = new DiagramActionService(
						(GraphicalViewer)getParent().getViewer(), editingDomain, actionRegistry);
				actionService.registerVerticalAlignFirstSelectedAction();
		
				DiagramUpdateService service = new DiagramUpdateService(
						(GraphicalViewer)getParent().getViewer(), editingDomain, actionRegistry);
				service.cleanUpDiagram();
			}
		}
	}

}