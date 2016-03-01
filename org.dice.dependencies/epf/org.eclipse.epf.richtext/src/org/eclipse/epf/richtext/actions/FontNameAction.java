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
package org.eclipse.epf.richtext.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.RichTextCommand;
import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * Sets the font name for the selected text in a rich text control.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 */
public class FontNameAction extends RichTextComboAction {

	/**
	 * Creates a new instance.
	 */
	public FontNameAction(final IRichText richText) {
		super(richText);
		setToolTipText(RichTextResources.fontNameAction_toolTipText);
		// get system fonts
		SortedSet<String> fontSet = new TreeSet<String>();
		FontData[] fonts = Display.getCurrent().getFontList(null, true);
		for (int i = 0; i < fonts.length; i++) {
			fontSet.add(((FontData) fonts[i]).getName());
		}
		input = new ArrayList<String>();
		input.add(RichTextResources.fontNameAction_DefaultFontName);
		for (Iterator<String> iter = fontSet.iterator(); iter.hasNext();) {
			String fontName = iter.next();
			input.add(fontName);
		}
		
		// add listener
		richText.addListener(SWT.SELECTED, new Listener() {
			public void handleEvent(Event event) {
				String fontName = richText.getSelected().getFontName();
				if (fontName
						.equals(RichTextResources.fontNameAction_CSS_Default)
						|| fontName
								.equals(RichTextResources.fontNameAction_CSS_Default_Mozilla)
						|| fontName.equals("default")) { //$NON-NLS-1$
					fontName = RichTextResources.fontNameAction_DefaultFontName;
				}
				int index = findFontNameInItems(fontName);
				setNotifyListeners(false);
				getCCombo().select(index);
				setNotifyListeners(true);
			}
		});
	}

	private int findFontNameInItems(String fontName) {
		int index = -1;
		for (Iterator<String> iter = input.iterator(); iter.hasNext();) {
			String font = iter.next();
			index++;
			if (font.equalsIgnoreCase(fontName)) {
				return index;
			}
		}
		return -1;
	}
	
	/**
	 * Executes the action.
	 * 
	 * @param richText
	 *            a rich text control
	 * @param index
	 *            the index of the selected item
	 */
	public void execute(IRichText richText) {
		if (richText != null) {
			String selected = getCComboSelection();
			if (selected.equals(RichTextResources.fontNameAction_DefaultFontName)) {
				richText.executeCommand(RichTextCommand.SET_FONT_NAME, ""); //$NON-NLS-1$
			} else {
				richText.executeCommand(RichTextCommand.SET_FONT_NAME, selected);
			}
		}
	}

	@Override
	public Collection<String> getInput() {
		return input;
	}

}