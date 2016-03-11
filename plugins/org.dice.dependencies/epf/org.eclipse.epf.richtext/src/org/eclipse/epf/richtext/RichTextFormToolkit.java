/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
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

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * A helper toolkit for creating a rich text control and editor that can be
 * added to an Eclipse form.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class RichTextFormToolkit {

	/**
	 * Creates a rich text control and adapts it to be used in a form.
	 * 
	 * @param toolkit
	 *            the form toolkit
	 * @param parent
	 *            the parent control
	 * @param text
	 *            the initial text for the viewer
	 * @param style
	 *            the initial style for the viewer
	 * @param basePath
	 *            the base path used for resolving hrefs
	 * @return a new <code>IRichText</code> instance
	 */
	public static IRichText createRichText(FormToolkit toolkit,
			Composite parent, String text, int style) {
		IRichText richText = new RichText(parent, toolkit.getBorderStyle()
				| style | toolkit.getOrientation());
		richText.getControl().setData(FormToolkit.KEY_DRAW_BORDER,
				FormToolkit.TEXT_BORDER);
		if (text != null) {
			richText.setText(text);
		}
		return richText;
	}

	/**
	 * Creates a rich text editor and adapts it to be used in a form.
	 * 
	 * @param toolkit
	 *            the form toolkit
	 * @param parent
	 *            the parent control
	 * @param text
	 *            the initial text for the viewer
	 * @param style
	 *            the initial style for the viewer
	 * @param basePath
	 *            the base path used for resolving hrefs
	 * @return a new <code>IRichText</code> instance
	 */
	public static IRichTextEditor createRichTextEditor(FormToolkit toolkit,
			Composite parent, String text, int style, IEditorSite editorSite) {
		IRichTextEditor richTextEditor = new RichTextEditor(parent, toolkit
				.getBorderStyle()
				| style | toolkit.getOrientation(), editorSite);
		richTextEditor.getControl().setData(FormToolkit.KEY_DRAW_BORDER,
				FormToolkit.TEXT_BORDER);
		if (text != null) {
			richTextEditor.setInitialText(text);
		}
		return richTextEditor;
	}

}
