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
package org.eclipse.epf.library.edit.util;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;

/**
 * A ConfigurableComposedAdapterFactory that implements IEditingDomainProvider
 * 
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class EditingDomainComposedAdapterFactory extends
		ConfigurableComposedAdapterFactory implements IEditingDomainProvider {

	private EditingDomain editingDomain;

	public EditingDomainComposedAdapterFactory(AdapterFactory[] adapterFactories) {
		super(adapterFactories);
	}

	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	public void setEditingDomain(EditingDomain editingDomain) {
		this.editingDomain = editingDomain;
		if (editingDomain instanceof AdapterFactoryEditingDomain) {
			((AdapterFactoryEditingDomain) editingDomain)
					.setAdapterFactory(this);
		}
	}

}
