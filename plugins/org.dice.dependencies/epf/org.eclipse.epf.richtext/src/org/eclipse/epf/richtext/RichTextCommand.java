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

/**
 * Defines the editing commands supported by the default rich text control and
 * editor.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class RichTextCommand {

	/**
	 * Adds a HTML fragment.
	 */
	public static final String ADD_HTML = "addHTML"; //$NON-NLS-1$	

	/**
	 * Adds an image.
	 */
	public static final String ADD_IMAGE = "addImage"; //$NON-NLS-1$

	/**
	 * Adds a horizontal line.
	 */
	public static final String ADD_LINE = "addLine"; //$NON-NLS-1$

	/**
	 * Adds a link.
	 */
	public static final String ADD_LINK = "addLink"; //$NON-NLS-1$

	/**
	 * Inserts text over the current selection.
	 */
	public static final String INSERT_TEXT = "insertText"; //$NON-NLS-1$

	/**
	 * Adds an ordered list.
	 */
	public static final String ADD_ORDERED_LIST = "addOrderedList"; //$NON-NLS-1$

	/**
	 * Adds a table.
	 */
	public static final String ADD_TABLE = "addTable"; //$NON-NLS-1$

	/**
	 * Adds an unordered list.
	 */
	public static final String ADD_UNORDERED_LIST = "addUnorderedList"; //$NON-NLS-1$

	/**
	 * Sets the background color of the selected text.
	 */
	public static final String BACKGROUND_COLOR = "backColor"; //$NON-NLS-1$

	/**
	 * Toggles the 'bold' attribute of the selected text.
	 */
	public static final String BOLD = "bold"; //$NON-NLS-1$

	/**
	 * Clears the rich text content.
	 */
	public static final String CLEAR_CONTENT = "clearContent"; //$NON-NLS-1$

	/**
	 * Copies the selected text to the clipboard.
	 */
	public static final String COPY = "copy"; //$NON-NLS-1$

	/**
	 * Cuts the selected text to the clipboard.
	 */
	public static final String CUT = "cut"; //$NON-NLS-1$

	/**
	 * Deletes the selected text.
	 */
	public static final String DELETE = "deleteText"; //$NON-NLS-1$

	/**
	 * Finds text.
	 */
	public static final String FIND_TEXT = "findText"; //$NON-NLS-1$

	/**
	 * Gets the HTML source.
	 */
	public static final String GET_TEXT = "getText"; //$NON-NLS-1$	

	/**
	 * Sets the foreground color of the selected text.
	 */
	public static final String FORGROUND_COLOR = "foreColor"; //$NON-NLS-1$

	/**
	 * Formats the selected text.
	 */
	public static final String FORMAT_BLOCK = "formatBlock"; //$NON-NLS-1$

	/**
	 * Returns the selected text.
	 */
	public static final String GET_SELECTED_TEXT = "getSelectedText"; //$NON-NLS-1$

	/**
	 * Indents the selected text.
	 */
	public static final String INDENT = "indent"; //$NON-NLS-1$

	/**
	 * Toggles the 'italic' attribute of the selected text.
	 */
	public static final String ITALIC = "italic"; //$NON-NLS-1$

	/**
	 * Center justifies the selected text.
	 */
	public static final String JUSTIFY_CENTER = "justifyCenter"; //$NON-NLS-1$

	/**
	 * Fully justifies the selected text.
	 */
	public static final String JUSTIFY_FULL = "justifyFull"; //$NON-NLS-1$

	/**
	 * Left justifies the selected text.
	 */
	public static final String JUSTIFY_LEFT = "justifyLeft"; //$NON-NLS-1$

	/**
	 * Right justifies the selected text.
	 */
	public static final String JUSTIFY_RIGHT = "justifyRight"; //$NON-NLS-1$

	/**
	 * Outdents the selected text.
	 */
	public static final String OUTDENT = "outdent"; //$NON-NLS-1$

	/**
	 * Pastes text from the clipboard.
	 */
	public static final String PASTE = "paste"; //$NON-NLS-1$

	/**
	 * Replaces all text.
	 */
	public static final String REPLACE_ALL_TEXT = "replaceAllText"; //$NON-NLS-1$		

	/**
	 * Replaces the selected text.
	 */
	public static final String REPLACE_TEXT = "replaceText"; //$NON-NLS-1$		

	/**
	 * Redoes the previous command.
	 */
	public static final String REDO = "redo"; //$NON-NLS-1$

	/**
	 * Removes the current formatting of the selected text.
	 */
	public static final String REMOVE_FORMAT = "removeFormat"; //$NON-NLS-1$

	/**
	 * Saves the editor
	 */
	public static final String SAVE = "save"; //$NON-NLS-1$

	/**
	 * Saves all editors
	 */
	public static final String SAVE_ALL = "saveAll"; //$NON-NLS-1$

	/**
	 * Selects all text.
	 */
	public static final String SELECT_ALL = "selectAll"; //$NON-NLS-1$

	/**
	 * Sets the font name for the selected text.
	 */
	public static final String SET_FONT_NAME = "setFontName"; //$NON-NLS-1$

	/**
	 * Sets the font size for the selected text.
	 */
	public static final String SET_FONT_SIZE = "setFontSize"; //$NON-NLS-1$

	/**
	 * Sets the font style for the selected text.
	 */
	public static final String SET_FONT_STYLE = "setFontStyle"; //$NON-NLS-1$

	/**
	 * Sets whether the content can be edited.
	 */
	public static final String SET_EDITABLE = "setEditable"; //$NON-NLS-1$

	/**
	 * Sets focus to this control.
	 */
	public static final String SET_FOCUS = "setFocus"; //$NON-NLS-1$

	/**
	 * Sets the height of this control.
	 */
	public static final String SET_HEIGHT = "setHeight"; //$NON-NLS-1$

	/**
	 * Sets the HTML source.
	 */
	public static final String SET_TEXT = "setText"; //$NON-NLS-1$

	/**
	 * Toggles the 'strike-through' attribute of the selected text.
	 */
	public static final String STRIKE_THROUGH = "strikeThrough"; //$NON-NLS-1$

	/**
	 * Toggles the subscript attribute of the selected text.
	 */
	public static final String SUBSCRIPT = "subscript"; //$NON-NLS-1$

	/**
	 * Toggles the superscript attribute of the selected text.
	 */
	public static final String SUPERSCRIPT = "superscript"; //$NON-NLS-1$

	/**
	 * Toggles the underline attribute of the selected text.
	 */
	public static final String UNDERLINE = "underline"; //$NON-NLS-1$

	/**
	 * Undoes the previous command.
	 */
	public static final String UNDO = "undo"; //$NON-NLS-1$

	/**
	 * Converts a link to normal text.
	 */
	public static final String UNLINK = "unlink"; //$NON-NLS-1$
	
	/**
	 * Add a row to the selected table.
	 */
	public static final String ADD_ROW = "addRow"; //$NON-NLS-1$
	
	/**
	 * Add a column to the selected table.
	 */
	public static final String ADD_COLUMN = "addColumn"; //$NON-NLS-1$
	
	/**
	 * Delete the last row of the selected table.
	 */
	public static final String DELETE_LAST_ROW = "deleteLastRow"; //$NON-NLS-1$
	
	/**
	 * Delete the last column of the selected table.
	 */
	public static final String DELETE_LAST_COLUMN = "deleteLastColumn"; //$NON-NLS-1$
	
}
