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
package org.eclipse.epf.diagram.ui.service;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.WorkBreakdownElement;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * Class for mapping between Activity Diagram
 * 
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * @author Chinh Vo
 * @since 1.2
 */
public interface IDiagramUIService {

	public static final String ACTIVITY_NODE = "Activity";	//$NON-NLS-1$

	public static final String PHASE_NODE = "Phase";		//$NON-NLS-1$

	public static final String ITERATION_NODE = "Iteration";	//$NON-NLS-1$

	public static final String TASK_DESCRIPTOR_NODE = "TaskDescriptor"; //$NON-NLS-1$

	public static final String MILESTONE_NODE = "Milestone";	//$NON-NLS-1$

	public static final String INITIAL_NODE = "InitialNode";	//$NON-NLS-1$

	public static final String FINAL_NODE = "FinalNode";		//$NON-NLS-1$

	public static final String FORK_NODE = "ForkNode";			//$NON-NLS-1$

	public static final String JOIN_NODE = "JoinNode";			//$NON-NLS-1$

	public static final String MERGE_NODE = "MergeNode";		//$NON-NLS-1$

	public static final String DECISION_NODE = "DecisionNode";	//$NON-NLS-1$

	
	/**
	 * Returns all diagram nodes in the diagram
	 * @param diagram
	 * @return
	 */
	public EList getAllNodes(Diagram diagram);

	/**
	 * Return diagram node for given node type under given activity and diagram
	 * @param diagram
	 * @param activity
	 * @param nodeType
	 * 			e.g. IDiagramUIService.ACTIVITY_NODE, etc
	 * @return
	 */
	public List<Node> getNodes(Diagram diagram, Activity activity,
			String nodeType);

	/**
	 * To create a link
	 * 
	 * @param diagram
	 * @param source
	 * @param target
	 * @param linkName
	 * @return
	 */
	public Edge createEdge(Diagram diagram, View source, View target, String linkName);

	/**
	 * Creates diagam node for the given node type
	 * 
	 * @param diagram
	 * @param nodeType
	 * @param nodeName
	 * @return
	 */
	public Node createNode(Diagram diagram, String nodeType, String nodeName);

	/**
	 * Create diagram node for the given work breakdown element
	 * @param diagram
	 * @param wbeElement
	 * @return
	 */
	public Node createNode(Diagram diagram, WorkBreakdownElement wbeElement);

	/**
	 * Returns all edges in the diagram
	 * @param diagram
	 * @return
	 */
	public EList getEdges(Diagram diagram);

	/**
	 * Creates an activity diagram for the given activity.
	 * 
	 * @param activity
	 * @return
	 */
	public Diagram createActivityDiagram(Activity activity);

}
