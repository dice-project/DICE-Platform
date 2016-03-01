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
package org.eclipse.epf.library.ui.providers;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * The label provider for the method search result tree and table viewers.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class ElementTreeLabelProvider extends LabelProvider {

	private static ILabelProvider labelProvider = new AdapterFactoryLabelProvider(TngAdapterFactory.INSTANCE.getNavigatorView_ComposedAdapterFactory());

	/**
	 * Creates a new instance.
	 */
	public ElementTreeLabelProvider() {
		super();
	}

	/**
	 * @see LabelProvider#getText(Object)
	 */
	public String getText(Object obj) {
		if (obj instanceof ElementTreeContentProviderUIFolder) {
			return ((ElementTreeContentProviderUIFolder) obj).getName();
		} else if (obj instanceof MethodElement) {
			return TngUtil.getLabel(obj);
		}
		return labelProvider.getText(obj);
	}

	/**
	 * @see LabelProvider#getImage(Object)
	 */
	public Image getImage(Object obj) {
		if (obj instanceof ElementTreeContentProviderUIFolder) {
			return ((ElementTreeContentProviderUIFolder) obj).getImage();
		}
		return labelProvider.getImage(obj);
	}

}
