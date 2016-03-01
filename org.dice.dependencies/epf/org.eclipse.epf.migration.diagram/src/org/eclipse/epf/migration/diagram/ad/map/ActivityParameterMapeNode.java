/*******************************************************************************
 * Copyright (c) 2005, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *******************************************************************************/
package org.eclipse.epf.migration.diagram.ad.map;

import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.TaskDescriptor;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @author Shilpa Toraskar
 * @since 1.2
 */
public class ActivityParameterMapeNode extends MapNode {

	public ActivityParameterMapeNode(Node node) {
		super(node);
	}

	/**
	 * Update uml node
	 */
	public void updateNode() {
		super.updateNode();
		umlNode = (ActivityParameterNode) getActivity().createOwnedNode(name,
				UMLPackage.eINSTANCE.getActivityParameterNode());
		Object obj = (Object) node.getObject();
		if (obj instanceof MethodElement) {
			BridgeHelper.addEAnnotation(umlNode, ((MethodElement) obj));
		}
		if (obj instanceof Milestone) {
			BridgeHelper.addEAnnotationType(umlNode, 
					BridgeHelper.UMA_MILESTONE);
		} else if (obj instanceof TaskDescriptor) {
			BridgeHelper.addEAnnotationType(umlNode, 
					BridgeHelper.UMA_TASK_DESCRIPTOR);
		}

		if (umlNode != null) {
			graphUMLNodeMap.put(node, umlNode);
		}
	}
}
