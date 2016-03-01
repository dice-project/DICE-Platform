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

import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @author Shashidhar Kannoori
 * @author Shilpa Toraskar
 * @author Weiping Lu
 * @since 1.2
 */
public class ActivityMapNode extends MapNode {

	public ActivityMapNode(Node node) {
		super(node);
	}

	/**
	 * Update uml node
	 */
	public void updateNode() {
		super.updateNode();
		umlNode = (StructuredActivityNode) getActivity().createOwnedNode(name,
				UMLPackage.eINSTANCE.getStructuredActivityNode());
		Object obj = (Object) node.getObject();
/*		if (obj instanceof MethodElement) {
			BridgeHelper.addEAnnotation(umlNode, ((MethodElement) obj));
		}
		if (obj instanceof Phase) {
			BridgeHelper.addEAnnotationType(umlNode, 
					BridgeHelper.UMA_PHASE);
		} else if (obj instanceof Iteration) {
			BridgeHelper.addEAnnotationType(umlNode, 
					BridgeHelper.UMA_ITERATION);
		} else {
			BridgeHelper.addEAnnotationType(umlNode, 
					BridgeHelper.UMA_ACTIVITY);
		}*/
		if (obj instanceof MethodElement) {
			BridgeHelper.associate(umlNode, ((MethodElement) obj));
		}

		if (umlNode != null) {
			graphUMLNodeMap.put(node, umlNode);
		}
	}
}
