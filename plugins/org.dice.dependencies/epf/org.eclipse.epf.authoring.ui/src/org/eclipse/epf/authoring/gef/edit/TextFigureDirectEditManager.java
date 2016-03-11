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

package org.eclipse.epf.authoring.gef.edit;

import org.eclipse.epf.authoring.gef.figures.TextFigure;
import org.eclipse.epf.authoring.gef.util.DiagramUIResources;
import org.eclipse.epf.authoring.gef.util.TemplateConstants;
import org.eclipse.epf.authoring.ui.AuthoringUIPlugin;
import org.eclipse.epf.common.ui.util.MsgDialog;
import org.eclipse.epf.diagram.model.NamedNode;
import org.eclipse.epf.diagram.model.WorkBreakdownElementNode;
import org.eclipse.epf.library.edit.util.ProcessUtil;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.BreakdownElement;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

/**
 * Manages direct editing and creating celleditor for {@link TextFigure}
 * 
 * @author James Thario
 * @since 1.0
 */
public class TextFigureDirectEditManager extends DirectEditManager {

	Font scaledFont;

	protected VerifyListener verifyListener;

	protected TextFigure figure = null;

	public TextFigureDirectEditManager(GraphicalEditPart source,
			Class editorType, CellEditorLocator locator, TextFigure figure) {
		super(source, editorType, locator);
		this.figure = figure;
	}

	/**
	 * @see org.eclipse.gef.tools.DirectEditManager#bringDown()
	 */
	protected void bringDown() {
		bringDown(true);
	}

	/**
	 * @see org.eclipse.gef.tools.DirectEditManager#bringDown()
	 */
	protected void bringDown(boolean check) {
		// This method might be re-entered when super.bringDown() is called.
		Font disposeFont = scaledFont;
		scaledFont = null;
		super.bringDown();
		if (disposeFont != null)
			disposeFont.dispose();
		if (check) {
			NamedNode node = (NamedNode) getEditPart().getModel();
			String name = node.getName();
			if (node instanceof WorkBreakdownElementNode) {
				Object wbelement = node.getObject();
				if (wbelement instanceof Activity
						&& ProcessUtil
								.isExtendingOrLocallyContributing((BreakdownElement) wbelement)) {
					name = ProcessUtil
							.getPresentationName((BreakdownElement) wbelement);
				}

			}
			if (name == null || name.trim().length() == 0) {
				Display
						.getCurrent()
						.asyncExec(
								new PromptEdit(
										(NamedNodeEditPart) getEditPart(),
										DiagramUIResources.err_name_empty)); 
			}
		}
	}

	/**
	 * @see org.eclipse.gef.tools.DirectEditManager#initCellEditor()
	 */
	protected void initCellEditor() {
		TextCellEditor editor = (TextCellEditor) getCellEditor();
		editor.setStyle(SWT.MULTI);
		Text t = (Text) editor.getControl();
		verifyListener = new VerifyListener() {
			public void verifyText(VerifyEvent event) {
				Text text = (Text) getCellEditor().getControl();
				String oldText = text.getText();
				String leftText = oldText.substring(0, event.start);
				String rightText = oldText.substring(event.end, oldText
						.length());
				GC gc = new GC(text);
				Point size = gc.textExtent(leftText + event.text + rightText);
				gc.dispose();
				if (size.x != 0)
					size = text.computeSize(size.x, SWT.DEFAULT);
				getCellEditor().getControl().setSize(size.x, size.y);
			}
		};
		t.addVerifyListener(verifyListener);
		String initialLabelText = ((NamedNodeEditPart) this.getEditPart())
				.getDirectEditText();
		editor.setValue(initialLabelText);
		t.setFont(TemplateConstants.DEFAULT_FONT);
		t.selectAll();
	}

	/**
	 * @see org.eclipse.gef.tools.DirectEditManager#unhookListeners()
	 */
	protected void unhookListeners() {
		super.unhookListeners();
		if (getCellEditor() != null) {
			Text text = (Text) getCellEditor().getControl();
			if (verifyListener != null)
				text.removeVerifyListener(verifyListener);
			verifyListener = null;
		}
	}

	/**
	 * Checks if the given text is valid. Subclasses can override this method.
	 * 
	 * @param txt
	 * @return null if the given text is valid, an error message otherwise
	 */
	protected String validate(String txt) {
		return null;
	}

	/**
	 * @see org.eclipse.gef.tools.DirectEditManager#commit()
	 */
	protected void commit() {
		String msg = validate((String) getCellEditor().getValue());
		if (msg != null) {
			bringDown(false);
			Display.getCurrent().asyncExec(
					new PromptEdit((NamedNodeEditPart) getEditPart(), msg));
			return;
		}
		super.commit();
	}

	static class PromptEdit implements Runnable {

		private NamedNodeEditPart part;

		private String msg;

		PromptEdit(NamedNodeEditPart part, String msg) {
			this.part = part;
			this.msg = msg;
		}

		/**
		 * @see java.lang.Runnable#run()
		 */
		public void run() {
			String title = DiagramUIResources.diagram_errorDialog_title_text0; 
			MsgDialog dialog = AuthoringUIPlugin.getDefault().getMsgDialog();
			dialog.displayError(title, msg); 
			part.performDirectEdit();
		}

	}

}
