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
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.epf.authoring.gef.commands.CreateLinkCommand;
import org.eclipse.epf.authoring.gef.commands.ReconnectLinkCommand;
import org.eclipse.epf.authoring.gef.util.ConnectionAnchorLocator;
import org.eclipse.epf.authoring.gef.util.TemplateConstants;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelFactory;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.epf.diagram.model.WorkProductComposite;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.ibm.icu.util.StringTokenizer;

/**
 * WorkProductCompositeEditPart is container with {@link FlowLayout}, and does not support adding/removing child editpart.
 * @author Phong Nguyen Le
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class WorkProductCompositeEditPart extends NodeContainerEditPart
		implements NodeEditPart {

	//private static final Color BG_COLOR = new Color(Display.getCurrent(), 255,
	//		255, 205);
	private static Color BG_COLOR;

	/**
	 * @param model
	 */
	public WorkProductCompositeEditPart(WorkProductComposite model) {
		super(model);
	}

	protected IFigure createFigure() {
		Figure figure = new Figure();
		FlowLayout layout = new FlowLayout();
		layout.setMinorSpacing(20);
		figure.setLayoutManager(layout);
		figure.setBorder(new RaisedMarginBorder());
		figure.setBackgroundColor(getBackgroundColor());
		figure.setOpaque(true);
		return figure;
	}

	/**
	 * @see org.eclipse.epf.authoring.gef.edit.NodeContainerEditPart#handlePropertyChanged(Notification)
	 */
	protected void handlePropertyChanged(Notification msg) {
		switch (msg.getFeatureID(NodeContainer.class)) {
		case ModelPackage.NODE_CONTAINER__NODES:
			refreshChildren();
			
			adjustSize();
			
			// refresh diagram's children
			EditPart diagramEditPart = getParent();
			diagramEditPart.refresh();

			return;
		}
		super.handlePropertyChanged(msg);
	}

	/**
	 * 
	 */
	private void adjustSize() {
		// TODO Auto-generated method stub
		List list = getChildren();
		int tHeight = 20;
		int tWidth = 20;
		for(Iterator iterator = list.iterator(); iterator.hasNext();){
			GraphicalEditPart editpart = (GraphicalEditPart)iterator.next();
			tHeight+=editpart.getFigure().getPreferredSize().height;
			if(tWidth < editpart.getFigure().getPreferredSize().width){
				tWidth = editpart.getFigure().getPreferredSize().width;
			}
		}
		((Node) this.getModel()).setHeight(tHeight);
		((Node) this.getModel()).setWidth(tWidth+20);
	}

	protected void createEditPolicies() {
		super.createEditPolicies();
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
		installEditPolicy(EditPolicy.LAYOUT_ROLE, null);
		installEditPolicy(EditPolicy.COMPONENT_ROLE, null);
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

	protected List getModelSourceConnections() {
		return getNode().getOutgoingConnections();
	}

	protected List getModelTargetConnections() {
		return getNode().getIncomingConnections();
	}

	protected Node getNode() {
		return (Node) getModel();
	}
	
	protected Color getBackgroundColor(){
		if(BG_COLOR != null) return BG_COLOR;
		
		if(BG_COLOR == null){
			String colorString = AuthoringUIPlugin.getDefault().getPreferenceStore().getString(
					TemplateConstants.ADD_WP_BOX_BG_COLOR_RGB);
			if(colorString != null && colorString.length() > 0){
				StringTokenizer tokenizer = new StringTokenizer(colorString, ","); //$NON-NLS-1$
				int r = Integer.parseInt(tokenizer.nextToken());
				int g = Integer.parseInt(tokenizer.nextToken());
				int b = Integer.parseInt(tokenizer.nextToken());
				BG_COLOR = new Color(null, r,g,b);
				return BG_COLOR;
			}
		}
		if(BG_COLOR == null){
			BG_COLOR = new Color(Display.getCurrent(), 255,
							255, 205);
		}
		return BG_COLOR;
	}
}
