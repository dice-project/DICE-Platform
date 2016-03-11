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
package org.eclipse.epf.library.edit.configuration;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.uma.UmaPackage;

/**
 * The item provider adapter for a domain in the Configuration view.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class DomainItemProvider extends
		org.eclipse.epf.library.edit.category.DomainItemProvider {

	/**
	 * Creates a new instance.
	 */
	public DomainItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	public Collection getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			childrenFeatures = new ArrayList();
			childrenFeatures.add(UmaPackage.eINSTANCE.getDomain_Subdomains());
			childrenFeatures.add(UmaPackage.eINSTANCE.getDomain_WorkProducts());
		}
		return childrenFeatures;
	}
}
