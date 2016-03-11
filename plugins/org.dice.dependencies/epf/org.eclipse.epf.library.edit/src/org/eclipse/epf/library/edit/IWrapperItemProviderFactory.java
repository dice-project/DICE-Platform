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
package org.eclipse.epf.library.edit;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;

/**
 * Factory to create {@link IWrapperItemProvider}
 * 
 * @author Phong Nguyen Le - Aug 2, 2006
 * @since  1.0
 */
public interface IWrapperItemProviderFactory {
	/**
	 * Creates wrapper of given value for the given owner
	 * 
	 * @param value
	 * @param owner
	 * @param adapterFactory
	 * @return
	 */
	IWrapperItemProvider createWrapper(Object value,
			Object owner, AdapterFactory adapterFactory);

	/**
	 * 
	 * @param value
	 * @param owner
	 * @param feature
	 * @param index The index at which the value is located. If feature is non-null, this index is within that feature.
	 * @param adapterFactory
	 * @return
	 */
	IWrapperItemProvider createWrapper(Object value,
			Object owner, EStructuralFeature feature, int index,
			AdapterFactory adapterFactory);

}
