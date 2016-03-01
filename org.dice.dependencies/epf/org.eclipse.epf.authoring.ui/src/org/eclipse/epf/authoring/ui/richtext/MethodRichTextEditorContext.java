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
package org.eclipse.epf.authoring.ui.richtext;

import org.eclipse.epf.uma.MethodElement;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorSite;

public class MethodRichTextEditorContext extends MethodRichTextContext {

	private MethodElement methodElement;
	private Label label;
	private IEditorSite editorSite;
	
	public MethodRichTextEditorContext(Composite parent, int style, String basePath, MethodElement methodElement, Label label, IEditorSite editorSite) {
		super(parent, style, basePath);
		this.methodElement = methodElement;
		this.label = label;
		this.editorSite = editorSite;
	}

	public MethodElement getMethodElement() {
		return methodElement;
	}

	public void setMethodElement(MethodElement methodElement) {
		this.methodElement = methodElement;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public IEditorSite getEditorSite() {
		return editorSite;
	}

	public void setEditorSite(IEditorSite editorSite) {
		this.editorSite = editorSite;
	}

}
