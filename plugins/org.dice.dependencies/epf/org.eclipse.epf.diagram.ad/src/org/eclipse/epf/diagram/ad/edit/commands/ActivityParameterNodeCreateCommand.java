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
package org.eclipse.epf.diagram.ad.edit.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.epf.diagram.ad.providers.UMLElementTypes;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class ActivityParameterNodeCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	public ActivityParameterNodeCreateCommand(CreateElementRequest req) {
		super(req);
	}

	/**
	 * @generated
	 */
	protected EClass getEClassToEdit() {
		return UMLPackage.eINSTANCE.getActivity();
	}

	/**
	 * @generated
	 */
	protected EObject getElementToEdit() {
		EObject container = ((CreateElementRequest) getRequest())
				.getContainer();
		if (container instanceof View) {
			container = ((View) container).getElement();
		}
		return container;
	}

	/**
	 * @modified
	 */
	protected EObject doDefaultElementCreation() {
		ActivityParameterNode newElement = (ActivityParameterNode) super
				.doDefaultElementCreation();
		if (newElement != null
				&& UMLElementTypes.ActivityParameterNode_1009 == ((CreateElementRequest) getRequest())
						.getElementType()) {
			UMLElementTypes.Initializers.ActivityParameterNode_1009
					.init(newElement);
		}
		if (newElement != null
				&& UMLElementTypes.ActivityParameterNode_1012 == ((CreateElementRequest) getRequest())
						.getElementType()) {
			UMLElementTypes.Initializers.ActivityParameterNode_1012
					.init(newElement);
		}
		return newElement;
	}

}
