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
package org.eclipse.epf.library.ui.views;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.provider.UmaItemProviderAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * The label provider for the process tree viewer.
 * 
 * @author Kelvin Low
 * @since 1.2
 */
public class ProcessTreeLabelProvider extends LabelProvider {

	private static ILabelProvider labelProvider = new AdapterFactoryLabelProvider(
			new UmaItemProviderAdapterFactory());

	/**
	 * Creates a new instance.
	 */
	public ProcessTreeLabelProvider() {
		super();
	}

	/**
	 * @see LabelProvider#getText(Object)
	 */
	public String getText(Object obj) {
		if (obj instanceof ProcessTreeUIFolder) {
			return ((ProcessTreeUIFolder) obj).getName();
		} else if (obj instanceof MethodElement) {
			return ((MethodElement) obj).getName();
		}
		return labelProvider.getText(obj);
	}

	/**
	 * @see LabelProvider#getImage(Object)
	 */
	public Image getImage(Object obj) {
		if (obj instanceof ProcessTreeUIFolder) {
			return ((ProcessTreeUIFolder) obj).getImage();
		}
		return labelProvider.getImage(obj);
	}

}
