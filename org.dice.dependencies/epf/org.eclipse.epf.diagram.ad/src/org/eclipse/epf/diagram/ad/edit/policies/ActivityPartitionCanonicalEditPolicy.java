/*
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial implementation
 *
 */
package org.eclipse.epf.diagram.ad.edit.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.ad.edit.parts.ActivityEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.ActivityNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @generated
 */
public class ActivityPartitionCanonicalEditPolicy extends CanonicalEditPolicy {

	/**
	 * @generated
	 */
	protected List getSemanticChildrenList() {
		List result = new LinkedList();
		return result;
	}

	/**
	 * @modified
	 */
	protected boolean shouldDeleteView(View view) {
		// Delete the view if activity partition is not part of the activitynode.
		if(view.getElement() instanceof ActivityNode){
			if(!((ActivityNode)view.getElement()).getInPartitions().contains(((View)getHost().getModel()).getElement()))
				return true;
		}
		return view.isSetElement() && view.getElement() != null
				&& view.getElement().eIsProxy();
	}

	/**
	 * @generated
	 */
	protected String getDefaultFactoryHint() {
		return null;
	}

	/**
	 * Override to avoid the duplicate links generation.
	 * Set parent canonicalEditPolicy to false.
	 */
	@Override
	public void enableRefresh(boolean enable) {
		EditPart part  = getHost().getParent();
		if(part instanceof ActivityEditPart){
			Object view = part.getModel();
			EObject semanticHost = ((View)view).getElement();
			if (semanticHost != null) {
				List ceps = CanonicalEditPolicy.getRegisteredEditPolicies(semanticHost);
				for ( int i = 0; i < ceps.size(); i++ ) {
					CanonicalEditPolicy cep = (CanonicalEditPolicy)ceps.get(i);
					cep.enableRefresh(enable); 
				}
			}
		}
		super.enableRefresh(enable);
	}
}
