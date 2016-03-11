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
package org.eclipse.epf.richtext.actions;

import org.eclipse.epf.common.utils.XMLUtil;
import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.RichTextCommand;
import org.eclipse.epf.richtext.RichTextEditor;
import org.eclipse.epf.richtext.RichTextImages;
import org.eclipse.epf.richtext.RichTextPlugin;
import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.epf.richtext.dialogs.FindReplaceDialog;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

/**
 * Finds and replaces text in a rich text control.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class FindReplaceAction extends RichTextAction {

	/**
	 * Finds text sub action.
	 */
	public static final int FIND_TEXT = 1;

	/**
	 * Replaces text sub action.
	 */
	public static final int REPLACE_TEXT = 2;

	/**
	 * Replaces and finds text sub action.
	 */
	public static final int REPLACE_FIND_TEXT = 3;

	/**
	 * Replaces all sub action.
	 */
	public static final int REPLACE_ALL_TEXT = 4;

	/**
	 * Find match in a forward direction.
	 */
	public static final int FORWARD_MATCH = 1;

	/**
	 * Find match in a backward direction.
	 */
	public static final int BACKWARD_MATCH = -1;

	/**
	 * Whole word match.
	 */
	public static final int WHOLE_WORD_MATCH = 2;

	/**
	 * Case sensitive match.
	 */
	public static final int CASE_SENSITIVE_MATCH = 4;

	// Encoded single quote.
	protected static final String ENCODED_SINGLE_QUOTE = "%sq%"; //$NON-NLS-1$

	protected IRichText richText;

	protected StyledText styledText;

	protected boolean foundMatch = false;
	
	// the dialog instance
	protected FindReplaceDialog dialog;

	/**
	 * Creates a new instance.
	 */
	public FindReplaceAction(IRichText richText) {
		super(richText, IAction.AS_PUSH_BUTTON);
		setImageDescriptor(RichTextImages.IMG_DESC_FIND_REPLACE);
		setDisabledImageDescriptor(RichTextImages.DISABLED_IMG_DESC_FIND_REPLACE);
		setToolTipText(RichTextResources.findReplaceAction_toolTipText);
	}

	/**
	 * Returns <code>true</code> if this action should be disabled when the
	 * rich text editor is in readonly mode.
	 */
	public boolean disableInReadOnlyMode() {
		return false;
	}

	/**
	 * Returns <code>true</code> if this action should be disabled when the
	 * rich text editor is in source edit mode.
	 */
	public boolean disableInSourceMode() {
		return false;
	}

	/**
	 * Executes the action.
	 * 
	 * @param richText
	 *            a rich text control
	 */
	public void execute(IRichText richText) {
		if (this.richText == null)
			this.richText = richText;
		if (this.richText != null) {
			try {
				if (dialog != null) {
					dialog.setFindOnly(!this.richText.getEditable());
					dialog.open();
				} else {
					dialog = new FindReplaceDialog(Display
							.getCurrent().getActiveShell(), this, !this.richText
							.getEditable());
					dialog.open();
				}
			} catch (Exception e) {
				RichTextPlugin.getDefault().getLogger().logError(e);
			}
		}
	}

	/**
	 * Returns <code>true</code> if a match is found.
	 * 
	 * @return <code>true</code> if a match is found.
	 */
	public boolean getFoundMatch() {
		return foundMatch;
	}

	/**
	 * Executes the action.
	 * 
	 * @param subAction
	 *            the sub action to execute
	 * @param findText
	 *            the find text
	 * @param replaceText
	 *            the replace text
	 * @param matchDir
	 *            the match direction; the value can either be
	 *            <code>FIND_FORWARD</code> or <code>FIND_BACKWARD</code>.
	 * @param matchOptions
	 *            the match options
	 */
	public void run(int subAction, String findText, String replaceText,
			int matchDir, int matchOptions) {
		styledText = null;
		if (richText instanceof RichTextEditor
				&& ((RichTextEditor) richText).isHTMLTabSelected()) {
			styledText = ((RichTextEditor) richText).getSourceEdit();
		}
		if (styledText == null) {
			if (findText.indexOf("'") != -1) { //$NON-NLS-1$
				findText = findText.replaceAll("'", ENCODED_SINGLE_QUOTE); //$NON-NLS-1$
			}
			if (replaceText.indexOf("'") != -1) { //$NON-NLS-1$
				replaceText = replaceText.replaceAll("'", ENCODED_SINGLE_QUOTE); //$NON-NLS-1$
			}
		}
		try {
			foundMatch = false;
			int status = 0;
			switch (subAction) {
			case FIND_TEXT:
				status = findText(findText, matchDir, matchOptions);
				break;
			case REPLACE_TEXT:
				status = replaceText(replaceText, matchDir, matchOptions);
				break;
			case REPLACE_FIND_TEXT:
				status = replaceFindText(findText, replaceText, matchDir,
						matchOptions);
				break;
			case REPLACE_ALL_TEXT:
				replaceAll(findText, replaceText, matchOptions);
				break;
			}
			if (status > 0)
				foundMatch = true;
		} catch (Exception e) {
			RichTextPlugin.getDefault().getLogger().logError(e);
		}
	}

	/**
	 * Escapes the given text.
	 * 
	 * @param text
	 *            text to be escaped
	 */
	protected static String escape(String text) {
		if (text == null || text.length() == 0)
			return ""; //$NON-NLS-1$
		StringBuffer sb = new StringBuffer();
		int textSize = text.length();
		for (int i = 0; i < textSize; i++) {
			char ch = text.charAt(i);
			switch (ch) {
			case '<':
				sb.append(XMLUtil.XML_LT);
				break;
			case '>':
				sb.append(XMLUtil.XML_GT);
				break;
			case '&':
				sb.append(XMLUtil.XML_AMP);
				break;
			default:
				sb.append(ch);
				break;
			}
		}
		return sb.toString();
	}

	protected int findText(String findText, int matchDir, int matchOptions) {
		int status = 0;
		if (styledText != null) {
			status = styledTextFindTextAndSelect(findText, matchDir,
					matchOptions);
		} else {
			status = richText
					.executeCommand(RichTextCommand.FIND_TEXT, new String[] {
							findText, "" + matchDir, "" + matchOptions }); //$NON-NLS-1$ //$NON-NLS-2$				
		}
		return status;
	}

	protected int replaceText(String replaceText, int matchDir, int matchOptions) {
		int status = 0;
		if (styledText != null) {
			status = styledTextReplaceTextAndSelect(replaceText);
		} else {
			status = richText.executeCommand(RichTextCommand.REPLACE_TEXT,
					new String[] { replaceText,
							"" + matchDir, "" + matchOptions }); //$NON-NLS-1$ //$NON-NLS-2$					
		}
		return status;
	}

	protected int replaceFindText(String findText, String replaceText,
			int matchDir, int matchOptions) {
		int status = 0;
		if (styledText != null) {
			styledTextReplaceTextAndSelect(replaceText);
			status = styledTextFindTextAndSelect(findText, matchDir,
					matchOptions);
		} else {
			richText.executeCommand(RichTextCommand.REPLACE_TEXT, new String[] {
					replaceText, "" + matchDir, "" + matchOptions }); //$NON-NLS-1$ //$NON-NLS-2$
			status = richText
					.executeCommand(RichTextCommand.FIND_TEXT, new String[] {
							findText, "" + matchDir, "" + matchOptions }); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return status;
	}

	protected void replaceAll(String findText, String replaceText,
			int matchOptions) {
		if (styledText != null) {
			styledTextReplaceAll(findText, replaceText, matchOptions);
		} else {
			richText.executeCommand(RichTextCommand.REPLACE_ALL_TEXT,
					new String[] { escape(findText), escape(replaceText),
							"" + matchOptions }); //$NON-NLS-1$
		}
	}

	protected int styledTextFindTextAndSelect(String findText, int matchDir,
			int matchOptions) {
		Point selectionOffset = styledText.getSelectionRange();
		int firstSelectedOffset = selectionOffset.x;
		int lastSelectedOffset = selectionOffset.x + selectionOffset.y - 1;
		String htmlText = styledText.getText();
		int indexOfMatch = -1;
		if ((matchOptions & CASE_SENSITIVE_MATCH) == 0) {
			// TODO: use toUpperCase(Locale) once library has locale attribute
			htmlText = htmlText.toUpperCase();
			findText = findText.toUpperCase();
		}
		do {
			if (indexOfMatch != -1) {
				lastSelectedOffset = indexOfMatch + 1;
				firstSelectedOffset = indexOfMatch - 1;
			}
			if (matchDir == FORWARD_MATCH) {
				indexOfMatch = htmlText.indexOf(findText,
						lastSelectedOffset + 1);
			} else {
				indexOfMatch = htmlText.lastIndexOf(findText,
						firstSelectedOffset - 1);
			}
		} while (indexOfMatch != -1
				&& ((matchOptions & WHOLE_WORD_MATCH) == WHOLE_WORD_MATCH)
				&& isPartOfWord(htmlText, indexOfMatch, findText.length()));
		if (indexOfMatch != -1) {
			styledText.setSelectionRange(indexOfMatch, findText.length());
			styledText.showSelection();
		} else {
			String selectedText = styledText.getSelectionText();
			if ((matchOptions & CASE_SENSITIVE_MATCH) == 0) {
				selectedText = selectedText.toUpperCase();
			}
			if (selectedText.equals(findText)) {
				indexOfMatch = styledText.getSelectionRange().x;
			}
		}
		return indexOfMatch;

	}

	protected int styledTextReplaceTextAndSelect(String replaceText) {
		Point selectionOffset = styledText.getSelectionRange();
		styledText.replaceTextRange(selectionOffset.x, selectionOffset.y,
				replaceText);
		styledText.setSelectionRange(selectionOffset.x, replaceText.length());

		return 1;
	}

	protected void styledTextReplaceAll(String findText, String replaceText,
			int matchOptions) {
		styledText.setSelectionRange(0, 0);
		while (styledTextFindTextAndSelect(findText, FORWARD_MATCH,
				matchOptions) != -1) {
			styledTextReplaceTextAndSelect(replaceText);
		}
	}

	protected boolean isWordChar(char c) {
		if (Character.isLetterOrDigit(c))
			return true;
		return false;
	}

	protected boolean isPartOfWord(String text, int index, int length) {
		if (index > 0)
			if (isWordChar(text.charAt(index - 1)))
				return true;
		if (text.length() >= index + length)
			if (isWordChar(text.charAt(index + length)))
				return true;
		return false;
	}

	public IRichText getRichText() {
		return richText;
	}

	public void setRichText(IRichText richText) {
		this.richText = richText;
	}
	
	public void dispose() {
		if (dialog != null) {
			dialog.close();
			dialog = null;
		}
	}

}
