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
package org.eclipse.epf.migration.diagram.ad.map;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.TypedNode;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.TaskDescriptor;

/**
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * @since 1.2
 */


public class MapFactory {
	Map map = new HashMap();

	static MapFactory factory = null;

	
	public static MapFactory getInstance() {
		if (factory == null) {
			factory = new MapFactory();
		}
		return factory;
	}

	/**
	 * Crete map node
	 * @param node
	 * @return
	 */
	public MapNode create(Node node) {
		int classifier = node.eClass().getClassifierID();
		switch (classifier) {
		case ModelPackage.WORK_BREAKDOWN_ELEMENT_NODE:
			return createWorkBreakDownElementNode(node);
		case ModelPackage.TYPED_NODE:
			TypedNode typedNode = (TypedNode) node;
			if (typedNode.getType() == TypedNode.DECISION) {
				List incomingConnections = typedNode.getIncomingConnections();
				
				if (incomingConnections.size() > 1)
					return createMergeNode(node);
				else
					return createDecisionNode(node);
				
//				List outgoingConnections = typedNode.getOutgoingConnections();
//				if (incomingConnections.size() > 1 && outgoingConnections.size() == 1)
//					return createMergeNode(node);
//				if (incomingConnections.size() == 1 && outgoingConnections.size() > 1)
//					return createDecisionNode(node);
//				if (incomingConnections.size() > 1
//						&& outgoingConnections.size() > 1) {
//					createMergeNode(node);
//					return createDecisionNode(node);
//				}
			} else if (typedNode.getType() == TypedNode.START)
				return createInitialNode(node);
			else if (typedNode.getType() == TypedNode.END)
				return createFinalNode(node);
			else if (typedNode.getType() == TypedNode.SYNCH_BAR) {
				List incomingConnections = typedNode.getIncomingConnections();
				if (incomingConnections.size() > 1)
					return createJoinNode(node);
				else
					return createForkNode(node);
//				List outgoingConnections = typedNode.getOutgoingConnections();
//				if (incomingConnections.size() > 1 && outgoingConnections.size() == 1)
//					return createJoinNode(node);
//				if (incomingConnections.size() == 1 && outgoingConnections.size() > 1)
//					return createForkNode(node);
//				if (incomingConnections.size() > 1
//						&& outgoingConnections.size() > 1) {
//					createJoinNode(node);
//					return createForkNode(node);
//				}
			} else if (typedNode.getType() == TypedNode.FREE_TEXT) {
				return createFreeTextNode(node);
			}
		case ModelPackage.LINK:
			return createControlFlow(node);
		default:
			throw new IllegalArgumentException("The class '" //$NON-NLS-1$
					+ node.eClass().getName() + "' is not a valid classifier"); //$NON-NLS-1$
		}
	}

	private MapNode createWorkBreakDownElementNode(Node node) {
		Object obj = node.getObject();
		if (obj instanceof Activity) 
			return new ActivityMapNode(node);
		else if (obj instanceof TaskDescriptor || obj instanceof Milestone) 
			return new ActivityParameterMapeNode(node);
		
		return null;
	}

	private MapNode createFreeTextNode(Node node) {
		return null;
	}

	private MapNode createFinalNode(Node node) {
		return new FinalNodeMap(node);
	}

	private MapNode createInitialNode(Node node) {
		return new InitialNodeMap(node);
	}

	private MapNode createControlFlow(Node node) {
		return null;
	}

	private MapNode createMergeNode(Node node) {
		return new MergeNodeMap(node);
	}

	private MapNode createJoinNode(Node node) {
		return new JoinNodeMap(node);
	}

	private MapNode createDecisionNode(Node node) {
		return new DecisionNodeMap(node);
	}

	private MapNode createForkNode(Node node) {
		return new ForkNodeMap(node);
	}
}
