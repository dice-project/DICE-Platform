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

import org.eclipse.epf.richtext.IRichText;
import org.eclipse.epf.richtext.RichTextCommand;
import org.eclipse.epf.richtext.RichTextResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * Sets the font size for the selected text in a rich text control.
 * 
 * @author Kelvin Low
 * @author Jeff Hardy
 * @since 1.0
 */
public class FontSizeAction extends RichTextComboAction {

	/**
	 * Creates a new instance.
	 */
	public FontSizeAction(final IRichText richText) {
		super(richText);
		setToolTipText(RichTextResources.fontSizeAction_toolTipText);
		input = new ArrayList<String>();

		input.add("Default"); //$NON-NLS-1$
		input.add("1"); //$NON-NLS-1$
		input.add("2"); //$NON-NLS-1$
		input.add("3"); //$NON-NLS-1$
		input.add("4"); //$NON-NLS-1$
		input.add("5"); //$NON-NLS-1$
		input.add("6"); //$NON-NLS-1$
		input.add("7"); //$NON-NLS-1$		
		// add listener
		richText.addListener(SWT.SELECTED, new Listener() {
			public void handleEvent(Event event) {
				// mozilla returns "default" if no size is applied
				// IE returns 2 if no size is applied
				String fontSize = richText.getSelected().getFontSize();
				int index = -1;
				if (fontSize.equals("default")) { //$NON-NLS-1$
					index = 0;
				} else {
					try {
						index = Integer.parseInt(fontSize);
					} catch (NumberFormatException e) {
						// leave index at -1 so nothing is selected
					}
				}
				setNotifyListeners(false);
				getCCombo().select(index);
				setNotifyListeners(true);
			}
		});
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
			if ("Default".equals(selected)) { //$NON-NLS-1$
				richText.executeCommand(RichTextCommand.SET_FONT_SIZE, ""); //$NON-NLS-1$
			} else {
				richText.executeCommand(RichTextCommand.SET_FONT_SIZE,
						selected);
			}
		}
	}

	@Override
	public Collection<String> getInput() {
		return input;
	}

}