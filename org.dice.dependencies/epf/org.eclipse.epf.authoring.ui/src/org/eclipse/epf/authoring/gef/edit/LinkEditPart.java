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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.epf.authoring.gef.commands.DeleteCommand;
import org.eclipse.epf.authoring.gef.commands.DeleteLinkCommand;
import org.eclipse.epf.authoring.gef.edit.policies.LinkBendpointEditPolicy;
import org.eclipse.epf.authoring.gef.edit.policies.LinkEndpointEditPolicy;
import org.eclipse.epf.authoring.gef.figures.Colors;
import org.eclipse.epf.authoring.gef.util.Validation;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

/**
 * Provides support to update the appearance, and installs the editpolicies
 * specific the {@link Link} and listens to changes in the diagram model.
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class LinkEditPart extends AbstractConnectionEditPart {

	protected Adapter modelListener = new AdapterImpl() {
		public void notifyChanged(Notification msg) {
			handlePropertyChanged(msg);
		}
	};

	public LinkEditPart(Link conn) {
		super();
		setModel(conn);
	}

	public void activate() {
		super.activate();
		getLink().eAdapters().add(modelListener);
	}

	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new LinkEndpointEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new ConnectionEditPolicy() {
					protected Command getDeleteCommand(GroupRequest request) {
						Boolean bool = (Boolean) request.getExtendedData().get(
								DeleteCommand.KEY_PERM_DELETE);
						boolean permDelete = bool == null ? false : bool
								.booleanValue();
						return new DeleteLinkCommand((Link) getHost()
								.getModel(), permDelete);
					}
				});
		installEditPolicy(EditPolicy.CONNECTION_BENDPOINTS_ROLE,
				new LinkBendpointEditPolicy());
	}

	protected IFigure createFigure() {
		PolylineConnection conn = new PolylineConnection();
		conn.setTargetDecoration(new PolygonDecoration());
		updateAppearance();
		return conn;
	}

	private void updateAppearance() {
		if (figure == null)
			return;
		Link link = (Link) getModel();
		if (Validation.isReadOnly(link)) {
			figure.setForegroundColor(Colors.INHERITED_ELEMENT_LABEL);
		} else {
			figure.setForegroundColor(ColorConstants.black);
		}
	}

	public void deactivate() {
		getLink().eAdapters().remove(modelListener);
		super.deactivate();
	}

	protected Link getLink() {
		return (Link) getModel();
	}

	protected void handlePropertyChanged(Notification msg) {
		switch (msg.getFeatureID(Link.class)) {
		case ModelPackage.LINK__BENDPOINTS:
			refreshVisuals();
			break;
		case ModelPackage.LINK__TARGET_END_POINT:
			refreshTargetAnchor();
			break;
		case ModelPackage.LINK__SOURCE_END_POINT:
			refreshSourceAnchor();
			break;
		}
	}

	protected void refreshVisuals() {
		updateAppearance();
		getConnectionFigure().setRoutingConstraint(getLink().getBendpoints());
	}

}