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
import org.eclipse.epf.uma.TaskDescriptor;


/**
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class TaskDescriptorWrapperItemProvider extends
		BreakdownElementWrapperItemProvider {	

	protected TaskDescriptorWrapperItemProvider(Object value, Object owner, AdapterFactory adapterFactory) {
		super(value, owner, adapterFactory);
	}

	/**
	 * @param value
	 * @param owner
	 * @param index
	 * @param feature
	 * @param adapterFactory
	 */
	public TaskDescriptorWrapperItemProvider(TaskDescriptor value,
			Object owner, EStructuralFeature feature, int index,
			AdapterFactory adapterFactory) {
		super(value, owner, feature, index, adapterFactory);
	}

	@Override
	public String getAttribute(Object object, String property) {
		if(getValue() instanceof ComposedBreakdownElementWrapperItemProvider) {
			return ((ComposedBreakdownElementWrapperItemProvider)getValue()).getAttribute(object, property);
		}
		return super.getAttribute(object, property);
	}
	
	@Override
	public boolean isInherited() {
		if(getValue() instanceof ComposedBreakdownElementWrapperItemProvider) {
			return ((ComposedBreakdownElementWrapperItemProvider)getValue()).isInherited();
		}
		return super.isInherited();
	}
}
