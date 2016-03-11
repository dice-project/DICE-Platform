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
package org.eclipse.epf.diagram.add.edit.policies;

import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.epf.diagram.add.edit.parts.RoleNodeEditPart;
import org.eclipse.epf.diagram.add.edit.parts.TaskNodeEditPart;

import org.eclipse.epf.diagram.add.part.DiagramVisualIDRegistry;

import org.eclipse.epf.diagram.model.Node;
import org.eclipse.epf.diagram.model.NodeContainer;

/**
 * @generated
 */
public class RoleTaskCompositeCanonicalEditPolicy extends CanonicalEditPolicy {

	/**
	 * @generated
	 */
	protected List getSemanticChildrenList() {
		List result = new LinkedList();
		EObject modelObject = ((View) getHost().getModel()).getElement();
		View viewObject = (View) getHost().getModel();
		EObject nextValue;
		int nodeVID;
		for (Iterator values = ((NodeContainer) modelObject).getNodes()
				.iterator(); values.hasNext();) {
			nextValue = (EObject) values.next();
			nodeVID = DiagramVisualIDRegistry.getNodeVisualID(viewObject,
					nextValue);
			switch (nodeVID) {
			case RoleNodeEditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			case TaskNodeEditPart.VISUAL_ID: {
				result.add(nextValue);
				break;
			}
			}
		}
		return result;
	}

	/**
	 * @modified
	 */
	protected boolean shouldDeleteView(View view) {
		return view.isSetElement()
				&& view.getElement() != null
				&& (view.getElement().eIsProxy() || view.getElement() instanceof Node);
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

}
