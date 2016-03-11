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
package org.eclipse.epf.diagram.ad.custom.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableShapeEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;

/**
 * XYLayoutPolicy for ActivityPartition to handle the move of children.
 * @author Shashidhar Kannoori
 * @written
 */
public class ActivityPartitionLayoutEditPolicy extends XYLayoutEditPolicy {

	protected EditPolicy createChildEditPolicy(EditPart child) {
		EditPolicy result = super.createChildEditPolicy(child);
		if (result == null) {
			return new ResizableShapeEditPolicy();
		}
		return result;
	}
	protected Command getMoveChildrenCommand(Request request) {
        return super.getMoveChildrenCommand(request);
	}
}
