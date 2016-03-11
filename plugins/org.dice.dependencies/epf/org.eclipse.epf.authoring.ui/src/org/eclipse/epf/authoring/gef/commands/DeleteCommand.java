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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.epf.authoring.gef.util.Validation;
import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.diagram.model.Link;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.NodeContainer;
import org.eclipse.gef.commands.Command;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class DeleteCommand extends Command {

	private Command delegate;

	private static final String LABEL = AuthoringUIResources.gef_deleteCommand_label; 

	public static final String KEY_PERM_DELETE = "gef.ediagram.$perm"; //$NON-NLS-1$

	public DeleteCommand(boolean hardDelete) {
		super(LABEL);
	}

	public boolean canExecute() {
		return delegate != null && delegate.canExecute();
	}

	public void execute() {
		delegate.execute();
	}

	public Command setPartToBeDeleted(Object model) {
		if (model instanceof Node)
			delegate = new DeleteNodeCommand(model);
		else
			delegate = null;
		return this;
	}

	public void undo() {
		delegate.undo();
	}

	private static class DeleteNodeCommand extends Command {
		protected Map incomingConnectionsToSource;

		protected Map outgoingConnectionsToTarget;

		protected NodeContainer container;

		protected Node node;

		protected int index;

		public DeleteNodeCommand(Object node) {
			this.node = (Node) node;
			container = (NodeContainer) this.node.eContainer();
		}

		public void execute() {
			index = container.getNodes().indexOf(node);
			// There's always the possibility that this node has already been
			// deleted by the time this command is executed (eg., when the
			// package containing this class is also selected). Hence, we check
			// to see if the node exists in the diagram. If not, do nothing.
			if (index == -1)
				return;
			incomingConnectionsToSource = new HashMap();
			outgoingConnectionsToTarget = new HashMap();
			for (Iterator iter = node.getOutgoingConnections().iterator(); iter
					.hasNext();) {
				Link link = (Link) iter.next();
				outgoingConnectionsToTarget.put(link, link.getTarget());
				link.setTarget(null);
			}
			for (Iterator iter = node.getIncomingConnections().iterator(); iter
					.hasNext();) {
				Link link = (Link) iter.next();
				incomingConnectionsToSource.put(link, link.getSource());
				link.setSource(null);
			}
			// Commenting below two lines, Links get removed from the model
			// when link.setTarget(null) or link.setSource(null) is done.  For 
			// Node if we clear incoming/outgoing connections, Undo command will not work.
			
			//node.getOutgoingConnections().clear();
			//node.getIncomingConnections().clear();
			container.getNodes().remove(index);
		}

		public void undo() {
			if (index == -1)
				return;
			container.getNodes().add(index, node);
			for (Iterator iter = outgoingConnectionsToTarget.keySet()
					.iterator(); iter.hasNext();) {
				Link link = (Link) iter.next();
				link.setTarget((Node) outgoingConnectionsToTarget.get(link));
				link.setSource(node);
			}
			for (Iterator iter = incomingConnectionsToSource.keySet()
					.iterator(); iter.hasNext();) {
				Link link = (Link) iter.next();
				link.setTarget(node);
				link.setSource((Node) incomingConnectionsToSource.get(link));
			}
			outgoingConnectionsToTarget = null;
			incomingConnectionsToSource = null;
		}

		/**
		 * @see org.eclipse.gef.commands.Command#canExecute()
		 */
		public boolean canExecute() {
			if (Validation.checkDelete(node) != null)
				return false;
			return super.canExecute();
		}
	}
	/*
	 * Not in use. Need modification for future feature implementations. 
	 * Compound Command deletion do the recursive deletion and undo. 
	 */
//	private static class DeleteNodeCompoundCommand extends CompoundCommand {
//		protected Map incomingConnectionsToSource;
//
//		protected Map outgoingConnectionsToTarget;
//
//		protected NodeContainer container;
//
//		protected Node node;
//
//		protected int index;
//
//		public DeleteNodeCompoundCommand(Object node) {
//			this.node = (Node) node;
//			container = (NodeContainer) this.node.eContainer();
//			incomingConnectionsToSource = new HashMap();
//			outgoingConnectionsToTarget = new HashMap();
//			for (Iterator iter = this.node.getOutgoingConnections().iterator(); iter
//					.hasNext();) {
//				Link link = (Link) iter.next();
//				outgoingConnectionsToTarget.put(link, link.getTarget());
//				add(new DeleteLinkCommand( link, false));
//			}
//			for (Iterator iter = this.node.getIncomingConnections().iterator(); iter
//					.hasNext();) {
//				Link link = (Link) iter.next();
//				incomingConnectionsToSource.put(link, link.getSource());
//				add(new DeleteLinkCommand( link, false));
//			}
//		}
//
//		public void execute() {
//			index = container.getNodes().indexOf(node);
//			// There's always the possibility that this node has already been
//			// deleted by the time this command is executed (eg., when the
//			// package containing this class is also selected). Hence, we check
//			// to see if the node exists in the diagram. If not, do nothing.
//			if (index == -1)
//				return;
//
//			super.execute();
//			container.getNodes().remove(index);
//			
//		}
//
//		public void undo() {
//			if (index == -1)
//				return;
//			container.getNodes().add(index, node);
//			super.undo();
//		}
//
//		/**
//		 * @see org.eclipse.gef.commands.Command#canExecute()
//		 */
//		public boolean canExecute() {
//			if (Validation.checkDelete(node) != null)
//				return false;
//			return super.canExecute();
//		}
//	}

}