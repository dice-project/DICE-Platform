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
package org.eclipse.epf.diagram.ad.edit.helpers;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.core.util.PackageUtil;
import org.eclipse.gmf.runtime.emf.type.core.commands.MoveElementsCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;

/**
 * @generated
 */
public class ActivityPartitionEditHelper extends UMLBaseEditHelper {
	@Override
	protected ICommand getMoveCommand(MoveRequest req) {
		// TODO Auto-generated method stub
		//		return super.getMoveCommand(req);
		MoveElementsCommand cmd = new MoveElementsCommand(req) {

			@Override
			public boolean canExecute() {
				// TODO Auto-generated method stub
				//return super.canExecute();
				EObject container = getTargetContainer();

				if (container == null || getElementsToMove() == null
						|| getElementsToMove().isEmpty()) {
					return false;
				}

				for (Iterator i = getElementsToMove().keySet().iterator(); i
						.hasNext();) {
					EObject element = (EObject) i.next();
					EReference feature = getTargetFeature(element);

					if (feature == null
							|| !container.eClass().getEAllReferences()
									.contains(feature)) {
						// If the target feature doesn't exist in the target container,
						// don't allow the move.
						return false;
					}

					// IF the element is already in the target container...
					if (container.equals(element.eContainer())
							&& feature == element.eContainmentFeature()) {
						// Don't allow the reparenting
						return false;
					}

					// IF the element is the parent of the target container...
					if (EcoreUtil.isAncestor(element, getTargetContainer())) {
						// Don't allow the reparenting
						return false;
					}

					// IF the container can not contain the element...
					//					if (!PackageUtil.canContain(getTargetContainer().eClass(),
					//							feature, element.eClass(), false)) {
					//						// Don't allow the reparenting
					//						return false;
					//					}
				}

				return true;
			}

		};
		return cmd;
	}
}