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
package org.eclipse.epf.richtext.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Preferences for the rich text editor.
 * 
 * @author Kelvin Low
 * @since 1.0
 */
public class RichTextPreferences {

	/**
	 * rich text Editor preference keys.
	 */
	public static final String LINE_WIDTH = "htmlEditor.lineWidth"; //$NON-NLS-1$

	public static final String INDENT = "htmlEditor.indent"; //$NON-NLS-1$

	public static final String INDENT_SIZE = "htmlEditor.indentSize"; //$NON-NLS-1$

	/**
	 * Initializes the default preferences.
	 */
	public static void initializeDefaultPreferences(IPreferenceStore store) {
		store.setDefault(LINE_WIDTH, 120);
		store.setDefault(INDENT, true);
		store.setDefault(INDENT_SIZE, 4);
	}

}
