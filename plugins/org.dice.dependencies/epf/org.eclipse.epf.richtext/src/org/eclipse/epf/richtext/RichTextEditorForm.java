/*******************************************************************************
 * Copyright (c) 2007, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
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
package org.eclipse.epf.richtext;

import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * A composite for laying out the default rich text editor in an Eclipse form.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class RichTextEditorForm extends ViewForm {

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            the parent control
	 * @param style
	 *            the style for this control
	 */
	public RichTextEditorForm(Composite parent, int style) {
		super(parent, style);
	}

	/**
	 * @see org.eclipse.swt.widgets#getData(String key)
	 */
	public Object getData(String key) {
		if (key != null && key.equals(FormToolkit.KEY_DRAW_BORDER)) {
			// Return a text border to get the FormToolkit's BorderPainter to
			// paint a border for the rich text editor.
			return FormToolkit.TEXT_BORDER;
		}
		return super.getData();
	}

}
