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
package org.eclipse.epf.authoring.ui.dialogs;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.epf.library.edit.TngAdapterFactory;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.util.UmaUtil;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;


/**
 * Open an dialog to add views into configuration
 * 
 * @author Shashidhar Kannoori
 * @since 1.0
 */
public class ConfigurationAddViewsDialog extends ItemsFilterDialog {

	/**
	 * @param parentShell
	 */
	public ConfigurationAddViewsDialog(Shell parentShell) {
		super(parentShell);
	}

	protected Control createDialogArea(Composite parent) {
		labelProvider = new AdapterFactoryLabelProvider(
				TngAdapterFactory.INSTANCE
						.getNavigatorView_ComposedAdapterFactory()) {
			public String getText(Object object) {
				String presentationName = null;
				if (object instanceof DescribableElement) {
					presentationName = ((DescribableElement) object)
							.getPresentationName();
				}
				if (presentationName == null || "".equals(presentationName)) { //$NON-NLS-1$
					presentationName = super.getText(object);
				}			
				if (object instanceof CustomCategory) {
					String pluginName = UmaUtil.getMethodPlugin((EObject)object).getName();
					presentationName = presentationName + " (" + pluginName + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				}
				
				return presentationName;
			}
		};
		helper.setShowPresentationName(true);
		return super.createDialogArea(parent);
	}
}
