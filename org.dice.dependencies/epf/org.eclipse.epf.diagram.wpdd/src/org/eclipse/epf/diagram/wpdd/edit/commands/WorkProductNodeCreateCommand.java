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
package org.eclipse.epf.diagram.wpdd.edit.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.epf.diagram.model.Diagram;
import org.eclipse.epf.diagram.model.ModelPackage;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class WorkProductNodeCreateCommand extends CreateElementCommand {

	/**
	 * @generated
	 */
	public WorkProductNodeCreateCommand(CreateElementRequest req) {
		super(req);
	}

	/**
	 * @generated
	 */
	protected EClass getEClassToEdit() {
		return ModelPackage.eINSTANCE.getNodeContainer();
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

	
	@Override
	protected EObject doDefaultElementCreation() {
		NamedNode newNode = (NamedNode)super.doDefaultElementCreation();
		Diagram diagram =newNode.getDiagram();
		diagram.setDefaultName(newNode);
		return newNode;
	}
}
