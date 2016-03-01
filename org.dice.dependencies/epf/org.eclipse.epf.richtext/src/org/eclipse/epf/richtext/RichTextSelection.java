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
// Copyright (c) 2005, 2007 IBM Corporation and others.
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
 * Models a text selection in a rich text control and editor.
 * 
 * @author Jeff Hardy
 * @since 1.2
 */
public class RichTextSelection {

	// The control's text selection.
	protected String text = ""; //$NON-NLS-1$

	// The control's text selection offset
	protected int offsetStart = 0;

	// The control's text selection font
	protected String fontName = ""; //$NON-NLS-1$

	// The control's text selection font size
	// could be in point size (12pt), or HTML size (1-7)
	protected String fontSize = ""; //$NON-NLS-1$

	// The control's text current style
	protected String blockStyle = ""; //$NON-NLS-1$

	// The control's text current flags
	protected int flags = 0;

	// Text status flags
	public static final int BOLD = 1;

	public static final int ITALIC = BOLD << 1;

	public static final int UNDERLINE = ITALIC << 1;

	public static final int SUBSCRIPT = UNDERLINE << 1;

	public static final int SUPERSCRIPT = SUBSCRIPT << 1;

	public String getBlockStyle() {
		return blockStyle;
	}

	public void setBlockStyle(String blockStyle) {
		this.blockStyle = blockStyle;
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public int getOffsetStart() {
		return offsetStart;
	}

	public void setOffsetStart(int offsetStart) {
		this.offsetStart = offsetStart;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isBold() {
		return (flags & BOLD) != 0;
	}

	public boolean isItalic() {
		return (flags & ITALIC) != 0;
	}

	public boolean isUnderLine() {
		return (flags & UNDERLINE) != 0;
	}

	public boolean isSubscript() {
		return (flags & SUBSCRIPT) != 0;
	}

	public boolean isSuperscript() {
		return (flags & SUPERSCRIPT) != 0;
	}

	/**
	 * Clears the selection info
	 */
	public void clear() {
		text = ""; //$NON-NLS-1$
		offsetStart = 0;
		fontName = ""; //$NON-NLS-1$
		fontSize = ""; //$NON-NLS-1$
		blockStyle = ""; //$NON-NLS-1$
		flags = 0;
	}

	public String toString() {
		String str = "Text: " + text + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
		str += "fontName: " + fontName + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
		str += "fontSize: " + fontSize + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
		str += "blockStyle: " + blockStyle + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
		str += "flags: " + flags + "\n"; //$NON-NLS-1$ //$NON-NLS-2$
		return str;
	}

}
