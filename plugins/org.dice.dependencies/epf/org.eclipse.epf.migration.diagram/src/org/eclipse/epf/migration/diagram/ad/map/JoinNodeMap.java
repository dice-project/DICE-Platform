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

import org.eclipse.epf.diagram.model.Node;
import org.eclipse.uml2.uml.JoinNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * 
 * @author Shilpa Toraskar
 * @since 1.2
 *
 */
public class JoinNodeMap extends MapNode {

	public JoinNodeMap(Node node) {
		super(node);
	}
	
	/**
	 * Update uml node
	 */
	public void updateNode() {
		super.updateNode();
		umlNode = (JoinNode)getActivity().createOwnedNode(name = (name != null)? name : GRAPH_NODE_JOIN, 
						UMLPackage.eINSTANCE.getJoinNode());
		
		if(umlNode != null){
			graphUMLNodeMap.put(node, umlNode);
		}
	}
}
