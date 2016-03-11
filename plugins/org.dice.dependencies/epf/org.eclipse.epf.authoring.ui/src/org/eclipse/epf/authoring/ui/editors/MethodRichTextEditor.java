//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.authoring.ui.editors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.epf.authoring.ui.actions.MethodAddImageAction;
import org.eclipse.epf.authoring.ui.actions.MethodAddLinkAction;
import org.eclipse.epf.authoring.ui.forms.MethodFormToolkit;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichText;
import org.eclipse.epf.authoring.ui.richtext.IMethodRichTextEditor;
import org.eclipse.epf.authoring.ui.richtext.MethodRichTextEditorContext;
import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.IRichTextToolBar;
import org.eclipse.epf.richtext.RichTextEditor;
import org.eclipse.epf.richtext.actions.AddCodeAction;
import org.eclipse.epf.richtext.actions.AddOrderedListAction;
import org.eclipse.epf.richtext.actions.AddTableAction;
import org.eclipse.epf.richtext.actions.AddUnorderedListAction;
import org.eclipse.epf.richtext.actions.BoldAction;
import org.eclipse.epf.richtext.actions.ClearContentAction;
import org.eclipse.epf.richtext.actions.CopyAction;
import org.eclipse.epf.richtext.actions.CutAction;
import org.eclipse.epf.richtext.actions.FindReplaceAction;
import org.eclipse.epf.richtext.actions.FontNameAction;
import org.eclipse.epf.richtext.actions.FontSizeAction;
import org.eclipse.epf.richtext.actions.FontStyleAction;
import org.eclipse.epf.richtext.actions.IndentAction;
import org.eclipse.epf.richtext.actions.ItalicAction;
import org.eclipse.epf.richtext.actions.OutdentAction;
import org.eclipse.epf.richtext.actions.PasteAction;
import org.eclipse.epf.richtext.actions.SubscriptAction;
import org.eclipse.epf.richtext.actions.SuperscriptAction;
import org.eclipse.epf.richtext.actions.TidyActionGroup;
import org.eclipse.epf.richtext.actions.UnderlineAction;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorSite;

/**
 * A Rich Text editor with added extensions to support Method browsing and
 * authoring. Includes a tool bar and an integrated HTML source editor.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 */
public class MethodRichTextEditor extends RichTextEditor implements
		IMethodRichTextEditor {

	// The method element associated with this rich text control.
	private MethodElement methodElement;

	// The modal object associated with this rich text control.
	private EObject modalObject;

	// The modal object feature associated with this rich text control.
	private EStructuralFeature modalObjectFeature;

	/**
	 * Creates a new instance.
	 * 
	 * @param parent
	 *            The parent control.
	 * @param style
	 *            The initial style for the editor.
	 * @param basePath
	 *            The base path used for resolving hrefs.
	 */
	public MethodRichTextEditor(Composite parent, int style, String basePath,
			MethodElement methodElement, Label label, IEditorSite editorSite) {
		super(parent, style, editorSite, basePath);
		this.methodElement = methodElement;
		init(methodElement, label);
	}
	
	/**
	 * Creates a new instance.
	 * 
	 * @param context
	 *            The context
	 */
	public MethodRichTextEditor(MethodRichTextEditorContext context) {
		this(context.getParent(), context.getStyle(), context.getBasePath(),
				context.getMethodElement(), context.getLabel(), context.getEditorSite());
	}


	/**
	 * Creates the underlying rich text control.
	 * 
	 * @param parent
	 *            The parent composite.
	 * @param style
	 *            The style for this control.
	 * @param basePath
	 *            The path used for resolving links.
	 */
	protected IRichText createRichTextControl(Composite parent, int style,
			String basePath) {
		return MethodFormToolkit.createMethodRichText(parent, style, basePath);
	}

	/**
	 * Fills the Rich Text editor tool bar with action items.
	 * 
	 * @param toolBar
	 *            The Rich text editor tool bar.
	 */
	public void fillToolBar(IRichTextToolBar toolBar) {
		toolBar.addAction(new FontStyleAction(this));
		toolBar.addAction(new FontNameAction(this));
		toolBar.addAction(new FontSizeAction(this));
		toolBar.addSeparator();		
		toolBar.addAction(new CutAction(this));
		toolBar.addAction(new CopyAction(this));
		toolBar.addAction(new PasteAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new ClearContentAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new BoldAction(this));
		toolBar.addAction(new ItalicAction(this));
		toolBar.addAction(new UnderlineAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new SubscriptAction(this));
		toolBar.addAction(new SuperscriptAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new TidyActionGroup(this));
		toolBar.addSeparator();
		toolBar.addAction(new AddOrderedListAction(this));
		toolBar.addAction(new AddUnorderedListAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new OutdentAction(this));
		toolBar.addAction(new IndentAction(this));
		toolBar.addSeparator();
		toolBar.addAction(new FindReplaceAction(this) {
			@Override
			public void execute(IRichText richText) {
				richText.getFindReplaceAction().execute(richText);
			}
		});
		toolBar.addSeparator();
		toolBar.addAction(new MethodAddLinkAction(this));
		toolBar.addAction(new MethodAddImageAction(this));
		toolBar.addAction(new AddTableAction(this));
		toolBar.addAction(new AddCodeAction(this));
	}

	/**
	 * Returns the method element associated with this rich text control.
	 */
	public MethodElement getMethodElement() {
		return methodElement;
	}

	/**
	 * Returns the modal object associated with this rich text control.
	 */
	public EObject getModalObject() {
		return modalObject;
	}

	/**
	 * Sets the modal object associated with this rich text control.
	 */
	public void setModalObject(EObject modalObject) {
		this.modalObject = modalObject;
	}

	/**
	 * Returns modal object feature associated with this rich text control.
	 */
	public EStructuralFeature getModalObjectFeature() {
		return modalObjectFeature;
	}

	/**
	 * Sets the modal object feature associated with this rich text control.
	 */
	public void setModalObjectFeature(EStructuralFeature modalObjectFeature) {
		this.modalObjectFeature = modalObjectFeature;
	}

	public void init(MethodElement element, Label label) {
		if (richText instanceof IMethodRichText)
			((IMethodRichText)richText).init(methodElement, label);
	}
	
	
	public void collapse() {	
	}
}
