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
package org.eclipse.epf.authoring.gef.edit.policies;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.epf.authoring.gef.commands.CreateBendpointCommand;
import org.eclipse.epf.authoring.gef.commands.DeleteBendpointCommand;
import org.eclipse.epf.authoring.gef.commands.MoveBendpointCommand;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;

/**
 * A EditPolicy to handle the create, move and delete bendpoints activities of Link.
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class LinkBendpointEditPolicy extends BendpointEditPolicy {

	protected Command getCreateBendpointCommand(BendpointRequest request) {
		Point loc = request.getLocation();
		getConnection().translateToRelative(loc);
		return new CreateBendpointCommand(
				(Link) request.getSource().getModel(), loc, request.getIndex());
	}

	protected Command getDeleteBendpointCommand(BendpointRequest request) {
		return new DeleteBendpointCommand((Link) getHost().getModel(), request
				.getIndex());
	}

	protected Command getMoveBendpointCommand(BendpointRequest request) {
		Point loc = request.getLocation();
		getConnection().translateToRelative(loc);
		return new MoveBendpointCommand((Link) request.getSource().getModel(),
				loc, request.getIndex());
	}

}