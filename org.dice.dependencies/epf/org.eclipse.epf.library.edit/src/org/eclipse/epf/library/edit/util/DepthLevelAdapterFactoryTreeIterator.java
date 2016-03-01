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
package org.eclipse.epf.library.edit.util;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;

/**
 * @author Phong Nguyen Le
 * @since 1.2
 */
public class DepthLevelAdapterFactoryTreeIterator<E> extends
		AdapterFactoryTreeIterator<E> {

	private static final long serialVersionUID = 252717827762000822L;

	private int depthLevel;

	public DepthLevelAdapterFactoryTreeIterator(AdapterFactory adapterFactory,
			Object object, boolean includeRoot) {
		super(adapterFactory, object, includeRoot);
	}

	public DepthLevelAdapterFactoryTreeIterator(AdapterFactory adapterFactory,
			E object) {
		super(adapterFactory, object);
	}

	public E next() {
		depthLevel = size();
		return super.next();
	}
	
	public int getDepthLevel() {
		return depthLevel;
	}
}
