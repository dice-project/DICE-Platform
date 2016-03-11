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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epf.diagram.ad.providers.UMLElementTypes;
import org.eclipse.epf.diagram.core.bridge.BridgeHelper;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * @author Shashidhar Kannoori
 *
 */
public class CreateActivityNodeInActivityPartition extends CreateElementCommand {

	

	/**
	 * @param request
	 */
	public CreateActivityNodeInActivityPartition(CreateElementRequest request) {
		super(request);
	}
	
	/**
	 * 
	 */
	protected EClass getEClassToEdit() {
		return UMLPackage.eINSTANCE.getActivity();
	};

	/**
	 * @modified
	 */
	protected EObject getElementToEdit() {
		EObject container = ((CreateElementRequest) getRequest())
				.getContainer();
		if (container instanceof View) {
			container = ((View) container).getElement();
		}
		if(container instanceof ActivityPartition){
			container = container.eContainer();
		}
		return container;
	}
	public boolean canExecute() {
		return super.canExecute();
	}
	
	/**
	 * (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecute(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 * @modified
	 */
	protected IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		IStatus status = null;
		status = super.doExecute(monitor, info);
		EObject newElement = getNewElement();
		
		if (newElement instanceof ActivityNode) 
		{
			EObject obj = getOriginalContainer();
			if (obj instanceof ActivityPartition) {
				// set inpartition 
				((ActivityNode) newElement).getInPartitions().add((ActivityPartition) obj);
			}
		}
		return status;
	}
	
	

	/**
	 * @custom
	 */
	private EObject getOriginalContainer()
	{
		return ((CreateElementRequest) getRequest()).getContainer();
	}
	
	/**
	 * Gets the containment feature for the new element.
	 * 
	 * @return the containment feature
	 * @modified
	 */
	protected EReference getContainmentFeature() {

		/**
		 * The containment feature in which the new element will be created.
		 */
		EReference containmentFeature=null;
		if (containmentFeature == null) {
			EClass classToEdit = getEClassToEdit();

			if (classToEdit != null) {
				IElementType type = getElementType();

				if (type != null && type.getEClass() != null) {
					containmentFeature = PackageUtil.findFeature(classToEdit,
							type.getEClass());
				}
			}
		}

		return containmentFeature;
	}
	
	@Override
	protected EObject doDefaultElementCreation() {
		// TODO Auto-generated method stub
		EObject newElement = super.doDefaultElementCreation();
		if(newElement instanceof StructuredActivityNode
				|| newElement instanceof ActivityParameterNode){
			if (newElement != null) {
				BridgeHelper.setDefaultName((ActivityNode)newElement);
			}
		}
		CreateElementRequest request = getCreateRequest();
		
		if(UMLElementTypes.StructuredActivityNode_1007 == request.getElementType()){
			if (newElement != null) {
				UMLElementTypes.Initializers.StructuredActivityNode_1007
						.init(newElement);
			}
		}else if(UMLElementTypes.StructuredActivityNode_1010 == request
						.getElementType()){
			if (newElement != null) {
				UMLElementTypes.Initializers.StructuredActivityNode_1010
						.init(newElement);
			}
		}else if(UMLElementTypes.StructuredActivityNode_1011 == request
						.getElementType()){
			if (newElement != null) {
				UMLElementTypes.Initializers.StructuredActivityNode_1011
						.init(newElement);
			}
		}else if(UMLElementTypes.ActivityParameterNode_1009 == request
						.getElementType()){
			if (newElement != null) {
				UMLElementTypes.Initializers.ActivityParameterNode_1009
						.init(newElement);
			}
		}else if(UMLElementTypes.ActivityParameterNode_1012 == request
						.getElementType()){
			if (newElement != null) {
				UMLElementTypes.Initializers.ActivityParameterNode_1012
						.init(newElement);
			}
		}
		
		return newElement;
	}
}
