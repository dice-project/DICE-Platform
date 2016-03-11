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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.epf.diagram.ad.edit.commands.ActivityPartitionCreateCommand;
import org.eclipse.epf.diagram.ad.providers.UMLElementTypes;

import org.eclipse.gef.commands.Command;

import org.eclipse.gmf.runtime.emf.type.core.commands.CreateElementCommand;

import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.uml2.uml.UMLPackage;

/**
 * @generated
 */
public class ActivityPartitionPartitionCampartment2ItemSemanticEditPolicy
		extends UMLBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (UMLElementTypes.ActivityPartition_2001 == req.getElementType()) {
			if (req.getContainmentFeature() == null) {
				req.setContainmentFeature(UMLPackage.eINSTANCE
						.getActivityPartition_Subpartition());
			}
			return getMSLWrapper(new ActivityPartitionCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
