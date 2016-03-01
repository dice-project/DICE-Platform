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

import org.eclipse.epf.authoring.ui.editors.MethodElementEditorInput;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.editor.FormEditor;


/**
 * The base class for all Process form pages.
 * 
 * @author Phong Nguyen Le
 * @author Kelvin Low
 * @since 1.0
 */
public class ProcessFormPage extends DescriptionFormPage {

	protected Process process;

	/**
	 * Creates a new instance.
	 * 
	 * @param editor
	 *            The parent form editor.
	 * @param id
	 *            The unique ID for the form page.
	 * @param title
	 *            The title for the form page.
	 */
	public ProcessFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}

	/**
	 * @see org.eclipse.epf.authoring.ui.forms.BaseFormPage#init(org.eclipse.ui.IEditorSite, org.eclipse.ui.IEditorInput)
	 */
	public void init(IEditorSite site, IEditorInput input) {
		super.init(site, input);
		
		//Always disable auto-gen-name for other sub classes other than ProcessDescription
		if (! (this instanceof ProcessDescription)) {
			setAutoGenName(false);
		}

		// Get the Process object from the editor input.
		MethodElementEditorInput methodElementInput = (MethodElementEditorInput) input;

		Object obj = methodElementInput.getMethodElement();
		if (obj instanceof ProcessComponent) {
			process = ((ProcessComponent) obj).getProcess();
		}
	}

	@Override
	protected Object getContentElement() {
		return process;
	}

}
