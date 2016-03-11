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
package org.eclipse.epf.authoring.ui.forms;

import org.eclipse.epf.authoring.ui.AuthoringUIResources;
import org.eclipse.epf.authoring.ui.editors.ConfigurationEditorInput;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * The Description page in the Configuration editor.
 * 
 * @author Shashidhar Kannoori
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class ConfigurationDescription extends DescriptionFormPage implements IRefreshable {

	private static final String FORM_PREFIX = AuthoringUIResources.ConfigurationDescriptionFormPrefix; 

	private MethodConfiguration config = null;

	/**
	 * Creates a new instance.
	 */
	public ConfigurationDescription(FormEditor editor) {
		super(
				editor,
				AuthoringUIResources.ConfigurationDescriptionDescription, AuthoringUIResources.ConfigurationDescriptionDescription); 
	}

	/**
	 * @see org.eclipse.ui.forms.editor.FormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		ConfigurationEditorInput configInput = (ConfigurationEditorInput) input;
		config = configInput.getConfiguration();
		versionSectionOn = false;
		detailSectionOn = false;
		variabilitySectionOn = false;
		fullDescOn = false;
		keyConsiderationOn = false;
	}

	@Override
	protected void createEditorContent(FormToolkit toolkit) {
		super.createEditorContent(toolkit);
		// Set focus on the Name text control.
		Display display = form.getBody().getDisplay();
		if (!(display == null || display.isDisposed())) {
			display.asyncExec(new Runnable() {
				public void run() {
					if(ctrl_name.isDisposed()) return;		
					if (isAutoGenName()) {
						ctrl_presentation_name.setFocus();
						ctrl_presentation_name.setSelection(0, ctrl_presentation_name.getText().length());
					} else {
						ctrl_name.setFocus();
						ctrl_name.setSelection(0, ctrl_name.getText().length());
					}
				}
			});
		}
	}

	@Override
	public void loadSectionDescription() {
		this.generalSectionDescription = AuthoringUIResources.ConfigurationDescriptionDescription_text;
	}

	@Override
	protected Object getContentElement() {
		return config;
	}
	
}