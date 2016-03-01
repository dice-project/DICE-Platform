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
package org.eclipse.epf.search.ui.internal;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

/**
 * @author Phong Nguyen Le
 * @since 1.5
 *
 */
public interface ISearchResultProviderFactory {
	ITreeContentProvider createTreeContentProvider();
	
	IStructuredContentProvider createTableContentProvider();
	
	ILabelProvider createLabelProvider();
}
