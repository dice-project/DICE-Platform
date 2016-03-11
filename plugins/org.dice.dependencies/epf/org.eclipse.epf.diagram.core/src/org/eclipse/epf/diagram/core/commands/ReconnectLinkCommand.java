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
package org.eclipse.epf.diagram.core.commands;

import java.util.Collections;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.epf.diagram.core.DiagramCorePlugin;
import org.eclipse.epf.diagram.core.util.DiagramCoreValidation;
import org.eclipse.epf.diagram.model.util.TxUtil;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;

/**
 * @author Shashidhar Kannoori
 * @since 1.2
 */
public class ReconnectLinkCommand extends Command {

	private boolean isSource;

	private Edge link;

	private Node newNode, oldNode;

	private int viewIndex;

	private Point endPoint;

	private InternalTransactionalEditingDomain domain;

	private static final String LABEL = "Reconnect Link"; //$NON-NLS-1$

	public ReconnectLinkCommand(InternalTransactionalEditingDomain domain, Edge link, Node newNode, boolean isSource) {
		super(LABEL);
		this.domain = domain;
		this.link = link;
		this.newNode = newNode;
		this.isSource = isSource;
	}

	public void setEndPoint(Point p) {
		endPoint = p.getCopy();
	}

	public boolean canExecute() {
		boolean result = link != null && newNode != null;
		if (!result) {
			return false;
		}
		if (isSource) {
			return DiagramCoreValidation.checkReconnect(newNode, (Node)link.getTarget(), link) == null;
		} else {
			return DiagramCoreValidation.checkReconnect((Node)link.getSource(), newNode, link) == null;
		}
	}

	public void execute() {

		// Link
		try {
			TxUtil.runInTransaction(domain, new Runnable() {

				public void run() {
					if (isSource) {
						oldNode = (Node) link.getSource();
						if (oldNode != null) { // safety check to handle dangling link
							viewIndex = oldNode.getTargetEdges().indexOf(link);
						}
						link.setSource(newNode);
					} else {
						oldNode = (Node) link.getTarget();
						if (oldNode != null) { // safety check to handle dangling link
							viewIndex = oldNode.getSourceEdges().indexOf(link);
						}
						link.setTarget(newNode);
					}
				}
				
			});
		} catch (Exception e) {
			DiagramCorePlugin.getDefault().getLogger().logError("Error while re-connect the link :", e); //$NON-NLS-1$
		}
	}

	public void undo() {
		if (isSource) {
			newNode.getTargetEdges().remove(link);
			oldNode.getTargetEdges().add(viewIndex, link);
		} else {
			newNode.getSourceEdges().remove(link);
			oldNode.getSourceEdges().add(viewIndex, link);
		}
		oldNode = null;
	}

}
