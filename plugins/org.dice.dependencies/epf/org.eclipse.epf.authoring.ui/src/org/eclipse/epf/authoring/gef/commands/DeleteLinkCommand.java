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
package org.eclipse.epf.authoring.gef.commands;

import org.eclipse.epf.authoring.gef.util.Validation;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.gef.commands.Command;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class DeleteLinkCommand extends Command {

	private Link link;

	private Node src, target;

	private int srcIndex, targetIndex;

	private static final String LABEL = AuthoringUIResources.gef_deleteLinkCommand_label; 

	public DeleteLinkCommand(Link link, boolean isHardDelete) {
		super(LABEL);
		this.link = link;
		src = link.getSource();
		target = link.getTarget();
	}

	public boolean canExecute() {
		if (Validation.checkDelete(link) != null)
			return false;
		return link != null && src != null && target != null;
	}

	/*
	 * This should work even if the link (view) was deleted. Eg., user selects a
	 * class and one of its references, and hits Ctrl+Del. The class will be
	 * deleted first and it will delete all its references. When this command is
	 * executed, it should do the hard-delete part.
	 */
	public void execute() {
		srcIndex = src.getOutgoingConnections().indexOf(link);
		targetIndex = target.getIncomingConnections().indexOf(link);
		if (srcIndex != -1 && targetIndex != -1) {
			src.getOutgoingConnections().remove(srcIndex);
			target.getIncomingConnections().remove(targetIndex);
		}
	}

	public void undo() {
		if (srcIndex != -1 && targetIndex != -1) {
			src.getOutgoingConnections().add(srcIndex, link);
			target.getIncomingConnections().add(targetIndex, link);
		}
	}

}