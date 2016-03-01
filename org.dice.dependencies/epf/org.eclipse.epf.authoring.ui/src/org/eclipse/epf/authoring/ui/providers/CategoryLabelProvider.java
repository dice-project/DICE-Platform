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
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * Label provider for Custom Category view of configuration page
 * 
 * @author Jeff Hardy
 */

public class CategoryLabelProvider extends AdapterFactoryLabelProvider {

	/**
	 * Creates an instance
	 * @param contentProvider
	 */
	public CategoryLabelProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	protected Image getImageFromObject(Object object) {
		return ExtendedImageRegistry.getInstance().getImage(object);
	}

	/**
	 * This implements {@link ILabelProvider}.getText by forwarding it to
	 * an object that implements
	 * {@link IItemLabelProvider#getText IItemLabelProvider.getText}
	 */
	public String getText(Object object) {
		if (object instanceof ProcessComponent) {
			Process proc = (Process) ((ProcessComponent) object)
					.getProcess();
			if (proc != null)
				return proc.getPresentationName();
			else
				// if process is null, return processcomponent name
				return ((ProcessComponent) object).getName();
		} else
			return super.getText(object);
	}

	/**
	 * @see org.eclipse.jface.viewers.LabelProvider#dispose()
	 */
	public void dispose() {
		super.dispose();
	}
}
