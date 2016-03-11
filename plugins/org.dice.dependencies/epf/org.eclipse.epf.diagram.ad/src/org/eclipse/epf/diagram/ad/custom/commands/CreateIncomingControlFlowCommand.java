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
/**
 * 
 */
package org.eclipse.epf.diagram.ad.custom.commands;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateRelationshipCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @author Shashidhar Kannoori
 *
 */
public class CreateIncomingControlFlowCommand extends CreateRelationshipCommand {

	/**
	 * 
	 * @param request
	 * @param element
	 */
	public CreateIncomingControlFlowCommand(CreateRelationshipRequest request,
			EObject element) {
		super(request);
		setElementToEdit(element);
	}
	/**
	 * @param request
	 */
	public CreateIncomingControlFlowCommand(CreateRelationshipRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	protected EClass getEClassToEdit() {
		return UMLPackage.eINSTANCE.getActivity();
	}
	
	public boolean canExecute() {
//		if(getSource() == null)
//			return false;
//		
//		if (getEClassToEdit() == null) {
//			return false;
//		}
//
//		if (getContainmentFeature() != null) {
//			EClassifier eClassifier = getContainmentFeature().getEType();
//			boolean result = true;
//
//			if (eClassifier instanceof EClass) {
//				result = ((EClass) eClassifier).isSuperTypeOf(getElementType()
//						.getEClass());
//			}
//
//			result = result
//					&& PackageUtil.canReference(getEClassToEdit(),
//							getContainmentFeature(), getElementType()
//									.getEClass());
//
//			return result;
//		}
//		return false;
		return super.canExecute();
	}
	protected EObject doDefaultElementCreation() {
		ControlFlow newElement = (ControlFlow) super.doDefaultElementCreation();
		if (newElement != null) {
			newElement.setTarget((ActivityNode) getTarget());
			newElement.setSource((ActivityNode) getSource());
		}
		return newElement;
	}
	
}
