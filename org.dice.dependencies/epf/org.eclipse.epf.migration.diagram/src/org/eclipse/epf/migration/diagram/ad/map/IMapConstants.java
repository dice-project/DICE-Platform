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
import java.util.Map;

import org.eclipse.epf.migration.diagram.MigrationResources;

/**
 * 
 * @author Shashidhar Kannoori
 * @since 1.2
 *
 */
public interface IMapConstants {
	
	public static boolean debug  = true;
	
	public static final String GRAPH_NODE_FORK_NODE = ""; //MigrationResources.GRAPH_NODE_FORK; //$NON-NLS-1$

	public static final String GRAPH_NODE_INITIAL = ""; //MigrationResources.GRAPH_NODE_INITIAL; //$NON-NLS-1$

	public static final String GRAPH_NODE_FINAL = ""; // MigrationResources.GRAPH_NODE_FINAL; //$NON-NLS-1$

	public static final String GRAPH_NODE_DECISION =  MigrationResources.GRAPH_NODE_DECISION;

	public static final String GRAPH_NODE_FREE_TEXT = MigrationResources.GRAPH_NODE_FREE_TEXT;
	
	public static final String GRAPH_NODE_MERGE = MigrationResources.GRAPH_NODE_MERGE;
	
	public static final String GRAPH_NODE_JOIN = ""; //MigrationResources.GRAPH_NODE_JOIN; //$NON-NLS-1$

	public static Map graphUMLNodeMap = new HashMap();
	
}
