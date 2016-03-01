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
package org.eclipse.epf.library.edit.process;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.AdapterFactory;

/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class WorkOrderItemProvider extends
		org.eclipse.epf.uma.provider.WorkOrderItemProvider {

	/**
	 * @param adapterFactory
	 */
	public WorkOrderItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	// /* (non-Javadoc)
	// * @see
	// org.eclipse.emf.edit.provider.ItemProviderAdapter#createInitializeCopyCommand(org.eclipse.emf.edit.domain.EditingDomain,
	// org.eclipse.emf.ecore.EObject,
	// org.eclipse.emf.edit.command.CopyCommand.Helper)
	// */
	// protected Command createInitializeCopyCommand(EditingDomain domain,
	// EObject owner, Helper helper) {
	// return new MethodElementInitializeCopyCommand(domain, owner, helper);
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.epf.uma.provider.DescribableElementItemProvider#getChildrenFeatures(java.lang.Object)
	 */
	public Collection getChildrenFeatures(Object object) {
		return Collections.EMPTY_LIST;
	}

}
