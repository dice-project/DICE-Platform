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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.uma.CompositeRole;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class CompositeRoleWrapperItemProvider extends
		RoleDescriptorWrapperItemProvider {

	/**
	 * @param role
	 * @param owner
	 * @param feature
	 * @param index
	 * @param adapterFactory
	 */
	public CompositeRoleWrapperItemProvider(CompositeRole role, Object owner,
			EStructuralFeature feature, int index, AdapterFactory adapterFactory) {
		super(role, owner, feature, index, adapterFactory);
	}

}
