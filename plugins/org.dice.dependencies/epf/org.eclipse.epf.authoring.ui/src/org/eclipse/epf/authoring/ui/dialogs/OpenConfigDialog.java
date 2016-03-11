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

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.LibraryServiceUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Jinhua Xi
 * @since 1.0
 * @deprecated
 */
public class OpenConfigDialog extends Dialog {
	private static final String NEW_CONFIG_TEXT = AuthoringUIResources.OpenConfigDialognewconfig_text; 

	// Combo combo_configs;
	String seelctedName;

	Button[] ctrl_configs = null;

	Text ctrl_new_config = null;

	public OpenConfigDialog(Shell parent) {
		super(parent);
	}

	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout();
		area.setLayout(gridLayout);
		{
			Group g = new Group(area, SWT.NONE);
			{
				GridData gridData = new GridData(GridData.FILL_BOTH);
				g.setLayoutData(gridData);
				GridLayout layout = new GridLayout();
				g.setLayout(layout);
			}

			String[] names = LibraryServiceUtil.getMethodConfigurationNames(
					LibraryService.getInstance().getCurrentMethodLibrary());
			if (names != null) {
				ctrl_configs = new Button[names.length + 1];
				for (int i = 0; i < ctrl_configs.length; i++) {
					String name = NEW_CONFIG_TEXT;
					if (i < names.length) {
						name = names[i];
					}
					ctrl_configs[i] = new Button(g, SWT.RADIO);
					ctrl_configs[i].setText(name);
					{
						GridData gridData = new GridData(
								GridData.FILL_HORIZONTAL);
						ctrl_configs[i].setLayoutData(gridData);
					}
				}
			}

			ctrl_new_config = new Text(g, SWT.BORDER);
			{
				GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
				gridData.horizontalIndent = 10;
				ctrl_new_config.setLayoutData(gridData);
			}

			// set image and title
			super
					.getShell()
					.setText(
							AuthoringUIResources.OpenConfigDialogopenconfig_text); 
		}

		return area;
	}

	/**
	 * Called when the OK button is selected.
	 */
	protected void okPressed() {
		seelctedName = null;
		for (int i = 0; i < ctrl_configs.length; i++) {
			if (ctrl_configs[i].getSelection()) {
				seelctedName = ctrl_configs[i].getText();
				break;
			}
		}

		if (seelctedName == null) {
			return;
		}

		if (seelctedName.equals(NEW_CONFIG_TEXT)) {
			seelctedName = ctrl_new_config.getText();
		}

		if (seelctedName == null || seelctedName.equals("")) { //$NON-NLS-1$
			return;
		}

		super.okPressed();
	}

	public String getSelection() {
		if (super.open() == Dialog.OK) {
			return seelctedName;
		} else {
			return null;
		}
	}

}
