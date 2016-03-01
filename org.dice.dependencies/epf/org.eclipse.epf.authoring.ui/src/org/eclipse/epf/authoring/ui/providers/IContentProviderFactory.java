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
package org.eclipse.epf.authoring.ui.providers;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.epf.library.edit.IFilter;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IViewPart;

/**
 * @author Phong Nguyen Le
 * @since 1.5
 *
 */
public interface IContentProviderFactory {
	IContentProvider createProvider(AdapterFactory adapterFactory, IViewPart view);
	IFilter createFilter(MethodConfiguration config, Viewer viewer);
}
