//------------------------------------------------------------------------------
// Copyright (c) 2005, 2008 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.library.ui.providers;

import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Phong Nguyen Le
 * @since 1.5
 *
 */
public class DelegateLabelProvider extends LabelProvider {
	private IItemLabelProvider itemLabelProvider;

	public DelegateLabelProvider(IItemLabelProvider itemLabelProvider) {
		super();
		this.itemLabelProvider = itemLabelProvider;
	}
	
	@Override
	public String getText(Object element) {
		return itemLabelProvider.getText(element);
	}
	
	@Override
	public Image getImage(Object element) {
		return ExtendedImageRegistry.getInstance().getImage(itemLabelProvider.getImage(element));
	}
}
