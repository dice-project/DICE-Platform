//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.edit.process.command;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.epf.library.edit.command.IResourceAwareCommand;
import org.eclipse.epf.library.edit.command.MethodElementAddCommand;
import org.eclipse.epf.library.edit.validation.IValidator;
import org.eclipse.epf.library.edit.validation.IValidatorFactory;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.epf.uma.UmaPackage;


/**
 * @author Phong Nguyen Le - Sep 16, 2005
 * @since 1.0
 */
public class ProcessElementAddCommand extends MethodElementAddCommand 
implements IResourceAwareCommand
{

	public ProcessElementAddCommand(Command command) {
		// disable run with progress bar when adding in process editor
		//
		super(command, false);
	}

	public ProcessElementAddCommand(Command command, boolean runWithProgress) {
		super(command, runWithProgress);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.MethodElementAddCommand#getFeaturesToCheck()
	 */
	protected Collection getFeaturesToCheck() {
		if (featuresToCheck == null) {
			super.getFeaturesToCheck().add(
					UmaPackage.eINSTANCE
							.getMethodElement_PresentationName());
		}
		return featuresToCheck;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.MethodElementAddCommand#willSaveModifiedResources()
	 */
	protected boolean willSaveModifiedResources() {
		// don't save the resources after each add
		//
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.library.edit.command.MethodElementAddCommand#saveModifiedResources()
	 */
	protected void saveModifiedResources() {
		// don't save the resources after each add
	}

	/* (non-Javadoc)
	 * @see org.eclipse.epf.library.edit.command.MethodElementAddCommand#createValidator(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	protected IValidator createValidator(EObject e, EStructuralFeature feature) {
		EditingDomain domain = addCommand.getDomain();
		if (e instanceof BreakdownElement
				&& addCommand.getFeature() instanceof EReference
				&& domain instanceof AdapterFactoryEditingDomain) {
			return IValidatorFactory.INSTANCE.createValidator(addCommand.getOwner(), null, e,
					feature, ((AdapterFactoryEditingDomain) domain).getAdapterFactory());
		}
		return super.createValidator(e, feature);
	}
}
