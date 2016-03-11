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

import org.eclipse.epf.authoring.ui.editors.MethodRichText;
import org.eclipse.epf.authoring.ui.editors.MethodRichTextEditor;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichTextEditor;
import org.eclipse.epf.authoring.ui.richtext.MethodRichTextContext;
import org.eclipse.epf.authoring.ui.richtext.MethodRichTextEditorContext;
import org.eclipse.epf.common.utils.ExtensionHelper;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.forms.widgets.FormToolkit;


/**
 * A help class for creating UI widgets adapted adapted to work in Eclipse
 * forms.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class MethodFormToolkit {

	/**
	 * Creates a Rich Text control and adapts it to be used in a form.
	 * 
	 * @param parent
	 *            The parent control.
	 * @param text
	 *            The initial text for the viewer.
	 * @param style
	 *            The initial style for the viewer.
	 * @param basePath
	 *            The base path used for resolving hrefs.
	 * @return A new <code>IMethodRichText</code> instance.
	 */
	public static IMethodRichText createRichText(FormToolkit toolkit,
			Composite parent, String text, int style, String basePath,
			MethodElement methodElement, Label label) {
		IMethodRichText richText = createMethodRichText(parent, style, basePath);
		richText.init(methodElement, label);
		richText.getControl().setData(FormToolkit.KEY_DRAW_BORDER,
				FormToolkit.TEXT_BORDER);
		if (text != null) {
			richText.setText(text);
		}
		return richText;
	}
	
	public static IMethodRichText createMethodRichText(Composite parent, int style, String basePath) {
		MethodRichTextContext context = new MethodRichTextContext(parent, style, basePath);
		Object provider = ExtensionHelper.create(IMethodRichText.class, context);
		return provider instanceof IMethodRichText ? (IMethodRichText) provider : new MethodRichText(context);
	}

	/**
	 * Creates a Rich Text editor and adapts it to be used in a form.
	 * 
	 * @param parent
	 *            The parent control.
	 * @param text
	 *            The initial text for the viewer.
	 * @param style
	 *            The initial style for the viewer.
	 * @param basePath
	 *            The base path used for resolving hrefs.
	 * @return A new <code>IMethodRichTextEditor</code> instance.
	 */
	public static IMethodRichTextEditor createRichTextEditor(
			FormToolkit toolkit, Composite parent, String text, int style,
			String basePath, MethodElement methodElement, Label label, IEditorSite editorSite) {
		IMethodRichTextEditor editor = createMethodRichTextEditor(parent, style,
				basePath, methodElement, label, editorSite);
		editor.getControl().setData(FormToolkit.KEY_DRAW_BORDER,
				FormToolkit.TEXT_BORDER);
		if (text != null) {
			editor.setText(text);
		}
		return editor;
	}
	
	public static IMethodRichTextEditor createMethodRichTextEditor(Composite parent, int style, String basePath, MethodElement methodElement, Label label, IEditorSite editorSite) {
		MethodRichTextEditorContext context = new MethodRichTextEditorContext(parent, style, basePath, methodElement, label, editorSite);
		Object provider = ExtensionHelper.create(IMethodRichTextEditor.class, context);
		return provider instanceof IMethodRichTextEditor ? (IMethodRichTextEditor) provider : new MethodRichTextEditor(context);
	}


}
